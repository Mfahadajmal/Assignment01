package io.grpc.internal;

import J.N;
import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.net.Network;
import android.os.Process;
import android.util.Log;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.common.api.internal.BaseLifecycleHelper;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionListener;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.CombinedFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.DesugarCollections;
import j$.util.Optional;
import java.io.InputStream;
import java.lang.Thread;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.android.HandlerContext;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.CronetException;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.CallbackExceptionImpl;
import org.chromium.net.impl.CronetBidirectionalStream;
import org.chromium.net.impl.CronetExceptionImpl;
import org.chromium.net.impl.CronetLibraryLoader;
import org.chromium.net.impl.CronetLogger;
import org.chromium.net.impl.CronetUrlRequest;
import org.chromium.net.impl.CronetUrlRequestContext;
import org.chromium.net.impl.JavaCronetEngine;
import org.chromium.net.impl.JavaUrlRequest;
import org.chromium.net.impl.RefCountDelegate;
import org.chromium.net.impl.VersionSafeCallbacks$RequestFinishedInfoListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RetriableStream implements ClientStream {
    public final Executor callExecutor;
    private Status cancellationStatus;
    public final long channelBufferLimit;
    public final RemoteModelManager channelBufferUsed$ar$class_merging$ar$class_merging;
    public final InsightBuilder closedSubstreamsInsight;
    private final Metadata headers;
    public final HedgingPolicy hedgingPolicy;
    public final AtomicInteger inFlightSubStreams;
    public boolean isClosed;
    public final boolean isHedging;
    public final Executor listenerSerializeExecutor;
    public final AtomicInteger localOnlyTransparentRetries;
    public final Object lock;
    public ClientStreamListener masterListener;
    public final MethodDescriptor method;
    public long nextBackoffIntervalNanos;
    public final AtomicBoolean noMoreTransparentRetry;
    public final long perRpcBufferLimit;
    public long perRpcBufferUsed;
    public final RetryPolicy retryPolicy;
    public OptionalMethod savedCloseMasterListenerReason$ar$class_merging;
    public final ScheduledExecutorService scheduledExecutorService;
    public FutureCanceller scheduledHedging;
    public FutureCanceller scheduledRetry;
    public volatile State state;
    final /* synthetic */ ManagedChannelImpl.ChannelStreamProvider this$1;
    public final Throttle throttle;
    final /* synthetic */ CallOptions val$callOptions;
    final /* synthetic */ Context val$context;
    final /* synthetic */ MethodDescriptor val$method;
    static final Metadata.Key GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key GRPC_RETRY_PUSHBACK_MS = Metadata.Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    public static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    public static final Random random = new Random();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: io.grpc.internal.RetriableStream$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Thread.UncaughtExceptionHandler {
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            r1 = i;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            if (r1 != 0) {
                throw null;
            }
            throw new StatusRuntimeException(Status.fromThrowable(th).withDescription("Uncaught exception in the SynchronizationContext. Re-thrown."));
        }
    }

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.RetriableStream$1CommitTask */
    /* loaded from: classes.dex */
    public final class C1CommitTask implements Runnable {
        final /* synthetic */ RetriableStream this$0;
        final /* synthetic */ Future val$hedgingFuture;
        final /* synthetic */ Future val$retryFuture;
        final /* synthetic */ Collection val$savedDrainedSubstreams;
        final /* synthetic */ boolean val$wasCancelled;
        final /* synthetic */ Substream val$winningSubstream;

        public C1CommitTask(RetriableStream retriableStream, Collection collection, Substream substream, Future future, boolean z, Future future2) {
            this.val$savedDrainedSubstreams = collection;
            this.val$winningSubstream = substream;
            this.val$retryFuture = future;
            this.val$wasCancelled = z;
            this.val$hedgingFuture = future2;
            this.this$0 = retriableStream;
        }

        @Override // java.lang.Runnable
        public final void run() {
            for (Substream substream : this.val$savedDrainedSubstreams) {
                if (substream != this.val$winningSubstream) {
                    substream.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                }
            }
            Future future = this.val$retryFuture;
            if (future != null) {
                future.cancel(false);
                if (!this.val$wasCancelled && this.this$0.inFlightSubStreams.decrementAndGet() == Integer.MIN_VALUE) {
                    RetriableStream retriableStream = this.this$0;
                    retriableStream.listenerSerializeExecutor.execute(new InternalSubchannel$4$1(this, 20));
                }
            }
            Future future2 = this.val$hedgingFuture;
            if (future2 != null) {
                future2.cancel(false);
            }
            this.this$0.postCommit();
        }
    }

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.RetriableStream$1DeadlineEntry */
    /* loaded from: classes.dex */
    final class C1DeadlineEntry implements BufferEntry {
        final /* synthetic */ Object RetriableStream$1DeadlineEntry$ar$val$deadline;
        private final /* synthetic */ int switching_field;

        public C1DeadlineEntry(Object obj, int i) {
            this.switching_field = i;
            this.RetriableStream$1DeadlineEntry$ar$val$deadline = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [io.grpc.Compressor, java.lang.Object] */
        @Override // io.grpc.internal.RetriableStream.BufferEntry
        public final void runWith(Substream substream) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    substream.stream.setDecompressorRegistry((DecompressorRegistry) this.RetriableStream$1DeadlineEntry$ar$val$deadline);
                    return;
                } else {
                    substream.stream.setCompressor(this.RetriableStream$1DeadlineEntry$ar$val$deadline);
                    return;
                }
            }
            substream.stream.setDeadline((Deadline) this.RetriableStream$1DeadlineEntry$ar$val$deadline);
        }
    }

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.RetriableStream$1HalfCloseEntry */
    /* loaded from: classes.dex */
    final class C1HalfCloseEntry implements BufferEntry {
        private final /* synthetic */ int switching_field;

        public C1HalfCloseEntry(int i) {
            this.switching_field = i;
        }

        @Override // io.grpc.internal.RetriableStream.BufferEntry
        public final void runWith(Substream substream) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    substream.stream.optimizeForDirectExecutor();
                    return;
                } else {
                    substream.stream.flush();
                    return;
                }
            }
            substream.stream.halfClose();
        }
    }

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.RetriableStream$1MaxOutboundMessageSizeEntry */
    /* loaded from: classes.dex */
    final class C1MaxOutboundMessageSizeEntry implements BufferEntry {
        private final /* synthetic */ int switching_field;
        final /* synthetic */ int val$maxSize;

        public C1MaxOutboundMessageSizeEntry(int i, int i2) {
            this.switching_field = i2;
            this.val$maxSize = i;
        }

        @Override // io.grpc.internal.RetriableStream.BufferEntry
        public final void runWith(Substream substream) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    substream.stream.request(this.val$maxSize);
                    return;
                } else {
                    substream.stream.setMaxInboundMessageSize(this.val$maxSize);
                    return;
                }
            }
            substream.stream.setMaxOutboundMessageSize(this.val$maxSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: io.grpc.internal.RetriableStream$1SendMessageEntry */
    /* loaded from: classes.dex */
    public final class C1SendMessageEntry implements BufferEntry {
        final /* synthetic */ RetriableStream this$0;
        final /* synthetic */ Object val$message;

        public C1SendMessageEntry(RetriableStream retriableStream, Object obj) {
            r2 = obj;
            r1 = retriableStream;
        }

        @Override // io.grpc.internal.RetriableStream.BufferEntry
        public final void runWith(Substream substream) {
            substream.stream.writeMessage(r1.method.streamRequest(r2));
            substream.stream.flush();
        }
    }

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.RetriableStream$2 */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends OnDeviceFaceMeshLoadLogEvent {
        public AnonymousClass2() {
            super(null);
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent
        public final ClientStreamTracer newClientStreamTracer$ar$ds() {
            return ClientStreamTracer.this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BufferEntry {
        void runWith(Substream substream);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BufferSizeTracer extends ClientStreamTracer {
        long bufferNeeded;
        private final Substream substream;

        public BufferSizeTracer(Substream substream) {
            this.substream = substream;
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceImageLabelCreateLogEvent
        public final void outboundWireSize(long j) {
            Runnable runnable;
            if (RetriableStream.this.state.winningSubstream == null) {
                synchronized (RetriableStream.this.lock) {
                    if (RetriableStream.this.state.winningSubstream == null) {
                        Substream substream = this.substream;
                        if (!substream.closed) {
                            long j2 = this.bufferNeeded + j;
                            this.bufferNeeded = j2;
                            RetriableStream retriableStream = RetriableStream.this;
                            long j3 = retriableStream.perRpcBufferUsed;
                            if (j2 <= j3) {
                                return;
                            }
                            if (j2 > retriableStream.perRpcBufferLimit) {
                                substream.bufferLimitExceeded = true;
                            } else {
                                long addAndGet = retriableStream.channelBufferUsed$ar$class_merging$ar$class_merging.addAndGet(j2 - j3);
                                RetriableStream retriableStream2 = RetriableStream.this;
                                retriableStream2.perRpcBufferUsed = this.bufferNeeded;
                                if (addAndGet > retriableStream2.channelBufferLimit) {
                                    this.substream.bufferLimitExceeded = true;
                                }
                            }
                            Substream substream2 = this.substream;
                            if (substream2.bufferLimitExceeded) {
                                runnable = RetriableStream.this.commit(substream2);
                            } else {
                                runnable = null;
                            }
                            if (runnable != null) {
                                runnable.run();
                            }
                        }
                    }
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FutureCanceller {
        Object RetriableStream$FutureCanceller$ar$future;
        public boolean cancelled;
        public final Object lock;

        public FutureCanceller() {
            this.lock = new Object();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.Queue] */
        public final void add(TaskCompletionListener taskCompletionListener) {
            synchronized (this.lock) {
                if (this.RetriableStream$FutureCanceller$ar$future == null) {
                    this.RetriableStream$FutureCanceller$ar$future = new ArrayDeque();
                }
                this.RetriableStream$FutureCanceller$ar$future.add(taskCompletionListener);
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.Queue] */
        public final void flush(Task task) {
            TaskCompletionListener taskCompletionListener;
            synchronized (this.lock) {
                if (this.RetriableStream$FutureCanceller$ar$future != null && !this.cancelled) {
                    this.cancelled = true;
                    while (true) {
                        synchronized (this.lock) {
                            taskCompletionListener = (TaskCompletionListener) this.RetriableStream$FutureCanceller$ar$future.poll();
                            if (taskCompletionListener == null) {
                                this.cancelled = false;
                                return;
                            }
                        }
                        taskCompletionListener.onComplete(task);
                    }
                }
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.util.concurrent.Future, java.lang.Object] */
        public final Future markCancelled() {
            this.cancelled = true;
            return this.RetriableStream$FutureCanceller$ar$future;
        }

        public final void setFuture(Future future) {
            boolean z;
            synchronized (this.lock) {
                z = this.cancelled;
                if (!z) {
                    this.RetriableStream$FutureCanceller$ar$future = future;
                }
            }
            if (z) {
                future.cancel(false);
            }
        }

        public FutureCanceller(Object obj) {
            this.lock = obj;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HedgingPlan {
        public final Object RetriableStream$HedgingPlan$ar$hedgingPushbackMillis;
        public final boolean isHedgeable;

        public HedgingPlan(Object obj, boolean z) {
            this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis = obj;
            this.isHedgeable = z;
        }

        public final ListenableFuture call(Callable callable, Executor executor) {
            return new CombinedFuture((ImmutableCollection) this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis, this.isHedgeable, executor, callable);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List, java.lang.Object] */
        public final int getWindowIndex(AccessibilityWindowInfo accessibilityWindowInfo) {
            if (accessibilityWindowInfo == null) {
                return -1;
            }
            int size = this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis.size();
            for (int i = 0; i < size; i++) {
                if (accessibilityWindowInfo.equals(this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis.get(i))) {
                    return i;
                }
            }
            return -1;
        }

        public HedgingPlan(boolean z, ImmutableList immutableList) {
            this.isHedgeable = z;
            this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis = immutableList;
        }

        public HedgingPlan(boolean z, Object obj) {
            this.isHedgeable = z;
            this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis = obj;
        }

        public HedgingPlan(RetryingNameResolver.ResolutionResultListener resolutionResultListener, boolean z) {
            resolutionResultListener.getClass();
            this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis = resolutionResultListener;
            this.isHedgeable = z;
        }

        public HedgingPlan(AccessibilityService accessibilityService) {
            ArrayList arrayList = new ArrayList();
            this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis = arrayList;
            boolean isScreenLayoutRTL = SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(accessibilityService);
            this.isHedgeable = isScreenLayoutRTL;
            List windows = SpannableUtils$IdentifierSpan.getWindows(accessibilityService);
            arrayList.clear();
            Optional.ofNullable(windows).ifPresent(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(arrayList, 15));
            Collections.sort(arrayList, new Comparator(isScreenLayoutRTL) { // from class: com.google.android.accessibility.talkback.focusmanagement.WindowTraversal$WindowOrderComparator
                private final boolean mIsInRTL;

                {
                    this.mIsInRTL = isScreenLayoutRTL;
                }

                private static int compareBounds(AccessibilityWindowInfo accessibilityWindowInfo, AccessibilityWindowInfo accessibilityWindowInfo2, boolean z) {
                    int i;
                    int i2;
                    Rect rect = new Rect();
                    Rect rect2 = new Rect();
                    accessibilityWindowInfo.getBoundsInScreen(rect);
                    accessibilityWindowInfo2.getBoundsInScreen(rect2);
                    if (rect.top != rect2.top) {
                        return rect.top - rect2.top;
                    }
                    if (z) {
                        i = rect2.right;
                        i2 = rect.right;
                    } else {
                        i = rect.left;
                        i2 = rect2.left;
                    }
                    return i - i2;
                }

                @Override // java.util.Comparator
                public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                    int displayId;
                    int displayId2;
                    int displayId3;
                    int displayId4;
                    AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) obj;
                    AccessibilityWindowInfo accessibilityWindowInfo2 = (AccessibilityWindowInfo) obj2;
                    if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
                        displayId = accessibilityWindowInfo.getDisplayId();
                        displayId2 = accessibilityWindowInfo2.getDisplayId();
                        if (displayId != displayId2) {
                            displayId3 = accessibilityWindowInfo.getDisplayId();
                            displayId4 = accessibilityWindowInfo2.getDisplayId();
                            return displayId3 - displayId4;
                        }
                    }
                    if (accessibilityWindowInfo.getType() == accessibilityWindowInfo2.getType()) {
                        return compareBounds(accessibilityWindowInfo, accessibilityWindowInfo2, this.mIsInRTL);
                    }
                    if (accessibilityWindowInfo.getType() == 3) {
                        return 1;
                    }
                    if (accessibilityWindowInfo2.getType() == 3) {
                        return -1;
                    }
                    return compareBounds(accessibilityWindowInfo, accessibilityWindowInfo2, this.mIsInRTL);
                }
            });
        }

        public HedgingPlan() {
            this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis = new ArrayList();
            this.isHedgeable = true;
        }

        public HedgingPlan(List list, boolean z) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(!list.isEmpty(), "APIs must not be empty.");
            this.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis = list;
            this.isHedgeable = true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RetryPlan {
        final long backoffNanos;
        final boolean shouldRetry;

        public RetryPlan(boolean z, long j) {
            this.shouldRetry = z;
            this.backoffNanos = j;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StartEntry implements BufferEntry {
        public StartEntry() {
        }

        @Override // io.grpc.internal.RetriableStream.BufferEntry
        public final void runWith(Substream substream) {
            substream.stream.start(new Sublistener(substream));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State {
        final Collection activeHedges;
        final List buffer;
        final boolean cancelled;
        final Collection drainedSubstreams;
        public final int hedgingAttemptCount;
        final boolean hedgingFrozen;
        final boolean passThrough;
        final Substream winningSubstream;

        public State(List list, Collection collection, Collection collection2, Substream substream, boolean z, boolean z2, boolean z3, int i) {
            boolean z4;
            boolean z5;
            boolean z6;
            this.buffer = list;
            collection.getClass();
            this.drainedSubstreams = collection;
            this.winningSubstream = substream;
            this.activeHedges = collection2;
            this.cancelled = z;
            this.passThrough = z2;
            this.hedgingFrozen = z3;
            this.hedgingAttemptCount = i;
            if (z2 && list != null) {
                z4 = false;
            } else {
                z4 = true;
            }
            ContextDataProvider.checkState(z4, "passThrough should imply buffer is null");
            if (z2 && substream == null) {
                z5 = false;
            } else {
                z5 = true;
            }
            ContextDataProvider.checkState(z5, "passThrough should imply winningSubstream != null");
            if (z2 && ((collection.size() != 1 || !collection.contains(substream)) && (collection.size() != 0 || !substream.closed))) {
                z6 = false;
            } else {
                z6 = true;
            }
            ContextDataProvider.checkState(z6, "passThrough should imply winningSubstream is drained");
            ContextDataProvider.checkState((z && substream == null) ? false : true, "cancelled should imply committed");
        }

        public final State addActiveHedge(Substream substream) {
            boolean z;
            Collection unmodifiableCollection;
            ContextDataProvider.checkState(!this.hedgingFrozen, "hedging frozen");
            if (this.winningSubstream == null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "already committed");
            Collection collection = this.activeHedges;
            if (collection == null) {
                unmodifiableCollection = Collections.singleton(substream);
            } else {
                ArrayList arrayList = new ArrayList(collection);
                arrayList.add(substream);
                unmodifiableCollection = DesugarCollections.unmodifiableCollection(arrayList);
            }
            return new State(this.buffer, this.drainedSubstreams, unmodifiableCollection, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount + 1);
        }

        public final State freezeHedging() {
            if (this.hedgingFrozen) {
                return this;
            }
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, true, this.hedgingAttemptCount);
        }

        final State substreamDrained(Substream substream) {
            Collection unmodifiableCollection;
            boolean z;
            boolean z2 = true;
            ContextDataProvider.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                unmodifiableCollection = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                unmodifiableCollection = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                unmodifiableCollection = DesugarCollections.unmodifiableCollection(arrayList);
            }
            Collection collection = unmodifiableCollection;
            Substream substream2 = this.winningSubstream;
            if (substream2 != null) {
                z = true;
            } else {
                z = false;
            }
            List list = this.buffer;
            if (z) {
                if (substream2 != substream) {
                    z2 = false;
                }
                ContextDataProvider.checkState(z2, "Another RPC attempt has already committed");
                list = null;
            }
            return new State(list, collection, this.activeHedges, this.winningSubstream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Sublistener implements ClientStreamListener {
        final Substream substream;

        /* compiled from: PG */
        /* renamed from: io.grpc.internal.RetriableStream$Sublistener$4 */
        /* loaded from: classes.dex */
        public final class AnonymousClass4 implements Runnable {
            final /* synthetic */ Object RetriableStream$Sublistener$4$ar$this$1;
            final /* synthetic */ Object RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging;
            private final /* synthetic */ int switching_field;

            public AnonymousClass4(Sublistener sublistener, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, int i) {
                this.switching_field = i;
                this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging = onDeviceTextDetectionLoadLogEvent;
                this.RetriableStream$Sublistener$4$ar$this$1 = sublistener;
            }

            /* JADX WARN: Type inference failed for: r0v35, types: [java.lang.Object, java.lang.Runnable] */
            /* JADX WARN: Type inference failed for: r0v36, types: [org.chromium.net.impl.JavaUrlRequestUtils$CheckedRunnable, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v38, types: [org.chromium.net.impl.JavaUrlRequestUtils$CheckedRunnable, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v40, types: [org.chromium.net.impl.JavaUrlRequestUtils$CheckedRunnable, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v23, types: [java.lang.Object, java.lang.Runnable] */
            @Override // java.lang.Runnable
            public final void run() {
                switch (this.switching_field) {
                    case 0:
                        RetriableStream.this.masterListener.messagesAvailable$ar$class_merging$ar$class_merging((OnDeviceTextDetectionLoadLogEvent) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging);
                        return;
                    case 1:
                        RetriableStream.this.drain((Substream) this.RetriableStream$Sublistener$4$ar$this$1);
                        return;
                    case 2:
                        Object obj = this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging;
                        CancellableContinuationImpl cancellableContinuationImpl = (CancellableContinuationImpl) obj;
                        cancellableContinuationImpl.resumeUndispatched((CoroutineDispatcher) this.RetriableStream$Sublistener$4$ar$this$1, Unit.INSTANCE);
                        return;
                    case 3:
                        NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkDisconnect(NetworkChangeNotifierAutoDetect.networkToNetId((Network) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging));
                        return;
                    case 4:
                        synchronized (((CronetBidirectionalStream) this.RetriableStream$Sublistener$4$ar$this$1).mNativeStreamLock) {
                            if (((CronetBidirectionalStream) this.RetriableStream$Sublistener$4$ar$this$1).isDoneLocked()) {
                                return;
                            }
                            try {
                                Object obj2 = this.RetriableStream$Sublistener$4$ar$this$1;
                                ((CronetBidirectionalStream) obj2).mCallback.onResponseTrailersReceived((BidirectionalStream) obj2, ((CronetBidirectionalStream) obj2).mResponseInfo, (UrlResponseInfo.HeaderBlock) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging);
                                return;
                            } catch (Exception e) {
                                ((CronetBidirectionalStream) this.RetriableStream$Sublistener$4$ar$this$1).onNonfinalCallbackException(e);
                                return;
                            }
                        }
                    case 5:
                        ((CronetBidirectionalStream) this.RetriableStream$Sublistener$4$ar$this$1).failWithExceptionOnExecutor((CronetException) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging);
                        return;
                    case 6:
                        String str = CronetUrlRequestContext.LOG_TAG;
                        Object obj3 = this.RetriableStream$Sublistener$4$ar$this$1;
                        try {
                            try {
                                this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging.run();
                            } finally {
                                if (obj3 != null) {
                                    ((RefCountDelegate) obj3).decrement();
                                }
                            }
                        } catch (Exception e2) {
                            Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Exception thrown from observation task", e2);
                        }
                        if (obj3 != null) {
                            return;
                        } else {
                            return;
                        }
                    case 7:
                        synchronized (((CronetUrlRequestContext) this.RetriableStream$Sublistener$4$ar$this$1).mLock) {
                            Object obj4 = this.RetriableStream$Sublistener$4$ar$this$1;
                            N.M6Dz0nZ5(((CronetUrlRequestContext) obj4).mUrlRequestContextAdapter, obj4);
                        }
                        Object obj5 = this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging;
                        if (obj5 != null) {
                            CronetLibraryLoader.CronetInitializedInfo cronetInitializedInfo = CronetLibraryLoader.sInitializedInfo;
                            CronetUrlRequestContext.CronetInitializedInfoLogger cronetInitializedInfoLogger = (CronetUrlRequestContext.CronetInitializedInfoLogger) obj5;
                            cronetInitializedInfoLogger.mCronetInitializedInfo.httpFlagsLatencyMillis = cronetInitializedInfo.httpFlagsLatencyMillis;
                            Boolean bool = cronetInitializedInfo.httpFlagsSuccessful;
                            CronetLogger.CronetInitializedInfo cronetInitializedInfo2 = cronetInitializedInfoLogger.mCronetInitializedInfo;
                            cronetInitializedInfo2.httpFlagsSuccessful = bool;
                            cronetInitializedInfo2.httpFlagsNames = cronetInitializedInfo.httpFlagsNames;
                            cronetInitializedInfo2.httpFlagsValues = cronetInitializedInfo.httpFlagsValues;
                            CronetLogger.CronetInitializedInfo cronetInitializedInfo3 = cronetInitializedInfoLogger.mCronetInitializedInfo;
                            int elapsedTime = cronetInitializedInfoLogger.getElapsedTime();
                            synchronized (cronetInitializedInfo3) {
                                ((CronetUrlRequestContext.CronetInitializedInfoLogger) obj5).mCronetInitializedInfo.engineAsyncLatencyMillis = elapsedTime;
                                ((CronetUrlRequestContext.CronetInitializedInfoLogger) obj5).maybeLog();
                            }
                            return;
                        }
                        return;
                    case 8:
                        ((VersionSafeCallbacks$RequestFinishedInfoListener) this.RetriableStream$Sublistener$4$ar$this$1).onRequestFinished((RequestFinishedInfo) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging);
                        return;
                    case 9:
                        Thread.currentThread().setName("JavaCronetEngine");
                        Process.setThreadPriority(((JavaCronetEngine.AnonymousClass1) this.RetriableStream$Sublistener$4$ar$this$1).val$threadPriority);
                        this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging.run();
                        return;
                    case 10:
                        try {
                            this.RetriableStream$Sublistener$4$ar$this$1.run();
                            return;
                        } catch (Throwable th) {
                            ((JavaUrlRequest) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging).enterErrorState(new CronetExceptionImpl("System error", th));
                            return;
                        }
                    case 11:
                        try {
                            this.RetriableStream$Sublistener$4$ar$this$1.run();
                            return;
                        } catch (Throwable th2) {
                            ((JavaUrlRequest) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging).enterUploadErrorState(th2);
                            return;
                        }
                    case 12:
                        try {
                            this.RetriableStream$Sublistener$4$ar$this$1.run();
                            return;
                        } catch (Throwable th3) {
                            Object obj6 = this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging;
                            CronetUrlRequest.AnonymousClass8 anonymousClass8 = new CronetUrlRequest.AnonymousClass8(obj6, 6);
                            JavaUrlRequest javaUrlRequest = (JavaUrlRequest) obj6;
                            javaUrlRequest.mExecutor.execute(anonymousClass8);
                            javaUrlRequest.enterErrorState(new CallbackExceptionImpl("Exception received from UrlRequest.Callback", th3));
                            return;
                        }
                    case 13:
                        Object obj7 = this.RetriableStream$Sublistener$4$ar$this$1;
                        Object obj8 = this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging;
                        ((JavaUrlRequest) obj8).mExecutor.execute(new AnonymousClass4(obj8, obj7, 10, (byte[]) null));
                        return;
                    case 14:
                        Object obj9 = this.RetriableStream$Sublistener$4$ar$this$1;
                        JavaUrlRequest javaUrlRequest2 = (JavaUrlRequest) obj9;
                        javaUrlRequest2.mPendingRedirectUrl = URI.create(javaUrlRequest2.mCurrentUrl).resolve((String) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging).toString();
                        javaUrlRequest2.mUrlChain.add(javaUrlRequest2.mPendingRedirectUrl);
                        javaUrlRequest2.transitionStates(2, 3, new CronetUrlRequest.AnonymousClass8(obj9, 4));
                        return;
                    case 15:
                        ((JavaUrlRequest.AsyncUrlRequestCallback) this.RetriableStream$Sublistener$4$ar$this$1).lambda$onSucceeded$6((UrlResponseInfo) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging);
                        return;
                    default:
                        ((JavaUrlRequest.AsyncUrlRequestCallback) this.RetriableStream$Sublistener$4$ar$this$1).lambda$onCanceled$5((UrlResponseInfo) this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging);
                        return;
                }
            }

            public AnonymousClass4(Sublistener sublistener, Substream substream, int i) {
                this.switching_field = i;
                this.RetriableStream$Sublistener$4$ar$this$1 = substream;
                this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging = sublistener;
            }

            public AnonymousClass4(Object obj, Object obj2, int i) {
                this.switching_field = i;
                this.RetriableStream$Sublistener$4$ar$this$1 = obj;
                this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging = obj2;
            }

            public /* synthetic */ AnonymousClass4(Object obj, Object obj2, int i, byte[] bArr) {
                this.switching_field = i;
                this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging = obj;
                this.RetriableStream$Sublistener$4$ar$this$1 = obj2;
            }

            public /* synthetic */ AnonymousClass4(Object obj, Object obj2, int i, char[] cArr) {
                this.switching_field = i;
                this.RetriableStream$Sublistener$4$ar$this$1 = obj;
                this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging = obj2;
            }

            public AnonymousClass4(CancellableContinuationImpl cancellableContinuationImpl, HandlerContext handlerContext, int i) {
                this.switching_field = i;
                this.RetriableStream$Sublistener$4$ar$val$producer$ar$class_merging = cancellableContinuationImpl;
                this.RetriableStream$Sublistener$4$ar$this$1 = handlerContext;
            }
        }

        public Sublistener(Substream substream) {
            this.substream = substream;
        }

        private static final Integer getPushbackMills$ar$ds(Metadata metadata) {
            String str = (String) metadata.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
            if (str != null) {
                try {
                    return Integer.valueOf(str);
                } catch (NumberFormatException unused) {
                    return -1;
                }
            }
            return null;
        }

        @Override // io.grpc.internal.ClientStreamListener
        public final void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            boolean z;
            boolean z2;
            boolean z3;
            RetryPlan retryPlan;
            FutureCanceller futureCanceller;
            boolean z4;
            synchronized (RetriableStream.this.lock) {
                RetriableStream retriableStream = RetriableStream.this;
                State state = retriableStream.state;
                Substream substream = this.substream;
                z = true;
                substream.closed = true;
                if (state.drainedSubstreams.contains(substream)) {
                    ArrayList arrayList = new ArrayList(state.drainedSubstreams);
                    arrayList.remove(substream);
                    state = new State(state.buffer, DesugarCollections.unmodifiableCollection(arrayList), state.activeHedges, state.winningSubstream, state.cancelled, state.passThrough, state.hedgingFrozen, state.hedgingAttemptCount);
                }
                retriableStream.state = state;
                RetriableStream.this.closedSubstreamsInsight.append$ar$ds(status.code);
            }
            if (RetriableStream.this.inFlightSubStreams.decrementAndGet() == Integer.MIN_VALUE) {
                RetriableStream.this.listenerSerializeExecutor.execute(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 2));
                return;
            }
            Substream substream2 = this.substream;
            if (substream2.bufferLimitExceeded) {
                RetriableStream.this.commitAndRun(substream2);
                if (RetriableStream.this.state.winningSubstream == this.substream) {
                    RetriableStream.this.safeCloseMasterListener(status, rpcProgress, metadata);
                    return;
                }
                return;
            }
            if (rpcProgress == ClientStreamListener.RpcProgress.MISCARRIED && RetriableStream.this.localOnlyTransparentRetries.incrementAndGet() > 1000) {
                RetriableStream.this.commitAndRun(this.substream);
                if (RetriableStream.this.state.winningSubstream == this.substream) {
                    RetriableStream.this.safeCloseMasterListener(Status.INTERNAL.withDescription("Too many transparent retries. Might be a bug in gRPC").withCause(new StatusRuntimeException(status)), rpcProgress, metadata);
                    return;
                }
                return;
            }
            if (RetriableStream.this.state.winningSubstream == null) {
                if (rpcProgress != ClientStreamListener.RpcProgress.MISCARRIED && (rpcProgress != ClientStreamListener.RpcProgress.REFUSED || !RetriableStream.this.noMoreTransparentRetry.compareAndSet(false, true))) {
                    if (rpcProgress == ClientStreamListener.RpcProgress.DROPPED) {
                        RetriableStream retriableStream2 = RetriableStream.this;
                        if (retriableStream2.isHedging) {
                            retriableStream2.freezeHedging();
                        }
                    } else {
                        RetriableStream.this.noMoreTransparentRetry.set(true);
                        RetriableStream retriableStream3 = RetriableStream.this;
                        if (retriableStream3.isHedging) {
                            Integer pushbackMills$ar$ds = getPushbackMills$ar$ds(metadata);
                            boolean z5 = !RetriableStream.this.hedgingPolicy.nonFatalStatusCodes.contains(status.code);
                            if (RetriableStream.this.throttle != null && (!z5 || (pushbackMills$ar$ds != null && pushbackMills$ar$ds.intValue() < 0))) {
                                z4 = !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
                            } else {
                                z4 = false;
                            }
                            if (!z5 && !z4 && !status.isOk() && pushbackMills$ar$ds != null && pushbackMills$ar$ds.intValue() > 0) {
                                pushbackMills$ar$ds = 0;
                            }
                            if (z5 || z4) {
                                z = false;
                            }
                            HedgingPlan hedgingPlan = new HedgingPlan(z, pushbackMills$ar$ds);
                            if (hedgingPlan.isHedgeable) {
                                RetriableStream retriableStream4 = RetriableStream.this;
                                Object obj = hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis;
                                if (obj != null) {
                                    if (((Integer) obj).intValue() < 0) {
                                        retriableStream4.freezeHedging();
                                    } else {
                                        synchronized (retriableStream4.lock) {
                                            FutureCanceller futureCanceller2 = retriableStream4.scheduledHedging;
                                            if (futureCanceller2 != null) {
                                                Future markCancelled = futureCanceller2.markCancelled();
                                                FutureCanceller futureCanceller3 = new FutureCanceller(retriableStream4.lock);
                                                retriableStream4.scheduledHedging = futureCanceller3;
                                                if (markCancelled != null) {
                                                    markCancelled.cancel(false);
                                                }
                                                futureCanceller3.setFuture(retriableStream4.scheduledExecutorService.schedule(new BaseLifecycleHelper.ConnectionFailedResolver(retriableStream4, futureCanceller3, 2), r7.intValue(), TimeUnit.MILLISECONDS));
                                            }
                                        }
                                    }
                                }
                            }
                            synchronized (RetriableStream.this.lock) {
                                RetriableStream retriableStream5 = RetriableStream.this;
                                State state2 = retriableStream5.state;
                                Substream substream3 = this.substream;
                                ArrayList arrayList2 = new ArrayList(state2.activeHedges);
                                arrayList2.remove(substream3);
                                retriableStream5.state = new State(state2.buffer, state2.drainedSubstreams, DesugarCollections.unmodifiableCollection(arrayList2), state2.winningSubstream, state2.cancelled, state2.passThrough, state2.hedgingFrozen, state2.hedgingAttemptCount);
                                if (hedgingPlan.isHedgeable) {
                                    RetriableStream retriableStream6 = RetriableStream.this;
                                    if (retriableStream6.hasPotentialHedging(retriableStream6.state) || !RetriableStream.this.state.activeHedges.isEmpty()) {
                                        return;
                                    }
                                }
                            }
                        } else {
                            RetryPolicy retryPolicy = retriableStream3.retryPolicy;
                            long j = 0;
                            if (retryPolicy == null) {
                                retryPlan = new RetryPlan(false, 0L);
                            } else {
                                boolean contains = retryPolicy.retryableStatusCodes.contains(status.code);
                                Integer pushbackMills$ar$ds2 = getPushbackMills$ar$ds(metadata);
                                if (RetriableStream.this.throttle != null && (contains || (pushbackMills$ar$ds2 != null && pushbackMills$ar$ds2.intValue() < 0))) {
                                    z2 = !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
                                } else {
                                    z2 = false;
                                }
                                if (RetriableStream.this.retryPolicy.maxAttempts > this.substream.previousAttemptCount + 1 && !z2) {
                                    if (pushbackMills$ar$ds2 == null) {
                                        if (contains) {
                                            double nextDouble = r11.nextBackoffIntervalNanos * RetriableStream.random.nextDouble();
                                            RetriableStream retriableStream7 = RetriableStream.this;
                                            double d = retriableStream7.nextBackoffIntervalNanos;
                                            RetryPolicy retryPolicy2 = retriableStream7.retryPolicy;
                                            retriableStream7.nextBackoffIntervalNanos = Math.min((long) (d * retryPolicy2.backoffMultiplier), retryPolicy2.maxBackoffNanos);
                                            j = (long) nextDouble;
                                            z3 = true;
                                        }
                                    } else if (pushbackMills$ar$ds2.intValue() >= 0) {
                                        j = TimeUnit.MILLISECONDS.toNanos(pushbackMills$ar$ds2.intValue());
                                        RetriableStream retriableStream8 = RetriableStream.this;
                                        retriableStream8.nextBackoffIntervalNanos = retriableStream8.retryPolicy.initialBackoffNanos;
                                        z3 = true;
                                    }
                                    retryPlan = new RetryPlan(z3, j);
                                }
                                z3 = false;
                                retryPlan = new RetryPlan(z3, j);
                            }
                            if (retryPlan.shouldRetry) {
                                Substream createSubstream = RetriableStream.this.createSubstream(this.substream.previousAttemptCount + 1, false);
                                if (createSubstream != null) {
                                    synchronized (RetriableStream.this.lock) {
                                        RetriableStream retriableStream9 = RetriableStream.this;
                                        futureCanceller = new FutureCanceller(retriableStream9.lock);
                                        retriableStream9.scheduledRetry = futureCanceller;
                                    }
                                    futureCanceller.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, futureCanceller, createSubstream, 18), retryPlan.backoffNanos, TimeUnit.NANOSECONDS));
                                    return;
                                }
                                return;
                            }
                        }
                    }
                } else {
                    Substream createSubstream2 = RetriableStream.this.createSubstream(this.substream.previousAttemptCount, true);
                    if (createSubstream2 != null) {
                        RetriableStream retriableStream10 = RetriableStream.this;
                        if (retriableStream10.isHedging) {
                            synchronized (retriableStream10.lock) {
                                RetriableStream retriableStream11 = RetriableStream.this;
                                State state3 = retriableStream11.state;
                                Substream substream4 = this.substream;
                                ArrayList arrayList3 = new ArrayList(state3.activeHedges);
                                arrayList3.remove(substream4);
                                arrayList3.add(createSubstream2);
                                retriableStream11.state = new State(state3.buffer, state3.drainedSubstreams, DesugarCollections.unmodifiableCollection(arrayList3), state3.winningSubstream, state3.cancelled, state3.passThrough, state3.hedgingFrozen, state3.hedgingAttemptCount);
                            }
                        }
                        RetriableStream.this.callExecutor.execute(new AnonymousClass4(this, createSubstream2, 1));
                        return;
                    }
                    return;
                }
            }
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                RetriableStream.this.safeCloseMasterListener(status, rpcProgress, metadata);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0047, code lost:
        
            if (r0.tokenCount.compareAndSet(r1, java.lang.Math.min(r0.tokenRatio + r1, r2)) == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0049, code lost:
        
            r0 = io.grpc.internal.RetriableStream.this;
            r0.listenerSerializeExecutor.execute(new io.grpc.internal.DelayedClientCall.AnonymousClass4(r5, r6, 20, null));
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0058, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x002d, code lost:
        
            if (r0 != null) goto L29;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x002f, code lost:
        
            r1 = r0.tokenCount;
            r2 = r0.maxTokens;
            r1 = r1.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0037, code lost:
        
            if (r1 != r2) goto L32;
         */
        @Override // io.grpc.internal.ClientStreamListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void headersRead(io.grpc.Metadata r6) {
            /*
                r5 = this;
                io.grpc.internal.RetriableStream$Substream r0 = r5.substream
                int r0 = r0.previousAttemptCount
                if (r0 <= 0) goto L18
                io.grpc.Metadata$Key r0 = io.grpc.internal.RetriableStream.GRPC_PREVIOUS_RPC_ATTEMPTS
                r6.discardAll(r0)
                io.grpc.internal.RetriableStream$Substream r0 = r5.substream
                io.grpc.Metadata$Key r1 = io.grpc.internal.RetriableStream.GRPC_PREVIOUS_RPC_ATTEMPTS
                int r0 = r0.previousAttemptCount
                java.lang.String r0 = java.lang.String.valueOf(r0)
                r6.put(r1, r0)
            L18:
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                r0.commitAndRun(r1)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                if (r0 != r1) goto L58
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Throttle r0 = r0.throttle
                if (r0 == 0) goto L49
            L2f:
                java.util.concurrent.atomic.AtomicInteger r1 = r0.tokenCount
                int r2 = r0.maxTokens
                int r1 = r1.get()
                if (r1 != r2) goto L3a
                goto L49
            L3a:
                int r3 = r0.tokenRatio
                java.util.concurrent.atomic.AtomicInteger r4 = r0.tokenCount
                int r3 = r3 + r1
                int r2 = java.lang.Math.min(r3, r2)
                boolean r1 = r4.compareAndSet(r1, r2)
                if (r1 == 0) goto L2f
            L49:
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.DelayedClientCall$4 r1 = new io.grpc.internal.DelayedClientCall$4
                r2 = 20
                r3 = 0
                r1.<init>(r5, r6, r2, r3)
                java.util.concurrent.Executor r6 = r0.listenerSerializeExecutor
                r6.execute(r1)
            L58:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.Sublistener.headersRead(io.grpc.Metadata):void");
        }

        @Override // io.grpc.internal.StreamListener
        public final void messagesAvailable$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
            boolean z;
            State state = RetriableStream.this.state;
            if (state.winningSubstream != null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "Headers should be received prior to messages.");
            if (state.winningSubstream != this.substream) {
                GrpcUtil.closeQuietly$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent);
                return;
            }
            RetriableStream.this.listenerSerializeExecutor.execute(new AnonymousClass4(this, onDeviceTextDetectionLoadLogEvent, 0));
        }

        @Override // io.grpc.internal.StreamListener
        public final void onReady() {
            if (!RetriableStream.this.isReady()) {
                return;
            }
            RetriableStream retriableStream = RetriableStream.this;
            retriableStream.listenerSerializeExecutor.execute(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 3));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Substream {
        boolean bufferLimitExceeded;
        boolean closed;
        final int previousAttemptCount;
        ClientStream stream;

        public Substream(int i) {
            this.previousAttemptCount = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Throttle {
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount;
        final int tokenRatio;

        public Throttle(float f, float f2) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.tokenCount = atomicInteger;
            this.tokenRatio = (int) (f2 * 1000.0f);
            int i = (int) (f * 1000.0f);
            this.maxTokens = i;
            this.threshold = i / 2;
            atomicInteger.set(i);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Throttle)) {
                return false;
            }
            Throttle throttle = (Throttle) obj;
            if (this.maxTokens == throttle.maxTokens && this.tokenRatio == throttle.tokenRatio) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio)});
        }

        public final boolean isAboveThreshold() {
            if (this.tokenCount.get() > this.threshold) {
                return true;
            }
            return false;
        }

        final boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                if (i == 0) {
                    return false;
                }
                i2 = i - 1000;
            } while (!this.tokenCount.compareAndSet(i, Math.max(i2, 0)));
            if (i2 <= this.threshold) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: -$$Nest$fputisClosed$ar$ds */
    public static /* bridge */ /* synthetic */ void m244$$Nest$fputisClosed$ar$ds(RetriableStream retriableStream) {
        retriableStream.isClosed = true;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public RetriableStream(io.grpc.internal.ManagedChannelImpl.ChannelStreamProvider r15, io.grpc.MethodDescriptor r16, io.grpc.Metadata r17, io.grpc.CallOptions r18, io.grpc.internal.RetryPolicy r19, io.grpc.internal.HedgingPolicy r20, io.grpc.Context r21) {
        /*
            r14 = this;
            r13 = r14
            r0 = r15
            r1 = r18
            r2 = r16
            r13.val$method = r2
            r13.val$callOptions = r1
            r3 = r21
            r13.val$context = r3
            r13.this$1 = r0
            io.grpc.internal.ManagedChannelImpl r3 = io.grpc.internal.ManagedChannelImpl.this
            com.google.mlkit.common.model.RemoteModelManager r4 = r3.channelBufferUsed$ar$class_merging$ar$class_merging
            long r5 = r3.perRpcBufferLimit
            long r7 = r3.channelBufferLimit
            java.util.concurrent.Executor r9 = r3.getCallExecutor(r1)
            io.grpc.internal.ManagedChannelImpl r1 = io.grpc.internal.ManagedChannelImpl.this
            io.grpc.internal.ClientTransportFactory r1 = r1.transportFactory
            java.util.concurrent.ScheduledExecutorService r10 = r1.getScheduledExecutorService()
            io.grpc.internal.RetriableStream$Throttle r12 = r0.throttle
            r0 = r14
            r1 = r16
            r2 = r17
            r3 = r4
            r4 = r5
            r6 = r7
            r8 = r9
            r9 = r10
            r10 = r19
            r11 = r20
            r0.<init>(r1, r2, r3, r4, r6, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.<init>(io.grpc.internal.ManagedChannelImpl$ChannelStreamProvider, io.grpc.MethodDescriptor, io.grpc.Metadata, io.grpc.CallOptions, io.grpc.internal.RetryPolicy, io.grpc.internal.HedgingPolicy, io.grpc.Context):void");
    }

    @Override // io.grpc.internal.ClientStream
    public final void appendTimeoutInsight(InsightBuilder insightBuilder) {
        State state;
        synchronized (this.lock) {
            insightBuilder.appendKeyValue$ar$ds("closed", this.closedSubstreamsInsight);
            state = this.state;
        }
        if (state.winningSubstream != null) {
            InsightBuilder insightBuilder2 = new InsightBuilder();
            state.winningSubstream.stream.appendTimeoutInsight(insightBuilder2);
            insightBuilder.appendKeyValue$ar$ds("committed", insightBuilder2);
            return;
        }
        InsightBuilder insightBuilder3 = new InsightBuilder();
        for (Substream substream : state.drainedSubstreams) {
            InsightBuilder insightBuilder4 = new InsightBuilder();
            substream.stream.appendTimeoutInsight(insightBuilder4);
            insightBuilder3.append$ar$ds(insightBuilder4);
        }
        insightBuilder.appendKeyValue$ar$ds("open", insightBuilder3);
    }

    @Override // io.grpc.internal.ClientStream
    public final void cancel(Status status) {
        Substream substream;
        Substream substream2 = new Substream(0);
        substream2.stream = new NoopClientStream();
        Runnable commit = commit(substream2);
        if (commit != null) {
            synchronized (this.lock) {
                this.state = this.state.substreamDrained(substream2);
            }
            commit.run();
            safeCloseMasterListener(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
            return;
        }
        synchronized (this.lock) {
            if (this.state.drainedSubstreams.contains(this.state.winningSubstream)) {
                substream = this.state.winningSubstream;
            } else {
                this.cancellationStatus = status;
                substream = null;
            }
            State state = this.state;
            this.state = new State(state.buffer, state.drainedSubstreams, state.activeHedges, state.winningSubstream, true, state.passThrough, state.hedgingFrozen, state.hedgingAttemptCount);
        }
        if (substream != null) {
            substream.stream.cancel(status);
        }
    }

    public final Runnable commit(Substream substream) {
        boolean z;
        List list;
        Collection emptyList;
        boolean z2;
        boolean z3;
        Future future;
        Future future2;
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            Collection collection = this.state.drainedSubstreams;
            State state = this.state;
            if (state.winningSubstream == null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z, "Already committed");
            List list2 = state.buffer;
            if (state.drainedSubstreams.contains(substream)) {
                list = null;
                emptyList = Collections.singleton(substream);
                z2 = true;
            } else {
                list = list2;
                emptyList = Collections.emptyList();
                z2 = false;
            }
            this.state = new State(list, emptyList, state.activeHedges, substream, state.cancelled, z2, state.hedgingFrozen, state.hedgingAttemptCount);
            this.channelBufferUsed$ar$class_merging$ar$class_merging.addAndGet(-this.perRpcBufferUsed);
            FutureCanceller futureCanceller = this.scheduledRetry;
            if (futureCanceller != null) {
                z3 = futureCanceller.cancelled;
            } else {
                z3 = false;
            }
            if (futureCanceller != null) {
                Future markCancelled = futureCanceller.markCancelled();
                this.scheduledRetry = null;
                future = markCancelled;
            } else {
                future = null;
            }
            FutureCanceller futureCanceller2 = this.scheduledHedging;
            if (futureCanceller2 != null) {
                future2 = futureCanceller2.markCancelled();
                this.scheduledHedging = null;
            } else {
                future2 = null;
            }
            return new C1CommitTask(this, collection, substream, future, z3, future2);
        }
    }

    public final void commitAndRun(Substream substream) {
        Runnable commit = commit(substream);
        if (commit != null) {
            this.callExecutor.execute(commit);
        }
    }

    public final Substream createSubstream(int i, boolean z) {
        int i2;
        do {
            i2 = this.inFlightSubStreams.get();
            if (i2 < 0) {
                return null;
            }
        } while (!this.inFlightSubStreams.compareAndSet(i2, i2 + 1));
        Substream substream = new Substream(i);
        AnonymousClass2 anonymousClass2 = new OnDeviceFaceMeshLoadLogEvent() { // from class: io.grpc.internal.RetriableStream.2
            public AnonymousClass2() {
                super(null);
            }

            @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent
            public final ClientStreamTracer newClientStreamTracer$ar$ds() {
                return ClientStreamTracer.this;
            }
        };
        Metadata metadata = this.headers;
        Metadata metadata2 = new Metadata();
        metadata2.merge(metadata);
        if (i > 0) {
            metadata2.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        substream.stream = newSubstream$ar$class_merging$ar$class_merging(metadata2, anonymousClass2, i, z);
        return substream;
    }

    public final void delayOrExecute(BufferEntry bufferEntry) {
        Collection collection;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            collection = this.state.drainedSubstreams;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            bufferEntry.runWith((Substream) it.next());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0035, code lost:
    
        if (r1 == null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
    
        r9.listenerSerializeExecutor.execute(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
    
        if (r4 != false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003f, code lost:
    
        r10.stream.start(new io.grpc.internal.RetriableStream.Sublistener(r9, r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0049, code lost:
    
        r0 = r10.stream;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
    
        if (r9.state.winningSubstream != r10) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
    
        r10 = r9.cancellationStatus;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0056, code lost:
    
        r0.cancel(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0054, code lost:
    
        r10 = io.grpc.internal.RetriableStream.CANCELLED_BECAUSE_COMMITTED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0087, code lost:
    
        r2 = r3.size();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008c, code lost:
    
        if (r5 >= r2) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008e, code lost:
    
        r6 = (io.grpc.internal.RetriableStream.BufferEntry) r3.get(r5);
        r6.runWith(r10);
        r4 = r4 | (r6 instanceof io.grpc.internal.RetriableStream.StartEntry);
        r6 = r9.state;
        r8 = r6.winningSubstream;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x009e, code lost:
    
        if (r8 == null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a0, code lost:
    
        if (r8 != r10) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a2, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a6, code lost:
    
        if (r6.cancelled == false) goto L123;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void drain(io.grpc.internal.RetriableStream.Substream r10) {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            r2 = r0
            r4 = r2
            r3 = r1
        L5:
            java.lang.Object r5 = r9.lock
            monitor-enter(r5)
            io.grpc.internal.RetriableStream$State r6 = r9.state     // Catch: java.lang.Throwable -> Lab
            io.grpc.internal.RetriableStream$Substream r7 = r6.winningSubstream     // Catch: java.lang.Throwable -> Lab
            if (r7 == 0) goto L12
            if (r7 == r10) goto L12
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lab
            goto L35
        L12:
            boolean r7 = r6.cancelled     // Catch: java.lang.Throwable -> Lab
            if (r7 == 0) goto L18
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lab
            goto L35
        L18:
            java.util.List r7 = r6.buffer     // Catch: java.lang.Throwable -> Lab
            int r7 = r7.size()     // Catch: java.lang.Throwable -> Lab
            if (r2 != r7) goto L5a
            io.grpc.internal.RetriableStream$State r0 = r6.substreamDrained(r10)     // Catch: java.lang.Throwable -> Lab
            r9.state = r0     // Catch: java.lang.Throwable -> Lab
            boolean r0 = r9.isReady()     // Catch: java.lang.Throwable -> Lab
            if (r0 != 0) goto L2e
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lab
            return
        L2e:
            io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1 r1 = new io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1     // Catch: java.lang.Throwable -> Lab
            r0 = 1
            r1.<init>(r9, r0)     // Catch: java.lang.Throwable -> Lab
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lab
        L35:
            if (r1 == 0) goto L3d
            java.util.concurrent.Executor r10 = r9.listenerSerializeExecutor
            r10.execute(r1)
            return
        L3d:
            if (r4 != 0) goto L49
            io.grpc.internal.ClientStream r0 = r10.stream
            io.grpc.internal.RetriableStream$Sublistener r1 = new io.grpc.internal.RetriableStream$Sublistener
            r1.<init>(r10)
            r0.start(r1)
        L49:
            io.grpc.internal.ClientStream r0 = r10.stream
            io.grpc.internal.RetriableStream$State r1 = r9.state
            io.grpc.internal.RetriableStream$Substream r1 = r1.winningSubstream
            if (r1 != r10) goto L54
            io.grpc.Status r10 = r9.cancellationStatus
            goto L56
        L54:
            io.grpc.Status r10 = io.grpc.internal.RetriableStream.CANCELLED_BECAUSE_COMMITTED
        L56:
            r0.cancel(r10)
            return
        L5a:
            boolean r7 = r10.closed     // Catch: java.lang.Throwable -> Lab
            if (r7 == 0) goto L60
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lab
            return
        L60:
            int r7 = r2 + 128
            java.util.List r8 = r6.buffer     // Catch: java.lang.Throwable -> Lab
            int r8 = r8.size()     // Catch: java.lang.Throwable -> Lab
            int r7 = java.lang.Math.min(r7, r8)     // Catch: java.lang.Throwable -> Lab
            if (r3 != 0) goto L7a
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lab
            java.util.List r6 = r6.buffer     // Catch: java.lang.Throwable -> Lab
            java.util.List r2 = r6.subList(r2, r7)     // Catch: java.lang.Throwable -> Lab
            r3.<init>(r2)     // Catch: java.lang.Throwable -> Lab
            goto L86
        L7a:
            r3.clear()     // Catch: java.lang.Throwable -> Lab
            java.util.List r6 = r6.buffer     // Catch: java.lang.Throwable -> Lab
            java.util.List r2 = r6.subList(r2, r7)     // Catch: java.lang.Throwable -> Lab
            r3.addAll(r2)     // Catch: java.lang.Throwable -> Lab
        L86:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lab
            int r2 = r3.size()
            r5 = r0
        L8c:
            if (r5 >= r2) goto La8
            java.lang.Object r6 = r3.get(r5)
            io.grpc.internal.RetriableStream$BufferEntry r6 = (io.grpc.internal.RetriableStream.BufferEntry) r6
            r6.runWith(r10)
            boolean r6 = r6 instanceof io.grpc.internal.RetriableStream.StartEntry
            r4 = r4 | r6
            io.grpc.internal.RetriableStream$State r6 = r9.state
            io.grpc.internal.RetriableStream$Substream r8 = r6.winningSubstream
            if (r8 == 0) goto La2
            if (r8 != r10) goto La8
        La2:
            boolean r6 = r6.cancelled
            int r5 = r5 + 1
            if (r6 == 0) goto L8c
        La8:
            r2 = r7
            goto L5
        Lab:
            r10 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lab
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.drain(io.grpc.internal.RetriableStream$Substream):void");
    }

    @Override // io.grpc.internal.Stream
    public final void flush() {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new C1HalfCloseEntry(1));
        }
    }

    public final void freezeHedging() {
        Future future;
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            future = null;
            if (futureCanceller != null) {
                Future markCancelled = futureCanceller.markCancelled();
                this.scheduledHedging = null;
                future = markCancelled;
            }
            this.state = this.state.freezeHedging();
        }
        if (future != null) {
            future.cancel(false);
        }
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        throw null;
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
        delayOrExecute(new C1HalfCloseEntry(0));
    }

    public final boolean hasPotentialHedging(State state) {
        if (state.winningSubstream == null && state.hedgingAttemptCount < this.hedgingPolicy.maxAttempts && !state.hedgingFrozen) {
            return true;
        }
        return false;
    }

    @Override // io.grpc.internal.Stream
    public final boolean isReady() {
        Iterator it = this.state.drainedSubstreams.iterator();
        while (it.hasNext()) {
            if (((Substream) it.next()).stream.isReady()) {
                return true;
            }
        }
        return false;
    }

    public final ClientStream newSubstream$ar$class_merging$ar$class_merging(Metadata metadata, OnDeviceFaceMeshLoadLogEvent onDeviceFaceMeshLoadLogEvent, int i, boolean z) {
        CallOptions withStreamTracerFactory$ar$class_merging$ar$class_merging = this.val$callOptions.withStreamTracerFactory$ar$class_merging$ar$class_merging(onDeviceFaceMeshLoadLogEvent);
        ClientStreamTracer[] clientStreamTracers = GrpcUtil.getClientStreamTracers(withStreamTracerFactory$ar$class_merging$ar$class_merging, metadata, i, z);
        Context attach = this.val$context.attach();
        try {
            return ManagedChannelImpl.this.delayedTransport.newStream(this.val$method, metadata, withStreamTracerFactory$ar$class_merging$ar$class_merging, clientStreamTracers);
        } finally {
            this.val$context.detach(attach);
        }
    }

    @Override // io.grpc.internal.Stream
    public final void optimizeForDirectExecutor() {
        delayOrExecute(new C1HalfCloseEntry(2));
    }

    public final void postCommit() {
        Status status;
        ManagedChannelImpl.UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry = ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry;
        synchronized (uncommittedRetriableStreamsRegistry.lock) {
            uncommittedRetriableStreamsRegistry.uncommittedRetriableStreams.remove(this);
            if (uncommittedRetriableStreamsRegistry.uncommittedRetriableStreams.isEmpty()) {
                status = uncommittedRetriableStreamsRegistry.shutdownStatus;
                uncommittedRetriableStreamsRegistry.uncommittedRetriableStreams = new HashSet();
            } else {
                status = null;
            }
        }
        if (status != null) {
            ManagedChannelImpl.this.delayedTransport.shutdown(status);
        }
    }

    public final Status prestart() {
        ManagedChannelImpl.UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry = ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry;
        synchronized (uncommittedRetriableStreamsRegistry.lock) {
            Status status = uncommittedRetriableStreamsRegistry.shutdownStatus;
            if (status == null) {
                uncommittedRetriableStreamsRegistry.uncommittedRetriableStreams.add(this);
                return null;
            }
            return status;
        }
    }

    @Override // io.grpc.internal.Stream
    public final void request(int i) {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.request(i);
        } else {
            delayOrExecute(new C1MaxOutboundMessageSizeEntry(i, 2));
        }
    }

    public final void safeCloseMasterListener(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
        this.savedCloseMasterListenerReason$ar$class_merging = new OptionalMethod(status, rpcProgress, metadata, (byte[]) null);
        if (this.inFlightSubStreams.addAndGet(Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            this.listenerSerializeExecutor.execute(new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(this, status, rpcProgress, metadata, 7));
        }
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(Compressor compressor) {
        delayOrExecute(new C1DeadlineEntry(compressor, 1));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDeadline(Deadline deadline) {
        delayOrExecute(new C1DeadlineEntry(deadline, 0));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new C1DeadlineEntry(decompressorRegistry, 2));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxInboundMessageSize(int i) {
        delayOrExecute(new C1MaxOutboundMessageSizeEntry(i, 1));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxOutboundMessageSize(int i) {
        delayOrExecute(new C1MaxOutboundMessageSizeEntry(i, 0));
    }

    @Override // io.grpc.internal.ClientStream
    public final void start(ClientStreamListener clientStreamListener) {
        FutureCanceller futureCanceller;
        Throttle throttle;
        this.masterListener = clientStreamListener;
        Status prestart = prestart();
        if (prestart != null) {
            cancel(prestart);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new StartEntry());
        }
        Substream createSubstream = createSubstream(0, false);
        if (createSubstream == null) {
            return;
        }
        if (this.isHedging) {
            synchronized (this.lock) {
                this.state = this.state.addActiveHedge(createSubstream);
                futureCanceller = null;
                if (hasPotentialHedging(this.state) && ((throttle = this.throttle) == null || throttle.isAboveThreshold())) {
                    futureCanceller = new FutureCanceller(this.lock);
                    this.scheduledHedging = futureCanceller;
                }
            }
            if (futureCanceller != null) {
                futureCanceller.setFuture(this.scheduledExecutorService.schedule(new BaseLifecycleHelper.ConnectionFailedResolver(this, futureCanceller, 2), this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
            }
        }
        drain(createSubstream);
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    public RetriableStream(MethodDescriptor methodDescriptor, Metadata metadata, RemoteModelManager remoteModelManager, long j, long j2, Executor executor, ScheduledExecutorService scheduledExecutorService, RetryPolicy retryPolicy, HedgingPolicy hedgingPolicy, Throttle throttle) {
        this.listenerSerializeExecutor = new SynchronizationContext(new Thread.UncaughtExceptionHandler() { // from class: io.grpc.internal.RetriableStream.1
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(int i) {
                r1 = i;
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                if (r1 != 0) {
                    throw null;
                }
                throw new StatusRuntimeException(Status.fromThrowable(th).withDescription("Uncaught exception in the SynchronizationContext. Re-thrown."));
            }
        });
        this.lock = new Object();
        this.closedSubstreamsInsight = new InsightBuilder();
        this.state = new State(new ArrayList(8), Collections.emptyList(), null, null, false, false, false, 0);
        this.noMoreTransparentRetry = new AtomicBoolean();
        this.localOnlyTransparentRetries = new AtomicInteger();
        this.inFlightSubStreams = new AtomicInteger();
        this.method = methodDescriptor;
        this.channelBufferUsed$ar$class_merging$ar$class_merging = remoteModelManager;
        this.perRpcBufferLimit = j;
        this.channelBufferLimit = j2;
        this.callExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService;
        this.headers = metadata;
        this.retryPolicy = retryPolicy;
        if (retryPolicy != null) {
            this.nextBackoffIntervalNanos = retryPolicy.initialBackoffNanos;
        }
        this.hedgingPolicy = hedgingPolicy;
        ContextDataProvider.checkArgument(retryPolicy == null || hedgingPolicy == null, (Object) "Should not provide both retryPolicy and hedgingPolicy");
        this.isHedging = hedgingPolicy != null;
        this.throttle = throttle;
    }
}
