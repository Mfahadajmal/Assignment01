package j$.time.format;

import j$.time.Instant;
import j$.util.Objects;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class a {
    public static final a e;
    private final f a;
    private final Locale b;
    private final t c;
    private final j$.time.chrono.u d;

    static {
        o oVar = new o();
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        v vVar = v.EXCEEDS_PAD;
        oVar.l(aVar, 4, 10, vVar);
        oVar.e('-');
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MONTH_OF_YEAR;
        oVar.k(aVar2, 2);
        oVar.e('-');
        j$.time.temporal.a aVar3 = j$.time.temporal.a.DAY_OF_MONTH;
        oVar.k(aVar3, 2);
        u uVar = u.STRICT;
        j$.time.chrono.u uVar2 = j$.time.chrono.u.d;
        a t = oVar.t(uVar, uVar2);
        o oVar2 = new o();
        oVar2.p();
        oVar2.a(t);
        oVar2.h();
        oVar2.t(uVar, uVar2);
        o oVar3 = new o();
        oVar3.p();
        oVar3.a(t);
        oVar3.o();
        oVar3.h();
        oVar3.t(uVar, uVar2);
        o oVar4 = new o();
        j$.time.temporal.a aVar4 = j$.time.temporal.a.HOUR_OF_DAY;
        oVar4.k(aVar4, 2);
        oVar4.e(':');
        j$.time.temporal.a aVar5 = j$.time.temporal.a.MINUTE_OF_HOUR;
        oVar4.k(aVar5, 2);
        oVar4.o();
        oVar4.e(':');
        j$.time.temporal.a aVar6 = j$.time.temporal.a.SECOND_OF_MINUTE;
        oVar4.k(aVar6, 2);
        oVar4.o();
        oVar4.b(j$.time.temporal.a.NANO_OF_SECOND);
        a t2 = oVar4.t(uVar, null);
        o oVar5 = new o();
        oVar5.p();
        oVar5.a(t2);
        oVar5.h();
        oVar5.t(uVar, null);
        o oVar6 = new o();
        oVar6.p();
        oVar6.a(t2);
        oVar6.o();
        oVar6.h();
        oVar6.t(uVar, null);
        o oVar7 = new o();
        oVar7.p();
        oVar7.a(t);
        oVar7.e('T');
        oVar7.a(t2);
        a t3 = oVar7.t(uVar, uVar2);
        o oVar8 = new o();
        oVar8.p();
        oVar8.a(t3);
        oVar8.r();
        oVar8.h();
        oVar8.s();
        a t4 = oVar8.t(uVar, uVar2);
        o oVar9 = new o();
        oVar9.a(t4);
        oVar9.o();
        oVar9.e('[');
        oVar9.q();
        oVar9.m();
        oVar9.e(']');
        oVar9.t(uVar, uVar2);
        o oVar10 = new o();
        oVar10.a(t3);
        oVar10.o();
        oVar10.h();
        oVar10.o();
        oVar10.e('[');
        oVar10.q();
        oVar10.m();
        oVar10.e(']');
        oVar10.t(uVar, uVar2);
        o oVar11 = new o();
        oVar11.p();
        oVar11.l(aVar, 4, 10, vVar);
        oVar11.e('-');
        oVar11.k(j$.time.temporal.a.DAY_OF_YEAR, 3);
        oVar11.o();
        oVar11.h();
        oVar11.t(uVar, uVar2);
        o oVar12 = new o();
        oVar12.p();
        oVar12.l(j$.time.temporal.h.c, 4, 10, vVar);
        oVar12.f("-W");
        oVar12.k(j$.time.temporal.h.b, 2);
        oVar12.e('-');
        j$.time.temporal.a aVar7 = j$.time.temporal.a.DAY_OF_WEEK;
        oVar12.k(aVar7, 1);
        oVar12.o();
        oVar12.h();
        oVar12.t(uVar, uVar2);
        o oVar13 = new o();
        oVar13.p();
        oVar13.c();
        e = oVar13.t(uVar, null);
        o oVar14 = new o();
        oVar14.p();
        oVar14.k(aVar, 4);
        oVar14.k(aVar2, 2);
        oVar14.k(aVar3, 2);
        oVar14.o();
        oVar14.r();
        oVar14.g("+HHMMss", "Z");
        oVar14.s();
        oVar14.t(uVar, uVar2);
        HashMap hashMap = new HashMap();
        hashMap.put(1L, "Mon");
        hashMap.put(2L, "Tue");
        hashMap.put(3L, "Wed");
        hashMap.put(4L, "Thu");
        hashMap.put(5L, "Fri");
        hashMap.put(6L, "Sat");
        hashMap.put(7L, "Sun");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(1L, "Jan");
        hashMap2.put(2L, "Feb");
        hashMap2.put(3L, "Mar");
        hashMap2.put(4L, "Apr");
        hashMap2.put(5L, "May");
        hashMap2.put(6L, "Jun");
        hashMap2.put(7L, "Jul");
        hashMap2.put(8L, "Aug");
        hashMap2.put(9L, "Sep");
        hashMap2.put(10L, "Oct");
        hashMap2.put(11L, "Nov");
        hashMap2.put(12L, "Dec");
        o oVar15 = new o();
        oVar15.p();
        oVar15.r();
        oVar15.o();
        oVar15.i(aVar7, hashMap);
        oVar15.f(", ");
        oVar15.n();
        oVar15.l(aVar3, 1, 2, v.NOT_NEGATIVE);
        oVar15.e(' ');
        oVar15.i(aVar2, hashMap2);
        oVar15.e(' ');
        oVar15.k(aVar, 4);
        oVar15.e(' ');
        oVar15.k(aVar4, 2);
        oVar15.e(':');
        oVar15.k(aVar5, 2);
        oVar15.o();
        oVar15.e(':');
        oVar15.k(aVar6, 2);
        oVar15.n();
        oVar15.e(' ');
        oVar15.g("+HHMM", "GMT");
        oVar15.t(u.SMART, uVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(f fVar, Locale locale, u uVar, j$.time.chrono.u uVar2) {
        t tVar = t.a;
        this.a = fVar;
        Objects.a(locale, "locale");
        this.b = locale;
        this.c = tVar;
        Objects.a(uVar, "resolverStyle");
        this.d = uVar2;
    }

    public final String a(Instant instant) {
        StringBuilder sb = new StringBuilder(32);
        try {
            this.a.h(new q(instant, this), sb);
            return sb.toString();
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public final j$.time.chrono.n b() {
        return this.d;
    }

    public final t c() {
        return this.c;
    }

    public final Locale d() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final f e() {
        return this.a.a();
    }

    public final String toString() {
        String fVar = this.a.toString();
        return fVar.startsWith("[") ? fVar : fVar.substring(1, fVar.length() - 1);
    }
}
