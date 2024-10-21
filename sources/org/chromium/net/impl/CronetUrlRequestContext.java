package org.chromium.net.impl;

import J.N;
import _COROUTINE._BOUNDARY;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import io.grpc.internal.RetriableStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ObserverList;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.ExperimentalBidirectionalStream;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.NetworkQualityRttListener;
import org.chromium.net.NetworkQualityThroughputListener;
import org.chromium.net.RequestContextConfigOptions;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;
import org.chromium.net.impl.CronetEngineBuilderImpl;
import org.chromium.net.impl.CronetLogger;
import org.chromium.net.urlconnection.CronetHttpURLConnection;
import org.chromium.net.urlconnection.CronetURLStreamHandlerFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CronetUrlRequestContext extends CronetEngineBase {
    public static final String LOG_TAG = "CronetUrlRequestContext";
    private static final HashSet sInUseStoragePaths = new HashSet();
    private final AtomicInteger mActiveRequestCount;
    private int mDownstreamThroughputKbps;
    private int mEffectiveConnectionType;
    private final Object mFinishedListenerLock;
    private final Map mFinishedListenerMap;
    private int mHttpRttMs;
    private final String mInUseStoragePath;
    private final ConditionVariable mInitCompleted;
    private boolean mIsLogging;
    private boolean mIsStoppingNetLog;
    public final Object mLock;
    public final long mLogId;
    public final CronetLogger mLogger;
    private long mNetworkHandle;
    private final boolean mNetworkQualityEstimatorEnabled;
    private final Object mNetworkQualityLock;
    public Thread mNetworkThread;
    private final ObserverList mRttListenerList;
    private final AtomicInteger mRunningRequestCount;
    private final ConditionVariable mStopNetLogCompleted;
    private final ObserverList mThroughputListenerList;
    private int mTransportRttMs;
    public long mUrlRequestContextAdapter;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetInitializedInfoLogger {
        public final CronetLogger.CronetInitializedInfo mCronetInitializedInfo;
        private final CronetLogger mCronetLogger;
        private final long mStartUptimeMillis;

        public CronetInitializedInfoLogger(CronetLogger cronetLogger, long j, long j2) {
            CronetLogger.CronetInitializedInfo cronetInitializedInfo = new CronetLogger.CronetInitializedInfo();
            this.mCronetInitializedInfo = cronetInitializedInfo;
            this.mCronetLogger = cronetLogger;
            cronetInitializedInfo.cronetInitializationRef = j;
            this.mStartUptimeMillis = j2;
        }

        public final int getElapsedTime() {
            return (int) (SystemClock.uptimeMillis() - this.mStartUptimeMillis);
        }

        public final void maybeLog() {
            CronetLogger.CronetInitializedInfo cronetInitializedInfo = this.mCronetInitializedInfo;
            if (cronetInitializedInfo.engineCreationLatencyMillis >= 0 && cronetInitializedInfo.engineAsyncLatencyMillis >= 0) {
                this.mCronetLogger.logCronetInitializedInfo(cronetInitializedInfo);
            }
        }
    }

    public CronetUrlRequestContext(CronetEngineBuilderImpl cronetEngineBuilderImpl, long j) {
        int threadPriority;
        Object obj = new Object();
        this.mLock = obj;
        this.mInitCompleted = new ConditionVariable(false);
        this.mRunningRequestCount = new AtomicInteger(0);
        this.mActiveRequestCount = new AtomicInteger(0);
        this.mNetworkQualityLock = new Object();
        this.mFinishedListenerLock = new Object();
        this.mEffectiveConnectionType = 0;
        this.mHttpRttMs = -1;
        this.mTransportRttMs = -1;
        this.mDownstreamThroughputKbps = -1;
        ObserverList observerList = new ObserverList();
        this.mRttListenerList = observerList;
        ObserverList observerList2 = new ObserverList();
        this.mThroughputListenerList = observerList2;
        this.mFinishedListenerMap = new HashMap();
        this.mStopNetLogCompleted = new ConditionVariable();
        this.mNetworkHandle = -1L;
        observerList.disableThreadAsserts();
        observerList2.disableThreadAsserts();
        this.mNetworkQualityEstimatorEnabled = cronetEngineBuilderImpl.mNetworkQualityEstimatorEnabled;
        boolean ensureInitialized = CronetLibraryLoader.ensureInitialized(cronetEngineBuilderImpl.mApplicationContext, cronetEngineBuilderImpl, false);
        if (cronetEngineBuilderImpl.httpCacheMode() == 1) {
            String str = cronetEngineBuilderImpl.mStoragePath;
            this.mInUseStoragePath = str;
            HashSet hashSet = sInUseStoragePaths;
            synchronized (hashSet) {
                if (!hashSet.add(str)) {
                    throw new IllegalStateException("Disk cache storage path already in use");
                }
            }
        } else {
            this.mInUseStoragePath = null;
        }
        synchronized (obj) {
            ExecutorSelector executorSelector = (ExecutorSelector) CronetLibraryLoader.getHttpFlags$ar$class_merging().flags().get("Cronet_override_network_thread_priority");
            RequestContextConfigOptions.Builder newBuilder = RequestContextConfigOptions.newBuilder();
            newBuilder.setQuicEnabled(cronetEngineBuilderImpl.mQuicEnabled);
            newBuilder.setHttp2Enabled(cronetEngineBuilderImpl.mHttp2Enabled);
            newBuilder.setBrotliEnabled(cronetEngineBuilderImpl.mBrotiEnabled);
            newBuilder.setDisableCache(!cronetEngineBuilderImpl.mHttpCacheMode.mContentCacheEnabled);
            newBuilder.setHttpCacheMode(cronetEngineBuilderImpl.httpCacheMode());
            newBuilder.setHttpCacheMaxSize(cronetEngineBuilderImpl.mHttpCacheMaxSize);
            newBuilder.setMockCertVerifier(0L);
            newBuilder.setEnableNetworkQualityEstimator(cronetEngineBuilderImpl.mNetworkQualityEstimatorEnabled);
            newBuilder.setBypassPublicKeyPinningForLocalTrustAnchors(cronetEngineBuilderImpl.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled);
            if (executorSelector != null) {
                threadPriority = (int) executorSelector.getIntValue();
            } else {
                threadPriority = cronetEngineBuilderImpl.threadPriority(10);
            }
            newBuilder.setNetworkThreadPriority(threadPriority);
            String str2 = cronetEngineBuilderImpl.mUserAgent;
            if (str2 != null) {
                newBuilder.setUserAgent(str2);
            }
            String str3 = cronetEngineBuilderImpl.mStoragePath;
            if (str3 != null) {
                newBuilder.setStoragePath(str3);
            }
            cronetEngineBuilderImpl.getDefaultQuicUserAgentId();
            newBuilder.setQuicDefaultUserAgentId(cronetEngineBuilderImpl.getDefaultQuicUserAgentId());
            String str4 = cronetEngineBuilderImpl.mExperimentalOptions;
            if (str4 != null) {
                newBuilder.setExperimentalOptions(str4);
            }
            long MB3ntV7V = N.MB3ntV7V(newBuilder.build().toByteArray());
            if (MB3ntV7V != 0) {
                for (CronetEngineBuilderImpl.QuicHint quicHint : cronetEngineBuilderImpl.mQuicHints) {
                    N.MyRIv1Ij(MB3ntV7V, (String) quicHint.CronetEngineBuilderImpl$QuicHint$ar$mHost, quicHint.mPort, quicHint.mAlternatePort);
                }
                for (CronetEngineBuilderImpl.Pkp pkp : cronetEngineBuilderImpl.mPkps) {
                    String str5 = (String) pkp.CronetEngineBuilderImpl$Pkp$ar$mHost;
                    N.Muq3ic6p(MB3ntV7V, str5, (byte[][]) pkp.CronetEngineBuilderImpl$Pkp$ar$mHashes, pkp.mIncludeSubdomains, ((Date) pkp.CronetEngineBuilderImpl$Pkp$ar$mExpirationDate).getTime());
                    MB3ntV7V = MB3ntV7V;
                }
                long M135Cu0D = N.M135Cu0D(MB3ntV7V);
                this.mUrlRequestContextAdapter = M135Cu0D;
                if (M135Cu0D == 0) {
                    throw new NullPointerException("Context Adapter creation failed.");
                }
            } else {
                throw new IllegalArgumentException("Experimental options parsing failed.");
            }
        }
        CronetLogger createLogger = CronetLoggerFactory.createLogger(cronetEngineBuilderImpl.mApplicationContext, cronetEngineBuilderImpl.mSource);
        this.mLogger = createLogger;
        long generateId = createLogger.generateId();
        this.mLogId = generateId;
        CronetLogger.CronetEngineBuilderInfo loggerInfo = cronetEngineBuilderImpl.toLoggerInfo();
        try {
            createLogger.logCronetEngineCreation(generateId, loggerInfo, new CronetLogger.CronetVersion("Cronet/".concat(String.valueOf(ImplVersion.getCronetVersionWithLastChange())).split("/")[1].split("@")[0]), cronetEngineBuilderImpl.mSource);
        } catch (RuntimeException unused) {
        }
        CronetInitializedInfoLogger cronetInitializedInfoLogger = ensureInitialized ? new CronetInitializedInfoLogger(this.mLogger, loggerInfo.mCronetInitializationRef, j) : null;
        CronetLibraryLoader.postToInitThread(new RetriableStream.Sublistener.AnonymousClass4(this, cronetInitializedInfoLogger, 7));
        if (cronetInitializedInfoLogger != null) {
            int elapsedTime = cronetInitializedInfoLogger.getElapsedTime();
            synchronized (cronetInitializedInfoLogger.mCronetInitializedInfo) {
                cronetInitializedInfoLogger.mCronetInitializedInfo.engineCreationLatencyMillis = elapsedTime;
                cronetInitializedInfoLogger.maybeLog();
            }
        }
    }

    private final void checkHaveAdapter() {
        if (haveRequestContextAdapter()) {
        } else {
            throw new IllegalStateException("Engine is shut down.");
        }
    }

    private final boolean haveRequestContextAdapter() {
        if (this.mUrlRequestContextAdapter != 0) {
            return true;
        }
        return false;
    }

    private void initNetworkThread() {
        this.mNetworkThread = Thread.currentThread();
        this.mInitCompleted.open();
        Thread.currentThread().setName("ChromiumNet");
    }

    private void onEffectiveConnectionTypeChanged(int i) {
        synchronized (this.mNetworkQualityLock) {
            this.mEffectiveConnectionType = i;
        }
    }

    private void onRTTOrThroughputEstimatesComputed(int i, int i2, int i3) {
        synchronized (this.mNetworkQualityLock) {
            this.mHttpRttMs = i;
            this.mTransportRttMs = i2;
            this.mDownstreamThroughputKbps = i3;
        }
    }

    private void onRttObservation(int i, long j, int i2) {
        synchronized (this.mNetworkQualityLock) {
            Iterator it = this.mRttListenerList.iterator();
            while (it.hasNext()) {
                VersionSafeCallbacks$NetworkQualityRttListenerWrapper versionSafeCallbacks$NetworkQualityRttListenerWrapper = (VersionSafeCallbacks$NetworkQualityRttListenerWrapper) it.next();
                postObservationTaskToExecutor(versionSafeCallbacks$NetworkQualityRttListenerWrapper.getExecutor(), new TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4(versionSafeCallbacks$NetworkQualityRttListenerWrapper, i, j, i2, 2));
            }
        }
    }

    private void onThroughputObservation(int i, long j, int i2) {
        synchronized (this.mNetworkQualityLock) {
            Iterator it = this.mThroughputListenerList.iterator();
            while (it.hasNext()) {
                VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper versionSafeCallbacks$NetworkQualityThroughputListenerWrapper = (VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper) it.next();
                postObservationTaskToExecutor(versionSafeCallbacks$NetworkQualityThroughputListenerWrapper.getExecutor(), new TalkBackAnalyticsLoggerWithClearcut$$ExternalSyntheticLambda4(versionSafeCallbacks$NetworkQualityThroughputListenerWrapper, i, j, i2, 3));
            }
        }
    }

    private static void postObservationTaskToExecutor(Executor executor, Runnable runnable) {
        postObservationTaskToExecutor(executor, runnable, null);
    }

    @Override // org.chromium.net.CronetEngine
    public final void addRequestFinishedListener(RequestFinishedInfo.Listener listener) {
        synchronized (this.mFinishedListenerLock) {
            this.mFinishedListenerMap.put(listener, new VersionSafeCallbacks$RequestFinishedInfoListener(listener));
        }
    }

    @Override // org.chromium.net.CronetEngine
    public final void addRttListener(NetworkQualityRttListener networkQualityRttListener) {
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                if (this.mRttListenerList.isEmpty()) {
                    synchronized (this.mLock) {
                        checkHaveAdapter();
                        N.MpnFLFF2(this.mUrlRequestContextAdapter, this, true);
                    }
                }
                this.mRttListenerList.addObserver$ar$ds(new VersionSafeCallbacks$NetworkQualityRttListenerWrapper(networkQualityRttListener));
            }
            return;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    @Override // org.chromium.net.CronetEngine
    public final void addThroughputListener(NetworkQualityThroughputListener networkQualityThroughputListener) {
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                if (this.mThroughputListenerList.isEmpty()) {
                    synchronized (this.mLock) {
                        checkHaveAdapter();
                        N.MnPUhNKP(this.mUrlRequestContextAdapter, this, true);
                    }
                }
                this.mThroughputListenerList.addObserver$ar$ds(new VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper(networkQualityThroughputListener));
            }
            return;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    @Override // org.chromium.net.CronetEngine
    public final void bindToNetwork(long j) {
        this.mNetworkHandle = j;
    }

    @Override // org.chromium.net.CronetEngine
    public final void configureNetworkQualityEstimatorForTesting(boolean z, boolean z2, boolean z3) {
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mLock) {
                checkHaveAdapter();
                N.M6sIJDgy_ForTesting(this.mUrlRequestContextAdapter, this, z, z2, z3);
            }
            return;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.chromium.net.impl.CronetEngineBase
    public final ExperimentalBidirectionalStream createBidirectionalStream(String str, BidirectionalStream.Callback callback, Executor executor, String str2, List list, int i, boolean z, Collection collection, boolean z2, int i2, boolean z3, int i3, long j) {
        long j2;
        if (j == -1) {
            j2 = this.mNetworkHandle;
        } else {
            j2 = j;
        }
        synchronized (this.mLock) {
            try {
                try {
                    checkHaveAdapter();
                    return new CronetBidirectionalStream(this, str, i, callback, executor, str2, list, z, collection, z2, i2, z3, i3, j2);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    @Override // org.chromium.net.impl.CronetEngineBase
    public final ExperimentalUrlRequest createRequest(String str, UrlRequest.Callback callback, Executor executor, int i, Collection collection, boolean z, boolean z2, boolean z3, boolean z4, int i2, boolean z5, int i3, RequestFinishedInfo.Listener listener, int i4, long j, String str2, ArrayList arrayList, UploadDataProvider uploadDataProvider, Executor executor2) {
        long j2 = j == -1 ? this.mNetworkHandle : j;
        synchronized (this.mLock) {
            try {
                try {
                    checkHaveAdapter();
                    return new CronetUrlRequest(this, str, i, callback, executor, collection, z, z2, z3, z4, i2, z5, i3, listener, i4, j2, str2, arrayList, uploadDataProvider, executor2);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    @Override // org.chromium.net.CronetEngine
    public final URLStreamHandlerFactory createURLStreamHandlerFactory() {
        return new CronetURLStreamHandlerFactory(this);
    }

    @Override // org.chromium.net.CronetEngine
    public final int getActiveRequestCount() {
        return this.mActiveRequestCount.get();
    }

    @Override // org.chromium.net.CronetEngine
    public final int getDownstreamThroughputKbps() {
        int i;
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                i = this.mDownstreamThroughputKbps;
                if (i == -1) {
                    i = -1;
                }
            }
            return i;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    @Override // org.chromium.net.CronetEngine
    public final int getEffectiveConnectionType() {
        int i;
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                int i2 = this.mEffectiveConnectionType;
                if (i2 != 0) {
                    i = 1;
                    if (i2 != 1) {
                        i = 2;
                        if (i2 != 2) {
                            i = 3;
                            if (i2 != 3) {
                                i = 4;
                                if (i2 != 4) {
                                    i = 5;
                                    if (i2 != 5) {
                                        throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i2, "Internal Error: Illegal EffectiveConnectionType value "));
                                    }
                                }
                            }
                        }
                    }
                } else {
                    i = 0;
                }
            }
            return i;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    @Override // org.chromium.net.CronetEngine
    public final byte[] getGlobalMetricsDeltas() {
        return N.M7CZ_Klr();
    }

    @Override // org.chromium.net.CronetEngine
    public final int getHttpRttMs() {
        int i;
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                i = this.mHttpRttMs;
                if (i == -1) {
                    i = -1;
                }
            }
            return i;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    @Override // org.chromium.net.CronetEngine
    public final int getTransportRttMs() {
        int i;
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                i = this.mTransportRttMs;
                if (i == -1) {
                    i = -1;
                }
            }
            return i;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    public final long getUrlRequestContextAdapter() {
        long j;
        synchronized (this.mLock) {
            checkHaveAdapter();
            j = this.mUrlRequestContextAdapter;
        }
        return j;
    }

    @Override // org.chromium.net.CronetEngine
    public final String getVersionString() {
        return "Cronet/".concat(String.valueOf(ImplVersion.getCronetVersionWithLastChange()));
    }

    @Override // org.chromium.net.impl.CronetEngineBase, org.chromium.net.ExperimentalCronetEngine, org.chromium.net.CronetEngine
    public final /* synthetic */ BidirectionalStream.Builder newBidirectionalStreamBuilder(String str, BidirectionalStream.Callback callback, Executor executor) {
        return new BidirectionalStreamBuilderImpl(str, callback, executor, this);
    }

    @Override // org.chromium.net.impl.CronetEngineBase, org.chromium.net.ExperimentalCronetEngine, org.chromium.net.CronetEngine
    public final /* synthetic */ UrlRequest.Builder newUrlRequestBuilder(String str, UrlRequest.Callback callback, Executor executor) {
        return new UrlRequestBuilderImpl(str, callback, executor, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onRequestDestroyed() {
        this.mRunningRequestCount.decrementAndGet();
    }

    public final void onRequestFinished() {
        this.mActiveRequestCount.decrementAndGet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onRequestStarted() {
        this.mActiveRequestCount.incrementAndGet();
        this.mRunningRequestCount.incrementAndGet();
    }

    @Override // org.chromium.net.CronetEngine
    public final URLConnection openConnection(URL url) {
        return openConnection(url, Proxy.NO_PROXY);
    }

    @Override // org.chromium.net.CronetEngine
    public final void removeRequestFinishedListener(RequestFinishedInfo.Listener listener) {
        synchronized (this.mFinishedListenerLock) {
            this.mFinishedListenerMap.remove(listener);
        }
    }

    @Override // org.chromium.net.CronetEngine
    public final void removeRttListener(NetworkQualityRttListener networkQualityRttListener) {
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                if (this.mRttListenerList.removeObserver(new VersionSafeCallbacks$NetworkQualityRttListenerWrapper(networkQualityRttListener)) && this.mRttListenerList.isEmpty()) {
                    synchronized (this.mLock) {
                        checkHaveAdapter();
                        N.MpnFLFF2(this.mUrlRequestContextAdapter, this, false);
                    }
                }
            }
            return;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    @Override // org.chromium.net.CronetEngine
    public final void removeThroughputListener(NetworkQualityThroughputListener networkQualityThroughputListener) {
        if (this.mNetworkQualityEstimatorEnabled) {
            synchronized (this.mNetworkQualityLock) {
                if (this.mThroughputListenerList.removeObserver(new VersionSafeCallbacks$NetworkQualityThroughputListenerWrapper(networkQualityThroughputListener)) && this.mThroughputListenerList.isEmpty()) {
                    synchronized (this.mLock) {
                        checkHaveAdapter();
                        N.MnPUhNKP(this.mUrlRequestContextAdapter, this, false);
                    }
                }
            }
            return;
        }
        throw new IllegalStateException("Network quality estimator must be enabled");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void reportRequestFinished(RequestFinishedInfo requestFinishedInfo, RefCountDelegate refCountDelegate, VersionSafeCallbacks$RequestFinishedInfoListener versionSafeCallbacks$RequestFinishedInfoListener) {
        synchronized (this.mFinishedListenerLock) {
            if (this.mFinishedListenerMap.isEmpty() && versionSafeCallbacks$RequestFinishedInfoListener == null) {
                return;
            }
            ArrayList arrayList = new ArrayList(this.mFinishedListenerMap.values());
            if (versionSafeCallbacks$RequestFinishedInfoListener != null) {
                arrayList.add(versionSafeCallbacks$RequestFinishedInfoListener);
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                VersionSafeCallbacks$RequestFinishedInfoListener versionSafeCallbacks$RequestFinishedInfoListener2 = (VersionSafeCallbacks$RequestFinishedInfoListener) arrayList.get(i);
                postObservationTaskToExecutor(versionSafeCallbacks$RequestFinishedInfoListener2.getExecutor(), new RetriableStream.Sublistener.AnonymousClass4(versionSafeCallbacks$RequestFinishedInfoListener2, requestFinishedInfo, 8), refCountDelegate);
            }
        }
    }

    @Override // org.chromium.net.CronetEngine
    public final void shutdown() {
        if (this.mInUseStoragePath != null) {
            HashSet hashSet = sInUseStoragePaths;
            synchronized (hashSet) {
                hashSet.remove(this.mInUseStoragePath);
            }
        }
        synchronized (this.mLock) {
            checkHaveAdapter();
            if (this.mRunningRequestCount.get() == 0) {
                if (Thread.currentThread() == this.mNetworkThread) {
                    throw new IllegalThreadStateException("Cannot shutdown from network thread.");
                }
            } else {
                throw new IllegalStateException("Cannot shutdown with running requests.");
            }
        }
        this.mInitCompleted.block();
        synchronized (this.mLock) {
            checkHaveAdapter();
            if (this.mIsLogging && !this.mIsStoppingNetLog) {
                N.MKFm_qQ7(this.mUrlRequestContextAdapter, this);
                this.mIsStoppingNetLog = true;
                this.mStopNetLogCompleted.block();
                this.mStopNetLogCompleted.close();
                synchronized (this.mLock) {
                    this.mIsStoppingNetLog = false;
                    this.mIsLogging = false;
                }
            }
        }
        synchronized (this.mLock) {
            if (!haveRequestContextAdapter()) {
                return;
            }
            N.MeBvNXm5(this.mUrlRequestContextAdapter, this);
            this.mUrlRequestContextAdapter = 0L;
        }
    }

    @Override // org.chromium.net.CronetEngine
    public final void startNetLogToDisk(String str, boolean z, int i) {
        synchronized (this.mLock) {
            checkHaveAdapter();
            if (this.mIsLogging) {
                return;
            }
            N.MTULt02u(this.mUrlRequestContextAdapter, this, str, z, i);
            this.mIsLogging = true;
        }
    }

    @Override // org.chromium.net.CronetEngine
    public final void startNetLogToFile(String str, boolean z) {
        synchronized (this.mLock) {
            checkHaveAdapter();
            if (this.mIsLogging) {
                return;
            }
            if (N.MgwJQAH1(this.mUrlRequestContextAdapter, this, str, z)) {
                this.mIsLogging = true;
                return;
            }
            throw new RuntimeException("Unable to start NetLog");
        }
    }

    @Override // org.chromium.net.CronetEngine
    public final void stopNetLog() {
        synchronized (this.mLock) {
            checkHaveAdapter();
            if (this.mIsLogging && !this.mIsStoppingNetLog) {
                N.MKFm_qQ7(this.mUrlRequestContextAdapter, this);
                this.mIsStoppingNetLog = true;
                this.mStopNetLogCompleted.block();
                this.mStopNetLogCompleted.close();
                synchronized (this.mLock) {
                    this.mIsStoppingNetLog = false;
                    this.mIsLogging = false;
                }
            }
        }
    }

    public void stopNetLogCompleted() {
        this.mStopNetLogCompleted.open();
    }

    private static void postObservationTaskToExecutor(Executor executor, Runnable runnable, RefCountDelegate refCountDelegate) {
        if (refCountDelegate != null) {
            refCountDelegate.increment();
        }
        try {
            executor.execute(new RetriableStream.Sublistener.AnonymousClass4(runnable, refCountDelegate, 6, (byte[]) null));
        } catch (RejectedExecutionException e) {
            if (refCountDelegate != null) {
                refCountDelegate.decrement();
            }
            Log.e("cr_".concat(String.valueOf(LOG_TAG)), "Exception posting task to executor", e);
        }
    }

    @Override // org.chromium.net.impl.CronetEngineBase, org.chromium.net.ExperimentalCronetEngine, org.chromium.net.CronetEngine
    public final ExperimentalBidirectionalStream.Builder newBidirectionalStreamBuilder(String str, BidirectionalStream.Callback callback, Executor executor) {
        return new BidirectionalStreamBuilderImpl(str, callback, executor, this);
    }

    @Override // org.chromium.net.ExperimentalCronetEngine
    public final URLConnection openConnection(URL url, Proxy proxy) {
        if (proxy.type() == Proxy.Type.DIRECT) {
            String protocol = url.getProtocol();
            if (!"http".equals(protocol) && !"https".equals(protocol)) {
                throw new UnsupportedOperationException("Unexpected protocol:".concat(String.valueOf(protocol)));
            }
            return new CronetHttpURLConnection(url, this);
        }
        throw new UnsupportedOperationException();
    }
}
