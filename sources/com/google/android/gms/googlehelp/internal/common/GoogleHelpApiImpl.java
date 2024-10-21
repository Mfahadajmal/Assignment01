package com.google.android.gms.googlehelp.internal.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.Help;
import com.google.android.gms.googlehelp.InProductHelp;
import com.google.android.gms.googlehelp.trails.TrailsBuffer;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleHelpApiImpl {
    public static final Status RESULT_FAILURE = new Status(13);

    /* compiled from: PG */
    /* renamed from: com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends GoogleHelpImpl {
        final /* synthetic */ GoogleHelpApiImpl this$0;
        final /* synthetic */ WeakReference val$callingActivityWeakRef;
        final /* synthetic */ Intent val$helpIntent;
        final /* synthetic */ Bitmap val$pipBitmap = null;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(GoogleHelpApiImpl googleHelpApiImpl, GoogleApiClient googleApiClient, Intent intent, Bitmap bitmap, WeakReference weakReference) {
            super(googleApiClient);
            this.val$helpIntent = intent;
            this.val$callingActivityWeakRef = weakReference;
            this.this$0 = googleHelpApiImpl;
        }

        @Override // com.google.android.gms.googlehelp.internal.common.GoogleHelpApiImpl.BaseGoogleHelpApiMethodImpl
        protected final void doExecute$ar$ds(IGoogleHelpService iGoogleHelpService) {
            GoogleHelp googleHelp = (GoogleHelp) this.val$helpIntent.getParcelableExtra("EXTRA_GOOGLE_HELP");
            GoogleHelpApiImpl.addTrailsData$ar$ds(googleHelp);
            new AnonymousClass2.AnonymousClass1(this, iGoogleHelpService, this, 1).onSyncHelpPsdCollected(googleHelp);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class BaseGoogleHelpApiMethodImpl extends BaseImplementation$ApiMethodImpl {
        public BaseGoogleHelpApiMethodImpl(GoogleApiClient googleApiClient) {
            super(Help.API$ar$class_merging$ar$class_merging$ar$class_merging, googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
        protected final /* bridge */ /* synthetic */ void doExecute(Api$AnyClient api$AnyClient) {
            GoogleHelpClientImpl googleHelpClientImpl = (GoogleHelpClientImpl) api$AnyClient;
            Context context = googleHelpClientImpl.context;
            doExecute$ar$ds((IGoogleHelpService) googleHelpClientImpl.getService());
        }

        protected abstract void doExecute$ar$ds(IGoogleHelpService iGoogleHelpService);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class GoogleHelpImpl extends BaseGoogleHelpApiMethodImpl {
        public GoogleHelpImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
            if (status == null) {
                return Status.RESULT_INTERNAL_ERROR;
            }
            return status;
        }
    }

    static final void addTrailsData$ar$ds(GoogleHelp googleHelp) {
        ArrayList arrayList;
        int i = googleHelp.trailsConsent;
        if (i == 0 || i == 1) {
            synchronized (TrailsBuffer.interactions) {
                arrayList = new ArrayList(TrailsBuffer.interactions);
            }
            if (!arrayList.isEmpty()) {
                googleHelp.trailsInteractions = arrayList;
            }
        }
    }

    static final void startHelpActivity$ar$ds(GoogleHelpImpl googleHelpImpl, Activity activity, Intent intent, GoogleHelp googleHelp) {
        InProductHelp createFromParcel;
        if (intent.hasExtra("EXTRA_GOOGLE_HELP")) {
            intent.putExtra("EXTRA_GOOGLE_HELP", googleHelp);
        } else if (intent.hasExtra("EXTRA_IN_PRODUCT_HELP")) {
            Parcelable.Creator<InProductHelp> creator = InProductHelp.CREATOR;
            byte[] byteArrayExtra = intent.getByteArrayExtra("EXTRA_IN_PRODUCT_HELP");
            if (byteArrayExtra == null) {
                createFromParcel = null;
            } else {
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(creator);
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArrayExtra, 0, byteArrayExtra.length);
                obtain.setDataPosition(0);
                createFromParcel = creator.createFromParcel(obtain);
                obtain.recycle();
            }
            InProductHelp inProductHelp = createFromParcel;
            inProductHelp.googleHelp = googleHelp;
            StrictModeUtils$VmPolicyBuilderCompatS.serializeToIntentExtra$ar$ds(inProductHelp, intent);
        }
        new TracingHandler(Looper.getMainLooper()).post(new ListMenuManager$$ExternalSyntheticLambda3(activity, intent, 15));
        googleHelpImpl.setResult(Status.RESULT_SUCCESS);
    }
}
