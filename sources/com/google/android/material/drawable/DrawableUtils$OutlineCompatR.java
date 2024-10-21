package com.google.android.material.drawable;

import _COROUTINE._BOUNDARY;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.resources.R$styleable;
import com.google.android.material.resources.TextAppearance;
import com.google.common.util.concurrent.ExecutionList;
import java.io.IOException;
import java.util.Locale;
import org.chromium.base.PathUtils$$ExternalSyntheticApiModelOutline0;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DrawableUtils$OutlineCompatR {
    public static final float calculateScaleX$ar$ds(float f) {
        TimeInterpolator timeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        return (f * 0.6f) + 0.4f;
    }

    public static Drawable createTintableMutatedDrawableIfNeeded(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            Drawable mutate = drawable.mutate();
            if (mode != null) {
                DrawableCompat$Api21Impl.setTintMode(mutate, mode);
                return mutate;
            }
            return mutate;
        }
        return drawable;
    }

    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, int i) {
        int resourceId;
        ColorStateList colorStateList;
        if (typedArray.hasValue(i) && (resourceId = typedArray.getResourceId(i, 0)) != 0 && (colorStateList = EditorInfoCompat.getColorStateList(context, resourceId)) != null) {
            return colorStateList;
        }
        return typedArray.getColorStateList(i);
    }

    public static ColorStateList getColorStateList$ar$class_merging$ar$class_merging$ar$class_merging(Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, int i) {
        int resourceId;
        ColorStateList colorStateList;
        if (runnableExecutorPair.hasValue(i) && (resourceId = runnableExecutorPair.getResourceId(i, 0)) != 0 && (colorStateList = EditorInfoCompat.getColorStateList(context, resourceId)) != null) {
            return colorStateList;
        }
        return runnableExecutorPair.getColorStateList(i);
    }

    public static ColorStateList getColorStateListOrNull(Drawable drawable) {
        ColorStateList colorStateList;
        if (drawable instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (Build.VERSION.SDK_INT >= 29 && PathUtils$$ExternalSyntheticApiModelOutline0.m290m((Object) drawable)) {
            colorStateList = PathUtils$$ExternalSyntheticApiModelOutline0.m((Object) drawable).getColorStateList();
            return colorStateList;
        }
        return null;
    }

    public static Drawable getDrawable(Context context, TypedArray typedArray, int i) {
        int resourceId;
        Drawable drawable;
        if (typedArray.hasValue(i) && (resourceId = typedArray.getResourceId(i, 0)) != 0 && (drawable = AppCompatDelegate.Api33Impl.getDrawable(context, resourceId)) != null) {
            return drawable;
        }
        return typedArray.getDrawable(i);
    }

    public static TextAppearance getTextAppearance$ar$ds(Context context, TypedArray typedArray) {
        int resourceId;
        if (typedArray.hasValue(0) && (resourceId = typedArray.getResourceId(0, 0)) != 0) {
            return new TextAppearance(context, resourceId);
        }
        return null;
    }

    public static int getUnscaledLineHeight$ar$ds(Context context, int i) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
            TypedValue typedValue = new TypedValue();
            boolean value = obtainStyledAttributes.getValue(2, typedValue);
            if (!value) {
                value = obtainStyledAttributes.getValue(1, typedValue);
            }
            obtainStyledAttributes.recycle();
            if (value) {
                if (typedValue.getComplexUnit() == 2) {
                    return Math.round(TypedValue.complexToFloat(typedValue.data) * context.getResources().getDisplayMetrics().density);
                }
                return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            }
            return 0;
        }
        return 0;
    }

    public static boolean isFontScaleAtLeast1_3(Context context) {
        if (context.getResources().getConfiguration().fontScale >= 1.3f) {
            return true;
        }
        return false;
    }

    public static boolean isFontScaleAtLeast2_0(Context context) {
        if (context.getResources().getConfiguration().fontScale >= 2.0f) {
            return true;
        }
        return false;
    }

    public static boolean isMeizuDevice() {
        String str;
        String str2 = Build.MANUFACTURER;
        if (str2 != null) {
            str = str2.toLowerCase(Locale.ENGLISH);
        } else {
            str = "";
        }
        return str.equals("meizu");
    }

    public static boolean isUsingLightSystemBar(int i, boolean z) {
        if (FileUtils.isColorLight(i)) {
            return true;
        }
        if (i == 0 && z) {
            return true;
        }
        return false;
    }

    public static Typeface maybeCopyWithFontWeightAdjustment(Configuration configuration, Typeface typeface) {
        int i;
        int i2;
        int weight;
        int i3;
        Typeface create;
        if (Build.VERSION.SDK_INT >= 31) {
            i = configuration.fontWeightAdjustment;
            if (i != Integer.MAX_VALUE) {
                i2 = configuration.fontWeightAdjustment;
                if (i2 != 0 && typeface != null) {
                    weight = typeface.getWeight();
                    i3 = configuration.fontWeightAdjustment;
                    create = Typeface.create(typeface, NotificationCompatBuilder$Api26Impl.clamp(weight + i3, 1, 1000), typeface.isItalic());
                    return create;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public static AttributeSet parseDrawableXml$ar$ds(Context context, int i) {
        int next;
        try {
            XmlResourceParser xml = context.getResources().getXml(i);
            do {
                next = xml.next();
                if (next == 2) {
                    if (TextUtils.equals(xml.getName(), "badge")) {
                        return Xml.asAttributeSet(xml);
                    }
                    throw new XmlPullParserException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4("badge", "Must have a <", "> start tag"));
                }
            } while (next != 1);
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException e) {
            Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x".concat(String.valueOf(Integer.toHexString(i))));
            notFoundException.initCause(e);
            throw notFoundException;
        }
    }

    public static TypedValue resolve(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean resolveBoolean(Context context, int i, boolean z) {
        TypedValue resolve = resolve(context, i);
        if (resolve != null && resolve.type == 18) {
            if (resolve.data == 0) {
                return false;
            }
            return true;
        }
        return z;
    }

    public static int resolveInteger(Context context, int i, int i2) {
        TypedValue resolve = resolve(context, i);
        if (resolve != null && resolve.type == 16) {
            return resolve.data;
        }
        return i2;
    }

    public static TypedValue resolveTypedValueOrThrow(Context context, int i, String str) {
        TypedValue resolve = resolve(context, i);
        if (resolve != null) {
            return resolve;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i)));
    }

    public static void setOutlineToPath(Outline outline, Path path) {
        if (Build.VERSION.SDK_INT >= 30) {
            setPath(outline, path);
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                DrawableUtils$OutlineCompatL.setConvexPath(outline, path);
            } catch (IllegalArgumentException unused) {
            }
        } else if (path.isConvex()) {
            DrawableUtils$OutlineCompatL.setConvexPath(outline, path);
        }
    }

    static void setPath(Outline outline, Path path) {
        outline.setPath(path);
    }

    public static PorterDuffColorFilter updateTintFilter(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList != null && mode != null) {
            return new PorterDuffColorFilter(colorStateList.getColorForState(drawable.getState(), 0), mode);
        }
        return null;
    }

    public float calculateScaleY(float f, float f2) {
        return 1.0f;
    }
}
