package com.google.common.android.concurrent;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import androidx.collection.SparseArrayCompatKt;
import androidx.preference.Preference;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager$$ExternalSyntheticLambda4;
import com.google.common.collect.ListMultimap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.frameworks.client.data.android.impl.TransportChannel;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCall;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener;
import com.google.frameworks.client.data.android.internal.TracePropagatingClientCallListener;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.DurationStats;
import com.google.mlkit.logging.schema.MLKitEnum$ClientType;
import com.google.mlkit.logging.schema.MLKitEnum$EventName;
import com.google.mlkit.logging.schema.MLKitSdkLogEvent;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.shared.logger.SchemaLogEvent;
import com.google.mlkit.vision.text.internal.TextRecognizerTaskWithResource;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import io.grpc.internal.RetryingNameResolver;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.CronetUrlRequest;
import org.chromium.net.impl.JavaUrlRequest;
import org.chromium.net.impl.UrlResponseInfoImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FutureCallbackViewModel$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Object FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
    public final /* synthetic */ Object FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1;
    public final /* synthetic */ Object f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FutureCallbackViewModel$$ExternalSyntheticLambda1(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Status status, Metadata metadata, int i) {
        this.switching_field = i;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = onDeviceFaceMeshCreateLogEvent;
        this.f$2 = status;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = metadata;
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.util.List, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        MLKitEnum$ClientType mLKitEnum$ClientType;
        int i = 0;
        switch (this.switching_field) {
            case 0:
                ((FutureCallbackRegistry$Callback) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0).onSuccess(((ParcelableFuture) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1).input, this.f$2);
                return;
            case 1:
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Iterator it = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1.iterator();
                while (true) {
                    Object obj2 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                    if (it.hasNext()) {
                        File file = (File) it.next();
                        String splitIdFromFile = NativeLibraryPathListMutex.getSplitIdFromFile(file);
                        Uri fromFile = Uri.fromFile(file);
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setDataAndType(fromFile, ((FakeSplitInstallManager) obj2).context.getContentResolver().getType(fromFile));
                        intent.addFlags(1);
                        intent.putExtra("module_name", FakeSplitInstallManager.getModuleName(splitIdFromFile));
                        intent.putExtra("split_id", splitIdFromFile);
                        arrayList.add(intent);
                        arrayList2.add(FakeSplitInstallManager.getModuleName(NativeLibraryPathListMutex.getSplitIdFromFile(file)));
                    } else {
                        FakeSplitInstallManager fakeSplitInstallManager = (FakeSplitInstallManager) obj2;
                        SplitInstallSessionState internalSessionState = fakeSplitInstallManager.getInternalSessionState();
                        if (internalSessionState == null) {
                            return;
                        }
                        fakeSplitInstallManager.backgroundExecutor.execute(new FakeSplitInstallManager$$ExternalSyntheticLambda4(fakeSplitInstallManager, internalSessionState.totalBytesToDownload(), arrayList, arrayList2, (List) this.f$2, 1));
                        return;
                    }
                }
            case 2:
                FutureCallbackViewModel futureCallbackViewModel = (FutureCallbackViewModel) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                FragmentManager fragmentManager = futureCallbackViewModel.hostFragmentManager;
                if (fragmentManager != null) {
                    if (!fragmentManager.isStateSaved()) {
                        if (!futureCallbackViewModel.hostFragmentManager.mDestroyed && futureCallbackViewModel.pendingFutures.remove(this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1)) {
                            this.f$2.run();
                            return;
                        }
                        return;
                    }
                    futureCallbackViewModel.retryDelivery = true;
                    return;
                }
                return;
            case 3:
                Object obj3 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1;
                int i2 = ((ParcelableFuture) obj3).callbackId;
                FutureCallbackViewModel futureCallbackViewModel2 = (FutureCallbackViewModel) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                futureCallbackViewModel2.deliverResult(new FutureCallbackViewModel$$ExternalSyntheticLambda1((FutureCallbackRegistry$Callback) SparseArrayCompatKt.commonGet(futureCallbackViewModel2.callbacks, i2), obj3, this.f$2, 5));
                return;
            case 4:
                Object obj4 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1;
                int i3 = ((ParcelableFuture) obj4).callbackId;
                FutureCallbackViewModel futureCallbackViewModel3 = (FutureCallbackViewModel) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                futureCallbackViewModel3.deliverResult(new FutureCallbackViewModel$$ExternalSyntheticLambda1((FutureCallbackRegistry$Callback) SparseArrayCompatKt.commonGet(futureCallbackViewModel3.callbacks, i3), obj4, this.f$2, i));
                return;
            case 5:
                ((FutureCallbackRegistry$Callback) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0).onFailure(((ParcelableFuture) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1).input, (Throwable) this.f$2);
                return;
            case 6:
                ((TransportChannel.EnqueueingClientCall) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0).delegate.cancel((String) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1, (Throwable) this.f$2);
                return;
            case 7:
                ((TransportChannel.EnqueueingClientCall) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0).delegate.start$ar$class_merging$ar$class_merging((OnDeviceFaceMeshCreateLogEvent) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1, (Metadata) this.f$2);
                return;
            case 8:
                ((AsyncInterceptorsClientCall) this.f$2).continueRequestMessageProcessing$ar$class_merging$ar$class_merging$ar$class_merging((ContextDataProvider) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0, (AsyncInterceptorsClientCall.PendingMessage) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1);
                return;
            case 9:
                AsyncInterceptorsClientCall asyncInterceptorsClientCall = (AsyncInterceptorsClientCall) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                Iterator it2 = asyncInterceptorsClientCall.cancelableTriggers.iterator();
                while (it2.hasNext()) {
                    ((ListenableFuture) it2.next()).cancel(true);
                }
                ClientCall clientCall = asyncInterceptorsClientCall.delegate;
                if (clientCall != null) {
                    clientCall.cancel((String) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1, (Throwable) this.f$2);
                    return;
                }
                return;
            case 10:
                ((AsyncInterceptorsClientCall) this.f$2).continueRequestMessageProcessing$ar$class_merging$ar$class_merging$ar$class_merging((ContextDataProvider) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0, (AsyncInterceptorsClientCall.PendingMessage) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1);
                return;
            case 11:
                Object obj5 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                AsyncInterceptorsClientCall.ClosedOnceListener closedOnceListener = (AsyncInterceptorsClientCall.ClosedOnceListener) obj5;
                boolean z = closedOnceListener.closed;
                Object obj6 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1;
                Object obj7 = this.f$2;
                if (!z) {
                    try {
                        ((AsyncInterceptorsClientCall.ClosedOnceListener) obj5).delegate$ar$class_merging$a40ae667_0$ar$class_merging.onClose((Status) obj7, (Metadata) obj6);
                        return;
                    } finally {
                        closedOnceListener.closed = true;
                        closedOnceListener.this$0.releaseDelegateExecutor.freeDelegate();
                    }
                }
                return;
            case 12:
                AsyncInterceptorsClientCallListener.PendingMessage pendingMessage = (AsyncInterceptorsClientCallListener.PendingMessage) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1;
                Iterator it3 = ((LinkedHashMap) pendingMessage.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors).entrySet().iterator();
                while (true) {
                    obj = this.f$2;
                    if (it3.hasNext()) {
                        Map.Entry entry = (Map.Entry) it3.next();
                        if (((ListenableFuture) entry.getValue()).isDone()) {
                            it3.remove();
                            try {
                                ((AsyncClientInterceptor) entry.getKey()).continueResponseMessageProcessing$ar$ds();
                            } catch (Throwable th) {
                                AsyncInterceptorsClientCallListener asyncInterceptorsClientCallListener = (AsyncInterceptorsClientCallListener) obj;
                                asyncInterceptorsClientCallListener.status = Status.fromThrowable(th);
                                asyncInterceptorsClientCallListener.trailers = new Metadata();
                                asyncInterceptorsClientCallListener.startCloseDelegate();
                                asyncInterceptorsClientCallListener.completedWithErrorStatus = true;
                                return;
                            }
                        }
                    }
                }
                if (!pendingMessage.hasPendingFutures()) {
                    pendingMessage.currentStage--;
                    ((AsyncInterceptorsClientCallListener) obj).maybeProcessResponseMessages();
                    return;
                }
                return;
            case 13:
                ((TracePropagatingClientCallListener) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0).delegate$ar$class_merging$a40ae667_0$ar$class_merging.onClose((Status) this.f$2, (Metadata) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1);
                return;
            case 14:
                Object obj8 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                MLKitStatsLogger mLKitStatsLogger = (MLKitStatsLogger) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1;
                ListMultimap listMultimap = (ListMultimap) mLKitStatsLogger.durationsMsMap.get(obj8);
                if (listMultimap != null) {
                    for (Object obj9 : listMultimap.keySet()) {
                        ArrayList arrayList3 = new ArrayList(listMultimap.get(obj9));
                        Collections.sort(arrayList3);
                        DurationStats durationStats = new DurationStats();
                        Iterator it4 = arrayList3.iterator();
                        long j = 0;
                        while (it4.hasNext()) {
                            j += ((Long) it4.next()).longValue();
                        }
                        Object obj10 = this.f$2;
                        long size = j / arrayList3.size();
                        Long.valueOf(size).getClass();
                        durationStats.DurationStats$ar$avgMs = Long.valueOf(size & Long.MAX_VALUE);
                        long percentile = MLKitStatsLogger.percentile(arrayList3, 100.0d);
                        Long.valueOf(percentile).getClass();
                        durationStats.DurationStats$ar$maxMs = Long.valueOf(percentile & Long.MAX_VALUE);
                        long percentile2 = MLKitStatsLogger.percentile(arrayList3, 75.0d);
                        Long.valueOf(percentile2).getClass();
                        durationStats.DurationStats$ar$thirdQuartileMs = Long.valueOf(percentile2 & Long.MAX_VALUE);
                        long percentile3 = MLKitStatsLogger.percentile(arrayList3, 50.0d);
                        Long.valueOf(percentile3).getClass();
                        durationStats.DurationStats$ar$medianMs = Long.valueOf(percentile3 & Long.MAX_VALUE);
                        long percentile4 = MLKitStatsLogger.percentile(arrayList3, 25.0d);
                        Long.valueOf(percentile4).getClass();
                        durationStats.DurationStats$ar$firstQuartileMs = Long.valueOf(percentile4 & Long.MAX_VALUE);
                        long percentile5 = MLKitStatsLogger.percentile(arrayList3, 0.0d);
                        Long.valueOf(percentile5).getClass();
                        durationStats.DurationStats$ar$minMs = Long.valueOf(percentile5 & Long.MAX_VALUE);
                        DurationStats durationStats2 = new DurationStats(durationStats);
                        int size2 = arrayList3.size();
                        AggregatedOnDeviceTextDetectionLogEvent.LogEventKey logEventKey = (AggregatedOnDeviceTextDetectionLogEvent.LogEventKey) obj9;
                        MLKitSdkLogEvent mLKitSdkLogEvent = new MLKitSdkLogEvent();
                        if (((TextRecognizerTaskWithResource) ((RetryingNameResolver.ResolutionResultListener) obj10).RetryingNameResolver$ResolutionResultListener$ar$this$0).textRecognizerOptions.getIsThickClient()) {
                            mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THICK;
                        } else {
                            mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THIN;
                        }
                        mLKitSdkLogEvent.clientType = mLKitEnum$ClientType;
                        AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent();
                        Integer.valueOf(size2).getClass();
                        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = Integer.valueOf(size2 & Preference.DEFAULT_ORDER);
                        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = logEventKey;
                        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = durationStats2;
                        mLKitSdkLogEvent.aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent(aggregatedOnDeviceTextDetectionLogEvent);
                        mLKitStatsLogger.logEventWithEventName$ar$class_merging(new SchemaLogEvent(mLKitSdkLogEvent, 0), (MLKitEnum$EventName) obj8);
                    }
                    mLKitStatsLogger.durationsMsMap.remove(obj8);
                    return;
                }
                return;
            case 15:
                ((DelayedClientCall) this.f$2).realCall.start$ar$class_merging$ar$class_merging((OnDeviceFaceMeshCreateLogEvent) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0, (Metadata) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1);
                return;
            case 16:
                ((DelayedClientCall.DelayedListener) this.f$2).realListener$ar$class_merging$ar$class_merging.onClose((Status) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1, (Metadata) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0);
                return;
            case 17:
                Object obj11 = this.f$2;
                ManagedChannelImpl managedChannelImpl = ManagedChannelImpl.this;
                if (obj11 == managedChannelImpl.lbHelper) {
                    managedChannelImpl.updateSubchannelPicker((LoadBalancer.SubchannelPicker) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1);
                    if (this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 != ConnectivityState.SHUTDOWN) {
                        ManagedChannelImpl.this.channelLogger.log$ar$edu$7fdc135b_0(2, "Entering {0} state with picker: {1}", this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0, this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1);
                        ManagedChannelImpl.this.channelStateManager.gotoState((ConnectivityState) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0);
                        return;
                    }
                    return;
                }
                return;
            case 18:
                synchronized (((RetriableStream.FutureCanceller) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0).lock) {
                    Object obj12 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0;
                    if (((RetriableStream.FutureCanceller) obj12).cancelled) {
                        return;
                    }
                    ((RetriableStream.FutureCanceller) obj12).markCancelled();
                    RetriableStream.this.callExecutor.execute(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 0));
                    return;
                }
            case 19:
                ((CronetUrlRequest) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1).checkCallingThread();
                synchronized (((CronetUrlRequest) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1).mUrlRequestAdapterLock) {
                    if (((CronetUrlRequest) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1).isDoneLocked()) {
                        return;
                    }
                    ((CronetUrlRequest) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1).mWaitingOnRedirect = true;
                    try {
                        Object obj13 = this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1;
                        ((CronetUrlRequest) obj13).mCallback.onRedirectReceived((UrlRequest) obj13, (UrlResponseInfo) this.f$2, (String) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0);
                        return;
                    } catch (Exception e) {
                        ((CronetUrlRequest) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1).onNonfinalCallbackException(e);
                        return;
                    }
                }
            default:
                ((JavaUrlRequest.AsyncUrlRequestCallback) this.f$2).lambda$onFailed$7((UrlResponseInfo) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1, (CronetException) this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0);
                return;
        }
    }

    public /* synthetic */ FutureCallbackViewModel$$ExternalSyntheticLambda1(MLKitStatsLogger mLKitStatsLogger, MLKitEnum$EventName mLKitEnum$EventName, RetryingNameResolver.ResolutionResultListener resolutionResultListener, int i) {
        this.switching_field = i;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = mLKitStatsLogger;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = mLKitEnum$EventName;
        this.f$2 = resolutionResultListener;
    }

    public FutureCallbackViewModel$$ExternalSyntheticLambda1(DelayedClientCall delayedClientCall, OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata, int i) {
        this.switching_field = i;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = onDeviceFaceMeshCreateLogEvent;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = metadata;
        this.f$2 = delayedClientCall;
    }

    public FutureCallbackViewModel$$ExternalSyntheticLambda1(RetriableStream.Sublistener sublistener, RetriableStream.FutureCanceller futureCanceller, RetriableStream.Substream substream, int i) {
        this.switching_field = i;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = futureCanceller;
        this.f$2 = substream;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = sublistener;
    }

    public /* synthetic */ FutureCallbackViewModel$$ExternalSyntheticLambda1(Object obj, ContextDataProvider contextDataProvider, Object obj2, int i) {
        this.switching_field = i;
        this.f$2 = obj;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = contextDataProvider;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = obj2;
    }

    public /* synthetic */ FutureCallbackViewModel$$ExternalSyntheticLambda1(Object obj, Object obj2, Object obj3, int i) {
        this.switching_field = i;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = obj;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = obj2;
        this.f$2 = obj3;
    }

    public FutureCallbackViewModel$$ExternalSyntheticLambda1(Object obj, Object obj2, Object obj3, int i, byte[] bArr) {
        this.switching_field = i;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = obj2;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = obj3;
        this.f$2 = obj;
    }

    public FutureCallbackViewModel$$ExternalSyntheticLambda1(CronetUrlRequest cronetUrlRequest, UrlResponseInfoImpl urlResponseInfoImpl, String str, int i) {
        this.switching_field = i;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = cronetUrlRequest;
        this.f$2 = urlResponseInfoImpl;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = str;
    }

    public /* synthetic */ FutureCallbackViewModel$$ExternalSyntheticLambda1(JavaUrlRequest.AsyncUrlRequestCallback asyncUrlRequestCallback, UrlResponseInfo urlResponseInfo, CronetException cronetException, int i) {
        this.switching_field = i;
        this.f$2 = asyncUrlRequestCallback;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$1 = urlResponseInfo;
        this.FutureCallbackViewModel$$ExternalSyntheticLambda1$ar$f$0 = cronetException;
    }
}
