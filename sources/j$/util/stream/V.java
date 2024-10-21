package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntFunction;

/* loaded from: classes2.dex */
class V implements H {
    final double[] a;
    int b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public V(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.a = new double[(int) j];
        this.b = 0;
    }

    @Override // j$.util.stream.J, j$.util.stream.K
    public final J a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.J
    public final Object d() {
        double[] dArr = this.a;
        int length = dArr.length;
        int i = this.b;
        if (length != i) {
            return Arrays.copyOf(dArr, i);
        }
        return dArr;
    }

    @Override // j$.util.stream.K
    public final long f() {
        return this.b;
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ void forEach(Consumer consumer) {
        G.m(this, consumer);
    }

    @Override // j$.util.stream.J
    public final void g(Object obj) {
        DoubleConsumer doubleConsumer = (DoubleConsumer) obj;
        for (int i = 0; i < this.b; i++) {
            doubleConsumer.accept(this.a[i]);
        }
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ Object[] i(IntFunction intFunction) {
        return G.j(this, intFunction);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ void k(Object[] objArr, int i) {
        G.k(this, (Double[]) objArr, i);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ int m() {
        return 0;
    }

    @Override // j$.util.stream.J
    public final void n(int i, Object obj) {
        int i2 = this.b;
        System.arraycopy(this.a, 0, (double[]) obj, i, i2);
    }

    @Override // j$.util.stream.J, j$.util.stream.K
    public final j$.util.C spliterator() {
        return Spliterators.e(this.a, 0, this.b);
    }

    public String toString() {
        double[] dArr = this.a;
        return String.format("DoubleArrayNode[%d][%s]", Integer.valueOf(dArr.length - this.b), Arrays.toString(dArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public V(double[] dArr) {
        this.a = dArr;
        this.b = dArr.length;
    }

    @Override // j$.util.stream.K
    public final /* bridge */ /* synthetic */ K a(int i) {
        a(i);
        throw null;
    }

    @Override // j$.util.stream.K
    public final Spliterator spliterator() {
        return Spliterators.e(this.a, 0, this.b);
    }
}
