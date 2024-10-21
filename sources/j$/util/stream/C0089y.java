package j$.util.stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.y, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0089y extends C0 {
    public final /* synthetic */ int k;
    final /* synthetic */ Object l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0089y(A a, C0072p c0072p) {
        super(a, 0);
        this.k = 0;
        this.l = c0072p;
    }

    @Override // j$.util.stream.AbstractC0044b
    final boolean o() {
        return false;
    }

    @Override // j$.util.stream.AbstractC0044b
    final H0 p(int i, H0 h0) {
        switch (this.k) {
            case 0:
                return new C0087x(this, h0);
            case 1:
                return new C0066m(this, h0, 1);
            default:
                return new C0066m(this, h0, 2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0089y(C0 c0, int i, Object obj, int i2) {
        super(c0, i);
        this.k = i2;
        this.l = obj;
    }
}
