package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.stream.u, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0081u implements c1, d1 {
    final Consumer a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0081u(Consumer consumer) {
        this.a = consumer;
    }

    @Override // j$.util.stream.c1
    public final Object a(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        abstractC0044b.a(spliterator, abstractC0044b.u(this));
        return null;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(double d) {
        G.c();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.c1
    public final Object b(AbstractC0044b abstractC0044b, Spliterator spliterator) {
        new C0083v(abstractC0044b, spliterator, abstractC0044b.u(this)).invoke();
        return null;
    }

    @Override // j$.util.stream.c1
    public final int d() {
        return S0.p;
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        return false;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(int i) {
        G.i();
        throw null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.a.accept(obj);
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void e(long j) {
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void c() {
    }
}
