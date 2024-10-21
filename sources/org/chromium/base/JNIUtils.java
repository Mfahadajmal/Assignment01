package org.chromium.base;

import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JNIUtils {
    private static ClassLoader getSplitClassLoader(String str) {
        ClassLoader classLoader;
        if (!str.isEmpty()) {
            boolean isIsolatedSplitInstalled = BundleUtils.isIsolatedSplitInstalled(str);
            String.format(Locale.US, "Init JNI Classloader for %s. isInstalled=%b", str, Boolean.valueOf(isIsolatedSplitInstalled));
            if (isIsolatedSplitInstalled) {
                synchronized (BundleUtils.sCachedClassLoaders) {
                    classLoader = (ClassLoader) BundleUtils.sCachedClassLoaders.get(str);
                }
                if (classLoader == null) {
                    BundleUtils.createIsolatedSplitContext(ContextUtils.sApplicationContext, str);
                    synchronized (BundleUtils.sCachedClassLoaders) {
                        classLoader = (ClassLoader) BundleUtils.sCachedClassLoaders.get(str);
                    }
                }
                return classLoader;
            }
        }
        return JNIUtils.class.getClassLoader();
    }
}
