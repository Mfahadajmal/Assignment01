package com.google.android.accessibility.selecttospeak.logging;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat$Api24Impl;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.brailleime.input.MultitouchHandler$$ExternalSyntheticLambda8;
import com.google.android.accessibility.selecttospeak.SelectToSpeakActionsProto$SelectToSpeakActions;
import com.google.android.accessibility.selecttospeak.SelectToSpeakLogProto$SelectToSpeakExtension;
import com.google.android.accessibility.selecttospeak.SelectToSpeakPipelineProto$SelectToSpeakPipeline;
import com.google.android.accessibility.selecttospeak.SelectToSpeakSettingsProto$SelectToSpeakSettings;
import com.google.android.accessibility.selecttospeak.SelectToSpeakWordCountsProto$SelectToSpeakWordCounts;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SEntryPoint;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SSettings;
import com.google.android.accessibility.utils.AnalyticsCommon;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.marvin.talkback.R;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.mlkit.logging.schema.OnDeviceObjectInferenceLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import j$.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.jvm.internal.Intrinsics;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakLogSender {
    public static final SelectToSpeakLogSender INSTANCE = new SelectToSpeakLogSender();
    public static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static final long INTERVAL_BETWEEN_LOGS_MS = TimeUnit.DAYS.toMillis(1);

    private SelectToSpeakLogSender() {
    }

    private static final boolean getBoolean$ar$ds$e50867ec_0(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        return sharedPreferences.getBoolean(resources.getString(i), resources.getBoolean(i2));
    }

    public static final SharedPreferences getSharedPreference(Context context) {
        Context createDeviceProtectedStorageContext = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(context);
        if (createDeviceProtectedStorageContext != null) {
            context = createDeviceProtectedStorageContext;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("SELECT_TO_SPEAK", 0);
        sharedPreferences.getClass();
        return sharedPreferences;
    }

    public static final String getUsageKey$ar$edu(int i) {
        if (i != 0) {
            return String.valueOf(A11yS2SProtoEnums$A11yS2SEntryPoint.toStringGeneratedd7c9859b585c301d(i)).concat("_usage");
        }
        throw null;
    }

    public static final boolean isTalkBackOn(Context context) {
        Object systemService = context.getSystemService("accessibility");
        systemService.getClass();
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = ((AccessibilityManager) systemService).getEnabledAccessibilityServiceList(4);
        if (enabledAccessibilityServiceList == null) {
            return false;
        }
        return Collection.EL.stream(enabledAccessibilityServiceList).anyMatch(new MultitouchHandler$$ExternalSyntheticLambda8(S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$6c47703c_0, 3));
    }

    public static final void sendSelectToSpeakLogs(Context context) {
        Iterator it;
        Iterator it2;
        int i;
        int i2;
        int i3;
        String obj;
        Iterator<Map.Entry<String, ?>> it3;
        boolean z;
        int i4;
        Resources resources;
        Iterator it4;
        Integer valueOf;
        int digit;
        boolean boolean$ar$ds$e50867ec_0;
        Resources resources2;
        ReentrantReadWriteLock reentrantReadWriteLock = lock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            SharedPreferences sharedPreference = getSharedPreference(context);
            SharedPreferences pipelineSharedPreference$ar$ds = SpannableUtils$NonCopyableTextSpan.getPipelineSharedPreference$ar$ds(context);
            Resources resources3 = context.getResources();
            AnalyticsCommon analyticsCommon = new AnalyticsCommon(context) { // from class: com.google.android.accessibility.selecttospeak.logging.SelectToSpeakLogSender$sendSelectToSpeakLogs$analyticsCommon$1
                final /* synthetic */ Context $context;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(context);
                    this.$context = context;
                }

                @Override // com.google.android.accessibility.utils.AnalyticsCommon
                public final /* bridge */ /* synthetic */ void sendLog(Object obj2) {
                    SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension = (SelectToSpeakLogProto$SelectToSpeakExtension) obj2;
                    SelectToSpeakLogSender selectToSpeakLogSender = SelectToSpeakLogSender.INSTANCE;
                    if (selectToSpeakLogProto$SelectToSpeakExtension == null) {
                        return;
                    }
                    Context context2 = this.$context;
                    new SnackbarManager(context2, "SELECT_TO_SPEAK", null).build().newEvent$ar$class_merging(selectToSpeakLogProto$SelectToSpeakExtension, CollectionBasisLogVerifier.newInstance$ar$class_merging$30765897_0(context2, new BaseProtoCollectionBasis() { // from class: logs.proto.accessibility.selecttospeak.SelectToSpeakCollectionBasisHelper$SelectToSpeakExtension
                        @Override // com.google.android.libraries.consentverifier.BaseProtoCollectionBasis
                        public final void singleCollectionBasis$ar$ds() {
                        }
                    })).logAsync();
                    LogUtils.v("SelectToSpeakLogSender", "Report delivered", new Object[0]);
                }
            };
            pipelineSharedPreference$ar$ds.getClass();
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakLogProto$SelectToSpeakExtension.DEFAULT_INSTANCE.createBuilder();
            builder.getClass();
            reentrantReadWriteLock.readLock().lock();
            try {
                String versionName = SpannableUtils$IdentifierSpan.getVersionName(context);
                if (versionName != null) {
                    builder.copyOnWrite();
                    SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension = (SelectToSpeakLogProto$SelectToSpeakExtension) builder.instance;
                    selectToSpeakLogProto$SelectToSpeakExtension.bitField0_ |= 4;
                    selectToSpeakLogProto$SelectToSpeakExtension.accessibilitySuiteVersion_ = versionName;
                }
                boolean z2 = false;
                LogUtils.v("SelectToSpeakLogSender", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(versionName, "version name : "), new Object[0]);
                boolean z3 = sharedPreference.getBoolean(context.getResources().getString(R.string.s2s_pref_analytics_is_talkback_on), false);
                builder.copyOnWrite();
                SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension2 = (SelectToSpeakLogProto$SelectToSpeakExtension) builder.instance;
                selectToSpeakLogProto$SelectToSpeakExtension2.bitField0_ |= 8;
                selectToSpeakLogProto$SelectToSpeakExtension2.talkbackEnabledDuringLogCycle_ = z3;
                boolean isTalkBackOn = isTalkBackOn(context);
                builder.copyOnWrite();
                SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension3 = (SelectToSpeakLogProto$SelectToSpeakExtension) builder.instance;
                selectToSpeakLogProto$SelectToSpeakExtension3.bitField0_ |= 16;
                selectToSpeakLogProto$SelectToSpeakExtension3.talkbackEnabledWhenLogSent_ = isTalkBackOn;
                Resources resources4 = context.getResources();
                resources4.getClass();
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakSettingsProto$SelectToSpeakSettings.DEFAULT_INSTANCE.createBuilder();
                builder2.getClass();
                int[] values$ar$edu$573f0f23_0 = A11yS2SProtoEnums$A11yS2SSettings.values$ar$edu$573f0f23_0();
                int length = values$ar$edu$573f0f23_0.length;
                int i5 = 0;
                while (i5 < length) {
                    int i6 = values$ar$edu$573f0f23_0[i5];
                    int i7 = i6 - 1;
                    if (i6 != 0) {
                        if (i7 != 1) {
                            if (i7 != 2) {
                                if (i7 != 3) {
                                    resources2 = resources4;
                                    i5++;
                                    resources4 = resources2;
                                    z2 = false;
                                } else {
                                    boolean$ar$ds$e50867ec_0 = sharedPreference.getBoolean("ENABLE_IMPROVED_ACCURACY_SETTING", z2);
                                }
                            } else {
                                boolean$ar$ds$e50867ec_0 = getBoolean$ar$ds$e50867ec_0(sharedPreference, resources4, R.string.s2s_pref_ocr_key, R.bool.s2s_pref_ocr_default);
                            }
                        } else {
                            boolean$ar$ds$e50867ec_0 = getBoolean$ar$ds$e50867ec_0(sharedPreference, resources4, R.string.s2s_pref_multitasking_key, R.bool.s2s_pref_multitasking_default);
                        }
                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord.DEFAULT_INSTANCE.createBuilder();
                        builder3.getClass();
                        builder3.copyOnWrite();
                        SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord settingsRecord = (SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord) builder3.instance;
                        resources2 = resources4;
                        settingsRecord.bitField0_ |= 2;
                        settingsRecord.enabled_ = boolean$ar$ds$e50867ec_0;
                        builder3.copyOnWrite();
                        SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord settingsRecord2 = (SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord) builder3.instance;
                        if (i6 != 0) {
                            settingsRecord2.settings_ = i7;
                            settingsRecord2.bitField0_ |= 1;
                            builder2.copyOnWrite();
                            SelectToSpeakSettingsProto$SelectToSpeakSettings selectToSpeakSettingsProto$SelectToSpeakSettings = (SelectToSpeakSettingsProto$SelectToSpeakSettings) builder2.instance;
                            SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord settingsRecord3 = (SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord) builder3.build();
                            settingsRecord3.getClass();
                            Internal.ProtobufList protobufList = selectToSpeakSettingsProto$SelectToSpeakSettings.settingsRecord_;
                            if (!protobufList.isModifiable()) {
                                selectToSpeakSettingsProto$SelectToSpeakSettings.settingsRecord_ = GeneratedMessageLite.mutableCopy(protobufList);
                            }
                            selectToSpeakSettingsProto$SelectToSpeakSettings.settingsRecord_.add(settingsRecord3);
                            i5++;
                            resources4 = resources2;
                            z2 = false;
                        } else {
                            throw null;
                        }
                    } else {
                        throw null;
                    }
                }
                builder.copyOnWrite();
                SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension4 = (SelectToSpeakLogProto$SelectToSpeakExtension) builder.instance;
                SelectToSpeakSettingsProto$SelectToSpeakSettings selectToSpeakSettingsProto$SelectToSpeakSettings2 = (SelectToSpeakSettingsProto$SelectToSpeakSettings) builder2.build();
                selectToSpeakSettingsProto$SelectToSpeakSettings2.getClass();
                selectToSpeakLogProto$SelectToSpeakExtension4.a11Ys2SSettings_ = selectToSpeakSettingsProto$SelectToSpeakSettings2;
                selectToSpeakLogProto$SelectToSpeakExtension4.bitField0_ |= 1;
                SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakActionsProto$SelectToSpeakActions.DEFAULT_INSTANCE.createBuilder();
                builder4.getClass();
                int i8 = 0;
                for (int i9 : A11yS2SProtoEnums$A11yS2SActions.values$ar$edu$8e43f36c_0()) {
                    String stringGenerated96103c6a9278eb61 = A11yS2SProtoEnums$A11yS2SActions.toStringGenerated96103c6a9278eb61(i9);
                    if (i9 != 0) {
                        int i10 = sharedPreference.getInt(stringGenerated96103c6a9278eb61, 0);
                        if (i9 != A11yS2SProtoEnums$A11yS2SActions.UNSPECIFIED_ACTION$ar$edu && i9 != A11yS2SProtoEnums$A11yS2SActions.ANY_ACTION$ar$edu && i10 != 0) {
                            SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord.DEFAULT_INSTANCE.createBuilder();
                            builder5.getClass();
                            builder5.copyOnWrite();
                            SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord actionsRecord = (SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord) builder5.instance;
                            actionsRecord.bitField0_ |= 2;
                            actionsRecord.count_ = i10;
                            builder5.copyOnWrite();
                            SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord actionsRecord2 = (SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord) builder5.instance;
                            int i11 = i9 - 1;
                            if (i9 != 0) {
                                actionsRecord2.actions_ = i11;
                                actionsRecord2.bitField0_ |= 1;
                                builder4.addActionsRecord$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder5);
                                i8++;
                            } else {
                                throw null;
                            }
                        }
                    } else {
                        throw null;
                    }
                }
                if (i8 > 0) {
                    SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord.DEFAULT_INSTANCE.createBuilder();
                    builder6.getClass();
                    builder6.copyOnWrite();
                    SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord actionsRecord3 = (SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord) builder6.instance;
                    actionsRecord3.bitField0_ |= 2;
                    actionsRecord3.count_ = i8;
                    int i12 = A11yS2SProtoEnums$A11yS2SActions.ANY_ACTION$ar$edu;
                    builder6.copyOnWrite();
                    SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord actionsRecord4 = (SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord) builder6.instance;
                    int i13 = i12 - 1;
                    if (i12 != 0) {
                        actionsRecord4.actions_ = i13;
                        actionsRecord4.bitField0_ |= 1;
                        builder4.addActionsRecord$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder6);
                    } else {
                        throw null;
                    }
                }
                builder.copyOnWrite();
                SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension5 = (SelectToSpeakLogProto$SelectToSpeakExtension) builder.instance;
                SelectToSpeakActionsProto$SelectToSpeakActions selectToSpeakActionsProto$SelectToSpeakActions = (SelectToSpeakActionsProto$SelectToSpeakActions) builder4.build();
                selectToSpeakActionsProto$SelectToSpeakActions.getClass();
                selectToSpeakLogProto$SelectToSpeakExtension5.a11Ys2SActions_ = selectToSpeakActionsProto$SelectToSpeakActions;
                selectToSpeakLogProto$SelectToSpeakExtension5.bitField0_ |= 2;
                SystemHealthProto$PackedHistogram.Builder builder7 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.DEFAULT_INSTANCE.createBuilder();
                builder7.getClass();
                for (int i14 : A11yS2SProtoEnums$A11yS2SEntryPoint.values$ar$edu$48623e11_0()) {
                    if (i14 != A11yS2SProtoEnums$A11yS2SEntryPoint.ENTRY_UNSPECIFIED$ar$edu) {
                        String stringGeneratedd7c9859b585c301d = A11yS2SProtoEnums$A11yS2SEntryPoint.toStringGeneratedd7c9859b585c301d(i14);
                        if (i14 != 0) {
                            int i15 = sharedPreference.getInt(stringGeneratedd7c9859b585c301d, 0);
                            int i16 = sharedPreference.getInt(getUsageKey$ar$edu(i14), 0);
                            if (i16 != 0) {
                                SystemHealthProto$PackedHistogram.Builder builder8 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord.DEFAULT_INSTANCE.createBuilder();
                                builder8.copyOnWrite();
                                SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord wordCountsRecord = (SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord) builder8.instance;
                                int i17 = i14 - 1;
                                if (i14 != 0) {
                                    wordCountsRecord.entry_ = i17;
                                    wordCountsRecord.bitField0_ |= 1;
                                    builder8.copyOnWrite();
                                    SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord wordCountsRecord2 = (SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord) builder8.instance;
                                    wordCountsRecord2.bitField0_ |= 2;
                                    wordCountsRecord2.wordCount_ = i15;
                                    builder8.copyOnWrite();
                                    SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord wordCountsRecord3 = (SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord) builder8.instance;
                                    wordCountsRecord3.bitField0_ |= 4;
                                    wordCountsRecord3.usageCount_ = i16;
                                    builder7.copyOnWrite();
                                    SelectToSpeakWordCountsProto$SelectToSpeakWordCounts selectToSpeakWordCountsProto$SelectToSpeakWordCounts = (SelectToSpeakWordCountsProto$SelectToSpeakWordCounts) builder7.instance;
                                    SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord wordCountsRecord4 = (SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord) builder8.build();
                                    wordCountsRecord4.getClass();
                                    Internal.ProtobufList protobufList2 = selectToSpeakWordCountsProto$SelectToSpeakWordCounts.wordCountsRecord_;
                                    if (!protobufList2.isModifiable()) {
                                        selectToSpeakWordCountsProto$SelectToSpeakWordCounts.wordCountsRecord_ = GeneratedMessageLite.mutableCopy(protobufList2);
                                    }
                                    selectToSpeakWordCountsProto$SelectToSpeakWordCounts.wordCountsRecord_.add(wordCountsRecord4);
                                } else {
                                    throw null;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            throw null;
                        }
                    }
                }
                Integer num = null;
                builder.copyOnWrite();
                SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension6 = (SelectToSpeakLogProto$SelectToSpeakExtension) builder.instance;
                SelectToSpeakWordCountsProto$SelectToSpeakWordCounts selectToSpeakWordCountsProto$SelectToSpeakWordCounts2 = (SelectToSpeakWordCountsProto$SelectToSpeakWordCounts) builder7.build();
                selectToSpeakWordCountsProto$SelectToSpeakWordCounts2.getClass();
                selectToSpeakLogProto$SelectToSpeakExtension6.a11Ys2SWordCounts_ = selectToSpeakWordCountsProto$SelectToSpeakWordCounts2;
                selectToSpeakLogProto$SelectToSpeakExtension6.bitField0_ |= 32;
                SystemHealthProto$PackedHistogram.Builder builder9 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakPipelineProto$SelectToSpeakPipeline.DEFAULT_INSTANCE.createBuilder();
                builder9.getClass();
                SystemHealthProto$PackedHistogram.Builder builder10 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.DEFAULT_INSTANCE.createBuilder();
                builder10.getClass();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Map<String, ?> all = pipelineSharedPreference$ar$ds.getAll();
                all.getClass();
                Iterator<Map.Entry<String, ?>> it5 = all.entrySet().iterator();
                while (it5.hasNext()) {
                    Map.Entry<String, ?> next = it5.next();
                    Object value = next.getValue();
                    if (value != null && (obj = value.toString()) != null) {
                        List split$default$ar$ds = OnDeviceStainRemovalLogEvent.split$default$ar$ds(obj, new String[]{","});
                        ArrayList arrayList = new ArrayList();
                        Iterator it6 = split$default$ar$ds.iterator();
                        while (it6.hasNext()) {
                            String str = (String) it6.next();
                            str.getClass();
                            OnDeviceSmartReplyLogEvent.checkRadix$ar$ds(10);
                            int length2 = str.length();
                            if (length2 == 0) {
                                resources = resources3;
                                valueOf = num;
                                it3 = it5;
                                it4 = it6;
                            } else {
                                it3 = it5;
                                char charAt = str.charAt(0);
                                int i18 = -2147483647;
                                if (Intrinsics.compare(charAt, 48) < 0) {
                                    if (length2 != 1) {
                                        if (charAt == '-') {
                                            i18 = Integer.MIN_VALUE;
                                            z = true;
                                        } else if (charAt == '+') {
                                            z = false;
                                        }
                                        i4 = 1;
                                    }
                                    resources = resources3;
                                    it4 = it6;
                                    valueOf = null;
                                    break;
                                }
                                z = false;
                                i4 = 0;
                                resources = resources3;
                                int i19 = i4;
                                int i20 = -59652323;
                                it4 = it6;
                                int i21 = 0;
                                while (i19 < length2) {
                                    digit = Character.digit((int) str.charAt(i19), 10);
                                    if (digit >= 0) {
                                        String str2 = str;
                                        if (i21 < i20) {
                                            if (i20 != -59652323) {
                                                valueOf = null;
                                                break;
                                            } else {
                                                i20 = -214748364;
                                                if (i21 < -214748364) {
                                                }
                                            }
                                        }
                                        int i22 = i21 * 10;
                                        if (i22 >= i18 + digit) {
                                            i21 = i22 - digit;
                                            i19++;
                                            str = str2;
                                        }
                                    }
                                    valueOf = null;
                                    break;
                                }
                                if (z) {
                                    valueOf = Integer.valueOf(i21);
                                } else {
                                    valueOf = Integer.valueOf(-i21);
                                }
                            }
                            if (valueOf != null) {
                                arrayList.add(valueOf);
                            }
                            it5 = it3;
                            it6 = it4;
                            resources3 = resources;
                            num = null;
                        }
                        Resources resources5 = resources3;
                        String key = next.getKey();
                        key.getClass();
                        linkedHashMap.put(key, arrayList);
                        it5 = it5;
                        resources3 = resources5;
                        num = null;
                    }
                }
                Resources resources6 = resources3;
                if (!linkedHashMap.isEmpty()) {
                    Iterator it7 = linkedHashMap.entrySet().iterator();
                    while (it7.hasNext()) {
                        Map.Entry entry = (Map.Entry) it7.next();
                        SystemHealthProto$PackedHistogram.Builder builder11 = (SystemHealthProto$PackedHistogram.Builder) SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket.DEFAULT_INSTANCE.createBuilder();
                        builder11.getClass();
                        List list = (List) entry.getValue();
                        TimeBucketHelper timeBucketHelper = TimeBucketHelper.INSTANCE;
                        list.getClass();
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                        Iterator it8 = list.iterator();
                        while (it8.hasNext()) {
                            int intValue = ((Number) it8.next()).intValue();
                            if (intValue <= 0) {
                                it = it7;
                                it2 = it8;
                                i2 = -1;
                            } else {
                                List list2 = TimeBucketHelper.timeBucketMs;
                                Integer valueOf2 = Integer.valueOf(intValue);
                                int size = list2.size();
                                list2.getClass();
                                int size2 = list2.size();
                                it = it7;
                                if (size >= 0) {
                                    if (size <= size2) {
                                        int i23 = size - 1;
                                        int i24 = 0;
                                        while (true) {
                                            if (i24 <= i23) {
                                                i = (i24 + i23) >>> 1;
                                                it2 = it8;
                                                int compareValues = OnDeviceObjectInferenceLogEvent.compareValues((Comparable) list2.get(i), valueOf2);
                                                if (compareValues < 0) {
                                                    i24 = i + 1;
                                                } else if (compareValues > 0) {
                                                    i23 = i - 1;
                                                }
                                                it8 = it2;
                                            } else {
                                                it2 = it8;
                                                i = -(i24 + 1);
                                            }
                                        }
                                        i2 = i >= 0 ? i + 1 : (-i) - 1;
                                    } else {
                                        throw new IndexOutOfBoundsException("toIndex (" + size + ") is greater than size (" + size2 + ").");
                                    }
                                } else {
                                    throw new IllegalArgumentException("fromIndex (0) is greater than toIndex (" + size + ").");
                                }
                            }
                            Integer valueOf3 = Integer.valueOf(i2);
                            Integer num2 = (Integer) linkedHashMap2.get(valueOf3);
                            if (num2 != null) {
                                i3 = num2.intValue() + 1;
                            } else {
                                i3 = 1;
                            }
                            linkedHashMap2.put(valueOf3, Integer.valueOf(i3));
                            it7 = it;
                            it8 = it2;
                        }
                        Iterator it9 = it7;
                        for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                            switch (((Number) entry2.getKey()).intValue()) {
                                case 1:
                                    int intValue2 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket.bitField0_ |= 1;
                                    timeBucket.bucket1MsTo500Ms_ = intValue2;
                                    break;
                                case 2:
                                    int intValue3 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket2 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket2.bitField0_ |= 2;
                                    timeBucket2.bucket500MsTo1S_ = intValue3;
                                    break;
                                case 3:
                                    int intValue4 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket3 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket3.bitField0_ |= 4;
                                    timeBucket3.bucket1STo2S_ = intValue4;
                                    break;
                                case 4:
                                    int intValue5 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket4 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket4.bitField0_ |= 8;
                                    timeBucket4.bucket2STo5S_ = intValue5;
                                    break;
                                case 5:
                                    int intValue6 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket5 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket5.bitField0_ |= 16;
                                    timeBucket5.bucket5STo10S_ = intValue6;
                                    break;
                                case 6:
                                    int intValue7 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket6 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket6.bitField0_ |= 32;
                                    timeBucket6.bucket10STo20S_ = intValue7;
                                    break;
                                case 7:
                                    int intValue8 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket7 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket7.bitField0_ |= 64;
                                    timeBucket7.bucket20STo40S_ = intValue8;
                                    break;
                                case 8:
                                    int intValue9 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket8 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket8.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
                                    timeBucket8.bucket40SPlus_ = intValue9;
                                    break;
                                default:
                                    int intValue10 = ((Number) entry2.getValue()).intValue();
                                    builder11.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket9 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.instance;
                                    timeBucket9.bitField0_ |= 256;
                                    timeBucket9.unknown_ = intValue10;
                                    break;
                            }
                        }
                        String str3 = (String) entry.getKey();
                        switch (str3.hashCode()) {
                            case -1281456389:
                                if (str3.equals("KEY_S2S_UI_READY_TO_STOP_MS")) {
                                    builder10.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord pipelineRecord = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord) builder10.instance;
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket10 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.build();
                                    timeBucket10.getClass();
                                    pipelineRecord.uiReadyToStop_ = timeBucket10;
                                    pipelineRecord.bitField0_ |= 4;
                                    break;
                                } else {
                                    break;
                                }
                            case 109305481:
                                if (str3.equals("KEY_S2S_UI_READY_TO_PLAY_MS")) {
                                    builder10.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord pipelineRecord2 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord) builder10.instance;
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket11 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.build();
                                    timeBucket11.getClass();
                                    pipelineRecord2.uiReadyToPlay_ = timeBucket11;
                                    pipelineRecord2.bitField0_ |= 2;
                                    break;
                                } else {
                                    break;
                                }
                            case 841371966:
                                if (str3.equals("KEY_S2S_UI_READY_TO_SELECTION_START_MS")) {
                                    builder10.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord pipelineRecord3 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord) builder10.instance;
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket12 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.build();
                                    timeBucket12.getClass();
                                    pipelineRecord3.uiReadyToSelection_ = timeBucket12;
                                    pipelineRecord3.bitField0_ |= 1;
                                    break;
                                } else {
                                    break;
                                }
                            case 1480911059:
                                if (str3.equals("KEY_S2S_SELECTION_START_TO_END_MS")) {
                                    builder10.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord pipelineRecord4 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord) builder10.instance;
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket13 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.build();
                                    timeBucket13.getClass();
                                    pipelineRecord4.selectionStartToEnd_ = timeBucket13;
                                    pipelineRecord4.bitField0_ |= 8;
                                    break;
                                } else {
                                    break;
                                }
                            case 1771741724:
                                if (str3.equals("KEY_INTERACTION_TO_JOB_START_MS")) {
                                    builder10.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord pipelineRecord5 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord) builder10.instance;
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket14 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.build();
                                    timeBucket14.getClass();
                                    pipelineRecord5.interactionToJobStart_ = timeBucket14;
                                    pipelineRecord5.bitField0_ |= 16;
                                    break;
                                } else {
                                    break;
                                }
                            case 2002747886:
                                if (str3.equals("KEY_INTERACTION_TO_NO_TEXT_FOUND_MS")) {
                                    builder10.copyOnWrite();
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord pipelineRecord6 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord) builder10.instance;
                                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket timeBucket15 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket) builder11.build();
                                    timeBucket15.getClass();
                                    pipelineRecord6.interactionToNoTextFound_ = timeBucket15;
                                    pipelineRecord6.bitField0_ |= 32;
                                    break;
                                } else {
                                    break;
                                }
                        }
                        it7 = it9;
                    }
                    builder9.copyOnWrite();
                    SelectToSpeakPipelineProto$SelectToSpeakPipeline selectToSpeakPipelineProto$SelectToSpeakPipeline = (SelectToSpeakPipelineProto$SelectToSpeakPipeline) builder9.instance;
                    SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord pipelineRecord7 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord) builder10.build();
                    pipelineRecord7.getClass();
                    Internal.ProtobufList protobufList3 = selectToSpeakPipelineProto$SelectToSpeakPipeline.pipelineRecord_;
                    if (!protobufList3.isModifiable()) {
                        selectToSpeakPipelineProto$SelectToSpeakPipeline.pipelineRecord_ = GeneratedMessageLite.mutableCopy(protobufList3);
                    }
                    selectToSpeakPipelineProto$SelectToSpeakPipeline.pipelineRecord_.add(pipelineRecord7);
                }
                builder.copyOnWrite();
                SelectToSpeakLogProto$SelectToSpeakExtension selectToSpeakLogProto$SelectToSpeakExtension7 = (SelectToSpeakLogProto$SelectToSpeakExtension) builder.instance;
                SelectToSpeakPipelineProto$SelectToSpeakPipeline selectToSpeakPipelineProto$SelectToSpeakPipeline2 = (SelectToSpeakPipelineProto$SelectToSpeakPipeline) builder9.build();
                selectToSpeakPipelineProto$SelectToSpeakPipeline2.getClass();
                selectToSpeakLogProto$SelectToSpeakExtension7.a11Ys2SPipeline_ = selectToSpeakPipelineProto$SelectToSpeakPipeline2;
                selectToSpeakLogProto$SelectToSpeakExtension7.bitField0_ |= 64;
                ReentrantReadWriteLock reentrantReadWriteLock2 = lock;
                reentrantReadWriteLock2.readLock().unlock();
                GeneratedMessageLite build = builder.build();
                build.getClass();
                analyticsCommon.doInBackground((SelectToSpeakLogProto$SelectToSpeakExtension) build);
                pipelineSharedPreference$ar$ds.getClass();
                reentrantReadWriteLock2.writeLock().lock();
                try {
                    sharedPreference.edit().clear().commit();
                    pipelineSharedPreference$ar$ds.edit().clear().commit();
                    reentrantReadWriteLock2.writeLock().unlock();
                    resources6.getClass();
                    reentrantReadWriteLock2.writeLock().lock();
                    try {
                        sharedPreference.edit().putLong(resources6.getString(R.string.s2s_pref_last_log_time_ms), System.currentTimeMillis()).commit();
                        reentrantReadWriteLock2.writeLock().unlock();
                        reentrantReadWriteLock2.writeLock().unlock();
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                lock.readLock().unlock();
                throw th;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
