package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.h0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0057h0 extends AbstractC0063k0 implements F0 {
    private final double[] h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0057h0(Spliterator spliterator, AbstractC0044b abstractC0044b, double[] dArr) {
        super(spliterator, abstractC0044b, dArr.length);
        this.h = dArr;
    }

    @Override // j$.util.stream.AbstractC0063k0
    final AbstractC0063k0 a(Spliterator spliterator, long j, long j2) {
        return new C0057h0(this, spliterator, j, j2);
    }

    @Override // j$.util.stream.AbstractC0063k0, j$.util.stream.H0
    public final void accept(double d) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        double[] dArr = this.h;
        this.f = i + 1;
        dArr[i] = d;
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.nio.file.attribute.a.a(this, doubleConsumer);
    }

    @Override // j$.util.stream.F0
    public final /* synthetic */ void p(Double d) {
        G.e(this, d);
    }

    C0057h0(C0057h0 c0057h0, Spliterator spliterator, long j, long j2) {
        super(c0057h0, spliterator, j, j2, c0057h0.h.length);
        this.h = c0057h0.h;
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        p((Double) obj);
    }
}
