package j$.util.stream;

import j$.util.Optional;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.r0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0076r0 implements InterfaceC0088x0 {
    private boolean a;
    private Object b;
    final /* synthetic */ j$.util.function.a c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0076r0(j$.util.function.a aVar) {
        this.c = aVar;
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
        this.a = true;
        this.b = null;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.a) {
            return Optional.empty();
        }
        return Optional.of(this.b);
    }

    @Override // j$.util.stream.InterfaceC0088x0
    public final void j(InterfaceC0088x0 interfaceC0088x0) {
        C0076r0 c0076r0 = (C0076r0) interfaceC0088x0;
        if (!c0076r0.a) {
            accept(c0076r0.b);
        }
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
        if (this.a) {
            this.a = false;
        } else {
            obj = this.c.apply(this.b, obj);
        }
        this.b = obj;
    }
}
