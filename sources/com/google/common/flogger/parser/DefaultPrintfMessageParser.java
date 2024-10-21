package com.google.common.flogger.parser;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.flogger.backend.FormatChar;
import com.google.common.flogger.backend.FormatOptions;
import com.google.common.flogger.parameter.DateTimeFormat;
import com.google.common.flogger.parameter.DateTimeParameter;
import com.google.common.flogger.parameter.Parameter;
import com.google.common.flogger.parameter.SimpleParameter;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultPrintfMessageParser extends PrintfMessageParser {
    public static final PrintfMessageParser INSTANCE = new DefaultPrintfMessageParser();

    private DefaultPrintfMessageParser() {
    }

    @Override // com.google.common.flogger.parser.PrintfMessageParser
    public final int parsePrintfTerm(MessageBuilder messageBuilder, int i, String str, int i2, int i3, int i4) {
        boolean z;
        int i5;
        FormatOptions formatOptions;
        int i6;
        char charAt;
        Parameter dateTimeParameter;
        Parameter parameter;
        char charAt2 = str.charAt(i4);
        int i7 = charAt2 & ' ';
        int i8 = 1;
        if (i7 == 0) {
            z = true;
        } else {
            z = false;
        }
        FormatOptions formatOptions2 = FormatOptions.DEFAULT;
        char c = ' ';
        int i9 = i3;
        if (i9 == i4 && !z) {
            formatOptions = FormatOptions.DEFAULT;
        } else {
            if (true != z) {
                i5 = 0;
            } else {
                i5 = BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
            }
            while (true) {
                if (i9 == i4) {
                    formatOptions = new FormatOptions(i5, -1, -1);
                    break;
                }
                i6 = i9 + 1;
                charAt = str.charAt(i9);
                if (charAt < c || charAt > '0') {
                    break;
                }
                int indexOfFlagCharacter = FormatOptions.indexOfFlagCharacter(charAt);
                if (indexOfFlagCharacter < 0) {
                    if (charAt == '.') {
                        formatOptions = new FormatOptions(i5, -1, FormatOptions.parsePrecision(str, i6, i4));
                    } else {
                        throw ParseException.atPosition("invalid flag", str, i9);
                    }
                } else {
                    int i10 = 1 << indexOfFlagCharacter;
                    if ((i5 & i10) == 0) {
                        i5 |= i10;
                        i9 = i6;
                        c = ' ';
                    } else {
                        throw ParseException.atPosition("repeated flag", str, i9);
                    }
                }
            }
            if (charAt <= '9') {
                int i11 = charAt - '0';
                while (true) {
                    if (i6 == i4) {
                        formatOptions = new FormatOptions(i5, i11, -1);
                        break;
                    }
                    int i12 = i6 + 1;
                    char charAt3 = str.charAt(i6);
                    if (charAt3 == '.') {
                        formatOptions = new FormatOptions(i5, i11, FormatOptions.parsePrecision(str, i12, i4));
                        break;
                    }
                    char c2 = (char) (charAt3 - '0');
                    if (c2 < '\n') {
                        i11 = (i11 * 10) + c2;
                        if (i11 <= 999999) {
                            i6 = i12;
                            i8 = 1;
                        } else {
                            throw ParseException.withBounds("width too large", str, i9, i4);
                        }
                    } else {
                        throw ParseException.atPosition("invalid width character", str, i6);
                    }
                }
            } else {
                throw ParseException.atPosition("invalid flag", str, i9);
            }
        }
        FormatChar formatChar = FormatChar.MAP[FormatChar.indexOf(charAt2)];
        if (i7 == 0 && (formatChar == null || (formatChar.allowedFlags & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) == 0)) {
            formatChar = null;
        }
        int i13 = i4 + 1;
        if (formatChar != null) {
            if (formatOptions.validate(formatChar.allowedFlags, formatChar.type.supportsPrecision)) {
                Map map = SimpleParameter.DEFAULT_PARAMETERS;
                if (i < 10 && formatOptions.isDefault()) {
                    dateTimeParameter = ((SimpleParameter[]) SimpleParameter.DEFAULT_PARAMETERS.get(formatChar))[i];
                } else {
                    parameter = new SimpleParameter(i, formatChar, formatOptions);
                    dateTimeParameter = parameter;
                }
            } else {
                throw ParseException.withBounds("invalid format specifier", str, i2, i13);
            }
        } else if (charAt2 != 't' && charAt2 != 'T') {
            if (charAt2 != 'h' && charAt2 != 'H') {
                throw ParseException.withBounds("invalid format specification", str, i2, i13);
            }
            if (formatOptions.validate(160, false)) {
                parameter = new Parameter(formatOptions, i) { // from class: com.google.common.flogger.parser.DefaultPrintfMessageParser.1
                    @Override // com.google.common.flogger.parameter.Parameter
                    public final void accept$ar$class_merging$ar$class_merging(MessageBuilder messageBuilder2, Object obj) {
                        messageBuilder2.visit(Integer.valueOf(obj.hashCode()), FormatChar.HEX, this.options);
                    }
                };
                dateTimeParameter = parameter;
            } else {
                throw ParseException.withBounds("invalid format specification", str, i2, i13);
            }
        } else if (formatOptions.validate(160, false)) {
            int i14 = i4 + 2;
            if (i14 <= str.length()) {
                DateTimeFormat dateTimeFormat = (DateTimeFormat) DateTimeFormat.MAP.get(Character.valueOf(str.charAt(i13)));
                if (dateTimeFormat != null) {
                    dateTimeParameter = new DateTimeParameter(formatOptions, i, dateTimeFormat);
                    i13 = i14;
                } else {
                    throw ParseException.atPosition("illegal date/time conversion", str, i13);
                }
            } else {
                throw ParseException.atPosition("truncated format specifier", str, i2);
            }
        } else {
            throw ParseException.withBounds("invalid format specification", str, i2, i13);
        }
        int i15 = dateTimeParameter.index;
        if (i15 < 32) {
            messageBuilder.pmask |= i8 << i15;
        }
        messageBuilder.maxIndex = Math.max(messageBuilder.maxIndex, i15);
        messageBuilder.addParameterImpl(i2, i13, dateTimeParameter);
        return i13;
    }
}
