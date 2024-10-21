package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.util.MultiChildLoadBalancer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DelayedClientTransport implements ManagedClientTransport {
    private final Executor defaultAppExecutor;
    public ManagedClientTransport.Listener listener;
    public Runnable reportTransportInUse;
    public Runnable reportTransportNotInUse;
    public Runnable reportTransportTerminated;
    public final SynchronizationContext syncContext;
    private final InternalLogId logId = InternalLogId.allocate(DelayedClientTransport.class, (String) null);
    public final Object lock = new Object();
    public Collection pendingStreams = new LinkedHashSet();
    public volatile MultiChildLoadBalancer.AcceptResolvedAddrRetVal pickerState$ar$class_merging$ar$class_merging = new MultiChildLoadBalancer.AcceptResolvedAddrRetVal((Object) null, (Object) null);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PendingStream extends DelayedStream {
        public final LoadBalancer.PickSubchannelArgs args;
        public final Context context = Context.current();
        public final ClientStreamTracer[] tracers;

        public PendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs, ClientStreamTracer[] clientStreamTracerArr) {
            this.args = pickSubchannelArgs;
            this.tracers = clientStreamTracerArr;
        }

        @Override // io.grpc.internal.DelayedStream, io.grpc.internal.ClientStream
        public final void appendTimeoutInsight(InsightBuilder insightBuilder) {
            if (this.args.callOptions.isWaitForReady()) {
                insightBuilder.append$ar$ds("wait_for_ready");
            }
            super.appendTimeoutInsight(insightBuilder);
        }

        @Override // io.grpc.internal.DelayedStream, io.grpc.internal.ClientStream
        public final void cancel(Status status) {
            super.cancel(status);
            synchronized (DelayedClientTransport.this.lock) {
                DelayedClientTransport delayedClientTransport = DelayedClientTransport.this;
                if (delayedClientTransport.reportTransportTerminated != null) {
                    boolean remove = delayedClientTransport.pendingStreams.remove(this);
                    if (!DelayedClientTransport.this.hasPendingStreams() && remove) {
                        DelayedClientTransport delayedClientTransport2 = DelayedClientTransport.this;
                        delayedClientTransport2.syncContext.executeLater(delayedClientTransport2.reportTransportNotInUse);
                        if (DelayedClientTransport.this.pickerState$ar$class_merging$ar$class_merging.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status != null) {
                            DelayedClientTransport delayedClientTransport3 = DelayedClientTransport.this;
                            delayedClientTransport3.syncContext.executeLater(delayedClientTransport3.reportTransportTerminated);
                            DelayedClientTransport.this.reportTransportTerminated = null;
                        }
                    }
                }
            }
            DelayedClientTransport.this.syncContext.drain();
        }

        @Override // io.grpc.internal.DelayedStream
        protected final void onEarlyCancellation(Status status) {
            int i = 0;
            while (true) {
                ClientStreamTracer[] clientStreamTracerArr = this.tracers;
                if (i < clientStreamTracerArr.length) {
                    clientStreamTracerArr[i].streamClosed$ar$ds();
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public DelayedClientTransport(Executor executor, SynchronizationContext synchronizationContext) {
        this.defaultAppExecutor = executor;
        this.syncContext = synchronizationContext;
    }

    @Override // io.grpc.InternalWithLogId
    public final InternalLogId getLogId() {
        return this.logId;
    }

    public final boolean hasPendingStreams() {
        boolean z;
        synchronized (this.lock) {
            z = !this.pendingStreams.isEmpty();
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x006b, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003e, code lost:
    
        r3 = new io.grpc.internal.DelayedClientTransport.PendingStream(r2, r0, r6);
        r2.pendingStreams.add(r3);
        r5 = r2.lock;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004a, code lost:
    
        monitor-enter(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004b, code lost:
    
        r0 = r2.pendingStreams.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0051, code lost:
    
        monitor-exit(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
    
        if (r0 != 1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
    
        r2.syncContext.executeLater(r2.reportTransportInUse);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005c, code lost:
    
        r5 = r6.length;
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005e, code lost:
    
        if (r0 >= r5) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0060, code lost:
    
        r1 = r6[r0];
        r0 = r0 + 1;
     */
    @Override // io.grpc.internal.ClientTransport
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final io.grpc.internal.ClientStream newStream(io.grpc.MethodDescriptor r3, io.grpc.Metadata r4, io.grpc.CallOptions r5, io.grpc.ClientStreamTracer[] r6) {
        /*
            r2 = this;
            io.grpc.LoadBalancer$PickSubchannelArgs r0 = new io.grpc.LoadBalancer$PickSubchannelArgs     // Catch: java.lang.Throwable -> L75
            io.grpc.internal.PickDetailsConsumerImpl r1 = new io.grpc.internal.PickDetailsConsumerImpl     // Catch: java.lang.Throwable -> L75
            r1.<init>()     // Catch: java.lang.Throwable -> L75
            r0.<init>(r3, r4, r5, r1)     // Catch: java.lang.Throwable -> L75
            io.grpc.util.MultiChildLoadBalancer$AcceptResolvedAddrRetVal r3 = r2.pickerState$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> L75
        Lc:
            java.lang.Object r4 = r3.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status     // Catch: java.lang.Throwable -> L75
            if (r4 == 0) goto L18
            io.grpc.internal.FailingClientStream r3 = new io.grpc.internal.FailingClientStream     // Catch: java.lang.Throwable -> L75
            io.grpc.Status r4 = (io.grpc.Status) r4     // Catch: java.lang.Throwable -> L75
            r3.<init>(r4, r6)     // Catch: java.lang.Throwable -> L75
            goto L66
        L18:
            java.lang.Object r4 = r3.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren     // Catch: java.lang.Throwable -> L75
            if (r4 == 0) goto L37
            io.grpc.LoadBalancer$SubchannelPicker r4 = (io.grpc.LoadBalancer.SubchannelPicker) r4     // Catch: java.lang.Throwable -> L75
            io.grpc.LoadBalancer$PickResult r4 = r4.pickSubchannel(r0)     // Catch: java.lang.Throwable -> L75
            boolean r1 = r5.isWaitForReady()     // Catch: java.lang.Throwable -> L75
            io.grpc.internal.ClientTransport r4 = io.grpc.internal.GrpcUtil.getTransportFromPickResult(r4, r1)     // Catch: java.lang.Throwable -> L75
            if (r4 == 0) goto L37
            io.grpc.MethodDescriptor r3 = r0.method     // Catch: java.lang.Throwable -> L75
            io.grpc.Metadata r5 = r0.headers     // Catch: java.lang.Throwable -> L75
            io.grpc.CallOptions r0 = r0.callOptions     // Catch: java.lang.Throwable -> L75
            io.grpc.internal.ClientStream r3 = r4.newStream(r3, r5, r0, r6)     // Catch: java.lang.Throwable -> L75
            goto L66
        L37:
            java.lang.Object r4 = r2.lock     // Catch: java.lang.Throwable -> L75
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L75
            io.grpc.util.MultiChildLoadBalancer$AcceptResolvedAddrRetVal r1 = r2.pickerState$ar$class_merging$ar$class_merging     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L6f
            io.grpc.internal.DelayedClientTransport$PendingStream r3 = new io.grpc.internal.DelayedClientTransport$PendingStream     // Catch: java.lang.Throwable -> L72
            r3.<init>(r0, r6)     // Catch: java.lang.Throwable -> L72
            java.util.Collection r5 = r2.pendingStreams     // Catch: java.lang.Throwable -> L72
            r5.add(r3)     // Catch: java.lang.Throwable -> L72
            java.lang.Object r5 = r2.lock     // Catch: java.lang.Throwable -> L72
            monitor-enter(r5)     // Catch: java.lang.Throwable -> L72
            java.util.Collection r0 = r2.pendingStreams     // Catch: java.lang.Throwable -> L6c
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L6c
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L6c
            r5 = 1
            if (r0 != r5) goto L5c
            io.grpc.SynchronizationContext r5 = r2.syncContext     // Catch: java.lang.Throwable -> L72
            java.lang.Runnable r0 = r2.reportTransportInUse     // Catch: java.lang.Throwable -> L72
            r5.executeLater(r0)     // Catch: java.lang.Throwable -> L72
        L5c:
            int r5 = r6.length     // Catch: java.lang.Throwable -> L72
            r0 = 0
        L5e:
            if (r0 >= r5) goto L65
            r1 = r6[r0]     // Catch: java.lang.Throwable -> L72
            int r0 = r0 + 1
            goto L5e
        L65:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L72
        L66:
            io.grpc.SynchronizationContext r4 = r2.syncContext
            r4.drain()
            return r3
        L6c:
            r3 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L6c
            throw r3     // Catch: java.lang.Throwable -> L72
        L6f:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L72
            r3 = r1
            goto Lc
        L72:
            r3 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L72
            throw r3     // Catch: java.lang.Throwable -> L75
        L75:
            r3 = move-exception
            io.grpc.SynchronizationContext r4 = r2.syncContext
            r4.drain()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientTransport.newStream(io.grpc.MethodDescriptor, io.grpc.Metadata, io.grpc.CallOptions, io.grpc.ClientStreamTracer[]):io.grpc.internal.ClientStream");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void reprocess(LoadBalancer.SubchannelPicker subchannelPicker) {
        Runnable runnable;
        synchronized (this.lock) {
            this.pickerState$ar$class_merging$ar$class_merging = new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(subchannelPicker, this.pickerState$ar$class_merging$ar$class_merging.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status);
            if (subchannelPicker != null && hasPendingStreams()) {
                ArrayList arrayList = new ArrayList(this.pendingStreams);
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    PendingStream pendingStream = (PendingStream) arrayList.get(i);
                    LoadBalancer.PickResult pickSubchannel = subchannelPicker.pickSubchannel(pendingStream.args);
                    CallOptions callOptions = pendingStream.args.callOptions;
                    ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(pickSubchannel, callOptions.isWaitForReady());
                    if (transportFromPickResult != null) {
                        Executor executor = this.defaultAppExecutor;
                        Executor executor2 = callOptions.executor;
                        Context attach = pendingStream.context.attach();
                        try {
                            LoadBalancer.PickSubchannelArgs pickSubchannelArgs = pendingStream.args;
                            ClientStream newStream = transportFromPickResult.newStream(pickSubchannelArgs.method, pickSubchannelArgs.headers, pickSubchannelArgs.callOptions, pendingStream.tracers);
                            pendingStream.context.detach(attach);
                            Runnable stream = pendingStream.setStream(newStream);
                            if (stream != null) {
                                if (executor2 != null) {
                                    executor = executor2;
                                }
                                executor.execute(stream);
                            }
                            arrayList2.add(pendingStream);
                        } catch (Throwable th) {
                            pendingStream.context.detach(attach);
                            throw th;
                        }
                    }
                }
                synchronized (this.lock) {
                    if (!hasPendingStreams()) {
                        return;
                    }
                    this.pendingStreams.removeAll(arrayList2);
                    if (this.pendingStreams.isEmpty()) {
                        this.pendingStreams = new LinkedHashSet();
                    }
                    if (!hasPendingStreams()) {
                        this.syncContext.executeLater(this.reportTransportNotInUse);
                        if (this.pickerState$ar$class_merging$ar$class_merging.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status != null && (runnable = this.reportTransportTerminated) != null) {
                            this.syncContext.executeLater(runnable);
                            this.reportTransportTerminated = null;
                        }
                    }
                    this.syncContext.drain();
                }
            }
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdown(Status status) {
        Runnable runnable;
        synchronized (this.lock) {
            if (this.pickerState$ar$class_merging$ar$class_merging.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status != null) {
                return;
            }
            this.pickerState$ar$class_merging$ar$class_merging = new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(this.pickerState$ar$class_merging$ar$class_merging.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren, status);
            this.syncContext.executeLater(new DelayedClientCall.AnonymousClass4(this, status, 4, null));
            if (!hasPendingStreams() && (runnable = this.reportTransportTerminated) != null) {
                this.syncContext.executeLater(runnable);
                this.reportTransportTerminated = null;
            }
            this.syncContext.drain();
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdownNow(Status status) {
        throw null;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final Runnable start(ManagedClientTransport.Listener listener) {
        throw null;
    }
}
