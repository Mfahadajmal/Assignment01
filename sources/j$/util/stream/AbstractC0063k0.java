package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.k0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
abstract class AbstractC0063k0 extends CountedCompleter implements H0 {
    protected final Spliterator a;
    protected final AbstractC0044b b;
    protected final long c;
    protected long d;
    protected long e;
    protected int f;
    protected int g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0063k0(Spliterator spliterator, AbstractC0044b abstractC0044b, int i) {
        this.a = spliterator;
        this.b = abstractC0044b;
        this.c = AbstractC0054g.e(spliterator.d());
        this.d = 0L;
        this.e = i;
    }

    abstract AbstractC0063k0 a(Spliterator spliterator, long j, long j2);

    public /* synthetic */ void accept(double d) {
        G.c();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void c() {
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator b;
        Spliterator spliterator = this.a;
        AbstractC0063k0 abstractC0063k0 = this;
        while (spliterator.d() > abstractC0063k0.c && (b = spliterator.b()) != null) {
            abstractC0063k0.setPendingCount(1);
            long d = b.d();
            abstractC0063k0.a(b, abstractC0063k0.d, d).fork();
            abstractC0063k0 = abstractC0063k0.a(spliterator, abstractC0063k0.d + d, abstractC0063k0.e - d);
        }
        abstractC0063k0.b.t(spliterator, abstractC0063k0);
        abstractC0063k0.propagateCompletion();
    }

    @Override // j$.util.stream.H0
    public final void e(long j) {
        long j2 = this.e;
        if (j > j2) {
            throw new IllegalStateException("size passed to Sink.begin exceeds array length");
        }
        int i = (int) this.d;
        this.f = i;
        this.g = i + ((int) j2);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0063k0(AbstractC0063k0 abstractC0063k0, Spliterator spliterator, long j, long j2, int i) {
        super(abstractC0063k0);
        this.a = spliterator;
        this.b = abstractC0063k0.b;
        this.c = abstractC0063k0.c;
        this.d = j;
        this.e = j2;
        if (j < 0 || j2 < 0 || (j + j2) - 1 >= i) {
            throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(j), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)));
        }
    }

    public /* synthetic */ void accept(int i) {
        G.i();
        throw null;
    }
}
