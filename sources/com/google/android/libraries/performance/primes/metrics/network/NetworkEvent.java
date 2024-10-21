package com.google.android.libraries.performance.primes.metrics.network;

import android.os.SystemClock;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.NetworkMetric$RequestStatus;
import logs.proto.wireless.performance.mobile.ProcessProto$AndroidProcessStats;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkEvent {
    private static final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    public int bytesDownloaded;
    public int bytesUploaded;
    public int cacheHitCount;
    public int cacheLookupCount;
    public String contentType;
    final String domainPath;
    int httpStatusCode;
    final boolean isConstantRpcPath;
    ExtensionMetric$MetricExtension metricExtension;
    String negotiationProtocol;
    int networkType$ar$edu;
    ProcessProto$AndroidProcessStats processStats;
    int quicDetailedErrorCode;
    int requestFailedReason;
    final String requestPath;
    public int serverDistance$ar$edu;
    public final long startTimeMs;
    public long timeToResponseDataFinishMs;
    public long timeToResponseHeaderMs;
    int requestStatus$ar$edu = NetworkMetric$RequestStatus.REQUEST_STATUS_UNSPECIFIED$ar$edu;
    public int rpcStatusCode = -1;
    public Optional eventSampleRatePerMille = Absent.INSTANCE;

    public NetworkEvent(String str, String str2, boolean z, long j) {
        this.domainPath = ContextDataProvider.emptyToNull(str);
        this.requestPath = ContextDataProvider.emptyToNull(str2);
        this.isConstantRpcPath = z;
        this.startTimeMs = j;
    }

    public static NetworkEvent forConstantRpcPath(String str, NoPiiString noPiiString) {
        return new NetworkEvent(str, NoPiiString.safeToString(noPiiString), true, SystemClock.elapsedRealtime());
    }
}
