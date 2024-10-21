package com.google.common.collect;

import com.google.common.collect.Range;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImmutableRangeSet extends AbstractRangeSet implements Serializable {
    public static final ImmutableRangeSet ALL;
    public static final ImmutableRangeSet EMPTY;
    private final transient ImmutableList ranges;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SerializedForm implements Serializable {
        private final ImmutableList ranges;

        public SerializedForm(ImmutableList immutableList) {
            this.ranges = immutableList;
        }

        Object readResolve() {
            if (this.ranges.isEmpty()) {
                return ImmutableRangeSet.EMPTY;
            }
            if (ContextDataProvider.equalsImpl(this.ranges, ImmutableList.of((Object) Range.ALL))) {
                return ImmutableRangeSet.ALL;
            }
            return new ImmutableRangeSet(this.ranges);
        }
    }

    static {
        int i = ImmutableList.ImmutableList$ar$NoOp;
        EMPTY = new ImmutableRangeSet(RegularImmutableList.EMPTY);
        ALL = new ImmutableRangeSet(ImmutableList.of((Object) Range.ALL));
    }

    public ImmutableRangeSet(ImmutableList immutableList) {
        this.ranges = immutableList;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // com.google.common.collect.RangeSet
    public final /* bridge */ /* synthetic */ Set asRanges() {
        if (this.ranges.isEmpty()) {
            return RegularImmutableSet.EMPTY;
        }
        return new RegularImmutableSortedSet(this.ranges, Range.RangeLexOrdering.INSTANCE);
    }

    Object writeReplace() {
        return new SerializedForm(this.ranges);
    }
}
