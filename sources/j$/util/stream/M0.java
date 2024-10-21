package j$.util.stream;

import j$.util.Spliterators;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class M0 extends O0 implements IntConsumer {
    @Override // java.util.function.IntConsumer
    public void accept(int i) {
        v();
        int[] iArr = (int[]) this.d;
        int i2 = this.a;
        this.a = i2 + 1;
        iArr[i2] = i;
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.nio.file.attribute.a.b(this, intConsumer);
    }

    @Override // j$.util.stream.O0
    public final Object b(int i) {
        return new int[i];
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            g((IntConsumer) consumer);
        } else if (!f1.a) {
            j$.util.z.e((L0) w(), consumer);
        } else {
            f1.a(getClass(), "{0} calling SpinedBuffer.OfInt.forEach(Consumer)");
            throw null;
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return Spliterators.c(w());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.O0
    public final void o(Object obj, int i, int i2, Object obj2) {
        int[] iArr = (int[]) obj;
        IntConsumer intConsumer = (IntConsumer) obj2;
        while (i < i2) {
            intConsumer.accept(iArr[i]);
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.O0
    public final int q(Object obj) {
        return ((int[]) obj).length;
    }

    public final String toString() {
        int[] iArr = (int[]) d();
        if (iArr.length < 200) {
            return String.format("%s[length=%d, chunks=%d]%s", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.b), Arrays.toString(iArr));
        }
        return String.format("%s[length=%d, chunks=%d]%s...", getClass().getSimpleName(), Integer.valueOf(iArr.length), Integer.valueOf(this.b), Arrays.toString(Arrays.copyOf(iArr, 200)));
    }

    @Override // j$.util.stream.O0
    protected final Object[] u() {
        return new int[8];
    }

    public j$.util.B w() {
        return new L0(this, 0, this.b, 0, this.a);
    }
}
