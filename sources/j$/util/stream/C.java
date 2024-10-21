package j$.util.stream;

/* loaded from: classes2.dex */
enum C {
    ANY(true, true),
    ALL(false, false),
    NONE(true, false);

    private final boolean a;
    private final boolean b;

    C(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }
}
