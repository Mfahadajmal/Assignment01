package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.TypedValue;
import androidx.collection.LongSparseArray;
import androidx.collection.LongSparseArrayKt;
import androidx.collection.LruCache;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.collection.internal.ContainerHelpersKt;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.navigation.NavArgument;
import com.google.android.marvin.talkback.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ResourceManagerInternal {
    private static ResourceManagerInternal INSTANCE;
    private final WeakHashMap mDrawableCaches = new WeakHashMap(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private AppCompatDrawableManager.AnonymousClass1 mHooks$ar$class_merging;
    private WeakHashMap mTintLists;
    private TypedValue mTypedValue;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static final LruCache COLOR_FILTER_CACHE$ar$class_merging = new LruCache();

    private final synchronized void addDrawableToCache$ar$ds(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            LongSparseArray longSparseArray = (LongSparseArray) this.mDrawableCaches.get(context);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray();
                this.mDrawableCaches.put(context, longSparseArray);
            }
            longSparseArray.put(j, new WeakReference(constantState));
        }
    }

    private static long createCacheKey(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    public static synchronized ResourceManagerInternal get() {
        ResourceManagerInternal resourceManagerInternal;
        synchronized (ResourceManagerInternal.class) {
            if (INSTANCE == null) {
                INSTANCE = new ResourceManagerInternal();
            }
            resourceManagerInternal = INSTANCE;
        }
        return resourceManagerInternal;
    }

    private final synchronized Drawable getCachedDrawable(Context context, long j) {
        WeakReference weakReference;
        LongSparseArray longSparseArray = (LongSparseArray) this.mDrawableCaches.get(context);
        if (longSparseArray != null && (weakReference = (WeakReference) longSparseArray.get(j)) != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState == null) {
                int binarySearch = ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, j);
                if (binarySearch >= 0) {
                    Object[] objArr = longSparseArray.values;
                    Object obj = objArr[binarySearch];
                    Object obj2 = LongSparseArrayKt.DELETED;
                    if (obj != obj2) {
                        objArr[binarySearch] = obj2;
                        longSparseArray.garbage = true;
                    }
                }
            } else {
                return constantState.newDrawable(context.getResources());
            }
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int i, PorterDuff.Mode mode) {
        synchronized (ResourceManagerInternal.class) {
            LruCache lruCache = COLOR_FILTER_CACHE$ar$class_merging;
            PorterDuffColorFilter porterDuffColorFilter = (PorterDuffColorFilter) lruCache.get(Integer.valueOf(LruCache.generateCacheKey(i, mode)));
            if (porterDuffColorFilter == null) {
                PorterDuffColorFilter porterDuffColorFilter2 = new PorterDuffColorFilter(i, mode);
                return porterDuffColorFilter2;
            }
            return porterDuffColorFilter;
        }
    }

    private final Drawable loadDrawableFromDelegates(Context context, int i) {
        return null;
    }

    private final Drawable tintDrawable(Context context, int i, boolean z, Drawable drawable) {
        ColorStateList tintList = getTintList(context, i);
        PorterDuff.Mode mode = null;
        if (tintList != null) {
            drawable = drawable.mutate();
            DrawableCompat$Api21Impl.setTintList(drawable, tintList);
            if (this.mHooks$ar$class_merging != null && i == R.drawable.abc_switch_thumb_material) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (mode != null) {
                DrawableCompat$Api21Impl.setTintMode(drawable, mode);
                return drawable;
            }
        } else {
            if (this.mHooks$ar$class_merging != null) {
                if (i == R.drawable.abc_seekbar_track_material) {
                    LayerDrawable layerDrawable = (LayerDrawable) drawable;
                    AppCompatDrawableManager.AnonymousClass1.setPorterDuffColorFilter$ar$ds(layerDrawable.findDrawableByLayerId(android.R.id.background), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                    AppCompatDrawableManager.AnonymousClass1.setPorterDuffColorFilter$ar$ds(layerDrawable.findDrawableByLayerId(android.R.id.secondaryProgress), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                    AppCompatDrawableManager.AnonymousClass1.setPorterDuffColorFilter$ar$ds(layerDrawable.findDrawableByLayerId(android.R.id.progress), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                } else if (i == R.drawable.abc_ratingbar_material || i == R.drawable.abc_ratingbar_indicator_material || i == R.drawable.abc_ratingbar_small_material) {
                    LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                    AppCompatDrawableManager.AnonymousClass1.setPorterDuffColorFilter$ar$ds(layerDrawable2.findDrawableByLayerId(android.R.id.background), ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                    AppCompatDrawableManager.AnonymousClass1.setPorterDuffColorFilter$ar$ds(layerDrawable2.findDrawableByLayerId(android.R.id.secondaryProgress), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                    AppCompatDrawableManager.AnonymousClass1.setPorterDuffColorFilter$ar$ds(layerDrawable2.findDrawableByLayerId(android.R.id.progress), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                }
            }
            if (!tintDrawableUsingColorFilter(context, i, drawable) && z) {
                return null;
            }
        }
        return drawable;
    }

    public static void tintDrawable$ar$class_merging(Drawable drawable, NavArgument.Builder builder, int[] iArr) {
        Object obj;
        Object obj2;
        int[] state = drawable.getState();
        if (drawable.mutate() == drawable) {
            if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
                drawable.setState(new int[0]);
                drawable.setState(state);
            }
            PorterDuffColorFilter porterDuffColorFilter = null;
            if (!builder.defaultValuePresent) {
                if (builder.isNullable) {
                    obj = null;
                } else {
                    drawable.clearColorFilter();
                    return;
                }
            } else {
                obj = builder.NavArgument$Builder$ar$type;
            }
            if (builder.isNullable) {
                obj2 = builder.defaultValue;
            } else {
                obj2 = DEFAULT_MODE;
            }
            if (obj != null && obj2 != null) {
                porterDuffColorFilter = getPorterDuffColorFilter(((ColorStateList) obj).getColorForState(iArr, 0), (PorterDuff.Mode) obj2);
            }
            drawable.setColorFilter(porterDuffColorFilter);
        }
    }

    public final synchronized Drawable getDrawable(Context context, int i) {
        return getDrawable(context, i, false);
    }

    public final synchronized ColorStateList getTintList(Context context, int i) {
        ColorStateList colorStateList;
        SparseArrayCompat sparseArrayCompat;
        WeakHashMap weakHashMap = this.mTintLists;
        ColorStateList colorStateList2 = null;
        if (weakHashMap != null && (sparseArrayCompat = (SparseArrayCompat) weakHashMap.get(context)) != null) {
            colorStateList = (ColorStateList) SparseArrayCompatKt.commonGet(sparseArrayCompat, i);
        } else {
            colorStateList = null;
        }
        if (colorStateList == null) {
            AppCompatDrawableManager.AnonymousClass1 anonymousClass1 = this.mHooks$ar$class_merging;
            if (anonymousClass1 != null) {
                if (i == R.drawable.abc_edit_text_material) {
                    colorStateList2 = EditorInfoCompat.getColorStateList(context, R.color.abc_tint_edittext);
                } else if (i == R.drawable.abc_switch_track_mtrl_alpha) {
                    colorStateList2 = EditorInfoCompat.getColorStateList(context, R.color.abc_tint_switch_track);
                } else if (i == R.drawable.abc_switch_thumb_material) {
                    int[][] iArr = new int[3];
                    int[] iArr2 = new int[3];
                    ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorSwitchThumbNormal);
                    if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
                        int[] iArr3 = ThemeUtils.DISABLED_STATE_SET;
                        iArr[0] = iArr3;
                        iArr2[0] = themeAttrColorStateList.getColorForState(iArr3, 0);
                        iArr[1] = ThemeUtils.CHECKED_STATE_SET;
                        iArr2[1] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
                        iArr[2] = ThemeUtils.EMPTY_STATE_SET;
                        iArr2[2] = themeAttrColorStateList.getDefaultColor();
                    } else {
                        iArr[0] = ThemeUtils.DISABLED_STATE_SET;
                        iArr2[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
                        iArr[1] = ThemeUtils.CHECKED_STATE_SET;
                        iArr2[1] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
                        iArr[2] = ThemeUtils.EMPTY_STATE_SET;
                        iArr2[2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
                    }
                    colorStateList2 = new ColorStateList(iArr, iArr2);
                } else if (i == R.drawable.abc_btn_default_mtrl_shape) {
                    colorStateList2 = AppCompatDrawableManager.AnonymousClass1.createButtonColorStateList$ar$ds(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorButtonNormal));
                } else if (i == R.drawable.abc_btn_borderless_material) {
                    colorStateList2 = AppCompatDrawableManager.AnonymousClass1.createButtonColorStateList$ar$ds(context, 0);
                } else if (i == R.drawable.abc_btn_colored_material) {
                    colorStateList2 = AppCompatDrawableManager.AnonymousClass1.createButtonColorStateList$ar$ds(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorAccent));
                } else {
                    if (i != R.drawable.abc_spinner_mtrl_am_alpha && i != R.drawable.abc_spinner_textfield_background_material) {
                        if (AppCompatDrawableManager.AnonymousClass1.arrayContains$ar$ds(anonymousClass1.TINT_COLOR_CONTROL_NORMAL, i)) {
                            colorStateList2 = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
                        } else if (AppCompatDrawableManager.AnonymousClass1.arrayContains$ar$ds(anonymousClass1.TINT_COLOR_CONTROL_STATE_LIST, i)) {
                            colorStateList2 = EditorInfoCompat.getColorStateList(context, R.color.abc_tint_default);
                        } else if (AppCompatDrawableManager.AnonymousClass1.arrayContains$ar$ds(anonymousClass1.TINT_CHECKABLE_BUTTON_LIST, i)) {
                            colorStateList2 = EditorInfoCompat.getColorStateList(context, R.color.abc_tint_btn_checkable);
                        } else if (i == R.drawable.abc_seekbar_thumb_material) {
                            colorStateList2 = EditorInfoCompat.getColorStateList(context, R.color.abc_tint_seek_thumb);
                        }
                    }
                    colorStateList2 = EditorInfoCompat.getColorStateList(context, R.color.abc_tint_spinner);
                }
            }
            if (colorStateList2 != null) {
                if (this.mTintLists == null) {
                    this.mTintLists = new WeakHashMap();
                }
                SparseArrayCompat sparseArrayCompat2 = (SparseArrayCompat) this.mTintLists.get(context);
                if (sparseArrayCompat2 == null) {
                    sparseArrayCompat2 = new SparseArrayCompat();
                    this.mTintLists.put(context, sparseArrayCompat2);
                }
                sparseArrayCompat2.append(i, colorStateList2);
                return colorStateList2;
            }
            colorStateList = colorStateList2;
        }
        return colorStateList;
    }

    public final synchronized void onConfigurationChanged(Context context) {
        LongSparseArray longSparseArray = (LongSparseArray) this.mDrawableCaches.get(context);
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }

    public final synchronized void setHooks$ar$class_merging(AppCompatDrawableManager.AnonymousClass1 anonymousClass1) {
        this.mHooks$ar$class_merging = anonymousClass1;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final boolean tintDrawableUsingColorFilter(android.content.Context r8, int r9, android.graphics.drawable.Drawable r10) {
        /*
            r7 = this;
            android.support.v7.widget.AppCompatDrawableManager$1 r0 = r7.mHooks$ar$class_merging
            r1 = 0
            if (r0 == 0) goto L66
            int[] r2 = r0.COLORFILTER_TINT_COLOR_CONTROL_NORMAL
            android.graphics.PorterDuff$Mode r3 = android.support.v7.widget.AppCompatDrawableManager.DEFAULT_MODE
            boolean r2 = android.support.v7.widget.AppCompatDrawableManager.AnonymousClass1.arrayContains$ar$ds(r2, r9)
            r4 = -1
            r5 = 1
            if (r2 == 0) goto L17
            r9 = 2130968853(0x7f040115, float:1.7546371E38)
        L14:
            r0 = r4
            r2 = r5
            goto L4f
        L17:
            int[] r2 = r0.COLORFILTER_COLOR_CONTROL_ACTIVATED
            boolean r2 = android.support.v7.widget.AppCompatDrawableManager.AnonymousClass1.arrayContains$ar$ds(r2, r9)
            if (r2 == 0) goto L23
            r9 = 2130968851(0x7f040113, float:1.7546367E38)
            goto L14
        L23:
            int[] r0 = r0.COLORFILTER_COLOR_BACKGROUND_MULTIPLY
            boolean r0 = android.support.v7.widget.AppCompatDrawableManager.AnonymousClass1.arrayContains$ar$ds(r0, r9)
            r2 = 16842801(0x1010031, float:2.3693695E-38)
            if (r0 == 0) goto L32
            android.graphics.PorterDuff$Mode r3 = android.graphics.PorterDuff.Mode.MULTIPLY
        L30:
            r9 = r2
            goto L14
        L32:
            r0 = 2131230850(0x7f080082, float:1.8077764E38)
            if (r9 != r0) goto L46
            r9 = 1109603123(0x42233333, float:40.8)
            int r9 = java.lang.Math.round(r9)
            r0 = 16842800(0x1010030, float:2.3693693E-38)
            r2 = r5
            r6 = r0
            r0 = r9
            r9 = r6
            goto L4f
        L46:
            r0 = 2131230832(0x7f080070, float:1.8077728E38)
            if (r9 != r0) goto L4c
            goto L30
        L4c:
            r9 = r1
            r2 = r9
            r0 = r4
        L4f:
            if (r2 == 0) goto L66
            android.graphics.drawable.Drawable r10 = r10.mutate()
            int r8 = android.support.v7.widget.ThemeUtils.getThemeAttrColor(r8, r9)
            android.graphics.PorterDuffColorFilter r8 = android.support.v7.widget.AppCompatDrawableManager.getPorterDuffColorFilter(r8, r3)
            r10.setColorFilter(r8)
            if (r0 == r4) goto L65
            r10.setAlpha(r0)
        L65:
            return r5
        L66:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ResourceManagerInternal.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x009b A[Catch: all -> 0x00c3, TryCatch #0 {, blocks: (B:3:0x0001, B:6:0x0027, B:8:0x002d, B:10:0x0031, B:11:0x0038, B:13:0x004b, B:17:0x009b, B:21:0x0057, B:24:0x0078, B:27:0x0085, B:30:0x0092, B:33:0x00a8, B:35:0x00ae, B:37:0x00b4, B:41:0x0008, B:43:0x0013, B:45:0x0017, B:47:0x00b9, B:48:0x00c2), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized android.graphics.drawable.Drawable getDrawable(android.content.Context r10, int r11, boolean r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            boolean r0 = r9.mHasCheckedVectorDrawableSetup     // Catch: java.lang.Throwable -> Lc3
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L8
            goto L27
        L8:
            r9.mHasCheckedVectorDrawableSetup = r2     // Catch: java.lang.Throwable -> Lc3
            r0 = 2131230891(0x7f0800ab, float:1.8077848E38)
            android.graphics.drawable.Drawable r0 = r9.getDrawable(r10, r0)     // Catch: java.lang.Throwable -> Lc3
            if (r0 == 0) goto Lb9
            boolean r3 = r0 instanceof androidx.vectordrawable.graphics.drawable.VectorDrawableCompat     // Catch: java.lang.Throwable -> Lc3
            if (r3 != 0) goto L27
            java.lang.Class r0 = r0.getClass()     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r3 = "android.graphics.drawable.VectorDrawable"
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> Lc3
            boolean r0 = r3.equals(r0)     // Catch: java.lang.Throwable -> Lc3
            if (r0 == 0) goto Lb9
        L27:
            android.graphics.drawable.Drawable r0 = r9.loadDrawableFromDelegates(r10, r11)     // Catch: java.lang.Throwable -> Lc3
            if (r0 != 0) goto La6
            android.util.TypedValue r0 = r9.mTypedValue     // Catch: java.lang.Throwable -> Lc3
            if (r0 != 0) goto L38
            android.util.TypedValue r0 = new android.util.TypedValue     // Catch: java.lang.Throwable -> Lc3
            r0.<init>()     // Catch: java.lang.Throwable -> Lc3
            r9.mTypedValue = r0     // Catch: java.lang.Throwable -> Lc3
        L38:
            android.util.TypedValue r0 = r9.mTypedValue     // Catch: java.lang.Throwable -> Lc3
            android.content.res.Resources r3 = r10.getResources()     // Catch: java.lang.Throwable -> Lc3
            r3.getValue(r11, r0, r2)     // Catch: java.lang.Throwable -> Lc3
            long r3 = createCacheKey(r0)     // Catch: java.lang.Throwable -> Lc3
            android.graphics.drawable.Drawable r5 = r9.getCachedDrawable(r10, r3)     // Catch: java.lang.Throwable -> Lc3
            if (r5 != 0) goto La5
            android.support.v7.widget.AppCompatDrawableManager$1 r5 = r9.mHooks$ar$class_merging     // Catch: java.lang.Throwable -> Lc3
            r6 = 0
            if (r5 != 0) goto L52
        L50:
            r1 = r6
            goto L99
        L52:
            r5 = 2131230829(0x7f08006d, float:1.8077722E38)
            if (r11 != r5) goto L73
            android.graphics.drawable.LayerDrawable r5 = new android.graphics.drawable.LayerDrawable     // Catch: java.lang.Throwable -> Lc3
            r6 = 2131230828(0x7f08006c, float:1.807772E38)
            android.graphics.drawable.Drawable r6 = r9.getDrawable(r10, r6)     // Catch: java.lang.Throwable -> Lc3
            r7 = 2131230830(0x7f08006e, float:1.8077724E38)
            android.graphics.drawable.Drawable r7 = r9.getDrawable(r10, r7)     // Catch: java.lang.Throwable -> Lc3
            r8 = 2
            android.graphics.drawable.Drawable[] r8 = new android.graphics.drawable.Drawable[r8]     // Catch: java.lang.Throwable -> Lc3
            r8[r1] = r6     // Catch: java.lang.Throwable -> Lc3
            r8[r2] = r7     // Catch: java.lang.Throwable -> Lc3
            r5.<init>(r8)     // Catch: java.lang.Throwable -> Lc3
            r1 = r5
            goto L99
        L73:
            r1 = 2131230864(0x7f080090, float:1.8077793E38)
            if (r11 != r1) goto L80
            r1 = 2131165247(0x7f07003f, float:1.7944706E38)
            android.graphics.drawable.LayerDrawable r1 = android.support.v7.widget.AppCompatDrawableManager.AnonymousClass1.getRatingBarLayerDrawable$ar$ds(r9, r10, r1)     // Catch: java.lang.Throwable -> Lc3
            goto L99
        L80:
            r1 = 2131230863(0x7f08008f, float:1.807779E38)
            if (r11 != r1) goto L8d
            r1 = 2131165248(0x7f070040, float:1.7944708E38)
            android.graphics.drawable.LayerDrawable r1 = android.support.v7.widget.AppCompatDrawableManager.AnonymousClass1.getRatingBarLayerDrawable$ar$ds(r9, r10, r1)     // Catch: java.lang.Throwable -> Lc3
            goto L99
        L8d:
            r1 = 2131230865(0x7f080091, float:1.8077795E38)
            if (r11 != r1) goto L50
            r1 = 2131165249(0x7f070041, float:1.794471E38)
            android.graphics.drawable.LayerDrawable r1 = android.support.v7.widget.AppCompatDrawableManager.AnonymousClass1.getRatingBarLayerDrawable$ar$ds(r9, r10, r1)     // Catch: java.lang.Throwable -> Lc3
        L99:
            if (r1 == 0) goto La3
            int r0 = r0.changingConfigurations     // Catch: java.lang.Throwable -> Lc3
            r1.setChangingConfigurations(r0)     // Catch: java.lang.Throwable -> Lc3
            r9.addDrawableToCache$ar$ds(r10, r3, r1)     // Catch: java.lang.Throwable -> Lc3
        La3:
            r0 = r1
            goto La6
        La5:
            r0 = r5
        La6:
            if (r0 != 0) goto Lac
            android.graphics.drawable.Drawable r0 = androidx.core.content.ContextCompat$Api21Impl.getDrawable(r10, r11)     // Catch: java.lang.Throwable -> Lc3
        Lac:
            if (r0 == 0) goto Lb2
            android.graphics.drawable.Drawable r0 = r9.tintDrawable(r10, r11, r12, r0)     // Catch: java.lang.Throwable -> Lc3
        Lb2:
            if (r0 == 0) goto Lb7
            android.support.v7.widget.DrawableUtils.fixDrawable(r0)     // Catch: java.lang.Throwable -> Lc3
        Lb7:
            monitor-exit(r9)
            return r0
        Lb9:
            r9.mHasCheckedVectorDrawableSetup = r1     // Catch: java.lang.Throwable -> Lc3
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lc3
            java.lang.String r11 = "This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat."
            r10.<init>(r11)     // Catch: java.lang.Throwable -> Lc3
            throw r10     // Catch: java.lang.Throwable -> Lc3
        Lc3:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ResourceManagerInternal.getDrawable(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }
}
