package j$.util;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
final class E implements x, IntConsumer, t {
    boolean a = false;
    int b;
    final /* synthetic */ B c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public E(B b) {
        this.c = b;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.a = true;
        this.b = i;
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return j$.nio.file.attribute.a.b(this, intConsumer);
    }

    @Override // java.util.Iterator, j$.util.t
    public final void forEachRemaining(Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            IntConsumer intConsumer = (IntConsumer) consumer;
            intConsumer.getClass();
            while (hasNext()) {
                if (!this.a && !hasNext()) {
                    throw new NoSuchElementException();
                }
                this.a = false;
                intConsumer.accept(this.b);
            }
            return;
        }
        consumer.getClass();
        if (!Q.a) {
            w wVar = new w(consumer);
            while (hasNext()) {
                if (!this.a && !hasNext()) {
                    throw new NoSuchElementException();
                }
                this.a = false;
                wVar.accept(this.b);
            }
            return;
        }
        Q.a(E.class, "{0} calling PrimitiveIterator.OfInt.forEachRemainingInt(action::accept)");
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
            return Integer.valueOf(this.b);
        }
        Q.a(E.class, "{0} calling PrimitiveIterator.OfInt.nextInt()");
        throw null;
    }
}
