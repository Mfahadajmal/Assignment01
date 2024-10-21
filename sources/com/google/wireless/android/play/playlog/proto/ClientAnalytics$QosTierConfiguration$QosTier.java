package com.google.wireless.android.play.playlog.proto;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientAnalytics$QosTierConfiguration$QosTier {
    public static final int DEFAULT$ar$edu$90e3c492_0 = 1;
    public static final int UNMETERED_ONLY$ar$edu = 2;
    public static final int UNMETERED_OR_DAILY$ar$edu = 3;
    public static final int FAST_IF_RADIO_AWAKE$ar$edu = 4;
    public static final int NEVER$ar$edu = 5;
    private static final /* synthetic */ int[] $VALUES$ar$edu$f4ad6700_0 = {DEFAULT$ar$edu$90e3c492_0, UNMETERED_ONLY$ar$edu, UNMETERED_OR_DAILY$ar$edu, FAST_IF_RADIO_AWAKE$ar$edu, NEVER$ar$edu};

    public static int forNumber$ar$edu$febe9175_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return FAST_IF_RADIO_AWAKE$ar$edu;
                }
                return UNMETERED_OR_DAILY$ar$edu;
            }
            return UNMETERED_ONLY$ar$edu;
        }
        return DEFAULT$ar$edu$90e3c492_0;
    }

    public static int[] values$ar$edu$899d7b9f_0() {
        return new int[]{DEFAULT$ar$edu$90e3c492_0, UNMETERED_ONLY$ar$edu, UNMETERED_OR_DAILY$ar$edu, FAST_IF_RADIO_AWAKE$ar$edu, NEVER$ar$edu};
    }
}
