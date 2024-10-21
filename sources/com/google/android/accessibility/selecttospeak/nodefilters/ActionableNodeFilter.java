package com.google.android.accessibility.selecttospeak.nodefilters;

import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActionableNodeFilter extends Filter {
    @Override // com.google.android.accessibility.utils.Filter
    public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        if (accessibilityNodeInfoCompat == null || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 15) {
            return false;
        }
        if (TextUtils.isEmpty(accessibilityNodeInfoCompat.getText()) && TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription())) {
            return false;
        }
        return true;
    }
}
