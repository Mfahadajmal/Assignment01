package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
public abstract class D0 implements G0 {
    protected final H0 a;

    public D0(H0 h0) {
        h0.getClass();
        this.a = h0;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(double d) {
        G.c();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.H0
    public final void c() {
        this.a.c();
    }

    @Override // j$.util.stream.H0
    public final void e(long j) {
        this.a.e(j);
    }

    @Override // j$.util.stream.G0
    public final /* synthetic */ void h(Integer num) {
        G.g(this, num);
    }

    @Override // j$.util.stream.H0
    public final boolean l() {
        return this.a.l();
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        h((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.nio.file.attribute.a.b(this, intConsumer);
    }
}
