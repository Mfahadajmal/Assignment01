package okio;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SegmentPool {
    private static final int HASH_BUCKET_COUNT;
    public static final SegmentPool INSTANCE = new SegmentPool();
    private static final Segment LOCK = new Segment(new byte[0], 0, 0, false);
    private static final AtomicReference[] hashBuckets;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int highestOneBit = Integer.highestOneBit((availableProcessors + availableProcessors) - 1);
        HASH_BUCKET_COUNT = highestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[highestOneBit];
        for (int i = 0; i < highestOneBit; i++) {
            atomicReferenceArr[i] = new AtomicReference();
        }
        hashBuckets = atomicReferenceArr;
    }

    private SegmentPool() {
    }

    private static final AtomicReference firstRef$ar$ds() {
        return hashBuckets[(int) (Thread.currentThread().getId() & (HASH_BUCKET_COUNT - 1))];
    }

    public static final void recycle(Segment segment) {
        int i;
        if (segment.next == null && segment.prev == null) {
            if (!segment.shared) {
                AtomicReference firstRef$ar$ds = firstRef$ar$ds();
                Segment segment2 = LOCK;
                Segment segment3 = (Segment) firstRef$ar$ds.getAndSet(segment2);
                if (segment3 != segment2) {
                    if (segment3 != null) {
                        i = segment3.limit;
                    } else {
                        i = 0;
                    }
                    if (i >= 65536) {
                        firstRef$ar$ds.set(segment3);
                        return;
                    }
                    segment.next = segment3;
                    segment.pos = 0;
                    segment.limit = i + 8192;
                    firstRef$ar$ds.set(segment);
                    return;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static final Segment take() {
        AtomicReference firstRef$ar$ds = firstRef$ar$ds();
        Segment segment = LOCK;
        Segment segment2 = (Segment) firstRef$ar$ds.getAndSet(segment);
        if (segment2 == segment) {
            return new Segment();
        }
        if (segment2 == null) {
            firstRef$ar$ds.set(null);
            return new Segment();
        }
        firstRef$ar$ds.set(segment2.next);
        segment2.next = null;
        segment2.limit = 0;
        return segment2;
    }
}
