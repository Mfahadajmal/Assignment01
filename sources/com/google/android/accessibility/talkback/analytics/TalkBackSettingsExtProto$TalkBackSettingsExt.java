package com.google.android.accessibility.talkback.analytics;

import com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackSettingsExtProto$TalkBackSettingsExt extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final TalkBackSettingsExtProto$TalkBackSettingsExt DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public boolean autoDescribeIconsForUnlabelledImagesOnly_;
    public boolean autoDescribeIcons_;
    public boolean autoDescribeImagesForUnlabelledImagesOnly_;
    public boolean autoDescribeImages_;
    public boolean autoDescribeTextForUnlabelledImagesOnly_;
    public boolean autoDescribeText_;
    public int bitField0_;
    public boolean detailedDescribeImages_;
    public boolean developerDiagnosisMode_;
    public boolean developerEnableDebugLogOverlay_;
    public boolean developerHandleGestureInTalkback_;
    public int focusIndicatorColor_;
    public boolean focusIndicatorThickBorders_;
    public boolean geminiAutoDescribeImagesForUnlabelledImagesOnly_;
    public boolean geminiAutoDescribeImages_;
    public boolean limitFrequentContentChangeAnnouncement_;
    public int physicalKeyboardEcho_;
    public int punctuationVerbosity_;
    public boolean selectorActivationAccessibilityVolume_;
    public boolean selectorActivationNavigationContainer_;
    public boolean selectorActivationNavigationWindow_;
    public boolean selectorActivationScroll_;
    public boolean speakWindowNames_;
    public boolean talkbackMenuActivationBrailleDisplay_;
    public boolean talkbackMenuActivationDescribeImage_;
    public int typingLongClickInterval_;
    public int typingPreference_;
    public int virtualKeyboardEcho_;

    static {
        TalkBackSettingsExtProto$TalkBackSettingsExt talkBackSettingsExtProto$TalkBackSettingsExt = new TalkBackSettingsExtProto$TalkBackSettingsExt();
        DEFAULT_INSTANCE = talkBackSettingsExtProto$TalkBackSettingsExt;
        GeneratedMessageLite.registerDefaultInstance(TalkBackSettingsExtProto$TalkBackSettingsExt.class, talkBackSettingsExtProto$TalkBackSettingsExt);
    }

    private TalkBackSettingsExtProto$TalkBackSettingsExt() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                Internal.EnumVerifier enumVerifier = TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$8;
                return newMessageInfo(DEFAULT_INSTANCE, "\u0004\u001b\u0000\u0001\u0001S\u001b\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005᠌\u0004\u0006ဇ\u0005\u0007᠌\u0006\b᠌\u0007\tဇ\b\nဇ\t\u000bဇ\n\f᠌\u000b\rဇ\f\u000eဇ\r\u000fဇ\u000e\u0010ဇ\u000f\u0011ဇ\u0010\u0012ဇ\u00113ဇ\u00124ဇ\u0013=ဇ\u0014>ဇ\u0015?ဇ\u0016@ဇ\u0017Qဇ\u0018Rဇ\u0019Sဇ\u001a", new Object[]{"bitField0_", "virtualKeyboardEcho_", enumVerifier, "physicalKeyboardEcho_", enumVerifier, "speakWindowNames_", "focusIndicatorThickBorders_", "focusIndicatorColor_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$5, "autoDescribeIcons_", "typingPreference_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$15, "typingLongClickInterval_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$14, "limitFrequentContentChangeAnnouncement_", "autoDescribeImages_", "autoDescribeText_", "punctuationVerbosity_", TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.class_merging$INSTANCE$13, "detailedDescribeImages_", "autoDescribeIconsForUnlabelledImagesOnly_", "autoDescribeImagesForUnlabelledImagesOnly_", "autoDescribeTextForUnlabelledImagesOnly_", "geminiAutoDescribeImages_", "geminiAutoDescribeImagesForUnlabelledImagesOnly_", "talkbackMenuActivationDescribeImage_", "talkbackMenuActivationBrailleDisplay_", "selectorActivationNavigationWindow_", "selectorActivationScroll_", "selectorActivationAccessibilityVolume_", "selectorActivationNavigationContainer_", "developerDiagnosisMode_", "developerHandleGestureInTalkback_", "developerEnableDebugLogOverlay_"});
            case NEW_MUTABLE_INSTANCE:
                return new TalkBackSettingsExtProto$TalkBackSettingsExt();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((boolean[][][]) null, (byte[]) null, (short[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TalkBackSettingsExtProto$TalkBackSettingsExt.class) {
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
