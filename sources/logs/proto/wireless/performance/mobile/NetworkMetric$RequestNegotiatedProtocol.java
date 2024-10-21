package logs.proto.wireless.performance.mobile;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$RequestNegotiatedProtocol {
    public static final int REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN$ar$edu = 1;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_HTTP11$ar$edu = 2;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY2$ar$edu = 3;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY3$ar$edu = 4;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY31$ar$edu = 5;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY4$ar$edu = 6;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3$ar$edu = 7;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43$ar$edu = 8;
    private static final /* synthetic */ int[] $VALUES$ar$edu$5c4c63b9_0 = {REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_HTTP11$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY2$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY3$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY31$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY4$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43$ar$edu};

    public static int forNumber$ar$edu$57054137_0(int i) {
        switch (i) {
            case 0:
                return REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN$ar$edu;
            case 1:
                return REQUEST_NEGOTIATED_PROTOCOL_HTTP11$ar$edu;
            case 2:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY2$ar$edu;
            case 3:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY3$ar$edu;
            case 4:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY31$ar$edu;
            case 5:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY4$ar$edu;
            case 6:
                return REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3$ar$edu;
            case 7:
                return REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43$ar$edu;
            default:
                return 0;
        }
    }

    public static int[] values$ar$edu$1bbea5a9_0() {
        return new int[]{REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_HTTP11$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY2$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY3$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY31$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_SPDY4$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3$ar$edu, REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43$ar$edu};
    }
}
