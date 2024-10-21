package j$.time.format;

/* loaded from: classes2.dex */
abstract /* synthetic */ class d {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[v.values().length];
        a = iArr;
        try {
            iArr[v.EXCEEDS_PAD.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[v.ALWAYS.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[v.NORMAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[v.NOT_NEGATIVE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
