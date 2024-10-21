package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface K {
    K a(int i);

    long f();

    void forEach(Consumer consumer);

    Object[] i(IntFunction intFunction);

    void k(Object[] objArr, int i);

    int m();

    Spliterator spliterator();
}
