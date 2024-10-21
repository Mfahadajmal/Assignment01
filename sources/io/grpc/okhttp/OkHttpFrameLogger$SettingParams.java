package io.grpc.okhttp;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum OkHttpFrameLogger$SettingParams {
    HEADER_TABLE_SIZE(1),
    ENABLE_PUSH(2),
    MAX_CONCURRENT_STREAMS(4),
    MAX_FRAME_SIZE(5),
    MAX_HEADER_LIST_SIZE(6),
    INITIAL_WINDOW_SIZE(7);

    public final int bit;

    OkHttpFrameLogger$SettingParams(int i) {
        this.bit = i;
    }
}
