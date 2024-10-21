package com.google.android.apps.aicore.aidl;

import android.os.IInterface;
import android.os.Parcel;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.common.inject.ApplicationModule;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IImageDescriptionResultCallback extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IImageDescriptionResultCallback {
        final /* synthetic */ ApplicationModule val$callback$ar$class_merging$7d076673_0$ar$class_merging;

        public Stub() {
            super("com.google.android.apps.aicore.aidl.IImageDescriptionResultCallback");
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 2) {
                if (i != 3) {
                    return false;
                }
                int readInt = parcel.readInt();
                Codecs.enforceNoDataAvail(parcel);
                onImageDescriptionFailure(readInt);
                return true;
            }
            ImageDescriptionResult imageDescriptionResult = (ImageDescriptionResult) Codecs.createParcelable(parcel, ImageDescriptionResult.CREATOR);
            Codecs.enforceNoDataAvail(parcel);
            onImageDescriptionSuccess(imageDescriptionResult);
            return true;
        }

        public final void onImageDescriptionFailure(int i) {
            ((CallbackToFutureAdapter$Completer) this.val$callback$ar$class_merging$7d076673_0$ar$class_merging.ApplicationModule$ar$application).setException$ar$ds(new AiCoreException(2, i, "Inference failed.", null));
        }

        public final void onImageDescriptionSuccess(ImageDescriptionResult imageDescriptionResult) {
            ((CallbackToFutureAdapter$Completer) this.val$callback$ar$class_merging$7d076673_0$ar$class_merging.ApplicationModule$ar$application).set$ar$ds(new com.google.android.apps.aicore.client.api.imagedescription.ImageDescriptionResult(imageDescriptionResult.descriptions));
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(ApplicationModule applicationModule) {
            this();
            this.val$callback$ar$class_merging$7d076673_0$ar$class_merging = applicationModule;
        }
    }
}
