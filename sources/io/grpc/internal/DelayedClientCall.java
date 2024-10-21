package io.grpc.internal;

import com.google.android.gms.common.api.internal.BaseLifecycleHelper;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.DelayedStream;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.util.MultiChildLoadBalancer;
import java.io.InputStream;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DelayedClientCall extends ClientCall {
    private static final ClientCall NOOP_CALL;
    private final Executor callExecutor;
    public final Context context;
    private DelayedListener delayedListener;
    private Status error;
    private final ScheduledFuture initialDeadlineMonitor;
    private OnDeviceFaceMeshCreateLogEvent listener$ar$class_merging$a40ae667_0$ar$class_merging;
    private volatile boolean passThrough;
    private List pendingRunnables = new ArrayList();
    public ClientCall realCall;

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.DelayedClientCall$4, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 implements Runnable {
        final /* synthetic */ Object DelayedClientCall$4$ar$this$0;
        private final /* synthetic */ int switching_field;
        final /* synthetic */ Object val$message;

        public AnonymousClass4(RetryingNameResolver.ResolutionResultListener resolutionResultListener, Throwable th, int i) {
            this.switching_field = i;
            this.DelayedClientCall$4$ar$this$0 = resolutionResultListener;
            this.val$message = th;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [io.grpc.Compressor, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v145, types: [java.lang.Object, java.lang.Runnable] */
        /* JADX WARN: Type inference failed for: r0v26, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v98, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v15, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v0, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v2, types: [java.util.List, java.lang.Object] */
        @Override // java.lang.Runnable
        public final void run() {
            ManagedClientTransport managedClientTransport;
            boolean z;
            RetriableStream.Throttle throttle;
            boolean z2 = false;
            RetriableStream.FutureCanceller futureCanceller = null;
            switch (this.switching_field) {
                case 0:
                    ((DelayedClientCall) this.DelayedClientCall$4$ar$this$0).realCall.sendMessage(this.val$message);
                    return;
                case 1:
                    Status status = (Status) this.DelayedClientCall$4$ar$this$0;
                    ((DelayedClientCall) this.val$message).realCall.cancel(status.description, status.cause);
                    return;
                case 2:
                    ((DelayedListener) this.val$message).realListener$ar$class_merging$ar$class_merging.onHeaders((Metadata) this.DelayedClientCall$4$ar$this$0);
                    return;
                case 3:
                    ((DelayedListener) this.DelayedClientCall$4$ar$this$0).realListener$ar$class_merging$ar$class_merging.onMessage(this.val$message);
                    return;
                case 4:
                    ((DelayedClientTransport) this.val$message).listener.transportShutdown((Status) this.DelayedClientCall$4$ar$this$0);
                    return;
                case 5:
                    ((DelayedStream) this.val$message).realStream.setCompressor(this.DelayedClientCall$4$ar$this$0);
                    return;
                case 6:
                    ((DelayedStream) this.val$message).realStream.setDecompressorRegistry((DecompressorRegistry) this.DelayedClientCall$4$ar$this$0);
                    return;
                case 7:
                    ((DelayedStream) this.val$message).realStream.setDeadline((Deadline) this.DelayedClientCall$4$ar$this$0);
                    return;
                case 8:
                    ((DelayedStream) this.DelayedClientCall$4$ar$this$0).realStream.writeMessage((InputStream) this.val$message);
                    return;
                case 9:
                    ((DelayedStream) this.val$message).realStream.cancel((Status) this.DelayedClientCall$4$ar$this$0);
                    return;
                case 10:
                    ((DelayedStream.DelayedStreamListener) this.DelayedClientCall$4$ar$this$0).realListener.messagesAvailable$ar$class_merging$ar$class_merging((OnDeviceTextDetectionLoadLogEvent) this.val$message);
                    return;
                case 11:
                    ((DelayedStream.DelayedStreamListener) this.val$message).realListener.headersRead((Metadata) this.DelayedClientCall$4$ar$this$0);
                    return;
                case 12:
                    ((RetryingNameResolver.ResolutionResultListener) this.DelayedClientCall$4$ar$this$0).onFailure$ar$ds();
                    return;
                case 13:
                    Object obj = this.val$message;
                    InternalSubchannel.Index index = ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).addressIndex;
                    SocketAddress currentAddress = ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).addressIndex.getCurrentAddress();
                    index.InternalSubchannel$Index$ar$addressGroups = obj;
                    index.reset();
                    ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).addressGroups = this.val$message;
                    if (((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).state.state == ConnectivityState.READY || ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).state.state == ConnectivityState.CONNECTING) {
                        Object obj2 = this.DelayedClientCall$4$ar$this$0;
                        int i = 0;
                        while (true) {
                            InternalSubchannel.Index index2 = ((InternalSubchannel) obj2).addressIndex;
                            if (i < index2.InternalSubchannel$Index$ar$addressGroups.size()) {
                                int indexOf = ((EquivalentAddressGroup) index2.InternalSubchannel$Index$ar$addressGroups.get(i)).addrs.indexOf(currentAddress);
                                if (indexOf == -1) {
                                    i++;
                                } else {
                                    index2.groupIndex = i;
                                    index2.addressIndex = indexOf;
                                }
                            } else if (((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).state.state == ConnectivityState.READY) {
                                Object obj3 = this.DelayedClientCall$4$ar$this$0;
                                managedClientTransport = ((InternalSubchannel) obj3).activeTransport;
                                ((InternalSubchannel) obj3).activeTransport = null;
                                ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).addressIndex.reset();
                                ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).gotoNonErrorState(ConnectivityState.IDLE);
                            } else {
                                ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).pendingTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed pending transport due to address change"));
                                InternalSubchannel.m243$$Nest$fputpendingTransport$ar$ds((InternalSubchannel) this.DelayedClientCall$4$ar$this$0);
                                ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).addressIndex.reset();
                                ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).startNewTransport();
                            }
                        }
                    }
                    managedClientTransport = null;
                    if (managedClientTransport != null) {
                        InternalSubchannel internalSubchannel = (InternalSubchannel) this.DelayedClientCall$4$ar$this$0;
                        if (internalSubchannel.shutdownDueToUpdateTask$ar$class_merging$ar$class_merging != null) {
                            internalSubchannel.shutdownDueToUpdateTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport early due to address change"));
                            ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).shutdownDueToUpdateTask$ar$class_merging$ar$class_merging.cancel();
                            InternalSubchannel internalSubchannel2 = (InternalSubchannel) this.DelayedClientCall$4$ar$this$0;
                            internalSubchannel2.shutdownDueToUpdateTask$ar$class_merging$ar$class_merging = null;
                            internalSubchannel2.shutdownDueToUpdateTransport = null;
                        }
                        InternalSubchannel internalSubchannel3 = (InternalSubchannel) this.DelayedClientCall$4$ar$this$0;
                        internalSubchannel3.shutdownDueToUpdateTransport = managedClientTransport;
                        internalSubchannel3.shutdownDueToUpdateTask$ar$class_merging$ar$class_merging = internalSubchannel3.syncContext.schedule$ar$class_merging$ar$class_merging(new InternalSubchannel$4$1(this, 0), 5L, TimeUnit.SECONDS, ((InternalSubchannel) this.DelayedClientCall$4$ar$this$0).scheduledExecutor);
                        return;
                    }
                    return;
                case 14:
                    if (((InternalSubchannel) this.val$message).state.state != ConnectivityState.SHUTDOWN) {
                        InternalSubchannel internalSubchannel4 = (InternalSubchannel) this.val$message;
                        internalSubchannel4.shutdownReason = (Status) this.DelayedClientCall$4$ar$this$0;
                        Object obj4 = this.val$message;
                        ManagedClientTransport managedClientTransport2 = internalSubchannel4.activeTransport;
                        InternalSubchannel internalSubchannel5 = (InternalSubchannel) obj4;
                        ConnectionClientTransport connectionClientTransport = internalSubchannel5.pendingTransport;
                        internalSubchannel5.activeTransport = null;
                        InternalSubchannel.m243$$Nest$fputpendingTransport$ar$ds((InternalSubchannel) this.val$message);
                        ((InternalSubchannel) this.val$message).gotoNonErrorState(ConnectivityState.SHUTDOWN);
                        ((InternalSubchannel) this.val$message).addressIndex.reset();
                        if (((InternalSubchannel) this.val$message).transports.isEmpty()) {
                            ((InternalSubchannel) this.val$message).handleTermination();
                        }
                        InternalSubchannel internalSubchannel6 = (InternalSubchannel) this.val$message;
                        internalSubchannel6.syncContext.throwIfNotInThisSynchronizationContext();
                        MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = internalSubchannel6.reconnectTask$ar$class_merging$ar$class_merging;
                        if (acceptResolvedAddrRetVal != null) {
                            acceptResolvedAddrRetVal.cancel();
                            internalSubchannel6.reconnectTask$ar$class_merging$ar$class_merging = null;
                            internalSubchannel6.reconnectPolicy$ar$class_merging = null;
                        }
                        MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal2 = ((InternalSubchannel) this.val$message).shutdownDueToUpdateTask$ar$class_merging$ar$class_merging;
                        if (acceptResolvedAddrRetVal2 != null) {
                            acceptResolvedAddrRetVal2.cancel();
                            ((InternalSubchannel) this.val$message).shutdownDueToUpdateTransport.shutdown((Status) this.DelayedClientCall$4$ar$this$0);
                            InternalSubchannel internalSubchannel7 = (InternalSubchannel) this.val$message;
                            internalSubchannel7.shutdownDueToUpdateTask$ar$class_merging$ar$class_merging = null;
                            internalSubchannel7.shutdownDueToUpdateTransport = null;
                        }
                        if (managedClientTransport2 != null) {
                            managedClientTransport2.shutdown((Status) this.DelayedClientCall$4$ar$this$0);
                        }
                        if (connectionClientTransport != null) {
                            connectionClientTransport.shutdown((Status) this.DelayedClientCall$4$ar$this$0);
                            return;
                        }
                        return;
                    }
                    return;
                case 15:
                    if (InternalSubchannel.this.state.state != ConnectivityState.SHUTDOWN) {
                        Object obj5 = this.val$message;
                        InternalSubchannel.TransportListener transportListener = (InternalSubchannel.TransportListener) obj5;
                        ConnectionClientTransport connectionClientTransport2 = transportListener.transport;
                        if (InternalSubchannel.this.activeTransport == connectionClientTransport2) {
                            InternalSubchannel.this.activeTransport = null;
                            InternalSubchannel.this.addressIndex.reset();
                            InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                            return;
                        }
                        InternalSubchannel internalSubchannel8 = InternalSubchannel.this;
                        if (internalSubchannel8.pendingTransport == connectionClientTransport2) {
                            if (internalSubchannel8.state.state == ConnectivityState.CONNECTING) {
                                z = true;
                            } else {
                                z = false;
                            }
                            ContextDataProvider.checkState(z, "Expected state is CONNECTING, actual state is %s", InternalSubchannel.this.state.state);
                            InternalSubchannel.Index index3 = InternalSubchannel.this.addressIndex;
                            EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) index3.InternalSubchannel$Index$ar$addressGroups.get(index3.groupIndex);
                            int i2 = index3.addressIndex + 1;
                            index3.addressIndex = i2;
                            if (i2 >= equivalentAddressGroup.addrs.size()) {
                                index3.groupIndex++;
                                index3.addressIndex = 0;
                            }
                            InternalSubchannel.Index index4 = InternalSubchannel.this.addressIndex;
                            if (index4.groupIndex >= index4.InternalSubchannel$Index$ar$addressGroups.size()) {
                                InternalSubchannel.m243$$Nest$fputpendingTransport$ar$ds(InternalSubchannel.this);
                                InternalSubchannel.this.addressIndex.reset();
                                Object obj6 = this.val$message;
                                Object obj7 = this.DelayedClientCall$4$ar$this$0;
                                InternalSubchannel internalSubchannel9 = InternalSubchannel.this;
                                internalSubchannel9.syncContext.throwIfNotInThisSynchronizationContext();
                                Status status2 = (Status) obj7;
                                ContextDataProvider.checkArgument(!status2.isOk(), (Object) "The error status must not be OK");
                                internalSubchannel9.gotoState(new ConnectivityStateInfo(ConnectivityState.TRANSIENT_FAILURE, status2));
                                if (internalSubchannel9.reconnectPolicy$ar$class_merging == null) {
                                    internalSubchannel9.reconnectPolicy$ar$class_merging = new ExponentialBackoffPolicy();
                                }
                                long nextBackoffNanos = internalSubchannel9.reconnectPolicy$ar$class_merging.nextBackoffNanos() - internalSubchannel9.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
                                internalSubchannel9.channelLogger.log$ar$edu$7fdc135b_0(2, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", InternalSubchannel.printShortStatus$ar$ds(status2), Long.valueOf(nextBackoffNanos));
                                if (internalSubchannel9.reconnectTask$ar$class_merging$ar$class_merging == null) {
                                    z2 = true;
                                }
                                ContextDataProvider.checkState(z2, "previous reconnectTask is not done");
                                internalSubchannel9.reconnectTask$ar$class_merging$ar$class_merging = internalSubchannel9.syncContext.schedule$ar$class_merging$ar$class_merging(new Futures$$ExternalSyntheticLambda1(internalSubchannel9, 20, null), nextBackoffNanos, TimeUnit.NANOSECONDS, internalSubchannel9.scheduledExecutor);
                                return;
                            }
                            InternalSubchannel.this.startNewTransport();
                            return;
                        }
                        return;
                    }
                    return;
                case 16:
                    ManagedChannelImpl.NameResolverListener nameResolverListener = (ManagedChannelImpl.NameResolverListener) this.val$message;
                    ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                    Logger logger = ManagedChannelImpl.logger;
                    Level level = Level.WARNING;
                    InternalLogId internalLogId = managedChannelImpl.logId;
                    Object obj8 = this.DelayedClientCall$4$ar$this$0;
                    logger.logp(level, "io.grpc.internal.ManagedChannelImpl$NameResolverListener", "handleErrorInSyncContext", "[{0}] Failed to resolve name. status={1}", new Object[]{internalLogId, obj8});
                    ManagedChannelImpl.RealChannel realChannel = ManagedChannelImpl.this.realChannel;
                    if (realChannel.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        realChannel.updateConfigSelector(null);
                    }
                    if (ManagedChannelImpl.this.lastResolutionState$ar$edu != 3) {
                        ManagedChannelImpl.this.channelLogger.log$ar$edu$7fdc135b_0(3, "Failed to resolve name: {0}", obj8);
                        ManagedChannelImpl.this.lastResolutionState$ar$edu = 3;
                    }
                    ManagedChannelImpl.LbHelperImpl lbHelperImpl = nameResolverListener.helper;
                    if (lbHelperImpl != ManagedChannelImpl.this.lbHelper) {
                        return;
                    }
                    lbHelperImpl.lb.delegate.handleNameResolutionError((Status) obj8);
                    return;
                case 17:
                    if (((ManagedChannelImpl.RealChannel) this.val$message).configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
                        if (managedChannelImpl2.pendingCalls == null) {
                            managedChannelImpl2.pendingCalls = new LinkedHashSet();
                            ManagedChannelImpl managedChannelImpl3 = ManagedChannelImpl.this;
                            managedChannelImpl3.inUseStateAggregator.updateObjectInUse(managedChannelImpl3.pendingCallsInUseObject, true);
                        }
                        ManagedChannelImpl.this.pendingCalls.add(this.DelayedClientCall$4$ar$this$0);
                        return;
                    }
                    ((ManagedChannelImpl.RealChannel.PendingCall) this.DelayedClientCall$4$ar$this$0).reprocess();
                    return;
                case 18:
                    this.val$message.run();
                    Object obj9 = this.DelayedClientCall$4$ar$this$0;
                    ManagedChannelImpl.this.syncContext.execute(new InternalSubchannel$4$1(obj9, 13));
                    return;
                case 19:
                    synchronized (((RetriableStream) ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).lock) {
                        Object obj10 = this.DelayedClientCall$4$ar$this$0;
                        if (((RetriableStream.FutureCanceller) ((BaseLifecycleHelper.ConnectionFailedResolver) obj10).BaseLifecycleHelper$ConnectionFailedResolver$ar$clientConnectionResult).cancelled) {
                            z2 = true;
                        } else {
                            Object obj11 = ((BaseLifecycleHelper.ConnectionFailedResolver) obj10).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                            ((RetriableStream) obj11).state = ((RetriableStream) obj11).state.addActiveHedge((RetriableStream.Substream) this.val$message);
                            Object obj12 = ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                            if (((RetriableStream) obj12).hasPotentialHedging(((RetriableStream) obj12).state) && ((throttle = ((RetriableStream) ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).throttle) == null || throttle.isAboveThreshold())) {
                                Object obj13 = ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                                futureCanceller = new RetriableStream.FutureCanceller(((RetriableStream) obj13).lock);
                                ((RetriableStream) obj13).scheduledHedging = futureCanceller;
                            } else {
                                Object obj14 = ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                                ((RetriableStream) obj14).state = ((RetriableStream) obj14).state.freezeHedging();
                                ((RetriableStream) ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).scheduledHedging = null;
                            }
                        }
                    }
                    if (z2) {
                        Object obj15 = this.val$message;
                        ((RetriableStream.Substream) obj15).stream.start(new RetriableStream.Sublistener((RetriableStream.Substream) obj15));
                        ((RetriableStream.Substream) this.val$message).stream.cancel(Status.CANCELLED.withDescription("Unneeded hedging"));
                        return;
                    } else {
                        if (futureCanceller != null) {
                            Object obj16 = ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0;
                            BaseLifecycleHelper.ConnectionFailedResolver connectionFailedResolver = new BaseLifecycleHelper.ConnectionFailedResolver(obj16, futureCanceller, 2);
                            RetriableStream retriableStream = (RetriableStream) obj16;
                            futureCanceller.setFuture(retriableStream.scheduledExecutorService.schedule(connectionFailedResolver, retriableStream.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                        }
                        ((RetriableStream) ((BaseLifecycleHelper.ConnectionFailedResolver) this.DelayedClientCall$4$ar$this$0).BaseLifecycleHelper$ConnectionFailedResolver$ar$this$0).drain((RetriableStream.Substream) this.val$message);
                        return;
                    }
                default:
                    RetriableStream.this.masterListener.headersRead((Metadata) this.DelayedClientCall$4$ar$this$0);
                    return;
            }
        }

        public AnonymousClass4(Object obj, Object obj2, int i) {
            this.switching_field = i;
            this.val$message = obj2;
            this.DelayedClientCall$4$ar$this$0 = obj;
        }

        public AnonymousClass4(Object obj, Object obj2, int i, byte[] bArr) {
            this.switching_field = i;
            this.DelayedClientCall$4$ar$this$0 = obj2;
            this.val$message = obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CloseListenerRunnable extends ContextRunnable {
        final OnDeviceFaceMeshCreateLogEvent listener$ar$class_merging$a40ae667_0$ar$class_merging;
        final Status status;

        public CloseListenerRunnable(DelayedClientCall delayedClientCall, OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Status status) {
            super(delayedClientCall.context);
            this.listener$ar$class_merging$a40ae667_0$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
            this.status = status;
        }

        @Override // io.grpc.internal.ContextRunnable
        public final void runInContext() {
            this.listener$ar$class_merging$a40ae667_0$ar$class_merging.onClose(this.status, new Metadata());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DelayedListener extends OnDeviceFaceMeshCreateLogEvent {
        public volatile boolean passThrough;
        public List pendingCallbacks = new ArrayList();
        public final OnDeviceFaceMeshCreateLogEvent realListener$ar$class_merging$ar$class_merging;

        public DelayedListener(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent) {
            this.realListener$ar$class_merging$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
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

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onClose(Status status, Metadata metadata) {
            delayOrExecute(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, status, metadata, 16, null));
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onHeaders(Metadata metadata) {
            if (this.passThrough) {
                this.realListener$ar$class_merging$ar$class_merging.onHeaders(metadata);
            } else {
                delayOrExecute(new AnonymousClass4(this, metadata, 2, null));
            }
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onMessage(Object obj) {
            if (this.passThrough) {
                this.realListener$ar$class_merging$ar$class_merging.onMessage(obj);
            } else {
                delayOrExecute(new AnonymousClass4(this, obj, 3));
            }
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onReady() {
            if (this.passThrough) {
                this.realListener$ar$class_merging$ar$class_merging.onReady();
            } else {
                delayOrExecute(new Futures$$ExternalSyntheticLambda1(this, 11, null));
            }
        }
    }

    static {
        Logger.getLogger(DelayedClientCall.class.getName());
        NOOP_CALL = new ClientCall() { // from class: io.grpc.internal.DelayedClientCall.8
            @Override // io.grpc.ClientCall
            public final void halfClose() {
            }

            @Override // io.grpc.ClientCall
            public final void request(int i) {
            }

            @Override // io.grpc.ClientCall
            public final void sendMessage(Object obj) {
            }

            @Override // io.grpc.ClientCall
            public final void cancel(String str, Throwable th) {
            }

            @Override // io.grpc.ClientCall
            public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DelayedClientCall(Executor executor, ScheduledExecutorService scheduledExecutorService, Deadline deadline) {
        ScheduledFuture<?> schedule;
        executor.getClass();
        this.callExecutor = executor;
        scheduledExecutorService.getClass();
        Context current = Context.current();
        this.context = current;
        current.getDeadline$ar$ds();
        if (deadline == null) {
            schedule = null;
        } else {
            long timeRemaining = deadline.timeRemaining(TimeUnit.NANOSECONDS);
            long abs = Math.abs(timeRemaining) / TimeUnit.SECONDS.toNanos(1L);
            long abs2 = Math.abs(timeRemaining) % TimeUnit.SECONDS.toNanos(1L);
            StringBuilder sb = new StringBuilder();
            if (timeRemaining < 0) {
                sb.append("ClientCall started after CallOptions deadline was exceeded. Deadline has been exceeded for ");
            } else {
                sb.append("Deadline CallOptions will be exceeded in ");
            }
            sb.append(abs);
            sb.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
            sb.append("s. ");
            schedule = scheduledExecutorService.schedule(new EventBus$$ExternalSyntheticLambda0(this, sb, 20), timeRemaining, TimeUnit.NANOSECONDS);
        }
        this.initialDeadlineMonitor = schedule;
    }

    private final void delayOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingRunnables.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    public final void cancel(Status status, boolean z) {
        boolean z2;
        OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent;
        synchronized (this) {
            if (this.realCall == null) {
                setRealCall(NOOP_CALL);
                onDeviceFaceMeshCreateLogEvent = this.listener$ar$class_merging$a40ae667_0$ar$class_merging;
                this.error = status;
                z2 = false;
            } else {
                if (z) {
                    return;
                }
                z2 = true;
                onDeviceFaceMeshCreateLogEvent = null;
            }
            if (z2) {
                delayOrExecute(new AnonymousClass4(this, status, 1, null));
            } else {
                if (onDeviceFaceMeshCreateLogEvent != null) {
                    this.callExecutor.execute(new CloseListenerRunnable(this, onDeviceFaceMeshCreateLogEvent, status));
                }
                drainPendingCalls();
            }
            callCancelled();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0031, code lost:
    
        if (r0.hasNext() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
    
        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0029, code lost:
    
        r0 = r1.iterator();
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void drainPendingCalls() {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L5:
            monitor-enter(r3)
            java.util.List r1 = r3.pendingRunnables     // Catch: java.lang.Throwable -> L42
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L24
            r0 = 0
            r3.pendingRunnables = r0     // Catch: java.lang.Throwable -> L42
            r0 = 1
            r3.passThrough = r0     // Catch: java.lang.Throwable -> L42
            io.grpc.internal.DelayedClientCall$DelayedListener r0 = r3.delayedListener     // Catch: java.lang.Throwable -> L42
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L23
            java.util.concurrent.Executor r1 = r3.callExecutor
            io.grpc.internal.DelayedClientCall$1DrainListenerRunnable r2 = new io.grpc.internal.DelayedClientCall$1DrainListenerRunnable
            r2.<init>(r3)
            r1.execute(r2)
        L23:
            return
        L24:
            java.util.List r1 = r3.pendingRunnables     // Catch: java.lang.Throwable -> L42
            r3.pendingRunnables = r0     // Catch: java.lang.Throwable -> L42
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            java.util.Iterator r0 = r1.iterator()
        L2d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L3d
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L2d
        L3d:
            r1.clear()
            r0 = r1
            goto L5
        L42:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientCall.drainPendingCalls():void");
    }

    @Override // io.grpc.ClientCall
    public final void halfClose() {
        delayOrExecute(new Futures$$ExternalSyntheticLambda1(this, 10, null));
    }

    @Override // io.grpc.ClientCall
    public final void request(int i) {
        if (this.passThrough) {
            this.realCall.request(i);
        } else {
            delayOrExecute(new RatingView$$ExternalSyntheticLambda5(this, i, 7, (byte[]) null));
        }
    }

    @Override // io.grpc.ClientCall
    public final void sendMessage(Object obj) {
        if (this.passThrough) {
            this.realCall.sendMessage(obj);
        } else {
            delayOrExecute(new AnonymousClass4(this, obj, 0));
        }
    }

    public final void setRealCall(ClientCall clientCall) {
        boolean z;
        ClientCall clientCall2 = this.realCall;
        if (clientCall2 == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "realCall already set to %s", clientCall2);
        ScheduledFuture scheduledFuture = this.initialDeadlineMonitor;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.realCall = clientCall;
    }

    @Override // io.grpc.ClientCall
    public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
        boolean z;
        Status status;
        boolean z2;
        if (this.listener$ar$class_merging$a40ae667_0$ar$class_merging == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "already started");
        synchronized (this) {
            onDeviceFaceMeshCreateLogEvent.getClass();
            this.listener$ar$class_merging$a40ae667_0$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
            status = this.error;
            z2 = this.passThrough;
            if (!z2) {
                DelayedListener delayedListener = new DelayedListener(onDeviceFaceMeshCreateLogEvent);
                this.delayedListener = delayedListener;
                onDeviceFaceMeshCreateLogEvent = delayedListener;
            }
        }
        if (status != null) {
            this.callExecutor.execute(new CloseListenerRunnable(this, onDeviceFaceMeshCreateLogEvent, status));
        } else if (z2) {
            this.realCall.start$ar$class_merging$ar$class_merging(onDeviceFaceMeshCreateLogEvent, metadata);
        } else {
            delayOrExecute(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, onDeviceFaceMeshCreateLogEvent, metadata, 15));
        }
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("realCall", this.realCall);
        return stringHelper.toString();
    }

    @Override // io.grpc.ClientCall
    public final void cancel(String str, Throwable th) {
        Status withDescription;
        Status status = Status.CANCELLED;
        if (str != null) {
            withDescription = status.withDescription(str);
        } else {
            withDescription = status.withDescription("Call cancelled without message");
        }
        if (th != null) {
            withDescription = withDescription.withCause(th);
        }
        cancel(withDescription, false);
    }

    protected void callCancelled() {
    }
}
