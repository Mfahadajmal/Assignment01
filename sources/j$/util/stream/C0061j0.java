package j$.util.stream;

import j$.util.Spliterator;

/* renamed from: j$.util.stream.j0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0061j0 extends AbstractC0063k0 {
    private final Object[] h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0061j0(Spliterator spliterator, AbstractC0044b abstractC0044b, Object[] objArr) {
        super(spliterator, abstractC0044b, objArr.length);
        this.h = objArr;
    }

    @Override // j$.util.stream.AbstractC0063k0
    final AbstractC0063k0 a(Spliterator spliterator, long j, long j2) {
        return new C0061j0(this, spliterator, j, j2);
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.f;
        if (i >= this.g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f));
        }
        Object[] objArr = this.h;
        this.f = i + 1;
        objArr[i] = obj;
    }

    C0061j0(C0061j0 c0061j0, Spliterator spliterator, long j, long j2) {
        super(c0061j0, spliterator, j, j2, c0061j0.h.length);
        this.h = c0061j0.h;
    }
}
