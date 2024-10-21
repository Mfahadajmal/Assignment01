package com.google.android.accessibility.selecttospeak.nodefilters;

import android.graphics.Rect;
import com.google.android.accessibility.selecttospeak.AccessibilityNodeInfoCompatWithVisibility;
import com.google.android.accessibility.utils.Filter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IntersectsBoundsFilter extends Filter {
    public final Rect bounds = new Rect();

    @Override // com.google.android.accessibility.utils.Filter
    public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
        AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility = (AccessibilityNodeInfoCompatWithVisibility) obj;
        if (accessibilityNodeInfoCompatWithVisibility == null) {
            return false;
        }
        return accessibilityNodeInfoCompatWithVisibility.intersect(this.bounds);
    }
}
