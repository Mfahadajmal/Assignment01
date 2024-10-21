package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.os.BuildCompat;
import androidx.preference.Preference;
import androidx.work.impl.constraints.ConstraintsState$ConstraintsMet;
import androidx.work.impl.constraints.ConstraintsState$ConstraintsNotMet;
import androidx.work.impl.constraints.controllers.BaseConstraintController;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableUsbDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnection;
import com.google.android.accessibility.braille.brailledisplay.settings.AutoScrollDurationPreference;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.talkback.labeling.CustomLabelManager;
import com.google.android.accessibility.talkback.labeling.LabelImportActivity;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.api.internal.BaseLifecycleHelper;
import com.google.android.marvin.talkback.R;
import com.google.android.material.slider.Slider;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;
import java.util.Locale;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GooglePlayServicesUpdatedReceiver extends BroadcastReceiver {
    private final Callback callback;
    public Context mContext;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Callback {
        public final /* synthetic */ Object GooglePlayServicesUpdatedReceiver$Callback$ar$this$1;
        public final /* synthetic */ Object GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog;

        public Callback() {
        }

        public final void onConstraintChanged(Object obj) {
            Object obj2;
            if (((BaseConstraintController) this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog).isConstrained(obj)) {
                obj2 = new ConstraintsState$ConstraintsNotMet(((BaseConstraintController) this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog).getReason());
            } else {
                obj2 = ConstraintsState$ConstraintsMet.INSTANCE;
            }
            ((ProducerCoroutine) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).getChannel().mo257trySendJP2dKIU(obj2);
        }

        public final void onLabelImported(int i) {
            ((CustomLabelManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog).sendCacheRefreshIntent(new String[0]);
            LabelImportActivity labelImportActivity = (LabelImportActivity) LabelImportActivity.AnonymousClass1.this.LabelImportActivity$1$ar$this$0;
            Toast.makeText(labelImportActivity.getApplicationContext(), labelImportActivity.getResources().getQuantityString(R.plurals.label_import_succeeded, i, Integer.valueOf(i)), 0).show();
        }

        public final void onPermissionDenied(UsbDevice usbDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", String.valueOf(usbDevice.getDeviceName()).concat(" usb permission denied."));
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (byte[]) null, (char[]) null, (byte[]) null);
            onDeviceTextDetectionLoadLogEvent.setUsbDevice$ar$ds(usbDevice);
            ((Connectioneer) ((RetryingNameResolver.ResolutionResultListener) this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog).RetryingNameResolver$ResolutionResultListener$ar$this$0).userDeniedDevices.add(onDeviceTextDetectionLoadLogEvent.m223build().address());
            ((UsbConnectManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).askingPermission.set(false);
        }

        public final void onPermissionGranted(UsbDevice usbDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", String.valueOf(usbDevice.getDeviceName()).concat(" usb permission granted."));
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (byte[]) null, (char[]) null, (byte[]) null);
            onDeviceTextDetectionLoadLogEvent.setUsbDevice$ar$ds(usbDevice);
            ConnectableUsbDevice m223build = onDeviceTextDetectionLoadLogEvent.m223build();
            BuildCompat.isAtLeastV();
            ((UsbConnectManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).deviceConnection = new UsbConnection(m223build);
            ((RetryingNameResolver.ResolutionResultListener) this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog).onConnected(((UsbConnectManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).deviceConnection);
            if (((UsbConnectManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).isBatteryLow()) {
                ((UsbConnectManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).showBatteryLowDialog();
            } else {
                ((UsbConnectManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).showConnectViaUsbDialog();
            }
            ((UsbConnectManager) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).askingPermission.set(false);
        }

        public final void onUpdated() {
            ((BaseLifecycleHelper) ((BaseLifecycleHelper.ConnectionFailedResolver) this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).markErrorsResolved();
            if (((Dialog) this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog).isShowing()) {
                ((Dialog) this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog).dismiss();
            }
        }

        public final /* bridge */ /* synthetic */ void onValueChange$ar$ds(Object obj, float f) {
            NumberFormat numberInstance;
            String format;
            String str;
            Slider slider = (Slider) obj;
            Object obj2 = this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog;
            AutoScrollDurationPreference autoScrollDurationPreference = (AutoScrollDurationPreference) obj2;
            float currentDuration = autoScrollDurationPreference.getCurrentDuration();
            if (currentDuration > f) {
                BrailleUserPreferences.decreaseAutoScrollDuration(((Preference) obj2).getContext());
            } else if (currentDuration < f) {
                BrailleUserPreferences.increaseAutoScrollDuration(((Preference) obj2).getContext());
            }
            Object obj3 = this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1;
            Preference preference = (Preference) obj2;
            Context context = preference.getContext();
            numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
            format = numberInstance.format(slider.getValue());
            ((TextView) obj3).setText(context.getString(R.string.bd_auto_scroll_duration_value_text, String.valueOf(format)));
            if (autoScrollDurationPreference.slider.getValue() == autoScrollDurationPreference.slider.getValueFrom()) {
                str = preference.getContext().getString(R.string.bd_auto_scroll_duration_minimum);
            } else if (autoScrollDurationPreference.slider.getValue() == autoScrollDurationPreference.slider.getValueTo()) {
                str = preference.getContext().getString(R.string.bd_auto_scroll_duration_maximum);
            } else {
                str = "";
            }
            slider.announceForAccessibility(str);
        }

        public Callback(BaseConstraintController baseConstraintController, ProducerCoroutine producerCoroutine) {
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog = baseConstraintController;
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1 = producerCoroutine;
        }

        public Callback(UsbConnectManager usbConnectManager, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog = resolutionResultListener;
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1 = usbConnectManager;
        }

        public /* synthetic */ Callback(AutoScrollDurationPreference autoScrollDurationPreference, TextView textView) {
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog = autoScrollDurationPreference;
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1 = textView;
        }

        public Callback(CustomLabelManager customLabelManager, SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan) {
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1 = spannableUtils$IdentifierSpan;
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog = customLabelManager;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Callback(BaseLifecycleHelper.ConnectionFailedResolver connectionFailedResolver, Dialog dialog) {
            this();
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog = dialog;
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1 = connectionFailedResolver;
        }

        public /* synthetic */ Callback(CharSequence charSequence, CharSequence charSequence2) {
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1 = charSequence;
            this.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog = charSequence2;
        }
    }

    public GooglePlayServicesUpdatedReceiver(Callback callback) {
        this.callback = callback;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String str;
        Uri data = intent.getData();
        if (data != null) {
            str = data.getSchemeSpecificPart();
        } else {
            str = null;
        }
        if ("com.google.android.gms".equals(str)) {
            this.callback.onUpdated();
            unregister();
        }
    }

    public final synchronized void unregister() {
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.mContext = null;
    }
}
