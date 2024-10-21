package org.chromium.net.impl;

import android.net.ConnectivityManager;
import android.net.Network;
import io.grpc.internal.RetriableStream;
import j$.util.DesugarCollections;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;
import org.chromium.net.impl.CronetUrlRequest;
import org.chromium.net.impl.JavaUrlRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class JavaUploadDataSinkBase$$ExternalSyntheticLambda3 implements JavaUrlRequestUtils$CheckedRunnable {
    public final /* synthetic */ Object JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ JavaUploadDataSinkBase$$ExternalSyntheticLambda3(Object obj, int i) {
        this.switching_field = i;
        this.JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0 = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2, types: [char[]] */
    @Override // org.chromium.net.impl.JavaUrlRequestUtils$CheckedRunnable
    public final void run() {
        List<String> list;
        int i = this.switching_field;
        boolean z = false;
        if (i != 0) {
            if (i != 1) {
                Network network = 0;
                ReadableByteChannel readableByteChannel = null;
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            JavaUrlRequest.AsyncUrlRequestCallback asyncUrlRequestCallback = (JavaUrlRequest.AsyncUrlRequestCallback) this.JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0;
                            if (JavaUrlRequest.this.mState.compareAndSet(1, 4)) {
                                VersionSafeCallbacks$UrlRequestCallback versionSafeCallbacks$UrlRequestCallback = asyncUrlRequestCallback.mCallback;
                                JavaUrlRequest javaUrlRequest = JavaUrlRequest.this;
                                versionSafeCallbacks$UrlRequestCallback.onResponseStarted(javaUrlRequest, javaUrlRequest.mUrlResponseInfo);
                                return;
                            }
                            return;
                        }
                        String str = JavaUrlRequest.TAG;
                        ((UploadDataProvider) this.JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0).close();
                        return;
                    }
                    Object obj = this.JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0;
                    JavaUrlRequest javaUrlRequest2 = (JavaUrlRequest) obj;
                    if (javaUrlRequest2.mCurrentUrlConnection == null) {
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    String str2 = "http/1.1";
                    int i2 = 0;
                    while (true) {
                        String headerFieldKey = javaUrlRequest2.mCurrentUrlConnection.getHeaderFieldKey(i2);
                        if (headerFieldKey == null) {
                            break;
                        }
                        if ("X-Android-Selected-Transport".equalsIgnoreCase(headerFieldKey)) {
                            str2 = javaUrlRequest2.mCurrentUrlConnection.getHeaderField(i2);
                        }
                        if (!headerFieldKey.startsWith("X-Android")) {
                            arrayList.add(new AbstractMap.SimpleEntry(headerFieldKey, javaUrlRequest2.mCurrentUrlConnection.getHeaderField(i2)));
                        }
                        i2++;
                    }
                    int responseCode = javaUrlRequest2.mCurrentUrlConnection.getResponseCode();
                    javaUrlRequest2.mUrlResponseInfo = new UrlResponseInfoImpl(new ArrayList(javaUrlRequest2.mUrlChain), responseCode, javaUrlRequest2.mCurrentUrlConnection.getResponseMessage(), DesugarCollections.unmodifiableList(arrayList), false, str2, "", 0L);
                    if (responseCode >= 300 && responseCode < 400 && (list = javaUrlRequest2.mUrlResponseInfo.getAllHeaders().get("location")) != null) {
                        javaUrlRequest2.transitionStates(1, 2, new RetriableStream.Sublistener.AnonymousClass4(obj, list.get(0), 14, (char[]) network));
                        return;
                    }
                    javaUrlRequest2.fireCloseUploadDataProvider();
                    if (responseCode >= 400) {
                        InputStream errorStream = javaUrlRequest2.mCurrentUrlConnection.getErrorStream();
                        if (errorStream != null) {
                            readableByteChannel = InputStreamChannel.wrap(errorStream);
                        }
                        javaUrlRequest2.mResponseChannel = readableByteChannel;
                        javaUrlRequest2.mCallbackAsync.onResponseStarted$ar$ds();
                        return;
                    }
                    javaUrlRequest2.mResponseChannel = InputStreamChannel.wrap(javaUrlRequest2.mCurrentUrlConnection.getInputStream());
                    javaUrlRequest2.mCallbackAsync.onResponseStarted$ar$ds();
                    return;
                }
                JavaUrlRequest javaUrlRequest3 = (JavaUrlRequest) this.JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0;
                if (javaUrlRequest3.mState.get() == 8) {
                    return;
                }
                URL url = new URL(javaUrlRequest3.mCurrentUrl);
                HttpURLConnection httpURLConnection = javaUrlRequest3.mCurrentUrlConnection;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                    javaUrlRequest3.mCurrentUrlConnection = null;
                }
                long j = javaUrlRequest3.mNetworkHandle;
                if (j != -1) {
                    Network[] allNetworks = ((ConnectivityManager) javaUrlRequest3.mEngine.mContext.getSystemService("connectivity")).getAllNetworks();
                    int length = allNetworks.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            break;
                        }
                        Network network2 = allNetworks[i3];
                        if (network2.getNetworkHandle() == j) {
                            network = network2;
                            break;
                        }
                        i3++;
                    }
                    if (network != 0) {
                        javaUrlRequest3.mCurrentUrlConnection = (HttpURLConnection) network.openConnection(url);
                    } else {
                        throw new NetworkExceptionImpl("Network bound to request not found", 9, -4);
                    }
                } else {
                    javaUrlRequest3.mCurrentUrlConnection = (HttpURLConnection) url.openConnection();
                }
                javaUrlRequest3.mCurrentUrlConnection.setInstanceFollowRedirects(false);
                if (!javaUrlRequest3.mRequestHeaders.containsKey("User-Agent")) {
                    javaUrlRequest3.mRequestHeaders.put("User-Agent", javaUrlRequest3.mUserAgent);
                }
                for (Map.Entry entry : javaUrlRequest3.mRequestHeaders.entrySet()) {
                    javaUrlRequest3.mCurrentUrlConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
                javaUrlRequest3.mCurrentUrlConnection.setRequestMethod(javaUrlRequest3.mInitialMethod);
                VersionSafeCallbacks$UploadDataProviderWrapper versionSafeCallbacks$UploadDataProviderWrapper = javaUrlRequest3.mUploadDataProvider;
                if (versionSafeCallbacks$UploadDataProviderWrapper != null) {
                    javaUrlRequest3.mOutputStreamDataSink$ar$class_merging = new JavaUploadDataSinkBase(javaUrlRequest3, javaUrlRequest3.mUploadExecutor, javaUrlRequest3.mExecutor, javaUrlRequest3.mCurrentUrlConnection, versionSafeCallbacks$UploadDataProviderWrapper);
                    JavaUploadDataSinkBase javaUploadDataSinkBase = javaUrlRequest3.mOutputStreamDataSink$ar$class_merging;
                    if (javaUrlRequest3.mUrlChain.size() == 1) {
                        z = true;
                    }
                    javaUploadDataSinkBase.executeOnUploadExecutor(new JavaUploadDataSinkBase$$ExternalSyntheticLambda4(javaUploadDataSinkBase, z, 1));
                    return;
                }
                javaUrlRequest3.mAdditionalStatusDetails = 10;
                javaUrlRequest3.mCurrentUrlConnection.connect();
                javaUrlRequest3.fireGetHeaders();
                return;
            }
            Object obj2 = this.JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0;
            JavaUploadDataSinkBase javaUploadDataSinkBase2 = (JavaUploadDataSinkBase) obj2;
            javaUploadDataSinkBase2.mUploadProvider.read((UploadDataSink) obj2, javaUploadDataSinkBase2.mBuffer);
            javaUploadDataSinkBase2.mExecutor.execute(new CronetUrlRequest.AnonymousClass8(obj2, 2));
            return;
        }
        JavaUploadDataSinkBase javaUploadDataSinkBase3 = (JavaUploadDataSinkBase) this.JavaUploadDataSinkBase$$ExternalSyntheticLambda3$ar$f$0;
        javaUploadDataSinkBase3.initializeRead();
        javaUploadDataSinkBase3.mSinkState.set(0);
        javaUploadDataSinkBase3.readFromProvider();
    }
}
