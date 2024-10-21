package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ClientStreamTracer;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.internal.RetriableStream;
import io.perfmark.Impl;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientCallImpl extends ClientCall {
    public static final double NANO_TO_SECS;
    private static final Logger log = Logger.getLogger(ClientCallImpl.class.getName());
    public final Executor callExecutor;
    private final boolean callExecutorIsDirect;
    public CallOptions callOptions;
    private boolean cancelCalled;
    public CancellationHandler cancellationHandler;
    public final CallTracer channelCallsTracer;
    private final ManagedChannelImpl.ChannelStreamProvider clientStreamProvider$ar$class_merging;
    public final Context context;
    public final ScheduledExecutorService deadlineCancellationExecutor;
    public DecompressorRegistry decompressorRegistry = DecompressorRegistry.DEFAULT_INSTANCE;
    private boolean halfCloseCalled;
    public final MethodDescriptor method;
    public ClientStream stream;
    public final Tag tag;
    private final boolean unaryRequest;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CancellationHandler implements Runnable {
        public final boolean contextIsDeadlineSource;
        public volatile ScheduledFuture deadlineCancellationFuture;
        public final boolean hasDeadline;
        public final long remainingNanos;
        public volatile boolean tearDownCalled;

        public CancellationHandler(Deadline deadline, boolean z) {
            long timeRemaining;
            this.contextIsDeadlineSource = z;
            if (deadline == null) {
                this.hasDeadline = false;
                timeRemaining = 0;
            } else {
                this.hasDeadline = true;
                timeRemaining = deadline.timeRemaining(TimeUnit.NANOSECONDS);
            }
            this.remainingNanos = timeRemaining;
        }

        final Status formatDeadlineExceededStatus() {
            String str;
            double longValue;
            long abs = Math.abs(this.remainingNanos) / TimeUnit.SECONDS.toNanos(1L);
            long abs2 = Math.abs(this.remainingNanos) % TimeUnit.SECONDS.toNanos(1L);
            StringBuilder sb = new StringBuilder();
            if (true != this.contextIsDeadlineSource) {
                str = "CallOptions";
            } else {
                str = "Context";
            }
            sb.append(str);
            sb.append(" deadline exceeded after ");
            if (this.remainingNanos < 0) {
                sb.append('-');
            }
            sb.append(abs);
            sb.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
            sb.append("s. ");
            Long l = (Long) ClientCallImpl.this.callOptions.getOption(ClientStreamTracer.NAME_RESOLUTION_DELAYED);
            Locale locale = Locale.US;
            if (l == null) {
                longValue = 0.0d;
            } else {
                longValue = l.longValue() / ClientCallImpl.NANO_TO_SECS;
            }
            sb.append(String.format(locale, "Name resolution delay %.9f seconds.", Double.valueOf(longValue)));
            if (ClientCallImpl.this.stream != null) {
                InsightBuilder insightBuilder = new InsightBuilder();
                ClientCallImpl.this.stream.appendTimeoutInsight(insightBuilder);
                sb.append(" ");
                sb.append(insightBuilder);
            }
            return Status.DEADLINE_EXCEEDED.withDescription(sb.toString());
        }

        @Override // java.lang.Runnable
        public final void run() {
            ClientCallImpl.this.stream.cancel(formatDeadlineExceededStatus());
        }

        final void tearDown() {
            this.tearDownCalled = true;
            ScheduledFuture scheduledFuture = this.deadlineCancellationFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ClientCallImpl.this.context.removeListener$ar$class_merging$89eaf5ff_0$ar$ds();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ClientStreamListenerImpl implements ClientStreamListener {
        public Status exceptionStatus;
        public final OnDeviceFaceMeshCreateLogEvent observer$ar$class_merging$a40ae667_0$ar$class_merging;

        public ClientStreamListenerImpl(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent) {
            this.observer$ar$class_merging$a40ae667_0$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
        }

        @Override // io.grpc.internal.ClientStreamListener
        public final void closed(final Status status, ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
            final OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage;
            int i = PerfMark.PerfMark$ar$NoOp;
            Deadline effectiveDeadline = ClientCallImpl.this.effectiveDeadline();
            if (status.code == Status.Code.CANCELLED && effectiveDeadline != null) {
                if (!effectiveDeadline.expired) {
                    if (effectiveDeadline.deadlineNanos - System.nanoTime() <= 0) {
                        effectiveDeadline.expired = true;
                    }
                }
                status = ClientCallImpl.this.cancellationHandler.formatDeadlineExceededStatus();
                metadata = new Metadata();
            }
            identifiedLanguage = Impl.NO_LINK$ar$class_merging;
            ClientCallImpl.this.callExecutor.execute(new ContextRunnable(this) { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1StreamClosed
                final /* synthetic */ ClientStreamListenerImpl this$1;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(ClientCallImpl.this.context);
                    this.this$1 = this;
                }

                @Override // io.grpc.internal.ContextRunnable
                public final void runInContext() {
                    int i2 = PerfMark.PerfMark$ar$NoOp;
                    ClientCallImpl.this.cancellationHandler.tearDown();
                    Status status2 = status;
                    Metadata metadata2 = metadata;
                    Status status3 = this.this$1.exceptionStatus;
                    if (status3 != null) {
                        metadata2 = new Metadata();
                        status2 = status3;
                    }
                    try {
                        this.this$1.observer$ar$class_merging$a40ae667_0$ar$class_merging.onClose(status2, metadata2);
                    } finally {
                        ClientCallImpl.this.channelCallsTracer.reportCallEnded(status2.isOk());
                    }
                }
            });
        }

        public final void exceptionThrown(Status status) {
            this.exceptionStatus = status;
            ClientCallImpl.this.stream.cancel(status);
        }

        @Override // io.grpc.internal.ClientStreamListener
        public final void headersRead(final Metadata metadata) {
            final OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage;
            int i = PerfMark.PerfMark$ar$NoOp;
            identifiedLanguage = Impl.NO_LINK$ar$class_merging;
            ClientCallImpl.this.callExecutor.execute(new ContextRunnable(this) { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1HeadersRead
                final /* synthetic */ ClientStreamListenerImpl this$1;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(ClientCallImpl.this.context);
                    this.this$1 = this;
                }

                @Override // io.grpc.internal.ContextRunnable
                public final void runInContext() {
                    int i2 = PerfMark.PerfMark$ar$NoOp;
                    ClientStreamListenerImpl clientStreamListenerImpl = this.this$1;
                    if (clientStreamListenerImpl.exceptionStatus != null) {
                        return;
                    }
                    try {
                        clientStreamListenerImpl.observer$ar$class_merging$a40ae667_0$ar$class_merging.onHeaders(metadata);
                    } catch (Throwable th) {
                        this.this$1.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to read headers"));
                    }
                }
            });
        }

        @Override // io.grpc.internal.StreamListener
        public final void messagesAvailable$ar$class_merging$ar$class_merging(final OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
            final OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage;
            int i = PerfMark.PerfMark$ar$NoOp;
            identifiedLanguage = Impl.NO_LINK$ar$class_merging;
            ClientCallImpl.this.callExecutor.execute(new ContextRunnable(this) { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1MessagesAvailable
                final /* synthetic */ ClientStreamListenerImpl this$1;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(ClientCallImpl.this.context);
                    this.this$1 = this;
                }

                @Override // io.grpc.internal.ContextRunnable
                public final void runInContext() {
                    int i2 = PerfMark.PerfMark$ar$NoOp;
                    if (this.this$1.exceptionStatus != null) {
                        GrpcUtil.closeQuietly$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent);
                        return;
                    }
                    while (true) {
                        try {
                            InputStream next = onDeviceTextDetectionLoadLogEvent.next();
                            if (next != null) {
                                try {
                                    ClientStreamListenerImpl clientStreamListenerImpl = this.this$1;
                                    clientStreamListenerImpl.observer$ar$class_merging$a40ae667_0$ar$class_merging.onMessage(ClientCallImpl.this.method.responseMarshaller.parse(next));
                                    next.close();
                                } finally {
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            GrpcUtil.closeQuietly$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent);
                            this.this$1.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to read message."));
                            return;
                        }
                    }
                }
            });
        }

        @Override // io.grpc.internal.StreamListener
        public final void onReady() {
            final OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage;
            MethodDescriptor.MethodType methodType = ClientCallImpl.this.method.type;
            if (methodType != MethodDescriptor.MethodType.UNARY && methodType != MethodDescriptor.MethodType.SERVER_STREAMING) {
                int i = PerfMark.PerfMark$ar$NoOp;
                identifiedLanguage = Impl.NO_LINK$ar$class_merging;
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable(this) { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1StreamOnReady
                    final /* synthetic */ ClientStreamListenerImpl this$1;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(ClientCallImpl.this.context);
                        this.this$1 = this;
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public final void runInContext() {
                        int i2 = PerfMark.PerfMark$ar$NoOp;
                        ClientStreamListenerImpl clientStreamListenerImpl = this.this$1;
                        if (clientStreamListenerImpl.exceptionStatus != null) {
                            return;
                        }
                        try {
                            clientStreamListenerImpl.observer$ar$class_merging$a40ae667_0$ar$class_merging.onReady();
                        } catch (Throwable th) {
                            this.this$1.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to call onReady."));
                        }
                    }
                });
            }
        }
    }

    static {
        "gzip".getBytes(Charset.forName("US-ASCII"));
        NANO_TO_SECS = TimeUnit.SECONDS.toNanos(1L);
    }

    public ClientCallImpl(MethodDescriptor methodDescriptor, Executor executor, CallOptions callOptions, ManagedChannelImpl.ChannelStreamProvider channelStreamProvider, ScheduledExecutorService scheduledExecutorService, CallTracer callTracer) {
        CompressorRegistry compressorRegistry = CompressorRegistry.DEFAULT_INSTANCE;
        this.method = methodDescriptor;
        String str = methodDescriptor.fullMethodName;
        System.identityHashCode(this);
        this.tag = Impl.NO_TAG;
        if (executor == DirectExecutor.INSTANCE) {
            this.callExecutor = new SerializeReentrantCallsDirectExecutor();
            this.callExecutorIsDirect = true;
        } else {
            this.callExecutor = new SerializingExecutor(executor);
            this.callExecutorIsDirect = false;
        }
        this.channelCallsTracer = callTracer;
        this.context = Context.current();
        MethodDescriptor.MethodType methodType = methodDescriptor.type;
        this.unaryRequest = methodType == MethodDescriptor.MethodType.UNARY || methodType == MethodDescriptor.MethodType.SERVER_STREAMING;
        this.callOptions = callOptions;
        this.clientStreamProvider$ar$class_merging = channelStreamProvider;
        this.deadlineCancellationExecutor = scheduledExecutorService;
    }

    private final void sendMessageInternal(final Object obj) {
        boolean z;
        if (this.stream != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Not started");
        ContextDataProvider.checkState(!this.cancelCalled, "call was cancelled");
        ContextDataProvider.checkState(!this.halfCloseCalled, "call was half-closed");
        try {
            ClientStream clientStream = this.stream;
            if (clientStream instanceof RetriableStream) {
                final RetriableStream retriableStream = (RetriableStream) clientStream;
                RetriableStream.State state = retriableStream.state;
                if (state.passThrough) {
                    state.winningSubstream.stream.writeMessage(retriableStream.method.streamRequest(obj));
                } else {
                    retriableStream.delayOrExecute(new RetriableStream.BufferEntry() { // from class: io.grpc.internal.RetriableStream.1SendMessageEntry
                        final /* synthetic */ RetriableStream this$0;
                        final /* synthetic */ Object val$message;

                        public C1SendMessageEntry(final RetriableStream retriableStream2, final Object obj2) {
                            r2 = obj2;
                            r1 = retriableStream2;
                        }

                        @Override // io.grpc.internal.RetriableStream.BufferEntry
                        public final void runWith(Substream substream) {
                            substream.stream.writeMessage(r1.method.streamRequest(r2));
                            substream.stream.flush();
                        }
                    });
                }
            } else {
                clientStream.writeMessage(this.method.streamRequest(obj2));
            }
            if (!this.unaryRequest) {
                this.stream.flush();
            }
        } catch (Error e) {
            this.stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw e;
        } catch (RuntimeException e2) {
            this.stream.cancel(Status.CANCELLED.withCause(e2).withDescription("Failed to stream message"));
        }
    }

    @Override // io.grpc.ClientCall
    public final void cancel(String str, Throwable th) {
        Status withDescription;
        int i = PerfMark.PerfMark$ar$NoOp;
        if (str == null && th == null) {
            th = new CancellationException("Cancelled without a message or cause");
            log.logp(Level.WARNING, "io.grpc.internal.ClientCallImpl", "cancelInternal", "Cancelling without a message or cause is suboptimal", th);
        }
        if (!this.cancelCalled) {
            this.cancelCalled = true;
            try {
                if (this.stream != null) {
                    Status status = Status.CANCELLED;
                    if (str != null) {
                        withDescription = status.withDescription(str);
                    } else {
                        withDescription = status.withDescription("Call cancelled without message");
                    }
                    if (th != null) {
                        withDescription = withDescription.withCause(th);
                    }
                    this.stream.cancel(withDescription);
                }
                CancellationHandler cancellationHandler = this.cancellationHandler;
                if (cancellationHandler != null) {
                    cancellationHandler.tearDown();
                }
            } catch (Throwable th2) {
                CancellationHandler cancellationHandler2 = this.cancellationHandler;
                if (cancellationHandler2 != null) {
                    cancellationHandler2.tearDown();
                }
                throw th2;
            }
        }
    }

    public final Deadline effectiveDeadline() {
        Deadline deadline = this.callOptions.deadline;
        this.context.getDeadline$ar$ds();
        if (deadline == null) {
            return null;
        }
        return deadline;
    }

    @Override // io.grpc.ClientCall
    public final void halfClose() {
        boolean z;
        int i = PerfMark.PerfMark$ar$NoOp;
        if (this.stream != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Not started");
        ContextDataProvider.checkState(!this.cancelCalled, "call was cancelled");
        ContextDataProvider.checkState(!this.halfCloseCalled, "call already half-closed");
        this.halfCloseCalled = true;
        this.stream.halfClose();
    }

    @Override // io.grpc.ClientCall
    public final void request(int i) {
        boolean z;
        int i2 = PerfMark.PerfMark$ar$NoOp;
        if (this.stream != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Not started");
        ContextDataProvider.checkArgument(true, (Object) "Number requested must be non-negative");
        this.stream.request(i);
    }

    @Override // io.grpc.ClientCall
    public final void sendMessage(Object obj) {
        int i = PerfMark.PerfMark$ar$NoOp;
        sendMessageInternal(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01c0  */
    @Override // io.grpc.ClientCall
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void start$ar$class_merging$ar$class_merging(com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent r14, io.grpc.Metadata r15) {
        /*
            Method dump skipped, instructions count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ClientCallImpl.start$ar$class_merging$ar$class_merging(com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent, io.grpc.Metadata):void");
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("method", this.method);
        return stringHelper.toString();
    }
}
