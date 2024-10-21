package logs.proto.wireless.performance.mobile;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceOuterClass$EndStatus {
    public static final int UNKNOWN_STATUS$ar$edu = 1;
    public static final int SUCCESS$ar$edu = 2;
    public static final int ERROR$ar$edu = 3;
    public static final int CANCEL$ar$edu = 4;
    private static final /* synthetic */ int[] $VALUES$ar$edu$f316e82_0 = {UNKNOWN_STATUS$ar$edu, SUCCESS$ar$edu, ERROR$ar$edu, CANCEL$ar$edu};

    public static int forNumber$ar$edu$609d8896_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return 0;
                    }
                    return CANCEL$ar$edu;
                }
                return ERROR$ar$edu;
            }
            return SUCCESS$ar$edu;
        }
        return UNKNOWN_STATUS$ar$edu;
    }

    public static int[] values$ar$edu$8cb63f35_0() {
        return new int[]{UNKNOWN_STATUS$ar$edu, SUCCESS$ar$edu, ERROR$ar$edu, CANCEL$ar$edu};
    }
}
