package com.google.android.accessibility.brailleime.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeLogProto$GestureActionType implements Internal.EnumLite {
    UNSPECIFIED_ACTION(0),
    KEY_SPACE(1),
    KEY_DELETE_CHARACTER(2),
    KEY_DELETE_WORD(3),
    KEY_NEWLINE(4),
    SUBMIT_TEXT(5),
    CLOSE_KEYBOARD(6),
    SWITCH_KEYBOARD(7),
    OPEN_OPTIONS_MENU(8),
    KEY_MOVE_CURSOR_FORWARD(9),
    KEY_MOVE_CURSOR_BACKWARD(10),
    KEY_MOVE_CURSOR_FORWARD_BY_CHARACTER(11),
    KEY_MOVE_CURSOR_BACKWARD_BY_CHARACTER(12),
    KEY_MOVE_CURSOR_FORWARD_BY_WORD(13),
    KEY_MOVE_CURSOR_BACKWARD_BY_WORD(14),
    KEY_MOVE_CURSOR_FORWARD_BY_LINE(15),
    KEY_MOVE_CURSOR_BACKWARD_BY_LINE(16),
    KEY_MOVE_CURSOR_TO_BEGINNING(17),
    KEY_MOVE_CURSOR_TO_END(18),
    KEY_SELECT_NEXT_CHARACTER(19),
    KEY_SELECT_PREVIOUS_CHARACTER(20),
    KEY_SELECT_NEXT_WORD(21),
    KEY_SELECT_PREVIOUS_WORD(22),
    KEY_SELECT_NEXT_LINE(23),
    KEY_SELECT_PREVIOUS_LINE(24),
    KEY_SELECT_ALL_TEXT(25),
    KEY_CUT(26),
    KEY_COPY(27),
    KEY_PASTE(28),
    KEY_SWITCH_TO_NEXT_EDITING_GRANULARITY(29),
    KEY_SWITCH_TO_PREVIOUS_EDITING_GRANULARITY(30),
    KEY_SELECT_CURRENT_TO_START(31),
    KEY_SELECT_CURRENT_TO_END(32);

    public final int value;

    BrailleImeLogProto$GestureActionType(int i) {
        this.value = i;
    }

    public static BrailleImeLogProto$GestureActionType forNumber(int i) {
        switch (i) {
            case 0:
                return UNSPECIFIED_ACTION;
            case 1:
                return KEY_SPACE;
            case 2:
                return KEY_DELETE_CHARACTER;
            case 3:
                return KEY_DELETE_WORD;
            case 4:
                return KEY_NEWLINE;
            case 5:
                return SUBMIT_TEXT;
            case 6:
                return CLOSE_KEYBOARD;
            case 7:
                return SWITCH_KEYBOARD;
            case 8:
                return OPEN_OPTIONS_MENU;
            case 9:
                return KEY_MOVE_CURSOR_FORWARD;
            case 10:
                return KEY_MOVE_CURSOR_BACKWARD;
            case 11:
                return KEY_MOVE_CURSOR_FORWARD_BY_CHARACTER;
            case 12:
                return KEY_MOVE_CURSOR_BACKWARD_BY_CHARACTER;
            case 13:
                return KEY_MOVE_CURSOR_FORWARD_BY_WORD;
            case 14:
                return KEY_MOVE_CURSOR_BACKWARD_BY_WORD;
            case 15:
                return KEY_MOVE_CURSOR_FORWARD_BY_LINE;
            case 16:
                return KEY_MOVE_CURSOR_BACKWARD_BY_LINE;
            case 17:
                return KEY_MOVE_CURSOR_TO_BEGINNING;
            case 18:
                return KEY_MOVE_CURSOR_TO_END;
            case 19:
                return KEY_SELECT_NEXT_CHARACTER;
            case 20:
                return KEY_SELECT_PREVIOUS_CHARACTER;
            case 21:
                return KEY_SELECT_NEXT_WORD;
            case 22:
                return KEY_SELECT_PREVIOUS_WORD;
            case 23:
                return KEY_SELECT_NEXT_LINE;
            case 24:
                return KEY_SELECT_PREVIOUS_LINE;
            case 25:
                return KEY_SELECT_ALL_TEXT;
            case 26:
                return KEY_CUT;
            case 27:
                return KEY_COPY;
            case 28:
                return KEY_PASTE;
            case 29:
                return KEY_SWITCH_TO_NEXT_EDITING_GRANULARITY;
            case 30:
                return KEY_SWITCH_TO_PREVIOUS_EDITING_GRANULARITY;
            case 31:
                return KEY_SELECT_CURRENT_TO_START;
            case 32:
                return KEY_SELECT_CURRENT_TO_END;
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
