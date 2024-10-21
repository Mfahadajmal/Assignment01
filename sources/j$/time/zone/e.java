package j$.time.zone;

import j$.time.A;
import j$.time.Instant;
import j$.time.chrono.AbstractC0019i;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public final class e implements Serializable {
    private static final long[] i = new long[0];
    private static final d[] j = new d[0];
    private static final j$.time.i[] k = new j$.time.i[0];
    private static final b[] l = new b[0];
    private static final long serialVersionUID = 3044319355680032515L;
    private final long[] a;
    private final A[] b;
    private final long[] c;
    private final j$.time.i[] d;
    private final A[] e;
    private final d[] f;
    private final TimeZone g;
    private final transient ConcurrentHashMap h = new ConcurrentHashMap();

    private e(A a) {
        this.b = r0;
        A[] aArr = {a};
        long[] jArr = i;
        this.a = jArr;
        this.c = jArr;
        this.d = k;
        this.e = aArr;
        this.f = j;
        this.g = null;
    }

    private static Object a(j$.time.i iVar, b bVar) {
        j$.time.i n = bVar.n();
        boolean D = bVar.D();
        boolean O = iVar.O(n);
        if (D) {
            if (O) {
                return bVar.s();
            }
            if (iVar.O(bVar.h())) {
                return bVar;
            }
            return bVar.r();
        }
        if (!O) {
            return bVar.r();
        }
        if (iVar.O(bVar.h())) {
            return bVar.s();
        }
        return bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private b[] b(int i2) {
        long j2;
        Integer valueOf = Integer.valueOf(i2);
        ConcurrentHashMap concurrentHashMap = this.h;
        b[] bVarArr = (b[]) concurrentHashMap.get(valueOf);
        if (bVarArr != null) {
            return bVarArr;
        }
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            b[] bVarArr2 = l;
            if (i2 < 1800) {
                return bVarArr2;
            }
            long n = AbstractC0019i.n(j$.time.i.P(i2 - 1), this.b[0]);
            int offset = timeZone.getOffset(n * 1000);
            long j3 = 31968000 + n;
            while (n < j3) {
                long j4 = 7776000 + n;
                long j5 = n;
                if (offset != timeZone.getOffset(j4 * 1000)) {
                    n = j5;
                    while (j4 - n > 1) {
                        int i3 = offset;
                        long j6 = j3;
                        long c = j$.nio.file.attribute.a.c(j4 + n, 2L);
                        if (timeZone.getOffset(c * 1000) == i3) {
                            n = c;
                        } else {
                            j4 = c;
                        }
                        offset = i3;
                        j3 = j6;
                    }
                    j2 = j3;
                    int i4 = offset;
                    if (timeZone.getOffset(n * 1000) == i4) {
                        n = j4;
                    }
                    A i5 = i(i4);
                    offset = timeZone.getOffset(n * 1000);
                    A i6 = i(offset);
                    if (c(n, i6) == i2) {
                        bVarArr2 = (b[]) Arrays.copyOf(bVarArr2, bVarArr2.length + 1);
                        bVarArr2[bVarArr2.length - 1] = new b(n, i5, i6);
                    }
                } else {
                    j2 = j3;
                    n = j4;
                }
                j3 = j2;
            }
            if (1916 <= i2 && i2 < 2100) {
                concurrentHashMap.putIfAbsent(valueOf, bVarArr2);
            }
            return bVarArr2;
        }
        d[] dVarArr = this.f;
        b[] bVarArr3 = new b[dVarArr.length];
        for (int i7 = 0; i7 < dVarArr.length; i7++) {
            bVarArr3[i7] = dVarArr[i7].a(i2);
        }
        if (i2 < 2100) {
            concurrentHashMap.putIfAbsent(valueOf, bVarArr3);
        }
        return bVarArr3;
    }

    private static int c(long j2, A a) {
        return j$.time.g.X(j$.nio.file.attribute.a.c(j2 + a.Q(), 86400)).Q();
    }

    private Object e(j$.time.i iVar) {
        Object obj = null;
        A[] aArr = this.b;
        int i2 = 0;
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            b[] b = b(iVar.M());
            if (b.length == 0) {
                return i(timeZone.getOffset(AbstractC0019i.n(iVar, aArr[0]) * 1000));
            }
            int length = b.length;
            while (i2 < length) {
                b bVar = b[i2];
                Object a = a(iVar, bVar);
                if (!(a instanceof b) && !a.equals(bVar.s())) {
                    i2++;
                    obj = a;
                } else {
                    return a;
                }
            }
            return obj;
        }
        if (this.c.length == 0) {
            return aArr[0];
        }
        int length2 = this.f.length;
        j$.time.i[] iVarArr = this.d;
        if (length2 > 0 && iVar.N(iVarArr[iVarArr.length - 1])) {
            b[] b2 = b(iVar.M());
            int length3 = b2.length;
            while (i2 < length3) {
                b bVar2 = b2[i2];
                Object a2 = a(iVar, bVar2);
                if (!(a2 instanceof b) && !a2.equals(bVar2.s())) {
                    i2++;
                    obj = a2;
                } else {
                    return a2;
                }
            }
            return obj;
        }
        int binarySearch = Arrays.binarySearch(iVarArr, iVar);
        A[] aArr2 = this.e;
        if (binarySearch == -1) {
            return aArr2[0];
        }
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        } else if (binarySearch < iVarArr.length - 1) {
            int i3 = binarySearch + 1;
            if (iVarArr[binarySearch].equals(iVarArr[i3])) {
                binarySearch = i3;
            }
        }
        if ((binarySearch & 1) == 0) {
            j$.time.i iVar2 = iVarArr[binarySearch];
            j$.time.i iVar3 = iVarArr[binarySearch + 1];
            int i4 = binarySearch / 2;
            A a3 = aArr2[i4];
            A a4 = aArr2[i4 + 1];
            if (a4.Q() > a3.Q()) {
                return new b(iVar2, a3, a4);
            }
            return new b(iVar3, a3, a4);
        }
        return aArr2[(binarySearch / 2) + 1];
    }

    public static e h(A a) {
        Objects.a(a, "offset");
        return new e(a);
    }

    private static A i(int i2) {
        return A.T(i2 / 1000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e j(ObjectInput objectInput) {
        long[] jArr;
        d[] dVarArr;
        int readInt = objectInput.readInt();
        long[] jArr2 = i;
        if (readInt == 0) {
            jArr = jArr2;
        } else {
            jArr = new long[readInt];
        }
        for (int i2 = 0; i2 < readInt; i2++) {
            jArr[i2] = a.a(objectInput);
        }
        int i3 = readInt + 1;
        A[] aArr = new A[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            aArr[i4] = a.b(objectInput);
        }
        int readInt2 = objectInput.readInt();
        if (readInt2 != 0) {
            jArr2 = new long[readInt2];
        }
        long[] jArr3 = jArr2;
        for (int i5 = 0; i5 < readInt2; i5++) {
            jArr3[i5] = a.a(objectInput);
        }
        int i6 = readInt2 + 1;
        A[] aArr2 = new A[i6];
        for (int i7 = 0; i7 < i6; i7++) {
            aArr2[i7] = a.b(objectInput);
        }
        int readByte = objectInput.readByte();
        if (readByte == 0) {
            dVarArr = j;
        } else {
            dVarArr = new d[readByte];
        }
        d[] dVarArr2 = dVarArr;
        for (int i8 = 0; i8 < readByte; i8++) {
            dVarArr2[i8] = d.b(objectInput);
        }
        return new e(jArr, aArr, jArr3, aArr2, dVarArr2);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a(this.g != null ? (byte) 100 : (byte) 1, this);
    }

    public final A d(Instant instant) {
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            return i(timeZone.getOffset(instant.toEpochMilli()));
        }
        long[] jArr = this.c;
        if (jArr.length == 0) {
            return this.b[0];
        }
        long K = instant.K();
        int length = this.f.length;
        A[] aArr = this.e;
        if (length <= 0 || K <= jArr[jArr.length - 1]) {
            int binarySearch = Arrays.binarySearch(jArr, K);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 2;
            }
            return aArr[binarySearch + 1];
        }
        b[] b = b(c(K, aArr[aArr.length - 1]));
        b bVar = null;
        for (int i2 = 0; i2 < b.length; i2++) {
            bVar = b[i2];
            if (K < bVar.F()) {
                return bVar.s();
            }
        }
        return bVar.r();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return Objects.equals(this.g, eVar.g) && Arrays.equals(this.a, eVar.a) && Arrays.equals(this.b, eVar.b) && Arrays.equals(this.c, eVar.c) && Arrays.equals(this.e, eVar.e) && Arrays.equals(this.f, eVar.f);
    }

    public final b f(j$.time.i iVar) {
        Object e = e(iVar);
        if (e instanceof b) {
            return (b) e;
        }
        return null;
    }

    public final List g(j$.time.i iVar) {
        Object e = e(iVar);
        return e instanceof b ? ((b) e).z() : Collections.singletonList((A) e);
    }

    public final int hashCode() {
        return ((((Objects.hashCode(this.g) ^ Arrays.hashCode(this.a)) ^ Arrays.hashCode(this.b)) ^ Arrays.hashCode(this.c)) ^ Arrays.hashCode(this.e)) ^ Arrays.hashCode(this.f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void k(ObjectOutput objectOutput) {
        objectOutput.writeUTF(this.g.getID());
    }

    public final String toString() {
        String valueOf;
        StringBuilder sb;
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            valueOf = timeZone.getID();
            sb = new StringBuilder("ZoneRules[timeZone=");
        } else {
            valueOf = String.valueOf(this.b[r1.length - 1]);
            sb = new StringBuilder("ZoneRules[currentStandardOffset=");
        }
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        long[] jArr = this.a;
        objectOutput.writeInt(jArr.length);
        for (long j2 : jArr) {
            a.c(j2, objectOutput);
        }
        for (A a : this.b) {
            a.d(a, objectOutput);
        }
        long[] jArr2 = this.c;
        objectOutput.writeInt(jArr2.length);
        for (long j3 : jArr2) {
            a.c(j3, objectOutput);
        }
        for (A a2 : this.e) {
            a.d(a2, objectOutput);
        }
        d[] dVarArr = this.f;
        objectOutput.writeByte(dVarArr.length);
        for (d dVar : dVarArr) {
            dVar.writeExternal(objectOutput);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(TimeZone timeZone) {
        this.b = r0;
        A[] aArr = {i(timeZone.getRawOffset())};
        long[] jArr = i;
        this.a = jArr;
        this.c = jArr;
        this.d = k;
        this.e = aArr;
        this.f = j;
        this.g = timeZone;
    }

    private e(long[] jArr, A[] aArr, long[] jArr2, A[] aArr2, d[] dVarArr) {
        j$.time.i n;
        this.a = jArr;
        this.b = aArr;
        this.c = jArr2;
        this.e = aArr2;
        this.f = dVarArr;
        if (jArr2.length == 0) {
            this.d = k;
        } else {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < jArr2.length) {
                int i3 = i2 + 1;
                b bVar = new b(jArr2[i2], aArr2[i2], aArr2[i3]);
                if (bVar.D()) {
                    arrayList.add(bVar.n());
                    n = bVar.h();
                } else {
                    arrayList.add(bVar.h());
                    n = bVar.n();
                }
                arrayList.add(n);
                i2 = i3;
            }
            this.d = (j$.time.i[]) arrayList.toArray(new j$.time.i[arrayList.size()]);
        }
        this.g = null;
    }
}
