package j$.time.format;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class u {
    public static final u LENIENT;
    public static final u SMART;
    public static final u STRICT;
    private static final /* synthetic */ u[] a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Enum, j$.time.format.u] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Enum, j$.time.format.u] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Enum, j$.time.format.u] */
    static {
        ?? r3 = new Enum("STRICT", 0);
        STRICT = r3;
        ?? r4 = new Enum("SMART", 1);
        SMART = r4;
        ?? r5 = new Enum("LENIENT", 2);
        LENIENT = r5;
        a = new u[]{r3, r4, r5};
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) a.clone();
    }
}
