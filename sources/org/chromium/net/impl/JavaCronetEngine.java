package org.chromium.net.impl;

import android.content.Context;
import android.util.Log;
import io.grpc.internal.RetriableStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.ExperimentalBidirectionalStream;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.NetworkQualityRttListener;
import org.chromium.net.NetworkQualityThroughputListener;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;
import org.chromium.net.impl.CronetLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JavaCronetEngine extends CronetEngineBase {
    private static final String TAG = "JavaCronetEngine";
    public final Context mContext;
    public final int mCronetEngineId;
    private final ExecutorService mExecutorService;
    public final CronetLogger mLogger;
    private final String mUserAgent;
    public final AtomicInteger mActiveRequestCount = new AtomicInteger();
    private long mNetworkHandle = -1;

    /* compiled from: PG */
    /* renamed from: org.chromium.net.impl.JavaCronetEngine$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements ThreadFactory {
        public final /* synthetic */ int val$threadPriority;

        public AnonymousClass1(int i) {
            this.val$threadPriority = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return Executors.defaultThreadFactory().newThread(new RetriableStream.Sublistener.AnonymousClass4(this, runnable, 9));
        }
    }

    public JavaCronetEngine(CronetEngineBuilderImpl cronetEngineBuilderImpl) {
        Context context = cronetEngineBuilderImpl.mApplicationContext;
        this.mContext = context;
        int hashCode = hashCode();
        this.mCronetEngineId = hashCode;
        int threadPriority = cronetEngineBuilderImpl.threadPriority(9);
        this.mUserAgent = cronetEngineBuilderImpl.mUserAgent;
        this.mExecutorService = new ThreadPoolExecutor(10, 10, 50L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new AnonymousClass1(threadPriority));
        CronetLogger createLogger = CronetLoggerFactory.createLogger(context, CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK);
        this.mLogger = createLogger;
        try {
            createLogger.logCronetEngineCreation(hashCode, cronetEngineBuilderImpl.toLoggerInfo(), new CronetLogger.CronetVersion("CronetHttpURLConnection/".concat(String.valueOf(ImplVersion.getCronetVersionWithLastChange())).split("/")[1].split("@")[0]), CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK);
        } catch (RuntimeException e) {
            Log.e(TAG, "Error while trying to log JavaCronetEngine creation: ", e);
        }
        Log.w(TAG, "using the fallback Cronet Engine implementation. Performance will suffer and many HTTP client features, including caching, will not work.");
    }

    @Override // org.chromium.net.CronetEngine
    public final void bindToNetwork(long j) {
        this.mNetworkHandle = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.chromium.net.impl.CronetEngineBase
    public final ExperimentalBidirectionalStream createBidirectionalStream(String str, BidirectionalStream.Callback callback, Executor executor, String str2, List list, int i, boolean z, Collection collection, boolean z2, int i2, boolean z3, int i3, long j) {
        throw null;
    }

    @Override // org.chromium.net.impl.CronetEngineBase
    public final ExperimentalUrlRequest createRequest(String str, UrlRequest.Callback callback, Executor executor, int i, Collection collection, boolean z, boolean z2, boolean z3, boolean z4, int i2, boolean z5, int i3, RequestFinishedInfo.Listener listener, int i4, long j, String str2, ArrayList arrayList, UploadDataProvider uploadDataProvider, Executor executor2) {
        if (j != -1) {
            this.mNetworkHandle = j;
        }
        return new JavaUrlRequest(this, callback, this.mExecutorService, executor, str, this.mUserAgent, z3, z4, i2, z5, i3, this.mNetworkHandle, str2, arrayList, uploadDataProvider, executor2);
    }

    @Override // org.chromium.net.CronetEngine
    public final URLStreamHandlerFactory createURLStreamHandlerFactory() {
        return new URLStreamHandlerFactory() { // from class: org.chromium.net.impl.JavaCronetEngine.2
            @Override // java.net.URLStreamHandlerFactory
            public final URLStreamHandler createURLStreamHandler(String str) {
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void decrementActiveRequestCount() {
        this.mActiveRequestCount.decrementAndGet();
    }

    @Override // org.chromium.net.CronetEngine
    public final int getActiveRequestCount() {
        return this.mActiveRequestCount.get();
    }

    @Override // org.chromium.net.CronetEngine
    public final int getDownstreamThroughputKbps() {
        return -1;
    }

    @Override // org.chromium.net.CronetEngine
    public final int getEffectiveConnectionType() {
        return 0;
    }

    @Override // org.chromium.net.CronetEngine
    public final byte[] getGlobalMetricsDeltas() {
        return new byte[0];
    }

    @Override // org.chromium.net.CronetEngine
    public final int getHttpRttMs() {
        return -1;
    }

    @Override // org.chromium.net.CronetEngine
    public final int getTransportRttMs() {
        return -1;
    }

    @Override // org.chromium.net.CronetEngine
    public final String getVersionString() {
        return "CronetHttpURLConnection/".concat(String.valueOf(ImplVersion.getCronetVersionWithLastChange()));
    }

    @Override // org.chromium.net.impl.CronetEngineBase, org.chromium.net.ExperimentalCronetEngine, org.chromium.net.CronetEngine
    public final /* synthetic */ UrlRequest.Builder newUrlRequestBuilder(String str, UrlRequest.Callback callback, Executor executor) {
        return new UrlRequestBuilderImpl(str, callback, executor, this);
    }

    @Override // org.chromium.net.CronetEngine
    public final URLConnection openConnection(URL url) {
        return url.openConnection();
    }

    @Override // org.chromium.net.CronetEngine
    public final void shutdown() {
        this.mExecutorService.shutdown();
    }

    @Override // org.chromium.net.impl.CronetEngineBase, org.chromium.net.ExperimentalCronetEngine, org.chromium.net.CronetEngine
    public final ExperimentalBidirectionalStream.Builder newBidirectionalStreamBuilder(String str, BidirectionalStream.Callback callback, Executor executor) {
        throw new UnsupportedOperationException("The bidirectional stream API is not supported by the Java implementation of Cronet Engine");
    }

    @Override // org.chromium.net.ExperimentalCronetEngine
    public final URLConnection openConnection(URL url, Proxy proxy) {
        return url.openConnection(proxy);
    }

    @Override // org.chromium.net.CronetEngine
    public final void stopNetLog() {
    }

    @Override // org.chromium.net.CronetEngine
    public final void addRequestFinishedListener(RequestFinishedInfo.Listener listener) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void addRttListener(NetworkQualityRttListener networkQualityRttListener) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void addThroughputListener(NetworkQualityThroughputListener networkQualityThroughputListener) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void removeRequestFinishedListener(RequestFinishedInfo.Listener listener) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void removeRttListener(NetworkQualityRttListener networkQualityRttListener) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void removeThroughputListener(NetworkQualityThroughputListener networkQualityThroughputListener) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void startNetLogToFile(String str, boolean z) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void configureNetworkQualityEstimatorForTesting(boolean z, boolean z2, boolean z3) {
    }

    @Override // org.chromium.net.CronetEngine
    public final void startNetLogToDisk(String str, boolean z, int i) {
    }
}
