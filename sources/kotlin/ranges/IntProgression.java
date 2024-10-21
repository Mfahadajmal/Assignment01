package kotlin.ranges;

import kotlin.collections.IntIterator;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public class IntProgression implements Iterable, KMappedMarker {
    public static final TypeIntrinsics Companion$ar$class_merging$eeba1fcc_0 = new TypeIntrinsics();
    public final int first;
    public final int last;
    public final int step;

    public IntProgression(int i, int i2, int i3) {
        if (i3 != 0) {
            this.first = i;
            if (i3 > 0 || i3 < 0) {
                this.last = i2;
                this.step = i3;
                return;
            }
            throw new IllegalArgumentException("Step is zero.");
        }
        throw new IllegalArgumentException("Step must be non-zero.");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IntProgression)) {
            return false;
        }
        if (isEmpty() && ((IntProgression) obj).isEmpty()) {
            return true;
        }
        IntProgression intProgression = (IntProgression) obj;
        if (this.first != intProgression.first || this.last != intProgression.last || this.step != intProgression.step) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.first * 31) + this.last) * 31) + this.step;
    }

    public boolean isEmpty() {
        if (this.step > 0) {
            if (this.first > this.last) {
                return true;
            }
            return false;
        }
        if (this.first < this.last) {
            return true;
        }
        return false;
    }

    public String toString() {
        int i = this.step;
        if (i > 0) {
            return this.first + ".." + this.last + " step 1";
        }
        return this.first + " downTo " + this.last + " step " + (-i);
    }

    @Override // java.lang.Iterable
    public final IntIterator iterator() {
        return new IntProgressionIterator(this.first, this.last, this.step);
    }
}
