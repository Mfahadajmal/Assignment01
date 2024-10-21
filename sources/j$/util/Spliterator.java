package j$.util;

import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public interface Spliterator<T> {
    int a();

    Spliterator b();

    long d();

    void forEachRemaining(Consumer consumer);

    boolean m(int i);

    long n();

    Comparator o();

    boolean r(Consumer consumer);
}
