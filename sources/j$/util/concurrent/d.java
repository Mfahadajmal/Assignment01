package j$.util.concurrent;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
final class d extends AbstractC0027a implements Iterator {
    @Override // java.util.Iterator
    public final Object next() {
        k kVar = this.b;
        if (kVar != null) {
            Object obj = kVar.b;
            Object obj2 = kVar.c;
            this.j = kVar;
            c();
            return new j(obj, obj2, this.i);
        }
        throw new NoSuchElementException();
    }
}
