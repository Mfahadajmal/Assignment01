package logs.proto.wireless.performance.mobile;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$RequestStatus {
    public static final int REQUEST_STATUS_UNSPECIFIED$ar$edu = 1;
    public static final int SUCCEEDED$ar$edu = 2;
    public static final int FAILED$ar$edu = 3;
    public static final int CANCELED$ar$edu = 4;
    private static final /* synthetic */ int[] $VALUES$ar$edu$ee8474c4_0 = {REQUEST_STATUS_UNSPECIFIED$ar$edu, SUCCEEDED$ar$edu, FAILED$ar$edu, CANCELED$ar$edu};

    public static int forNumber$ar$edu$8ed70632_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return 0;
                    }
                    return CANCELED$ar$edu;
                }
                return FAILED$ar$edu;
            }
            return SUCCEEDED$ar$edu;
        }
        return REQUEST_STATUS_UNSPECIFIED$ar$edu;
    }

    public static int[] values$ar$edu$91f4abd2_0() {
        return new int[]{REQUEST_STATUS_UNSPECIFIED$ar$edu, SUCCEEDED$ar$edu, FAILED$ar$edu, CANCELED$ar$edu};
    }
}
