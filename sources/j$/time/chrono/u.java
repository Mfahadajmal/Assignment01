package j$.time.chrono;

import j$.time.Instant;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class u extends AbstractC0011a implements Serializable {
    public static final u d = new u();
    private static final long serialVersionUID = -1440403870442975015L;

    private u() {
    }

    public static boolean p(long j) {
        if ((3 & j) == 0 && (j % 100 != 0 || j % 400 == 0)) {
            return true;
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.chrono.AbstractC0011a, j$.time.chrono.n
    public final InterfaceC0021k C(Temporal temporal) {
        return j$.time.D.I(temporal);
    }

    @Override // j$.time.chrono.n
    public final o E(int i) {
        if (i != 0) {
            if (i == 1) {
                return v.CE;
            }
            throw new RuntimeException("Invalid era: " + i);
        }
        return v.BCE;
    }

    @Override // j$.time.chrono.AbstractC0011a, j$.time.chrono.n
    public final InterfaceC0015e G(Temporal temporal) {
        return j$.time.i.J(temporal);
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0012b H(Temporal temporal) {
        return j$.time.g.K(temporal);
    }

    @Override // j$.time.chrono.n
    public final String m() {
        return "ISO";
    }

    @Override // j$.time.chrono.n
    public final j$.time.temporal.s q(j$.time.temporal.a aVar) {
        return aVar.n();
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0021k t(Instant instant, j$.time.z zVar) {
        return j$.time.D.J(instant, zVar);
    }

    Object writeReplace() {
        return new G((byte) 1, this);
    }

    @Override // j$.time.chrono.n
    public final String x() {
        return "iso8601";
    }
}
