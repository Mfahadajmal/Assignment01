package logs.proto.wireless.performance.mobile;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$PeerDistance {
    public static final int PEER_DISTANCE_UNKNOWN$ar$edu = 1;
    public static final int PEER_DISTANCE_IN_PROCESS$ar$edu = 2;
    public static final int PEER_DISTANCE_INTER_PROCESS$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$cca50c8a_0 = {PEER_DISTANCE_UNKNOWN$ar$edu, PEER_DISTANCE_IN_PROCESS$ar$edu, PEER_DISTANCE_INTER_PROCESS$ar$edu};

    public static int forNumber$ar$edu$edc228e9_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return PEER_DISTANCE_INTER_PROCESS$ar$edu;
            }
            return PEER_DISTANCE_IN_PROCESS$ar$edu;
        }
        return PEER_DISTANCE_UNKNOWN$ar$edu;
    }

    public static int[] values$ar$edu$654ebbd6_0() {
        return new int[]{PEER_DISTANCE_UNKNOWN$ar$edu, PEER_DISTANCE_IN_PROCESS$ar$edu, PEER_DISTANCE_INTER_PROCESS$ar$edu};
    }
}
