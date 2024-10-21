package com.google.android.accessibility.talkback;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$ID extends SpannableUtils$NonCopyableTextSpan {
    public final Value value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Value {
        SCROLL_CANCEL_TIMEOUT,
        CONTINUOUS_READ_CONTENT_FOCUSED,
        CONTINUOUS_READ_INTERRUPT,
        CONTINUOUS_READ_IGNORE,
        STATE_CHANGE,
        PASS_THROUGH_INTERACTION_START,
        PASS_THROUGH_INTERACTION_END,
        ACCESSIBILITY_FOCUSED,
        SUBTREE_CHANGED,
        ACCESSIBILITY_EVENT_IDLE,
        SPELLING_SUGGESTION_HINT
    }

    public Interpretation$ID(Value value) {
        super((byte[]) null);
        this.value = value;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Interpretation$ID) || this.value != ((Interpretation$ID) obj).value) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(this.value);
    }

    public final String toString() {
        Value value = this.value;
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
