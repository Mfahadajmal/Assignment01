package logs.proto.wireless.performance.mobile;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$RequestFailedReason {
    public static final int REQUEST_FAILED_REASON_UNSPECIFIED$ar$edu = 1;
    public static final int LISTENER_EXCEPTION_THROWN$ar$edu = 2;
    public static final int HOSTNAME_NOT_RESOLVED$ar$edu = 3;
    public static final int INTERNET_DISCONNECTED$ar$edu = 4;
    public static final int NETWORK_CHANGED$ar$edu = 5;
    public static final int TIMED_OUT$ar$edu = 6;
    public static final int CONNECTION_CLOSED$ar$edu = 7;
    public static final int CONNECTION_TIMED_OUT$ar$edu = 8;
    public static final int CONNECTION_REFUSED$ar$edu = 9;
    public static final int CONNECTION_RESET$ar$edu = 10;
    public static final int ADDRESS_UNREACHABLE$ar$edu = 11;
    public static final int QUIC_PROTOCOL_FAILED$ar$edu = 12;
    public static final int OTHER$ar$edu$568892b2_0 = 13;
    private static final /* synthetic */ int[] $VALUES$ar$edu$8ee933ff_0 = {REQUEST_FAILED_REASON_UNSPECIFIED$ar$edu, LISTENER_EXCEPTION_THROWN$ar$edu, HOSTNAME_NOT_RESOLVED$ar$edu, INTERNET_DISCONNECTED$ar$edu, NETWORK_CHANGED$ar$edu, TIMED_OUT$ar$edu, CONNECTION_CLOSED$ar$edu, CONNECTION_TIMED_OUT$ar$edu, CONNECTION_REFUSED$ar$edu, CONNECTION_RESET$ar$edu, ADDRESS_UNREACHABLE$ar$edu, QUIC_PROTOCOL_FAILED$ar$edu, OTHER$ar$edu$568892b2_0};

    public static int forNumber$ar$edu$a09348fc_0(int i) {
        switch (i) {
            case 0:
                return REQUEST_FAILED_REASON_UNSPECIFIED$ar$edu;
            case 1:
                return LISTENER_EXCEPTION_THROWN$ar$edu;
            case 2:
                return HOSTNAME_NOT_RESOLVED$ar$edu;
            case 3:
                return INTERNET_DISCONNECTED$ar$edu;
            case 4:
                return NETWORK_CHANGED$ar$edu;
            case 5:
                return TIMED_OUT$ar$edu;
            case 6:
                return CONNECTION_CLOSED$ar$edu;
            case 7:
                return CONNECTION_TIMED_OUT$ar$edu;
            case 8:
                return CONNECTION_REFUSED$ar$edu;
            case 9:
                return CONNECTION_RESET$ar$edu;
            case 10:
                return ADDRESS_UNREACHABLE$ar$edu;
            case 11:
                return QUIC_PROTOCOL_FAILED$ar$edu;
            case 12:
                return OTHER$ar$edu$568892b2_0;
            default:
                return 0;
        }
    }

    public static int[] values$ar$edu$45046b_0() {
        return new int[]{REQUEST_FAILED_REASON_UNSPECIFIED$ar$edu, LISTENER_EXCEPTION_THROWN$ar$edu, HOSTNAME_NOT_RESOLVED$ar$edu, INTERNET_DISCONNECTED$ar$edu, NETWORK_CHANGED$ar$edu, TIMED_OUT$ar$edu, CONNECTION_CLOSED$ar$edu, CONNECTION_TIMED_OUT$ar$edu, CONNECTION_REFUSED$ar$edu, CONNECTION_RESET$ar$edu, ADDRESS_UNREACHABLE$ar$edu, QUIC_PROTOCOL_FAILED$ar$edu, OTHER$ar$edu$568892b2_0};
    }
}
