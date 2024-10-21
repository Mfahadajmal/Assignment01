package com.google.android.play.core.splitcompat.loader;

import android.util.Log;
import com.google.android.play.core.splitcompat.reflectutils.SplitCompatReflectionException;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import java.io.File;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ClassLoaderInjectorV28and29$1 implements ClassLoaderInjectorV21$IsOptimized {
    private final /* synthetic */ int switching_field;

    public ClassLoaderInjectorV28and29$1(int i) {
        this.switching_field = i;
    }

    @Override // com.google.android.play.core.splitcompat.loader.ClassLoaderInjectorV21$IsOptimized
    public final boolean isOptimized$ar$ds(File file) {
        Class<?> cls;
        if (this.switching_field == 0) {
            return true;
        }
        try {
            cls = Class.forName("dalvik.system.DexFile");
            try {
            } catch (Exception e) {
                throw new SplitCompatReflectionException(String.format("Failed to invoke static method %s on type %s", "isDexOptNeeded", cls), e);
            }
        } catch (ClassNotFoundException unused) {
            Log.e("SplitCompat", "Unexpected missing dalvik.system.DexFile.");
        }
        if (!((Boolean) Boolean.class.cast(NativeLibraryPathListMutex.findMethodInClass(cls, "isDexOptNeeded", String.class).invoke(null, file.getPath()))).booleanValue()) {
            return true;
        }
        return false;
    }
}
