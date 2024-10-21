package com.google.gson.stream;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import java.io.Closeable;
import java.io.EOFException;
import java.io.Reader;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonReader implements Closeable {
    private final Reader in;
    public int[] pathIndices;
    public String[] pathNames;
    public long peekedLong;
    public int peekedNumberLength;
    public String peekedString;
    private int[] stack;
    public final int strictness$ar$edu = 2;
    public final char[] buffer = new char[1024];
    public int pos = 0;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    public int peeked = 0;
    public int stackSize = 1;

    static {
        CustomRemoteModelManager.INSTANCE$ar$class_merging$bc738628_0$ar$class_merging$ar$class_merging = new CustomRemoteModelManager(null, null, null);
    }

    public JsonReader(Reader reader) {
        int[] iArr = new int[32];
        this.stack = iArr;
        iArr[0] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        this.in = reader;
    }

    private final void checkLenient() {
        throw syntaxError("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
    }

    private final boolean fillBuffer(int i) {
        int i2;
        int i3 = this.lineStart;
        int i4 = this.pos;
        this.lineStart = i3 - i4;
        char[] cArr = this.buffer;
        int i5 = this.limit;
        if (i5 != i4) {
            int i6 = i5 - i4;
            this.limit = i6;
            System.arraycopy(cArr, i4, cArr, 0, i6);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i7 = this.limit;
            int read = reader.read(cArr, i7, 1024 - i7);
            if (read == -1) {
                return false;
            }
            i2 = this.limit + read;
            this.limit = i2;
            if (this.lineNumber == 0 && this.lineStart == 0 && i2 > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    private final boolean isLiteral(char c) {
        if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
            if (c != '#') {
                if (c != ',') {
                    if (c != '/' && c != '=') {
                        if (c != '{' && c != '}' && c != ':') {
                            if (c != ';') {
                                switch (c) {
                                    case '[':
                                    case ']':
                                        return false;
                                    case '\\':
                                        break;
                                    default:
                                        return true;
                                }
                            }
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            checkLenient();
            return false;
        }
        return false;
    }

    private final int nextNonWhitespace(boolean z) {
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            if (i == i2) {
                this.pos = i;
                if (!fillBuffer(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input".concat(String.valueOf(locationString())));
                }
                i = this.pos;
                i2 = this.limit;
            }
            char[] cArr = this.buffer;
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = i3;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.pos = i3;
                    if (i3 == i2) {
                        this.pos = i;
                        boolean fillBuffer = fillBuffer(2);
                        this.pos++;
                        if (!fillBuffer) {
                            return 47;
                        }
                    }
                    checkLenient();
                    int i4 = this.pos;
                    char c2 = cArr[i4];
                    if (c2 != '*') {
                        if (c2 != '/') {
                            return 47;
                        }
                        this.pos = i4 + 1;
                        skipToEndOfLine();
                        i = this.pos;
                        i2 = this.limit;
                    } else {
                        this.pos = i4 + 1;
                        while (true) {
                            if (this.pos + 2 > this.limit && !fillBuffer(2)) {
                                throw syntaxError("Unterminated comment");
                            }
                            char[] cArr2 = this.buffer;
                            int i5 = this.pos;
                            if (cArr2[i5] == '\n') {
                                this.lineNumber++;
                                this.lineStart = i5 + 1;
                            } else {
                                for (int i6 = 0; i6 < 2; i6++) {
                                    if (this.buffer[this.pos + i6] == "*/".charAt(i6)) {
                                    }
                                }
                                i = this.pos + 2;
                                i2 = this.limit;
                                break;
                            }
                            this.pos++;
                        }
                    }
                } else if (c == '#') {
                    this.pos = i3;
                    checkLenient();
                    skipToEndOfLine();
                    i = this.pos;
                    i2 = this.limit;
                } else {
                    this.pos = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    private final void skipToEndOfLine() {
        char c;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                int i2 = i + 1;
                this.pos = i2;
                c = cArr[i];
                if (c == '\n') {
                    this.lineNumber++;
                    this.lineStart = i2;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:114:0x01ce, code lost:
    
        if (isLiteral(r1) != false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x01d2, code lost:
    
        if (r10 != 2) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x01d4, code lost:
    
        if (r16 == false) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x01da, code lost:
    
        if (r13 != Long.MIN_VALUE) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x01dc, code lost:
    
        if (r17 == 0) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x01de, code lost:
    
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01e6, code lost:
    
        if (r13 != 0) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x01e8, code lost:
    
        if (r6 != 0) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01ea, code lost:
    
        if (r6 == 0) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01ed, code lost:
    
        r13 = -r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01ee, code lost:
    
        r22.peekedLong = r13;
        r22.pos += r9;
        r22.peeked = 15;
        r6 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01e0, code lost:
    
        r6 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01fc, code lost:
    
        r10 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01fd, code lost:
    
        if (r10 == 2) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0200, code lost:
    
        if (r10 == 4) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0203, code lost:
    
        if (r10 != 7) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x014d, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0205, code lost:
    
        r22.peekedNumberLength = r9;
        r6 = 16;
        r22.peeked = 16;
     */
    /* JADX WARN: Removed duplicated region for block: B:139:0x024b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int doPeek() {
        /*
            Method dump skipped, instructions count: 732
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.doPeek():int");
    }

    public final String getPath() {
        StringBuilder sb = new StringBuilder("$");
        for (int i = 0; i < this.stackSize; i++) {
            int i2 = this.stack[i];
            switch (i2) {
                case 1:
                case 2:
                    int i3 = this.pathIndices[i];
                    sb.append('[');
                    sb.append(i3);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append('.');
                    String str = this.pathNames[i];
                    if (str != null) {
                        sb.append(str);
                        break;
                    } else {
                        break;
                    }
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new AssertionError(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i2, "Unknown scope value: "));
            }
        }
        return sb.toString();
    }

    public final boolean hasNext() {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i != 2 && i != 4 && i != 17) {
            return true;
        }
        return false;
    }

    final String locationString() {
        int i = this.lineNumber + 1;
        int i2 = this.pos - this.lineStart;
        return " at line " + i + " column " + (i2 + 1) + " path " + getPath();
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x011c, code lost:
    
        r0 = new java.lang.StringBuilder(java.lang.Math.max(r3 + r3, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0128, code lost:
    
        r0.append(r4, r2, r3);
        r10.pos = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00df, code lost:
    
        throw syntaxError("Malformed Unicode escape \\u".concat(new java.lang.String(r6, r10.pos, 4)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0118, code lost:
    
        r3 = r1 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x011a, code lost:
    
        if (r0 != null) goto L90;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String nextQuotedValue(char r11) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextQuotedValue(char):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0048, code lost:
    
        checkLenient();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:54:0x0042. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String nextUnquotedValue() {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r5.pos
            int r3 = r3 + r2
            int r4 = r5.limit
            if (r3 >= r4) goto L4c
            char[] r4 = r5.buffer
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L59
            r4 = 10
            if (r3 == r4) goto L59
            r4 = 12
            if (r3 == r4) goto L59
            r4 = 13
            if (r3 == r4) goto L59
            r4 = 32
            if (r3 == r4) goto L59
            r4 = 35
            if (r3 == r4) goto L48
            r4 = 44
            if (r3 == r4) goto L59
            r4 = 47
            if (r3 == r4) goto L48
            r4 = 61
            if (r3 == r4) goto L48
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L59
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L59
            r4 = 58
            if (r3 == r4) goto L59
            r4 = 59
            if (r3 == r4) goto L48
            switch(r3) {
                case 91: goto L59;
                case 92: goto L48;
                case 93: goto L59;
                default: goto L45;
            }
        L45:
            int r2 = r2 + 1
            goto L3
        L48:
            r5.checkLenient()
            goto L59
        L4c:
            r3 = 1024(0x400, float:1.435E-42)
            if (r2 >= r3) goto L5b
            int r3 = r2 + 1
            boolean r3 = r5.fillBuffer(r3)
            if (r3 == 0) goto L59
            goto L3
        L59:
            r0 = r2
            goto L7b
        L5b:
            if (r1 != 0) goto L68
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r1.<init>(r3)
        L68:
            char[] r3 = r5.buffer
            int r4 = r5.pos
            r1.append(r3, r4, r2)
            int r3 = r5.pos
            int r3 = r3 + r2
            r5.pos = r3
            r2 = 1
            boolean r2 = r5.fillBuffer(r2)
            if (r2 != 0) goto L2
        L7b:
            if (r1 != 0) goto L87
            char[] r1 = r5.buffer
            java.lang.String r2 = new java.lang.String
            int r3 = r5.pos
            r2.<init>(r1, r3, r0)
            goto L92
        L87:
            char[] r2 = r5.buffer
            int r3 = r5.pos
            r1.append(r2, r3, r0)
            java.lang.String r2 = r1.toString()
        L92:
            int r1 = r5.pos
            int r1 = r1 + r0
            r5.pos = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextUnquotedValue():java.lang.String");
    }

    public final int peek$ar$edu() {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        switch (i) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
            case 6:
                return 8;
            case 7:
                return 9;
            case 8:
            case 9:
            case 10:
            case 11:
                return 6;
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
                return 7;
            default:
                return 10;
        }
    }

    public final void push(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.stack;
        if (i2 == iArr.length) {
            int i3 = i2 + i2;
            this.stack = Arrays.copyOf(iArr, i3);
            this.pathIndices = Arrays.copyOf(this.pathIndices, i3);
            this.pathNames = (String[]) Arrays.copyOf(this.pathNames, i3);
        }
        int[] iArr2 = this.stack;
        int i4 = this.stackSize;
        this.stackSize = i4 + 1;
        iArr2[i4] = i;
    }

    public final MalformedJsonException syntaxError(String str) {
        throw new MalformedJsonException(str + locationString() + "\nSee " + CustomRemoteModelManager.createUrl("malformed-json"));
    }

    public final String toString() {
        return String.valueOf(getClass().getSimpleName()).concat(String.valueOf(locationString()));
    }

    public final IllegalStateException unexpectedTokenError(String str) {
        String str2;
        String str3;
        int peek$ar$edu = peek$ar$edu();
        switch (peek$ar$edu()) {
            case 1:
                str2 = "BEGIN_ARRAY";
                break;
            case 2:
                str2 = "END_ARRAY";
                break;
            case 3:
                str2 = "BEGIN_OBJECT";
                break;
            case 4:
                str2 = "END_OBJECT";
                break;
            case 5:
                str2 = "NAME";
                break;
            case 6:
                str2 = "STRING";
                break;
            case 7:
                str2 = "NUMBER";
                break;
            case 8:
                str2 = "BOOLEAN";
                break;
            case 9:
                str2 = "NULL";
                break;
            default:
                str2 = "END_DOCUMENT";
                break;
        }
        String locationString = locationString();
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(str);
        sb.append(" but was ");
        sb.append(str2);
        sb.append(locationString);
        sb.append("\nSee ");
        if (peek$ar$edu == 9) {
            str3 = "adapter-not-null-safe";
        } else {
            str3 = "unexpected-json-structure";
        }
        sb.append(CustomRemoteModelManager.createUrl(str3));
        return new IllegalStateException(sb.toString());
    }
}
