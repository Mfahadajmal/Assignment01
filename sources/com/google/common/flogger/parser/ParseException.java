package com.google.common.flogger.parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ParseException extends RuntimeException {
    public ParseException(String str) {
        super(str);
    }

    public static ParseException atPosition(String str, String str2, int i) {
        return new ParseException(msg(str, str2, i, i + 1));
    }

    private static String msg(String str, String str2, int i, int i2) {
        if (i2 < 0) {
            i2 = str2.length();
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(": ");
        if (i > 8) {
            sb.append("...");
            sb.append((CharSequence) str2, i - 5, i);
        } else {
            sb.append((CharSequence) str2, 0, i);
        }
        sb.append('[');
        sb.append(str2.substring(i, i2));
        sb.append(']');
        if (str2.length() - i2 > 8) {
            sb.append((CharSequence) str2, i2, i2 + 5);
            sb.append("...");
        } else {
            sb.append((CharSequence) str2, i2, str2.length());
        }
        return sb.toString();
    }

    public static ParseException withBounds(String str, String str2, int i, int i2) {
        return new ParseException(msg(str, str2, i, i2));
    }

    public static ParseException withStartPosition(String str, String str2, int i) {
        return new ParseException(msg(str, str2, i, -1));
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return this;
    }
}
