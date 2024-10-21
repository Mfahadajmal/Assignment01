package com.google.android.accessibility.brailleime;

import com.google.android.accessibility.brailleime.input.Swipe;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeGesture {
    UNASSIGNED_GESTURE(1, null, -1),
    SWIPE_UP_ONE_FINGER(2, Swipe.Direction.UP, 1),
    SWIPE_DOWN_ONE_FINGER(2, Swipe.Direction.DOWN, 1),
    SWIPE_LEFT_ONE_FINGER(2, Swipe.Direction.LEFT, 1),
    SWIPE_RIGHT_ONE_FINGER(2, Swipe.Direction.RIGHT, 1),
    SWIPE_UP_TWO_FINGERS(2, Swipe.Direction.UP, 2),
    SWIPE_DOWN_TWO_FINGERS(2, Swipe.Direction.DOWN, 2),
    SWIPE_LEFT_TWO_FINGERS(2, Swipe.Direction.LEFT, 2),
    SWIPE_RIGHT_TWO_FINGERS(2, Swipe.Direction.RIGHT, 2),
    SWIPE_UP_THREE_FINGERS(2, Swipe.Direction.UP, 3),
    SWIPE_DOWN_THREE_FINGERS(2, Swipe.Direction.DOWN, 3),
    SWIPE_LEFT_THREE_FINGERS(2, Swipe.Direction.LEFT, 3),
    SWIPE_RIGHT_THREE_FINGERS(2, Swipe.Direction.RIGHT, 3),
    HOLD_DOT1_SWIPE_UP_ONE_FINGER(3, Swipe.Direction.UP, 1, "1"),
    HOLD_DOT1_SWIPE_DOWN_ONE_FINGER(3, Swipe.Direction.DOWN, 1, "1"),
    HOLD_DOT1_SWIPE_LEFT_ONE_FINGER(3, Swipe.Direction.LEFT, 1, "1"),
    HOLD_DOT1_SWIPE_RIGHT_ONE_FINGER(3, Swipe.Direction.RIGHT, 1, "1"),
    HOLD_DOT1_SWIPE_UP_TWO_FINGERS(3, Swipe.Direction.UP, 2, "1"),
    HOLD_DOT1_SWIPE_DOWN_TWO_FINGERS(3, Swipe.Direction.DOWN, 2, "1"),
    HOLD_DOT1_SWIPE_LEFT_TWO_FINGERS(3, Swipe.Direction.LEFT, 2, "1"),
    HOLD_DOT1_SWIPE_RIGHT_TWO_FINGERS(3, Swipe.Direction.RIGHT, 2, "1"),
    HOLD_DOT1_SWIPE_UP_THREE_FINGERS(3, Swipe.Direction.UP, 3, "1"),
    HOLD_DOT1_SWIPE_DOWN_THREE_FINGERS(3, Swipe.Direction.DOWN, 3, "1"),
    HOLD_DOT1_SWIPE_LEFT_THREE_FINGERS(3, Swipe.Direction.LEFT, 3, "1"),
    HOLD_DOT1_SWIPE_RIGHT_THREE_FINGERS(3, Swipe.Direction.RIGHT, 3, "1"),
    HOLD_DOT2_SWIPE_UP_ONE_FINGER(3, Swipe.Direction.UP, 1, "2"),
    HOLD_DOT2_SWIPE_DOWN_ONE_FINGER(3, Swipe.Direction.DOWN, 1, "2"),
    HOLD_DOT2_SWIPE_LEFT_ONE_FINGER(3, Swipe.Direction.LEFT, 1, "2"),
    HOLD_DOT2_SWIPE_RIGHT_ONE_FINGER(3, Swipe.Direction.RIGHT, 1, "2"),
    HOLD_DOT2_SWIPE_UP_TWO_FINGERS(3, Swipe.Direction.UP, 2, "2"),
    HOLD_DOT2_SWIPE_DOWN_TWO_FINGERS(3, Swipe.Direction.DOWN, 2, "2"),
    HOLD_DOT2_SWIPE_LEFT_TWO_FINGERS(3, Swipe.Direction.LEFT, 2, "2"),
    HOLD_DOT2_SWIPE_RIGHT_TWO_FINGERS(3, Swipe.Direction.RIGHT, 2, "2"),
    HOLD_DOT2_SWIPE_UP_THREE_FINGERS(3, Swipe.Direction.UP, 3, "2"),
    HOLD_DOT2_SWIPE_DOWN_THREE_FINGERS(3, Swipe.Direction.DOWN, 3, "2"),
    HOLD_DOT2_SWIPE_LEFT_THREE_FINGERS(3, Swipe.Direction.LEFT, 3, "2"),
    HOLD_DOT2_SWIPE_RIGHT_THREE_FINGERS(3, Swipe.Direction.RIGHT, 3, "2"),
    HOLD_DOT3_SWIPE_UP_ONE_FINGER(3, Swipe.Direction.UP, 1, "3"),
    HOLD_DOT3_SWIPE_DOWN_ONE_FINGER(3, Swipe.Direction.DOWN, 1, "3"),
    HOLD_DOT3_SWIPE_LEFT_ONE_FINGER(3, Swipe.Direction.LEFT, 1, "3"),
    HOLD_DOT3_SWIPE_RIGHT_ONE_FINGER(3, Swipe.Direction.RIGHT, 1, "3"),
    HOLD_DOT3_SWIPE_UP_TWO_FINGERS(3, Swipe.Direction.UP, 2, "3"),
    HOLD_DOT3_SWIPE_DOWN_TWO_FINGERS(3, Swipe.Direction.DOWN, 2, "3"),
    HOLD_DOT3_SWIPE_LEFT_TWO_FINGERS(3, Swipe.Direction.LEFT, 2, "3"),
    HOLD_DOT3_SWIPE_RIGHT_TWO_FINGERS(3, Swipe.Direction.RIGHT, 2, "3"),
    HOLD_DOT3_SWIPE_UP_THREE_FINGERS(3, Swipe.Direction.UP, 3, "3"),
    HOLD_DOT3_SWIPE_DOWN_THREE_FINGERS(3, Swipe.Direction.DOWN, 3, "3"),
    HOLD_DOT3_SWIPE_LEFT_THREE_FINGERS(3, Swipe.Direction.LEFT, 3, "3"),
    HOLD_DOT3_SWIPE_RIGHT_THREE_FINGERS(3, Swipe.Direction.RIGHT, 3, "3"),
    HOLD_DOTS12_SWIPE_UP_ONE_FINGER(3, Swipe.Direction.UP, 1, "12"),
    HOLD_DOTS12_SWIPE_DOWN_ONE_FINGER(3, Swipe.Direction.DOWN, 1, "12"),
    HOLD_DOTS12_SWIPE_LEFT_ONE_FINGER(3, Swipe.Direction.LEFT, 1, "12"),
    HOLD_DOTS12_SWIPE_RIGHT_ONE_FINGER(3, Swipe.Direction.RIGHT, 1, "12"),
    HOLD_DOTS12_SWIPE_UP_TWO_FINGERS(3, Swipe.Direction.UP, 2, "12"),
    HOLD_DOTS12_SWIPE_DOWN_TWO_FINGERS(3, Swipe.Direction.DOWN, 2, "12"),
    HOLD_DOTS12_SWIPE_LEFT_TWO_FINGERS(3, Swipe.Direction.LEFT, 2, "12"),
    HOLD_DOTS12_SWIPE_RIGHT_TWO_FINGERS(3, Swipe.Direction.RIGHT, 2, "12"),
    HOLD_DOTS12_SWIPE_UP_THREE_FINGERS(3, Swipe.Direction.UP, 3, "12"),
    HOLD_DOTS12_SWIPE_DOWN_THREE_FINGERS(3, Swipe.Direction.DOWN, 3, "12"),
    HOLD_DOTS12_SWIPE_LEFT_THREE_FINGERS(3, Swipe.Direction.LEFT, 3, "12"),
    HOLD_DOTS12_SWIPE_RIGHT_THREE_FINGERS(3, Swipe.Direction.RIGHT, 3, "12");

    public final Swipe.Direction direction;
    public final int gestureType$ar$edu;
    public final String heldDotNumberString;
    public final int touchCount;

    BrailleImeGesture(int i, Swipe.Direction direction, int i2) {
        this(i, direction, i2, "");
    }

    BrailleImeGesture(int i, Swipe.Direction direction, int i2, String str) {
        this.gestureType$ar$edu = i;
        this.direction = direction;
        this.touchCount = i2;
        this.heldDotNumberString = str;
    }
}
