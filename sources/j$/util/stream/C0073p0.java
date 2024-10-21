package j$.util.stream;

import j$.util.OptionalDouble;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* renamed from: j$.util.stream.p0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0073p0 implements InterfaceC0088x0, F0 {
    private boolean a;
    private double b;
    final /* synthetic */ C0072p c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0073p0(C0072p c0072p) {
        this.c = c0072p;
    }

    @Override // j$.util.stream.H0
    public final void accept(double d) {
        if (this.a) {
            this.a = false;
        } else {
            d = this.c.applyAsDouble(this.b, d);
        }
        this.b = d;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void c() {
    }

    @Override // j$.util.stream.H0
    public final void e(long j) {
        this.a = true;
        this.b = 0.0d;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return OptionalDouble.a();
        }
        return OptionalDouble.b(this.b);
    }

    @Override // j$.util.stream.InterfaceC0088x0
    public final void j(InterfaceC0088x0 interfaceC0088x0) {
        C0073p0 c0073p0 = (C0073p0) interfaceC0088x0;
        if (!c0073p0.a) {
            accept(c0073p0.b);
        }
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        return false;
    }

    @Override // j$.util.stream.F0
    public final /* synthetic */ void p(Double d) {
        G.e(this, d);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(int i) {
        G.i();
        throw null;
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.nio.file.attribute.a.a(this, doubleConsumer);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        p((Double) obj);
    }
}
