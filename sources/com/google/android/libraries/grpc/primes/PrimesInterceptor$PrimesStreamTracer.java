package com.google.android.libraries.grpc.primes;

import android.os.SystemClock;
import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Grpc;
import io.grpc.binder.AndroidComponentAddress;
import io.grpc.inprocess.InProcessSocketAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import logs.proto.wireless.performance.mobile.NetworkMetric$PeerDistance;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PrimesInterceptor$PrimesStreamTracer extends ClientStreamTracer {
    public int inboundBytes;
    public final Object lock = new Object();
    public final NetworkEvent networkEvent;
    public int outboundBytes;
    public boolean recordedToPrimes;

    public PrimesInterceptor$PrimesStreamTracer(NetworkEvent networkEvent) {
        this.networkEvent = networkEvent;
    }

    @Override // io.grpc.ClientStreamTracer
    public final void inboundHeaders() {
        synchronized (this.lock) {
            if (!this.recordedToPrimes) {
                NetworkEvent networkEvent = this.networkEvent;
                networkEvent.timeToResponseHeaderMs = SystemClock.elapsedRealtime() - networkEvent.startTimeMs;
            }
        }
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent
    public final void inboundWireSize(long j) {
        synchronized (this.lock) {
            this.inboundBytes += (int) j;
        }
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent
    public final void outboundWireSize(long j) {
        synchronized (this.lock) {
            this.outboundBytes += (int) j;
        }
    }

    @Override // io.grpc.ClientStreamTracer
    public final void streamCreated$ar$ds(Attributes attributes) {
        int i;
        synchronized (this.lock) {
            NetworkEvent networkEvent = this.networkEvent;
            SocketAddress socketAddress = (SocketAddress) attributes.get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR);
            if (!(socketAddress instanceof InetSocketAddress)) {
                if (socketAddress instanceof InProcessSocketAddress) {
                    i = NetworkMetric$PeerDistance.PEER_DISTANCE_IN_PROCESS$ar$edu;
                } else if (socketAddress instanceof AndroidComponentAddress) {
                    i = NetworkMetric$PeerDistance.PEER_DISTANCE_INTER_PROCESS$ar$edu;
                }
                networkEvent.serverDistance$ar$edu = i;
            }
        }
    }
}
