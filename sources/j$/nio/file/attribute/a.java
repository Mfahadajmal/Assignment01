package j$.nio.file.attribute;

import j$.util.function.b;
import j$.util.function.d;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class a {
    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.function.b] */
    public static b a(final DoubleConsumer doubleConsumer, final DoubleConsumer doubleConsumer2) {
        doubleConsumer2.getClass();
        return new DoubleConsumer() { // from class: j$.util.function.b
            @Override // java.util.function.DoubleConsumer
            public final void accept(double d) {
                DoubleConsumer.this.accept(d);
                doubleConsumer2.accept(d);
            }

            public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer3) {
                return j$.nio.file.attribute.a.a(this, doubleConsumer3);
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [j$.util.function.d] */
    public static d b(final IntConsumer intConsumer, final IntConsumer intConsumer2) {
        intConsumer2.getClass();
        return new IntConsumer() { // from class: j$.util.function.d
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                IntConsumer.this.accept(i);
                intConsumer2.accept(i);
            }

            public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer3) {
                return j$.nio.file.attribute.a.b(this, intConsumer3);
            }
        };
    }

    public static /* synthetic */ long c(long j, long j2) {
        long j3 = j / j2;
        return (j - (j2 * j3) != 0 && (((j ^ j2) >> 63) | 1) < 0) ? j3 - 1 : j3;
    }

    public static /* synthetic */ long d(long j, long j2) {
        long j3 = j % j2;
        if (j3 == 0) {
            return 0L;
        }
        if ((((j ^ j2) >> 63) | 1) <= 0) {
            return j3 + j2;
        }
        return j3;
    }

    public static /* synthetic */ long e(long j, long j2) {
        boolean z;
        long j3 = j + j2;
        boolean z2 = false;
        if ((j2 ^ j) < 0) {
            z = true;
        } else {
            z = false;
        }
        if ((j ^ j3) >= 0) {
            z2 = true;
        }
        if (z | z2) {
            return j3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long f(long j, long j2) {
        boolean z;
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        if (numberOfLeadingZeros >= 64) {
            boolean z2 = false;
            if (j >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (j2 != Long.MIN_VALUE) {
                z2 = true;
            }
            if (z | z2) {
                long j3 = j * j2;
                if (j == 0 || j3 / j == j2) {
                    return j3;
                }
            }
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long g(long j, long j2) {
        boolean z;
        long j3 = j - j2;
        boolean z2 = false;
        if ((j2 ^ j) >= 0) {
            z = true;
        } else {
            z = false;
        }
        if ((j ^ j3) >= 0) {
            z2 = true;
        }
        if (z | z2) {
            return j3;
        }
        throw new ArithmeticException();
    }
}
