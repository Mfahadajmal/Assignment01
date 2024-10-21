package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: j$.util.stream.t, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0079t extends AbstractC0050e {
    private final C0074q j;
    private final boolean k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0079t(C0074q c0074q, boolean z, AbstractC0044b abstractC0044b, Spliterator spliterator) {
        super(abstractC0044b, spliterator);
        this.k = z;
        this.j = c0074q;
    }

    private void g(Object obj) {
        CountedCompleter completer;
        CountedCompleter completer2;
        CountedCompleter completer3;
        AbstractC0054g abstractC0054g = this;
        while (abstractC0054g != null) {
            completer = abstractC0054g.getCompleter();
            AbstractC0054g abstractC0054g2 = (AbstractC0054g) completer;
            if (abstractC0054g2 != null && abstractC0054g2.d != abstractC0054g) {
                completer2 = getCompleter();
                AbstractC0054g abstractC0054g3 = (AbstractC0050e) ((AbstractC0054g) completer2);
                AbstractC0054g abstractC0054g4 = this;
                while (abstractC0054g3 != null) {
                    if (abstractC0054g3.d == abstractC0054g4) {
                        AbstractC0050e abstractC0050e = (AbstractC0050e) abstractC0054g3.e;
                        if (!abstractC0050e.i) {
                            abstractC0050e.i = true;
                        }
                    }
                    completer3 = abstractC0054g3.getCompleter();
                    AbstractC0054g abstractC0054g5 = (AbstractC0050e) ((AbstractC0054g) completer3);
                    abstractC0054g4 = abstractC0054g3;
                    abstractC0054g3 = abstractC0054g5;
                }
                return;
            }
            abstractC0054g = abstractC0054g2;
        }
        AtomicReference atomicReference = this.h;
        while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final Object a() {
        AbstractC0044b abstractC0044b = this.a;
        d1 d1Var = (d1) this.j.d.get();
        abstractC0044b.t(this.b, d1Var);
        Object obj = d1Var.get();
        if (!this.k) {
            if (obj != null) {
                AtomicReference atomicReference = this.h;
                while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
                }
            }
            return null;
        }
        if (obj == null) {
            return null;
        }
        g(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final AbstractC0054g c(Spliterator spliterator) {
        return new C0079t(this, spliterator);
    }

    @Override // j$.util.stream.AbstractC0050e
    protected final Object f() {
        return this.j.b;
    }

    @Override // j$.util.stream.AbstractC0054g, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        if (this.k) {
            C0079t c0079t = (C0079t) this.d;
            C0079t c0079t2 = null;
            while (true) {
                if (c0079t != c0079t2) {
                    Object b = c0079t.b();
                    if (b != null && this.j.c.test(b)) {
                        d(b);
                        g(b);
                        break;
                    } else {
                        c0079t2 = c0079t;
                        c0079t = (C0079t) this.e;
                    }
                } else {
                    break;
                }
            }
        }
        super.onCompletion(countedCompleter);
    }

    C0079t(C0079t c0079t, Spliterator spliterator) {
        super(c0079t, spliterator);
        this.k = c0079t.k;
        this.j = c0079t.j;
    }
}
