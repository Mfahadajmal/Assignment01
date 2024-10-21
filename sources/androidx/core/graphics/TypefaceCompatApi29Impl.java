package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.ParcelFileDescriptor;
import androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat$FontInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    private static final Font findBaseFont$ar$ds(FontFamily fontFamily, int i) {
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        if (1 != (i & 1)) {
            i2 = 400;
        } else {
            i2 = 700;
        }
        if ((i & 2) != 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        FontStyle fontStyle = new FontStyle(i2, i3);
        Font font = fontFamily.getFont(0);
        int matchScore = getMatchScore(fontStyle, font.getStyle());
        while (i5 < fontFamily.getSize()) {
            Font font2 = fontFamily.getFont(i5);
            int matchScore2 = getMatchScore(fontStyle, font2.getStyle());
            if (matchScore2 < matchScore) {
                i4 = matchScore2;
            } else {
                i4 = matchScore;
            }
            if (matchScore2 < matchScore) {
                font = font2;
            }
            i5++;
            matchScore = i4;
        }
        return font;
    }

    private static int getMatchScore(FontStyle fontStyle, FontStyle fontStyle2) {
        int i;
        int abs = Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100;
        if (fontStyle.getSlant() == fontStyle2.getSlant()) {
            i = 0;
        } else {
            i = 2;
        }
        return abs + i;
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public final Typeface createFromFontFamilyFilesResourceEntry$ar$class_merging$ar$class_merging(Context context, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, Resources resources, int i) {
        try {
            Object obj = collectionItemInfoCompat.mInfo;
            int length = ((FontResourcesParserCompat$FontFileResourceEntry[]) obj).length;
            FontFamily.Builder builder = null;
            for (int i2 = 0; i2 < length; i2++) {
                FontResourcesParserCompat$FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry = ((FontResourcesParserCompat$FontFileResourceEntry[]) obj)[i2];
                try {
                    Font build = new Font.Builder(resources, fontResourcesParserCompat$FontFileResourceEntry.mResourceId).setWeight(fontResourcesParserCompat$FontFileResourceEntry.mWeight).setSlant(fontResourcesParserCompat$FontFileResourceEntry.mItalic ? 1 : 0).setTtcIndex(fontResourcesParserCompat$FontFileResourceEntry.mTtcIndex).setFontVariationSettings(fontResourcesParserCompat$FontFileResourceEntry.mVariationSettings).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(findBaseFont$ar$ds(build2, i).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public final Typeface createFromFontInfo$ar$ds(Context context, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily.Builder builder = null;
            for (FontsContractCompat$FontInfo fontsContractCompat$FontInfo : fontsContractCompat$FontInfoArr) {
                try {
                    ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(fontsContractCompat$FontInfo.mUri, "r", null);
                    if (openFileDescriptor != null) {
                        try {
                            Font build = new Font.Builder(openFileDescriptor).setWeight(fontsContractCompat$FontInfo.mWeight).setSlant(fontsContractCompat$FontInfo.mItalic ? 1 : 0).setTtcIndex(fontsContractCompat$FontInfo.mTtcIndex).build();
                            if (builder == null) {
                                builder = new FontFamily.Builder(build);
                            } else {
                                builder.addFont(build);
                            }
                            openFileDescriptor.close();
                        } catch (Throwable th) {
                            try {
                                openFileDescriptor.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily build2 = builder.build();
            return new Typeface.CustomFallbackBuilder(build2).setStyle(findBaseFont$ar$ds(build2, i).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public final Typeface createFromInputStream(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public final Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font build = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public final FontsContractCompat$FontInfo findBestInfo(FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
