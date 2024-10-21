package com.google.android.accessibility.brailleime;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import googledata.experiments.mobile.accessibility_suite.features.BrailleKeyboardConfig;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeActions {
    ADD_SPACE(Category.BASIC, R.string.bk_gesture_add_space, R.drawable.ic_one_finger_right),
    DELETE_CHARACTER(Category.BASIC, R.string.bk_gesture_delete, R.drawable.ic_one_finger_left),
    ADD_NEWLINE(Category.BASIC, R.string.bk_gesture_new_line, R.drawable.ic_two_fingers_right),
    DELETE_WORD(Category.BASIC, R.string.bk_gesture_delete_word, R.drawable.ic_two_fingers_left),
    MOVE_CURSOR_BACKWARD(Category.BASIC, R.string.bk_gesture_move_cursor_backward, R.drawable.ic_one_finger_up),
    MOVE_CURSOR_FORWARD(Category.BASIC, R.string.bk_gesture_move_cursor_forward, R.drawable.ic_one_finger_down),
    HIDE_KEYBOARD(Category.BASIC, R.string.bk_gesture_hide_the_keyboard, R.drawable.ic_two_fingers_down),
    SWITCH_KEYBOARD(Category.BASIC, R.string.bk_gesture_switch_to_next_keyboard, R.drawable.ic_three_fingers_down),
    SUBMIT_TEXT(Category.BASIC, R.string.bk_gesture_submit_text, R.drawable.ic_two_fingers_up),
    HELP_AND_OTHER_ACTIONS(Category.BASIC, R.string.bk_gesture_help_and_other_options, R.drawable.ic_three_fingers_up),
    PREVIOUS_CHARACTER(Category.CURSOR_MOVEMENT, SubCategory.CHARACTER, R.string.bk_gesture_move_to_previous_character),
    NEXT_CHARACTER(Category.CURSOR_MOVEMENT, SubCategory.CHARACTER, R.string.bk_gesture_move_to_next_character),
    PREVIOUS_WORD(Category.CURSOR_MOVEMENT, SubCategory.WORD, R.string.bk_gesture_move_to_previous_word),
    NEXT_WORD(Category.CURSOR_MOVEMENT, SubCategory.WORD, R.string.bk_gesture_move_to_next_word),
    PREVIOUS_LINE(Category.CURSOR_MOVEMENT, SubCategory.LINE, R.string.bk_gesture_move_to_previous_line),
    NEXT_LINE(Category.CURSOR_MOVEMENT, SubCategory.LINE, R.string.bk_gesture_move_to_next_line),
    BEGINNING_OF_PAGE(Category.CURSOR_MOVEMENT, SubCategory.PLACE_ON_PAGE, R.string.bk_gesture_move_to_beginning),
    END_OF_PAGE(Category.CURSOR_MOVEMENT, SubCategory.PLACE_ON_PAGE, R.string.bk_gesture_move_to_end),
    PREVIOUS_GRANULARITY(Category.CURSOR_MOVEMENT, SubCategory.GRANULARITY, R.string.bk_gesture_switch_to_previous_granularity),
    NEXT_GRANULARITY(Category.CURSOR_MOVEMENT, SubCategory.GRANULARITY, R.string.bk_gesture_switch_to_next_granularity),
    PREVIOUS_ITEM(Category.CURSOR_MOVEMENT, SubCategory.GRANULARITY, R.string.bk_gesture_moveh_to_previous_item),
    NEXT_ITEM(Category.CURSOR_MOVEMENT, SubCategory.GRANULARITY, R.string.bk_gesture_move_to_next_item),
    SELECT_PREVIOUS_CHARACTER(Category.TEXT_SELECTION_AND_EDITING, SubCategory.CHARACTER, R.string.bk_gesture_select_previous_character),
    SELECT_NEXT_CHARACTER(Category.TEXT_SELECTION_AND_EDITING, SubCategory.CHARACTER, R.string.bk_gesture_select_next_character),
    SELECT_PREVIOUS_WORD(Category.TEXT_SELECTION_AND_EDITING, SubCategory.WORD, R.string.bk_gesture_select_previous_word),
    SELECT_NEXT_WORD(Category.TEXT_SELECTION_AND_EDITING, SubCategory.WORD, R.string.bk_gesture_select_next_word),
    SELECT_PREVIOUS_LINE(Category.TEXT_SELECTION_AND_EDITING, SubCategory.LINE, R.string.bk_gesture_select_previous_line),
    SELECT_NEXT_LINE(Category.TEXT_SELECTION_AND_EDITING, SubCategory.LINE, R.string.bk_gesture_select_next_line),
    SELECT_ALL(Category.TEXT_SELECTION_AND_EDITING, SubCategory.EDITING, R.string.bk_gesture_select_all),
    SELECT_CURRENT_TO_START(Category.TEXT_SELECTION_AND_EDITING, SubCategory.EDITING, R.string.bk_gesture_select_current_to_start),
    SELECT_CURRENT_TO_END(Category.TEXT_SELECTION_AND_EDITING, SubCategory.EDITING, R.string.bk_gesture_select_current_to_end),
    COPY(Category.TEXT_SELECTION_AND_EDITING, SubCategory.EDITING, R.string.bk_gesture_copy),
    CUT(Category.TEXT_SELECTION_AND_EDITING, SubCategory.EDITING, R.string.bk_gesture_cut),
    PASTE(Category.TEXT_SELECTION_AND_EDITING, SubCategory.EDITING, R.string.bk_gesture_paste),
    PREVIOUS_MISSPELLED_WORD(Category.SPELL_CHECK, R.string.bk_gesture_previous_misspelled_word),
    NEXT_MISSPELLED_WORD(Category.SPELL_CHECK, R.string.bk_gesture_next_misspelled_word),
    HEAR_PREVIOUS_SPELLING_SUGGESTION(Category.SPELL_CHECK, R.string.bk_gesture_previous_suggestion),
    HEAR_NEXT_SPELLING_SUGGESTION(Category.SPELL_CHECK, R.string.bk_gesture_next_suggestion),
    CONFIRM_SPELLING_SUGGESTION(Category.SPELL_CHECK, R.string.bk_gesture_confirm_spelling_suggestion),
    UNDO_SPELLING_SUGGESTION(Category.SPELL_CHECK, R.string.bk_gesture_undo_spelling_suggestion);

    public final Category category;
    public final int descriptionRes;
    public final int iconRes;
    public final SubCategory subCategory;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum SubCategory {
        NONE(0),
        CHARACTER(R.string.bk_pref_category_title_character),
        WORD(R.string.bk_pref_category_title_word),
        LINE(R.string.bk_pref_category_title_line),
        PLACE_ON_PAGE(R.string.bk_pref_category_title_place_on_page),
        EDITING(R.string.bk_pref_category_title_editing),
        GRANULARITY(R.string.bk_pref_category_title_granularity);

        private final int nameRes;

        SubCategory(int i) {
            this.nameRes = i;
        }

        public final String getName(Resources resources) {
            int i = this.nameRes;
            if (i == 0) {
                return "";
            }
            return resources.getString(i);
        }
    }

    BrailleImeActions(Category category, int i) {
        this(category, SubCategory.NONE, i, 0);
    }

    public final boolean isAvailable(Context context) {
        if (this != SELECT_CURRENT_TO_START && this != SELECT_CURRENT_TO_END) {
            return true;
        }
        return BrailleKeyboardConfig.INSTANCE.get().selectCurrentToStartOrEnd(context);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Category {
        BASIC(R.string.braille_keyboard_basic_controls, SubCategory.NONE),
        CURSOR_MOVEMENT(R.string.braille_keyboard_cursor_movement, R.string.braille_keyboard_cursor_movement_description, SubCategory.GRANULARITY, SubCategory.CHARACTER, SubCategory.WORD, SubCategory.LINE, SubCategory.PLACE_ON_PAGE),
        TEXT_SELECTION_AND_EDITING(R.string.braille_keyboard_text_selection_and_editing, SubCategory.CHARACTER, SubCategory.WORD, SubCategory.LINE, SubCategory.EDITING),
        SPELL_CHECK(R.string.braille_keyboard_spell_check, R.string.braille_keyboard_spell_check_description, SubCategory.NONE);

        public final int descriptionRes;
        public final ImmutableList subCategoryList;
        public final int titleRes;

        Category(int i, int i2, SubCategory... subCategoryArr) {
            this.subCategoryList = ImmutableList.copyOf((Collection) Arrays.asList(subCategoryArr));
            this.titleRes = i;
            this.descriptionRes = i2;
        }

        Category(int i, SubCategory... subCategoryArr) {
            this(i, 0, subCategoryArr);
        }
    }

    BrailleImeActions(Category category, int i, int i2) {
        this(category, SubCategory.NONE, i, i2);
    }

    BrailleImeActions(Category category, SubCategory subCategory, int i) {
        this(category, subCategory, i, 0);
    }

    BrailleImeActions(Category category, SubCategory subCategory, int i, int i2) {
        Category category2 = Category.BASIC;
        if (category.subCategoryList.contains(subCategory)) {
            this.category = category;
            this.subCategory = subCategory;
            this.descriptionRes = i;
            this.iconRes = i2;
            return;
        }
        throw new IllegalArgumentException("Category does not have compatible SubCategory: ".concat(String.valueOf(String.valueOf(subCategory))));
    }
}
