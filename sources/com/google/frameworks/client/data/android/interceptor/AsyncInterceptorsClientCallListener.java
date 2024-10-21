package com.google.frameworks.client.data.android.interceptor;

import android.content.res.ColorStateList;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView$RecycledViewPool$ScrapData;
import android.util.SparseArray;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.Metadata;
import io.grpc.PartialForwardingClientCallListener;
import io.grpc.Status;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AsyncInterceptorsClientCallListener extends PartialForwardingClientCallListener {
    private final ImmutableList asyncInterceptors;
    public boolean completedWithErrorStatus;
    private int currentHeaderStage;
    public final LinkedHashMap detachedHeaders;
    public final LinkedHashMap detachedOnCompletes;
    private Metadata headers;
    private boolean headersDelivered;
    private final Queue pendingResponses;
    private final Executor sequentialExecutor;
    private final Set startedInterceptors;
    public Status status;
    public Metadata trailers;
    private boolean waitingToClose;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PendingMessage {
        public final Object AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors;
        public int currentStage;
        public final Object message;

        public PendingMessage(Shader shader, ColorStateList colorStateList, int i) {
            this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors = shader;
            this.message = colorStateList;
            this.currentStage = i;
        }

        public static final float kineticEnergyToVelocity$ar$ds(float f) {
            double signum = Math.signum(f);
            float abs = Math.abs(f);
            return (float) (signum * Math.sqrt(abs + abs));
        }

        public static final long runningAverage$ar$ds(long j, long j2) {
            if (j == 0) {
                return j2;
            }
            return ((j / 4) * 3) + (j2 / 4);
        }

        public final void addDataPoint(long j, float f) {
            int i = (this.currentStage + 1) % 20;
            this.currentStage = i;
            ((long[]) this.message)[i] = j;
            ((float[]) this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors)[i] = f;
        }

        public final void cancelPulse() {
            ((Handler) this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors).removeMessages(0);
        }

        public final RecyclerView$RecycledViewPool$ScrapData getScrapDataForType(int i) {
            RecyclerView$RecycledViewPool$ScrapData recyclerView$RecycledViewPool$ScrapData = (RecyclerView$RecycledViewPool$ScrapData) ((SparseArray) this.message).get(i);
            if (recyclerView$RecycledViewPool$ScrapData == null) {
                RecyclerView$RecycledViewPool$ScrapData recyclerView$RecycledViewPool$ScrapData2 = new RecyclerView$RecycledViewPool$ScrapData();
                ((SparseArray) this.message).put(i, recyclerView$RecycledViewPool$ScrapData2);
                return recyclerView$RecycledViewPool$ScrapData2;
            }
            return recyclerView$RecycledViewPool$ScrapData;
        }

        public final boolean hasPendingFutures() {
            if (!((LinkedHashMap) this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors).isEmpty()) {
                return true;
            }
            return false;
        }

        public final boolean isGradient() {
            if (this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors != null) {
                return true;
            }
            return false;
        }

        public final boolean isStateful() {
            Object obj;
            if (this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors == null && (obj = this.message) != null && ((ColorStateList) obj).isStateful()) {
                return true;
            }
            return false;
        }

        public final boolean onStateChanged(int[] iArr) {
            if (isStateful()) {
                ColorStateList colorStateList = (ColorStateList) this.message;
                int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
                if (colorForState != this.currentStage) {
                    this.currentStage = colorForState;
                    return true;
                }
                return false;
            }
            return false;
        }

        public final boolean willDraw() {
            if (!isGradient() && this.currentStage == 0) {
                return false;
            }
            return true;
        }

        public PendingMessage() {
            long[] jArr = new long[20];
            this.message = jArr;
            this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors = new float[20];
            this.currentStage = 0;
            Arrays.fill(jArr, Long.MIN_VALUE);
        }

        public PendingMessage(byte[] bArr) {
            this.message = new SparseArray();
            this.currentStage = 0;
            this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors = Collections.newSetFromMap(new IdentityHashMap());
        }

        public PendingMessage(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, int i) {
            this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors = new Handler() { // from class: com.google.android.accessibility.braille.brailledisplay.controller.Pulser$MainHandler
                {
                    super(Looper.getMainLooper());
                }

                @Override // android.os.Handler
                public final void handleMessage(Message message) {
                    ((FloatingActionButton.ShadowDelegateImpl) AsyncInterceptorsClientCallListener.PendingMessage.this.message).pulse();
                }
            };
            this.message = shadowDelegateImpl;
            this.currentStage = i;
        }

        public PendingMessage(Object obj, int i) {
            this.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors = new LinkedHashMap();
            this.message = obj;
            this.currentStage = i;
        }
    }

    public AsyncInterceptorsClientCallListener(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, ImmutableList immutableList, Set set, Executor executor) {
        super(onDeviceFaceMeshCreateLogEvent);
        this.detachedHeaders = new LinkedHashMap();
        this.pendingResponses = new ArrayDeque();
        this.detachedOnCompletes = new LinkedHashMap();
        this.asyncInterceptors = immutableList;
        this.currentHeaderStage = immutableList.size();
        this.startedInterceptors = set;
        this.sequentialExecutor = executor;
    }

    private final void maybeClose() {
        if (!headerFuturesAreDetached() && this.pendingResponses.isEmpty() && this.waitingToClose && !this.completedWithErrorStatus) {
            startCloseDelegate();
        }
    }

    public final boolean headerFuturesAreDetached() {
        if (!this.detachedHeaders.isEmpty()) {
            return true;
        }
        return false;
    }

    public final void maybeProcessResponseMessages() {
        if (this.headersDelivered) {
            for (PendingMessage pendingMessage : this.pendingResponses) {
                Iterator it = ContextDataProvider.reverse(this.asyncInterceptors.subList(0, pendingMessage.currentStage)).iterator();
                while (it.hasNext()) {
                    for (AsyncClientInterceptor asyncClientInterceptor : ContextDataProvider.reverse((List) it.next())) {
                        if (this.startedInterceptors.contains(asyncClientInterceptor)) {
                            new ContextDataProvider(pendingMessage.message);
                            try {
                                asyncClientInterceptor.startResponseMessageProcessing$ar$ds();
                            } catch (Throwable th) {
                                this.status = Status.fromThrowable(th);
                                this.trailers = new Metadata();
                                startCloseDelegate();
                                this.completedWithErrorStatus = true;
                                return;
                            }
                        }
                    }
                    if (pendingMessage.hasPendingFutures()) {
                        return;
                    } else {
                        pendingMessage.currentStage--;
                    }
                }
            }
            while (!this.pendingResponses.isEmpty()) {
                PendingMessage pendingMessage2 = (PendingMessage) this.pendingResponses.peek();
                if (pendingMessage2.hasPendingFutures() || pendingMessage2.currentStage != 0) {
                    break;
                } else {
                    this.delegate$ar$class_merging$a40ae667_0$ar$class_merging.onMessage(((PendingMessage) this.pendingResponses.poll()).message);
                }
            }
            maybeClose();
        }
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onClose(Status status, Metadata metadata) {
        this.status = status;
        this.trailers = metadata;
        this.waitingToClose = true;
        maybeClose();
    }

    @Override // io.grpc.PartialForwardingClientCallListener, com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onHeaders(Metadata metadata) {
        this.headers = metadata;
        startResponseHeaderProcessing();
    }

    @Override // io.grpc.PartialForwardingClientCallListener, com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onMessage(Object obj) {
        this.pendingResponses.add(new PendingMessage(obj, this.asyncInterceptors.size()));
        maybeProcessResponseMessages();
    }

    public final void startCloseDelegate() {
        Iterator it = ContextDataProvider.reverse(this.asyncInterceptors).iterator();
        while (it.hasNext()) {
            for (AsyncClientInterceptor asyncClientInterceptor : ContextDataProvider.reverse((List) it.next())) {
                RemoteModelManager remoteModelManager = new RemoteModelManager(this.status, this.trailers);
                if (this.startedInterceptors.contains(asyncClientInterceptor)) {
                    try {
                        asyncClientInterceptor.startOnCompleteProcessing$ar$class_merging$ar$class_merging(remoteModelManager);
                    } catch (Throwable th) {
                        this.status = Status.fromThrowable(th);
                        this.trailers = new Metadata();
                    }
                }
            }
        }
        if (this.detachedOnCompletes.isEmpty()) {
            this.delegate$ar$class_merging$a40ae667_0$ar$class_merging.onClose(this.status, this.trailers);
        }
    }

    public final void startResponseHeaderProcessing() {
        new ContextDataProvider(this.headers);
        Iterator it = ContextDataProvider.reverse(this.asyncInterceptors.subList(0, this.currentHeaderStage)).iterator();
        while (it.hasNext()) {
            this.currentHeaderStage--;
            for (AsyncClientInterceptor asyncClientInterceptor : ContextDataProvider.reverse((List) it.next())) {
                if (this.startedInterceptors.contains(asyncClientInterceptor)) {
                    try {
                        asyncClientInterceptor.startResponseHeaderProcessing$ar$ds();
                    } catch (Throwable th) {
                        this.status = Status.fromThrowable(th);
                        this.trailers = new Metadata();
                        startCloseDelegate();
                        return;
                    }
                }
            }
            if (headerFuturesAreDetached()) {
                return;
            }
        }
        this.delegate$ar$class_merging$a40ae667_0$ar$class_merging.onHeaders(this.headers);
        this.headersDelivered = true;
        maybeProcessResponseMessages();
    }
}
