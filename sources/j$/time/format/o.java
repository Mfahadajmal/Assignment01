package j$.time.format;

import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class o {
    private static final b f = new Object();
    private o a;
    private final o b;
    private final ArrayList c;
    private final boolean d;
    private int e;

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.time.format.b, java.lang.Object] */
    static {
        HashMap hashMap = new HashMap();
        hashMap.put('G', j$.time.temporal.a.ERA);
        hashMap.put('y', j$.time.temporal.a.YEAR_OF_ERA);
        hashMap.put('u', j$.time.temporal.a.YEAR);
        j$.time.temporal.o oVar = j$.time.temporal.h.a;
        hashMap.put('Q', oVar);
        hashMap.put('q', oVar);
        j$.time.temporal.a aVar = j$.time.temporal.a.MONTH_OF_YEAR;
        hashMap.put('M', aVar);
        hashMap.put('L', aVar);
        hashMap.put('D', j$.time.temporal.a.DAY_OF_YEAR);
        hashMap.put('d', j$.time.temporal.a.DAY_OF_MONTH);
        hashMap.put('F', j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        j$.time.temporal.a aVar2 = j$.time.temporal.a.DAY_OF_WEEK;
        hashMap.put('E', aVar2);
        hashMap.put('c', aVar2);
        hashMap.put('e', aVar2);
        hashMap.put('a', j$.time.temporal.a.AMPM_OF_DAY);
        hashMap.put('H', j$.time.temporal.a.HOUR_OF_DAY);
        hashMap.put('k', j$.time.temporal.a.CLOCK_HOUR_OF_DAY);
        hashMap.put('K', j$.time.temporal.a.HOUR_OF_AMPM);
        hashMap.put('h', j$.time.temporal.a.CLOCK_HOUR_OF_AMPM);
        hashMap.put('m', j$.time.temporal.a.MINUTE_OF_HOUR);
        hashMap.put('s', j$.time.temporal.a.SECOND_OF_MINUTE);
        j$.time.temporal.a aVar3 = j$.time.temporal.a.NANO_OF_SECOND;
        hashMap.put('S', aVar3);
        hashMap.put('A', j$.time.temporal.a.MILLI_OF_DAY);
        hashMap.put('n', aVar3);
        hashMap.put('N', j$.time.temporal.a.NANO_OF_DAY);
        hashMap.put('g', j$.time.temporal.j.a);
    }

    public o() {
        this.a = this;
        this.c = new ArrayList();
        this.e = -1;
        this.b = null;
        this.d = false;
    }

    private int d(g gVar) {
        Objects.a(gVar, "pp");
        o oVar = this.a;
        oVar.getClass();
        oVar.c.add(gVar);
        this.a.e = -1;
        return r2.c.size() - 1;
    }

    private void j(j jVar) {
        j b;
        v vVar;
        o oVar = this.a;
        int i = oVar.e;
        if (i >= 0) {
            j jVar2 = (j) oVar.c.get(i);
            int i2 = jVar.b;
            int i3 = jVar.c;
            if (i2 == i3) {
                vVar = jVar.d;
                if (vVar == v.NOT_NEGATIVE) {
                    b = jVar2.c(i3);
                    d(jVar.b());
                    this.a.e = i;
                    this.a.c.set(i, b);
                    return;
                }
            }
            b = jVar2.b();
            this.a.e = d(jVar);
            this.a.c.set(i, b);
            return;
        }
        oVar.e = d(jVar);
    }

    private a u(Locale locale, u uVar, j$.time.chrono.u uVar2) {
        Objects.a(locale, "locale");
        while (this.a.b != null) {
            n();
        }
        f fVar = new f(this.c, false);
        t tVar = t.a;
        return new a(fVar, locale, uVar, uVar2);
    }

    public final void a(a aVar) {
        d(aVar.e());
    }

    public final void b(j$.time.temporal.a aVar) {
        h hVar = new h(aVar, 0, 9, true, 0);
        Objects.a(aVar, "field");
        if (aVar.n().g()) {
            d(hVar);
            return;
        }
        throw new IllegalArgumentException("Field must have a fixed set of values: ".concat(String.valueOf(aVar)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [j$.time.format.g, java.lang.Object] */
    public final void c() {
        d(new Object());
    }

    public final void e(char c) {
        d(new e(c));
    }

    public final void f(String str) {
        g mVar;
        if (!str.isEmpty()) {
            if (str.length() == 1) {
                mVar = new e(str.charAt(0));
            } else {
                mVar = new m(0, str);
            }
            d(mVar);
        }
    }

    public final void g(String str, String str2) {
        d(new k(str, str2));
    }

    public final void h() {
        d(k.e);
    }

    public final void i(j$.time.temporal.a aVar, HashMap hashMap) {
        Objects.a(aVar, "field");
        LinkedHashMap linkedHashMap = new LinkedHashMap(hashMap);
        w wVar = w.FULL;
        d(new n(aVar, wVar, new c(new s(Collections.singletonMap(wVar, linkedHashMap)))));
    }

    public final void k(j$.time.temporal.o oVar, int i) {
        Objects.a(oVar, "field");
        if (i >= 1 && i <= 19) {
            j(new j(oVar, i, i, v.NOT_NEGATIVE));
        } else {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + i);
        }
    }

    public final void l(j$.time.temporal.o oVar, int i, int i2, v vVar) {
        if (i == i2 && vVar == v.NOT_NEGATIVE) {
            k(oVar, i2);
            return;
        }
        Objects.a(oVar, "field");
        Objects.a(vVar, "signStyle");
        if (i >= 1 && i <= 19) {
            if (i2 >= 1 && i2 <= 19) {
                if (i2 >= i) {
                    j(new j(oVar, i, i2, vVar));
                    return;
                }
                throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + i2 + " < " + i);
            }
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + i2);
        }
        throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + i);
    }

    public final void m() {
        d(new m(1, f));
    }

    public final void n() {
        o oVar = this.a;
        if (oVar.b != null) {
            if (oVar.c.size() > 0) {
                o oVar2 = this.a;
                f fVar = new f(oVar2.c, oVar2.d);
                this.a = this.a.b;
                d(fVar);
                return;
            }
            this.a = this.a.b;
            return;
        }
        throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
    }

    public final void o() {
        o oVar = this.a;
        oVar.e = -1;
        this.a = new o(oVar);
    }

    public final void p() {
        d(l.INSENSITIVE);
    }

    public final void q() {
        d(l.SENSITIVE);
    }

    public final void r() {
        d(l.LENIENT);
    }

    public final void s() {
        d(l.STRICT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final a t(u uVar, j$.time.chrono.u uVar2) {
        return u(Locale.getDefault(), uVar, uVar2);
    }

    public final void v() {
        u(Locale.getDefault(), u.SMART, null);
    }

    private o(o oVar) {
        this.a = this;
        this.c = new ArrayList();
        this.e = -1;
        this.b = oVar;
        this.d = true;
    }
}
