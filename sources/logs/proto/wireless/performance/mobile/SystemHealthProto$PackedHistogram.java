package logs.proto.wireless.performance.mobile;

import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuLogProto$AccessibilityMenuExtension;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuSettingsProto$AccessibilityMenuSettings;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuShortcutProto$AccessibilityMenuShortcut;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$BrailleImeExtension;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationRecord;
import com.google.android.accessibility.selecttospeak.SelectToSpeakActionsProto$SelectToSpeakActions;
import com.google.android.accessibility.selecttospeak.SelectToSpeakLogProto$SelectToSpeakExtension;
import com.google.android.accessibility.selecttospeak.SelectToSpeakPipelineProto$SelectToSpeakPipeline;
import com.google.android.accessibility.selecttospeak.SelectToSpeakSettingsProto$SelectToSpeakSettings;
import com.google.android.accessibility.selecttospeak.SelectToSpeakWordCountsProto$SelectToSpeakWordCounts;
import com.google.android.accessibility.talkback.analytics.GranularityMovementsProto$GranularityMovements;
import com.google.android.accessibility.talkback.analytics.ImageCaptionResultLevelProto$ImageCaptionResultLevel;
import com.google.android.accessibility.talkback.analytics.ImageCaptionerProto$ImageCaptioner;
import com.google.android.accessibility.talkback.analytics.MagnificationUsedProto$MagnificationUsed;
import com.google.android.accessibility.talkback.analytics.ShortcutActionsProto$GestureShortcutActions;
import com.google.android.accessibility.talkback.analytics.TalkBackGeminiProto$TalkBackGemini;
import com.google.android.accessibility.talkback.analytics.TalkBackGeminiProto$TalkBackOnDeviceGemini;
import com.google.android.accessibility.talkback.analytics.TalkBackGesturesUsedProto$TalkBackGesturesUsed;
import com.google.android.accessibility.talkback.analytics.TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog;
import com.google.android.accessibility.talkback.analytics.TalkBackLogProto$TalkBackExtension;
import com.google.android.accessibility.talkback.analytics.TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery;
import com.google.android.accessibility.talkback.analytics.TalkBackSelectorProto$TalkBackSelector;
import com.google.android.accessibility.talkback.analytics.TalkBackSettingChangesProto$TalkBackSettingChanges;
import com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeyboardShortcut;
import com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeymapType;
import com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$ModifierKey;
import com.google.android.accessibility.talkback.analytics.TalkBackSettingsExtProto$TalkBackSettingsExt;
import com.google.android.accessibility.talkback.analytics.TalkBackSettingsProto$TalkBackSettings;
import com.google.android.accessibility.talkback.analytics.TalkBackVoiceCommandProto$TalkBackVoiceCommand;
import com.google.android.accessibility.talkback.analytics.TalkbackContextMenuActionsProto$TalkbackContextMenuActions;
import com.google.android.accessibility.talkback.analytics.TrainingUsageProto$PageDuration;
import com.google.android.accessibility.talkback.analytics.TrainingUsageProto$TrainingMetricWrapper;
import com.google.android.accessibility.talkback.analytics.TrainingUsageProto$TrainingUsage;
import com.google.android.apps.aicore.llm.InterfaceData$DrafterGuess;
import com.google.android.apps.aicore.llm.InterfaceData$SpeculativeDecodeStatistics;
import com.google.android.libraries.mdi.Download$ClientFile;
import com.google.android.libraries.mdi.Download$ClientFileGroup;
import com.google.android.libraries.nlp.garcon.ondevice.pipeline.Caption;
import com.google.android.libraries.nlp.garcon.ondevice.pipeline.ImageCaptioningPipelineOutput;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorFlags;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopStorage;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.performance.primes.metrics.crash.CrashedTikTokTraceConfigs;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshot;
import com.google.android.libraries.performance.proto.primes.persistent.PersistentFormat$BatterySnapshot;
import com.google.android.libraries.phenotype.client.api.Configurations;
import com.google.android.libraries.phenotype.client.api.Flag;
import com.google.android.libraries.phenotype.client.shareddir.SnapshotTokens;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$CredentialEncryptedStorageInfo;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$DeviceEncryptedStorageInfo;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$StorageInfos;
import com.google.android.libraries.phenotype.client.stable.AccountList;
import com.google.android.libraries.phenotype.client.stable.Accounts;
import com.google.android.libraries.phenotype.client.stable.FlagUpdateInfo;
import com.google.android.libraries.phenotype.client.stable.PackageMetadataProto$PackageMetadata;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$Snapshot;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$SnapshotFlag;
import com.google.android.libraries.vision.visionkit.OcrOptions;
import com.google.android.libraries.vision.visionkit.pipeline.AsynchronousApiOptions;
import com.google.android.libraries.vision.visionkit.pipeline.ComponentStatus;
import com.google.android.libraries.vision.visionkit.pipeline.HandTrackingResult;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig;
import com.google.android.libraries.vision.visionkit.pipeline.ResultsAccumulatorOptions;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.android.libraries.vision.visionkit.pipeline.ScreenOptions;
import com.google.android.libraries.vision.visionkit.pipeline.Status;
import com.google.android.libraries.vision.visionkit.pipeline.Subgraph;
import com.google.android.libraries.vision.visionkit.pipeline.VisualAnnotationResults;
import com.google.android.ssb.SsbProto$SsbState;
import com.google.common.logging.proto2api.Eventid$EventIdMessage;
import com.google.common.logging.proto2api.Logrecord$LogRecordProto;
import com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto;
import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import com.google.drishti.proto.GraphTemplateProto$TemplateArgument;
import com.google.drishti.proto.GraphTemplateProto$TemplateDict;
import com.google.identity.signedoutstate.v1.MobileSignedOutConsent;
import com.google.identity.signedoutstate.v1.SignedOutState;
import com.google.mediapipe.formats.proto.RectProto$NormalizedRect;
import com.google.ocr.OcrEngine$OcrEngineRuntimeOptions;
import com.google.ocr.PageLayoutMutator$PageLayoutMutatorContextOptions;
import com.google.ocr.PageLayoutMutator$PageLayoutMutatorRuntimeOptions;
import com.google.ocr.photo.ImageProtos$TextImage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Any;
import com.google.protobuf.Duration;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;
import com.google.protos.photos_vision_objectrec.proto2api.GeoLocation;
import com.google.protos.photos_vision_objectrec.proto2api.ImageTemplate;
import com.google.protos.photos_vision_objectrec.proto2api.ROI;
import com.google.protos.privacy.context.external.ExternalPRequestContext;
import com.google.protos.privacy.context.external.ExternalPrivacyContext;
import com.google.protos.research.socrates.Visual$Rectangular;
import com.google.protos.research.socrates.Visual$UIComponent;
import com.google.protos.research.socrates.Visual$VisualAnnotation;
import com.google.protos.research.socrates.VisualSelectionDescriptorOuterClass$Point2D;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$AnswerChoice;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$AnswerChoices;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$ClientContext;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$CommonData;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Completion;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$DisplaySettings;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Event;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$HttpEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Invitation;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$LibraryEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$MultiSelect;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$OpenText;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Payload;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$PrivacySettings;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$ProductContext;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Question;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Rating;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SensitiveClientContext;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$Session;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SingleSelect;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyRecordEventRequest;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyRecordEventResponse;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyTriggerRequest;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$SurveyTriggerResponse;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$TextSubstitution;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$TriggerContext;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$UserData;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$UserEvent;
import com.google.protos.uservoice.surveys.client.logging.UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry;
import com.google.scone.proto.Service$SurveyRecordEventRequest;
import com.google.scone.proto.Service$SurveyRecordEventResponse;
import com.google.scone.proto.Service$SurveyTriggerRequest;
import com.google.scone.proto.Service$SurveyTriggerResponse;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$BranchingDestination;
import com.google.scone.proto.Survey$ClientContext;
import com.google.scone.proto.Survey$Completion;
import com.google.scone.proto.Survey$DisplaySettings;
import com.google.scone.proto.Survey$Event;
import com.google.scone.proto.Survey$Invitation;
import com.google.scone.proto.Survey$LegalSettings;
import com.google.scone.proto.Survey$MultiSelect;
import com.google.scone.proto.Survey$OpenText;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$PrivacySettings;
import com.google.scone.proto.Survey$ProductContext;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$Rating;
import com.google.scone.proto.Survey$SensitiveClientContext;
import com.google.scone.proto.Survey$Session;
import com.google.scone.proto.Survey$SingleSelect;
import com.google.scone.proto.Survey$TextSubstitution;
import com.google.scone.proto.Survey$TriggerContext;
import com.google.search.mdi.aratea.cros.agent.proto.CrosAgentContextData;
import com.google.search.mdi.aratea.cros.agent.proto.CrosAgentResponse;
import com.google.search.mdi.aratea.proto.ArateaInputData;
import com.google.search.mdi.aratea.proto.ArateaOutputData;
import com.google.search.mdi.aratea.proto.FilteredData;
import com.google.search.mdi.aratea.proto.GenerateModelConfiguration;
import com.google.search.mdi.aratea.proto.GenerateRequest;
import com.google.search.mdi.aratea.proto.GenerateResponse;
import com.google.search.mdi.aratea.proto.GenerationSignalOverride;
import com.google.search.mdi.aratea.proto.Image;
import com.google.wireless.android.play.playlog.proto.LogSamplingRulesProto$LogSamplingRules;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$BraillebackExtension;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$DeviceInfo;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$SessionStartedEvent;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$SettingBrailleCode;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigRequest;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigResponse;
import google.internal.feedback.v1.Survey$SurveyExperimentFlags;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$CrashMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$CrashedTikTokTraceInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackageMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PrimesStats;
import org.chromium.net.httpflags.BaseFeatureOverrides;
import org.chromium.net.httpflags.FlagValue;
import org.chromium.net.httpflags.Flags;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$PackedHistogram extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final SystemHealthProto$PackedHistogram DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public Internal.IntList population_ = emptyIntList();
    public Internal.IntList lowerBound_ = emptyIntList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7) {
            super(AccessibilityMenuLogProto$AccessibilityMenuExtension.DEFAULT_INSTANCE);
        }

        public final void addActionsRecord$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Builder builder) {
            copyOnWrite();
            SelectToSpeakActionsProto$SelectToSpeakActions selectToSpeakActionsProto$SelectToSpeakActions = (SelectToSpeakActionsProto$SelectToSpeakActions) this.instance;
            SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord actionsRecord = (SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord) builder.build();
            SelectToSpeakActionsProto$SelectToSpeakActions selectToSpeakActionsProto$SelectToSpeakActions2 = SelectToSpeakActionsProto$SelectToSpeakActions.DEFAULT_INSTANCE;
            actionsRecord.getClass();
            Internal.ProtobufList protobufList = selectToSpeakActionsProto$SelectToSpeakActions.actionsRecord_;
            if (!protobufList.isModifiable()) {
                selectToSpeakActionsProto$SelectToSpeakActions.actionsRecord_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            selectToSpeakActionsProto$SelectToSpeakActions.actionsRecord_.add(actionsRecord);
        }

        public final void addAllJobs$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$UidHealthProto.ensureJobsIsMutable();
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.jobs_);
        }

        public final void addAllSensors$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$UidHealthProto.ensureSensorsIsMutable();
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.sensors_);
        }

        public final void addAllSpans$ar$ds(Iterable iterable) {
            copyOnWrite();
            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace = (PrimesTraceOuterClass$PrimesTrace) this.instance;
            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace2 = PrimesTraceOuterClass$PrimesTrace.DEFAULT_INSTANCE;
            primesTraceOuterClass$PrimesTrace.ensureSpansIsMutable();
            AbstractMessageLite.addAll(iterable, primesTraceOuterClass$PrimesTrace.spans_);
        }

        public final void addAllStatsPackages$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            Internal.ProtobufList protobufList = batteryMetric$UidHealthProto.statsPackages_;
            if (!protobufList.isModifiable()) {
                batteryMetric$UidHealthProto.statsPackages_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.statsPackages_);
        }

        public final void addAllStatsProcesses$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            Internal.ProtobufList protobufList = batteryMetric$UidHealthProto.statsProcesses_;
            if (!protobufList.isModifiable()) {
                batteryMetric$UidHealthProto.statsProcesses_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.statsProcesses_);
        }

        public final void addAllStatsServices$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto = (BatteryMetric$PackageHealthProto) this.instance;
            BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto2 = BatteryMetric$PackageHealthProto.DEFAULT_INSTANCE;
            Internal.ProtobufList protobufList = batteryMetric$PackageHealthProto.statsServices_;
            if (!protobufList.isModifiable()) {
                batteryMetric$PackageHealthProto.statsServices_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            AbstractMessageLite.addAll(iterable, batteryMetric$PackageHealthProto.statsServices_);
        }

        public final void addAllSyncs$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$UidHealthProto.ensureSyncsIsMutable();
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.syncs_);
        }

        public final void addAllWakelocksDraw$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$UidHealthProto.ensureWakelocksDrawIsMutable();
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.wakelocksDraw_);
        }

        public final void addAllWakelocksFull$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$UidHealthProto.ensureWakelocksFullIsMutable();
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.wakelocksFull_);
        }

        public final void addAllWakelocksPartial$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$UidHealthProto.ensureWakelocksPartialIsMutable();
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.wakelocksPartial_);
        }

        public final void addAllWakelocksWindow$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$UidHealthProto.ensureWakelocksWindowIsMutable();
            AbstractMessageLite.addAll(iterable, batteryMetric$UidHealthProto.wakelocksWindow_);
        }

        public final void addAllWakeupAlarmsCount$ar$ds(Iterable iterable) {
            copyOnWrite();
            BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto = (BatteryMetric$PackageHealthProto) this.instance;
            BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto2 = BatteryMetric$PackageHealthProto.DEFAULT_INSTANCE;
            Internal.ProtobufList protobufList = batteryMetric$PackageHealthProto.wakeupAlarmsCount_;
            if (!protobufList.isModifiable()) {
                batteryMetric$PackageHealthProto.wakeupAlarmsCount_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            AbstractMessageLite.addAll(iterable, batteryMetric$PackageHealthProto.wakeupAlarmsCount_);
        }

        public final void addAnswer$ar$ds(Survey$Event.QuestionAnswered.Selection selection) {
            copyOnWrite();
            Survey$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer = (Survey$Event.QuestionAnswered.MultipleSelectAnswer) this.instance;
            Survey$Event.QuestionAnswered.MultipleSelectAnswer multipleSelectAnswer2 = Survey$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE;
            selection.getClass();
            Internal.ProtobufList protobufList = multipleSelectAnswer.answer_;
            if (!protobufList.isModifiable()) {
                multipleSelectAnswer.answer_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            multipleSelectAnswer.answer_.add(selection);
        }

        public final void addCauses$ar$ds(Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
            copyOnWrite();
            Logrecord$ThrowableProto logrecord$ThrowableProto = (Logrecord$ThrowableProto) this.instance;
            Logrecord$ThrowableProto logrecord$ThrowableProto2 = Logrecord$ThrowableProto.DEFAULT_INSTANCE;
            logrecord$ThrowableBlockProto.getClass();
            logrecord$ThrowableProto.ensureCausesIsMutable();
            logrecord$ThrowableProto.causes_.add(logrecord$ThrowableBlockProto);
        }

        public final void addContextMenuActionEntities$ar$ds(TalkbackContextMenuActionsProto$TalkbackContextMenuActions.ContextMenuActions contextMenuActions) {
            copyOnWrite();
            TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions = (TalkbackContextMenuActionsProto$TalkbackContextMenuActions) this.instance;
            TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions2 = TalkbackContextMenuActionsProto$TalkbackContextMenuActions.DEFAULT_INSTANCE;
            contextMenuActions.getClass();
            Internal.ProtobufList protobufList = talkbackContextMenuActionsProto$TalkbackContextMenuActions.contextMenuActionEntities_;
            if (!protobufList.isModifiable()) {
                talkbackContextMenuActionsProto$TalkbackContextMenuActions.contextMenuActionEntities_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkbackContextMenuActionsProto$TalkbackContextMenuActions.contextMenuActionEntities_.add(contextMenuActions);
        }

        public final void addError$ar$ds$63342751_0(String str) {
            copyOnWrite();
            Service$SurveyTriggerResponse service$SurveyTriggerResponse = (Service$SurveyTriggerResponse) this.instance;
            Service$SurveyTriggerResponse service$SurveyTriggerResponse2 = Service$SurveyTriggerResponse.DEFAULT_INSTANCE;
            str.getClass();
            Internal.ProtobufList protobufList = service$SurveyTriggerResponse.error_;
            if (!protobufList.isModifiable()) {
                service$SurveyTriggerResponse.error_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            service$SurveyTriggerResponse.error_.add(str);
        }

        public final void addGestureSettings$ar$ds(TalkBackSettingsProto$TalkBackSettings.GestureShortcutAssignment gestureShortcutAssignment) {
            copyOnWrite();
            TalkBackSettingsProto$TalkBackSettings talkBackSettingsProto$TalkBackSettings = (TalkBackSettingsProto$TalkBackSettings) this.instance;
            TalkBackSettingsProto$TalkBackSettings talkBackSettingsProto$TalkBackSettings2 = TalkBackSettingsProto$TalkBackSettings.DEFAULT_INSTANCE;
            gestureShortcutAssignment.getClass();
            Internal.ProtobufList protobufList = talkBackSettingsProto$TalkBackSettings.gestureSettings_;
            if (!protobufList.isModifiable()) {
                talkBackSettingsProto$TalkBackSettings.gestureSettings_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackSettingsProto$TalkBackSettings.gestureSettings_.add(gestureShortcutAssignment);
        }

        public final void addGestureShortcutEntity$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Builder builder) {
            copyOnWrite();
            ShortcutActionsProto$GestureShortcutActions shortcutActionsProto$GestureShortcutActions = (ShortcutActionsProto$GestureShortcutActions) this.instance;
            ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity gestureShortcutEntity = (ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity) builder.build();
            ShortcutActionsProto$GestureShortcutActions shortcutActionsProto$GestureShortcutActions2 = ShortcutActionsProto$GestureShortcutActions.DEFAULT_INSTANCE;
            gestureShortcutEntity.getClass();
            Internal.ProtobufList protobufList = shortcutActionsProto$GestureShortcutActions.gestureShortcutEntity_;
            if (!protobufList.isModifiable()) {
                shortcutActionsProto$GestureShortcutActions.gestureShortcutEntity_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            shortcutActionsProto$GestureShortcutActions.gestureShortcutEntity_.add(gestureShortcutEntity);
        }

        public final void addGranularityMovementEntities$ar$ds(GranularityMovementsProto$GranularityMovements.GranularityMovementEntity granularityMovementEntity) {
            copyOnWrite();
            GranularityMovementsProto$GranularityMovements granularityMovementsProto$GranularityMovements = (GranularityMovementsProto$GranularityMovements) this.instance;
            GranularityMovementsProto$GranularityMovements granularityMovementsProto$GranularityMovements2 = GranularityMovementsProto$GranularityMovements.DEFAULT_INSTANCE;
            granularityMovementEntity.getClass();
            Internal.ProtobufList protobufList = granularityMovementsProto$GranularityMovements.granularityMovementEntities_;
            if (!protobufList.isModifiable()) {
                granularityMovementsProto$GranularityMovements.granularityMovementEntities_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            granularityMovementsProto$GranularityMovements.granularityMovementEntities_.add(granularityMovementEntity);
        }

        public final void addInputData$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(Builder builder) {
            copyOnWrite();
            GenerateRequest generateRequest = (GenerateRequest) this.instance;
            ArateaInputData arateaInputData = (ArateaInputData) builder.build();
            GenerateRequest generateRequest2 = GenerateRequest.DEFAULT_INSTANCE;
            arateaInputData.getClass();
            Internal.ProtobufList protobufList = generateRequest.inputData_;
            if (!protobufList.isModifiable()) {
                generateRequest.inputData_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            generateRequest.inputData_.add(arateaInputData);
        }

        public final void addInputStreamName$ar$ds(String str) {
            copyOnWrite();
            Subgraph subgraph = (Subgraph) this.instance;
            Subgraph subgraph2 = Subgraph.DEFAULT_INSTANCE;
            Internal.ProtobufList protobufList = subgraph.inputStreamName_;
            if (!protobufList.isModifiable()) {
                subgraph.inputStreamName_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            subgraph.inputStreamName_.add(str);
        }

        public final void addKeyCombination$ar$ds(int i) {
            copyOnWrite();
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity keyboardShortcutEntity = (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity) this.instance;
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity keyboardShortcutEntity2 = TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity.DEFAULT_INSTANCE;
            Internal.IntList intList = keyboardShortcutEntity.keyCombination_;
            if (!intList.isModifiable()) {
                keyboardShortcutEntity.keyCombination_ = GeneratedMessageLite.mutableCopy(intList);
            }
            keyboardShortcutEntity.keyCombination_.addInt(i);
        }

        public final void addKeyboardShortcutChangedEntities$ar$ds(TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity keyboardShortcutEntity) {
            copyOnWrite();
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog = (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) this.instance;
            Internal.IntListAdapter.IntConverter intConverter = TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_converter_;
            keyboardShortcutEntity.getClass();
            Internal.ProtobufList protobufList = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keyboardShortcutChangedEntities_;
            if (!protobufList.isModifiable()) {
                talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keyboardShortcutChangedEntities_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keyboardShortcutChangedEntities_.add(keyboardShortcutEntity);
        }

        public final void addKeyboardShortcutSettings$ar$ds(TalkBackSettingEnums$KeyboardShortcut talkBackSettingEnums$KeyboardShortcut) {
            copyOnWrite();
            TalkBackSettingsProto$TalkBackSettings talkBackSettingsProto$TalkBackSettings = (TalkBackSettingsProto$TalkBackSettings) this.instance;
            TalkBackSettingsProto$TalkBackSettings talkBackSettingsProto$TalkBackSettings2 = TalkBackSettingsProto$TalkBackSettings.DEFAULT_INSTANCE;
            talkBackSettingEnums$KeyboardShortcut.getClass();
            Internal.IntList intList = talkBackSettingsProto$TalkBackSettings.keyboardShortcutSettings_;
            if (!intList.isModifiable()) {
                talkBackSettingsProto$TalkBackSettings.keyboardShortcutSettings_ = GeneratedMessageLite.mutableCopy(intList);
            }
            talkBackSettingsProto$TalkBackSettings.keyboardShortcutSettings_.addInt(talkBackSettingEnums$KeyboardShortcut.value);
        }

        public final void addKeyboardShortcutUsedEntities$ar$ds(TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity keyboardShortcutEntity) {
            copyOnWrite();
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog = (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) this.instance;
            Internal.IntListAdapter.IntConverter intConverter = TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_converter_;
            keyboardShortcutEntity.getClass();
            Internal.ProtobufList protobufList = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keyboardShortcutUsedEntities_;
            if (!protobufList.isModifiable()) {
                talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keyboardShortcutUsedEntities_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keyboardShortcutUsedEntities_.add(keyboardShortcutEntity);
        }

        public final void addKeymapTypeChanged$ar$ds(TalkBackSettingEnums$KeymapType talkBackSettingEnums$KeymapType) {
            copyOnWrite();
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog = (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) this.instance;
            Internal.IntListAdapter.IntConverter intConverter = TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_converter_;
            talkBackSettingEnums$KeymapType.getClass();
            Internal.IntList intList = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_;
            if (!intList.isModifiable()) {
                talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_ = GeneratedMessageLite.mutableCopy(intList);
            }
            talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_.addInt(talkBackSettingEnums$KeymapType.value);
        }

        public final void addKeymapTypeUsed$ar$ds(TalkBackSettingEnums$KeymapType talkBackSettingEnums$KeymapType) {
            copyOnWrite();
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog = (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) this.instance;
            Internal.IntListAdapter.IntConverter intConverter = TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_converter_;
            talkBackSettingEnums$KeymapType.getClass();
            Internal.IntList intList = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeUsed_;
            if (!intList.isModifiable()) {
                talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeUsed_ = GeneratedMessageLite.mutableCopy(intList);
            }
            talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeUsed_.addInt(talkBackSettingEnums$KeymapType.value);
        }

        public final void addLowerBound$ar$ds(int i) {
            copyOnWrite();
            SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram = (SystemHealthProto$PackedHistogram) this.instance;
            SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram2 = SystemHealthProto$PackedHistogram.DEFAULT_INSTANCE;
            Internal.IntList intList = systemHealthProto$PackedHistogram.lowerBound_;
            if (!intList.isModifiable()) {
                systemHealthProto$PackedHistogram.lowerBound_ = GeneratedMessageLite.mutableCopy(intList);
            }
            systemHealthProto$PackedHistogram.lowerBound_.addInt(i);
        }

        public final void addModifierKeyChanged$ar$ds(TalkBackSettingEnums$ModifierKey talkBackSettingEnums$ModifierKey) {
            copyOnWrite();
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog = (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) this.instance;
            Internal.IntListAdapter.IntConverter intConverter = TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_converter_;
            talkBackSettingEnums$ModifierKey.getClass();
            Internal.IntList intList = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.modifierKeyChanged_;
            if (!intList.isModifiable()) {
                talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.modifierKeyChanged_ = GeneratedMessageLite.mutableCopy(intList);
            }
            talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.modifierKeyChanged_.addInt(talkBackSettingEnums$ModifierKey.value);
        }

        public final void addModifierKeyUsed$ar$ds(TalkBackSettingEnums$ModifierKey talkBackSettingEnums$ModifierKey) {
            copyOnWrite();
            TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog = (TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) this.instance;
            Internal.IntListAdapter.IntConverter intConverter = TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.keymapTypeChanged_converter_;
            talkBackSettingEnums$ModifierKey.getClass();
            Internal.IntList intList = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.modifierKeyUsed_;
            if (!intList.isModifiable()) {
                talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.modifierKeyUsed_ = GeneratedMessageLite.mutableCopy(intList);
            }
            talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.modifierKeyUsed_.addInt(talkBackSettingEnums$ModifierKey.value);
        }

        public final void addNetworkEventUsage$ar$ds$ar$class_merging$ar$class_merging(Builder builder) {
            copyOnWrite();
            NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric = (NetworkMetric$NetworkUsageMetric) this.instance;
            NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage = (NetworkMetric$NetworkEventUsage) builder.build();
            NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric2 = NetworkMetric$NetworkUsageMetric.DEFAULT_INSTANCE;
            networkMetric$NetworkEventUsage.getClass();
            networkMetric$NetworkUsageMetric.ensureNetworkEventUsageIsMutable();
            networkMetric$NetworkUsageMetric.networkEventUsage_.add(networkMetric$NetworkEventUsage);
        }

        public final void addNodes$ar$ds$aafd927b_0(Logrecord$ThrowableProto.ThrowableNode throwableNode) {
            copyOnWrite();
            Logrecord$ThrowableProto.ThrowableGraph throwableGraph = (Logrecord$ThrowableProto.ThrowableGraph) this.instance;
            Logrecord$ThrowableProto.ThrowableGraph throwableGraph2 = Logrecord$ThrowableProto.ThrowableGraph.DEFAULT_INSTANCE;
            throwableNode.getClass();
            throwableGraph.ensureNodesIsMutable();
            throwableGraph.nodes_.add(throwableNode);
        }

        public final void addNodes$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Builder builder) {
            copyOnWrite();
            Logrecord$ThrowableProto.ThrowableGraph throwableGraph = (Logrecord$ThrowableProto.ThrowableGraph) this.instance;
            Logrecord$ThrowableProto.ThrowableNode throwableNode = (Logrecord$ThrowableProto.ThrowableNode) builder.build();
            Logrecord$ThrowableProto.ThrowableGraph throwableGraph2 = Logrecord$ThrowableProto.ThrowableGraph.DEFAULT_INSTANCE;
            throwableNode.getClass();
            throwableGraph.ensureNodesIsMutable();
            throwableGraph.nodes_.add(throwableNode);
        }

        public final void addPopulation$ar$ds(int i) {
            copyOnWrite();
            SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram = (SystemHealthProto$PackedHistogram) this.instance;
            SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram2 = SystemHealthProto$PackedHistogram.DEFAULT_INSTANCE;
            Internal.IntList intList = systemHealthProto$PackedHistogram.population_;
            if (!intList.isModifiable()) {
                systemHealthProto$PackedHistogram.population_ = GeneratedMessageLite.mutableCopy(intList);
            }
            systemHealthProto$PackedHistogram.population_.addInt(i);
        }

        public final void addPresentError$ar$ds$ar$edu(int i) {
            copyOnWrite();
            UserVoiceSurveysLogging$LibraryEvent.PresentSurveyCallInfo presentSurveyCallInfo = (UserVoiceSurveysLogging$LibraryEvent.PresentSurveyCallInfo) this.instance;
            UserVoiceSurveysLogging$LibraryEvent.PresentSurveyCallInfo presentSurveyCallInfo2 = UserVoiceSurveysLogging$LibraryEvent.PresentSurveyCallInfo.DEFAULT_INSTANCE;
            if (i != 0) {
                Internal.IntList intList = presentSurveyCallInfo.presentError_;
                if (!intList.isModifiable()) {
                    presentSurveyCallInfo.presentError_ = GeneratedMessageLite.mutableCopy(intList);
                }
                presentSurveyCallInfo.presentError_.addInt(UserVoiceSurveysLogging$LibraryEvent.PresentSurveyCallInfo.PresentError.getNumber$ar$edu$9ec2dfce_0(i));
                return;
            }
            throw null;
        }

        public final void addSelectorEntities$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Builder builder) {
            copyOnWrite();
            TalkBackSelectorProto$TalkBackSelector talkBackSelectorProto$TalkBackSelector = (TalkBackSelectorProto$TalkBackSelector) this.instance;
            TalkBackSelectorProto$TalkBackSelector.SelectorEntity selectorEntity = (TalkBackSelectorProto$TalkBackSelector.SelectorEntity) builder.build();
            TalkBackSelectorProto$TalkBackSelector talkBackSelectorProto$TalkBackSelector2 = TalkBackSelectorProto$TalkBackSelector.DEFAULT_INSTANCE;
            selectorEntity.getClass();
            Internal.ProtobufList protobufList = talkBackSelectorProto$TalkBackSelector.selectorEntities_;
            if (!protobufList.isModifiable()) {
                talkBackSelectorProto$TalkBackSelector.selectorEntities_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackSelectorProto$TalkBackSelector.selectorEntities_.add(selectorEntity);
        }

        public final void addSettingChangeEntities$ar$ds(TalkBackSettingChangesProto$TalkBackSettingChanges.SettingChangeEntity settingChangeEntity) {
            copyOnWrite();
            TalkBackSettingChangesProto$TalkBackSettingChanges talkBackSettingChangesProto$TalkBackSettingChanges = (TalkBackSettingChangesProto$TalkBackSettingChanges) this.instance;
            TalkBackSettingChangesProto$TalkBackSettingChanges talkBackSettingChangesProto$TalkBackSettingChanges2 = TalkBackSettingChangesProto$TalkBackSettingChanges.DEFAULT_INSTANCE;
            settingChangeEntity.getClass();
            Internal.ProtobufList protobufList = talkBackSettingChangesProto$TalkBackSettingChanges.settingChangeEntities_;
            if (!protobufList.isModifiable()) {
                talkBackSettingChangesProto$TalkBackSettingChanges.settingChangeEntities_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackSettingChangesProto$TalkBackSettingChanges.settingChangeEntities_.add(settingChangeEntity);
        }

        public final void addSettingExtChangeEntities$ar$ds(TalkBackSettingChangesProto$TalkBackSettingChanges.SettingExtChangeEntity settingExtChangeEntity) {
            copyOnWrite();
            TalkBackSettingChangesProto$TalkBackSettingChanges talkBackSettingChangesProto$TalkBackSettingChanges = (TalkBackSettingChangesProto$TalkBackSettingChanges) this.instance;
            TalkBackSettingChangesProto$TalkBackSettingChanges talkBackSettingChangesProto$TalkBackSettingChanges2 = TalkBackSettingChangesProto$TalkBackSettingChanges.DEFAULT_INSTANCE;
            settingExtChangeEntity.getClass();
            Internal.ProtobufList protobufList = talkBackSettingChangesProto$TalkBackSettingChanges.settingExtChangeEntities_;
            if (!protobufList.isModifiable()) {
                talkBackSettingChangesProto$TalkBackSettingChanges.settingExtChangeEntities_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackSettingChangesProto$TalkBackSettingChanges.settingExtChangeEntities_.add(settingExtChangeEntity);
        }

        public final void addTalkbackGestureUsed$ar$ds(TalkBackGesturesUsedProto$TalkBackGesturesUsed.TalkBackGesturesUsedEntry talkBackGesturesUsedEntry) {
            copyOnWrite();
            TalkBackGesturesUsedProto$TalkBackGesturesUsed talkBackGesturesUsedProto$TalkBackGesturesUsed = (TalkBackGesturesUsedProto$TalkBackGesturesUsed) this.instance;
            TalkBackGesturesUsedProto$TalkBackGesturesUsed talkBackGesturesUsedProto$TalkBackGesturesUsed2 = TalkBackGesturesUsedProto$TalkBackGesturesUsed.DEFAULT_INSTANCE;
            talkBackGesturesUsedEntry.getClass();
            Internal.ProtobufList protobufList = talkBackGesturesUsedProto$TalkBackGesturesUsed.talkbackGestureUsed_;
            if (!protobufList.isModifiable()) {
                talkBackGesturesUsedProto$TalkBackGesturesUsed.talkbackGestureUsed_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackGesturesUsedProto$TalkBackGesturesUsed.talkbackGestureUsed_.add(talkBackGesturesUsedEntry);
        }

        public final void addTrainingMetricWrapper$ar$ds(TrainingUsageProto$TrainingMetricWrapper trainingUsageProto$TrainingMetricWrapper) {
            copyOnWrite();
            TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage = (TrainingUsageProto$TrainingUsage) this.instance;
            TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage2 = TrainingUsageProto$TrainingUsage.DEFAULT_INSTANCE;
            trainingUsageProto$TrainingMetricWrapper.getClass();
            Internal.ProtobufList protobufList = trainingUsageProto$TrainingUsage.trainingMetricWrapper_;
            if (!protobufList.isModifiable()) {
                trainingUsageProto$TrainingUsage.trainingMetricWrapper_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            trainingUsageProto$TrainingUsage.trainingMetricWrapper_.add(trainingUsageProto$TrainingMetricWrapper);
        }

        public final void addValues$ar$ds(String str) {
            copyOnWrite();
            AccountList accountList = (AccountList) this.instance;
            AccountList accountList2 = AccountList.DEFAULT_INSTANCE;
            str.getClass();
            Internal.ProtobufList protobufList = accountList.values_;
            if (!protobufList.isModifiable()) {
                accountList.values_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            accountList.values_.add(str);
        }

        public final void addVoiceCommandMetrics$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Builder builder) {
            copyOnWrite();
            TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand = (TalkBackVoiceCommandProto$TalkBackVoiceCommand) this.instance;
            TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics voiceCommandMetrics = (TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics) builder.build();
            TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand2 = TalkBackVoiceCommandProto$TalkBackVoiceCommand.DEFAULT_INSTANCE;
            voiceCommandMetrics.getClass();
            Internal.ProtobufList protobufList = talkBackVoiceCommandProto$TalkBackVoiceCommand.voiceCommandMetrics_;
            if (!protobufList.isModifiable()) {
                talkBackVoiceCommandProto$TalkBackVoiceCommand.voiceCommandMetrics_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            talkBackVoiceCommandProto$TalkBackVoiceCommand.voiceCommandMetrics_.add(voiceCommandMetrics);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final BatteryMetric$Timer getJobs(int i) {
            return (BatteryMetric$Timer) ((BatteryMetric$UidHealthProto) this.instance).jobs_.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final BatteryMetric$Timer getSensors(int i) {
            return (BatteryMetric$Timer) ((BatteryMetric$UidHealthProto) this.instance).sensors_.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final BatteryMetric$Timer getSyncs(int i) {
            return (BatteryMetric$Timer) ((BatteryMetric$UidHealthProto) this.instance).syncs_.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final BatteryMetric$Timer getWakelocksDraw(int i) {
            return (BatteryMetric$Timer) ((BatteryMetric$UidHealthProto) this.instance).wakelocksDraw_.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final BatteryMetric$Timer getWakelocksFull(int i) {
            return (BatteryMetric$Timer) ((BatteryMetric$UidHealthProto) this.instance).wakelocksFull_.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final BatteryMetric$Timer getWakelocksPartial(int i) {
            return (BatteryMetric$Timer) ((BatteryMetric$UidHealthProto) this.instance).wakelocksPartial_.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final BatteryMetric$Timer getWakelocksWindow(int i) {
            return (BatteryMetric$Timer) ((BatteryMetric$UidHealthProto) this.instance).wakelocksWindow_.get(i);
        }

        public final void putAccountLists$ar$ds(String str, AccountList accountList) {
            str.getClass();
            accountList.getClass();
            copyOnWrite();
            Accounts accounts = (Accounts) this.instance;
            Accounts accounts2 = Accounts.DEFAULT_INSTANCE;
            MapFieldLite mapFieldLite = accounts.accountLists_;
            if (!mapFieldLite.isMutable) {
                accounts.accountLists_ = mapFieldLite.mutableCopy();
            }
            accounts.accountLists_.put(str, accountList);
        }

        public final void setCauses$ar$ds(int i, Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto) {
            copyOnWrite();
            Logrecord$ThrowableProto logrecord$ThrowableProto = (Logrecord$ThrowableProto) this.instance;
            Logrecord$ThrowableProto logrecord$ThrowableProto2 = Logrecord$ThrowableProto.DEFAULT_INSTANCE;
            logrecord$ThrowableBlockProto.getClass();
            logrecord$ThrowableProto.ensureCausesIsMutable();
            logrecord$ThrowableProto.causes_.set(i, logrecord$ThrowableBlockProto);
        }

        public final void setJobs$ar$ds(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$Timer.getClass();
            batteryMetric$UidHealthProto.ensureJobsIsMutable();
            batteryMetric$UidHealthProto.jobs_.set(i, batteryMetric$Timer);
        }

        public final void setNetworkEventUsage$ar$ds$ar$class_merging$ar$class_merging(int i, Builder builder) {
            copyOnWrite();
            NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric = (NetworkMetric$NetworkUsageMetric) this.instance;
            NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage = (NetworkMetric$NetworkEventUsage) builder.build();
            NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric2 = NetworkMetric$NetworkUsageMetric.DEFAULT_INSTANCE;
            networkMetric$NetworkEventUsage.getClass();
            networkMetric$NetworkUsageMetric.ensureNetworkEventUsageIsMutable();
            networkMetric$NetworkUsageMetric.networkEventUsage_.set(i, networkMetric$NetworkEventUsage);
        }

        public final void setNodes$ar$ds(int i, Logrecord$ThrowableProto.ThrowableNode throwableNode) {
            copyOnWrite();
            Logrecord$ThrowableProto.ThrowableGraph throwableGraph = (Logrecord$ThrowableProto.ThrowableGraph) this.instance;
            Logrecord$ThrowableProto.ThrowableGraph throwableGraph2 = Logrecord$ThrowableProto.ThrowableGraph.DEFAULT_INSTANCE;
            throwableNode.getClass();
            throwableGraph.ensureNodesIsMutable();
            throwableGraph.nodes_.set(i, throwableNode);
        }

        public final void setSensors$ar$ds(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$Timer.getClass();
            batteryMetric$UidHealthProto.ensureSensorsIsMutable();
            batteryMetric$UidHealthProto.sensors_.set(i, batteryMetric$Timer);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void setSpans$ar$ds$ar$class_merging$ar$class_merging(int i, SchedulerOptions.Builder builder) {
            copyOnWrite();
            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace = (PrimesTraceOuterClass$PrimesTrace) this.instance;
            PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder.build();
            PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace2 = PrimesTraceOuterClass$PrimesTrace.DEFAULT_INSTANCE;
            primesTraceOuterClass$Span.getClass();
            primesTraceOuterClass$PrimesTrace.ensureSpansIsMutable();
            primesTraceOuterClass$PrimesTrace.spans_.set(i, primesTraceOuterClass$Span);
        }

        public final void setSyncs$ar$ds(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$Timer.getClass();
            batteryMetric$UidHealthProto.ensureSyncsIsMutable();
            batteryMetric$UidHealthProto.syncs_.set(i, batteryMetric$Timer);
        }

        public final void setWakelocksDraw$ar$ds(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$Timer.getClass();
            batteryMetric$UidHealthProto.ensureWakelocksDrawIsMutable();
            batteryMetric$UidHealthProto.wakelocksDraw_.set(i, batteryMetric$Timer);
        }

        public final void setWakelocksFull$ar$ds(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$Timer.getClass();
            batteryMetric$UidHealthProto.ensureWakelocksFullIsMutable();
            batteryMetric$UidHealthProto.wakelocksFull_.set(i, batteryMetric$Timer);
        }

        public final void setWakelocksPartial$ar$ds(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$Timer.getClass();
            batteryMetric$UidHealthProto.ensureWakelocksPartialIsMutable();
            batteryMetric$UidHealthProto.wakelocksPartial_.set(i, batteryMetric$Timer);
        }

        public final void setWakelocksWindow$ar$ds(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = (BatteryMetric$UidHealthProto) this.instance;
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2 = BatteryMetric$UidHealthProto.DEFAULT_INSTANCE;
            batteryMetric$Timer.getClass();
            batteryMetric$UidHealthProto.ensureWakelocksWindowIsMutable();
            batteryMetric$UidHealthProto.wakelocksWindow_.set(i, batteryMetric$Timer);
        }

        public Builder(byte[] bArr, char[] cArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            super(AccessibilityMenuSettingsProto$AccessibilityMenuSettings.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, char[] cArr2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(AccessibilityMenuSettingsProto$AccessibilityMenuSettings.SettingsRecord.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(AccessibilityMenuShortcutProto$AccessibilityMenuShortcut.ShortcutRecord.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(BrailleImeLogProto$BrailleImeExtension.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(BrailleImeLogProto$BrailleImeExtension.ContextMenuRecord.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            super(BrailleImeLogProto$BrailleImeExtension.GestureAction.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(BrailleImeLogProto$BrailleImeExtension.Language.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(BrailleImeLogProto$BrailleImeExtension.SettingsRecord.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(BrailleImeLogProto$BrailleImeExtension.TutorialFinishEvent.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(BrailleImeLogProto$CalibrationRecord.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(SelectToSpeakActionsProto$SelectToSpeakActions.ActionsRecord.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(SelectToSpeakActionsProto$SelectToSpeakActions.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            super(SelectToSpeakLogProto$SelectToSpeakExtension.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(SelectToSpeakPipelineProto$SelectToSpeakPipeline.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(SelectToSpeakPipelineProto$SelectToSpeakPipeline.PipelineRecord.TimeBucket.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(SelectToSpeakSettingsProto$SelectToSpeakSettings.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(SelectToSpeakSettingsProto$SelectToSpeakSettings.SettingsRecord.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8) {
            super(SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            super(SelectToSpeakWordCountsProto$SelectToSpeakWordCounts.WordCountsRecord.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            super(GranularityMovementsProto$GranularityMovements.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, char[] cArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(GranularityMovementsProto$GranularityMovements.GranularityMovementEntity.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, char[] cArr, short[] sArr2) {
            super(ImageCaptionResultLevelProto$ImageCaptionResultLevel.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, char[] cArr2, char[] cArr3) {
            super(ImageCaptionerProto$ImageCaptioner.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, byte[] bArr2, short[] sArr2) {
            super(MagnificationUsedProto$MagnificationUsed.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, short[] sArr, char[] cArr) {
            super(ShortcutActionsProto$GestureShortcutActions.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, char[] cArr, short[] sArr) {
            super(ShortcutActionsProto$GestureShortcutActions.GestureShortcutEntity.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, char[] cArr, char[] cArr2) {
            super(TalkBackGeminiProto$TalkBackGemini.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, byte[] bArr, short[] sArr) {
            super(TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, byte[] bArr2, short[] sArr) {
            super(TalkBackGeminiProto$TalkBackGemini.GeminiFailCount.GeminiFailReasonCount.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, byte[] bArr, short[] sArr) {
            super(TalkBackGeminiProto$TalkBackOnDeviceGemini.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, byte[] bArr, short[] sArr2) {
            super(TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, byte[] bArr, short[] sArr) {
            super(TalkBackGeminiProto$TalkBackOnDeviceGemini.GeminiFailCount.GeminiFailReasonCount.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, short[] sArr, char[] cArr2) {
            super(TalkBackGesturesUsedProto$TalkBackGesturesUsed.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, byte[] bArr, short[] sArr) {
            super(TalkBackGesturesUsedProto$TalkBackGesturesUsed.TalkBackGesturesUsedEntry.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, short[] sArr, char[] cArr) {
            super(TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, byte[] bArr, short[] sArr) {
            super(TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.KeyboardShortcutEntity.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, byte[] bArr, short[] sArr) {
            super(TalkBackLogProto$TalkBackExtension.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, byte[] bArr2, short[] sArr) {
            super(TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, char[] cArr, char[] cArr2) {
            super(TalkBackSelectorProto$TalkBackSelector.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, byte[] bArr, short[] sArr) {
            super(TalkBackSelectorProto$TalkBackSelector.SelectorEntity.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, char[] cArr2, short[] sArr) {
            super(TalkBackSettingChangesProto$TalkBackSettingChanges.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, byte[] bArr, short[] sArr2) {
            super(TalkBackSettingChangesProto$TalkBackSettingChanges.SettingChangeEntity.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, byte[] bArr, short[] sArr) {
            super(TalkBackSettingChangesProto$TalkBackSettingChanges.SettingExtChangeEntity.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, byte[] bArr, short[] sArr) {
            super(TalkBackSettingsExtProto$TalkBackSettingsExt.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, char[] cArr, short[] sArr) {
            super(TalkBackSettingsProto$TalkBackSettings.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, byte[] bArr, short[] sArr) {
            super(TalkBackSettingsProto$TalkBackSettings.GestureShortcutAssignment.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, byte[] bArr, short[] sArr) {
            super(TalkBackVoiceCommandProto$TalkBackVoiceCommand.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, char[] cArr, byte[] bArr5) {
            super(TalkBackVoiceCommandProto$TalkBackVoiceCommand.VoiceCommandMetrics.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, int[] iArr) {
            super(TalkbackContextMenuActionsProto$TalkbackContextMenuActions.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, char[] cArr2) {
            super(TalkbackContextMenuActionsProto$TalkbackContextMenuActions.ContextMenuActions.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, boolean[] zArr) {
            super(TrainingUsageProto$PageDuration.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, boolean[] zArr) {
            super(TrainingUsageProto$TrainingMetricWrapper.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, boolean[] zArr) {
            super(TrainingUsageProto$TrainingUsage.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, int[] iArr) {
            super(InterfaceData$DrafterGuess.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, boolean[] zArr) {
            super(InterfaceData$SpeculativeDecodeStatistics.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, boolean[] zArr) {
            super(Download$ClientFile.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, boolean[] zArr2) {
            super(Download$ClientFileGroup.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, boolean[] zArr) {
            super(Caption.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, int[] iArr) {
            super(ImageCaptioningPipelineOutput.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, int[] iArr) {
            super(CrashLoopMonitorFlags.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, int[] iArr) {
            super(CrashLoopStorage.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, int[] iArr2) {
            super(CrashRecordingTimeouts.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, int[] iArr) {
            super(CrashedTikTokTraceConfigs.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, int[] iArr) {
            super(PerfettoTraceConfigurations$JankPerfettoConfigurations.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, int[] iArr) {
            super(PerfettoTraceConfigurations$JankPerfettoConfigurations.Counter.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, int[] iArr) {
            super(ClearcutMetricSnapshot.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, int[] iArr) {
            super(PersistentFormat$BatterySnapshot.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, int[] iArr2) {
            super(Configurations.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, int[] iArr) {
            super(Flag.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, int[] iArr) {
            super(SnapshotTokens.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, short[] sArr) {
            super(StorageInfoProto$CredentialEncryptedStorageInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, short[] sArr) {
            super(StorageInfoProto$DeviceEncryptedStorageInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, short[] sArr, byte[] bArr3) {
            super(StorageInfoProto$StorageInfos.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, int[] iArr) {
            super(AccountList.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, char[] cArr, byte[] bArr2, byte[] bArr3) {
            super(Accounts.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, int[] iArr, byte[] bArr) {
            super(FlagUpdateInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, short[] sArr, byte[] bArr2) {
            super(PackageMetadataProto$PackageMetadata.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, int[] iArr, byte[] bArr) {
            super(SnapshotProto$Snapshot.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, int[] iArr2, byte[] bArr) {
            super(SnapshotProto$SnapshotFlag.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, int[] iArr, byte[] bArr) {
            super(OcrOptions.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, short[] sArr, byte[] bArr) {
            super(AsynchronousApiOptions.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, short[] sArr, byte[] bArr2) {
            super(ComponentStatus.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, short[] sArr, byte[] bArr) {
            super(HandTrackingResult.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, short[] sArr2, byte[] bArr) {
            super(PipelineConfig.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, short[] sArr, byte[] bArr) {
            super(ResultsAccumulatorOptions.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, short[] sArr, byte[] bArr) {
            super(ScreenOptions.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, short[] sArr, byte[] bArr) {
            super(Status.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, int[] iArr, byte[] bArr2, byte[] bArr3) {
            super(Subgraph.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, short[] sArr, byte[] bArr2) {
            super(VisualAnnotationResults.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, short[] sArr, byte[] bArr) {
            super(SsbProto$SsbState.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, short[] sArr2, byte[] bArr) {
            super(Eventid$EventIdMessage.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, short[] sArr, byte[] bArr) {
            super(Logrecord$LogRecordProto.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, short[] sArr, byte[] bArr) {
            super(Logrecord$ThrowableBlockProto.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, short[] sArr, byte[] bArr) {
            super(Logrecord$ThrowableBlockProto.StackTraceElement.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, short[] sArr, byte[] bArr, byte[] bArr2) {
            super(Logrecord$ThrowableProto.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, byte[] bArr2, char[] cArr) {
            super(Logrecord$ThrowableProto.ThrowableGraph.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            super(Logrecord$ThrowableProto.ThrowableNode.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            super(GraphTemplateProto$TemplateArgument.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, short[] sArr, byte[] bArr2, byte[] bArr3) {
            super(GraphTemplateProto$TemplateDict.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, char[] cArr, char[] cArr2) {
            super(GraphTemplateProto$TemplateDict.Parameter.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, short[] sArr, byte[] bArr, byte[] bArr2) {
            super(MobileSignedOutConsent.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, short[] sArr2, byte[] bArr, byte[] bArr2) {
            super(SignedOutState.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, short[] sArr, byte[] bArr, byte[] bArr2) {
            super(RectProto$NormalizedRect.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(OcrEngine$OcrEngineRuntimeOptions.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(PageLayoutMutator$PageLayoutMutatorContextOptions.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, char[] cArr, byte[] bArr2, byte[] bArr3) {
            super(PageLayoutMutator$PageLayoutMutatorRuntimeOptions.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, char[] cArr2, byte[] bArr, byte[] bArr2) {
            super(ImageProtos$TextImage.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(Any.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(Duration.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(Timestamp.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(TypedFeatures$StringListParam.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, char[] cArr, byte[] bArr2, byte[] bArr3) {
            super(GeoLocation.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, char[] cArr2, byte[] bArr, byte[] bArr2) {
            super(ImageTemplate.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(ROI.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(ExternalPRequestContext.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(ExternalPrivacyContext.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(Visual$Rectangular.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, char[] cArr, byte[] bArr3, byte[] bArr4) {
            super(Visual$UIComponent.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, char[] cArr2, byte[] bArr2, byte[] bArr3) {
            super(Visual$UIComponent.PredictedType.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, char[] cArr, char[] cArr2, byte[] bArr2) {
            super(Visual$VisualAnnotation.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, short[] sArr2) {
            super(VisualSelectionDescriptorOuterClass$Point2D.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, char[] cArr2, char[] cArr3, byte[] bArr) {
            super(UserVoiceSurveysLogging$AnswerChoice.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, char[] cArr, char[] cArr2) {
            super(UserVoiceSurveysLogging$AnswerChoices.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$ClientContext.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$ClientContext.DeviceInfo.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$ClientContext.DeviceInfo.MobileInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, byte[] bArr2, char[] cArr, byte[] bArr3) {
            super(UserVoiceSurveysLogging$ClientContext.LibraryInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, byte[] bArr, char[] cArr2, byte[] bArr2) {
            super(UserVoiceSurveysLogging$CommonData.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Completion.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$DisplaySettings.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$DisplaySettings.PromptDelay.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Event.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, byte[] bArr2, char[] cArr, byte[] bArr3) {
            super(UserVoiceSurveysLogging$Event.InvitationAnswered.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, byte[] bArr, char[] cArr2, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Event.QuestionAnswered.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Event.QuestionAnswered.OpenTextAnswer.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Event.QuestionAnswered.RatingAnswer.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, char[] cArr, byte[] bArr4) {
            super(UserVoiceSurveysLogging$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, char[] cArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$Event.SurveyAccepted.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, int[] iArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Event.SurveyClosed.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, short[] sArr, byte[] bArr) {
            super(UserVoiceSurveysLogging$Event.SurveyShown.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, int[] iArr) {
            super(UserVoiceSurveysLogging$HttpEvent.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, int[] iArr) {
            super(UserVoiceSurveysLogging$Invitation.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, int[] iArr2) {
            super(UserVoiceSurveysLogging$LibraryEvent.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, int[] iArr) {
            super(UserVoiceSurveysLogging$LibraryEvent.CreateClientCallInfo.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, short[] sArr) {
            super(UserVoiceSurveysLogging$LibraryEvent.DismissSurveyCallInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, short[] sArr) {
            super(UserVoiceSurveysLogging$LibraryEvent.LoginStateChangedInfo.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(UserVoiceSurveysLogging$LibraryEvent.PresentSurveyCallInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, short[] sArr) {
            super(UserVoiceSurveysLogging$LibraryEvent.ReportSurveyDeclinedCallInfo.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, short[] sArr2) {
            super(UserVoiceSurveysLogging$LibraryEvent.RequestSurveyCallInfo.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, short[] sArr) {
            super(UserVoiceSurveysLogging$MultiSelect.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, short[] sArr) {
            super(UserVoiceSurveysLogging$OpenText.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, short[] sArr) {
            super(UserVoiceSurveysLogging$Payload.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, short[] sArr) {
            super(UserVoiceSurveysLogging$PrivacySettings.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, short[] sArr) {
            super(UserVoiceSurveysLogging$ProductContext.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, short[] sArr2) {
            super(UserVoiceSurveysLogging$ProductContext.SensitiveContext.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, short[] sArr) {
            super(UserVoiceSurveysLogging$Question.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, short[] sArr) {
            super(UserVoiceSurveysLogging$Rating.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, short[] sArr) {
            super(UserVoiceSurveysLogging$SensitiveClientContext.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, int[] iArr) {
            super(UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, short[] sArr) {
            super(UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, char[] cArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, char[] cArr2, byte[] bArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$Session.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, char[] cArr, byte[] bArr, byte[] bArr2) {
            super(UserVoiceSurveysLogging$SingleSelect.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$SurveyRecordEventRequest.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$SurveyRecordEventResponse.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(UserVoiceSurveysLogging$SurveyTriggerRequest.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$SurveyTriggerResponse.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$TextSubstitution.AnswerPipe.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$TextSubstitution.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$TriggerContext.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$UserData.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(UserVoiceSurveysLogging$UserEvent.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(UserVoiceSurveysLogging$UserVoiceSurveysClientLogEntry.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(Service$SurveyRecordEventRequest.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(Service$SurveyRecordEventResponse.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(Service$SurveyTriggerRequest.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, char[] cArr, byte[] bArr2) {
            super(Service$SurveyTriggerResponse.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(Survey$AnswerChoice.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, char[] cArr) {
            super(Survey$AnswerChoices.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(Survey$BranchingDestination.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, short[] sArr, byte[] bArr2) {
            super(Survey$ClientContext.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, char[] cArr, byte[] bArr) {
            super(Survey$ClientContext.DeviceInfo.BrowserInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, short[] sArr, byte[] bArr) {
            super(Survey$ClientContext.DeviceInfo.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, short[] sArr2, byte[] bArr) {
            super(Survey$ClientContext.DeviceInfo.MobileInfo.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, short[] sArr, byte[] bArr) {
            super(Survey$ClientContext.LibraryInfo.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, char[] cArr, byte[] bArr) {
            super(Survey$Completion.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, char[] cArr, byte[] bArr) {
            super(Survey$DisplaySettings.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, char[] cArr, byte[] bArr2) {
            super(Survey$DisplaySettings.PromptDelay.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, char[] cArr2, byte[] bArr) {
            super(Survey$Event.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, char[] cArr, byte[] bArr) {
            super(Survey$Event.InvitationAnswered.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, char[] cArr, byte[] bArr) {
            super(Survey$Event.QuestionAnswered.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(Survey$Event.QuestionAnswered.MultipleSelectAnswer.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, char[] cArr, byte[] bArr) {
            super(Survey$Event.QuestionAnswered.OpenTextAnswer.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, char[] cArr, byte[] bArr) {
            super(Survey$Event.QuestionAnswered.RatingAnswer.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, char[] cArr, byte[] bArr2) {
            super(Survey$Event.QuestionAnswered.Selection.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, char[] cArr2, byte[] bArr) {
            super(Survey$Event.QuestionAnswered.SingleSelectAnswer.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, char[] cArr, byte[] bArr) {
            super(Survey$Event.SurveyAccepted.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, char[] cArr, byte[] bArr) {
            super(Survey$Event.SurveyClosed.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, char[] cArr, byte[] bArr) {
            super(Survey$Event.SurveyShown.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, char[] cArr, byte[] bArr) {
            super(Survey$Invitation.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, short[] sArr) {
            super(Survey$LegalSettings.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, char[] cArr2, byte[] bArr2) {
            super(Survey$MultiSelect.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, char[] cArr, char[] cArr2) {
            super(Survey$OpenText.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, char[] cArr) {
            super(Survey$Payload.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, char[] cArr2, char[] cArr3) {
            super(Survey$PrivacySettings.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, char[] cArr, byte[] bArr) {
            super(Survey$ProductContext.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, byte[] bArr, char[] cArr) {
            super(Survey$ProductContext.SensitiveContext.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, byte[] bArr, char[] cArr) {
            super(Survey$ProductContext.SensitiveContext.CustomEntry.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, byte[] bArr, char[] cArr) {
            super(Survey$Question.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, byte[] bArr2, char[] cArr) {
            super(Survey$Question.QuestionBranching.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, byte[] bArr, char[] cArr2) {
            super(Survey$Rating.BranchingConfig.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, byte[] bArr, char[] cArr) {
            super(Survey$Rating.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, byte[] bArr, char[] cArr) {
            super(Survey$SensitiveClientContext.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, byte[] bArr, char[] cArr) {
            super(Survey$SensitiveClientContext.SensitiveDeviceInfo.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, byte[] bArr, char[] cArr) {
            super(Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, byte[] bArr2, char[] cArr) {
            super(Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, byte[] bArr, char[] cArr2) {
            super(Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveChimeraInfo.SensitiveModuleSetInfo.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, byte[] bArr, char[] cArr) {
            super(Survey$SensitiveClientContext.SensitiveDeviceInfo.SensitiveMobileInfo.SensitiveTelephonyInfo.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, byte[] bArr, char[] cArr) {
            super(Survey$Session.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, byte[] bArr, char[] cArr) {
            super(Survey$SingleSelect.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, byte[] bArr, char[] cArr) {
            super(Survey$TextSubstitution.AnswerPipe.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, char[] cArr, byte[] bArr3) {
            super(Survey$TextSubstitution.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, char[] cArr2) {
            super(Survey$TriggerContext.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, char[] cArr) {
            super(CrosAgentContextData.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, int[] iArr) {
            super(CrosAgentResponse.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, short[] sArr) {
            super(ArateaInputData.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, short[] sArr2) {
            super(ArateaOutputData.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, short[] sArr) {
            super(FilteredData.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, short[] sArr) {
            super(GenerateModelConfiguration.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, char[] cArr) {
            super(GenerateRequest.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, char[] cArr) {
            super(GenerateResponse.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, char[] cArr) {
            super(GenerationSignalOverride.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, char[] cArr2) {
            super(Image.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, char[] cArr) {
            super(LogSamplingRulesProto$LogSamplingRules.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, char[] cArr) {
            super(LogSamplingRulesProto$LogSamplingRules.Rule.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, char[] cArr) {
            super(BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, char[] cArr) {
            super(BraillebackLogProto$DeviceInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, char[] cArr) {
            super(BraillebackLogProto$SessionStartedEvent.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, char[] cArr2) {
            super(BraillebackLogProto$SettingBrailleCode.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, char[] cArr) {
            super(Service$GetSurveyStartupConfigRequest.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, char[] cArr) {
            super(Service$GetSurveyStartupConfigResponse.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, char[] cArr) {
            super(google.internal.feedback.v1.Service$SurveyRecordEventRequest.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, char[] cArr) {
            super(google.internal.feedback.v1.Service$SurveyRecordEventResponse.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, char[] cArr) {
            super(google.internal.feedback.v1.Service$SurveyTriggerRequest.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, char[] cArr2) {
            super(google.internal.feedback.v1.Service$SurveyTriggerResponse.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            super(Survey$SurveyExperimentFlags.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, char[] cArr, byte[] bArr2) {
            super(AnrDiagnostic.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, char[] cArr2, byte[] bArr) {
            super(ApplicationExitInfo.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, char[] cArr) {
            super(ApplicationExitMetric.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, char[] cArr) {
            super(ApplicationExitReasons.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$BatteryUsageMetric.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, byte[] bArr2, byte[] bArr3) {
            super(BatteryMetric$Counter.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$HashedString.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$PackageHealthProto.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$PidHealthProto.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$ProcessHealthProto.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$ServiceHealthProto.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$Timer.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr, byte[] bArr2) {
            super(BatteryMetric$UidHealthProto.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, byte[] bArr2, byte[] bArr3) {
            super(CpuProfiling$CpuProfilingMetric.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, byte[] bArr, byte[] bArr2) {
            super(CpuProfiling$DeviceMetadata.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, byte[] bArr, byte[] bArr2) {
            super(CpuProfiling$DeviceState.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, byte[] bArr, byte[] bArr2) {
            super(ExtensionTalkback$TalkbackExtension.ActionLatencyInfo.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, byte[] bArr, byte[] bArr2) {
            super(ExtensionTalkback$TalkbackExtension.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, byte[] bArr, byte[] bArr2) {
            super(ExtensionTalkback$TalkbackExtension.EventLatencyInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            super(ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(ExtensionTalkback$TalkbackExtension.MessageQueueDetails.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, short[] sArr) {
            super(ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr) {
            super(InteractionContext.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, char[] cArr2) {
            super(MemoryMetric$AndroidMemoryStats.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr, byte[] bArr) {
            super(MemoryMetric$DeviceStats.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr, byte[] bArr) {
            super(MemoryMetric$MemoryStats.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr, byte[] bArr) {
            super(NetworkMetric$CacheStats.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr, byte[] bArr) {
            super(NetworkMetric$NetworkConnectionInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr, byte[] bArr2) {
            super(NetworkMetric$NetworkEventUsage.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, char[] cArr) {
            super(NetworkMetric$NetworkUsageMetric.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr, byte[] bArr) {
            super(NetworkMetric$RpcStats.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr, byte[] bArr) {
            super(NetworkMetric$ServerNetworkEventUsage.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr) {
            super(PrimesTraceOuterClass$PrimesTrace.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr, byte[] bArr) {
            super(PrimesTraceOuterClass$StartupMeasurements.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr, byte[] bArr) {
            super(PrimesTracing$Trace.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr, byte[] bArr) {
            super(ProcessProto$AndroidProcessStats.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr, byte[] bArr2) {
            super(ProcessProto$ProcessStats.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr, byte[] bArr) {
            super(SystemHealthProto$AccountableComponent.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr, byte[] bArr) {
            super(SystemHealthProto$ApplicationInfo.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr, byte[] bArr) {
            super(SystemHealthProto$ClientErrorLoggingMetric.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr, byte[] bArr) {
            super(SystemHealthProto$CrashMetric.DEFAULT_INSTANCE);
        }

        public Builder(float[][][] fArr, byte[] bArr) {
            super(SystemHealthProto$CrashMetric.CrashLoopInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(SystemHealthProto$CrashedTikTokTraceInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr, byte[] bArr, byte[] bArr2) {
            super(SystemHealthProto$CrashedTikTokTraceInfo.TruncationInfo.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            super(SystemHealthProto$DebugLogs.DEFAULT_INSTANCE);
        }

        public Builder(byte[] bArr) {
            super(SystemHealthProto$DeviceInfo.DEFAULT_INSTANCE);
        }

        public Builder(char[] cArr) {
            super(SystemHealthProto$HistogramBucket.DEFAULT_INSTANCE);
        }

        public Builder(short[] sArr) {
            super(SystemHealthProto$JankMetric.DEFAULT_INSTANCE);
        }

        public Builder(int[] iArr) {
            super(SystemHealthProto$PackageMetric.DEFAULT_INSTANCE);
        }

        public Builder(boolean[] zArr) {
            super(SystemHealthProto$PackageMetric.DirStats.DEFAULT_INSTANCE);
        }

        public Builder() {
            super(SystemHealthProto$PackedHistogram.DEFAULT_INSTANCE);
        }

        public Builder(float[] fArr) {
            super(SystemHealthProto$PrimesStats.DEFAULT_INSTANCE);
        }

        public Builder(byte[][] bArr) {
            super(SystemHealthProto$PrimesStats.PrimesDebugMessage.DEFAULT_INSTANCE);
        }

        public Builder(char[][] cArr) {
            super(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE);
        }

        public Builder(short[][] sArr) {
            super(SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE);
        }

        public Builder(int[][] iArr) {
            super(SystemHealthProto$TimerMetric.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][] zArr) {
            super(SystemHealthProto$TraceMetadata.DEFAULT_INSTANCE);
        }

        public Builder(float[][] fArr) {
            super(SystemHealthProto$TraceMetric.DEFAULT_INSTANCE);
        }

        public Builder(byte[][][] bArr) {
            super(BaseFeatureOverrides.DEFAULT_INSTANCE);
        }

        public Builder(char[][][] cArr) {
            super(BaseFeatureOverrides.FeatureState.DEFAULT_INSTANCE);
        }

        public Builder(short[][][] sArr) {
            super(FlagValue.DEFAULT_INSTANCE);
        }

        public Builder(int[][][] iArr) {
            super(FlagValue.ConstrainedValue.DEFAULT_INSTANCE);
        }

        public Builder(boolean[][][] zArr) {
            super(Flags.DEFAULT_INSTANCE);
        }
    }

    static {
        SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram = new SystemHealthProto$PackedHistogram();
        DEFAULT_INSTANCE = systemHealthProto$PackedHistogram;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$PackedHistogram.class, systemHealthProto$PackedHistogram);
    }

    private SystemHealthProto$PackedHistogram() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001'\u0002'", new Object[]{"population_", "lowerBound_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemHealthProto$PackedHistogram();
            case NEW_BUILDER:
                return new Builder();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$PackedHistogram.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
