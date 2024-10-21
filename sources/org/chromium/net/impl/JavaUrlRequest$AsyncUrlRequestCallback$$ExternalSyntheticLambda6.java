package org.chromium.net.impl;

import java.nio.ByteBuffer;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.impl.JavaUrlRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6 implements JavaUrlRequestUtils$CheckedRunnable {
    public final /* synthetic */ Object JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6$ar$f$2;
    public final /* synthetic */ JavaUrlRequest.AsyncUrlRequestCallback f$0;
    public final /* synthetic */ UrlResponseInfo f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6(JavaUrlRequest.AsyncUrlRequestCallback asyncUrlRequestCallback, UrlResponseInfo urlResponseInfo, Object obj, int i) {
        this.switching_field = i;
        this.f$0 = asyncUrlRequestCallback;
        this.f$1 = urlResponseInfo;
        this.JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6$ar$f$2 = obj;
    }

    @Override // org.chromium.net.impl.JavaUrlRequestUtils$CheckedRunnable
    public final void run() {
        if (this.switching_field != 0) {
            JavaUrlRequest.AsyncUrlRequestCallback asyncUrlRequestCallback = this.f$0;
            if (JavaUrlRequest.this.mState.compareAndSet(5, 4)) {
                Object obj = this.JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6$ar$f$2;
                asyncUrlRequestCallback.mCallback.onReadCompleted(JavaUrlRequest.this, this.f$1, (ByteBuffer) obj);
                return;
            }
            return;
        }
        JavaUrlRequest.AsyncUrlRequestCallback asyncUrlRequestCallback2 = this.f$0;
        Object obj2 = this.JavaUrlRequest$AsyncUrlRequestCallback$$ExternalSyntheticLambda6$ar$f$2;
        asyncUrlRequestCallback2.mCallback.onRedirectReceived(JavaUrlRequest.this, this.f$1, (String) obj2);
    }
}
