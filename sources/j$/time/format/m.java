package j$.time.format;

import j$.time.z;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class m implements g {
    public final /* synthetic */ int a;
    private final Object b;

    public /* synthetic */ m(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // j$.time.format.g
    public final boolean h(q qVar, StringBuilder sb) {
        switch (this.a) {
            case 0:
                sb.append((String) this.b);
                return true;
            default:
                z zVar = (z) qVar.f((b) this.b);
                if (zVar == null) {
                    return false;
                }
                sb.append(zVar.m());
                return true;
        }
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                return "'" + ((String) this.b).replace("'", "''") + "'";
            default:
                return "ZoneRegionId()";
        }
    }
}
