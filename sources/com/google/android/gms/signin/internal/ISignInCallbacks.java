package com.google.android.gms.signin.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ISignInCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Stub extends BaseStub implements ISignInCallbacks {
        public Stub(byte[] bArr) {
            this();
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            switch (i) {
                case 3:
                    Codecs.enforceNoDataAvail(parcel);
                    break;
                case 4:
                    Codecs.enforceNoDataAvail(parcel);
                    break;
                case 5:
                default:
                    return false;
                case 6:
                    Codecs.enforceNoDataAvail(parcel);
                    break;
                case 7:
                    Codecs.enforceNoDataAvail(parcel);
                    break;
                case 8:
                    SignInResponse signInResponse = (SignInResponse) Codecs.createParcelable(parcel, SignInResponse.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onSignInComplete(signInResponse);
                    break;
                case 9:
                    Codecs.enforceNoDataAvail(parcel);
                    break;
            }
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            super("com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        public void onSignInComplete(SignInResponse signInResponse) {
        }
    }

    void onSignInComplete(SignInResponse signInResponse);
}
