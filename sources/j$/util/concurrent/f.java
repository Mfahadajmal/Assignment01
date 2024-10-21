package j$.util.concurrent;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class f extends o implements Spliterator {
    final ConcurrentHashMap i;
    long j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(k[] kVarArr, int i, int i2, int i3, long j, ConcurrentHashMap concurrentHashMap) {
        super(kVarArr, i, i2, i3);
        this.i = concurrentHashMap;
        this.j = j;
    }

    @Override // j$.util.Spliterator
    public final int a() {
        return 4353;
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        int i = this.f;
        int i2 = this.g;
        int i3 = (i + i2) >>> 1;
        if (i3 <= i) {
            return null;
        }
        k[] kVarArr = this.a;
        this.g = i3;
        long j = this.j >>> 1;
        this.j = j;
        return new f(kVarArr, this.h, i3, i2, j, this.i);
    }

    @Override // j$.util.Spliterator
    public final long d() {
        return this.j;
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        while (true) {
            k c = c();
            if (c == null) {
                return;
            } else {
                consumer.accept(new j(c.b, c.c, this.i));
            }
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
        consumer.getClass();
        k c = c();
        if (c == null) {
            return false;
        }
        consumer.accept(new j(c.b, c.c, this.i));
        return true;
    }
}
