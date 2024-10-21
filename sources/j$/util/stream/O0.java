package j$.util.stream;

import java.util.Arrays;

/* loaded from: classes2.dex */
abstract class O0 extends AbstractC0052f implements Iterable {
    Object d = b(16);
    Object[] e;

    public abstract Object b(int i);

    @Override // j$.util.stream.AbstractC0052f
    public final void clear() {
        Object[] objArr = this.e;
        if (objArr != null) {
            this.d = objArr[0];
            this.e = null;
            this.c = null;
        }
        this.a = 0;
        this.b = 0;
    }

    public Object d() {
        long f = f();
        if (f >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object b = b((int) f);
        n(0, b);
        return b;
    }

    public void g(Object obj) {
        for (int i = 0; i < this.b; i++) {
            Object obj2 = this.e[i];
            o(obj2, 0, q(obj2), obj);
        }
        o(this.d, 0, this.a, obj);
    }

    public void n(int i, Object obj) {
        long j = i;
        long f = f() + j;
        if (f <= q(obj) && f >= j) {
            if (this.b == 0) {
                System.arraycopy(this.d, 0, obj, i, this.a);
                return;
            }
            for (int i2 = 0; i2 < this.b; i2++) {
                Object obj2 = this.e[i2];
                System.arraycopy(obj2, 0, obj, i, q(obj2));
                i += q(this.e[i2]);
            }
            int i3 = this.a;
            if (i3 > 0) {
                System.arraycopy(this.d, 0, obj, i, i3);
                return;
            }
            return;
        }
        throw new IndexOutOfBoundsException("does not fit");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void o(Object obj, int i, int i2, Object obj2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int q(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public final int r(long j) {
        if (this.b == 0) {
            if (j < this.a) {
                return 0;
            }
            throw new IndexOutOfBoundsException(Long.toString(j));
        }
        if (j >= f()) {
            throw new IndexOutOfBoundsException(Long.toString(j));
        }
        for (int i = 0; i <= this.b; i++) {
            if (j < this.c[i] + q(this.e[i])) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException(Long.toString(j));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void t(long j) {
        long q;
        int i;
        int i2 = this.b;
        if (i2 == 0) {
            q = q(this.d);
        } else {
            q = q(this.e[i2]) + this.c[i2];
        }
        if (j > q) {
            if (this.e == null) {
                Object[] u = u();
                this.e = u;
                this.c = new long[8];
                u[0] = this.d;
            }
            int i3 = this.b + 1;
            while (j > q) {
                Object[] objArr = this.e;
                if (i3 >= objArr.length) {
                    int length = objArr.length * 2;
                    this.e = Arrays.copyOf(objArr, length);
                    this.c = Arrays.copyOf(this.c, length);
                }
                if (i3 != 0 && i3 != 1) {
                    i = Math.min(i3 + 3, 30);
                } else {
                    i = 4;
                }
                int i4 = 1 << i;
                this.e[i3] = b(i4);
                long[] jArr = this.c;
                jArr[i3] = jArr[i3 - 1] + q(this.e[r6]);
                q += i4;
                i3++;
            }
        }
    }

    protected abstract Object[] u();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void v() {
        long q;
        if (this.a == q(this.d)) {
            if (this.e == null) {
                Object[] u = u();
                this.e = u;
                this.c = new long[8];
                u[0] = this.d;
            }
            int i = this.b;
            int i2 = i + 1;
            Object[] objArr = this.e;
            if (i2 >= objArr.length || objArr[i2] == null) {
                if (i == 0) {
                    q = q(this.d);
                } else {
                    q = q(objArr[i]) + this.c[i];
                }
                t(q + 1);
            }
            this.a = 0;
            int i3 = this.b + 1;
            this.b = i3;
            this.d = this.e[i3];
        }
    }
}
