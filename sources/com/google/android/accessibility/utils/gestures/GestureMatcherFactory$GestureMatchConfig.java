package com.google.android.accessibility.utils.gestures;

/* compiled from: PG */
/* loaded from: classes.dex */
enum GestureMatcherFactory$GestureMatchConfig {
    MAPPER_GESTURE_DOUBLE_TAP(17, 1, 2, -1, -1),
    MAPPER_GESTURE_DOUBLE_TAP_AND_HOLD(18, 1, 2, true, -1, -1),
    MAPPER_GESTURE_FAKED_SPLIT_TYPING(-3, 1, 1, true, -1, -1),
    MAPPER_GESTURE_SWIPE_RIGHT(4, 1, 0, 1, -1),
    MAPPER_GESTURE_SWIPE_LEFT(3, 1, 0, 0, -1),
    MAPPER_GESTURE_SWIPE_UP(1, 1, 0, 2, -1),
    MAPPER_GESTURE_SWIPE_DOWN(2, 1, 0, 3, -1),
    MAPPER_GESTURE_SWIPE_LEFT_AND_RIGHT(5, 1, 0, 0, 1),
    MAPPER_GESTURE_SWIPE_LEFT_AND_UP(9, 1, 0, 0, 2),
    MAPPER_GESTURE_SWIPE_LEFT_AND_DOWN(10, 1, 0, 0, 3),
    MAPPER_GESTURE_SWIPE_RIGHT_AND_UP(11, 1, 0, 1, 2),
    MAPPER_GESTURE_SWIPE_RIGHT_AND_DOWN(12, 1, 0, 1, 3),
    MAPPER_GESTURE_SWIPE_RIGHT_AND_LEFT(6, 1, 0, 1, 0),
    MAPPER_GESTURE_SWIPE_DOWN_AND_UP(8, 1, 0, 3, 2),
    MAPPER_GESTURE_SWIPE_DOWN_AND_LEFT(15, 1, 0, 3, 0),
    MAPPER_GESTURE_SWIPE_DOWN_AND_RIGHT(16, 1, 0, 3, 1),
    MAPPER_GESTURE_SWIPE_UP_AND_DOWN(7, 1, 0, 2, 3),
    MAPPER_GESTURE_SWIPE_UP_AND_LEFT(13, 1, 0, 2, 0),
    MAPPER_GESTURE_SWIPE_UP_AND_RIGHT(14, 1, 0, 2, 1),
    MAPPER_GESTURE_2_FINGER_SINGLE_TAP(19, 2, 1, -1, -1),
    MAPPER_GESTURE_2_FINGER_DOUBLE_TAP(20, 2, 2, -1, -1),
    MAPPER_GESTURE_2_FINGER_DOUBLE_TAP_AND_HOLD(40, 2, 2, true, -1, -1),
    MAPPER_GESTURE_2_FINGER_TRIPLE_TAP(21, 2, 3, -1, -1),
    MAPPER_GESTURE_2_FINGER_TRIPLE_TAP_AND_HOLD(43, 2, 3, true, -1, -1),
    MAPPER_GESTURE_3_FINGER_SINGLE_TAP(22, 3, 1, -1, -1),
    MAPPER_GESTURE_3_FINGER_DOUBLE_TAP(23, 3, 2, -1, -1),
    MAPPER_GESTURE_3_FINGER_SINGLE_TAP_AND_HOLD(44, 3, 1, true, -1, -1),
    MAPPER_GESTURE_3_FINGER_DOUBLE_TAP_AND_HOLD(41, 3, 2, true, -1, -1),
    MAPPER_GESTURE_3_FINGER_TRIPLE_TAP(24, 3, 3, -1, -1),
    MAPPER_GESTURE_3_FINGER_TRIPLE_TAP_AND_HOLD(45, 3, 3, true, -1, -1),
    MAPPER_GESTURE_4_FINGER_SINGLE_TAP(37, 4, 1, -1, -1),
    MAPPER_GESTURE_4_FINGER_DOUBLE_TAP(38, 4, 2, -1, -1),
    MAPPER_GESTURE_4_FINGER_DOUBLE_TAP_AND_HOLD(42, 4, 2, true, -1, -1),
    MAPPER_GESTURE_4_FINGER_TRIPLE_TAP(39, 4, 3, -1, -1),
    MAPPER_GESTURE_2_FINGER_SWIPE_DOWN(26, 2, 0, 3, -1),
    MAPPER_GESTURE_2_FINGER_SWIPE_LEFT(27, 2, 0, 0, -1),
    MAPPER_GESTURE_2_FINGER_SWIPE_RIGHT(28, 2, 0, 1, -1),
    MAPPER_GESTURE_2_FINGER_SWIPE_UP(25, 2, 0, 2, -1),
    MAPPER_GESTURE_3_FINGER_SWIPE_DOWN(30, 3, 0, 3, -1),
    MAPPER_GESTURE_3_FINGER_SWIPE_LEFT(31, 3, 0, 0, -1),
    MAPPER_GESTURE_3_FINGER_SWIPE_RIGHT(32, 3, 0, 1, -1),
    MAPPER_GESTURE_3_FINGER_SWIPE_UP(29, 3, 0, 2, -1),
    MAPPER_GESTURE_4_FINGER_SWIPE_DOWN(34, 4, 0, 3, -1),
    MAPPER_GESTURE_4_FINGER_SWIPE_LEFT(35, 4, 0, 0, -1),
    MAPPER_GESTURE_4_FINGER_SWIPE_RIGHT(36, 4, 0, 1, -1),
    MAPPER_GESTURE_4_FINGER_SWIPE_UP(33, 4, 0, 2, -1);

    final int direction1;
    final int direction2;
    final int finger;
    final int gestureId;
    final boolean isHold;
    final int tap;

    GestureMatcherFactory$GestureMatchConfig(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3, false, i4, i5);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "GestureMatchConfig{gestureId=" + this.gestureId + ", finger=" + this.finger + ", tap=" + this.tap + ", isHold=" + this.isHold + ", direction1=" + this.direction1 + ", direction2=" + this.direction2 + "}";
    }

    GestureMatcherFactory$GestureMatchConfig(int i, int i2, int i3, boolean z, int i4, int i5) {
        this.gestureId = i;
        this.finger = i2;
        this.tap = i3;
        this.isHold = z;
        this.direction1 = i4;
        this.direction2 = i5;
    }
}
