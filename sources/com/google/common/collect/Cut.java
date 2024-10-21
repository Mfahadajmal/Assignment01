package com.google.common.collect;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Cut implements Comparable, Serializable {
    private static final long serialVersionUID = 0;
    final Comparable endpoint;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AboveAll extends Cut {
        public static final AboveAll INSTANCE = new AboveAll();
        private static final long serialVersionUID = 0;

        private AboveAll() {
            super("");
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.collect.Cut
        public final int compareTo(Cut cut) {
            return cut == this ? 0 : 1;
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsLowerBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsUpperBound(StringBuilder sb) {
            sb.append("+∞)");
        }

        @Override // com.google.common.collect.Cut
        public final int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.Cut
        public final boolean isLessThan(Comparable comparable) {
            return false;
        }

        public final String toString() {
            return "+∞";
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return compareTo((Cut) obj);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AboveValue extends Cut {
        private static final long serialVersionUID = 0;

        public AboveValue(Comparable comparable) {
            super(comparable);
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsLowerBound(StringBuilder sb) {
            sb.append('(');
            sb.append(this.endpoint);
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(']');
        }

        @Override // com.google.common.collect.Cut
        public final int hashCode() {
            return ~this.endpoint.hashCode();
        }

        @Override // com.google.common.collect.Cut
        public final boolean isLessThan(Comparable comparable) {
            if (Range.compareOrThrow(this.endpoint, comparable) < 0) {
                return true;
            }
            return false;
        }

        public final String toString() {
            return "/" + this.endpoint.toString() + "\\";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BelowAll extends Cut {
        public static final BelowAll INSTANCE = new BelowAll();
        private static final long serialVersionUID = 0;

        private BelowAll() {
            super("");
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.collect.Cut
        public final int compareTo(Cut cut) {
            return cut == this ? 0 : -1;
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsLowerBound(StringBuilder sb) {
            sb.append("(-∞");
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsUpperBound(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        public final int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.Cut
        public final boolean isLessThan(Comparable comparable) {
            return true;
        }

        public final String toString() {
            return "-∞";
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return compareTo((Cut) obj);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BelowValue extends Cut {
        private static final long serialVersionUID = 0;

        public BelowValue(Comparable comparable) {
            super(comparable);
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsLowerBound(StringBuilder sb) {
            sb.append('[');
            sb.append(this.endpoint);
        }

        @Override // com.google.common.collect.Cut
        public final void describeAsUpperBound(StringBuilder sb) {
            sb.append(this.endpoint);
            sb.append(')');
        }

        @Override // com.google.common.collect.Cut
        public final int hashCode() {
            return this.endpoint.hashCode();
        }

        @Override // com.google.common.collect.Cut
        public final boolean isLessThan(Comparable comparable) {
            if (Range.compareOrThrow(this.endpoint, comparable) <= 0) {
                return true;
            }
            return false;
        }

        public final String toString() {
            return "\\" + this.endpoint.toString() + "/";
        }
    }

    public Cut(Comparable comparable) {
        this.endpoint = comparable;
    }

    @Override // java.lang.Comparable
    public int compareTo(Cut cut) {
        if (cut != BelowAll.INSTANCE) {
            if (cut == AboveAll.INSTANCE) {
                return -1;
            }
            int compareOrThrow = Range.compareOrThrow(this.endpoint, cut.endpoint);
            if (compareOrThrow != 0) {
                return compareOrThrow;
            }
            boolean z = this instanceof AboveValue;
            if (z == (cut instanceof AboveValue)) {
                return 0;
            }
            if (!z) {
                return -1;
            }
        }
        return 1;
    }

    public abstract void describeAsLowerBound(StringBuilder sb);

    public abstract void describeAsUpperBound(StringBuilder sb);

    public final boolean equals(Object obj) {
        if (obj instanceof Cut) {
            try {
                if (compareTo((Cut) obj) == 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public abstract int hashCode();

    public abstract boolean isLessThan(Comparable comparable);
}
