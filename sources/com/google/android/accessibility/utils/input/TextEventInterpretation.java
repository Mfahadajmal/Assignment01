package com.google.android.accessibility.utils.input;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.utils.ReadOnly;
import com.google.android.accessibility.utils.StringBuilderUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextEventInterpretation extends ReadOnly {
    public int event;
    public CharSequence mAddedText;
    public CharSequence mDeselectedText;
    public CharSequence mInitialWord;
    public boolean mIsCutAction = false;
    public boolean mIsPasteAction = false;
    private String mReason;
    public CharSequence mRemovedText;
    public CharSequence mSelectedText;
    public CharSequence mTraversedText;
    public CharSequence textOrDescription;

    public TextEventInterpretation(int i) {
        this.event = i;
    }

    public final void setAddedText(CharSequence charSequence) {
        checkIsWritable();
        this.mAddedText = charSequence;
    }

    public final void setEvent(int i) {
        checkIsWritable();
        this.event = i;
    }

    public final void setInitialWord(CharSequence charSequence) {
        checkIsWritable();
        this.mInitialWord = charSequence;
    }

    public final void setInvalid$ar$ds(String str) {
        setEvent(1073741833);
        setReason(str);
    }

    public final void setReason(String str) {
        checkIsWritable();
        this.mReason = str;
    }

    public final void setTraversedText(CharSequence charSequence) {
        checkIsWritable();
        this.mTraversedText = charSequence;
    }

    public final String toString() {
        String str;
        int i = this.event;
        switch (i) {
            case 1073741826:
                str = "TEXT_CLEAR";
                break;
            case 1073741827:
                str = "TEXT_REMOVE";
                break;
            case 1073741828:
                str = "TEXT_ADD";
                break;
            case 1073741829:
                str = "TEXT_REPLACE";
                break;
            case 1073741830:
                str = "TEXT_PASSWORD_ADD";
                break;
            case 1073741831:
                str = "TEXT_PASSWORD_REMOVE";
                break;
            case 1073741832:
                str = "TEXT_PASSWORD_REPLACE";
                break;
            case 1073741833:
                str = "CHANGE_INVALID";
                break;
            case 1073741834:
                str = "SELECTION_FOCUS_EDIT_TEXT";
                break;
            case 1073741835:
                str = "SELECTION_MOVE_CURSOR_TO_BEGINNING";
                break;
            case 1073741836:
                str = "SELECTION_MOVE_CURSOR_TO_END";
                break;
            case 1073741837:
                str = "SELECTION_MOVE_CURSOR_NO_SELECTION";
                break;
            case 1073741838:
                str = "SELECTION_MOVE_CURSOR_WITH_SELECTION";
                break;
            case 1073741839:
                str = "SELECTION_MOVE_CURSOR_SELECTION_CLEARED";
                break;
            case 1073741840:
                str = "SELECTION_CUT";
                break;
            case 1073741841:
                str = "SELECTION_PASTE";
                break;
            case 1073741842:
                str = "SELECTION_TEXT_TRAVERSAL";
                break;
            case 1073741843:
                str = "SELECTION_SELECT_ALL";
                break;
            case 1073741844:
                str = "SELECTION_SELECT_ALL_WITH_KEYBOARD";
                break;
            case 1073741845:
                str = "SELECTION_RESET_SELECTION";
                break;
            case 1073741846:
                str = "SET_TEXT_BY_ACTION";
                break;
            default:
                str = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "(unknown event ", ")");
                break;
        }
        return StringBuilderUtils.joinFields(StringBuilderUtils.optionalField("Event", str), StringBuilderUtils.optionalText("Reason", this.mReason), StringBuilderUtils.optionalTag("isCut", this.mIsCutAction), StringBuilderUtils.optionalTag("isPaste", this.mIsPasteAction), StringBuilderUtils.optionalText("textOrDescription", this.textOrDescription), StringBuilderUtils.optionalText("removedText", this.mRemovedText), StringBuilderUtils.optionalText("addedText", this.mAddedText), StringBuilderUtils.optionalText("initialWord", this.mInitialWord), StringBuilderUtils.optionalText("deselectedText", this.mDeselectedText), StringBuilderUtils.optionalText("selectedText", this.mSelectedText), StringBuilderUtils.optionalText("traversedText", this.mTraversedText));
    }
}
