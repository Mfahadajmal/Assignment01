package kotlin.collections;

import _COROUTINE._BOUNDARY;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LongIterator implements Iterator, KMappedMarker {
    private final long finalElement;
    private boolean hasNext;
    private long next;

    public LongIterator() {
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return Long.valueOf(nextLong());
    }

    public final long nextLong() {
        long j = this.next;
        if (j == this.finalElement) {
            if (this.hasNext) {
                this.hasNext = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.next = 1 + j;
        }
        return j;
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }

    public LongIterator(long j, long j2) {
        this();
        this.finalElement = j2;
        boolean z = j <= j2;
        this.hasNext = z;
        this.next = true != z ? j2 : j;
    }
}
