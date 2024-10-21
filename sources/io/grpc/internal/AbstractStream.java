package io.grpc.internal;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.MessageDeframer;
import io.perfmark.Impl;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractStream implements Stream {
    @Override // io.grpc.internal.Stream
    public final void flush() {
        if (!framer().isClosed()) {
            framer().flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Framer framer();

    @Override // io.grpc.internal.Stream
    public boolean isReady() {
        return transportState().isReady();
    }

    public final void onSendingBytes(int i) {
        TransportState transportState = transportState();
        synchronized (transportState.onReadyLock) {
            transportState.numSentBytesQueued += i;
        }
    }

    @Override // io.grpc.internal.Stream
    public final void optimizeForDirectExecutor() {
        TransportState transportState = transportState();
        MessageDeframer messageDeframer = transportState.rawDeframer;
        messageDeframer.listener = transportState;
        transportState.deframer = messageDeframer;
    }

    @Override // io.grpc.internal.Stream
    public final void request(int i) {
        OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage;
        TransportState transportState = transportState();
        Deframer deframer = transportState.deframer;
        identifiedLanguage = Impl.NO_LINK$ar$class_merging;
        transportState.runOnTransportThread(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(transportState, identifiedLanguage, i, 8));
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(Compressor compressor) {
        framer().setCompressor$ar$ds(compressor);
    }

    protected abstract TransportState transportState();

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
        try {
            if (!framer().isClosed()) {
                framer().writePayload(inputStream);
            }
        } finally {
            GrpcUtil.closeQuietly(inputStream);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class TransportState implements ApplicationThreadDeframerListener$TransportExecutor, MessageDeframer.Listener {
        private boolean allocated;
        public boolean deallocated;
        public DecompressorRegistry decompressorRegistry;
        public Deframer deframer;
        private boolean deframerClosed;
        private Runnable deframerClosedTask;
        public ClientStreamListener listener;
        private boolean listenerClosed;
        public int numSentBytesQueued;
        public final Object onReadyLock;
        private int onReadyThreshold;
        public volatile boolean outboundClosed;
        public final MessageDeframer rawDeframer;
        public final StatsTraceContext statsTraceCtx;
        public boolean statusReported;
        private boolean statusReportedIsOk;
        public final TransportTracer transportTracer;

        /* JADX INFO: Access modifiers changed from: protected */
        public TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer, byte[] bArr) {
            this(i, statsTraceContext, transportTracer);
            this.decompressorRegistry = DecompressorRegistry.DEFAULT_INSTANCE;
            this.deframerClosed = false;
            this.statsTraceCtx = statsTraceContext;
        }

        private final void notifyIfReady() {
            boolean isReady;
            synchronized (this.onReadyLock) {
                isReady = isReady();
            }
            if (isReady) {
                listener().onReady();
            }
        }

        public final void closeListener(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            if (!this.listenerClosed) {
                this.listenerClosed = true;
                StatsTraceContext statsTraceContext = this.statsTraceCtx;
                if (statsTraceContext.closed.compareAndSet(false, true)) {
                    for (OnDeviceImageLabelCreateLogEvent onDeviceImageLabelCreateLogEvent : statsTraceContext.tracers$ar$class_merging$ar$class_merging) {
                        onDeviceImageLabelCreateLogEvent.streamClosed$ar$ds();
                    }
                }
                TransportTracer transportTracer = this.transportTracer;
                if (status.isOk()) {
                    transportTracer.streamsSucceeded++;
                } else {
                    transportTracer.streamsFailed++;
                }
                this.listener.closed(status, rpcProgress, metadata);
            }
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframerClosed(boolean z) {
            ContextDataProvider.checkState(this.statusReported, "status should have been reported on deframer closed");
            this.deframerClosed = true;
            if (this.statusReportedIsOk && z) {
                transportReportStatus(Status.INTERNAL.withDescription("Encountered end-of-stream mid-frame"), true, new Metadata());
            }
            Runnable runnable = this.deframerClosedTask;
            if (runnable != null) {
                runnable.run();
                this.deframerClosedTask = null;
            }
        }

        public final boolean isReady() {
            boolean z;
            synchronized (this.onReadyLock) {
                z = false;
                if (this.allocated && this.numSentBytesQueued < this.onReadyThreshold && !this.deallocated) {
                    z = true;
                }
            }
            return z;
        }

        protected final /* synthetic */ StreamListener listener() {
            return this.listener;
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public final void messagesAvailable$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
            listener().messagesAvailable$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent);
        }

        public final void onSentBytes(int i) {
            boolean z;
            synchronized (this.onReadyLock) {
                ContextDataProvider.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                int i2 = this.numSentBytesQueued;
                int i3 = this.onReadyThreshold;
                int i4 = i2 - i;
                this.numSentBytesQueued = i4;
                z = false;
                if (i2 >= i3 && i4 < i3) {
                    z = true;
                }
            }
            if (z) {
                notifyIfReady();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onStreamAllocated() {
            boolean z;
            if (listener() != null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z);
            synchronized (this.onReadyLock) {
                ContextDataProvider.checkState(!this.allocated, "Already allocated");
                this.allocated = true;
            }
            notifyIfReady();
        }

        public final void transportReportStatus(Status status, ClientStreamListener.RpcProgress rpcProgress, boolean z, Metadata metadata) {
            status.getClass();
            metadata.getClass();
            if (this.statusReported) {
                if (!z) {
                    return;
                } else {
                    z = true;
                }
            }
            this.statusReported = true;
            this.statusReportedIsOk = status.isOk();
            synchronized (this.onReadyLock) {
                this.deallocated = true;
            }
            if (this.deframerClosed) {
                this.deframerClosedTask = null;
                closeListener(status, rpcProgress, metadata);
                return;
            }
            this.deframerClosedTask = new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(this, status, rpcProgress, metadata, 5);
            if (z) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        protected TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
            this.onReadyLock = new Object();
            this.transportTracer = transportTracer;
            MessageDeframer messageDeframer = new MessageDeframer(this, Codec.Identity.NONE, i, statsTraceContext, transportTracer);
            this.rawDeframer = messageDeframer;
            this.deframer = messageDeframer;
            this.onReadyThreshold = 32768;
        }

        public final void transportReportStatus(Status status, boolean z, Metadata metadata) {
            transportReportStatus(status, ClientStreamListener.RpcProgress.PROCESSED, z, metadata);
        }
    }
}
