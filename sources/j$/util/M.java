package j$.util;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class M implements B {
    private final int[] a;
    private int b;
    private final int c;
    private final int d;

    public M(int[] iArr, int i, int i2, int i3) {
        this.a = iArr;
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
        return new M(this.a, i, i2, this.d);
    }

    @Override // j$.util.Spliterator
    public final long d() {
        return this.c - this.b;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        z.e(this, consumer);
    }

    @Override // j$.util.C
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public final boolean q(IntConsumer intConsumer) {
        intConsumer.getClass();
        int i = this.b;
        if (i < 0 || i >= this.c) {
            return false;
        }
        this.b = i + 1;
        intConsumer.accept(this.a[i]);
        return true;
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

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean r(Consumer consumer) {
        return z.p(this, consumer);
    }

    @Override // j$.util.C
    public final void t(IntConsumer intConsumer) {
        int i;
        intConsumer.getClass();
        int[] iArr = this.a;
        int length = iArr.length;
        int i2 = this.c;
        if (length < i2 || (i = this.b) < 0) {
            return;
        }
        this.b = i2;
        if (i >= i2) {
            return;
        }
        do {
            intConsumer.accept(iArr[i]);
            i++;
        } while (i < i2);
    }
}
