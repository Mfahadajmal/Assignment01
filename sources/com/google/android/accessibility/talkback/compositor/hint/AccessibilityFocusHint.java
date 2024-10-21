package com.google.android.accessibility.talkback.compositor.hint;

import android.content.Context;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AccessibilityFocusHint {
    protected final Context context;
    protected final GlobalVariables globalVariables;
    private final NodeRoleHint roleHint;

    public AccessibilityFocusHint(Context context, GlobalVariables globalVariables) {
        this.context = context;
        this.globalVariables = globalVariables;
        this.roleHint = new NodeRoleHint(context, globalVariables);
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x01e8, code lost:
    
        if (android.text.TextUtils.isEmpty(r6) != false) goto L80;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0237  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.CharSequence getHint(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r19) {
        /*
            Method dump skipped, instructions count: 746
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.compositor.hint.AccessibilityFocusHint.getHint(androidx.core.view.accessibility.AccessibilityNodeInfoCompat):java.lang.CharSequence");
    }
}
