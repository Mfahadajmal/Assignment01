package com.google.android.libraries.surveys.internal.view;

import android.view.View;
import com.google.android.libraries.surveys.internal.view.RatingView;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.frameworks.client.data.android.impl.TransportChannel;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCall;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.DelayedStream;
import org.chromium.net.NetworkChangeNotifierAutoDetect;
import org.chromium.net.impl.VersionSafeCallbacks$UrlRequestStatusListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class RatingView$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ Object RatingView$$ExternalSyntheticLambda5$ar$f$0;
    public final /* synthetic */ int f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ RatingView$$ExternalSyntheticLambda5(Object obj, int i, int i2) {
        this.switching_field = i2;
        this.RatingView$$ExternalSyntheticLambda5$ar$f$0 = obj;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 4;
        switch (this.switching_field) {
            case 0:
                RatingView ratingView = (RatingView) this.RatingView$$ExternalSyntheticLambda5$ar$f$0;
                RatingView.OnRatingClickListener onRatingClickListener = ratingView.onRatingClickListener;
                if (onRatingClickListener != null) {
                    onRatingClickListener.onClickRating(this.f$1);
                    ratingView.onRatingClickListener = null;
                    return;
                }
                return;
            case 1:
                RatingView ratingView2 = (RatingView) this.RatingView$$ExternalSyntheticLambda5$ar$f$0;
                RatingView.OnRatingClickListener onRatingClickListener2 = ratingView2.onRatingClickListener;
                if (onRatingClickListener2 != null) {
                    onRatingClickListener2.onClickRating(this.f$1);
                    ratingView2.onRatingClickListener = null;
                    return;
                }
                return;
            case 2:
                ((MaterialCalendar) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).recyclerView.smoothScrollToPosition(this.f$1);
                return;
            case 3:
                ((NavigationBarItemView) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).updateActiveIndicatorLayoutParams(this.f$1);
                return;
            case 4:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.RatingView$$ExternalSyntheticLambda5$ar$f$0;
                View view = (View) sideSheetBehavior.viewRef.get();
                if (view != null) {
                    sideSheetBehavior.startSettling(view, this.f$1, false);
                    return;
                }
                return;
            case 5:
                ((TransportChannel.EnqueueingClientCall) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).delegate.request(this.f$1);
                return;
            case 6:
                AsyncInterceptorsClientCall asyncInterceptorsClientCall = (AsyncInterceptorsClientCall) this.RatingView$$ExternalSyntheticLambda5$ar$f$0;
                if (!asyncInterceptorsClientCall.aborted) {
                    int i2 = this.f$1;
                    if (asyncInterceptorsClientCall.state.phase$ar$edu == 4) {
                        asyncInterceptorsClientCall.delegate.request(i2);
                        return;
                    } else {
                        asyncInterceptorsClientCall.preStartRequestCount += i2;
                        return;
                    }
                }
                return;
            case 7:
                ((DelayedClientCall) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).realCall.request(this.f$1);
                return;
            case 8:
                ((DelayedStream) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).realStream.request(this.f$1);
                return;
            case 9:
                ((DelayedStream) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).realStream.setMaxInboundMessageSize(this.f$1);
                return;
            case 10:
                ((DelayedStream) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).realStream.setMaxOutboundMessageSize(this.f$1);
                return;
            case 11:
                NetworkChangeNotifierAutoDetect.this.mObserver.onConnectionTypeChanged(this.f$1);
                return;
            case 12:
                switch (this.f$1) {
                    case 0:
                        i = 0;
                        break;
                    case 1:
                        i = 1;
                        break;
                    case 2:
                        i = 2;
                        break;
                    case 3:
                        i = 3;
                        break;
                    case 4:
                        break;
                    case 5:
                    default:
                        throw new IllegalArgumentException("No request status found.");
                    case 6:
                        i = 5;
                        break;
                    case 7:
                        i = 6;
                        break;
                    case 8:
                        i = 7;
                        break;
                    case 9:
                        i = 8;
                        break;
                    case 10:
                        i = 9;
                        break;
                    case 11:
                        i = 10;
                        break;
                    case 12:
                        i = 11;
                        break;
                    case 13:
                        i = 12;
                        break;
                    case 14:
                        i = 13;
                        break;
                    case 15:
                        i = 14;
                        break;
                }
                ((VersionSafeCallbacks$UrlRequestStatusListener) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).onStatus(i);
                return;
            default:
                ((VersionSafeCallbacks$UrlRequestStatusListener) this.RatingView$$ExternalSyntheticLambda5$ar$f$0).onStatus(this.f$1);
                return;
        }
    }

    public RatingView$$ExternalSyntheticLambda5(Object obj, int i, int i2, byte[] bArr) {
        this.switching_field = i2;
        this.f$1 = i;
        this.RatingView$$ExternalSyntheticLambda5$ar$f$0 = obj;
    }

    public RatingView$$ExternalSyntheticLambda5(Object obj, int i, int i2, char[] cArr) {
        this.switching_field = i2;
        this.RatingView$$ExternalSyntheticLambda5$ar$f$0 = obj;
        this.f$1 = i;
    }
}
