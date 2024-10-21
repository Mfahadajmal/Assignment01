package com.google.android.apps.aicore.aidl;

import android.os.IInterface;
import android.os.Parcel;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.apps.aicore.client.api.AiCoreException;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IPrepareInferenceEngineCallback extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IPrepareInferenceEngineCallback {
        final /* synthetic */ CallbackToFutureAdapter$Completer val$completer;

        public Stub() {
            super("com.google.android.apps.aicore.aidl.IPrepareInferenceEngineCallback");
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 2) {
                if (i != 3) {
                    return false;
                }
                int readInt = parcel.readInt();
                Codecs.enforceNoDataAvail(parcel);
                onPreparationFailure(readInt);
                return true;
            }
            onPreparationSuccess();
            return true;
        }

        public final void onPreparationFailure(int i) {
            this.val$completer.setException$ar$ds(AiCoreException.newPreparationError(i, "Preparation failed."));
        }

        public final void onPreparationSuccess() {
            this.val$completer.set$ar$ds(null);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer) {
            this();
            this.val$completer = callbackToFutureAdapter$Completer;
        }
    }
}
