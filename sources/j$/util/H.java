package j$.util;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes2.dex */
final class H implements A {
    private final double[] a;
    private int b;
    private final int c;
    private final int d;

    public H(double[] dArr, int i, int i2, int i3) {
        this.a = dArr;
        this.b = i;
        this.c = i2;
        this.d = i3 | 16448;
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return this.d;
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        int i = this.b;
        int i2 = (this.c + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        this.b = i2;
        return new H(this.a, i, i2, this.d);
    }

    @Override // j$.util.Spliterator
    public final long d() {
        return this.c - this.b;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        z.c(this, consumer);
    }

    @Override // j$.util.C
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void t(DoubleConsumer doubleConsumer) {
        int i;
        doubleConsumer.getClass();
        double[] dArr = this.a;
        int length = dArr.length;
        int i2 = this.c;
        if (length < i2 || (i = this.b) < 0) {
            return;
        }
        this.b = i2;
        if (i >= i2) {
            return;
        }
        do {
            doubleConsumer.accept(dArr[i]);
            i++;
        } while (i < i2);
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
        if (z.j(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }

    @Override // j$.util.C
    public final boolean q(DoubleConsumer doubleConsumer) {
        doubleConsumer.getClass();
        int i = this.b;
        if (i < 0 || i >= this.c) {
            return false;
        }
        this.b = i + 1;
        doubleConsumer.accept(this.a[i]);
        return true;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean r(Consumer consumer) {
        return z.l(this, consumer);
    }
}
