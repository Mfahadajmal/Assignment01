package androidx.core.app;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.SharedElementCallback;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.view.inputmethod.EditorInfoCompat;
import j$.util.Objects;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActivityCompat extends EditorInfoCompat {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api23Impl {
        public static int noteProxyOpNoThrow(Context context, String str, String str2) {
            return AppOpsManagerCompat$Api23Impl.noteProxyOpNoThrow((AppOpsManager) AppOpsManagerCompat$Api23Impl.getSystemService(context, AppOpsManager.class), str, str2);
        }

        static void onSharedElementsReady(Object obj) {
            ((SharedElementCallback.OnSharedElementsReadyListener) obj).onSharedElementsReady();
        }

        static void requestPermissions(Activity activity, String[] strArr, int i) {
            activity.requestPermissions(strArr, i);
        }

        public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api31Impl {
        static boolean isLaunchedFromBubble(Activity activity) {
            return activity.isLaunchedFromBubble();
        }

        public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            try {
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", String.class).invoke(activity.getApplication().getPackageManager(), str)).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return activity.shouldShowRequestPermissionRationale(str);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api32Impl {
        public static int checkSelfPermission(Context context, String str) {
            int noteProxyOpNoThrow;
            int myPid = Process.myPid();
            int myUid = Process.myUid();
            String packageName = context.getPackageName();
            if (context.checkPermission(str, myPid, myUid) == -1) {
                return -1;
            }
            String permissionToOp = AppOpsManagerCompat$Api23Impl.permissionToOp(str);
            if (permissionToOp == null) {
                return 0;
            }
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(myUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            int myUid2 = Process.myUid();
            String packageName2 = context.getPackageName();
            if (myUid2 == myUid && Objects.equals(packageName2, packageName)) {
                if (Build.VERSION.SDK_INT >= 29) {
                    AppOpsManager systemService = AppOpsManagerCompat$Api29Impl.getSystemService(context);
                    noteProxyOpNoThrow = AppOpsManagerCompat$Api29Impl.checkOpNoThrow(systemService, permissionToOp, Binder.getCallingUid(), packageName);
                    if (noteProxyOpNoThrow == 0) {
                        noteProxyOpNoThrow = AppOpsManagerCompat$Api29Impl.checkOpNoThrow(systemService, permissionToOp, myUid, AppOpsManagerCompat$Api29Impl.getOpPackageName(context));
                    }
                } else {
                    noteProxyOpNoThrow = Api23Impl.noteProxyOpNoThrow(context, permissionToOp, packageName);
                }
            } else {
                noteProxyOpNoThrow = Api23Impl.noteProxyOpNoThrow(context, permissionToOp, packageName);
            }
            if (noteProxyOpNoThrow == 0) {
                return 0;
            }
            return -2;
        }

        public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        String[] strArr2;
        HashSet hashSet = new HashSet();
        int i2 = 0;
        while (true) {
            int length = strArr.length;
            if (i2 < length) {
                if (!TextUtils.isEmpty(strArr[i2])) {
                    if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(strArr[i2], "android.permission.POST_NOTIFICATIONS")) {
                        hashSet.add(Integer.valueOf(i2));
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException("Permission request for permissions " + Arrays.toString(strArr) + " must not contain null or empty values");
                }
            } else {
                int size = hashSet.size();
                if (size > 0) {
                    strArr2 = new String[length - size];
                } else {
                    strArr2 = strArr;
                }
                if (size > 0) {
                    if (size != length) {
                        int i3 = 0;
                        for (int i4 = 0; i4 < strArr.length; i4++) {
                            if (!hashSet.contains(Integer.valueOf(i4))) {
                                strArr2[i3] = strArr[i4];
                                i3++;
                            }
                        }
                    } else {
                        return;
                    }
                }
                if (activity instanceof FragmentActivity) {
                }
                Api23Impl.requestPermissions(activity, strArr, i);
                return;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api28Impl {
        public static Intent getParentActivityIntent(Activity activity) {
            Intent parentActivityIntent = activity.getParentActivityIntent();
            if (parentActivityIntent != null) {
                return parentActivityIntent;
            }
            String parentActivityName = getParentActivityName(activity);
            if (parentActivityName == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, parentActivityName);
            try {
                if (getParentActivityName(activity, componentName) == null) {
                    return Intent.makeMainActivity(componentName);
                }
                return new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(parentActivityName, "getParentActivityIntent: bad parentActivityName '", "' in manifest"));
                return null;
            }
        }

        public static String getParentActivityName(Activity activity) {
            try {
                return getParentActivityName(activity, activity.getComponentName());
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public static <T> T requireViewById(Activity activity, int i) {
            return (T) activity.requireViewById(i);
        }

        public static String getParentActivityName(Context context, ComponentName componentName) {
            String string;
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, Build.VERSION.SDK_INT >= 29 ? 269222528 : 787072);
            String str = activityInfo.parentActivityName;
            if (str != null) {
                return str;
            }
            if (activityInfo.metaData == null || (string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY")) == null) {
                return null;
            }
            return string.charAt(0) == '.' ? String.valueOf(context.getPackageName()).concat(string) : string;
        }

        public static Intent getParentActivityIntent(Context context, ComponentName componentName) {
            String parentActivityName = getParentActivityName(context, componentName);
            if (parentActivityName == null) {
                return null;
            }
            ComponentName componentName2 = new ComponentName(componentName.getPackageName(), parentActivityName);
            if (getParentActivityName(context, componentName2) == null) {
                return Intent.makeMainActivity(componentName2);
            }
            return new Intent().setComponent(componentName2);
        }
    }
}
