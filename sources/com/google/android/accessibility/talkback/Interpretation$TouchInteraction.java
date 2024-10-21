package com.google.android.accessibility.talkback;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$TouchInteraction extends SpannableUtils$NonCopyableTextSpan {
    private final boolean interactionActive;

    public Interpretation$TouchInteraction() {
        super((byte[]) null);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof Interpretation$TouchInteraction) && this.interactionActive == ((Interpretation$TouchInteraction) obj).interactionActive()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        if (true != this.interactionActive) {
            i = 1237;
        } else {
            i = 1231;
        }
        return i ^ 1000003;
    }

    public final boolean interactionActive() {
        return this.interactionActive;
    }

    public final String toString() {
        return "TouchInteraction{interactionActive=" + this.interactionActive + "}";
    }

    public Interpretation$TouchInteraction(boolean z) {
        this();
        this.interactionActive = z;
    }
}
