package com.google.android.accessibility.braille.brailledisplay.controller.utils;

import android.hardware.usb.UsbDevice;
import android.support.v7.app.AppCompatDelegate;
import android.util.Pair;
import android.util.Range;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brailledisplay.settings.KeyBindingsActivity;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.settings.LocaleLanguageActivity;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import j$.util.Collection;
import j$.util.function.Predicate$CC;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleKeyBindingUtils$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ Object BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BrailleKeyBindingUtils$$ExternalSyntheticLambda2(Object obj, int i) {
        this.switching_field = i;
        this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0 = obj;
    }

    public final /* synthetic */ Predicate and(Predicate predicate) {
        switch (this.switching_field) {
            case 0:
                return Predicate$CC.$default$and(this, predicate);
            case 1:
                return Predicate$CC.$default$and(this, predicate);
            case 2:
                return Predicate$CC.$default$and(this, predicate);
            case 3:
                return Predicate$CC.$default$and(this, predicate);
            case 4:
                return Predicate$CC.$default$and(this, predicate);
            case 5:
                return Predicate$CC.$default$and(this, predicate);
            case 6:
                return Predicate$CC.$default$and(this, predicate);
            case 7:
                return Predicate$CC.$default$and(this, predicate);
            case 8:
                return Predicate$CC.$default$and(this, predicate);
            case 9:
                return Predicate$CC.$default$and(this, predicate);
            case 10:
                return Predicate$CC.$default$and(this, predicate);
            case 11:
                return Predicate$CC.$default$and(this, predicate);
            case 12:
                return Predicate$CC.$default$and(this, predicate);
            case 13:
                return Predicate$CC.$default$and(this, predicate);
            case 14:
                return Predicate$CC.$default$and(this, predicate);
            case 15:
                return Predicate$CC.$default$and(this, predicate);
            case 16:
                return Predicate$CC.$default$and(this, predicate);
            case 17:
                return Predicate$CC.$default$and(this, predicate);
            case 18:
                return Predicate$CC.$default$and(this, predicate);
            case 19:
                return Predicate$CC.$default$and(this, predicate);
            default:
                return Predicate$CC.$default$and(this, predicate);
        }
    }

    public final /* synthetic */ Predicate negate() {
        switch (this.switching_field) {
            case 0:
                return Predicate$CC.$default$negate(this);
            case 1:
                return Predicate$CC.$default$negate(this);
            case 2:
                return Predicate$CC.$default$negate(this);
            case 3:
                return Predicate$CC.$default$negate(this);
            case 4:
                return Predicate$CC.$default$negate(this);
            case 5:
                return Predicate$CC.$default$negate(this);
            case 6:
                return Predicate$CC.$default$negate(this);
            case 7:
                return Predicate$CC.$default$negate(this);
            case 8:
                return Predicate$CC.$default$negate(this);
            case 9:
                return Predicate$CC.$default$negate(this);
            case 10:
                return Predicate$CC.$default$negate(this);
            case 11:
                return Predicate$CC.$default$negate(this);
            case 12:
                return Predicate$CC.$default$negate(this);
            case 13:
                return Predicate$CC.$default$negate(this);
            case 14:
                return Predicate$CC.$default$negate(this);
            case 15:
                return Predicate$CC.$default$negate(this);
            case 16:
                return Predicate$CC.$default$negate(this);
            case 17:
                return Predicate$CC.$default$negate(this);
            case 18:
                return Predicate$CC.$default$negate(this);
            case 19:
                return Predicate$CC.$default$negate(this);
            default:
                return Predicate$CC.$default$negate(this);
        }
    }

    public final /* synthetic */ Predicate or(Predicate predicate) {
        switch (this.switching_field) {
            case 0:
                return Predicate$CC.$default$or(this, predicate);
            case 1:
                return Predicate$CC.$default$or(this, predicate);
            case 2:
                return Predicate$CC.$default$or(this, predicate);
            case 3:
                return Predicate$CC.$default$or(this, predicate);
            case 4:
                return Predicate$CC.$default$or(this, predicate);
            case 5:
                return Predicate$CC.$default$or(this, predicate);
            case 6:
                return Predicate$CC.$default$or(this, predicate);
            case 7:
                return Predicate$CC.$default$or(this, predicate);
            case 8:
                return Predicate$CC.$default$or(this, predicate);
            case 9:
                return Predicate$CC.$default$or(this, predicate);
            case 10:
                return Predicate$CC.$default$or(this, predicate);
            case 11:
                return Predicate$CC.$default$or(this, predicate);
            case 12:
                return Predicate$CC.$default$or(this, predicate);
            case 13:
                return Predicate$CC.$default$or(this, predicate);
            case 14:
                return Predicate$CC.$default$or(this, predicate);
            case 15:
                return Predicate$CC.$default$or(this, predicate);
            case 16:
                return Predicate$CC.$default$or(this, predicate);
            case 17:
                return Predicate$CC.$default$or(this, predicate);
            case 18:
                return Predicate$CC.$default$or(this, predicate);
            case 19:
                return Predicate$CC.$default$or(this, predicate);
            default:
                return Predicate$CC.$default$or(this, predicate);
        }
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        boolean equals;
        switch (this.switching_field) {
            case 0:
                if (((BrailleKeyBindingUtils.SupportedCommand) obj).command != ((BrailleInputEvent) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).getCommand()) {
                    return false;
                }
                return true;
            case 1:
                return true;
            case 2:
                return ((Connectioneer) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).allowDevice(((UsbDevice) obj).getProductName());
            case 3:
                return ((String) ((Pair) obj).second).equals(((ConnectableDevice) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).address());
            case 4:
                ConnectableDevice connectableDevice = (ConnectableDevice) obj;
                String name = connectableDevice.name();
                Connectioneer connectioneer = (Connectioneer) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0;
                if (!connectioneer.allowDevice(name) || connectioneer.userDisconnectedDevices.contains(connectableDevice.address()) || connectioneer.userDeniedDevices.contains(connectableDevice.address())) {
                    return false;
                }
                if (connectioneer.connectManagerProxy.getType$ar$edu$c2cf13b1_0() != 1) {
                    return true;
                }
                return Collection.EL.stream(AppCompatDelegate.Api24Impl.getRememberedDevices(connectioneer.context)).anyMatch(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(connectableDevice, 3));
            case 5:
                return ((ConnectableDevice) obj).address().equals(this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0);
            case 6:
                return ((ConnectableDevice) obj).address().equals(this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0);
            case 7:
                return ((Connectioneer.AspectConnection) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).m38x6f326594((ConnectableDevice) obj);
            case 8:
                return ((String) ((Pair) obj).second).equals(((Pair) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).second);
            case 9:
                return ((String) ((Pair) obj).second).equals(this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0);
            case 10:
                return ((ConnectableDevice) obj).address().equals(((Pair) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).second);
            case 11:
                return ((BrailleDisplaySettingsFragment) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).m55x23f9d86f((String) obj);
            case 12:
                equals = ((String) ((Pair) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).second).equals(((ConnectableDevice) obj).address());
                return equals;
            case 13:
                return ((String) ((Pair) obj).second).equals(((ConnectableDevice) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).address());
            case 14:
                return KeyBindingsActivity.KeyBindingsFragment.lambda$isCategoryCommandSupported$0((BrailleKeyBindingUtils.SupportedCommand.Category) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0, (BrailleKeyBindingUtils.SupportedCommand) obj);
            case 15:
                return KeyBindingsActivity.KeyBindingsFragment.lambda$isCategoryCommandSupported$1((ArrayList) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0, (BrailleKeyBindingUtils.SupportedCommand) obj);
            case 16:
                return ((String) obj).equals(this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0);
            case 17:
                return ((LocaleLanguageActivity.PreferredLocaleLanguageFragment) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).m69x157b9713((BrailleLanguages.Code) obj);
            case 18:
                Locale locale = BrailleLanguages.LOCALE_AR;
                return ((BrailleLanguages.Code) obj).locale.getLanguage().equals(((Locale) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).getLanguage());
            case 19:
                Locale locale2 = BrailleLanguages.LOCALE_AR;
                return ((BrailleLanguages.Code) obj).locale.getCountry().equals(((Locale) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).getCountry());
            default:
                return ((Range) this.BrailleKeyBindingUtils$$ExternalSyntheticLambda2$ar$f$0).contains((Range) Long.valueOf(((MultitouchHandler.PointerWithHistory) obj).momentMadeInactive));
        }
    }
}
