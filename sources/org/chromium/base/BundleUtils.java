package org.chromium.base;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.util.ArrayMap;
import dalvik.system.BaseDexClassLoader;
import j$.util.DesugarCollections;
import java.lang.reflect.Field;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BundleUtils {
    private static final Object sSplitLock = new Object();
    public static final ArrayMap sCachedClassLoaders = new ArrayMap();

    static {
        DesugarCollections.synchronizedMap(new ArrayMap());
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0015, code lost:
    
        r0 = org.chromium.base.BundleUtils.sSplitLock;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0017, code lost:
    
        monitor-enter(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0018, code lost:
    
        r4 = com.google.mlkit.logging.schema.acceleration.DeviceInfo.createContextForSplit(r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x001c, code lost:
    
        monitor-exit(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Context createIsolatedSplitContext(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = r4
        L1:
            boolean r1 = r0 instanceof android.content.ContextWrapper     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            if (r1 == 0) goto L15
            boolean r1 = r0 instanceof android.app.Application     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            if (r1 == 0) goto Le
            android.content.Context r4 = com.google.mlkit.logging.schema.acceleration.DeviceInfo.createContextForSplit(r4, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            goto L1d
        Le:
            android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            android.content.Context r0 = r0.getBaseContext()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            goto L1
        L15:
            java.lang.Object r0 = org.chromium.base.BundleUtils.sSplitLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            monitor-enter(r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            android.content.Context r4 = com.google.mlkit.logging.schema.acceleration.DeviceInfo.createContextForSplit(r4, r5)     // Catch: java.lang.Throwable -> Ldb
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Ldb
        L1d:
            java.lang.ClassLoader r0 = r4.getClassLoader()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r0.getParent()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            android.util.ArrayMap r0 = org.chromium.base.BundleUtils.sCachedClassLoaders     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            monitor-enter(r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.lang.Object r1 = r0.get(r5)     // Catch: java.lang.Throwable -> Ld8
            java.lang.ClassLoader r1 = (java.lang.ClassLoader) r1     // Catch: java.lang.Throwable -> Ld8
            r2 = 0
            if (r1 == 0) goto L3f
            java.lang.ClassLoader r3 = r4.getClassLoader()     // Catch: java.lang.Throwable -> Ld8
            boolean r3 = r1.equals(r3)     // Catch: java.lang.Throwable -> Ld8
            if (r3 != 0) goto L46
            replaceClassLoader(r4, r1)     // Catch: java.lang.Throwable -> Ld8
            r2 = 1
            goto L46
        L3f:
            java.lang.ClassLoader r1 = r4.getClassLoader()     // Catch: java.lang.Throwable -> Ld8
            r0.put(r5, r1)     // Catch: java.lang.Throwable -> Ld8
        L46:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Ld8
            java.lang.String r0 = "Android.IsolatedSplits.ClassLoaderReplaced."
            java.lang.String r5 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(r5, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            org.chromium.base.metrics.CachingUmaRecorder r0 = org.chromium.base.metrics.UmaRecorderHolder.sRecorder     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r0.mRwLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r1.readLock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r1.lock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.Map r1 = r0.mHistogramByName     // Catch: java.lang.Throwable -> Lcd
            java.lang.Object r1 = r1.get(r5)     // Catch: java.lang.Throwable -> Lcd
            org.chromium.base.metrics.CachingUmaRecorder$Histogram r1 = (org.chromium.base.metrics.CachingUmaRecorder.Histogram) r1     // Catch: java.lang.Throwable -> Lcd
            if (r1 != 0) goto Lb8
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r0.mRwLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r1.readLock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r1.unlock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r0.mRwLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r1.writeLock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r1.lock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.Map r1 = r0.mHistogramByName     // Catch: java.lang.Throwable -> Lad
            java.lang.Object r1 = r1.get(r5)     // Catch: java.lang.Throwable -> Lad
            org.chromium.base.metrics.CachingUmaRecorder$Histogram r1 = (org.chromium.base.metrics.CachingUmaRecorder.Histogram) r1     // Catch: java.lang.Throwable -> Lad
            if (r1 != 0) goto L98
            java.util.Map r1 = r0.mHistogramByName     // Catch: java.lang.Throwable -> Lad
            int r1 = r1.size()     // Catch: java.lang.Throwable -> Lad
            r3 = 256(0x100, float:3.59E-43)
            if (r1 < r3) goto L8e
            java.util.concurrent.atomic.AtomicInteger r5 = r0.mDroppedHistogramSampleCount     // Catch: java.lang.Throwable -> Lad
            r5.incrementAndGet()     // Catch: java.lang.Throwable -> Lad
            goto La3
        L8e:
            org.chromium.base.metrics.CachingUmaRecorder$Histogram r1 = new org.chromium.base.metrics.CachingUmaRecorder$Histogram     // Catch: java.lang.Throwable -> Lad
            r1.<init>()     // Catch: java.lang.Throwable -> Lad
            java.util.Map r3 = r0.mHistogramByName     // Catch: java.lang.Throwable -> Lad
            r3.put(r5, r1)     // Catch: java.lang.Throwable -> Lad
        L98:
            boolean r5 = r1.addSample$ar$ds(r2)     // Catch: java.lang.Throwable -> Lad
            if (r5 != 0) goto La3
            java.util.concurrent.atomic.AtomicInteger r5 = r0.mDroppedHistogramSampleCount     // Catch: java.lang.Throwable -> Lad
            r5.incrementAndGet()     // Catch: java.lang.Throwable -> Lad
        La3:
            java.util.concurrent.locks.ReentrantReadWriteLock r5 = r0.mRwLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r5 = r5.writeLock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r5.unlock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            goto Lcc
        Lad:
            r4 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r5 = r0.mRwLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r5 = r5.writeLock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r5.unlock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            throw r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
        Lb8:
            boolean r5 = r1.addSample$ar$ds(r2)     // Catch: java.lang.Throwable -> Lcd
            if (r5 != 0) goto Lc3
            java.util.concurrent.atomic.AtomicInteger r5 = r0.mDroppedHistogramSampleCount     // Catch: java.lang.Throwable -> Lcd
            r5.incrementAndGet()     // Catch: java.lang.Throwable -> Lcd
        Lc3:
            java.util.concurrent.locks.ReentrantReadWriteLock r5 = r0.mRwLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r5 = r5.readLock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r5.unlock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
        Lcc:
            return r4
        Lcd:
            r4 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r5 = r0.mRwLock     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r5 = r5.readLock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            r5.unlock()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
            throw r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
        Ld8:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Ld8
            throw r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
        Ldb:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Ldb
            throw r4     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lde
        Lde:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.BundleUtils.createIsolatedSplitContext(android.content.Context, java.lang.String):android.content.Context");
    }

    public static String getNativeLibraryPath(String str, String str2) {
        StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
        try {
            String findLibrary = ((BaseDexClassLoader) BundleUtils.class.getClassLoader()).findLibrary(str);
            if (findLibrary == null) {
                ClassLoader classLoader = ContextUtils.sApplicationContext.getClassLoader();
                if (classLoader instanceof BaseDexClassLoader) {
                    findLibrary = ((BaseDexClassLoader) classLoader).findLibrary(str);
                } else if (classLoader instanceof WrappedClassLoader) {
                    throw null;
                }
                if (findLibrary != null) {
                    allowDiskReads.close();
                    return findLibrary;
                }
                String splitApkLibraryPath = getSplitApkLibraryPath(str, str2);
                allowDiskReads.close();
                return splitApkLibraryPath;
            }
            allowDiskReads.close();
            return findLibrary;
        } catch (Throwable th) {
            try {
                allowDiskReads.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static String getSplitApkLibraryPath(String str, String str2) {
        String splitApkPath = getSplitApkPath(str2);
        if (splitApkPath != null) {
            try {
                ApplicationInfo applicationInfo = ContextUtils.sApplicationContext.getApplicationInfo();
                return splitApkPath + "!/lib/" + ((String) applicationInfo.getClass().getField("primaryCpuAbi").get(applicationInfo)) + "/" + System.mapLibraryName(str);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private static String getSplitApkPath(String str) {
        String[] strArr;
        int binarySearch;
        ApplicationInfo applicationInfo = ContextUtils.sApplicationContext.getApplicationInfo();
        strArr = applicationInfo.splitNames;
        if (strArr != null && (binarySearch = Arrays.binarySearch(strArr, str)) >= 0) {
            return applicationInfo.splitSourceDirs[binarySearch];
        }
        return null;
    }

    public static boolean isBundleForNative() {
        return false;
    }

    public static boolean isIsolatedSplitInstalled(String str) {
        if (getSplitApkPath(str) != null) {
            return true;
        }
        return false;
    }

    public static void replaceClassLoader(Context context, ClassLoader classLoader) {
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        try {
            Field declaredField = context.getClass().getDeclaredField("mClassLoader");
            declaredField.setAccessible(true);
            declaredField.set(context, classLoader);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Error setting ClassLoader.", e);
        }
    }
}
