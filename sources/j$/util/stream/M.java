package j$.util.stream;

import j$.util.Spliterator;
import j$.util.Spliterators;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class M implements K {
    final Object[] a;
    int b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public M(long j, IntFunction intFunction) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.a = (Object[]) intFunction.apply((int) j);
        this.b = 0;
    }

    @Override // j$.util.stream.K
    public final K a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.K
    public final long f() {
        return this.b;
    }

    @Override // j$.util.stream.K
    public final void forEach(Consumer consumer) {
        for (int i = 0; i < this.b; i++) {
            consumer.accept(this.a[i]);
        }
    }

    @Override // j$.util.stream.K
    public final Object[] i(IntFunction intFunction) {
        Object[] objArr = this.a;
        if (objArr.length == this.b) {
            return objArr;
        }
        throw new IllegalStateException();
    }

    @Override // j$.util.stream.K
    public final void k(Object[] objArr, int i) {
        System.arraycopy(this.a, 0, objArr, i, this.b);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ int m() {
        return 0;
    }

    @Override // j$.util.stream.K
    public final Spliterator spliterator() {
        return Spliterators.g(this.a, 0, this.b, 1040);
    }

    public String toString() {
        Object[] objArr = this.a;
        return String.format("ArrayNode[%d][%s]", Integer.valueOf(objArr.length - this.b), Arrays.toString(objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public M(Object[] objArr) {
        this.a = objArr;
        this.b = objArr.length;
    }
}
