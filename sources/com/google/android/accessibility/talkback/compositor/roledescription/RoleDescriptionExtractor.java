package com.google.android.accessibility.talkback.compositor.roledescription;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RoleDescriptionExtractor {
    private final Context context;
    private final EditTextDescription defaultDescription$ar$class_merging;
    private final EditTextDescription editTextDescription;
    private final GlobalVariables globalVariables;
    private final ExecutionList.RunnableExecutorPair imageContents$ar$class_merging$ar$class_merging;
    private final EditTextDescription nonTextViewsDescription$ar$class_merging;
    private final EditTextDescription pagerPageDescription$ar$class_merging;
    private final SeekBarDescription seekBarDescription;
    private final EditTextDescription switchDescription$ar$class_merging;

    public RoleDescriptionExtractor(Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, GlobalVariables globalVariables) {
        this.context = context;
        this.imageContents$ar$class_merging$ar$class_merging = runnableExecutorPair;
        this.globalVariables = globalVariables;
        this.defaultDescription$ar$class_merging = new EditTextDescription(runnableExecutorPair, 1);
        this.editTextDescription = new EditTextDescription(runnableExecutorPair, 0);
        this.nonTextViewsDescription$ar$class_merging = new EditTextDescription(runnableExecutorPair, 2);
        this.pagerPageDescription$ar$class_merging = new EditTextDescription(runnableExecutorPair, 3);
        this.seekBarDescription = new SeekBarDescription(runnableExecutorPair);
        this.switchDescription$ar$class_merging = new EditTextDescription(runnableExecutorPair, 4);
    }

    public final CharSequence getSeekBarStateDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityEvent accessibilityEvent) {
        return SeekBarDescription.stateDescription(accessibilityEvent, accessibilityNodeInfoCompat, this.context, this.globalVariables);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0080 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.CharSequence nodeRoleDescriptionText(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r8, android.view.accessibility.AccessibilityEvent r9) {
        /*
            r7 = this;
            android.content.Context r0 = r7.context
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r1 = r7.imageContents$ar$class_merging$ar$class_merging
            com.google.android.accessibility.talkback.compositor.GlobalVariables r2 = r7.globalVariables
            int r3 = r2.descriptionOrder
            int r4 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRole(r8)
            java.lang.CharSequence r0 = com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.getUnlabelledNodeDescription$ar$class_merging$ar$class_merging(r4, r8, r0, r1, r2)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L18
            goto Ldd
        L18:
            int r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRole(r8)
            r1 = 3
            r2 = 1
            if (r0 == r1) goto L78
            r1 = 4
            if (r0 == r1) goto L75
            r1 = 6
            if (r0 == r1) goto L72
            r1 = 7
            if (r0 == r1) goto L72
            r1 = 10
            if (r0 == r1) goto L6f
            r1 = 11
            if (r0 == r1) goto L6c
            r1 = 13
            if (r0 == r1) goto L6c
            r1 = 16
            if (r0 == r1) goto L69
            r4 = 18
            if (r0 == r4) goto L6f
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r0 = r8.getParent()
            if (r0 == 0) goto L78
            int r4 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getRole(r0)
            if (r4 != r1) goto L78
            java.lang.Class r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.CLASS_TOUCHWIZ_TWADAPTERVIEW
            int r1 = r0.getChildCount()
            r4 = 0
            r5 = r4
        L51:
            if (r4 >= r1) goto L64
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r6 = r0.getChild(r4)
            if (r6 == 0) goto L61
            boolean r6 = r6.isVisibleToUser()
            if (r6 == 0) goto L61
            int r5 = r5 + 1
        L61:
            int r4 = r4 + 1
            goto L51
        L64:
            if (r5 != r2) goto L78
            com.google.android.accessibility.talkback.compositor.roledescription.EditTextDescription r0 = r7.pagerPageDescription$ar$class_merging
            goto L7a
        L69:
            com.google.android.accessibility.talkback.compositor.roledescription.EditTextDescription r0 = r7.pagerPageDescription$ar$class_merging
            goto L7a
        L6c:
            com.google.android.accessibility.talkback.compositor.roledescription.EditTextDescription r0 = r7.switchDescription$ar$class_merging
            goto L7a
        L6f:
            com.google.android.accessibility.talkback.compositor.roledescription.SeekBarDescription r0 = r7.seekBarDescription
            goto L7a
        L72:
            com.google.android.accessibility.talkback.compositor.roledescription.EditTextDescription r0 = r7.nonTextViewsDescription$ar$class_merging
            goto L7a
        L75:
            com.google.android.accessibility.talkback.compositor.roledescription.EditTextDescription r0 = r7.editTextDescription
            goto L7a
        L78:
            com.google.android.accessibility.talkback.compositor.roledescription.EditTextDescription r0 = r7.defaultDescription$ar$class_merging
        L7a:
            boolean r1 = r0.shouldIgnoreDescription(r8)
            if (r1 == 0) goto L83
            java.lang.String r0 = ""
            goto Ldd
        L83:
            if (r3 == 0) goto Lc1
            if (r3 == r2) goto La4
            android.content.Context r1 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r2 = r7.globalVariables
            java.lang.CharSequence r1 = r0.nodeName(r8, r1, r2)
            android.content.Context r2 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r3 = r7.globalVariables
            java.lang.CharSequence r2 = r0.nodeRole(r8, r2, r3)
            android.content.Context r3 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r4 = r7.globalVariables
            java.lang.CharSequence r8 = r0.nodeState(r9, r8, r3, r4)
            java.lang.CharSequence r0 = com.google.android.accessibility.talkback.compositor.CompositorUtils.dedupJoin(r1, r2, r8)
            goto Ldd
        La4:
            android.content.Context r1 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r2 = r7.globalVariables
            java.lang.CharSequence r9 = r0.nodeState(r9, r8, r1, r2)
            android.content.Context r1 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r2 = r7.globalVariables
            java.lang.CharSequence r1 = r0.nodeName(r8, r1, r2)
            android.content.Context r2 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r3 = r7.globalVariables
            java.lang.CharSequence r8 = r0.nodeRole(r8, r2, r3)
            java.lang.CharSequence r0 = com.google.android.accessibility.talkback.compositor.CompositorUtils.dedupJoin(r9, r1, r8)
            goto Ldd
        Lc1:
            android.content.Context r1 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r2 = r7.globalVariables
            java.lang.CharSequence r1 = r0.nodeRole(r8, r1, r2)
            android.content.Context r2 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r3 = r7.globalVariables
            java.lang.CharSequence r2 = r0.nodeName(r8, r2, r3)
            android.content.Context r3 = r7.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r4 = r7.globalVariables
            java.lang.CharSequence r8 = r0.nodeState(r9, r8, r3, r4)
            java.lang.CharSequence r0 = com.google.android.accessibility.talkback.compositor.CompositorUtils.dedupJoin(r1, r2, r8)
        Ldd:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.compositor.roledescription.RoleDescriptionExtractor.nodeRoleDescriptionText(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, android.view.accessibility.AccessibilityEvent):java.lang.CharSequence");
    }
}
