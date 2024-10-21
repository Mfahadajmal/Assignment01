package com.google.android.accessibility.talkback;

import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$AccessibilityFocused extends SpannableUtils$NonCopyableTextSpan {
    private final FocusActionInfo focusActionInfo;
    private final boolean needsCaption;

    public Interpretation$AccessibilityFocused() {
        super((byte[]) null);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Interpretation$AccessibilityFocused) {
            Interpretation$AccessibilityFocused interpretation$AccessibilityFocused = (Interpretation$AccessibilityFocused) obj;
            FocusActionInfo focusActionInfo = this.focusActionInfo;
            if (focusActionInfo != null ? focusActionInfo.equals(interpretation$AccessibilityFocused.focusActionInfo()) : interpretation$AccessibilityFocused.focusActionInfo() == null) {
                if (this.needsCaption == interpretation$AccessibilityFocused.needsCaption()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final FocusActionInfo focusActionInfo() {
        return this.focusActionInfo;
    }

    public final int hashCode() {
        int hashCode;
        int i;
        FocusActionInfo focusActionInfo = this.focusActionInfo;
        if (focusActionInfo == null) {
            hashCode = 0;
        } else {
            hashCode = focusActionInfo.hashCode();
        }
        if (true != this.needsCaption) {
            i = 1237;
        } else {
            i = 1231;
        }
        return ((hashCode ^ 1000003) * 1000003) ^ i;
    }

    public final boolean needsCaption() {
        return this.needsCaption;
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("AccessibilityFocused{", StringBuilderUtils.optionalField("focusActionInfo=", focusActionInfo()), StringBuilderUtils.optionalTag("needsCaption", needsCaption()), "}");
    }

    public Interpretation$AccessibilityFocused(FocusActionInfo focusActionInfo, boolean z) {
        this();
        this.focusActionInfo = focusActionInfo;
        this.needsCaption = z;
    }
}
