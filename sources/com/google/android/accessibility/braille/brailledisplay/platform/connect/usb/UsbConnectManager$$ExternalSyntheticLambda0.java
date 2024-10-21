package com.google.android.accessibility.braille.brailledisplay.platform.connect.usb;

import android.content.Context;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.marvin.talkback.R;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Range;
import j$.util.function.BiConsumer$CC;
import java.util.function.BiConsumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class UsbConnectManager$$ExternalSyntheticLambda0 implements BiConsumer {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ UsbConnectManager$$ExternalSyntheticLambda0(int i) {
        this.switching_field = i;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        switch (this.switching_field) {
            case 0:
                Context context = (Context) obj;
                BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putBoolean(context.getString(R.string.pref_bd_show_usb_connect_dialog), ((Boolean) obj2).booleanValue()).apply();
                return;
            case 1:
                Context context2 = (Context) obj;
                BrailleUserPreferences.getSharedPreferences$ar$ds(context2).edit().putBoolean(context2.getString(R.string.pref_bd_show_navigation_command_unavailable_tip), ((Boolean) obj2).booleanValue()).apply();
                return;
            case 2:
                BrailleUserPreferences.writeCurrentActiveInputCode((Context) obj, (BrailleLanguages.Code) obj2);
                return;
            case 3:
                Context context3 = (Context) obj;
                BrailleUserPreferences.getSharedPreferences$ar$ds(context3).edit().putBoolean(context3.getString(R.string.pref_bd_show_switch_input_code_gesture_tip), ((Boolean) obj2).booleanValue()).apply();
                return;
            case 4:
                Context context4 = (Context) obj;
                BrailleUserPreferences.getSharedPreferences$ar$ds(context4).edit().putBoolean(context4.getString(R.string.pref_bd_show_switch_output_code_gesture_tip), ((Boolean) obj2).booleanValue()).apply();
                return;
            case 5:
                BrailleUserPreferences.writeCurrentActiveOutputCode((Context) obj, (BrailleLanguages.Code) obj2);
                return;
            case 6:
                Context context5 = (Context) obj;
                BrailleUserPreferences.getSharedPreferences$ar$ds(context5).edit().putBoolean(context5.getString(R.string.pref_show_switch_input_code_gesture_tip), ((Boolean) obj2).booleanValue()).apply();
                return;
            case 7:
                BrailleUserPreferences.writeCurrentActiveInputCode((Context) obj, (BrailleLanguages.Code) obj2);
                return;
            case 8:
                int i = ImageCaptioner.ImageCaptioner$ar$NoOp;
                ((ImageCaptioner.CaptionResult) obj2).recycleAndClearScreenshots();
                return;
            case 9:
                ((ImmutableList.Builder) obj).add$ar$ds$4f674a09_0(obj2);
                return;
            case 10:
                ((ImmutableSet.Builder) obj).add$ar$ds$187ad64f_0(obj2);
                return;
            default:
                ((SplitInstallSharedPreferences) obj).add$ar$ds$52de16dd_0((Range) obj2);
                return;
        }
    }

    public final /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.switching_field) {
            case 0:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 1:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 2:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 3:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 4:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 5:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 6:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 7:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 8:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 9:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            case 10:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            default:
                return BiConsumer$CC.$default$andThen(this, biConsumer);
        }
    }
}
