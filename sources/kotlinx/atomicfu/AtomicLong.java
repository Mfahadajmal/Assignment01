package kotlinx.atomicfu;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AtomicLong {
    public static final AtomicLongFieldUpdater FU = AtomicLongFieldUpdater.newUpdater(AtomicLong.class, "value");
    public final OnDeviceSubjectSegmentationInferenceLogEvent trace$ar$class_merging$ar$class_merging;
    public volatile long value;

    public AtomicLong(long j, OnDeviceSubjectSegmentationInferenceLogEvent onDeviceSubjectSegmentationInferenceLogEvent) {
        this.trace$ar$class_merging$ar$class_merging = onDeviceSubjectSegmentationInferenceLogEvent;
        this.value = j;
    }

    public final long addAndGet(long j) {
        long addAndGet = FU.addAndGet(this, j);
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
        return addAndGet;
    }

    public final boolean compareAndSet(long j, long j2) {
        boolean compareAndSet = FU.compareAndSet(this, j, j2);
        if (compareAndSet && this.trace$ar$class_merging$ar$class_merging != TraceBase$None.INSTANCE) {
            return true;
        }
        return compareAndSet;
    }

    public final long getAndIncrement() {
        long andIncrement = FU.getAndIncrement(this);
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
        return andIncrement;
    }

    public final void setValue(long j) {
        this.value = j;
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
