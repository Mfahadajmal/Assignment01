package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.BinaryOperator;
import java.util.function.LongFunction;

/* loaded from: classes2.dex */
class P extends AbstractC0054g {
    protected final AbstractC0044b h;
    protected final LongFunction i;
    protected final BinaryOperator j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public P(AbstractC0044b abstractC0044b, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator) {
        super(abstractC0044b, spliterator);
        this.h = abstractC0044b;
        this.i = longFunction;
        this.j = binaryOperator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final Object a() {
        F f = (F) this.i.apply(this.h.f(this.b));
        this.h.t(this.b, f);
        return f.s();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractC0054g
    public final AbstractC0054g c(Spliterator spliterator) {
        return new P(this, spliterator);
    }

    @Override // j$.util.stream.AbstractC0054g, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        AbstractC0054g abstractC0054g = this.d;
        if (abstractC0054g != null) {
            d((K) this.j.apply((K) ((P) abstractC0054g).b(), (K) ((P) this.e).b()));
        }
        super.onCompletion(countedCompleter);
    }

    P(P p, Spliterator spliterator) {
        super(p, spliterator);
        this.h = p.h;
        this.i = p.i;
        this.j = p.j;
    }
}
