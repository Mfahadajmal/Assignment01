package kotlin.sequences;

import _COROUTINE._BOUNDARY;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TakeWhileSequence$iterator$1 implements Iterator, KMappedMarker {
    final /* synthetic */ Object TakeWhileSequence$iterator$1$ar$this$0;
    private final Iterator iterator;
    private Object nextItem;
    private int nextState = -1;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.sequences.Sequence, java.lang.Object] */
    public TakeWhileSequence$iterator$1(GeneratorSequence generatorSequence, int i, byte[] bArr) {
        this.switching_field = i;
        this.TakeWhileSequence$iterator$1$ar$this$0 = generatorSequence;
        this.iterator = generatorSequence.GeneratorSequence$ar$getNextValue.iterator();
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    private final void calcNext() {
        if (this.iterator.hasNext()) {
            Iterator it = this.iterator;
            ?? r1 = ((GeneratorSequence) this.TakeWhileSequence$iterator$1$ar$this$0).GeneratorSequence$ar$getInitialValue;
            Object next = it.next();
            if (((Boolean) r1.invoke(next)).booleanValue()) {
                this.nextState = 1;
                this.nextItem = next;
                return;
            }
        }
        this.nextState = 0;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    private final void class_merging$calcNext() {
        int i;
        while (true) {
            if (this.iterator.hasNext()) {
                Iterator it = this.iterator;
                ?? r1 = ((GeneratorSequence) this.TakeWhileSequence$iterator$1$ar$this$0).GeneratorSequence$ar$getInitialValue;
                Object next = it.next();
                if (!((Boolean) r1.invoke(next)).booleanValue()) {
                    this.nextItem = next;
                    i = 1;
                    break;
                }
            } else {
                i = 0;
                break;
            }
        }
        this.nextState = i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.switching_field != 0) {
            if (this.nextState == -1) {
                class_merging$calcNext();
            }
            if (this.nextState != 1) {
                return false;
            }
            return true;
        }
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 1) {
            return false;
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.switching_field != 0) {
            if (this.nextState == -1) {
                class_merging$calcNext();
            }
            if (this.nextState != 0) {
                Object obj = this.nextItem;
                this.nextItem = null;
                this.nextState = -1;
                return obj;
            }
            throw new NoSuchElementException();
        }
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            Object obj2 = this.nextItem;
            this.nextItem = null;
            this.nextState = -1;
            return obj2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.switching_field != 0) {
            _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
        } else {
            _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.sequences.Sequence, java.lang.Object] */
    public TakeWhileSequence$iterator$1(GeneratorSequence generatorSequence, int i) {
        this.switching_field = i;
        this.TakeWhileSequence$iterator$1$ar$this$0 = generatorSequence;
        this.iterator = generatorSequence.GeneratorSequence$ar$getNextValue.iterator();
    }
}
