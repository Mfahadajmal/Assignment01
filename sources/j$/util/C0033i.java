package j$.util;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/* renamed from: j$.util.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0033i implements Iterator, t {
    public final /* synthetic */ int a = 0;
    private final Iterator b;

    public C0033i(C0034j c0034j) {
        this.b = c0034j.a.iterator();
    }

    @Override // java.util.Iterator, j$.util.t
    public final void forEachRemaining(Consumer consumer) {
        switch (this.a) {
            case 0:
                z.s(this.b, consumer);
                return;
            default:
                z.s(this.b, new C0037m(consumer));
                return;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.a) {
            case 0:
                return this.b.hasNext();
            default:
                return this.b.hasNext();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.a) {
            case 0:
                return this.b.next();
            default:
                return new C0038n((Map.Entry) this.b.next());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    public C0033i(C0040p c0040p) {
        this.b = c0040p.a.iterator();
    }
}
