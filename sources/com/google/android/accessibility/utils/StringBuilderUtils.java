package com.google.android.accessibility.utils;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StringBuilderUtils {
    private static final char[] HEX_ALPHABET = "0123456789abcdef".toCharArray();

    public static void appendWithSeparator$ar$ds(SpannableStringBuilder spannableStringBuilder, boolean z, CharSequence... charSequenceArr) {
        for (CharSequence charSequence : charSequenceArr) {
            if (charSequence != null && charSequence.length() != 0) {
                if (spannableStringBuilder.length() > 0) {
                    if (!TextUtils.isEmpty(spannableStringBuilder) && Character.isLetterOrDigit(spannableStringBuilder.charAt(spannableStringBuilder.length() - 1))) {
                        if (z) {
                            spannableStringBuilder.append(SpannableUtils$IdentifierSpan.wrapWithIdentifierSpan(", "));
                        } else {
                            spannableStringBuilder.append(", ");
                        }
                    } else if (z) {
                        spannableStringBuilder.append(SpannableUtils$IdentifierSpan.wrapWithIdentifierSpan(" "));
                    } else {
                        spannableStringBuilder.append(" ");
                    }
                }
                spannableStringBuilder.append(charSequence);
            }
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (byte b : bArr) {
            char[] cArr = HEX_ALPHABET;
            sb.append(cArr[(b >>> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public static CharSequence getAggregateText(List list) {
        if (list.isEmpty()) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            appendWithSeparator$ar$ds(spannableStringBuilder, false, (CharSequence) it.next());
        }
        return spannableStringBuilder;
    }

    public static String joinFields(String... strArr) {
        return joinStrings("  ", strArr);
    }

    public static String joinStrings(String str, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            if (!TextUtils.isEmpty(str2)) {
                sb.append(str2);
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String optionalDouble(String str, double d, double d2) {
        if (d == d2) {
            return "";
        }
        return String.format("%s=%s", str, Double.valueOf(d));
    }

    public static String optionalField(String str, Object obj) {
        if (obj == null) {
            return "";
        }
        return String.format("%s=%s", str, obj.toString());
    }

    public static String optionalInt(String str, int i, int i2) {
        if (i == i2) {
            return "";
        }
        return String.format("%s=%s", str, Integer.valueOf(i));
    }

    public static String optionalInt$ar$ds(String str, long j) {
        if (j == 0) {
            return "";
        }
        return String.format("%s=%s", str, Long.valueOf(j));
    }

    public static String optionalSubObj(String str, Object obj) {
        if (obj == null) {
            return "";
        }
        return String.format("%s= %s", str, obj.toString());
    }

    public static String optionalTag(String str, boolean z) {
        if (z) {
            return str;
        }
        return "";
    }

    public static String optionalText(String str, CharSequence charSequence) {
        if (charSequence == null) {
            return "";
        }
        return String.format("%s=\"%s\"", str, charSequence);
    }
}
