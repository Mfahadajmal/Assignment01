package com.google.android.accessibility.talkback.focusmanagement.record;

import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusActionInfo {
    public final boolean forceMuteFeedback;
    public final int initialFocusType;
    public final boolean isFromRefocusAction;
    public final NavigationAction navigationAction;
    public final int sourceAction;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public boolean forceMuteFeedback;
        public int initialFocusType;
        public boolean isFromRefocusAction;
        public NavigationAction navigationAction;
        public int sourceAction;

        public Builder() {
            this.sourceAction = 0;
            this.isFromRefocusAction = false;
            this.navigationAction = null;
            this.initialFocusType = 0;
            this.forceMuteFeedback = false;
        }

        public Builder(FocusActionInfo focusActionInfo) {
            this.sourceAction = 0;
            this.isFromRefocusAction = false;
            this.navigationAction = null;
            this.initialFocusType = 0;
            this.forceMuteFeedback = false;
            this.sourceAction = focusActionInfo.sourceAction;
            this.isFromRefocusAction = focusActionInfo.isFromRefocusAction;
            this.navigationAction = focusActionInfo.navigationAction;
            this.initialFocusType = focusActionInfo.initialFocusType;
            this.forceMuteFeedback = focusActionInfo.forceMuteFeedback;
        }
    }

    public FocusActionInfo(Builder builder) {
        this.sourceAction = builder.sourceAction;
        this.isFromRefocusAction = builder.isFromRefocusAction;
        this.navigationAction = builder.navigationAction;
        this.initialFocusType = builder.initialFocusType;
        this.forceMuteFeedback = builder.forceMuteFeedback;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FocusActionInfo)) {
            return false;
        }
        FocusActionInfo focusActionInfo = (FocusActionInfo) obj;
        if (this.forceMuteFeedback != focusActionInfo.forceMuteFeedback || this.isFromRefocusAction != focusActionInfo.isFromRefocusAction || this.initialFocusType != focusActionInfo.initialFocusType || this.sourceAction != focusActionInfo.sourceAction || !Objects.equals(this.navigationAction, focusActionInfo.navigationAction)) {
            return false;
        }
        return true;
    }

    public final boolean forceFeedbackEvenIfAudioPlaybackActive() {
        if (this.sourceAction != 0) {
            return true;
        }
        return false;
    }

    public final boolean forceFeedbackEvenIfMicrophoneActive() {
        if (this.sourceAction != 0) {
            return true;
        }
        return false;
    }

    public final boolean forceFeedbackEvenIfSsbActive() {
        int i = this.sourceAction;
        if (i != 2 && i != 4) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.sourceAction), Boolean.valueOf(this.isFromRefocusAction), this.navigationAction, Integer.valueOf(this.initialFocusType), Boolean.valueOf(this.forceMuteFeedback));
    }

    public final String toString() {
        String str;
        String str2;
        switch (this.sourceAction) {
            case 1:
                str = "MANUAL_SCROLL";
                break;
            case 2:
                str = "TOUCH_EXPLORATION";
                break;
            case 3:
                str = "FOCUS_SYNCHRONIZATION";
                break;
            case 4:
                str = "LOGICAL_NAVIGATION";
                break;
            case 5:
                str = "SCREEN_STATE_CHANGE";
                break;
            case 6:
                str = "ENSURE_ON_SCREEN";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        String optionalField = StringBuilderUtils.optionalField("sourceAction", str);
        String optionalTag = StringBuilderUtils.optionalTag("isFromRefocusAction", this.isFromRefocusAction);
        String optionalSubObj = StringBuilderUtils.optionalSubObj("navigationAction", this.navigationAction);
        int i = this.initialFocusType;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        str2 = "UNDEFINED";
                    } else {
                        str2 = "REQUESTED_INITIAL_NODE";
                    }
                } else {
                    str2 = "SYNCED_INPUT_FOCUS";
                }
            } else {
                str2 = "RESTORED_LAST_FOCUS";
            }
        } else {
            str2 = "FIRST_FOCUSABLE_NODE";
        }
        return StringBuilderUtils.joinFields("FocusActionInfo{", optionalField, optionalTag, optionalSubObj, StringBuilderUtils.optionalField("initialFocusType", str2), StringBuilderUtils.optionalTag("forceMuteFeedback", this.forceMuteFeedback), StringBuilderUtils.optionalTag("forceFeedbackEvenIfAudioPlaybackActive", forceFeedbackEvenIfAudioPlaybackActive()), StringBuilderUtils.optionalTag("forceFeedbackEvenIfMicrophoneActive", forceFeedbackEvenIfMicrophoneActive()), StringBuilderUtils.optionalTag("forceFeedbackEvenIfSsbActive", forceFeedbackEvenIfSsbActive()), "}");
    }
}
