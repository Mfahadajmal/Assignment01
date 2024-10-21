package com.google.android.accessibility.brailleime.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeLogProto$BrailleType {
    public static final int BRAILLE_TYPE_INVALID$ar$edu = 1;
    public static final int BRAILLE_TYPE_SIX_DOT$ar$edu = 2;
    public static final int BRAILLE_TYPE_EIGHT_DOT$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$46b36a73_0 = {BRAILLE_TYPE_INVALID$ar$edu, BRAILLE_TYPE_SIX_DOT$ar$edu, BRAILLE_TYPE_EIGHT_DOT$ar$edu};

    public static int forNumber$ar$edu$a7370849_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return BRAILLE_TYPE_EIGHT_DOT$ar$edu;
            }
            return BRAILLE_TYPE_SIX_DOT$ar$edu;
        }
        return BRAILLE_TYPE_INVALID$ar$edu;
    }

    public static int[] values$ar$edu$dac0a754_0() {
        return new int[]{BRAILLE_TYPE_INVALID$ar$edu, BRAILLE_TYPE_SIX_DOT$ar$edu, BRAILLE_TYPE_EIGHT_DOT$ar$edu};
    }
}
