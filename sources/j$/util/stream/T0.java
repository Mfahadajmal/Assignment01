package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class T0 {
    public static final T0 DOUBLE_VALUE;
    public static final T0 INT_VALUE;
    public static final T0 LONG_VALUE;
    public static final T0 REFERENCE;
    private static final /* synthetic */ T0[] a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Enum, j$.util.stream.T0] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Enum, j$.util.stream.T0] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Enum, j$.util.stream.T0] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Enum, j$.util.stream.T0] */
    static {
        ?? r4 = new Enum("REFERENCE", 0);
        REFERENCE = r4;
        ?? r5 = new Enum("INT_VALUE", 1);
        INT_VALUE = r5;
        ?? r6 = new Enum("LONG_VALUE", 2);
        LONG_VALUE = r6;
        ?? r7 = new Enum("DOUBLE_VALUE", 3);
        DOUBLE_VALUE = r7;
        a = new T0[]{r4, r5, r6, r7};
    }

    public static T0 valueOf(String str) {
        return (T0) Enum.valueOf(T0.class, str);
    }

    public static T0[] values() {
        return (T0[]) a.clone();
    }
}
