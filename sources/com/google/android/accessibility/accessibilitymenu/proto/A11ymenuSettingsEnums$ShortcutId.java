package com.google.android.accessibility.accessibilitymenu.proto;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum A11ymenuSettingsEnums$ShortcutId implements Internal.EnumLite {
    UNSPECIFIED_ID(0),
    ID_ASSISTANT(1),
    ID_A11YSETTING(2),
    ID_POWER(3),
    ID_RECENT(4),
    ID_LOCKSCREEN(5),
    ID_QUICKSETTING(6),
    ID_NOTIFICATION(7),
    ID_SCREENSHOT(8),
    ID_BRIGHTNESS_UP(9),
    ID_BRIGHTNESS_DOWN(10),
    ID_VOLUME_UP(11),
    ID_VOLUME_DOWN(12);

    public final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ShortcutIdVerifier implements Internal.EnumVerifier {
        private final /* synthetic */ int switching_field;
        public static final Internal.EnumVerifier class_merging$INSTANCE$19 = new ShortcutIdVerifier(20);
        public static final Internal.EnumVerifier class_merging$INSTANCE$18 = new ShortcutIdVerifier(19);
        public static final Internal.EnumVerifier class_merging$INSTANCE$17 = new ShortcutIdVerifier(18);
        public static final Internal.EnumVerifier class_merging$INSTANCE$16 = new ShortcutIdVerifier(17);
        public static final Internal.EnumVerifier class_merging$INSTANCE$15 = new ShortcutIdVerifier(16);
        public static final Internal.EnumVerifier class_merging$INSTANCE$14 = new ShortcutIdVerifier(15);
        public static final Internal.EnumVerifier class_merging$INSTANCE$13 = new ShortcutIdVerifier(14);
        public static final Internal.EnumVerifier class_merging$INSTANCE$12 = new ShortcutIdVerifier(13);
        public static final Internal.EnumVerifier class_merging$INSTANCE$11 = new ShortcutIdVerifier(12);
        public static final Internal.EnumVerifier class_merging$INSTANCE$10 = new ShortcutIdVerifier(11);
        public static final Internal.EnumVerifier class_merging$INSTANCE$9 = new ShortcutIdVerifier(10);
        public static final Internal.EnumVerifier class_merging$INSTANCE$8 = new ShortcutIdVerifier(9);
        public static final Internal.EnumVerifier class_merging$INSTANCE$7 = new ShortcutIdVerifier(8);
        public static final Internal.EnumVerifier class_merging$INSTANCE$6 = new ShortcutIdVerifier(7);
        public static final Internal.EnumVerifier class_merging$INSTANCE$5 = new ShortcutIdVerifier(6);
        public static final Internal.EnumVerifier class_merging$INSTANCE$4 = new ShortcutIdVerifier(5);
        public static final Internal.EnumVerifier class_merging$INSTANCE$3 = new ShortcutIdVerifier(4);
        public static final Internal.EnumVerifier class_merging$INSTANCE$2 = new ShortcutIdVerifier(3);
        public static final Internal.EnumVerifier class_merging$INSTANCE$1 = new ShortcutIdVerifier(2);
        public static final Internal.EnumVerifier class_merging$INSTANCE = new ShortcutIdVerifier(1);
        public static final Internal.EnumVerifier INSTANCE = new ShortcutIdVerifier(0);

        private ShortcutIdVerifier(int i) {
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
                int r4 = com.google.android.accessibility.talkback.analytics.ShortcutActionsEnums$ShortcutActions.forNumber$ar$edu$398a6427_0(r4)
                if (r4 == 0) goto Lad
                return r1
            Le:
                int r4 = com.google.android.accessibility.talkback.analytics.SelectorItemEnums$SelectorItem.forNumber$ar$edu$23eba87_0(r4)
                if (r4 == 0) goto L15
                return r1
            L15:
                return r2
            L16:
                int r4 = com.google.android.accessibility.talkback.analytics.GranularityEnums$Granularity.forNumber$ar$edu$ff4738dc_0(r4)
                if (r4 == 0) goto L1d
                return r1
            L1d:
                return r2
            L1e:
                int r4 = com.google.android.accessibility.talkback.analytics.AccessibilityActionEnums$ActionType.forNumber$ar$edu$94576bc0_0(r4)
                if (r4 == 0) goto L25
                return r1
            L25:
                return r2
            L26:
                int r4 = com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SSettings.forNumber$ar$edu$8845a5ec_0(r4)
                if (r4 == 0) goto L2d
                return r1
            L2d:
                return r2
            L2e:
                int r4 = com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SEntryPoint.forNumber$ar$edu$70dcef61_0(r4)
                if (r4 == 0) goto L35
                return r1
            L35:
                return r2
            L36:
                int r4 = com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions.forNumber$ar$edu$a250b3f5_0(r4)
                if (r4 == 0) goto L3d
                return r1
            L3d:
                return r2
            L3e:
                int r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$TutorialFinishState.forNumber$ar$edu$567b72a8_0(r4)
                if (r4 == 0) goto L45
                return r1
            L45:
                return r2
            L46:
                com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$Translator r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$Translator.forNumber(r4)
                if (r4 == 0) goto L4d
                return r1
            L4d:
                return r2
            L4e:
                int r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$State.forNumber$ar$edu$1523a66e_0(r4)
                if (r4 == 0) goto L55
                return r1
            L55:
                return r2
            L56:
                com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$SettingsEvent r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$SettingsEvent.forNumber(r4)
                if (r4 == 0) goto L5d
                return r1
            L5d:
                return r2
            L5e:
                com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$LayoutCode r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$LayoutCode.forNumber(r4)
                if (r4 == 0) goto L65
                return r1
            L65:
                return r2
            L66:
                com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$LanguageCode r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$LanguageCode.forNumber(r4)
                if (r4 == 0) goto L6d
                return r1
            L6d:
                return r2
            L6e:
                com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$GestureActionType r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$GestureActionType.forNumber(r4)
                if (r4 == 0) goto L75
                return r1
            L75:
                return r2
            L76:
                int r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$ErrorType.forNumber$ar$edu$1bdadeea_0(r4)
                if (r4 == 0) goto L7d
                return r1
            L7d:
                return r2
            L7e:
                com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$ContextMenuOption r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$ContextMenuOption.forNumber(r4)
                if (r4 == 0) goto L85
                return r1
            L85:
                return r2
            L86:
                int r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationType.forNumber$ar$edu$1d442578_0(r4)
                if (r4 == 0) goto L8d
                return r1
            L8d:
                return r2
            L8e:
                int r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationState.forNumber$ar$edu$425b3e52_0(r4)
                if (r4 == 0) goto L95
                return r1
            L95:
                return r2
            L96:
                int r4 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$BrailleType.forNumber$ar$edu$a7370849_0(r4)
                if (r4 == 0) goto L9d
                return r1
            L9d:
                return r2
            L9e:
                int r4 = com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$A11ymenuSettings.forNumber$ar$edu$8db3b02f_0(r4)
                if (r4 == 0) goto La5
                return r1
            La5:
                return r2
            La6:
                com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId r4 = com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId.forNumber(r4)
                if (r4 == 0) goto Lad
                return r1
            Lad:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$ShortcutId.ShortcutIdVerifier.isInRange(int):boolean");
        }
    }

    A11ymenuSettingsEnums$ShortcutId(int i) {
        this.value = i;
    }

    public static A11ymenuSettingsEnums$ShortcutId forNumber(int i) {
        switch (i) {
            case 0:
                return UNSPECIFIED_ID;
            case 1:
                return ID_ASSISTANT;
            case 2:
                return ID_A11YSETTING;
            case 3:
                return ID_POWER;
            case 4:
                return ID_RECENT;
            case 5:
                return ID_LOCKSCREEN;
            case 6:
                return ID_QUICKSETTING;
            case 7:
                return ID_NOTIFICATION;
            case 8:
                return ID_SCREENSHOT;
            case 9:
                return ID_BRIGHTNESS_UP;
            case 10:
                return ID_BRIGHTNESS_DOWN;
            case 11:
                return ID_VOLUME_UP;
            case 12:
                return ID_VOLUME_DOWN;
            default:
                return null;
        }
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.value);
    }
}
