package com.google.android.accessibility.braille.brailledisplay.platform.connect;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.Connector;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BluetoothDevices;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableBluetoothDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brltty.BrailleInputEventIA;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Connector {
    public Callback callback;
    public BluetoothSocket connectingSocket;
    public final ConnectableDevice device;
    public boolean hasFailed;
    public final Handler mainHandler;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        void onConnectFailure(ConnectableDevice connectableDevice, Exception exc);
    }

    public Connector(ConnectableDevice connectableDevice) {
        this.device = connectableDevice;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtRfCommConnector$ConnectThread] */
    public final void connect() {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtRfCommConnector", "createRfcommSocket");
        try {
            this.connectingSocket = ((ConnectableBluetoothDevice) this.device).bluetoothDevice().createInsecureRfcommSocketToServiceRecord(BluetoothDevices.MY_UUID);
            new Thread() { // from class: com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtRfCommConnector$ConnectThread
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    try {
                        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtRfCommConnector", "Socket.connect()");
                        Connector.this.connectingSocket.connect();
                        InputStream inputStream = Connector.this.connectingSocket.getInputStream();
                        OutputStream outputStream = Connector.this.connectingSocket.getOutputStream();
                        Connector connector = Connector.this;
                        connector.mainHandler.obtainMessage(1, new BtConnection(connector.device, inputStream, outputStream)).sendToTarget();
                    } catch (Exception e) {
                        Connector.this.mainHandler.obtainMessage(2, e).sendToTarget();
                    }
                }
            }.start();
        } catch (IOException e) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.e("BtRfCommConnector", "createRfcommSocket failed");
            this.callback.onConnectFailure(this.device, e);
        }
    }

    public final void disconnect() {
        this.mainHandler.removeCallbacksAndMessages(null);
        BluetoothSocket bluetoothSocket = this.connectingSocket;
        if (bluetoothSocket == null) {
            return;
        }
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.e("BtRfCommConnector", "socket closed exception.", e);
        }
    }

    public final boolean isShutdown() {
        if (this.callback == null) {
            return true;
        }
        return false;
    }

    public Connector(ConnectableDevice connectableDevice, Callback callback) {
        this(connectableDevice);
        BrailleInputEventIA.assertMainThread();
        this.callback = callback;
        this.mainHandler = new Handler() { // from class: com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtRfCommConnector$MainHandler
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                if (message.what == 1) {
                    BtConnection btConnection = (BtConnection) message.obj;
                    if (Connector.this.isShutdown()) {
                        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtRfCommConnector", "skip onConnectSuccess due to shutdown");
                        btConnection.shutdown();
                        return;
                    }
                    AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtRfCommConnector", "invoke onConnectSuccess");
                    Connector.Callback callback2 = Connector.this.callback;
                    AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "RFCOMM onConnectSuccess");
                    BtConnectManager btConnectManager = ((BtConnectManager.BtRfCommConnectorCallback) callback2).this$0;
                    btConnectManager.deviceConnection = btConnection;
                    btConnectManager.connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onConnected(btConnectManager.deviceConnection);
                    return;
                }
                if (message.what == 2) {
                    if (Connector.this.isShutdown()) {
                        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtRfCommConnector", "skip onConnectFailure due to shutdown");
                        return;
                    }
                    if (Connector.this.hasFailed) {
                        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtRfCommConnector", "skip onConnectFailure due to has failed");
                        return;
                    }
                    AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtRfCommConnector", "invoke onConnectFailure");
                    Connector connector = Connector.this;
                    connector.hasFailed = true;
                    connector.callback.onConnectFailure(connector.device, (Exception) message.obj);
                    Connector.this.callback = null;
                }
            }
        };
    }
}
