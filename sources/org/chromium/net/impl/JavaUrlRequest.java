package org.chromium.net.impl;

import _COROUTINE._BOUNDARY;
import android.net.TrafficStats;
import android.util.Log;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.mlkit.logging.schema.acceleration.NNAPIInfo;
import io.grpc.internal.RetriableStream;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.net.CronetException;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.InlineExecutionProhibitedException;
import org.chromium.net.ThreadStatsUid;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.CronetUrlRequest;
import org.chromium.net.impl.JavaUrlRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JavaUrlRequest extends ExperimentalUrlRequest {
    public static final String TAG = "JavaUrlRequest";
    public final boolean mAllowDirectExecutor;
    public final AsyncUrlRequestCallback mCallbackAsync;
    public final int mCronetEngineId;
    public String mCurrentUrl;
    public HttpURLConnection mCurrentUrlConnection;
    public final JavaCronetEngine mEngine;
    public final Executor mExecutor;
    public boolean mFinalUserCallbackThrew;
    public final String mInitialMethod;
    public final CronetLogger mLogger;
    public final long mNetworkHandle;
    public int mNonfinalUserCallbackExceptionCount;
    public JavaUploadDataSinkBase mOutputStreamDataSink$ar$class_merging;
    public String mPendingRedirectUrl;
    public int mReadCount;
    public ReadableByteChannel mResponseChannel;
    public VersionSafeCallbacks$UploadDataProviderWrapper mUploadDataProvider;
    public Executor mUploadExecutor;
    public UrlResponseInfoImpl mUrlResponseInfo;
    public final String mUserAgent;
    public final Map mRequestHeaders = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    public final List mUrlChain = new ArrayList();
    public final AtomicInteger mState = new AtomicInteger(0);
    private final AtomicBoolean mUploadProviderClosed = new AtomicBoolean(false);
    public volatile int mAdditionalStatusDetails = -1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AsyncUrlRequestCallback {
        final VersionSafeCallbacks$UrlRequestCallback mCallback;
        final Executor mFallbackExecutor;
        final Executor mUserExecutor;

        public AsyncUrlRequestCallback(UrlRequest.Callback callback, Executor executor) {
            this.mCallback = new VersionSafeCallbacks$UrlRequestCallback(callback);
            if (JavaUrlRequest.this.mAllowDirectExecutor) {
                this.mUserExecutor = executor;
                this.mFallbackExecutor = null;
            } else {
                this.mUserExecutor = new JavaUrlRequestUtils$DirectPreventingExecutor(executor, 0);
                this.mFallbackExecutor = executor;
            }
        }

        private final void maybeReportMetrics() {
            JavaUrlRequest.this.mExecutor.execute(new CronetUrlRequest.AnonymousClass8(this, 9));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void execute(JavaUrlRequestUtils$CheckedRunnable javaUrlRequestUtils$CheckedRunnable) {
            try {
                this.mUserExecutor.execute(new RetriableStream.Sublistener.AnonymousClass4(JavaUrlRequest.this, javaUrlRequestUtils$CheckedRunnable, 12, (byte[]) null));
            } catch (RejectedExecutionException e) {
                JavaUrlRequest.this.enterErrorState(new CronetExceptionImpl("Exception posting task to executor", e));
            }
        }

        public final /* synthetic */ void lambda$onCanceled$5(UrlResponseInfo urlResponseInfo) {
            try {
                this.mCallback.onCanceled(JavaUrlRequest.this, urlResponseInfo);
            } catch (Exception e) {
                JavaUrlRequest.this.onFinalCallbackException("onCanceled", e);
            }
            maybeReportMetrics();
            JavaUrlRequest.this.mEngine.decrementActiveRequestCount();
        }

        public final /* synthetic */ void lambda$onFailed$7(UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            try {
                this.mCallback.onFailed(JavaUrlRequest.this, urlResponseInfo, cronetException);
            } catch (Exception e) {
                JavaUrlRequest.this.onFinalCallbackException("onFailed", e);
            }
            maybeReportMetrics();
            JavaUrlRequest.this.mEngine.decrementActiveRequestCount();
        }

        public final /* synthetic */ void lambda$onSucceeded$6(UrlResponseInfo urlResponseInfo) {
            try {
                this.mCallback.onSucceeded(JavaUrlRequest.this, urlResponseInfo);
            } catch (Exception e) {
                JavaUrlRequest.this.onFinalCallbackException("onSucceded", e);
            }
            maybeReportMetrics();
            JavaUrlRequest.this.mEngine.decrementActiveRequestCount();
        }

        final void onCanceled(UrlResponseInfo urlResponseInfo) {
            JavaUrlRequest.this.closeResponseChannel();
            this.mUserExecutor.execute(new RetriableStream.Sublistener.AnonymousClass4(this, urlResponseInfo, 16, (char[]) null));
        }

        final void onFailed(UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            JavaUrlRequest.this.closeResponseChannel();
            FutureCallbackViewModel$$ExternalSyntheticLambda1 futureCallbackViewModel$$ExternalSyntheticLambda1 = new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, urlResponseInfo, cronetException, 20);
            try {
                this.mUserExecutor.execute(futureCallbackViewModel$$ExternalSyntheticLambda1);
            } catch (InlineExecutionProhibitedException unused) {
                Executor executor = this.mFallbackExecutor;
                if (executor != null) {
                    executor.execute(futureCallbackViewModel$$ExternalSyntheticLambda1);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void onResponseStarted$ar$ds() {
            execute(new JavaUploadDataSinkBase$$ExternalSyntheticLambda3(this, 5));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SerializingExecutor implements Executor {
        private boolean mRunning;
        private final Executor mUnderlyingExecutor;
        private final Runnable mRunTasks = new CronetUrlRequest.AnonymousClass8(this, 10);
        private final ArrayDeque mTaskQueue = new ArrayDeque();

        public SerializingExecutor(Executor executor) {
            this.mUnderlyingExecutor = executor;
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            synchronized (this.mTaskQueue) {
                this.mTaskQueue.addLast(runnable);
                try {
                    this.mUnderlyingExecutor.execute(this.mRunTasks);
                } catch (RejectedExecutionException unused) {
                    this.mTaskQueue.removeLast();
                }
            }
        }

        public final void runTasks() {
            boolean z;
            boolean z2;
            synchronized (this.mTaskQueue) {
                if (this.mRunning) {
                    return;
                }
                Runnable runnable = (Runnable) this.mTaskQueue.pollFirst();
                if (runnable != null) {
                    z = true;
                } else {
                    z = false;
                }
                this.mRunning = z;
                while (runnable != null) {
                    try {
                        runnable.run();
                        synchronized (this.mTaskQueue) {
                            runnable = (Runnable) this.mTaskQueue.pollFirst();
                            if (runnable != null) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            this.mRunning = z2;
                        }
                    } catch (Throwable th) {
                        synchronized (this.mTaskQueue) {
                            this.mRunning = false;
                            try {
                                this.mUnderlyingExecutor.execute(this.mRunTasks);
                            } catch (RejectedExecutionException unused) {
                            }
                            throw th;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:36:0x00ed. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:37:0x00f0. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:38:0x00f3. Please report as an issue. */
    public JavaUrlRequest(JavaCronetEngine javaCronetEngine, UrlRequest.Callback callback, final Executor executor, Executor executor2, String str, String str2, boolean z, boolean z2, int i, final boolean z3, final int i2, long j, String str3, ArrayList arrayList, UploadDataProvider uploadDataProvider, Executor executor3) {
        VersionSafeCallbacks$UploadDataProviderWrapper versionSafeCallbacks$UploadDataProviderWrapper;
        this.mAllowDirectExecutor = z;
        this.mCallbackAsync = new AsyncUrlRequestCallback(callback, executor2);
        final int threadStatsTag = !z2 ? TrafficStats.getThreadStatsTag() : i;
        this.mExecutor = new SerializingExecutor(new Executor() { // from class: org.chromium.net.impl.JavaUrlRequest$$ExternalSyntheticLambda11
            @Override // java.util.concurrent.Executor
            public final void execute(final Runnable runnable) {
                final int i3 = threadStatsTag;
                final boolean z4 = z3;
                final int i4 = i2;
                executor.execute(new Runnable() { // from class: org.chromium.net.impl.JavaUrlRequest$$ExternalSyntheticLambda13
                    @Override // java.lang.Runnable
                    public final void run() {
                        int threadStatsTag2 = TrafficStats.getThreadStatsTag();
                        TrafficStats.setThreadStatsTag(i3);
                        Runnable runnable2 = runnable;
                        boolean z5 = z4;
                        if (z5) {
                            ThreadStatsUid.set(i4);
                        }
                        try {
                            runnable2.run();
                            if (z5) {
                                ThreadStatsUid.clear();
                            }
                            TrafficStats.setThreadStatsTag(threadStatsTag2);
                        } catch (Throwable th) {
                            if (z5) {
                                ThreadStatsUid.clear();
                            }
                            TrafficStats.setThreadStatsTag(threadStatsTag2);
                            throw th;
                        }
                    }
                });
            }
        });
        this.mEngine = javaCronetEngine;
        this.mCronetEngineId = javaCronetEngine.mCronetEngineId;
        this.mLogger = javaCronetEngine.mLogger;
        this.mCurrentUrl = str;
        this.mUserAgent = str2;
        this.mNetworkHandle = j;
        str3.getClass();
        if (!"OPTIONS".equalsIgnoreCase(str3) && !"GET".equalsIgnoreCase(str3) && !"HEAD".equalsIgnoreCase(str3) && !"POST".equalsIgnoreCase(str3) && !"PUT".equalsIgnoreCase(str3) && !"DELETE".equalsIgnoreCase(str3) && !"TRACE".equalsIgnoreCase(str3) && !"PATCH".equalsIgnoreCase(str3)) {
            throw new IllegalArgumentException("Invalid http method ".concat(str3));
        }
        this.mInitialMethod = str3;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            Map.Entry entry = (Map.Entry) arrayList.get(i3);
            String str4 = (String) entry.getKey();
            int i4 = 0;
            while (true) {
                if (i4 < str4.length()) {
                    char charAt = str4.charAt(i4);
                    if (charAt != ',' && charAt != '/' && charAt != '{' && charAt != '}') {
                        switch (charAt) {
                            case '\'':
                            case '(':
                            case ')':
                                break;
                            default:
                                switch (charAt) {
                                    case ':':
                                    case ';':
                                    case '<':
                                    case '=':
                                    case '>':
                                    case '?':
                                    case '@':
                                        break;
                                    default:
                                        switch (charAt) {
                                            case '[':
                                            case '\\':
                                            case ']':
                                                break;
                                            default:
                                                i4 = (Character.isISOControl(charAt) || Character.isWhitespace(charAt)) ? i4 : i4 + 1;
                                                break;
                                        }
                                }
                        }
                    }
                } else if (!((String) entry.getValue()).contains("\r\n")) {
                    this.mRequestHeaders.put((String) entry.getKey(), (String) entry.getValue());
                }
            }
            throw new IllegalArgumentException("Invalid header with headername: ".concat(String.valueOf((String) entry.getKey())));
        }
        if (uploadDataProvider == null) {
            versionSafeCallbacks$UploadDataProviderWrapper = null;
        } else if (this.mRequestHeaders.containsKey("Content-Type")) {
            versionSafeCallbacks$UploadDataProviderWrapper = new VersionSafeCallbacks$UploadDataProviderWrapper(uploadDataProvider);
        } else {
            throw new IllegalArgumentException("Requests with upload data must have a Content-Type.");
        }
        this.mUploadDataProvider = versionSafeCallbacks$UploadDataProviderWrapper;
        this.mUploadExecutor = (executor3 == null || this.mAllowDirectExecutor) ? executor3 : new JavaUrlRequestUtils$DirectPreventingExecutor(executor3, 0);
    }

    @Override // org.chromium.net.UrlRequest
    public final void cancel() {
        int andSet = this.mState.getAndSet(8);
        if (andSet != 1 && andSet != 2 && andSet != 3 && andSet != 4 && andSet != 5) {
            return;
        }
        fireDisconnect();
        fireCloseUploadDataProvider();
        this.mCallbackAsync.onCanceled(this.mUrlResponseInfo);
    }

    public final void closeResponseChannel() {
        this.mExecutor.execute(new CronetUrlRequest.AnonymousClass8((Object) this, 5));
    }

    public final void enterErrorState(CronetException cronetException) {
        int i;
        do {
            i = this.mState.get();
            if (i != 0) {
                if (i == 6 || i == 7 || i == 8) {
                    return;
                }
            } else {
                throw new IllegalStateException("Can't enter error state before start");
            }
        } while (!this.mState.compareAndSet(i, 6));
        fireDisconnect();
        fireCloseUploadDataProvider();
        this.mCallbackAsync.onFailed(this.mUrlResponseInfo, cronetException);
    }

    public final void enterUploadErrorState(Throwable th) {
        enterErrorState(new CallbackExceptionImpl("Exception received from UploadDataProvider", th));
    }

    public final void fireCloseUploadDataProvider() {
        if (this.mUploadDataProvider != null && this.mUploadProviderClosed.compareAndSet(false, true)) {
            try {
                Executor executor = this.mUploadExecutor;
                VersionSafeCallbacks$UploadDataProviderWrapper versionSafeCallbacks$UploadDataProviderWrapper = this.mUploadDataProvider;
                versionSafeCallbacks$UploadDataProviderWrapper.getClass();
                executor.execute(new RetriableStream.Sublistener.AnonymousClass4(this, new JavaUploadDataSinkBase$$ExternalSyntheticLambda3(versionSafeCallbacks$UploadDataProviderWrapper, 4), 11, (byte[]) null));
            } catch (RejectedExecutionException e) {
                Log.e(TAG, "Exception when closing uploadDataProvider", e);
            }
        }
    }

    public final void fireDisconnect() {
        this.mExecutor.execute(new CronetUrlRequest.AnonymousClass8((Object) this, 7));
    }

    public final void fireGetHeaders() {
        this.mAdditionalStatusDetails = 13;
        this.mExecutor.execute(new RetriableStream.Sublistener.AnonymousClass4(this, new JavaUploadDataSinkBase$$ExternalSyntheticLambda3(this, 3), 10, (byte[]) null));
    }

    public final void fireOpenConnection() {
        this.mExecutor.execute(new RetriableStream.Sublistener.AnonymousClass4(this, new JavaUploadDataSinkBase$$ExternalSyntheticLambda3(this, 2), 10, (byte[]) null));
    }

    @Override // org.chromium.net.UrlRequest
    public final void followRedirect() {
        transitionStates(3, 1, new CronetUrlRequest.AnonymousClass8((ExperimentalUrlRequest) this, 8));
    }

    @Override // org.chromium.net.UrlRequest
    public final void getStatus(UrlRequest.StatusListener statusListener) {
        int i = this.mState.get();
        int i2 = this.mAdditionalStatusDetails;
        switch (i) {
            case 0:
            case 6:
            case 7:
            case 8:
                i2 = -1;
                break;
            case 1:
                break;
            case 2:
            case 3:
            case 4:
                i2 = 0;
                break;
            case 5:
                i2 = 14;
                break;
            default:
                throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Switch is exhaustive: "));
        }
        AsyncUrlRequestCallback asyncUrlRequestCallback = this.mCallbackAsync;
        asyncUrlRequestCallback.mUserExecutor.execute(new RatingView$$ExternalSyntheticLambda5(new VersionSafeCallbacks$UrlRequestStatusListener(statusListener), i2, 13));
    }

    @Override // org.chromium.net.UrlRequest
    public final boolean isDone() {
        int i = this.mState.get();
        if (i != 7 && i != 6 && i != 8) {
            return false;
        }
        return true;
    }

    public final void onFinalCallbackException(String str, Exception exc) {
        Log.e(TAG, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Exception in ", " method"), exc);
        this.mFinalUserCallbackThrew = true;
    }

    @Override // org.chromium.net.UrlRequest
    public final void read(final ByteBuffer byteBuffer) {
        NNAPIInfo.checkDirect(byteBuffer);
        NNAPIInfo.checkHasRemaining(byteBuffer);
        transitionStates(4, 5, new RetriableStream.Sublistener.AnonymousClass4(this, new JavaUrlRequestUtils$CheckedRunnable() { // from class: org.chromium.net.impl.JavaUrlRequest$$ExternalSyntheticLambda5
            @Override // org.chromium.net.impl.JavaUrlRequestUtils$CheckedRunnable
            public final void run() {
                int i;
                JavaUrlRequest javaUrlRequest = JavaUrlRequest.this;
                ReadableByteChannel readableByteChannel = javaUrlRequest.mResponseChannel;
                ByteBuffer byteBuffer2 = byteBuffer;
                int i2 = 1;
                if (readableByteChannel != null) {
                    javaUrlRequest.mReadCount++;
                    i = readableByteChannel.read(byteBuffer2);
                } else {
                    i = -1;
                }
                if (i != -1) {
                    JavaUrlRequest.AsyncUrlRequestCallback asyncUrlRequestCallback = javaUrlRequest.mCallbackAsync;
                    asyncUrlRequestCallback.execute(new JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6(asyncUrlRequestCallback, javaUrlRequest.mUrlResponseInfo, byteBuffer2, i2));
                    return;
                }
                ReadableByteChannel readableByteChannel2 = javaUrlRequest.mResponseChannel;
                if (readableByteChannel2 != null) {
                    readableByteChannel2.close();
                }
                if (javaUrlRequest.mState.compareAndSet(5, 7)) {
                    javaUrlRequest.fireDisconnect();
                    JavaUrlRequest.AsyncUrlRequestCallback asyncUrlRequestCallback2 = javaUrlRequest.mCallbackAsync;
                    asyncUrlRequestCallback2.mUserExecutor.execute(new RetriableStream.Sublistener.AnonymousClass4(asyncUrlRequestCallback2, javaUrlRequest.mUrlResponseInfo, 15, (char[]) null));
                }
            }
        }, 13, (byte[]) null));
    }

    @Override // org.chromium.net.UrlRequest
    public final void start() {
        this.mAdditionalStatusDetails = 10;
        this.mEngine.mActiveRequestCount.incrementAndGet();
        transitionStates(0, 1, new CronetUrlRequest.AnonymousClass8((Object) this, 3));
    }

    public final void transitionStates(int i, int i2, Runnable runnable) {
        if (!this.mState.compareAndSet(i, i2)) {
            int i3 = this.mState.get();
            if (i3 != 8 && i3 != 6) {
                throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i3, i, "Invalid state transition - expected ", " but was "));
            }
            return;
        }
        runnable.run();
    }
}
