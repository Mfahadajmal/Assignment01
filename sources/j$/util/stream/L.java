package j$.util.stream;

/* loaded from: classes2.dex */
abstract class L implements K {
    protected final K a;
    protected final K b;
    private final long c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public L(K k, K k2) {
        this.a = k;
        this.b = k2;
        this.c = k.f() + k2.f();
    }

    @Override // j$.util.stream.K
    public /* bridge */ /* synthetic */ J a(int i) {
        return (J) a(i);
    }

    @Override // j$.util.stream.K
    public final long f() {
        return this.c;
    }

    @Override // j$.util.stream.K
    public final int m() {
        return 2;
    }

    @Override // j$.util.stream.K
    public final K a(int i) {
        if (i == 0) {
            return this.a;
        }
        if (i == 1) {
            return this.b;
        }
        throw new IndexOutOfBoundsException();
    }
}
