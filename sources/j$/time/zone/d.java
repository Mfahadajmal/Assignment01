package j$.time.zone;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import j$.time.A;
import j$.time.EnumC0023d;
import j$.time.chrono.u;
import j$.time.k;
import j$.time.l;
import j$.time.temporal.m;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import org.chromium.net.PrivateKeyType;

/* loaded from: classes2.dex */
public final class d implements Serializable {
    private static final long serialVersionUID = 6889046316657758795L;
    private final l a;
    private final byte b;
    private final EnumC0023d c;
    private final k d;
    private final boolean e;
    private final c f;
    private final A g;
    private final A h;
    private final A i;

    d(l lVar, int i, EnumC0023d enumC0023d, k kVar, boolean z, c cVar, A a, A a2, A a3) {
        this.a = lVar;
        this.b = (byte) i;
        this.c = enumC0023d;
        this.d = kVar;
        this.e = z;
        this.f = cVar;
        this.g = a;
        this.h = a2;
        this.i = a3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d b(ObjectInput objectInput) {
        EnumC0023d I;
        k P;
        int i;
        A T;
        A T2;
        boolean z;
        int readInt = objectInput.readInt();
        l L = l.L(readInt >>> 28);
        int i2 = ((264241152 & readInt) >>> 22) - 32;
        int i3 = (3670016 & readInt) >>> 19;
        if (i3 == 0) {
            I = null;
        } else {
            I = EnumC0023d.I(i3);
        }
        EnumC0023d enumC0023d = I;
        int i4 = (507904 & readInt) >>> 14;
        c cVar = c.values()[(readInt & 12288) >>> 12];
        int i5 = (readInt & 4080) >>> 4;
        int i6 = (readInt & 12) >>> 2;
        int i7 = readInt & 3;
        if (i4 == 31) {
            P = k.R(objectInput.readInt());
        } else {
            P = k.P(i4 % 24);
        }
        if (i5 == 255) {
            i = objectInput.readInt();
        } else {
            i = (i5 - 128) * 900;
        }
        A T3 = A.T(i);
        if (i6 == 3) {
            T = A.T(objectInput.readInt());
        } else {
            T = A.T((i6 * 1800) + T3.Q());
        }
        A a = T;
        if (i7 == 3) {
            T2 = A.T(objectInput.readInt());
        } else {
            T2 = A.T((i7 * 1800) + T3.Q());
        }
        if (i4 == 24) {
            z = true;
        } else {
            z = false;
        }
        Objects.a(L, "month");
        Objects.a(P, "time");
        Objects.a(cVar, "timeDefnition");
        if (i2 >= -28 && i2 <= 31 && i2 != 0) {
            if (z && !P.equals(k.g)) {
                throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
            }
            if (P.N() == 0) {
                return new d(L, i2, enumC0023d, P, z, cVar, T3, a, T2);
            }
            throw new IllegalArgumentException("Time's nano-of-second must be zero");
        }
        throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 3, this);
    }

    public final b a(int i) {
        j$.time.g W;
        m mVar;
        int Q;
        A a;
        l lVar = this.a;
        EnumC0023d enumC0023d = this.c;
        byte b = this.b;
        if (b < 0) {
            u.d.getClass();
            W = j$.time.g.W(i, lVar, lVar.J(u.p(i)) + 1 + b);
            if (enumC0023d != null) {
                final int value = enumC0023d.getValue();
                final int i2 = 1;
                mVar = new m() { // from class: j$.time.temporal.n
                    @Override // j$.time.temporal.m
                    public final Temporal h(Temporal temporal) {
                        int i3;
                        int i4;
                        switch (i2) {
                            case 0:
                                int p = temporal.p(a.DAY_OF_WEEK);
                                int i5 = value;
                                if (p != i5) {
                                    int i6 = p - i5;
                                    if (i6 >= 0) {
                                        i3 = 7 - i6;
                                    } else {
                                        i3 = -i6;
                                    }
                                    return temporal.g(i3, ChronoUnit.DAYS);
                                }
                                return temporal;
                            default:
                                int p2 = temporal.p(a.DAY_OF_WEEK);
                                int i7 = value;
                                if (p2 != i7) {
                                    int i8 = i7 - p2;
                                    if (i8 >= 0) {
                                        i4 = 7 - i8;
                                    } else {
                                        i4 = -i8;
                                    }
                                    return temporal.n(i4, ChronoUnit.DAYS);
                                }
                                return temporal;
                        }
                    }
                };
                W = W.r(mVar);
            }
        } else {
            W = j$.time.g.W(i, lVar, b);
            if (enumC0023d != null) {
                final int value2 = enumC0023d.getValue();
                final int i3 = 0;
                mVar = new m() { // from class: j$.time.temporal.n
                    @Override // j$.time.temporal.m
                    public final Temporal h(Temporal temporal) {
                        int i32;
                        int i4;
                        switch (i3) {
                            case 0:
                                int p = temporal.p(a.DAY_OF_WEEK);
                                int i5 = value2;
                                if (p != i5) {
                                    int i6 = p - i5;
                                    if (i6 >= 0) {
                                        i32 = 7 - i6;
                                    } else {
                                        i32 = -i6;
                                    }
                                    return temporal.g(i32, ChronoUnit.DAYS);
                                }
                                return temporal;
                            default:
                                int p2 = temporal.p(a.DAY_OF_WEEK);
                                int i7 = value2;
                                if (p2 != i7) {
                                    int i8 = i7 - p2;
                                    if (i8 >= 0) {
                                        i4 = 7 - i8;
                                    } else {
                                        i4 = -i8;
                                    }
                                    return temporal.n(i4, ChronoUnit.DAYS);
                                }
                                return temporal;
                        }
                    }
                };
                W = W.r(mVar);
            }
        }
        if (this.e) {
            W = W.Z(1L);
        }
        j$.time.i Q2 = j$.time.i.Q(W, this.d);
        int ordinal = this.f.ordinal();
        A a2 = this.h;
        if (ordinal != 0) {
            if (ordinal == 2) {
                Q = a2.Q();
                a = this.g;
            }
            return new b(Q2, a2, this.i);
        }
        Q = a2.Q();
        a = A.e;
        Q2 = Q2.T(Q - a.Q());
        return new b(Q2, a2, this.i);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.a == dVar.a && this.b == dVar.b && this.c == dVar.c && this.f == dVar.f && this.d.equals(dVar.d) && this.e == dVar.e && this.g.equals(dVar.g) && this.h.equals(dVar.h) && this.i.equals(dVar.i);
    }

