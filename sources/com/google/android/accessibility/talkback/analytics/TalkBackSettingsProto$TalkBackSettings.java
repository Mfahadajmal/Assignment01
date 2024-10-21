package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackSettingsProto$TalkBackSettings extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackSettingsProto$TalkBackSettings DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int activeKeyMap_;
    public boolean alwaysSpeakWhenScrolling_;
    public boolean audioDucking_;
    public int bitField0_;
    public int bitField1_;
    public int bitField2_;
    public int capitalLetterHandle_;
    public boolean dimScreenWhenTalkbackIsEnabled_;
    public boolean displaySpeechOutput_;
    public boolean echoRecognizedSpeech_;
    public int elementDescriptionOrder_;
    public boolean enableNodeTreeDebugging_;
    public boolean enablePerformanceStatistics_;
    public int eventDumperBitMask_;
    public boolean exploreByTouch_;
    public boolean hasCustomLabels_;
    public int logOutputLevel_;
    public int modifierKey_;
    public boolean passwordAlwaysSpoken_;
    public boolean reduceWindowDelay_;
    public boolean selectorActivationAudioDucking_;
    public boolean selectorActivationHideScreen_;
    public boolean selectorActivationNavigationCharacter_;
    public boolean selectorActivationNavigationControl_;
    public boolean selectorActivationNavigationDefault_;
    public boolean selectorActivationNavigationHeading_;
    public boolean selectorActivationNavigationLandmark_;
    public boolean selectorActivationNavigationLine_;
    public boolean selectorActivationNavigationLink_;
    public boolean selectorActivationNavigationParagraph_;
    public boolean selectorActivationNavigationWord_;
    public boolean selectorActivationPunctuation_;
    public boolean selectorActivationSpeechRate_;
    public boolean selectorActivationSpokenLanguage_;
    public boolean selectorActivationVerbosity_;
    public boolean singleTapActivation_;
    public boolean soundFeedback_;
    public int soundVolumeLevel_;
    public boolean speakCollectionInfo_;
    public boolean speakElementIdWhenButtonUnlabeled_;
    public boolean speakElementType_;
    public boolean speakPhoneticLetters_;
    public boolean speakUsageHint_;
    public boolean speakWhenScreenOff_;
    public boolean talkbackMenuActivationAction_;
    public boolean talkbackMenuActivationAudioDucking_;
    public boolean talkbackMenuActivationCopyLastSpoken_;
    public boolean talkbackMenuActivationEditLabel_;
    public boolean talkbackMenuActivationEditingOption_;
    public boolean talkbackMenuActivationLink_;
    public boolean talkbackMenuActivationNavigataionCharacter_;
    public boolean talkbackMenuActivationNavigataionContainer_;
    public boolean talkbackMenuActivationNavigataionControl_;
    public boolean talkbackMenuActivationNavigataionDefault_;
    public boolean talkbackMenuActivationNavigataionHeading_;
    public boolean talkbackMenuActivationNavigataionLandmark_;
    public boolean talkbackMenuActivationNavigataionLine_;
    public boolean talkbackMenuActivationNavigataionLink_;
    public boolean talkbackMenuActivationNavigataionParagraph_;
    public boolean talkbackMenuActivationNavigataionWindow_;
    public boolean talkbackMenuActivationNavigataionWord_;
    public boolean talkbackMenuActivationNavigation_;
    public boolean talkbackMenuActivationPageNavigation_;
    public boolean talkbackMenuActivationReadFromNext_;
    public boolean talkbackMenuActivationReadFromTop_;
    public boolean talkbackMenuActivationRepeatLastSpoken_;
    public boolean talkbackMenuActivationScreenSearch_;
    public boolean talkbackMenuActivationShowHideScreen_;
    public boolean talkbackMenuActivationSoundFeedback_;
    public boolean talkbackMenuActivationSpellLastSpoken_;
    public boolean talkbackMenuActivationSpokenLanguage_;
    public boolean talkbackMenuActivationSystemActions_;
    public boolean talkbackMenuActivationTalkbackSetting_;
    public boolean talkbackMenuActivationTtsSetting_;
    public boolean talkbackMenuActivationVerbosity_;
    public boolean talkbackMenuActivationVibrationFeedback_;
    public boolean talkbackMenuActivationVoiceCommand_;
    public boolean usePitchChanges_;
    public boolean useProximitySensor_;
    public int verbosityLevel_;
    public boolean vibrationFeedback_;
    public Internal.ProtobufList gestureSettings_ = emptyProtobufList();
    public Internal.IntList keyboardShortcutSettings_ = emptyIntList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GestureShortcutAssignment extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final GestureShortcutAssignment DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public int gestureAction_;
        public int gestureShortcut_;

        static {
            GestureShortcutAssignment gestureShortcutAssignment = new GestureShortcutAssignment();
            DEFAULT_INSTANCE = gestureShortcutAssignment;
            GeneratedMessageLite.registerDefaultInstance(GestureShortcutAssignment.class, gestureShortcutAssignment);
        }

        private GestureShortcutAssignment() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"bitField0_", "gestureAction_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$6, "gestureShortcut_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$7});
                case NEW_MUTABLE_INSTANCE:
                    return new GestureShortcutAssignment();
                case NEW_BUILDER:
                    return new SystemHealthProto$PackedHistogram.Builder((float[][][]) null, (byte[]) null, (short[]) null);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (GestureShortcutAssignment.class) {
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

    static {
        TalkBackSettingsProto$TalkBackSettings talkBackSettingsProto$TalkBackSettings = new TalkBackSettingsProto$TalkBackSettings();
        DEFAULT_INSTANCE = talkBackSettingsProto$TalkBackSettings;
        GeneratedMessageLite.registerDefaultInstance(TalkBackSettingsProto$TalkBackSettings.class, talkBackSettingsProto$TalkBackSettings);
    }

    private TalkBackSettingsProto$TalkBackSettings() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001P\u0000\u0003\u0002aP\u0000\u0002\u0000\u0002᠌\u0001\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဇ\u0006\bဇ\u0007\tဇ\b\n᠌\t\u000bဇ\n\rဇ\f\u000eဇ\r\u000fဇ\u000e\u0010᠌\u000f\u0011ဇ\u0010\u0013ဇ\u0012\u0015ဇ\u0014\u0017ဇ\u0016\u001a\u001b\u001c᠌\u001a\u001d᠌\u001b\u001eࠞ\u001f᠌\u001c ဇ\u001d!ဇ\u001e\"ဇ\u001f#င $ဇ!%ဇ\"&ဇ#(ဇ%)ဇ&+ဇ(,ဇ)-ဇ*.᠌+/ဇ,0ဇ-1ဇ.2ဇ/3ဇ04ဇ15ဇ26ဇ37ဇ48ဇ59ဇ6<ဇ7=ဇ8>ဇ9?ဇ:@ဇ;Aဇ<Bဇ=Cဇ>Dဇ?Eဇ@FဇAGဇBHဇCIဇDJဇEKဇFLဇGMဇHNဇIOဇJPဇKRဇMSဇNTဇOUဇPVဇQWဇRXဇSYဇTZဇU]ဇX_ဇZ`ဇ[aဇ\\", new Object[]{"bitField0_", "bitField1_", "bitField2_", "verbosityLevel_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$16, "speakUsageHint_", "speakCollectionInfo_", "speakElementType_", "speakPhoneticLetters_", "usePitchChanges_", "speakWhenScreenOff_", "elementDescriptionOrder_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$4, "useProximitySensor_", "vibrationFeedback_", "soundFeedback_", "audioDucking_", "soundVolumeLevel_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$17, "exploreByTouch_", "singleTapActivation_", "hasCustomLabels_", "dimScreenWhenTalkbackIsEnabled_", "gestureSettings_", GestureShortcutAssignment.class, "activeKeyMap_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$10, "modifierKey_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$12, "keyboardShortcutSettings_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$9, "logOutputLevel_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$11, "displaySpeechOutput_", "enableNodeTreeDebugging_", "enablePerformanceStatistics_", "eventDumperBitMask_", "alwaysSpeakWhenScrolling_", "speakElementIdWhenButtonUnlabeled_", "passwordAlwaysSpoken_", "selectorActivationSpeechRate_", "selectorActivationVerbosity_", "selectorActivationAudioDucking_", "reduceWindowDelay_", "echoRecognizedSpeech_", "capitalLetterHandle_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$3, "selectorActivationNavigationCharacter_", "selectorActivationNavigationWord_", "selectorActivationNavigationLine_", "selectorActivationNavigationParagraph_", "selectorActivationNavigationHeading_", "selectorActivationNavigationControl_", "selectorActivationNavigationLink_", "selectorActivationNavigationLandmark_", "selectorActivationNavigationDefault_", "selectorActivationSpokenLanguage_", "selectorActivationHideScreen_", "talkbackMenuActivationAction_", "talkbackMenuActivationEditingOption_", "talkbackMenuActivationLink_", "talkbackMenuActivationPageNavigation_", "talkbackMenuActivationEditLabel_", "talkbackMenuActivationNavigation_", "talkbackMenuActivationReadFromTop_", "talkbackMenuActivationReadFromNext_", "talkbackMenuActivationCopyLastSpoken_", "talkbackMenuActivationSpellLastSpoken_", "talkbackMenuActivationRepeatLastSpoken_", "talkbackMenuActivationVerbosity_", "talkbackMenuActivationSpokenLanguage_", "talkbackMenuActivationAudioDucking_", "talkbackMenuActivationSoundFeedback_", "talkbackMenuActivationVibrationFeedback_", "talkbackMenuActivationScreenSearch_", "talkbackMenuActivationShowHideScreen_", "talkbackMenuActivationVoiceCommand_", "talkbackMenuActivationTalkbackSetting_", "talkbackMenuActivationTtsSetting_", "talkbackMenuActivationSystemActions_", "talkbackMenuActivationNavigataionCharacter_", "talkbackMenuActivationNavigataionWord_", "talkbackMenuActivationNavigataionLine_", "talkbackMenuActivationNavigataionParagraph_", "talkbackMenuActivationNavigataionHeading_", "talkbackMenuActivationNavigataionControl_", "talkbackMenuActivationNavigataionLink_", "talkbackMenuActivationNavigataionLandmark_", "talkbackMenuActivationNavigataionDefault_", "selectorActivationPunctuation_", "talkbackMenuActivationNavigataionWindow_", "talkbackMenuActivationNavigataionContainer_"});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackSettingsProto$TalkBackSettings();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((byte[]) null, (char[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackSettingsProto$TalkBackSettings.class) {
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
