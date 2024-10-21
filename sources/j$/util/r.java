package j$.util;

import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class r extends C0036l implements RandomAccess {
    private static final long serialVersionUID = -2542308836966382001L;

    private Object writeReplace() {
        return new C0036l(this.b);
    }

    @Override // j$.util.C0036l, java.util.List
    public final java.util.List subList(int i, int i2) {
        return new C0036l(this.b.subList(i, i2));
    }
}
