package kotlinx.atomicfu;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AtomicRef {
    private static final AtomicReferenceFieldUpdater FU = AtomicReferenceFieldUpdater.newUpdater(AtomicRef.class, Object.class, "value");
    private final OnDeviceSubjectSegmentationInferenceLogEvent trace$ar$class_merging$ar$class_merging;
    public volatile Object value;

    public AtomicRef(Object obj, OnDeviceSubjectSegmentationInferenceLogEvent onDeviceSubjectSegmentationInferenceLogEvent) {
        this.trace$ar$class_merging$ar$class_merging = onDeviceSubjectSegmentationInferenceLogEvent;
        this.value = obj;
    }

    public final boolean compareAndSet(Object obj, Object obj2) {
        boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_13 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_13(FU, this, obj, obj2);
        if (ArtificialStackFrames$ar$MethodMerging$dc56d17a_13 && this.trace$ar$class_merging$ar$class_merging != TraceBase$None.INSTANCE) {
            Objects.toString(obj);
            Objects.toString(obj2);
            return true;
        }
        return ArtificialStackFrames$ar$MethodMerging$dc56d17a_13;
    }

    public final Object getAndSet(Object obj) {
        Object andSet = FU.getAndSet(this, obj);
        if (this.trace$ar$class_merging$ar$class_merging != TraceBase$None.INSTANCE) {
            Objects.toString(obj);
            Objects.toString(andSet);
        }
        return andSet;
    }

    public final void lazySet(Object obj) {
        FU.lazySet(this, obj);
        if (this.trace$ar$class_merging$ar$class_merging != TraceBase$None.INSTANCE) {
            Objects.toString(obj);
        }
    }

    public final void setValue(Object obj) {
        this.value = obj;
        if (this.trace$ar$class_merging$ar$class_merging != TraceBase$None.INSTANCE) {
            Objects.toString(obj);
        }
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
