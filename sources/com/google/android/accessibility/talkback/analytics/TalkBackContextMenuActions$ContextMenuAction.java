package com.google.android.accessibility.talkback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackContextMenuActions$ContextMenuAction {
    public static final int ACTION_UNSPECIFIED$ar$edu = 1;
    public static final int ACTION_READ_FROM_TOP$ar$edu = 2;
    public static final int ACTION_READ_FROM_NEXT_ITEM$ar$edu = 3;
    public static final int ACTION_REPEAT_LAST_UTTERANCE$ar$edu = 4;
    public static final int ACTION_SPELL_LAST_UTTERANCE$ar$edu = 5;
    public static final int ACTION_COPY_LAST_UTTERANCE_TO_CLIPBOARD$ar$edu = 6;
    public static final int ACTION_PAUSE_FEEDBACK$ar$edu = 7;
    public static final int ACTION_SCREEN_SEARCH$ar$edu = 8;
    public static final int ACTION_TALKBACK_SETTINGS$ar$edu = 9;
    public static final int ACTION_TEXT_TO_SPEECH_SETTINGS$ar$edu = 10;
    public static final int ACTION_DIM_SCREEN$ar$edu = 11;
    public static final int ACTION_BRIGHTEN_SCREEN$ar$edu = 12;
    public static final int ACTION_LANGUAGES$ar$edu = 13;
    public static final int ACTION_QUICK_NAVIGATION$ar$edu = 14;
    public static final int ACTION_VERBOSITY$ar$edu = 15;
    public static final int ACTION_AUDIO_DUCKING$ar$edu = 16;
    public static final int ACTION_SOUND_FEEDBACK$ar$edu = 17;
    public static final int ACTION_VIBRATION_FEEDBACK$ar$edu = 18;
    public static final int ACTION_VOICE_COMMAND$ar$edu = 19;
    public static final int ACTION_SYSTEM_ACTIONS$ar$edu = 20;
    public static final int ACTION_GRANULARITY_DEFAULT$ar$edu = 21;
    public static final int ACTION_GRANULARITY_CHARACTERS$ar$edu = 22;
    public static final int ACTION_GRANULARITY_WORDS$ar$edu = 23;
    public static final int ACTION_GRANULARITY_LINE$ar$edu = 24;
    public static final int ACTION_GRANULARITY_PARAGRAPH$ar$edu = 25;
    public static final int ACTION_GRANULARITY_HEADINGS$ar$edu = 26;
    public static final int ACTION_GRANULARITY_LINKS$ar$edu = 27;
    public static final int ACTION_GRANULARITY_CONTROLS$ar$edu = 28;
    public static final int ACTION_GRANULARITY_WEB_HEADINGS$ar$edu = 29;
    public static final int ACTION_GRANULARITY_WEB_LINKS$ar$edu = 30;
    public static final int ACTION_GRANULARITY_WEB_LIST$ar$edu = 31;
    public static final int ACTION_GRANULARITY_WEB_CONTROLS$ar$edu = 32;
    public static final int ACTION_GRANULARITY_SPECIAL_CONTENTS$ar$edu = 33;
    public static final int ACTION_GRANULARITY_LANDMARKS$ar$edu = 34;
    public static final int ACTION_GRANULARITY_WINDOWS$ar$edu = 35;
    public static final int ACTION_GRANULARITY_CONTAINERS$ar$edu = 36;
    public static final int ACTION_EDIT_OPTIONS_CURSOR_TO_BEGINNING$ar$edu = 41;
    public static final int ACTION_EDIT_OPTIONS_CURSOR_TO_END$ar$edu = 42;
    public static final int ACTION_EDIT_OPTIONS_CUT$ar$edu = 43;
    public static final int ACTION_EDIT_OPTIONS_COPY$ar$edu = 44;
    public static final int ACTION_EDIT_OPTIONS_PASTE$ar$edu = 45;
    public static final int ACTION_EDIT_OPTIONS_SELECT_ALL$ar$edu = 46;
    public static final int ACTION_EDIT_OPTIONS_START_SELECT$ar$edu = 47;
    public static final int ACTION_EDIT_OPTIONS_END_SELECT$ar$edu = 48;
    public static final int ACTION_VIEW_PAGER_PAGE_PREVIOUS$ar$edu = 51;
    public static final int ACTION_VIEW_PAGER_PAGE_NEXT$ar$edu = 52;
    public static final int ACTION_VIEW_PAGER_PAGE_UP$ar$edu = 53;
    public static final int ACTION_VIEW_PAGER_PAGE_DOWN$ar$edu = 54;
    public static final int ACTION_VIEW_PAGER_PAGE_LEFT$ar$edu = 55;
    public static final int ACTION_VIEW_PAGER_PAGE_RIGHT$ar$edu = 56;
    public static final int ACTION_LABELING_ADD_LABEL$ar$edu = 61;
    public static final int ACTION_LABELING_EDIT_LABEL$ar$edu = 62;
    public static final int ACTION_LABELING_REMOVE_LABEL$ar$edu = 63;
    public static final int ACTION_CUSTOM_ACTION_OTHERS$ar$edu = 71;
    public static final int ACTION_CUSTOM_ACTION_DISMISS$ar$edu = 72;
    public static final int ACTION_CUSTOM_ACTION_EXPAND$ar$edu = 73;
    public static final int ACTION_CUSTOM_ACTION_COLLAPSE$ar$edu = 74;
    public static final int ACTION_SEEK_BAR$ar$edu = 81;
    public static final int ACTION_SPANNABLES$ar$edu = 82;
    public static final int ACTION_DESCRIBE_IMAGE$ar$edu = 83;
    public static final int ACTION_BRAILLE_DISPLAY_SETTING$ar$edu = 84;
    private static final /* synthetic */ int[] $VALUES$ar$edu$fb85ddeb_0 = {ACTION_UNSPECIFIED$ar$edu, ACTION_READ_FROM_TOP$ar$edu, ACTION_READ_FROM_NEXT_ITEM$ar$edu, ACTION_REPEAT_LAST_UTTERANCE$ar$edu, ACTION_SPELL_LAST_UTTERANCE$ar$edu, ACTION_COPY_LAST_UTTERANCE_TO_CLIPBOARD$ar$edu, ACTION_PAUSE_FEEDBACK$ar$edu, ACTION_SCREEN_SEARCH$ar$edu, ACTION_TALKBACK_SETTINGS$ar$edu, ACTION_TEXT_TO_SPEECH_SETTINGS$ar$edu, ACTION_DIM_SCREEN$ar$edu, ACTION_BRIGHTEN_SCREEN$ar$edu, ACTION_LANGUAGES$ar$edu, ACTION_QUICK_NAVIGATION$ar$edu, ACTION_VERBOSITY$ar$edu, ACTION_AUDIO_DUCKING$ar$edu, ACTION_SOUND_FEEDBACK$ar$edu, ACTION_VIBRATION_FEEDBACK$ar$edu, ACTION_VOICE_COMMAND$ar$edu, ACTION_SYSTEM_ACTIONS$ar$edu, ACTION_GRANULARITY_DEFAULT$ar$edu, ACTION_GRANULARITY_CHARACTERS$ar$edu, ACTION_GRANULARITY_WORDS$ar$edu, ACTION_GRANULARITY_LINE$ar$edu, ACTION_GRANULARITY_PARAGRAPH$ar$edu, ACTION_GRANULARITY_HEADINGS$ar$edu, ACTION_GRANULARITY_LINKS$ar$edu, ACTION_GRANULARITY_CONTROLS$ar$edu, ACTION_GRANULARITY_WEB_HEADINGS$ar$edu, ACTION_GRANULARITY_WEB_LINKS$ar$edu, ACTION_GRANULARITY_WEB_LIST$ar$edu, ACTION_GRANULARITY_WEB_CONTROLS$ar$edu, ACTION_GRANULARITY_SPECIAL_CONTENTS$ar$edu, ACTION_GRANULARITY_LANDMARKS$ar$edu, ACTION_GRANULARITY_WINDOWS$ar$edu, ACTION_GRANULARITY_CONTAINERS$ar$edu, ACTION_EDIT_OPTIONS_CURSOR_TO_BEGINNING$ar$edu, ACTION_EDIT_OPTIONS_CURSOR_TO_END$ar$edu, ACTION_EDIT_OPTIONS_CUT$ar$edu, ACTION_EDIT_OPTIONS_COPY$ar$edu, ACTION_EDIT_OPTIONS_PASTE$ar$edu, ACTION_EDIT_OPTIONS_SELECT_ALL$ar$edu, ACTION_EDIT_OPTIONS_START_SELECT$ar$edu, ACTION_EDIT_OPTIONS_END_SELECT$ar$edu, ACTION_VIEW_PAGER_PAGE_PREVIOUS$ar$edu, ACTION_VIEW_PAGER_PAGE_NEXT$ar$edu, ACTION_VIEW_PAGER_PAGE_UP$ar$edu, ACTION_VIEW_PAGER_PAGE_DOWN$ar$edu, ACTION_VIEW_PAGER_PAGE_LEFT$ar$edu, ACTION_VIEW_PAGER_PAGE_RIGHT$ar$edu, ACTION_LABELING_ADD_LABEL$ar$edu, ACTION_LABELING_EDIT_LABEL$ar$edu, ACTION_LABELING_REMOVE_LABEL$ar$edu, ACTION_CUSTOM_ACTION_OTHERS$ar$edu, ACTION_CUSTOM_ACTION_DISMISS$ar$edu, ACTION_CUSTOM_ACTION_EXPAND$ar$edu, ACTION_CUSTOM_ACTION_COLLAPSE$ar$edu, ACTION_SEEK_BAR$ar$edu, ACTION_SPANNABLES$ar$edu, ACTION_DESCRIBE_IMAGE$ar$edu, ACTION_BRAILLE_DISPLAY_SETTING$ar$edu};

    public static int forNumber$ar$edu$1e7c3a14_0(int i) {
        switch (i) {
            case 0:
                return ACTION_UNSPECIFIED$ar$edu;
            case 1:
                return ACTION_READ_FROM_TOP$ar$edu;
            case 2:
                return ACTION_READ_FROM_NEXT_ITEM$ar$edu;
            case 3:
                return ACTION_REPEAT_LAST_UTTERANCE$ar$edu;
            case 4:
                return ACTION_SPELL_LAST_UTTERANCE$ar$edu;
            case 5:
                return ACTION_COPY_LAST_UTTERANCE_TO_CLIPBOARD$ar$edu;
            case 6:
                return ACTION_PAUSE_FEEDBACK$ar$edu;
            case 7:
                return ACTION_SCREEN_SEARCH$ar$edu;
            case 8:
                return ACTION_TALKBACK_SETTINGS$ar$edu;
            case 9:
                return ACTION_TEXT_TO_SPEECH_SETTINGS$ar$edu;
            case 10:
                return ACTION_DIM_SCREEN$ar$edu;
            case 11:
                return ACTION_BRIGHTEN_SCREEN$ar$edu;
            case 12:
                return ACTION_LANGUAGES$ar$edu;
            case 13:
                return ACTION_QUICK_NAVIGATION$ar$edu;
            case 14:
                return ACTION_VERBOSITY$ar$edu;
            case 15:
                return ACTION_AUDIO_DUCKING$ar$edu;
            case 16:
                return ACTION_SOUND_FEEDBACK$ar$edu;
            case 17:
                return ACTION_VIBRATION_FEEDBACK$ar$edu;
            case 18:
                return ACTION_VOICE_COMMAND$ar$edu;
            case 19:
                return ACTION_SYSTEM_ACTIONS$ar$edu;
            case 20:
                return ACTION_GRANULARITY_DEFAULT$ar$edu;
            case 21:
                return ACTION_GRANULARITY_CHARACTERS$ar$edu;
            case 22:
                return ACTION_GRANULARITY_WORDS$ar$edu;
            case 23:
                return ACTION_GRANULARITY_LINE$ar$edu;
            case 24:
                return ACTION_GRANULARITY_PARAGRAPH$ar$edu;
            case 25:
                return ACTION_GRANULARITY_HEADINGS$ar$edu;
            case 26:
                return ACTION_GRANULARITY_LINKS$ar$edu;
            case 27:
                return ACTION_GRANULARITY_CONTROLS$ar$edu;
            case 28:
                return ACTION_GRANULARITY_WEB_HEADINGS$ar$edu;
            case 29:
                return ACTION_GRANULARITY_WEB_LINKS$ar$edu;
            case 30:
                return ACTION_GRANULARITY_WEB_LIST$ar$edu;
            case 31:
                return ACTION_GRANULARITY_WEB_CONTROLS$ar$edu;
            case 32:
                return ACTION_GRANULARITY_SPECIAL_CONTENTS$ar$edu;
            case 33:
                return ACTION_GRANULARITY_LANDMARKS$ar$edu;
            case 34:
                return ACTION_GRANULARITY_WINDOWS$ar$edu;
            case 35:
                return ACTION_GRANULARITY_CONTAINERS$ar$edu;
            default:
                switch (i) {
                    case 40:
                        return ACTION_EDIT_OPTIONS_CURSOR_TO_BEGINNING$ar$edu;
                    case 41:
                        return ACTION_EDIT_OPTIONS_CURSOR_TO_END$ar$edu;
                    case 42:
                        return ACTION_EDIT_OPTIONS_CUT$ar$edu;
                    case 43:
                        return ACTION_EDIT_OPTIONS_COPY$ar$edu;
                    case 44:
                        return ACTION_EDIT_OPTIONS_PASTE$ar$edu;
                    case 45:
                        return ACTION_EDIT_OPTIONS_SELECT_ALL$ar$edu;
                    case 46:
                        return ACTION_EDIT_OPTIONS_START_SELECT$ar$edu;
                    case 47:
                        return ACTION_EDIT_OPTIONS_END_SELECT$ar$edu;
                    default:
                        switch (i) {
                            case 50:
                                return ACTION_VIEW_PAGER_PAGE_PREVIOUS$ar$edu;
                            case 51:
                                return ACTION_VIEW_PAGER_PAGE_NEXT$ar$edu;
                            case 52:
                                return ACTION_VIEW_PAGER_PAGE_UP$ar$edu;
                            case 53:
                                return ACTION_VIEW_PAGER_PAGE_DOWN$ar$edu;
                            case 54:
                                return ACTION_VIEW_PAGER_PAGE_LEFT$ar$edu;
                            case 55:
                                return ACTION_VIEW_PAGER_PAGE_RIGHT$ar$edu;
                            default:
                                switch (i) {
                                    case 60:
                                        return ACTION_LABELING_ADD_LABEL$ar$edu;
                                    case 61:
                                        return ACTION_LABELING_EDIT_LABEL$ar$edu;
                                    case 62:
                                        return ACTION_LABELING_REMOVE_LABEL$ar$edu;
                                    default:
                                        switch (i) {
                                            case 70:
                                                return ACTION_CUSTOM_ACTION_OTHERS$ar$edu;
                                            case 71:
                                                return ACTION_CUSTOM_ACTION_DISMISS$ar$edu;
                                            case 72:
                                                return ACTION_CUSTOM_ACTION_EXPAND$ar$edu;
                                            case 73:
                                                return ACTION_CUSTOM_ACTION_COLLAPSE$ar$edu;
                                            default:
                                                switch (i) {
                                                    case 80:
                                                        return ACTION_SEEK_BAR$ar$edu;
                                                    case 81:
                                                        return ACTION_SPANNABLES$ar$edu;
                                                    case 82:
                                                        return ACTION_DESCRIBE_IMAGE$ar$edu;
                                                    case 83:
                                                        return ACTION_BRAILLE_DISPLAY_SETTING$ar$edu;
                                                    default:
                                                        return 0;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static int[] values$ar$edu$576e06a9_0() {
        return new int[]{ACTION_UNSPECIFIED$ar$edu, ACTION_READ_FROM_TOP$ar$edu, ACTION_READ_FROM_NEXT_ITEM$ar$edu, ACTION_REPEAT_LAST_UTTERANCE$ar$edu, ACTION_SPELL_LAST_UTTERANCE$ar$edu, ACTION_COPY_LAST_UTTERANCE_TO_CLIPBOARD$ar$edu, ACTION_PAUSE_FEEDBACK$ar$edu, ACTION_SCREEN_SEARCH$ar$edu, ACTION_TALKBACK_SETTINGS$ar$edu, ACTION_TEXT_TO_SPEECH_SETTINGS$ar$edu, ACTION_DIM_SCREEN$ar$edu, ACTION_BRIGHTEN_SCREEN$ar$edu, ACTION_LANGUAGES$ar$edu, ACTION_QUICK_NAVIGATION$ar$edu, ACTION_VERBOSITY$ar$edu, ACTION_AUDIO_DUCKING$ar$edu, ACTION_SOUND_FEEDBACK$ar$edu, ACTION_VIBRATION_FEEDBACK$ar$edu, ACTION_VOICE_COMMAND$ar$edu, ACTION_SYSTEM_ACTIONS$ar$edu, ACTION_GRANULARITY_DEFAULT$ar$edu, ACTION_GRANULARITY_CHARACTERS$ar$edu, ACTION_GRANULARITY_WORDS$ar$edu, ACTION_GRANULARITY_LINE$ar$edu, ACTION_GRANULARITY_PARAGRAPH$ar$edu, ACTION_GRANULARITY_HEADINGS$ar$edu, ACTION_GRANULARITY_LINKS$ar$edu, ACTION_GRANULARITY_CONTROLS$ar$edu, ACTION_GRANULARITY_WEB_HEADINGS$ar$edu, ACTION_GRANULARITY_WEB_LINKS$ar$edu, ACTION_GRANULARITY_WEB_LIST$ar$edu, ACTION_GRANULARITY_WEB_CONTROLS$ar$edu, ACTION_GRANULARITY_SPECIAL_CONTENTS$ar$edu, ACTION_GRANULARITY_LANDMARKS$ar$edu, ACTION_GRANULARITY_WINDOWS$ar$edu, ACTION_GRANULARITY_CONTAINERS$ar$edu, ACTION_EDIT_OPTIONS_CURSOR_TO_BEGINNING$ar$edu, ACTION_EDIT_OPTIONS_CURSOR_TO_END$ar$edu, ACTION_EDIT_OPTIONS_CUT$ar$edu, ACTION_EDIT_OPTIONS_COPY$ar$edu, ACTION_EDIT_OPTIONS_PASTE$ar$edu, ACTION_EDIT_OPTIONS_SELECT_ALL$ar$edu, ACTION_EDIT_OPTIONS_START_SELECT$ar$edu, ACTION_EDIT_OPTIONS_END_SELECT$ar$edu, ACTION_VIEW_PAGER_PAGE_PREVIOUS$ar$edu, ACTION_VIEW_PAGER_PAGE_NEXT$ar$edu, ACTION_VIEW_PAGER_PAGE_UP$ar$edu, ACTION_VIEW_PAGER_PAGE_DOWN$ar$edu, ACTION_VIEW_PAGER_PAGE_LEFT$ar$edu, ACTION_VIEW_PAGER_PAGE_RIGHT$ar$edu, ACTION_LABELING_ADD_LABEL$ar$edu, ACTION_LABELING_EDIT_LABEL$ar$edu, ACTION_LABELING_REMOVE_LABEL$ar$edu, ACTION_CUSTOM_ACTION_OTHERS$ar$edu, ACTION_CUSTOM_ACTION_DISMISS$ar$edu, ACTION_CUSTOM_ACTION_EXPAND$ar$edu, ACTION_CUSTOM_ACTION_COLLAPSE$ar$edu, ACTION_SEEK_BAR$ar$edu, ACTION_SPANNABLES$ar$edu, ACTION_DESCRIBE_IMAGE$ar$edu, ACTION_BRAILLE_DISPLAY_SETTING$ar$edu};
    }
}
