package j$.time.chrono;

import j$.time.Instant;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class x extends AbstractC0011a implements Serializable {
    public static final x d = new x();
    private static final long serialVersionUID = 459996390165777884L;

    private x() {
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.n
    public final o E(int i) {
        return A.C(i);
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0012b H(Temporal temporal) {
        if (temporal instanceof z) {
            return (z) temporal;
        }
        return new z(j$.time.g.K(temporal));
    }

    @Override // j$.time.chrono.n
    public final String m() {
        return "Japanese";
    }

    @Override // j$.time.chrono.n
    public final j$.time.temporal.s q(j$.time.temporal.a aVar) {
        long Q;
        long j;
        switch (w.a[aVar.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                throw new RuntimeException("Unsupported field: ".concat(String.valueOf(aVar)));
            case 5:
                return j$.time.temporal.s.k(A.G(), 999999999 - A.q().t().Q());
            case 6:
                return j$.time.temporal.s.k(A.E(), j$.time.temporal.a.DAY_OF_YEAR.n().d());
            case 7:
                Q = z.d.Q();
                j = 999999999;
                break;
            case 8:
                Q = A.d.getValue();
                j = A.q().getValue();
                break;
            default:
                return aVar.n();
        }
        return j$.time.temporal.s.j(Q, j);
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
        return "japanese";
    }
}
