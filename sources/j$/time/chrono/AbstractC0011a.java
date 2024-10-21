package j$.time.chrono;

import j$.time.C0010c;
import j$.time.Instant;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.ServiceLoader;

/* renamed from: j$.time.chrono.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0011a implements n {
    private static final ConcurrentHashMap a = new ConcurrentHashMap();
    private static final ConcurrentHashMap b = new ConcurrentHashMap();
    public static final /* synthetic */ int c = 0;

    static {
        new Locale("ja", "JP", "JP");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static n h(String str) {
        Objects.a(str, "id");
        while (true) {
            ConcurrentHashMap concurrentHashMap = a;
            n nVar = (n) concurrentHashMap.get(str);
            if (nVar == null) {
                nVar = (n) b.get(str);
            }
            if (nVar != null) {
                return nVar;
            }
            if (concurrentHashMap.get("ISO") == null) {
                q qVar = q.o;
                n(qVar, qVar.m());
                x xVar = x.d;
                n(xVar, xVar.m());
                C c2 = C.d;
                n(c2, c2.m());
                I i = I.d;
                n(i, i.m());
                Iterator it = ServiceLoader.load(AbstractC0011a.class, null).iterator();
                while (it.hasNext()) {
                    AbstractC0011a abstractC0011a = (AbstractC0011a) it.next();
                    if (!abstractC0011a.m().equals("ISO")) {
                        n(abstractC0011a, abstractC0011a.m());
                    }
                }
                u uVar = u.d;
                n(uVar, uVar.m());
            } else {
                Iterator it2 = ServiceLoader.load(n.class).iterator();
                while (it2.hasNext()) {
                    n nVar2 = (n) it2.next();
                    if (str.equals(nVar2.m()) || str.equals(nVar2.x())) {
                        return nVar2;
                    }
                }
                throw new RuntimeException("Unknown chronology: ".concat(str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static n n(AbstractC0011a abstractC0011a, String str) {
        String x;
        n nVar = (n) a.putIfAbsent(str, abstractC0011a);
        if (nVar == null && (x = abstractC0011a.x()) != null) {
            b.putIfAbsent(x, abstractC0011a);
        }
        return nVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v7, types: [j$.time.chrono.k] */
    @Override // j$.time.chrono.n
    public InterfaceC0021k C(Temporal temporal) {
        try {
            j$.time.z I = j$.time.z.I(temporal);
            try {
                temporal = t(Instant.J(temporal), I);
                return temporal;
            } catch (C0010c unused) {
                return m.I(I, null, C0017g.I(this, G(temporal)));
            }
        } catch (C0010c e) {
            throw new RuntimeException("Unable to obtain ChronoZonedDateTime from TemporalAccessor: ".concat(String.valueOf(temporal.getClass())), e);
        }
    }

    @Override // j$.time.chrono.n
    public InterfaceC0015e G(Temporal temporal) {
        try {
            return H(temporal).B(j$.time.k.K(temporal));
        } catch (C0010c e) {
            throw new RuntimeException("Unable to obtain ChronoLocalDateTime from TemporalAccessor: ".concat(String.valueOf(temporal.getClass())), e);
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return m().compareTo(((n) obj).m());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof AbstractC0011a) && m().compareTo(((AbstractC0011a) obj).m()) == 0) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return getClass().hashCode() ^ m().hashCode();
    }

    public final String toString() {
        return m();
    }
}
