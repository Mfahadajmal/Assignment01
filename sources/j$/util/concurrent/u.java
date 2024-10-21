package j$.util.concurrent;

import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.concurrent.ConcurrentLinkedQueue;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class u implements Spliterator {
    ConcurrentLinkedQueue.Node a;
    int b;
    boolean c;
    final /* synthetic */ ConcurrentLinkedQueue d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(ConcurrentLinkedQueue concurrentLinkedQueue) {
        this.d = concurrentLinkedQueue;
    }

    private ConcurrentLinkedQueue.Node c() {
        ConcurrentLinkedQueue.Node node = this.a;
        if (node == null && !this.c) {
            node = this.d.b();
            this.a = node;
            if (node == null) {
                this.c = true;
            }
        }
        return node;
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return 4368;
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        ConcurrentLinkedQueue.Node node;
        ConcurrentLinkedQueue.Node c = c();
        if (c == null || (node = c.next) == null) {
            return null;
        }
        int min = Math.min(this.b + 1, 33554432);
        this.b = min;
        Object[] objArr = null;
        int i = 0;
        do {
            Object obj = c.item;
            if (obj != null) {
                if (objArr == null) {
                    objArr = new Object[min];
                }
                objArr[i] = obj;
                i++;
            }
            if (c == node) {
                c = this.d.b();
            } else {
                c = node;
            }
            if (c == null || (node = c.next) == null) {
                break;
            }
        } while (i < min);
        this.a = c;
        if (c == null) {
            this.c = true;
        }
        if (i == 0) {
            return null;
        }
        return Spliterators.g(objArr, 0, i, 4368);
    }

    @Override // j$.util.Spliterator
    public final long d() {
        return Long.MAX_VALUE;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        ConcurrentLinkedQueue.Node c = c();
        if (c != null) {
            this.a = null;
            this.c = true;
            this.d.c(consumer, c);
        }
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
        throw new IllegalStateException();
    }

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        E e;
        consumer.getClass();
        ConcurrentLinkedQueue.Node c = c();
        if (c == null) {
            return false;
        }
        do {
            e = c.item;
            ConcurrentLinkedQueue.Node node = c.next;
            if (c == node) {
                c = this.d.b();
            } else {
                c = node;
            }
            if (e != 0) {
                break;
            }
        } while (c != null);
        this.a = c;
        if (c == null) {
            this.c = true;
        }
        if (e != 0) {
            consumer.accept(e);
            return true;
        }
        return false;
    }
}
