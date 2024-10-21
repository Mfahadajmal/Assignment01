package j$.util.stream;

import j$.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class J0 extends N0 implements j$.util.A {
    final /* synthetic */ K0 g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J0(K0 k0, int i, int i2, int i3, int i4) {
        super(k0, i, i2, i3, i4);
        this.g = k0;
    }

    @Override // j$.util.stream.N0
    final void c(int i, Object obj, Object obj2) {
        ((DoubleConsumer) obj2).accept(((double[]) obj)[i]);
    }

    @Override // j$.util.stream.N0
    final j$.util.C e(Object obj, int i, int i2) {
        return Spliterators.e((double[]) obj, i, i2 + i);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.util.z.c(this, consumer);
    }

    @Override // j$.util.stream.N0
    final j$.util.C h(int i, int i2, int i3, int i4) {
        return new J0(this.g, i, i2, i3, i4);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean r(Consumer consumer) {
        return j$.util.z.l(this, consumer);
    }
}
