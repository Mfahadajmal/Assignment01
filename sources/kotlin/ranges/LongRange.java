package kotlin.ranges;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LongRange extends LongProgression {
    public LongRange(long j, long j2) {
        super(j, j2);
    }

    public final boolean contains(long j) {
        if (this.first <= j && j <= this.last) {
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.LongProgression
    public final boolean equals(Object obj) {
        if (!(obj instanceof LongRange)) {
            return false;
        }
        if (isEmpty() && ((LongRange) obj).isEmpty()) {
            return true;
        }
        LongRange longRange = (LongRange) obj;
        if (this.first != longRange.first || this.last != longRange.last) {
            return false;
        }
        return true;
    }

    @Override // kotlin.ranges.LongProgression
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = this.first;
        long j2 = this.last;
        return (int) (((j ^ (j >>> 32)) * 31) + (j2 ^ (j2 >>> 32)));
    }

    @Override // kotlin.ranges.LongProgression
    public final boolean isEmpty() {
        if (this.first > this.last) {
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.LongProgression
    public final String toString() {
        return this.first + ".." + this.last;
    }
}
