package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;

/* loaded from: classes2.dex */
abstract class N0 implements j$.util.C {
    int a;
    final int b;
    int c;
    final int d;
    Object e;
    final /* synthetic */ O0 f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public N0(O0 o0, int i, int i2, int i3, int i4) {
        this.f = o0;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        Object[] objArr = o0.e;
        this.e = objArr == null ? o0.d : objArr[i];
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return 16464;
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        int i = this.a;
        int i2 = this.b;
        if (i < i2) {
            int i3 = i2 - 1;
            int i4 = this.c;
            O0 o0 = this.f;
            j$.util.C h = h(i, i3, i4, o0.q(o0.e[i3]));
            this.a = i2;
            this.c = 0;
            this.e = o0.e[i2];
            return h;
        }
        if (i == i2) {
            int i5 = this.c;
            int i6 = (this.d - i5) / 2;
            if (i6 != 0) {
                j$.util.C e = e(this.e, i5, i6);
                this.c += i6;
                return e;
            }
        }
        return null;
    }

    abstract void c(int i, Object obj, Object obj2);

    @Override // j$.util.Spliterator
    public final long d() {
        int i = this.a;
        int i2 = this.d;
        int i3 = this.b;
        if (i == i3) {
            return i2 - this.c;
        }
        long[] jArr = this.f.c;
        return ((jArr[i3] + i2) - jArr[i]) - this.c;
    }

    abstract j$.util.C e(Object obj, int i, int i2);

    @Override // j$.util.C
    /* renamed from: f, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final void t(Object obj) {
        O0 o0;
        Object obj2;
        obj.getClass();
        int i = this.a;
        int i2 = this.d;
        int i3 = this.b;
        if (i < i3 || (i == i3 && this.c < i2)) {
            int i4 = this.c;
            while (true) {
                o0 = this.f;
                if (i >= i3) {
                    break;
                }
                Object obj3 = o0.e[i];
                o0.o(obj3, i4, o0.q(obj3), obj);
                i++;
                i4 = 0;
            }
            if (this.a == i3) {
                obj2 = this.e;
            } else {
                obj2 = o0.e[i3];
            }
            o0.o(obj2, i4, i2, obj);
            this.a = i3;
            this.c = i2;
        }
    }

    @Override // j$.util.C
    /* renamed from: g, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final boolean q(Object obj) {
        obj.getClass();
        int i = this.a;
        int i2 = this.b;
        if (i >= i2 && (i != i2 || this.c >= this.d)) {
            return false;
        }
        Object obj2 = this.e;
        int i3 = this.c;
        this.c = i3 + 1;
        c(i3, obj2, obj);
        int i4 = this.c;
        Object obj3 = this.e;
        O0 o0 = this.f;
        if (i4 == o0.q(obj3)) {
            this.c = 0;
            int i5 = this.a + 1;
            this.a = i5;
            Object[] objArr = o0.e;
            if (objArr != null && i5 <= i2) {
                this.e = objArr[i5];
            }
        }
        return true;
    }

    abstract j$.util.C h(int i, int i2, int i3, int i4);

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean m(int i) {
        return j$.util.z.j(this, i);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long n() {
        return j$.util.z.h(this);
    }

    @Override // j$.util.Spliterator
    public final Comparator o() {
        throw new IllegalStateException();
    }
}
