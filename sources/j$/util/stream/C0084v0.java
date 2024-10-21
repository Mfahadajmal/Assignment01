package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.v0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0084v0 extends AbstractC0090y0 implements InterfaceC0088x0 {
    final /* synthetic */ C0058i b;
    final /* synthetic */ C0058i c;
    final /* synthetic */ C0058i d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0084v0(C0058i c0058i, C0058i c0058i2, C0058i c0058i3) {
        this.b = c0058i;
        this.c = c0058i2;
        this.d = c0058i3;
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
        this.d.accept(this.a, ((C0084v0) interfaceC0088x0).a);
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
