package com.google.common.io;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.math.IntMath;
import j$.util.Objects;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseEncoding {
    public static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    public static final BaseEncoding BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Base16Encoding extends StandardBaseEncoding {
        final char[] encoding;

        public Base16Encoding(Alphabet alphabet) {
            super(alphabet, null);
            boolean z;
            this.encoding = new char[512];
            if (alphabet.chars.length == 16) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z);
            for (int i = 0; i < 256; i++) {
                this.encoding[i] = alphabet.encode(i >>> 4);
                this.encoding[i | 256] = alphabet.encode(i & 15);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public final int decodeTo(byte[] bArr, CharSequence charSequence) {
            if (charSequence.length() % 2 != 1) {
                int i = 0;
                int i2 = 0;
                while (i < charSequence.length()) {
                    bArr[i2] = (byte) ((this.alphabet.decode(charSequence.charAt(i)) << 4) | this.alphabet.decode(charSequence.charAt(i + 1)));
                    i += 2;
                    i2++;
                }
                return i2;
            }
            throw new DecodingException("Invalid input length " + charSequence.length());
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public final void encodeTo$ar$ds(Appendable appendable, byte[] bArr, int i) {
            ContextDataProvider.checkPositionIndexes(0, i, bArr.length);
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = bArr[i2] & 255;
                appendable.append(this.encoding[i3]);
                appendable.append(this.encoding[i3 | 256]);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DecodingException extends IOException {
        public DecodingException(String str) {
            super(str);
        }
    }

    static {
        new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
        new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
        new Base16Encoding(new Alphabet("base16()", "0123456789ABCDEF".toCharArray()));
    }

    public final byte[] decode(CharSequence charSequence) {
        try {
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            int maxDecodedSize = maxDecodedSize(trimTrailingPadding.length());
            byte[] bArr = new byte[maxDecodedSize];
            int decodeTo = decodeTo(bArr, trimTrailingPadding);
            if (decodeTo != maxDecodedSize) {
                byte[] bArr2 = new byte[decodeTo];
                System.arraycopy(bArr, 0, bArr2, 0, decodeTo);
                return bArr2;
            }
            return bArr;
        } catch (DecodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int decodeTo(byte[] bArr, CharSequence charSequence);

    public final String encode(byte[] bArr) {
        int length = bArr.length;
        ContextDataProvider.checkPositionIndexes(0, length, length);
        StringBuilder sb = new StringBuilder(maxEncodedSize(length));
        try {
            encodeTo$ar$ds(sb, bArr, length);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public abstract void encodeTo$ar$ds(Appendable appendable, byte[] bArr, int i);

    public abstract int maxDecodedSize(int i);

    public abstract int maxEncodedSize(int i);

    public CharSequence trimTrailingPadding(CharSequence charSequence) {
        throw null;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Base64Encoding extends StandardBaseEncoding {
        private Base64Encoding(Alphabet alphabet, Character ch) {
            super(alphabet, ch);
            ContextDataProvider.checkArgument(alphabet.chars.length == 64);
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public final int decodeTo(byte[] bArr, CharSequence charSequence) {
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                int i = 0;
                int i2 = 0;
                while (i < trimTrailingPadding.length()) {
                    int i3 = i2 + 1;
                    int decode = (this.alphabet.decode(trimTrailingPadding.charAt(i)) << 18) | (this.alphabet.decode(trimTrailingPadding.charAt(i + 1)) << 12);
                    bArr[i2] = (byte) (decode >>> 16);
                    int i4 = i + 2;
                    if (i4 < trimTrailingPadding.length()) {
                        int i5 = i + 3;
                        int decode2 = decode | (this.alphabet.decode(trimTrailingPadding.charAt(i4)) << 6);
                        int i6 = i2 + 2;
                        bArr[i3] = (byte) ((decode2 >>> 8) & PrivateKeyType.INVALID);
                        if (i5 < trimTrailingPadding.length()) {
                            i += 4;
                            i2 += 3;
                            bArr[i6] = (byte) ((decode2 | this.alphabet.decode(trimTrailingPadding.charAt(i5))) & PrivateKeyType.INVALID);
                        } else {
                            i2 = i6;
                            i = i5;
                        }
                    } else {
                        i = i4;
                        i2 = i3;
                    }
                }
                return i2;
            }
            throw new DecodingException("Invalid input length " + trimTrailingPadding.length());
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        public final void encodeTo$ar$ds(Appendable appendable, byte[] bArr, int i) {
            int i2 = 0;
            ContextDataProvider.checkPositionIndexes(0, i, bArr.length);
            for (int i3 = i; i3 >= 3; i3 -= 3) {
                int i4 = bArr[i2] & 255;
                int i5 = ((bArr[i2 + 1] & 255) << 8) | (i4 << 16) | (bArr[i2 + 2] & 255);
                appendable.append(this.alphabet.encode(i5 >>> 18));
                appendable.append(this.alphabet.encode((i5 >>> 12) & 63));
                appendable.append(this.alphabet.encode((i5 >>> 6) & 63));
                appendable.append(this.alphabet.encode(i5 & 63));
                i2 += 3;
            }
            if (i2 < i) {
                encodeChunkTo(appendable, bArr, i2, i - i2);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding
        public final BaseEncoding newInstance(Alphabet alphabet, Character ch) {
            return new Base64Encoding(alphabet, null);
        }

        public Base64Encoding(String str, String str2, Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class StandardBaseEncoding extends BaseEncoding {
        public final Alphabet alphabet;
        public final Character paddingChar;

        public StandardBaseEncoding(Alphabet alphabet, Character ch) {
            this.alphabet = alphabet;
            boolean z = true;
            if (ch != null) {
                ch.charValue();
                if (alphabet.decodabet[61] != -1) {
                    z = false;
                }
            }
            ContextDataProvider.checkArgument(z, "Padding character %s was already in alphabet", ch);
            this.paddingChar = ch;
        }

        @Override // com.google.common.io.BaseEncoding
        public int decodeTo(byte[] bArr, CharSequence charSequence) {
            Alphabet alphabet;
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                int i = 0;
                int i2 = 0;
                while (i < trimTrailingPadding.length()) {
                    long j = 0;
                    int i3 = 0;
                    int i4 = 0;
                    while (true) {
                        alphabet = this.alphabet;
                        if (i3 >= alphabet.charsPerChunk) {
                            break;
                        }
                        j <<= alphabet.bitsPerChar;
                        if (i + i3 < trimTrailingPadding.length()) {
                            j |= this.alphabet.decode(trimTrailingPadding.charAt(i4 + i));
                            i4++;
                        }
                        i3++;
                    }
                    int i5 = alphabet.bytesPerChunk;
                    int i6 = i4 * alphabet.bitsPerChar;
                    int i7 = (i5 - 1) * 8;
                    while (i7 >= (i5 * 8) - i6) {
                        bArr[i2] = (byte) ((j >>> i7) & 255);
                        i7 -= 8;
                        i2++;
                    }
                    i += this.alphabet.charsPerChunk;
                }
                return i2;
            }
            throw new DecodingException("Invalid input length " + trimTrailingPadding.length());
        }

        final void encodeChunkTo(Appendable appendable, byte[] bArr, int i, int i2) {
            boolean z;
            ContextDataProvider.checkPositionIndexes(i, i + i2, bArr.length);
            int i3 = 0;
            if (i2 <= this.alphabet.bytesPerChunk) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z);
            long j = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                j = (j | (bArr[i + i4] & 255)) << 8;
            }
            int i5 = (i2 + 1) * 8;
            Alphabet alphabet = this.alphabet;
            while (i3 < i2 * 8) {
                long j2 = j >>> ((i5 - alphabet.bitsPerChar) - i3);
                Alphabet alphabet2 = this.alphabet;
                appendable.append(alphabet2.encode(((int) j2) & alphabet2.mask));
                i3 += this.alphabet.bitsPerChar;
            }
            if (this.paddingChar != null) {
                while (i3 < this.alphabet.bytesPerChunk * 8) {
                    this.paddingChar.charValue();
                    appendable.append('=');
                    i3 += this.alphabet.bitsPerChar;
                }
            }
        }

        @Override // com.google.common.io.BaseEncoding
        public void encodeTo$ar$ds(Appendable appendable, byte[] bArr, int i) {
            int i2 = 0;
            ContextDataProvider.checkPositionIndexes(0, i, bArr.length);
            while (i2 < i) {
                encodeChunkTo(appendable, bArr, i2, Math.min(this.alphabet.bytesPerChunk, i - i2));
                i2 += this.alphabet.bytesPerChunk;
            }
        }

        public final boolean equals(Object obj) {
            if (obj instanceof StandardBaseEncoding) {
                StandardBaseEncoding standardBaseEncoding = (StandardBaseEncoding) obj;
                if (this.alphabet.equals(standardBaseEncoding.alphabet) && Objects.equals(this.paddingChar, standardBaseEncoding.paddingChar)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Character ch = this.paddingChar;
            return Objects.hashCode(ch) ^ this.alphabet.hashCode();
        }

        @Override // com.google.common.io.BaseEncoding
        public final int maxDecodedSize(int i) {
            return (int) (((this.alphabet.bitsPerChar * i) + 7) / 8);
        }

        @Override // com.google.common.io.BaseEncoding
        public final int maxEncodedSize(int i) {
            return this.alphabet.charsPerChunk * IntMath.divide(i, this.alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        public BaseEncoding newInstance(Alphabet alphabet, Character ch) {
            throw null;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.alphabet);
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.paddingChar);
                    sb.append("')");
                }
            }
            return sb.toString();
        }

        @Override // com.google.common.io.BaseEncoding
        public final CharSequence trimTrailingPadding(CharSequence charSequence) {
            charSequence.getClass();
            Character ch = this.paddingChar;
            if (ch == null) {
                return charSequence;
            }
            ch.charValue();
            int length = charSequence.length();
            do {
                length--;
                if (length < 0) {
                    break;
                }
            } while (charSequence.charAt(length) == '=');
            return charSequence.subSequence(0, length + 1);
        }

        public StandardBaseEncoding(String str, String str2, Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Alphabet {
        final int bitsPerChar;
        final int bytesPerChunk;
        public final char[] chars;
        final int charsPerChunk;
        public final byte[] decodabet;
        private final boolean ignoreCase;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public Alphabet(java.lang.String r10, char[] r11) {
            /*
                r9 = this;
                r0 = 128(0x80, float:1.794E-43)
                byte[] r1 = new byte[r0]
                r2 = -1
                java.util.Arrays.fill(r1, r2)
                r3 = 0
                r4 = r3
            La:
                int r5 = r11.length
                if (r4 >= r5) goto L2b
                char r5 = r11[r4]
                r6 = 1
                if (r5 >= r0) goto L14
                r7 = r6
                goto L15
            L14:
                r7 = r3
            L15:
                java.lang.String r8 = "Non-ASCII character: %s"
                com.google.common.flogger.context.ContextDataProvider.checkArgument(r7, r8, r5)
                r7 = r1[r5]
                if (r7 != r2) goto L1f
                goto L20
            L1f:
                r6 = r3
            L20:
                java.lang.String r7 = "Duplicate character: %s"
                com.google.common.flogger.context.ContextDataProvider.checkArgument(r6, r7, r5)
                byte r6 = (byte) r4
                r1[r5] = r6
                int r4 = r4 + 1
                goto La
            L2b:
                r9.<init>(r10, r11, r1, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.Alphabet.<init>(java.lang.String, char[]):void");
        }

        final int decode(char c) {
            if (c <= 127) {
                byte b = this.decodabet[c];
                if (b == -1) {
                    if (c > ' ' && c != 127) {
                        throw new DecodingException("Unrecognized character: " + c);
                    }
                    throw new DecodingException("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(c))));
                }
                return b;
            }
            throw new DecodingException("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(c))));
        }

        final char encode(int i) {
            return this.chars[i];
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Alphabet) {
                Alphabet alphabet = (Alphabet) obj;
                boolean z = alphabet.ignoreCase;
                if (Arrays.equals(this.chars, alphabet.chars)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(this.chars) + 1237;
        }

        final boolean isValidPaddingStartPosition(int i) {
            return this.validPadding[i % this.charsPerChunk];
        }

        public final String toString() {
            return this.name;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0019. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0065 A[LOOP:0: B:14:0x0061->B:16:0x0065, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private Alphabet(java.lang.String r4, char[] r5, byte[] r6, boolean r7) {
            /*
                r3 = this;
                r3.<init>()
                r3.name = r4
                r5.getClass()
                r3.chars = r5
                int r4 = r5.length     // Catch: java.lang.ArithmeticException -> L85
                java.math.RoundingMode r7 = java.math.RoundingMode.UNNECESSARY     // Catch: java.lang.ArithmeticException -> L85
                if (r4 <= 0) goto L7d
                int[] r0 = com.google.common.math.IntMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode     // Catch: java.lang.ArithmeticException -> L85
                int r7 = r7.ordinal()     // Catch: java.lang.ArithmeticException -> L85
                r7 = r0[r7]     // Catch: java.lang.ArithmeticException -> L85
                r0 = 0
                r1 = 1
                switch(r7) {
                    case 1: goto L37;
                    case 2: goto L42;
                    case 3: goto L42;
                    case 4: goto L2e;
                    case 5: goto L2e;
                    case 6: goto L1f;
                    case 7: goto L1f;
                    case 8: goto L1f;
                    default: goto L1c;
                }     // Catch: java.lang.ArithmeticException -> L85
            L1c:
                java.lang.AssertionError r4 = new java.lang.AssertionError     // Catch: java.lang.ArithmeticException -> L85
                goto L79
            L1f:
                int r7 = java.lang.Integer.numberOfLeadingZeros(r4)     // Catch: java.lang.ArithmeticException -> L85
                r2 = -1257966797(0xffffffffb504f333, float:-4.9527733E-7)
                int r2 = r2 >>> r7
                int r7 = 31 - r7
                int r2 = r2 - r4
                int r2 = r2 >>> 31
                int r7 = r7 + r2
                goto L48
            L2e:
                int r7 = r4 + (-1)
                int r7 = java.lang.Integer.numberOfLeadingZeros(r7)     // Catch: java.lang.ArithmeticException -> L85
                int r7 = 32 - r7
                goto L48
            L37:
                int r7 = r4 + (-1)
                r7 = r7 & r4
                if (r7 != 0) goto L3e
                r7 = r1
                goto L3f
            L3e:
                r7 = r0
            L3f:
                com.google.common.flogger.context.ContextDataProvider.checkRoundingUnnecessary(r7)     // Catch: java.lang.ArithmeticException -> L85
            L42:
                int r7 = java.lang.Integer.numberOfLeadingZeros(r4)     // Catch: java.lang.ArithmeticException -> L85
                int r7 = 31 - r7
            L48:
                r3.bitsPerChar = r7     // Catch: java.lang.ArithmeticException -> L85
                int r4 = r4 + (-1)
                int r5 = java.lang.Integer.numberOfTrailingZeros(r7)
                int r2 = 3 - r5
                int r2 = r1 << r2
                r3.charsPerChunk = r2
                int r5 = r7 >> r5
                r3.bytesPerChunk = r5
                r3.mask = r4
                r3.decodabet = r6
                boolean[] r4 = new boolean[r2]
                r5 = r0
            L61:
                int r6 = r3.bytesPerChunk
                if (r5 >= r6) goto L74
                int r6 = r5 * 8
                int r7 = r3.bitsPerChar
                java.math.RoundingMode r2 = java.math.RoundingMode.CEILING
                int r6 = com.google.common.math.IntMath.divide(r6, r7, r2)
                r4[r6] = r1
                int r5 = r5 + 1
                goto L61
            L74:
                r3.validPadding = r4
                r3.ignoreCase = r0
                return
            L79:
                r4.<init>()     // Catch: java.lang.ArithmeticException -> L85
                throw r4     // Catch: java.lang.ArithmeticException -> L85
            L7d:
                java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.ArithmeticException -> L85
                java.lang.String r6 = "x (0) must be > 0"
                r4.<init>(r6)     // Catch: java.lang.ArithmeticException -> L85
                throw r4     // Catch: java.lang.ArithmeticException -> L85
            L85:
                r4 = move-exception
                int r5 = r5.length
                java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                java.lang.String r0 = "Illegal alphabet length "
                r7.<init>(r0)
                r7.append(r5)
                java.lang.String r5 = r7.toString()
                r6.<init>(r5, r4)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.Alphabet.<init>(java.lang.String, char[], byte[], boolean):void");
        }
    }
}
