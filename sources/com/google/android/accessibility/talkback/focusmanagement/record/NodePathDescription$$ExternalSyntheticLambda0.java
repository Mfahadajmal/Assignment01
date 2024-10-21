package com.google.android.accessibility.talkback.focusmanagement.record;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.common.base.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NodePathDescription$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Object NodePathDescription$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ NodePathDescription$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.NodePathDescription$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Map, java.lang.Object] */
    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                Class cls = AccessibilityNodeInfoUtils.CLASS_TOUCHWIZ_TWADAPTERVIEW;
                ((Filter) this.NodePathDescription$$ExternalSyntheticLambda0$ar$f$0).accept((AccessibilityNodeInfoCompat) obj);
                return false;
            }
            return AccessibilityNodeInfoUtils.shouldFocusNode((AccessibilityNodeInfoCompat) obj, this.NodePathDescription$$ExternalSyntheticLambda0$ar$f$0);
        }
        int i2 = NodePathDescription.NodePathDescription$ar$NoOp;
        return AccessibilityNodeInfoUtils.shouldFocusNode((AccessibilityNodeInfoCompat) obj, this.NodePathDescription$$ExternalSyntheticLambda0$ar$f$0);
    }
}
