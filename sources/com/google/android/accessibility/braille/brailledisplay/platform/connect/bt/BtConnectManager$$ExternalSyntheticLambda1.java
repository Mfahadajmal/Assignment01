package com.google.android.accessibility.braille.brailledisplay.platform.connect.bt;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.usb.UsbDevice;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import com.google.android.accessibility.braille.brailledisplay.platform.ConnectibleDeviceInfo;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.Connector;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.TouchDots;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.input.BrailleInputPlane;
import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import j$.util.function.Function$CC;
import java.util.Locale;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BtConnectManager$$ExternalSyntheticLambda1 implements Function {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BtConnectManager$$ExternalSyntheticLambda1(int i) {
        this.switching_field = i;
    }

    public final /* synthetic */ Function andThen(Function function) {
        switch (this.switching_field) {
            case 0:
                return Function$CC.$default$andThen(this, function);
            case 1:
                return Function$CC.$default$andThen(this, function);
            case 2:
                return Function$CC.$default$andThen(this, function);
            case 3:
                return Function$CC.$default$andThen(this, function);
            case 4:
                return Function$CC.$default$andThen(this, function);
            case 5:
                return Function$CC.$default$andThen(this, function);
            case 6:
                return Function$CC.$default$andThen(this, function);
            case 7:
                return Function$CC.$default$andThen(this, function);
            case 8:
                return Function$CC.$default$andThen(this, function);
            case 9:
                return Function$CC.$default$andThen(this, function);
            case 10:
                return Function$CC.$default$andThen(this, function);
            case 11:
                return Function$CC.$default$andThen(this, function);
            case 12:
                return Function$CC.$default$andThen(this, function);
            case 13:
                return Function$CC.$default$andThen(this, function);
            case 14:
                return Function$CC.$default$andThen(this, function);
            case 15:
                return Function$CC.$default$andThen(this, function);
            case 16:
                return Function$CC.$default$andThen(this, function);
            case 17:
                return Function$CC.$default$andThen(this, function);
            case 18:
                return Function$CC.$default$andThen(this, function);
            case 19:
                return Function$CC.$default$andThen(this, function);
            default:
                return Function$CC.$default$andThen(this, function);
        }
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        ConnectibleDeviceInfo connectibleDeviceInfo;
        Locale forLanguageTag;
        switch (this.switching_field) {
            case 0:
                OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
                onDeviceTextDetectionLoadLogEvent.setBluetoothDevice$ar$ds((BluetoothDevice) obj);
                return onDeviceTextDetectionLoadLogEvent.build();
            case 1:
                return ((Connector) obj).device;
            case 2:
                return ((D2dConnection) obj).getDevice();
            case 3:
                OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent2 = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (byte[]) null, (char[]) null, (byte[]) null);
                onDeviceTextDetectionLoadLogEvent2.setUsbDevice$ar$ds((UsbDevice) obj);
                return onDeviceTextDetectionLoadLogEvent2.m223build();
            case 4:
                return ((D2dConnection) obj).getDevice();
            case 5:
                return BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect((Context) obj);
            case 6:
                connectibleDeviceInfo = ((BrailleDisplaySettingsFragment.DevicePreference) obj).rowDevice;
                return connectibleDeviceInfo;
            case 7:
                return BrailleUserPreferences.readCurrentActiveOutputCodeAndCorrect((Context) obj);
            case 8:
                return ((BrailleLanguages.Code) obj).name();
            case 9:
                return (BrailleLanguages.Code) AppCompatDelegate.Api33Impl.valueOfSafe((String) obj, BrailleLanguages.Code.STUB);
            case 10:
                forLanguageTag = Locale.forLanguageTag(((BrailleLanguages.Code) obj).locale.getLanguage());
                return forLanguageTag;
            case 11:
                int i = BrailleInputPlane.BrailleInputPlane$ar$NoOp;
                return Integer.valueOf(((OrderVerifyingClientCall.State) obj).type$ar$edu$88c656f2_0);
            case 12:
                return ((MultitouchHandler.PointerWithHistory) obj).pointCurrent;
            case 13:
                return ((MultitouchHandler.PointerWithHistory) obj).pointInitial;
            case 14:
                MultitouchHandler.PointerWithHistory pointerWithHistory = (MultitouchHandler.PointerWithHistory) obj;
                return Float.valueOf(pointerWithHistory.pointCurrent.x - pointerWithHistory.pointInitial.x);
            case 15:
                MultitouchHandler.PointerWithHistory pointerWithHistory2 = (MultitouchHandler.PointerWithHistory) obj;
                return Float.valueOf(pointerWithHistory2.pointCurrent.y - pointerWithHistory2.pointInitial.y);
            case 16:
                return ((MultitouchHandler.PointerWithHistory) obj).pointCurrent;
            case 17:
                return ((MultitouchHandler.PointerWithHistory) obj).pointCurrent;
            case 18:
                View view = (View) obj;
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                int i2 = iArr[0];
                return new Rect(i2, iArr[1], view.getWidth() + i2, iArr[1] + view.getHeight());
            case 19:
                return ((TouchDots) obj).name();
            default:
                return BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect((Context) obj);
        }
    }

    public final /* synthetic */ Function compose(Function function) {
        switch (this.switching_field) {
            case 0:
                return Function$CC.$default$compose(this, function);
            case 1:
                return Function$CC.$default$compose(this, function);
            case 2:
                return Function$CC.$default$compose(this, function);
            case 3:
                return Function$CC.$default$compose(this, function);
            case 4:
                return Function$CC.$default$compose(this, function);
            case 5:
                return Function$CC.$default$compose(this, function);
            case 6:
                return Function$CC.$default$compose(this, function);
            case 7:
                return Function$CC.$default$compose(this, function);
            case 8:
                return Function$CC.$default$compose(this, function);
            case 9:
                return Function$CC.$default$compose(this, function);
            case 10:
                return Function$CC.$default$compose(this, function);
            case 11:
                return Function$CC.$default$compose(this, function);
            case 12:
                return Function$CC.$default$compose(this, function);
            case 13:
                return Function$CC.$default$compose(this, function);
            case 14:
                return Function$CC.$default$compose(this, function);
            case 15:
                return Function$CC.$default$compose(this, function);
            case 16:
                return Function$CC.$default$compose(this, function);
            case 17:
                return Function$CC.$default$compose(this, function);
            case 18:
                return Function$CC.$default$compose(this, function);
            case 19:
                return Function$CC.$default$compose(this, function);
            default:
                return Function$CC.$default$compose(this, function);
        }
    }
}
