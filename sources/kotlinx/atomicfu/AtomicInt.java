package kotlinx.atomicfu;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AtomicInt {
    public static final AtomicIntegerFieldUpdater FU = AtomicIntegerFieldUpdater.newUpdater(AtomicInt.class, "value");
    public final OnDeviceSubjectSegmentationInferenceLogEvent trace$ar$class_merging$ar$class_merging;
    public volatile int value;

    public AtomicInt(int i, OnDeviceSubjectSegmentationInferenceLogEvent onDeviceSubjectSegmentationInferenceLogEvent) {
        this.trace$ar$class_merging$ar$class_merging = onDeviceSubjectSegmentationInferenceLogEvent;
        this.value = i;
    }

    public final boolean compareAndSet(int i, int i2) {
        boolean compareAndSet = FU.compareAndSet(this, i, i2);
        if (compareAndSet && this.trace$ar$class_merging$ar$class_merging != TraceBase$None.INSTANCE) {
            return true;
        }
        return compareAndSet;
    }

    public final int decrementAndGet() {
        int decrementAndGet = FU.decrementAndGet(this);
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
        return decrementAndGet;
    }

    public final int incrementAndGet() {
        int incrementAndGet = FU.incrementAndGet(this);
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
        return incrementAndGet;
    }

    public final void setValue(int i) {
        this.value = i;
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
