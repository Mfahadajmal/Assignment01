package com.googlecode.eyesfree.brailleback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$ConnectProtocol {
    public static final int CONNECT_PROTOCOL_UNSPECIFIED$ar$edu = 1;
    public static final int CONNECT_PROTOCOL_SERIAL$ar$edu = 2;
    public static final int CONNECT_PROTOCOL_RFCOMM$ar$edu = 3;
    public static final int CONNECT_PROTOCOL_HID$ar$edu = 4;
    private static final /* synthetic */ int[] $VALUES$ar$edu$7617fcb6_0 = {CONNECT_PROTOCOL_UNSPECIFIED$ar$edu, CONNECT_PROTOCOL_SERIAL$ar$edu, CONNECT_PROTOCOL_RFCOMM$ar$edu, CONNECT_PROTOCOL_HID$ar$edu};

    public static int forNumber$ar$edu$3cfa585a_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return 0;
                    }
                    return CONNECT_PROTOCOL_HID$ar$edu;
                }
                return CONNECT_PROTOCOL_RFCOMM$ar$edu;
            }
            return CONNECT_PROTOCOL_SERIAL$ar$edu;
        }
        return CONNECT_PROTOCOL_UNSPECIFIED$ar$edu;
    }

    public static int[] values$ar$edu$71651d8c_0() {
        return new int[]{CONNECT_PROTOCOL_UNSPECIFIED$ar$edu, CONNECT_PROTOCOL_SERIAL$ar$edu, CONNECT_PROTOCOL_RFCOMM$ar$edu, CONNECT_PROTOCOL_HID$ar$edu};
    }
}
