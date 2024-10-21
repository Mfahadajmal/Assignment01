package org.chromium.net.impl;

import J.N;
import _COROUTINE._BOUNDARY;
import android.util.Log;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0;
import com.google.mlkit.logging.schema.acceleration.NNAPIInfo;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.CronetException;
import org.chromium.net.ExperimentalBidirectionalStream;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.impl.UrlResponseInfoImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CronetBidirectionalStream extends ExperimentalBidirectionalStream {
    public final VersionSafeCallbacks$BidirectionalStreamCallback mCallback;
    private final boolean mDelayRequestHeadersUntilFirstFlush;
    private boolean mEndOfStreamWritten;
    private CronetException mException;
    private final Executor mExecutor;
    public boolean mFinalUserCallbackThrew;
    public int mFlushCount;
    private LinkedList mFlushData;
    public RefCountDelegate mInflightDoneCallbackCount;
    public final String mInitialMethod;
    private final int mInitialPriority;
    private final String mInitialUrl;
    public final CronetLogger mLogger;
    public RequestFinishedInfo.Metrics mMetrics;
    private long mNativeStream;
    private final long mNetworkHandle;
    public int mNonfinalUserCallbackExceptionCount;
    private OnReadCompletedRunnable mOnReadCompletedTask;
    private LinkedList mPendingData;
    public boolean mQuicConnectionMigrationAttempted;
    public boolean mQuicConnectionMigrationSuccessful;
    public int mReadCount;
    private final Collection mRequestAnnotations;
    public final CronetUrlRequestContext mRequestContext;
    public final String[] mRequestHeaders;
    public boolean mRequestHeadersSent;
    public UrlResponseInfoImpl mResponseInfo;
    private final int mTrafficStatsTag;
    private final boolean mTrafficStatsTagSet;
    private final int mTrafficStatsUid;
    private final boolean mTrafficStatsUidSet;
    public final Object mNativeStreamLock = new Object();
    public int mReadState = 0;
    public int mWriteState = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnReadCompletedRunnable implements Runnable {
        ByteBuffer mByteBuffer;
        boolean mEndOfStream;

        public OnReadCompletedRunnable() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                ByteBuffer byteBuffer = this.mByteBuffer;
                this.mByteBuffer = null;
                synchronized (CronetBidirectionalStream.this.mNativeStreamLock) {
                    if (CronetBidirectionalStream.this.isDoneLocked()) {
                        return;
                    }
                    boolean z = false;
                    if (this.mEndOfStream) {
                        CronetBidirectionalStream cronetBidirectionalStream = CronetBidirectionalStream.this;
                        cronetBidirectionalStream.mReadState = 4;
                        if (cronetBidirectionalStream.mWriteState == 10) {
                            z = true;
                        }
                    } else {
                        CronetBidirectionalStream.this.mReadState = 2;
                    }
                    CronetBidirectionalStream cronetBidirectionalStream2 = CronetBidirectionalStream.this;
                    cronetBidirectionalStream2.mCallback.onReadCompleted(cronetBidirectionalStream2, cronetBidirectionalStream2.mResponseInfo, byteBuffer, this.mEndOfStream);
                    if (z) {
                        CronetBidirectionalStream.this.maybeOnSucceededOnExecutor();
                    }
                }
            } catch (Exception e) {
                CronetBidirectionalStream.this.onNonfinalCallbackException(e);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnWriteCompletedRunnable implements Runnable {
        private ByteBuffer mByteBuffer;
        private final boolean mEndOfStream;

        public OnWriteCompletedRunnable(ByteBuffer byteBuffer, boolean z) {
            this.mByteBuffer = byteBuffer;
            this.mEndOfStream = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                ByteBuffer byteBuffer = this.mByteBuffer;
                this.mByteBuffer = null;
                synchronized (CronetBidirectionalStream.this.mNativeStreamLock) {
                    if (CronetBidirectionalStream.this.isDoneLocked()) {
                        return;
                    }
                    boolean z = false;
                    if (this.mEndOfStream) {
                        CronetBidirectionalStream cronetBidirectionalStream = CronetBidirectionalStream.this;
                        cronetBidirectionalStream.mWriteState = 10;
                        if (cronetBidirectionalStream.mReadState == 4) {
                            z = true;
                        }
                    }
                    CronetBidirectionalStream cronetBidirectionalStream2 = CronetBidirectionalStream.this;
                    cronetBidirectionalStream2.mCallback.onWriteCompleted(cronetBidirectionalStream2, cronetBidirectionalStream2.mResponseInfo, byteBuffer, this.mEndOfStream);
                    if (z) {
                        CronetBidirectionalStream.this.maybeOnSucceededOnExecutor();
                    }
                }
            } catch (Exception e) {
                CronetBidirectionalStream.this.onNonfinalCallbackException(e);
            }
        }
    }

    public CronetBidirectionalStream(CronetUrlRequestContext cronetUrlRequestContext, String str, int i, BidirectionalStream.Callback callback, Executor executor, String str2, List list, boolean z, Collection collection, boolean z2, int i2, boolean z3, int i3, long j) {
        int i4 = 0;
        this.mRequestContext = cronetUrlRequestContext;
        this.mInitialUrl = str;
        int i5 = 1;
        if (i != 0) {
            if (i != 1) {
                i5 = 3;
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            i5 = 5;
                        } else {
                            throw new IllegalArgumentException("Invalid stream priority.");
                        }
                    } else {
                        i5 = 4;
                    }
                }
            } else {
                i5 = 2;
            }
        }
        this.mInitialPriority = i5;
        this.mCallback = new VersionSafeCallbacks$BidirectionalStreamCallback(callback);
        this.mExecutor = executor;
        this.mInitialMethod = str2;
        int size = list.size();
        String[] strArr = new String[size + size];
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int i6 = i4 + 1;
            strArr[i4] = (String) entry.getKey();
            i4 += 2;
            strArr[i6] = (String) entry.getValue();
        }
        this.mRequestHeaders = strArr;
        this.mDelayRequestHeadersUntilFirstFlush = z;
        this.mPendingData = new LinkedList();
        this.mFlushData = new LinkedList();
        this.mRequestAnnotations = collection;
        this.mTrafficStatsTagSet = z2;
        this.mTrafficStatsTag = i2;
        this.mTrafficStatsUidSet = z3;
        this.mTrafficStatsUid = i3;
        this.mNetworkHandle = j;
        this.mLogger = cronetUrlRequestContext.mLogger;
    }

    private final void destroyNativeStreamLocked(boolean z) {
        String str = CronetUrlRequestContext.LOG_TAG;
        toString();
        long j = this.mNativeStream;
        if (j == 0) {
            return;
        }
        N.MS2l1kNx(j, this, z);
        if (this.mReadState != 0) {
            this.mRequestContext.onRequestDestroyed();
        }
        this.mNativeStream = 0L;
    }

    public static boolean doesMethodAllowWriteData(String str) {
        if (!str.equals("GET") && !str.equals("HEAD")) {
            return true;
        }
        return false;
    }

    private final void failWithException(CronetException cronetException) {
        postTaskToExecutor(new RetriableStream.Sublistener.AnonymousClass4(this, cronetException, 5));
    }

    private static ArrayList headersListFromStrings(String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length >> 1);
        for (int i = 0; i < strArr.length; i += 2) {
            arrayList.add(new AbstractMap.SimpleImmutableEntry(strArr[i], strArr[i + 1]));
        }
        return arrayList;
    }

    private void onCanceled() {
        postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 13));
    }

    private void onError(int i, int i2, int i3, String str, long j) {
        UrlResponseInfoImpl urlResponseInfoImpl = this.mResponseInfo;
        if (urlResponseInfoImpl != null) {
            urlResponseInfoImpl.setReceivedByteCount(j);
        }
        if (i != 10 && i != 3) {
            failWithException(new BidirectionalStreamNetworkException("Exception in BidirectionalStream: ".concat(String.valueOf(str)), i, i2));
        } else {
            failWithException(new QuicExceptionImpl("Exception in BidirectionalStream: ".concat(String.valueOf(str)), i, i2, i3));
        }
    }

    private void onMetricsCollected(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, boolean z, long j14, long j15, boolean z2, boolean z3) {
        try {
            if (this.mMetrics == null) {
                CronetMetrics cronetMetrics = new CronetMetrics(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, z, j14, j15);
                this.mMetrics = cronetMetrics;
                this.mQuicConnectionMigrationAttempted = z2;
                this.mQuicConnectionMigrationSuccessful = z3;
                this.mRequestContext.reportRequestFinished(new RequestFinishedInfoImpl(this.mInitialUrl, this.mRequestAnnotations, cronetMetrics, getFinishedReason(), this.mResponseInfo, this.mException), this.mInflightDoneCallbackCount, null);
                return;
            }
            throw new IllegalStateException("Metrics collection should only happen once.");
        } finally {
            this.mInflightDoneCallbackCount.decrement();
        }
    }

    private void onReadCompleted(ByteBuffer byteBuffer, int i, int i2, int i3, long j) {
        int i4;
        boolean z;
        this.mResponseInfo.setReceivedByteCount(j);
        if (byteBuffer.position() == i2 && byteBuffer.limit() == i3) {
            if (i >= 0 && (i4 = i2 + i) <= i3) {
                OnReadCompletedRunnable onReadCompletedRunnable = this.mOnReadCompletedTask;
                onReadCompletedRunnable.mByteBuffer = byteBuffer;
                if (i == 0) {
                    z = true;
                } else {
                    z = false;
                }
                onReadCompletedRunnable.mEndOfStream = z;
                postTaskToExecutor(onReadCompletedRunnable);
                return;
            }
            failWithException(new CronetExceptionImpl("Invalid number of bytes read", null));
            return;
        }
        failWithException(new CronetExceptionImpl("ByteBuffer modified externally during read", null));
    }

    private void onResponseHeadersReceived(int i, String str, String[] strArr, long j) {
        try {
            this.mResponseInfo = new UrlResponseInfoImpl(Arrays.asList(this.mInitialUrl), i, "", headersListFromStrings(strArr), false, str, null, j);
            postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 12));
        } catch (Exception unused) {
            failWithException(new CronetExceptionImpl("Cannot prepare ResponseInfo", null));
        }
    }

    private void onResponseTrailersReceived(String[] strArr) {
        postTaskToExecutor(new RetriableStream.Sublistener.AnonymousClass4(this, new UrlResponseInfoImpl.HeaderBlockImpl(headersListFromStrings(strArr)), 4));
    }

    private void onStreamReady(boolean z) {
        postTaskToExecutor(new MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0(this, z, 3));
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0051, code lost:
    
        failWithException(new org.chromium.net.impl.CronetExceptionImpl("ByteBuffer modified externally during write", null));
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005c, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onWritevCompleted(java.nio.ByteBuffer[] r7, int[] r8, int[] r9, boolean r10) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mNativeStreamLock
            monitor-enter(r0)
            boolean r1 = r6.isDoneLocked()     // Catch: java.lang.Throwable -> L5d
            if (r1 == 0) goto Lb
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5d
            return
        Lb:
            r1 = 8
            r6.mWriteState = r1     // Catch: java.lang.Throwable -> L5d
            java.util.LinkedList r1 = r6.mFlushData     // Catch: java.lang.Throwable -> L5d
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto L1a
            r6.sendFlushDataLocked()     // Catch: java.lang.Throwable -> L5d
        L1a:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5d
            r0 = 0
            r1 = r0
        L1d:
            int r2 = r7.length
            if (r1 >= r2) goto L5c
            r3 = r7[r1]
            int r4 = r3.position()
            r5 = r8[r1]
            if (r4 != r5) goto L51
            int r4 = r3.limit()
            r5 = r9[r1]
            if (r4 == r5) goto L33
            goto L51
        L33:
            int r4 = r3.limit()
            java.nio.Buffer r4 = r3.position(r4)
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            org.chromium.net.impl.CronetBidirectionalStream$OnWriteCompletedRunnable r4 = new org.chromium.net.impl.CronetBidirectionalStream$OnWriteCompletedRunnable
            if (r10 == 0) goto L47
            int r2 = r2 + (-1)
            if (r1 != r2) goto L47
            r2 = 1
            goto L48
        L47:
            r2 = r0
        L48:
            r4.<init>(r3, r2)
            r6.postTaskToExecutor(r4)
            int r1 = r1 + 1
            goto L1d
        L51:
            org.chromium.net.impl.CronetExceptionImpl r7 = new org.chromium.net.impl.CronetExceptionImpl
            java.lang.String r8 = "ByteBuffer modified externally during write"
            r9 = 0
            r7.<init>(r8, r9)
            r6.failWithException(r7)
        L5c:
            return
        L5d:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5d
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.impl.CronetBidirectionalStream.onWritevCompleted(java.nio.ByteBuffer[], int[], int[], boolean):void");
    }

    private final void postTaskToExecutor(Runnable runnable) {
        try {
            this.mExecutor.execute(runnable);
        } catch (RejectedExecutionException e) {
            Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Exception posting task to executor", e);
            synchronized (this.mNativeStreamLock) {
                this.mWriteState = 6;
                this.mReadState = 6;
                destroyNativeStreamLocked(false);
            }
        }
    }

    private final void sendFlushDataLocked() {
        boolean z;
        int size = this.mFlushData.size();
        ByteBuffer[] byteBufferArr = new ByteBuffer[size];
        int[] iArr = new int[size];
        int[] iArr2 = new int[size];
        for (int i = 0; i < size; i++) {
            ByteBuffer byteBuffer = (ByteBuffer) this.mFlushData.poll();
            byteBufferArr[i] = byteBuffer;
            iArr[i] = byteBuffer.position();
            iArr2[i] = byteBuffer.limit();
        }
        this.mWriteState = 9;
        this.mRequestHeadersSent = true;
        long j = this.mNativeStream;
        if (this.mEndOfStreamWritten && this.mPendingData.isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        if (N.MwJCBTMQ(j, this, byteBufferArr, iArr, iArr2, z)) {
            return;
        }
        this.mWriteState = 8;
        throw new IllegalArgumentException("Unable to call native writev.");
    }

    @Override // org.chromium.net.BidirectionalStream
    public final void cancel() {
        synchronized (this.mNativeStreamLock) {
            if (!isDoneLocked() && this.mReadState != 0) {
                this.mWriteState = 5;
                this.mReadState = 5;
                destroyNativeStreamLocked(true);
            }
        }
    }

    public final void failWithExceptionOnExecutor(CronetException cronetException) {
        this.mException = cronetException;
        synchronized (this.mNativeStreamLock) {
            if (isDoneLocked()) {
                return;
            }
            this.mWriteState = 6;
            this.mReadState = 6;
            destroyNativeStreamLocked(false);
            try {
                this.mCallback.onFailed(this, this.mResponseInfo, cronetException);
            } catch (Exception e) {
                onFinalCallbackException("onFailed", e);
            }
            this.mInflightDoneCallbackCount.decrement();
        }
    }

    @Override // org.chromium.net.BidirectionalStream
    public final void flush() {
        int i;
        synchronized (this.mNativeStreamLock) {
            if (!isDoneLocked() && ((i = this.mWriteState) == 8 || i == 9)) {
                if (this.mPendingData.isEmpty() && this.mFlushData.isEmpty()) {
                    if (!this.mRequestHeadersSent) {
                        this.mRequestHeadersSent = true;
                        N.MGLIR7Sc(this.mNativeStream, this);
                        if (!doesMethodAllowWriteData(this.mInitialMethod)) {
                            this.mWriteState = 10;
                        }
                    }
                    return;
                }
                if (!this.mPendingData.isEmpty()) {
                    this.mFlushData.addAll(this.mPendingData);
                    this.mPendingData.clear();
                }
                if (this.mWriteState == 9) {
                    return;
                }
                sendFlushDataLocked();
                this.mFlushCount++;
            }
        }
    }

    public final int getFinishedReason() {
        int i = this.mReadState;
        int i2 = this.mWriteState;
        if (i == i2) {
            if (i != 5) {
                if (i != 6) {
                    if (i == 7) {
                        return 0;
                    }
                    throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Cronet bidirectional stream read state is ", " which is not a valid finished state!"));
                }
                return 1;
            }
            return 2;
        }
        throw new IllegalStateException("Cronet bidirectional stream read state is " + i + " which is different from write state " + i2 + "!");
    }

    @Override // org.chromium.net.BidirectionalStream
    public final boolean isDone() {
        boolean isDoneLocked;
        synchronized (this.mNativeStreamLock) {
            isDoneLocked = isDoneLocked();
        }
        return isDoneLocked;
    }

    public final boolean isDoneLocked() {
        if (this.mReadState != 0 && this.mNativeStream == 0) {
            return true;
        }
        return false;
    }

    public final void maybeOnSucceededOnExecutor() {
        synchronized (this.mNativeStreamLock) {
            if (isDoneLocked()) {
                return;
            }
            if (this.mWriteState == 10 && this.mReadState == 4) {
                this.mWriteState = 7;
                this.mReadState = 7;
                destroyNativeStreamLocked(false);
                try {
                    this.mCallback.onSucceeded(this, this.mResponseInfo);
                } catch (Exception e) {
                    onFinalCallbackException("onSucceeded", e);
                }
                this.mInflightDoneCallbackCount.decrement();
            }
        }
    }

    public final void onFinalCallbackException(String str, Exception exc) {
        this.mFinalUserCallbackThrew = true;
        Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Exception in ", " method"), exc);
    }

    public final void onNonfinalCallbackException(Exception exc) {
        this.mNonfinalUserCallbackExceptionCount++;
        CallbackExceptionImpl callbackExceptionImpl = new CallbackExceptionImpl("CalledByNative method has thrown an exception", exc);
        Log.e("cr_".concat(String.valueOf(CronetUrlRequestContext.LOG_TAG)), "Exception in CalledByNative method", exc);
        failWithExceptionOnExecutor(callbackExceptionImpl);
    }

    @Override // org.chromium.net.BidirectionalStream
    public final void read(ByteBuffer byteBuffer) {
        synchronized (this.mNativeStreamLock) {
            NNAPIInfo.checkHasRemaining(byteBuffer);
            NNAPIInfo.checkDirect(byteBuffer);
            if (this.mReadState == 2) {
                if (isDoneLocked()) {
                    return;
                }
                if (this.mOnReadCompletedTask == null) {
                    this.mOnReadCompletedTask = new OnReadCompletedRunnable();
                }
                this.mReadState = 3;
                if (N.Md_rPmgC(this.mNativeStream, this, byteBuffer, byteBuffer.position(), byteBuffer.limit())) {
                    this.mReadCount++;
                    return;
                } else {
                    this.mReadState = 2;
                    throw new IllegalArgumentException("Unable to call native read");
                }
            }
            throw new IllegalStateException("Unexpected read attempt.");
        }
    }

    @Override // org.chromium.net.BidirectionalStream
    public final void start() {
        synchronized (this.mNativeStreamLock) {
            if (this.mReadState == 0) {
                try {
                    long MqTDYvZd = N.MqTDYvZd(this, this.mRequestContext.getUrlRequestContextAdapter(), !this.mDelayRequestHeadersUntilFirstFlush, this.mTrafficStatsTagSet, this.mTrafficStatsTag, this.mTrafficStatsUidSet, this.mTrafficStatsUid, this.mNetworkHandle);
                    this.mNativeStream = MqTDYvZd;
                    int McDUim_I = N.McDUim_I(MqTDYvZd, this, this.mInitialUrl, this.mInitialPriority, this.mInitialMethod, this.mRequestHeaders, !doesMethodAllowWriteData(r6));
                    if (McDUim_I != -1) {
                        if (McDUim_I <= 0) {
                            this.mRequestContext.onRequestStarted();
                            RefCountDelegate refCountDelegate = new RefCountDelegate(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 11, null));
                            this.mInflightDoneCallbackCount = refCountDelegate;
                            refCountDelegate.increment();
                            this.mWriteState = 1;
                            this.mReadState = 1;
                        } else {
                            throw new IllegalArgumentException("Invalid header with headername: " + this.mRequestHeaders[McDUim_I - 1]);
                        }
                    } else {
                        throw new IllegalArgumentException("Invalid http method " + this.mInitialMethod);
                    }
                } catch (RuntimeException e) {
                    destroyNativeStreamLocked(false);
                    throw e;
                }
            } else {
                throw new IllegalStateException("Stream is already started.");
            }
        }
    }

    @Override // org.chromium.net.BidirectionalStream
    public final void write(ByteBuffer byteBuffer, boolean z) {
        synchronized (this.mNativeStreamLock) {
            NNAPIInfo.checkDirect(byteBuffer);
            if (!byteBuffer.hasRemaining() && !z) {
                throw new IllegalArgumentException("Empty buffer before end of stream.");
            }
            if (!this.mEndOfStreamWritten) {
                if (isDoneLocked()) {
                    return;
                }
                this.mPendingData.add(byteBuffer);
                if (z) {
                    this.mEndOfStreamWritten = true;
                }
                return;
            }
            throw new IllegalArgumentException("Write after writing end of stream.");
        }
    }
}
