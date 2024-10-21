package com.google.android.accessibility.talkback.compositor.rule;

import android.app.Notification;
import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.accessibility.talkback.compositor.CompositorUtils;
import com.google.android.accessibility.talkback.compositor.EventFeedback;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.talkback.compositor.GestureShortcutProvider;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.MagnificationState;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.input.TextEventInterpretation;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import j$.util.Optional;
import j$.util.function.Function$CC;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ GlobalVariables f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(Context context, GlobalVariables globalVariables, int i) {
        this.switching_field = i;
        this.f$0 = context;
        this.f$1 = globalVariables;
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
            default:
                return Function$CC.$default$andThen(this, function);
        }
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Optional of;
        boolean z;
        CharSequence charSequence;
        CharSequence aggregateText;
        int i;
        CharSequence charSequence2;
        boolean z2;
        String str;
        String string;
        int i2;
        int inputType;
        int i3;
        int i4;
        CharSequence charSequence3;
        char c;
        String str2 = "";
        int i5 = 2;
        switch (this.switching_field) {
            case 0:
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                Object obj2 = ((Compositor$HandleEventOptions) obj).Compositor$HandleEventOptions$ar$sourceNode;
                Locale locale = this.f$1.userPreferredLocale;
                if (obj2 == null) {
                    LogUtils.v("EventTypeViewClickedFeedbackRule", "source node is null", new Object[0]);
                    of = Optional.empty();
                } else {
                    Context context = this.f$0;
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj2;
                    CharSequence nodeCheckedStateText = SpannableUtils$NonCopyableTextSpan.getNodeCheckedStateText(accessibilityNodeInfoCompat, context, locale);
                    if (TextUtils.isEmpty(nodeCheckedStateText)) {
                        int childCount = accessibilityNodeInfoCompat.getChildCount();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= childCount) {
                                nodeCheckedStateText = "";
                            } else {
                                AccessibilityNodeInfoCompat child = accessibilityNodeInfoCompat.getChild(i6);
                                if (child != null) {
                                    CharSequence nodeCheckedStateText2 = SpannableUtils$NonCopyableTextSpan.getNodeCheckedStateText(child, context, locale);
                                    if (!TextUtils.isEmpty(nodeCheckedStateText2)) {
                                        LogUtils.v("EventTypeViewClickedFeedbackRule", " getViewCheckStateText: child node index=%d  isChecked=%b", Integer.valueOf(i6), Boolean.valueOf(accessibilityNodeInfoCompat.isChecked()));
                                        nodeCheckedStateText = nodeCheckedStateText2;
                                    }
                                }
                                i6++;
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(nodeCheckedStateText)) {
                        LogUtils.v("EventTypeViewClickedFeedbackRule", " ttsOutputRule= checkedState", new Object[0]);
                        of = Optional.of(nodeCheckedStateText);
                    } else {
                        LogUtils.v("EventTypeViewClickedFeedbackRule", StringBuilderUtils.joinFields(StringBuilderUtils.optionalTag("srcIsCheckable", accessibilityNodeInfoCompat.isCheckable()), StringBuilderUtils.optionalTag("srcIsChecked", accessibilityNodeInfoCompat.isChecked()), StringBuilderUtils.optionalTag("srcIsSelected", accessibilityNodeInfoCompat.isSelected())), new Object[0]);
                        CharSequence selectedStateText = SpannableUtils$NonCopyableTextSpan.getSelectedStateText(accessibilityNodeInfoCompat, context);
                        if (!TextUtils.isEmpty(selectedStateText)) {
                            LogUtils.v("EventTypeViewClickedFeedbackRule", " ttsOutputRule= selectedState", new Object[0]);
                            of = Optional.of(selectedStateText);
                        } else {
                            LogUtils.v("EventTypeViewClickedFeedbackRule", " ttsOutputRule= collapsedOrExpandedStateText", new Object[0]);
                            of = Optional.of(SpannableUtils$NonCopyableTextSpan.getCollapsedOrExpandedStateText(accessibilityNodeInfoCompat, context));
                        }
                    }
                }
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = of;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 0;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider = Integer.valueOf(R.raw.tick);
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider = Integer.valueOf(R.array.view_clicked_pattern);
                return builder$ar$class_merging$f18d5ad3_0.build();
            case 1:
                Compositor$HandleEventOptions compositor$HandleEventOptions = (Compositor$HandleEventOptions) obj;
                int sourceRole = SpannableUtils$IdentifierSpan.getSourceRole((AccessibilityEvent) compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventObject);
                Notification extractNotification = SpannableUtils$IdentifierSpan.extractNotification((AccessibilityEvent) compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventObject);
                Context context2 = this.f$0;
                CharSequence notificationCategoryStateText = SpannableUtils$NonCopyableTextSpan.getNotificationCategoryStateText(context2, extractNotification);
                if (sourceRole == 23) {
                    z = true;
                } else {
                    z = false;
                }
                GlobalVariables globalVariables = this.f$1;
                if (z) {
                    charSequence2 = SpannableUtils$NonCopyableTextSpan.getEventContentDescriptionOrEventAggregateText((AccessibilityEvent) compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventObject, globalVariables.userPreferredLocale);
                    LogUtils.v("EventTypeNotificationStateChangedFeedbackRule", " ttsOutputRule= eventContentDescriptionOrEventAggregateText, role=toast", new Object[0]);
                } else {
                    Notification extractNotification2 = SpannableUtils$IdentifierSpan.extractNotification((AccessibilityEvent) compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventObject);
                    if (extractNotification2 == null) {
                        aggregateText = "";
                        charSequence = aggregateText;
                        i = 2;
                    } else {
                        ArrayList arrayList = new ArrayList();
                        CharSequence charSequence4 = extractNotification2.tickerText;
                        if (extractNotification2.extras != null) {
                            charSequence = "";
                            CharSequence charSequence5 = extractNotification2.extras.getCharSequence("android.title");
                            CharSequence charSequence6 = extractNotification2.extras.getCharSequence("android.text");
                            if (!TextUtils.isEmpty(charSequence5)) {
                                arrayList.add(charSequence5);
                            }
                            if (!TextUtils.isEmpty(charSequence6)) {
                                arrayList.add(charSequence6);
                            } else {
                                arrayList.add(charSequence4);
                            }
                        } else {
                            charSequence = "";
                        }
                        if (arrayList.isEmpty()) {
                            aggregateText = null;
                        } else {
                            aggregateText = StringBuilderUtils.getAggregateText(arrayList);
                        }
                        if (aggregateText == null) {
                            aggregateText = charSequence;
                        }
                        i = 2;
                    }
                    CharSequence[] charSequenceArr = new CharSequence[i];
                    charSequenceArr[0] = notificationCategoryStateText;
                    charSequenceArr[1] = aggregateText;
                    CharSequence joinCharSequences = CompositorUtils.joinCharSequences(charSequenceArr);
                    if (notificationCategoryStateText.toString().equals(context2.getString(R.string.notification_category_call)) && globalVariables.enableMediaControlHintForCall && SpannableUtils$IdentifierSpan.isAtLeastU() && globalVariables.usageHintEnabled) {
                        GestureShortcutProvider gestureShortcutProvider = globalVariables.gestureShortcutProvider;
                        if (gestureShortcutProvider != null) {
                            GestureShortcutMapping gestureShortcutMapping = (GestureShortcutMapping) gestureShortcutProvider;
                            charSequence = gestureShortcutMapping.getGestureFromActionKey(gestureShortcutMapping.mediaControlShortcut);
                        }
                        if (charSequence != null) {
                            joinCharSequences = CompositorUtils.joinCharSequences(joinCharSequences, context2.getString(R.string.template_hint_for_call, charSequence));
                        }
                    }
                    LogUtils.v("EventTypeNotificationStateChangedFeedbackRule", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("notificationCategory", notificationCategoryStateText), StringBuilderUtils.optionalText("notificationDetails", aggregateText), StringBuilderUtils.optionalText(", role", SpannableUtils$IdentifierSpan.roleToString(sourceRole))), new Object[0]);
                    charSequence2 = joinCharSequences;
                }
                if (!z && !notificationCategoryStateText.toString().equals(context2.getString(R.string.notification_category_call))) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(charSequence2);
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                Boolean valueOf = Boolean.valueOf(z2);
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = valueOf;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = valueOf;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider = valueOf;
                return builder$ar$class_merging$f18d5ad3_02.build();
            case 2:
                Compositor$HandleEventOptions compositor$HandleEventOptions2 = (Compositor$HandleEventOptions) obj;
                int sourceRole2 = SpannableUtils$IdentifierSpan.getSourceRole((AccessibilityEvent) compositor$HandleEventOptions2.Compositor$HandleEventOptions$ar$eventObject);
                CharSequence eventContentDescriptionOrEventAggregateText = SpannableUtils$NonCopyableTextSpan.getEventContentDescriptionOrEventAggregateText((AccessibilityEvent) compositor$HandleEventOptions2.Compositor$HandleEventOptions$ar$eventObject, this.f$1.userPreferredLocale);
                Object obj3 = compositor$HandleEventOptions2.Compositor$HandleEventOptions$ar$eventObject;
                Context context3 = this.f$0;
                if (sourceRole2 != 8) {
                    switch (sourceRole2) {
                        case 20:
                            str2 = context3.getString(R.string.template_drawer_opened, eventContentDescriptionOrEventAggregateText);
                            break;
                        case 21:
                            str2 = context3.getString(R.string.value_sliding_drawer_opened);
                            break;
                        case 22:
                            str2 = context3.getString(R.string.value_options_menu_open);
                            break;
                    }
                } else if (!TextUtils.isEmpty(eventContentDescriptionOrEventAggregateText)) {
                    AccessibilityEvent accessibilityEvent = (AccessibilityEvent) obj3;
                    int itemCount = accessibilityEvent.getItemCount();
                    if (TextUtils.isEmpty(accessibilityEvent.getContentDescription()) && !accessibilityEvent.getText().isEmpty() && TextUtils.isEmpty(accessibilityEvent.getText().get(0)) && itemCount > 0) {
                        str2 = context3.getResources().getQuantityString(R.plurals.template_containers, itemCount, eventContentDescriptionOrEventAggregateText, Integer.valueOf(itemCount));
                    }
                }
                LogUtils.v("EventTypeWindowStateChangedFeedbackRule", StringBuilderUtils.joinFields(" ttsOutputRule= windowStateChangedState, ", StringBuilderUtils.optionalText("role", SpannableUtils$IdentifierSpan.roleToString(sourceRole2)), StringBuilderUtils.optionalText("ttsOutput", str2)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(str2);
                if (sourceRole2 != 22 && (sourceRole2 != 8 || TextUtils.isEmpty(str2))) {
                    i5 = 1;
                }
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = Integer.valueOf(i5);
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = Boolean.valueOf(!SpannableUtils$NonCopyableTextSpan.safeAccessibilityFocusInterpretation((EventInterpretation) compositor$HandleEventOptions2.Compositor$HandleEventOptions$ar$eventInterpretation).isInitialFocusAfterScreenStateChange);
                builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_03.build();
            case 3:
                GlobalVariables globalVariables2 = this.f$1;
                Compositor$HandleEventOptions compositor$HandleEventOptions3 = (Compositor$HandleEventOptions) obj;
                GestureShortcutProvider gestureShortcutProvider2 = globalVariables2.gestureShortcutProvider;
                if (gestureShortcutProvider2 == null) {
                    str = "";
                } else {
                    GestureShortcutMapping gestureShortcutMapping2 = (GestureShortcutMapping) gestureShortcutProvider2;
                    str = gestureShortcutMapping2.getGestureFromActionKey(gestureShortcutMapping2.nextWindowShortcut);
                }
                Context context4 = this.f$0;
                if (FeatureSupport.isMultiFingerGestureSupported() && str != null) {
                    string = context4.getString(R.string.heads_up_window_available_with_gesture, str);
                } else {
                    string = context4.getString(R.string.heads_up_window_available);
                }
                CharSequence notificationCategoryStateText2 = SpannableUtils$NonCopyableTextSpan.getNotificationCategoryStateText(context4, SpannableUtils$IdentifierSpan.extractNotification((AccessibilityEvent) compositor$HandleEventOptions3.Compositor$HandleEventOptions$ar$eventObject));
                if (globalVariables2.usageHintEnabled && !notificationCategoryStateText2.toString().equals(context4.getString(R.string.notification_category_call))) {
                    str2 = string;
                }
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(str2);
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_04.build();
            case 4:
                Compositor$HandleEventOptions compositor$HandleEventOptions4 = (Compositor$HandleEventOptions) obj;
                StringBuilder sb = new StringBuilder();
                TextEventInterpretation safeTextInterpretation = SpannableUtils$NonCopyableTextSpan.safeTextInterpretation((EventInterpretation) compositor$HandleEventOptions4.Compositor$HandleEventOptions$ar$eventInterpretation);
                CharSequence charSequence7 = safeTextInterpretation.mInitialWord;
                Context context5 = this.f$0;
                CharSequence cleanupString = CompositorUtils.getCleanupString(charSequence7, context5);
                CharSequence cleanupString2 = CompositorUtils.getCleanupString(safeTextInterpretation.mAddedText, context5);
                boolean z3 = safeTextInterpretation.mIsPasteAction;
                boolean isEmpty = TextUtils.isEmpty(cleanupString);
                sb.append(String.format(" isPasteAction=%b ", Boolean.valueOf(z3)));
                sb.append(String.format(", isInitialWordEmpty=%b ", Boolean.valueOf(isEmpty)));
                GlobalVariables globalVariables3 = this.f$1;
                CharSequence charSequence8 = cleanupString;
                if (z3) {
                    if (globalVariables3.sayCapital) {
                        cleanupString2 = CompositorUtils.prependCapital(cleanupString2, context5);
                    }
                    StringBuilder sb2 = new StringBuilder(context5.getString(R.string.template_text_pasted, cleanupString2));
                    Object obj4 = compositor$HandleEventOptions4.Compositor$HandleEventOptions$ar$sourceNode;
                    if (obj4 == null) {
                        inputType = 0;
                    } else {
                        inputType = ((AccessibilityNodeInfoCompat) obj4).getInputType();
                    }
                    ImmutableList suggestionSpans = AccessibilityNodeInfoUtils.getSuggestionSpans(context5, cleanupString2, inputType);
                    charSequence8 = sb2;
                    if (!suggestionSpans.isEmpty()) {
                        sb2.append(context5.getString(R.string.template_hint_edit_text_containing_typo, Integer.valueOf(((RegularImmutableList) suggestionSpans).size), globalVariables3.getGestureStringForReadingMenuNextSetting()));
                        charSequence8 = sb2;
                    }
                } else if (isEmpty) {
                    if (globalVariables3.sayCapital) {
                        charSequence8 = CompositorUtils.prependCapital(cleanupString2, context5);
                    } else {
                        charSequence8 = cleanupString2;
                    }
                }
                CharSequence notifyMaxLengthReachedStateText = SpannableUtils$NonCopyableTextSpan.notifyMaxLengthReachedStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions4.Compositor$HandleEventOptions$ar$sourceNode, context5);
                CharSequence notifyErrorStateText = SpannableUtils$NonCopyableTextSpan.notifyErrorStateText((AccessibilityNodeInfoCompat) compositor$HandleEventOptions4.Compositor$HandleEventOptions$ar$sourceNode, context5);
                CharSequence joinCharSequences2 = CompositorUtils.joinCharSequences(charSequence8, notifyMaxLengthReachedStateText, notifyErrorStateText);
                if (!z3 && !isEmpty) {
                    i2 = 1073741825;
                } else {
                    i2 = 1;
                }
                sb.append("\n    ttsOutputRule= ");
                sb.append(String.format("changedText=%s", charSequence8));
                sb.append(String.format(", notifyMaxLengthReachedState=%s", notifyMaxLengthReachedStateText));
                sb.append(String.format(", notifyErrorState=%s", notifyErrorStateText));
                LogUtils.v("InputTextFeedbackRules", sb.toString(), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences2);
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = Integer.valueOf(i2);
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_05.build();
            case 5:
                Compositor$HandleEventOptions compositor$HandleEventOptions5 = (Compositor$HandleEventOptions) obj;
                Object obj5 = compositor$HandleEventOptions5.Compositor$HandleEventOptions$ar$eventInterpretation;
                GlobalVariables globalVariables4 = this.f$1;
                CharSequence eventTraversedText = SpannableUtils$NonCopyableTextSpan.getEventTraversedText((EventInterpretation) obj5, globalVariables4.userPreferredLocale);
                Context context6 = this.f$0;
                CharSequence cleanupString3 = CompositorUtils.getCleanupString(eventTraversedText, context6);
                if (globalVariables4.sayCapital) {
                    cleanupString3 = CompositorUtils.prependCapital(cleanupString3, context6);
                }
                if (((AccessibilityEvent) compositor$HandleEventOptions5.Compositor$HandleEventOptions$ar$eventObject).getToIndex() == 0) {
                    str2 = context6.getString(R.string.notification_type_beginning_of_field);
                } else if (((AccessibilityEvent) compositor$HandleEventOptions5.Compositor$HandleEventOptions$ar$eventObject).getToIndex() == ((AccessibilityEvent) compositor$HandleEventOptions5.Compositor$HandleEventOptions$ar$eventObject).getItemCount()) {
                    str2 = context6.getString(R.string.notification_type_end_of_field);
                }
                CharSequence joinCharSequences3 = CompositorUtils.joinCharSequences(cleanupString3, str2);
                LogUtils.v("InputTextSelectionFeedbackRules", StringBuilderUtils.joinFields(" ttsOutputRule= ", StringBuilderUtils.optionalText("textTraversedState", cleanupString3), StringBuilderUtils.optionalText("notificationTypeEdgeOfFieldState", str2)), new Object[0]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(joinCharSequences3);
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1073741825;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_06.build();
            case 6:
                Object obj6 = ((Compositor$HandleEventOptions) obj).Compositor$HandleEventOptions$ar$eventInterpretation;
                GlobalVariables globalVariables5 = this.f$1;
                CharSequence eventTraversedText2 = SpannableUtils$NonCopyableTextSpan.getEventTraversedText((EventInterpretation) obj6, globalVariables5.userPreferredLocale);
                Context context7 = this.f$0;
                CharSequence cleanupString4 = CompositorUtils.getCleanupString(eventTraversedText2, context7);
                if (globalVariables5.sayCapital) {
                    cleanupString4 = CompositorUtils.prependCapital(cleanupString4, context7);
                }
                if (cleanupString4.length() <= 30) {
                    i3 = 3;
                } else {
                    i3 = 1;
                }
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(cleanupString4);
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = Integer.valueOf(i3);
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 1;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                return builder$ar$class_merging$f18d5ad3_07.build();
            case 7:
                GlobalVariables globalVariables6 = this.f$1;
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                MagnificationState magnificationState = globalVariables6.magnificationState;
                float f = magnificationState.currentScale * 100.0f;
                int i7 = magnificationState.state;
                Integer num = magnificationState.mode;
                Context context8 = this.f$0;
                int i8 = (int) f;
                if (i7 == 1) {
                    if (num == null) {
                        str2 = context8.getString(R.string.template_magnification_on, Integer.valueOf(i8));
                    } else if (num.equals(1)) {
                        str2 = context8.getString(R.string.template_fullscreen_magnification_on, Integer.valueOf(i8));
                    } else if (num.equals(2)) {
                        str2 = context8.getString(R.string.template_partial_magnification_on, Integer.valueOf(i8));
                    } else {
                        i7 = 1;
                    }
                    builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(str2);
                    builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                    builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 4;
                    builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                    builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = false;
                    builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = false;
                    builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                    return builder$ar$class_merging$f18d5ad3_08.build();
                }
                if (i7 == 0) {
                    str2 = context8.getString(R.string.magnification_off);
                } else if (i7 == 2) {
                    if (num == null) {
                        str2 = context8.getString(R.string.template_magnification_scale_changed, Integer.valueOf(i8));
                    } else if (num.equals(1)) {
                        str2 = context8.getString(R.string.template_fullscreen_magnification_scale_changed, Integer.valueOf(i8));
                    } else if (num.equals(2)) {
                        str2 = context8.getString(R.string.template_partial_magnification_scale_changed, Integer.valueOf(i8));
                    }
                }
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(str2);
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 4;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = true;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = false;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = false;
                builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                return builder$ar$class_merging$f18d5ad3_08.build();
            default:
                Object obj7 = ((Compositor$HandleEventOptions) obj).Compositor$HandleEventOptions$ar$eventObject;
                if (obj7 == null) {
                    charSequence3 = "";
                } else {
                    Context context9 = this.f$0;
                    AccessibilityEvent accessibilityEvent2 = (AccessibilityEvent) obj7;
                    if (SpannableUtils$IdentifierSpan.getSourceRole(accessibilityEvent2) == 16) {
                        GlobalVariables globalVariables7 = this.f$1;
                        LogUtils.v("ScrollPositionFeedbackRule", " scrollPositionText role_pager", new Object[0]);
                        charSequence3 = SpannableUtils$NonCopyableTextSpan.getPagerIndexCount(accessibilityEvent2, context9, globalVariables7);
                    } else {
                        int fromIndex = accessibilityEvent2.getFromIndex();
                        int toIndex = accessibilityEvent2.getToIndex();
                        int itemCount2 = accessibilityEvent2.getItemCount();
                        LogUtils.v("ScrollPositionFeedbackRule", StringBuilderUtils.joinFields(" scrollPositionText ", StringBuilderUtils.optionalInt("fromIndex", fromIndex, -1), StringBuilderUtils.optionalInt("toIndex", toIndex, -1), StringBuilderUtils.optionalInt("itemCount", itemCount2, -1)), new Object[0]);
                        if (fromIndex >= 0 && itemCount2 > 0) {
                            int i9 = fromIndex + 1;
                            if (fromIndex != toIndex && toIndex >= 0) {
                                c = 1;
                                int i10 = toIndex + 1;
                                if (i10 <= itemCount2) {
                                    i4 = 0;
                                    charSequence3 = context9.getString(R.string.template_scroll_from_to_count, Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(itemCount2));
                                }
                            } else {
                                c = 1;
                            }
                            i4 = 0;
                            Integer valueOf2 = Integer.valueOf(i9);
                            Integer valueOf3 = Integer.valueOf(itemCount2);
                            Object[] objArr = new Object[2];
                            objArr[0] = valueOf2;
                            objArr[c] = valueOf3;
                            charSequence3 = context9.getString(R.string.template_scroll_from_count, objArr);
                        } else {
                            i4 = 0;
                            charSequence3 = "";
                        }
                        LogUtils.v("ScrollPositionFeedbackRule", " ttsOutputRule= scrollPositionText ", new Object[i4]);
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_09 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(charSequence3);
                        builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 0;
                        builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_09.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                        builder$ar$class_merging$f18d5ad3_09.setTtsPitch$ar$ds(1.2d);
                        return builder$ar$class_merging$f18d5ad3_09.build();
                    }
                }
                i4 = 0;
                LogUtils.v("ScrollPositionFeedbackRule", " ttsOutputRule= scrollPositionText ", new Object[i4]);
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_092 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_092.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(charSequence3);
                builder$ar$class_merging$f18d5ad3_092.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 0;
                builder$ar$class_merging$f18d5ad3_092.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_092.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                builder$ar$class_merging$f18d5ad3_092.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                builder$ar$class_merging$f18d5ad3_092.setTtsPitch$ar$ds(1.2d);
                return builder$ar$class_merging$f18d5ad3_092.build();
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
            default:
                return Function$CC.$default$compose(this, function);
        }
    }

    public /* synthetic */ EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(GlobalVariables globalVariables, Context context, int i) {
        this.switching_field = i;
        this.f$1 = globalVariables;
        this.f$0 = context;
    }
}
