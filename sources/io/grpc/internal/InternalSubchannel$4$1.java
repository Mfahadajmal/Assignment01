package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.ConnectivityState;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.internal.PickFirstLoadBalancer;
import io.grpc.internal.RetriableStream;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalSubchannel$4$1 implements Runnable {
    final /* synthetic */ Object InternalSubchannel$4$1$ar$this$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ InternalSubchannel$4$1(PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer, int i, byte[] bArr) {
        this.switching_field = i;
        this.InternalSubchannel$4$1$ar$this$1 = pickFirstLeafLoadBalancer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = true;
        switch (this.switching_field) {
            case 0:
                InternalSubchannel internalSubchannel = (InternalSubchannel) ((DelayedClientCall.AnonymousClass4) this.InternalSubchannel$4$1$ar$this$1).DelayedClientCall$4$ar$this$0;
                ManagedClientTransport managedClientTransport = internalSubchannel.shutdownDueToUpdateTransport;
                internalSubchannel.shutdownDueToUpdateTask$ar$class_merging$ar$class_merging = null;
                internalSubchannel.shutdownDueToUpdateTransport = null;
                managedClientTransport.shutdown(Status.UNAVAILABLE.withDescription("InternalSubchannel closed transport due to address change"));
                return;
            case 1:
                if (((InternalSubchannel) this.InternalSubchannel$4$1$ar$this$1).state.state == ConnectivityState.IDLE) {
                    ((InternalSubchannel) this.InternalSubchannel$4$1$ar$this$1).channelLogger.log$ar$edu(2, "CONNECTING as requested");
                    ((InternalSubchannel) this.InternalSubchannel$4$1$ar$this$1).gotoNonErrorState(ConnectivityState.CONNECTING);
                    ((InternalSubchannel) this.InternalSubchannel$4$1$ar$this$1).startNewTransport();
                    return;
                }
                return;
            case 2:
                ((InternalSubchannel) this.InternalSubchannel$4$1$ar$this$1).channelLogger.log$ar$edu(2, "Terminated");
                InternalSubchannel internalSubchannel2 = (InternalSubchannel) this.InternalSubchannel$4$1$ar$this$1;
                internalSubchannel2.callback$ar$class_merging$a099e745_0.onTerminated(internalSubchannel2);
                return;
            case 3:
                InternalSubchannel.TransportListener transportListener = (InternalSubchannel.TransportListener) this.InternalSubchannel$4$1$ar$this$1;
                InternalSubchannel internalSubchannel3 = InternalSubchannel.this;
                internalSubchannel3.reconnectPolicy$ar$class_merging = null;
                if (internalSubchannel3.shutdownReason != null) {
                    if (internalSubchannel3.activeTransport != null) {
                        z = false;
                    }
                    ContextDataProvider.checkState(z, "Unexpected non-null activeTransport");
                    InternalSubchannel.TransportListener transportListener2 = (InternalSubchannel.TransportListener) this.InternalSubchannel$4$1$ar$this$1;
                    transportListener2.transport.shutdown(InternalSubchannel.this.shutdownReason);
                    return;
                }
                ConnectionClientTransport connectionClientTransport = internalSubchannel3.pendingTransport;
                ConnectionClientTransport connectionClientTransport2 = transportListener.transport;
                if (connectionClientTransport == connectionClientTransport2) {
                    internalSubchannel3.activeTransport = connectionClientTransport2;
                    InternalSubchannel.this.pendingTransport = null;
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.READY);
                    return;
                }
                return;
            case 4:
                InternalSubchannel.TransportListener transportListener3 = (InternalSubchannel.TransportListener) this.InternalSubchannel$4$1$ar$this$1;
                InternalSubchannel.this.transports.remove(transportListener3.transport);
                if (InternalSubchannel.this.state.state == ConnectivityState.SHUTDOWN && InternalSubchannel.this.transports.isEmpty()) {
                    InternalSubchannel.this.handleTermination();
                    return;
                }
                return;
            case 5:
                synchronized (this.InternalSubchannel$4$1$ar$this$1) {
                    if (((KeepAliveManager) this.InternalSubchannel$4$1$ar$this$1).state$ar$edu$75a4b03c_0 != 6) {
                        ((KeepAliveManager) this.InternalSubchannel$4$1$ar$this$1).state$ar$edu$75a4b03c_0 = 6;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    ((KeepAliveManager) this.InternalSubchannel$4$1$ar$this$1).keepAlivePinger$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPingTimeout();
                    return;
                }
                return;
            case 6:
                synchronized (this.InternalSubchannel$4$1$ar$this$1) {
                    Object obj = this.InternalSubchannel$4$1$ar$this$1;
                    ((KeepAliveManager) obj).pingFuture = null;
                    if (((KeepAliveManager) obj).state$ar$edu$75a4b03c_0 == 2) {
                        Object obj2 = this.InternalSubchannel$4$1$ar$this$1;
                        ((KeepAliveManager) obj2).state$ar$edu$75a4b03c_0 = 4;
                        ((KeepAliveManager) obj2).shutdownFuture = ((KeepAliveManager) obj2).scheduler.schedule(((KeepAliveManager) obj2).shutdown, ((KeepAliveManager) obj2).keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
                    } else {
                        Object obj3 = this.InternalSubchannel$4$1$ar$this$1;
                        if (((KeepAliveManager) obj3).state$ar$edu$75a4b03c_0 == 3) {
                            ((KeepAliveManager) obj3).pingFuture = ((KeepAliveManager) obj3).scheduler.schedule(((KeepAliveManager) obj3).sendPing, ((KeepAliveManager) obj3).keepAliveTimeInNanos - ((KeepAliveManager) obj3).stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
                            ((KeepAliveManager) this.InternalSubchannel$4$1$ar$this$1).state$ar$edu$75a4b03c_0 = 2;
                        }
                        z = false;
                    }
                }
                if (z) {
                    ((KeepAliveManager) this.InternalSubchannel$4$1$ar$this$1).keepAlivePinger$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ping();
                    return;
                }
                return;
            case 7:
                ((ManagedChannelImpl) this.InternalSubchannel$4$1$ar$this$1).cancelIdleTimer(true);
                return;
            case 8:
                ((ManagedChannelImpl) this.InternalSubchannel$4$1$ar$this$1).channelLogger.log$ar$edu(2, "Entering SHUTDOWN state");
                ((ManagedChannelImpl) this.InternalSubchannel$4$1$ar$this$1).channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
                return;
            case 9:
                ManagedChannelImpl managedChannelImpl = (ManagedChannelImpl) this.InternalSubchannel$4$1$ar$this$1;
                if (managedChannelImpl.lbHelper != null) {
                    managedChannelImpl.shutdownNameResolverAndLoadBalancer(true);
                    managedChannelImpl.delayedTransport.reprocess(null);
                    managedChannelImpl.channelLogger.log$ar$edu(2, "Entering IDLE state");
                    managedChannelImpl.channelStateManager.gotoState(ConnectivityState.IDLE);
                    InUseStateAggregator inUseStateAggregator = managedChannelImpl.inUseStateAggregator;
                    Object[] objArr = {managedChannelImpl.pendingCallsInUseObject, managedChannelImpl.delayedTransport};
                    for (int i = 0; i < 2; i++) {
                        if (inUseStateAggregator.inUseObjects.contains(objArr[i])) {
                            managedChannelImpl.exitIdleMode();
                            return;
                        }
                    }
                    return;
                }
                return;
            case 10:
                ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
                managedChannelImpl2.syncContext.throwIfNotInThisSynchronizationContext();
                if (managedChannelImpl2.nameResolverStarted) {
                    managedChannelImpl2.nameResolver.refresh();
                    return;
                }
                return;
            case 11:
                ManagedChannelImpl.RealChannel realChannel = (ManagedChannelImpl.RealChannel) this.InternalSubchannel$4$1$ar$this$1;
                if (ManagedChannelImpl.this.pendingCalls == null) {
                    if (realChannel.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        ((ManagedChannelImpl.RealChannel) this.InternalSubchannel$4$1$ar$this$1).configSelector.set(null);
                    }
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    return;
                }
                return;
            case 12:
                ManagedChannelImpl.this.exitIdleMode();
                return;
            case 13:
                Object obj4 = this.InternalSubchannel$4$1$ar$this$1;
                Collection collection = ManagedChannelImpl.this.pendingCalls;
                if (collection != null) {
                    collection.remove(obj4);
                    if (ManagedChannelImpl.this.pendingCalls.isEmpty()) {
                        ManagedChannelImpl managedChannelImpl3 = ManagedChannelImpl.this;
                        managedChannelImpl3.inUseStateAggregator.updateObjectInUse(managedChannelImpl3.pendingCallsInUseObject, false);
                        ManagedChannelImpl managedChannelImpl4 = ManagedChannelImpl.this;
                        managedChannelImpl4.pendingCalls = null;
                        if (managedChannelImpl4.shutdown.get()) {
                            ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case 14:
                ((AbstractSubchannel) this.InternalSubchannel$4$1$ar$this$1).subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
                return;
            case 15:
                PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer = (PickFirstLeafLoadBalancer) this.InternalSubchannel$4$1$ar$this$1;
                pickFirstLeafLoadBalancer.scheduleConnectionTask$ar$class_merging$ar$class_merging = null;
                if (pickFirstLeafLoadBalancer.addressIndex.increment()) {
                    ((LoadBalancer) this.InternalSubchannel$4$1$ar$this$1).requestConnection();
                    return;
                }
                return;
            case 16:
                ((LoadBalancer) this.InternalSubchannel$4$1$ar$this$1).requestConnection();
                return;
            case 17:
                ((PickFirstLoadBalancer.RequestConnectionPicker) this.InternalSubchannel$4$1$ar$this$1).subchannel.requestConnection();
                return;
            case 18:
                Rescheduler rescheduler = (Rescheduler) this.InternalSubchannel$4$1$ar$this$1;
                if (!rescheduler.enabled) {
                    rescheduler.wakeUp = null;
                    return;
                }
                long nanoTime = rescheduler.nanoTime();
                Object obj5 = this.InternalSubchannel$4$1$ar$this$1;
                Rescheduler rescheduler2 = (Rescheduler) obj5;
                if (rescheduler2.runAtNanos - nanoTime > 0) {
                    rescheduler2.wakeUp = rescheduler2.scheduler.schedule(new InternalSubchannel$4$1(obj5, 19), ((Rescheduler) this.InternalSubchannel$4$1$ar$this$1).runAtNanos - nanoTime, TimeUnit.NANOSECONDS);
                    return;
                }
                rescheduler2.enabled = false;
                rescheduler2.wakeUp = null;
                rescheduler2.runnable.run();
                return;
            case 19:
                Object obj6 = this.InternalSubchannel$4$1$ar$this$1;
                ((Rescheduler) obj6).serializingExecutor.execute(new InternalSubchannel$4$1(obj6, 18));
                return;
            default:
                ((RetriableStream.C1CommitTask) this.InternalSubchannel$4$1$ar$this$1).this$0.isClosed = true;
                RetriableStream retriableStream = ((RetriableStream.C1CommitTask) this.InternalSubchannel$4$1$ar$this$1).this$0;
                ClientStreamListener clientStreamListener = retriableStream.masterListener;
                OptionalMethod optionalMethod = retriableStream.savedCloseMasterListenerReason$ar$class_merging;
                clientStreamListener.closed((Status) optionalMethod.OptionalMethod$ar$methodName, (ClientStreamListener.RpcProgress) optionalMethod.OptionalMethod$ar$methodParams, (Metadata) optionalMethod.OptionalMethod$ar$returnType);
                return;
        }
    }

    public InternalSubchannel$4$1(Object obj, int i) {
        this.switching_field = i;
        this.InternalSubchannel$4$1$ar$this$1 = obj;
    }
}
