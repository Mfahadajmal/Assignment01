package kotlin.sequences;

import _COROUTINE._BOUNDARY;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransformingSequence$iterator$1 implements Iterator, KMappedMarker {
    private final Iterator iterator;
    final /* synthetic */ GeneratorSequence this$0$ar$class_merging$bdd1d3e0_0;

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.sequences.Sequence, java.lang.Object] */
    public TransformingSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.this$0$ar$class_merging$bdd1d3e0_0 = generatorSequence;
        this.iterator = generatorSequence.GeneratorSequence$ar$getNextValue.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    @Override // java.util.Iterator
    public final Object next() {
        return this.this$0$ar$class_merging$bdd1d3e0_0.GeneratorSequence$ar$getInitialValue.invoke(this.iterator.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }
}
