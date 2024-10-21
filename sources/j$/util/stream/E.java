package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class E extends AbstractC0050e {
    private final D j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public E(D d, AbstractC0044b abstractC0044b, Spliterator spliterator) {
        super(abstractC0044b, spliterator);
        this.j = d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final Object a() {
        boolean z;
        AbstractC0044b abstractC0044b = this.a;
        B b = (B) this.j.b.get();
        abstractC0044b.t(this.b, b);
        boolean z2 = b.b;
        z = this.j.a.b;
        if (z2 == z) {
            Boolean valueOf = Boolean.valueOf(z2);
            AtomicReference atomicReference = this.h;
            while (!atomicReference.compareAndSet(null, valueOf) && atomicReference.get() == null) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final AbstractC0054g c(Spliterator spliterator) {
        return new E(this, spliterator);
    }

    @Override // j$.util.stream.AbstractC0050e
    protected final Object f() {
        boolean z;
        z = this.j.a.b;
        return Boolean.valueOf(!z);
    }

    E(E e, Spliterator spliterator) {
        super(e, spliterator);
        this.j = e.j;
    }
}
