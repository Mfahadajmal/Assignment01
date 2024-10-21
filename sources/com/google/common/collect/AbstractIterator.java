package com.google.common.collect;

import com.google.common.collect.TreeRangeMap;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AbstractIterator extends UnmodifiableIterator {
    private Object next;
    private int state$ar$edu$af9cd93c_0;
    final /* synthetic */ TreeRangeMap.SubRangeMap.SubRangeMapAsMap this$2;
    final /* synthetic */ Iterator val$backingItr;

    protected AbstractIterator() {
        this.state$ar$edu$af9cd93c_0 = 2;
    }

    protected final /* bridge */ /* synthetic */ Object computeNext() {
        while (this.val$backingItr.hasNext()) {
            TreeRangeMap.RangeMapEntry rangeMapEntry = (TreeRangeMap.RangeMapEntry) this.val$backingItr.next();
            if (rangeMapEntry.getLowerBound().compareTo(TreeRangeMap.SubRangeMap.this.subRange.upperBound) >= 0) {
                endOfData$ar$ds();
                return null;
            }
            if (rangeMapEntry.getUpperBound().compareTo(TreeRangeMap.SubRangeMap.this.subRange.lowerBound) > 0) {
                return new ImmutableEntry(rangeMapEntry.range.intersection(TreeRangeMap.SubRangeMap.this.subRange), rangeMapEntry.value);
            }
        }
        endOfData$ar$ds();
        return null;
    }

    protected final void endOfData$ar$ds() {
        this.state$ar$edu$af9cd93c_0 = 3;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        boolean z;
        if (this.state$ar$edu$af9cd93c_0 != 4) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z);
        int i = this.state$ar$edu$af9cd93c_0;
        int i2 = i - 1;
        if (i != 0) {
            if (i2 == 0) {
                return true;
            }
            if (i2 != 2) {
                this.state$ar$edu$af9cd93c_0 = 4;
                this.next = computeNext();
                if (this.state$ar$edu$af9cd93c_0 != 3) {
                    this.state$ar$edu$af9cd93c_0 = 1;
                    return true;
                }
            }
            return false;
        }
        throw null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            this.state$ar$edu$af9cd93c_0 = 2;
            Object obj = this.next;
            this.next = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AbstractIterator(TreeRangeMap.SubRangeMap.SubRangeMapAsMap subRangeMapAsMap, Iterator it) {
        this();
        this.val$backingItr = it;
        this.this$2 = subRangeMapAsMap;
    }
}
