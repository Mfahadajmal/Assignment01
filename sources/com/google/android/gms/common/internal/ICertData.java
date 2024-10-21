package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ICertData extends IInterface {
    IObjectWrapper getBytesWrapped();

    int getHashCode();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements ICertData {
        private int hashValue;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Proxy extends BaseProxy implements ICertData {
            public Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.ICertData");
            }

            @Override // com.google.android.gms.common.internal.ICertData
            public final IObjectWrapper getBytesWrapped() {
                IObjectWrapper proxy;
                Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
                IBinder readStrongBinder = transactAndReadException.readStrongBinder();
                if (readStrongBinder == null) {
                    proxy = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
                    if (queryLocalInterface instanceof IObjectWrapper) {
                        proxy = (IObjectWrapper) queryLocalInterface;
                    } else {
                        proxy = new IObjectWrapper.Stub.Proxy(readStrongBinder);
                    }
                }
                transactAndReadException.recycle();
                return proxy;
            }

            @Override // com.google.android.gms.common.internal.ICertData
            public final int getHashCode() {
                Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
                int readInt = transactAndReadException.readInt();
                transactAndReadException.recycle();
                return readInt;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Stub(byte[] bArr) {
            this();
            StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(bArr.length == 25);
            this.hashValue = Arrays.hashCode(bArr);
        }

        public static byte[] extractBytes(String str) {
            try {
                return str.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                int hashCode = getHashCode();
                parcel2.writeNoException();
                parcel2.writeInt(hashCode);
            } else {
                IObjectWrapper bytesWrapped = getBytesWrapped();
                parcel2.writeNoException();
                Codecs.writeStrongBinder(parcel2, bytesWrapped);
            }
            return true;
        }

        public final boolean equals(Object obj) {
            IObjectWrapper bytesWrapped;
            if (obj != null && (obj instanceof ICertData)) {
                try {
                    ICertData iCertData = (ICertData) obj;
                    if (iCertData.getHashCode() != this.hashValue || (bytesWrapped = iCertData.getBytesWrapped()) == null) {
                        return false;
                    }
                    return Arrays.equals(getBytes(), (byte[]) IObjectWrapper.Stub.unwrap(bytesWrapped));
                } catch (RemoteException e) {
                    Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                }
            }
            return false;
        }

        public abstract byte[] getBytes();

        @Override // com.google.android.gms.common.internal.ICertData
        public final IObjectWrapper getBytesWrapped() {
            return new IObjectWrapper.Stub(getBytes());
        }

        @Override // com.google.android.gms.common.internal.ICertData
        public final int getHashCode() {
            return this.hashValue;
        }

        public final int hashCode() {
            return this.hashValue;
        }

        public Stub() {
            super("com.google.android.gms.common.internal.ICertData");
        }
    }
}
