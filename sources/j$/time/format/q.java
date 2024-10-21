package j$.time.format;

import j$.time.Instant;
import j$.time.chrono.InterfaceC0012b;
import j$.time.z;
import j$.util.Objects;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class q {
    private j$.time.temporal.l a;
    private a b;
    private int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [j$.time.format.p] */
    public q(Instant instant, a aVar) {
        j$.time.chrono.n nVar;
        j$.time.chrono.n b = aVar.b();
        if (b != null) {
            j$.time.chrono.n nVar2 = (j$.time.chrono.n) instant.D(j$.time.temporal.k.e());
            z zVar = (z) instant.D(j$.time.temporal.k.k());
            InterfaceC0012b interfaceC0012b = null;
            b = Objects.equals(b, nVar2) ? null : b;
            Objects.equals(null, zVar);
            if (b != null) {
                if (b != null) {
                    nVar = b;
                } else {
                    nVar = nVar2;
                }
                if (b != null) {
                    if (instant.f(j$.time.temporal.a.EPOCH_DAY)) {
                        interfaceC0012b = nVar.H(instant);
                    } else if (b != j$.time.chrono.u.d || nVar2 != null) {
                        for (j$.time.temporal.a aVar2 : j$.time.temporal.a.values()) {
                            if (aVar2.z() && instant.f(aVar2)) {
                                throw new RuntimeException("Unable to apply override chronology '" + String.valueOf(b) + "' because the temporal object being formatted contains date fields but does not represent a whole date: " + String.valueOf(instant));
                            }
                        }
                    }
                }
                instant = new p(interfaceC0012b, instant, nVar, zVar);
            }
        }
        this.a = instant;
        this.b = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.c--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final t b() {
        return this.b.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Locale c() {
        return this.b.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final j$.time.temporal.l d() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Long e(j$.time.temporal.o oVar) {
        int i = this.c;
        j$.time.temporal.l lVar = this.a;
        if (i <= 0 || lVar.f(oVar)) {
            return Long.valueOf(lVar.z(oVar));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object f(b bVar) {
        j$.time.temporal.l lVar = this.a;
        Object D = lVar.D(bVar);
        if (D == null && this.c == 0) {
            throw new RuntimeException("Unable to extract " + String.valueOf(bVar) + " from temporal " + String.valueOf(lVar));
        }
        return D;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g() {
        this.c++;
    }

    public final String toString() {
        return this.a.toString();
    }
}
