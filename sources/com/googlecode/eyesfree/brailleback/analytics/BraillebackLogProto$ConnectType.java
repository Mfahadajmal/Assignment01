package com.googlecode.eyesfree.brailleback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$ConnectType {
    public static final int CONNECT_TYPE_UNSPECIFIED_CONNECT_TYPE$ar$edu = 1;
    public static final int CONNECT_TYPE_BLUETOOTH$ar$edu = 2;
    public static final int CONNECT_TYPE_USB$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$c47d77ed_0 = {CONNECT_TYPE_UNSPECIFIED_CONNECT_TYPE$ar$edu, CONNECT_TYPE_BLUETOOTH$ar$edu, CONNECT_TYPE_USB$ar$edu};

    public static int forNumber$ar$edu$9ba822e0_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return CONNECT_TYPE_USB$ar$edu;
            }
            return CONNECT_TYPE_BLUETOOTH$ar$edu;
        }
        return CONNECT_TYPE_UNSPECIFIED_CONNECT_TYPE$ar$edu;
    }

    public static int[] values$ar$edu$a65c51ec_0() {
        return new int[]{CONNECT_TYPE_UNSPECIFIED_CONNECT_TYPE$ar$edu, CONNECT_TYPE_BLUETOOTH$ar$edu, CONNECT_TYPE_USB$ar$edu};
    }
}
