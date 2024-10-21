package j$.util.stream;

import j$.util.Spliterator;
import java.util.ArrayDeque;
import java.util.function.Consumer;

/* renamed from: j$.util.stream.f0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
final class C0053f0 extends AbstractC0055g0 {
    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            Spliterator spliterator = this.c;
            if (spliterator != null) {
                spliterator.forEachRemaining(consumer);
                return;
            }
            ArrayDeque e = e();
            while (true) {
                K c = AbstractC0055g0.c(e);
                if (c == null) {
                    this.a = null;
                    return;
                }
                c.forEach(consumer);
            }
        }
        do {
        } while (r(consumer));
    }

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        K c;
        if (!h()) {
            return false;
        }
        boolean r = this.d.r(consumer);
        if (!r) {
            if (this.c == null && (c = AbstractC0055g0.c(this.e)) != null) {
                Spliterator spliterator = c.spliterator();
                this.d = spliterator;
                return spliterator.r(consumer);
            }
            this.a = null;
        }
        return r;
    }
}
