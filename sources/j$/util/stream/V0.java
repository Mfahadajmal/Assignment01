package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import j$.util.function.Consumer$CC;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class V0 implements Spliterator, Consumer {
    private static final Object d = new Object();
    private final Spliterator a;
    private final ConcurrentHashMap b;
    private Object c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public V0(Spliterator spliterator) {
        this(spliterator, new ConcurrentHashMap());
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return (this.a.a() & (-16469)) | 1;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.c = obj;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        Spliterator b = this.a.b();
        if (b != null) {
            return new V0(b, this.b);
        }
        return null;
    }

    @Override // j$.util.Spliterator
    public final long d() {
        return this.a.d();
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        this.a.forEachRemaining(new C0060j(2, this, consumer));
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean m(int i) {
        return j$.util.z.j(this, i);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long n() {
        return j$.util.z.h(this);
    }

    @Override // j$.util.Spliterator
    public final Comparator o() {
        return this.a.o();
    }

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        while (this.a.r(this)) {
            Object obj = this.c;
            if (obj == null) {
                obj = d;
            }
            if (this.b.putIfAbsent(obj, Boolean.TRUE) == null) {
                consumer.accept(this.c);
                this.c = null;
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void u(Consumer consumer, Object obj) {
        Object obj2;
        if (obj != null) {
            obj2 = obj;
        } else {
            obj2 = d;
        }
        if (this.b.putIfAbsent(obj2, Boolean.TRUE) == null) {
            consumer.accept(obj);
        }
    }

    private V0(Spliterator spliterator, ConcurrentHashMap concurrentHashMap) {
        this.a = spliterator;
        this.b = concurrentHashMap;
    }
}
