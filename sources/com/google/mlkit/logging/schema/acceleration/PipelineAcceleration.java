package com.google.mlkit.logging.schema.acceleration;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PipelineAcceleration {
    public static void checkSizeIsValid(long j, String str) {
        if (j >= 0) {
        } else {
            throw new IllegalArgumentException(str);
        }
    }

    public static int fromBoolean$ar$edu(Boolean bool) {
        if (bool == null) {
            return 1;
        }
        if (bool.booleanValue()) {
            return 2;
        }
        return 3;
    }

    public static int getValue$ar$edu$7d7efcc2_0(int i) {
        return i - 1;
    }

    public static boolean isInClosedOpenRange(double d, int i, int i2) {
        if (d >= i && d < i2) {
            return true;
        }
        return false;
    }
}
