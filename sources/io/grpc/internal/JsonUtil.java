package io.grpc.internal;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonUtil {
    private static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);

    public static void checkObjectList$ar$ds(List list) {
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i) instanceof Map)) {
                throw new ClassCastException(String.format(Locale.US, "value %s for idx %d in %s is not object", list.get(i), Integer.valueOf(i), list));
            }
        }
    }

    public static Boolean getBoolean(Map map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Boolean", obj, str, map));
    }

    public static List getList(Map map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof List) {
            return (List) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not List", obj, str, map));
    }

    public static List getListOfObjects(Map map, String str) {
        List list = getList(map, str);
        if (list == null) {
            return null;
        }
        checkObjectList$ar$ds(list);
        return list;
    }

    public static List getListOfStrings(Map map, String str) {
        List list = getList(map, str);
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i) instanceof String)) {
                throw new ClassCastException(String.format(Locale.US, "value '%s' for idx %d in '%s' is not string", list.get(i), Integer.valueOf(i), list));
            }
        }
        return list;
    }

    public static Double getNumberAsDouble(Map map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not a double", obj, str));
            }
        }
        throw new IllegalArgumentException(String.format("value '%s' for key '%s' in '%s' is not a number", obj, str, map));
    }

    public static Integer getNumberAsInteger(Map map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Double) {
            Double d = (Double) obj;
            int intValue = d.intValue();
            if (intValue == d.doubleValue()) {
                return Integer.valueOf(intValue);
            }
            Objects.toString(d);
            throw new ClassCastException("Number expected to be integer: ".concat(String.valueOf(d)));
        }
        if (obj instanceof String) {
            try {
                return Integer.valueOf(Integer.parseInt((String) obj));
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not an integer", obj, str));
            }
        }
        throw new IllegalArgumentException(String.format("value '%s' for key '%s' is not an integer", obj, str));
    }

    public static Map getObject(Map map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof Map) {
            return (Map) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not object", obj, str, map));
    }

    public static String getString(Map map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not String", obj, str, map));
    }

    public static Long getStringAsDuration(Map map, String str) {
        boolean z;
        int i;
        boolean z2;
        boolean z3;
        boolean z4;
        String string = getString(map, str);
        if (string == null) {
            return null;
        }
        try {
            boolean z5 = false;
            if (!string.isEmpty() && string.charAt(string.length() - 1) == 's') {
                if (string.charAt(0) == '-') {
                    string = string.substring(1);
                    z = true;
                } else {
                    z = false;
                }
                String substring = string.substring(0, string.length() - 1);
                String str2 = "";
                int indexOf = substring.indexOf(46);
                if (indexOf != -1) {
                    str2 = substring.substring(indexOf + 1);
                    substring = substring.substring(0, indexOf);
                }
                long parseLong = Long.parseLong(substring);
                if (str2.isEmpty()) {
                    i = 0;
                } else {
                    i = 0;
                    for (int i2 = 0; i2 < 9; i2++) {
                        i *= 10;
                        if (i2 < str2.length()) {
                            if (str2.charAt(i2) >= '0' && str2.charAt(i2) <= '9') {
                                i += str2.charAt(i2) - '0';
                            } else {
                                throw new ParseException("Invalid nanoseconds.", 0);
                            }
                        }
                    }
                }
                if (parseLong >= 0) {
                    if (z) {
                        parseLong = -parseLong;
                        i = -i;
                    }
                    long j = i;
                    try {
                        long j2 = NANOS_PER_SECOND;
                        if (j <= (-j2) || j >= j2) {
                            long j3 = j / j2;
                            long j4 = parseLong + j3;
                            long j5 = parseLong ^ j3;
                            if ((parseLong ^ j4) >= 0) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (j5 < 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z3 | z2) {
                                i = (int) (j % j2);
                                parseLong = j4;
                            } else {
                                throw new ArithmeticException("overflow: checkedAdd(" + parseLong + ", " + j3 + ")");
                            }
                        }
                        if (parseLong > 0 && i < 0) {
                            parseLong--;
                            i = (int) (i + j2);
                        }
                        if (parseLong < 0 && i > 0) {
                            parseLong++;
                            i = (int) (i - j2);
                        }
                        if (parseLong >= -315576000000L && parseLong <= 315576000000L) {
                            long j6 = i;
                            if (j6 >= -999999999 && j6 < j2 && ((parseLong >= 0 && i >= 0) || (parseLong <= 0 && i <= 0))) {
                                long nanos = TimeUnit.SECONDS.toNanos(parseLong);
                                long j7 = nanos + j6;
                                long j8 = nanos ^ j6;
                                if ((nanos ^ j7) >= 0) {
                                    z4 = true;
                                } else {
                                    z4 = false;
                                }
                                if (j8 < 0) {
                                    z5 = true;
                                }
                                if (!(z4 | z5)) {
                                    j7 = ((j7 >>> 63) ^ 1) + Long.MAX_VALUE;
                                }
                                return Long.valueOf(j7);
                            }
                        }
                        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(parseLong), Integer.valueOf(i)));
                    } catch (IllegalArgumentException unused) {
                        throw new ParseException("Duration value is out of range.", 0);
                    }
                }
                throw new ParseException("Invalid duration string: ".concat(String.valueOf(string)), 0);
            }
            throw new ParseException("Invalid duration string: ".concat(string), 0);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
