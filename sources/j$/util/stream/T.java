package j$.util.stream;

import java.util.function.IntFunction;

/* loaded from: classes2.dex */
abstract class T extends L implements J {
    @Override // j$.util.stream.J
    public final Object d() {
        long f = f();
        if (f >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object b = b((int) f);
        n(0, b);
        return b;
    }

    @Override // j$.util.stream.J
    public final void g(Object obj) {
        ((J) this.a).g(obj);
        ((J) this.b).g(obj);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ Object[] i(IntFunction intFunction) {
        return G.j(this, intFunction);
    }

    @Override // j$.util.stream.J
    public final void n(int i, Object obj) {
        K k = this.a;
        ((J) k).n(i, obj);
        ((J) this.b).n(i + ((int) ((J) k).f()), obj);
    }

    public final String toString() {
        long f = f();
        String name = getClass().getName();
        return f < 32 ? String.format("%s[%s.%s]", name, this.a, this.b) : String.format("%s[size=%d]", name, Long.valueOf(f()));
    }
}
