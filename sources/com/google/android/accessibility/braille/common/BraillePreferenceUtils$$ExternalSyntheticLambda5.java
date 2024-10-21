package com.google.android.accessibility.braille.common;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.liblouis.LibLouisTranslator;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.accessibility.talkback.compositor.CompositorUtils;
import com.google.android.accessibility.talkback.compositor.EventFeedback;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.roledescription.RoleDescriptionExtractor;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.input.TextEventInterpretation;
import com.google.android.accessibility.utils.output.SpeechCleanupUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import j$.util.Optional;
import j$.util.function.Function$CC;
import java.util.function.Function;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BraillePreferenceUtils$$ExternalSyntheticLambda5 implements Function {
    public final /* synthetic */ Object BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BraillePreferenceUtils$$ExternalSyntheticLambda5(Object obj, int i) {
        this.switching_field = i;
        this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0 = obj;
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        boolean z;
        int i;
        int i2;
        boolean z2;
        int i3;
        int i4;
        CharSequence string;
        char c;
        String string2;
        String string3;
        int i5;
        String string4;
        int i6;
        CharSequence charSequence = "";
        int i7 = 1073741825;
        switch (this.switching_field) {
            case 0:
                return ((BrailleLanguages.Code) obj).getUserFacingName((Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0);
            case 1:
                return ((BrailleDisplaySettingsFragment) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0).m49x12a727fa((ConnectableDevice) obj);
            case 2:
                return ((LibLouisTranslator) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0).translateToPrintDirect$ar$ds((BrailleWord) obj);
            case 3:
                CronetEngineBuilderImpl.Pkp pkp = (CronetEngineBuilderImpl.Pkp) obj;
                ImmutableList immutableList = ContextMenuDialog.ITEM_STRING_IDS;
                if (!TextUtils.isEmpty(pkp.CronetEngineBuilderImpl$Pkp$ar$mHost)) {
                    Object obj2 = this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                    Object obj3 = pkp.CronetEngineBuilderImpl$Pkp$ar$mExpirationDate;
                    CharSequence charSequence2 = pkp.CronetEngineBuilderImpl$Pkp$ar$mHost;
                    int color = ((Context) obj2).getColor(R.color.dialog_secondary_text);
                    SpannableString spannableString = new SpannableString(charSequence2);
                    spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), 33);
                    return TextUtils.concat(obj3, "\n", spannableString);
                }
                return pkp.CronetEngineBuilderImpl$Pkp$ar$mExpirationDate;
            case 4:
                return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0).m81x52f73443((TouchDots) obj);
            case 5:
                CharSequence eventContentDescriptionOrEventAggregateText = SpannableUtils$NonCopyableTextSpan.getEventContentDescriptionOrEventAggregateText((AccessibilityEvent) ((Compositor$HandleEventOptions) obj).Compositor$HandleEventOptions$ar$eventObject, ((GlobalVariables) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0).userPreferredLocale);
                LogUtils.v("EventTypeAnnouncementFeedbackRule", " ttsOutputRule= eventContentDescriptionOrEventAggregateText, ", new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(eventContentDescriptionOrEventAggregateText);
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider = Integer.valueOf(R.array.notification_pattern);
                return builder$ar$class_merging$f18d5ad3_0.build();
            case 6:
                Compositor$HandleEventOptions compositor$HandleEventOptions = (Compositor$HandleEventOptions) obj;
                CharSequence eventContentDescriptionOrEventAggregateText2 = SpannableUtils$NonCopyableTextSpan.getEventContentDescriptionOrEventAggregateText((AccessibilityEvent) compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventObject, ((GlobalVariables) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0).userPreferredLocale);
                if (compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$sourceNode == null) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i = R.raw.focus;
                } else {
                    i = -1;
                }
                if (true != z) {
                    i2 = -1;
                } else {
                    i2 = R.array.view_hovered_pattern;
                }
                LogUtils.v("EventTypeHoverEnterFeedbackRule", StringBuilderUtils.joinFields(" ttsOutputRule= eventContentDescriptionOrEventAggregateText, ", StringBuilderUtils.optionalTag("sourceNodeIsNull", z)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(eventContentDescriptionOrEventAggregateText2);
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider = Integer.valueOf(i);
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider = Integer.valueOf(i2);
                return builder$ar$class_merging$f18d5ad3_02.build();
            case 7:
                Compositor$HandleEventOptions compositor$HandleEventOptions2 = (Compositor$HandleEventOptions) obj;
                if (!FormFactorUtils.getInstance().isAndroidTv && !FormFactorUtils.getInstance().isAndroidWear) {
                    charSequence = SpannableUtils$NonCopyableTextSpan.getEventContentDescriptionOrEventAggregateText((AccessibilityEvent) compositor$HandleEventOptions2.Compositor$HandleEventOptions$ar$eventObject, ((GlobalVariables) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0).userPreferredLocale);
                }
                if (compositor$HandleEventOptions2.Compositor$HandleEventOptions$ar$sourceNode == null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    i3 = R.raw.focus_actionable;
                } else {
                    i3 = -1;
                }
                if (true != z2) {
                    i4 = -1;
                } else {
                    i4 = R.array.view_focused_or_selected_pattern;
                }
                LogUtils.v("EventTypeViewFocusedFeedbackRule", StringBuilderUtils.joinFields(" ttsOutputRule= eventContentDescriptionOrEventAggregateText, ", StringBuilderUtils.optionalTag("sourceNodeIsNull", z2)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(charSequence);
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider = Integer.valueOf(i3);
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider = Integer.valueOf(i4);
                return builder$ar$class_merging$f18d5ad3_03.build();
            case 8:
                Compositor$HandleEventOptions compositor$HandleEventOptions3 = (Compositor$HandleEventOptions) obj;
                CharSequence nodeRoleDescriptionText = ((RoleDescriptionExtractor) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0).nodeRoleDescriptionText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions3.Compositor$HandleEventOptions$ar$sourceNode, (AccessibilityEvent) compositor$HandleEventOptions3.Compositor$HandleEventOptions$ar$eventObject);
                LogUtils.v("InputDescribeNodeFeedbackRule", " ttsOutputRule= nodeRoleDescriptionText ", new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(nodeRoleDescriptionText);
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 0;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_04.build();
            case 9:
                Compositor$HandleEventOptions compositor$HandleEventOptions4 = (Compositor$HandleEventOptions) obj;
                Object obj4 = compositor$HandleEventOptions4.Compositor$HandleEventOptions$ar$eventObject;
                boolean z3 = SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) compositor$HandleEventOptions4.Compositor$HandleEventOptions$ar$eventInterpretation).mIsCutAction;
                Object obj5 = this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                if (z3) {
                    Object[] objArr = {((AccessibilityEvent) obj4).getBeforeText()};
                    Context context = (Context) obj5;
                    string = CompositorUtils.joinCharSequences(context.getString(R.string.template_text_cut, objArr), context.getString(R.string.value_text_cleared));
                } else {
                    string = ((Context) obj5).getString(R.string.value_text_cleared);
                }
                CharSequence notifyErrorStateText = SpannableUtils$NonCopyableTextSpan.notifyErrorStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions4.Compositor$HandleEventOptions$ar$sourceNode, (Context) obj5);
                CharSequence joinCharSequences = CompositorUtils.joinCharSequences(string, notifyErrorStateText);
                LogUtils.v("InputTextFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("changedText", string), StringBuilderUtils.optionalText("notifyErrorState", notifyErrorStateText), StringBuilderUtils.optionalTag("isCutAction", z3)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences);
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                return builder$ar$class_merging$f18d5ad3_05.build();
            case 10:
                Compositor$HandleEventOptions compositor$HandleEventOptions5 = (Compositor$HandleEventOptions) obj;
                TextEventInterpretation safeTextInterpretation = SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) compositor$HandleEventOptions5.Compositor$HandleEventOptions$ar$eventInterpretation);
                boolean z4 = safeTextInterpretation.mIsCutAction;
                CharSequence charSequence3 = safeTextInterpretation.mRemovedText;
                Context context2 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                CharSequence cleanupString = CompositorUtils.getCleanupString(charSequence3, context2);
                if (z4) {
                    c = 0;
                    string2 = context2.getString(R.string.template_text_cut, cleanupString);
                } else {
                    c = 0;
                    string2 = context2.getString(R.string.template_text_removed, cleanupString);
                }
                CharSequence notifyErrorStateText2 = SpannableUtils$NonCopyableTextSpan.notifyErrorStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions5.Compositor$HandleEventOptions$ar$sourceNode, context2);
                CharSequence[] charSequenceArr = new CharSequence[2];
                charSequenceArr[c] = string2;
                charSequenceArr[1] = notifyErrorStateText2;
                CharSequence joinCharSequences2 = CompositorUtils.joinCharSequences(charSequenceArr);
                if (true == TextUtils.isEmpty(safeTextInterpretation.mInitialWord)) {
                    i7 = 1;
                }
                LogUtils.v("InputTextFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("changedText", string2), StringBuilderUtils.optionalText("notifyErrorState", notifyErrorStateText2), StringBuilderUtils.optionalTag(" isCutAction", z4)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences2);
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = Integer.valueOf(i7);
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_06.setTtsPitch$ar$ds(1.2d);
                return builder$ar$class_merging$f18d5ad3_06.build();
            case 11:
                Compositor$HandleEventOptions compositor$HandleEventOptions6 = (Compositor$HandleEventOptions) obj;
                TextEventInterpretation safeTextInterpretation2 = SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) compositor$HandleEventOptions6.Compositor$HandleEventOptions$ar$eventInterpretation);
                CharSequence charSequence4 = safeTextInterpretation2.mAddedText;
                Context context3 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                CharSequence cleanupString2 = CompositorUtils.getCleanupString(charSequence4, context3);
                String string5 = context3.getString(R.string.template_text_replaced, cleanupString2, CompositorUtils.getCleanupString(safeTextInterpretation2.mRemovedText, context3));
                if (!SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) compositor$HandleEventOptions6.Compositor$HandleEventOptions$ar$eventInterpretation).mIsPasteAction) {
                    charSequence = SpannableUtils$NonCopyableTextSpan.spelling(cleanupString2, context3);
                }
                CharSequence charSequence5 = charSequence;
                CharSequence notifyMaxLengthReachedStateText = SpannableUtils$NonCopyableTextSpan.notifyMaxLengthReachedStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions6.Compositor$HandleEventOptions$ar$sourceNode, context3);
                CharSequence notifyErrorStateText3 = SpannableUtils$NonCopyableTextSpan.notifyErrorStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions6.Compositor$HandleEventOptions$ar$sourceNode, context3);
                CharSequence joinCharSequences3 = CompositorUtils.joinCharSequences(string5, charSequence5, notifyMaxLengthReachedStateText, notifyErrorStateText3);
                LogUtils.v("InputTextFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("changedText", string5), StringBuilderUtils.optionalText("spellingTextAddedState", charSequence5), StringBuilderUtils.optionalText("notifyMaxLengthReachedState", notifyMaxLengthReachedStateText), StringBuilderUtils.optionalText("notifyErrorState", notifyErrorStateText3)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences3);
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.setTtsPitch$ar$ds(1.2d);
                return builder$ar$class_merging$f18d5ad3_07.build();
            case 12:
                return SpeechCleanupUtils.cleanUp((Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0, (CharSequence) obj);
            case 13:
                Compositor$HandleEventOptions compositor$HandleEventOptions7 = (Compositor$HandleEventOptions) obj;
                Context context4 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                String string6 = context4.getString(R.string.symbol_bullet);
                CharSequence notifyMaxLengthReachedStateText2 = SpannableUtils$NonCopyableTextSpan.notifyMaxLengthReachedStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions7.Compositor$HandleEventOptions$ar$sourceNode, context4);
                CharSequence notifyErrorStateText4 = SpannableUtils$NonCopyableTextSpan.notifyErrorStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions7.Compositor$HandleEventOptions$ar$sourceNode, context4);
                CharSequence joinCharSequences4 = CompositorUtils.joinCharSequences(string6, notifyMaxLengthReachedStateText2, notifyErrorStateText4);
                LogUtils.v("InputTextPasswordFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("symbolBullet", string6), StringBuilderUtils.optionalText("notifyMaxLengthReachedState", notifyMaxLengthReachedStateText2), StringBuilderUtils.optionalText("notifyErrorState", notifyErrorStateText4)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences4);
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_08.build();
            case 14:
                Context context5 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                String string7 = context5.getString(R.string.template_text_removed, context5.getString(R.string.symbol_bullet));
                CharSequence notifyErrorStateText5 = SpannableUtils$NonCopyableTextSpan.notifyErrorStateText((AccessibilityNodeInfoCompat) ((Compositor$HandleEventOptions) obj).Compositor$HandleEventOptions$ar$sourceNode, context5);
                CharSequence joinCharSequences5 = CompositorUtils.joinCharSequences(string7, notifyErrorStateText5);
                LogUtils.v("InputTextPasswordFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("textRemovedState", string7), StringBuilderUtils.optionalText("notifyErrorState", notifyErrorStateText5)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_09 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences5);
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_09.setTtsPitch$ar$ds(1.2d);
                return builder$ar$class_merging$f18d5ad3_09.build();
            case 15:
                Compositor$HandleEventOptions compositor$HandleEventOptions8 = (Compositor$HandleEventOptions) obj;
                Object[] objArr2 = {String.valueOf(((AccessibilityEvent) compositor$HandleEventOptions8.Compositor$HandleEventOptions$ar$eventObject).getRemovedCount()), String.valueOf(((AccessibilityEvent) compositor$HandleEventOptions8.Compositor$HandleEventOptions$ar$eventObject).getAddedCount())};
                Context context6 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                String string8 = context6.getString(R.string.template_replaced_characters, objArr2);
                CharSequence notifyMaxLengthReachedStateText3 = SpannableUtils$NonCopyableTextSpan.notifyMaxLengthReachedStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions8.Compositor$HandleEventOptions$ar$sourceNode, context6);
                CharSequence notifyErrorStateText6 = SpannableUtils$NonCopyableTextSpan.notifyErrorStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions8.Compositor$HandleEventOptions$ar$sourceNode, context6);
                CharSequence joinCharSequences6 = CompositorUtils.joinCharSequences(string8, notifyMaxLengthReachedStateText3, notifyErrorStateText6);
                LogUtils.v("InputTextPasswordFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("textReplacedState", string8), StringBuilderUtils.optionalText("notifyMaxLengthReachedState", notifyMaxLengthReachedStateText3), StringBuilderUtils.optionalText("notifyErrorState", notifyErrorStateText6)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_010 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences6);
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_010.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_010.setTtsPitch$ar$ds(1.2d);
                return builder$ar$class_merging$f18d5ad3_010.build();
            case 16:
                Compositor$HandleEventOptions compositor$HandleEventOptions9 = (Compositor$HandleEventOptions) obj;
                CharSequence charSequence6 = SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) compositor$HandleEventOptions9.Compositor$HandleEventOptions$ar$eventInterpretation).mDeselectedText;
                Context context7 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                CharSequence cleanupString3 = CompositorUtils.getCleanupString(charSequence6, context7);
                if (TextUtils.isEmpty(cleanupString3)) {
                    string3 = "";
                } else {
                    string3 = context7.getString(R.string.template_text_unselected, cleanupString3);
                }
                CharSequence cleanupString4 = CompositorUtils.getCleanupString(SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) compositor$HandleEventOptions9.Compositor$HandleEventOptions$ar$eventInterpretation).mSelectedText, context7);
                if (TextUtils.isEmpty(cleanupString4)) {
                    string4 = "";
                    i6 = 2;
                    i5 = 0;
                } else {
                    i5 = 0;
                    string4 = context7.getString(R.string.template_text_selected, cleanupString4);
                    i6 = 2;
                }
                CharSequence[] charSequenceArr2 = new CharSequence[i6];
                charSequenceArr2[i5] = string3;
                charSequenceArr2[1] = string4;
                CharSequence joinCharSequences7 = CompositorUtils.joinCharSequences(charSequenceArr2);
                LogUtils.v("InputTextSelectionFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("textDeselectedState", string3), StringBuilderUtils.optionalText("textSelectedState", string4)), new Object[i5]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_011 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_011.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences7);
                builder$ar$class_merging$f18d5ad3_011.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_011.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_011.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_011.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_011.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_011.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_011.build();
            case 17:
                Compositor$HandleEventOptions compositor$HandleEventOptions10 = (Compositor$HandleEventOptions) obj;
                Context context8 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                String string9 = context8.getString(R.string.notification_type_selection_cleared);
                if (((AccessibilityEvent) compositor$HandleEventOptions10.Compositor$HandleEventOptions$ar$eventObject).getToIndex() == 0) {
                    charSequence = context8.getString(R.string.notification_type_beginning_of_field);
                } else if (((AccessibilityEvent) compositor$HandleEventOptions10.Compositor$HandleEventOptions$ar$eventObject).getToIndex() == ((AccessibilityEvent) compositor$HandleEventOptions10.Compositor$HandleEventOptions$ar$eventObject).getItemCount()) {
                    charSequence = context8.getString(R.string.notification_type_end_of_field);
                }
                CharSequence charSequence7 = charSequence;
                CharSequence joinCharSequences8 = CompositorUtils.joinCharSequences(string9, charSequence7);
                LogUtils.v("InputTextSelectionFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("selectionClearedState", string9), StringBuilderUtils.optionalText("notificationTypeEdgeOfFieldState", charSequence7)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_012 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_012.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences8);
                builder$ar$class_merging$f18d5ad3_012.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_012.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_012.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_012.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_012.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_012.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_012.build();
            case 18:
                CharSequence charSequence8 = SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) ((Compositor$HandleEventOptions) obj).Compositor$HandleEventOptions$ar$eventInterpretation).textOrDescription;
                Context context9 = (Context) this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                CharSequence cleanupString5 = CompositorUtils.getCleanupString(charSequence8, context9);
                if (true != TextUtils.isEmpty(cleanupString5)) {
                    charSequence = cleanupString5;
                }
                String string10 = context9.getString(R.string.template_announce_selected_text, charSequence);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_013 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(string10);
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_013.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_013.build();
            case 19:
                Object obj6 = this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_014 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_014.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(((Context) obj6).getString(R.string.value_caps_lock_on));
                builder$ar$class_merging$f18d5ad3_014.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                builder$ar$class_merging$f18d5ad3_014.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_014.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_014.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_014.build();
            default:
                Object obj7 = this.BraillePreferenceUtils$$ExternalSyntheticLambda5$ar$f$0;
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_015 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_015.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(((Context) obj7).getString(R.string.value_caps_lock_off));
                builder$ar$class_merging$f18d5ad3_015.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                builder$ar$class_merging$f18d5ad3_015.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_015.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_015.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_015.build();
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
