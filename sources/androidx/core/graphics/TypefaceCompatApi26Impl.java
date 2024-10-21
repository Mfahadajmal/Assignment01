package androidx.core.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import j$.nio.channels.DesugarChannels;
import j$.util.DesugarCollections;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class mFontFamily;
    protected final Constructor mFontFamilyCtor;
    protected final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        Method method;
        Constructor<?> constructor;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(null);
            Class<?> cls3 = Integer.TYPE;
            method2 = cls2.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls3, Boolean.TYPE, cls3, cls3, cls3, FontVariationAxis[].class);
            Class<?> cls4 = Integer.TYPE;
            method3 = cls2.getMethod("addFontFromBuffer", ByteBuffer.class, cls4, FontVariationAxis[].class, cls4, cls4);
            method4 = cls2.getMethod("freeze", null);
            method5 = cls2.getMethod("abortCreation", null);
            method = obtainCreateFromFamiliesWithDefaultMethod(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(String.valueOf(e.getClass().getName())), e);
            method = null;
            constructor = null;
            method2 = null;
            method3 = null;
            method4 = null;
            method5 = null;
        }
        this.mFontFamily = cls;
        this.mFontFamilyCtor = constructor;
        this.mAddFontFromAssetManager = method2;
        this.mAddFontFromBuffer = method3;
        this.mFreeze = method4;
        this.mAbortCreation = method5;
        this.mCreateFromFamiliesWithDefault = method;
    }

    private final void abortCreation(Object obj) {
        try {
            this.mAbortCreation.invoke(obj, null);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    private final boolean addFontFromAssetManager(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.mAddFontFromAssetManager.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private final boolean freeze(Object obj) {
        try {
            return ((Boolean) this.mFreeze.invoke(obj, null)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private final boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (this.mAddFontFromAssetManager != null) {
            return true;
        }
        return false;
    }

    private final Object newFamily() {
        try {
            return this.mFontFamilyCtor.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    protected Typeface createFromFamiliesWithDefault(Object obj) {
        try {
            Object newInstance = Array.newInstance((Class<?>) this.mFontFamily, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.mCreateFromFamiliesWithDefault.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public final Typeface createFromFontFamilyFilesResourceEntry$ar$class_merging$ar$class_merging(Context context, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry$ar$class_merging$ar$class_merging(context, collectionItemInfoCompat, resources, i);
        }
        Object newFamily = newFamily();
        if (newFamily != null) {
            for (FontResourcesParserCompat$FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry : (FontResourcesParserCompat$FontFileResourceEntry[]) collectionItemInfoCompat.mInfo) {
                if (!addFontFromAssetManager(context, newFamily, fontResourcesParserCompat$FontFileResourceEntry.mFileName, fontResourcesParserCompat$FontFileResourceEntry.mTtcIndex, fontResourcesParserCompat$FontFileResourceEntry.mWeight, fontResourcesParserCompat$FontFileResourceEntry.mItalic ? 1 : 0, FontVariationAxis.fromFontVariationSettings(fontResourcesParserCompat$FontFileResourceEntry.mVariationSettings))) {
                    abortCreation(newFamily);
                    return null;
                }
            }
            if (freeze(newFamily)) {
                return createFromFamiliesWithDefault(newFamily);
            }
        }
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatApi21Impl, androidx.core.graphics.TypefaceCompatBaseImpl
    public final Typeface createFromFontInfo$ar$ds(Context context, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        Typeface createFromFamiliesWithDefault;
        MappedByteBuffer mappedByteBuffer;
        ParcelFileDescriptor openFileDescriptor;
        if (fontsContractCompat$FontInfoArr.length <= 0) {
            return null;
        }
        if (!isFontFamilyPrivateAPIAvailable()) {
            FontsContractCompat$FontInfo findBestInfo = findBestInfo(fontsContractCompat$FontInfoArr, i);
            try {
                ParcelFileDescriptor openFileDescriptor2 = context.getContentResolver().openFileDescriptor(findBestInfo.mUri, "r", null);
                if (openFileDescriptor2 == null) {
                    return null;
                }
                try {
                    Typeface build = new Typeface.Builder(openFileDescriptor2.getFileDescriptor()).setWeight(findBestInfo.mWeight).setItalic(findBestInfo.mItalic).build();
                    openFileDescriptor2.close();
                    return build;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        } else {
            HashMap hashMap = new HashMap();
            for (FontsContractCompat$FontInfo fontsContractCompat$FontInfo : fontsContractCompat$FontInfoArr) {
                if (fontsContractCompat$FontInfo.mResultCode == 0) {
                    Uri uri = fontsContractCompat$FontInfo.mUri;
                    if (hashMap.containsKey(uri)) {
                        continue;
                    } else {
                        try {
                            openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", null);
                        } catch (IOException unused2) {
                        }
                        if (openFileDescriptor != null) {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                                try {
                                    FileChannel convertMaybeLegacyFileChannelFromLibrary = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(fileInputStream.getChannel());
                                    mappedByteBuffer = convertMaybeLegacyFileChannelFromLibrary.map(FileChannel.MapMode.READ_ONLY, 0L, convertMaybeLegacyFileChannelFromLibrary.size());
                                    fileInputStream.close();
                                    openFileDescriptor.close();
                                    hashMap.put(uri, mappedByteBuffer);
                                } finally {
                                    break;
                                }
                            } finally {
                                break;
                            }
                        }
                        mappedByteBuffer = null;
                        hashMap.put(uri, mappedByteBuffer);
                    }
                }
            }
            Map unmodifiableMap = DesugarCollections.unmodifiableMap(hashMap);
            Object newFamily = newFamily();
            if (newFamily == null) {
                return null;
            }
            int length = fontsContractCompat$FontInfoArr.length;
            int i2 = 0;
            boolean z = false;
            while (i2 < length) {
                FontsContractCompat$FontInfo fontsContractCompat$FontInfo2 = fontsContractCompat$FontInfoArr[i2];
                ByteBuffer byteBuffer = (ByteBuffer) unmodifiableMap.get(fontsContractCompat$FontInfo2.mUri);
                if (byteBuffer != null) {
                    if (((Boolean) this.mAddFontFromBuffer.invoke(newFamily, byteBuffer, Integer.valueOf(fontsContractCompat$FontInfo2.mTtcIndex), null, Integer.valueOf(fontsContractCompat$FontInfo2.mWeight), Integer.valueOf(fontsContractCompat$FontInfo2.mItalic ? 1 : 0))).booleanValue()) {
                        z = true;
                    } else {
                        abortCreation(newFamily);
                        return null;
                    }
                }
                i2++;
                z = z;
            }
            if (!z) {
                abortCreation(newFamily);
                return null;
            }
            if (!freeze(newFamily) || (createFromFamiliesWithDefault = createFromFamiliesWithDefault(newFamily)) == null) {
                return null;
            }
            return Typeface.create(createFromFamiliesWithDefault, i);
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public final Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, i, str, i2);
        }
        Object newFamily = newFamily();
        if (newFamily != null) {
            if (!addFontFromAssetManager(context, newFamily, str, 0, -1, -1, null)) {
                abortCreation(newFamily);
                return null;
            }
            if (freeze(newFamily)) {
                return createFromFamiliesWithDefault(newFamily);
            }
        }
        return null;
    }

    protected Method obtainCreateFromFamiliesWithDefaultMethod(Class cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
