package j$.time;

import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class z implements Serializable {
    private static final long serialVersionUID = 8352817235686L;

    static {
        Map.Entry[] entryArr = {new AbstractMap.SimpleImmutableEntry("ACT", "Australia/Darwin"), new AbstractMap.SimpleImmutableEntry("AET", "Australia/Sydney"), new AbstractMap.SimpleImmutableEntry("AGT", "America/Argentina/Buenos_Aires"), new AbstractMap.SimpleImmutableEntry("ART", "Africa/Cairo"), new AbstractMap.SimpleImmutableEntry("AST", "America/Anchorage"), new AbstractMap.SimpleImmutableEntry("BET", "America/Sao_Paulo"), new AbstractMap.SimpleImmutableEntry("BST", "Asia/Dhaka"), new AbstractMap.SimpleImmutableEntry("CAT", "Africa/Harare"), new AbstractMap.SimpleImmutableEntry("CNT", "America/St_Johns"), new AbstractMap.SimpleImmutableEntry("CST", "America/Chicago"), new AbstractMap.SimpleImmutableEntry("CTT", "Asia/Shanghai"), new AbstractMap.SimpleImmutableEntry("EAT", "Africa/Addis_Ababa"), new AbstractMap.SimpleImmutableEntry("ECT", "Europe/Paris"), new AbstractMap.SimpleImmutableEntry("IET", "America/Indiana/Indianapolis"), new AbstractMap.SimpleImmutableEntry("IST", "Asia/Kolkata"), new AbstractMap.SimpleImmutableEntry("JST", "Asia/Tokyo"), new AbstractMap.SimpleImmutableEntry("MIT", "Pacific/Apia"), new AbstractMap.SimpleImmutableEntry("NET", "Asia/Yerevan"), new AbstractMap.SimpleImmutableEntry("NST", "Pacific/Auckland"), new AbstractMap.SimpleImmutableEntry("PLT", "Asia/Karachi"), new AbstractMap.SimpleImmutableEntry("PNT", "America/Phoenix"), new AbstractMap.SimpleImmutableEntry("PRT", "America/Puerto_Rico"), new AbstractMap.SimpleImmutableEntry("PST", "America/Los_Angeles"), new AbstractMap.SimpleImmutableEntry("SST", "Pacific/Guadalcanal"), new AbstractMap.SimpleImmutableEntry("VST", "Asia/Ho_Chi_Minh"), new AbstractMap.SimpleImmutableEntry("EST", "-05:00"), new AbstractMap.SimpleImmutableEntry("MST", "-07:00"), new AbstractMap.SimpleImmutableEntry("HST", "-10:00")};
        HashMap hashMap = new HashMap(28);
        for (int i = 0; i < 28; i++) {
            Map.Entry entry = entryArr[i];
            Object key = entry.getKey();
            key.getClass();
            Object value = entry.getValue();
            value.getClass();
            if (hashMap.put(key, value) != null) {
                throw new IllegalArgumentException("duplicate key: " + key);
            }
        }
        Collections.unmodifiableMap(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public z() {
        if (getClass() != A.class && getClass() != B.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public static z I(Temporal temporal) {
        z zVar = (z) temporal.D(j$.time.temporal.k.j());
        if (zVar != null) {
            return zVar;
        }
        throw new RuntimeException("Unable to obtain ZoneId from TemporalAccessor: " + String.valueOf(temporal) + " of type " + temporal.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static z K(String str) {
        int i;
        Objects.a(str, "zoneId");
        if (str.length() > 1 && !str.startsWith("+") && !str.startsWith("-")) {
            if (!str.startsWith("UTC") && !str.startsWith("GMT")) {
                if (str.startsWith("UT")) {
                    i = 2;
                } else {
                    return B.O(str);
                }
            } else {
                i = 3;
            }
            return M(str, i);
        }
        return A.R(str);
    }

    public static z L(String str, A a) {
        Objects.a(str, "prefix");
        Objects.a(a, "offset");
        if (str.isEmpty()) {
            return a;
        }
        if (!str.equals("GMT") && !str.equals("UTC") && !str.equals("UT")) {
            throw new IllegalArgumentException("prefix should be GMT, UTC or UT, is: ".concat(str));
        }
        if (a.Q() != 0) {
            str = str.concat(a.m());
        }
        return new B(str, j$.time.zone.e.h(a));
    }

    private static z M(String str, int i) {
        String substring = str.substring(0, i);
        if (str.length() == i) {
            return L(substring, A.e);
        }
        if (str.charAt(i) != '+' && str.charAt(i) != '-') {
            return B.O(str);
        }
        try {
            A R = A.R(str.substring(i));
            if (R == A.e) {
                return L(substring, R);
            }
            return L(substring, R);
        } catch (C0010c e) {
            throw new RuntimeException("Invalid ID for offset-based ZoneId: ".concat(str), e);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new t((byte) 7, this);
    }

    public abstract j$.time.zone.e J();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void N(ObjectOutput objectOutput);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z) {
            return m().equals(((z) obj).m());
        }
        return false;
    }

    public int hashCode() {
        return m().hashCode();
    }

    public abstract String m();

    public String toString() {
        return m();
    }
}
