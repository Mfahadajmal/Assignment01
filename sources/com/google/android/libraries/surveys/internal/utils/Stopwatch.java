package com.google.android.libraries.surveys.internal.utils;

import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Stopwatch {
    private final long start = System.nanoTime();

    public final Duration getElapsed() {
        long nanoTime = System.nanoTime() - this.start;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Duration.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        ((Duration) builder.instance).seconds_ = nanoTime / 1000000000;
        builder.copyOnWrite();
        ((Duration) builder.instance).nanos_ = (int) (nanoTime % 1000000000);
        return (Duration) builder.build();
    }

    public final Timestamp getStart() {
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Timestamp.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        Timestamp timestamp = (Timestamp) builder.instance;
        long j = this.start;
        timestamp.seconds_ = j / 1000000000;
        builder.copyOnWrite();
        ((Timestamp) builder.instance).nanos_ = (int) (j % 1000000000);
        return (Timestamp) builder.build();
    }
}
