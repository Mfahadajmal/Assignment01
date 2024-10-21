package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
public final /* synthetic */ class Y0 implements G0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ IntConsumer b;

    public /* synthetic */ Y0(IntConsumer intConsumer, int i) {
        this.a = i;
        this.b = intConsumer;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(double d) {
        switch (this.a) {
            case 0:
                G.c();
                throw null;
            default:
                G.c();
                throw null;
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

    @Override // j$.util.stream.G0
    public final /* synthetic */ void h(Integer num) {
        switch (this.a) {
            case 0:
                G.g(this, num);
                return;
            default:
                G.g(this, num);
                return;
        }
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

    @Override // j$.util.stream.G0, j$.util.stream.H0
    public final void accept(int i) {
        switch (this.a) {
            case 0:
                ((M0) this.b).accept(i);
                return;
            default:
                this.b.accept(i);
                return;
        }
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        switch (this.a) {
            case 0:
                return j$.nio.file.attribute.a.b(this, intConsumer);
            default:
                return j$.nio.file.attribute.a.b(this, intConsumer);
        }
    }

    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.a) {
            case 0:
                h((Integer) obj);
                return;
            default:
                h((Integer) obj);
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
