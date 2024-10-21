package j$.util.stream;

import java.util.function.ToIntFunction;

/* loaded from: classes2.dex */
final class A0 extends A {
    final /* synthetic */ ToIntFunction k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A0(C0 c0, int i, ToIntFunction toIntFunction) {
        super(c0, i);
        this.k = toIntFunction;
    }

    @Override // j$.util.stream.AbstractC0044b
    final boolean o() {
        return false;
    }

    @Override // j$.util.stream.AbstractC0044b
    final H0 p(int i, H0 h0) {
        return new C0066m(this, h0, 3);
    }
}
