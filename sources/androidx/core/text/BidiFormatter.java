package androidx.core.text;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BidiFormatter {
    public static final BidiFormatter DEFAULT_LTR_INSTANCE;
    public static final BidiFormatter DEFAULT_RTL_INSTANCE;
    public static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    public static final String LRM_STRING;
    public static final String RLM_STRING;
    public final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    public final int mFlags;
    public final boolean mIsRtlContext;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DirectionalityEstimator {
        private static final byte[] DIR_TYPE_CACHE = new byte[1792];
        public int charIndex;
        public char lastChar;
        public final int length;
        public final CharSequence text;

        static {
            for (int i = 0; i < 1792; i++) {
                DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
            }
        }

        public DirectionalityEstimator(CharSequence charSequence) {
            this.text = charSequence;
            this.length = charSequence.length();
        }

        public static byte getCachedDirectionality(char c) {
            if (c < 1792) {
                return DIR_TYPE_CACHE[c];
            }
            return Character.getDirectionality(c);
        }

        final byte dirTypeBackward() {
            char charAt = this.text.charAt(this.charIndex - 1);
            this.lastChar = charAt;
            if (Character.isLowSurrogate(charAt)) {
                int codePointBefore = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.charIndex--;
            return getCachedDirectionality(this.lastChar);
        }
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        DEFAULT_TEXT_DIRECTION_HEURISTIC = textDirectionHeuristicCompat;
        LRM_STRING = Character.toString((char) 8206);
        RLM_STRING = Character.toString((char) 8207);
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, textDirectionHeuristicCompat);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, textDirectionHeuristicCompat);
    }

    public BidiFormatter(boolean z, int i, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.mIsRtlContext = z;
        this.mFlags = i;
        this.mDefaultTextDirectionHeuristicCompat = textDirectionHeuristicCompat;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0067, code lost:
    
        if (r1 != 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006a, code lost:
    
        if (r2 != 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x006e, code lost:
    
        if (r0.charIndex <= 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0074, code lost:
    
        switch(r0.dirTypeBackward()) {
            case 14: goto L62;
            case 15: goto L62;
            case 16: goto L61;
            case 17: goto L61;
            case 18: goto L60;
            default: goto L67;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0078, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x007b, code lost:
    
        if (r1 != r3) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0083, code lost:
    
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x007f, code lost:
    
        if (r1 != r3) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:?, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0087, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getEntryDir(java.lang.CharSequence r9) {
        /*
            androidx.core.text.BidiFormatter$DirectionalityEstimator r0 = new androidx.core.text.BidiFormatter$DirectionalityEstimator
            r0.<init>(r9)
            r9 = 0
            r0.charIndex = r9
            r1 = r9
            r2 = r1
            r3 = r2
        Lb:
            int r4 = r0.charIndex
            int r5 = r0.length
            r6 = -1
            r7 = 1
            if (r4 >= r5) goto L67
            if (r1 != 0) goto L67
            java.lang.CharSequence r5 = r0.text
            char r4 = r5.charAt(r4)
            r0.lastChar = r4
            boolean r4 = java.lang.Character.isHighSurrogate(r4)
            if (r4 == 0) goto L39
            java.lang.CharSequence r4 = r0.text
            int r5 = r0.charIndex
            int r4 = java.lang.Character.codePointAt(r4, r5)
            int r5 = r0.charIndex
            int r8 = java.lang.Character.charCount(r4)
            int r5 = r5 + r8
            r0.charIndex = r5
            byte r4 = java.lang.Character.getDirectionality(r4)
            goto L44
        L39:
            int r4 = r0.charIndex
            int r4 = r4 + r7
            r0.charIndex = r4
            char r4 = r0.lastChar
            byte r4 = androidx.core.text.BidiFormatter.DirectionalityEstimator.getCachedDirectionality(r4)
        L44:
            if (r4 == 0) goto L62
            if (r4 == r7) goto L5f
            r5 = 2
            if (r4 == r5) goto L5f
            r5 = 9
            if (r4 == r5) goto Lb
            switch(r4) {
                case 14: goto L5b;
                case 15: goto L5b;
                case 16: goto L57;
                case 17: goto L57;
                case 18: goto L53;
                default: goto L52;
            }
        L52:
            goto L65
        L53:
            int r3 = r3 + (-1)
            r2 = r9
            goto Lb
        L57:
            int r3 = r3 + 1
            r2 = r7
            goto Lb
        L5b:
            int r3 = r3 + 1
            r2 = r6
            goto Lb
        L5f:
            if (r3 != 0) goto L65
            goto L7d
        L62:
            if (r3 != 0) goto L65
            goto L81
        L65:
            r1 = r3
            goto Lb
        L67:
            if (r1 != 0) goto L6a
            goto L87
        L6a:
            if (r2 != 0) goto L86
        L6c:
            int r2 = r0.charIndex
            if (r2 <= 0) goto L87
            byte r2 = r0.dirTypeBackward()
            switch(r2) {
                case 14: goto L7f;
                case 15: goto L7f;
                case 16: goto L7b;
                case 17: goto L7b;
                case 18: goto L78;
                default: goto L77;
            }
        L77:
            goto L6c
        L78:
            int r3 = r3 + 1
            goto L6c
        L7b:
            if (r1 != r3) goto L83
        L7d:
            r9 = r7
            goto L87
        L7f:
            if (r1 != r3) goto L83
        L81:
            r9 = r6
            goto L87
        L83:
            int r3 = r3 + (-1)
            goto L6c
        L86:
            r9 = r2
        L87:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.BidiFormatter.getEntryDir(java.lang.CharSequence):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0041, code lost:
    
        return 1;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:35:0x0021. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getExitDir(java.lang.CharSequence r7) {
        /*
            androidx.core.text.BidiFormatter$DirectionalityEstimator r0 = new androidx.core.text.BidiFormatter$DirectionalityEstimator
            r0.<init>(r7)
            int r7 = r0.length
            r0.charIndex = r7
            r7 = 0
            r1 = r7
        Lb:
            r2 = r1
        Lc:
            int r3 = r0.charIndex
            if (r3 <= 0) goto L41
            byte r3 = r0.dirTypeBackward()
            r4 = -1
            if (r3 == 0) goto L3a
            r5 = 1
            if (r3 == r5) goto L33
            r6 = 2
            if (r3 == r6) goto L33
            r6 = 9
            if (r3 == r6) goto Lc
            switch(r3) {
                case 14: goto L2d;
                case 15: goto L2d;
                case 16: goto L2a;
                case 17: goto L2a;
                case 18: goto L27;
                default: goto L24;
            }
        L24:
            if (r2 != 0) goto Lc
            goto L40
        L27:
            int r1 = r1 + 1
            goto Lc
        L2a:
            if (r2 != r1) goto L30
            goto L35
        L2d:
            if (r2 != r1) goto L30
            goto L3c
        L30:
            int r1 = r1 + (-1)
            goto Lc
        L33:
            if (r1 != 0) goto L37
        L35:
            r7 = r5
            goto L41
        L37:
            if (r2 != 0) goto Lc
            goto L40
        L3a:
            if (r1 != 0) goto L3e
        L3c:
            r7 = r4
            goto L41
        L3e:
            if (r2 != 0) goto Lc
        L40:
            goto Lb
        L41:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.BidiFormatter.getExitDir(java.lang.CharSequence):int");
    }
}
