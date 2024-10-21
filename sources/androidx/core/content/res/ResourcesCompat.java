package androidx.core.content.res;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import android.util.Log;
import android.util.TypedValue;
import android.view.InputDevice;
import android.view.View;
import android.view.ViewParent;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.core.util.Supplier;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewParentCompat$Api21Impl;
import j$.util.Objects;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ResourcesCompat {
    public static final ThreadLocal sTempTypedValue = new ThreadLocal();
    public static final WeakHashMap sColorStateCaches = new WeakHashMap(0);
    public static final Object sColorStateCacheLock = new Object();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api21Impl {
        public static int getCompatFlingVelocityThreshold(Resources resources, int i, Supplier supplier, int i2) {
            int dimensionPixelSize;
            if (i != -1) {
                if (i != 0 && (dimensionPixelSize = resources.getDimensionPixelSize(i)) >= 0) {
                    return dimensionPixelSize;
                }
                return i2;
            }
            return ((Integer) supplier.get()).intValue();
        }

        public static Drawable getDrawable(Resources resources, int i, Resources.Theme theme) {
            return resources.getDrawable(i, theme);
        }

        public static Drawable getDrawableForDensity(Resources resources, int i, int i2, Resources.Theme theme) {
            return resources.getDrawableForDensity(i, i2, theme);
        }

        public static int getPlatformResId(Resources resources, String str, String str2) {
            return resources.getIdentifier(str, str2, "android");
        }

        public static boolean isInputDeviceInfoValid(int i, int i2, int i3) {
            InputDevice device = InputDevice.getDevice(i);
            if (device != null && device.getMotionRange(i2, i3) != null) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api23Impl {
        static int getColor(Resources resources, int i, Resources.Theme theme) {
            return resources.getColor(i, theme);
        }

        public static ColorStateList getColorStateList(Resources resources, int i, Resources.Theme theme) {
            return resources.getColorStateList(i, theme);
        }

        public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean z) {
            try {
                return ViewParentCompat$Api21Impl.onNestedFling(viewParent, view, f, f2, z);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(viewParent, "ViewParent ", " does not implement interface method onNestedFling"), e);
                return false;
            }
        }

        public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
            try {
                return ViewParentCompat$Api21Impl.onNestedPreFling(viewParent, view, f, f2);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(viewParent, "ViewParent ", " does not implement interface method onNestedPreFling"), e);
                return false;
            }
        }

        public static void onNestedPreScroll(ViewParent viewParent, View view, int i, int i2, int[] iArr, int i3) {
            if (viewParent instanceof NestedScrollingParent2) {
                ((NestedScrollingParent2) viewParent).onNestedPreScroll(view, i, i2, iArr, i3);
            } else if (i3 == 0) {
                try {
                    ViewParentCompat$Api21Impl.onNestedPreScroll(viewParent, view, i, i2, iArr);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(viewParent, "ViewParent ", " does not implement interface method onNestedPreScroll"), e);
                }
            }
        }

        public static void onNestedScroll(ViewParent viewParent, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            if (viewParent instanceof NestedScrollingParent3) {
                ((NestedScrollingParent3) viewParent).onNestedScroll(view, i, i2, i3, i4, i5, iArr);
                return;
            }
            iArr[0] = iArr[0] + i3;
            iArr[1] = iArr[1] + i4;
            if (viewParent instanceof NestedScrollingParent2) {
                ((NestedScrollingParent2) viewParent).onNestedScroll(view, i, i2, i3, i4, i5);
            } else if (i5 == 0) {
                try {
                    ViewParentCompat$Api21Impl.onNestedScroll(viewParent, view, i, i2, i3, i4);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(viewParent, "ViewParent ", " does not implement interface method onNestedScroll"), e);
                }
            }
        }

        public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i, int i2) {
            if (viewParent instanceof NestedScrollingParent2) {
                ((NestedScrollingParent2) viewParent).onNestedScrollAccepted(view, view2, i, i2);
            } else if (i2 == 0) {
                try {
                    ViewParentCompat$Api21Impl.onNestedScrollAccepted(viewParent, view, view2, i);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(viewParent, "ViewParent ", " does not implement interface method onNestedScrollAccepted"), e);
                }
            }
        }

        public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i, int i2) {
            if (viewParent instanceof NestedScrollingParent2) {
                return ((NestedScrollingParent2) viewParent).onStartNestedScroll(view, view2, i, i2);
            }
            if (i2 == 0) {
                try {
                    return ViewParentCompat$Api21Impl.onStartNestedScroll(viewParent, view, view2, i);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(viewParent, "ViewParent ", " does not implement interface method onStartNestedScroll"), e);
                    return false;
                }
            }
            return false;
        }

        public static void onStopNestedScroll(ViewParent viewParent, View view, int i) {
            if (viewParent instanceof NestedScrollingParent2) {
                ((NestedScrollingParent2) viewParent).onStopNestedScroll(view, i);
            } else if (i == 0) {
                try {
                    ViewParentCompat$Api21Impl.onStopNestedScroll(viewParent, view);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(viewParent, "ViewParent ", " does not implement interface method onStopNestedScroll"), e);
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ColorStateListCacheKey {
        public final Resources mResources;
        public final Resources.Theme mTheme;

        public ColorStateListCacheKey(Resources resources, Resources.Theme theme) {
            this.mResources = resources;
            this.mTheme = theme;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                ColorStateListCacheKey colorStateListCacheKey = (ColorStateListCacheKey) obj;
                if (this.mResources.equals(colorStateListCacheKey.mResources) && Objects.equals(this.mTheme, colorStateListCacheKey.mTheme)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(this.mResources, this.mTheme);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class FontCallback {
        public static Handler getHandler$ar$ds() {
            return new Handler(Looper.getMainLooper());
        }

        public final void callbackFailAsync$ar$ds(int i) {
            getHandler$ar$ds().post(new CallbackWithHandler$2(this, i, 1));
        }

        public final void callbackSuccessAsync$ar$ds(Typeface typeface) {
            getHandler$ar$ds().post(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(this, typeface, 15));
        }

        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(Typeface typeface);
    }

    public static Typeface loadFont$ar$ds(Context context, int i, TypedValue typedValue, int i2, FontCallback fontCallback, boolean z, boolean z2) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface loadFont$ar$ds$383396c_0 = loadFont$ar$ds$383396c_0(context, resources, typedValue, i, i2, fontCallback, z, z2);
        if (loadFont$ar$ds$383396c_0 == null && fontCallback == null && !z2) {
            throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
        }
        return loadFont$ar$ds$383396c_0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x01c3, code lost:
    
        if (r28 == null) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01b0, code lost:
    
        if (r3.equals(r9) == false) goto L93;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:180:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x017d A[Catch: IOException -> 0x02d6, XmlPullParserException -> 0x02e7, TryCatch #4 {IOException -> 0x02d6, XmlPullParserException -> 0x02e7, blocks: (B:17:0x003f, B:19:0x004b, B:20:0x004f, B:25:0x005a, B:26:0x0061, B:28:0x0062, B:30:0x0074, B:34:0x00a9, B:36:0x00b0, B:38:0x00b4, B:40:0x017d, B:42:0x0186, B:45:0x018c, B:47:0x0192, B:49:0x0198, B:52:0x019f, B:54:0x01ac, B:58:0x01b7, B:62:0x01be, B:68:0x01cb, B:69:0x01cf, B:71:0x01e1, B:73:0x01ef, B:76:0x02ab, B:77:0x01fa, B:78:0x0202, B:90:0x0221, B:92:0x0234, B:93:0x0243, B:94:0x023e, B:97:0x024e, B:98:0x024f, B:100:0x025d, B:102:0x0269, B:104:0x0274, B:106:0x0279, B:108:0x0287, B:113:0x0294, B:116:0x02a0, B:117:0x02a4, B:119:0x00c4, B:120:0x00c9, B:122:0x00d0, B:125:0x00d7, B:132:0x00e3, B:135:0x00f7, B:138:0x0106, B:141:0x0112, B:144:0x011b, B:147:0x0123, B:150:0x0136, B:151:0x0141, B:153:0x0148, B:155:0x014c, B:128:0x0158, B:162:0x015f, B:166:0x0166, B:167:0x0177, B:169:0x02ba, B:172:0x02cd, B:173:0x02d1, B:80:0x0203, B:82:0x020d, B:83:0x0210, B:88:0x0213, B:89:0x0220), top: B:16:0x003f, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x018c A[Catch: IOException -> 0x02d6, XmlPullParserException -> 0x02e7, TryCatch #4 {IOException -> 0x02d6, XmlPullParserException -> 0x02e7, blocks: (B:17:0x003f, B:19:0x004b, B:20:0x004f, B:25:0x005a, B:26:0x0061, B:28:0x0062, B:30:0x0074, B:34:0x00a9, B:36:0x00b0, B:38:0x00b4, B:40:0x017d, B:42:0x0186, B:45:0x018c, B:47:0x0192, B:49:0x0198, B:52:0x019f, B:54:0x01ac, B:58:0x01b7, B:62:0x01be, B:68:0x01cb, B:69:0x01cf, B:71:0x01e1, B:73:0x01ef, B:76:0x02ab, B:77:0x01fa, B:78:0x0202, B:90:0x0221, B:92:0x0234, B:93:0x0243, B:94:0x023e, B:97:0x024e, B:98:0x024f, B:100:0x025d, B:102:0x0269, B:104:0x0274, B:106:0x0279, B:108:0x0287, B:113:0x0294, B:116:0x02a0, B:117:0x02a4, B:119:0x00c4, B:120:0x00c9, B:122:0x00d0, B:125:0x00d7, B:132:0x00e3, B:135:0x00f7, B:138:0x0106, B:141:0x0112, B:144:0x011b, B:147:0x0123, B:150:0x0136, B:151:0x0141, B:153:0x0148, B:155:0x014c, B:128:0x0158, B:162:0x015f, B:166:0x0166, B:167:0x0177, B:169:0x02ba, B:172:0x02cd, B:173:0x02d1, B:80:0x0203, B:82:0x020d, B:83:0x0210, B:88:0x0213, B:89:0x0220), top: B:16:0x003f, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Typeface loadFont$ar$ds$383396c_0(final android.content.Context r23, android.content.res.Resources r24, android.util.TypedValue r25, int r26, final int r27, androidx.core.content.res.ResourcesCompat.FontCallback r28, boolean r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 812
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.loadFont$ar$ds$383396c_0(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.ResourcesCompat$FontCallback, boolean, boolean):android.graphics.Typeface");
    }
}
