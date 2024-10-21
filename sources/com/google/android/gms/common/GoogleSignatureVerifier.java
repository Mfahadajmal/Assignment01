package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.gms.common.GoogleCertificates;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleSignatureVerifier {
    public static volatile Set googlePackagesForTesting;
    private static GoogleSignatureVerifier instance;
    public static volatile Set nonGooglePackagesForTesting;
    public final Context context;
    public volatile String lastSuccessfulPackageNameVolatile;

    public GoogleSignatureVerifier(Context context) {
        this.context = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (instance == null) {
                GoogleCertificates.init(context);
                instance = new GoogleSignatureVerifier(context);
            }
        }
        return instance;
    }

    static final ICertData.Stub verifySignature$ar$class_merging$ar$ds(PackageInfo packageInfo, ICertData.Stub... stubArr) {
        if (packageInfo.signatures != null) {
            if (packageInfo.signatures.length != 1) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                return null;
            }
            GoogleCertificates.FullCertData fullCertData = new GoogleCertificates.FullCertData(packageInfo.signatures[0].toByteArray());
            for (int i = 0; i < stubArr.length; i++) {
                if (stubArr[i].equals(fullCertData)) {
                    return stubArr[i];
                }
            }
        }
        return null;
    }

    public final boolean isGooglePublicSignedPackage(PackageInfo packageInfo, boolean z) {
        boolean z2;
        ICertData.Stub verifySignature$ar$class_merging$ar$ds;
        if (z) {
            if (packageInfo != null) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (packageInfo != null && ("com.android.vending".equals(packageInfo.packageName) || "com.google.android.gms".equals(packageInfo.packageName))) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if (applicationInfo == null || (applicationInfo.flags & BrailleInputEvent.CMD_NAV_BOTTOM_OR_KEY_ACTIVATE) == 0) {
                    z = false;
                } else {
                    z = true;
                }
            }
        } else if (packageInfo != null) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (true != z2 && packageInfo.signatures != null) {
            if (z) {
                verifySignature$ar$class_merging$ar$ds = verifySignature$ar$class_merging$ar$ds(packageInfo, GoogleCertificates.VALID_PUBLIC_SIGNATURES.KEYS$ar$class_merging);
            } else {
                verifySignature$ar$class_merging$ar$ds = verifySignature$ar$class_merging$ar$ds(packageInfo, GoogleCertificates.VALID_PUBLIC_SIGNATURES.KEYS$ar$class_merging[0]);
            }
            if (verifySignature$ar$class_merging$ar$ds != null) {
                return true;
            }
        }
        return false;
    }
}
