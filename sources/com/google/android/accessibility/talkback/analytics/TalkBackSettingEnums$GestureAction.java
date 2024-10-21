package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$GestureAction implements Internal.EnumLite {
    GESTURE_SWIPE_UP(0),
    GESTURE_SWIPE_DOWN(1),
    GESTURE_SWIPE_LEFT(2),
    GESTURE_SWIPE_RIGHT(3),
    GESTURE_SWIPE_UP_DOWN(4),
    GESTURE_SWIPE_DOWN_UP(5),
    GESTURE_SWIPE_LEFT_RIGHT(6),
    GESTURE_SWIPE_RIGHT_LEFT(7),
    GESTURE_SWIPE_UP_LEFT(8),
    GESTURE_SWIPE_UP_RIGHT(9),
    GESTURE_SWIPE_DOWN_LEFT(10),
    GESTURE_SWIPE_DOWN_RIGHT(11),
    GESTURE_SWIPE_LEFT_UP(12),
    GESTURE_SWIPE_LEFT_DOWN(13),
    GESTURE_SWIPE_RIGHT_UP(14),
    GESTURE_SWIPE_RIGHT_DOWN(15),
    GESTURE_FINGERPRINT_SWIPE_UP(16),
    GESTURE_FINGERPRINT_SWIPE_DOWN(17),
    GESTURE_FINGERPRINT_SWIPE_LEFT(18),
    GESTURE_FINGERPRINT_SWIPE_RIGHT(19),
    GESTURE_SINGLE_SIDE_TAP(20),
    GESTURE_DOUBLE_SIDE_TAP(21),
    GESTURE_TWO_FINGER_TAP(30),
    GESTURE_TWO_FINGER_DOUBLE_TAP(31),
    GESTURE_TWO_FINGER_DOUBLE_TAP_HOLD(32),
    GESTURE_TWO_FINGER_TRIPLE_TAP(33),
    GESTURE_TWO_FINGER_SWIPE_UP(34),
    GESTURE_TWO_FINGER_SWIPE_DOWN(35),
    GESTURE_TWO_FINGER_SWIPE_LEFT(36),
    GESTURE_TWO_FINGER_SWIPE_RIGHT(37),
    GESTURE_TWO_FINGER_TRIPLE_TAP_HOLD(38),
    GESTURE_THREE_FINGER_TAP(40),
    GESTURE_THREE_FINGER_DOUBLE_TAP(41),
    GESTURE_THREE_FINGER_DOUBLE_TAP_HOLD(42),
    GESTURE_THREE_FINGER_TRIPLE_TAP(43),
    GESTURE_THREE_FINGER_SWIPE_UP(44),
    GESTURE_THREE_FINGER_SWIPE_DOWN(45),
    GESTURE_THREE_FINGER_SWIPE_LEFT(46),
    GESTURE_THREE_FINGER_SWIPE_RIGHT(47),
    GESTURE_THREE_FINGER_TAP_HOLD(48),
    GESTURE_THREE_FINGER_TRIPLE_TAP_HOLD(49),
    GESTURE_FOUR_FINGER_TAP(50),
    GESTURE_FOUR_FINGER_DOUBLE_TAP(51),
    GESTURE_FOUR_FINGER_DOUBLE_TAP_HOLD(52),
    GESTURE_FOUR_FINGER_TRIPLE_TAP(53),
    GESTURE_FOUR_FINGER_SWIPE_UP(54),
    GESTURE_FOUR_FINGER_SWIPE_DOWN(55),
    GESTURE_FOUR_FINGER_SWIPE_LEFT(56),
    GESTURE_FOUR_FINGER_SWIPE_RIGHT(57);

    public final int value;

    TalkBackSettingEnums$GestureAction(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$GestureAction forNumber(int i) {
        switch (i) {
            case 0:
                return GESTURE_SWIPE_UP;
            case 1:
                return GESTURE_SWIPE_DOWN;
            case 2:
                return GESTURE_SWIPE_LEFT;
            case 3:
                return GESTURE_SWIPE_RIGHT;
            case 4:
                return GESTURE_SWIPE_UP_DOWN;
            case 5:
                return GESTURE_SWIPE_DOWN_UP;
            case 6:
                return GESTURE_SWIPE_LEFT_RIGHT;
            case 7:
                return GESTURE_SWIPE_RIGHT_LEFT;
            case 8:
                return GESTURE_SWIPE_UP_LEFT;
            case 9:
                return GESTURE_SWIPE_UP_RIGHT;
            case 10:
                return GESTURE_SWIPE_DOWN_LEFT;
            case 11:
                return GESTURE_SWIPE_DOWN_RIGHT;
            case 12:
                return GESTURE_SWIPE_LEFT_UP;
            case 13:
                return GESTURE_SWIPE_LEFT_DOWN;
            case 14:
                return GESTURE_SWIPE_RIGHT_UP;
            case 15:
                return GESTURE_SWIPE_RIGHT_DOWN;
            case 16:
                return GESTURE_FINGERPRINT_SWIPE_UP;
            case 17:
                return GESTURE_FINGERPRINT_SWIPE_DOWN;
            case 18:
                return GESTURE_FINGERPRINT_SWIPE_LEFT;
            case 19:
                return GESTURE_FINGERPRINT_SWIPE_RIGHT;
            case 20:
                return GESTURE_SINGLE_SIDE_TAP;
            case 21:
                return GESTURE_DOUBLE_SIDE_TAP;
            default:
                switch (i) {
                    case 30:
                        return GESTURE_TWO_FINGER_TAP;
                    case 31:
                        return GESTURE_TWO_FINGER_DOUBLE_TAP;
                    case 32:
                        return GESTURE_TWO_FINGER_DOUBLE_TAP_HOLD;
                    case 33:
                        return GESTURE_TWO_FINGER_TRIPLE_TAP;
                    case 34:
                        return GESTURE_TWO_FINGER_SWIPE_UP;
                    case 35:
                        return GESTURE_TWO_FINGER_SWIPE_DOWN;
                    case 36:
                        return GESTURE_TWO_FINGER_SWIPE_LEFT;
                    case 37:
                        return GESTURE_TWO_FINGER_SWIPE_RIGHT;
                    case 38:
                        return GESTURE_TWO_FINGER_TRIPLE_TAP_HOLD;
                    default:
                        switch (i) {
                            case 40:
                                return GESTURE_THREE_FINGER_TAP;
                            case 41:
                                return GESTURE_THREE_FINGER_DOUBLE_TAP;
                            case 42:
                                return GESTURE_THREE_FINGER_DOUBLE_TAP_HOLD;
                            case 43:
                                return GESTURE_THREE_FINGER_TRIPLE_TAP;
                            case 44:
                                return GESTURE_THREE_FINGER_SWIPE_UP;
                            case 45:
                                return GESTURE_THREE_FINGER_SWIPE_DOWN;
                            case 46:
                                return GESTURE_THREE_FINGER_SWIPE_LEFT;
                            case 47:
                                return GESTURE_THREE_FINGER_SWIPE_RIGHT;
                            case 48:
                                return GESTURE_THREE_FINGER_TAP_HOLD;
                            case 49:
                                return GESTURE_THREE_FINGER_TRIPLE_TAP_HOLD;
                            case 50:
                                return GESTURE_FOUR_FINGER_TAP;
                            case 51:
                                return GESTURE_FOUR_FINGER_DOUBLE_TAP;
                            case 52:
                                return GESTURE_FOUR_FINGER_DOUBLE_TAP_HOLD;
                            case 53:
                                return GESTURE_FOUR_FINGER_TRIPLE_TAP;
                            case 54:
                                return GESTURE_FOUR_FINGER_SWIPE_UP;
                            case 55:
                                return GESTURE_FOUR_FINGER_SWIPE_DOWN;
                            case 56:
                                return GESTURE_FOUR_FINGER_SWIPE_LEFT;
                            case 57:
                                return GESTURE_FOUR_FINGER_SWIPE_RIGHT;
                            default:
                                return null;
                        }
                }
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
