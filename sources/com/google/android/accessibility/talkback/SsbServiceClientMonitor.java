package com.google.android.accessibility.talkback;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Looper;
import android.os.Message;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.gsa.ssb.client.SsbServiceCallback;
import com.google.android.libraries.gsa.ssb.client.SsbServiceClient;
import com.google.android.ssb.SsbProto$SsbState;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SsbServiceClientMonitor extends SameThreadBroadcastReceiver implements SsbServiceCallback {
    public static final IntentFilter INTENT_FILTER;
    private final FormFactorUtils formFactorUtils;
    public boolean isSuspended = true;
    public RetryingNameResolver.ResolutionResultListener listener$ar$class_merging$ar$class_merging;
    public final ServiceConnectionHandler serviceConnectionHandler;
    private final String ssbPackageName;
    public final SsbServiceClient ssbServiceClient;
    public int ssbState$ar$edu;

    static {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        INTENT_FILTER = intentFilter;
        intentFilter.addDataScheme("package");
    }

    public SsbServiceClientMonitor(Context context) {
        String str;
        FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
        this.formFactorUtils = formFactorUtils;
        this.ssbServiceClient = new SsbServiceClient(context);
        this.serviceConnectionHandler = new ServiceConnectionHandler(this);
        if (formFactorUtils.isAndroidWear) {
            str = "com.google.android.wearable.assistant";
        } else if (formFactorUtils.isAndroidTv) {
            str = "com.google.android.katniss";
        } else {
            str = "com.google.android.googlequicksearchbox";
        }
        this.ssbPackageName = str;
    }

    private static String ssbStateToString$ar$edu(int i) {
        if (i != 0) {
            int i2 = i - 1;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                return "UNKOWN";
                            }
                            return "PLAYING_TTS";
                        }
                        return "PROCESSING";
                    }
                    return "RECORDING";
                }
                return "LISTENING";
            }
            return "IDLE";
        }
        throw null;
    }

    public final boolean isSsbActive() {
        if (this.ssbState$ar$edu != SsbProto$SsbState.AudioState.IDLE$ar$edu$63119a3c_0 && this.ssbServiceClient.isConnected()) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver
    public final void onReceiveIntent(Intent intent) {
        if (!this.isSuspended && "android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
            String str = "";
            if (intent.getData() != null) {
                str = intent.getData().toString().replace("package:", "");
            }
            if (this.ssbPackageName.equals(str)) {
                LogUtils.v("SsbServiceClientMonitor", "Ssb installed. Try to reconnect to the service.", new Object[0]);
                this.serviceConnectionHandler.connectToService();
            }
        }
    }

    @Override // com.google.android.libraries.gsa.ssb.client.SsbServiceCallback
    public final void updateSsb(byte[] bArr) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener;
        try {
            SsbProto$SsbState ssbProto$SsbState = (SsbProto$SsbState) GeneratedMessageLite.parseFrom(SsbProto$SsbState.DEFAULT_INSTANCE, bArr);
            int forNumber$ar$edu$9af75a9c_0 = SsbProto$SsbState.AudioState.forNumber$ar$edu$9af75a9c_0(ssbProto$SsbState.audioState_);
            if (forNumber$ar$edu$9af75a9c_0 == 0) {
                forNumber$ar$edu$9af75a9c_0 = SsbProto$SsbState.AudioState.IDLE$ar$edu$63119a3c_0;
            }
            if (forNumber$ar$edu$9af75a9c_0 != 0) {
                int i = forNumber$ar$edu$9af75a9c_0 - 1;
                if ((i == 1 || i == 2 || i == 3 || i == 4) && this.ssbState$ar$edu == SsbProto$SsbState.AudioState.IDLE$ar$edu$63119a3c_0 && (resolutionResultListener = this.listener$ar$class_merging$ar$class_merging) != null) {
                    resolutionResultListener.onSsbServiceActivated();
                }
                String ssbStateToString$ar$edu = ssbStateToString$ar$edu(this.ssbState$ar$edu);
                int forNumber$ar$edu$9af75a9c_02 = SsbProto$SsbState.AudioState.forNumber$ar$edu$9af75a9c_0(ssbProto$SsbState.audioState_);
                if (forNumber$ar$edu$9af75a9c_02 == 0) {
                    forNumber$ar$edu$9af75a9c_02 = SsbProto$SsbState.AudioState.IDLE$ar$edu$63119a3c_0;
                }
                LogUtils.v("SsbServiceClientMonitor", "Update SsbState: %s to %s", ssbStateToString$ar$edu, ssbStateToString$ar$edu(forNumber$ar$edu$9af75a9c_02));
                int forNumber$ar$edu$9af75a9c_03 = SsbProto$SsbState.AudioState.forNumber$ar$edu$9af75a9c_0(ssbProto$SsbState.audioState_);
                if (forNumber$ar$edu$9af75a9c_03 == 0) {
                    forNumber$ar$edu$9af75a9c_03 = SsbProto$SsbState.AudioState.IDLE$ar$edu$63119a3c_0;
                }
                this.ssbState$ar$edu = forNumber$ar$edu$9af75a9c_03;
                return;
            }
            throw null;
        } catch (InvalidProtocolBufferException unused) {
            this.ssbState$ar$edu = SsbProto$SsbState.AudioState.IDLE$ar$edu$63119a3c_0;
            LogUtils.e("SsbServiceClientMonitor", "Cannot parse SsbState, reset SsbState to IDLE.", new Object[0]);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ServiceConnectionHandler extends WeakReferenceHandler {
        public ServiceConnectionHandler(SsbServiceClientMonitor ssbServiceClientMonitor) {
            super(ssbServiceClientMonitor, Looper.getMainLooper());
        }

        public final void connectToService() {
            removeMessages(0);
            connectToService(0);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            int i;
            PackageInfo packageInfoWithSignature;
            String str = "com.google.android.wearable.assistant";
            SsbServiceClientMonitor ssbServiceClientMonitor = (SsbServiceClientMonitor) obj;
            if (message.what == 0 && (i = message.arg1) < 5 && !ssbServiceClientMonitor.ssbServiceClient.isConnected()) {
                try {
                    LogUtils.v("SsbServiceClientMonitor", "Connecting to Ssb service... Attempt: %d", Integer.valueOf(i));
                    SsbServiceClient ssbServiceClient = ssbServiceClientMonitor.ssbServiceClient;
                    SsbServiceClient.checkMainThread();
                    ssbServiceClient.callback = ssbServiceClientMonitor;
                    Context context = ssbServiceClient.context;
                    UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
                    if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
                        if (!SsbServiceClient.isOpaTvSsbCapable(context)) {
                            if (SsbServiceClient.isVelvetSsbCapable(context)) {
                                str = "com.google.android.googlequicksearchbox";
                            }
                            str = null;
                        }
                        str = "com.google.android.katniss";
                    } else if (uiModeManager != null && uiModeManager.getCurrentModeType() == 3) {
                        if (SsbServiceClient.isSsbCapablePackage(context, "com.google.android.carassistant", 1, SsbServiceClient.CAR_ASSISTANT_RELEASE_KEY_CERTIFICATE, SsbServiceClient.CAR_ASSISTANT_DEBUG_KEY_CERTIFICATE)) {
                            str = "com.google.android.carassistant";
                        } else {
                            if (SsbServiceClient.isVelvetSsbCapable(context)) {
                                str = "com.google.android.googlequicksearchbox";
                            }
                            str = null;
                        }
                    } else if (uiModeManager != null && uiModeManager.getCurrentModeType() == 6) {
                        if (!SsbServiceClient.isSsbCapablePackage(context, "com.google.android.wearable.assistant", 1, SsbServiceClient.WATCH_ASSISTANT_RELEASE_KEY_CERTIFICATE, SsbServiceClient.WATCH_ASSISTANT_DEBUG_KEY_CERTIFICATE)) {
                            if (SsbServiceClient.isVelvetSsbCapable(context)) {
                                str = "com.google.android.googlequicksearchbox";
                            }
                            str = null;
                        }
                    } else {
                        if (!SsbServiceClient.isVelvetSsbCapable(context)) {
                            if (SsbServiceClient.isOpaTvSsbCapable(context)) {
                                str = "com.google.android.katniss";
                            }
                            str = null;
                        }
                        str = "com.google.android.googlequicksearchbox";
                    }
                    if (str != null) {
                        Intent intent = new Intent("com.google.android.ssb.action.SSB_SERVICE").setPackage(str);
                        if (str.equals("com.google.android.katniss") && (packageInfoWithSignature = SsbServiceClient.getPackageInfoWithSignature(ssbServiceClient.context, "com.google.android.katniss")) != null && packageInfoWithSignature.versionCode >= 13001070) {
                            intent = new Intent("com.google.android.apps.tvsearch.voice.search.KVS_SERVICE").setPackage(str);
                        }
                        ssbServiceClient.isPendingConnection = ssbServiceClient.context.bindService(intent, ssbServiceClient.connection$ar$class_merging, 1);
                        boolean z = ssbServiceClient.isPendingConnection;
                    }
                } catch (Exception e) {
                    LogUtils.e("SsbServiceClientMonitor", "Error occurs when connecting to SsbServiceClient.", new Object[0]);
                    e.printStackTrace();
                }
                connectToService(i);
            }
        }

        private final void connectToService(int i) {
            sendMessageDelayed(obtainMessage(0, i + 1, 0), ((long) Math.pow(3.0d, i)) * 1000);
        }
    }
}
