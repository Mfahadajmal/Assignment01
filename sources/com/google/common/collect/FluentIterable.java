package com.google.common.collect;

import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FluentIterable implements Iterable {
    private final Optional iterableDelegate;
    final /* synthetic */ Iterable val$iterable;
    final /* synthetic */ int val$numberToSkip;

    protected FluentIterable() {
        this.iterableDelegate = Absent.INSTANCE;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        boolean z;
        Iterable iterable = this.val$iterable;
        if (iterable instanceof List) {
            List list = (List) iterable;
            return list.subList(Math.min(list.size(), this.val$numberToSkip), list.size()).iterator();
        }
        int i = this.val$numberToSkip;
        final Iterator it = iterable.iterator();
        it.getClass();
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, (Object) "numberToAdvance must be nonnegative");
        for (int i2 = 0; i2 < i && it.hasNext(); i2++) {
            it.next();
        }
        return new Iterator() { // from class: com.google.common.collect.Iterables$6$1
            boolean atStart = true;

            @Override // java.util.Iterator
            public final boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public final Object next() {
                Object next = it.next();
                this.atStart = false;
                return next;
            }

            @Override // java.util.Iterator
            public final void remove() {
                ContextDataProvider.checkRemove(!this.atStart);
                it.remove();
            }
        };
    }

    public final String toString() {
        Iterator it = iterator();
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(it.next());
            z = false;
        }
        sb.append(']');
        return sb.toString();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FluentIterable(Iterable iterable, int i) {
        this();
        this.val$iterable = iterable;
        this.val$numberToSkip = i;
    }
}
