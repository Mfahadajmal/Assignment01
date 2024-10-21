package j$.util.stream;

import j$.util.stream.Collector;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.s0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0078s0 extends G {
    final /* synthetic */ BinaryOperator a;
    final /* synthetic */ BiConsumer b;
    final /* synthetic */ Supplier c;
    final /* synthetic */ Collector d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0078s0(T0 t0, BinaryOperator binaryOperator, BiConsumer biConsumer, Supplier supplier, Collector collector) {
        this.a = binaryOperator;
        this.b = biConsumer;
        this.c = supplier;
        this.d = collector;
    }

    @Override // j$.util.stream.G, j$.util.stream.c1
    public final int d() {
        if (this.d.a().contains(Collector.Characteristics.UNORDERED)) {
            return S0.p;
        }
        return 0;
    }

    @Override // j$.util.stream.G
    public final InterfaceC0088x0 r() {
        return new C0080t0(this.c, this.b, this.a);
    }
}
