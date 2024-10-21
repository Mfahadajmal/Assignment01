package j$.time.format;

import j$.time.Instant;
import j$.time.chrono.InterfaceC0012b;
import j$.time.z;

/* loaded from: classes2.dex */
final class p implements j$.time.temporal.l {
    final /* synthetic */ InterfaceC0012b a;
    final /* synthetic */ Instant b;
    final /* synthetic */ j$.time.chrono.n c;
    final /* synthetic */ z d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(InterfaceC0012b interfaceC0012b, Instant instant, j$.time.chrono.n nVar, z zVar) {
        this.a = interfaceC0012b;
        this.b = instant;
        this.c = nVar;
        this.d = zVar;
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return qVar == j$.time.temporal.k.e() ? this.c : qVar == j$.time.temporal.k.k() ? this.d : qVar == j$.time.temporal.k.i() ? this.b.D(qVar) : qVar.a(this);
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        InterfaceC0012b interfaceC0012b = this.a;
        return (interfaceC0012b == null || !oVar.z()) ? this.b.f(oVar) : interfaceC0012b.f(oVar);
    }

    @Override // j$.time.temporal.l
    public final /* synthetic */ int p(j$.time.temporal.o oVar) {
        return j$.time.temporal.k.a(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        InterfaceC0012b interfaceC0012b = this.a;
        if (interfaceC0012b != null && oVar.z()) {
            return interfaceC0012b.s(oVar);
        }
        return j$.time.temporal.k.d(this.b, oVar);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.b);
        j$.time.chrono.n nVar = this.c;
        String concat = nVar != null ? " with chronology ".concat(String.valueOf(nVar)) : "";
        z zVar = this.d;
        return valueOf + concat + (zVar != null ? " with zone ".concat(String.valueOf(zVar)) : "");
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        InterfaceC0012b interfaceC0012b = this.a;
        return (interfaceC0012b == null || !oVar.z()) ? this.b.z(oVar) : interfaceC0012b.z(oVar);
    }
}
