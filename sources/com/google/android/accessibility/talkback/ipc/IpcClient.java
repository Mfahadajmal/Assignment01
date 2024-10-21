package com.google.android.accessibility.talkback.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class IpcClient implements ServiceConnection {
    private final Messenger clientMessenger = new Messenger(new Handler(Looper.myLooper()) { // from class: com.google.android.accessibility.talkback.ipc.IpcClient.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            IpcClient.this.handleMessageFromService(message);
        }
    });
    public final Context context;
    public Messenger serviceMessenger;

    public IpcClient(Context context) {
        this.context = context;
    }

    public final void bindService() {
        if (this.serviceMessenger != null) {
            return;
        }
        Context context = this.context;
        context.bindService(new Intent(context, (Class<?>) IpcService.class), this, 1);
    }

    public abstract void handleMessageFromService(Message message);

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.serviceMessenger = new Messenger(iBinder);
        sendMessage(Message.obtain((Handler) null, 3));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        sendMessage(Message.obtain((Handler) null, 4));
        this.serviceMessenger = null;
    }

    public final void sendMessage(Message message) {
        if (this.serviceMessenger == null) {
            LogUtils.e("IpcClient", "Service is unavailable.", new Object[0]);
            return;
        }
        try {
            message.replyTo = this.clientMessenger;
            this.serviceMessenger.send(message);
        } catch (RemoteException e) {
            LogUtils.e("IpcClient", "Fail to send message to IpcService. %s", e.getMessage());
        }
    }
}
