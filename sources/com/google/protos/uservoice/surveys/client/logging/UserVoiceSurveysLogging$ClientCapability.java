package com.google.protos.uservoice.surveys.client.logging;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserVoiceSurveysLogging$ClientCapability {
    public static final int CLIENT_CAPABILITY_UNKNOWN$ar$edu = 2;
    public static final int CLIENT_CAPABILITY_PII$ar$edu = 3;
    public static final int CLIENT_CAPABILITY_RATING_SCALE_10_11$ar$edu = 4;
    public static final int UNRECOGNIZED$ar$edu$12aad49e_0 = 1;
    private static final /* synthetic */ int[] $VALUES$ar$edu$5748c8a1_0 = {CLIENT_CAPABILITY_UNKNOWN$ar$edu, CLIENT_CAPABILITY_PII$ar$edu, CLIENT_CAPABILITY_RATING_SCALE_10_11$ar$edu, UNRECOGNIZED$ar$edu$12aad49e_0};

    public static int getNumber$ar$edu$12aad49e_0(int i) {
        if (i != UNRECOGNIZED$ar$edu$12aad49e_0) {
            return i - 2;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static int[] values$ar$edu$5bfd9b1_0() {
        return new int[]{CLIENT_CAPABILITY_UNKNOWN$ar$edu, CLIENT_CAPABILITY_PII$ar$edu, CLIENT_CAPABILITY_RATING_SCALE_10_11$ar$edu, UNRECOGNIZED$ar$edu$12aad49e_0};
    }
}
