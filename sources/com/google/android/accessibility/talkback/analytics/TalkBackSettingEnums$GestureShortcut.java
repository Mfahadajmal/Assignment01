package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$GestureShortcut implements Internal.EnumLite {
    GESTURE_SHORTCUT_UNASSIGNED(0),
    GESTURE_SHORTCUT_PREVIOUS_ITEM(1),
    GESTURE_SHORTCUT_NEXT_ITEM(2),
    GESTURE_SHORTCUT_FOCUS_FIRST_ITEM_ON_SCREEN(3),
    GESTURE_SHORTCUT_FOCUS_LAST_ITEM_ON_SCREEN(4),
    GESTURE_SHORTCUT_SCROLL_BACK(5),
    GESTURE_SHORTCUT_SCROLL_FORWARD(6),
    GESTURE_SHORTCUT_PREVIOUS_NAVIGATION_SETTING(7),
    GESTURE_SHORTCUT_NEXT_NAVIGATION_SETTING(8),
    GESTURE_SHORTCUT_BACK_BUTTON(9),
    GESTURE_SHORTCUT_HOME_BUTTON(10),
    GESTURE_SHORTCUT_OVERVIEW_BUTTON(11),
    GESTURE_SHORTCUT_OPEN_NOTIFICATIONS(12),
    GESTURE_SHORTCUT_OPEN_QUICK_SETTINGS(13),
    GESTURE_SHORTCUT_OPEN_GLOBAL_CONTEXT_MENU(14),
    GESTURE_SHORTCUT_OPEN_LOCAL_CONTEXT_MENU(15),
    GESTURE_SHORTCUT_READ_FROM_TOP(16),
    GESTURE_SHORTCUT_READ_FROM_NEXT_ITEM(17),
    GESTURE_SHORTCUT_SHOW_ACTIONS(18),
    GESTURE_SHORTCUT_PERFORM_CLICK(19),
    GESTURE_SHORTCUT_LANGUAGE_OPTIONS(20),
    GESTURE_SHORTCUT_PRINT_NODE_TREE(21),
    GESTURE_SHORTCUT_PRINT_PERFORMANCE_STATISTICS(22),
    GESTURE_SHORTCUT_EDITING(23),
    GESTURE_SHORTCUT_SELECTOR_NEXT_ACTION(24),
    GESTURE_SHORTCUT_SELECTOR_PREVIOUS_ACTION(25),
    GESTURE_SHORTCUT_SELECTOR_NEXT_SETTING(26),
    GESTURE_SHORTCUT_SELECTOR_PREVIOUS_SETTING(27),
    GESTURE_SHORTCUT_SUMMARY(28),
    GESTURE_SHORTCUT_VOICE_COMMANDS(29),
    GESTURE_SHORTCUT_SCREEN_SEARCH(30),
    GESTURE_SHORTCUT_HEADPHONE_NAVIGATION(31),
    GESTURE_SHORTCUT_PASS_THROUGH_NEXT(32),
    GESTURE_SHORTCUT_PERFORM_LONG_CLICK(33),
    GESTURE_SHORTCUT_ALL_APPS(34),
    GESTURE_SHORTCUT_ACCESSIBILITY_SHORTCUT_CLICK(35),
    GESTURE_SHORTCUT_ACCESSIBILITY_SHORTCUT_LONG_CLICK(36),
    GESTURE_SHORTCUT_PAUSE_RESUME_SPEECH(37),
    GESTURE_SHORTCUT_TURN_ON_OFF_SPEECH(38),
    GESTURE_SHORTCUT_CHANGE_SELECTION_MODE(39),
    GESTURE_SHORTCUT_TEXT_COPY(40),
    GESTURE_SHORTCUT_TEXT_CUT(41),
    GESTURE_SHORTCUT_TEXT_PASTE(42),
    GESTURE_SHORTCUT_SWITCH_TO_BRAILLE_KEYBOARD(43),
    GESTURE_SHORTCUT_PLAY_PAUSE_MEDIA(44),
    GESTURE_SHORTCUT_OPEN_TUTORIAL(45),
    GESTURE_SHORTCUT_PREVIOUS_WINDOW(46),
    GESTURE_SHORTCUT_NEXT_WINDOW(47),
    GESTURE_SHORTCUT_SCROLL_UP(48),
    GESTURE_SHORTCUT_SCROLL_DOWN(49),
    GESTURE_SHORTCUT_SCROLL_LEFT(50),
    GESTURE_SHORTCUT_SCROLL_RIGHT(51),
    GESTURE_SHORTCUT_MOVE_CURSOR_TO_BEGIN(52),
    GESTURE_SHORTCUT_MOVE_CURSOR_TO_END(53),
    GESTURE_SHORTCUT_SELECT_ALL(54),
    GESTURE_SHORTCUT_NAVIGATE_TO_BRAILLE_DISPLAY_SETTINGS(55),
    GESTURE_SHORTCUT_PRACTICE_GESTURE(56),
    GESTURE_SHORTCUT_PREV_CONTAINER(57),
    GESTURE_SHORTCUT_NEXT_CONTAINER(58),
    GESTURE_SHORTCUT_SHOW_HIDE_SCREEN(59);

    public final int value;

    TalkBackSettingEnums$GestureShortcut(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$GestureShortcut forNumber(int i) {
        switch (i) {
            case 0:
                return GESTURE_SHORTCUT_UNASSIGNED;
            case 1:
                return GESTURE_SHORTCUT_PREVIOUS_ITEM;
            case 2:
                return GESTURE_SHORTCUT_NEXT_ITEM;
            case 3:
                return GESTURE_SHORTCUT_FOCUS_FIRST_ITEM_ON_SCREEN;
            case 4:
                return GESTURE_SHORTCUT_FOCUS_LAST_ITEM_ON_SCREEN;
            case 5:
                return GESTURE_SHORTCUT_SCROLL_BACK;
            case 6:
                return GESTURE_SHORTCUT_SCROLL_FORWARD;
            case 7:
                return GESTURE_SHORTCUT_PREVIOUS_NAVIGATION_SETTING;
            case 8:
                return GESTURE_SHORTCUT_NEXT_NAVIGATION_SETTING;
            case 9:
                return GESTURE_SHORTCUT_BACK_BUTTON;
            case 10:
                return GESTURE_SHORTCUT_HOME_BUTTON;
            case 11:
                return GESTURE_SHORTCUT_OVERVIEW_BUTTON;
            case 12:
                return GESTURE_SHORTCUT_OPEN_NOTIFICATIONS;
            case 13:
                return GESTURE_SHORTCUT_OPEN_QUICK_SETTINGS;
            case 14:
                return GESTURE_SHORTCUT_OPEN_GLOBAL_CONTEXT_MENU;
            case 15:
                return GESTURE_SHORTCUT_OPEN_LOCAL_CONTEXT_MENU;
            case 16:
                return GESTURE_SHORTCUT_READ_FROM_TOP;
            case 17:
                return GESTURE_SHORTCUT_READ_FROM_NEXT_ITEM;
            case 18:
                return GESTURE_SHORTCUT_SHOW_ACTIONS;
            case 19:
                return GESTURE_SHORTCUT_PERFORM_CLICK;
            case 20:
                return GESTURE_SHORTCUT_LANGUAGE_OPTIONS;
            case 21:
                return GESTURE_SHORTCUT_PRINT_NODE_TREE;
            case 22:
                return GESTURE_SHORTCUT_PRINT_PERFORMANCE_STATISTICS;
            case 23:
                return GESTURE_SHORTCUT_EDITING;
            case 24:
                return GESTURE_SHORTCUT_SELECTOR_NEXT_ACTION;
            case 25:
                return GESTURE_SHORTCUT_SELECTOR_PREVIOUS_ACTION;
            case 26:
                return GESTURE_SHORTCUT_SELECTOR_NEXT_SETTING;
            case 27:
                return GESTURE_SHORTCUT_SELECTOR_PREVIOUS_SETTING;
            case 28:
                return GESTURE_SHORTCUT_SUMMARY;
            case 29:
                return GESTURE_SHORTCUT_VOICE_COMMANDS;
            case 30:
                return GESTURE_SHORTCUT_SCREEN_SEARCH;
            case 31:
                return GESTURE_SHORTCUT_HEADPHONE_NAVIGATION;
            case 32:
                return GESTURE_SHORTCUT_PASS_THROUGH_NEXT;
            case 33:
                return GESTURE_SHORTCUT_PERFORM_LONG_CLICK;
            case 34:
                return GESTURE_SHORTCUT_ALL_APPS;
            case 35:
                return GESTURE_SHORTCUT_ACCESSIBILITY_SHORTCUT_CLICK;
            case 36:
                return GESTURE_SHORTCUT_ACCESSIBILITY_SHORTCUT_LONG_CLICK;
            case 37:
                return GESTURE_SHORTCUT_PAUSE_RESUME_SPEECH;
            case 38:
                return GESTURE_SHORTCUT_TURN_ON_OFF_SPEECH;
            case 39:
                return GESTURE_SHORTCUT_CHANGE_SELECTION_MODE;
            case 40:
                return GESTURE_SHORTCUT_TEXT_COPY;
            case 41:
                return GESTURE_SHORTCUT_TEXT_CUT;
            case 42:
                return GESTURE_SHORTCUT_TEXT_PASTE;
            case 43:
                return GESTURE_SHORTCUT_SWITCH_TO_BRAILLE_KEYBOARD;
            case 44:
                return GESTURE_SHORTCUT_PLAY_PAUSE_MEDIA;
            case 45:
                return GESTURE_SHORTCUT_OPEN_TUTORIAL;
            case 46:
                return GESTURE_SHORTCUT_PREVIOUS_WINDOW;
            case 47:
                return GESTURE_SHORTCUT_NEXT_WINDOW;
            case 48:
                return GESTURE_SHORTCUT_SCROLL_UP;
            case 49:
                return GESTURE_SHORTCUT_SCROLL_DOWN;
            case 50:
                return GESTURE_SHORTCUT_SCROLL_LEFT;
            case 51:
                return GESTURE_SHORTCUT_SCROLL_RIGHT;
            case 52:
                return GESTURE_SHORTCUT_MOVE_CURSOR_TO_BEGIN;
            case 53:
                return GESTURE_SHORTCUT_MOVE_CURSOR_TO_END;
            case 54:
                return GESTURE_SHORTCUT_SELECT_ALL;
            case 55:
                return GESTURE_SHORTCUT_NAVIGATE_TO_BRAILLE_DISPLAY_SETTINGS;
            case 56:
                return GESTURE_SHORTCUT_PRACTICE_GESTURE;
            case 57:
                return GESTURE_SHORTCUT_PREV_CONTAINER;
            case 58:
                return GESTURE_SHORTCUT_NEXT_CONTAINER;
            case 59:
                return GESTURE_SHORTCUT_SHOW_HIDE_SCREEN;
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
