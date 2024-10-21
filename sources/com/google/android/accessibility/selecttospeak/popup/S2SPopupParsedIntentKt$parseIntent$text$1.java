package com.google.android.accessibility.selecttospeak.popup;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlaysAnimations;
import com.google.android.accessibility.selecttospeak.ui.HighlightScrollingTextView;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.lang.reflect.Constructor;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SPopupParsedIntentKt$parseIntent$text$1 extends Lambda implements Function1 {
    final /* synthetic */ Object S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S2SPopupParsedIntentKt$parseIntent$text$1(int i) {
        super(1);
        this.switching_field = i;
        this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context = "";
    }

    /* JADX WARN: Type inference failed for: r0v34, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v47, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r6v63, types: [kotlin.coroutines.Continuation, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        CharSequence text;
        Object obj2;
        Button button = null;
        Object obj3 = null;
        SharedPreferences sharedPreferences = null;
        switch (this.switching_field) {
            case 0:
                ClipData.Item item = (ClipData.Item) obj;
                item.getClass();
                CharSequence coerceToText = item.coerceToText((Context) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context);
                coerceToText.getClass();
                return coerceToText;
            case 1:
                if (!((Boolean) obj).booleanValue()) {
                    ((ControlOverlaysAnimations) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).foregroundUpdater.invoke(ControlOverlays.OverlayTypes.COLLAPSED);
                    ((ControlOverlaysAnimations) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).analytics.onUiReady();
                }
                return Unit.INSTANCE;
            case 2:
                Integer num = (Integer) obj;
                num.getClass();
                ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).showSurveyAndCloseActivity(num.intValue());
                return Unit.INSTANCE;
            case 3:
                ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).showSurveyAndCloseActivity(0);
                return Unit.INSTANCE;
            case 4:
                Boolean bool = (Boolean) obj;
                Button button2 = ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).playPauseButton;
                if (button2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                    button2 = null;
                }
                bool.getClass();
                button2.setSelected(bool.booleanValue());
                Button button3 = ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).playPauseButton;
                if (button3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                } else {
                    button = button3;
                }
                if (bool.booleanValue()) {
                    text = ((AppCompatActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getResources().getText(R.string.s2s_popup_resume);
                } else {
                    text = ((AppCompatActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getResources().getText(R.string.s2s_popup_pause);
                }
                button.setContentDescription(text);
                return Unit.INSTANCE;
            case 5:
                CharSequence charSequence = (CharSequence) obj;
                HighlightScrollingTextView scrollView = ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getScrollView();
                charSequence.getClass();
                scrollView.textView.setText(charSequence);
                return Unit.INSTANCE;
            case 6:
                Integer num2 = (Integer) obj;
                HighlightScrollingTextView scrollView2 = ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getScrollView();
                num2.getClass();
                scrollView2.adjustScroll(num2.intValue());
                return Unit.INSTANCE;
            case 7:
                Boolean bool2 = (Boolean) obj;
                bool2.getClass();
                if (bool2.booleanValue()) {
                    ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getScrollView().invalidate();
                }
                return Unit.INSTANCE;
            case 8:
                Float f = (Float) obj;
                HighlightScrollingTextView scrollView3 = ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getScrollView();
                f.getClass();
                TextView textView = scrollView3.textView;
                float floatValue = f.floatValue();
                if (Math.abs(floatValue - textView.getTextSize()) >= 0.01f) {
                    scrollView3.textView.setTextSize(0, floatValue);
                    scrollView3.textView.post(new ContextMenuDialog$$ExternalSyntheticLambda5(scrollView3, 19));
                }
                SharedPreferences sharedPreferences2 = ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).prefs;
                if (sharedPreferences2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                } else {
                    sharedPreferences = sharedPreferences2;
                }
                sharedPreferences.edit().putInt(((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getString(R.string.s2s_pref_popup_text_size), ((SelectToSpeakPopupActivity) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).getTextSizeModel().currentIndex).apply();
                return Unit.INSTANCE;
            case 9:
                if (obj == this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context) {
                    return "(this Collection)";
                }
                return String.valueOf(obj);
            case 10:
                String str = (String) obj;
                str.getClass();
                return ((String) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).concat(str);
            case 11:
                String str2 = (String) obj;
                str2.getClass();
                if (OnDeviceStainRemovalLogEvent.isBlank(str2)) {
                    int length = str2.length();
                    Object obj4 = this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context;
                    if (length < ((String) obj4).length()) {
                        return obj4;
                    }
                    return str2;
                }
                return ((String) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).concat(str2);
            case 12:
                IntRange intRange = (IntRange) obj;
                intRange.getClass();
                return OnDeviceStainRemovalLogEvent.substring(this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context, intRange);
            case 13:
                this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context.resumeWith(Unit.INSTANCE);
                return Unit.INSTANCE;
            case 14:
                Throwable th = (Throwable) obj;
                Object newInstance = ((Constructor) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).newInstance(th.getMessage(), th);
                newInstance.getClass();
                return (Throwable) newInstance;
            case 15:
                Throwable th2 = (Throwable) obj;
                Object newInstance2 = ((Constructor) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).newInstance(th2.getMessage());
                newInstance2.getClass();
                Throwable th3 = (Throwable) newInstance2;
                th3.initCause(th2);
                return th3;
            case 16:
                Object newInstance3 = ((Constructor) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).newInstance((Throwable) obj);
                newInstance3.getClass();
                return (Throwable) newInstance3;
            case 17:
                Object newInstance4 = ((Constructor) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context).newInstance(null);
                newInstance4.getClass();
                Throwable th4 = (Throwable) newInstance4;
                th4.initCause((Throwable) obj);
                return th4;
            default:
                Throwable th5 = (Throwable) obj;
                try {
                    Throwable th6 = (Throwable) this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context.invoke(th5);
                    boolean areEqual = Intrinsics.areEqual(th5.getMessage(), th6.getMessage());
                    obj2 = th6;
                    if (!areEqual) {
                        boolean areEqual2 = Intrinsics.areEqual(th6.getMessage(), th5.toString());
                        obj2 = th6;
                        if (!areEqual2) {
                            obj2 = null;
                        }
                    }
                } catch (Throwable th7) {
                    obj2 = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th7);
                }
                if (true != (obj2 instanceof Result.Failure)) {
                    obj3 = obj2;
                }
                return (Throwable) obj3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S2SPopupParsedIntentKt$parseIntent$text$1(int i, byte[] bArr) {
        super(1);
        this.switching_field = i;
        this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context = "    ";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S2SPopupParsedIntentKt$parseIntent$text$1(Object obj, int i) {
        super(1);
        this.switching_field = i;
        this.S2SPopupParsedIntentKt$parseIntent$text$1$ar$$context = obj;
    }
}
