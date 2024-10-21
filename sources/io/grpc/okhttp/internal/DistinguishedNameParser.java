package io.grpc.okhttp.internal;

import javax.security.auth.x500.X500Principal;

/* compiled from: PG */
/* loaded from: classes.dex */
final class DistinguishedNameParser {
    public int beg;
    public char[] chars;
    public int cur;
    public final String dn;
    public int end;
    public final int length;
    public int pos;

    public DistinguishedNameParser(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.dn = name;
        this.length = name.length();
    }

    public final int getByte(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.length) {
            char[] cArr = this.chars;
            char c = cArr[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else if (c >= 'A' && c <= 'F') {
                i2 = c - '7';
            } else {
                throw new IllegalStateException("Malformed DN: ".concat(String.valueOf(this.dn)));
            }
            char c2 = cArr[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 >= 'A' && c2 <= 'F') {
                i3 = c2 - '7';
            } else {
                throw new IllegalStateException("Malformed DN: ".concat(String.valueOf(this.dn)));
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: ".concat(String.valueOf(this.dn)));
    }

    public final char getEscaped() {
        int i;
        int i2;
        int i3 = this.pos + 1;
        this.pos = i3;
        if (i3 != this.length) {
            char c = this.chars[i3];
            if (c != ' ' && c != '%' && c != '\\' && c != '_' && c != '\"' && c != '#') {
                switch (c) {
                    default:
                        switch (c) {
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                                break;
                            default:
                                int i4 = getByte(i3);
                                this.pos++;
                                if (i4 >= 128) {
                                    if (i4 >= 192 && i4 <= 247) {
                                        if (i4 <= 223) {
                                            i = i4 & 31;
                                            i2 = 1;
                                        } else if (i4 <= 239) {
                                            i = i4 & 15;
                                            i2 = 2;
                                        } else {
                                            i = i4 & 7;
                                            i2 = 3;
                                        }
                                        for (int i5 = 0; i5 < i2; i5++) {
                                            int i6 = this.pos;
                                            int i7 = i6 + 1;
                                            this.pos = i7;
                                            if (i7 != this.length && this.chars[i7] == '\\') {
                                                int i8 = i6 + 2;
                                                this.pos = i8;
                                                int i9 = getByte(i8);
                                                this.pos++;
                                                if ((i9 & 192) == 128) {
                                                    i = (i << 6) + (i9 & 63);
                                                }
                                            }
                                        }
                                        i4 = (char) i;
                                    }
                                    i4 = 63;
                                }
                                return (char) i4;
                        }
                    case '*':
                    case '+':
                    case ',':
                        return c;
                }
            }
            return c;
        }
        throw new IllegalStateException("Unexpected end of DN: ".concat(String.valueOf(this.dn)));
    }

    public final String nextAT() {
        int i;
        int i2;
        int i3;
        int i4;
        char c;
        char c2;
        char c3;
        int i5;
        char c4;
        char c5;
        while (true) {
            i = this.pos;
            i2 = this.length;
            if (i >= i2 || this.chars[i] != ' ') {
                break;
            }
            this.pos = i + 1;
        }
        if (i == i2) {
            return null;
        }
        this.beg = i;
        this.pos = i + 1;
        while (true) {
            i3 = this.pos;
            i4 = this.length;
            if (i3 >= i4 || (c5 = this.chars[i3]) == '=' || c5 == ' ') {
                break;
            }
            this.pos = i3 + 1;
        }
        if (i3 < i4) {
            this.end = i3;
            if (this.chars[i3] == ' ') {
                while (true) {
                    i3 = this.pos;
                    i5 = this.length;
                    if (i3 >= i5 || (c4 = this.chars[i3]) == '=' || c4 != ' ') {
                        break;
                    }
                    this.pos = i3 + 1;
                }
                if (this.chars[i3] != '=' || i3 == i5) {
                    throw new IllegalStateException("Unexpected end of DN: ".concat(String.valueOf(this.dn)));
                }
            }
            this.pos = i3 + 1;
            while (true) {
                int i6 = this.pos;
                if (i6 >= this.length || this.chars[i6] != ' ') {
                    break;
                }
                this.pos = i6 + 1;
            }
            int i7 = this.end;
            int i8 = this.beg;
            if (i7 - i8 > 4) {
                char[] cArr = this.chars;
                if (cArr[i8 + 3] == '.' && (((c = cArr[i8]) == 'O' || c == 'o') && (((c2 = cArr[i8 + 1]) == 'I' || c2 == 'i') && ((c3 = cArr[i8 + 2]) == 'D' || c3 == 'd')))) {
                    i8 += 4;
                    this.beg = i8;
                }
            }
            return new String(this.chars, i8, i7 - i8);
        }
        throw new IllegalStateException("Unexpected end of DN: ".concat(String.valueOf(this.dn)));
    }
}
