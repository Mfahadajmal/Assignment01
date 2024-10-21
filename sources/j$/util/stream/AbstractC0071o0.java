package j$.util.stream;

import java.util.concurrent.CountedCompleter;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.o0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0071o0 extends CountedCompleter {
    protected final K a;
    protected final int b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0071o0(K k) {
        this.a = k;
        this.b = 0;
    }

    abstract void a();

    abstract AbstractC0071o0 b(int i, int i2);

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        AbstractC0071o0 abstractC0071o0 = this;
        while (abstractC0071o0.a.m() != 0) {
            abstractC0071o0.setPendingCount(abstractC0071o0.a.m() - 1);
            int i = 0;
            int i2 = 0;
            while (i < abstractC0071o0.a.m() - 1) {
                AbstractC0071o0 b = abstractC0071o0.b(i, abstractC0071o0.b + i2);
                i2 = (int) (i2 + b.a.f());
                b.fork();
                i++;
            }
            abstractC0071o0 = abstractC0071o0.b(i, abstractC0071o0.b + i2);
        }
        abstractC0071o0.a();
        abstractC0071o0.propagateCompletion();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0071o0(AbstractC0071o0 abstractC0071o0, K k, int i) {
        super(abstractC0071o0);
        this.a = k;
        this.b = i;
    }
}
