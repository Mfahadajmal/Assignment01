package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* loaded from: classes2.dex */
final class B implements H0 {
    boolean a;
    boolean b;
    final /* synthetic */ C c;
    final /* synthetic */ Predicate d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public B(C c, Predicate predicate) {
        boolean z;
        this.c = c;
        this.d = predicate;
        z = c.b;
        this.b = !z;
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
    public final boolean l() {
        return this.a;
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void accept(int i) {
        G.i();
        throw null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        boolean z;
        boolean z2;
        if (this.a) {
            return;
        }
        boolean test = this.d.test(obj);
        C c = this.c;
        z = c.a;
        if (test == z) {
            this.a = true;
            z2 = c.b;
            this.b = z2;
        }
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void e(long j) {
    }

    @Override // j$.util.stream.H0
    public final /* synthetic */ void c() {
    }
}
