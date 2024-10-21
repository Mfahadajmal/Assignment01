package com.google.gson.stream;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.gson.FormattingStyle;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private String deferredName;
    private String formattedColon;
    private String formattedComma;
    private FormattingStyle formattingStyle;
    public boolean htmlSafe;
    public final Writer out;
    public boolean serializeNulls;
    private int[] stack = new int[32];
    private int stackSize = 0;
    public int strictness$ar$edu;
    private boolean usesEmptyNewlineAndIndent;
    private static final Pattern VALID_JSON_NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    private static final String[] REPLACEMENT_CHARS = new String[BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE];

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        HTML_SAFE_REPLACEMENT_CHARS = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public JsonWriter(Writer writer) {
        push(6);
        this.strictness$ar$edu = 2;
        this.serializeNulls = true;
        this.out = writer;
        setFormattingStyle(FormattingStyle.COMPACT);
    }

    private final void closeScope$ar$ds(int i, int i2, char c) {
        int peek = peek();
        if (peek != i2 && peek != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        String str = this.deferredName;
        if (str == null) {
            this.stackSize--;
            if (peek == i2) {
                newline();
            }
            this.out.write(c);
            return;
        }
        throw new IllegalStateException("Dangling name: ".concat(String.valueOf(str)));
    }

    private final void newline() {
        if (!this.usesEmptyNewlineAndIndent) {
            this.out.write(this.formattingStyle.newline);
            int i = this.stackSize;
            for (int i2 = 1; i2 < i; i2++) {
                this.out.write(this.formattingStyle.indent);
            }
        }
    }

    private final void openScope$ar$ds(int i, char c) {
        beforeValue();
        push(i);
        this.out.write(c);
    }

    private final int peek() {
        int i = this.stackSize;
        if (i != 0) {
            return this.stack[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private final void push(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.stack;
        if (i2 == iArr.length) {
            this.stack = Arrays.copyOf(iArr, i2 + i2);
        }
        int[] iArr2 = this.stack;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr2[i3] = i;
    }

    private final void replaceTop(int i) {
        this.stack[this.stackSize - 1] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void string(java.lang.String r9) {
        /*
            r8 = this;
            boolean r0 = r8.htmlSafe
            if (r0 == 0) goto L7
            java.lang.String[] r0 = com.google.gson.stream.JsonWriter.HTML_SAFE_REPLACEMENT_CHARS
            goto L9
        L7:
            java.lang.String[] r0 = com.google.gson.stream.JsonWriter.REPLACEMENT_CHARS
        L9:
            java.io.Writer r1 = r8.out
            r2 = 34
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = r3
        L16:
            if (r3 >= r1) goto L44
            int r5 = r3 + 1
            char r6 = r9.charAt(r3)
            r7 = 128(0x80, float:1.794E-43)
            if (r6 >= r7) goto L27
            r6 = r0[r6]
            if (r6 == 0) goto L42
            goto L34
        L27:
            r7 = 8232(0x2028, float:1.1535E-41)
            if (r6 != r7) goto L2e
            java.lang.String r6 = "\\u2028"
            goto L34
        L2e:
            r7 = 8233(0x2029, float:1.1537E-41)
            if (r6 != r7) goto L42
            java.lang.String r6 = "\\u2029"
        L34:
            if (r4 >= r3) goto L3c
            java.io.Writer r7 = r8.out
            int r3 = r3 - r4
            r7.write(r9, r4, r3)
        L3c:
            java.io.Writer r3 = r8.out
            r3.write(r6)
            r4 = r5
        L42:
            r3 = r5
            goto L16
        L44:
            if (r4 >= r1) goto L4c
            java.io.Writer r0 = r8.out
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4c:
            java.io.Writer r9 = r8.out
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonWriter.string(java.lang.String):void");
    }

    public final void beforeValue() {
        int peek = peek();
        if (peek != 1) {
            if (peek != 2) {
                if (peek != 4) {
                    if (peek != 6) {
                        if (peek == 7) {
                            if (this.strictness$ar$edu != 1) {
                                throw new IllegalStateException("JSON must have only one top-level value.");
                            }
                        } else {
                            throw new IllegalStateException("Nesting problem.");
                        }
                    }
                    replaceTop(7);
                    return;
                }
                this.out.append((CharSequence) this.formattedColon);
                replaceTop(5);
                return;
            }
            this.out.append((CharSequence) this.formattedComma);
            newline();
            return;
        }
        replaceTop(2);
        newline();
    }

    public final void beginArray$ar$ds() {
        writeDeferredName();
        openScope$ar$ds(1, '[');
    }

    public final void beginObject$ar$ds() {
        writeDeferredName();
        openScope$ar$ds(3, '{');
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.out.close();
        int i = this.stackSize;
        if (i <= 1 && (i != 1 || this.stack[0] == 7)) {
            this.stackSize = 0;
            return;
        }
        throw new IOException("Incomplete document");
    }

    public final void endArray$ar$ds() {
        closeScope$ar$ds(1, 2, ']');
    }

    public final void endObject$ar$ds() {
        closeScope$ar$ds(3, 5, '}');
    }

    @Override // java.io.Flushable
    public final void flush() {
        if (this.stackSize != 0) {
            this.out.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void name$ar$ds(String str) {
        str.getClass();
        if (this.deferredName == null) {
            int peek = peek();
            if (peek != 3 && peek != 5) {
                throw new IllegalStateException("Please begin an object before writing a name.");
            }
            this.deferredName = str;
            return;
        }
        throw new IllegalStateException("Already wrote a name, expecting a value.");
    }

    public final void nullValue$ar$ds() {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return;
            }
        }
        beforeValue();
        this.out.write("null");
    }

    public final void setFormattingStyle(FormattingStyle formattingStyle) {
        formattingStyle.getClass();
        this.formattingStyle = formattingStyle;
        this.formattedComma = ",";
        if (formattingStyle.spaceAfterSeparators) {
            this.formattedColon = ": ";
            if (formattingStyle.newline.isEmpty()) {
                this.formattedComma = ", ";
            }
        } else {
            this.formattedColon = ":";
        }
        boolean z = false;
        if (this.formattingStyle.newline.isEmpty() && this.formattingStyle.indent.isEmpty()) {
            z = true;
        }
        this.usesEmptyNewlineAndIndent = z;
    }

    public final void setStrictness$ar$edu(int i) {
        if (i != 0) {
            this.strictness$ar$edu = i;
            return;
        }
        throw null;
    }

    public final void value$ar$ds(double d) {
        writeDeferredName();
        if (this.strictness$ar$edu != 1 && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        beforeValue();
        this.out.append((CharSequence) Double.toString(d));
    }

    public final void value$ar$ds$1248a0e6_0(String str) {
        if (str == null) {
            nullValue$ar$ds();
            return;
        }
        writeDeferredName();
        beforeValue();
        string(str);
    }

    public final void value$ar$ds$65d8268b_0(boolean z) {
        String str;
        writeDeferredName();
        beforeValue();
        if (true != z) {
            str = "false";
        } else {
            str = "true";
        }
        this.out.write(str);
    }

    public final void value$ar$ds$89b27ec2_0(long j) {
        writeDeferredName();
        beforeValue();
        this.out.write(Long.toString(j));
    }

    public final void value$ar$ds$8dbd521e_0(Number number) {
        if (number == null) {
            nullValue$ar$ds();
            return;
        }
        writeDeferredName();
        String obj = number.toString();
        if (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN")) {
            Class<?> cls = number.getClass();
            if (cls != Integer.class && cls != Long.class && cls != Double.class && cls != Float.class && cls != Byte.class && cls != Short.class && cls != BigDecimal.class && cls != BigInteger.class && cls != AtomicInteger.class && cls != AtomicLong.class && !VALID_JSON_NUMBER_PATTERN.matcher(obj).matches()) {
                throw new IllegalArgumentException("String created by " + String.valueOf(cls) + " is not a valid JSON number: " + obj);
            }
        } else if (this.strictness$ar$edu != 1) {
            throw new IllegalArgumentException("Numeric values must be finite, but was ".concat(String.valueOf(obj)));
        }
        beforeValue();
        this.out.append((CharSequence) obj);
    }

    public final void writeDeferredName() {
        if (this.deferredName != null) {
            int peek = peek();
            if (peek == 5) {
                this.out.write(this.formattedComma);
            } else if (peek != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            newline();
            replaceTop(4);
            string(this.deferredName);
            this.deferredName = null;
        }
    }
}
