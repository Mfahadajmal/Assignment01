package com.google.android.accessibility.talkback;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$CompositorID extends SpannableUtils$NonCopyableTextSpan {
    public final EventInterpretation eventInterpretation;
    private AccessibilityNodeInfoCompat node;
    public final int value;

    public Interpretation$CompositorID(int i) {
        super((byte[]) null);
        this.value = i;
        this.eventInterpretation = null;
        this.node = null;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == null || !(obj instanceof Interpretation$CompositorID)) {
            return false;
        }
        Interpretation$CompositorID interpretation$CompositorID = (Interpretation$CompositorID) obj;
        if (this.value != interpretation$CompositorID.value) {
            return false;
        }
        EventInterpretation eventInterpretation = this.eventInterpretation;
        if (eventInterpretation == null) {
            z = true;
        } else {
            z = false;
        }
        EventInterpretation eventInterpretation2 = interpretation$CompositorID.eventInterpretation;
        if (eventInterpretation2 == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z ^ z2) {
            return false;
        }
        if (eventInterpretation != null && !eventInterpretation.toString().equals(eventInterpretation2.toString())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.value), this.eventInterpretation, null);
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("CompositorID{", StringBuilderUtils.optionalInt("value", this.value, 1073741924), StringBuilderUtils.optionalSubObj("eventInterp", this.eventInterpretation), StringBuilderUtils.optionalSubObj("node", null), "}");
    }

    public Interpretation$CompositorID(int i, EventInterpretation eventInterpretation) {
        super((byte[]) null);
        this.value = i;
        this.eventInterpretation = eventInterpretation;
    }
}
