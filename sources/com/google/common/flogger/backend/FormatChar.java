package com.google.common.flogger.backend;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum FormatChar {
    STRING('s', FormatType.GENERAL, "-#", true),
    BOOLEAN('b', FormatType.BOOLEAN, "-", true),
    CHAR('c', FormatType.CHARACTER, "-", true),
    DECIMAL('d', FormatType.INTEGRAL, "-0+ ,(", false),
    OCTAL('o', FormatType.INTEGRAL, "-#0(", false),
    HEX('x', FormatType.INTEGRAL, "-#0(", true),
    FLOAT('f', FormatType.FLOAT, "-#0+ ,(", false),
    EXPONENT('e', FormatType.FLOAT, "-#0+ (", true),
    GENERAL('g', FormatType.FLOAT, "-0+ ,(", true),
    EXPONENT_HEX('a', FormatType.FLOAT, "-#0+ ", true);

    public static final FormatChar[] MAP = new FormatChar[26];
    public final int allowedFlags;
    public final String defaultFormatString;
    public final char formatChar;
    public final FormatType type;

    static {
        for (FormatChar formatChar : values()) {
            MAP[indexOf(formatChar.formatChar)] = formatChar;
        }
    }

    FormatChar(char c, FormatType formatType, String str, boolean z) {
        int i;
        this.formatChar = c;
        this.type = formatType;
        FormatOptions formatOptions = FormatOptions.DEFAULT;
        if (true != z) {
            i = 0;
        } else {
            i = BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            int indexOfFlagCharacter = FormatOptions.indexOfFlagCharacter(str.charAt(i2));
            if (indexOfFlagCharacter >= 0) {
                i |= 1 << indexOfFlagCharacter;
            } else {
                throw new IllegalArgumentException("invalid flags: ".concat(str));
            }
        }
        this.allowedFlags = i;
        this.defaultFormatString = "%" + c;
    }

    public static int indexOf(char c) {
        return (c | ' ') - 97;
    }
}
