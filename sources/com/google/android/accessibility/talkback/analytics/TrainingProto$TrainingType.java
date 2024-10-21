package com.google.android.accessibility.talkback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingProto$TrainingType {
    public static final int TYPE_UNKNOWN$ar$edu = 1;
    public static final int TYPE_TUTORIAL$ar$edu = 2;
    public static final int TYPE_ONBOARDING$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$f8c03f14_0 = {TYPE_UNKNOWN$ar$edu, TYPE_TUTORIAL$ar$edu, TYPE_ONBOARDING$ar$edu};

    public static int forNumber$ar$edu$145d02fd_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return TYPE_ONBOARDING$ar$edu;
            }
            return TYPE_TUTORIAL$ar$edu;
        }
        return TYPE_UNKNOWN$ar$edu;
    }

    public static int[] values$ar$edu$9bc29e62_0() {
        return new int[]{TYPE_UNKNOWN$ar$edu, TYPE_TUTORIAL$ar$edu, TYPE_ONBOARDING$ar$edu};
    }
}
