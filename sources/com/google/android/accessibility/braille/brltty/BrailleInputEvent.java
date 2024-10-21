package com.google.android.accessibility.braille.brltty;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleInputEvent implements Parcelable {
    public static final int ARGUMENT_DOTS = 1;
    public static final int ARGUMENT_NONE = 0;
    public static final int ARGUMENT_POSITION = 2;
    public static final int CMD_ACTIVATE_CURRENT = 20;
    public static final int CMD_ALL_APPS = 95;
    public static final int CMD_BRAILLE_DISPLAY_SETTINGS = 121;
    public static final int CMD_BRAILLE_KEY = 60;
    public static final int CMD_CHARACTER_NEXT = 10;
    public static final int CMD_CHARACTER_PREVIOUS = 9;
    public static final int CMD_CONTROL_NEXT = 112;
    public static final int CMD_CONTROL_PREVIOUS = 113;
    public static final int CMD_DECREASE_AUTO_SCROLL_DURATION = 133;
    public static final int CMD_DEL_WORD = 72;
    public static final int CMD_EDIT_CUSTOM_LABEL = 117;
    public static final int CMD_GLOBAL_BACK = 90;
    public static final int CMD_GLOBAL_HOME = 91;
    public static final int CMD_GLOBAL_NOTIFICATIONS = 93;
    public static final int CMD_GLOBAL_RECENTS = 92;
    public static final int CMD_HEADING_NEXT = 110;
    public static final int CMD_HEADING_PREVIOUS = 111;
    public static final int CMD_HELP = 100;
    public static final int CMD_INCREASE_AUTO_SCROLL_DURATION = 132;
    public static final int CMD_KEY_DEL = 71;
    public static final int CMD_KEY_ENTER = 70;
    public static final int CMD_LINK_NEXT = 114;
    public static final int CMD_LINK_PREVIOUS = 115;
    public static final int CMD_LONG_PRESS_CURRENT = 21;
    public static final int CMD_LONG_PRESS_ROUTE = 51;
    public static final int CMD_NAVIGATE_BY_READING_GRANULARITY_OR_ADJUST_READING_CONTROL_BACKWARD = 15;
    public static final int CMD_NAVIGATE_BY_READING_GRANULARITY_OR_ADJUST_READING_CONTROL_FORWARD = 16;
    public static final int CMD_NAV_BOTTOM = 8;
    public static final int CMD_NAV_BOTTOM_OR_KEY_ACTIVATE = 129;
    public static final int CMD_NAV_ITEM_NEXT = 4;
    public static final int CMD_NAV_ITEM_PREVIOUS = 3;
    public static final int CMD_NAV_LINE_NEXT = 2;
    public static final int CMD_NAV_LINE_PREVIOUS = 1;
    public static final int CMD_NAV_PAN_DOWN = 6;
    public static final int CMD_NAV_PAN_UP = 5;
    public static final int CMD_NAV_TOP = 7;
    public static final int CMD_NAV_TOP_OR_KEY_ACTIVATE = 128;
    public static final int CMD_NEXT_INPUT_METHOD = 83;
    public static final int CMD_NEXT_READING_CONTROL = 126;
    public static final int CMD_NONE = -1;
    public static final int CMD_OPEN_TALKBACK_MENU = 118;
    public static final int CMD_PLAY_PAUSE_MEDIA = 134;
    public static final int CMD_PREVIOUS_READING_CONTROL = 125;
    public static final int CMD_QUICK_SETTINGS = 94;
    public static final int CMD_ROUTE = 50;
    public static final int CMD_SCROLL_BACKWARD = 30;
    public static final int CMD_SCROLL_FORWARD = 31;
    public static final int CMD_SELECTION_COPY = 44;
    public static final int CMD_SELECTION_CUT = 43;
    public static final int CMD_SELECTION_END = 41;
    public static final int CMD_SELECTION_PASTE = 45;
    public static final int CMD_SELECTION_SELECT_ALL = 42;
    public static final int CMD_SELECTION_SELECT_CURRENT_TO_END = 47;
    public static final int CMD_SELECTION_SELECT_CURRENT_TO_START = 46;
    public static final int CMD_SELECTION_START = 40;
    public static final int CMD_SELECT_NEXT_CHARACTER = 78;
    public static final int CMD_SELECT_NEXT_LINE = 82;
    public static final int CMD_SELECT_NEXT_WORD = 80;
    public static final int CMD_SELECT_PREVIOUS_CHARACTER = 77;
    public static final int CMD_SELECT_PREVIOUS_LINE = 81;
    public static final int CMD_SELECT_PREVIOUS_WORD = 79;
    public static final int CMD_STOP_READING = 130;
    public static final int CMD_SWITCH_TO_NEXT_INPUT_LANGUAGE = 119;
    public static final int CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE = 120;
    public static final int CMD_TALKBACK_SETTINGS = 122;
    public static final int CMD_TOGGLE_AUTO_SCROLL = 131;
    public static final int CMD_TOGGLE_BRAILLE_GRADE = 127;
    public static final int CMD_TOGGLE_SCREEN_SEARCH = 116;
    public static final int CMD_TOGGLE_VOICE_FEEDBACK = 124;
    public static final int CMD_TURN_OFF_BRAILLE_DISPLAY = 123;
    public static final int CMD_WINDOW_NEXT = 14;
    public static final int CMD_WINDOW_PREVIOUS = 13;
    public static final int CMD_WORD_NEXT = 12;
    public static final int CMD_WORD_PREVIOUS = 11;
    public static final Parcelable.Creator<BrailleInputEvent> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(3);
    private final int argument;
    private final int command;
    private final long eventTime;

    public BrailleInputEvent(int i, int i2, long j) {
        this.command = i;
        this.argument = i2;
        this.eventTime = j;
    }

    public static int argumentType(int i) {
        if (i != 40 && i != 41 && i != 50 && i != 51) {
            if (i != 60) {
                return 0;
            }
            return 1;
        }
        return 2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getArgument() {
        return this.argument;
    }

    public int getCommand() {
        return this.command;
    }

    public long getEventTime() {
        return this.eventTime;
    }

    public String toString() {
        return "BrailleInputEvent {cmd=" + this.command + ", arg=" + this.argument + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.command);
        parcel.writeInt(this.argument);
        parcel.writeLong(this.eventTime);
    }

    public /* synthetic */ BrailleInputEvent(Parcel parcel, BrailleInputEventIA brailleInputEventIA) {
        this(parcel);
    }

    private BrailleInputEvent(Parcel parcel) {
        this.command = parcel.readInt();
        this.argument = parcel.readInt();
        this.eventTime = parcel.readLong();
    }
}
