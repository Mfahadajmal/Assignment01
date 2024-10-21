package com.google.android.gms.common.internal;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NotificationCompatBuilder$Api29Impl;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.marvin.talkback.R;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionErrorMessages {
    private static final SimpleArrayMap cache = new SimpleArrayMap();
    private static Locale currentLocale;

    public static String getAppName(Context context) {
        AppLifecycleMonitor packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging;
        String packageName = context.getPackageName();
        try {
            packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging = Wrappers.wrappers.getPackageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging(context);
            return ((Context) packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging.AppLifecycleMonitor$ar$tracker).getPackageManager().getApplicationLabel(((Context) packageManagerWrapper$ar$class_merging$ar$class_merging$ar$class_merging.AppLifecycleMonitor$ar$tracker).getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            if (TextUtils.isEmpty(str)) {
                return packageName;
            }
            return str;
        }
    }

    public static String getErrorMessage(Context context, int i) {
        Resources resources = context.getResources();
        String appName = getAppName(context);
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 5) {
                        if (i != 7) {
                            if (i != 9) {
                                if (i != 20) {
                                    switch (i) {
                                        case 16:
                                            return getRemoteMessage(context, "common_google_play_services_api_unavailable_text", appName);
                                        case 17:
                                            return getRemoteMessage(context, "common_google_play_services_sign_in_failed_text", appName);
                                        case 18:
                                            return resources.getString(R.string.common_google_play_services_updating_text, appName);
                                        default:
                                            return resources.getString(R.string.common_google_play_services_unknown_issue, appName);
                                    }
                                }
                                return getRemoteMessage(context, "common_google_play_services_restricted_profile_text", appName);
                            }
                            return resources.getString(R.string.common_google_play_services_unsupported_text, appName);
                        }
                        return getRemoteMessage(context, "common_google_play_services_network_error_text", appName);
                    }
                    return getRemoteMessage(context, "common_google_play_services_invalid_account_text", appName);
                }
                return resources.getString(R.string.common_google_play_services_enable_text, appName);
            }
            if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                return resources.getString(R.string.common_google_play_services_wear_update_text);
            }
            return resources.getString(R.string.common_google_play_services_update_text, appName);
        }
        return resources.getString(R.string.common_google_play_services_install_text, appName);
    }

    public static String getErrorTitle(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return getRemoteString(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return getRemoteString(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                Log.e("GoogleApiAvailability", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unexpected error code "));
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return getRemoteString(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return getRemoteString(context, "common_google_play_services_restricted_profile_title");
        }
    }

    public static String getRemoteMessage(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String remoteString = getRemoteString(context, str);
        if (remoteString == null) {
            remoteString = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, remoteString, str2);
    }

    public static String getRemoteString(Context context, String str) {
        Resources resources;
        SimpleArrayMap simpleArrayMap = cache;
        synchronized (simpleArrayMap) {
            Locale locale = NotificationCompatBuilder$Api29Impl.getLocales(context.getResources().getConfiguration()).get(0);
            if (!locale.equals(currentLocale)) {
                simpleArrayMap.clear();
                currentLocale = locale;
            }
            String str2 = (String) simpleArrayMap.get(str);
            if (str2 != null) {
                return str2;
            }
            int i = GooglePlayServicesUtil.GooglePlayServicesUtil$ar$NoOp;
            try {
                resources = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
            } catch (PackageManager.NameNotFoundException unused) {
                resources = null;
            }
            if (resources == null) {
                return null;
            }
            int identifier = resources.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                Log.w("GoogleApiAvailability", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(str, "Missing resource: "));
                return null;
            }
            String string = resources.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                Log.w("GoogleApiAvailability", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(str, "Got empty resource: "));
                return null;
            }
            cache.put(str, string);
            return string;
        }
    }
}
