package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

/* renamed from: j$.util.stream.b0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0045b0 extends M0 implements I, F, G0 {
    @Override // j$.util.stream.J, j$.util.stream.K
    public final J a(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(double d) {
        G.c();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.H0
    public final void c() {
    }

    @Override // j$.util.stream.O0, j$.util.stream.J
    public final Object d() {
        return (int[]) super.d();
    }

    @Override // j$.util.stream.H0
    public final void e(long j) {
        clear();
        t(j);
    }

    @Override // j$.util.stream.O0, j$.util.stream.J
    public final void g(Object obj) {
        super.g((IntConsumer) obj);
    }

    @Override // j$.util.stream.G0
    public final /* synthetic */ void h(Integer num) {
        G.g(this, num);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ Object[] i(IntFunction intFunction) {
        return G.j(this, intFunction);
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ void k(Object[] objArr, int i) {
        G.l(this, (Integer[]) objArr, i);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        return false;
    }

    @Override // j$.util.stream.K
    public final /* synthetic */ int m() {
        return 0;
    }

    @Override // j$.util.stream.O0, j$.util.stream.J
    public final void n(int i, Object obj) {
        super.n(i, (int[]) obj);
    }

    @Override // j$.util.stream.F
    public final K s() {
        return this;
    }

    @Override // java.lang.Iterable, j$.util.stream.J, j$.util.stream.K
    public final j$.util.C spliterator() {
        return super.w();
    }

    @Override // j$.util.stream.K
    public final /* bridge */ /* synthetic */ K a(int i) {
        a(i);
        throw null;
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        h((Integer) obj);
    }

    @Override // java.lang.Iterable, j$.util.stream.K
    public final Spliterator spliterator() {
        return super.w();
    }
}
