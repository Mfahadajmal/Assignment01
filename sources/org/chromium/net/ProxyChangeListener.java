package org.chromium.net;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.Locale;
import org.chromium.base.ContextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.net.ProxyChangeListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ProxyChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "ProxyChangeListener";
    private static boolean sEnabled = true;
    private Delegate mDelegate;
    private final Handler mHandler;
    private final Looper mLooper;
    private long mNativePtr;
    private ProxyReceiver mProxyReceiver;
    private BroadcastReceiver mRealProxyReceiver;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Delegate {
        void proxySettingsChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProxyConfig {
        public static final ProxyConfig DIRECT = new ProxyConfig("", 0, "", new String[0]);
        public final String[] mExclusionList;
        public final String mHost;
        public final String mPacUrl;
        public final int mPort;

        public ProxyConfig(String str, int i, String str2, String[] strArr) {
            this.mHost = str;
            this.mPort = i;
            this.mPacUrl = str2;
            this.mExclusionList = strArr;
        }

        public static ProxyConfig fromProxyInfo(ProxyInfo proxyInfo) {
            String str = null;
            if (proxyInfo == null) {
                return null;
            }
            String host = proxyInfo.getHost();
            if (host == null) {
                host = "";
            }
            Uri pacFileUrl = proxyInfo.getPacFileUrl();
            int port = proxyInfo.getPort();
            if (!Uri.EMPTY.equals(pacFileUrl)) {
                str = pacFileUrl.toString();
            }
            return new ProxyConfig(host, port, str, proxyInfo.getExclusionList());
        }

        public final String toString() {
            String str;
            String str2;
            if (!this.mHost.equals("localhost") && !this.mHost.isEmpty()) {
                str = "<redacted>";
            } else {
                str = this.mHost;
            }
            Locale locale = Locale.US;
            Integer valueOf = Integer.valueOf(this.mPort);
            if (this.mPacUrl == null) {
                str2 = "null";
            } else {
                str2 = "\"<redacted>\"";
            }
            return String.format(locale, "ProxyConfig [mHost=\"%s\", mPort=%d, mPacUrl=%s]", str, valueOf, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ProxyReceiver extends BroadcastReceiver {
        public ProxyReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
                ProxyChangeListener.this.runOnThread(new Runnable() { // from class: org.chromium.net.ProxyChangeListener$ProxyReceiver$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProxyChangeListener.ProxyConfig extractNewProxy;
                        ProxyChangeListener proxyChangeListener = ProxyChangeListener.this;
                        extractNewProxy = ProxyChangeListener.extractNewProxy(intent);
                        proxyChangeListener.proxySettingsChanged(extractNewProxy);
                    }
                });
            }
        }
    }

    private ProxyChangeListener() {
        Looper myLooper = Looper.myLooper();
        this.mLooper = myLooper;
        this.mHandler = new Handler(myLooper);
    }

    public static ProxyChangeListener create() {
        return new ProxyChangeListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProxyConfig extractNewProxy(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        return ProxyConfig.fromProxyInfo((ProxyInfo) extras.get("android.intent.extra.PROXY_INFO"));
    }

    public static String getProperty(String str) {
        return System.getProperty(str);
    }

    private ProxyConfig getProxyConfig(Intent intent) {
        ProxyConfig fromProxyInfo = ProxyConfig.fromProxyInfo(((ConnectivityManager) ContextUtils.sApplicationContext.getSystemService("connectivity")).getDefaultProxy());
        if (fromProxyInfo == null) {
            return ProxyConfig.DIRECT;
        }
        if (Build.VERSION.SDK_INT >= 29 && fromProxyInfo.mHost.equals("localhost") && fromProxyInfo.mPort == -1) {
            ProxyConfig extractNewProxy = extractNewProxy(intent);
            String.format(Locale.US, "configFromConnectivityManager = %s, configFromIntent = %s", fromProxyInfo, extractNewProxy);
            if (extractNewProxy == null) {
                return null;
            }
            return new ProxyConfig(extractNewProxy.mHost, extractNewProxy.mPort, fromProxyInfo.mPacUrl, fromProxyInfo.mExclusionList);
        }
        return fromProxyInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDelegateForTesting$0(Delegate delegate) {
        this.mDelegate = delegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateProxyConfigFromConnectivityManager$1(Intent intent) {
        proxySettingsChanged(getProxyConfig(intent));
    }

    private boolean onThread() {
        if (this.mLooper == Looper.myLooper()) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void proxySettingsChanged(ProxyConfig proxyConfig) {
        if (sEnabled) {
            Delegate delegate = this.mDelegate;
            if (delegate != null) {
                delegate.proxySettingsChanged();
            }
            long j = this.mNativePtr;
            if (j != 0) {
                if (proxyConfig != null) {
                    String[] strArr = proxyConfig.mExclusionList;
                    String str = proxyConfig.mPacUrl;
                    N.MyoFZt$2(j, this, proxyConfig.mHost, proxyConfig.mPort, str, strArr);
                    return;
                }
                N.MCIk73GZ(j, this);
            }
        }
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PROXY_CHANGE");
        this.mProxyReceiver = new ProxyReceiver();
        if (!ContextUtils.isSdkSandboxProcess()) {
            ContextUtils.sApplicationContext.registerReceiver(this.mProxyReceiver, new IntentFilter(), null, null, 4);
        }
        this.mRealProxyReceiver = new ProxyBroadcastReceiver(this);
        ContextUtils.registerProtectedBroadcastReceiver(ContextUtils.sApplicationContext, this.mRealProxyReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runOnThread(Runnable runnable) {
        if (onThread()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }

    public static void setEnabled(boolean z) {
        sEnabled = z;
    }

    private void unregisterBroadcastReceiver() {
        ContextUtils.sApplicationContext.unregisterReceiver(this.mProxyReceiver);
        BroadcastReceiver broadcastReceiver = this.mRealProxyReceiver;
        if (broadcastReceiver != null) {
            ContextUtils.sApplicationContext.unregisterReceiver(broadcastReceiver);
        }
        this.mProxyReceiver = null;
        this.mRealProxyReceiver = null;
    }

    public void setDelegateForTesting(Delegate delegate) {
        this.mDelegate = delegate;
    }

    public void start(long j) {
        TraceEvent scoped = TraceEvent.scoped("ProxyChangeListener.start");
        try {
            this.mNativePtr = j;
            registerBroadcastReceiver();
            if (scoped != null) {
                scoped.close();
            }
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void stop() {
        this.mNativePtr = 0L;
        unregisterBroadcastReceiver();
    }

    public void updateProxyConfigFromConnectivityManager(final Intent intent) {
        runOnThread(new Runnable() { // from class: org.chromium.net.ProxyChangeListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ProxyChangeListener.this.lambda$updateProxyConfigFromConnectivityManager$1(intent);
            }
        });
    }

    private void assertOnThread() {
    }
}
