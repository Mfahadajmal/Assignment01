package com.google.android.gms.net;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetProviderInstaller {
    private static final String TAG = "CronetProviderInstaller";
    private static final GoogleApiAvailabilityLight apiAvailability = GoogleApiAvailabilityLight.INSTANCE;
    public static final Object lock = new Object();
    private static DynamiteModule dynamiteModule = null;
    public static String cronetImplVersion = "0";

    private CronetProviderInstaller() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DynamiteModule getDynamiteModule() {
        DynamiteModule dynamiteModule2;
        synchronized (lock) {
            dynamiteModule2 = dynamiteModule;
        }
        return dynamiteModule2;
    }

    @Deprecated
    public static void installIfNeeded(Context context) {
        synchronized (lock) {
            if (isInstalled()) {
                return;
            }
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(context, "Context must not be null");
            ClassLoader classLoader = CronetProviderInstaller.class.getClassLoader();
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(classLoader);
            try {
                classLoader.loadClass("org.chromium.net.CronetEngine");
                GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context, 11925000);
                try {
                    DynamiteModule load = DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.cronet_dynamite");
                    try {
                        Class<?> loadClass = load.moduleContext.getClassLoader().loadClass("org.chromium.net.impl.ImplVersion");
                        if (loadClass.getClassLoader() != CronetProviderInstaller.class.getClassLoader()) {
                            Method method = loadClass.getMethod("getApiLevel", null);
                            Method method2 = loadClass.getMethod("getCronetVersion", null);
                            Integer num = (Integer) method.invoke(null, new Object[0]);
                            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(num);
                            int intValue = num.intValue();
                            String str = (String) method2.invoke(null, new Object[0]);
                            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str);
                            cronetImplVersion = str;
                            if (intValue < 3) {
                                Intent errorResolutionIntent = apiAvailability.getErrorResolutionIntent(context, 2, "cr");
                                if (errorResolutionIntent == null) {
                                    Log.e(TAG, "Unable to fetch error resolution intent");
                                    throw new GooglePlayServicesNotAvailableException();
                                }
                                throw new GooglePlayServicesRepairableException(2, "Google Play Services update is required. The API Level of the client is 3. The API Level of the implementation is " + intValue + ". The Cronet implementation version is " + cronetImplVersion, errorResolutionIntent);
                            }
                            dynamiteModule = load;
                            return;
                        }
                        Log.e(TAG, "ImplVersion class is missing from Cronet module.");
                        throw new GooglePlayServicesNotAvailableException();
                    } catch (Exception e) {
                        Log.e(TAG, "Unable to read Cronet version from the Cronet module ", e);
                        throw ((GooglePlayServicesNotAvailableException) new GooglePlayServicesNotAvailableException().initCause(e));
                    }
                } catch (DynamiteModule.LoadingException e2) {
                    Log.e(TAG, "Unable to load Cronet module", e2);
                    throw ((GooglePlayServicesNotAvailableException) new GooglePlayServicesNotAvailableException().initCause(e2));
                }
            } catch (ClassNotFoundException e3) {
                Log.e(TAG, "Cronet API is not available. Have you included all required dependencies?");
                throw ((GooglePlayServicesNotAvailableException) new GooglePlayServicesNotAvailableException().initCause(e3));
            }
        }
    }

    public static boolean isInstalled() {
        if (getDynamiteModule() != null) {
            return true;
        }
        return false;
    }
}
