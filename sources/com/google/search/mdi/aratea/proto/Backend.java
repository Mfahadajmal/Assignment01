package com.google.search.mdi.aratea.proto;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Backend {
    public static final int BACKEND_UNSPECIFIED$ar$edu = 1;
    public static final int SUP$ar$edu = 2;
    public static final int ALS$ar$edu = 3;
    public static final int VERTEX_AI$ar$edu = 4;
    private static final /* synthetic */ int[] $VALUES$ar$edu$5863fdd8_0 = {BACKEND_UNSPECIFIED$ar$edu, SUP$ar$edu, ALS$ar$edu, VERTEX_AI$ar$edu};

    public static int forNumber$ar$edu$9a3b3366_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return 0;
                    }
                    return VERTEX_AI$ar$edu;
                }
                return ALS$ar$edu;
            }
            return SUP$ar$edu;
        }
        return BACKEND_UNSPECIFIED$ar$edu;
    }

    public static int[] values$ar$edu$2384af6d_0() {
        return new int[]{BACKEND_UNSPECIFIED$ar$edu, SUP$ar$edu, ALS$ar$edu, VERTEX_AI$ar$edu};
    }
}
