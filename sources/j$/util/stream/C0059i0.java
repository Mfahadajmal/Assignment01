package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntConsumer;

/* renamed from: j$.util.stream.i0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0059i0 extends AbstractC0063k0 implements G0 {
    private final int[] h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0059i0(Spliterator spliterator, AbstractC0044b abstractC0044b, int[] iArr) {
        super(spliterator, abstractC0044b, iArr.length);
        this.h = iArr;
    }

    @Override // j$.util.stream.AbstractC0063k0
    final AbstractC0063k0 a(Spliterator spliterator, long j, long j2) {
        return new C0059i0(this, spliterator, j, j2);
    }

    @Override // j$.util.stream.AbstractC0063k0, j$.util.stream.H0
    public final void accept(int i) {
        int i2 = this.f;
        if (i2 >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        int[] iArr = this.h;
        this.f = i2 + 1;
        iArr[i2] = i;
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.nio.file.attribute.a.b(this, intConsumer);
    }

    @Override // j$.util.stream.G0
    public final /* synthetic */ void h(Integer num) {
        G.g(this, num);
    }

    C0059i0(C0059i0 c0059i0, Spliterator spliterator, long j, long j2) {
        super(c0059i0, spliterator, j, j2, c0059i0.h.length);
        this.h = c0059i0.h;
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        h((Integer) obj);
    }
}
