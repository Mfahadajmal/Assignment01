package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.core.app.NotificationCompatBuilder$Api23Impl;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static Method sAddFontWeightStyle = null;
    private static Method sCreateFromFamiliesWithDefault = null;
    private static Class sFontFamily = null;
    private static Constructor sFontFamilyCtor = null;
    private static boolean sHasInitBeenCalled = false;

    private static final File getFile$ar$ds(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
        } catch (ErrnoException unused) {
        }
        return null;
    }

    private static void init() {
        Method method;
        Class<?> cls;
        Method method2;
        if (sHasInitBeenCalled) {
            return;
        }
        sHasInitBeenCalled = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
            method = null;
            cls = null;
            method2 = null;
        }
        sFontFamilyCtor = constructor;
        sFontFamily = cls;
        sAddFontWeightStyle = method2;
        sCreateFromFamiliesWithDefault = method;
    }

    private static Object newFamily() {
        init();
        try {
            return sFontFamilyCtor.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry$ar$class_merging$ar$class_merging(Context context, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, Resources resources, int i) {
        Object obj = collectionItemInfoCompat.mInfo;
        Object newFamily = newFamily();
        for (FontResourcesParserCompat$FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry : (FontResourcesParserCompat$FontFileResourceEntry[]) obj) {
            File tempFile = NotificationCompatBuilder$Api23Impl.getTempFile(context);
            if (tempFile != null) {
                try {
                    if (NotificationCompatBuilder$Api23Impl.copyToFile(tempFile, resources, fontResourcesParserCompat$FontFileResourceEntry.mResourceId)) {
                        String path = tempFile.getPath();
                        int i2 = fontResourcesParserCompat$FontFileResourceEntry.mWeight;
                        boolean z = fontResourcesParserCompat$FontFileResourceEntry.mItalic;
                        init();
                        try {
                            if (((Boolean) sAddFontWeightStyle.invoke(newFamily, path, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue()) {
                                tempFile.delete();
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (RuntimeException unused) {
                } catch (Throwable th) {
                    tempFile.delete();
                    throw th;
                }
                tempFile.delete();
            }
            return null;
        }
        init();
        try {
            Object newInstance = Array.newInstance((Class<?>) sFontFamily, 1);
            Array.set(newInstance, 0, newFamily);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo$ar$ds(Context context, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        if (fontsContractCompat$FontInfoArr.length <= 0) {
            return null;
        }
        FontsContractCompat$FontInfo findBestInfo = findBestInfo(fontsContractCompat$FontInfoArr, i);
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(findBestInfo.mUri, "r", null);
            if (openFileDescriptor == null) {
                return null;
            }
            try {
                File file$ar$ds = getFile$ar$ds(openFileDescriptor);
                if (file$ar$ds != null && file$ar$ds.canRead()) {
                    Typeface createFromFile = Typeface.createFromFile(file$ar$ds);
                    openFileDescriptor.close();
                    return createFromFile;
                }
                FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                try {
                    Typeface createFromInputStream = super.createFromInputStream(context, fileInputStream);
                    fileInputStream.close();
                    openFileDescriptor.close();
                    return createFromInputStream;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
