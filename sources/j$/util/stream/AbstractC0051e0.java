package j$.util.stream;

import j$.util.Spliterator;
import java.util.ArrayDeque;

/* renamed from: j$.util.stream.e0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
abstract class AbstractC0051e0 extends AbstractC0055g0 implements j$.util.C {
    @Override // j$.util.C
    /* renamed from: f, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final void t(Object obj) {
        if (this.a == null) {
            return;
        }
        if (this.d == null) {
            Spliterator spliterator = this.c;
            if (spliterator != null) {
                ((j$.util.C) spliterator).t(obj);
                return;
            }
            ArrayDeque e = e();
            while (true) {
                J j = (J) AbstractC0055g0.c(e);
                if (j == null) {
                    this.a = null;
                    return;
                }
                j.g(obj);
            }
        }
        do {
        } while (q(obj));
    }

    @Override // j$.util.C
    /* renamed from: g, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final boolean q(Object obj) {
        J j;
        if (!h()) {
            return false;
        }
        boolean q = ((j$.util.C) this.d).q(obj);
        if (!q) {
            if (this.c == null && (j = (J) AbstractC0055g0.c(this.e)) != null) {
                j$.util.C spliterator = j.spliterator();
                this.d = spliterator;
                return spliterator.q(obj);
            }
            this.a = null;
        }
        return q;
    }
}
