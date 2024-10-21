package com.google.common.flogger.parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrintfMessageParser extends MessageParser {
    private static final String SYSTEM_NEWLINE;

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000c, code lost:
    
        if (r0.matches("\\n|\\r(?:\\n)?") == false) goto L4;
     */
    static {
        /*
            java.lang.String r0 = "line.separator"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch: java.lang.SecurityException -> Le
            java.lang.String r1 = "\\n|\\r(?:\\n)?"
            boolean r1 = r0.matches(r1)     // Catch: java.lang.SecurityException -> Le
            if (r1 != 0) goto L10
        Le:
            java.lang.String r0 = "\n"
        L10:
            com.google.common.flogger.parser.PrintfMessageParser.SYSTEM_NEWLINE = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.flogger.parser.PrintfMessageParser.<clinit>():void");
    }

    static int nextPrintfTerm(String str, int i) {
        while (i < str.length()) {
            int i2 = i + 1;
            if (str.charAt(i) != '%') {
                i = i2;
            } else if (i2 < str.length()) {
                char charAt = str.charAt(i2);
                if (charAt != '%' && charAt != 'n') {
                    return i;
                }
                i += 2;
            } else {
                throw ParseException.withStartPosition("trailing unquoted '%' character", str, i);
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a7, code lost:
    
        r4 = nextPrintfTerm(r7, parsePrintfTerm(r15, r12, r7, r4, r5, r6));
        r0 = r11;
        r1 = r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009a  */
    @Override // com.google.common.flogger.parser.MessageParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void parseImpl(com.google.common.flogger.parser.MessageBuilder r15) {
        /*
            r14 = this;
            java.lang.String r7 = r15.getMessage()
            r8 = 0
            int r0 = nextPrintfTerm(r7, r8)
            r9 = -1
            r4 = r0
            r0 = r8
            r1 = r9
        Ld:
            if (r4 < 0) goto Lc4
            int r2 = r4 + 1
            r3 = r2
            r5 = r8
        L13:
            int r6 = r7.length()
            java.lang.String r10 = "unterminated parameter"
            if (r3 >= r6) goto Lbf
            int r6 = r3 + 1
            char r11 = r7.charAt(r3)
            int r12 = r11 + (-48)
            char r12 = (char) r12
            r13 = 10
            if (r12 >= r13) goto L39
            int r5 = r5 * 10
            int r5 = r5 + r12
            r3 = 1000000(0xf4240, float:1.401298E-39)
            if (r5 >= r3) goto L32
            r3 = r6
            goto L13
        L32:
            java.lang.String r15 = "index too large"
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withBounds(r15, r7, r4, r6)
            throw r15
        L39:
            r12 = 36
            if (r11 != r12) goto L6c
            int r1 = r3 - r2
            if (r1 == 0) goto L65
            char r1 = r7.charAt(r2)
            r2 = 48
            if (r1 == r2) goto L5e
            int r5 = r5 + (-1)
            int r1 = r7.length()
            if (r6 == r1) goto L59
            int r3 = r3 + 2
            r7.charAt(r6)
            r11 = r0
            r12 = r5
            goto L7f
        L59:
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withStartPosition(r10, r7, r4)
            throw r15
        L5e:
            java.lang.String r15 = "index has leading zero"
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withBounds(r15, r7, r4, r6)
            throw r15
        L65:
            java.lang.String r15 = "missing index"
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withBounds(r15, r7, r4, r6)
            throw r15
        L6c:
            r5 = 60
            if (r11 != r5) goto L8e
            if (r1 == r9) goto L87
            int r2 = r7.length()
            if (r6 == r2) goto L82
            int r3 = r3 + 2
            r7.charAt(r6)
            r11 = r0
            r12 = r1
        L7f:
            r5 = r6
            r6 = r3
            goto L93
        L82:
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withStartPosition(r10, r7, r4)
            throw r15
        L87:
            java.lang.String r15 = "invalid relative parameter"
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withBounds(r15, r7, r4, r6)
            throw r15
        L8e:
            int r1 = r0 + 1
            r12 = r0
            r11 = r1
            r5 = r2
        L93:
            int r6 = r6 + r9
        L94:
            int r0 = r7.length()
            if (r6 >= r0) goto Lba
            char r0 = r7.charAt(r6)
            r0 = r0 & (-33)
            int r0 = r0 + (-65)
            char r0 = (char) r0
            r1 = 26
            if (r0 >= r1) goto Lb7
            r0 = r14
            r1 = r15
            r2 = r12
            r3 = r7
            int r0 = r0.parsePrintfTerm(r1, r2, r3, r4, r5, r6)
            int r4 = nextPrintfTerm(r7, r0)
            r0 = r11
            r1 = r12
            goto Ld
        Lb7:
            int r6 = r6 + 1
            goto L94
        Lba:
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withStartPosition(r10, r7, r4)
            throw r15
        Lbf:
            com.google.common.flogger.parser.ParseException r15 = com.google.common.flogger.parser.ParseException.withStartPosition(r10, r7, r4)
            throw r15
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.flogger.parser.PrintfMessageParser.parseImpl(com.google.common.flogger.parser.MessageBuilder):void");
    }

    public abstract int parsePrintfTerm(MessageBuilder messageBuilder, int i, String str, int i2, int i3, int i4);

    @Override // com.google.common.flogger.parser.MessageParser
    public final void unescape(StringBuilder sb, String str, int i, int i2) {
        int i3 = i;
        while (i < i2) {
            int i4 = i + 1;
            if (str.charAt(i) == '%') {
                if (i4 == i2) {
                    break;
                }
                char charAt = str.charAt(i4);
                if (charAt == '%') {
                    sb.append((CharSequence) str, i3, i4);
                } else if (charAt == 'n') {
                    sb.append((CharSequence) str, i3, i);
                    sb.append(SYSTEM_NEWLINE);
                }
                i3 = i + 2;
                i = i3;
            }
            i = i4;
        }
        if (i3 < i2) {
            sb.append((CharSequence) str, i3, i2);
        }
    }
}
