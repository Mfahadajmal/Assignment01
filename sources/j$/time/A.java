package j$.time;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/* loaded from: classes2.dex */
public final class A extends z implements j$.time.temporal.l, j$.time.temporal.m, Comparable {
    private static final ConcurrentHashMap c = new ConcurrentHashMap(16, 0.75f, 4);
    private static final ConcurrentHashMap d = new ConcurrentHashMap(16, 0.75f, 4);
    public static final A e = T(0);
    public static final A f = T(-64800);
    public static final A g = T(64800);
    private static final long serialVersionUID = 2357656521762053153L;
    private final int a;
    private final transient String b;

    private A(int i) {
        String str;
        String str2;
        String str3;
        String sb;
        this.a = i;
        if (i == 0) {
            sb = "Z";
        } else {
            int abs = Math.abs(i);
            StringBuilder sb2 = new StringBuilder();
            int i2 = abs / 3600;
            int i3 = (abs / 60) % 60;
            if (i < 0) {
                str = "-";
            } else {
                str = "+";
            }
            sb2.append(str);
            if (i2 < 10) {
                str2 = "0";
            } else {
                str2 = "";
            }
            sb2.append(str2);
            sb2.append(i2);
            if (i3 >= 10) {
                str3 = ":";
            } else {
                str3 = ":0";
            }
            sb2.append(str3);
            sb2.append(i3);
            int i4 = abs % 60;
            if (i4 != 0) {
                sb2.append(i4 < 10 ? ":0" : ":");
                sb2.append(i4);
            }
            sb = sb2.toString();
        }
        this.b = sb;
    }

