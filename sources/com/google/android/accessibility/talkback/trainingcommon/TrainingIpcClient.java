package com.google.android.accessibility.talkback.trainingcommon;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.google.android.accessibility.talkback.ipc.IpcClient;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import j$.util.Collection;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingIpcClient extends IpcClient {
    private final Runnable connectionStateListener;
    private final TrainingActivity ipcServerStateListener$ar$class_merging;
    public final ServiceData serviceData;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ServiceData {
        public final Context context;
        public final boolean showExitBanner;
        public boolean isAnyGestureChanged = true;
        public final HashMap actionKeyToGestureText = new HashMap();
        public boolean isIconDetectionUnavailable = false;
        public boolean isImageDescriptionUnavailable = false;

        public ServiceData(Context context, boolean z) {
            this.context = context;
            this.showExitBanner = z;
        }
    }

    public TrainingIpcClient(Context context, TrainingActivity trainingActivity, boolean z, Runnable runnable) {
        super(context);
        this.ipcServerStateListener$ar$class_merging = trainingActivity;
        this.connectionStateListener = runnable;
        this.serviceData = new ServiceData(context, z);
    }

    @Override // com.google.android.accessibility.talkback.ipc.IpcClient
    public final void handleMessageFromService(Message message) {
        int i = 0;
        LogUtils.v("TrainingIpcClient", "handleMessageFromService(): %s", Integer.valueOf(message.what));
        int i2 = message.what;
        if (i2 != 1) {
            if (i2 != 5) {
                if (i2 == 7) {
                    Bundle data = message.getData();
                    this.serviceData.isIconDetectionUnavailable = data.getBoolean("is_icon_detection_unavailable");
                    this.serviceData.isImageDescriptionUnavailable = data.getBoolean("is_image_description_unavailable");
                    return;
                }
                return;
            }
            TrainingActivity trainingActivity = this.ipcServerStateListener$ar$class_merging;
            if (trainingActivity != null) {
                trainingActivity.onIpcServerDestroyed();
                return;
            }
            return;
        }
        this.serviceData.actionKeyToGestureText.clear();
        Bundle data2 = message.getData();
        Collection.EL.forEach(data2.keySet(), new TrainingIpcClient$$ExternalSyntheticLambda0(this, data2, i));
    }

    @Override // com.google.android.accessibility.talkback.ipc.IpcClient, android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.serviceMessenger = new Messenger(iBinder);
        sendMessage(Message.obtain((Handler) null, 3));
        this.connectionStateListener.run();
    }
}
