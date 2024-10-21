package com.google.android.accessibility.selecttospeak.nodefilters;

import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.Filter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IsGoogleDocsEditTextFilter extends Filter {
    @Override // com.google.android.accessibility.utils.Filter
    public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        if (accessibilityNodeInfoCompat != null) {
            if ((TextUtils.equals("android.widget.EditText", accessibilityNodeInfoCompat.getClassName()) || TextUtils.equals("android.widget.TextView", accessibilityNodeInfoCompat.getClassName())) && TextUtils.equals("com.google.android.apps.docs.editors.docs:id/viewport_view", accessibilityNodeInfoCompat.getViewIdResourceName()) && TextUtils.isEmpty(accessibilityNodeInfoCompat.getText()) && TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription())) {
                return true;
            }
            return false;
        }
        return false;
    }
}
