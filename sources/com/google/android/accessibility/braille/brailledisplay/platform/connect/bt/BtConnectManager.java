package com.google.android.accessibility.braille.brailledisplay.platform.connect.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.core.os.BuildCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.Connector;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableBluetoothDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brltty.BrailleInputEventIA;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.common.collect.CollectCollectors;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import j$.util.Optional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BtConnectManager extends ConnectManager {
    public final BluetoothAdapter btAdapter;
    private final BtConnectStateReceiver btBondedReceiver;
    private final RetryingNameResolver.ResolutionResultListener btBondedReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public Connector btConnector;
    private final BtOnOffReceiver btOnOffReceiver;
    private final RetryingNameResolver.ResolutionResultListener btOnOffReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final BtScanReceiver btScanReceiver;
    private final RetryingNameResolver.ResolutionResultListener btScanReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final RetryingNameResolver.ResolutionResultListener connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context context;
    public D2dConnection deviceConnection;
    public final LinkedHashSet foundDevices = new LinkedHashSet();
    private final MainHandler mainHandler;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class BtRfCommConnectorCallback implements Connector.Callback {
        private final boolean manual;

        public BtRfCommConnectorCallback(boolean z) {
            this.manual = z;
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.Connector.Callback
        public final void onConnectFailure(ConnectableDevice connectableDevice, Exception exc) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "RFCOMM onConnectFailure: ".concat(String.valueOf(exc.getMessage())));
            BtConnectManager.this.disconnect();
            BtConnectManager.this.connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onConnectFailure(connectableDevice, this.manual, exc);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MainHandler extends Handler {
        public MainHandler() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what == 0) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.v("BtConnectManager", "invoke stopDiscovery from handler");
                BtConnectManager btConnectManager = BtConnectManager.this;
                if (btConnectManager.btAdapter != null && btConnectManager.mayScan()) {
                    BtConnectManager.this.btAdapter.cancelDiscovery();
                }
            }
        }
    }

    public BtConnectManager(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener2 = new RetryingNameResolver.ResolutionResultListener(this);
        this.btScanReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener2;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener3 = new RetryingNameResolver.ResolutionResultListener(this);
        this.btOnOffReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener3;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener4 = new RetryingNameResolver.ResolutionResultListener(this);
        this.btBondedReceiverCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener4;
        BrailleInputEventIA.assertMainThread();
        this.context = context;
        this.connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.btBondedReceiver = new BtConnectStateReceiver(context, resolutionResultListener4);
        this.btOnOffReceiver = new BtOnOffReceiver(context, resolutionResultListener3);
        this.btScanReceiver = new BtScanReceiver(context, resolutionResultListener2);
        this.btAdapter = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
        this.mainHandler = new MainHandler();
    }

    private final void startScanPossibly$ar$edu(int i) {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "startScanPossibly reason: ".concat(AppCompatDelegate.Api24Impl.toStringGeneratedfc30da1462dd4304(i)));
        if (!SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(this.context)) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "Disable bluetooth scanning in setup wizard");
            return;
        }
        this.foundDevices.clear();
        this.connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDeviceListCleared();
        MainHandler mainHandler = this.mainHandler;
        mainHandler.removeMessages(0);
        mainHandler.sendEmptyMessageDelayed(0, 30000L);
        if ((i == 7 || !isConnectingOrConnected()) && this.btAdapter != null && mayScan() && this.btAdapter.startDiscovery()) {
            return;
        }
        AppCompatTextViewAutoSizeHelper.Api23Impl.e("BtConnectManager", "startScanPossibly failed to start discovery");
        stopSearch$ar$edu(11);
        this.connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSearchFailure();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void connect(ConnectableDevice connectableDevice, boolean z) {
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "connect: ".concat(String.valueOf(String.valueOf(connectableDevice))));
        BuildCompat.isAtLeastV();
        this.connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onConnectStarted$ar$edu(1);
        Connector connector = new Connector(connectableDevice, new BtRfCommConnectorCallback(z));
        this.btConnector = connector;
        connector.connect();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void disconnect() {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "disconnect");
        Connector connector = this.btConnector;
        if (connector != null) {
            connector.disconnect();
            this.btConnector = null;
        }
        D2dConnection d2dConnection = this.deviceConnection;
        if (d2dConnection != null) {
            d2dConnection.shutdown();
            this.deviceConnection = null;
            this.connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDisconnected();
        }
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void forget(ConnectableDevice connectableDevice) {
        BluetoothDevice bluetoothDevice = ((ConnectableBluetoothDevice) connectableDevice).bluetoothDevice();
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", null);
            if (method != null) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.i("BtConnectManager", "removeBond: " + ((Boolean) method.invoke(bluetoothDevice, null)).booleanValue());
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.w("BtConnectManager", "Unable to call removeBond. ", e);
        }
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Set getBondedDevices() {
        Set<BluetoothDevice> hashSet = new HashSet<>();
        if (this.btAdapter != null && ((!BuildCompat.isAtLeastS() || EditorInfoCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_CONNECT") == 0) && (hashSet = this.btAdapter.getBondedDevices()) == null)) {
            return new HashSet();
        }
        return (Set) Collection.EL.stream(hashSet).map(new BtConnectManager$$ExternalSyntheticLambda1(0)).collect(CollectCollectors.TO_IMMUTABLE_SET);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final java.util.Collection getConnectableDevices() {
        return new ArrayList(this.foundDevices);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Optional getCurrentlyConnectedDevice() {
        return Optional.ofNullable(this.deviceConnection).map(new BtConnectManager$$ExternalSyntheticLambda1(2));
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Optional getCurrentlyConnectingDevice() {
        return Optional.ofNullable(this.btConnector).map(new BtConnectManager$$ExternalSyntheticLambda1(1));
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final int getType$ar$edu$c2cf13b1_0() {
        return 1;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final boolean isConnected() {
        if (this.deviceConnection != null) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final boolean isConnecting() {
        if (this.btConnector != null && this.deviceConnection == null) {
            return true;
        }
        return false;
    }

    public final boolean isConnectingOrConnected() {
        if (!isConnecting() && !isConnected()) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final boolean isScanning() {
        return isScanningOngoing();
    }

    public final boolean isScanningOngoing() {
        return this.mainHandler.hasMessages(0);
    }

    public final boolean mayScan() {
        if (BuildCompat.isAtLeastS() && EditorInfoCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH_SCAN") != 0) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void onStart() {
        this.btScanReceiver.registerSelf$ar$ds();
        this.btOnOffReceiver.registerSelf$ar$ds();
        this.btBondedReceiver.registerSelf$ar$ds();
        startScanPossibly$ar$edu(2);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void onStop() {
        this.btScanReceiver.unregisterSelf();
        this.btOnOffReceiver.unregisterSelf();
        this.btBondedReceiver.unregisterSelf();
        stopSearch$ar$edu(9);
        disconnect();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void sendOutgoingPacket(byte[] bArr) {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "sendOutgoingPacket");
        D2dConnection d2dConnection = this.deviceConnection;
        if (d2dConnection != null) {
            d2dConnection.sendOutgoingPacket(bArr);
        }
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void startSearch$ar$edu(int i) {
        startScanPossibly$ar$edu(i);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void stopSearch$ar$edu(int i) {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "stopScan ".concat(AppCompatDelegate.Api24Impl.toStringGeneratedfc30da1462dd4304(i)));
        this.mainHandler.removeMessages(0);
        if (this.btAdapter != null && mayScan()) {
            this.btAdapter.cancelDiscovery();
        }
    }
}