    public static A P(Temporal temporal) {
        Objects.a(temporal, "temporal");
        A a = (A) temporal.D(j$.time.temporal.k.h());
        if (a != null) {
            return a;
        }
        throw new RuntimeException("Unable to obtain ZoneOffset from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static j$.time.A R(java.lang.String r7) {
        /*
            java.lang.String r0 = "offsetId"
            j$.util.Objects.a(r7, r0)
            j$.util.concurrent.ConcurrentHashMap r0 = j$.time.A.d
            java.lang.Object r0 = r0.get(r7)
            j$.time.A r0 = (j$.time.A) r0
            if (r0 == 0) goto L10
            return r0
        L10:
            int r0 = r7.length()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L63
            r1 = 3
            if (r0 == r1) goto L7f
            r4 = 5
            if (r0 == r4) goto L5a
            r5 = 6
            r6 = 4
            if (r0 == r5) goto L50
            r5 = 7
            if (r0 == r5) goto L43
            r1 = 9
            if (r0 != r1) goto L37
            int r0 = U(r7, r2, r3)
            int r1 = U(r7, r6, r2)
            int r2 = U(r7, r5, r2)
            goto L85
        L37:
            j$.time.c r0 = new j$.time.c
            java.lang.String r1 = "Invalid ID for ZoneOffset, invalid format: "
            java.lang.String r7 = r1.concat(r7)
            r0.<init>(r7)
            throw r0
        L43:
            int r0 = U(r7, r2, r3)
            int r1 = U(r7, r1, r3)
            int r2 = U(r7, r4, r3)
            goto L85
        L50:
            int r0 = U(r7, r2, r3)
            int r1 = U(r7, r6, r2)
        L58:
            r2 = 0
            goto L85
        L5a:
            int r0 = U(r7, r2, r3)
            int r1 = U(r7, r1, r3)
            goto L58
        L63:
            char r0 = r7.charAt(r3)
            char r7 = r7.charAt(r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "0"
            r1.append(r0)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
        L7f:
            int r0 = U(r7, r2, r3)
            r1 = 0
            goto L58
        L85:
            char r3 = r7.charAt(r3)
            r4 = 43
            r5 = 45
            if (r3 == r4) goto L9e
            if (r3 != r5) goto L92
            goto L9e
        L92:
            j$.time.c r0 = new j$.time.c
            java.lang.String r1 = "Invalid ID for ZoneOffset, plus/minus not found when expected: "
            java.lang.String r7 = r1.concat(r7)
            r0.<init>(r7)
            throw r0
        L9e:
            if (r3 != r5) goto La8
            int r7 = -r0
            int r0 = -r1
            int r1 = -r2
            j$.time.A r7 = S(r7, r0, r1)
            return r7
        La8:
            j$.time.A r7 = S(r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.A.R(java.lang.String):j$.time.A");
    }

    public static A S(int i, int i2, int i3) {
        if (i >= -18 && i <= 18) {
            if (i > 0) {
                if (i2 < 0 || i3 < 0) {
                    throw new RuntimeException("Zone offset minutes and seconds must be positive because hours is positive");
                }
            } else if (i < 0) {
                if (i2 > 0 || i3 > 0) {
                    throw new RuntimeException("Zone offset minutes and seconds must be negative because hours is negative");
                }
            } else if ((i2 > 0 && i3 < 0) || (i2 < 0 && i3 > 0)) {
                throw new RuntimeException("Zone offset minutes and seconds must have the same sign");
            }
            if (i2 >= -59 && i2 <= 59) {
                if (i3 >= -59 && i3 <= 59) {
                    if (Math.abs(i) == 18 && (i2 | i3) != 0) {
                        throw new RuntimeException("Zone offset not in valid range: -18:00 to +18:00");
                    }
                    return T((i2 * 60) + (i * 3600) + i3);
                }
                throw new RuntimeException("Zone offset seconds not in valid range: value " + i3 + " is not in the range -59 to 59");
            }
            throw new RuntimeException("Zone offset minutes not in valid range: value " + i2 + " is not in the range -59 to 59");
        }
        throw new RuntimeException("Zone offset hours not in valid range: value " + i + " is not in the range -18 to 18");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static A T(int i) {
        if (i >= -64800 && i <= 64800) {
            if (i % 900 == 0) {
                Integer valueOf = Integer.valueOf(i);
                ConcurrentHashMap concurrentHashMap = c;
                A a = (A) concurrentHashMap.get(valueOf);
                if (a == null) {
                    concurrentHashMap.putIfAbsent(valueOf, new A(i));
                    A a2 = (A) concurrentHashMap.get(valueOf);
                    d.putIfAbsent(a2.b, a2);
                    return a2;
                }
                return a;
            }
            return new A(i);
        }
        throw new RuntimeException("Zone offset not in valid range: -18:00 to +18:00");
    }

    private static int U(String str, int i, boolean z) {
        if (z && str.charAt(i - 1) != ':') {
            throw new RuntimeException("Invalid ID for ZoneOffset, colon not found when expected: ".concat(str));
        }
        char charAt = str.charAt(i);
        char charAt2 = str.charAt(i + 1);
        if (charAt >= '0' && charAt <= '9' && charAt2 >= '0' && charAt2 <= '9') {
            return (charAt2 - '0') + ((charAt - '0') * 10);
        }
        throw new RuntimeException("Invalid ID for ZoneOffset, non numeric characters found: ".concat(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static A V(ObjectInput objectInput) {
        byte readByte = objectInput.readByte();
        if (readByte == Byte.MAX_VALUE) {
            return T(objectInput.readInt());
        }
        return T(readByte * 900);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 8, this);
    }

    @Override // j$.time.temporal.l
    public final Object D(j$.time.temporal.q qVar) {
        return (qVar == j$.time.temporal.k.h() || qVar == j$.time.temporal.k.j()) ? this : j$.time.temporal.k.c(this, qVar);
    }

    @Override // j$.time.z
    public final j$.time.zone.e J() {
        return j$.time.zone.e.h(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.time.z
    public final void N(ObjectOutput objectOutput) {
        objectOutput.writeByte(8);
        W(objectOutput);
    }

    @Override // java.lang.Comparable
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public final int compareTo(A a) {
        return a.a - this.a;
    }

    public final int Q() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void W(DataOutput dataOutput) {
        int i = this.a;
        int i2 = i % 900 == 0 ? i / 900 : BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE;
        dataOutput.writeByte(i2);
        if (i2 == 127) {
            dataOutput.writeInt(i);
        }
    }

    @Override // j$.time.z
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof A) {
            return this.a == ((A) obj).a;
        }
        return false;
    }

    @Override // j$.time.temporal.l
    public final boolean f(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.OFFSET_SECONDS : oVar != null && oVar.p(this);
    }

    @Override // j$.time.temporal.m
    public final Temporal h(Temporal temporal) {
        return temporal.e(this.a, j$.time.temporal.a.OFFSET_SECONDS);
    }

    @Override // j$.time.z
    public final int hashCode() {
        return this.a;
    }

    @Override // j$.time.z
    public final String m() {
        return this.b;
    }

    @Override // j$.time.temporal.l
    public final int p(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.a;
        }
        if (!(oVar instanceof j$.time.temporal.a)) {
            return j$.time.temporal.k.d(this, oVar).a(z(oVar), oVar);
        }
        throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
    }

    @Override // j$.time.temporal.l
    public final j$.time.temporal.s s(j$.time.temporal.o oVar) {
        return j$.time.temporal.k.d(this, oVar);
    }

    @Override // j$.time.z
    public final String toString() {
        return this.b;
    }

    @Override // j$.time.temporal.l
    public final long z(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.a;
        }
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.h(this);
        }
        throw new RuntimeException("Unsupported field: ".concat(String.valueOf(oVar)));
    }
}
