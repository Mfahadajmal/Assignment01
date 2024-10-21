package com.google.android.accessibility.utils.input;

import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.StringBuilderUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectionEventInterpreter implements AccessibilityEventListener {
    private long windowChangeTimeInMillis = -1;
    public final List listeners = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Interpretation {
        public final AccessibilityEvent event;
        public final Performance.EventId eventId;
        public final boolean isSelected;
        public final boolean isTransitional;

        public Interpretation(AccessibilityEvent accessibilityEvent, Performance.EventId eventId, boolean z, boolean z2) {
            this.event = accessibilityEvent;
            this.eventId = eventId;
            this.isTransitional = z;
            this.isSelected = z2;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(this.eventId.toString(), StringBuilderUtils.optionalTag("transition", this.isTransitional), StringBuilderUtils.optionalTag("selected", this.isSelected));
        }
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 4196356;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0082 A[LOOP:0: B:22:0x007c->B:24:0x0082, LOOP_END] */
    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAccessibilityEvent(android.view.accessibility.AccessibilityEvent r12, com.google.android.accessibility.utils.Performance.EventId r13) {
        /*
            r11 = this;
            int r0 = r12.getEventType()
            r1 = 4194304(0x400000, float:5.877472E-39)
            if (r0 != r1) goto Lf
            long r12 = android.os.SystemClock.uptimeMillis()
            r11.windowChangeTimeInMillis = r12
            return
        Lf:
            r1 = 2048(0x800, float:2.87E-42)
            java.lang.String r2 = "SelectionInterpreter"
            r3 = 2
            r4 = 1
            r5 = 0
            if (r0 != r1) goto L3b
            int r0 = r12.getContentChangeTypes()
            if (r0 == 0) goto L20
            goto L8c
        L20:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.sourceCompat(r12)
            int r1 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRole(r0)
            if (r1 == r3) goto L32
            java.lang.Object[] r12 = new java.lang.Object[r5]
            java.lang.String r13 = "Skip ROLE_CHECK_BOX for de-selection"
            com.google.android.libraries.accessibility.utils.log.LogUtils.v(r2, r13, r12)
            return
        L32:
            if (r0 == 0) goto L8c
            boolean r0 = r0.isSelected()
            if (r0 != 0) goto L8c
            goto L4c
        L3b:
            r1 = 4
            if (r0 != r1) goto L8c
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.sourceCompat(r12)
            if (r0 == 0) goto L4c
            boolean r0 = r0.isSelected()
            if (r0 == 0) goto L4c
            r0 = r4
            goto L4d
        L4c:
            r0 = r5
        L4d:
            com.google.android.accessibility.utils.input.SelectionEventInterpreter$Interpretation r1 = new com.google.android.accessibility.utils.input.SelectionEventInterpreter$Interpretation
            long r6 = android.os.SystemClock.uptimeMillis()
            java.lang.Long r8 = java.lang.Long.valueOf(r6)
            long r9 = r11.windowChangeTimeInMillis
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r5] = r8
            r3[r4] = r9
            java.lang.String r8 = "TYPE_VIEW_SELECTED time=%d and TYPE_WINDOWS_CHANGED time=%d"
            com.google.android.libraries.accessibility.utils.log.LogUtils.v(r2, r8, r3)
            long r2 = r11.windowChangeTimeInMillis
            long r6 = r6 - r2
            r2 = 100
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 > 0) goto L72
            goto L73
        L72:
            r4 = r5
        L73:
            r1.<init>(r12, r13, r4, r0)
            java.util.List r12 = r11.listeners
            java.util.Iterator r12 = r12.iterator()
        L7c:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L8c
            java.lang.Object r13 = r12.next()
            com.google.android.accessibility.utils.Consumer r13 = (com.google.android.accessibility.utils.Consumer) r13
            r13.accept(r1)
            goto L7c
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.input.SelectionEventInterpreter.onAccessibilityEvent(android.view.accessibility.AccessibilityEvent, com.google.android.accessibility.utils.Performance$EventId):void");
    }
}
