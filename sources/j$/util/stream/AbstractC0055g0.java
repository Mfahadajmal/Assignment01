package j$.util.stream;

import j$.util.Spliterator;
import java.util.ArrayDeque;
import java.util.Comparator;

/* renamed from: j$.util.stream.g0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
abstract class AbstractC0055g0 implements Spliterator {
    K a;
    int b;
    Spliterator c;
    Spliterator d;
    ArrayDeque e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0055g0(K k) {
        this.a = k;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static K c(ArrayDeque arrayDeque) {
        while (true) {
            K k = (K) arrayDeque.pollFirst();
            if (k != null) {
                if (k.m() == 0) {
                    if (k.f() > 0) {
                        return k;
                    }
                } else {
                    for (int m = k.m() - 1; m >= 0; m--) {
                        arrayDeque.addFirst(k.a(m));
                    }
                }
            } else {
                return null;
            }
        }
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return 64;
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        K k = this.a;
        if (k == null || this.d != null) {
            return null;
        }
        Spliterator spliterator = this.c;
        if (spliterator != null) {
            return spliterator.b();
        }
        if (this.b < k.m() - 1) {
            K k2 = this.a;
            int i = this.b;
            this.b = i + 1;
            return k2.a(i).spliterator();
        }
        K a = this.a.a(this.b);
        this.a = a;
        if (a.m() == 0) {
            Spliterator spliterator2 = this.a.spliterator();
            this.c = spliterator2;
            return spliterator2.b();
        }
        K k3 = this.a;
        this.b = 1;
        return k3.a(0).spliterator();
    }

    @Override // j$.util.Spliterator
    public final long d() {
        long j = 0;
        if (this.a == null) {
            return 0L;
        }
        Spliterator spliterator = this.c;
        if (spliterator != null) {
            return spliterator.d();
        }
        for (int i = this.b; i < this.a.m(); i++) {
            j += this.a.a(i).f();
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ArrayDeque e() {
        ArrayDeque arrayDeque = new ArrayDeque(8);
        int m = this.a.m();
        while (true) {
            m--;
            if (m >= this.b) {
                arrayDeque.addFirst(this.a.a(m));
            } else {
                return arrayDeque;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean h() {
        if (this.a == null) {
            return false;
        }
        if (this.d != null) {
            return true;
        }
        Spliterator spliterator = this.c;
        if (spliterator == null) {
            ArrayDeque e = e();
            this.e = e;
            K c = c(e);
            if (c == null) {
                this.a = null;
                return false;
            }
            spliterator = c.spliterator();
        }
        this.d = spliterator;
        return true;
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
}
