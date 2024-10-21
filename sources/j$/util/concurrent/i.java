package j$.util.concurrent;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
final class i extends o implements Spliterator {
    public final /* synthetic */ int i;
    long j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(k[] kVarArr, int i, int i2, int i3, long j, int i4) {
        super(kVarArr, i, i2, i3);
        this.i = i4;
        this.j = j;
    }

    @Override // j$.util.Spliterator
    public final int a() {
        switch (this.i) {
            case 0:
                return 4353;
            default:
                return 4352;
        }
    }

    @Override // j$.util.Spliterator
    public final Spliterator b() {
        switch (this.i) {
            case 0:
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
                return new i(kVarArr, this.h, i3, i2, j, 0);
            default:
                int i4 = this.f;
                int i5 = this.g;
                int i6 = (i4 + i5) >>> 1;
                if (i6 <= i4) {
                    return null;
                }
                k[] kVarArr2 = this.a;
                this.g = i6;
                long j2 = this.j >>> 1;
                this.j = j2;
                return new i(kVarArr2, this.h, i6, i5, j2, 1);
        }
    }

    @Override // j$.util.Spliterator
    public final long d() {
        switch (this.i) {
            case 0:
                return this.j;
            default:
                return this.j;
        }
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        switch (this.i) {
            case 0:
                consumer.getClass();
                while (true) {
                    k c = c();
                    if (c != null) {
                        consumer.accept(c.b);
                    } else {
                        return;
                    }
                }
            default:
                consumer.getClass();
                while (true) {
                    k c2 = c();
                    if (c2 != null) {
                        consumer.accept(c2.c);
                    } else {
                        return;
                    }
                }
        }
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean m(int i) {
        switch (this.i) {
            case 0:
                return j$.util.z.j(this, i);
            default:
                return j$.util.z.j(this, i);
        }
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long n() {
        switch (this.i) {
            case 0:
                return j$.util.z.h(this);
            default:
                return j$.util.z.h(this);
        }
    }

    @Override // j$.util.Spliterator
    public final Comparator o() {
        switch (this.i) {
            case 0:
                throw new IllegalStateException();
            default:
                throw new IllegalStateException();
        }
    }

    @Override // j$.util.Spliterator
    public final boolean r(Consumer consumer) {
        switch (this.i) {
            case 0:
                consumer.getClass();
                k c = c();
                if (c == null) {
                    return false;
                }
                consumer.accept(c.b);
                return true;
            default:
                consumer.getClass();
                k c2 = c();
                if (c2 == null) {
                    return false;
                }
                consumer.accept(c2.c);
                return true;
        }
    }
}
