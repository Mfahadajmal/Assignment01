package org.chromium.net.impl;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import org.chromium.net.UrlResponseInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UrlResponseInfoImpl extends UrlResponseInfo {
    private final HeaderBlockImpl mHeaders;
    public final int mHttpStatusCode;
    private final String mHttpStatusText;
    public final String mNegotiatedProtocol;
    private final String mProxyServer;
    private final AtomicLong mReceivedByteCount;
    private final List mResponseInfoUrlChain;
    public final boolean mWasCached;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HeaderBlockImpl extends UrlResponseInfo.HeaderBlock {
        public final List mAllHeadersList;
        public Map mHeadersMap;

        public HeaderBlockImpl(List list) {
            this.mAllHeadersList = list;
        }

        @Override // org.chromium.net.UrlResponseInfo.HeaderBlock
        public final List<Map.Entry<String, String>> getAsList() {
            return this.mAllHeadersList;
        }

        @Override // org.chromium.net.UrlResponseInfo.HeaderBlock
        public final Map<String, List<String>> getAsMap() {
            Map<String, List<String>> map = this.mHeadersMap;
            if (map != null) {
                return map;
            }
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (Map.Entry entry : this.mAllHeadersList) {
                ArrayList arrayList = new ArrayList();
                if (treeMap.containsKey(entry.getKey())) {
                    arrayList.addAll((Collection) treeMap.get(entry.getKey()));
                }
                arrayList.add((String) entry.getValue());
                treeMap.put((String) entry.getKey(), DesugarCollections.unmodifiableList(arrayList));
            }
            Map<String, List<String>> unmodifiableMap = DesugarCollections.unmodifiableMap(treeMap);
            this.mHeadersMap = unmodifiableMap;
            return unmodifiableMap;
        }
    }

    public UrlResponseInfoImpl(List list, int i, String str, List list2, boolean z, String str2, String str3, long j) {
        this.mResponseInfoUrlChain = DesugarCollections.unmodifiableList(list);
        this.mHttpStatusCode = i;
        this.mHttpStatusText = str;
        this.mHeaders = new HeaderBlockImpl(DesugarCollections.unmodifiableList(list2));
        this.mWasCached = z;
        this.mNegotiatedProtocol = str2;
        this.mProxyServer = str3;
        this.mReceivedByteCount = new AtomicLong(j);
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final Map<String, List<String>> getAllHeaders() {
        HeaderBlockImpl headerBlockImpl = this.mHeaders;
        Map<String, List<String>> map = headerBlockImpl.mHeadersMap;
        if (map == null) {
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (Map.Entry entry : headerBlockImpl.mAllHeadersList) {
                ArrayList arrayList = new ArrayList();
                if (treeMap.containsKey(entry.getKey())) {
                    arrayList.addAll((Collection) treeMap.get(entry.getKey()));
                }
                arrayList.add((String) entry.getValue());
                treeMap.put((String) entry.getKey(), DesugarCollections.unmodifiableList(arrayList));
            }
            headerBlockImpl.mHeadersMap = DesugarCollections.unmodifiableMap(treeMap);
            return headerBlockImpl.mHeadersMap;
        }
        return map;
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final List<Map.Entry<String, String>> getAllHeadersAsList() {
        return this.mHeaders.mAllHeadersList;
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final int getHttpStatusCode() {
        return this.mHttpStatusCode;
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final String getHttpStatusText() {
        return this.mHttpStatusText;
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final String getNegotiatedProtocol() {
        return this.mNegotiatedProtocol;
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final String getProxyServer() {
        return this.mProxyServer;
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final long getReceivedByteCount() {
        return this.mReceivedByteCount.get();
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final String getUrl() {
        return (String) this.mResponseInfoUrlChain.get(this.mResponseInfoUrlChain.size() - 1);
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final List<String> getUrlChain() {
        return this.mResponseInfoUrlChain;
    }

    public final void setReceivedByteCount(long j) {
        this.mReceivedByteCount.set(j);
    }

    public final String toString() {
        return String.format(Locale.ROOT, "UrlResponseInfo@[%s][%s]: urlChain = %s, httpStatus = %d %s, headers = %s, wasCached = %b, negotiatedProtocol = %s, proxyServer= %s, receivedByteCount = %d", Integer.toHexString(System.identityHashCode(this)), (String) this.mResponseInfoUrlChain.get(this.mResponseInfoUrlChain.size() - 1), this.mResponseInfoUrlChain.toString(), Integer.valueOf(this.mHttpStatusCode), this.mHttpStatusText, getAllHeadersAsList().toString(), Boolean.valueOf(this.mWasCached), this.mNegotiatedProtocol, this.mProxyServer, Long.valueOf(this.mReceivedByteCount.get()));
    }

    @Override // org.chromium.net.UrlResponseInfo
    public final boolean wasCached() {
        return this.mWasCached;
    }
}
