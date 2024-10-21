package j$.util;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class z {
    public static void c(A a, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            a.k((DoubleConsumer) consumer);
        } else if (!Q.a) {
            consumer.getClass();
            a.k(new u(consumer));
        } else {
            Q.a(a.getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
            throw null;
        }
    }

    public static void e(B b, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            b.t((IntConsumer) consumer);
        } else if (!Q.a) {
            consumer.getClass();
            b.t(new w(consumer));
        } else {
            Q.a(b.getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
            throw null;
        }
    }

    public static long h(Spliterator spliterator) {
        if ((spliterator.a() & 64) == 0) {
            return -1L;
        }
        return spliterator.d();
    }

    public static boolean j(Spliterator spliterator, int i) {
        if ((spliterator.a() & i) == i) {
            return true;
        }
        return false;
    }

    public static boolean l(A a, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            return a.q((DoubleConsumer) consumer);
        }
        if (!Q.a) {
            consumer.getClass();
            return a.q(new u(consumer));
        }
        Q.a(a.getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
        throw null;
    }

    public static boolean p(B b, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            return b.i((IntConsumer) consumer);
        }
        if (!Q.a) {
            consumer.getClass();
            return b.i(new w(consumer));
        }
        Q.a(b.getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
        throw null;
    }

    public static void s(Iterator it, Consumer consumer) {
        if (it instanceof t) {
            ((t) it).forEachRemaining(consumer);
            return;
        }
        consumer.getClass();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    public int a() {
        return 16448;
    }

    public Spliterator b() {
        return null;
    }

    public long d() {
        return 0L;
    }

    public void f(Object obj) {
        obj.getClass();
    }

    public boolean g(Object obj) {
        obj.getClass();
        return false;
    }
}
