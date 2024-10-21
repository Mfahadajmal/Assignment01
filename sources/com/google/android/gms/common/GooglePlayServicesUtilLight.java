package com.google.android.gms.common;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GooglePlayServicesUtilLight {
    public static final /* synthetic */ int GooglePlayServicesUtilLight$ar$NoOp = 0;
    private static boolean isTestKeys = false;
    static boolean sCachedFieldsPopulated = false;

    @Deprecated
    static final AtomicBoolean sCanceledAvailabilityNotification = new AtomicBoolean();
    private static final AtomicBoolean usingApkIndependentContext = new AtomicBoolean();

    @Deprecated
    public static void ensurePlayServicesAvailable(Context context, int i) {
        int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.INSTANCE.isGooglePlayServicesAvailable(context, i);
        if (isGooglePlayServicesAvailable != 0) {
            Intent errorResolutionIntent = GoogleApiAvailabilityLight.INSTANCE.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(isGooglePlayServicesAvailable, "GooglePlayServices not available due to error "));
            if (errorResolutionIntent == null) {
                throw new GooglePlayServicesNotAvailableException();
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", errorResolutionIntent);
        }
    }

    @Deprecated
    public static int getApkVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    public static boolean honorsDebugCertificates(Context context) {
        try {
            if (!sCachedFieldsPopulated) {
                PackageInfo packageInfo = Wrappers.packageManager$ar$class_merging$ar$class_merging$ar$class_merging(context).getPackageInfo("com.google.android.gms", 64);
                GoogleSignatureVerifier googleSignatureVerifier = GoogleSignatureVerifier.getInstance(context);
                if (packageInfo != null && !googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, false) && googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, true)) {
                    isTestKeys = true;
                } else {
                    isTestKeys = false;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
        } finally {
            sCachedFieldsPopulated = true;
        }
        if (!isTestKeys && "user".equals(Build.TYPE)) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int isGooglePlayServicesAvailable(android.content.Context r9, int r10) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(android.content.Context, int):int");
    }

    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context, int i) {
        if (i == 1) {
            return isUninstalledAppPossiblyUpdating$ar$ds(context);
        }
        return false;
    }

    public static boolean isUninstalledAppPossiblyUpdating$ar$ds(Context context) {
        try {
            Iterator<PackageInstaller.SessionInfo> it = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
            while (it.hasNext()) {
                if ("com.google.android.gms".equals(it.next().getAppPackageName())) {
                    return true;
                }
            }
            return context.getPackageManager().getApplicationInfo("com.google.android.gms", 8192).enabled;
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }
}
