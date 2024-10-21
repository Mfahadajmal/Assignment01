package com.google.android.accessibility.talkback;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.ScrollEventInterpreter;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$Scroll extends SpannableUtils$NonCopyableTextSpan {
    public final ScrollEventInterpreter.ScrollEventInterpretation scroll;

    public Interpretation$Scroll(ScrollEventInterpreter.ScrollEventInterpretation scrollEventInterpretation) {
        super((byte[]) null);
        this.scroll = scrollEventInterpretation;
    }

    public final boolean equals(Object obj) {
        Interpretation$Scroll interpretation$Scroll = (Interpretation$Scroll) SpannableUtils$NonCopyableTextSpan.castOrNull(obj, Interpretation$Scroll.class);
        if (interpretation$Scroll != null && Objects.equals(this.scroll, interpretation$Scroll.scroll)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.scroll);
    }

    public final String toString() {
        return this.scroll.toString();
    }
}
