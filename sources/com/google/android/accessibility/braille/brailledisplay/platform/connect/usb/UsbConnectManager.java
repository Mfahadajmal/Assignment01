package com.google.android.accessibility.braille.brailledisplay.platform.connect.usb;

import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.core.os.BuildCompat;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableUsbDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.BatteryChangeReceiver;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.ScreenUnlockReceiver;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.stream.Collectors;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UsbConnectManager extends ConnectManager {
    private final BatteryChangeReceiver batteryChangeReceiver;
    private Dialog batteryLowDialog;
    private final BatteryManager batteryManager;
    private final RetryingNameResolver.ResolutionResultListener connectManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Context context;
    public D2dConnection deviceConnection;
    private final ScreenUnlockReceiver screenUnlockReceiver;
    private Dialog usbConnectDialog;
    private final UsbManager usbManager;
    private final UsbPermissionReceiver usbPermissionReceiver;
    public final AtomicBoolean askingPermission = new AtomicBoolean();
    private int batteryVolumePercentage = -1;

    public UsbConnectManager(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.context = context;
        this.connectManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.batteryManager = (BatteryManager) context.getSystemService("batterymanager");
        this.usbPermissionReceiver = new UsbPermissionReceiver(context, new GooglePlayServicesUpdatedReceiver.Callback(this, resolutionResultListener));
        this.batteryChangeReceiver = new BatteryChangeReceiver(context, new RetryingNameResolver.ResolutionResultListener(this, null));
        this.screenUnlockReceiver = new ScreenUnlockReceiver(context, new RetryingNameResolver.ResolutionResultListener(this));
        this.usbManager = (UsbManager) context.getSystemService("usb");
    }

    private final int getBatteryPercentage() {
        return this.batteryManager.getIntProperty(4);
    }

    private final boolean isScreenLocked() {
        KeyguardManager keyguardManager = (KeyguardManager) this.context.getSystemService("keyguard");
        AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", "screen is locked: " + keyguardManager.isKeyguardLocked());
        return keyguardManager.isKeyguardLocked();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void connect(ConnectableDevice connectableDevice, boolean z) {
        int i;
        AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", "connect");
        UsbDevice usbDevice = ((ConnectableUsbDevice) connectableDevice).usbDevice();
        if (this.usbManager.hasPermission(usbDevice)) {
            BuildCompat.isAtLeastV();
            UsbConnection usbConnection = new UsbConnection(connectableDevice);
            this.deviceConnection = usbConnection;
            this.connectManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onConnected(usbConnection);
            if (isBatteryLow()) {
                showBatteryLowDialog();
                return;
            } else {
                showConnectViaUsbDialog();
                return;
            }
        }
        UsbManager usbManager = this.usbManager;
        UsbPermissionReceiver usbPermissionReceiver = this.usbPermissionReceiver;
        Intent intent = new Intent(String.valueOf(usbPermissionReceiver.context.getPackageName()).concat(".USB_PERMISSION"));
        intent.putExtra("device", usbDevice);
        intent.setPackage(usbPermissionReceiver.context.getPackageName());
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 31) {
            i = 167772160;
        } else {
            i = 134217728;
        }
        usbManager.requestPermission(usbDevice, PendingIntent.getBroadcast(usbPermissionReceiver.context, 0, intent, i));
        this.askingPermission.set(true);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void disconnect() {
        boolean z;
        D2dConnection d2dConnection = this.deviceConnection;
        StringBuilder sb = new StringBuilder("disconnect: ");
        if (d2dConnection != null) {
            z = true;
        } else {
            z = false;
        }
        sb.append(z);
        AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", sb.toString());
        D2dConnection d2dConnection2 = this.deviceConnection;
        if (d2dConnection2 != null) {
            d2dConnection2.shutdown();
            this.deviceConnection = null;
            this.connectManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDisconnected();
        }
        dismissAllDialogs();
        this.batteryVolumePercentage = -1;
    }

    public final void dismissAllDialogs() {
        Dialog dialog = this.usbConnectDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        Dialog dialog2 = this.batteryLowDialog;
        if (dialog2 != null) {
            dialog2.dismiss();
        }
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Set getBondedDevices() {
        return (Set) Collection.EL.stream(this.usbManager.getDeviceList().values()).map(new BtConnectManager$$ExternalSyntheticLambda1(3)).collect(Collectors.toSet());
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final java.util.Collection getConnectableDevices() {
        return getBondedDevices();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Optional getCurrentlyConnectedDevice() {
        return Optional.ofNullable(this.deviceConnection).map(new BtConnectManager$$ExternalSyntheticLambda1(4));
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Optional getCurrentlyConnectingDevice() {
        return Optional.empty();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final int getType$ar$edu$c2cf13b1_0() {
        return 2;
    }

    public final boolean isBatteryLow() {
        if (getBatteryPercentage() <= 15) {
            return true;
        }
        return false;
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
        return false;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final boolean isScanning() {
        return false;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void onStart() {
        this.connectManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDeviceListCleared();
        this.usbPermissionReceiver.registerSelf$ar$ds();
        this.batteryChangeReceiver.registerSelf$ar$ds();
        this.screenUnlockReceiver.registerSelf$ar$ds();
        startSearch$ar$edu(2);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void onStop() {
        this.usbPermissionReceiver.unregisterSelf();
        this.batteryChangeReceiver.unregisterSelf();
        this.screenUnlockReceiver.unregisterSelf();
        disconnect();
    }

    public final void showBatteryLowDialog() {
        boolean z;
        AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", "isConnected: " + isConnected());
        if (isConnected() && !isScreenLocked() && this.accessibilityServiceContextProvider.getAccessibilityServiceContext() != null) {
            int batteryPercentage = getBatteryPercentage();
            AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", "batteryVolume: " + this.batteryVolumePercentage + "; percentage: " + batteryPercentage);
            int i = this.batteryVolumePercentage;
            if (i != batteryPercentage && ((i > 15 || i == -1) && batteryPercentage <= 15)) {
                z = true;
            } else {
                z = false;
            }
            this.batteryVolumePercentage = batteryPercentage;
            if (z) {
                Dialog dialog = this.batteryLowDialog;
                if (dialog == null || !dialog.isShowing()) {
                    AlertDialog.Builder alertDialogBuilder = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this.accessibilityServiceContextProvider.getAccessibilityServiceContext());
                    Context context = this.context;
                    AlertDialog.Builder title = alertDialogBuilder.setTitle(AppCompatDelegateImpl.Api21Impl.toCharacterTitleCase(context.getString(R.string.bd_battery_low_dialog_title, context.getString(R.string.bd_device))));
                    Context context2 = this.context;
                    AlertDialog create = title.setMessage(context2.getString(R.string.bd_battery_low_dialog_message, context2.getString(R.string.bd_device))).setPositiveButton(R.string.bd_battery_low_dialog_button, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 6)).create();
                    this.batteryLowDialog = create;
                    create.getWindow().setType(2032);
                    this.batteryLowDialog.show();
                }
            }
        }
    }

    public final void showConnectViaUsbDialog() {
        Context context = this.context;
        if (BrailleUserPreferences.getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_bd_show_usb_connect_dialog), true)) {
            Dialog dialog = this.usbConnectDialog;
            if ((dialog == null || !dialog.isShowing()) && !isScreenLocked() && this.accessibilityServiceContextProvider.getAccessibilityServiceContext() != null) {
                Context accessibilityServiceContext = this.accessibilityServiceContextProvider.getAccessibilityServiceContext();
                Context context2 = this.context;
                AlertDialog createTipAlertDialog = AppCompatDelegateImpl.Api21Impl.createTipAlertDialog(accessibilityServiceContext, context2.getString(R.string.bd_usb_connect_dialog_title), context2.getString(R.string.bd_usb_connect_dialog_message, context2.getString(R.string.bd_device)), new UsbConnectManager$$ExternalSyntheticLambda0(0));
                this.usbConnectDialog = createTipAlertDialog;
                createTipAlertDialog.getWindow().setType(2032);
                this.usbConnectDialog.show();
            }
        }
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void startSearch$ar$edu(int i) {
        Iterator it = getBondedDevices().iterator();
        while (it.hasNext()) {
            this.connectManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDeviceSeenOrUpdated((ConnectableDevice) it.next());
        }
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void forget(ConnectableDevice connectableDevice) {
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void sendOutgoingPacket(byte[] bArr) {
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void stopSearch$ar$edu(int i) {
    }
}
