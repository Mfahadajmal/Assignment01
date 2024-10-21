package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: j$.util.stream.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
abstract class AbstractC0050e extends AbstractC0054g {
    protected final AtomicReference h;
    protected volatile boolean i;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC0050e(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        super(abstractC0044b, spliterator);
        this.h = new AtomicReference(null);
    }

    @Override // j$.util.stream.AbstractC0054g
    public final Object b() {
        CountedCompleter completer;
        completer = getCompleter();
        if (((AbstractC0054g) completer) == null) {
            Object obj = this.h.get();
            if (obj == null) {
                return f();
            }
            return obj;
        }
        return super.b();
    }

    @Override // j$.util.stream.AbstractC0054g, java.util.concurrent.CountedCompleter
    public final void compute() {
        Object obj;
        Spliterator b;
        CountedCompleter completer;
        Spliterator spliterator = this.b;
        long d = spliterator.d();
        long j = this.c;
        if (j == 0) {
            j = AbstractC0054g.e(d);
            this.c = j;
        }
        AtomicReference atomicReference = this.h;
        boolean z = false;
        AbstractC0050e abstractC0050e = this;
        while (true) {
            obj = atomicReference.get();
            if (obj != null) {
                break;
            }
            boolean z2 = abstractC0050e.i;
            if (!z2) {
                completer = abstractC0050e.getCompleter();
                while (true) {
                    AbstractC0050e abstractC0050e2 = (AbstractC0050e) ((AbstractC0054g) completer);
                    if (z2 || abstractC0050e2 == null) {
                        break;
                    }
                    z2 = abstractC0050e2.i;
                    completer = abstractC0050e2.getCompleter();
                }
            }
            if (z2) {
                obj = abstractC0050e.f();
                break;
            }
            if (d <= j || (b = spliterator.b()) == null) {
                break;
            }
            AbstractC0050e abstractC0050e3 = (AbstractC0050e) abstractC0050e.c(b);
            abstractC0050e.d = abstractC0050e3;
            AbstractC0050e abstractC0050e4 = (AbstractC0050e) abstractC0050e.c(spliterator);
            abstractC0050e.e = abstractC0050e4;
            abstractC0050e.setPendingCount(1);
            if (z) {
                spliterator = b;
                abstractC0050e = abstractC0050e3;
                abstractC0050e3 = abstractC0050e4;
            } else {
                abstractC0050e = abstractC0050e4;
            }
            z = !z;
            abstractC0050e3.fork();
            d = spliterator.d();
        }
        obj = abstractC0050e.a();
        abstractC0050e.d(obj);
        abstractC0050e.tryComplete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final void d(Object obj) {
        CountedCompleter completer;
        completer = getCompleter();
        if (((AbstractC0054g) completer) == null) {
            if (obj != null) {
                AtomicReference atomicReference = this.h;
                while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
                }
                return;
            }
            return;
        }
        super.d(obj);
    }

    protected abstract Object f();

    @Override // j$.util.stream.AbstractC0054g, java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public final Object getRawResult() {
        return b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC0050e(AbstractC0050e abstractC0050e, Spliterator spliterator) {
        super(abstractC0050e, spliterator);
        this.h = abstractC0050e.h;
    }
}
