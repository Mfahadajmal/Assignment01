package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.play.core.listener.ListenerRegistry;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallListenerRegistry extends ListenerRegistry {
    private static SplitInstallListenerRegistry instance;
    private final DownloadedStateInterceptor$Provider downloadedStateInterceptorProvider;
    private final Set frameworkListeners;
    private final Handler mainThreadHandler;

    public SplitInstallListenerRegistry(Context context, DownloadedStateInterceptor$Provider downloadedStateInterceptor$Provider) {
        super(new SplitInstallModule("SplitInstallListenerRegistry"), new IntentFilter("com.google.android.play.core.splitinstall.receiver.SplitInstallUpdateIntentService"), context);
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.frameworkListeners = new LinkedHashSet();
        this.downloadedStateInterceptorProvider = downloadedStateInterceptor$Provider;
    }

    public static synchronized SplitInstallListenerRegistry getInstance(Context context) {
        SplitInstallListenerRegistry splitInstallListenerRegistry;
        synchronized (SplitInstallListenerRegistry.class) {
            if (instance == null) {
                instance = new SplitInstallListenerRegistry(context, SplitCompatInterceptorProvider.INSTANCE);
            }
            splitInstallListenerRegistry = instance;
        }
        return splitInstallListenerRegistry;
    }

    @Override // com.google.android.play.core.listener.ListenerRegistry
    protected final void onReceive(final Context context, final Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("session_state");
        if (bundleExtra == null) {
            return;
        }
        DownloadedStateInterceptor$Provider downloadedStateInterceptor$Provider = this.downloadedStateInterceptorProvider;
        final SplitInstallSessionState fromBundle = SplitInstallSessionState.fromBundle(bundleExtra);
        int i = fromBundle.status;
        WorkQueue interceptor$ar$class_merging$ar$class_merging = downloadedStateInterceptor$Provider.getInterceptor$ar$class_merging$ar$class_merging();
        if (i == 3 && interceptor$ar$class_merging$ar$class_merging != null) {
            interceptor$ar$class_merging$ar$class_merging.intercept(fromBundle.splitFileIntents, new DownloadedStateInterceptor$Callback(this) { // from class: com.google.android.play.core.splitinstall.SplitInstallListenerRegistry.1
                final /* synthetic */ SplitInstallListenerRegistry this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback
                public final void whenEmulated() {
                    this.this$0.updateListenersOnMainThread(fromBundle, 5, 0);
                }

                @Override // com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback
                public final void whenFailed(int i2) {
                    this.this$0.updateListenersOnMainThread(fromBundle, 6, i2);
                }

                @Override // com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback
                public final void whenVerified() {
                    if (!intent.getBooleanExtra("triggered_from_app_after_verification", false)) {
                        intent.putExtra("triggered_from_app_after_verification", true);
                        context.sendBroadcast(intent);
                    } else {
                        this.this$0.logger$ar$class_merging$ceb098d3_0$ar$class_merging.e$ar$ds$cdf76eb7_0("Splits copied and verified more than once.", new Object[0]);
                    }
                }
            });
        } else {
            updateListeners(fromBundle);
        }
    }

    public final synchronized void updateListeners(SplitInstallSessionState splitInstallSessionState) {
        Iterator it = new LinkedHashSet(this.frameworkListeners).iterator();
        while (it.hasNext()) {
            ((HapticPatternParser$$ExternalSyntheticLambda1) it.next()).onStateUpdate(splitInstallSessionState);
        }
        super.updateListeners((Object) splitInstallSessionState);
    }

    public final void updateListenersOnMainThread(final SplitInstallSessionState splitInstallSessionState, final int i, final int i2) {
        this.mainThreadHandler.post(new Runnable(this) { // from class: com.google.android.play.core.splitinstall.SplitInstallListenerRegistry.2
            final /* synthetic */ SplitInstallListenerRegistry this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                SplitInstallSessionState splitInstallSessionState2 = splitInstallSessionState;
                this.this$0.updateListeners(new SplitInstallSessionState(splitInstallSessionState2.sessionId(), i, i2, splitInstallSessionState2.bytesDownloaded(), splitInstallSessionState2.totalBytesToDownload(), splitInstallSessionState2.moduleNamesNullable(), splitInstallSessionState2.languagesNullable(), splitInstallSessionState2.resolutionIntent(), splitInstallSessionState2.splitFileIntents()));
            }
        });
    }
}