    public final int hashCode() {
        int Z = ((this.d.Z() + (this.e ? 1 : 0)) << 15) + (this.a.ordinal() << 11) + ((this.b + 32) << 5);
        EnumC0023d enumC0023d = this.c;
        return ((this.g.hashCode() ^ (this.f.ordinal() + (Z + ((enumC0023d == null ? 7 : enumC0023d.ordinal()) << 2)))) ^ this.h.hashCode()) ^ this.i.hashCode();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "TransitionRule["
            r0.<init>(r1)
            j$.time.A r1 = r6.h
            j$.time.A r2 = r6.i
            int r3 = r1.compareTo(r2)
            if (r3 <= 0) goto L14
            java.lang.String r3 = "Gap "
            goto L16
        L14:
            java.lang.String r3 = "Overlap "
        L16:
            r0.append(r3)
            r0.append(r1)
            java.lang.String r1 = " to "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r1 = ", "
            r0.append(r1)
            r1 = 32
            j$.time.l r2 = r6.a
            byte r3 = r6.b
            j$.time.d r4 = r6.c
            if (r4 == 0) goto L6d
            r5 = -1
            if (r3 != r5) goto L4a
            java.lang.String r1 = r4.name()
            r0.append(r1)
            java.lang.String r1 = " on or before last day of "
        L3f:
            r0.append(r1)
            java.lang.String r1 = r2.name()
            r0.append(r1)
            goto L7a
        L4a:
            if (r3 >= 0) goto L61
            java.lang.String r1 = r4.name()
            r0.append(r1)
            java.lang.String r1 = " on or before last day minus "
            r0.append(r1)
            int r1 = -r3
            int r1 = r1 + (-1)
            r0.append(r1)
            java.lang.String r1 = " of "
            goto L3f
        L61:
            java.lang.String r4 = r4.name()
            r0.append(r4)
            java.lang.String r4 = " on or after "
            r0.append(r4)
        L6d:
            java.lang.String r2 = r2.name()
            r0.append(r2)
            r0.append(r1)
            r0.append(r3)
        L7a:
            java.lang.String r1 = " at "
            r0.append(r1)
            boolean r1 = r6.e
            if (r1 == 0) goto L86
            java.lang.String r1 = "24:00"
            goto L8c
        L86:
            j$.time.k r1 = r6.d
            java.lang.String r1 = r1.toString()
        L8c:
            r0.append(r1)
            java.lang.String r1 = " "
            r0.append(r1)
            j$.time.zone.c r1 = r6.f
            r0.append(r1)
            java.lang.String r1 = ", standard offset "
            r0.append(r1)
            j$.time.A r1 = r6.g
            r0.append(r1)
            r1 = 93
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.zone.d.toString():java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        int Z;
        int i;
        int i2;
        int i3;
        int i4;
        int value;
        k kVar = this.d;
        boolean z = this.e;
        if (z) {
            Z = 86400;
        } else {
            Z = kVar.Z();
        }
        int Q = this.g.Q();
        A a = this.h;
        int Q2 = a.Q() - Q;
        A a2 = this.i;
        int Q3 = a2.Q() - Q;
        if (Z % 3600 == 0) {
            if (z) {
                i = 24;
            } else {
                i = kVar.M();
            }
        } else {
            i = 31;
        }
        if (Q % 900 == 0) {
            i2 = (Q / 900) + BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        } else {
            i2 = PrivateKeyType.INVALID;
        }
        if (Q2 != 0 && Q2 != 1800 && Q2 != 3600) {
            i3 = 3;
        } else {
            i3 = Q2 / 1800;
        }
        if (Q3 != 0 && Q3 != 1800 && Q3 != 3600) {
            i4 = 3;
        } else {
            i4 = Q3 / 1800;
        }
        EnumC0023d enumC0023d = this.c;
        if (enumC0023d == null) {
            value = 0;
        } else {
            value = enumC0023d.getValue();
        }
        objectOutput.writeInt((this.a.getValue() << 28) + ((this.b + 32) << 22) + (value << 19) + (i << 14) + (this.f.ordinal() << 12) + (i2 << 4) + (i3 << 2) + i4);
        if (i == 31) {
            objectOutput.writeInt(Z);
        }
        if (i2 == 255) {
            objectOutput.writeInt(Q);
        }
        if (i3 == 3) {
            objectOutput.writeInt(a.Q());
        }
        if (i4 == 3) {
            objectOutput.writeInt(a2.Q());
        }
    }
}
