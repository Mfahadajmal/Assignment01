package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.n, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0068n extends C0 {
    public final /* synthetic */ int k = 1;

    public /* synthetic */ C0068n(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    static N w(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        C0058i c0058i = new C0058i(27);
        C0058i c0058i2 = new C0058i(28);
        return new N((Collection) new C0082u0(T0.REFERENCE, new C0058i(29), c0058i2, c0058i).b(abstractC0044b, spliterator));
    }

    @Override // j$.util.stream.C0, j$.util.stream.Stream
    public void forEach(Consumer consumer) {
        switch (this.k) {
            case 1:
                if (!j()) {
                    r().forEachRemaining(consumer);
                    return;
                } else {
                    super.forEach(consumer);
                    return;
                }
            default:
                super.forEach(consumer);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.util.stream.AbstractC0044b
    public K m(AbstractC0044b abstractC0044b, Spliterator spliterator, IntFunction intFunction) {
        switch (this.k) {
            case 0:
                if (S0.DISTINCT.p(abstractC0044b.h())) {
                    return abstractC0044b.b(spliterator, false, intFunction);
                }
                if (S0.ORDERED.p(abstractC0044b.h())) {
                    return w(abstractC0044b, spliterator);
                }
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                new C0081u(new C0060j(3, atomicBoolean, concurrentHashMap)).b(abstractC0044b, spliterator);
                Set keySet = concurrentHashMap.keySet();
                if (atomicBoolean.get()) {
                    HashSet hashSet = new HashSet(keySet);
                    hashSet.add(null);
                    keySet = hashSet;
                }
                return new N(keySet);
            default:
                return super.m(abstractC0044b, spliterator, intFunction);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.util.stream.AbstractC0044b
    public Spliterator n(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        switch (this.k) {
            case 0:
                if (S0.DISTINCT.p(abstractC0044b.h())) {
                    return abstractC0044b.v(spliterator);
                }
                if (S0.ORDERED.p(abstractC0044b.h())) {
                    return w(abstractC0044b, spliterator).spliterator();
                }
                return new V0(abstractC0044b.v(spliterator));
            default:
                return super.n(abstractC0044b, spliterator);
        }
    }

    @Override // j$.util.stream.AbstractC0044b
    final boolean o() {
        switch (this.k) {
            case 0:
                return true;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // j$.util.stream.AbstractC0044b
    final H0 p(int i, H0 h0) {
        H0 c0066m;
        switch (this.k) {
            case 0:
                h0.getClass();
                if (!S0.DISTINCT.p(i)) {
                    if (S0.SORTED.p(i)) {
                        c0066m = new E0(h0);
                    } else {
                        c0066m = new C0066m(h0);
                    }
                    return c0066m;
                }
                return h0;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public /* synthetic */ C0068n(AbstractC0044b abstractC0044b, int i) {
        super(abstractC0044b, i);
    }
}
