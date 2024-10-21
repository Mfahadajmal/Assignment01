package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Supplier;

/* renamed from: j$.util.stream.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final /* synthetic */ class C0042a implements Supplier, LongFunction, BooleanSupplier, H0, Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0042a(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // j$.util.stream.H0
    public /* synthetic */ void accept(double d) {
        switch (this.a) {
            case 5:
                G.c();
                throw null;
            default:
                G.c();
                throw null;
        }
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.a) {
            case 5:
                return Consumer$CC.$default$andThen(this, consumer);
            case 6:
            default:
                return Consumer$CC.$default$andThen(this, consumer);
            case 7:
                return Consumer$CC.$default$andThen(this, consumer);
        }
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j) {
        if (j >= 0 && j < 2147483639) {
            return new M(j, (IntFunction) this.b);
        }
        return new C0065l0();
    }

    @Override // j$.util.stream.H0
    public /* synthetic */ void c() {
        int i = this.a;
    }

    @Override // j$.util.stream.H0
    public /* synthetic */ void e(long j) {
        int i = this.a;
    }

    @Override // java.util.function.Supplier
    public Object get() {
        Object obj = this.b;
        switch (this.a) {
            case 0:
                return ((AbstractC0044b) obj).k();
            case 1:
                Set set = Collectors.a;
                return new j$.util.O((CharSequence) obj);
            default:
                return (Spliterator) obj;
        }
    }

    @Override // java.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        switch (this.a) {
            case 3:
                X0 x0 = (X0) this.b;
                return x0.d.r(x0.e);
            case 4:
                Z0 z0 = (Z0) this.b;
                return z0.d.r(z0.e);
            default:
                a1 a1Var = (a1) this.b;
                return a1Var.d.r(a1Var.e);
        }
    }

    @Override // j$.util.stream.H0
    public /* synthetic */ boolean l() {
        switch (this.a) {
            case 5:
                return false;
            default:
                return false;
        }
    }

    @Override // j$.util.stream.H0
    public /* synthetic */ void accept(int i) {
        switch (this.a) {
            case 5:
                G.i();
                throw null;
            default:
                G.i();
                throw null;
        }
    }

    @Override // java.util.function.Consumer
    public void accept(Object obj) {
        switch (this.a) {
            case 5:
                ((P0) this.b).accept(obj);
                return;
            case 6:
            default:
                ((ArrayList) this.b).add(obj);
                return;
            case 7:
                ((Consumer) this.b).accept(obj);
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
