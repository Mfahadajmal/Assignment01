package com.google.android.accessibility.talkback.compositor.hint;

import android.content.Context;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LongClickableHint {
    protected final Context context;
    protected final GlobalVariables globalVariables;

    public LongClickableHint(Context context, GlobalVariables globalVariables) {
        this.context = context;
        this.globalVariables = globalVariables;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00e5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.CharSequence getHint(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = 32
            r2 = r18
            java.lang.CharSequence r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getActionLabelById(r2, r1)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            r4 = 2132086641(0x7f150f71, float:1.9813515E38)
            r5 = 2132086663(0x7f150f87, float:1.981356E38)
            r6 = 0
            r8 = 3
            r9 = 2132084211(0x7f1505f3, float:1.9808586E38)
            java.lang.String r10 = ""
            r11 = 0
            r12 = 1
            if (r3 == 0) goto L22
        L20:
            r1 = r10
            goto L77
        L22:
            com.google.android.accessibility.talkback.compositor.GlobalVariables r3 = r0.globalVariables
            int r3 = r3.getGlobalInputMode()
            r13 = 2132086189(0x7f150dad, float:1.9812598E38)
            r14 = 2
            if (r3 != r12) goto L4e
            com.google.android.accessibility.talkback.compositor.GlobalVariables r3 = r0.globalVariables
            long r15 = r3.getKeyComboCodeForKey(r9)
            int r3 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r3 == 0) goto L61
            android.content.Context r3 = r0.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r13 = r0.globalVariables
            java.lang.CharSequence r13 = r13.getKeyComboStringRepresentation(r9)
            java.lang.Object[] r14 = new java.lang.Object[r14]
            r14[r11] = r13
            r14[r12] = r1
            r1 = 2132086188(0x7f150dac, float:1.9812596E38)
            java.lang.String r1 = r3.getString(r1, r14)
            goto L77
        L4e:
            if (r3 != r8) goto L61
            android.content.Context r3 = r0.context
            java.lang.String r15 = r3.getString(r5)
            java.lang.Object[] r14 = new java.lang.Object[r14]
            r14[r11] = r15
            r14[r12] = r1
            java.lang.String r1 = r3.getString(r13, r14)
            goto L77
        L61:
            boolean r3 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isKeyboard(r18)
            if (r3 != 0) goto L20
            android.content.Context r3 = r0.context
            java.lang.String r15 = r3.getString(r4)
            java.lang.Object[] r14 = new java.lang.Object[r14]
            r14[r11] = r15
            r14[r12] = r1
            java.lang.String r1 = r3.getString(r13, r14)
        L77:
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            java.lang.String r13 = "LongClickableHint"
            if (r3 == 0) goto Le6
            boolean r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isLongClickable(r18)
            if (r1 != 0) goto L87
        L85:
            r1 = r10
            goto Ld5
        L87:
            com.google.android.accessibility.talkback.compositor.GlobalVariables r1 = r0.globalVariables
            int r1 = r1.getGlobalInputMode()
            r3 = 2132086204(0x7f150dbc, float:1.9812628E38)
            if (r1 != r12) goto Lb0
            com.google.android.accessibility.talkback.compositor.GlobalVariables r1 = r0.globalVariables
            long r14 = r1.getKeyComboCodeForKey(r9)
            int r1 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r1 == 0) goto Lc1
            android.content.Context r1 = r0.context
            com.google.android.accessibility.talkback.compositor.GlobalVariables r2 = r0.globalVariables
            java.lang.CharSequence r2 = r2.getKeyComboStringRepresentation(r9)
            java.lang.Object[] r3 = new java.lang.Object[r12]
            r3[r11] = r2
            r2 = 2132086205(0x7f150dbd, float:1.981263E38)
            java.lang.String r1 = r1.getString(r2, r3)
            goto Ld5
        Lb0:
            if (r1 != r8) goto Lc1
            android.content.Context r1 = r0.context
            java.lang.String r2 = r1.getString(r5)
            java.lang.Object[] r4 = new java.lang.Object[r12]
            r4[r11] = r2
            java.lang.String r1 = r1.getString(r3, r4)
            goto Ld5
        Lc1:
            boolean r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.isKeyboard(r18)
            if (r1 != 0) goto L85
            android.content.Context r1 = r0.context
            java.lang.String r2 = r1.getString(r4)
            java.lang.Object[] r4 = new java.lang.Object[r12]
            r4[r11] = r2
            java.lang.String r1 = r1.getString(r3, r4)
        Ld5:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto Le5
            java.lang.Object[] r2 = new java.lang.Object[r12]
            r2[r11] = r1
            java.lang.String r3 = " longClickableHint={%s}"
            com.google.android.libraries.accessibility.utils.log.LogUtils.v(r13, r3, r2)
            return r1
        Le5:
            return r10
        Le6:
            java.lang.Object[] r2 = new java.lang.Object[r12]
            r2[r11] = r1
            java.lang.String r3 = " actionLongClickHint={%s}"
            com.google.android.libraries.accessibility.utils.log.LogUtils.v(r13, r3, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.compositor.hint.LongClickableHint.getHint(androidx.core.view.accessibility.AccessibilityNodeInfoCompat):java.lang.CharSequence");
    }
}
