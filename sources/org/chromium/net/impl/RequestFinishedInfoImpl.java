package org.chromium.net.impl;

import java.util.Collection;
import java.util.Collections;
import org.chromium.net.CronetException;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UrlResponseInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RequestFinishedInfoImpl extends RequestFinishedInfo {
    private final Collection mAnnotations;
    private final CronetException mException;
    private final int mFinishedReason;
    private final RequestFinishedInfo.Metrics mMetrics;
    private final UrlResponseInfo mResponseInfo;
    private final String mUrl;

    public RequestFinishedInfoImpl(String str, Collection collection, RequestFinishedInfo.Metrics metrics, int i, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
        this.mUrl = str;
        this.mAnnotations = collection;
        this.mMetrics = metrics;
        this.mFinishedReason = i;
        this.mResponseInfo = urlResponseInfo;
        this.mException = cronetException;
    }

    @Override // org.chromium.net.RequestFinishedInfo
    public final Collection<Object> getAnnotations() {
        Collection<Object> collection = this.mAnnotations;
        if (collection == null) {
            return Collections.emptyList();
        }
        return collection;
    }

    @Override // org.chromium.net.RequestFinishedInfo
    public final CronetException getException() {
        return this.mException;
    }

    @Override // org.chromium.net.RequestFinishedInfo
    public final int getFinishedReason() {
        return this.mFinishedReason;
    }

    @Override // org.chromium.net.RequestFinishedInfo
    public final RequestFinishedInfo.Metrics getMetrics() {
        return this.mMetrics;
    }

    @Override // org.chromium.net.RequestFinishedInfo
    public final UrlResponseInfo getResponseInfo() {
        return this.mResponseInfo;
    }

    @Override // org.chromium.net.RequestFinishedInfo
    public final String getUrl() {
        return this.mUrl;
    }
}
