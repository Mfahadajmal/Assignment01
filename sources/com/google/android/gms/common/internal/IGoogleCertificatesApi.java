package com.google.android.gms.common.internal;

import android.os.IInterface;
import com.google.android.gms.common.GoogleCertificatesLookupQuery;
import com.google.android.gms.common.GoogleCertificatesLookupResponse;
import com.google.android.gms.common.GoogleCertificatesQuery;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGoogleCertificatesApi extends IInterface {
    boolean isGoogleOrPlatformSigned(GoogleCertificatesQuery googleCertificatesQuery, IObjectWrapper iObjectWrapper);

    GoogleCertificatesLookupResponse isPackageGoogleOrPlatformSigned(GoogleCertificatesLookupQuery googleCertificatesLookupQuery);

    boolean isPackageGoogleOrPlatformSignedAvailable();
}
