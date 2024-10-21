package com.google.android.apps.aicore.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl;
import com.google.search.mdi.aratea.proto.FeatureName;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IAiCoreServiceProviderCallback extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IAiCoreServiceProviderCallback {
        final /* synthetic */ AiCoreClientImpl.AiCoreServiceConnection this$1;

        public Stub() {
            super("com.google.android.apps.aicore.aidl.IAiCoreServiceProviderCallback");
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            IAICoreService iAICoreService$Stub$Proxy;
            if (i != 2) {
                if (i != 3) {
                    return false;
                }
                int readInt = parcel.readInt();
                String readString = parcel.readString();
                Codecs.enforceNoDataAvail(parcel);
                onServiceProviderFailure(readInt, readString);
                return true;
            }
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                iAICoreService$Stub$Proxy = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.apps.aicore.aidl.IAICoreService");
                if (queryLocalInterface instanceof IAICoreService) {
                    iAICoreService$Stub$Proxy = (IAICoreService) queryLocalInterface;
                } else {
                    iAICoreService$Stub$Proxy = new IAICoreService$Stub$Proxy(readStrongBinder);
                }
            }
            Codecs.enforceNoDataAvail(parcel);
            onServiceProviderSuccess(iAICoreService$Stub$Proxy);
            return true;
        }

        public final void onServiceProviderFailure(int i, String str) {
            int i2;
            if (i != 1) {
                if (i != 2) {
                    i2 = 0;
                } else {
                    i2 = 604;
                }
            } else {
                i2 = FeatureName.AM_GENERATIVE_STICKERS$ar$edu;
            }
            this.this$1.handleConnectionError(AiCoreException.newConnectionError(i2, "AiCore service is not connected. Service error: ".concat(String.valueOf(str))));
        }

        public final void onServiceProviderSuccess(IAICoreService iAICoreService) {
            this.this$1.handleAiCoreService(iAICoreService);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(AiCoreClientImpl.AiCoreServiceConnection aiCoreServiceConnection) {
            this();
            this.this$1 = aiCoreServiceConnection;
        }
    }
}
