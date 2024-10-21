package j$.time.format;

import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class f implements g {
    private final g[] a;
    private final boolean b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(ArrayList arrayList, boolean z) {
        this((g[]) arrayList.toArray(new g[arrayList.size()]), z);
    }

    public final f a() {
        if (!this.b) {
            return this;
        }
        return new f(this.a, false);
    }

    @Override // j$.time.format.g
    public final boolean h(q qVar, StringBuilder sb) {
        int length = sb.length();
        boolean z = this.b;
        if (z) {
            qVar.g();
        }
        try {
            for (g gVar : this.a) {
                if (!gVar.h(qVar, sb)) {
                    sb.setLength(length);
                    return true;
                }
            }
            if (z) {
                qVar.a();
            }
            return true;
        } finally {
            if (z) {
                qVar.a();
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        g[] gVarArr = this.a;
        if (gVarArr != null) {
            boolean z = this.b;
            sb.append(z ? "[" : "(");
            for (g gVar : gVarArr) {
                sb.append(gVar);
            }
            sb.append(z ? "]" : ")");
        }
        return sb.toString();
    }

    f(g[] gVarArr, boolean z) {
        this.a = gVarArr;
        this.b = z;
    }
}
