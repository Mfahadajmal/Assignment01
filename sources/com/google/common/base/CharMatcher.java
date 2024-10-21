package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CharMatcher implements Predicate {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class FastMatcher extends CharMatcher {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Is extends FastMatcher {
        private final char match;

        public Is(char c) {
            this.match = c;
        }

        @Override // com.google.common.base.CharMatcher
        public final boolean matches(char c) {
            if (c == this.match) {
                return true;
            }
            return false;
        }

        public final String toString() {
            char[] cArr = new char[6];
            cArr[0] = '\\';
            cArr[1] = 'u';
            cArr[2] = 0;
            cArr[3] = 0;
            cArr[4] = 0;
            cArr[5] = 0;
            int i = this.match;
            for (int i2 = 0; i2 < 4; i2++) {
                cArr[5 - i2] = "0123456789ABCDEF".charAt(i & 15);
                i >>= 4;
            }
            return "CharMatcher.is('" + String.copyValueOf(cArr) + "')";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class NamedFastMatcher extends FastMatcher {
        private final String description;

        public NamedFastMatcher(String str) {
            this.description = str;
        }

        public final String toString() {
            return this.description;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class None extends NamedFastMatcher {
        static final CharMatcher INSTANCE = new None();

        private None() {
            super("CharMatcher.none()");
        }

        @Override // com.google.common.base.CharMatcher
        public final boolean matches(char c) {
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Whitespace extends NamedFastMatcher {
        static final int SHIFT = Integer.numberOfLeadingZeros(31);
        public static final CharMatcher INSTANCE = new Whitespace();

        public Whitespace() {
            super("CharMatcher.whitespace()");
        }

        @Override // com.google.common.base.CharMatcher
        public final boolean matches(char c) {
            if ("\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000".charAt((48906 * c) >>> SHIFT) == c) {
                return true;
            }
            return false;
        }
    }

    protected CharMatcher() {
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public final /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return matches(((Character) obj).charValue());
    }

    public abstract boolean matches(char c);
}
