package j$.time.format;

import j$.time.A;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class i implements g {
    @Override // j$.time.format.g
    public final boolean h(q qVar, StringBuilder sb) {
        Long e = qVar.e(j$.time.temporal.a.INSTANT_SECONDS);
        j$.time.temporal.l d = qVar.d();
        j$.time.temporal.a aVar = j$.time.temporal.a.NANO_OF_SECOND;
        Long valueOf = d.f(aVar) ? Long.valueOf(qVar.d().z(aVar)) : null;
        int i = 0;
        if (e == null) {
            return false;
        }
        long longValue = e.longValue();
        int D = aVar.D(valueOf != null ? valueOf.longValue() : 0L);
        if (longValue >= -62167219200L) {
            long j = longValue - 253402300800L;
            long c = j$.nio.file.attribute.a.c(j, 315569520000L) + 1;
            j$.time.i R = j$.time.i.R(j$.nio.file.attribute.a.d(j, 315569520000L) - 62167219200L, 0, A.e);
            if (c > 0) {
                sb.append('+');
                sb.append(c);
            }
            sb.append(R);
            if (R.L() == 0) {
                sb.append(":00");
            }
        } else {
            long j2 = longValue + 62167219200L;
            long j3 = j2 / 315569520000L;
            long j4 = j2 % 315569520000L;
            j$.time.i R2 = j$.time.i.R(j4 - 62167219200L, 0, A.e);
            int length = sb.length();
            sb.append(R2);
            if (R2.L() == 0) {
                sb.append(":00");
            }
            if (j3 < 0) {
                if (R2.M() == -10000) {
                    sb.replace(length, length + 2, Long.toString(j3 - 1));
                } else if (j4 == 0) {
                    sb.insert(length, j3);
                } else {
                    sb.insert(length + 1, Math.abs(j3));
                }
            }
        }
        if (D > 0) {
            sb.append('.');
            int i2 = 100000000;
            while (true) {
                if (D <= 0 && i % 3 == 0 && i >= -2) {
                    break;
                }
                int i3 = D / i2;
                sb.append((char) (i3 + 48));
                D -= i3 * i2;
                i2 /= 10;
                i++;
            }
        }
        sb.append('Z');
        return true;
    }

    public final String toString() {
        return "Instant()";
    }
}
