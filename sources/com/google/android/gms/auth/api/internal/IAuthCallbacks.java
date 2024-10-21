package com.google.android.gms.auth.api.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IAuthCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements IAuthCallbacks {
        public Stub(byte[] bArr) {
            this();
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                String readString = parcel.readString();
                Codecs.enforceNoDataAvail(parcel);
                handleSpatulaHeader(readString);
            } else {
                ProxyResponse proxyResponse = (ProxyResponse) Codecs.createParcelable(parcel, ProxyResponse.CREATOR);
                Codecs.enforceNoDataAvail(parcel);
                handleProxyResponse(proxyResponse);
            }
            parcel2.writeNoException();
            return true;
        }

        public void handleProxyResponse(ProxyResponse proxyResponse) {
            throw new UnsupportedOperationException();
        }

        public void handleSpatulaHeader(String str) {
            throw new UnsupportedOperationException();
        }

        public Stub() {
            super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
        }
    }
}
