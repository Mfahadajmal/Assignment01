package com.google.common.collect;

import com.google.auth.Credentials;
import com.google.common.base.Predicate;
import com.google.common.collect.Cut;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Range extends Credentials implements Predicate, Serializable {
    public static final Range ALL = new Range(Cut.BelowAll.INSTANCE, Cut.AboveAll.INSTANCE);
    private static final long serialVersionUID = 0;
    public final Cut lowerBound;
    public final Cut upperBound;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RangeLexOrdering extends Ordering implements Serializable {
        public static final Ordering INSTANCE = new RangeLexOrdering();
        private static final long serialVersionUID = 0;

        private RangeLexOrdering() {
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            Range range = (Range) obj;
            Range range2 = (Range) obj2;
            return ComparisonChain.ACTIVE.compare(range.lowerBound, range2.lowerBound).compare(range.upperBound, range2.upperBound).result();
        }
    }

    public Range(Cut cut, Cut cut2) {
        this.lowerBound = cut;
        this.upperBound = cut2;
        if (cut.compareTo(cut2) <= 0 && cut != Cut.AboveAll.INSTANCE && cut2 != Cut.BelowAll.INSTANCE) {
        } else {
            throw new IllegalArgumentException("Invalid range: ".concat(String.valueOf(toString(cut, cut2))));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int compareOrThrow(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static Range open(Comparable comparable, Comparable comparable2) {
        return new Range(new Cut.AboveValue(comparable), new Cut.BelowValue(comparable2));
    }

    @Override // com.google.common.base.Predicate
    /* renamed from: contains, reason: merged with bridge method [inline-methods] */
    public final boolean apply(Comparable comparable) {
        comparable.getClass();
        if (this.lowerBound.isLessThan(comparable) && !this.upperBound.isLessThan(comparable)) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.base.Predicate
    public final boolean equals(Object obj) {
        if (obj instanceof Range) {
            Range range = (Range) obj;
            if (this.lowerBound.equals(range.lowerBound) && this.upperBound.equals(range.upperBound)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.lowerBound.hashCode() * 31) + this.upperBound.hashCode();
    }

    public final Range intersection(Range range) {
        Cut cut;
        Cut cut2;
        boolean z;
        int compareTo = this.lowerBound.compareTo(range.lowerBound);
        int compareTo2 = this.upperBound.compareTo(range.upperBound);
        if (compareTo >= 0 && compareTo2 <= 0) {
            return this;
        }
        if (compareTo <= 0 && compareTo2 >= 0) {
            return range;
        }
        if (compareTo >= 0) {
            cut = this.lowerBound;
        } else {
            cut = range.lowerBound;
        }
        if (compareTo2 <= 0) {
            cut2 = this.upperBound;
        } else {
            cut2 = range.upperBound;
        }
        if (cut.compareTo(cut2) <= 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, "intersection is undefined for disconnected ranges %s and %s", this, range);
        return new Range(cut, cut2);
    }

    public final boolean isConnected(Range range) {
        if (this.lowerBound.compareTo(range.upperBound) <= 0 && range.lowerBound.compareTo(this.upperBound) <= 0) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        return this.lowerBound.equals(this.upperBound);
    }

    Object readResolve() {
        Range range = ALL;
        if (equals(range)) {
            return range;
        }
        return this;
    }

    public final String toString() {
        return toString(this.lowerBound, this.upperBound);
    }

    private static String toString(Cut cut, Cut cut2) {
        StringBuilder sb = new StringBuilder(16);
        cut.describeAsLowerBound(sb);
        sb.append("..");
        cut2.describeAsUpperBound(sb);
        return sb.toString();
    }
}
