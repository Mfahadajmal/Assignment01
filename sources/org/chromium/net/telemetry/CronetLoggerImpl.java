package org.chromium.net.telemetry;

import android.os.Build;
import android.os.SystemClock;
import com.google.mlkit.logging.schema.acceleration.NNAPIInfo;
import com.google.mlkit.logging.schema.acceleration.PipelineAcceleration;
import j$.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.net.impl.CronetLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetLoggerImpl extends CronetLogger {
    private final RateLimiter mRateLimiter;
    private final AtomicInteger mSamplesRateLimited;

    public CronetLoggerImpl() {
        RateLimiter rateLimiter = new RateLimiter();
        this.mSamplesRateLimited = new AtomicInteger();
        this.mRateLimiter = rateLimiter;
    }

    private static long[] longListToLongArray(List list) {
        long[] jArr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            jArr[i] = ((Long) list.get(i)).longValue();
        }
        return jArr;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final long generateId() {
        long nextLong = ThreadLocalRandom.current().nextLong(-9223372036854775807L, 9223372036854775805L);
        if (nextLong >= -1) {
            return nextLong + 2;
        }
        return nextLong;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final void logCronetEngineBuilderInitializedInfo(CronetLogger.CronetEngineBuilderInitializedInfo cronetEngineBuilderInitializedInfo) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long j = cronetEngineBuilderInitializedInfo.cronetInitializationRef;
        int i6 = cronetEngineBuilderInitializedInfo.author$ar$edu;
        int i7 = i6 - 1;
        if (i6 != 0) {
            int i8 = 2;
            if (i7 != 0) {
                if (i7 != 1) {
                    i = 0;
                } else {
                    i = 2;
                }
            } else {
                i = 1;
            }
            int i9 = cronetEngineBuilderInitializedInfo.engineBuilderCreatedLatencyMillis;
            int ordinal = ((CronetLogger.CronetSource) cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$source).ordinal();
            if (ordinal != 1) {
                if (ordinal != 2) {
                    i8 = 3;
                    if (ordinal != 3) {
                        i8 = 4;
                        if (ordinal != 4) {
                            i8 = 0;
                        }
                    }
                }
            } else {
                i8 = 1;
            }
            int value$ar$edu$7d7efcc2_0 = PipelineAcceleration.getValue$ar$edu$7d7efcc2_0(PipelineAcceleration.fromBoolean$ar$edu((Boolean) cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$creationSuccessful));
            CronetLogger.CronetVersion cronetVersion = (CronetLogger.CronetVersion) cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$apiVersion;
            int i10 = cronetVersion.mMajorVersion;
            int i11 = cronetVersion.mMinorVersion;
            int i12 = cronetVersion.mBuildVersion;
            int i13 = cronetVersion.mPatchVersion;
            Object obj = cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$implVersion;
            if (obj == null) {
                i2 = -1;
            } else {
                i2 = ((CronetLogger.CronetVersion) obj).mMajorVersion;
            }
            if (obj == null) {
                i3 = -1;
            } else {
                i3 = ((CronetLogger.CronetVersion) obj).mMinorVersion;
            }
            if (obj == null) {
                i4 = -1;
            } else {
                i4 = ((CronetLogger.CronetVersion) obj).mBuildVersion;
            }
            if (obj == null) {
                i5 = -1;
            } else {
                i5 = ((CronetLogger.CronetVersion) obj).mPatchVersion;
            }
            NNAPIInfo.write$ar$ds$d3c1bcd5_0(j, i, i9, i8, value$ar$edu$7d7efcc2_0, i10, i11, i12, i13, i2, i3, i4, i5, cronetEngineBuilderInitializedInfo.uid);
            return;
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006e A[Catch: Exception -> 0x028e, TryCatch #0 {Exception -> 0x028e, blocks: (B:4:0x000e, B:11:0x0034, B:16:0x004c, B:18:0x006e, B:20:0x007d, B:22:0x0097, B:24:0x009a, B:27:0x00a3, B:29:0x00b4, B:30:0x00b8, B:32:0x00c3, B:34:0x00cb, B:35:0x00d5, B:38:0x0266, B:43:0x025c), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x025c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x004a  */
    @Override // org.chromium.net.impl.CronetLogger
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void logCronetEngineCreation(long r47, org.chromium.net.impl.CronetLogger.CronetEngineBuilderInfo r49, org.chromium.net.impl.CronetLogger.CronetVersion r50, org.chromium.net.impl.CronetLogger.CronetSource r51) {
        /*
            Method dump skipped, instructions count: 655
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.telemetry.CronetLoggerImpl.logCronetEngineCreation(long, org.chromium.net.impl.CronetLogger$CronetEngineBuilderInfo, org.chromium.net.impl.CronetLogger$CronetVersion, org.chromium.net.impl.CronetLogger$CronetSource):void");
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final void logCronetInitializedInfo(CronetLogger.CronetInitializedInfo cronetInitializedInfo) {
        if (Build.VERSION.SDK_INT < 33) {
            return;
        }
        NNAPIInfo.write$ar$ds$3303faad_0(cronetInitializedInfo.cronetInitializationRef, cronetInitializedInfo.engineCreationLatencyMillis, cronetInitializedInfo.engineAsyncLatencyMillis, cronetInitializedInfo.httpFlagsLatencyMillis, PipelineAcceleration.getValue$ar$edu$7d7efcc2_0(PipelineAcceleration.fromBoolean$ar$edu(cronetInitializedInfo.httpFlagsSuccessful)), longListToLongArray(cronetInitializedInfo.httpFlagsNames), longListToLongArray(cronetInitializedInfo.httpFlagsValues));
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final void logCronetTrafficInfo(long j, CronetLogger.CronetTrafficInfo cronetTrafficInfo) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        RateLimiter rateLimiter = this.mRateLimiter;
        synchronized (rateLimiter.mLock) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (rateLimiter.mLastPermitMillis + 1000 <= elapsedRealtime) {
                rateLimiter.mSamplesLoggedDuringSecond = 1;
                rateLimiter.mLastPermitMillis = elapsedRealtime;
            } else if (rateLimiter.mSamplesLoggedDuringSecond <= 0) {
                rateLimiter.mSamplesLoggedDuringSecond = 1;
            } else {
                this.mSamplesRateLimited.incrementAndGet();
                return;
            }
            int andSet = this.mSamplesRateLimited.getAndSet(0);
            try {
                long j2 = cronetTrafficInfo.mRequestHeaderSizeInBytes;
                PipelineAcceleration.checkSizeIsValid(j2, "Request header size is negative");
                double d = j2 / 1024.0d;
                if (PipelineAcceleration.isInClosedOpenRange(d, 0, 1)) {
                    i = 1;
                } else if (PipelineAcceleration.isInClosedOpenRange(d, 1, 10)) {
                    i = 2;
                } else if (PipelineAcceleration.isInClosedOpenRange(d, 10, 25)) {
                    i = 3;
                } else if (PipelineAcceleration.isInClosedOpenRange(d, 25, 50)) {
                    i = 4;
                } else if (PipelineAcceleration.isInClosedOpenRange(d, 50, 100)) {
                    i = 5;
                } else {
                    i = 6;
                }
                long j3 = cronetTrafficInfo.mRequestBodySizeInBytes;
                PipelineAcceleration.checkSizeIsValid(j3, "Request body size is negative");
                double d2 = j3 / 1024.0d;
                if (d2 == 0.0d) {
                    i2 = 1;
                } else if (d2 > 0.0d && d2 < 10.0d) {
                    i2 = 2;
                } else if (PipelineAcceleration.isInClosedOpenRange(d2, 10, 50)) {
                    i2 = 3;
                } else if (PipelineAcceleration.isInClosedOpenRange(d2, 50, 200)) {
                    i2 = 4;
                } else if (PipelineAcceleration.isInClosedOpenRange(d2, 200, 500)) {
                    i2 = 5;
                } else if (PipelineAcceleration.isInClosedOpenRange(d2, 500, 1000)) {
                    i2 = 6;
                } else if (PipelineAcceleration.isInClosedOpenRange(d2, 1000, 5000)) {
                    i2 = 7;
                } else {
                    i2 = 8;
                }
                long j4 = cronetTrafficInfo.mResponseHeaderSizeInBytes;
                PipelineAcceleration.checkSizeIsValid(j4, "Response header size is negative");
                double d3 = j4 / 1024.0d;
                if (PipelineAcceleration.isInClosedOpenRange(d3, 0, 1)) {
                    i3 = 1;
                } else if (PipelineAcceleration.isInClosedOpenRange(d3, 1, 10)) {
                    i3 = 2;
                } else if (PipelineAcceleration.isInClosedOpenRange(d3, 10, 25)) {
                    i3 = 3;
                } else if (PipelineAcceleration.isInClosedOpenRange(d3, 25, 50)) {
                    i3 = 4;
                } else if (PipelineAcceleration.isInClosedOpenRange(d3, 50, 100)) {
                    i3 = 5;
                } else {
                    i3 = 6;
                }
                long j5 = cronetTrafficInfo.mResponseBodySizeInBytes;
                PipelineAcceleration.checkSizeIsValid(j5, "Response body size is negative");
                double d4 = j5 / 1024.0d;
                if (d4 == 0.0d) {
                    i4 = 1;
                } else if (d4 > 0.0d && d4 < 10.0d) {
                    i4 = 2;
                } else if (PipelineAcceleration.isInClosedOpenRange(d4, 10, 50)) {
                    i4 = 3;
                } else if (PipelineAcceleration.isInClosedOpenRange(d4, 50, 200)) {
                    i4 = 4;
                } else if (PipelineAcceleration.isInClosedOpenRange(d4, 200, 500)) {
                    i4 = 5;
                } else if (PipelineAcceleration.isInClosedOpenRange(d4, 500, 1000)) {
                    i4 = 6;
                } else if (PipelineAcceleration.isInClosedOpenRange(d4, 1000, 5000)) {
                    i4 = 7;
                } else {
                    i4 = 8;
                }
                int i6 = cronetTrafficInfo.mResponseStatusCode;
                long hash = Hash.hash(cronetTrafficInfo.mNegotiatedProtocol);
                int millis = (int) cronetTrafficInfo.mHeadersLatency.toMillis();
                int millis2 = (int) cronetTrafficInfo.mTotalLatency.toMillis();
                boolean z = cronetTrafficInfo.mWasConnectionMigrationAttempted;
                boolean z2 = cronetTrafficInfo.mDidConnectionMigrationSucceed;
                int i7 = cronetTrafficInfo.mTerminalState$ar$edu - 1;
                if (i7 != 0) {
                    if (i7 != 1) {
                        i5 = 3;
                    } else {
                        i5 = 2;
                    }
                } else {
                    i5 = 1;
                }
                NNAPIInfo.write$ar$ds$c46fd982_0(j, i, i2, i3, i4, i6, hash, millis, millis2, z, z2, andSet, i5, cronetTrafficInfo.mNonfinalUserCallbackExceptionCount, cronetTrafficInfo.mReadCount, cronetTrafficInfo.mOnUploadReadCount, PipelineAcceleration.getValue$ar$edu$7d7efcc2_0(PipelineAcceleration.fromBoolean$ar$edu(Boolean.valueOf(cronetTrafficInfo.mIsBidiStream))), PipelineAcceleration.getValue$ar$edu$7d7efcc2_0(PipelineAcceleration.fromBoolean$ar$edu(Boolean.valueOf(cronetTrafficInfo.mFinalUserCallbackThrew))));
            } catch (Exception unused) {
                this.mSamplesRateLimited.addAndGet(andSet);
            }
        }
    }
}
