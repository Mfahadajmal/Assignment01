package com.google.android.accessibility.talkback.focusmanagement.action;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavigationAction {
    public final int actionType;
    public final int autoScrollAttempt;
    public final AccessibilityNodeInfoCompat fallbackTarget;
    public final int inputMode;
    public final CursorGranularity originalNavigationGranularity;
    public final int prevScrollDeltaSumX;
    public final int prevScrollDeltaSumY;
    public final int searchDirection;
    public final boolean shouldScroll;
    public final boolean shouldWrap;
    public final int targetType;
    public final boolean useInputFocusAsPivotIfEmpty;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public int actionType = 0;
        public int targetType = 0;
        public int inputMode = -1;
        public int searchDirection = 0;
        public boolean shouldWrap = false;
        public boolean shouldScroll = false;
        public boolean useInputFocusAsPivotIfEmpty = false;
        public CursorGranularity originalNavigationGranularity = null;
        public int autoScrollAttempt = 0;
        public int prevScrollDeltaSumX = 0;
        public int prevScrollDeltaSumY = 0;
        public AccessibilityNodeInfoCompat fallbackTarget = null;

        public static Builder copy(NavigationAction navigationAction) {
            Builder builder = new Builder();
            builder.actionType = navigationAction.actionType;
            builder.searchDirection = navigationAction.searchDirection;
            builder.targetType = navigationAction.targetType;
            builder.inputMode = navigationAction.inputMode;
            builder.shouldWrap = navigationAction.shouldWrap;
            builder.shouldScroll = navigationAction.shouldScroll;
            builder.useInputFocusAsPivotIfEmpty = navigationAction.useInputFocusAsPivotIfEmpty;
            builder.originalNavigationGranularity = navigationAction.originalNavigationGranularity;
            builder.autoScrollAttempt = navigationAction.autoScrollAttempt;
            builder.prevScrollDeltaSumX = navigationAction.prevScrollDeltaSumX;
            builder.prevScrollDeltaSumY = navigationAction.prevScrollDeltaSumY;
            builder.fallbackTarget = navigationAction.fallbackTarget;
            return builder;
        }
    }

    public NavigationAction(Builder builder) {
        this.actionType = builder.actionType;
        this.searchDirection = builder.searchDirection;
        this.targetType = builder.targetType;
        this.inputMode = builder.inputMode;
        this.shouldWrap = builder.shouldWrap;
        this.shouldScroll = builder.shouldScroll;
        this.useInputFocusAsPivotIfEmpty = builder.useInputFocusAsPivotIfEmpty;
        this.originalNavigationGranularity = builder.originalNavigationGranularity;
        this.autoScrollAttempt = builder.autoScrollAttempt;
        this.prevScrollDeltaSumX = builder.prevScrollDeltaSumX;
        this.prevScrollDeltaSumY = builder.prevScrollDeltaSumY;
        this.fallbackTarget = builder.fallbackTarget;
    }

    public static String actionTypeToString(int i) {
        switch (i) {
            case 1:
                return "DIRECTIONAL_NAVIGATION";
            case 2:
                return "JUMP_TO_TOP";
            case 3:
                return "JUMP_TO_BOTTOM";
            case 4:
                return "SCROLL_FORWARD";
            case 5:
                return "SCROLL_BACKWARD";
            case 6:
                return "SCROLL_UP";
            case 7:
                return "SCROLL_DOWN";
            case 8:
                return "SCROLL_LEFT";
            case 9:
                return "SCROLL_RIGHT";
            default:
                return "UNKNOWN";
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof NavigationAction)) {
            return false;
        }
        NavigationAction navigationAction = (NavigationAction) obj;
        if (this.actionType != navigationAction.actionType || this.searchDirection != navigationAction.searchDirection || this.targetType != navigationAction.targetType || this.inputMode != navigationAction.inputMode || this.shouldWrap != navigationAction.shouldWrap || this.shouldScroll != navigationAction.shouldScroll || this.useInputFocusAsPivotIfEmpty != navigationAction.useInputFocusAsPivotIfEmpty || this.originalNavigationGranularity != navigationAction.originalNavigationGranularity || this.autoScrollAttempt != navigationAction.autoScrollAttempt || this.prevScrollDeltaSumX != navigationAction.prevScrollDeltaSumX || this.prevScrollDeltaSumY != navigationAction.prevScrollDeltaSumY || this.fallbackTarget != navigationAction.fallbackTarget) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.actionType), Integer.valueOf(this.searchDirection), Integer.valueOf(this.targetType), Integer.valueOf(this.inputMode), Boolean.valueOf(this.shouldWrap), Boolean.valueOf(this.shouldScroll), Boolean.valueOf(this.useInputFocusAsPivotIfEmpty), this.originalNavigationGranularity, Integer.valueOf(this.autoScrollAttempt), Integer.valueOf(this.prevScrollDeltaSumX), Integer.valueOf(this.prevScrollDeltaSumY), this.fallbackTarget);
    }

    public final String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder("NavigationAction{actionType=");
        sb.append(actionTypeToString(this.actionType));
        sb.append(", targetType=");
        int i = this.targetType;
        if (i != 0) {
            if (i != 201) {
                if (i != 202) {
                    switch (i) {
                        case 65641:
                            str = "TARGET_HTML_ELEMENT_LIST";
                            break;
                        case 65642:
                            str = "TARGET_HTML_ELEMENT_BUTTON";
                            break;
                        case 65643:
                            str = "TARGET_HTML_ELEMENT_CHECKBOX";
                            break;
                        case 65644:
                            str = "TARGET_HTML_ELEMENT_ARIA_LANDMARK";
                            break;
                        case 65645:
                            str = "TARGET_HTML_ELEMENT_EDIT_FIELD";
                            break;
                        case 65646:
                            str = "TARGET_HTML_ELEMENT_FOCUSABLE_ITEM";
                            break;
                        case 65647:
                            str = "TARGET_HTML_ELEMENT_HEADING_1";
                            break;
                        case 65648:
                            str = "TARGET_HTML_ELEMENT_HEADING_2";
                            break;
                        case 65649:
                            str = "TARGET_HTML_ELEMENT_HEADING_3";
                            break;
                        case 65650:
                            str = "TARGET_HTML_ELEMENT_HEADING_4";
                            break;
                        case 65651:
                            str = "TARGET_HTML_ELEMENT_HEADING_5";
                            break;
                        case 65652:
                            str = "TARGET_HTML_ELEMENT_HEADING_6";
                            break;
                        case 65653:
                            str = "TARGET_HTML_ELEMENT_GRAPHIC";
                            break;
                        case 65654:
                            str = "TARGET_HTML_ELEMENT_LIST_ITEM";
                            break;
                        case 65655:
                            str = "TARGET_HTML_ELEMENT_TABLE";
                            break;
                        case 65656:
                            str = "TARGET_HTML_ELEMENT_COMBOBOX";
                            break;
                        default:
                            switch (i) {
                                case 262145:
                                    str = "TARGET_HTML_ELEMENT_LINK";
                                    break;
                                case 262146:
                                    str = "TARGET_HTML_ELEMENT_CONTROL";
                                    break;
                                case 262147:
                                    str = "TARGET_HTML_ELEMENT_HEADING";
                                    break;
                                default:
                                    switch (i) {
                                        case 1048577:
                                            str = "TARGET_HEADING";
                                            break;
                                        case 1048578:
                                            str = "TARGET_CONTROL";
                                            break;
                                        case 1048579:
                                            str = "TARGET_LINK";
                                            break;
                                        default:
                                            str = "UNKNOWN";
                                            break;
                                    }
                            }
                    }
                } else {
                    str = "TARGET_CONTAINER";
                }
            } else {
                str = "TARGET_WINDOW";
            }
        } else {
            str = "TARGET_DEFAULT";
        }
        sb.append(str);
        sb.append(", inputMode=");
        int i2 = this.inputMode;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                str2 = "INPUT_MODE_UNKNOWN";
                            } else {
                                str2 = "INPUT_MODE_BRAILLE_KEYBOARD";
                            }
                        } else {
                            str2 = "INPUT_MODE_BRAILLE_DISPLAY";
                        }
                    } else {
                        str2 = "INPUT_MODE_NON_ALPHABETIC_KEYBOARD";
                    }
                } else {
                    str2 = "INPUT_MODE_TV_REMOTE";
                }
            } else {
                str2 = "INPUT_MODE_KEYBOARD";
            }
        } else {
            str2 = "INPUT_MODE_TOUCH";
        }
        sb.append(str2);
        sb.append(", searchDirection=");
        sb.append(TraversalStrategyUtils.directionToString(this.searchDirection));
        sb.append(", shouldWrap=");
        sb.append(this.shouldWrap);
        sb.append(", shouldScroll=");
        sb.append(this.shouldScroll);
        sb.append(", useInputFocusAsPivotIfEmpty=");
        sb.append(this.useInputFocusAsPivotIfEmpty);
        sb.append(", originalNavigationGranularity=");
        sb.append(this.originalNavigationGranularity);
        sb.append(", autoScrollAttempt=");
        sb.append(this.autoScrollAttempt);
        sb.append(", prevScrollDeltaSumX=");
        sb.append(this.prevScrollDeltaSumX);
        sb.append(", prevScrollDeltaSumY=");
        sb.append(this.prevScrollDeltaSumY);
        sb.append(", fallbackTarget=");
        sb.append(this.fallbackTarget);
        sb.append('}');
        return sb.toString();
    }
}
