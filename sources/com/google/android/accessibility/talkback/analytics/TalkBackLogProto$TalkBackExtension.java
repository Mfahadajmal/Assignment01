package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackLogProto$TalkBackExtension extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackLogProto$TalkBackExtension DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int activationCount_;
    public int bitField0_;
    private TalkbackContextMenuActionsProto$TalkbackContextMenuActions contextMenuActions_;
    private TalkBackGesturesUsedProto$TalkBackGesturesUsed gesturesUsed_;
    private GranularityMovementsProto$GranularityMovements granularityMovements_;
    private int gupEntered_;
    public int hardwareVariant_;
    private ImageCaptionResultLevelProto$ImageCaptionResultLevel imageCaptionResultLevel_;
    private ImageCaptionerProto$ImageCaptioner imageCaptioner_;
    private int lastSentTimeHour_;
    private MagnificationUsedProto$MagnificationUsed magnificationUsed_;
    private TalkBackSelectorProto$TalkBackSelector selectorActions_;
    private TalkBackSettingChangesProto$TalkBackSettingChanges settingsChanges_;
    public TalkBackSettingsExtProto$TalkBackSettingsExt settingsSnapshotExt_;
    public TalkBackSettingsProto$TalkBackSettings settingsSnapshot_;
    private ShortcutActionsProto$GestureShortcutActions shortcutPerformed_;
    public boolean supportOnDeviceGemini_;
    public int survivalTimeHour_;
    public TalkBackGeminiProto$TalkBackGemini talkbackGemini_;
    private int talkbackItemsChanged_;
    private TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkbackKeyboardLog_;
    private int talkbackLoggingAttempted_;
    public TalkBackMistriggeringRecoveryProto$TalkBackMistriggeringRecovery talkbackMistriggeringRecovery_;
    public TalkBackGeminiProto$TalkBackOnDeviceGemini talkbackOnDeviceGemini_;
    private TrainingUsageProto$TrainingUsage trainingUsage_;
    private boolean ttsPredefined_;
    private int viewClicked_;
    private int viewFocused_;
    private TalkBackVoiceCommandProto$TalkBackVoiceCommand voiceCommands_;
    public String talkbackVersion_ = "";
    private String ttsPackage_ = "";

    static {
        TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension = new TalkBackLogProto$TalkBackExtension();
        DEFAULT_INSTANCE = talkBackLogProto$TalkBackExtension;
        GeneratedMessageLite.registerDefaultInstance(TalkBackLogProto$TalkBackExtension.class, talkBackLogProto$TalkBackExtension);
    }

    private TalkBackLogProto$TalkBackExtension() {
    }

    public static /* synthetic */ void access$100(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, String str) {
        str.getClass();
        talkBackLogProto$TalkBackExtension.bitField0_ |= 1;
        talkBackLogProto$TalkBackExtension.talkbackVersion_ = str;
    }

    public static /* synthetic */ void access$1300(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, TalkbackContextMenuActionsProto$TalkbackContextMenuActions talkbackContextMenuActionsProto$TalkbackContextMenuActions) {
        talkbackContextMenuActionsProto$TalkbackContextMenuActions.getClass();
        talkBackLogProto$TalkBackExtension.contextMenuActions_ = talkbackContextMenuActionsProto$TalkbackContextMenuActions;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 16;
    }

    public static /* synthetic */ void access$1600(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 32;
        talkBackLogProto$TalkBackExtension.talkbackLoggingAttempted_ = i;
    }

    public static /* synthetic */ void access$1800(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 64;
        talkBackLogProto$TalkBackExtension.talkbackItemsChanged_ = i;
    }

    public static /* synthetic */ void access$2000(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, TalkBackGesturesUsedProto$TalkBackGesturesUsed talkBackGesturesUsedProto$TalkBackGesturesUsed) {
        talkBackGesturesUsedProto$TalkBackGesturesUsed.getClass();
        talkBackLogProto$TalkBackExtension.gesturesUsed_ = talkBackGesturesUsedProto$TalkBackGesturesUsed;
        talkBackLogProto$TalkBackExtension.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
    }

    public static /* synthetic */ void access$2300(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, TalkBackVoiceCommandProto$TalkBackVoiceCommand talkBackVoiceCommandProto$TalkBackVoiceCommand) {
        talkBackVoiceCommandProto$TalkBackVoiceCommand.getClass();
        talkBackLogProto$TalkBackExtension.voiceCommands_ = talkBackVoiceCommandProto$TalkBackVoiceCommand;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 256;
    }

    public static /* synthetic */ void access$2600(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, GranularityMovementsProto$GranularityMovements granularityMovementsProto$GranularityMovements) {
        granularityMovementsProto$GranularityMovements.getClass();
        talkBackLogProto$TalkBackExtension.granularityMovements_ = granularityMovementsProto$GranularityMovements;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 512;
    }

    public static /* synthetic */ void access$2900(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, TalkBackSelectorProto$TalkBackSelector talkBackSelectorProto$TalkBackSelector) {
        talkBackSelectorProto$TalkBackSelector.getClass();
        talkBackLogProto$TalkBackExtension.selectorActions_ = talkBackSelectorProto$TalkBackSelector;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 1024;
    }

    public static /* synthetic */ void access$3200(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, ImageCaptionerProto$ImageCaptioner imageCaptionerProto$ImageCaptioner) {
        imageCaptionerProto$ImageCaptioner.getClass();
        talkBackLogProto$TalkBackExtension.imageCaptioner_ = imageCaptionerProto$ImageCaptioner;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 2048;
    }

    public static /* synthetic */ void access$3500(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, MagnificationUsedProto$MagnificationUsed magnificationUsedProto$MagnificationUsed) {
        magnificationUsedProto$MagnificationUsed.getClass();
        talkBackLogProto$TalkBackExtension.magnificationUsed_ = magnificationUsedProto$MagnificationUsed;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 4096;
    }

    public static /* synthetic */ void access$3800(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, TalkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog) {
        talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog.getClass();
        talkBackLogProto$TalkBackExtension.talkbackKeyboardLog_ = talkBackKeyboardShortcutProto$TalkBackKeyboardShortcutLog;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 8192;
    }

    public static /* synthetic */ void access$4400(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 32768;
        talkBackLogProto$TalkBackExtension.survivalTimeHour_ = i;
    }

    public static /* synthetic */ void access$4600(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 65536;
        talkBackLogProto$TalkBackExtension.lastSentTimeHour_ = i;
    }

    public static /* synthetic */ void access$4800(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 131072;
        talkBackLogProto$TalkBackExtension.activationCount_ = i;
    }

    public static /* synthetic */ void access$5000(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, ShortcutActionsProto$GestureShortcutActions shortcutActionsProto$GestureShortcutActions) {
        shortcutActionsProto$GestureShortcutActions.getClass();
        talkBackLogProto$TalkBackExtension.shortcutPerformed_ = shortcutActionsProto$GestureShortcutActions;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 262144;
    }

    public static /* synthetic */ void access$5300(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, TrainingUsageProto$TrainingUsage trainingUsageProto$TrainingUsage) {
        trainingUsageProto$TrainingUsage.getClass();
        talkBackLogProto$TalkBackExtension.trainingUsage_ = trainingUsageProto$TrainingUsage;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 524288;
    }

    public static /* synthetic */ void access$5600(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, ImageCaptionResultLevelProto$ImageCaptionResultLevel imageCaptionResultLevelProto$ImageCaptionResultLevel) {
        imageCaptionResultLevelProto$ImageCaptionResultLevel.getClass();
        talkBackLogProto$TalkBackExtension.imageCaptionResultLevel_ = imageCaptionResultLevelProto$ImageCaptionResultLevel;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 1048576;
    }

    public static /* synthetic */ void access$6200(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 4194304;
        talkBackLogProto$TalkBackExtension.viewClicked_ = i;
    }

    public static /* synthetic */ void access$6400(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 8388608;
        talkBackLogProto$TalkBackExtension.viewFocused_ = i;
    }

    public static /* synthetic */ void access$6600(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, String str) {
        str.getClass();
        talkBackLogProto$TalkBackExtension.bitField0_ |= 16777216;
        talkBackLogProto$TalkBackExtension.ttsPackage_ = str;
    }

    public static /* synthetic */ void access$6900(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, boolean z) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 33554432;
        talkBackLogProto$TalkBackExtension.ttsPredefined_ = z;
    }

    public static /* synthetic */ void access$700(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, TalkBackSettingChangesProto$TalkBackSettingChanges talkBackSettingChangesProto$TalkBackSettingChanges) {
        talkBackSettingChangesProto$TalkBackSettingChanges.getClass();
        talkBackLogProto$TalkBackExtension.settingsChanges_ = talkBackSettingChangesProto$TalkBackSettingChanges;
        talkBackLogProto$TalkBackExtension.bitField0_ |= 4;
    }

    public static /* synthetic */ void access$7400$ar$edu(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        if (i != 0) {
            talkBackLogProto$TalkBackExtension.hardwareVariant_ = i - 1;
            talkBackLogProto$TalkBackExtension.bitField0_ |= 134217728;
            return;
        }
        throw null;
    }

    public static /* synthetic */ void access$7900(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, int i) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 536870912;
        talkBackLogProto$TalkBackExtension.gupEntered_ = i;
    }

    public static /* synthetic */ void access$8100(TalkBackLogProto$TalkBackExtension talkBackLogProto$TalkBackExtension, boolean z) {
        talkBackLogProto$TalkBackExtension.bitField0_ |= 1073741824;
        talkBackLogProto$TalkBackExtension.supportOnDeviceGemini_ = z;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u001e\u0000\u0001\u0001 \u001e\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0005ဉ\u0004\u0006င\u0005\u0007င\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n\fဉ\u000b\rဉ\f\u000eဉ\r\u000fဉ\u000e\u0010င\u000f\u0011င\u0010\u0012င\u0011\u0013ဉ\u0012\u0014ဉ\u0013\u0015ဉ\u0014\u0016ဉ\u0015\u0017င\u0016\u0018င\u0017\u0019ဈ\u0018\u001aဇ\u0019\u001bဉ\u001a\u001c᠌\u001b\u001dဉ\u001c\u001eင\u001d ဇ\u001e", new Object[]{"bitField0_", "talkbackVersion_", "settingsSnapshot_", "settingsChanges_", "contextMenuActions_", "talkbackLoggingAttempted_", "talkbackItemsChanged_", "gesturesUsed_", "voiceCommands_", "granularityMovements_", "selectorActions_", "imageCaptioner_", "magnificationUsed_", "talkbackKeyboardLog_", "settingsSnapshotExt_", "survivalTimeHour_", "lastSentTimeHour_", "activationCount_", "shortcutPerformed_", "trainingUsage_", "imageCaptionResultLevel_", "talkbackMistriggeringRecovery_", "viewClicked_", "viewFocused_", "ttsPackage_", "ttsPredefined_", "talkbackGemini_", "hardwareVariant_", ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.TtsEngineName.TtsEngineNameVerifier.class_merging$INSTANCE$10, "talkbackOnDeviceGemini_", "gupEntered_", "supportOnDeviceGemini_"});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackLogProto$TalkBackExtension();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((float[][]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackLogProto$TalkBackExtension.class) {
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
