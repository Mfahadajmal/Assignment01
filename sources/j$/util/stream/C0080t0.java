package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.t0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0080t0 extends AbstractC0090y0 implements InterfaceC0088x0 {
    final /* synthetic */ Supplier b;
    final /* synthetic */ BiConsumer c;
    final /* synthetic */ BinaryOperator d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0080t0(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator) {
        this.b = supplier;
        this.c = biConsumer;
        this.d = binaryOperator;
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
        this.a = this.b.get();
    }

    @Override // j$.util.stream.InterfaceC0088x0
    public final void j(InterfaceC0088x0 interfaceC0088x0) {
        this.a = this.d.apply(this.a, ((C0080t0) interfaceC0088x0).a);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        return false;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(int i) {
        G.i();
        throw null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.c.accept(this.a, obj);
    }
}
