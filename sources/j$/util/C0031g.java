package j$.util;

import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.util.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0031g extends C0029e implements RandomAccess {
    private static final long serialVersionUID = 1530674583602358482L;

    private Object writeReplace() {
        return new C0029e(this.c);
    }

    @Override // j$.util.C0029e, java.util.List
    public final java.util.List subList(int i, int i2) {
        C0029e c0029e;
        synchronized (this.b) {
            c0029e = new C0029e(this.c.subList(i, i2), this.b);
        }
        return c0029e;
    }
}
