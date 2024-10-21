package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import androidx.collection.LruCache;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypefaceCompat {
    public static final LruCache sTypefaceCache;
    public static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ResourcesCallbackAdapter extends NotificationManagerCompat.Api24Impl {
        public final ResourcesCompat.FontCallback mFontCallback;

        public ResourcesCallbackAdapter(ResourcesCompat.FontCallback fontCallback) {
            this.mFontCallback = fontCallback;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            sTypefaceCompatImpl = new TypefaceCompatApi29Impl();
        } else if (Build.VERSION.SDK_INT >= 28) {
            sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        } else {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        }
        sTypefaceCache = new LruCache(16);
    }

    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2, int i3) {
        Typeface createFromResourcesFontFile = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i, str, i3);
        if (createFromResourcesFontFile != null) {
            sTypefaceCache.put(createResourceUid(resources, i, str, i2, i3), createFromResourcesFontFile);
        }
        return createFromResourcesFontFile;
    }

    public static String createResourceUid(Resources resources, int i, String str, int i2, int i3) {
        return resources.getResourcePackageName(i) + '-' + str + '-' + i2 + '-' + i + '-' + i3;
    }
}
