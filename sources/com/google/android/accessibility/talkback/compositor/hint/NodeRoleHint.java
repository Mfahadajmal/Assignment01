package com.google.android.accessibility.talkback.compositor.hint;

import android.content.Context;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NodeRoleHint {
    public final ClickableHint clickableHint;
    public final Context context;
    public final GlobalVariables globalVariables;
    public final LongClickableHint longClickableHint;

    public NodeRoleHint(Context context, GlobalVariables globalVariables) {
        this.context = context;
        this.globalVariables = globalVariables;
        this.clickableHint = new ClickableHint(context, globalVariables);
        this.longClickableHint = new LongClickableHint(context, globalVariables);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.CharSequence getDefaultHint$ar$ds(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r12, com.google.android.accessibility.talkback.compositor.hint.ClickableHint r13, com.google.android.accessibility.talkback.compositor.hint.LongClickableHint r14) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.compositor.hint.NodeRoleHint.getDefaultHint$ar$ds(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, com.google.android.accessibility.talkback.compositor.hint.ClickableHint, com.google.android.accessibility.talkback.compositor.hint.LongClickableHint):java.lang.CharSequence");
    }
}
