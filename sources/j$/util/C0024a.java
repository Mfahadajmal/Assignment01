package j$.util;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.function.Consumer;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0024a implements Spliterator {
    private final java.util.List a;
    private int b;
    private int c;

    private C0024a(C0024a c0024a, int i, int i2) {
        this.a = c0024a.a;
        this.b = i;
        this.c = i2;
    }

    private int c() {
        int i = this.c;
        if (i >= 0) {
            return i;
        }
        int size = this.a.size();
        this.c = size;
        return size;
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return 16464;
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        int c = c();
        int i = this.b;
        int i2 = (c + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        this.b = i2;
        return new C0024a(this, i, i2);
    }

    @Override // j$.util.Spliterator
    public final long d() {
        return c() - this.b;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        int c = c();
        this.b = c;
        for (int i = this.b; i < c; i++) {
            try {
                consumer.accept(this.a.get(i));
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean m(int i) {
        return z.j(this, i);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long n() {
        return z.h(this);
    }

    @Override // j$.util.Spliterator
    public final Comparator o() {
        throw new IllegalStateException();
    }

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        consumer.getClass();
        int c = c();
        int i = this.b;
        if (i < c) {
            this.b = i + 1;
            try {
                consumer.accept(this.a.get(i));
                return true;
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0024a(java.util.List list) {
        this.a = list;
        this.b = 0;
        this.c = -1;
    }
}
