package com.google.common.flogger.backend;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.flogger.parser.ParseException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FormatOptions {
    public static final FormatOptions DEFAULT;
    private static final long ENCODED_FLAG_INDICES;
    public final int flags;
    public final int precision;
    public final int width;

    static {
        long j = 0;
        for (int i = 0; i < 7; i++) {
            j |= (i + 1) << ((int) ((" #(+,-0".charAt(i) - ' ') * 3));
        }
        ENCODED_FLAG_INDICES = j;
        DEFAULT = new FormatOptions(0, -1, -1);
    }

    public FormatOptions(int i, int i2, int i3) {
        this.flags = i;
        this.width = i2;
        this.precision = i3;
    }

    public static int indexOfFlagCharacter(char c) {
        return ((int) ((ENCODED_FLAG_INDICES >>> ((c - ' ') * 3)) & 7)) - 1;
    }

    public static int parsePrecision(String str, int i, int i2) {
        if (i != i2) {
            int i3 = 0;
            for (int i4 = i; i4 < i2; i4++) {
                char charAt = (char) (str.charAt(i4) - '0');
                if (charAt < '\n') {
                    i3 = (i3 * 10) + charAt;
                    if (i3 > 999999) {
                        throw ParseException.withBounds("precision too large", str, i, i2);
                    }
                } else {
                    throw ParseException.atPosition("invalid precision character", str, i4);
                }
            }
            if (i3 == 0) {
                if (i2 == i + 1) {
                    return 0;
                }
                throw ParseException.withBounds("invalid precision", str, i, i2);
            }
            return i3;
        }
        throw ParseException.atPosition("missing precision", str, i - 1);
    }

    public final void appendPrintfOptions$ar$ds(StringBuilder sb) {
        if (!isDefault()) {
            int i = this.flags;
            int i2 = 0;
            while (true) {
                int i3 = i & (-129);
                int i4 = 1 << i2;
                if (i4 > i3) {
                    break;
                }
                if ((i3 & i4) != 0) {
                    sb.append(" #(+,-0".charAt(i2));
                }
                i2++;
            }
            int i5 = this.width;
            if (i5 != -1) {
                sb.append(i5);
            }
            if (this.precision != -1) {
                sb.append('.');
                sb.append(this.precision);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FormatOptions) {
            FormatOptions formatOptions = (FormatOptions) obj;
            if (formatOptions.flags == this.flags && formatOptions.width == this.width && formatOptions.precision == this.precision) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((this.flags * 31) + this.width) * 31) + this.precision;
    }

    public final boolean isDefault() {
        if (this == DEFAULT) {
            return true;
        }
        return false;
    }

    public final boolean shouldUpperCase() {
        if ((this.flags & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0) {
            return true;
        }
        return false;
    }

    public final boolean validate(int i, boolean z) {
        int i2;
        if (isDefault()) {
            return true;
        }
        int i3 = this.flags;
        if (((~i) & i3) != 0) {
            return false;
        }
        if (!z && this.precision != -1) {
            return false;
        }
        int i4 = this.width;
        if ((i3 & 9) != 9 && (i2 = i3 & 96) != 96 && (i2 == 0 || i4 != -1)) {
            return true;
        }
        return false;
    }
}
