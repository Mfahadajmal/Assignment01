package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    public static final ThreadLocal sTransformRunning = new ThreadLocal() { // from class: com.google.android.gms.common.api.internal.BasePendingResult.1
        @Override // java.lang.ThreadLocal
        protected final /* bridge */ /* synthetic */ Object initialValue() {
            return false;
        }
    };
    private ResultCallback callback;
    private volatile boolean consumed;
    private final AtomicReference consumedCallback;
    private boolean forcedFailure;
    public boolean isInChain;
    private final CountDownLatch latch;
    protected final CallbackHandler mHandler;
    public Result result;
    private Status status;
    private final ArrayList statusListeners;
    private final Object syncToken;
    private volatile TransformedResult transformRoot$ar$class_merging;

    @Deprecated
    BasePendingResult() {
        this.syncToken = new Object();
        this.latch = new CountDownLatch(1);
        this.statusListeners = new ArrayList();
        this.consumedCallback = new AtomicReference();
        this.isInChain = false;
        this.mHandler = new CallbackHandler(Looper.getMainLooper());
        new WeakReference(null);
    }

    private final Result get() {
        Result result;
        synchronized (this.syncToken) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(!this.consumed, "Result has already been consumed.");
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(isReady(), "Result is not ready.");
            result = this.result;
            this.result = null;
            this.callback = null;
            this.consumed = true;
        }
        UnconsumedApiCalls$ResultConsumedCallback unconsumedApiCalls$ResultConsumedCallback = (UnconsumedApiCalls$ResultConsumedCallback) this.consumedCallback.getAndSet(null);
        if (unconsumedApiCalls$ResultConsumedCallback != null) {
            unconsumedApiCalls$ResultConsumedCallback.onConsumed$ar$ds();
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(result);
        return result;
    }

    public static void maybeReleaseResult(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w("BasePendingResult", "Unable to release ".concat(String.valueOf(String.valueOf(result))), e);
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(PendingResult.StatusListener statusListener) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(true, "Callback cannot be null.");
        synchronized (this.syncToken) {
            if (isReady()) {
                statusListener.onComplete(this.status);
            } else {
                this.statusListeners.add(statusListener);
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void await$ar$ds(TimeUnit timeUnit) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkState(!this.consumed, "Result has already been consumed.");
        StrictModeUtils$VmPolicyBuilderCompatS.checkState(true, "Cannot await if then() has been called.");
        try {
            if (!this.latch.await(0L, timeUnit)) {
                forceFailureUnlessReady(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkState(isReady(), "Result is not ready.");
        get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Result createFailedResult(Status status);

    @Deprecated
    public final void forceFailureUnlessReady(Status status) {
        synchronized (this.syncToken) {
            if (!isReady()) {
                setResult(createFailedResult(status));
                this.forcedFailure = true;
            }
        }
    }

    public final boolean isReady() {
        if (this.latch.getCount() == 0) {
            return true;
        }
        return false;
    }

    public final void setResult(Result result) {
        synchronized (this.syncToken) {
            if (!this.forcedFailure) {
                isReady();
                StrictModeUtils$VmPolicyBuilderCompatS.checkState(!isReady(), "Results have already been set");
                StrictModeUtils$VmPolicyBuilderCompatS.checkState(!this.consumed, "Result has already been consumed");
                this.result = result;
                this.status = result.getStatus();
                this.latch.countDown();
                ResultCallback resultCallback = this.callback;
                if (resultCallback != null) {
                    this.mHandler.removeMessages(2);
                    this.mHandler.sendResultCallback(resultCallback, get());
                }
                ArrayList arrayList = this.statusListeners;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((PendingResult.StatusListener) arrayList.get(i)).onComplete(this.status);
                }
                this.statusListeners.clear();
                return;
            }
            maybeReleaseResult(result);
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback resultCallback) {
        synchronized (this.syncToken) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(!this.consumed, "Result has already been consumed.");
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(true, "Cannot set callbacks if then() has been called.");
            synchronized (this.syncToken) {
            }
            if (isReady()) {
                this.mHandler.sendResultCallback(resultCallback, get());
            } else {
                this.callback = resultCallback;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CallbackHandler extends TracingHandler {
        public CallbackHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                    return;
                }
                ((BasePendingResult) message.obj).forceFailureUnlessReady(Status.RESULT_TIMEOUT);
                return;
            }
            Pair pair = (Pair) message.obj;
            ResultCallback resultCallback = (ResultCallback) pair.first;
            Result result = (Result) pair.second;
            try {
                resultCallback.onResult(result);
            } catch (RuntimeException e) {
                BasePendingResult.maybeReleaseResult(result);
                throw e;
            }
        }

        public final void sendResultCallback(ResultCallback resultCallback, Result result) {
            ThreadLocal threadLocal = BasePendingResult.sTransformRunning;
            sendMessage(obtainMessage(1, new Pair(resultCallback, result)));
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BasePendingResult(GoogleApiClient googleApiClient) {
        this.syncToken = new Object();
        this.latch = new CountDownLatch(1);
        this.statusListeners = new ArrayList();
        this.consumedCallback = new AtomicReference();
        this.isInChain = false;
        this.mHandler = new CallbackHandler(googleApiClient.getLooper());
        new WeakReference(googleApiClient);
    }
}
