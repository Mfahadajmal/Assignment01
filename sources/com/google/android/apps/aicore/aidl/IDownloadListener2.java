package com.google.android.apps.aicore.aidl;

import android.os.IInterface;
import android.os.Parcel;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import com.google.android.accessibility.talkback.TouchInteractionMonitor$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$1$$ExternalSyntheticLambda0;
import com.google.search.mdi.aratea.proto.FeatureName;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IDownloadListener2 extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IDownloadListener2 {
        public final /* synthetic */ AiCoreClientImpl this$0;
        final /* synthetic */ DownloadCallback val$callback;
        final /* synthetic */ CallbackToFutureAdapter$Completer val$completer;
        final /* synthetic */ AiFeature val$feature;

        public Stub() {
            super("com.google.android.apps.aicore.aidl.IDownloadListener2");
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return false;
                        }
                        parcel.readString();
                        Codecs.enforceNoDataAvail(parcel);
                        onDownloadCompleted$ar$ds$8a43d8e2_1();
                        return true;
                    }
                    String readString = parcel.readString();
                    int readInt = parcel.readInt();
                    String readString2 = parcel.readString();
                    Codecs.enforceNoDataAvail(parcel);
                    onDownloadFailed(readString, readInt, readString2);
                    return true;
                }
                parcel.readString();
                long readLong = parcel.readLong();
                Codecs.enforceNoDataAvail(parcel);
                onDownloadProgress$ar$ds$2f51f47d_1(readLong);
                return true;
            }
            parcel.readString();
            long readLong2 = parcel.readLong();
            Codecs.enforceNoDataAvail(parcel);
            onDownloadStarted$ar$ds$da3cc59d_1(readLong2);
            return true;
        }

        public final void onDownloadCompleted$ar$ds$8a43d8e2_1() {
            this.this$0.callbackExecutor.execute(new ListMenuManager$$ExternalSyntheticLambda3(this.val$callback, this.val$feature, 8, (char[]) null));
            this.val$completer.set$ar$ds(null);
        }

        public final void onDownloadFailed(String str, int i, String str2) {
            int i2 = 0;
            String format = String.format(Locale.ENGLISH, "Feature %s failed with failure status %d and error %s.", str, Integer.valueOf(i), str2);
            if (i == 1) {
                i2 = FeatureName.GENC_COMPUTE$ar$edu;
            }
            AiCoreException newDownloadError = AiCoreException.newDownloadError(i2, format);
            this.this$0.callbackExecutor.execute(new AiCoreClientImpl$1$$ExternalSyntheticLambda0(this, this.val$callback, this.val$feature, i, newDownloadError, 0));
            this.val$completer.setException$ar$ds(newDownloadError);
        }

        public final void onDownloadProgress$ar$ds$2f51f47d_1(long j) {
            this.this$0.callbackExecutor.execute(new TouchInteractionMonitor$$ExternalSyntheticLambda1(this.val$callback, this.val$feature, j, 3));
        }

        public final void onDownloadStarted$ar$ds$da3cc59d_1(long j) {
            this.this$0.callbackExecutor.execute(new TouchInteractionMonitor$$ExternalSyntheticLambda1(this.val$callback, this.val$feature, j, 2));
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(AiCoreClientImpl aiCoreClientImpl, DownloadCallback downloadCallback, AiFeature aiFeature, CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer) {
            this();
            this.val$callback = downloadCallback;
            this.val$feature = aiFeature;
            this.val$completer = callbackToFutureAdapter$Completer;
            this.this$0 = aiCoreClientImpl;
        }
    }
}
