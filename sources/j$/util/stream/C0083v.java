package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;

/* renamed from: j$.util.stream.v, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0083v extends CountedCompleter {
    private Spliterator a;
    private final H0 b;
    private final AbstractC0044b c;
    private long d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0083v(AbstractC0044b abstractC0044b, Spliterator spliterator, H0 h0) {
        super(null);
        this.b = h0;
        this.c = abstractC0044b;
        this.a = spliterator;
        this.d = 0L;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Spliterator b;
        Spliterator spliterator = this.a;
        long d = spliterator.d();
        long j = this.d;
        if (j == 0) {
            j = AbstractC0054g.e(d);
            this.d = j;
        }
        boolean p = S0.SHORT_CIRCUIT.p(this.c.h());
        H0 h0 = this.b;
        boolean z = false;
        C0083v c0083v = this;
        while (true) {
            if (p && h0.l()) {
                break;
            }
            if (d <= j || (b = spliterator.b()) == null) {
                break;
            }
            C0083v c0083v2 = new C0083v(c0083v, b);
            c0083v.addToPendingCount(1);
            if (z) {
                spliterator = b;
            } else {
                C0083v c0083v3 = c0083v;
                c0083v = c0083v2;
                c0083v2 = c0083v3;
            }
            z = !z;
            c0083v.fork();
            c0083v = c0083v2;
            d = spliterator.d();
        }
        c0083v.c.a(spliterator, h0);
        c0083v.a = null;
        c0083v.propagateCompletion();
    }

    C0083v(C0083v c0083v, Spliterator spliterator) {
        super(c0083v);
        this.a = spliterator;
        this.b = c0083v.b;
        this.d = c0083v.d;
        this.c = c0083v.c;
    }
}
