package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* renamed from: j$.util.stream.w0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0086w0 implements InterfaceC0088x0, G0 {
    private int a;
    final /* synthetic */ C0072p b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0086w0(C0072p c0072p) {
        this.b = c0072p;
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
    public final /* synthetic */ void c() {
    }

    @Override // j$.util.stream.H0
    public final void e(long j) {
        this.a = 0;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Integer.valueOf(this.a);
    }

    @Override // j$.util.stream.G0
    public final /* synthetic */ void h(Integer num) {
        G.g(this, num);
    }

    @Override // j$.util.stream.InterfaceC0088x0
    public final void j(InterfaceC0088x0 interfaceC0088x0) {
        accept(((C0086w0) interfaceC0088x0).a);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        return false;
    }

    @Override // j$.util.stream.H0
    public final void accept(int i) {
        this.a = this.b.applyAsInt(this.a, i);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.nio.file.attribute.a.b(this, intConsumer);
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        h((Integer) obj);
    }
}
