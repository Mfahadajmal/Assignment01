package com.google.android.accessibility.talkback.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.analytics.ShortcutActionsProto$GestureShortcutActions;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsDBHelper;
import com.google.android.accessibility.talkback.analytics.TalkBackGeminiProto$TalkBackGemini;
import com.google.android.accessibility.talkback.analytics.TalkBackGeminiProto$TalkBackOnDeviceGemini;
import com.google.android.accessibility.talkback.analytics.TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog;
import com.google.android.accessibility.talkback.analytics.TalkBackSelectorProto$TalkBackSelector;
import com.google.android.accessibility.talkback.analytics.TalkBackVoiceCommandProto$TalkBackVoiceCommand;
import com.google.android.accessibility.talkback.labeling.CustomLabelManager;
import com.google.android.accessibility.talkback.labeling.LabelsFetchRequest;
import com.google.android.accessibility.talkback.logging.TalkbackExtensionUtils;
import com.google.android.accessibility.utils.AnalyticsCommon;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.marvin.talkback.R;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.common.collect.ImmutableList;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$ApplicationInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackAnalyticsLoggerWithClearcut {
    public int activationCount;
    public ActorState actorState;
    public final AnalyticsCommon analyticsCommon;
    public final MetricRecorderFactory clearcutHelper$ar$class_merging;
    public volatile TalkBackAnalyticsDBHelper dbHelper;
    public final int hardwareVariant$ar$edu;
    public long lastLogTimeBackup;
    public final ClearcutLogger logger;
    public final SharedPreferences prefs;
    public final Context service;
    public OrderVerifyingClientCall.State pendingSettingChangeAction$ar$class_merging$ar$class_merging$ar$class_merging = null;
    public final LabelsFetchRequest.OnLabelsFetchedListener finalizeLogListener = new CustomLabelManager.AnonymousClass7(this, 1);
    public String ttsInUse = "";
    public final long serviceUpTime = SystemClock.elapsedRealtime();
    public long lastLogTime = Calendar.getInstance().getTimeInMillis();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionTask extends AsyncTask {
        private final Runnable runnable;

        public ActionTask(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override // android.os.AsyncTask
        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            this.runnable.run();
            return null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SendLogsToClearcutAndClearCacheTask extends AsyncTask {
        private final TalkBackAnalyticsLoggerWithClearcut parent;

        public SendLogsToClearcutAndClearCacheTask(TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut) {
            this.parent = talkBackAnalyticsLoggerWithClearcut;
        }

        @Override // android.os.AsyncTask
        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            this.parent.sendAllLogsAndClearCacheInternal(((Boolean[]) objArr)[0].booleanValue());
            return null;
        }
    }

    public TalkBackAnalyticsLoggerWithClearcut(Context context) {
        int i;
        this.service = context;
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.clearcutHelper$ar$class_merging = new MetricRecorderFactory(context);
        this.dbHelper = new TalkBackAnalyticsDBHelper(context);
        this.logger = new SnackbarManager(context, "TALKBACK", null).build();
        this.analyticsCommon = new AnalyticsCommon(context) { // from class: com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut.2
            @Override // com.google.android.accessibility.utils.AnalyticsCommon
            public final /* bridge */ /* synthetic */ void sendLog(Object obj) {
                TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = TalkBackAnalyticsLoggerWithClearcut.this;
                ClearcutLogger clearcutLogger = talkBackAnalyticsLoggerWithClearcut.logger;
                TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension = (TalkBackLogProto$TalkBackExtension) obj;
                if (clearcutLogger != null) {
                    clearcutLogger.newEvent$ar$class_merging(talkBackLogProto$TalkBackExtension, CollectionBasisLogVerifier.newInstance$ar$class_merging$30765897_0(talkBackAnalyticsLoggerWithClearcut.service, new BaseProtoCollectionBasis() { // from class: logs.proto.accessibility.talkback.TalkBackCollectionBasisHelper$TalkBackExtension
                        @Override // com.google.android.libraries.consentverifier.BaseProtoCollectionBasis
                        public final void singleCollectionBasis$ar$ds() {
                        }
                    })).logAsync();
                }
            }
        };
        FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
        if (formFactorUtils.isAndroidAuto) {
            i = SystemHealthProto$ApplicationInfo.HardwareVariant.AUTOMOTIVE$ar$edu;
        } else if (formFactorUtils.isAndroidTv) {
            i = SystemHealthProto$ApplicationInfo.HardwareVariant.LEANBACK$ar$edu;
        } else if (formFactorUtils.isAndroidWear) {
            i = SystemHealthProto$ApplicationInfo.HardwareVariant.WATCH$ar$edu;
        } else {
            i = SystemHealthProto$ApplicationInfo.HardwareVariant.PHONE_OR_TABLET$ar$edu;
        }
        this.hardwareVariant$ar$edu = i;
    }

    private static final void createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SystemHealthProto$PackedHistogram.Builder builder, int i, int i2) {
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount.DEFAULT_INSTANCE.createBuilder();
        builder2.copyOnWrite();
        TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount geminiFailReasonCount = (TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount) builder2.instance;
        if (i != 0) {
            geminiFailReasonCount.geminiFailReason_ = i - 1;
            geminiFailReasonCount.bitField0_ |= 1;
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount geminiFailReasonCount2 = (TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount) builder2.instance;
            geminiFailReasonCount2.bitField0_ |= 2;
            geminiFailReasonCount2.count_ = i2;
            builder.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount geminiFailCount = (TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount) builder.instance;
            TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount geminiFailReasonCount3 = (TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount) builder2.build();
            TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount geminiFailCount2 = TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.DEFAULT_INSTANCE;
            geminiFailReasonCount3.getClass();
            Internal.ProtobufList protobufList = geminiFailCount.geminiFailReasonCount_;
            if (!protobufList.isModifiable()) {
                geminiFailCount.geminiFailReasonCount_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            geminiFailCount.geminiFailReasonCount_.add(geminiFailReasonCount3);
            return;
        }
        throw null;
    }

    private static final void createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SystemHealthProto$PackedHistogram.Builder builder, int i, int i2) {
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount.DEFAULT_INSTANCE.createBuilder();
        builder2.copyOnWrite();
        TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount geminiFailReasonCount = (TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount) builder2.instance;
        if (i != 0) {
            geminiFailReasonCount.geminiFailReason_ = i - 1;
            geminiFailReasonCount.bitField0_ |= 1;
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount geminiFailReasonCount2 = (TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount) builder2.instance;
            geminiFailReasonCount2.bitField0_ |= 2;
            geminiFailReasonCount2.count_ = i2;
            builder.copyOnWrite();
            TalkBackGeminiProto$TalkBackGemini.GeminiFailCount geminiFailCount = (TalkBackGeminiProto$TalkBackGemini.GeminiFailCount) builder.instance;
            TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount geminiFailReasonCount3 = (TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount) builder2.build();
            TalkBackGeminiProto$TalkBackGemini.GeminiFailCount geminiFailCount2 = TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.DEFAULT_INSTANCE;
            geminiFailReasonCount3.getClass();
            Internal.ProtobufList protobufList = geminiFailCount.geminiFailReasonCount_;
            if (!protobufList.isModifiable()) {
                geminiFailCount.geminiFailReasonCount_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            geminiFailCount.geminiFailReasonCount_.add(geminiFailReasonCount3);
            return;
        }
        throw null;
    }

    private final int getAndIncLoggingAttempt() {
        int i;
        int i2 = this.prefs.getInt(this.service.getString(R.string.pref_key_last_log_attempt_key), -1);
        if (i2 < 0) {
            i = 0;
        } else {
            i = i2 + 1;
        }
        this.prefs.edit().putInt(this.service.getString(R.string.pref_key_last_log_attempt_key), i).apply();
        return i;
    }

    private final TalkBackLogProto$TalkBackExtension populateEventAndClearCache(boolean z) {
        SystemHealthProto$PackedHistogram.Builder builder;
        SystemHealthProto$PackedHistogram.Builder builder2;
        SystemHealthProto$PackedHistogram.Builder builder3;
        SystemHealthProto$PackedHistogram.Builder builder4;
        boolean z2;
        SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) TalkBackLogProto$TalkBackExtension.DEFAULT_INSTANCE.createBuilder();
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$7400$ar$edu((TalkBackLogProto$TalkBackExtension) builder5.instance, this.hardwareVariant$ar$edu);
        String versionName = SpannableUtils$IdentifierSpan.getVersionName(this.service);
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$100((TalkBackLogProto$TalkBackExtension) builder5.instance, versionName);
        int hours = (int) TimeUnit.MILLISECONDS.toHours(SystemClock.elapsedRealtime() - this.serviceUpTime);
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$4400((TalkBackLogProto$TalkBackExtension) builder5.instance, hours);
        int hours2 = (int) TimeUnit.MILLISECONDS.toHours(Calendar.getInstance().getTimeInMillis() - this.lastLogTimeBackup);
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$4600((TalkBackLogProto$TalkBackExtension) builder5.instance, hours2);
        int i = this.activationCount;
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$4800((TalkBackLogProto$TalkBackExtension) builder5.instance, i);
        String str = this.ttsInUse;
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$6600((TalkBackLogProto$TalkBackExtension) builder5.instance, str);
        int ttsEnginePackageNameToEnum$ar$edu = TalkbackExtensionUtils.ttsEnginePackageNameToEnum$ar$edu(this.ttsInUse);
        int i2 = ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.ENGINE_UNDEFINED$ar$edu;
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$6900((TalkBackLogProto$TalkBackExtension) builder5.instance, ttsEnginePackageNameToEnum$ar$edu != i2);
        ActorState actorState = this.actorState;
        boolean z3 = actorState != null && actorState.getGeminiState$ar$class_merging$ar$class_merging$ar$class_merging().hasAiCore();
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$8100((TalkBackLogProto$TalkBackExtension) builder5.instance, z3);
        populateGeminiEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder5);
        populateGeminiAicoreEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder5);
        SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.DEFAULT_INSTANCE.createBuilder();
        ImmutableList of = ImmutableList.of((Object) this.service.getResources().getString(R.string.pref_select_keymap_key), (Object) this.service.getResources().getString(R.string.pref_default_keymap_trigger_modifier_key));
        Set<TalkBackAnalyticsDBHelper.GranularityMovementEntry> granularityMovements = this.dbHelper.getGranularityMovements();
        if (!granularityMovements.isEmpty()) {
            SystemHealthProto$PackedHistogram.Builder builder7 = (SystemHealthProto$PackedHistogram.Builder) GranularityMovementsProto$GranularityMovements.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.GranularityMovementEntry granularityMovementEntry : granularityMovements) {
                builder7.addGranularityMovementEntities$ar$ds(MetricRecorderFactory.createGranularityMovementEntity(granularityMovementEntry.granularity, granularityMovementEntry.count));
            }
            builder5.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$2600((TalkBackLogProto$TalkBackExtension) builder5.instance, (GranularityMovementsProto$GranularityMovements) builder7.build());
        }
        Set<TalkBackAnalyticsDBHelper.SettingChangeEntry> settingChanges = this.dbHelper.getSettingChanges();
        if (!settingChanges.isEmpty()) {
            SystemHealthProto$PackedHistogram.Builder builder8 = (SystemHealthProto$PackedHistogram.Builder) TalkBackSettingChangesProto$TalkBackSettingChanges.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.SettingChangeEntry settingChangeEntry : settingChanges) {
                if (of.contains(settingChangeEntry.prefKey)) {
                    if (settingChangeEntry.prefKey.equals(this.service.getResources().getString(R.string.pref_select_keymap_key))) {
                        for (int i3 = 0; i3 < settingChangeEntry.count; i3++) {
                            builder6.addKeymapTypeChanged$ar$ds(this.clearcutHelper$ar$class_merging.prefValueToKeymapType(settingChangeEntry.prefValue));
                        }
                    } else {
                        for (int i4 = 0; i4 < settingChangeEntry.count; i4++) {
                            builder6.addModifierKeyChanged$ar$ds(this.clearcutHelper$ar$class_merging.prefValueToModifierKey(settingChangeEntry.prefValue));
                        }
                    }
                }
                if (this.clearcutHelper$ar$class_merging.isExtensionKey(settingChangeEntry.prefKey)) {
                    builder8.addSettingExtChangeEntities$ar$ds(this.clearcutHelper$ar$class_merging.createSettingExtChangeEntity(settingChangeEntry.prefKey, settingChangeEntry.prefValue, settingChangeEntry.userActionType, settingChangeEntry.count));
                } else {
                    builder8.addSettingChangeEntities$ar$ds(this.clearcutHelper$ar$class_merging.createSettingChangeEntity(settingChangeEntry.prefKey, settingChangeEntry.prefValue, settingChangeEntry.userActionType, settingChangeEntry.count));
                }
            }
            builder5.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$700((TalkBackLogProto$TalkBackExtension) builder5.instance, (TalkBackSettingChangesProto$TalkBackSettingChanges) builder8.build());
        }
        SystemHealthProto$PackedHistogram.Builder builder9 = (SystemHealthProto$PackedHistogram.Builder) TalkbackContextMenuActionsProto$TalkbackContextMenuActions.DEFAULT_INSTANCE.createBuilder();
        Set<TalkBackAnalyticsDBHelper.GlobalContextMenuEntry> globalContextMenu = this.dbHelper.getGlobalContextMenu();
        if (!globalContextMenu.isEmpty()) {
            for (TalkBackAnalyticsDBHelper.GlobalContextMenuEntry globalContextMenuEntry : globalContextMenu) {
                builder9.addContextMenuActionEntities$ar$ds(MetricRecorderFactory.createContextMenuEntity$ar$ds(globalContextMenuEntry.menuAction(), globalContextMenuEntry.count()));
            }
        }
        Set<TalkBackAnalyticsDBHelper.LocalContextMenuEntry> localContextMenu = this.dbHelper.getLocalContextMenu();
        if (!localContextMenu.isEmpty()) {
            for (TalkBackAnalyticsDBHelper.LocalContextMenuEntry localContextMenuEntry : localContextMenu) {
                builder9.addContextMenuActionEntities$ar$ds(MetricRecorderFactory.createContextMenuEntity$ar$ds(localContextMenuEntry.menuAction(), localContextMenuEntry.count()));
            }
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (TalkBackAnalyticsDBHelper.ContextMenuOpenedEntry contextMenuOpenedEntry : this.dbHelper.getContextMenuOpened()) {
            int menuType = contextMenuOpenedEntry.menuType();
            if (menuType != 0) {
                if (menuType == 1) {
                    if (contextMenuOpenedEntry.menuStyle() == 0) {
                        i7 = contextMenuOpenedEntry.count();
                    } else {
                        i8 = contextMenuOpenedEntry.count();
                    }
                }
            } else if (contextMenuOpenedEntry.menuStyle() == 0) {
                i5 = contextMenuOpenedEntry.count();
            } else {
                i6 = contextMenuOpenedEntry.count();
            }
        }
        builder9.copyOnWrite();
        TalkbackContextMenuActionsProto$TalkbackContextMenuActions.access$1300((TalkbackContextMenuActionsProto$TalkbackContextMenuActions) builder9.instance, i5);
        builder9.copyOnWrite();
        TalkbackContextMenuActionsProto$TalkbackContextMenuActions.access$1700((TalkbackContextMenuActionsProto$TalkbackContextMenuActions) builder9.instance, i6);
        builder9.copyOnWrite();
        TalkbackContextMenuActionsProto$TalkbackContextMenuActions.access$1500((TalkbackContextMenuActionsProto$TalkbackContextMenuActions) builder9.instance, i7);
        builder9.copyOnWrite();
        TalkbackContextMenuActionsProto$TalkbackContextMenuActions.access$1900((TalkbackContextMenuActionsProto$TalkbackContextMenuActions) builder9.instance, i8);
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$1300((TalkBackLogProto$TalkBackExtension) builder5.instance, (TalkbackContextMenuActionsProto$TalkbackContextMenuActions) builder9.build());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        this.clearcutHelper$ar$class_merging.createSettingSnapshot$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder5, z, atomicInteger);
        int i9 = atomicInteger.get();
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$1800((TalkBackLogProto$TalkBackExtension) builder5.instance, i9);
        int andIncLoggingAttempt = getAndIncLoggingAttempt();
        builder5.copyOnWrite();
        TalkBackLogProto$TalkBackExtension.access$1600((TalkBackLogProto$TalkBackExtension) builder5.instance, andIncLoggingAttempt);
        Set<TalkBackAnalyticsDBHelper.GestureUsedEntry> gestureUsed = this.dbHelper.getGestureUsed();
        if (!gestureUsed.isEmpty()) {
            SystemHealthProto$PackedHistogram.Builder builder10 = (SystemHealthProto$PackedHistogram.Builder) TalkBackGesturesUsedProto$TalkBackGesturesUsed.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.GestureUsedEntry gestureUsedEntry : gestureUsed) {
                builder10.addTalkbackGestureUsed$ar$ds(MetricRecorderFactory.createTalkBackGesturesUsedEntry$ar$ds(gestureUsedEntry.gestureId, gestureUsedEntry.count));
            }
            builder5.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$2000((TalkBackLogProto$TalkBackExtension) builder5.instance, (TalkBackGesturesUsedProto$TalkBackGesturesUsed) builder10.build());
        }
        Set<TalkBackAnalyticsDBHelper.VoiceCommandEvent> voiceCommandEvent = this.dbHelper.getVoiceCommandEvent();
        if (!voiceCommandEvent.isEmpty()) {
            SystemHealthProto$PackedHistogram.Builder builder11 = (SystemHealthProto$PackedHistogram.Builder) TalkBackVoiceCommandProto$TalkBackVoiceCommand.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.VoiceCommandEvent voiceCommandEvent2 : voiceCommandEvent) {
                int event = voiceCommandEvent2.event();
                if (event == 1) {
                    int count = voiceCommandEvent2.count();
                    builder11.copyOnWrite();
                    TalkBackVoiceCommandProto$TalkBackVoiceCommand.access$700((TalkBackVoiceCommandProto$TalkBackVoiceCommand) builder11.instance, count);
                } else if (event == 2) {
                    int count2 = voiceCommandEvent2.count();
                    builder11.copyOnWrite();
                    TalkBackVoiceCommandProto$TalkBackVoiceCommand.access$900((TalkBackVoiceCommandProto$TalkBackVoiceCommand) builder11.instance, count2);
                } else if (event == 3) {
                    int count3 = voiceCommandEvent2.count();
                    builder11.copyOnWrite();
                    TalkBackVoiceCommandProto$TalkBackVoiceCommand.access$1300((TalkBackVoiceCommandProto$TalkBackVoiceCommand) builder11.instance, count3);
                } else if (event == 4) {
                    int count4 = voiceCommandEvent2.count();
                    builder11.copyOnWrite();
                    TalkBackVoiceCommandProto$TalkBackVoiceCommand.access$1100((TalkBackVoiceCommandProto$TalkBackVoiceCommand) builder11.instance, count4);
                } else if (event == 5) {
                    int count5 = voiceCommandEvent2.count();
                    builder11.copyOnWrite();
                    TalkBackVoiceCommandProto$TalkBackVoiceCommand.access$1500((TalkBackVoiceCommandProto$TalkBackVoiceCommand) builder11.instance, count5);
                }
            }
            Set<TalkBackAnalyticsDBHelper.MiscellaneousEntry> miscellaneousEntry = this.dbHelper.getMiscellaneousEntry();
            if (!miscellaneousEntry.isEmpty()) {
                for (TalkBackAnalyticsDBHelper.MiscellaneousEntry miscellaneousEntry2 : miscellaneousEntry) {
                    if (miscellaneousEntry2.item() >= 101 && miscellaneousEntry2.item() <= 139) {
                        SystemHealthProto$PackedHistogram.Builder builder12 = (SystemHealthProto$PackedHistogram.Builder) TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics.DEFAULT_INSTANCE.createBuilder();
                        int forNumber$ar$edu$9ff16495_0 = VoiceCommandEnums$VoiceCommandType.forNumber$ar$edu$9ff16495_0(miscellaneousEntry2.item() - 100);
                        builder12.copyOnWrite();
                        TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics.access$100$ar$edu$9ca0d2fd_0((TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics) builder12.instance, forNumber$ar$edu$9ff16495_0);
                        int count6 = miscellaneousEntry2.count();
                        builder12.copyOnWrite();
                        TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics.access$300((TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics) builder12.instance, count6);
                        builder11.addVoiceCommandMetrics$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder12);
                    }
                }
            }
            builder5.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$2300((TalkBackLogProto$TalkBackExtension) builder5.instance, (TalkBackVoiceCommandProto$TalkBackVoiceCommand) builder11.build());
        }
        Set<TalkBackAnalyticsDBHelper.SelectorEvent> selectorEvent = this.dbHelper.getSelectorEvent();
        if (selectorEvent.isEmpty()) {
            builder = null;
        } else {
            builder = (SystemHealthProto$PackedHistogram.Builder) TalkBackSelectorProto$TalkBackSelector.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.SelectorEvent selectorEvent2 : selectorEvent) {
                if (selectorEvent2.isDestination()) {
                    int count7 = selectorEvent2.count();
                    builder.copyOnWrite();
                    TalkBackSelectorProto$TalkBackSelector.access$1500((TalkBackSelectorProto$TalkBackSelector) builder.instance, count7);
                } else {
                    int count8 = selectorEvent2.count();
                    builder.copyOnWrite();
                    TalkBackSelectorProto$TalkBackSelector.access$1300((TalkBackSelectorProto$TalkBackSelector) builder.instance, count8);
                }
            }
        }
        Set<TalkBackAnalyticsDBHelper.SelectorActionEvent> selectorActionEvent = this.dbHelper.getSelectorActionEvent();
        if (!selectorActionEvent.isEmpty()) {
            if (builder == null) {
                builder = (SystemHealthProto$PackedHistogram.Builder) TalkBackSelectorProto$TalkBackSelector.DEFAULT_INSTANCE.createBuilder();
            }
            for (TalkBackAnalyticsDBHelper.SelectorActionEvent selectorActionEvent2 : selectorActionEvent) {
                SystemHealthProto$PackedHistogram.Builder builder13 = (SystemHealthProto$PackedHistogram.Builder) TalkBackSelectorProto$TalkBackSelector.SelectorEntity.DEFAULT_INSTANCE.createBuilder();
                int selectedItem = selectorActionEvent2.selectedItem();
                switch (selectedItem) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        break;
                    default:
                        switch (selectedItem) {
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                                break;
                            default:
                                selectedItem = 0;
                                break;
                        }
                }
                int forNumber$ar$edu$23eba87_0 = SelectorItemEnums$SelectorItem.forNumber$ar$edu$23eba87_0(selectedItem);
                builder13.copyOnWrite();
                TalkBackSelectorProto$TalkBackSelector.SelectorEntity.access$100$ar$edu$3ea9e5e3_0((TalkBackSelectorProto$TalkBackSelector.SelectorEntity) builder13.instance, forNumber$ar$edu$23eba87_0);
                int count9 = selectorActionEvent2.count();
                builder13.copyOnWrite();
                TalkBackSelectorProto$TalkBackSelector.SelectorEntity.access$300((TalkBackSelectorProto$TalkBackSelector.SelectorEntity) builder13.instance, count9);
                builder.addSelectorEntities$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder13);
            }
        }
        if (builder != null) {
            builder5.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$2900((TalkBackLogProto$TalkBackExtension) builder5.instance, (TalkBackSelectorProto$TalkBackSelector) builder.build());
        }
        Set<TalkBackAnalyticsDBHelper.ImageCaptionEvent> imageCaptionEvent = this.dbHelper.getImageCaptionEvent();
        if (imageCaptionEvent.isEmpty()) {
            builder2 = null;
        } else {
            builder2 = (SystemHealthProto$PackedHistogram.Builder) ImageCaptionerProto$ImageCaptioner.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.ImageCaptionEvent imageCaptionEvent2 : imageCaptionEvent) {
                int count10 = imageCaptionEvent2.count();
                int event2 = imageCaptionEvent2.event();
                if (event2 == 35) {
                    builder2.copyOnWrite();
                    ImageCaptionerProto$ImageCaptioner.access$5500((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                } else if (event2 != 36) {
                    switch (event2) {
                        case 1:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$100((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 2:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 3:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$500((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 4:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$700((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 5:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$900((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 6:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$1100((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 7:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$1300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 8:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$1500((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 9:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$1700((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 10:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$1900((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 11:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$2100((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 12:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$2300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 13:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$2500((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 14:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$2700((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 15:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$2900((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 16:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$3300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 17:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$3700((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 18:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$3900((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 19:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$4100((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 20:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$4300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 21:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$4500((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 22:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$4700((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 23:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$4900((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 24:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$5100((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 25:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$5300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 26:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$5900((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 27:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$6300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 28:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$6700((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 29:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$6900((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 30:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$7100((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                        case 31:
                            builder2.copyOnWrite();
                            ImageCaptionerProto$ImageCaptioner.access$7300((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                            break;
                    }
                } else {
                    builder2.copyOnWrite();
                    ImageCaptionerProto$ImageCaptioner.access$5700((ImageCaptionerProto$ImageCaptioner) builder2.instance, count10);
                }
            }
        }
        int i10 = this.prefs.getInt(this.service.getString(R.string.pref_icon_detection_install_lib_success), 0);
        int i11 = this.prefs.getInt(this.service.getString(R.string.pref_icon_detection_install_lib_fail), 0);
        int i12 = this.prefs.getInt(this.service.getString(R.string.pref_icon_detection_install_lib_request), 0);
        int i13 = this.prefs.getInt(this.service.getString(R.string.pref_icon_detection_install_lib_deny), 0);
        int i14 = this.prefs.getInt(this.service.getString(R.string.pref_icon_detection_uninstalled_request), 0);
        int i15 = this.prefs.getInt(this.service.getString(R.string.pref_icon_detection_uninstalled_deny), 0);
        int i16 = this.prefs.getInt(this.service.getString(R.string.pref_image_description_install_lib_success), 0);
        int i17 = this.prefs.getInt(this.service.getString(R.string.pref_image_description_install_lib_fail), 0);
        int i18 = this.prefs.getInt(this.service.getString(R.string.pref_image_description_install_lib_request), 0);
        int i19 = this.prefs.getInt(this.service.getString(R.string.pref_image_description_install_lib_deny), 0);
        int i20 = this.prefs.getInt(this.service.getString(R.string.pref_image_description_uninstalled_request), 0);
        int i21 = this.prefs.getInt(this.service.getString(R.string.pref_image_description_uninstalled_deny), 0);
        if (i10 + i11 + i12 + i13 + i14 + i15 + i16 + i17 + i18 + i19 + i20 + i21 != 0) {
            if (builder2 == null) {
                builder2 = (SystemHealthProto$PackedHistogram.Builder) ImageCaptionerProto$ImageCaptioner.DEFAULT_INSTANCE.createBuilder();
            }
            if (i10 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner.bitField0_ & 262144) != 0) {
                    i10 += imageCaptionerProto$ImageCaptioner.iconDetectLibInstallSuccess_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$3700((ImageCaptionerProto$ImageCaptioner) builder2.instance, i10);
            }
            if (i11 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner2 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner2.bitField0_ & 524288) != 0) {
                    i11 += imageCaptionerProto$ImageCaptioner2.iconDetectLibInstallFail_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$3900((ImageCaptionerProto$ImageCaptioner) builder2.instance, i11);
            }
            if (i12 != 0) {
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$3100((ImageCaptionerProto$ImageCaptioner) builder2.instance, i12);
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner3 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner3.bitField0_ & 16384) != 0) {
                    i12 += imageCaptionerProto$ImageCaptioner3.iconDetectLibInstallRequest_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$2900((ImageCaptionerProto$ImageCaptioner) builder2.instance, i12);
            }
            if (i13 != 0) {
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$3500((ImageCaptionerProto$ImageCaptioner) builder2.instance, i13);
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner4 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner4.bitField0_ & 65536) != 0) {
                    i13 += imageCaptionerProto$ImageCaptioner4.iconDetectLibInstallDeny_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$3300((ImageCaptionerProto$ImageCaptioner) builder2.instance, i13);
            }
            if (i14 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner5 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner5.bitField0_ & 1048576) != 0) {
                    i14 += imageCaptionerProto$ImageCaptioner5.iconDetectLibUninstallRequest_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$4100((ImageCaptionerProto$ImageCaptioner) builder2.instance, i14);
            }
            if (i15 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner6 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner6.bitField0_ & 2097152) != 0) {
                    i15 += imageCaptionerProto$ImageCaptioner6.iconDetectLibUninstallDeny_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$4300((ImageCaptionerProto$ImageCaptioner) builder2.instance, i15);
            }
            if (i16 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner7 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner7.bitField1_ & 2) != 0) {
                    i16 += imageCaptionerProto$ImageCaptioner7.imageDescribeLibInstallSuccess_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$6700((ImageCaptionerProto$ImageCaptioner) builder2.instance, i16);
            }
            if (i17 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner8 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner8.bitField1_ & 4) != 0) {
                    i17 += imageCaptionerProto$ImageCaptioner8.imageDescribeLibInstallFail_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$6900((ImageCaptionerProto$ImageCaptioner) builder2.instance, i17);
            }
            if (i18 != 0) {
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$6100((ImageCaptionerProto$ImageCaptioner) builder2.instance, i18);
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner9 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner9.bitField0_ & 536870912) != 0) {
                    i18 += imageCaptionerProto$ImageCaptioner9.imageDescribeLibInstallRequest_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$5900((ImageCaptionerProto$ImageCaptioner) builder2.instance, i18);
            }
            if (i19 != 0) {
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$6500((ImageCaptionerProto$ImageCaptioner) builder2.instance, i19);
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner10 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner10.bitField0_ & Integer.MIN_VALUE) != 0) {
                    i19 += imageCaptionerProto$ImageCaptioner10.imageDescribeLibInstallDeny_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$6300((ImageCaptionerProto$ImageCaptioner) builder2.instance, i19);
            }
            if (i20 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner11 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner11.bitField1_ & 8) != 0) {
                    i20 += imageCaptionerProto$ImageCaptioner11.imageDescribeLibUninstallRequest_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$7100((ImageCaptionerProto$ImageCaptioner) builder2.instance, i20);
            }
            if (i21 != 0) {
                ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner12 = (ImageCaptionerProto$ImageCaptioner) builder2.instance;
                if ((imageCaptionerProto$ImageCaptioner12.bitField1_ & 16) != 0) {
                    i21 += imageCaptionerProto$ImageCaptioner12.imageDescribeLibUninstallDeny_;
                }
                builder2.copyOnWrite();
                ImageCaptionerProto$ImageCaptioner.access$7300((ImageCaptionerProto$ImageCaptioner) builder2.instance, i21);
            }
        }
        if (builder2 != null) {
            builder5.copyOnWrite();
            builder3 = builder5;
            TalkBackLogProto$TalkBackExtension.access$3200((TalkBackLogProto$TalkBackExtension) builder3.instance, (ImageCaptionerProto$ImageCaptioner) builder2.build());
        } else {
            builder3 = builder5;
        }
        Set<TalkBackAnalyticsDBHelper.MagnificationEntry> magnificationEntry = this.dbHelper.getMagnificationEntry();
        if (!magnificationEntry.isEmpty()) {
            SystemHealthProto$PackedHistogram.Builder builder14 = (SystemHealthProto$PackedHistogram.Builder) MagnificationUsedProto$MagnificationUsed.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.MagnificationEntry magnificationEntry2 : magnificationEntry) {
                int count11 = magnificationEntry2.count();
                int magnificationUsed = magnificationEntry2.magnificationUsed();
                if (magnificationUsed == 1) {
                    builder14.copyOnWrite();
                    MagnificationUsedProto$MagnificationUsed.access$100((MagnificationUsedProto$MagnificationUsed) builder14.instance, count11);
                } else if (magnificationUsed == 2) {
                    builder14.copyOnWrite();
                    MagnificationUsedProto$MagnificationUsed.access$300((MagnificationUsedProto$MagnificationUsed) builder14.instance, count11);
                }
            }
            builder3.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$3500((TalkBackLogProto$TalkBackExtension) builder3.instance, (MagnificationUsedProto$MagnificationUsed) builder14.build());
        }
        Set<TalkBackAnalyticsDBHelper.KeyboardModifierKeyUsedEntry> keyboardModifierKeyUsedEntry = this.dbHelper.getKeyboardModifierKeyUsedEntry();
        if (!keyboardModifierKeyUsedEntry.isEmpty()) {
            for (TalkBackAnalyticsDBHelper.KeyboardModifierKeyUsedEntry keyboardModifierKeyUsedEntry2 : keyboardModifierKeyUsedEntry) {
                TalkBackSettingEnums$ModifierKey forNumber = TalkBackSettingEnums$ModifierKey.forNumber(keyboardModifierKeyUsedEntry2.modifierKey());
                for (int i22 = 0; i22 < keyboardModifierKeyUsedEntry2.count(); i22++) {
                    builder6.addModifierKeyUsed$ar$ds(forNumber);
                }
            }
        }
        Set<TalkBackAnalyticsDBHelper.KeyboardKeymapUsedEntry> keyboardKeymapUsedEntry = this.dbHelper.getKeyboardKeymapUsedEntry();
        if (!keyboardKeymapUsedEntry.isEmpty()) {
            for (TalkBackAnalyticsDBHelper.KeyboardKeymapUsedEntry keyboardKeymapUsedEntry2 : keyboardKeymapUsedEntry) {
                TalkBackSettingEnums$KeymapType forNumber2 = TalkBackSettingEnums$KeymapType.forNumber(keyboardKeymapUsedEntry2.keymapType());
                for (int i23 = 0; i23 < keyboardKeymapUsedEntry2.count(); i23++) {
                    builder6.addKeymapTypeUsed$ar$ds(forNumber2);
                }
            }
        }
        Set<TalkBackAnalyticsDBHelper.KeyboardShortcutUsedEntry> keyboardShortcutUsedEntry = this.dbHelper.getKeyboardShortcutUsedEntry();
        if (!keyboardShortcutUsedEntry.isEmpty()) {
            for (TalkBackAnalyticsDBHelper.KeyboardShortcutUsedEntry keyboardShortcutUsedEntry2 : keyboardShortcutUsedEntry) {
                TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity createKeyboardShortcutEntities = this.clearcutHelper$ar$class_merging.createKeyboardShortcutEntities(keyboardShortcutUsedEntry2.actionId(), keyboardShortcutUsedEntry2.triggerModifier(), keyboardShortcutUsedEntry2.keyComboCode());
                for (int i24 = 0; i24 < keyboardShortcutUsedEntry2.count(); i24++) {
                    builder6.addKeyboardShortcutUsedEntities$ar$ds(createKeyboardShortcutEntities);
                }
            }
        }
        Set<TalkBackAnalyticsDBHelper.KeyboardShortcutChangedEntry> keyboardShortcutChangedEntry = this.dbHelper.getKeyboardShortcutChangedEntry();
        if (!keyboardShortcutChangedEntry.isEmpty()) {
            for (TalkBackAnalyticsDBHelper.KeyboardShortcutChangedEntry keyboardShortcutChangedEntry2 : keyboardShortcutChangedEntry) {
                TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity createKeyboardShortcutEntities2 = this.clearcutHelper$ar$class_merging.createKeyboardShortcutEntities(keyboardShortcutChangedEntry2.actionId(), keyboardShortcutChangedEntry2.triggerModifier(), keyboardShortcutChangedEntry2.keyComboCode());
                for (int i25 = 0; i25 < keyboardShortcutChangedEntry2.count(); i25++) {
                    builder6.addKeyboardShortcutChangedEntities$ar$ds(createKeyboardShortcutEntities2);
                }
            }
        }
        if (!keyboardModifierKeyUsedEntry.isEmpty() || !keyboardKeymapUsedEntry.isEmpty() || !keyboardShortcutUsedEntry.isEmpty() || !keyboardShortcutChangedEntry.isEmpty() || !new Internal.IntListAdapter(((TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) builder6.instance).keymapTypeChanged_, TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_converter_).isEmpty() || !new Internal.IntListAdapter(((TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) builder6.instance).modifierKeyChanged_, TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.modifierKeyChanged_converter_).isEmpty()) {
            builder3.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$3800((TalkBackLogProto$TalkBackExtension) builder3.instance, (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) builder6.build());
        }
        Set<TalkBackAnalyticsDBHelper.ShortcutActionsEntry> shortcutActionsEntry = this.dbHelper.getShortcutActionsEntry();
        if (!shortcutActionsEntry.isEmpty()) {
            SystemHealthProto$PackedHistogram.Builder builder15 = (SystemHealthProto$PackedHistogram.Builder) ShortcutActionsProto$GestureShortcutActions.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.ShortcutActionsEntry shortcutActionsEntry2 : shortcutActionsEntry) {
                SystemHealthProto$PackedHistogram.Builder builder16 = (SystemHealthProto$PackedHistogram.Builder) ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity.DEFAULT_INSTANCE.createBuilder();
                int forNumber$ar$edu$398a6427_0 = ShortcutActionsEnums$ShortcutActions.forNumber$ar$edu$398a6427_0(shortcutActionsEntry2.shortcutAction());
                builder16.copyOnWrite();
                ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity.access$100$ar$edu((ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity) builder16.instance, forNumber$ar$edu$398a6427_0);
                int count12 = shortcutActionsEntry2.count();
                builder16.copyOnWrite();
                ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity.access$300((ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity) builder16.instance, count12);
                builder15.addGestureShortcutEntity$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder16);
            }
            if (((ShortcutActionsProto$GestureShortcutActions) builder15.instance).gestureShortcutEntity_.size() != 0) {
                builder3.copyOnWrite();
                TalkBackLogProto$TalkBackExtension.access$5000((TalkBackLogProto$TalkBackExtension) builder3.instance, (ShortcutActionsProto$GestureShortcutActions) builder15.build());
            }
        }
        Set<TalkBackAnalyticsDBHelper.TrainingSectionEntry> tutorialSectionEntry = this.dbHelper.getTutorialSectionEntry();
        if (!tutorialSectionEntry.isEmpty()) {
            builder4 = (SystemHealthProto$PackedHistogram.Builder) TrainingUsageProto$TrainingUsage.DEFAULT_INSTANCE.createBuilder();
            for (TalkBackAnalyticsDBHelper.TrainingSectionEntry trainingSectionEntry : tutorialSectionEntry) {
                switch (trainingSectionEntry.trainingSection()) {
                    case 1:
                        int count13 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$100((TrainingUsageProto$TrainingUsage) builder4.instance, count13);
                        break;
                    case 2:
                        int count14 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$300((TrainingUsageProto$TrainingUsage) builder4.instance, count14);
                        break;
                    case 3:
                        int count15 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$500((TrainingUsageProto$TrainingUsage) builder4.instance, count15);
                        break;
                    case 4:
                        int count16 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$700((TrainingUsageProto$TrainingUsage) builder4.instance, count16);
                        break;
                    case 5:
                        int count17 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$900((TrainingUsageProto$TrainingUsage) builder4.instance, count17);
                        break;
                    case 6:
                        int count18 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$1100((TrainingUsageProto$TrainingUsage) builder4.instance, count18);
                        break;
                    case 7:
                        int count19 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$1300((TrainingUsageProto$TrainingUsage) builder4.instance, count19);
                        break;
                    case 8:
                        int count20 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$1500((TrainingUsageProto$TrainingUsage) builder4.instance, count20);
                        break;
                    case 9:
                        int count21 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$1700((TrainingUsageProto$TrainingUsage) builder4.instance, count21);
                        break;
                    case 10:
                        int count22 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$1900((TrainingUsageProto$TrainingUsage) builder4.instance, count22);
                        break;
                    case 11:
                        int count23 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$2100((TrainingUsageProto$TrainingUsage) builder4.instance, count23);
                        break;
                    case 12:
                        int count24 = trainingSectionEntry.count();
                        builder4.copyOnWrite();
                        TrainingUsageProto$TrainingUsage.access$2300((TrainingUsageProto$TrainingUsage) builder4.instance, count24);
                        break;
                }
            }
        } else {
            builder4 = null;
        }
        List trainingEntry = this.dbHelper.getTrainingEntry();
        if (!trainingEntry.isEmpty()) {
            if (builder4 == null) {
                builder4 = (SystemHealthProto$PackedHistogram.Builder) TrainingUsageProto$TrainingUsage.DEFAULT_INSTANCE.createBuilder();
            }
            Iterator it = trainingEntry.iterator();
            while (it.hasNext()) {
                try {
                    builder4.addTrainingMetricWrapper$ar$ds((TrainingUsageProto$TrainingMetricWrapper) GeneratedMessageLite.parseFrom(TrainingUsageProto$TrainingMetricWrapper.DEFAULT_INSTANCE, ((TalkBackAnalyticsDBHelper.TrainingEntry) it.next()).trainingMetricInBytes(), ExtensionRegistryLite.getGeneratedRegistry()));
                } catch (InvalidProtocolBufferException unused) {
                    LogUtils.w("TalkBackAnalyticsLoggerWithClearcut", "Skip invalid tutorial proto.", new Object[0]);
                }
            }
        }
        if (builder4 != null) {
            builder3.copyOnWrite();
            TalkBackLogProto$TalkBackExtension.access$5300((TalkBackLogProto$TalkBackExtension) builder3.instance, (TrainingUsageProto$TrainingUsage) builder4.build());
        }
        Set<TalkBackAnalyticsDBHelper.MiscellaneousEntry> miscellaneousEntry3 = this.dbHelper.getMiscellaneousEntry();
        SystemHealthProto$PackedHistogram.Builder builder17 = (SystemHealthProto$PackedHistogram.Builder) ImageCaptionResultLevelProto$ImageCaptionResultLevel.DEFAULT_INSTANCE.createBuilder();
        if (!miscellaneousEntry3.isEmpty()) {
            boolean z4 = false;
            for (TalkBackAnalyticsDBHelper.MiscellaneousEntry miscellaneousEntry4 : miscellaneousEntry3) {
                int item = miscellaneousEntry4.item();
                if (item != 0) {
                    z2 = true;
                    if (item == 1) {
                        int count25 = miscellaneousEntry4.count();
                        builder17.copyOnWrite();
                        ImageCaptionResultLevelProto$ImageCaptionResultLevel.access$300((ImageCaptionResultLevelProto$ImageCaptionResultLevel) builder17.instance, count25);
                    } else if (item == 2) {
                        int count26 = miscellaneousEntry4.count();
                        builder17.copyOnWrite();
                        ImageCaptionResultLevelProto$ImageCaptionResultLevel.access$500((ImageCaptionResultLevelProto$ImageCaptionResultLevel) builder17.instance, count26);
                    } else if (item == 3) {
                        int count27 = miscellaneousEntry4.count();
                        builder3.copyOnWrite();
                        TalkBackLogProto$TalkBackExtension.access$6200((TalkBackLogProto$TalkBackExtension) builder3.instance, count27);
                    } else if (item == 4) {
                        int count28 = miscellaneousEntry4.count();
                        builder3.copyOnWrite();
                        TalkBackLogProto$TalkBackExtension.access$6400((TalkBackLogProto$TalkBackExtension) builder3.instance, count28);
                    } else if (item == 19) {
                        int count29 = miscellaneousEntry4.count();
                        builder3.copyOnWrite();
                        TalkBackLogProto$TalkBackExtension.access$7900((TalkBackLogProto$TalkBackExtension) builder3.instance, count29);
                    }
                } else {
                    z2 = true;
                    int count30 = miscellaneousEntry4.count();
                    builder17.copyOnWrite();
                    ImageCaptionResultLevelProto$ImageCaptionResultLevel.access$100((ImageCaptionResultLevelProto$ImageCaptionResultLevel) builder17.instance, count30);
                }
                z4 = z2;
            }
            if (z4) {
                builder3.copyOnWrite();
                TalkBackLogProto$TalkBackExtension.access$5600((TalkBackLogProto$TalkBackExtension) builder3.instance, (ImageCaptionResultLevelProto$ImageCaptionResultLevel) builder17.build());
            }
        }
        SharedPreferences sharedPreferences = this.prefs;
        Context context = this.service;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(context.getString(R.string.pref_icon_detection_install_lib_success));
        edit.remove(this.service.getString(R.string.pref_icon_detection_install_lib_fail));
        edit.remove(this.service.getString(R.string.pref_icon_detection_install_lib_request));
        edit.remove(this.service.getString(R.string.pref_icon_detection_install_lib_deny));
        edit.remove(this.service.getString(R.string.pref_icon_detection_uninstalled_request));
        edit.remove(this.service.getString(R.string.pref_icon_detection_uninstalled_deny));
        edit.remove(this.service.getString(R.string.pref_image_description_install_lib_success));
        edit.remove(this.service.getString(R.string.pref_image_description_install_lib_fail));
        edit.remove(this.service.getString(R.string.pref_image_description_install_lib_request));
        edit.remove(this.service.getString(R.string.pref_image_description_install_lib_deny));
        edit.remove(this.service.getString(R.string.pref_image_description_uninstalled_request));
        edit.remove(this.service.getString(R.string.pref_image_description_uninstalled_deny));
        edit.remove(this.service.getString(R.string.pref_key_service_activation_count_key));
        edit.apply();
        this.activationCount = 0;
        this.dbHelper.reset();
        return (TalkBackLogProto$TalkBackExtension) builder3.build();
    }

    private final void populateGeminiAicoreEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SystemHealthProto$PackedHistogram.Builder builder) {
        boolean z;
        boolean z2;
        Set<TalkBackAnalyticsDBHelper.MiscellaneousEntry> miscellaneousEntry = this.dbHelper.getMiscellaneousEntry();
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) TalkBackGeminiProto$TalkBackOnDeviceGemini.DEFAULT_INSTANCE.createBuilder();
        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.DEFAULT_INSTANCE.createBuilder();
        boolean z3 = true;
        if (!miscellaneousEntry.isEmpty()) {
            z = false;
            for (TalkBackAnalyticsDBHelper.MiscellaneousEntry miscellaneousEntry2 : miscellaneousEntry) {
                switch (miscellaneousEntry2.item()) {
                    case 20:
                        int count = miscellaneousEntry2.count();
                        builder2.copyOnWrite();
                        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
                        talkBackGeminiProto$TalkBackOnDeviceGemini.bitField0_ |= 1;
                        talkBackGeminiProto$TalkBackOnDeviceGemini.geminiRequestCount_ = count;
                        break;
                    case 21:
                        int count2 = miscellaneousEntry2.count();
                        builder2.copyOnWrite();
                        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini2 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
                        talkBackGeminiProto$TalkBackOnDeviceGemini2.bitField0_ |= 2;
                        talkBackGeminiProto$TalkBackOnDeviceGemini2.geminiSuccessCount_ = count2;
                        continue;
                    case 22:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_APIKEY_NOT_AVAILABLE$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 23:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_USER_NOT_OPT_IN$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 24:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_INTERNET_UNREACHABLE$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 25:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_NO_SCREENSHOT_PROVIDED$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 26:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_COMMAND_NOT_PROVIDED$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 27:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_FAIL_TO_ENCODE_PICTURE$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 28:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_FAIL_TO_PARSE_RESPONSE$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 29:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_CONTENT_BLOCKED$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 30:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_PROTOCOL_ERROR$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 31:
                        int count3 = miscellaneousEntry2.count();
                        builder2.copyOnWrite();
                        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini3 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
                        talkBackGeminiProto$TalkBackOnDeviceGemini3.bitField0_ |= 32;
                        talkBackGeminiProto$TalkBackOnDeviceGemini3.geminiOptInShownCount_ = count3;
                        break;
                    case 32:
                        int count4 = miscellaneousEntry2.count();
                        builder2.copyOnWrite();
                        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini4 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
                        talkBackGeminiProto$TalkBackOnDeviceGemini4.bitField0_ |= 8;
                        talkBackGeminiProto$TalkBackOnDeviceGemini4.geminiOptInConsentCount_ = count4;
                        continue;
                    case 33:
                        int count5 = miscellaneousEntry2.count();
                        builder2.copyOnWrite();
                        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini5 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
                        talkBackGeminiProto$TalkBackOnDeviceGemini5.bitField0_ |= 16;
                        talkBackGeminiProto$TalkBackOnDeviceGemini5.geminiOptInDissentCount_ = count5;
                        continue;
                    case 34:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_SERVICE_UNAVAILABLE$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 36:
                        createGeminiAicoreFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_USER_ABORT$ar$edu, miscellaneousEntry2.count());
                        continue;
                    case 37:
                        int count6 = miscellaneousEntry2.count();
                        builder2.copyOnWrite();
                        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini6 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
                        talkBackGeminiProto$TalkBackOnDeviceGemini6.bitField0_ |= 64;
                        talkBackGeminiProto$TalkBackOnDeviceGemini6.geminiManualRequestCount_ = count6;
                        continue;
                }
                z = true;
            }
            if (((TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount) builder3.instance).geminiFailReasonCount_.size() != 0) {
                builder2.copyOnWrite();
                TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini7 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
                TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount geminiFailCount = (TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount) builder3.build();
                geminiFailCount.getClass();
                talkBackGeminiProto$TalkBackOnDeviceGemini7.geminiFailCount_ = geminiFailCount;
                talkBackGeminiProto$TalkBackOnDeviceGemini7.bitField0_ |= 4;
            }
        } else {
            z = false;
        }
        if (z) {
            builder.copyOnWrite();
            TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension = (TalkBackLogProto$TalkBackExtension) builder.instance;
            TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension2 = TalkBackLogProto$TalkBackExtension.DEFAULT_INSTANCE;
            talkBackLogProto$TalkBackExtension.bitField0_ |= 1073741824;
            talkBackLogProto$TalkBackExtension.supportOnDeviceGemini_ = true;
        }
        int i = this.prefs.getInt(this.service.getString(R.string.pref_gemini_aicore_opt_in_show_dialog_key), 0);
        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini8 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
        if ((talkBackGeminiProto$TalkBackOnDeviceGemini8.bitField0_ & 32) != 0) {
            i += talkBackGeminiProto$TalkBackOnDeviceGemini8.geminiOptInShownCount_;
        }
        int i2 = this.prefs.getInt(this.service.getString(R.string.pref_gemini_aicore_opt_in_consent_key), 0);
        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini9 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
        if ((talkBackGeminiProto$TalkBackOnDeviceGemini9.bitField0_ & 8) != 0) {
            i2 += talkBackGeminiProto$TalkBackOnDeviceGemini9.geminiOptInConsentCount_;
        }
        int i3 = this.prefs.getInt(this.service.getString(R.string.pref_gemini_aicore_opt_in_dissent_key), 0);
        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini10 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
        int i4 = talkBackGeminiProto$TalkBackOnDeviceGemini10.bitField0_;
        if ((i4 & 16) != 0) {
            i3 += talkBackGeminiProto$TalkBackOnDeviceGemini10.geminiOptInDissentCount_;
        }
        if (!z && (i4 & 1) == 0 && i + i2 + i3 <= 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (i > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini11 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini11.bitField0_ |= 32;
            talkBackGeminiProto$TalkBackOnDeviceGemini11.geminiOptInShownCount_ = i;
        }
        if (i2 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini12 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini12.bitField0_ |= 8;
            talkBackGeminiProto$TalkBackOnDeviceGemini12.geminiOptInConsentCount_ = i2;
        }
        if (i3 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini13 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini13.bitField0_ |= 16;
            talkBackGeminiProto$TalkBackOnDeviceGemini13.geminiOptInDissentCount_ = i3;
        }
        int i5 = this.prefs.getInt(this.service.getString(R.string.pref_ai_feature_download_request_key), 0);
        if (i5 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini14 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini14.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
            talkBackGeminiProto$TalkBackOnDeviceGemini14.aifeatureDownloadRequestCount_ = i5;
            z2 = true;
        }
        int i6 = this.prefs.getInt(this.service.getString(R.string.pref_ai_feature_download_accept_key), 0);
        if (i6 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini15 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini15.bitField0_ |= 256;
            talkBackGeminiProto$TalkBackOnDeviceGemini15.aifeatureDownloadAcceptCount_ = i6;
            z2 = true;
        }
        int i7 = this.prefs.getInt(this.service.getString(R.string.pref_ai_core_update_request_key), 0);
        if (i7 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini16 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini16.bitField0_ |= 512;
            talkBackGeminiProto$TalkBackOnDeviceGemini16.aicoreUpdateRequestCount_ = i7;
            z2 = true;
        }
        int i8 = this.prefs.getInt(this.service.getString(R.string.pref_ai_core_update_accept_key), 0);
        if (i8 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini17 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini17.bitField0_ |= 1024;
            talkBackGeminiProto$TalkBackOnDeviceGemini17.aicoreUpdateAcceptCount_ = i8;
            z2 = true;
        }
        int i9 = this.prefs.getInt(this.service.getString(R.string.pref_astrea_update_request_key), 0);
        if (i9 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini18 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini18.bitField0_ |= 2048;
            talkBackGeminiProto$TalkBackOnDeviceGemini18.astreaUpdateRequestCount_ = i9;
        } else {
            z3 = z2;
        }
        int i10 = this.prefs.getInt(this.service.getString(R.string.pref_astrea_update_accept_key), 0);
        if (i10 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini19 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.instance;
            talkBackGeminiProto$TalkBackOnDeviceGemini19.bitField0_ |= 4096;
            talkBackGeminiProto$TalkBackOnDeviceGemini19.astreaUpdateAcceptCount_ = i10;
        } else if (!z3) {
            return;
        }
        builder.copyOnWrite();
        TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension3 = (TalkBackLogProto$TalkBackExtension) builder.instance;
        TalkBackGeminiProto$TalkBackOnDeviceGemini talkBackGeminiProto$TalkBackOnDeviceGemini20 = (TalkBackGeminiProto$TalkBackOnDeviceGemini) builder2.build();
        TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension4 = TalkBackLogProto$TalkBackExtension.DEFAULT_INSTANCE;
        talkBackGeminiProto$TalkBackOnDeviceGemini20.getClass();
        talkBackLogProto$TalkBackExtension3.talkbackOnDeviceGemini_ = talkBackGeminiProto$TalkBackOnDeviceGemini20;
        talkBackLogProto$TalkBackExtension3.bitField0_ |= 268435456;
        this.prefs.edit().remove(this.service.getString(R.string.pref_gemini_aicore_opt_in_show_dialog_key)).remove(this.service.getString(R.string.pref_gemini_aicore_opt_in_consent_key)).remove(this.service.getString(R.string.pref_gemini_aicore_opt_in_dissent_key)).remove(this.service.getString(R.string.pref_ai_feature_download_request_key)).remove(this.service.getString(R.string.pref_ai_feature_download_accept_key)).remove(this.service.getString(R.string.pref_ai_core_update_request_key)).remove(this.service.getString(R.string.pref_ai_core_update_accept_key)).remove(this.service.getString(R.string.pref_astrea_update_request_key)).remove(this.service.getString(R.string.pref_astrea_update_accept_key)).apply();
    }

    private final void populateGeminiEvent$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SystemHealthProto$PackedHistogram.Builder builder) {
        Set<TalkBackAnalyticsDBHelper.MiscellaneousEntry> miscellaneousEntry = this.dbHelper.getMiscellaneousEntry();
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) TalkBackGeminiProto$TalkBackGemini.DEFAULT_INSTANCE.createBuilder();
        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.DEFAULT_INSTANCE.createBuilder();
        boolean z = true;
        if (!miscellaneousEntry.isEmpty()) {
            for (TalkBackAnalyticsDBHelper.MiscellaneousEntry miscellaneousEntry2 : miscellaneousEntry) {
                int item = miscellaneousEntry2.item();
                if (item != 35) {
                    switch (item) {
                        case 5:
                            int count = miscellaneousEntry2.count();
                            builder2.copyOnWrite();
                            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
                            talkBackGeminiProto$TalkBackGemini.bitField0_ |= 1;
                            talkBackGeminiProto$TalkBackGemini.geminiRequestCount_ = count;
                            break;
                        case 6:
                            int count2 = miscellaneousEntry2.count();
                            builder2.copyOnWrite();
                            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini2 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
                            talkBackGeminiProto$TalkBackGemini2.bitField0_ |= 2;
                            talkBackGeminiProto$TalkBackGemini2.geminiSuccessCount_ = count2;
                            break;
                        case 7:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_APIKEY_NOT_AVAILABLE$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 8:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_USER_NOT_OPT_IN$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 9:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_INTERNET_UNREACHABLE$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 10:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_NO_SCREENSHOT_PROVIDED$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 11:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_COMMAND_NOT_PROVIDED$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 12:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_FAIL_TO_ENCODE_PICTURE$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 13:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_FAIL_TO_PARSE_RESPONSE$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 14:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_CONTENT_BLOCKED$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 15:
                            createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_PROTOCOL_ERROR$ar$edu, miscellaneousEntry2.count());
                            break;
                        case 16:
                            int count3 = miscellaneousEntry2.count();
                            builder2.copyOnWrite();
                            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini3 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
                            talkBackGeminiProto$TalkBackGemini3.bitField0_ |= 32;
                            talkBackGeminiProto$TalkBackGemini3.geminiOptInShownCount_ = count3;
                            break;
                        case 17:
                            int count4 = miscellaneousEntry2.count();
                            builder2.copyOnWrite();
                            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini4 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
                            talkBackGeminiProto$TalkBackGemini4.bitField0_ |= 8;
                            talkBackGeminiProto$TalkBackGemini4.geminiOptInConsentCount_ = count4;
                            break;
                        case 18:
                            int count5 = miscellaneousEntry2.count();
                            builder2.copyOnWrite();
                            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini5 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
                            talkBackGeminiProto$TalkBackGemini5.bitField0_ |= 16;
                            talkBackGeminiProto$TalkBackGemini5.geminiOptInDissentCount_ = count5;
                            break;
                    }
                } else {
                    createGeminiFailRecord$ar$ds$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder3, TalkBackGeminiConstant$GeminiFailReason.GEMINI_FAIL_REASON_USER_ABORT$ar$edu, miscellaneousEntry2.count());
                }
            }
            if (((TalkBackGeminiProto$TalkBackGemini.GeminiFailCount) builder3.instance).geminiFailReasonCount_.size() != 0) {
                builder2.copyOnWrite();
                TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini6 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
                TalkBackGeminiProto$TalkBackGemini.GeminiFailCount geminiFailCount = (TalkBackGeminiProto$TalkBackGemini.GeminiFailCount) builder3.build();
                geminiFailCount.getClass();
                talkBackGeminiProto$TalkBackGemini6.geminiFailCount_ = geminiFailCount;
                talkBackGeminiProto$TalkBackGemini6.bitField0_ |= 4;
            }
        }
        int i = this.prefs.getInt(this.service.getString(R.string.pref_gemini_opt_in_show_dialog_key), 0);
        TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini7 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
        if ((talkBackGeminiProto$TalkBackGemini7.bitField0_ & 32) != 0) {
            i += talkBackGeminiProto$TalkBackGemini7.geminiOptInShownCount_;
        }
        int i2 = this.prefs.getInt(this.service.getString(R.string.pref_gemini_opt_in_consent_key), 0);
        TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini8 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
        if ((talkBackGeminiProto$TalkBackGemini8.bitField0_ & 8) != 0) {
            i2 += talkBackGeminiProto$TalkBackGemini8.geminiOptInConsentCount_;
        }
        int i3 = this.prefs.getInt(this.service.getString(R.string.pref_gemini_opt_in_dissent_key), 0);
        TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini9 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
        int i4 = talkBackGeminiProto$TalkBackGemini9.bitField0_;
        if ((i4 & 16) != 0) {
            i3 += talkBackGeminiProto$TalkBackGemini9.geminiOptInDissentCount_;
        }
        if ((i4 & 1) == 0 && i + i2 + i3 <= 0) {
            z = false;
        }
        if (i > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini10 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
            talkBackGeminiProto$TalkBackGemini10.bitField0_ |= 32;
            talkBackGeminiProto$TalkBackGemini10.geminiOptInShownCount_ = i;
        }
        if (i2 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini11 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
            talkBackGeminiProto$TalkBackGemini11.bitField0_ |= 8;
            talkBackGeminiProto$TalkBackGemini11.geminiOptInConsentCount_ = i2;
        }
        if (i3 > 0) {
            builder2.copyOnWrite();
            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini12 = (TalkBackGeminiProto$TalkBackGemini) builder2.instance;
            talkBackGeminiProto$TalkBackGemini12.bitField0_ |= 16;
            talkBackGeminiProto$TalkBackGemini12.geminiOptInDissentCount_ = i3;
        }
        if (z) {
            builder.copyOnWrite();
            TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension = (TalkBackLogProto$TalkBackExtension) builder.instance;
            TalkBackGeminiProto$TalkBackGemini talkBackGeminiProto$TalkBackGemini13 = (TalkBackGeminiProto$TalkBackGemini) builder2.build();
            TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension2 = TalkBackLogProto$TalkBackExtension.DEFAULT_INSTANCE;
            talkBackGeminiProto$TalkBackGemini13.getClass();
            talkBackLogProto$TalkBackExtension.talkbackGemini_ = talkBackGeminiProto$TalkBackGemini13;
            talkBackLogProto$TalkBackExtension.bitField0_ |= 67108864;
            this.prefs.edit().remove(this.service.getString(R.string.pref_gemini_opt_in_show_dialog_key)).remove(this.service.getString(R.string.pref_gemini_opt_in_consent_key)).remove(this.service.getString(R.string.pref_gemini_opt_in_dissent_key)).apply();
        }
    }

    public final String getLoggablePrefValue(String str) {
        if (!this.prefs.contains(str)) {
            return null;
        }
        return String.valueOf(this.prefs.getAll().get(str));
    }

    public final void logPendingChanges() {
        OrderVerifyingClientCall.State state;
        if (this.dbHelper != null && (state = this.pendingSettingChangeAction$ar$class_merging$ar$class_merging$ar$class_merging) != null) {
            final String str = (String) state.OrderVerifyingClientCall$State$ar$cancellationStatus;
            final String loggablePrefValue = getLoggablePrefValue(str);
            if (loggablePrefValue != null) {
                new ActionTask(new Runnable() { // from class: com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda7
                    public final /* synthetic */ int f$3 = 4;

                    @Override // java.lang.Runnable
                    public final void run() {
                        TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = TalkBackAnalyticsLoggerWithClearcut.this;
                        if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                            String str2 = loggablePrefValue;
                            talkBackAnalyticsLoggerWithClearcut.dbHelper.cacheSettingChange(str, str2, 4);
                        }
                    }
                }).execute(new Void[0]);
            }
            this.pendingSettingChangeAction$ar$class_merging$ar$class_merging$ar$class_merging = null;
        }
    }

    public final void saveLastLogTime(long j) {
        this.lastLogTime = j;
        this.prefs.edit().putLong(this.service.getString(R.string.pref_key_last_log_time_key), j).apply();
    }

    public final synchronized void sendAllLogsAndClearCacheInternal(boolean z) {
        if (this.dbHelper != null) {
            this.analyticsCommon.doInBackground(populateEventAndClearCache(z));
        }
    }

    public final synchronized void shutdownInfrastructure() {
        this.dbHelper.close();
        this.dbHelper = null;
    }
}
