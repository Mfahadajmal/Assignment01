package org.chromium.net.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executor;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UrlRequestBuilderImpl extends ExperimentalUrlRequest.Builder {
    private boolean mAllowDirectExecutor;
    private final UrlRequest.Callback mCallback;
    private final CronetEngineBase mCronetEngine;
    private boolean mDisableCache;
    private boolean mDisableConnectionMigration;
    private final Executor mExecutor;
    private String mMethod;
    private Collection mRequestAnnotations;
    private RequestFinishedInfo.Listener mRequestFinishedListener;
    private int mTrafficStatsTag;
    private boolean mTrafficStatsTagSet;
    private int mTrafficStatsUid;
    private boolean mTrafficStatsUidSet;
    private UploadDataProvider mUploadDataProvider;
    private Executor mUploadDataProviderExecutor;
    private final String mUrl;
    private final ArrayList mRequestHeaders = new ArrayList();
    private int mPriority = 3;
    private long mNetworkHandle = -1;
    private int mIdempotency = 0;

    public UrlRequestBuilderImpl(String str, UrlRequest.Callback callback, Executor executor, CronetEngineBase cronetEngineBase) {
        str.getClass();
        this.mUrl = str;
        callback.getClass();
        this.mCallback = callback;
        executor.getClass();
        this.mExecutor = executor;
        this.mCronetEngine = cronetEngineBase;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder addHeader(String str, String str2) {
        addHeader$ar$ds$597c4390_0(str, str2);
        return this;
    }

    public final void addHeader$ar$ds$597c4390_0(String str, String str2) {
        str.getClass();
        str2.getClass();
        if ("Accept-Encoding".equalsIgnoreCase(str)) {
            return;
        }
        this.mRequestHeaders.add(new AbstractMap.SimpleEntry(str, str2));
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder addRequestAnnotation(Object obj) {
        addRequestAnnotation$ar$ds(obj);
        return this;
    }

    public final void addRequestAnnotation$ar$ds(Object obj) {
        obj.getClass();
        if (this.mRequestAnnotations == null) {
            this.mRequestAnnotations = new ArrayList();
        }
        this.mRequestAnnotations.add(obj);
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder allowDirectExecutor() {
        allowDirectExecutor$ar$ds();
        return this;
    }

    public final void allowDirectExecutor$ar$ds() {
        this.mAllowDirectExecutor = true;
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public final /* synthetic */ UrlRequest.Builder bindToNetwork(long j) {
        this.mNetworkHandle = j;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final ExperimentalUrlRequest build() {
        int i = this.mPriority;
        Collection collection = this.mRequestAnnotations;
        boolean z = this.mDisableCache;
        boolean z2 = this.mDisableConnectionMigration;
        boolean z3 = this.mAllowDirectExecutor;
        boolean z4 = this.mTrafficStatsTagSet;
        int i2 = this.mTrafficStatsTag;
        boolean z5 = this.mTrafficStatsUidSet;
        int i3 = this.mTrafficStatsUid;
        RequestFinishedInfo.Listener listener = this.mRequestFinishedListener;
        int i4 = this.mIdempotency;
        long j = this.mNetworkHandle;
        String str = this.mMethod;
        if (str == null) {
            str = "GET";
        }
        String str2 = str;
        UploadDataProvider uploadDataProvider = this.mUploadDataProvider;
        Executor executor = this.mUploadDataProviderExecutor;
        return this.mCronetEngine.createRequest(this.mUrl, this.mCallback, this.mExecutor, i, collection, z, z2, z3, z4, i2, z5, i3, listener, i4, j, str2, this.mRequestHeaders, uploadDataProvider, executor);
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder disableCache() {
        disableCache$ar$ds();
        return this;
    }

    public final void disableCache$ar$ds() {
        this.mDisableCache = true;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder disableConnectionMigration() {
        this.mDisableConnectionMigration = true;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final ExperimentalUrlRequest.Builder setHttpMethod(String str) {
        str.getClass();
        this.mMethod = str;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder
    public final /* synthetic */ ExperimentalUrlRequest.Builder setIdempotency(int i) {
        this.mIdempotency = i;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* synthetic */ ExperimentalUrlRequest.Builder setPriority(int i) {
        this.mPriority = i;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* synthetic */ ExperimentalUrlRequest.Builder setRequestFinishedListener(RequestFinishedInfo.Listener listener) {
        this.mRequestFinishedListener = listener;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder setTrafficStatsTag(int i) {
        setTrafficStatsTag$ar$ds(i);
        return this;
    }

    public final void setTrafficStatsTag$ar$ds(int i) {
        this.mTrafficStatsTagSet = true;
        this.mTrafficStatsTag = i;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder setTrafficStatsUid(int i) {
        setTrafficStatsUid$ar$ds(i);
        return this;
    }

    public final void setTrafficStatsUid$ar$ds(int i) {
        this.mTrafficStatsUidSet = true;
        this.mTrafficStatsUid = i;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ ExperimentalUrlRequest.Builder setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor) {
        setUploadDataProvider$ar$ds(uploadDataProvider, executor);
        return this;
    }

    public final void setUploadDataProvider$ar$ds(UploadDataProvider uploadDataProvider, Executor executor) {
        uploadDataProvider.getClass();
        this.mUploadDataProvider = uploadDataProvider;
        executor.getClass();
        this.mUploadDataProviderExecutor = executor;
        if (this.mMethod == null) {
            this.mMethod = "POST";
        }
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ UrlRequest.Builder addHeader(String str, String str2) {
        addHeader$ar$ds$597c4390_0(str, str2);
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ UrlRequest.Builder addRequestAnnotation(Object obj) {
        addRequestAnnotation$ar$ds(obj);
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ UrlRequest.Builder allowDirectExecutor() {
        allowDirectExecutor$ar$ds();
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ UrlRequest.Builder disableCache() {
        disableCache$ar$ds();
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* synthetic */ UrlRequest.Builder setPriority(int i) {
        this.mPriority = i;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* synthetic */ UrlRequest.Builder setRequestFinishedListener(RequestFinishedInfo.Listener listener) {
        this.mRequestFinishedListener = listener;
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ UrlRequest.Builder setTrafficStatsTag(int i) {
        setTrafficStatsTag$ar$ds(i);
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ UrlRequest.Builder setTrafficStatsUid(int i) {
        setTrafficStatsUid$ar$ds(i);
        return this;
    }

    @Override // org.chromium.net.ExperimentalUrlRequest.Builder, org.chromium.net.UrlRequest.Builder
    public final /* bridge */ /* synthetic */ UrlRequest.Builder setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor) {
        setUploadDataProvider$ar$ds(uploadDataProvider, executor);
        return this;
    }
}
