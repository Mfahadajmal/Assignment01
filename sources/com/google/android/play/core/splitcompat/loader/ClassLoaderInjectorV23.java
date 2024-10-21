package com.google.android.play.core.splitcompat.loader;

import android.util.Log;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClassLoaderInjectorV23 implements ClassLoaderInjector {
    private final /* synthetic */ int switching_field;

    public ClassLoaderInjectorV23(int i) {
        this.switching_field = i;
    }

    static Object getDexPathListFromClassLoader(ClassLoader classLoader) {
        return NativeLibraryPathListMutex.getField$ar$class_merging$b104d96d_0$ar$class_merging(classLoader, "pathList", Object.class).get();
    }

    static boolean v21InstallDexes$ar$class_merging$ar$class_merging(ClassLoader classLoader, File file, File file2, boolean z, NativeLibraryPathListMutex nativeLibraryPathListMutex, String str, ClassLoaderInjectorV21$IsOptimized classLoaderInjectorV21$IsOptimized) {
        ArrayList arrayList = new ArrayList();
        Object dexPathListFromClassLoader = getDexPathListFromClassLoader(classLoader);
        OptionalMethod arrayField$ar$class_merging$ar$class_merging$ar$class_merging = NativeLibraryPathListMutex.getArrayField$ar$class_merging$ar$class_merging$ar$class_merging(dexPathListFromClassLoader, "dexElements", Object.class);
        List asList = Arrays.asList((Object[]) arrayField$ar$class_merging$ar$class_merging$ar$class_merging.get());
        ArrayList arrayList2 = new ArrayList();
        Iterator it = asList.iterator();
        while (it.hasNext()) {
            arrayList2.add((File) NativeLibraryPathListMutex.getField$ar$class_merging$b104d96d_0$ar$class_merging(it.next(), "path", File.class).get());
        }
        if (!arrayList2.contains(file2)) {
            if (!z && !classLoaderInjectorV21$IsOptimized.isOptimized$ar$ds(file2)) {
                Log.w("SplitCompat", "Should be optimized ".concat(String.valueOf(file2.getPath())));
                return false;
            }
            arrayField$ar$class_merging$ar$class_merging$ar$class_merging.expandArray(Arrays.asList(NativeLibraryPathListMutex.makeDexElements$ar$ds(dexPathListFromClassLoader, new ArrayList(Collections.singleton(file2)), file, arrayList)));
            if (!arrayList.isEmpty()) {
                SplitCompatLoadingException splitCompatLoadingException = new SplitCompatLoadingException("DexPathList.makeDexElement failed");
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    IOException iOException = (IOException) arrayList.get(i);
                    Log.e("SplitCompat", "DexPathList.makeDexElement failed", iOException);
                    splitCompatLoadingException.addSuppressed(iOException);
                }
                NativeLibraryPathListMutex.getArrayField$ar$class_merging$ar$class_merging$ar$class_merging(dexPathListFromClassLoader, "dexElementsSuppressedExceptions", IOException.class).expandArray(arrayList);
                throw splitCompatLoadingException;
            }
            return true;
        }
        return true;
    }

    public static void v23InstallNativeLibraries$ar$class_merging$ar$class_merging$ar$class_merging(ClassLoader classLoader, Set set, NativeLibraryPathListMutex nativeLibraryPathListMutex) {
        int length;
        if (set.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(((File) it.next()).getParentFile());
        }
        Object dexPathListFromClassLoader = getDexPathListFromClassLoader(classLoader);
        OptionalMethod field$ar$class_merging$b104d96d_0$ar$class_merging = NativeLibraryPathListMutex.getField$ar$class_merging$b104d96d_0$ar$class_merging(dexPathListFromClassLoader, "nativeLibraryDirectories", List.class);
        synchronized (NativeLibraryPathListMutex.class) {
            ArrayList arrayList = new ArrayList((Collection) field$ar$class_merging$b104d96d_0$ar$class_merging.get());
            hashSet.removeAll(arrayList);
            arrayList.addAll(hashSet);
            field$ar$class_merging$b104d96d_0$ar$class_merging.set(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        Object[] makePathElements$ar$ds = NativeLibraryPathListMutex.makePathElements$ar$ds(dexPathListFromClassLoader, new ArrayList(hashSet));
        int i = 0;
        if (!arrayList2.isEmpty()) {
            SplitCompatLoadingException splitCompatLoadingException = new SplitCompatLoadingException("Error in makePathElements");
            int size = arrayList2.size();
            while (i < size) {
                splitCompatLoadingException.addSuppressed((IOException) arrayList2.get(i));
                i++;
            }
            throw splitCompatLoadingException;
        }
        synchronized (NativeLibraryPathListMutex.class) {
            OptionalMethod arrayField$ar$class_merging$ar$class_merging$ar$class_merging = NativeLibraryPathListMutex.getArrayField$ar$class_merging$ar$class_merging$ar$class_merging(dexPathListFromClassLoader, "nativeLibraryPathElements", Object.class);
            List asList = Arrays.asList(makePathElements$ar$ds);
            Object[] objArr = (Object[]) arrayField$ar$class_merging$ar$class_merging$ar$class_merging.get();
            if (objArr == null) {
                length = 0;
            } else {
                length = objArr.length;
            }
            Object[] objArr2 = (Object[]) Array.newInstance((Class<?>) arrayField$ar$class_merging$ar$class_merging$ar$class_merging.getFieldArrayType(), length + asList.size());
            if (objArr != null) {
                System.arraycopy(objArr, 0, objArr2, asList.size(), objArr.length);
            }
            Iterator it2 = asList.iterator();
            while (it2.hasNext()) {
                objArr2[i] = it2.next();
                i++;
            }
            arrayField$ar$class_merging$ar$class_merging$ar$class_merging.set(objArr2);
        }
    }

    static boolean v26InstallDexes(ClassLoader classLoader, File file, File file2, boolean z) {
        return v21InstallDexes$ar$class_merging$ar$class_merging(classLoader, file, file2, z, new NativeLibraryPathListMutex(), "path", new ClassLoaderInjectorV28and29$1(1));
    }

    static void v26InstallNativeLibraries(ClassLoader classLoader, Set set) {
        v23InstallNativeLibraries$ar$class_merging$ar$class_merging$ar$class_merging(classLoader, set, new NativeLibraryPathListMutex());
    }

    @Override // com.google.android.play.core.splitcompat.loader.ClassLoaderInjector
    public final boolean installDexes(ClassLoader classLoader, File file, File file2, boolean z) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return v21InstallDexes$ar$class_merging$ar$class_merging(classLoader, file, file2, z, new NativeLibraryPathListMutex(), "path", new ClassLoaderInjectorV28and29$1(0));
                    }
                    return v26InstallDexes(classLoader, file, file2, z);
                }
                return v26InstallDexes(classLoader, file, file2, z);
            }
            throw null;
        }
        throw null;
    }

    @Override // com.google.android.play.core.splitcompat.loader.ClassLoaderInjector
    public final void installNativeLibraries(ClassLoader classLoader, Set set) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        v26InstallNativeLibraries(classLoader, set);
                        return;
                    } else {
                        v26InstallNativeLibraries(classLoader, set);
                        return;
                    }
                }
                v26InstallNativeLibraries(classLoader, set);
                return;
            }
            throw null;
        }
        throw null;
    }
}
