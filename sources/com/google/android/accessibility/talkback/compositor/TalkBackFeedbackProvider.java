package com.google.android.accessibility.talkback.compositor;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda5;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.accessibility.talkback.compositor.EventFeedback;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.talkback.compositor.GestureShortcutProvider;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.HintEventInterpretation;
import com.google.android.accessibility.talkback.compositor.TalkBackFeedbackProvider;
import com.google.android.accessibility.talkback.compositor.hint.AccessibilityFocusHint;
import com.google.android.accessibility.talkback.compositor.hint.tv.AccessibilityFocusHintForTV;
import com.google.android.accessibility.talkback.compositor.roledescription.RoleDescriptionExtractor;
import com.google.android.accessibility.talkback.compositor.rule.EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;
import j$.util.Optional;
import j$.util.function.Function$CC;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackFeedbackProvider {
    public static final EventFeedback EMPTY_FEEDBACK = EventFeedback.builder$ar$class_merging$f18d5ad3_0().build();
    public final Map feedbackRules;

    public TalkBackFeedbackProvider(int i, final Context context, final ExecutionList.RunnableExecutorPair runnableExecutorPair, final GlobalVariables globalVariables, final RoleDescriptionExtractor roleDescriptionExtractor, final ProcessorPhoneticLetters processorPhoneticLetters) {
        final AccessibilityFocusHint accessibilityFocusHint;
        HashMap hashMap = new HashMap();
        this.feedbackRules = hashMap;
        final ProcessStatsCapture processStatsCapture = new ProcessStatsCapture(context, runnableExecutorPair, globalVariables, roleDescriptionExtractor);
        final int i2 = 4;
        hashMap.put(1073741925, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i2) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i2) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i2) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        final int i3 = 5;
        hashMap.put(1073741928, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i3) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i3) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i3) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        final int i4 = 6;
        hashMap.put(1073741935, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i4) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i4) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i4) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        final int i5 = 7;
        hashMap.put(1073741936, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i5) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i5) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i5) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        hashMap.put(1073741929, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 19));
        hashMap.put(1073741930, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 20));
        final int i6 = 1;
        hashMap.put(1073741931, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i6) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i6) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i6) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        final int i7 = 0;
        hashMap.put(1073741932, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i7) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i7) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i7) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        final int i8 = 2;
        hashMap.put(1073741933, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i8) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i8) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i8) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        final int i9 = 3;
        hashMap.put(1073741934, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.KeyboardLockChangedFeedbackRules$$ExternalSyntheticLambda3
            public final /* synthetic */ Function andThen(Function function) {
                switch (i9) {
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
                    default:
                        return Function$CC.$default$andThen(this, function);
                }
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i9) {
                    case 0:
                        Context context2 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context2.getString(R.string.value_num_lock_off));
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_0.build();
                    case 1:
                        Context context3 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_02 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context3.getString(R.string.value_num_lock_on));
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_02.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_02.build();
                    case 2:
                        Context context4 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_03 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context4.getString(R.string.value_scroll_lock_on));
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_03.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_03.build();
                    case 3:
                        Context context5 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_04 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context5.getString(R.string.value_scroll_lock_off));
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_04.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_04.build();
                    case 4:
                        Context context6 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_05 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context6.getString(R.string.talkback_on));
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_05.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_05.build();
                    case 5:
                        Context context7 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_06 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context7.getString(R.string.talkback_disabled));
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_06.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_06.build();
                    case 6:
                        Context context8 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_07 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context8.getString(R.string.orientation_portrait));
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_07.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_07.build();
                    default:
                        Context context9 = context;
                        DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_08 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(context9.getString(R.string.orientation_landscape));
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 2;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = true;
                        builder$ar$class_merging$f18d5ad3_08.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = true;
                        return builder$ar$class_merging$f18d5ad3_08.build();
                }
            }

            public final /* synthetic */ Function compose(Function function) {
                switch (i9) {
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
                    default:
                        return Function$CC.$default$compose(this, function);
                }
            }
        });
        hashMap.put(1073741940, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, i5));
        hashMap.put(16384, new BraillePreferenceUtils$$ExternalSyntheticLambda5(globalVariables, i3));
        hashMap.put(Integer.valueOf(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE), new BraillePreferenceUtils$$ExternalSyntheticLambda5(globalVariables, i4));
        hashMap.put(64, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, i6));
        hashMap.put(32768, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.EventTypeViewAccessibilityFocusedFeedbackRule$$ExternalSyntheticLambda2
            public final /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return EventTypeViewAccessibilityFocusedFeedbackRule.viewAccessibilityFocused$ar$class_merging$ar$class_merging$ar$class_merging((Compositor$HandleEventOptions) obj, context, runnableExecutorPair, globalVariables, processorPhoneticLetters, processStatsCapture);
            }

            public final /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
        hashMap.put(1, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, i7));
        int i10 = 8;
        hashMap.put(8, new BraillePreferenceUtils$$ExternalSyntheticLambda5(globalVariables, i5));
        hashMap.put(2, new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(i5));
        hashMap.put(4, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.EventTypeViewSelectedFeedbackRule$$ExternalSyntheticLambda0
            public final /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            /* JADX WARN: Removed duplicated region for block: B:32:0x014a  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x014f  */
            @Override // java.util.function.Function
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object apply(java.lang.Object r15) {
                /*
                    Method dump skipped, instructions count: 383
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.compositor.rule.EventTypeViewSelectedFeedbackRule$$ExternalSyntheticLambda0.apply(java.lang.Object):java.lang.Object");
            }

            public final /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
        hashMap.put(2048, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.EventTypeWindowContentChangedFeedbackRule$$ExternalSyntheticLambda0
            public final /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            /* JADX WARN: Code restructure failed: missing block: B:79:0x02f9, code lost:
            
                if (r17 == 2) goto L163;
             */
            /* JADX WARN: Code restructure failed: missing block: B:96:0x0234, code lost:
            
                if (r1.getLiveRegion() == 0) goto L132;
             */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0239  */
            /* JADX WARN: Removed duplicated region for block: B:85:0x023c  */
            @Override // java.util.function.Function
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object apply(java.lang.Object r23) {
                /*
                    Method dump skipped, instructions count: 866
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.compositor.rule.EventTypeWindowContentChangedFeedbackRule$$ExternalSyntheticLambda0.apply(java.lang.Object):java.lang.Object");
            }

            public final /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
        hashMap.put(32, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, i8));
        hashMap.put(1073741826, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 9));
        hashMap.put(1073741827, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 10));
        hashMap.put(1073741828, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, 4));
        hashMap.put(1073741829, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 11));
        hashMap.put(1073741830, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 13));
        hashMap.put(1073741831, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 14));
        hashMap.put(1073741832, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 15));
        hashMap.put(1073741837, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, i3));
        hashMap.put(1073741838, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 16));
        hashMap.put(1073741839, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 17));
        hashMap.put(1073741842, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, 6));
        hashMap.put(1073741844, new BraillePreferenceUtils$$ExternalSyntheticLambda5(context, 18));
        hashMap.put(1073741939, new BraillePreferenceUtils$$ExternalSyntheticLambda5(roleDescriptionExtractor, i10));
        hashMap.put(1073741938, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(context, globalVariables, i10));
        if (i == 2) {
            accessibilityFocusHint = new AccessibilityFocusHintForTV(context, globalVariables);
        } else {
            accessibilityFocusHint = new AccessibilityFocusHint(context, globalVariables);
        }
        hashMap.put(1073741937, new Function() { // from class: com.google.android.accessibility.talkback.compositor.rule.HintFeedbackRule$$ExternalSyntheticLambda0
            public final /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                boolean z;
                CharSequence hint;
                boolean z2;
                int hashCode;
                Compositor$HandleEventOptions compositor$HandleEventOptions = (Compositor$HandleEventOptions) obj;
                Object obj2 = compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventInterpretation;
                Object obj3 = compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$sourceNode;
                if (obj2 != null) {
                    AccessibilityFocusHint accessibilityFocusHint2 = accessibilityFocusHint;
                    EventInterpretation eventInterpretation = (EventInterpretation) obj2;
                    HintEventInterpretation hintEventInterpretation = eventInterpretation.mHint;
                    if (hintEventInterpretation == null) {
                        LogUtils.e("HintFeedbackRule", "speakHintFeedback: error: hintEventInterpretation is null.", new Object[0]);
                        return TalkBackFeedbackProvider.EMPTY_FEEDBACK;
                    }
                    GlobalVariables globalVariables2 = globalVariables;
                    Context context2 = context;
                    StringBuilder sb = new StringBuilder();
                    int i11 = hintEventInterpretation.mHintType;
                    boolean z3 = true;
                    if (i11 != 1) {
                        hint = "";
                        if (i11 != 2) {
                            if (i11 != 3 && i11 != 4) {
                                if (i11 != 5) {
                                    if (obj3 == null) {
                                        z2 = false;
                                    } else {
                                        z2 = true;
                                    }
                                } else {
                                    if (obj3 == null) {
                                        z2 = false;
                                    } else {
                                        z2 = true;
                                    }
                                    StringBuilder sb2 = new StringBuilder();
                                    boolean z4 = globalVariables2.usageHintEnabled;
                                    boolean hasReadingMenuActionSettings = globalVariables2.hasReadingMenuActionSettings();
                                    int ordinal = SelectorController.getCurrentSetting(globalVariables2.mContext).ordinal();
                                    sb2.append(String.format("enableUsageHint=%s", Boolean.valueOf(z4)));
                                    sb2.append(String.format(", hasReadingMenuActionSettings=%s", Boolean.valueOf(hasReadingMenuActionSettings)));
                                    sb2.append(String.format(", currentReadingMenu=%s", Integer.valueOf(ordinal)));
                                    LogUtils.v("HintFeedbackRule", "    textSuggestionHint: %s", sb2.toString());
                                    if (z4 && hasReadingMenuActionSettings) {
                                        if (ordinal == SelectorController.Setting.ACTIONS.ordinal()) {
                                            if (globalVariables2.inputModeTracker.mInputMode == 1) {
                                                CharSequence keyComboStringRepresentation = globalVariables2.getKeyComboStringRepresentation(R.string.keycombo_shortcut_global_adjust_reading_setting_next);
                                                if (!TextUtils.isEmpty(keyComboStringRepresentation)) {
                                                    hint = keyComboStringRepresentation;
                                                    hint = context2.getString(R.string.template_hint_reading_menu_actions_for_spelling_suggestion, hint);
                                                }
                                            }
                                            GestureShortcutProvider gestureShortcutProvider = globalVariables2.gestureShortcutProvider;
                                            if (gestureShortcutProvider != null) {
                                                hint = gestureShortcutProvider.readingMenuUpShortcut();
                                            }
                                            hint = context2.getString(R.string.template_hint_reading_menu_actions_for_spelling_suggestion, hint);
                                        } else {
                                            hint = context2.getString(R.string.template_hint_reading_menu_spelling_suggestion, globalVariables2.getGestureStringForReadingMenuNextSetting());
                                        }
                                    } else {
                                        hint = context2.getString(R.string.hint_suggestion);
                                    }
                                }
                            } else {
                                if (obj3 == null) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                boolean z5 = globalVariables2.usageHintEnabled;
                                LogUtils.v("HintFeedbackRule", "    hintInterpretationText: %s", String.format(" enableUsageHint=%s", Boolean.valueOf(z5)));
                                if (z5) {
                                    hint = hintEventInterpretation.mText;
                                }
                            }
                        } else {
                            if (obj3 == null) {
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                            if (obj3 == null) {
                                LogUtils.e("HintFeedbackRule", "    inputFocusHint: src node null", new Object[0]);
                            } else {
                                StringBuilder sb3 = new StringBuilder();
                                boolean z6 = globalVariables2.usageHintEnabled;
                                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj3;
                                boolean isEnabled = accessibilityNodeInfoCompat.isEnabled();
                                boolean isFocused = accessibilityNodeInfoCompat.isFocused();
                                sb3.append(String.format("enableUsageHint=%s", Boolean.valueOf(z6)));
                                sb3.append(String.format(", isEnabled=%s", Boolean.valueOf(isEnabled)));
                                sb3.append(String.format(", isFocused=%s", Boolean.valueOf(isFocused)));
                                if (z6 && isFocused && isEnabled) {
                                    int role = SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat);
                                    boolean isPassword = accessibilityNodeInfoCompat.isPassword();
                                    boolean z7 = globalVariables2.mShouldSpeakPasswords;
                                    sb3.append(String.format(", role=%s", SpannableUtils$IdentifierSpan.roleToString(role)));
                                    sb3.append(String.format(", isPassword=%s", Boolean.valueOf(isPassword)));
                                    sb3.append(String.format(", shouldSpeakPasswords=%s", Boolean.valueOf(z7)));
                                    LogUtils.v("HintFeedbackRule", "    inputFocusHint: %s", sb3.toString());
                                    if (role == 4 && isPassword && !z7) {
                                        hint = context2.getString(R.string.summaryOff_pref_speak_passwords_without_headphones);
                                    }
                                }
                                LogUtils.v("HintFeedbackRule", "    inputFocusHint: %s", sb3.toString());
                            }
                        }
                    } else {
                        if (obj3 == null) {
                            z = false;
                        } else {
                            z = true;
                        }
                        hint = accessibilityFocusHint2.getHint((AccessibilityNodeInfoCompat) obj3);
                        z2 = z;
                    }
                    if (true != z2) {
                        hashCode = 0;
                    } else {
                        hashCode = ((AccessibilityNodeInfoCompat) obj3).hashCode();
                    }
                    sb.append(String.format("(%s) ", Integer.valueOf(hashCode)));
                    sb.append(String.format(", hint={%s}", hint));
                    sb.append(String.format(", hintType=%s", Integer.valueOf(i11)));
                    LogUtils.v("HintFeedbackRule", "  speakHintText: %s,", sb.toString());
                    DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                    builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of(hint);
                    builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 0;
                    builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider = true;
                    HintEventInterpretation hintEventInterpretation2 = eventInterpretation.mHint;
                    if (hintEventInterpretation2 == null || !hintEventInterpretation2.forceFeedbackEvenIfAudioPlaybackActive) {
                        z3 = false;
                    }
                    builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = Boolean.valueOf(z3);
                    builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = false;
                    builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
                    return builder$ar$class_merging$f18d5ad3_0.build();
                }
                LogUtils.e("HintFeedbackRule", "speakHintFeedback: error: eventOptions null.", new Object[0]);
                return TalkBackFeedbackProvider.EMPTY_FEEDBACK;
            }

            public final /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        });
        hashMap.put(1073741941, new EventTypeViewClickedFeedbackRule$$ExternalSyntheticLambda0(globalVariables, context, 3));
    }
}
