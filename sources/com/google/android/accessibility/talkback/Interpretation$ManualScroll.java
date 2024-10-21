package com.google.android.accessibility.talkback;

import android.os.Bundle;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$ManualScroll extends SpannableUtils$NonCopyableTextSpan {
    private final AccessibilityNodeInfoCompat currentFocusedNode;
    private final int direction;
    private final ScreenState screenState;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object Interpretation$ManualScroll$Builder$ar$currentFocusedNode;
        public Object Interpretation$ManualScroll$Builder$ar$screenState;
        public int direction;
        public byte set$0;

        public Builder() {
        }

        public final Feedback.NodeAction build() {
            Object obj;
            if (this.set$0 == 1 && (obj = this.Interpretation$ManualScroll$Builder$ar$screenState) != null) {
                return new Feedback.NodeAction((AccessibilityNode) obj, this.direction, (Bundle) this.Interpretation$ManualScroll$Builder$ar$currentFocusedNode);
            }
            StringBuilder sb = new StringBuilder();
            if (this.Interpretation$ManualScroll$Builder$ar$screenState == null) {
                sb.append(" target");
            }
            if (this.set$0 == 0) {
                sb.append(" actionId");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final void setActionId$ar$ds(int i) {
            this.direction = i;
            this.set$0 = (byte) 1;
        }

        public final void setTarget$ar$ds(AccessibilityNode accessibilityNode) {
            if (accessibilityNode != null) {
                this.Interpretation$ManualScroll$Builder$ar$screenState = accessibilityNode;
                return;
            }
            throw new NullPointerException("Null target");
        }

        public Builder(byte[] bArr) {
            this();
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            this();
        }
    }

    public Interpretation$ManualScroll() {
        super((byte[]) null);
    }

    public final AccessibilityNodeInfoCompat currentFocusedNode() {
        return this.currentFocusedNode;
    }

    public final int direction() {
        return this.direction;
    }

    public final boolean equals(Object obj) {
        ScreenState screenState;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Interpretation$ManualScroll) {
            Interpretation$ManualScroll interpretation$ManualScroll = (Interpretation$ManualScroll) obj;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.currentFocusedNode;
            if (accessibilityNodeInfoCompat != null ? accessibilityNodeInfoCompat.equals(interpretation$ManualScroll.currentFocusedNode()) : interpretation$ManualScroll.currentFocusedNode() == null) {
                if (this.direction == interpretation$ManualScroll.direction() && ((screenState = this.screenState) != null ? screenState.equals(interpretation$ManualScroll.screenState()) : interpretation$ManualScroll.screenState() == null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.currentFocusedNode;
        int i = 0;
        if (accessibilityNodeInfoCompat == null) {
            hashCode = 0;
        } else {
            hashCode = accessibilityNodeInfoCompat.hashCode();
        }
        int i2 = this.direction;
        ScreenState screenState = this.screenState;
        if (screenState != null) {
            i = screenState.hashCode();
        }
        return ((((hashCode ^ 1000003) * 1000003) ^ i2) * 1000003) ^ i;
    }

    public final ScreenState screenState() {
        return this.screenState;
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("ManualScroll{", StringBuilderUtils.optionalSubObj("currentNode", currentFocusedNode()), StringBuilderUtils.optionalField("direction", TraversalStrategyUtils.directionToString(direction())), StringBuilderUtils.optionalSubObj("screenState", screenState()), "}");
    }

    public Interpretation$ManualScroll(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, ScreenState screenState) {
        this();
        this.currentFocusedNode = accessibilityNodeInfoCompat;
        this.direction = i;
        this.screenState = screenState;
    }
}
