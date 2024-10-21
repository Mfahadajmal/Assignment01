package com.google.common.collect;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ComparisonChain {
    public static final ComparisonChain ACTIVE = new ComparisonChain() { // from class: com.google.common.collect.ComparisonChain.1
        @Override // com.google.common.collect.ComparisonChain
        public final ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            int compareTo = comparable.compareTo(comparable2);
            if (compareTo < 0) {
                return ComparisonChain.LESS;
            }
            if (compareTo > 0) {
                return ComparisonChain.GREATER;
            }
            return ComparisonChain.ACTIVE;
        }

        @Override // com.google.common.collect.ComparisonChain
        public final int result() {
            return 0;
        }
    };
    public static final ComparisonChain LESS = new InactiveComparisonChain(-1);
    public static final ComparisonChain GREATER = new InactiveComparisonChain(1);

    public abstract ComparisonChain compare(Comparable comparable, Comparable comparable2);

    public abstract int result();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        public InactiveComparisonChain(int i) {
            this.result = i;
        }

        @Override // com.google.common.collect.ComparisonChain
        public final int result() {
            return this.result;
        }

        @Override // com.google.common.collect.ComparisonChain
        public final ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return this;
        }
    }
}
