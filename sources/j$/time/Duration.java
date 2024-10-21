package j$.time;

import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.math.BigInteger;

/* loaded from: classes2.dex */
public final class Duration implements Comparable<Duration>, Serializable {
    public static final Duration ZERO = new Duration(0, 0);
    private static final long serialVersionUID = 3078945930695997490L;
    private final long a;
    private final int b;

    static {
        BigInteger.valueOf(1000000000L);
    }

    private Duration(long j, int i) {
        this.a = j;
        this.b = i;
    }

    public static Duration between(Temporal temporal, Temporal temporal2) {
        try {
            return p(temporal.b(temporal2, ChronoUnit.NANOS));
        } catch (C0010c | ArithmeticException unused) {
            long b = temporal.b(temporal2, ChronoUnit.SECONDS);
            long j = 0;
            try {
                j$.time.temporal.a aVar = j$.time.temporal.a.NANO_OF_SECOND;
                long z = temporal2.z(aVar) - temporal.z(aVar);
                if (b > 0 && z < 0) {
                    b++;
                } else if (b < 0 && z > 0) {
                    b--;
                }
                j = z;
            } catch (C0010c unused2) {
            }
            return r(b, j);
        }
    }

    private static Duration h(long j, int i) {
        return (((long) i) | j) == 0 ? ZERO : new Duration(j, i);
    }

    public static Duration ofMillis(long j) {
        long j2 = j / 1000;
        int i = (int) (j % 1000);
        if (i < 0) {
            i += 1000;
            j2--;
        }
        return h(j2, i * 1000000);
    }

    public static Duration ofMinutes(long j) {
        return h(j$.nio.file.attribute.a.f(j, 60), 0);
    }

    public static Duration ofSeconds(long j) {
        return h(j, 0);
    }

    public static Duration p(long j) {
        long j2 = j / 1000000000;
        int i = (int) (j % 1000000000);
        if (i < 0) {
            i = (int) (i + 1000000000);
            j2--;
        }
        return h(j2, i);
    }

    public static Duration r(long j, long j2) {
        return h(j$.nio.file.attribute.a.e(j, j$.nio.file.attribute.a.c(j2, 1000000000L)), (int) j$.nio.file.attribute.a.d(j2, 1000000000L));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Duration s(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return r(j$.nio.file.attribute.a.e(j$.nio.file.attribute.a.e(this.a, j), j2 / 1000000000), this.b + (j2 % 1000000000));
    }

    private Object writeReplace() {
        return new t((byte) 1, this);
    }

    @Override // java.lang.Comparable
    public int compareTo(Duration duration) {
        int compare = Long.compare(this.a, duration.a);
        return compare != 0 ? compare : this.b - duration.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        Duration duration = (Duration) obj;
        return this.a == duration.a && this.b == duration.b;
    }

    public long getSeconds() {
        return this.a;
    }

    public int hashCode() {
        long j = this.a;
        return (this.b * 51) + ((int) (j ^ (j >>> 32)));
    }

    public boolean isNegative() {
        return this.a < 0;
    }

    public Duration minus(Duration duration) {
        long seconds = duration.getSeconds();
        int i = duration.b;
        return seconds == Long.MIN_VALUE ? s(Long.MAX_VALUE, -i).s(1L, 0L) : s(-seconds, -i);
    }

    public final int n() {
        return this.b;
    }

    public Duration plus(Duration duration) {
        return s(duration.getSeconds(), duration.b);
    }

    public Duration plusSeconds(long j) {
        return s(j, 0L);
    }

    public long toMillis() {
        long j = this.b;
        long j2 = this.a;
        if (j2 < 0) {
            j2++;
            j -= 1000000000;
        }
        return j$.nio.file.attribute.a.e(j$.nio.file.attribute.a.f(j2, 1000), j / 1000000);
    }

    public long toSeconds() {
        return this.a;
    }

    public final String toString() {
        if (this == ZERO) {
            return "PT0S";
        }
        long j = this.a;
        int i = this.b;
        long j2 = (j >= 0 || i <= 0) ? j : 1 + j;
        long j3 = j2 / 3600;
        int i2 = (int) ((j2 % 3600) / 60);
        int i3 = (int) (j2 % 60);
        StringBuilder sb = new StringBuilder(24);
        sb.append("PT");
        if (j3 != 0) {
            sb.append(j3);
            sb.append('H');
        }
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        if (i3 == 0 && i == 0 && sb.length() > 2) {
            return sb.toString();
        }
        if (j >= 0 || i <= 0 || i3 != 0) {
            sb.append(i3);
        } else {
            sb.append("-0");
        }
        if (i > 0) {
            int length = sb.length();
            sb.append(j < 0 ? 2000000000 - i : i + 1000000000);
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            }
            sb.setCharAt(length, '.');
        }
        sb.append('S');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeLong(this.a);
        objectOutput.writeInt(this.b);
    }

    public final long z() {
        long j = this.b;
        long j2 = this.a;
        if (j2 < 0) {
            j2++;
            j -= 1000000000;
        }
        return j$.nio.file.attribute.a.e(j$.nio.file.attribute.a.f(j2, 1000000000L), j);
    }
}
