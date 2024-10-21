package kotlin.ranges;

import com.google.mlkit.logging.schema.OnDeviceSegmentationLogEvent;
import java.util.Iterator;
import kotlin.collections.LongIterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LongProgression implements Iterable, KMappedMarker {
    public final long first;
    public final long last;
    private final long step;

    public LongProgression(long j, long j2) {
        this.first = j;
        this.last = j < j2 ? j2 - OnDeviceSegmentationLogEvent.mod(OnDeviceSegmentationLogEvent.mod(j2, 1L) - OnDeviceSegmentationLogEvent.mod(j, 1L), 1L) : j2;
        this.step = 1L;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LongProgression)) {
            return false;
        }
        if (isEmpty() && ((LongProgression) obj).isEmpty()) {
            return true;
        }
        LongProgression longProgression = (LongProgression) obj;
        if (this.first != longProgression.first || this.last != longProgression.last) {
            return false;
        }
        long j = longProgression.step;
        return true;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = this.first;
        long j2 = this.last;
        return (int) (((((j ^ (j >>> 32)) * 31) + (j2 ^ (j2 >>> 32))) * 31) + 1);
    }

    public boolean isEmpty() {
        if (this.first > this.last) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return new LongIterator(this.first, this.last);
    }

    public String toString() {
        return this.first + ".." + this.last + " step 1";
    }
}
