package com.google.android.libraries.mdi.download.debug.common.filegroups;

import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import com.google.common.base.Stopwatch;
import io.grpc.internal.DnsNameResolver;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.impl.CronetBidirectionalStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ boolean f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0(ScreenCaptureController.ScreenshotAuthorizationReceiver screenshotAuthorizationReceiver, boolean z, int i) {
        this.switching_field = i;
        this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0 = screenshotAuthorizationReceiver;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    synchronized (((CronetBidirectionalStream) this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0).mNativeStreamLock) {
                        if (((CronetBidirectionalStream) this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0).isDoneLocked()) {
                            return;
                        }
                        Object obj = this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
                        ((CronetBidirectionalStream) obj).mRequestHeadersSent = this.f$1;
                        ((CronetBidirectionalStream) obj).mReadState = 2;
                        try {
                            if (!CronetBidirectionalStream.doesMethodAllowWriteData(((CronetBidirectionalStream) obj).mInitialMethod)) {
                                Object obj2 = this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
                                if (((CronetBidirectionalStream) obj2).mRequestHeadersSent) {
                                    ((CronetBidirectionalStream) obj2).mWriteState = 10;
                                    Object obj3 = this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
                                    ((CronetBidirectionalStream) obj3).mCallback.onStreamReady((BidirectionalStream) obj3);
                                    return;
                                }
                            }
                            Object obj32 = this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
                            ((CronetBidirectionalStream) obj32).mCallback.onStreamReady((BidirectionalStream) obj32);
                            return;
                        } catch (Exception e) {
                            ((CronetBidirectionalStream) this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0).onNonfinalCallbackException(e);
                            return;
                        }
                        ((CronetBidirectionalStream) this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0).mWriteState = 8;
                    }
                } else {
                    if (this.f$1) {
                        DnsNameResolver dnsNameResolver = DnsNameResolver.this;
                        dnsNameResolver.resolved = true;
                        if (dnsNameResolver.cacheTtlNanos > 0) {
                            Stopwatch stopwatch = dnsNameResolver.stopwatch;
                            stopwatch.reset$ar$ds();
                            stopwatch.start$ar$ds$db96ddcc_0();
                        }
                    }
                    DnsNameResolver.this.resolving = false;
                }
            } else {
                ((ScreenCaptureController.ScreenshotAuthorizationReceiver) this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0).listener.onAuthorizationFinished(this.f$1);
            }
        } else {
            MddDebugListFragmentUiProviderImpl mddDebugListFragmentUiProviderImpl = (MddDebugListFragmentUiProviderImpl) this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
            mddDebugListFragmentUiProviderImpl.recyclerView.setVisibility(0);
            mddDebugListFragmentUiProviderImpl.listStatusTextView.setVisibility(8);
        }
    }

    public /* synthetic */ MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0(MddDebugListFragmentUiProviderImpl mddDebugListFragmentUiProviderImpl, int i) {
        this.switching_field = i;
        this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0 = mddDebugListFragmentUiProviderImpl;
        this.f$1 = true;
    }

    public MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0(DnsNameResolver.Resolve resolve, boolean z, int i) {
        this.switching_field = i;
        this.f$1 = z;
        this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0 = resolve;
    }

    public MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0(CronetBidirectionalStream cronetBidirectionalStream, boolean z, int i) {
        this.switching_field = i;
        this.MddDebugListFragmentUiProviderImpl$$ExternalSyntheticLambda0$ar$f$0 = cronetBidirectionalStream;
        this.f$1 = z;
    }
}
