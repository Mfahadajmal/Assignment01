package org.chromium.net.urlconnection;

import _COROUTINE._BOUNDARY;
import android.util.Pair;
import j$.util.DesugarCollections;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetHttpURLConnection extends HttpURLConnection {
    private final CronetEngine mCronetEngine;
    public IOException mException;
    private boolean mHasResponseHeadersOrCompleted;
    public final CronetInputStream mInputStream;
    public final MessageLoop mMessageLoop;
    public boolean mOnRedirectCalled;
    public CronetOutputStream mOutputStream;
    public UrlRequest mRequest;
    private final List mRequestHeaders;
    private List mResponseHeadersList;
    private Map mResponseHeadersMap;
    public UrlResponseInfo mResponseInfo;
    private int mTrafficStatsTag;
    private boolean mTrafficStatsTagSet;
    private int mTrafficStatsUid;
    private boolean mTrafficStatsUidSet;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CronetUrlRequestCallback extends UrlRequest.Callback {
        public CronetUrlRequestCallback() {
        }

        private final void setResponseDataCompleted(IOException iOException) {
            CronetHttpURLConnection cronetHttpURLConnection = CronetHttpURLConnection.this;
            cronetHttpURLConnection.mException = iOException;
            CronetInputStream cronetInputStream = cronetHttpURLConnection.mInputStream;
            if (cronetInputStream != null) {
                cronetInputStream.mException = iOException;
                cronetInputStream.mResponseDataCompleted = true;
                cronetInputStream.mBuffer = null;
            }
            CronetOutputStream cronetOutputStream = cronetHttpURLConnection.mOutputStream;
            if (cronetOutputStream != null) {
                cronetOutputStream.mException = iOException;
                cronetOutputStream.mRequestCompleted = true;
            }
            cronetHttpURLConnection.mHasResponseHeadersOrCompleted = true;
            CronetHttpURLConnection.this.mMessageLoop.quit();
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public final void onCanceled(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            setResponseDataCompleted(new IOException("disconnect() called"));
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public final void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            if (cronetException != null) {
                CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
                setResponseDataCompleted(cronetException);
                return;
            }
            throw new IllegalStateException("Exception cannot be null in onFailed.");
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public final void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) {
            CronetHttpURLConnection cronetHttpURLConnection = CronetHttpURLConnection.this;
            cronetHttpURLConnection.mResponseInfo = urlResponseInfo;
            cronetHttpURLConnection.mMessageLoop.quit();
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public final void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) {
            CronetHttpURLConnection.this.mOnRedirectCalled = true;
            try {
                URL url = new URL(str);
                boolean equals = url.getProtocol().equals(CronetHttpURLConnection.this.url.getProtocol());
                if (CronetHttpURLConnection.this.instanceFollowRedirects) {
                    CronetHttpURLConnection.this.url = url;
                }
                if (CronetHttpURLConnection.this.instanceFollowRedirects && equals) {
                    CronetHttpURLConnection.this.mRequest.followRedirect();
                    return;
                }
            } catch (MalformedURLException unused) {
            }
            CronetHttpURLConnection cronetHttpURLConnection = CronetHttpURLConnection.this;
            cronetHttpURLConnection.mResponseInfo = urlResponseInfo;
            cronetHttpURLConnection.mRequest.cancel();
            setResponseDataCompleted(null);
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public final void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            CronetHttpURLConnection cronetHttpURLConnection = CronetHttpURLConnection.this;
            cronetHttpURLConnection.mResponseInfo = urlResponseInfo;
            cronetHttpURLConnection.mHasResponseHeadersOrCompleted = true;
            CronetHttpURLConnection.this.mMessageLoop.quit();
        }

        @Override // org.chromium.net.UrlRequest.Callback
        public final void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            setResponseDataCompleted(null);
        }
    }

    public CronetHttpURLConnection(URL url, CronetEngine cronetEngine) {
        super(url);
        this.mCronetEngine = cronetEngine;
        this.mMessageLoop = new MessageLoop();
        this.mInputStream = new CronetInputStream(this);
        this.mRequestHeaders = new ArrayList();
    }

    private final int findRequestProperty(String str) {
        for (int i = 0; i < this.mRequestHeaders.size(); i++) {
            if (((String) ((Pair) this.mRequestHeaders.get(i)).first).equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }

    private final Map getAllHeaders() {
        Map map = this.mResponseHeadersMap;
        if (map != null) {
            return map;
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Map.Entry entry : getAllHeadersAsList()) {
            ArrayList arrayList = new ArrayList();
            if (treeMap.containsKey(entry.getKey())) {
                arrayList.addAll((Collection) treeMap.get(entry.getKey()));
            }
            arrayList.add((String) entry.getValue());
            treeMap.put((String) entry.getKey(), DesugarCollections.unmodifiableList(arrayList));
        }
        Map unmodifiableMap = DesugarCollections.unmodifiableMap(treeMap);
        this.mResponseHeadersMap = unmodifiableMap;
        return unmodifiableMap;
    }

    private final List getAllHeadersAsList() {
        List list = this.mResponseHeadersList;
        if (list != null) {
            return list;
        }
        this.mResponseHeadersList = new ArrayList();
        for (Map.Entry<String, String> entry : this.mResponseInfo.getAllHeadersAsList()) {
            if (!entry.getKey().equalsIgnoreCase("Content-Encoding")) {
                this.mResponseHeadersList.add(new AbstractMap.SimpleImmutableEntry(entry));
            }
        }
        List unmodifiableList = DesugarCollections.unmodifiableList(this.mResponseHeadersList);
        this.mResponseHeadersList = unmodifiableList;
        return unmodifiableList;
    }

    private final Map.Entry getHeaderFieldEntry(int i) {
        try {
            getResponse();
            List allHeadersAsList = getAllHeadersAsList();
            if (i < allHeadersAsList.size()) {
                return (Map.Entry) allHeadersAsList.get(i);
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    private final void getResponse() {
        CronetOutputStream cronetOutputStream = this.mOutputStream;
        if (cronetOutputStream != null) {
            cronetOutputStream.checkReceivedEnoughContent();
            if (isChunkedUpload()) {
                this.mOutputStream.close();
            }
        }
        if (!this.mHasResponseHeadersOrCompleted) {
            startRequest();
            this.mMessageLoop.loop();
        }
        if (this.mHasResponseHeadersOrCompleted) {
            IOException iOException = this.mException;
            if (iOException == null) {
                if (this.mResponseInfo != null) {
                    return;
                } else {
                    throw new NullPointerException("Response info is null when there is no exception.");
                }
            }
            throw iOException;
        }
        throw new IllegalStateException("No response.");
    }

    private final boolean isChunkedUpload() {
        if (this.chunkLength > 0) {
            return true;
        }
        return false;
    }

    private final void setRequestPropertyInternal(String str, String str2, boolean z) {
        if (!this.connected) {
            int findRequestProperty = findRequestProperty(str);
            if (findRequestProperty >= 0) {
                if (z) {
                    this.mRequestHeaders.remove(findRequestProperty);
                } else {
                    throw new UnsupportedOperationException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Cannot add multiple headers of the same key, ", ". crbug.com/432719."));
                }
            }
            this.mRequestHeaders.add(Pair.create(str, str2));
            return;
        }
        throw new IllegalStateException("Cannot modify request property after connection is made.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b7, code lost:
    
        if (r5.mTrafficStatsTagSet != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d5, code lost:
    
        if (r5.mTrafficStatsUidSet != false) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void startRequest() {
        /*
            r5 = this;
            boolean r0 = r5.connected
            if (r0 == 0) goto L5
            return
        L5:
            org.chromium.net.CronetEngine r0 = r5.mCronetEngine
            java.net.URL r1 = r5.getURL()
            java.lang.String r1 = r1.toString()
            org.chromium.net.urlconnection.CronetHttpURLConnection$CronetUrlRequestCallback r2 = new org.chromium.net.urlconnection.CronetHttpURLConnection$CronetUrlRequestCallback
            r2.<init>()
            org.chromium.net.urlconnection.MessageLoop r3 = r5.mMessageLoop
            org.chromium.net.UrlRequest$Builder r0 = r0.newUrlRequestBuilder(r1, r2, r3)
            org.chromium.net.ExperimentalUrlRequest$Builder r0 = (org.chromium.net.ExperimentalUrlRequest.Builder) r0
            boolean r1 = r5.doOutput
            if (r1 == 0) goto L78
            java.lang.String r1 = r5.method
            java.lang.String r2 = "GET"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L2e
            java.lang.String r1 = "POST"
            r5.method = r1
        L2e:
            org.chromium.net.urlconnection.CronetOutputStream r1 = r5.mOutputStream
            java.lang.String r2 = "Content-Length"
            if (r1 == 0) goto L60
            org.chromium.net.urlconnection.MessageLoop r3 = r5.mMessageLoop
            org.chromium.net.UploadDataProvider r1 = r1.getUploadDataProvider()
            r0.setUploadDataProvider(r1, r3)
            java.lang.String r1 = r5.getRequestProperty(r2)
            if (r1 != 0) goto L5a
            boolean r1 = r5.isChunkedUpload()
            if (r1 != 0) goto L5a
            org.chromium.net.urlconnection.CronetOutputStream r1 = r5.mOutputStream
            org.chromium.net.UploadDataProvider r1 = r1.getUploadDataProvider()
            long r3 = r1.getLength()
            java.lang.String r1 = java.lang.Long.toString(r3)
            r5.addRequestProperty(r2, r1)
        L5a:
            org.chromium.net.urlconnection.CronetOutputStream r1 = r5.mOutputStream
            r1.setConnected()
            goto L6b
        L60:
            java.lang.String r1 = r5.getRequestProperty(r2)
            if (r1 != 0) goto L6b
            java.lang.String r1 = "0"
            r5.addRequestProperty(r2, r1)
        L6b:
            java.lang.String r1 = "Content-Type"
            java.lang.String r2 = r5.getRequestProperty(r1)
            if (r2 != 0) goto L78
            java.lang.String r2 = "application/x-www-form-urlencoded"
            r5.addRequestProperty(r1, r2)
        L78:
            java.util.List r1 = r5.mRequestHeaders
            java.util.Iterator r1 = r1.iterator()
        L7e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L96
            java.lang.Object r2 = r1.next()
            android.util.Pair r2 = (android.util.Pair) r2
            java.lang.Object r3 = r2.first
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.second
            java.lang.String r2 = (java.lang.String) r2
            r0.addHeader(r3, r2)
            goto L7e
        L96:
            boolean r1 = r5.getUseCaches()
            if (r1 != 0) goto L9f
            r0.disableCache()
        L9f:
            java.lang.String r1 = r5.method
            r0.setHttpMethod(r1)
            boolean r1 = r5.mTrafficStatsTagSet
            r2 = -1
            r3 = 1
            if (r1 == 0) goto Lab
            goto Lb9
        Lab:
            int r1 = android.net.TrafficStats.getThreadStatsTag()
            if (r1 == r2) goto Lb5
            r5.mTrafficStatsTag = r1
            r5.mTrafficStatsTagSet = r3
        Lb5:
            boolean r1 = r5.mTrafficStatsTagSet
            if (r1 == 0) goto Lbe
        Lb9:
            int r1 = r5.mTrafficStatsTag
            r0.setTrafficStatsTag(r1)
        Lbe:
            boolean r1 = r5.mTrafficStatsUidSet
            if (r1 == 0) goto Lc3
            goto Ld7
        Lc3:
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r1 < r4) goto Ldc
            int r1 = org.chromium.base.RadioUtils$$ExternalSyntheticApiModelOutline0.m()
            if (r1 == r2) goto Ld3
            r5.mTrafficStatsUid = r1
            r5.mTrafficStatsUidSet = r3
        Ld3:
            boolean r1 = r5.mTrafficStatsUidSet
            if (r1 == 0) goto Ldc
        Ld7:
            int r1 = r5.mTrafficStatsUid
            r0.setTrafficStatsUid(r1)
        Ldc:
            org.chromium.net.ExperimentalUrlRequest r0 = r0.build()
            r5.mRequest = r0
            r0.start()
            r5.connected = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.urlconnection.CronetHttpURLConnection.startRequest():void");
    }

    @Override // java.net.URLConnection
    public final void addRequestProperty(String str, String str2) {
        setRequestPropertyInternal(str, str2, false);
    }

    @Override // java.net.URLConnection
    public final void connect() {
        getOutputStream();
        startRequest();
    }

    @Override // java.net.HttpURLConnection
    public final void disconnect() {
        if (this.connected) {
            this.mRequest.cancel();
        }
    }

    @Override // java.net.HttpURLConnection
    public final InputStream getErrorStream() {
        try {
            getResponse();
            if (this.mResponseInfo.getHttpStatusCode() >= 400) {
                return this.mInputStream;
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final String getHeaderField(int i) {
        Map.Entry headerFieldEntry = getHeaderFieldEntry(i);
        if (headerFieldEntry == null) {
            return null;
        }
        return (String) headerFieldEntry.getValue();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final String getHeaderFieldKey(int i) {
        Map.Entry headerFieldEntry = getHeaderFieldEntry(i);
        if (headerFieldEntry == null) {
            return null;
        }
        return (String) headerFieldEntry.getKey();
    }

    @Override // java.net.URLConnection
    public final Map getHeaderFields() {
        try {
            getResponse();
            return getAllHeaders();
        } catch (IOException unused) {
            return Collections.emptyMap();
        }
    }

    @Override // java.net.URLConnection
    public final InputStream getInputStream() {
        getResponse();
        if (!this.instanceFollowRedirects && this.mOnRedirectCalled) {
            throw new IOException("Cannot read response body of a redirect.");
        }
        if (this.mResponseInfo.getHttpStatusCode() < 400) {
            return this.mInputStream;
        }
        throw new FileNotFoundException(this.url.toString());
    }

    @Override // java.net.URLConnection
    public final OutputStream getOutputStream() {
        if (this.mOutputStream == null && this.doOutput) {
            if (!this.connected) {
                if (isChunkedUpload()) {
                    this.mOutputStream = new CronetChunkedOutputStream(this.chunkLength, this.mMessageLoop);
                    startRequest();
                } else {
                    long j = this.fixedContentLength;
                    if (this.fixedContentLengthLong != -1) {
                        j = this.fixedContentLengthLong;
                    }
                    if (j != -1) {
                        this.mOutputStream = new CronetFixedModeOutputStream(j, this.mMessageLoop);
                        startRequest();
                    } else {
                        String requestProperty = getRequestProperty("Content-Length");
                        if (requestProperty == null) {
                            this.mOutputStream = new CronetBufferedOutputStream();
                        } else {
                            this.mOutputStream = new CronetBufferedOutputStream(Long.parseLong(requestProperty));
                        }
                    }
                }
            } else {
                throw new ProtocolException("Cannot write to OutputStream after receiving response.");
            }
        }
        return this.mOutputStream;
    }

    @Override // java.net.URLConnection
    public final Map getRequestProperties() {
        if (!this.connected) {
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (Pair pair : this.mRequestHeaders) {
                if (!treeMap.containsKey(pair.first)) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add((String) pair.second);
                    treeMap.put((String) pair.first, DesugarCollections.unmodifiableList(arrayList));
                } else {
                    throw new IllegalStateException("Should not have multiple values.");
                }
            }
            return DesugarCollections.unmodifiableMap(treeMap);
        }
        throw new IllegalStateException("Cannot access request headers after connection is set.");
    }

    @Override // java.net.URLConnection
    public final String getRequestProperty(String str) {
        int findRequestProperty = findRequestProperty(str);
        if (findRequestProperty >= 0) {
            return (String) ((Pair) this.mRequestHeaders.get(findRequestProperty)).second;
        }
        return null;
    }

    @Override // java.net.HttpURLConnection
    public final int getResponseCode() {
        getResponse();
        return this.mResponseInfo.getHttpStatusCode();
    }

    @Override // java.net.HttpURLConnection
    public final String getResponseMessage() {
        getResponse();
        return this.mResponseInfo.getHttpStatusText();
    }

    @Override // java.net.URLConnection
    public final void setRequestProperty(String str, String str2) {
        setRequestPropertyInternal(str, str2, true);
    }

    @Override // java.net.HttpURLConnection
    public final boolean usingProxy() {
        return false;
    }

    @Override // java.net.URLConnection
    public final String getHeaderField(String str) {
        try {
            getResponse();
            Map allHeaders = getAllHeaders();
            if (!allHeaders.containsKey(str)) {
                return null;
            }
            return (String) ((List) allHeaders.get(str)).get(r3.size() - 1);
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public final void setConnectTimeout(int i) {
    }
}
