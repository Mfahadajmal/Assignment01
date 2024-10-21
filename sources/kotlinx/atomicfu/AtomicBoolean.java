package kotlinx.atomicfu;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AtomicBoolean {
    private static final AtomicIntegerFieldUpdater FU = AtomicIntegerFieldUpdater.newUpdater(AtomicBoolean.class, "_value");
    private volatile int _value;
    private final OnDeviceSubjectSegmentationInferenceLogEvent trace$ar$class_merging$ar$class_merging;

    public AtomicBoolean(boolean z, OnDeviceSubjectSegmentationInferenceLogEvent onDeviceSubjectSegmentationInferenceLogEvent) {
        this.trace$ar$class_merging$ar$class_merging = onDeviceSubjectSegmentationInferenceLogEvent;
        this._value = z ? 1 : 0;
    }

    public final boolean compareAndSet(boolean z, boolean z2) {
        boolean compareAndSet = FU.compareAndSet(this, z ? 1 : 0, z2 ? 1 : 0);
        if (compareAndSet && this.trace$ar$class_merging$ar$class_merging != TraceBase$None.INSTANCE) {
            return true;
        }
        return compareAndSet;
    }

    public final boolean getValue() {
        if (this._value != 0) {
            return true;
        }
        return false;
    }

    public final void setValue$ar$ds() {
        this._value = 1;
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
    }

    public final String toString() {
        return String.valueOf(getValue());
    }
}
