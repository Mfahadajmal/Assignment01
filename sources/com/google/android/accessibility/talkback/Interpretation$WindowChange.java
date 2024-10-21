package com.google.android.accessibility.talkback;

import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$WindowChange extends SpannableUtils$NonCopyableTextSpan {
    private final boolean forceRestoreFocus;
    private final ScreenState screenState;

    public Interpretation$WindowChange() {
        super((byte[]) null);
    }

    public final boolean equals(Object obj) {
        ScreenState screenState;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Interpretation$WindowChange) {
            Interpretation$WindowChange interpretation$WindowChange = (Interpretation$WindowChange) obj;
            if (this.forceRestoreFocus == interpretation$WindowChange.forceRestoreFocus() && ((screenState = this.screenState) != null ? screenState.equals(interpretation$WindowChange.screenState()) : interpretation$WindowChange.screenState() == null)) {
                return true;
            }
        }
        return false;
    }

    public final boolean forceRestoreFocus() {
        return this.forceRestoreFocus;
    }

    public final int hashCode() {
        int hashCode;
        int i;
        ScreenState screenState = this.screenState;
        if (screenState == null) {
            hashCode = 0;
        } else {
            hashCode = screenState.hashCode();
        }
        if (true != this.forceRestoreFocus) {
            i = 1237;
        } else {
            i = 1231;
        }
        return hashCode ^ ((i ^ 1000003) * 1000003);
    }

    public final ScreenState screenState() {
        return this.screenState;
    }

    public final String toString() {
        return "WindowChange{forceRestoreFocus=" + this.forceRestoreFocus + ", screenState=" + String.valueOf(this.screenState) + "}";
    }

    public Interpretation$WindowChange(boolean z, ScreenState screenState) {
        this();
        this.forceRestoreFocus = z;
        this.screenState = screenState;
    }
}
