package kotlin.sequences;

import _COROUTINE._BOUNDARY;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeneratorSequence$iterator$1 implements Iterator, KMappedMarker {
    private Object nextItem;
    private int nextState = -2;
    final /* synthetic */ GeneratorSequence this$0;

    public GeneratorSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.this$0 = generatorSequence;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    private final void calcNext() {
        Object invoke;
        int i;
        if (this.nextState == -2) {
            invoke = this.this$0.GeneratorSequence$ar$getInitialValue.invoke();
        } else {
            GeneratorSequence generatorSequence = this.this$0;
            Object obj = this.nextItem;
            obj.getClass();
            invoke = generatorSequence.GeneratorSequence$ar$getNextValue.invoke(obj);
        }
        this.nextItem = invoke;
        if (invoke == null) {
            i = 0;
        } else {
            i = 1;
        }
        this.nextState = i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.nextState < 0) {
            calcNext();
        }
        if (this.nextState == 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.nextState < 0) {
            calcNext();
        }
        if (this.nextState != 0) {
            Object obj = this.nextItem;
            obj.getClass();
            this.nextState = -1;
            return obj;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }
}
