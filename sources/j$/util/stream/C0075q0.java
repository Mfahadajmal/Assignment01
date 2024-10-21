package j$.util.stream;

/* renamed from: j$.util.stream.q0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0075q0 extends G {
    public final /* synthetic */ int a;
    final /* synthetic */ Object b;

    public /* synthetic */ C0075q0(T0 t0, Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // j$.util.stream.G
    public final InterfaceC0088x0 r() {
        switch (this.a) {
            case 0:
                return new C0073p0((C0072p) this.b);
            case 1:
                return new C0076r0((j$.util.function.a) this.b);
            default:
                return new C0086w0((C0072p) this.b);
        }
    }
}
