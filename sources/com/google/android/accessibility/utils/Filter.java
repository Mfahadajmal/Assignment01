package com.google.android.accessibility.utils;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Filter {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FilterAnd extends Filter {
        private final LinkedList mFilters;

        public FilterAnd(Filter filter, Filter filter2) {
            LinkedList linkedList = new LinkedList();
            this.mFilters = linkedList;
            linkedList.add(filter);
            linkedList.add(filter2);
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final boolean accept(Object obj) {
            Iterator it = this.mFilters.iterator();
            while (it.hasNext()) {
                if (!((Filter) it.next()).accept(obj)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ Filter and(Filter filter) {
            this.mFilters.add(filter);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FilterOr extends Filter {
        private final LinkedList mFilters;

        public FilterOr(Filter filter, Filter filter2) {
            LinkedList linkedList = new LinkedList();
            this.mFilters = linkedList;
            linkedList.add(filter);
            linkedList.add(filter2);
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final boolean accept(Object obj) {
            Iterator it = this.mFilters.iterator();
            while (it.hasNext()) {
                if (((Filter) it.next()).accept(obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NodeCompat extends Filter {
        private final Predicate predicate;

        public NodeCompat(Predicate predicate) {
            this.predicate = predicate;
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            return this.predicate.apply((AccessibilityNodeInfoCompat) obj);
        }
    }

    public abstract boolean accept(Object obj);

    public Filter and(Filter filter) {
        if (filter == null) {
            return this;
        }
        return new FilterAnd(this, filter);
    }

    public final Filter or(Filter filter) {
        if (filter == null) {
            return this;
        }
        return new FilterOr(this, filter);
    }
}
