package j$.time.chrono;

import j$.time.Instant;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class C extends AbstractC0011a implements Serializable {
    public static final C d = new C();
    private static final long serialVersionUID = 1039765215346859963L;

    private C() {
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.n
    public final o E(int i) {
        if (i != 0) {
            if (i == 1) {
                return F.ROC;
            }
            throw new RuntimeException("Invalid era: " + i);
        }
        return F.BEFORE_ROC;
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0012b H(Temporal temporal) {
        if (temporal instanceof E) {
            return (E) temporal;
        }
        return new E(j$.time.g.K(temporal));
    }

    @Override // j$.time.chrono.n
    public final String m() {
        return "Minguo";
    }

    @Override // j$.time.chrono.n
    public final j$.time.temporal.s q(j$.time.temporal.a aVar) {
        int i = B.a[aVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return aVar.n();
                }
                j$.time.temporal.s n = j$.time.temporal.a.YEAR.n();
                return j$.time.temporal.s.j(n.e() - 1911, n.d() - 1911);
            }
            j$.time.temporal.s n2 = j$.time.temporal.a.YEAR.n();
            return j$.time.temporal.s.k(n2.d() - 1911, (-n2.e()) + 1912);
        }
        j$.time.temporal.s n3 = j$.time.temporal.a.PROLEPTIC_MONTH.n();
        return j$.time.temporal.s.j(n3.e() - 22932, n3.d() - 22932);
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0021k t(Instant instant, j$.time.z zVar) {
        return m.J(this, instant, zVar);
    }

    Object writeReplace() {
        return new G((byte) 1, this);
    }

    @Override // j$.time.chrono.n
    public final String x() {
        return "roc";
    }
}
