package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import androidx.core.app.NotificationCompatBuilder$Api23Impl;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.Preference;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TypefaceCompatBaseImpl {
    private final ConcurrentHashMap mFontFamilies = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: androidx.core.graphics.TypefaceCompatBaseImpl$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements StyleExtractor {
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i) {
            this.switching_field = i;
        }

        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public final /* synthetic */ int getWeight(Object obj) {
            if (this.switching_field != 0) {
                return ((FontsContractCompat$FontInfo) obj).mWeight;
            }
            return ((FontResourcesParserCompat$FontFileResourceEntry) obj).mWeight;
        }

        @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public final /* synthetic */ boolean isItalic(Object obj) {
            if (this.switching_field != 0) {
                return ((FontsContractCompat$FontInfo) obj).mItalic;
            }
            return ((FontResourcesParserCompat$FontFileResourceEntry) obj).mItalic;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface StyleExtractor {
        int getWeight(Object obj);

        boolean isItalic(Object obj);
    }

    private static Object findBestFont(Object[] objArr, int i, StyleExtractor styleExtractor) {
        boolean z;
        int i2;
        int i3 = Preference.DEFAULT_ORDER;
        Object obj = null;
        for (Object obj2 : objArr) {
            int i4 = 1;
            int i5 = i & 1;
            if ((i & 2) == 0) {
                z = false;
            } else {
                z = true;
            }
            if (1 != i5) {
                i2 = 400;
            } else {
                i2 = 700;
            }
            int abs = Math.abs(styleExtractor.getWeight(obj2) - i2);
            int i6 = abs + abs;
            if (styleExtractor.isItalic(obj2) == z) {
                i4 = 0;
            }
            int i7 = i6 + i4;
            if (obj == null || i3 > i7) {
                i3 = i7;
                obj = obj2;
            }
        }
        return obj;
    }

    private static long getUniqueKey(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e);
            return 0L;
        } catch (NoSuchFieldException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0L;
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry$ar$class_merging$ar$class_merging(Context context, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, Resources resources, int i) {
        FontResourcesParserCompat$FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry = (FontResourcesParserCompat$FontFileResourceEntry) findBestFont((Object[]) collectionItemInfoCompat.mInfo, i, new AnonymousClass2(0));
        if (fontResourcesParserCompat$FontFileResourceEntry == null) {
            return null;
        }
        Typeface createFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, fontResourcesParserCompat$FontFileResourceEntry.mResourceId, fontResourcesParserCompat$FontFileResourceEntry.mFileName, 0, i);
        long uniqueKey = getUniqueKey(createFromResourcesFontFile);
        if (uniqueKey != 0) {
            this.mFontFamilies.put(Long.valueOf(uniqueKey), collectionItemInfoCompat);
        }
        return createFromResourcesFontFile;
    }

    public Typeface createFromFontInfo$ar$ds(Context context, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (fontsContractCompat$FontInfoArr.length <= 0) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(findBestInfo(fontsContractCompat$FontInfoArr, i).mUri);
            try {
                Typeface createFromInputStream = createFromInputStream(context, inputStream);
                NotificationCompatBuilder$Api23Impl.closeQuietly(inputStream);
                return createFromInputStream;
            } catch (IOException unused) {
                NotificationCompatBuilder$Api23Impl.closeQuietly(inputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                NotificationCompatBuilder$Api23Impl.closeQuietly(inputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        File tempFile = NotificationCompatBuilder$Api23Impl.getTempFile(context);
        Typeface typeface = null;
        if (tempFile == null) {
            return null;
        }
        try {
            if (NotificationCompatBuilder$Api23Impl.copyToFile(tempFile, inputStream)) {
                typeface = Typeface.createFromFile(tempFile.getPath());
            }
        } catch (RuntimeException unused) {
        } catch (Throwable th) {
            tempFile.delete();
            throw th;
        }
        tempFile.delete();
        return typeface;
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        File tempFile = NotificationCompatBuilder$Api23Impl.getTempFile(context);
        Typeface typeface = null;
        if (tempFile == null) {
            return null;
        }
        try {
            if (NotificationCompatBuilder$Api23Impl.copyToFile(tempFile, resources, i)) {
                typeface = Typeface.createFromFile(tempFile.getPath());
            }
        } catch (RuntimeException unused) {
        } catch (Throwable th) {
            tempFile.delete();
            throw th;
        }
        tempFile.delete();
        return typeface;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FontsContractCompat$FontInfo findBestInfo(FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        return (FontsContractCompat$FontInfo) findBestFont(fontsContractCompat$FontInfoArr, i, new AnonymousClass2(1));
    }
}
