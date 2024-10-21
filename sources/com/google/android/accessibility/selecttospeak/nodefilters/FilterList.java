package com.google.android.accessibility.selecttospeak.nodefilters;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.Filter;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FilterList extends Filter {
    final List filters = new ArrayList();

    @Override // com.google.android.accessibility.utils.Filter
    public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        for (int i = 0; i < this.filters.size(); i++) {
            Filter filter = (Filter) this.filters.get(i);
            if (filter.accept(accessibilityNodeInfoCompat)) {
                LogUtils.v("FilterList", "Node accepted for OCR from filter=%s", filter);
                return true;
            }
        }
        return false;
    }

    public final void add(Filter filter) {
        this.filters.add(filter);
    }
}
