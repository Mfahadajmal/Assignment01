package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

/* renamed from: j$.util.stream.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
abstract class AbstractC0054g extends CountedCompleter {
    private static final int g;
    protected final AbstractC0044b a;
    protected Spliterator b;
    protected long c;
    protected AbstractC0054g d;
    protected AbstractC0054g e;
    private Object f;

    static {
        int commonPoolParallelism;
        commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism();
        g = commonPoolParallelism << 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC0054g(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        super(null);
        this.a = abstractC0044b;
        this.b = spliterator;
        this.c = 0L;
    }

    public static long e(long j) {
        long j2 = j / g;
        if (j2 <= 0) {
            return 1L;
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object a();

    /* JADX INFO: Access modifiers changed from: protected */
    public Object b() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract AbstractC0054g c(Spliterator spliterator);

    @Override // java.util.concurrent.CountedCompleter
    public void compute() {
        Spliterator b;
        Spliterator spliterator = this.b;
        long d = spliterator.d();
        long j = this.c;
        if (j == 0) {
            j = e(d);
            this.c = j;
        }
        boolean z = false;
        AbstractC0054g abstractC0054g = this;
        while (d > j && (b = spliterator.b()) != null) {
            AbstractC0054g c = abstractC0054g.c(b);
            abstractC0054g.d = c;
            AbstractC0054g c2 = abstractC0054g.c(spliterator);
            abstractC0054g.e = c2;
            abstractC0054g.setPendingCount(1);
            if (z) {
                spliterator = b;
                abstractC0054g = c;
                c = c2;
            } else {
                abstractC0054g = c2;
            }
            z = !z;
            c.fork();
            d = spliterator.d();
        }
        abstractC0054g.d(abstractC0054g.a());
        abstractC0054g.tryComplete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Object obj) {
        this.f = obj;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return this.f;
    }

    @Override // java.util.concurrent.CountedCompleter
    public void onCompletion(CountedCompleter countedCompleter) {
        this.b = null;
        this.e = null;
        this.d = null;
    }

    @Override // java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    protected final void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC0054g(AbstractC0054g abstractC0054g, Spliterator spliterator) {
        super(abstractC0054g);
        this.b = spliterator;
        this.a = abstractC0054g.a;
        this.c = abstractC0054g.c;
    }
}
