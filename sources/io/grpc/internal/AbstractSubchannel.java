package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.InternalChannelz;
import io.grpc.InternalChannelz$ChannelTrace$Event;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.internal.DelayedClientCall;
import io.grpc.util.MultiChildLoadBalancer;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class AbstractSubchannel extends LoadBalancer.Subchannel {
    List addressGroups;
    final LoadBalancer.CreateSubchannelArgs args;
    MultiChildLoadBalancer.AcceptResolvedAddrRetVal delayedShutdownTask$ar$class_merging$ar$class_merging;
    boolean shutdown;
    boolean started;
    InternalSubchannel subchannel;
    final InternalLogId subchannelLogId;
    final ChannelLoggerImpl subchannelLogger;
    final ChannelTracer subchannelTracer;
    final /* synthetic */ ManagedChannelImpl this$0;

    public AbstractSubchannel() {
    }

    @Override // io.grpc.LoadBalancer.Subchannel
    public final List getAllAddresses() {
        this.this$0.syncContext.throwIfNotInThisSynchronizationContext();
        ContextDataProvider.checkState(this.started, "not started");
        return this.addressGroups;
    }

    @Override // io.grpc.LoadBalancer.Subchannel
    public final Object getInternalSubchannel() {
        ContextDataProvider.checkState(this.started, "Subchannel is not started");
        return this.subchannel;
    }

    @Override // io.grpc.LoadBalancer.Subchannel
    public final void requestConnection() {
        this.this$0.syncContext.throwIfNotInThisSynchronizationContext();
        ContextDataProvider.checkState(this.started, "not started");
        this.subchannel.obtainActiveTransport();
    }

    @Override // io.grpc.LoadBalancer.Subchannel
    public final void shutdown() {
        MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal;
        this.this$0.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.subchannel == null) {
            this.shutdown = true;
            return;
        }
        if (this.shutdown) {
            if (this.this$0.terminating && (acceptResolvedAddrRetVal = this.delayedShutdownTask$ar$class_merging$ar$class_merging) != null) {
                acceptResolvedAddrRetVal.cancel();
                this.delayedShutdownTask$ar$class_merging$ar$class_merging = null;
            } else {
                return;
            }
        } else {
            this.shutdown = true;
        }
        ManagedChannelImpl managedChannelImpl = this.this$0;
        if (!managedChannelImpl.terminating) {
            this.delayedShutdownTask$ar$class_merging$ar$class_merging = managedChannelImpl.syncContext.schedule$ar$class_merging$ar$class_merging(new LogExceptionRunnable(new InternalSubchannel$4$1(this, 14)), 5L, TimeUnit.SECONDS, this.this$0.transportFactory.getScheduledExecutorService());
        } else {
            this.subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
        }
    }

    @Override // io.grpc.LoadBalancer.Subchannel
    public final void start(LoadBalancer.SubchannelStateListener subchannelStateListener) {
        this.this$0.syncContext.throwIfNotInThisSynchronizationContext();
        ContextDataProvider.checkState(!this.started, "already started");
        ContextDataProvider.checkState(!this.shutdown, "already shutdown");
        ContextDataProvider.checkState(!this.this$0.terminating, "Channel is being terminated");
        this.started = true;
        String authority = this.this$0.authority();
        ManagedChannelImpl managedChannelImpl = this.this$0;
        ClientTransportFactory clientTransportFactory = managedChannelImpl.transportFactory;
        ScheduledExecutorService scheduledExecutorService = clientTransportFactory.getScheduledExecutorService();
        ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback managedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback = new ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback(this, subchannelStateListener);
        ManagedChannelImpl managedChannelImpl2 = this.this$0;
        InternalChannelz internalChannelz = managedChannelImpl2.channelz;
        CallTracer create = managedChannelImpl2.callTracerFactory.create();
        InternalLogId internalLogId = this.subchannelLogId;
        String str = managedChannelImpl.userAgent;
        ManagedChannelImpl managedChannelImpl3 = this.this$0;
        InternalSubchannel internalSubchannel = new InternalSubchannel(this.args.addrs, authority, str, managedChannelImpl.backoffPolicyProvider$ar$class_merging$ar$class_merging$ar$class_merging, clientTransportFactory, scheduledExecutorService, managedChannelImpl3.stopwatchSupplier, managedChannelImpl3.syncContext, managedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback, internalChannelz, create, internalLogId, this.subchannelLogger, this.this$0.transportFilters);
        InternalChannelz$ChannelTrace$Event.Builder builder = new InternalChannelz$ChannelTrace$Event.Builder();
        builder.InternalChannelz$ChannelTrace$Event$Builder$ar$description = "Child Subchannel started";
        builder.InternalChannelz$ChannelTrace$Event$Builder$ar$severity = InternalChannelz$ChannelTrace$Event.Severity.CT_INFO;
        builder.setTimestampNanos$ar$ds(this.this$0.timeProvider.currentTimeNanos());
        builder.InternalChannelz$ChannelTrace$Event$Builder$ar$subchannelRef = internalSubchannel;
        this.this$0.channelTracer.reportEvent(builder.m241build());
        this.subchannel = internalSubchannel;
        InternalChannelz.add(this.this$0.channelz.subchannels, internalSubchannel);
        this.this$0.subchannels.add(internalSubchannel);
    }

    public final String toString() {
        return this.subchannelLogId.toString();
    }

    @Override // io.grpc.LoadBalancer.Subchannel
    public final void updateAddresses(List list) {
        this.this$0.syncContext.throwIfNotInThisSynchronizationContext();
        this.addressGroups = list;
        InternalSubchannel internalSubchannel = this.subchannel;
        list.getClass();
        InternalSubchannel.checkListHasNoNulls$ar$ds(list);
        ContextDataProvider.checkArgument(!list.isEmpty(), (Object) "newAddressGroups is empty");
        internalSubchannel.syncContext.execute(new DelayedClientCall.AnonymousClass4(internalSubchannel, DesugarCollections.unmodifiableList(new ArrayList(list)), 13));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AbstractSubchannel(ManagedChannelImpl managedChannelImpl, LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        this();
        this.this$0 = managedChannelImpl;
        this.addressGroups = createSubchannelArgs.addrs;
        this.args = createSubchannelArgs;
        InternalLogId allocate = InternalLogId.allocate("Subchannel", managedChannelImpl.authority());
        this.subchannelLogId = allocate;
        ChannelTracer channelTracer = new ChannelTracer(allocate, managedChannelImpl.timeProvider.currentTimeNanos(), "Subchannel for ".concat(createSubchannelArgs.addrs.toString()));
        this.subchannelTracer = channelTracer;
        this.subchannelLogger = new ChannelLoggerImpl(channelTracer, managedChannelImpl.timeProvider);
    }
}
