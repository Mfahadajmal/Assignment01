package kotlin.sequences;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SequenceBuilderIterator extends SequenceScope implements Iterator, Continuation, KMappedMarker {
    private Iterator nextIterator;
    public Continuation nextStep;
    private Object nextValue;
    private int state;

    private final Throwable exceptionalState() {
        int i = this.state;
        if (i != 4) {
            if (i != 5) {
                return new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unexpected state of the iterator: "));
            }
            return new IllegalStateException("Iterator has failed.");
        }
        return new NoSuchElementException();
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        while (true) {
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2 || i == 3) {
                        return true;
                    }
                    if (i == 4) {
                        return false;
                    }
                    throw exceptionalState();
                }
                Iterator it = this.nextIterator;
                it.getClass();
                if (it.hasNext()) {
                    this.state = 2;
                    return true;
                }
                this.nextIterator = null;
            }
            this.state = 5;
            Continuation continuation = this.nextStep;
            continuation.getClass();
            this.nextStep = null;
            continuation.resumeWith(Unit.INSTANCE);
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.state;
        if (i != 0 && i != 1) {
            if (i != 2) {
                if (i == 3) {
                    this.state = 0;
                    Object obj = this.nextValue;
                    this.nextValue = null;
                    return obj;
                }
                throw exceptionalState();
            }
            this.state = 1;
            Iterator it = this.nextIterator;
            it.getClass();
            return it.next();
        }
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        this.state = 4;
    }

    @Override // kotlin.sequences.SequenceScope
    public final Object yield(Object obj, Continuation continuation) {
        this.nextValue = obj;
        this.state = 3;
        this.nextStep = continuation;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (coroutineSingletons == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.sequences.SequenceScope
    public final Object yieldAll(Iterator it, Continuation continuation) {
        if (it.hasNext()) {
            this.nextIterator = it;
            this.state = 2;
            this.nextStep = continuation;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (coroutineSingletons == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }
}
