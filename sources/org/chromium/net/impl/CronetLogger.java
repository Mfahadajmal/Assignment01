package org.chromium.net.impl;

import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import j$.time.Duration;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CronetLogger {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetEngineBuilderInfo {
        public final boolean mBrotiEnabled;
        public final long mCronetInitializationRef;
        public final String mExperimentalOptions;
        public final boolean mHttp2Enabled;
        public final int mHttpCacheMode;
        public final boolean mNetworkQualityEstimatorEnabled;
        public final boolean mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
        public final boolean mQuicEnabled;
        public final int mThreadPriority;

        public CronetEngineBuilderInfo(boolean z, boolean z2, boolean z3, boolean z4, int i, String str, boolean z5, int i2, long j) {
            this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = z;
            this.mQuicEnabled = z2;
            this.mHttp2Enabled = z3;
            this.mBrotiEnabled = z4;
            this.mHttpCacheMode = i;
            this.mExperimentalOptions = str;
            this.mNetworkQualityEstimatorEnabled = z5;
            this.mThreadPriority = i2;
            this.mCronetInitializationRef = j;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetEngineBuilderInitializedInfo {
        public Object CronetLogger$CronetEngineBuilderInitializedInfo$ar$apiVersion;
        public Object CronetLogger$CronetEngineBuilderInitializedInfo$ar$creationSuccessful;
        public Object CronetLogger$CronetEngineBuilderInitializedInfo$ar$implVersion;
        public Object CronetLogger$CronetEngineBuilderInitializedInfo$ar$source;
        public int author$ar$edu;
        public long cronetInitializationRef;
        public int engineBuilderCreatedLatencyMillis;
        public int uid;

        public CronetEngineBuilderInitializedInfo() {
            this.engineBuilderCreatedLatencyMillis = -1;
            this.CronetLogger$CronetEngineBuilderInitializedInfo$ar$source = CronetSource.CRONET_SOURCE_UNSPECIFIED;
        }

        public final void incrementTextChangesAwaitingSelection(int i) {
            this.uid += i;
        }

        public final void setLastKeptTextSelection(AccessibilityEvent accessibilityEvent) {
            this.CronetLogger$CronetEngineBuilderInitializedInfo$ar$creationSuccessful = SpannableUtils$IdentifierSpan.copy(accessibilityEvent);
        }

        public final void setLastProcessedEvent(AccessibilityEvent accessibilityEvent) {
            this.CronetLogger$CronetEngineBuilderInitializedInfo$ar$source = SpannableUtils$IdentifierSpan.copy(accessibilityEvent);
        }

        public CronetEngineBuilderInitializedInfo(byte[] bArr) {
            this.uid = 0;
            this.cronetInitializationRef = -1L;
            this.author$ar$edu = -1;
            this.engineBuilderCreatedLatencyMillis = -1;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetInitializedInfo {
        public long cronetInitializationRef;
        public List httpFlagsNames;
        public Boolean httpFlagsSuccessful;
        public List httpFlagsValues;
        public int engineCreationLatencyMillis = -1;
        public int engineAsyncLatencyMillis = -1;
        public int httpFlagsLatencyMillis = -1;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum CronetSource {
        CRONET_SOURCE_UNSPECIFIED,
        CRONET_SOURCE_STATICALLY_LINKED,
        CRONET_SOURCE_PLAY_SERVICES,
        CRONET_SOURCE_FALLBACK,
        CRONET_SOURCE_PLATFORM,
        CRONET_SOURCE_FAKE
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetTrafficInfo {
        public final boolean mDidConnectionMigrationSucceed;
        public final boolean mFinalUserCallbackThrew;
        public final Duration mHeadersLatency;
        public final boolean mIsBidiStream;
        public final String mNegotiatedProtocol;
        public final int mNonfinalUserCallbackExceptionCount;
        public final int mOnUploadReadCount;
        public final int mReadCount;
        public final long mRequestBodySizeInBytes;
        public final long mRequestHeaderSizeInBytes;
        public final long mResponseBodySizeInBytes;
        public final long mResponseHeaderSizeInBytes;
        public final int mResponseStatusCode;
        public final int mTerminalState$ar$edu;
        public final Duration mTotalLatency;
        public final boolean mWasConnectionMigrationAttempted;

        public CronetTrafficInfo(long j, long j2, long j3, long j4, int i, Duration duration, Duration duration2, String str, boolean z, boolean z2, int i2, int i3, int i4, int i5, boolean z3, boolean z4) {
            this.mRequestHeaderSizeInBytes = j;
            this.mRequestBodySizeInBytes = j2;
            this.mResponseHeaderSizeInBytes = j3;
            this.mResponseBodySizeInBytes = j4;
            this.mResponseStatusCode = i;
            this.mHeadersLatency = duration;
            this.mTotalLatency = duration2;
            this.mNegotiatedProtocol = str;
            this.mWasConnectionMigrationAttempted = z;
            this.mDidConnectionMigrationSucceed = z2;
            this.mTerminalState$ar$edu = i2;
            this.mNonfinalUserCallbackExceptionCount = i3;
            this.mReadCount = i4;
            this.mOnUploadReadCount = i5;
            this.mIsBidiStream = z3;
            this.mFinalUserCallbackThrew = z4;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetVersion {
        public final int mBuildVersion;
        public final int mMajorVersion;
        public final int mMinorVersion;
        public final int mPatchVersion;

        public CronetVersion(String str) {
            String[] split = str.split("\\.");
            this.mMajorVersion = Integer.parseInt(split[0]);
            this.mMinorVersion = Integer.parseInt(split[1]);
            this.mBuildVersion = Integer.parseInt(split[2]);
            this.mPatchVersion = Integer.parseInt(split[3]);
        }

        public final String toString() {
            return this.mMajorVersion + "." + this.mMinorVersion + "." + this.mBuildVersion + "." + this.mPatchVersion;
        }
    }

    public abstract long generateId();

    public abstract void logCronetEngineBuilderInitializedInfo(CronetEngineBuilderInitializedInfo cronetEngineBuilderInitializedInfo);

    public abstract void logCronetEngineCreation(long j, CronetEngineBuilderInfo cronetEngineBuilderInfo, CronetVersion cronetVersion, CronetSource cronetSource);

    public abstract void logCronetInitializedInfo(CronetInitializedInfo cronetInitializedInfo);

    public abstract void logCronetTrafficInfo(long j, CronetTrafficInfo cronetTrafficInfo);
}
