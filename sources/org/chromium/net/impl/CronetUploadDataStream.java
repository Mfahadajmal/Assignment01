package org.chromium.net.impl;

import J.N;
import _COROUTINE._BOUNDARY;
import android.util.Log;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetUploadDataStream extends UploadDataSink {
    public static final String TAG = "CronetUploadDataStream";
    public ByteBuffer mByteBuffer;
    private long mByteBufferLimit;
    public final VersionSafeCallbacks$UploadDataProviderWrapper mDataProvider;
    private boolean mDestroyAdapterPostponed;
    private final Executor mExecutor;
    public long mLength;
    public long mRemainingLength;
    public final CronetUrlRequest mRequest;
    public long mUploadDataStreamAdapter;
    public final AtomicInteger mReadCount = new AtomicInteger();
    private final Runnable mReadTask = new Runnable() { // from class: org.chromium.net.impl.CronetUploadDataStream.1
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (CronetUploadDataStream.this.mLock) {
                CronetUploadDataStream cronetUploadDataStream = CronetUploadDataStream.this;
                if (cronetUploadDataStream.mUploadDataStreamAdapter != 0) {
                    cronetUploadDataStream.checkState(3);
                    CronetUploadDataStream cronetUploadDataStream2 = CronetUploadDataStream.this;
                    if (cronetUploadDataStream2.mByteBuffer != null) {
                        cronetUploadDataStream2.mInWhichUserCallback = 0;
                        try {
                            CronetUploadDataStream.this.checkCallingThread();
                            CronetUploadDataStream cronetUploadDataStream3 = CronetUploadDataStream.this;
                            cronetUploadDataStream3.mDataProvider.read(cronetUploadDataStream3, cronetUploadDataStream3.mByteBuffer);
                            CronetUploadDataStream.this.mReadCount.incrementAndGet();
                            return;
                        } catch (Exception e) {
                            CronetUploadDataStream.this.onError(e);
                            return;
                        }
                    }
                    throw new IllegalStateException("Unexpected readData call. Buffer is null");
                }
            }
        }
    };
    public final Object mLock = new Object();
    public int mInWhichUserCallback = 3;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: org.chromium.net.impl.CronetUploadDataStream$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (CronetUploadDataStream.this.mLock) {
                CronetUploadDataStream cronetUploadDataStream = CronetUploadDataStream.this;
                if (cronetUploadDataStream.mUploadDataStreamAdapter != 0) {
                    cronetUploadDataStream.checkState(3);
                    CronetUploadDataStream cronetUploadDataStream2 = CronetUploadDataStream.this;
                    if (cronetUploadDataStream2.mByteBuffer != null) {
                        cronetUploadDataStream2.mInWhichUserCallback = 0;
                        try {
                            CronetUploadDataStream.this.checkCallingThread();
                            CronetUploadDataStream cronetUploadDataStream3 = CronetUploadDataStream.this;
                            cronetUploadDataStream3.mDataProvider.read(cronetUploadDataStream3, cronetUploadDataStream3.mByteBuffer);
                            CronetUploadDataStream.this.mReadCount.incrementAndGet();
                            return;
                        } catch (Exception e) {
                            CronetUploadDataStream.this.onError(e);
                            return;
                        }
                    }
                    throw new IllegalStateException("Unexpected readData call. Buffer is null");
                }
            }
        }
    }

    /* renamed from: -$$Nest$mcheckState$ar$ds */
    public static /* bridge */ /* synthetic */ void m406$$Nest$mcheckState$ar$ds(CronetUploadDataStream cronetUploadDataStream) {
        cronetUploadDataStream.checkState(3);
    }

    public CronetUploadDataStream(UploadDataProvider uploadDataProvider, Executor executor, CronetUrlRequest cronetUrlRequest) {
        this.mExecutor = executor;
        this.mDataProvider = new VersionSafeCallbacks$UploadDataProviderWrapper(uploadDataProvider);
        this.mRequest = cronetUrlRequest;
    }

    public final void checkState(int i) {
        int i2 = this.mInWhichUserCallback;
        if (i2 == i) {
        } else {
            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i2, i, "Expected ", ", but was "));
        }
    }

    private final void destroyAdapter() {
        synchronized (this.mLock) {
            if (this.mInWhichUserCallback == 0) {
                this.mDestroyAdapterPostponed = true;
                return;
            }
            long j = this.mUploadDataStreamAdapter;
            if (j == 0) {
                return;
            }
            N.MMW1G0N1(j);
            this.mUploadDataStreamAdapter = 0L;
            postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 15));
        }
    }

    private final void destroyAdapterIfPostponed() {
        synchronized (this.mLock) {
            if (this.mInWhichUserCallback != 0) {
                if (this.mDestroyAdapterPostponed) {
                    destroyAdapter();
                }
            } else {
                throw new IllegalStateException("Method should not be called when read has not completed.");
            }
        }
    }

    public final void checkCallingThread() {
        this.mRequest.checkCallingThread();
    }

    public final void onError(Throwable th) {
        int i;
        synchronized (this.mLock) {
            i = this.mInWhichUserCallback;
            if (i != 3) {
                this.mInWhichUserCallback = 3;
                this.mByteBuffer = null;
                destroyAdapterIfPostponed();
            } else {
                throw new IllegalStateException("There is no read or rewind or length check in progress.", th);
            }
        }
        if (i == 2) {
            try {
                this.mDataProvider.close();
            } catch (Exception e) {
                Log.e("cr_".concat(String.valueOf(TAG)), "Failure closing data provider", e);
            }
        }
        this.mRequest.onUploadException(th);
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onReadError(Exception exc) {
        synchronized (this.mLock) {
            checkState(0);
            onError(exc);
        }
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onReadSucceeded(boolean z) {
        synchronized (this.mLock) {
            checkState(0);
            if (this.mByteBufferLimit == this.mByteBuffer.limit()) {
                if (z && this.mLength >= 0) {
                    throw new IllegalArgumentException("Non-chunked upload can't have last chunk");
                }
                int position = this.mByteBuffer.position();
                if (position == 0) {
                    if (!z) {
                        onError(new IllegalStateException("Bytes read can't be zero except for last chunk!"));
                        return;
                    }
                    position = 0;
                }
                long j = this.mRemainingLength - position;
                this.mRemainingLength = j;
                if (j < 0) {
                    long j2 = this.mLength;
                    if (j2 >= 0) {
                        throw new IllegalArgumentException(String.format("Read upload data length %d exceeds expected length %d", Long.valueOf(j2 - j), Long.valueOf(this.mLength)));
                    }
                }
                this.mByteBuffer = null;
                this.mInWhichUserCallback = 3;
                destroyAdapterIfPostponed();
                long j3 = this.mUploadDataStreamAdapter;
                if (j3 == 0) {
                    return;
                }
                N.MpWH3VIr(j3, this, position, z);
                return;
            }
            throw new IllegalStateException("ByteBuffer limit changed");
        }
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onRewindError(Exception exc) {
        synchronized (this.mLock) {
            checkState(1);
            onError(exc);
        }
    }

    @Override // org.chromium.net.UploadDataSink
    public final void onRewindSucceeded() {
        synchronized (this.mLock) {
            checkState(1);
            this.mInWhichUserCallback = 3;
            this.mRemainingLength = this.mLength;
            long j = this.mUploadDataStreamAdapter;
            if (j == 0) {
                return;
            }
            N.MFpRjSMv(j, this);
        }
    }

    void onUploadDataStreamDestroyed() {
        destroyAdapter();
    }

    public final void postTaskToExecutor(Runnable runnable) {
        try {
            this.mExecutor.execute(runnable);
        } catch (Throwable th) {
            this.mRequest.onUploadException(th);
        }
    }

    void readData(ByteBuffer byteBuffer) {
        this.mByteBuffer = byteBuffer;
        this.mByteBufferLimit = byteBuffer.limit();
        postTaskToExecutor(this.mReadTask);
    }

    void rewind() {
        postTaskToExecutor(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 14));
    }
}
