package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.aidl.BaseProxy;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.GoogleCertificatesLookupQuery;
import com.google.android.gms.common.GoogleCertificatesLookupResponse;
import com.google.android.gms.common.GoogleCertificatesQuery;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IGoogleCertificatesApi$Stub$Proxy extends BaseProxy implements IGoogleCertificatesApi {
    public IGoogleCertificatesApi$Stub$Proxy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.IGoogleCertificatesApi
    public final boolean isGoogleOrPlatformSigned(GoogleCertificatesQuery googleCertificatesQuery, IObjectWrapper iObjectWrapper) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleCertificatesQuery);
        Codecs.writeStrongBinder(obtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
        boolean createBoolean = Codecs.createBoolean(transactAndReadException);
        transactAndReadException.recycle();
        return createBoolean;
    }

    @Override // com.google.android.gms.common.internal.IGoogleCertificatesApi
    public final GoogleCertificatesLookupResponse isPackageGoogleOrPlatformSigned(GoogleCertificatesLookupQuery googleCertificatesLookupQuery) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        Codecs.writeParcelable(obtainAndWriteInterfaceToken, googleCertificatesLookupQuery);
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken);
        GoogleCertificatesLookupResponse googleCertificatesLookupResponse = (GoogleCertificatesLookupResponse) Codecs.createParcelable(transactAndReadException, GoogleCertificatesLookupResponse.CREATOR);
        transactAndReadException.recycle();
        return googleCertificatesLookupResponse;
    }

    @Override // com.google.android.gms.common.internal.IGoogleCertificatesApi
    public final boolean isPackageGoogleOrPlatformSignedAvailable() {
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken());
        boolean createBoolean = Codecs.createBoolean(transactAndReadException);
        transactAndReadException.recycle();
        return createBoolean;
    }
}
