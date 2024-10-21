package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.InputImageConstructionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLogEvent;
import com.google.mlkit.logging.schema.OnDeviceImageCaptioningLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceImageLabelDetectionLogEvent;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ChannelLogger;
import io.grpc.ClientCall;
import io.grpc.ClientStreamTracer;
import io.grpc.CompressorRegistry;
import io.grpc.ConnectivityState;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.ForwardingClientCall;
import io.grpc.InternalChannelz;
import io.grpc.InternalConfigSelector;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerRegistry;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.CallTracer;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.ManagedChannelServiceConfig;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.RetriableStream;
import io.grpc.util.MultiChildLoadBalancer;
import java.lang.Thread;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.chromium.base.JavaHandlerThread;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented {
    public static final ManagedChannelServiceConfig EMPTY_SERVICE_CONFIG;
    public static final InternalConfigSelector INITIAL_PENDING_SELECTOR;
    public static final ClientCall NOOP_CALL;
    public static final LoadBalancer.PickDetailsConsumer NOOP_PICK_DETAILS_CONSUMER;
    static final Status SHUTDOWN_STATUS;
    static final Status SUBCHANNEL_SHUTDOWN_STATUS;
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    public final OnDeviceImageLabelDetectionLogEvent backoffPolicyProvider$ar$class_merging$ar$class_merging$ar$class_merging;
    private final ExecutorHolder balancerRpcExecutorHolder;
    public final CallTracer.Factory callTracerFactory;
    public final long channelBufferLimit;
    public final RemoteModelManager channelBufferUsed$ar$class_merging$ar$class_merging;
    public final CallTracer channelCallTracer;
    public final ChannelLogger channelLogger;
    public final ConnectivityStateManager channelStateManager;
    public final ChannelTracer channelTracer;
    public final InternalChannelz channelz;
    private final CompressorRegistry compressorRegistry;
    public final DecompressorRegistry decompressorRegistry;
    public final ManagedChannelServiceConfig defaultServiceConfig;
    public final DelayedClientTransport delayedTransport;
    private final ManagedClientTransport.Listener delayedTransportListener;
    public final Executor executor;
    private final ObjectPool executorPool;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    final InUseStateAggregator inUseStateAggregator;
    private final Channel interceptorChannel;
    public int lastResolutionState$ar$edu;
    public ManagedChannelServiceConfig lastServiceConfig;
    public LbHelperImpl lbHelper;
    private final MultiChildLoadBalancer.AcceptResolvedAddrRetVal loadBalancerFactory$ar$class_merging;
    public final InternalLogId logId;
    public final boolean lookUpServiceConfig;
    public NameResolver nameResolver;
    private final NameResolver.Args nameResolverArgs;
    private final NameResolverProvider nameResolverProvider;
    public boolean nameResolverStarted;
    private final ExecutorHolder offloadExecutorHolder;
    private final Set oobChannels;
    public boolean panicMode;
    public Collection pendingCalls;
    public final Object pendingCallsInUseObject;
    public final long perRpcBufferLimit;
    public final RealChannel realChannel;
    public final boolean retryEnabled;
    public final RestrictedScheduledExecutor scheduledExecutor;
    public boolean serviceConfigUpdated;
    public final AtomicBoolean shutdown;
    public final Supplier stopwatchSupplier;
    private volatile LoadBalancer.SubchannelPicker subchannelPicker;
    public final Set subchannels;
    final SynchronizationContext syncContext;
    private final String target;
    private final URI targetUri;
    public volatile boolean terminated;
    private final CountDownLatch terminatedLatch;
    public boolean terminating;
    private final OnDeviceFaceMeshLogEvent ticker$ar$class_merging$ar$class_merging;
    public final TimeProvider timeProvider;
    public final ClientTransportFactory transportFactory;
    public final List transportFilters;
    public final ChannelStreamProvider transportProvider;
    public final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry;
    public final String userAgent;

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.ManagedChannelImpl$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements Thread.UncaughtExceptionHandler {
        final /* synthetic */ Object ManagedChannelImpl$3$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass3(Object obj, int i) {
            this.switching_field = i;
            this.ManagedChannelImpl$3$ar$this$0 = obj;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    ((JavaHandlerThread) this.ManagedChannelImpl$3$ar$this$0).mUnhandledException = th;
                    return;
                }
                _BOUNDARY.e("BrailleIme", "Uncaught exception", th);
                try {
                    try {
                        ((BrailleIme) this.ManagedChannelImpl$3$ar$this$0).deactivateIfNeeded$ar$ds();
                        if (((BrailleIme) this.ManagedChannelImpl$3$ar$this$0).isInputViewShown()) {
                            ((BrailleIme) this.ManagedChannelImpl$3$ar$this$0).switchToNextInputMethod();
                        }
                    } catch (RuntimeException unused) {
                        _BOUNDARY.e("BrailleIme", "Uncaught exception in handler", th);
                    }
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = ((BrailleIme) this.ManagedChannelImpl$3$ar$this$0).originalDefaultUncaughtExceptionHandler;
                    if (uncaughtExceptionHandler != null) {
                        uncaughtExceptionHandler.uncaughtException(thread, th);
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = ((BrailleIme) this.ManagedChannelImpl$3$ar$this$0).originalDefaultUncaughtExceptionHandler;
                    if (uncaughtExceptionHandler2 != null) {
                        uncaughtExceptionHandler2.uncaughtException(thread, th);
                    }
                    throw th2;
                }
            }
            InternalLogId internalLogId = ((ManagedChannelImpl) this.ManagedChannelImpl$3$ar$this$0).logId;
            ManagedChannelImpl.logger.logp(Level.SEVERE, "io.grpc.internal.ManagedChannelImpl$3", "uncaughtException", "[" + String.valueOf(internalLogId) + "] Uncaught exception in the SynchronizationContext. Panic!", th);
            ManagedChannelImpl managedChannelImpl = (ManagedChannelImpl) this.ManagedChannelImpl$3$ar$this$0;
            if (managedChannelImpl.panicMode) {
                return;
            }
            managedChannelImpl.panicMode = true;
            managedChannelImpl.cancelIdleTimer(true);
            managedChannelImpl.shutdownNameResolverAndLoadBalancer(false);
            managedChannelImpl.updateSubchannelPicker(new LoadBalancer.SubchannelPicker(th) { // from class: io.grpc.internal.ManagedChannelImpl.1PanicSubchannelPicker
                private final LoadBalancer.PickResult panicPickResult;
                final /* synthetic */ Throwable val$t;

                {
                    this.val$t = th;
                    Status withCause = Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(th);
                    LoadBalancer.PickResult pickResult = LoadBalancer.PickResult.NO_RESULT;
                    ContextDataProvider.checkArgument(!withCause.isOk(), (Object) "drop status shouldn't be OK");
                    this.panicPickResult = new LoadBalancer.PickResult(null, withCause, true);
                }

                @Override // io.grpc.LoadBalancer.SubchannelPicker
                public final LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                    return this.panicPickResult;
                }

                public final String toString() {
                    MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(C1PanicSubchannelPicker.class);
                    stringHelper.addHolder$ar$ds("panicPickResult", this.panicPickResult);
                    return stringHelper.toString();
                }
            });
            managedChannelImpl.realChannel.updateConfigSelector(null);
            managedChannelImpl.channelLogger.log$ar$edu(4, "PANIC! Entering TRANSIENT_FAILURE");
            managedChannelImpl.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ChannelStreamProvider {
        volatile RetriableStream.Throttle throttle;

        public ChannelStreamProvider() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ConfigSelectingClientCall extends ForwardingClientCall {
        private final Executor callExecutor;
        private CallOptions callOptions;
        private final Channel channel;
        private final InternalConfigSelector configSelector;
        public final Context context;
        private ClientCall delegate;
        private final MethodDescriptor method;

        public ConfigSelectingClientCall(InternalConfigSelector internalConfigSelector, Channel channel, Executor executor, MethodDescriptor methodDescriptor, CallOptions callOptions) {
            this.configSelector = internalConfigSelector;
            this.channel = channel;
            this.method = methodDescriptor;
            Executor executor2 = callOptions.executor;
            executor = executor2 != null ? executor2 : executor;
            this.callExecutor = executor;
            CallOptions.Builder builder = CallOptions.toBuilder(callOptions);
            builder.executor = executor;
            this.callOptions = new CallOptions(builder);
            this.context = Context.current();
        }

        @Override // io.grpc.PartialForwardingClientCall, io.grpc.ClientCall
        public final void cancel(String str, Throwable th) {
            ClientCall clientCall = this.delegate;
            if (clientCall != null) {
                clientCall.cancel(str, th);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall
        public final ClientCall delegate() {
            return this.delegate;
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
        public final void start$ar$class_merging$ar$class_merging(final OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
            new LoadBalancer.PickSubchannelArgs(this.method, metadata, this.callOptions, ManagedChannelImpl.NOOP_PICK_DETAILS_CONSUMER);
            MultiChildLoadBalancer.AcceptResolvedAddrRetVal selectConfig$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging = this.configSelector.selectConfig$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging();
            Status status = (Status) selectConfig$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status;
            if (status.isOk()) {
                ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig) selectConfig$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren).getMethodConfig(this.method);
                if (methodConfig != null) {
                    this.callOptions = this.callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
                }
                ClientCall newCall = this.channel.newCall(this.method, this.callOptions);
                this.delegate = newCall;
                newCall.start$ar$class_merging$ar$class_merging(onDeviceFaceMeshCreateLogEvent, metadata);
                return;
            }
            final Status replaceInappropriateControlPlaneStatus = GrpcUtil.replaceInappropriateControlPlaneStatus(status);
            this.callExecutor.execute(new ContextRunnable(this) { // from class: io.grpc.internal.ManagedChannelImpl.ConfigSelectingClientCall.1CloseInContext
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(this.context);
                }

                @Override // io.grpc.internal.ContextRunnable
                public final void runInContext() {
                    onDeviceFaceMeshCreateLogEvent.onClose(replaceInappropriateControlPlaneStatus, new Metadata());
                }
            });
            this.delegate = ManagedChannelImpl.NOOP_CALL;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ExecutorHolder implements Executor {
        private Executor executor;
        private final ObjectPool pool;

        public ExecutorHolder(ObjectPool objectPool) {
            this.pool = objectPool;
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            getExecutor().execute(runnable);
        }

        /* JADX WARN: Type inference failed for: r0v4, types: [java.util.concurrent.Executor, java.lang.Object] */
        final synchronized Executor getExecutor() {
            if (this.executor == null) {
                ?? object = this.pool.getObject();
                object.getClass();
                this.executor = object;
            }
            return this.executor;
        }

        final synchronized void release() {
            Executor executor = this.executor;
            if (executor != null) {
                this.pool.returnObject$ar$ds(executor);
                this.executor = null;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class IdleModeStateAggregator extends InUseStateAggregator {
        public IdleModeStateAggregator() {
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected final void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        @Override // io.grpc.internal.InUseStateAggregator
        protected final void handleNotInUse() {
            if (ManagedChannelImpl.this.shutdown.get()) {
                return;
            }
            ManagedChannelImpl.this.rescheduleIdleTimer();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LbHelperImpl extends LoadBalancer.Helper {
        AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer lb;

        public LbHelperImpl() {
        }

        @Override // io.grpc.LoadBalancer.Helper
        public final /* bridge */ /* synthetic */ LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            ContextDataProvider.checkState(!ManagedChannelImpl.this.terminating, "Channel is being terminated");
            return new AbstractSubchannel(ManagedChannelImpl.this, createSubchannelArgs);
        }

        @Override // io.grpc.LoadBalancer.Helper
        public final ChannelLogger getChannelLogger() {
            return ManagedChannelImpl.this.channelLogger;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public final ScheduledExecutorService getScheduledExecutorService() {
            return ManagedChannelImpl.this.scheduledExecutor;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public final SynchronizationContext getSynchronizationContext() {
            return ManagedChannelImpl.this.syncContext;
        }

        @Override // io.grpc.LoadBalancer.Helper
        public final void refreshNameResolution() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            ManagedChannelImpl.this.syncContext.execute(new InternalSubchannel$4$1(this, 10));
        }

        @Override // io.grpc.LoadBalancer.Helper
        public final void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            connectivityState.getClass();
            ManagedChannelImpl.this.syncContext.execute(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, subchannelPicker, connectivityState, 17, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NameResolverListener extends NameResolver.Listener2 {
        final LbHelperImpl helper;
        final NameResolver resolver;

        public NameResolverListener(LbHelperImpl lbHelperImpl, NameResolver nameResolver) {
            this.helper = lbHelperImpl;
            nameResolver.getClass();
            this.resolver = nameResolver;
        }

        @Override // io.grpc.NameResolver.Listener2
        public final void onError(Status status) {
            ContextDataProvider.checkArgument(!status.isOk(), (Object) "the error status must not be OK");
            ManagedChannelImpl.this.syncContext.execute(new DelayedClientCall.AnonymousClass4(this, status, 16, null));
        }

        @Override // io.grpc.NameResolver.Listener2
        public final void onResult(final NameResolver.ResolutionResult resolutionResult) {
            ManagedChannelImpl.this.syncContext.execute(new Runnable(this) { // from class: io.grpc.internal.ManagedChannelImpl.NameResolverListener.1NamesResolved
                final /* synthetic */ NameResolverListener this$1;

                {
                    this.this$1 = this;
                }

                /* JADX WARN: Code restructure failed: missing block: B:39:0x023b, code lost:
                
                    if (r5.provider.getPolicyName().equals(r2.getPolicyName()) == false) goto L80;
                 */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void run() {
                    /*
                        Method dump skipped, instructions count: 693
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ManagedChannelImpl.NameResolverListener.C1NamesResolved.run():void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RealChannel extends Channel {
        public final String authority;
        public final AtomicReference configSelector = new AtomicReference(ManagedChannelImpl.INITIAL_PENDING_SELECTOR);
        private final Channel clientCallImplChannel = new Channel() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.1
            @Override // io.grpc.Channel
            public final String authority() {
                return RealChannel.this.authority;
            }

            @Override // io.grpc.Channel
            public final ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
                ScheduledExecutorService scheduledExecutorService;
                Executor callExecutor = ManagedChannelImpl.this.getCallExecutor(callOptions);
                ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                if (managedChannelImpl.terminated) {
                    scheduledExecutorService = null;
                } else {
                    scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
                }
                ClientCallImpl clientCallImpl = new ClientCallImpl(methodDescriptor, callExecutor, callOptions, managedChannelImpl.transportProvider, scheduledExecutorService, ManagedChannelImpl.this.channelCallTracer);
                clientCallImpl.decompressorRegistry = ManagedChannelImpl.this.decompressorRegistry;
                return clientCallImpl;
            }
        };

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class PendingCall extends DelayedClientCall {
            private final long callCreationTime;
            final CallOptions callOptions;
            final Context context;
            final MethodDescriptor method;

            public PendingCall(Context context, MethodDescriptor methodDescriptor, CallOptions callOptions) {
                super(ManagedChannelImpl.this.getCallExecutor(callOptions), ManagedChannelImpl.this.scheduledExecutor, callOptions.deadline);
                this.context = context;
                this.method = methodDescriptor;
                this.callOptions = callOptions;
                this.callCreationTime = System.nanoTime();
            }

            @Override // io.grpc.internal.DelayedClientCall
            protected final void callCancelled() {
                ManagedChannelImpl.this.syncContext.execute(new InternalSubchannel$4$1(this, 13));
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final void reprocess() {
                ContextRunnable contextRunnable;
                Context attach = this.context.attach();
                try {
                    ClientCall newClientCall = RealChannel.this.newClientCall(this.method, this.callOptions.withOption(ClientStreamTracer.NAME_RESOLUTION_DELAYED, Long.valueOf(System.nanoTime() - this.callCreationTime)));
                    synchronized (this) {
                        if (this.realCall != null) {
                            contextRunnable = null;
                        } else {
                            super.setRealCall(newClientCall);
                            contextRunnable = new ContextRunnable(super.context) { // from class: io.grpc.internal.DelayedClientCall.1
                                @Override // io.grpc.internal.ContextRunnable
                                public final void runInContext() {
                                    DelayedClientCall.this.drainPendingCalls();
                                }
                            };
                        }
                    }
                    if (contextRunnable == null) {
                        RealChannel realChannel = RealChannel.this;
                        ManagedChannelImpl.this.syncContext.execute(new InternalSubchannel$4$1(this, 13));
                    } else {
                        RealChannel realChannel2 = RealChannel.this;
                        ManagedChannelImpl.this.getCallExecutor(this.callOptions).execute(new DelayedClientCall.AnonymousClass4(this, contextRunnable, 18));
                    }
                } finally {
                    this.context.detach(attach);
                }
            }
        }

        public RealChannel(String str) {
            str.getClass();
            this.authority = str;
        }

        @Override // io.grpc.Channel
        public final String authority() {
            return this.authority;
        }

        @Override // io.grpc.Channel
        public final ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(methodDescriptor, callOptions);
            }
            ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
            managedChannelImpl.syncContext.execute(new InternalSubchannel$4$1(this, 12));
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(methodDescriptor, callOptions);
            }
            if (ManagedChannelImpl.this.shutdown.get()) {
                return new ClientCall() { // from class: io.grpc.internal.ManagedChannelImpl.RealChannel.3
                    @Override // io.grpc.ClientCall
                    public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
                        onDeviceFaceMeshCreateLogEvent.onClose(ManagedChannelImpl.SHUTDOWN_STATUS, new Metadata());
                    }

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
                };
            }
            PendingCall pendingCall = new PendingCall(Context.current(), methodDescriptor, callOptions);
            ManagedChannelImpl managedChannelImpl2 = ManagedChannelImpl.this;
            managedChannelImpl2.syncContext.execute(new DelayedClientCall.AnonymousClass4(this, pendingCall, 17, null));
            return pendingCall;
        }

        public final ClientCall newClientCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
            InternalConfigSelector internalConfigSelector = (InternalConfigSelector) this.configSelector.get();
            if (internalConfigSelector == null) {
                return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
            }
            if (internalConfigSelector instanceof ManagedChannelServiceConfig.ServiceConfigConvertedSelector) {
                ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig.ServiceConfigConvertedSelector) internalConfigSelector).config.getMethodConfig(methodDescriptor);
                if (methodConfig != null) {
                    callOptions = callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
                }
                return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
            }
            return new ConfigSelectingClientCall(internalConfigSelector, this.clientCallImplChannel, ManagedChannelImpl.this.executor, methodDescriptor, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void updateConfigSelector(InternalConfigSelector internalConfigSelector) {
            Collection collection;
            InternalConfigSelector internalConfigSelector2 = (InternalConfigSelector) this.configSelector.get();
            this.configSelector.set(internalConfigSelector);
            if (internalConfigSelector2 == ManagedChannelImpl.INITIAL_PENDING_SELECTOR && (collection = ManagedChannelImpl.this.pendingCalls) != null) {
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    ((PendingCall) it.next()).reprocess();
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RestrictedScheduledExecutor implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        public RestrictedScheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
            scheduledExecutorService.getClass();
            this.delegate = scheduledExecutorService;
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean awaitTermination(long j, TimeUnit timeUnit) {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }

        @Override // java.util.concurrent.ExecutorService
        public final List invokeAll(Collection collection) {
            return this.delegate.invokeAll(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public final Object invokeAny(Collection collection) {
            return this.delegate.invokeAny(collection);
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public final ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(runnable, j, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public final void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        @Override // java.util.concurrent.ExecutorService
        public final List shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        @Override // java.util.concurrent.ExecutorService
        public final Future submit(Runnable runnable) {
            return this.delegate.submit(runnable);
        }

        @Override // java.util.concurrent.ExecutorService
        public final List invokeAll(Collection collection, long j, TimeUnit timeUnit) {
            return this.delegate.invokeAll(collection, j, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public final Object invokeAny(Collection collection, long j, TimeUnit timeUnit) {
            return this.delegate.invokeAny(collection, j, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(callable, j, timeUnit);
        }

        @Override // java.util.concurrent.ExecutorService
        public final Future submit(Runnable runnable, Object obj) {
            return this.delegate.submit(runnable, obj);
        }

        @Override // java.util.concurrent.ExecutorService
        public final Future submit(Callable callable) {
            return this.delegate.submit(callable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UncommittedRetriableStreamsRegistry {
        Status shutdownStatus;
        final Object lock = new Object();
        Collection uncommittedRetriableStreams = new HashSet();

        public UncommittedRetriableStreamsRegistry() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void onShutdown(Status status) {
            synchronized (this.lock) {
                if (this.shutdownStatus != null) {
                    return;
                }
                this.shutdownStatus = status;
                boolean isEmpty = this.uncommittedRetriableStreams.isEmpty();
                if (isEmpty) {
                    ManagedChannelImpl.this.delayedTransport.shutdown(status);
                }
            }
        }
    }

    static {
        Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
        SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
        SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
        EMPTY_SERVICE_CONFIG = new ManagedChannelServiceConfig(null, new HashMap(), new HashMap(), null, null, null);
        INITIAL_PENDING_SELECTOR = new InternalConfigSelector() { // from class: io.grpc.internal.ManagedChannelImpl.1
            @Override // io.grpc.InternalConfigSelector
            public final MultiChildLoadBalancer.AcceptResolvedAddrRetVal selectConfig$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging() {
                throw new IllegalStateException("Resolution is pending");
            }
        };
        NOOP_PICK_DETAILS_CONSUMER = new PickDetailsConsumerImpl();
        NOOP_CALL = new ClientCall() { // from class: io.grpc.internal.ManagedChannelImpl.5
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

    /* JADX WARN: Type inference failed for: r12v4, types: [java.util.concurrent.ScheduledExecutorService, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v5, types: [io.grpc.ProxyDetector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.util.concurrent.Executor, java.lang.Object] */
    public ManagedChannelImpl(ManagedChannelImplBuilder managedChannelImplBuilder, ClientTransportFactory clientTransportFactory, URI uri, NameResolverProvider nameResolverProvider, OnDeviceImageLabelDetectionLogEvent onDeviceImageLabelDetectionLogEvent, ObjectPool objectPool, Supplier supplier, List list, final TimeProvider timeProvider) {
        SynchronizationContext synchronizationContext = new SynchronizationContext(new AnonymousClass3(this, 0));
        this.syncContext = synchronizationContext;
        this.channelStateManager = new ConnectivityStateManager();
        this.subchannels = new HashSet(16, 0.75f);
        this.pendingCallsInUseObject = new Object();
        this.oobChannels = new HashSet(1, 0.75f);
        this.uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
        this.shutdown = new AtomicBoolean(false);
        this.terminatedLatch = new CountDownLatch(1);
        this.lastResolutionState$ar$edu = 1;
        this.lastServiceConfig = EMPTY_SERVICE_CONFIG;
        this.serviceConfigUpdated = false;
        this.channelBufferUsed$ar$class_merging$ar$class_merging = new RemoteModelManager((byte[]) null);
        this.ticker$ar$class_merging$ar$class_merging = Deadline.SYSTEM_TICKER$ar$class_merging$ar$class_merging$ar$class_merging;
        DelayedTransportListener delayedTransportListener = new DelayedTransportListener();
        this.delayedTransportListener = delayedTransportListener;
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelStreamProvider();
        String str = managedChannelImplBuilder.target;
        str.getClass();
        this.target = str;
        InternalLogId allocate = InternalLogId.allocate("Channel", str);
        this.logId = allocate;
        this.timeProvider = timeProvider;
        ObjectPool objectPool2 = managedChannelImplBuilder.executorPool;
        objectPool2.getClass();
        this.executorPool = objectPool2;
        ?? object = objectPool2.getObject();
        object.getClass();
        this.executor = object;
        ObjectPool objectPool3 = managedChannelImplBuilder.offloadExecutorPool;
        objectPool3.getClass();
        ExecutorHolder executorHolder = new ExecutorHolder(objectPool3);
        this.offloadExecutorHolder = executorHolder;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, executorHolder);
        this.transportFactory = callCredentialsApplyingTransportFactory;
        new CallCredentialsApplyingTransportFactory(clientTransportFactory, executorHolder);
        RestrictedScheduledExecutor restrictedScheduledExecutor = new RestrictedScheduledExecutor(callCredentialsApplyingTransportFactory.getScheduledExecutorService());
        this.scheduledExecutor = restrictedScheduledExecutor;
        ChannelTracer channelTracer = new ChannelTracer(allocate, timeProvider.currentTimeNanos(), "Channel for '" + str + "'");
        this.channelTracer = channelTracer;
        ChannelLoggerImpl channelLoggerImpl = new ChannelLoggerImpl(channelTracer, timeProvider);
        this.channelLogger = channelLoggerImpl;
        ProxyDetector proxyDetector = GrpcUtil.DEFAULT_PROXY_DETECTOR;
        boolean z = managedChannelImplBuilder.retryEnabled;
        this.retryEnabled = z;
        MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(LoadBalancerRegistry.getDefaultRegistry(), managedChannelImplBuilder.defaultLbPolicy);
        this.loadBalancerFactory$ar$class_merging = acceptResolvedAddrRetVal;
        this.targetUri = uri;
        this.nameResolverProvider = nameResolverProvider;
        NameResolver.ServiceConfigParser serviceConfigParser = new NameResolver.ServiceConfigParser(z, managedChannelImplBuilder.maxRetryAttempts, managedChannelImplBuilder.maxHedgedAttempts, acceptResolvedAddrRetVal);
        InputImageConstructionLogEvent inputImageConstructionLogEvent = new InputImageConstructionLogEvent();
        inputImageConstructionLogEvent.imageWidth = Integer.valueOf(managedChannelImplBuilder.channelBuilderDefaultPortProvider.getDefaultPort());
        proxyDetector.getClass();
        inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageByteSize = proxyDetector;
        inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageHeight = synchronizationContext;
        inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$durationMs = restrictedScheduledExecutor;
        inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageSource = serviceConfigParser;
        inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageFormat = channelLoggerImpl;
        inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$rotationDegrees = executorHolder;
        NameResolver.Args args = new NameResolver.Args(inputImageConstructionLogEvent.imageWidth, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageByteSize, (SynchronizationContext) inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageHeight, (NameResolver.ServiceConfigParser) inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageSource, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$durationMs, (ChannelLogger) inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageFormat, inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$rotationDegrees);
        this.nameResolverArgs = args;
        this.nameResolver = getNameResolver$ar$ds(uri, nameResolverProvider, args);
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool);
        DelayedClientTransport delayedClientTransport = new DelayedClientTransport(object, synchronizationContext);
        this.delayedTransport = delayedClientTransport;
        delayedClientTransport.listener = delayedTransportListener;
        delayedClientTransport.reportTransportInUse = new Futures$$ExternalSyntheticLambda1(delayedTransportListener, 12, null);
        delayedClientTransport.reportTransportNotInUse = new Futures$$ExternalSyntheticLambda1(delayedTransportListener, 13, null);
        delayedClientTransport.reportTransportTerminated = new Futures$$ExternalSyntheticLambda1(delayedTransportListener, 14, null);
        this.backoffPolicyProvider$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceImageLabelDetectionLogEvent;
        this.defaultServiceConfig = null;
        boolean z2 = managedChannelImplBuilder.lookUpServiceConfig;
        this.lookUpServiceConfig = z2;
        RealChannel realChannel = new RealChannel(this.nameResolver.getServiceAuthority());
        this.realChannel = realChannel;
        this.interceptorChannel = OnDeviceFaceMeshCreateLogEvent.intercept(realChannel, list);
        this.transportFilters = new ArrayList(managedChannelImplBuilder.transportFilters);
        supplier.getClass();
        this.stopwatchSupplier = supplier;
        long j = managedChannelImplBuilder.idleTimeoutMillis;
        if (j == -1) {
            this.idleTimeoutMillis = -1L;
        } else {
            ContextDataProvider.checkArgument(j >= ManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS, "invalid idleTimeoutMillis %s", j);
            this.idleTimeoutMillis = managedChannelImplBuilder.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new InternalSubchannel$4$1(this, 9), synchronizationContext, callCredentialsApplyingTransportFactory.getScheduledExecutorService(), new Stopwatch());
        DecompressorRegistry decompressorRegistry = managedChannelImplBuilder.decompressorRegistry;
        decompressorRegistry.getClass();
        this.decompressorRegistry = decompressorRegistry;
        CompressorRegistry compressorRegistry = managedChannelImplBuilder.compressorRegistry;
        compressorRegistry.getClass();
        this.compressorRegistry = compressorRegistry;
        this.userAgent = managedChannelImplBuilder.userAgent;
        this.channelBufferLimit = managedChannelImplBuilder.retryBufferSize;
        this.perRpcBufferLimit = managedChannelImplBuilder.perRpcBufferLimit;
        CallTracer.Factory factory = new CallTracer.Factory() { // from class: io.grpc.internal.ManagedChannelImpl.1ChannelCallTracerFactory
            @Override // io.grpc.internal.CallTracer.Factory
            public final CallTracer create() {
                return new CallTracer(TimeProvider.this);
            }
        };
        this.callTracerFactory = factory;
        this.channelCallTracer = factory.create();
        InternalChannelz internalChannelz = managedChannelImplBuilder.channelz;
        internalChannelz.getClass();
        this.channelz = internalChannelz;
        InternalChannelz.add(internalChannelz.rootChannels, this);
        if (!z2) {
            this.serviceConfigUpdated = true;
        }
        OnDeviceImageCaptioningLoadLogEvent.getDefaultRegistry$ar$ds();
    }

    static NameResolver getNameResolver$ar$ds(URI uri, NameResolverProvider nameResolverProvider, NameResolver.Args args) {
        NameResolver newNameResolver = nameResolverProvider.newNameResolver(uri, args);
        if (newNameResolver != null) {
            OnDeviceImageLabelDetectionLogEvent onDeviceImageLabelDetectionLogEvent = new OnDeviceImageLabelDetectionLogEvent(null);
            ScheduledExecutorService scheduledExecutorService = args.scheduledExecutorService;
            if (scheduledExecutorService != null) {
                return new RetryingNameResolver(newNameResolver, new BackoffPolicyRetryScheduler(onDeviceImageLabelDetectionLogEvent, scheduledExecutorService, args.syncContext), args.syncContext);
            }
            throw new IllegalStateException("ScheduledExecutorService not set in Builder");
        }
        throw new IllegalArgumentException("cannot create a NameResolver for ".concat(String.valueOf(String.valueOf(uri))));
    }

    @Override // io.grpc.Channel
    public final String authority() {
        return this.interceptorChannel.authority();
    }

    public final void cancelIdleTimer(boolean z) {
        ScheduledFuture scheduledFuture;
        Rescheduler rescheduler = this.idleTimer;
        rescheduler.enabled = false;
        if (z && (scheduledFuture = rescheduler.wakeUp) != null) {
            scheduledFuture.cancel(false);
            rescheduler.wakeUp = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void exitIdleMode() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (!this.shutdown.get() && !this.panicMode) {
            if (!this.inUseStateAggregator.inUseObjects.isEmpty()) {
                cancelIdleTimer(false);
            } else {
                rescheduleIdleTimer();
            }
            if (this.lbHelper == null) {
                this.channelLogger.log$ar$edu(2, "Exiting idle mode");
                LbHelperImpl lbHelperImpl = new LbHelperImpl();
                lbHelperImpl.lb = new AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer(this.loadBalancerFactory$ar$class_merging, lbHelperImpl);
                this.lbHelper = lbHelperImpl;
                this.channelStateManager.gotoState(ConnectivityState.CONNECTING);
                this.nameResolver.start(new NameResolverListener(lbHelperImpl, this.nameResolver));
                this.nameResolverStarted = true;
            }
        }
    }

    public final Executor getCallExecutor(CallOptions callOptions) {
        Executor executor = callOptions.executor;
        if (executor == null) {
            return this.executor;
        }
        return executor;
    }

    @Override // io.grpc.InternalWithLogId
    public final InternalLogId getLogId() {
        return this.logId;
    }

    public final void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log$ar$edu(2, "Terminated");
            InternalChannelz.remove(this.channelz.rootChannels, this);
            this.executorPool.returnObject$ar$ds(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.offloadExecutorHolder.release();
            this.transportFactory.close();
            this.terminated = true;
            this.terminatedLatch.countDown();
        }
    }

    @Override // io.grpc.Channel
    public final ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    public final void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j == -1) {
            return;
        }
        Rescheduler rescheduler = this.idleTimer;
        long nanos = TimeUnit.MILLISECONDS.toNanos(j);
        long nanoTime = rescheduler.nanoTime() + nanos;
        rescheduler.enabled = true;
        if (nanoTime - rescheduler.runAtNanos < 0 || rescheduler.wakeUp == null) {
            ScheduledFuture scheduledFuture = rescheduler.wakeUp;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            rescheduler.wakeUp = rescheduler.scheduler.schedule(new InternalSubchannel$4$1(rescheduler, 19), nanos, TimeUnit.NANOSECONDS);
        }
        rescheduler.runAtNanos = nanoTime;
    }

    @Override // io.grpc.ManagedChannel
    /* renamed from: shutdown$ar$ds$d72ad71c_0, reason: merged with bridge method [inline-methods] */
    public final void shutdown$ar$ds$17197e6c_0() {
        this.channelLogger.log$ar$edu(1, "shutdown() called");
        if (!this.shutdown.compareAndSet(false, true)) {
            return;
        }
        this.syncContext.execute(new InternalSubchannel$4$1(this, 8));
        RealChannel realChannel = this.realChannel;
        ManagedChannelImpl.this.syncContext.execute(new InternalSubchannel$4$1(realChannel, 11));
        this.syncContext.execute(new InternalSubchannel$4$1(this, 7));
    }

    public final void shutdownNameResolverAndLoadBalancer(boolean z) {
        boolean z2;
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (z) {
            ContextDataProvider.checkState(this.nameResolverStarted, "nameResolver is not started");
            if (this.lbHelper != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            ContextDataProvider.checkState(z2, "lbHelper is null");
        }
        NameResolver nameResolver = this.nameResolver;
        if (nameResolver != null) {
            nameResolver.shutdown();
            this.nameResolverStarted = false;
            if (z) {
                this.nameResolver = getNameResolver$ar$ds(this.targetUri, this.nameResolverProvider, this.nameResolverArgs);
            } else {
                this.nameResolver = null;
            }
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer autoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer = lbHelperImpl.lb;
            autoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer.delegate.shutdown();
            autoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer.delegate = null;
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    public final String toString() {
        MoreObjects$ToStringHelper add = ContextDataProvider.toStringHelper(this).add("logId", this.logId.id);
        add.addHolder$ar$ds("target", this.target);
        return add.toString();
    }

    public final void updateSubchannelPicker(LoadBalancer.SubchannelPicker subchannelPicker) {
        this.subchannelPicker = subchannelPicker;
        this.delayedTransport.reprocess(subchannelPicker);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DelayedTransportListener implements ManagedClientTransport.Listener {
        public DelayedTransportListener() {
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final Attributes filterTransport(Attributes attributes) {
            throw null;
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportInUse(boolean z) {
            ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
            managedChannelImpl.inUseStateAggregator.updateObjectInUse(managedChannelImpl.delayedTransport, z);
            if (z) {
                ManagedChannelImpl.this.exitIdleMode();
            }
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportShutdown(Status status) {
            ContextDataProvider.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportTerminated() {
            ContextDataProvider.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
            managedChannelImpl.terminating = true;
            managedChannelImpl.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeTerminateChannel();
        }

        @Override // io.grpc.internal.ManagedClientTransport.Listener
        public final void transportReady() {
        }
    }
}
