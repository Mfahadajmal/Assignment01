package com.google.scone.proto;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Survey$ClientCapability {
    public static final int CLIENT_CAPABILITY_UNKNOWN$ar$edu$ede8387a_0 = 2;
    public static final int CLIENT_CAPABILITY_PII$ar$edu$ede8387a_0 = 3;
    public static final int CLIENT_CAPABILITY_RATING_SCALE_10_11$ar$edu$ede8387a_0 = 4;
    public static final int UNRECOGNIZED$ar$edu$ede8387a_0 = 1;
    private static final /* synthetic */ int[] $VALUES$ar$edu$6848a6eb_0 = {CLIENT_CAPABILITY_UNKNOWN$ar$edu$ede8387a_0, CLIENT_CAPABILITY_PII$ar$edu$ede8387a_0, CLIENT_CAPABILITY_RATING_SCALE_10_11$ar$edu$ede8387a_0, UNRECOGNIZED$ar$edu$ede8387a_0};

    public static int getNumber$ar$edu$ede8387a_0(int i) {
        if (i != UNRECOGNIZED$ar$edu$ede8387a_0) {
            return i - 2;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static int[] values$ar$edu$94f18f61_0() {
        return new int[]{CLIENT_CAPABILITY_UNKNOWN$ar$edu$ede8387a_0, CLIENT_CAPABILITY_PII$ar$edu$ede8387a_0, CLIENT_CAPABILITY_RATING_SCALE_10_11$ar$edu$ede8387a_0, UNRECOGNIZED$ar$edu$ede8387a_0};
    }
}
