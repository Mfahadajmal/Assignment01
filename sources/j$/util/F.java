package j$.util;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* loaded from: classes2.dex */
final class F implements v, DoubleConsumer, t {
    boolean a = false;
    double b;
    final /* synthetic */ A c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public F(A a) {
        this.c = a;
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.a = true;
        this.b = d;
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.nio.file.attribute.a.a(this, doubleConsumer);
    }

    @Override // java.util.Iterator, j$.util.t
    public final void forEachRemaining(Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            DoubleConsumer doubleConsumer = (DoubleConsumer) consumer;
            doubleConsumer.getClass();
            while (hasNext()) {
                if (!this.a && !hasNext()) {
                    throw new NoSuchElementException();
                }
                this.a = false;
                doubleConsumer.accept(this.b);
            }
            return;
        }
        consumer.getClass();
        if (!Q.a) {
            u uVar = new u(consumer);
            while (hasNext()) {
                if (!this.a && !hasNext()) {
                    throw new NoSuchElementException();
                }
                this.a = false;
                uVar.accept(this.b);
            }
            return;
        }
        Q.a(F.class, "{0} calling PrimitiveIterator.OfDouble.forEachRemainingDouble(action::accept)");
        throw null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.a) {
            this.c.q(this);
        }
        return this.a;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!Q.a) {
            if (!this.a && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.a = false;
            return Double.valueOf(this.b);
        }
        Q.a(F.class, "{0} calling PrimitiveIterator.OfDouble.nextLong()");
        throw null;
    }
}
