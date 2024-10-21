package j$.time.zone;

import j$.time.A;
import j$.time.Duration;
import j$.time.chrono.AbstractC0019i;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class b implements Comparable, Serializable {
    private static final long serialVersionUID = -6946044323557704546L;
    private final long a;
    private final j$.time.i b;
    private final A c;
    private final A d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(long j, A a, A a2) {
        this.a = j;
        this.b = j$.time.i.R(j, 0, a);
        this.c = a;
        this.d = a2;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 2, this);
    }

    public final boolean D() {
        return this.d.Q() > this.c.Q();
    }

    public final long F() {
        return this.a;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.a, ((b) obj).a);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a == bVar.a && this.c.equals(bVar.c) && this.d.equals(bVar.d);
    }

    public final j$.time.i h() {
        return this.b.T(this.d.Q() - this.c.Q());
    }

    public final int hashCode() {
        return (this.b.hashCode() ^ this.c.hashCode()) ^ Integer.rotateLeft(this.d.hashCode(), 16);
    }

    public final j$.time.i n() {
        return this.b;
    }

    public final Duration p() {
        return Duration.ofSeconds(this.d.Q() - this.c.Q());
    }

    public final A r() {
        return this.d;
    }

    public final A s() {
        return this.c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Transition[");
        sb.append(D() ? "Gap" : "Overlap");
        sb.append(" at ");
        sb.append(this.b);
        sb.append(this.c);
        sb.append(" to ");
        sb.append(this.d);
        sb.append(']');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        a.c(this.a, objectOutput);
        a.d(this.c, objectOutput);
        a.d(this.d, objectOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List z() {
        if (D()) {
            return Collections.emptyList();
        }
        return j$.desugar.sun.nio.fs.c.a(new Object[]{this.c, this.d});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(j$.time.i iVar, A a, A a2) {
        iVar.getClass();
        this.a = AbstractC0019i.n(iVar, a);
        this.b = iVar;
        this.c = a;
        this.d = a2;
    }
}
