package io.grpc.internal;

import J.N;
import android.os.Process;
import android.util.Log;
import androidx.preference.Preference;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.mlkit.logging.schema.acceleration.NNAPIInfo;
import io.grpc.Metadata;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.RetriableStream;
import io.grpc.okhttp.AsyncSink;
import io.grpc.okhttp.OkHttpClientTransport;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.time.Duration;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import okio.Buffer;
import okio.Sink;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.TaskRunnerImpl;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.UploadDataSink;
import org.chromium.net.UrlRequest;
import org.chromium.net.impl.CronetBidirectionalStream;
import org.chromium.net.impl.CronetLogger;
import org.chromium.net.impl.CronetMetrics;
import org.chromium.net.impl.CronetUploadDataStream;
import org.chromium.net.impl.CronetUrlRequest;
import org.chromium.net.impl.UrlResponseInfoImpl;
import org.chromium.net.impl.VersionSafeCallbacks$UrlRequestStatusListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RetriableStream$Sublistener$1RetryBackoffRunnable$1 implements Runnable {
    final /* synthetic */ Object RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
    private final /* synthetic */ int switching_field;

    public RetriableStream$Sublistener$1RetryBackoffRunnable$1(Object obj, int i) {
        this.switching_field = i;
        this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        Map<String, List<String>> emptyMap;
        boolean z;
        int i;
        long j;
        long j2;
        long max;
        long estimateHeadersSizeInBytes;
        Duration ofSeconds;
        Duration ofSeconds2;
        switch (this.switching_field) {
            case 0:
                FutureCallbackViewModel$$ExternalSyntheticLambda1 futureCallbackViewModel$$ExternalSyntheticLambda1 = (FutureCallbackViewModel$$ExternalSyntheticLambda1) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                RetriableStream.this.drain((RetriableStream.Substream) futureCallbackViewModel$$ExternalSyntheticLambda1.f$2);
                return;
            case 1:
                RetriableStream retriableStream = (RetriableStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                if (!retriableStream.isClosed) {
                    retriableStream.masterListener.onReady();
                    return;
                }
                return;
            case 2:
                RetriableStream.m244$$Nest$fputisClosed$ar$ds(RetriableStream.this);
                RetriableStream retriableStream2 = RetriableStream.this;
                ClientStreamListener clientStreamListener = retriableStream2.masterListener;
                OptionalMethod optionalMethod = retriableStream2.savedCloseMasterListenerReason$ar$class_merging;
                clientStreamListener.closed((Status) optionalMethod.OptionalMethod$ar$methodName, (ClientStreamListener.RpcProgress) optionalMethod.OptionalMethod$ar$methodParams, (Metadata) optionalMethod.OptionalMethod$ar$returnType);
                return;
            case 3:
                RetriableStream retriableStream3 = RetriableStream.this;
                if (!retriableStream3.isClosed) {
                    retriableStream3.masterListener.onReady();
                    return;
                }
                return;
            case 4:
                ((NameResolver) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).refresh();
                return;
            case 5:
                RetryingNameResolver retryingNameResolver = RetryingNameResolver.this;
                retryingNameResolver.retryScheduler.schedule(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(retryingNameResolver, 4));
                return;
            case 6:
                try {
                    Object obj = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                    Sink sink = ((AsyncSink) obj).sink;
                    if (sink != null) {
                        Buffer buffer = ((AsyncSink) obj).buffer;
                        long j3 = buffer.size;
                        if (j3 > 0) {
                            sink.write(buffer, j3);
                        }
                    }
                } catch (IOException e) {
                    ((AsyncSink) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).transportExceptionHandler.onException(e);
                }
                try {
                    Sink sink2 = ((AsyncSink) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).sink;
                    if (sink2 != null) {
                        sink2.close();
                    }
                } catch (IOException e2) {
                    ((AsyncSink) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).transportExceptionHandler.onException(e2);
                }
                try {
                    Socket socket = ((AsyncSink) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).socket;
                    if (socket != null) {
                        socket.close();
                        return;
                    }
                    return;
                } catch (IOException e3) {
                    ((AsyncSink) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).transportExceptionHandler.onException(e3);
                    return;
                }
            case 7:
                AtomicBackoff.State state = (AtomicBackoff.State) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                long j4 = state.savedValue;
                AtomicBackoff atomicBackoff = AtomicBackoff.this;
                long j5 = state.savedValue;
                long max2 = Math.max(j5 + j5, j5);
                if (atomicBackoff.value.compareAndSet(j4, max2)) {
                    AtomicBackoff.log.logp(Level.WARNING, "io.grpc.internal.AtomicBackoff$State", "backoff", "Increased {0} to {1}", new Object[]{AtomicBackoff.this.name, Long.valueOf(max2)});
                    return;
                }
                return;
            case 8:
                OkHttpClientTransport okHttpClientTransport = (OkHttpClientTransport) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                okHttpClientTransport.executor.execute(okHttpClientTransport.clientFrameHandler);
                synchronized (((OkHttpClientTransport) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).lock) {
                    Object obj2 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                    ((OkHttpClientTransport) obj2).maxConcurrentStreams = Preference.DEFAULT_ORDER;
                    ((OkHttpClientTransport) obj2).startPendingStreams();
                }
                return;
            case 9:
                Object obj3 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                TraceEvent scoped = TraceEvent.scoped(((TaskRunnerImpl) obj3).mTraceEvent);
                try {
                    synchronized (((TaskRunnerImpl) obj3).mPreNativeTaskLock) {
                        LinkedList linkedList = ((TaskRunnerImpl) obj3).mPreNativeTasks;
                        if (linkedList != null) {
                            Runnable runnable = (Runnable) linkedList.poll();
                            int i2 = ((TaskRunnerImpl) obj3).mTaskTraits;
                            if (i2 != 0 && i2 != 1) {
                                if (i2 != 2 && i2 != 3) {
                                    if (i2 == 4 || i2 == 5) {
                                        Process.setThreadPriority(-1);
                                    }
                                } else {
                                    Process.setThreadPriority(0);
                                }
                            } else {
                                Process.setThreadPriority(10);
                            }
                            runnable.run();
                        }
                    }
                    if (scoped != null) {
                        scoped.close();
                        return;
                    }
                    return;
                } finally {
                }
            case 10:
                if (NetworkChangeNotifierAutoDetect.m341$$Nest$fgetmIgnoreNextBroadcast((NetworkChangeNotifierAutoDetect) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2)) {
                    NetworkChangeNotifierAutoDetect.m344$$Nest$fputmIgnoreNextBroadcast((NetworkChangeNotifierAutoDetect) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2, false);
                    return;
                } else {
                    NetworkChangeNotifierAutoDetect.m345$$Nest$mconnectionTypeChanged((NetworkChangeNotifierAutoDetect) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2);
                    return;
                }
            case 11:
                CronetBidirectionalStream cronetBidirectionalStream = (CronetBidirectionalStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                int finishedReason = cronetBidirectionalStream.getFinishedReason();
                boolean z2 = cronetBidirectionalStream.mQuicConnectionMigrationAttempted;
                boolean z3 = cronetBidirectionalStream.mQuicConnectionMigrationSuccessful;
                UrlResponseInfoImpl urlResponseInfoImpl = cronetBidirectionalStream.mResponseInfo;
                if (urlResponseInfoImpl != null) {
                    emptyMap = urlResponseInfoImpl.getAllHeaders();
                    UrlResponseInfoImpl urlResponseInfoImpl2 = cronetBidirectionalStream.mResponseInfo;
                    String str2 = urlResponseInfoImpl2.mNegotiatedProtocol;
                    int i3 = urlResponseInfoImpl2.mHttpStatusCode;
                    z = urlResponseInfoImpl2.mWasCached;
                    str = str2;
                    i = i3;
                } else {
                    str = "";
                    emptyMap = Collections.emptyMap();
                    z = false;
                    i = 0;
                }
                long longValue = ((CronetMetrics) cronetBidirectionalStream.mMetrics).mSentByteCount.longValue();
                if (z && longValue == 0) {
                    j2 = 0;
                    max = 0;
                    j = 0;
                } else {
                    long j6 = 0;
                    for (String str3 : cronetBidirectionalStream.mRequestHeaders) {
                        if (str3 != null) {
                            j6 += r15.length();
                        }
                    }
                    j = 0;
                    j2 = j6;
                    max = Math.max(0L, longValue - j6);
                }
                long longValue2 = ((CronetMetrics) cronetBidirectionalStream.mMetrics).mReceivedByteCount.longValue();
                if (z && longValue2 == j) {
                    estimateHeadersSizeInBytes = j;
                } else {
                    estimateHeadersSizeInBytes = NNAPIInfo.DeviceInfo.estimateHeadersSizeInBytes(emptyMap);
                    j = Math.max(j, longValue2 - estimateHeadersSizeInBytes);
                }
                if (cronetBidirectionalStream.mMetrics.getRequestStart() != null && cronetBidirectionalStream.mMetrics.getResponseStart() != null) {
                    ofSeconds = Duration.ofMillis(cronetBidirectionalStream.mMetrics.getResponseStart().getTime() - cronetBidirectionalStream.mMetrics.getRequestStart().getTime());
                } else {
                    ofSeconds = Duration.ofSeconds(0L);
                }
                if (cronetBidirectionalStream.mMetrics.getRequestStart() != null && cronetBidirectionalStream.mMetrics.getRequestEnd() != null) {
                    ofSeconds2 = Duration.ofMillis(cronetBidirectionalStream.mMetrics.getRequestEnd().getTime() - cronetBidirectionalStream.mMetrics.getRequestStart().getTime());
                } else {
                    ofSeconds2 = Duration.ofSeconds(0L);
                }
                cronetBidirectionalStream.mLogger.logCronetTrafficInfo(cronetBidirectionalStream.mRequestContext.mLogId, new CronetLogger.CronetTrafficInfo(j2, max, estimateHeadersSizeInBytes, j, i, ofSeconds, ofSeconds2, str, z2, z3, NNAPIInfo.DeviceInfo.finishedReasonToCronetTrafficInfoRequestTerminalState$ar$edu(finishedReason), cronetBidirectionalStream.mNonfinalUserCallbackExceptionCount, cronetBidirectionalStream.mReadCount, cronetBidirectionalStream.mFlushCount, true, cronetBidirectionalStream.mFinalUserCallbackThrew));
                cronetBidirectionalStream.mRequestContext.onRequestFinished();
                return;
            case 12:
                synchronized (((CronetBidirectionalStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mNativeStreamLock) {
                    if (((CronetBidirectionalStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).isDoneLocked()) {
                        return;
                    }
                    ((CronetBidirectionalStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mReadState = 2;
                    try {
                        Object obj4 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                        ((CronetBidirectionalStream) obj4).mCallback.onResponseHeadersReceived((BidirectionalStream) obj4, ((CronetBidirectionalStream) obj4).mResponseInfo);
                        return;
                    } catch (Exception e4) {
                        ((CronetBidirectionalStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).onNonfinalCallbackException(e4);
                        return;
                    }
                }
            case 13:
                try {
                    Object obj5 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                    ((CronetBidirectionalStream) obj5).mCallback.onCanceled((BidirectionalStream) obj5, ((CronetBidirectionalStream) obj5).mResponseInfo);
                } catch (Exception e5) {
                    ((CronetBidirectionalStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).onFinalCallbackException("onCanceled", e5);
                }
                ((CronetBidirectionalStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mInflightDoneCallbackCount.decrement();
                return;
            case 14:
                synchronized (((CronetUploadDataStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mLock) {
                    Object obj6 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                    if (((CronetUploadDataStream) obj6).mUploadDataStreamAdapter == 0) {
                        return;
                    }
                    CronetUploadDataStream.m406$$Nest$mcheckState$ar$ds((CronetUploadDataStream) obj6);
                    ((CronetUploadDataStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mInWhichUserCallback = 1;
                    try {
                        ((CronetUploadDataStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).checkCallingThread();
                        Object obj7 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                        ((CronetUploadDataStream) obj7).mDataProvider.rewind((UploadDataSink) obj7);
                        return;
                    } catch (Exception e6) {
                        ((CronetUploadDataStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).onError(e6);
                        return;
                    }
                }
            case 15:
                try {
                    ((CronetUploadDataStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).checkCallingThread();
                    ((CronetUploadDataStream) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mDataProvider.close();
                    return;
                } catch (Exception e7) {
                    Log.e("cr_".concat(String.valueOf(CronetUploadDataStream.TAG)), "Exception thrown when closing", e7);
                    return;
                }
            case 16:
                ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mRequestContext.onRequestFinished();
                return;
            case 17:
                CronetUploadDataStream cronetUploadDataStream = ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mUploadDataStream;
                synchronized (cronetUploadDataStream.mLock) {
                    cronetUploadDataStream.mInWhichUserCallback = 2;
                }
                try {
                    cronetUploadDataStream.mRequest.checkCallingThread();
                    long length = cronetUploadDataStream.mDataProvider.getLength();
                    cronetUploadDataStream.mLength = length;
                    cronetUploadDataStream.mRemainingLength = length;
                } catch (Throwable th) {
                    cronetUploadDataStream.onError(th);
                }
                synchronized (cronetUploadDataStream.mLock) {
                    cronetUploadDataStream.mInWhichUserCallback = 3;
                }
                synchronized (((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mUrlRequestAdapterLock) {
                    if (((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).isDoneLocked()) {
                        return;
                    }
                    Object obj8 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                    CronetUploadDataStream cronetUploadDataStream2 = ((CronetUrlRequest) obj8).mUploadDataStream;
                    long j7 = ((CronetUrlRequest) obj8).mUrlRequestAdapter;
                    synchronized (cronetUploadDataStream2.mLock) {
                        cronetUploadDataStream2.mUploadDataStreamAdapter = N.MA4X1aZa(cronetUploadDataStream2, j7, cronetUploadDataStream2.mLength);
                    }
                    ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).startInternalLocked();
                    return;
                }
            case 18:
                ((VersionSafeCallbacks$UrlRequestStatusListener) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).onStatus(-1);
                return;
            case 19:
                ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).checkCallingThread();
                synchronized (((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mUrlRequestAdapterLock) {
                    if (((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).isDoneLocked()) {
                        return;
                    }
                    CronetUrlRequest.m407$$Nest$fputmWaitingOnRead$ar$ds((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2);
                    try {
                        Object obj9 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                        ((CronetUrlRequest) obj9).mCallback.onResponseStarted((UrlRequest) obj9, ((CronetUrlRequest) obj9).mResponseInfo);
                        return;
                    } catch (Exception e8) {
                        ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).onNonfinalCallbackException(e8);
                        return;
                    }
                }
            default:
                synchronized (((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).mUrlRequestAdapterLock) {
                    if (((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).isDoneLocked()) {
                        return;
                    }
                    ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).destroyRequestAdapterLocked(0);
                    try {
                        Object obj10 = this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2;
                        ((CronetUrlRequest) obj10).mCallback.onSucceeded((UrlRequest) obj10, ((CronetUrlRequest) obj10).mResponseInfo);
                    } catch (Exception e9) {
                        ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).onFinalCallbackException("onSucceeded", e9);
                    }
                    ((CronetUrlRequest) this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2).maybeReportMetrics();
                    return;
                }
        }
    }

    public /* synthetic */ RetriableStream$Sublistener$1RetryBackoffRunnable$1(Object obj, int i, byte[] bArr) {
        this.switching_field = i;
        this.RetriableStream$Sublistener$1RetryBackoffRunnable$1$ar$this$2 = obj;
    }
}
