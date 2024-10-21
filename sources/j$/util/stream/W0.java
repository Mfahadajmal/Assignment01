package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes2.dex */
public final /* synthetic */ class W0 implements F0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ DoubleConsumer b;

    public /* synthetic */ W0(DoubleConsumer doubleConsumer, int i) {
        this.a = i;
        this.b = doubleConsumer;
    }

    @Override // j$.util.stream.F0, j$.util.stream.H0
    public final void accept(double d) {
        switch (this.a) {
            case 0:
                ((K0) this.b).accept(d);
                return;
            default:
                this.b.accept(d);
                return;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
            case 0:
                return Consumer$CC.$default$andThen(this, consumer);
            default:
                return Consumer$CC.$default$andThen(this, consumer);
        }
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void c() {
        int i = this.a;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void e(long j) {
        int i = this.a;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ boolean l() {
        switch (this.a) {
            case 0:
                return false;
            default:
                return false;
        }
    }

    @Override // j$.util.stream.F0
    public final /* synthetic */ void p(Double d) {
        switch (this.a) {
            case 0:
                G.e(this, d);
                return;
            default:
                G.e(this, d);
                return;
        }
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(int i) {
        switch (this.a) {
            case 0:
                G.i();
                throw null;
            default:
                G.i();
                throw null;
        }
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        switch (this.a) {
            case 0:
                return j$.nio.file.attribute.a.a(this, doubleConsumer);
            default:
                return j$.nio.file.attribute.a.a(this, doubleConsumer);
        }
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.a) {
            case 0:
                p((Double) obj);
                return;
            default:
                p((Double) obj);
                return;
        }
    }

    private final /* synthetic */ void a(long j) {
    }

    private final /* synthetic */ void b(long j) {
    }

    private final /* synthetic */ void d() {
    }

    private final /* synthetic */ void f() {
    }
}
