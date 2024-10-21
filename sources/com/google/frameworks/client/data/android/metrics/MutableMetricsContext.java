package com.google.frameworks.client.data.android.metrics;

import com.google.frameworks.client.data.android.RpcId;
import io.grpc.CallOptions;
import j$.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MutableMetricsContext implements MetricsContext {
    public static final CallOptions.Key KEY = new CallOptions.Key("com.google.frameworks.client.data.android.metrics.MutableMetricsContext", null);
    public final RpcId rpcId;
    public final AtomicBoolean cacheHit = new AtomicBoolean(false);
    public final AtomicBoolean cacheLookup = new AtomicBoolean(false);
    public final AtomicInteger requests = new AtomicInteger(0);
    public final AtomicInteger serverMessages = new AtomicInteger(0);
    public final AtomicInteger clientMessages = new AtomicInteger(0);
    public final AtomicInteger latency = new AtomicInteger(-1);
    public final ConcurrentLinkedQueue requestBytes = new ConcurrentLinkedQueue();
    public final ConcurrentLinkedQueue responseBytes = new ConcurrentLinkedQueue();
    public final AtomicLong streamDurationMs = new AtomicLong(-1);
    public final AtomicReference result = new AtomicReference(null);

    public MutableMetricsContext(RpcId rpcId) {
        this.rpcId = rpcId;
    }
}
