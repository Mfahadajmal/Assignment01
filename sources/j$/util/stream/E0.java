package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public abstract class E0 implements H0 {
    protected final H0 a;

    public E0(H0 h0) {
        h0.getClass();
        this.a = h0;
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
    public void c() {
        this.a.c();
    }

    @Override // j$.util.stream.H0
    public void e(long j) {
        this.a.e(j);
    }

    @Override // j$.util.stream.H0
    public final boolean l() {
        return this.a.l();
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(int i) {
        G.i();
        throw null;
    }
}
