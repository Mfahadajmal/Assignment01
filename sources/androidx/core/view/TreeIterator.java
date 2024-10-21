package androidx.core.view;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TreeIterator implements Iterator, KMappedMarker {
    private final Function1 getChildIterator;
    private Iterator iterator;
    private final List stack = new ArrayList();

    public TreeIterator(Iterator it, Function1 function1) {
        this.getChildIterator = function1;
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object next = this.iterator.next();
        Iterator it = (Iterator) this.getChildIterator.invoke(next);
        if (it != null && it.hasNext()) {
            this.stack.add(this.iterator);
            this.iterator = it;
        } else {
            while (!this.iterator.hasNext() && !this.stack.isEmpty()) {
                this.iterator = (Iterator) OnDeviceLanguageIdentificationLogEvent.last(this.stack);
                OnDeviceLanguageIdentificationLogEvent.removeLast(this.stack);
            }
        }
        return next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }
}
