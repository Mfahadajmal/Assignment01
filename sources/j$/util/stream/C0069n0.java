package j$.util.stream;

/* renamed from: j$.util.stream.n0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
class C0069n0 extends AbstractC0071o0 {
    public final /* synthetic */ int c;
    private final Object d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0069n0(K k, Object obj, int i) {
        super(k);
        this.c = i;
        this.d = obj;
    }

    @Override // j$.util.stream.AbstractC0071o0
    final void a() {
        switch (this.c) {
            case 0:
                ((J) this.a).n(this.b, this.d);
                return;
            default:
                this.a.k((Object[]) this.d, this.b);
                return;
        }
    }

    @Override // j$.util.stream.AbstractC0071o0
    final AbstractC0071o0 b(int i, int i2) {
        switch (this.c) {
            case 0:
                return new C0069n0(this, ((J) this.a).a(i), i2);
            default:
                return new C0069n0(this, this.a.a(i), i2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0069n0(C0069n0 c0069n0, J j, int i) {
        super(c0069n0, j, i);
        this.c = 0;
        this.d = c0069n0.d;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0069n0(C0069n0 c0069n0, K k, int i) {
        super(c0069n0, k, i);
        this.c = 1;
        this.d = (Object[]) c0069n0.d;
    }
}
