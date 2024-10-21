package com.google.android.accessibility.brailleime.analytics;

import android.content.Context;
import android.util.Base64;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.TouchDots;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.translate.liblouis.LibLouis;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$BrailleImeExtension;
import com.google.android.accessibility.utils.AnalyticsCommon;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import j$.time.Duration;
import j$.util.DesugarCollections;
import j$.util.Optional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import kotlinx.coroutines.scheduling.WorkQueue;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeAnalytics {
    public static final Duration MINIMAL_KEYBOARD_SESSION_DURATION = Duration.ofSeconds(5);
    private static BrailleImeAnalytics instance;
    private final AnalyticsCommon analyticsCommon;
    public final Context context;
    private final boolean enableSendLog = true;
    public final WorkQueue helper$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ClearcutLogger logger;

    private BrailleImeAnalytics(final Context context) {
        this.context = context;
        this.logger = new SnackbarManager(context, "BRAILLEIME", null).build();
        this.helper$ar$class_merging$ar$class_merging$ar$class_merging = new WorkQueue(context, (char[]) null);
        this.analyticsCommon = new AnalyticsCommon(context) { // from class: com.google.android.accessibility.brailleime.analytics.BrailleImeAnalytics.1
            @Override // com.google.android.accessibility.utils.AnalyticsCommon
            public final /* bridge */ /* synthetic */ void sendLog(Object obj) {
                BrailleImeAnalytics brailleImeAnalytics = BrailleImeAnalytics.getInstance(context);
                BaseProtoCollectionBasis baseProtoCollectionBasis = new BaseProtoCollectionBasis() { // from class: logs.proto.wireless.android.aas.brailleime.BrailleImeCollectionBasisHelper$BrailleImeExtension
                    @Override // com.google.android.libraries.consentverifier.BaseProtoCollectionBasis
                    public final void singleCollectionBasis$ar$ds() {
                    }
                };
                brailleImeAnalytics.logger.newEvent$ar$class_merging((BrailleImeLogProto$BrailleImeExtension) obj, CollectionBasisLogVerifier.newInstance$ar$class_merging$30765897_0(brailleImeAnalytics.context, baseProtoCollectionBasis)).logAsync();
            }
        };
        reportCachedLogs();
    }

    public static BrailleImeAnalytics getInstance(Context context) {
        if (instance == null) {
            instance = new BrailleImeAnalytics(context.getApplicationContext());
        }
        return instance;
    }

    private static final int getState$ar$ds$c1fd0491_0$ar$edu(boolean z) {
        if (z) {
            return BrailleImeLogProto$State.STATE_ON$ar$edu;
        }
        return BrailleImeLogProto$State.STATE_OFF$ar$edu;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.content.SharedPreferences, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.SharedPreferences, java.lang.Object] */
    private final void reportCachedLogs() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, ?>> it = this.helper$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$producerIndex.getAll().entrySet().iterator();
        while (it.hasNext()) {
            byte[] decode = Base64.decode(it.next().getValue().toString(), 0);
            try {
                arrayList.add((BrailleImeLogProto$BrailleImeExtension) GeneratedMessageLite.parseFrom(BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE, decode, ExtensionRegistryLite.getGeneratedRegistry()));
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            reportToClearcut((BrailleImeLogProto$BrailleImeExtension) it2.next());
        }
        this.helper$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$producerIndex.edit().clear().apply();
    }

    private final void reportToClearcut(BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension) {
        this.analyticsCommon.doInBackground(brailleImeLogProto$BrailleImeExtension);
    }

    public final void collectSessionEvents() {
        Optional empty;
        Optional empty2;
        Optional empty3;
        if (this.helper$ar$class_merging$ar$class_merging$ar$class_merging.finishCalculateSessionTime() && this.helper$ar$class_merging$ar$class_merging$ar$class_merging.getSessionDuration().compareTo(MINIMAL_KEYBOARD_SESSION_DURATION) >= 0) {
            WorkQueue workQueue = this.helper$ar$class_merging$ar$class_merging$ar$class_merging;
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE.createBuilder();
            int state$ar$ds$c1fd0491_0$ar$edu = getState$ar$ds$c1fd0491_0$ar$edu(BrailleUserPreferences.readAccumulateMode(this.context));
            builder.copyOnWrite();
            BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
            int i = state$ar$ds$c1fd0491_0$ar$edu - 1;
            if (state$ar$ds$c1fd0491_0$ar$edu != 0) {
                brailleImeLogProto$BrailleImeExtension.accumulatedModeV2_ = i;
                brailleImeLogProto$BrailleImeExtension.bitField0_ |= 512;
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.Language.DEFAULT_INSTANCE.createBuilder();
                BrailleLanguages.Code readCurrentActiveInputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(this.context);
                BrailleImeLogProto$LanguageCode[] values = BrailleImeLogProto$LanguageCode.values();
                int length = values.length;
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        BrailleImeLogProto$LanguageCode brailleImeLogProto$LanguageCode = values[i3];
                        if (brailleImeLogProto$LanguageCode.name().equals(readCurrentActiveInputCodeAndCorrect.name())) {
                            empty = Optional.of(brailleImeLogProto$LanguageCode);
                            break;
                        }
                        i3++;
                    } else {
                        empty = Optional.empty();
                        break;
                    }
                }
                if (BrailleUserPreferences.readTranslatorFactory(this.context).getLibraryName().equals(LibLouis.class.getSimpleName())) {
                    empty2 = Optional.of(BrailleImeLogProto$Translator.LIB_LOUIS);
                } else {
                    empty2 = Optional.empty();
                }
                if (empty.isPresent() && empty2.isPresent()) {
                    Object obj = empty.get();
                    builder2.copyOnWrite();
                    BrailleImeLogProto$BrailleImeExtension.Language language = (BrailleImeLogProto$BrailleImeExtension.Language) builder2.instance;
                    language.code_ = ((BrailleImeLogProto$LanguageCode) obj).value;
                    language.bitField0_ |= 1;
                    boolean readContractedMode = BrailleUserPreferences.readContractedMode(this.context);
                    builder2.copyOnWrite();
                    BrailleImeLogProto$BrailleImeExtension.Language language2 = (BrailleImeLogProto$BrailleImeExtension.Language) builder2.instance;
                    language2.bitField0_ |= 2;
                    language2.contracted_ = readContractedMode;
                    Object obj2 = empty2.get();
                    builder2.copyOnWrite();
                    BrailleImeLogProto$BrailleImeExtension.Language language3 = (BrailleImeLogProto$BrailleImeExtension.Language) builder2.instance;
                    language3.translator_ = ((BrailleImeLogProto$Translator) obj2).value;
                    language3.bitField0_ |= 4;
                }
                BrailleImeLogProto$BrailleImeExtension.Language language4 = (BrailleImeLogProto$BrailleImeExtension.Language) builder2.build();
                builder.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension2 = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
                language4.getClass();
                brailleImeLogProto$BrailleImeExtension2.language_ = language4;
                brailleImeLogProto$BrailleImeExtension2.bitField0_ |= 2;
                int i4 = ((BrailleImeAnalyticsHelper$SessionCache) this.helper$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$consumerIndex).totalBrailleCharCount;
                builder.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension3 = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
                brailleImeLogProto$BrailleImeExtension3.bitField0_ |= 4;
                brailleImeLogProto$BrailleImeExtension3.countOfTypingBrailleCharacters_ = i4;
                int seconds = (int) this.helper$ar$class_merging$ar$class_merging$ar$class_merging.getSessionDuration().getSeconds();
                builder.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension4 = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
                brailleImeLogProto$BrailleImeExtension4.bitField0_ |= 16;
                brailleImeLogProto$BrailleImeExtension4.keyboardSessionSeconds_ = seconds;
                HashSet hashSet = new HashSet();
                for (Map.Entry entry : DesugarCollections.unmodifiableMap(((BrailleImeAnalyticsHelper$SessionCache) this.helper$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$consumerIndex).gestureActMap).entrySet()) {
                    SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.GestureAction.DEFAULT_INSTANCE.createBuilder();
                    BrailleImeLogProto$GestureActionType brailleImeLogProto$GestureActionType = (BrailleImeLogProto$GestureActionType) entry.getKey();
                    builder3.copyOnWrite();
                    BrailleImeLogProto$BrailleImeExtension.GestureAction gestureAction = (BrailleImeLogProto$BrailleImeExtension.GestureAction) builder3.instance;
                    gestureAction.type_ = brailleImeLogProto$GestureActionType.value;
                    gestureAction.bitField0_ |= 1;
                    int intValue = ((Integer) entry.getValue()).intValue();
                    builder3.copyOnWrite();
                    BrailleImeLogProto$BrailleImeExtension.GestureAction gestureAction2 = (BrailleImeLogProto$BrailleImeExtension.GestureAction) builder3.instance;
                    gestureAction2.bitField0_ |= 2;
                    gestureAction2.count_ = intValue;
                    hashSet.add((BrailleImeLogProto$BrailleImeExtension.GestureAction) builder3.build());
                }
                builder.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension5 = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
                Internal.ProtobufList protobufList = brailleImeLogProto$BrailleImeExtension5.action_;
                if (!protobufList.isModifiable()) {
                    brailleImeLogProto$BrailleImeExtension5.action_ = GeneratedMessageLite.mutableCopy(protobufList);
                }
                AbstractMessageLite.addAll(hashSet, brailleImeLogProto$BrailleImeExtension5.action_);
                int state$ar$ds$c1fd0491_0$ar$edu2 = getState$ar$ds$c1fd0491_0$ar$edu(BrailleUserPreferences.readReverseDotsMode(this.context));
                builder.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension6 = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
                int i5 = state$ar$ds$c1fd0491_0$ar$edu2 - 1;
                if (state$ar$ds$c1fd0491_0$ar$edu2 != 0) {
                    brailleImeLogProto$BrailleImeExtension6.reverseDotsModeV2_ = i5;
                    brailleImeLogProto$BrailleImeExtension6.bitField0_ |= 1024;
                    TouchDots readLayoutMode = BrailleUserPreferences.readLayoutMode(this.context);
                    BrailleImeLogProto$LayoutCode[] values2 = BrailleImeLogProto$LayoutCode.values();
                    int length2 = values2.length;
                    while (true) {
                        if (i2 < length2) {
                            BrailleImeLogProto$LayoutCode brailleImeLogProto$LayoutCode = values2[i2];
                            if (brailleImeLogProto$LayoutCode.name().equals(readLayoutMode.name())) {
                                empty3 = Optional.of(brailleImeLogProto$LayoutCode);
                                break;
                            }
                            i2++;
                        } else {
                            empty3 = Optional.empty();
                            break;
                        }
                    }
                    Object obj3 = empty3.get();
                    builder.copyOnWrite();
                    BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension7 = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
                    brailleImeLogProto$BrailleImeExtension7.layoutMode_ = ((BrailleImeLogProto$LayoutCode) obj3).value;
                    brailleImeLogProto$BrailleImeExtension7.bitField0_ |= 256;
                    workQueue.addExtensionToSharedPreference((BrailleImeLogProto$BrailleImeExtension) builder.build());
                } else {
                    throw null;
                }
            } else {
                throw null;
            }
        }
        this.helper$ar$class_merging$ar$class_merging$ar$class_merging.resetSessionEvents();
    }

    public final void logGestureAction(BrailleImeLogProto$GestureActionType brailleImeLogProto$GestureActionType) {
        if (brailleImeLogProto$GestureActionType != BrailleImeLogProto$GestureActionType.UNSPECIFIED_ACTION) {
            WorkQueue workQueue = this.helper$ar$class_merging$ar$class_merging$ar$class_merging;
            int i = 1;
            if (((BrailleImeAnalyticsHelper$SessionCache) workQueue.WorkQueue$ar$consumerIndex).gestureActMap.containsKey(brailleImeLogProto$GestureActionType)) {
                i = 1 + ((Integer) ((BrailleImeAnalyticsHelper$SessionCache) workQueue.WorkQueue$ar$consumerIndex).gestureActMap.get(brailleImeLogProto$GestureActionType)).intValue();
            }
            Object obj = workQueue.WorkQueue$ar$consumerIndex;
            ((BrailleImeAnalyticsHelper$SessionCache) obj).gestureActMap.put(brailleImeLogProto$GestureActionType, Integer.valueOf(i));
        }
    }

    public final void logTalkBackOffDialogDisplay() {
        sendErrorLog$ar$edu(BrailleImeLogProto$ErrorType.TALK_BACK_OFF$ar$edu);
    }

    /* JADX WARN: Type inference failed for: r3v10, types: [java.util.List, java.lang.Object] */
    public final void sendAllLogs() {
        collectSessionEvents();
        if (!this.helper$ar$class_merging$ar$class_merging$ar$class_merging.getSettingsEventMap().isEmpty()) {
            WorkQueue workQueue = this.helper$ar$class_merging$ar$class_merging$ar$class_merging;
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE.createBuilder();
            HashSet hashSet = new HashSet();
            for (Map.Entry entry : this.helper$ar$class_merging$ar$class_merging$ar$class_merging.getSettingsEventMap().entrySet()) {
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.SettingsRecord.DEFAULT_INSTANCE.createBuilder();
                BrailleImeLogProto$SettingsEvent brailleImeLogProto$SettingsEvent = (BrailleImeLogProto$SettingsEvent) entry.getKey();
                builder2.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension.SettingsRecord settingsRecord = (BrailleImeLogProto$BrailleImeExtension.SettingsRecord) builder2.instance;
                settingsRecord.settings_ = brailleImeLogProto$SettingsEvent.value;
                settingsRecord.bitField0_ |= 1;
                int intValue = ((Integer) entry.getValue()).intValue();
                builder2.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension.SettingsRecord settingsRecord2 = (BrailleImeLogProto$BrailleImeExtension.SettingsRecord) builder2.instance;
                settingsRecord2.bitField0_ |= 2;
                settingsRecord2.count_ = intValue;
                hashSet.add((BrailleImeLogProto$BrailleImeExtension.SettingsRecord) builder2.build());
            }
            builder.copyOnWrite();
            BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
            Internal.ProtobufList protobufList = brailleImeLogProto$BrailleImeExtension.settingsRecord_;
            if (!protobufList.isModifiable()) {
                brailleImeLogProto$BrailleImeExtension.settingsRecord_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            AbstractMessageLite.addAll(hashSet, brailleImeLogProto$BrailleImeExtension.settingsRecord_);
            workQueue.addExtensionToSharedPreference((BrailleImeLogProto$BrailleImeExtension) builder.build());
        }
        this.helper$ar$class_merging$ar$class_merging$ar$class_merging.resetSettingsEvents();
        if (!this.helper$ar$class_merging$ar$class_merging$ar$class_merging.getSettingsEventMap().isEmpty()) {
            WorkQueue workQueue2 = this.helper$ar$class_merging$ar$class_merging$ar$class_merging;
            SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE.createBuilder();
            HashSet hashSet2 = new HashSet();
            for (BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord : DesugarCollections.unmodifiableList(((ApplicationModule) this.helper$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$blockingTasksInBuffer).ApplicationModule$ar$application)) {
                SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$CalibrationRecord.DEFAULT_INSTANCE.createBuilder();
                int forNumber$ar$edu$1d442578_0 = BrailleImeLogProto$CalibrationType.forNumber$ar$edu$1d442578_0(brailleImeLogProto$CalibrationRecord.calibrationType_);
                if (forNumber$ar$edu$1d442578_0 == 0) {
                    forNumber$ar$edu$1d442578_0 = BrailleImeLogProto$CalibrationType.CALIBRATION_TYPE_INVALID$ar$edu;
                }
                builder4.copyOnWrite();
                BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord2 = (BrailleImeLogProto$CalibrationRecord) builder4.instance;
                if (forNumber$ar$edu$1d442578_0 != 0) {
                    brailleImeLogProto$CalibrationRecord2.calibrationType_ = forNumber$ar$edu$1d442578_0 - 1;
                    brailleImeLogProto$CalibrationRecord2.bitField0_ |= 4;
                    BrailleImeLogProto$LayoutCode forNumber = BrailleImeLogProto$LayoutCode.forNumber(brailleImeLogProto$CalibrationRecord.layout_);
                    if (forNumber == null) {
                        forNumber = BrailleImeLogProto$LayoutCode.AUTO_DETECT;
                    }
                    builder4.copyOnWrite();
                    BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord3 = (BrailleImeLogProto$CalibrationRecord) builder4.instance;
                    brailleImeLogProto$CalibrationRecord3.layout_ = forNumber.value;
                    brailleImeLogProto$CalibrationRecord3.bitField0_ |= 1;
                    int forNumber$ar$edu$425b3e52_0 = BrailleImeLogProto$CalibrationState.forNumber$ar$edu$425b3e52_0(brailleImeLogProto$CalibrationRecord.calibrationState_);
                    if (forNumber$ar$edu$425b3e52_0 == 0) {
                        forNumber$ar$edu$425b3e52_0 = BrailleImeLogProto$CalibrationState.CALIBRATION_STATE_INVALID$ar$edu;
                    }
                    builder4.copyOnWrite();
                    BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord4 = (BrailleImeLogProto$CalibrationRecord) builder4.instance;
                    int i = forNumber$ar$edu$425b3e52_0 - 1;
                    if (forNumber$ar$edu$425b3e52_0 != 0) {
                        brailleImeLogProto$CalibrationRecord4.calibrationState_ = i;
                        brailleImeLogProto$CalibrationRecord4.bitField0_ |= 8;
                        int forNumber$ar$edu$a7370849_0 = BrailleImeLogProto$BrailleType.forNumber$ar$edu$a7370849_0(brailleImeLogProto$CalibrationRecord.brailleType_);
                        if (forNumber$ar$edu$a7370849_0 == 0) {
                            forNumber$ar$edu$a7370849_0 = BrailleImeLogProto$BrailleType.BRAILLE_TYPE_INVALID$ar$edu;
                        }
                        builder4.copyOnWrite();
                        BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord5 = (BrailleImeLogProto$CalibrationRecord) builder4.instance;
                        int i2 = forNumber$ar$edu$a7370849_0 - 1;
                        if (forNumber$ar$edu$a7370849_0 != 0) {
                            brailleImeLogProto$CalibrationRecord5.brailleType_ = i2;
                            brailleImeLogProto$CalibrationRecord5.bitField0_ |= 2;
                            hashSet2.add((BrailleImeLogProto$CalibrationRecord) builder4.build());
                        } else {
                            throw null;
                        }
                    } else {
                        throw null;
                    }
                } else {
                    throw null;
                }
            }
            builder3.copyOnWrite();
            BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension2 = (BrailleImeLogProto$BrailleImeExtension) builder3.instance;
            Internal.ProtobufList protobufList2 = brailleImeLogProto$BrailleImeExtension2.calibrationRecord_;
            if (!protobufList2.isModifiable()) {
                brailleImeLogProto$BrailleImeExtension2.calibrationRecord_ = GeneratedMessageLite.mutableCopy(protobufList2);
            }
            AbstractMessageLite.addAll(hashSet2, brailleImeLogProto$BrailleImeExtension2.calibrationRecord_);
            workQueue2.addExtensionToSharedPreference((BrailleImeLogProto$BrailleImeExtension) builder3.build());
        }
        this.helper$ar$class_merging$ar$class_merging$ar$class_merging.resetCalibrationRecords();
        if (!this.helper$ar$class_merging$ar$class_merging$ar$class_merging.getContextMenuOptionMap().isEmpty()) {
            WorkQueue workQueue3 = this.helper$ar$class_merging$ar$class_merging$ar$class_merging;
            SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE.createBuilder();
            HashSet hashSet3 = new HashSet();
            for (Map.Entry entry2 : this.helper$ar$class_merging$ar$class_merging$ar$class_merging.getContextMenuOptionMap().entrySet()) {
                SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.ContextMenuRecord.DEFAULT_INSTANCE.createBuilder();
                BrailleImeLogProto$ContextMenuOption brailleImeLogProto$ContextMenuOption = (BrailleImeLogProto$ContextMenuOption) entry2.getKey();
                builder6.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension.ContextMenuRecord contextMenuRecord = (BrailleImeLogProto$BrailleImeExtension.ContextMenuRecord) builder6.instance;
                contextMenuRecord.selectOption_ = brailleImeLogProto$ContextMenuOption.value;
                contextMenuRecord.bitField0_ |= 1;
                int intValue2 = ((Integer) entry2.getValue()).intValue();
                builder6.copyOnWrite();
                BrailleImeLogProto$BrailleImeExtension.ContextMenuRecord contextMenuRecord2 = (BrailleImeLogProto$BrailleImeExtension.ContextMenuRecord) builder6.instance;
                contextMenuRecord2.bitField0_ |= 2;
                contextMenuRecord2.count_ = intValue2;
                hashSet3.add((BrailleImeLogProto$BrailleImeExtension.ContextMenuRecord) builder6.build());
            }
            builder5.copyOnWrite();
            BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension3 = (BrailleImeLogProto$BrailleImeExtension) builder5.instance;
            Internal.ProtobufList protobufList3 = brailleImeLogProto$BrailleImeExtension3.contextMenuRecord_;
            if (!protobufList3.isModifiable()) {
                brailleImeLogProto$BrailleImeExtension3.contextMenuRecord_ = GeneratedMessageLite.mutableCopy(protobufList3);
            }
            AbstractMessageLite.addAll(hashSet3, brailleImeLogProto$BrailleImeExtension3.contextMenuRecord_);
            workQueue3.addExtensionToSharedPreference((BrailleImeLogProto$BrailleImeExtension) builder5.build());
        }
        this.helper$ar$class_merging$ar$class_merging$ar$class_merging.resetContextMenuRecords();
        reportCachedLogs();
    }

    public final void sendErrorLog$ar$edu(int i) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension = (BrailleImeLogProto$BrailleImeExtension) builder.instance;
        if (i != 0) {
            brailleImeLogProto$BrailleImeExtension.errorType_ = i - 1;
            brailleImeLogProto$BrailleImeExtension.bitField0_ |= 64;
            reportToClearcut((BrailleImeLogProto$BrailleImeExtension) builder.build());
            return;
        }
        throw null;
    }

    public final void sendTutorialFinishLog$ar$edu(int i) {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent tutorialFinishEvent = (BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent) builder.instance;
        if (i != 0) {
            tutorialFinishEvent.finishState_ = i - 1;
            tutorialFinishEvent.bitField0_ |= 1;
            boolean shouldLaunchTutorial = BrailleUserPreferences.shouldLaunchTutorial(this.context);
            builder.copyOnWrite();
            BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent tutorialFinishEvent2 = (BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent) builder.instance;
            tutorialFinishEvent2.bitField0_ |= 2;
            tutorialFinishEvent2.autoLaunchTutorial_ = shouldLaunchTutorial;
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE.createBuilder();
            BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent tutorialFinishEvent3 = (BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent) builder.build();
            builder2.copyOnWrite();
            BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension = (BrailleImeLogProto$BrailleImeExtension) builder2.instance;
            tutorialFinishEvent3.getClass();
            brailleImeLogProto$BrailleImeExtension.tutorialFinish_ = tutorialFinishEvent3;
            brailleImeLogProto$BrailleImeExtension.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
            reportToClearcut((BrailleImeLogProto$BrailleImeExtension) builder2.build());
            return;
        }
        throw null;
    }
}
