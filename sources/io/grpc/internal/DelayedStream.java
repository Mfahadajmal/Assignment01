package io.grpc.internal;

import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.DelayedClientCall;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DelayedStream implements ClientStream {
    private DelayedStreamListener delayedListener;
    private Status error;
    private ClientStreamListener listener;
    private volatile boolean passThrough;
    private List pendingCalls = new ArrayList();
    private List preStartPendingCalls = new ArrayList();
    public ClientStream realStream;
    private long startTimeNanos;
    private long streamSetTimeNanos;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DelayedStreamListener implements ClientStreamListener {
        public volatile boolean passThrough;
        public List pendingCallbacks = new ArrayList();
        public final ClientStreamListener realListener;

        public DelayedStreamListener(ClientStreamListener clientStreamListener) {
            this.realListener = clientStreamListener;
        }

        private final void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        @Override // io.grpc.internal.ClientStreamListener
        public final void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            delayOrExecute(new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(this, status, rpcProgress, metadata, 6));
        }

        @Override // io.grpc.internal.ClientStreamListener
        public final void headersRead(Metadata metadata) {
            delayOrExecute(new DelayedClientCall.AnonymousClass4(this, metadata, 11, null));
        }

        @Override // io.grpc.internal.StreamListener
        public final void messagesAvailable$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
            if (this.passThrough) {
                this.realListener.messagesAvailable$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent);
            } else {
                delayOrExecute(new DelayedClientCall.AnonymousClass4(this, onDeviceTextDetectionLoadLogEvent, 10));
            }
        }

        @Override // io.grpc.internal.StreamListener
        public final void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Futures$$ExternalSyntheticLambda1(this, 19, null));
            }
        }
    }

    private final void delayOrExecute(Runnable runnable) {
        boolean z;
        if (this.listener != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called after start");
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingCalls.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    private final void internalStart(ClientStreamListener clientStreamListener) {
        Iterator it = this.preStartPendingCalls.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.preStartPendingCalls = null;
        this.realStream.start(clientStreamListener);
    }

    private final void setRealStream(ClientStream clientStream) {
        boolean z;
        ClientStream clientStream2 = this.realStream;
        if (clientStream2 == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "realStream already set to %s", clientStream2);
        this.realStream = clientStream;
        this.streamSetTimeNanos = System.nanoTime();
    }

    @Override // io.grpc.internal.ClientStream
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        synchronized (this) {
            if (this.listener == null) {
                return;
            }
            if (this.realStream != null) {
                insightBuilder.appendKeyValue$ar$ds("buffered_nanos", Long.valueOf(this.streamSetTimeNanos - this.startTimeNanos));
                this.realStream.appendTimeoutInsight(insightBuilder);
            } else {
                insightBuilder.appendKeyValue$ar$ds("buffered_nanos", Long.valueOf(System.nanoTime() - this.startTimeNanos));
                insightBuilder.append$ar$ds("waiting_for_connection");
            }
        }
    }

    @Override // io.grpc.internal.ClientStream
    public void cancel(Status status) {
        boolean z;
        boolean z2 = true;
        if (this.listener != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called after start");
        status.getClass();
        synchronized (this) {
            if (this.realStream == null) {
                setRealStream(NoopClientStream.INSTANCE);
                this.error = status;
                z2 = false;
            }
        }
        if (z2) {
            delayOrExecute(new DelayedClientCall.AnonymousClass4(this, status, 9, null));
            return;
        }
        drainPendingCalls();
        onEarlyCancellation(status);
        this.listener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x005c, code lost:
    
        if (r0.hasNext() == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005e, code lost:
    
        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0054, code lost:
    
        r0 = r1.iterator();
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x004e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void drainPendingCalls() {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L5:
            monitor-enter(r6)
            java.util.List r1 = r6.pendingCalls     // Catch: java.lang.Throwable -> L6d
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L6d
            if (r1 == 0) goto L4f
            r0 = 0
            r6.pendingCalls = r0     // Catch: java.lang.Throwable -> L6d
            r1 = 1
            r6.passThrough = r1     // Catch: java.lang.Throwable -> L6d
            io.grpc.internal.DelayedStream$DelayedStreamListener r2 = r6.delayedListener     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6d
            if (r2 == 0) goto L4e
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
        L1e:
            monitor-enter(r2)
            java.util.List r4 = r2.pendingCallbacks     // Catch: java.lang.Throwable -> L4b
            boolean r4 = r4.isEmpty()     // Catch: java.lang.Throwable -> L4b
            if (r4 == 0) goto L2d
            r2.pendingCallbacks = r0     // Catch: java.lang.Throwable -> L4b
            r2.passThrough = r1     // Catch: java.lang.Throwable -> L4b
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4b
            return
        L2d:
            java.util.List r4 = r2.pendingCallbacks     // Catch: java.lang.Throwable -> L4b
            r2.pendingCallbacks = r3     // Catch: java.lang.Throwable -> L4b
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4b
            java.util.Iterator r3 = r4.iterator()
        L36:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L46
            java.lang.Object r5 = r3.next()
            java.lang.Runnable r5 = (java.lang.Runnable) r5
            r5.run()
            goto L36
        L46:
            r4.clear()
            r3 = r4
            goto L1e
        L4b:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4b
            throw r0
        L4e:
            return
        L4f:
            java.util.List r1 = r6.pendingCalls     // Catch: java.lang.Throwable -> L6d
            r6.pendingCalls = r0     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6d
            java.util.Iterator r0 = r1.iterator()
        L58:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L68
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L58
        L68:
            r1.clear()
            r0 = r1
            goto L5
        L6d:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6d
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedStream.drainPendingCalls():void");
    }

    @Override // io.grpc.internal.Stream
    public final void flush() {
        boolean z;
        if (this.listener != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called after start");
        if (this.passThrough) {
            this.realStream.flush();
        } else {
            delayOrExecute(new Futures$$ExternalSyntheticLambda1(this, 17, null));
        }
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        throw null;
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
        boolean z;
        if (this.listener != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called after start");
        delayOrExecute(new Futures$$ExternalSyntheticLambda1(this, 18, null));
    }

    @Override // io.grpc.internal.Stream
    public final boolean isReady() {
        if (this.passThrough) {
            return this.realStream.isReady();
        }
        return false;
    }

    @Override // io.grpc.internal.Stream
    public final void optimizeForDirectExecutor() {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called before start");
        this.preStartPendingCalls.add(new Futures$$ExternalSyntheticLambda1(this, 15, null));
    }

    @Override // io.grpc.internal.Stream
    public final void request(int i) {
        boolean z;
        if (this.listener != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called after start");
        if (this.passThrough) {
            this.realStream.request(i);
        } else {
            delayOrExecute(new RatingView$$ExternalSyntheticLambda5(this, i, 8, (byte[]) null));
        }
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(Compressor compressor) {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called before start");
        this.preStartPendingCalls.add(new DelayedClientCall.AnonymousClass4(this, compressor, 5, null));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDeadline(Deadline deadline) {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called before start");
        this.preStartPendingCalls.add(new DelayedClientCall.AnonymousClass4(this, deadline, 7, null));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called before start");
        decompressorRegistry.getClass();
        this.preStartPendingCalls.add(new DelayedClientCall.AnonymousClass4(this, decompressorRegistry, 6, null));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxInboundMessageSize(int i) {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called before start");
        this.preStartPendingCalls.add(new RatingView$$ExternalSyntheticLambda5(this, i, 9, (byte[]) null));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxOutboundMessageSize(int i) {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called before start");
        this.preStartPendingCalls.add(new RatingView$$ExternalSyntheticLambda5(this, i, 10, (byte[]) null));
    }

    public final Runnable setStream(ClientStream clientStream) {
        synchronized (this) {
            if (this.realStream == null) {
                clientStream.getClass();
                setRealStream(clientStream);
                ClientStreamListener clientStreamListener = this.listener;
                if (clientStreamListener == null) {
                    this.pendingCalls = null;
                    this.passThrough = true;
                }
                if (clientStreamListener != null) {
                    internalStart(clientStreamListener);
                    return new Futures$$ExternalSyntheticLambda1(this, 16, null);
                }
            }
        }
        return null;
    }

    @Override // io.grpc.internal.ClientStream
    public final void start(ClientStreamListener clientStreamListener) {
        boolean z;
        Status status;
        boolean z2;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "already started");
        synchronized (this) {
            status = this.error;
            z2 = this.passThrough;
            if (!z2) {
                DelayedStreamListener delayedStreamListener = new DelayedStreamListener(clientStreamListener);
                this.delayedListener = delayedStreamListener;
                clientStreamListener = delayedStreamListener;
            }
            this.listener = clientStreamListener;
            this.startTimeNanos = System.nanoTime();
        }
        if (status != null) {
            clientStreamListener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
        } else if (z2) {
            internalStart(clientStreamListener);
        }
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
        boolean z;
        if (this.listener != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "May only be called after start");
        if (this.passThrough) {
            this.realStream.writeMessage(inputStream);
        } else {
            delayOrExecute(new DelayedClientCall.AnonymousClass4(this, inputStream, 8));
        }
    }

    protected void onEarlyCancellation(Status status) {
    }
}
