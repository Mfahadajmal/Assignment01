package j$.util.stream;

import java.util.function.IntFunction;

/* renamed from: j$.util.stream.l0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0065l0 extends P0 implements K, F {
    @Override // j$.util.stream.K
    public final K a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(double d) {
        G.c();
        throw null;
    }

    @Override // j$.util.stream.H0
    public final void c() {
    }

    @Override // j$.util.stream.H0
    public final void e(long j) {
        clear();
        o(j);
    }

    @Override // j$.util.stream.K
    public final Object[] i(IntFunction intFunction) {
        long f = f();
        if (f < 2147483639) {
            Object[] objArr = (Object[]) intFunction.apply((int) f);
            k(objArr, 0);
            return objArr;
        }
        throw new IllegalArgumentException("Stream size exceeds max array size");
    }

    @Override // j$.util.stream.K
    public final void k(Object[] objArr, int i) {
        long j = i;
        long f = f() + j;
        if (f <= objArr.length && f >= j) {
            if (this.b == 0) {
                System.arraycopy(this.d, 0, objArr, i, this.a);
                return;
            }
            for (int i2 = 0; i2 < this.b; i2++) {
                Object[] objArr2 = this.e[i2];
                System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
                i += this.e[i2].length;
            }
            int i3 = this.a;
            if (i3 > 0) {
                System.arraycopy(this.d, 0, objArr, i, i3);
                return;
            }
            return;
        }
        throw new IndexOutOfBoundsException("does not fit");
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        return false;
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ int m() {
        return 0;
    }

    @Override // j$.util.stream.F
    public final K s() {
        return this;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(int i) {
        G.i();
        throw null;
    }
}
