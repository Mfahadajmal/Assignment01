package j$.util.stream;

import j$.util.Map;
import java.util.EnumMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'DISTINCT' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes2.dex */
public final class S0 {
    public static final S0 DISTINCT;
    public static final S0 ORDERED;
    public static final S0 SHORT_CIRCUIT;
    public static final S0 SIZED;
    public static final S0 SORTED;
    static final int f;
    static final int g;
    static final int h;
    private static final int i;
    private static final int j;
    private static final int k;
    static final int l;
    static final int m;
    static final int n;
    static final int o;
    static final int p;
    static final int q;
    static final int r;
    static final int s;
    private static final /* synthetic */ S0[] t;
    private final EnumMap a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    static {
        R0 r0 = R0.SPLITERATOR;
        Q0 r2 = r(r0);
        R0 r02 = R0.STREAM;
        r2.a(r02);
        R0 r03 = R0.OP;
        r2.a.put((EnumMap) r03, (R0) 3);
        S0 s0 = new S0("DISTINCT", 0, 0, r2);
        DISTINCT = s0;
        Q0 r3 = r(r0);
        r3.a(r02);
        r3.a.put((EnumMap) r03, (R0) 3);
        S0 s02 = new S0("SORTED", 1, 1, r3);
        SORTED = s02;
        Q0 r4 = r(r0);
        r4.a(r02);
        EnumMap enumMap = r4.a;
        enumMap.put((EnumMap) r03, (R0) 3);
        R0 r04 = R0.TERMINAL_OP;
        enumMap.put((EnumMap) r04, (R0) 2);
        R0 r05 = R0.UPSTREAM_TERMINAL_OP;
        enumMap.put((EnumMap) r05, (R0) 2);
        S0 s03 = new S0("ORDERED", 2, 2, r4);
        ORDERED = s03;
        Q0 r5 = r(r0);
        r5.a(r02);
        r5.a.put((EnumMap) r03, (R0) 2);
        S0 s04 = new S0("SIZED", 3, 3, r5);
        SIZED = s04;
        Q0 r6 = r(r03);
        r6.a(r04);
        S0 s05 = new S0("SHORT_CIRCUIT", 4, 12, r6);
        SHORT_CIRCUIT = s05;
        t = new S0[]{s0, s02, s03, s04, s05};
        f = n(r0);
        g = n(r02);
        h = n(r03);
        n(r04);
        n(r05);
        int i2 = 0;
        for (S0 s06 : values()) {
            i2 |= s06.e;
        }
        i = i2;
        int i3 = g;
        j = i3;
        int i4 = i3 << 1;
        k = i4;
        l = i3 | i4;
        S0 s07 = DISTINCT;
        m = s07.c;
        n = s07.d;
        S0 s08 = SORTED;
        int i5 = s08.c;
        o = s08.d;
        S0 s09 = ORDERED;
        int i6 = s09.c;
        p = s09.d;
        S0 s010 = SIZED;
        q = s010.c;
        r = s010.d;
        s = SHORT_CIRCUIT.c;
    }

    private S0(String str, int i2, int i3, Q0 q0) {
        R0[] values = R0.values();
        int length = values.length;
        int i4 = 0;
        while (true) {
            EnumMap enumMap = q0.a;
            if (i4 < length) {
                Map.EL.b(enumMap, values[i4], 0);
                i4++;
            } else {
                this.a = enumMap;
                int i5 = i3 * 2;
                this.b = i5;
                this.c = 1 << i5;
                this.d = 2 << i5;
                this.e = 3 << i5;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(int i2, int i3) {
        int i4;
        if (i2 == 0) {
            i4 = i;
        } else {
            i4 = ~(((j & i2) << 1) | i2 | ((k & i2) >> 1));
        }
        return i2 | (i3 & i4);
    }

    private static int n(R0 r0) {
        int i2 = 0;
        for (S0 s0 : values()) {
            i2 |= ((Integer) s0.a.get(r0)).intValue() << s0.b;
        }
        return i2;
    }

    private static Q0 r(R0 r0) {
        Q0 q0 = new Q0(new EnumMap(R0.class));
        q0.a(r0);
        return q0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int s(int i2) {
        return i2 & ((~i2) >> 1) & j;
    }

    public static S0 valueOf(String str) {
        return (S0) Enum.valueOf(S0.class, str);
    }

    public static S0[] values() {
        return (S0[]) t.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean p(int i2) {
        return (i2 & this.e) == this.c;
    }
}
