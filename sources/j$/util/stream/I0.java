package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class I0 implements Spliterator {
    int a;
    final int b;
    int c;
    final int d;
    Object[] e;
    final /* synthetic */ P0 f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public I0(P0 p0, int i, int i2, int i3, int i4) {
        this.f = p0;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        Object[][] objArr = p0.e;
        this.e = objArr == null ? p0.d : objArr[i];
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
            int i3 = this.c;
            P0 p0 = this.f;
            I0 i0 = new I0(p0, i, i2 - 1, i3, p0.e[i2 - 1].length);
            this.a = i2;
            this.c = 0;
            this.e = this.f.e[i2];
            return i0;
        }
        if (i != i2) {
            return null;
        }
        int i4 = this.c;
        int i5 = (this.d - i4) / 2;
        if (i5 == 0) {
            return null;
        }
        Spliterator g = Spliterators.g(this.e, i4, i4 + i5, 1040);
        this.c += i5;
        return g;
    }

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

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        P0 p0;
        Object[] objArr;
        consumer.getClass();
        int i = this.a;
        int i2 = this.d;
        int i3 = this.b;
        if (i < i3 || (i == i3 && this.c < i2)) {
            int i4 = this.c;
            while (true) {
                p0 = this.f;
                if (i >= i3) {
                    break;
                }
                Object[] objArr2 = p0.e[i];
                while (i4 < objArr2.length) {
                    consumer.accept(objArr2[i4]);
                    i4++;
                }
                i++;
                i4 = 0;
            }
            if (this.a == i3) {
                objArr = this.e;
            } else {
                objArr = p0.e[i3];
            }
            while (i4 < i2) {
                consumer.accept(objArr[i4]);
                i4++;
            }
            this.a = i3;
            this.c = i2;
        }
    }

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

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        consumer.getClass();
        int i = this.a;
        int i2 = this.b;
        if (i >= i2 && (i != i2 || this.c >= this.d)) {
            return false;
        }
        Object[] objArr = this.e;
        int i3 = this.c;
        this.c = i3 + 1;
        consumer.accept(objArr[i3]);
        if (this.c == this.e.length) {
            this.c = 0;
            int i4 = this.a + 1;
            this.a = i4;
            Object[][] objArr2 = this.f.e;
            if (objArr2 != null && i4 <= i2) {
                this.e = objArr2[i4];
            }
        }
        return true;
    }
}
