package org.chromium.net.impl;

import android.os.SystemClock;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.chromium.net.CronetEngine;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.ICronetEngineBuilder;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NativeCronetEngineBuilderImpl extends CronetEngineBuilderImpl {
    private static final AtomicLong sLogCronetInitializationRef = new AtomicLong(0);

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public NativeCronetEngineBuilderImpl(android.content.Context r4) {
        /*
            r3 = this;
            java.lang.Class<org.chromium.net.impl.CronetEngineBuilderImpl> r0 = org.chromium.net.impl.CronetEngineBuilderImpl.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            java.lang.String r1 = r0.toString()
            java.lang.String r2 = "java.lang.BootClassLoader"
            boolean r1 = r1.startsWith(r2)
            if (r1 == 0) goto L15
            org.chromium.net.impl.CronetLogger$CronetSource r0 = org.chromium.net.impl.CronetLogger.CronetSource.CRONET_SOURCE_PLATFORM
            goto L26
        L15:
            java.lang.Class<org.chromium.net.ExperimentalCronetEngine> r1 = org.chromium.net.ExperimentalCronetEngine.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L24
            org.chromium.net.impl.CronetLogger$CronetSource r0 = org.chromium.net.impl.CronetLogger.CronetSource.CRONET_SOURCE_PLAY_SERVICES
            goto L26
        L24:
            org.chromium.net.impl.CronetLogger$CronetSource r0 = org.chromium.net.impl.CronetLogger.CronetSource.CRONET_SOURCE_STATICALLY_LINKED
        L26:
            r3.<init>(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.impl.NativeCronetEngineBuilderImpl.<init>(android.content.Context):void");
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* bridge */ /* synthetic */ ICronetEngineBuilder addPublicKeyPins(String str, Set set, boolean z, Date date) {
        super.addPublicKeyPins$ar$ds(str, set, z, date);
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* bridge */ /* synthetic */ ICronetEngineBuilder addQuicHint(String str, int i, int i2) {
        super.addQuicHint$ar$ds(str, i, i2);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ExperimentalCronetEngine build() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.mUserAgent == null) {
            this.mUserAgent = getDefaultUserAgent();
        }
        return new CronetUrlRequestContext(this, uptimeMillis);
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* synthetic */ ICronetEngineBuilder enableBrotli(boolean z) {
        this.mBrotiEnabled = z;
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* synthetic */ ICronetEngineBuilder enableHttp2(boolean z) {
        this.mHttp2Enabled = z;
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* bridge */ /* synthetic */ ICronetEngineBuilder enableHttpCache(int i, long j) {
        super.enableHttpCache$ar$ds(i, j);
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* synthetic */ ICronetEngineBuilder enableNetworkQualityEstimator(boolean z) {
        this.mNetworkQualityEstimatorEnabled = z;
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* synthetic */ ICronetEngineBuilder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean z) {
        this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = z;
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* synthetic */ ICronetEngineBuilder enableQuic(boolean z) {
        this.mQuicEnabled = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final long getLogCronetInitializationRef() {
        AtomicLong atomicLong = sLogCronetInitializationRef;
        atomicLong.compareAndSet(0L, this.mLogger.generateId());
        return atomicLong.get();
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* synthetic */ ICronetEngineBuilder setExperimentalOptions(String str) {
        this.mExperimentalOptions = str;
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* bridge */ /* synthetic */ ICronetEngineBuilder setStoragePath(String str) {
        super.setStoragePath$ar$ds(str);
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* bridge */ /* synthetic */ ICronetEngineBuilder setThreadPriority(int i) {
        super.setThreadPriority$ar$ds(i);
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* synthetic */ ICronetEngineBuilder setUserAgent(String str) {
        this.mUserAgent = str;
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* bridge */ /* synthetic */ ICronetEngineBuilder enableSdch(boolean z) {
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder setLibraryLoader(CronetEngine.Builder.LibraryLoader libraryLoader) {
        return this;
    }
}
