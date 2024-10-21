package j$.time;

import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: j$.time.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0008a extends AbstractC0009b implements Serializable {
    static final C0008a b;
    private static final long serialVersionUID = 6740630888130243051L;
    private final A a;

    static {
        System.currentTimeMillis();
        b = new C0008a(A.e);
    }

    C0008a(A a) {
        this.a = a;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0008a)) {
            return false;
        }
        return this.a.equals(((C0008a) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode() + 1;
    }

    public final String toString() {
        return "SystemClock[" + String.valueOf(this.a) + "]";
    }
}
