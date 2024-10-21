package j$.util.stream;

/* renamed from: j$.util.stream.x, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0087x extends D0 {
    final /* synthetic */ C0089y b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0087x(C0089y c0089y, H0 h0) {
        super(h0);
        this.b = c0089y;
    }

    @Override // j$.util.stream.G0, j$.util.stream.H0
    public final void accept(int i) {
        this.a.accept((H0) ((C0072p) this.b.l).apply(i));
    }
}
