package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Z implements I {
    final int[] a;
    int b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Z(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.a = new int[(int) j];
        this.b = 0;
    }

    @Override // j$.util.stream.J, j$.util.stream.K
    public final J a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.J
    public final Object d() {
        int[] iArr = this.a;
        int length = iArr.length;
        int i = this.b;
        if (length != i) {
            return Arrays.copyOf(iArr, i);
        }
        return iArr;
    }

    @Override // j$.util.stream.K
    public final long f() {
        return this.b;
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ void forEach(Consumer consumer) {
        G.n(this, consumer);
    }

    @Override // j$.util.stream.J
    public final void g(Object obj) {
        IntConsumer intConsumer = (IntConsumer) obj;
        for (int i = 0; i < this.b; i++) {
            intConsumer.accept(this.a[i]);
        }
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ Object[] i(IntFunction intFunction) {
        return G.j(this, intFunction);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ void k(Object[] objArr, int i) {
        G.l(this, (Integer[]) objArr, i);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ int m() {
        return 0;
    }

    @Override // j$.util.stream.J
    public final void n(int i, Object obj) {
        int i2 = this.b;
        System.arraycopy(this.a, 0, (int[]) obj, i, i2);
    }

    @Override // j$.util.stream.J, j$.util.stream.K
    public final j$.util.C spliterator() {
        return Spliterators.f(this.a, 0, this.b);
    }

    public String toString() {
        int[] iArr = this.a;
        return String.format("IntArrayNode[%d][%s]", Integer.valueOf(iArr.length - this.b), Arrays.toString(iArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Z(int[] iArr) {
        this.a = iArr;
        this.b = iArr.length;
    }

    @Override // j$.util.stream.K
    public final /* bridge */ /* synthetic */ K a(int i) {
        a(i);
        throw null;
    }

    @Override // j$.util.stream.K
    public final Spliterator spliterator() {
        return Spliterators.f(this.a, 0, this.b);
    }
}
