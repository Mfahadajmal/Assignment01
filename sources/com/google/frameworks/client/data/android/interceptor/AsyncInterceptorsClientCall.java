package com.google.frameworks.client.data.android.interceptor;

import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SequentialExecutor;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import com.google.frameworks.client.data.android.internal.TracePropagatingClientCallListener;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AsyncInterceptorsClientCall extends ClientCall {
    private final ImmutableList asyncInterceptors;
    private CallOptions callOptions;
    public ClientCall delegate;
    private Metadata headers;
    public OnDeviceFaceMeshCreateLogEvent listener$ar$class_merging$a40ae667_0$ar$class_merging;
    private final MethodDescriptor method;
    private final Channel next;
    public int preStartRequestCount;
    public final ReleaseDelegateExecutor releaseDelegateExecutor;
    private int requested;
    private Metadata responseTrailers;
    public final Executor sequentialExecutor;
    public State state;
    public final Set cancelableTriggers = ContextDataProvider.newIdentityHashSet();
    private final LinkedHashMap detachedHeaders = new LinkedHashMap();
    private final Set startedInterceptors = ContextDataProvider.newIdentityHashSet();
    public boolean halfClosed = false;
    public boolean aborted = false;
    private final int transportType$ar$edu = 2;
    public final Deque pendingRequestMessages = new ArrayDeque();
    private final Queue pendingResponseMessages = new ArrayDeque();
    private final Queue preStartMessages = new ArrayDeque();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ClosedOnceListener extends OnDeviceFaceMeshCreateLogEvent {
        public boolean closed = false;
        public final OnDeviceFaceMeshCreateLogEvent delegate$ar$class_merging$a40ae667_0$ar$class_merging;

        public ClosedOnceListener(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent) {
            this.delegate$ar$class_merging$a40ae667_0$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onClose(Status status, Metadata metadata) {
            AsyncInterceptorsClientCall.this.sequentialExecutor.execute(new FutureCallbackViewModel$$ExternalSyntheticLambda1((OnDeviceFaceMeshCreateLogEvent) this, status, metadata, 11));
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onHeaders(Metadata metadata) {
            AsyncInterceptorsClientCall.this.sequentialExecutor.execute(new EventBus$$ExternalSyntheticLambda0(this, metadata, 10, null));
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onMessage(Object obj) {
            AsyncInterceptorsClientCall.this.sequentialExecutor.execute(new EventBus$$ExternalSyntheticLambda0(this, obj, 11, null));
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onReady() {
            AsyncInterceptorsClientCall.this.sequentialExecutor.execute(new Futures$$ExternalSyntheticLambda1(this, 4));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PendingMessage {
        public int currentStage;
        public final LinkedHashMap detachedInterceptors = new LinkedHashMap();
        public final Object message;

        public PendingMessage(Object obj) {
            this.message = obj;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ReleaseDelegateExecutor implements Executor {
        private volatile Executor delegate;

        public ReleaseDelegateExecutor(Executor executor) {
            this.delegate = executor;
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }

        public final void freeDelegate() {
            this.delegate = DirectExecutor.INSTANCE;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State {
        public boolean delayStart = false;
        public final int headerStage;
        public final int messageStage;
        public final int numStages;
        public final int phase$ar$edu;

        public State(int i, int i2, int i3, int i4) {
            this.numStages = i;
            this.phase$ar$edu = i2;
            this.headerStage = i3;
            this.messageStage = i4;
        }
    }

    public AsyncInterceptorsClientCall(Channel channel, MethodDescriptor methodDescriptor, CallOptions callOptions, int i, ImmutableList immutableList) {
        this.next = channel;
        this.method = methodDescriptor;
        this.callOptions = callOptions;
        this.asyncInterceptors = immutableList;
        this.state = new State(immutableList.size(), 1, 0, 0);
        Executor executor = callOptions.executor;
        ReleaseDelegateExecutor releaseDelegateExecutor = new ReleaseDelegateExecutor(executor == null ? DirectExecutor.INSTANCE : executor);
        this.releaseDelegateExecutor = releaseDelegateExecutor;
        this.sequentialExecutor = new AsyncInterceptorsClientCall$$ExternalSyntheticLambda10(this, (Executor) new SequentialExecutor(releaseDelegateExecutor), 0);
    }

    private final AsyncClientInterceptor.RequestHeaderContext createHeaderContext(CallOptions callOptions, Metadata metadata, String str) {
        callOptions.getClass();
        metadata.getClass();
        str.getClass();
        return new AsyncClientInterceptor.RequestHeaderContext(2, this.method, callOptions, metadata, str);
    }

    private final boolean handleOutcome(Outcome outcome) {
        int i = outcome.type$ar$edu$1ad18efe_0 - 1;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        this.state.delayStart = true;
                        return false;
                    }
                    ListenableFuture trigger = outcome.getTrigger();
                    this.cancelableTriggers.add(trigger);
                    trigger.addListener(TracePropagation.propagateRunnable(new EventBus$$ExternalSyntheticLambda0(this, trigger, 4)), this.sequentialExecutor);
                    return false;
                }
                throw null;
            }
            RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = outcome.errorResponse$ar$class_merging;
            this.listener$ar$class_merging$a40ae667_0$ar$class_merging.onClose((Status) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass, (Metadata) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider);
            if (this.state.phase$ar$edu == 4) {
                this.delegate.cancel("Aborted RPC with exception", ((Status) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass).cause);
            }
            this.aborted = true;
            return true;
        }
        if (outcome.callOptions != null) {
            if (this.state.phase$ar$edu != 1) {
                IllegalStateException illegalStateException = new IllegalStateException("Cannot return proceedWithCallOptions() from message processing methods");
                this.listener$ar$class_merging$a40ae667_0$ar$class_merging.onClose(Status.fromThrowable(illegalStateException), new Metadata());
                if (this.state.phase$ar$edu == 4) {
                    this.delegate.cancel("Interceptor returned invalid outcome", illegalStateException);
                }
                return true;
            }
            this.callOptions = outcome.callOptions;
        }
        return false;
    }

    private final void maybeDrainRequestedLocalResponses() {
        ArrayDeque arrayDeque = new ArrayDeque();
        synchronized (this.pendingResponseMessages) {
            if (this.pendingResponseMessages.isEmpty()) {
                return;
            }
            Metadata metadata = this.responseTrailers;
            int i = this.requested;
            for (int i2 = 0; i2 < i; i2++) {
                Object poll = this.pendingResponseMessages.poll();
                if (poll != null) {
                    arrayDeque.add(poll);
                    this.requested--;
                }
            }
            boolean isEmpty = this.pendingResponseMessages.isEmpty();
            Iterator it = arrayDeque.iterator();
            while (it.hasNext()) {
                this.listener$ar$class_merging$a40ae667_0$ar$class_merging.onMessage(it.next());
            }
            if (isEmpty) {
                this.listener$ar$class_merging$a40ae667_0$ar$class_merging.onClose(Status.OK, metadata);
            }
        }
    }

    private final AsyncClientInterceptor.RequestHeaderContext maybeReplaceHeaderContext(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        CallOptions callOptions = this.callOptions;
        if (callOptions == requestHeaderContext.callOptions) {
            return requestHeaderContext;
        }
        return createHeaderContext(callOptions, requestHeaderContext.requestMetadata, requestHeaderContext.authority);
    }

    private final void sendReadyRequestMessages() {
        while (!this.pendingRequestMessages.isEmpty()) {
            PendingMessage pendingMessage = (PendingMessage) this.pendingRequestMessages.peek();
            if (pendingMessage.detachedInterceptors.isEmpty() && pendingMessage.currentStage == AsyncInterceptorsClientCall.this.state.numStages) {
                Object obj = ((PendingMessage) this.pendingRequestMessages.poll()).message;
                if (this.state.phase$ar$edu == 4) {
                    this.delegate.sendMessage(obj);
                } else {
                    this.preStartMessages.add(obj);
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void startRequestMessageProcessing(PendingMessage pendingMessage, int i, int i2) {
        ContextDataProvider contextDataProvider = new ContextDataProvider(pendingMessage.message, null);
        while (i < i2) {
            ImmutableList immutableList = (ImmutableList) this.asyncInterceptors.get(i);
            int size = immutableList.size();
            int i3 = 0;
            while (i3 < size) {
                AsyncClientInterceptor asyncClientInterceptor = (AsyncClientInterceptor) immutableList.get(i3);
                Outcome startRequestMessageProcessing$ar$ds = asyncClientInterceptor.startRequestMessageProcessing$ar$ds();
                if (startRequestMessageProcessing$ar$ds.type$ar$edu$1ad18efe_0 == 4) {
                    ListenableFuture trigger = startRequestMessageProcessing$ar$ds.getTrigger();
                    pendingMessage.detachedInterceptors.put(asyncClientInterceptor, trigger);
                    trigger.addListener(TracePropagation.propagateRunnable(new FutureCallbackViewModel$$ExternalSyntheticLambda1((Object) this, contextDataProvider, (Object) pendingMessage, 8)), this.sequentialExecutor);
                }
                i3++;
                if (handleOutcome(startRequestMessageProcessing$ar$ds)) {
                    return;
                }
            }
            i++;
        }
        pendingMessage.currentStage = i2;
        if (pendingMessage.detachedInterceptors.isEmpty()) {
            sendReadyRequestMessages();
            maybeHalfCloseOrTransitionState();
        }
    }

    private final void transitionState() {
        boolean z;
        State state;
        State state2 = this.state;
        if (state2.phase$ar$edu != 4) {
            z = false;
        } else {
            z = true;
        }
        ContextDataProvider.checkState(!z, "UNDERLYING_CALL_STARTED state is terminal, cannot transition");
        int i = state2.phase$ar$edu;
        if (i == 3) {
            state = new State(state2.numStages, 4, state2.headerStage, state2.messageStage);
        } else if (i == 1 && state2.delayStart) {
            int i2 = state2.numStages;
            int i3 = state2.headerStage;
            state = new State(i2, 2, i3, i3);
        } else {
            int i4 = state2.headerStage;
            int i5 = i4 + 1;
            int i6 = state2.numStages;
            int i7 = state2.messageStage;
            if (i5 < i6) {
                state = new State(i6, 1, i5, i7);
            } else {
                state = new State(i6, 3, i4, i7);
            }
        }
        this.state = state;
        int i8 = state.phase$ar$edu - 1;
        if (i8 != 0) {
            if (i8 != 2) {
                processReadyMessages();
                return;
            }
            ClientCall newCall = this.next.newCall(this.method, this.callOptions);
            this.delegate = newCall;
            newCall.start$ar$class_merging$ar$class_merging(this.listener$ar$class_merging$a40ae667_0$ar$class_merging, this.headers);
            int i9 = this.preStartRequestCount;
            if (i9 > 0) {
                this.delegate.request(i9);
            }
            Iterator it = this.preStartMessages.iterator();
            while (it.hasNext()) {
                this.delegate.sendMessage(it.next());
            }
            if (this.halfClosed && this.pendingRequestMessages.isEmpty()) {
                this.delegate.halfClose();
            }
            transitionState();
            return;
        }
        startRequestHeaderProcessing(this.headers);
    }

    @Override // io.grpc.ClientCall
    public final void cancel(String str, Throwable th) {
        this.sequentialExecutor.execute(TracePropagation.propagateRunnable(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, str, th, 9)));
    }

    public final void continueRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        if (!this.detachedHeaders.isEmpty()) {
            Iterator it = this.detachedHeaders.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!((ListenableFuture) entry.getValue()).isDone()) {
                    break;
                }
                it.remove();
                AsyncClientInterceptor asyncClientInterceptor = (AsyncClientInterceptor) entry.getKey();
                Outcome continueRequestHeaderProcessing = asyncClientInterceptor.continueRequestHeaderProcessing(maybeReplaceHeaderContext(requestHeaderContext));
                if (continueRequestHeaderProcessing.type$ar$edu$1ad18efe_0 == 4) {
                    ListenableFuture trigger = continueRequestHeaderProcessing.getTrigger();
                    this.detachedHeaders.put(asyncClientInterceptor, trigger);
                    trigger.addListener(TracePropagation.propagateRunnable(new EventBus$$ExternalSyntheticLambda0(this, requestHeaderContext, 9)), this.sequentialExecutor);
                }
                if (handleOutcome(continueRequestHeaderProcessing)) {
                    return;
                }
            }
            if (this.detachedHeaders.isEmpty() && !this.aborted) {
                transitionState();
            }
        }
    }

    public final void continueRequestMessageProcessing$ar$class_merging$ar$class_merging$ar$class_merging(ContextDataProvider contextDataProvider, PendingMessage pendingMessage) {
        Iterator it = pendingMessage.detachedInterceptors.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!((ListenableFuture) entry.getValue()).isDone()) {
                break;
            }
            it.remove();
            AsyncClientInterceptor asyncClientInterceptor = (AsyncClientInterceptor) entry.getKey();
            Outcome continueRequestMessageProcessing$ar$ds = asyncClientInterceptor.continueRequestMessageProcessing$ar$ds();
            if (continueRequestMessageProcessing$ar$ds.type$ar$edu$1ad18efe_0 == 4) {
                ListenableFuture trigger = continueRequestMessageProcessing$ar$ds.getTrigger();
                pendingMessage.detachedInterceptors.put(asyncClientInterceptor, trigger);
                trigger.addListener(TracePropagation.propagateRunnable(new FutureCallbackViewModel$$ExternalSyntheticLambda1((Object) this, contextDataProvider, (Object) pendingMessage, 10)), this.sequentialExecutor);
            }
            if (handleOutcome(continueRequestMessageProcessing$ar$ds)) {
                return;
            }
        }
        if (pendingMessage.detachedInterceptors.isEmpty()) {
            sendReadyRequestMessages();
            maybeHalfCloseOrTransitionState();
        }
    }

    @Override // io.grpc.ClientCall
    public final void halfClose() {
        this.sequentialExecutor.execute(TracePropagation.propagateRunnable(new Futures$$ExternalSyntheticLambda1(this, 3)));
    }

    public final void maybeHalfCloseOrTransitionState() {
        if (this.halfClosed) {
            PendingMessage pendingMessage = (PendingMessage) this.pendingRequestMessages.peekLast();
            int i = this.state.phase$ar$edu - 1;
            if (i != 1) {
                if (i == 3 && pendingMessage == null) {
                    this.delegate.halfClose();
                    return;
                }
                return;
            }
            if (pendingMessage == null || (pendingMessage.detachedInterceptors.isEmpty() && pendingMessage.currentStage == AsyncInterceptorsClientCall.this.state.messageStage + 1)) {
                transitionState();
            }
        }
    }

    public final void processReadyMessages() {
        int i = this.state.phase$ar$edu - 1;
        if (i != 1) {
            if (i == 3) {
                for (PendingMessage pendingMessage : this.pendingRequestMessages) {
                    startRequestMessageProcessing(pendingMessage, pendingMessage.currentStage, this.state.numStages);
                }
                return;
            }
            return;
        }
        for (PendingMessage pendingMessage2 : this.pendingRequestMessages) {
            int i2 = pendingMessage2.currentStage;
            int i3 = this.state.messageStage;
            if (i2 <= i3) {
                startRequestMessageProcessing(pendingMessage2, i2, i3 + 1);
            }
        }
    }

    @Override // io.grpc.ClientCall
    public final void request(int i) {
        synchronized (this.pendingResponseMessages) {
            this.requested += i;
        }
        maybeDrainRequestedLocalResponses();
        this.sequentialExecutor.execute(TracePropagation.propagateRunnable(new RatingView$$ExternalSyntheticLambda5(this, i, 6)));
    }

    @Override // io.grpc.ClientCall
    public final void sendMessage(Object obj) {
        this.sequentialExecutor.execute(TracePropagation.propagateRunnable(new EventBus$$ExternalSyntheticLambda0(this, obj, 6, null)));
    }

    @Override // io.grpc.ClientCall
    public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
        this.listener$ar$class_merging$a40ae667_0$ar$class_merging = new ClosedOnceListener(new TracePropagatingClientCallListener(new AsyncInterceptorsClientCallListener(onDeviceFaceMeshCreateLogEvent, this.asyncInterceptors, this.startedInterceptors, this.sequentialExecutor)));
        this.headers = metadata;
        this.sequentialExecutor.execute(TracePropagation.propagateRunnable(new EventBus$$ExternalSyntheticLambda0(this, metadata, 5, null)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startRequestHeaderProcessing(Metadata metadata) {
        AsyncClientInterceptor.RequestHeaderContext createHeaderContext = createHeaderContext(this.callOptions, metadata, this.next.authority());
        ImmutableList immutableList = (ImmutableList) this.asyncInterceptors.get(this.state.headerStage);
        int size = immutableList.size();
        for (int i = 0; i < size; i++) {
            AsyncClientInterceptor asyncClientInterceptor = (AsyncClientInterceptor) immutableList.get(i);
            Outcome startRequestHeaderProcessing = asyncClientInterceptor.startRequestHeaderProcessing(maybeReplaceHeaderContext(createHeaderContext));
            if (startRequestHeaderProcessing.type$ar$edu$1ad18efe_0 == 4) {
                ListenableFuture trigger = startRequestHeaderProcessing.getTrigger();
                this.detachedHeaders.put(asyncClientInterceptor, trigger);
                trigger.addListener(TracePropagation.propagateRunnable(new EventBus$$ExternalSyntheticLambda0(this, createHeaderContext, 8)), this.sequentialExecutor);
            }
            if (!handleOutcome(startRequestHeaderProcessing)) {
                this.startedInterceptors.add(asyncClientInterceptor);
            } else {
                return;
            }
        }
        if (this.detachedHeaders.isEmpty()) {
            transitionState();
        }
    }
}
