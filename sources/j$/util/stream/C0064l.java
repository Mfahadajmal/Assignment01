package j$.util.stream;

/* renamed from: j$.util.stream.l, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0064l extends E0 {
    boolean b;
    Object c;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        H0 h0 = this.a;
        if (obj != null) {
            Object obj2 = this.c;
            if (obj2 != null && obj.equals(obj2)) {
                return;
            }
        } else {
            if (this.b) {
                return;
            }
            this.b = true;
            obj = null;
        }
        this.c = obj;
        h0.accept((H0) obj);
    }

    @Override // j$.util.stream.E0, j$.util.stream.H0
    public final void c() {
        this.b = false;
        this.c = null;
        this.a.c();
    }

    @Override // j$.util.stream.E0, j$.util.stream.H0
    public final void e(long j) {
        this.b = false;
        this.c = null;
        this.a.e(-1L);
    }
}
