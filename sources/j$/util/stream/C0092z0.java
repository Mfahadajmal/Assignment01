package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.z0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0092z0 extends AbstractC0054g {
    private final G h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0092z0(G g, AbstractC0044b abstractC0044b, Spliterator spliterator) {
        super(abstractC0044b, spliterator);
        this.h = g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final Object a() {
        AbstractC0044b abstractC0044b = this.a;
        InterfaceC0088x0 r = this.h.r();
        abstractC0044b.t(this.b, r);
        return r;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final AbstractC0054g c(Spliterator spliterator) {
        return new C0092z0(this, spliterator);
    }

    @Override // j$.util.stream.AbstractC0054g, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        AbstractC0054g abstractC0054g = this.d;
        if (abstractC0054g != null) {
            InterfaceC0088x0 interfaceC0088x0 = (InterfaceC0088x0) ((C0092z0) abstractC0054g).b();
            interfaceC0088x0.j((InterfaceC0088x0) ((C0092z0) this.e).b());
            d(interfaceC0088x0);
        }
        super.onCompletion(countedCompleter);
    }

    C0092z0(C0092z0 c0092z0, Spliterator spliterator) {
        super(c0092z0, spliterator);
        this.h = c0092z0.h;
    }
}
