package com.google.android.gms.dynamite;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import dalvik.system.DelegateLastClassLoader;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DynamiteModule {
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING;
    public static final VersionPolicy PREFER_LOCAL;
    public static final VersionPolicy PREFER_REMOTE;
    private static boolean disableStandaloneDynamiteLoader = false;
    private static IDynamiteLoader dynamiteLoader = null;
    private static String dynamiteLoaderApkPath = null;
    private static IDynamiteLoaderV2 dynamiteLoaderV2 = null;
    private static int dynamiteLoaderVersion = -1;
    private static Boolean isGmsCoreValid;
    private static Boolean useV2;
    public final Context moduleContext;
    private static final ThreadLocal currentQueryResult = new ThreadLocal();
    private static final ThreadLocal startTimeMs = new ThreadLocal() { // from class: com.google.android.gms.dynamite.DynamiteModule.1
        @Override // java.lang.ThreadLocal
        protected final /* bridge */ /* synthetic */ Object initialValue() {
            return 0L;
        }
    };
    private static final VersionPolicy.IVersions DEFAULT_VERSIONS = new VersionPolicy.IVersions() { // from class: com.google.android.gms.dynamite.DynamiteModule.2
        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getLocalVersion(Context context, String str) {
            return DynamiteModule.getLocalVersion(context, str);
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getRemoteVersion(Context context, String str, boolean z) {
            return DynamiteModule.getRemoteVersion(context, str, z);
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FixedVersions implements VersionPolicy.IVersions {
        private final int localVersion;

        public FixedVersions(int i) {
            this.localVersion = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getLocalVersion(Context context, String str) {
            return this.localVersion;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
        public final int getRemoteVersion(Context context, String str, boolean z) {
            return 0;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LoadingException extends Exception {
        public LoadingException(String str) {
            super(str);
        }

        public LoadingException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface VersionPolicy {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface IVersions {
            int getLocalVersion(Context context, String str);

            int getRemoteVersion(Context context, String str, boolean z);
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions);
    }

    static {
        final int i = 1;
        PREFER_REMOTE = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.4
            @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
            public final VersionPolicy.SelectionResult selectModule(Context context, String str, VersionPolicy.IVersions iVersions) {
                int remoteVersion;
                int i2 = i;
                int i3 = -1;
                if (i2 != 0) {
                    if (i2 != 1) {
                        VersionPolicy.SelectionResult selectionResult = new VersionPolicy.SelectionResult();
                        int localVersion = iVersions.getLocalVersion(context, str);
                        selectionResult.localVersion = localVersion;
                        if (localVersion != 0) {
                            remoteVersion = iVersions.getRemoteVersion(context, str, false);
                            selectionResult.remoteVersion = remoteVersion;
                        } else {
                            remoteVersion = iVersions.getRemoteVersion(context, str, true);
                            selectionResult.remoteVersion = remoteVersion;
                        }
                        int i4 = selectionResult.localVersion;
                        if (i4 == 0 && remoteVersion == 0) {
                            i3 = 0;
                        } else if (i4 < remoteVersion) {
                            i3 = 1;
                        }
                        selectionResult.selection = i3;
                        return selectionResult;
                    }
                    VersionPolicy.SelectionResult selectionResult2 = new VersionPolicy.SelectionResult();
                    int remoteVersion2 = iVersions.getRemoteVersion(context, str, true);
                    selectionResult2.remoteVersion = remoteVersion2;
                    if (remoteVersion2 != 0) {
                        selectionResult2.selection = 1;
                    } else {
                        int localVersion2 = iVersions.getLocalVersion(context, str);
                        selectionResult2.localVersion = localVersion2;
                        if (localVersion2 != 0) {
                            selectionResult2.selection = -1;
                        }
                    }
                    return selectionResult2;
                }
                VersionPolicy.SelectionResult selectionResult3 = new VersionPolicy.SelectionResult();
                int localVersion3 = iVersions.getLocalVersion(context, str);
                selectionResult3.localVersion = localVersion3;
                if (localVersion3 != 0) {
                    selectionResult3.selection = -1;
                } else {
                    int remoteVersion3 = iVersions.getRemoteVersion(context, str, true);
                    selectionResult3.remoteVersion = remoteVersion3;
                    if (remoteVersion3 != 0) {
                        selectionResult3.selection = 1;
                    }
                }
                return selectionResult3;
            }
        };
        final int i2 = 0;
        PREFER_LOCAL = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.4
            @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
            public final VersionPolicy.SelectionResult selectModule(Context context, String str, VersionPolicy.IVersions iVersions) {
                int remoteVersion;
                int i22 = i2;
                int i3 = -1;
                if (i22 != 0) {
                    if (i22 != 1) {
                        VersionPolicy.SelectionResult selectionResult = new VersionPolicy.SelectionResult();
                        int localVersion = iVersions.getLocalVersion(context, str);
                        selectionResult.localVersion = localVersion;
                        if (localVersion != 0) {
                            remoteVersion = iVersions.getRemoteVersion(context, str, false);
                            selectionResult.remoteVersion = remoteVersion;
                        } else {
                            remoteVersion = iVersions.getRemoteVersion(context, str, true);
                            selectionResult.remoteVersion = remoteVersion;
                        }
                        int i4 = selectionResult.localVersion;
                        if (i4 == 0 && remoteVersion == 0) {
                            i3 = 0;
                        } else if (i4 < remoteVersion) {
                            i3 = 1;
                        }
                        selectionResult.selection = i3;
                        return selectionResult;
                    }
                    VersionPolicy.SelectionResult selectionResult2 = new VersionPolicy.SelectionResult();
                    int remoteVersion2 = iVersions.getRemoteVersion(context, str, true);
                    selectionResult2.remoteVersion = remoteVersion2;
                    if (remoteVersion2 != 0) {
                        selectionResult2.selection = 1;
                    } else {
                        int localVersion2 = iVersions.getLocalVersion(context, str);
                        selectionResult2.localVersion = localVersion2;
                        if (localVersion2 != 0) {
                            selectionResult2.selection = -1;
                        }
                    }
                    return selectionResult2;
                }
                VersionPolicy.SelectionResult selectionResult3 = new VersionPolicy.SelectionResult();
                int localVersion3 = iVersions.getLocalVersion(context, str);
                selectionResult3.localVersion = localVersion3;
                if (localVersion3 != 0) {
                    selectionResult3.selection = -1;
                } else {
                    int remoteVersion3 = iVersions.getRemoteVersion(context, str, true);
                    selectionResult3.remoteVersion = remoteVersion3;
                    if (remoteVersion3 != 0) {
                        selectionResult3.selection = 1;
                    }
                }
                return selectionResult3;
            }
        };
        final int i3 = 2;
        PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new VersionPolicy() { // from class: com.google.android.gms.dynamite.DynamiteModule.4
            @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
            public final VersionPolicy.SelectionResult selectModule(Context context, String str, VersionPolicy.IVersions iVersions) {
                int remoteVersion;
                int i22 = i3;
                int i32 = -1;
                if (i22 != 0) {
                    if (i22 != 1) {
                        VersionPolicy.SelectionResult selectionResult = new VersionPolicy.SelectionResult();
                        int localVersion = iVersions.getLocalVersion(context, str);
                        selectionResult.localVersion = localVersion;
                        if (localVersion != 0) {
                            remoteVersion = iVersions.getRemoteVersion(context, str, false);
                            selectionResult.remoteVersion = remoteVersion;
                        } else {
                            remoteVersion = iVersions.getRemoteVersion(context, str, true);
                            selectionResult.remoteVersion = remoteVersion;
                        }
                        int i4 = selectionResult.localVersion;
                        if (i4 == 0 && remoteVersion == 0) {
                            i32 = 0;
                        } else if (i4 < remoteVersion) {
                            i32 = 1;
                        }
                        selectionResult.selection = i32;
                        return selectionResult;
                    }
                    VersionPolicy.SelectionResult selectionResult2 = new VersionPolicy.SelectionResult();
                    int remoteVersion2 = iVersions.getRemoteVersion(context, str, true);
                    selectionResult2.remoteVersion = remoteVersion2;
                    if (remoteVersion2 != 0) {
                        selectionResult2.selection = 1;
                    } else {
                        int localVersion2 = iVersions.getLocalVersion(context, str);
                        selectionResult2.localVersion = localVersion2;
                        if (localVersion2 != 0) {
                            selectionResult2.selection = -1;
                        }
                    }
                    return selectionResult2;
                }
                VersionPolicy.SelectionResult selectionResult3 = new VersionPolicy.SelectionResult();
                int localVersion3 = iVersions.getLocalVersion(context, str);
                selectionResult3.localVersion = localVersion3;
                if (localVersion3 != 0) {
                    selectionResult3.selection = -1;
                } else {
                    int remoteVersion3 = iVersions.getRemoteVersion(context, str, true);
                    selectionResult3.remoteVersion = remoteVersion3;
                    if (remoteVersion3 != 0) {
                        selectionResult3.selection = 1;
                    }
                }
                return selectionResult3;
            }
        };
    }

    private DynamiteModule(Context context) {
        this.moduleContext = context;
    }

    private static void cacheDynamiteLoaderV2Locked(ClassLoader classLoader) {
        try {
            IDynamiteLoaderV2 iDynamiteLoaderV2 = null;
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder != null) {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof IDynamiteLoaderV2) {
                    iDynamiteLoaderV2 = (IDynamiteLoaderV2) queryLocalInterface;
                } else {
                    iDynamiteLoaderV2 = new IDynamiteLoaderV2$Stub$Proxy(iBinder);
                }
            }
            dynamiteLoaderV2 = iDynamiteLoaderV2;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e);
        }
    }

    private static boolean cacheQueryResultIfNeeded(Cursor cursor) {
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = (OnDeviceTextDetectionLoadLogEvent) currentQueryResult.get();
        if (onDeviceTextDetectionLoadLogEvent != null && onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode == null) {
            onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = cursor;
            return true;
        }
        return false;
    }

    private static boolean checkForValidGmsCore(Context context) {
        int i;
        if (Boolean.TRUE.equals(null) || Boolean.TRUE.equals(isGmsCoreValid)) {
            return true;
        }
        boolean z = false;
        if (isGmsCoreValid == null) {
            PackageManager packageManager = context.getPackageManager();
            if (Build.VERSION.SDK_INT < 29) {
                i = 0;
            } else {
                i = 268435456;
            }
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider("com.google.android.gms.chimera", i);
            if (GoogleApiAvailabilityLight.INSTANCE.isGooglePlayServicesAvailable(context, 10000000) == 0 && resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            isGmsCoreValid = valueOf;
            valueOf.getClass();
            if (z && resolveContentProvider.applicationInfo != null && (resolveContentProvider.applicationInfo.flags & BrailleInputEvent.CMD_NAV_BOTTOM_OR_KEY_ACTIVATE) == 0) {
                disableStandaloneDynamiteLoader = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static IDynamiteLoader getDynamiteLoader(Context context) {
        IDynamiteLoader iDynamiteLoader$Stub$Proxy;
        synchronized (DynamiteModule.class) {
            IDynamiteLoader iDynamiteLoader = dynamiteLoader;
            if (iDynamiteLoader != null) {
                return iDynamiteLoader;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    iDynamiteLoader$Stub$Proxy = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (queryLocalInterface instanceof IDynamiteLoader) {
                        iDynamiteLoader$Stub$Proxy = (IDynamiteLoader) queryLocalInterface;
                    } else {
                        iDynamiteLoader$Stub$Proxy = new IDynamiteLoader$Stub$Proxy(iBinder);
                    }
                }
                if (iDynamiteLoader$Stub$Proxy != null) {
                    dynamiteLoader = iDynamiteLoader$Stub$Proxy;
                    return iDynamiteLoader$Stub$Proxy;
                }
            } catch (Exception e) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e.getMessage());
            }
            return null;
        }
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "com.google.android.gms.dynamite.descriptors.", ".ModuleDescriptor"));
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(declaredField.get(null), str)) {
                Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
                return 0;
            }
            return declaredField2.getInt(null);
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Local module descriptor class for ", " not found."));
            return 0;
        } catch (Exception e) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e.getMessage())));
            return 0;
        }
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Object, android.database.Cursor] */
    public static int getRemoteVersion(Context context, String str, boolean z) {
        Field declaredField;
        Throwable th;
        RemoteException e;
        ?? r0;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = useV2;
                Cursor cursor = null;
                int i = 0;
                if (bool == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + e2.toString());
                        bool = Boolean.FALSE;
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader == ClassLoader.getSystemClassLoader()) {
                            bool = Boolean.FALSE;
                        } else if (classLoader != null) {
                            try {
                                cacheDynamiteLoaderV2Locked(classLoader);
                            } catch (LoadingException unused) {
                            }
                            bool = Boolean.TRUE;
                        } else {
                            if (!checkForValidGmsCore(context)) {
                                return 0;
                            }
                            if (!disableStandaloneDynamiteLoader && !Boolean.TRUE.equals(null)) {
                                try {
                                    int remoteVersionV2 = getRemoteVersionV2(context, str, z, true);
                                    String str2 = dynamiteLoaderApkPath;
                                    if (str2 != null && !str2.isEmpty()) {
                                        ClassLoader classLoader2 = DynamiteLoaderV2ClassLoader.get();
                                        if (classLoader2 == null) {
                                            if (Build.VERSION.SDK_INT >= 29) {
                                                String str3 = dynamiteLoaderApkPath;
                                                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str3);
                                                classLoader2 = new DelegateLastClassLoader(str3, ClassLoader.getSystemClassLoader());
                                            } else {
                                                String str4 = dynamiteLoaderApkPath;
                                                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str4);
                                                classLoader2 = new PathClassLoader(str4, ClassLoader.getSystemClassLoader()) { // from class: com.google.android.gms.dynamite.DynamiteModule.10
                                                    @Override // java.lang.ClassLoader
                                                    protected final Class loadClass(String str5, boolean z2) {
                                                        if (!str5.startsWith("java.") && !str5.startsWith("android.")) {
                                                            try {
                                                                return findClass(str5);
                                                            } catch (ClassNotFoundException unused2) {
                                                            }
                                                        }
                                                        return super.loadClass(str5, z2);
                                                    }
                                                };
                                            }
                                        }
                                        cacheDynamiteLoaderV2Locked(classLoader2);
                                        declaredField.set(null, classLoader2);
                                        useV2 = Boolean.TRUE;
                                        return remoteVersionV2;
                                    }
                                    return remoteVersionV2;
                                } catch (LoadingException unused2) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                }
                            } else {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            }
                        }
                        useV2 = bool;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return getRemoteVersionV2(context, str, z, false);
                    } catch (LoadingException e3) {
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e3.getMessage());
                        return 0;
                    }
                }
                IDynamiteLoader dynamiteLoader2 = getDynamiteLoader(context);
                if (dynamiteLoader2 != null) {
                    try {
                        try {
                            int iDynamiteLoaderVersion = dynamiteLoader2.getIDynamiteLoaderVersion();
                            if (iDynamiteLoaderVersion >= 3) {
                                OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = (OnDeviceTextDetectionLoadLogEvent) currentQueryResult.get();
                                if (onDeviceTextDetectionLoadLogEvent != null && (r0 = onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode) != 0) {
                                    i = r0.getInt(0);
                                } else {
                                    Cursor cursor2 = (Cursor) IObjectWrapper.Stub.unwrap(dynamiteLoader2.queryForDynamiteModuleNoCrashUtils(new IObjectWrapper.Stub(context), str, z, ((Long) startTimeMs.get()).longValue()));
                                    if (cursor2 != null) {
                                        try {
                                            if (cursor2.moveToFirst()) {
                                                int i2 = cursor2.getInt(0);
                                                if (i2 <= 0 || !cacheQueryResultIfNeeded(cursor2)) {
                                                    cursor = cursor2;
                                                }
                                                if (cursor != null) {
                                                    cursor.close();
                                                }
                                                i = i2;
                                            }
                                        } catch (RemoteException e4) {
                                            e = e4;
                                            cursor = cursor2;
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e.getMessage());
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            return i;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor = cursor2;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                }
                            } else if (iDynamiteLoaderVersion == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                i = dynamiteLoader2.getModuleVersion2NoCrashUtils(new IObjectWrapper.Stub(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                i = dynamiteLoader2.getModuleVersion2(new IObjectWrapper.Stub(context), str, z);
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (RemoteException e5) {
                        e = e5;
                    }
                }
                return i;
            }
        } catch (Throwable th4) {
            CrashUtils.addDynamiteErrorToDropBox$ar$ds(context);
            throw th4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0087, code lost:
    
        if (cacheQueryResultIfNeeded(r8) != false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int getRemoteVersionV2(android.content.Context r8, java.lang.String r9, boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.getRemoteVersionV2(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    /* JADX WARN: Not initialized variable reg: 17, insn: 0x0220: MOVE (r6 I:??[OBJECT, ARRAY]) = (r17 I:??[OBJECT, ARRAY]) (LINE:545), block:B:170:0x0220 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0201  */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.lang.Object, android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object, android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.dynamite.DynamiteModule load(android.content.Context r18, com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.load(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$VersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    public final IBinder instantiate(String str) {
        try {
            return (IBinder) this.moduleContext.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new LoadingException("Failed to instantiate module class: ".concat(str), e);
        }
    }
}
