package org.chromium.net.impl;

import J.N;
import _COROUTINE._BOUNDARY;
import android.util.Log;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.mlkit.logging.schema.OtherError;
import com.google.mlkit.logging.schema.acceleration.NNAPIInfo;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import j$.time.Duration;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.internal.LimitedDispatcher;
import org.chromium.net.CronetException;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.InlineExecutionProhibitedException;
import org.chromium.net.UrlRequest;
import org.chromium.net.impl.CronetLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetUrlRequest extends ExperimentalUrlRequest {
    private final boolean mAllowDirectExecutor;
    public final VersionSafeCallbacks$UrlRequestCallback mCallback;
    private final boolean mDisableCache;
    private final boolean mDisableConnectionMigration;
    public CronetException mException;
    private final Executor mExecutor;
    private boolean mFinalUserCallbackThrew;
    private int mFinishedReason;
    private final int mIdempotency;
    private final String mInitialMethod;
    private final String mInitialUrl;
    private final CronetLogger mLogger;
    private CronetMetrics mMetrics;
    private final long mNetworkHandle;
    private int mNonfinalUserCallbackExceptionCount;
    private OnReadCompletedRunnable mOnReadCompletedTask;
    private final int mPriority;
    private boolean mQuicConnectionMigrationAttempted;
    private boolean mQuicConnectionMigrationSuccessful;
    private int mReadCount;
    private final Collection mRequestAnnotations;
    public final CronetUrlRequestContext mRequestContext;
    private final VersionSafeCallbacks$RequestFinishedInfoListener mRequestFinishedListener;
    private final List mRequestHeaders;
    public UrlResponseInfoImpl mResponseInfo;
    private boolean mStarted;
    private final int mTrafficStatsTag;
    private final boolean mTrafficStatsTagSet;
    private final int mTrafficStatsUid;
    private final boolean mTrafficStatsUidSet;
    public final CronetUploadDataStream mUploadDataStream;
    private final List mUrlChain;
    public long mUrlRequestAdapter;
    public final Object mUrlRequestAdapterLock = new Object();
    private boolean mWaitingOnRead;
    public boolean mWaitingOnRedirect;

    /* compiled from: PG */
    /* renamed from: org.chromium.net.impl.CronetUrlRequest$8 */
    /* loaded from: classes.dex */
    public final class AnonymousClass8 implements Runnable {
        final /* synthetic */ Object CronetUrlRequest$8$ar$this$0;
        private final /* synthetic */ int switching_field;

        public /* synthetic */ AnonymousClass8(Object obj, int i) {
            this.switching_field = i;
            this.CronetUrlRequest$8$ar$this$0 = obj;
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0148 A[Catch: RuntimeException -> 0x016e, TryCatch #3 {RuntimeException -> 0x016e, blocks: (B:8:0x0015, B:10:0x0023, B:15:0x0104, B:23:0x0137, B:26:0x014b, B:28:0x0148, B:29:0x0123, B:30:0x0130, B:35:0x00e0, B:37:0x00e6, B:39:0x00f2, B:44:0x0096, B:45:0x00a0, B:47:0x00a6, B:49:0x00b4, B:50:0x00ba, B:53:0x00c1, B:54:0x00cb, B:56:0x00d1, B:59:0x00d9, B:69:0x004b, B:70:0x005c, B:72:0x0062, B:74:0x0070, B:75:0x0076, B:78:0x007e, B:84:0x0039), top: B:7:0x0015 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 594
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.impl.CronetUrlRequest.AnonymousClass8.run():void");
        }

        public AnonymousClass8(ExperimentalUrlRequest experimentalUrlRequest, int i) {
            this.switching_field = i;
            this.CronetUrlRequest$8$ar$this$0 = experimentalUrlRequest;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnReadCompletedRunnable implements Runnable {
        Object CronetUrlRequest$OnReadCompletedRunnable$ar$mByteBuffer;
        final /* synthetic */ Object CronetUrlRequest$OnReadCompletedRunnable$ar$this$0;
        private final /* synthetic */ int switching_field;

        public OnReadCompletedRunnable(LimitedDispatcher limitedDispatcher, Runnable runnable, int i) {
            this.switching_field = i;
            this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0 = limitedDispatcher;
            this.CronetUrlRequest$OnReadCompletedRunnable$ar$mByteBuffer = runnable;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, kotlin.coroutines.CoroutineContext] */
        /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.Object, kotlin.coroutines.CoroutineContext] */
        /* JADX WARN: Type inference failed for: r1v18, types: [java.lang.Object, java.lang.Runnable] */
        @Override // java.lang.Runnable
        public final void run() {
            if (this.switching_field != 0) {
                int i = 0;
                while (true) {
                    try {
                        this.CronetUrlRequest$OnReadCompletedRunnable$ar$mByteBuffer.run();
                    } catch (Throwable th) {
                        OtherError.handleCoroutineException(EmptyCoroutineContext.INSTANCE, th);
                    }
                    Runnable obtainTaskOrDeallocateWorker = ((LimitedDispatcher) this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0).obtainTaskOrDeallocateWorker();
                    if (obtainTaskOrDeallocateWorker == null) {
                        return;
                    }
                    this.CronetUrlRequest$OnReadCompletedRunnable$ar$mByteBuffer = obtainTaskOrDeallocateWorker;
                    i++;
                    if (i >= 16) {
                        ?? r1 = this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0;
                        if (((LimitedDispatcher) r1).dispatcher.isDispatchNeeded(r1)) {
                            ?? r0 = this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0;
                            ((LimitedDispatcher) r0).dispatcher.dispatch(r0, this);
                            return;
                        }
                    }
                }
            } else {
                ((CronetUrlRequest) this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0).checkCallingThread();
                Object obj = this.CronetUrlRequest$OnReadCompletedRunnable$ar$mByteBuffer;
                this.CronetUrlRequest$OnReadCompletedRunnable$ar$mByteBuffer = null;
                try {
                    synchronized (((CronetUrlRequest) this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0).mUrlRequestAdapterLock) {
                        if (!((CronetUrlRequest) this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0).isDoneLocked()) {
                            ((CronetUrlRequest) this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0).mWaitingOnRead = true;
                            Object obj2 = this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0;
                            ((CronetUrlRequest) obj2).mCallback.onReadCompleted((UrlRequest) obj2, ((CronetUrlRequest) obj2).mResponseInfo, (ByteBuffer) obj);
                        }
                    }
                } catch (Exception e) {
                    ((CronetUrlRequest) this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0).onNonfinalCallbackException(e);
                }
            }
        }

        public OnReadCompletedRunnable(CronetUrlRequest cronetUrlRequest, int i) {
            this.switching_field = i;
            this.CronetUrlRequest$OnReadCompletedRunnable$ar$this$0 = cronetUrlRequest;
        }
    }

    /* renamed from: -$$Nest$fputmWaitingOnRead$ar$ds */
    public static /* bridge */ /* synthetic */ void m407$$Nest$fputmWaitingOnRead$ar$ds(CronetUrlRequest cronetUrlRequest) {
        cronetUrlRequest.mWaitingOnRead = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0079, code lost:
    
        if (r24 != 2) goto L57;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public CronetUrlRequest(org.chromium.net.impl.CronetUrlRequestContext r10, java.lang.String r11, int r12, org.chromium.net.UrlRequest.Callback r13, java.util.concurrent.Executor r14, java.util.Collection r15, boolean r16, boolean r17, boolean r18, boolean r19, int r20, boolean r21, int r22, org.chromium.net.RequestFinishedInfo.Listener r23, int r24, long r25, java.lang.String r27, java.util.ArrayList r28, org.chromium.net.UploadDataProvider r29, java.util.concurrent.Executor r30) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r23
            r5 = r24
            r6 = r29
            r9.<init>()
            java.lang.Object r7 = new java.lang.Object
            r7.<init>()
            r0.mUrlRequestAdapterLock = r7
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r0.mUrlChain = r7
            r8 = r18
            r0.mAllowDirectExecutor = r8
            r0.mRequestContext = r1
            org.chromium.net.impl.CronetLogger r1 = r1.mLogger
            r0.mLogger = r1
            r0.mInitialUrl = r2
            r7.add(r11)
            r1 = 2
            r2 = 1
            if (r3 == 0) goto L3f
            if (r3 == r2) goto L3d
            r7 = 3
            if (r3 == r1) goto L40
            r8 = 4
            if (r3 == r7) goto L3b
            if (r3 == r8) goto L39
            goto L3b
        L39:
            r7 = 5
            goto L40
        L3b:
            r7 = r8
            goto L40
        L3d:
            r7 = r1
            goto L40
        L3f:
            r7 = r2
        L40:
            r0.mPriority = r7
            org.chromium.net.impl.VersionSafeCallbacks$UrlRequestCallback r3 = new org.chromium.net.impl.VersionSafeCallbacks$UrlRequestCallback
            r7 = r13
            r3.<init>(r13)
            r0.mCallback = r3
            r3 = r14
            r0.mExecutor = r3
            r3 = r15
            r0.mRequestAnnotations = r3
            r3 = r16
            r0.mDisableCache = r3
            r3 = r17
            r0.mDisableConnectionMigration = r3
            r3 = r19
            r0.mTrafficStatsTagSet = r3
            r3 = r20
            r0.mTrafficStatsTag = r3
            r3 = r21
            r0.mTrafficStatsUidSet = r3
            r3 = r22
            r0.mTrafficStatsUid = r3
            r3 = 0
            if (r4 == 0) goto L71
            org.chromium.net.impl.VersionSafeCallbacks$RequestFinishedInfoListener r7 = new org.chromium.net.impl.VersionSafeCallbacks$RequestFinishedInfoListener
            r7.<init>(r4)
            goto L72
        L71:
            r7 = r3
        L72:
            r0.mRequestFinishedListener = r7
            r4 = 0
            if (r5 == 0) goto L7e
            if (r5 == r2) goto L7c
            if (r5 == r1) goto L7f
            goto L7e
        L7c:
            r1 = r2
            goto L7f
        L7e:
            r1 = r4
        L7f:
            r0.mIdempotency = r1
            r1 = r25
            r0.mNetworkHandle = r1
            r1 = r27
            r0.mInitialMethod = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = r28
            r1.<init>(r2)
            java.util.List r1 = j$.util.DesugarCollections.unmodifiableList(r1)
            r0.mRequestHeaders = r1
            if (r6 != 0) goto L99
            goto La0
        L99:
            org.chromium.net.impl.CronetUploadDataStream r3 = new org.chromium.net.impl.CronetUploadDataStream
            r1 = r30
            r3.<init>(r6, r1, r9)
        La0:
            r0.mUploadDataStream = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.impl.CronetUrlRequest.<init>(org.chromium.net.impl.CronetUrlRequestContext, java.lang.String, int, org.chromium.net.UrlRequest$Callback, java.util.concurrent.Executor, java.util.Collection, boolean, boolean, boolean, boolean, int, boolean, int, org.chromium.net.RequestFinishedInfo$Listener, int, long, java.lang.String, java.util.ArrayList, org.chromium.net.UploadDataProvider, java.util.concurrent.Executor):void");
    }

    private final void failWithException(CronetException cronetException) {
        synchronized (this.mUrlRequestAdapterLock) {
            if (isDoneLocked()) {
                return;
            }
            this.mException = cronetException;
            destroyRequestAdapterLocked(1);
        }
    }

    private void onCanceled() {
        postTaskToExecutor(new AnonymousClass8((ExperimentalUrlRequest) this, 1));
    }

    private void onError(int i, int i2, int i3, String str, long j) {
        UrlResponseInfoImpl urlResponseInfoImpl = this.mResponseInfo;
        if (urlResponseInfoImpl != null) {
            urlResponseInfoImpl.setReceivedByteCount(j);
        }
        if (i != 10) {
            if (i == 3) {
                i = 3;
            } else {
                switch (i) {
                    case 1:
                        i = 1;
                        break;
                    case 2:
                        i = 2;
                        break;
                    case 3:
                        i = 3;
                        break;
                    case 4:
                        i = 4;
                        break;
                    case 5:
                        i = 5;
                        break;
                    case 6:
                        i = 6;
                        break;
                    case 7:
                        i = 7;
                        break;
                    case 8:
                        i = 8;
                        break;
                    case 9:
                        i = 9;
                        break;
                    case 10:
                        i = 10;
                        break;
                    case 11:
                        i = 11;
                        break;
                    default:
                        Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unknown error code: "));
                        break;
                }
                failWithException(new NetworkExceptionImpl("Exception in CronetUrlRequest: ".concat(String.valueOf(str)), i, i2));
                return;
            }
        }
        failWithException(new QuicExceptionImpl("Exception in CronetUrlRequest: ".concat(String.valueOf(str)), i, i2, i3));
    }

    private void onMetricsCollected(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, boolean z, long j14, long j15, boolean z2, boolean z3) {
        if (this.mMetrics == null) {
            this.mMetrics = new CronetMetrics(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, z, j14, j15);
            this.mQuicConnectionMigrationAttempted = z2;
            this.mQuicConnectionMigrationSuccessful = z3;
            return;
        }
        throw new IllegalStateException("Metrics collection should only happen once.");
    }

    private void onNativeAdapterDestroyed() {
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mException == null) {
                return;
            }
            try {
                this.mExecutor.execute(new AnonymousClass8((ExperimentalUrlRequest) this, 0));
            } catch (RejectedExecutionException e) {
                Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Exception posting task to executor", e);
            }
        }
    }

    private void onReadCompleted(ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        this.mResponseInfo.setReceivedByteCount(j);
        if (byteBuffer.position() == i2 && byteBuffer.limit() == i3) {
            if (this.mOnReadCompletedTask == null) {
                this.mOnReadCompletedTask = new OnReadCompletedRunnable(this, 0);
            }
            OnReadCompletedRunnable onReadCompletedRunnable = this.mOnReadCompletedTask;
            onReadCompletedRunnable.CronetUrlRequest$OnReadCompletedRunnable$ar$mByteBuffer = byteBuffer;
            postTaskToExecutor(onReadCompletedRunnable);
            return;
        }
        failWithException(new CronetExceptionImpl("ByteBuffer modified externally during read", null));
    }

    private void onRedirectReceived(String str, int i, String str2, String[] strArr, boolean z, String str3, String str4, long j) {
        List list = this.mUrlChain;
        UrlResponseInfoImpl prepareResponseInfoOnNetworkThread = prepareResponseInfoOnNetworkThread(i, str2, strArr, z, str3, str4, j);
        list.add(str);
        postTaskToExecutor(new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, prepareResponseInfoOnNetworkThread, str, 19));
    }

    private void onResponseStarted(int i, String str, String[] strArr, boolean z, String str2, String str3, long j) {
        this.mResponseInfo = prepareResponseInfoOnNetworkThread(i, str, strArr, z, str2, str3, j);
        postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 19));
    }

    private void onStatus(VersionSafeCallbacks$UrlRequestStatusListener versionSafeCallbacks$UrlRequestStatusListener, int i) {
        postTaskToExecutor(new RatingView$$ExternalSyntheticLambda5(versionSafeCallbacks$UrlRequestStatusListener, i, 12, (char[]) null));
    }

    private void onSucceeded(long j) {
        this.mResponseInfo.setReceivedByteCount(j);
        postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 20));
    }

    private final void postTaskToExecutor(Runnable runnable) {
        try {
            this.mExecutor.execute(runnable);
        } catch (RejectedExecutionException e) {
            Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Exception posting task to executor", e);
            failWithException(new CronetExceptionImpl("Exception posting task to executor", e));
        }
    }

    private final UrlResponseInfoImpl prepareResponseInfoOnNetworkThread(int i, String str, String[] strArr, boolean z, String str2, String str3, long j) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
            arrayList.add(new AbstractMap.SimpleImmutableEntry(strArr[i2], strArr[i2 + 1]));
        }
        return new UrlResponseInfoImpl(new ArrayList(this.mUrlChain), i, str, arrayList, z, str2, str3, j);
    }

    @Override // org.chromium.net.UrlRequest
    public final void cancel() {
        synchronized (this.mUrlRequestAdapterLock) {
            if (!isDoneLocked() && this.mStarted) {
                destroyRequestAdapterLocked(2);
            }
        }
    }

    public final void checkCallingThread() {
        if (!this.mAllowDirectExecutor) {
            if (Thread.currentThread() == this.mRequestContext.mNetworkThread) {
                throw new InlineExecutionProhibitedException();
            }
        }
    }

    public final void destroyRequestAdapterLocked(int i) {
        boolean z;
        this.mFinishedReason = i;
        if (this.mUrlRequestAdapter == 0) {
            return;
        }
        this.mRequestContext.onRequestDestroyed();
        long j = this.mUrlRequestAdapter;
        if (i == 2) {
            z = true;
        } else {
            z = false;
        }
        N.M4znfYdB(j, this, z);
        this.mUrlRequestAdapter = 0L;
    }

    @Override // org.chromium.net.UrlRequest
    public final void followRedirect() {
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mWaitingOnRedirect) {
                this.mWaitingOnRedirect = false;
                if (isDoneLocked()) {
                    return;
                }
                N.Mhp54Oqs(this.mUrlRequestAdapter, this);
                return;
            }
            throw new IllegalStateException("No redirect to follow.");
        }
    }

    @Override // org.chromium.net.UrlRequest
    public final void getStatus(UrlRequest.StatusListener statusListener) {
        VersionSafeCallbacks$UrlRequestStatusListener versionSafeCallbacks$UrlRequestStatusListener = new VersionSafeCallbacks$UrlRequestStatusListener(statusListener);
        synchronized (this.mUrlRequestAdapterLock) {
            long j = this.mUrlRequestAdapter;
            if (j != 0) {
                N.MgIIMpT9(j, this, versionSafeCallbacks$UrlRequestStatusListener);
            } else {
                postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(versionSafeCallbacks$UrlRequestStatusListener, 18));
            }
        }
    }

    @Override // org.chromium.net.UrlRequest
    public final boolean isDone() {
        boolean isDoneLocked;
        synchronized (this.mUrlRequestAdapterLock) {
            isDoneLocked = isDoneLocked();
        }
        return isDoneLocked;
    }

    public final boolean isDoneLocked() {
        if (this.mStarted && this.mUrlRequestAdapter == 0) {
            return true;
        }
        return false;
    }

    public final void maybeReportMetrics() {
        Map<String, List<String>> emptyMap;
        String str;
        boolean z;
        int i;
        long j;
        long max;
        long estimateHeadersSizeInBytes;
        long max2;
        Duration ofSeconds;
        Duration ofSeconds2;
        int i2;
        RefCountDelegate refCountDelegate = new RefCountDelegate(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 16, null));
        try {
            if (this.mMetrics != null) {
                try {
                    CronetLogger cronetLogger = this.mLogger;
                    long j2 = this.mRequestContext.mLogId;
                    UrlResponseInfoImpl urlResponseInfoImpl = this.mResponseInfo;
                    if (urlResponseInfoImpl != null) {
                        emptyMap = urlResponseInfoImpl.getAllHeaders();
                        UrlResponseInfoImpl urlResponseInfoImpl2 = this.mResponseInfo;
                        String str2 = urlResponseInfoImpl2.mNegotiatedProtocol;
                        int i3 = urlResponseInfoImpl2.mHttpStatusCode;
                        z = urlResponseInfoImpl2.mWasCached;
                        str = str2;
                        i = i3;
                    } else {
                        emptyMap = Collections.emptyMap();
                        str = "";
                        z = false;
                        i = 0;
                    }
                    long longValue = this.mMetrics.mSentByteCount.longValue();
                    long j3 = 0;
                    if (z && longValue == 0) {
                        j = 0;
                        max = 0;
                    } else {
                        List<Map.Entry> list = this.mRequestHeaders;
                        if (list == null) {
                            j = 0;
                        } else {
                            j = 0;
                            for (Map.Entry entry : list) {
                                if (((String) entry.getKey()) != null) {
                                    j += r16.length();
                                }
                                if (((String) entry.getValue()) != null) {
                                    j += ((String) entry.getValue()).length();
                                }
                            }
                        }
                        j3 = 0;
                        max = Math.max(0L, longValue - j);
                    }
                    long longValue2 = this.mMetrics.mReceivedByteCount.longValue();
                    if (z && longValue2 == j3) {
                        estimateHeadersSizeInBytes = j3;
                        max2 = estimateHeadersSizeInBytes;
                    } else {
                        estimateHeadersSizeInBytes = NNAPIInfo.DeviceInfo.estimateHeadersSizeInBytes(emptyMap);
                        max2 = Math.max(j3, longValue2 - estimateHeadersSizeInBytes);
                    }
                    if (this.mMetrics.getRequestStart() != null && this.mMetrics.getResponseStart() != null) {
                        ofSeconds = Duration.ofMillis(this.mMetrics.getResponseStart().getTime() - this.mMetrics.getRequestStart().getTime());
                    } else {
                        ofSeconds = Duration.ofSeconds(0L);
                    }
                    if (this.mMetrics.getRequestStart() != null && this.mMetrics.getRequestEnd() != null) {
                        ofSeconds2 = Duration.ofMillis(this.mMetrics.getRequestEnd().getTime() - this.mMetrics.getRequestStart().getTime());
                    } else {
                        ofSeconds2 = Duration.ofSeconds(0L);
                    }
                    Duration duration = ofSeconds2;
                    boolean z2 = this.mQuicConnectionMigrationAttempted;
                    boolean z3 = this.mQuicConnectionMigrationSuccessful;
                    int finishedReasonToCronetTrafficInfoRequestTerminalState$ar$edu = NNAPIInfo.DeviceInfo.finishedReasonToCronetTrafficInfoRequestTerminalState$ar$edu(this.mFinishedReason);
                    int i4 = this.mNonfinalUserCallbackExceptionCount;
                    int i5 = this.mReadCount;
                    CronetUploadDataStream cronetUploadDataStream = this.mUploadDataStream;
                    if (cronetUploadDataStream == null) {
                        i2 = 0;
                    } else {
                        i2 = cronetUploadDataStream.mReadCount.get();
                    }
                    cronetLogger.logCronetTrafficInfo(j2, new CronetLogger.CronetTrafficInfo(j, max, estimateHeadersSizeInBytes, max2, i, ofSeconds, duration, str, z2, z3, finishedReasonToCronetTrafficInfoRequestTerminalState$ar$edu, i4, i5, i2, false, this.mFinalUserCallbackThrew));
                } catch (RuntimeException e) {
                    Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Error while trying to log CronetTrafficInfo: ", e);
                }
                this.mRequestContext.reportRequestFinished(new RequestFinishedInfoImpl(this.mInitialUrl, this.mRequestAnnotations, this.mMetrics, this.mFinishedReason, this.mResponseInfo, this.mException), refCountDelegate, this.mRequestFinishedListener);
            }
        } finally {
            refCountDelegate.decrement();
        }
    }

    public final void onFinalCallbackException(String str, Exception exc) {
        this.mFinalUserCallbackThrew = true;
        Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Exception in ", " method"), exc);
    }

    public final void onNonfinalCallbackException(Exception exc) {
        this.mNonfinalUserCallbackExceptionCount++;
        CallbackExceptionImpl callbackExceptionImpl = new CallbackExceptionImpl("Exception received from UrlRequest.Callback", exc);
        Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Exception in CalledByNative method", exc);
        failWithException(callbackExceptionImpl);
    }

    public final void onUploadException(Throwable th) {
        CallbackExceptionImpl callbackExceptionImpl = new CallbackExceptionImpl("Exception received from UploadDataProvider", th);
        Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Exception in upload method", th);
        failWithException(callbackExceptionImpl);
    }

    @Override // org.chromium.net.UrlRequest
    public final void read(ByteBuffer byteBuffer) {
        NNAPIInfo.checkHasRemaining(byteBuffer);
        NNAPIInfo.checkDirect(byteBuffer);
        synchronized (this.mUrlRequestAdapterLock) {
            if (this.mWaitingOnRead) {
                this.mWaitingOnRead = false;
                if (isDoneLocked()) {
                    return;
                }
                if (N.MfCxA8r3(this.mUrlRequestAdapter, this, byteBuffer, byteBuffer.position(), byteBuffer.limit())) {
                    this.mReadCount++;
                    return;
                } else {
                    this.mWaitingOnRead = true;
                    throw new IllegalArgumentException("Unable to call native read");
                }
            }
            throw new IllegalStateException("Unexpected read attempt.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // org.chromium.net.UrlRequest
    public final void start() {
        int i;
        synchronized (this.mUrlRequestAdapterLock) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    synchronized (this.mUrlRequestAdapterLock) {
                        try {
                            if (!this.mStarted && !isDoneLocked()) {
                                try {
                                } catch (RuntimeException e) {
                                    e = e;
                                }
                                try {
                                    try {
                                        this.mUrlRequestAdapter = N.MuOIsMvf(this, this.mRequestContext.getUrlRequestContextAdapter(), this.mInitialUrl, this.mPriority, this.mDisableCache, this.mDisableConnectionMigration, this.mTrafficStatsTagSet, this.mTrafficStatsTag, this.mTrafficStatsUidSet, this.mTrafficStatsUid, this.mIdempotency, this.mNetworkHandle);
                                        this.mRequestContext.onRequestStarted();
                                        i = N.M51RPBJe(this.mUrlRequestAdapter, this, this.mInitialMethod);
                                        try {
                                            if (i != 0) {
                                                boolean z = false;
                                                for (Map.Entry entry : this.mRequestHeaders) {
                                                    if (((String) entry.getKey()).equalsIgnoreCase("Content-Type") && !((String) entry.getValue()).isEmpty()) {
                                                        z = true;
                                                    }
                                                    if (!N.MvHusd1J(this.mUrlRequestAdapter, this, (String) entry.getKey(), (String) entry.getValue())) {
                                                        throw new IllegalArgumentException("Invalid header with headername: " + ((String) entry.getKey()));
                                                    }
                                                }
                                                CronetUploadDataStream cronetUploadDataStream = this.mUploadDataStream;
                                                if (cronetUploadDataStream != null) {
                                                    if (z) {
                                                        this.mStarted = true;
                                                        cronetUploadDataStream.postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 17));
                                                        return;
                                                    }
                                                    throw new IllegalArgumentException("Requests with upload data must have a Content-Type.");
                                                }
                                                this.mStarted = true;
                                                startInternalLocked();
                                                return;
                                            }
                                            throw new IllegalArgumentException("Invalid http method " + this.mInitialMethod);
                                        } catch (RuntimeException e2) {
                                            e = e2;
                                            destroyRequestAdapterLocked(i);
                                            this.mRequestContext.onRequestFinished();
                                            throw e;
                                        }
                                    } catch (RuntimeException e3) {
                                        e = e3;
                                        i = 1;
                                    }
                                } catch (RuntimeException e4) {
                                    e = e4;
                                    i = 1;
                                    destroyRequestAdapterLocked(i);
                                    this.mRequestContext.onRequestFinished();
                                    throw e;
                                }
                            } else {
                                throw new IllegalStateException("Request is already started.");
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        }
    }

    public final void startInternalLocked() {
        N.MabZ5m6r(this.mUrlRequestAdapter, this);
    }
}
