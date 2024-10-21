package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackGeminiConstant$GeminiFailReason {
    public static final int GEMINI_FAIL_REASON_UNSPECIFIED$ar$edu = 1;
    public static final int GEMINI_FAIL_REASON_APIKEY_NOT_AVAILABLE$ar$edu = 2;
    public static final int GEMINI_FAIL_REASON_USER_NOT_OPT_IN$ar$edu = 3;
    public static final int GEMINI_FAIL_REASON_INTERNET_UNREACHABLE$ar$edu = 4;
    public static final int GEMINI_FAIL_REASON_NO_SCREENSHOT_PROVIDED$ar$edu = 5;
    public static final int GEMINI_FAIL_REASON_COMMAND_NOT_PROVIDED$ar$edu = 6;
    public static final int GEMINI_FAIL_REASON_FAIL_TO_ENCODE_PICTURE$ar$edu = 7;
    public static final int GEMINI_FAIL_REASON_FAIL_TO_PARSE_RESPONSE$ar$edu = 8;
    public static final int GEMINI_FAIL_REASON_CONTENT_BLOCKED$ar$edu = 9;
    public static final int GEMINI_FAIL_REASON_PROTOCOL_ERROR$ar$edu = 10;
    public static final int GEMINI_FAIL_REASON_USER_ABORT$ar$edu = 11;
    public static final int GEMINI_FAIL_REASON_SERVICE_UNAVAILABLE$ar$edu = 12;
    private static final /* synthetic */ int[] $VALUES$ar$edu$7ec810fb_0 = {GEMINI_FAIL_REASON_UNSPECIFIED$ar$edu, GEMINI_FAIL_REASON_APIKEY_NOT_AVAILABLE$ar$edu, GEMINI_FAIL_REASON_USER_NOT_OPT_IN$ar$edu, GEMINI_FAIL_REASON_INTERNET_UNREACHABLE$ar$edu, GEMINI_FAIL_REASON_NO_SCREENSHOT_PROVIDED$ar$edu, GEMINI_FAIL_REASON_COMMAND_NOT_PROVIDED$ar$edu, GEMINI_FAIL_REASON_FAIL_TO_ENCODE_PICTURE$ar$edu, GEMINI_FAIL_REASON_FAIL_TO_PARSE_RESPONSE$ar$edu, GEMINI_FAIL_REASON_CONTENT_BLOCKED$ar$edu, GEMINI_FAIL_REASON_PROTOCOL_ERROR$ar$edu, GEMINI_FAIL_REASON_USER_ABORT$ar$edu, GEMINI_FAIL_REASON_SERVICE_UNAVAILABLE$ar$edu};

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class GeminiFailReasonVerifier implements Internal.EnumVerifier {
        private final /* synthetic */ int switching_field;
        static final Internal.EnumVerifier class_merging$INSTANCE$19 = new GeminiFailReasonVerifier(20);
        static final Internal.EnumVerifier class_merging$INSTANCE$18 = new GeminiFailReasonVerifier(19);
        static final Internal.EnumVerifier class_merging$INSTANCE$17 = new GeminiFailReasonVerifier(18);
        static final Internal.EnumVerifier class_merging$INSTANCE$16 = new GeminiFailReasonVerifier(17);
        static final Internal.EnumVerifier class_merging$INSTANCE$15 = new GeminiFailReasonVerifier(16);
        static final Internal.EnumVerifier class_merging$INSTANCE$14 = new GeminiFailReasonVerifier(15);
        static final Internal.EnumVerifier class_merging$INSTANCE$13 = new GeminiFailReasonVerifier(14);
        static final Internal.EnumVerifier class_merging$INSTANCE$12 = new GeminiFailReasonVerifier(13);
        static final Internal.EnumVerifier class_merging$INSTANCE$11 = new GeminiFailReasonVerifier(12);
        static final Internal.EnumVerifier class_merging$INSTANCE$10 = new GeminiFailReasonVerifier(11);
        static final Internal.EnumVerifier class_merging$INSTANCE$9 = new GeminiFailReasonVerifier(10);
        static final Internal.EnumVerifier class_merging$INSTANCE$8 = new GeminiFailReasonVerifier(9);
        static final Internal.EnumVerifier class_merging$INSTANCE$7 = new GeminiFailReasonVerifier(8);
        static final Internal.EnumVerifier class_merging$INSTANCE$6 = new GeminiFailReasonVerifier(7);
        static final Internal.EnumVerifier class_merging$INSTANCE$5 = new GeminiFailReasonVerifier(6);
        static final Internal.EnumVerifier class_merging$INSTANCE$4 = new GeminiFailReasonVerifier(5);
        static final Internal.EnumVerifier class_merging$INSTANCE$3 = new GeminiFailReasonVerifier(4);
        static final Internal.EnumVerifier class_merging$INSTANCE$2 = new GeminiFailReasonVerifier(3);
        static final Internal.EnumVerifier class_merging$INSTANCE$1 = new GeminiFailReasonVerifier(2);
        static final Internal.EnumVerifier class_merging$INSTANCE = new GeminiFailReasonVerifier(1);
        static final Internal.EnumVerifier INSTANCE = new GeminiFailReasonVerifier(0);

        private GeminiFailReasonVerifier(int i) {
            this.switching_field = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x00ad A[RETURN] */
        @Override // com.google.protobuf.Internal.EnumVerifier
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean isInRange(int r4) {
            /*
                r3 = this;
                int r0 = r3.switching_field
                r1 = 1
                r2 = 0
                switch(r0) {
                    case 0: goto La6;
                    case 1: goto L9e;
                    case 2: goto L96;
                    case 3: goto L8e;
                    case 4: goto L86;
                    case 5: goto L7e;
                    case 6: goto L76;
                    case 7: goto L6e;
                    case 8: goto L66;
                    case 9: goto L5e;
                    case 10: goto L56;
                    case 11: goto L4e;
                    case 12: goto L46;
                    case 13: goto L3e;
                    case 14: goto L36;
                    case 15: goto L2e;
                    case 16: goto L26;
                    case 17: goto L1e;
                    case 18: goto L16;
                    case 19: goto Le;
                    default: goto L7;
                }
            L7:
                int r4 = com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingType.forNumber$ar$edu$145d02fd_0(r4)
                if (r4 == 0) goto Lad
                return r1
            Le:
                int r4 = com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId.forNumber$ar$edu$745ffd44_0(r4)
                if (r4 == 0) goto L15
                return r1
            L15:
                return r2
            L16:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$VolumeLevel r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$VolumeLevel.forNumber(r4)
                if (r4 == 0) goto L1d
                return r1
            L1d:
                return r2
            L1e:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$VerbosityLevel r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$VerbosityLevel.forNumber(r4)
                if (r4 == 0) goto L25
                return r1
            L25:
                return r2
            L26:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$TypingPreference r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$TypingPreference.forNumber(r4)
                if (r4 == 0) goto L2d
                return r1
            L2d:
                return r2
            L2e:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$TypingLongClickInterval r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$TypingLongClickInterval.forNumber(r4)
                if (r4 == 0) goto L35
                return r1
            L35:
                return r2
            L36:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$PunctuationVerbosity r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$PunctuationVerbosity.forNumber(r4)
                if (r4 == 0) goto L3d
                return r1
            L3d:
                return r2
            L3e:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$ModifierKey r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$ModifierKey.forNumber(r4)
                if (r4 == 0) goto L45
                return r1
            L45:
                return r2
            L46:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$LogOutputLevel r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$LogOutputLevel.forNumber(r4)
                if (r4 == 0) goto L4d
                return r1
            L4d:
                return r2
            L4e:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeymapType r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeymapType.forNumber(r4)
                if (r4 == 0) goto L55
                return r1
            L55:
                return r2
            L56:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeyboardShortcut r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeyboardShortcut.forNumber(r4)
                if (r4 == 0) goto L5d
                return r1
            L5d:
                return r2
            L5e:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeyboardEchoExt r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$KeyboardEchoExt.forNumber(r4)
                if (r4 == 0) goto L65
                return r1
            L65:
                return r2
            L66:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$GestureShortcut r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$GestureShortcut.forNumber(r4)
                if (r4 == 0) goto L6d
                return r1
            L6d:
                return r2
            L6e:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$GestureAction r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$GestureAction.forNumber(r4)
                if (r4 == 0) goto L75
                return r1
            L75:
                return r2
            L76:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$FocusIndicatorColor r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$FocusIndicatorColor.forNumber(r4)
                if (r4 == 0) goto L7d
                return r1
            L7d:
                return r2
            L7e:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$DescriptionOrder r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$DescriptionOrder.forNumber(r4)
                if (r4 == 0) goto L85
                return r1
            L85:
                return r2
            L86:
                com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$CapitalLetterHandle r4 = com.google.android.accessibility.talkback.analytics.TalkBackSettingEnums$CapitalLetterHandle.forNumber(r4)
                if (r4 == 0) goto L8d
                return r1
            L8d:
                return r2
            L8e:
                int r4 = com.google.android.accessibility.talkback.analytics.TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType.forNumber$ar$edu$891bf696_0(r4)
                if (r4 == 0) goto L95
                return r1
            L95:
                return r2
            L96:
                int r4 = com.google.android.accessibility.talkback.analytics.TalkBackGestureIdEnums$TalkBackGesturesId.forNumber$ar$edu$71a891d0_0(r4)
                if (r4 == 0) goto L9d
                return r1
            L9d:
                return r2
            L9e:
                int r4 = com.google.android.accessibility.talkback.analytics.TalkBackContextMenuActions$ContextMenuAction.forNumber$ar$edu$1e7c3a14_0(r4)
                if (r4 == 0) goto La5
                return r1
            La5:
                return r2
            La6:
                int r4 = com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason.forNumber$ar$edu$21c4e01d_0(r4)
                if (r4 == 0) goto Lad
                return r1
            Lad:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.analytics.TalkBackGeminiConstant$GeminiFailReason.GeminiFailReasonVerifier.isInRange(int):boolean");
        }
    }

    public static int forNumber$ar$edu$21c4e01d_0(int i) {
        switch (i) {
            case 0:
                return GEMINI_FAIL_REASON_UNSPECIFIED$ar$edu;
            case 1:
                return GEMINI_FAIL_REASON_APIKEY_NOT_AVAILABLE$ar$edu;
            case 2:
                return GEMINI_FAIL_REASON_USER_NOT_OPT_IN$ar$edu;
            case 3:
                return GEMINI_FAIL_REASON_INTERNET_UNREACHABLE$ar$edu;
            case 4:
                return GEMINI_FAIL_REASON_NO_SCREENSHOT_PROVIDED$ar$edu;
            case 5:
                return GEMINI_FAIL_REASON_COMMAND_NOT_PROVIDED$ar$edu;
            case 6:
                return GEMINI_FAIL_REASON_FAIL_TO_ENCODE_PICTURE$ar$edu;
            case 7:
                return GEMINI_FAIL_REASON_FAIL_TO_PARSE_RESPONSE$ar$edu;
            case 8:
                return GEMINI_FAIL_REASON_CONTENT_BLOCKED$ar$edu;
            case 9:
                return GEMINI_FAIL_REASON_PROTOCOL_ERROR$ar$edu;
            case 10:
                return GEMINI_FAIL_REASON_USER_ABORT$ar$edu;
            case 11:
                return GEMINI_FAIL_REASON_SERVICE_UNAVAILABLE$ar$edu;
            default:
                return 0;
        }
    }

    public static int[] values$ar$edu$926cb7f6_0() {
        return new int[]{GEMINI_FAIL_REASON_UNSPECIFIED$ar$edu, GEMINI_FAIL_REASON_APIKEY_NOT_AVAILABLE$ar$edu, GEMINI_FAIL_REASON_USER_NOT_OPT_IN$ar$edu, GEMINI_FAIL_REASON_INTERNET_UNREACHABLE$ar$edu, GEMINI_FAIL_REASON_NO_SCREENSHOT_PROVIDED$ar$edu, GEMINI_FAIL_REASON_COMMAND_NOT_PROVIDED$ar$edu, GEMINI_FAIL_REASON_FAIL_TO_ENCODE_PICTURE$ar$edu, GEMINI_FAIL_REASON_FAIL_TO_PARSE_RESPONSE$ar$edu, GEMINI_FAIL_REASON_CONTENT_BLOCKED$ar$edu, GEMINI_FAIL_REASON_PROTOCOL_ERROR$ar$edu, GEMINI_FAIL_REASON_USER_ABORT$ar$edu, GEMINI_FAIL_REASON_SERVICE_UNAVAILABLE$ar$edu};
    }
}
