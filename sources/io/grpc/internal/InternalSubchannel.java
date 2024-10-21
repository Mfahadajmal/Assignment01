package io.grpc.internal;

import android.graphics.Bitmap;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceImageLabelDetectionLogEvent;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ChannelLogger;
import io.grpc.ClientStreamTracer;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.util.MultiChildLoadBalancer;
import j$.util.DesugarCollections;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalSubchannel implements InternalInstrumented {
    public volatile ManagedClientTransport activeTransport;
    public volatile List addressGroups;
    public final Index addressIndex;
    private final String authority;
    private final OnDeviceImageLabelDetectionLogEvent backoffPolicyProvider$ar$class_merging$ar$class_merging$ar$class_merging;
    public final OnDeviceImageLabelDetectionLogEvent callback$ar$class_merging$a099e745_0;
    private final CallTracer callsTracer;
    public final ChannelLogger channelLogger;
    public final InternalChannelz channelz;
    public final Stopwatch connectingTimer;
    private final InternalLogId logId;
    public ConnectionClientTransport pendingTransport;
    public ExponentialBackoffPolicy reconnectPolicy$ar$class_merging;
    public MultiChildLoadBalancer.AcceptResolvedAddrRetVal reconnectTask$ar$class_merging$ar$class_merging;
    public final ScheduledExecutorService scheduledExecutor;
    public MultiChildLoadBalancer.AcceptResolvedAddrRetVal shutdownDueToUpdateTask$ar$class_merging$ar$class_merging;
    public ManagedClientTransport shutdownDueToUpdateTransport;
    public Status shutdownReason;
    public final SynchronizationContext syncContext;
    private final ClientTransportFactory transportFactory;
    public final List transportFilters;
    private final String userAgent;
    public final Collection transports = new ArrayList();
    public final InUseStateAggregator inUseStateAggregator = new InUseStateAggregator() { // from class: io.grpc.internal.InternalSubchannel.1
        public AnonymousClass1() {
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected final void handleInUse() {
            InternalSubchannel internalSubchannel = InternalSubchannel.this;
            ((ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback) internalSubchannel.callback$ar$class_merging$a099e745_0).this$1$ar$class_merging.this$0.inUseStateAggregator.updateObjectInUse(internalSubchannel, true);
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected final void handleNotInUse() {
            InternalSubchannel internalSubchannel = InternalSubchannel.this;
            ((ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback) internalSubchannel.callback$ar$class_merging$a099e745_0).this$1$ar$class_merging.this$0.inUseStateAggregator.updateObjectInUse(internalSubchannel, false);
        }
    };
    public volatile ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: io.grpc.internal.InternalSubchannel$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends InUseStateAggregator {
        public AnonymousClass1() {
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected final void handleInUse() {
            InternalSubchannel internalSubchannel = InternalSubchannel.this;
            ((ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback) internalSubchannel.callback$ar$class_merging$a099e745_0).this$1$ar$class_merging.this$0.inUseStateAggregator.updateObjectInUse(internalSubchannel, true);
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected final void handleNotInUse() {
            InternalSubchannel internalSubchannel = InternalSubchannel.this;
            ((ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback) internalSubchannel.callback$ar$class_merging$a099e745_0).this$1$ar$class_merging.this$0.inUseStateAggregator.updateObjectInUse(internalSubchannel, false);
        }
    }

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.InternalSubchannel$7 */
    /* loaded from: classes.dex */
    public final class AnonymousClass7 implements Runnable {
        final /* synthetic */ Object InternalSubchannel$7$ar$this$0;
        final /* synthetic */ Object InternalSubchannel$7$ar$val$transport;
        private final /* synthetic */ int switching_field;
        final /* synthetic */ boolean val$inUse;

        public /* synthetic */ AnonymousClass7(ScreenCaptureController.ScreenCaptureImageProcessor screenCaptureImageProcessor, Bitmap bitmap, boolean z, int i) {
            this.switching_field = i;
            this.InternalSubchannel$7$ar$this$0 = screenCaptureImageProcessor;
            this.InternalSubchannel$7$ar$val$transport = bitmap;
            this.val$inUse = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.switching_field != 0) {
                ((ScreenCaptureController.ScreenCaptureImageProcessor) this.InternalSubchannel$7$ar$this$0).listener.onScreenCaptureFinished((Bitmap) this.InternalSubchannel$7$ar$val$transport, this.val$inUse);
            } else {
                boolean z = this.val$inUse;
                ((InternalSubchannel) this.InternalSubchannel$7$ar$this$0).inUseStateAggregator.updateObjectInUse(this.InternalSubchannel$7$ar$val$transport, z);
            }
        }

        public AnonymousClass7(InternalSubchannel internalSubchannel, ConnectionClientTransport connectionClientTransport, boolean z, int i) {
            this.switching_field = i;
            this.InternalSubchannel$7$ar$val$transport = connectionClientTransport;
            this.val$inUse = z;
            this.InternalSubchannel$7$ar$this$0 = internalSubchannel;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CallTracingTransport extends ForwardingConnectionClientTransport {
        public final CallTracer callTracer;
        private final ConnectionClientTransport delegate;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* renamed from: io.grpc.internal.InternalSubchannel$CallTracingTransport$1 */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends ForwardingClientStream {
            final /* synthetic */ CallTracingTransport this$0;
            final /* synthetic */ ClientStream val$streamDelegate;

            /* compiled from: PG */
            /* renamed from: io.grpc.internal.InternalSubchannel$CallTracingTransport$1$1 */
            /* loaded from: classes.dex */
            final class C00061 extends ForwardingClientStreamListener {
                final /* synthetic */ AnonymousClass1 this$1;
                final /* synthetic */ ClientStreamListener val$listener;

                public C00061(AnonymousClass1 anonymousClass1, ClientStreamListener clientStreamListener) {
                    r2 = clientStreamListener;
                    this.this$1 = anonymousClass1;
                }

                @Override // io.grpc.internal.ForwardingClientStreamListener, io.grpc.internal.ClientStreamListener
                public final void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
                    this.this$1.this$0.callTracer.reportCallEnded(status.isOk());
                    delegate().closed(status, rpcProgress, metadata);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.grpc.internal.ForwardingClientStreamListener
                public final ClientStreamListener delegate() {
                    return r2;
                }
            }

            public AnonymousClass1(CallTracingTransport callTracingTransport, ClientStream clientStream) {
                r2 = clientStream;
                this.this$0 = callTracingTransport;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.internal.ForwardingClientStream
            public final ClientStream delegate() {
                return r2;
            }

            @Override // io.grpc.internal.ForwardingClientStream, io.grpc.internal.ClientStream
            public final void start(ClientStreamListener clientStreamListener) {
                this.this$0.callTracer.reportCallStarted();
                delegate().start(new ForwardingClientStreamListener(this) { // from class: io.grpc.internal.InternalSubchannel.CallTracingTransport.1.1
                    final /* synthetic */ AnonymousClass1 this$1;
                    final /* synthetic */ ClientStreamListener val$listener;

                    public C00061(AnonymousClass1 this, ClientStreamListener clientStreamListener2) {
                        r2 = clientStreamListener2;
                        this.this$1 = this;
                    }

                    @Override // io.grpc.internal.ForwardingClientStreamListener, io.grpc.internal.ClientStreamListener
                    public final void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
                        this.this$1.this$0.callTracer.reportCallEnded(status.isOk());
                        delegate().closed(status, rpcProgress, metadata);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // io.grpc.internal.ForwardingClientStreamListener
                    public final ClientStreamListener delegate() {
                        return r2;
                    }
                });
            }
        }

        public CallTracingTransport(ConnectionClientTransport connectionClientTransport, CallTracer callTracer) {
            this.delegate = connectionClientTransport;
            this.callTracer = callTracer;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.internal.ForwardingConnectionClientTransport
        public final ConnectionClientTransport delegate() {
            return this.delegate;
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport, io.grpc.internal.ClientTransport
        public final ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
            return new ForwardingClientStream(this) { // from class: io.grpc.internal.InternalSubchannel.CallTracingTransport.1
                final /* synthetic */ CallTracingTransport this$0;
                final /* synthetic */ ClientStream val$streamDelegate;

                /* compiled from: PG */
                /* renamed from: io.grpc.internal.InternalSubchannel$CallTracingTransport$1$1 */
                /* loaded from: classes.dex */
                final class C00061 extends ForwardingClientStreamListener {
                    final /* synthetic */ AnonymousClass1 this$1;
                    final /* synthetic */ ClientStreamListener val$listener;

                    public C00061(AnonymousClass1 this, ClientStreamListener clientStreamListener2) {
                        r2 = clientStreamListener2;
                        this.this$1 = this;
                    }

                    @Override // io.grpc.internal.ForwardingClientStreamListener, io.grpc.internal.ClientStreamListener
                    public final void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
                        this.this$1.this$0.callTracer.reportCallEnded(status.isOk());
                        delegate().closed(status, rpcProgress, metadata);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // io.grpc.internal.ForwardingClientStreamListener
                    public final ClientStreamListener delegate() {
                        return r2;
                    }
                }

                public AnonymousClass1(CallTracingTransport this, ClientStream clientStream) {
                    r2 = clientStream;
                    this.this$0 = this;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.grpc.internal.ForwardingClientStream
                public final ClientStream delegate() {
                    return r2;
                }

                @Override // io.grpc.internal.ForwardingClientStream, io.grpc.internal.ClientStream
                public final void start(ClientStreamListener clientStreamListener2) {
                    this.this$0.callTracer.reportCallStarted();
                    delegate().start(new ForwardingClientStreamListener(this) { // from class: io.grpc.internal.InternalSubchannel.CallTracingTransport.1.1
                        final /* synthetic */ AnonymousClass1 this$1;
                        final /* synthetic */ ClientStreamListener val$listener;

                        public C00061(AnonymousClass1 this, ClientStreamListener clientStreamListener22) {
                            r2 = clientStreamListener22;
                            this.this$1 = this;
                        }

                        @Override // io.grpc.internal.ForwardingClientStreamListener, io.grpc.internal.ClientStreamListener
                        public final void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata2) {
                            this.this$1.this$0.callTracer.reportCallEnded(status.isOk());
                            delegate().closed(status, rpcProgress, metadata2);
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // io.grpc.internal.ForwardingClientStreamListener
                        public final ClientStreamListener delegate() {
                            return r2;
                        }
                    });
                }
            };
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Index {
        public Object InternalSubchannel$Index$ar$addressGroups;
        public int addressIndex;
        public int groupIndex;

        public Index() {
            this.addressIndex = -1;
            this.groupIndex = -1;
        }

        public final void clear() {
            if (this.InternalSubchannel$Index$ar$addressGroups != null) {
                this.InternalSubchannel$Index$ar$addressGroups = null;
            }
            this.addressIndex = -1;
            this.groupIndex = -1;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
        public final SocketAddress getCurrentAddress() {
            return (SocketAddress) ((EquivalentAddressGroup) this.InternalSubchannel$Index$ar$addressGroups.get(this.groupIndex)).addrs.get(this.addressIndex);
        }

        public final void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public Index(List list) {
            this.InternalSubchannel$Index$ar$addressGroups = list;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TransportListener implements ManagedClientTransport.Listener {
        boolean shutdownInitiated = false;
        final ConnectionClientTransport transport;

        public TransportListener(ConnectionClientTransport connectionClientTransport) {
            this.transport = connectionClientTransport;
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final Attributes filterTransport(Attributes attributes) {
            Iterator it = InternalSubchannel.this.transportFilters.iterator();
            if (!it.hasNext()) {
                return attributes;
            }
            throw null;
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportInUse(boolean z) {
            InternalSubchannel.this.handleTransportInUseState(this.transport, z);
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportReady() {
            InternalSubchannel.this.channelLogger.log$ar$edu(2, "READY");
            InternalSubchannel.this.syncContext.execute(new InternalSubchannel$4$1(this, 3));
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportShutdown(Status status) {
            InternalSubchannel.this.channelLogger.log$ar$edu$7fdc135b_0(2, "{0} SHUTDOWN with {1}", this.transport.getLogId(), InternalSubchannel.printShortStatus$ar$ds(status));
            this.shutdownInitiated = true;
            InternalSubchannel.this.syncContext.execute(new DelayedClientCall.AnonymousClass4(this, status, 15, null));
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportTerminated() {
            ContextDataProvider.checkState(this.shutdownInitiated, "transportShutdown() must be called before transportTerminated().");
            InternalSubchannel.this.channelLogger.log$ar$edu$7fdc135b_0(2, "{0} Terminated", this.transport.getLogId());
            InternalChannelz.remove(InternalSubchannel.this.channelz.otherSockets, this.transport);
            InternalSubchannel.this.handleTransportInUseState(this.transport, false);
            Iterator it = InternalSubchannel.this.transportFilters.iterator();
            if (!it.hasNext()) {
                InternalSubchannel.this.syncContext.execute(new InternalSubchannel$4$1(this, 4));
            } else {
                this.transport.getAttributes();
                throw null;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TransportLogger extends ChannelLogger {
        InternalLogId logId;

        @Override // io.grpc.ChannelLogger
        public final void log$ar$edu(int i, String str) {
            throw null;
        }

        @Override // io.grpc.ChannelLogger
        public final void log$ar$edu$7fdc135b_0(int i, String str, Object... objArr) {
            throw null;
        }
    }

    /* renamed from: -$$Nest$fputpendingTransport$ar$ds */
    public static /* bridge */ /* synthetic */ void m243$$Nest$fputpendingTransport$ar$ds(InternalSubchannel internalSubchannel) {
        internalSubchannel.pendingTransport = null;
    }

    public InternalSubchannel(List list, String str, String str2, OnDeviceImageLabelDetectionLogEvent onDeviceImageLabelDetectionLogEvent, ClientTransportFactory clientTransportFactory, ScheduledExecutorService scheduledExecutorService, Supplier supplier, SynchronizationContext synchronizationContext, OnDeviceImageLabelDetectionLogEvent onDeviceImageLabelDetectionLogEvent2, InternalChannelz internalChannelz, CallTracer callTracer, InternalLogId internalLogId, ChannelLogger channelLogger, List list2) {
        ContextDataProvider.checkArgument(!list.isEmpty(), (Object) "addressGroups is empty");
        checkListHasNoNulls$ar$ds(list);
        List unmodifiableList = DesugarCollections.unmodifiableList(new ArrayList(list));
        this.addressGroups = unmodifiableList;
        this.addressIndex = new Index(unmodifiableList);
        this.authority = str;
        this.userAgent = str2;
        this.backoffPolicyProvider$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceImageLabelDetectionLogEvent;
        this.transportFactory = clientTransportFactory;
        this.scheduledExecutor = scheduledExecutorService;
        this.connectingTimer = (Stopwatch) supplier.get();
        this.syncContext = synchronizationContext;
        this.callback$ar$class_merging$a099e745_0 = onDeviceImageLabelDetectionLogEvent2;
        this.channelz = internalChannelz;
        this.callsTracer = callTracer;
        this.logId = internalLogId;
        this.channelLogger = channelLogger;
        this.transportFilters = list2;
    }

    public static void checkListHasNoNulls$ar$ds(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
    }

    public static final String printShortStatus$ar$ds(Status status) {
        StringBuilder sb = new StringBuilder();
        sb.append(status.code);
        if (status.description != null) {
            sb.append("(");
            sb.append(status.description);
            sb.append(")");
        }
        if (status.cause != null) {
            sb.append("[");
            sb.append(status.cause);
            sb.append("]");
        }
        return sb.toString();
    }

    @Override // io.grpc.InternalWithLogId
    public final InternalLogId getLogId() {
        return this.logId;
    }

    public final void gotoNonErrorState(ConnectivityState connectivityState) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forNonError(connectivityState));
    }

    public final void gotoState(ConnectivityStateInfo connectivityStateInfo) {
        boolean z;
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.state.state != connectivityStateInfo.state) {
            if (this.state.state != ConnectivityState.SHUTDOWN) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "Cannot transition out of SHUTDOWN to ".concat(connectivityStateInfo.toString()));
            this.state = connectivityStateInfo;
            OnDeviceImageLabelDetectionLogEvent onDeviceImageLabelDetectionLogEvent = this.callback$ar$class_merging$a099e745_0;
            ContextDataProvider.checkState(true, "listener is null");
            ((ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback) onDeviceImageLabelDetectionLogEvent).val$listener.onSubchannelState(connectivityStateInfo);
        }
    }

    public final void handleTermination() {
        this.syncContext.execute(new InternalSubchannel$4$1(this, 2));
    }

    public final void handleTransportInUseState(ConnectionClientTransport connectionClientTransport, boolean z) {
        this.syncContext.execute(new AnonymousClass7(this, connectionClientTransport, z, 0));
    }

    public final ClientTransport obtainActiveTransport() {
        ManagedClientTransport managedClientTransport = this.activeTransport;
        if (managedClientTransport != null) {
            return managedClientTransport;
        }
        this.syncContext.execute(new InternalSubchannel$4$1(this, 1));
        return null;
    }

    public final void shutdown(Status status) {
        this.syncContext.execute(new DelayedClientCall.AnonymousClass4(this, status, 14, null));
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.List, java.lang.Object] */
    public final void startNewTransport() {
        boolean z;
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress;
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.reconnectTask$ar$class_merging$ar$class_merging == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Should have no reconnectTask scheduled");
        Index index = this.addressIndex;
        if (index.groupIndex == 0 && index.addressIndex == 0) {
            Stopwatch stopwatch = this.connectingTimer;
            stopwatch.reset$ar$ds();
            stopwatch.start$ar$ds$db96ddcc_0();
        }
        SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
        if (currentAddress instanceof HttpConnectProxiedSocketAddress) {
            HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress2 = (HttpConnectProxiedSocketAddress) currentAddress;
            httpConnectProxiedSocketAddress = httpConnectProxiedSocketAddress2;
            currentAddress = httpConnectProxiedSocketAddress2.targetAddress;
        } else {
            httpConnectProxiedSocketAddress = null;
        }
        Index index2 = this.addressIndex;
        Attributes attributes = ((EquivalentAddressGroup) index2.InternalSubchannel$Index$ar$addressGroups.get(index2.groupIndex)).attrs;
        String str = (String) attributes.get(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE);
        ClientTransportFactory.ClientTransportOptions clientTransportOptions = new ClientTransportFactory.ClientTransportOptions();
        if (str == null) {
            str = this.authority;
        }
        str.getClass();
        clientTransportOptions.authority = str;
        clientTransportOptions.eagAttributes = attributes;
        clientTransportOptions.userAgent = this.userAgent;
        clientTransportOptions.connectProxiedSocketAddr = httpConnectProxiedSocketAddress;
        TransportLogger transportLogger = new TransportLogger();
        transportLogger.logId = this.logId;
        CallTracingTransport callTracingTransport = new CallTracingTransport(this.transportFactory.newClientTransport(currentAddress, clientTransportOptions, transportLogger), this.callsTracer);
        transportLogger.logId = callTracingTransport.getLogId();
        InternalChannelz.add(this.channelz.otherSockets, callTracingTransport);
        this.pendingTransport = callTracingTransport;
        this.transports.add(callTracingTransport);
        Runnable start = callTracingTransport.start(new TransportListener(callTracingTransport));
        if (start != null) {
            this.syncContext.executeLater(start);
        }
        this.channelLogger.log$ar$edu$7fdc135b_0(2, "Started transport {0}", transportLogger.logId);
    }

    public final String toString() {
        MoreObjects$ToStringHelper add = ContextDataProvider.toStringHelper(this).add("logId", this.logId.id);
        add.addHolder$ar$ds("addressGroups", this.addressGroups);
        return add.toString();
    }
}
