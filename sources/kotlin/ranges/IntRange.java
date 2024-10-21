package kotlin.ranges;

import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IntRange extends IntProgression {
    public static final OnDeviceSmartReplyLogEvent.SmartReply Companion$ar$class_merging$4de71312_0$ar$class_merging = new OnDeviceSmartReplyLogEvent.SmartReply();
    public static final IntRange EMPTY = new IntRange(1, 0);

    public IntRange(int i, int i2) {
        super(i, i2, 1);
    }

    public final boolean contains(int i) {
        if (this.first <= i && i <= this.last) {
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.IntProgression
    public final boolean equals(Object obj) {
        if (!(obj instanceof IntRange)) {
            return false;
        }
        if (isEmpty() && ((IntRange) obj).isEmpty()) {
            return true;
        }
        IntRange intRange = (IntRange) obj;
        if (this.first != intRange.first || this.last != intRange.last) {
            return false;
        }
        return true;
    }

    @Override // kotlin.ranges.IntProgression
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.first * 31) + this.last;
    }

    @Override // kotlin.ranges.IntProgression
    public final boolean isEmpty() {
        if (this.first > this.last) {
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.IntProgression
    public final String toString() {
        return this.first + ".." + this.last;
    }
}
