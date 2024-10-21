package j$.time.format;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class n implements g {
    private final j$.time.temporal.a a;
    private final w b;
    private final c c;
    private volatile j d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(j$.time.temporal.a aVar, w wVar, c cVar) {
        this.a = aVar;
        this.b = wVar;
        this.c = cVar;
    }

    @Override // j$.time.format.g
    public final boolean h(q qVar, StringBuilder sb) {
        String a;
        Long e = qVar.e(this.a);
        if (e == null) {
            return false;
        }
        j$.time.chrono.n nVar = (j$.time.chrono.n) qVar.d().D(j$.time.temporal.k.e());
        if (nVar != null && nVar != j$.time.chrono.u.d) {
            c cVar = this.c;
            long longValue = e.longValue();
            w wVar = this.b;
            qVar.c();
            a = cVar.a.a(longValue, wVar);
        } else {
            c cVar2 = this.c;
            long longValue2 = e.longValue();
            w wVar2 = this.b;
            qVar.c();
            a = cVar2.a.a(longValue2, wVar2);
        }
        if (a == null) {
            if (this.d == null) {
                this.d = new j(this.a, 1, 19, v.NORMAL);
            }
            return this.d.h(qVar, sb);
        }
        sb.append(a);
        return true;
    }

    public final String toString() {
        w wVar = w.FULL;
        j$.time.temporal.a aVar = this.a;
        w wVar2 = this.b;
        if (wVar2 == wVar) {
            return "Text(" + String.valueOf(aVar) + ")";
        }
        return "Text(" + String.valueOf(aVar) + "," + String.valueOf(wVar2) + ")";
    }
}
