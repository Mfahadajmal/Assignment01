package _COROUTINE;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Size;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.work.impl.model.WorkName;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.talkback.compositor.CompositorUtils;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.hint.ClickableHint;
import com.google.android.accessibility.talkback.compositor.hint.LongClickableHint;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import j$.util.Optional;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class _BOUNDARY {
    public static /* synthetic */ int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(int i) {
        if (i != 0) {
            return i;
        }
        throw null;
    }

    public static /* synthetic */ PorterDuff.Mode ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(int i, PorterDuff.Mode mode) {
        if (i != 3) {
            if (i != 5) {
                if (i != 9) {
                    switch (i) {
                        case 14:
                            return PorterDuff.Mode.MULTIPLY;
                        case 15:
                            return PorterDuff.Mode.SCREEN;
                        case 16:
                            return PorterDuff.Mode.ADD;
                        default:
                            return mode;
                    }
                }
                return PorterDuff.Mode.SRC_ATOP;
            }
            return PorterDuff.Mode.SRC_IN;
        }
        return PorterDuff.Mode.SRC_OVER;
    }

    public static /* synthetic */ int ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(boolean z) {
        if (z) {
            return 1231;
        }
        return 1237;
    }

    public static /* synthetic */ boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_12(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                return true;
            }
            if (mode != 1073741824 || size != i) {
                return false;
            }
            return true;
        }
        if (size < i) {
            return false;
        }
        return true;
    }

    public static /* synthetic */ boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_13(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Object obj, Object obj2, Object obj3) {
        while (!atomicReferenceFieldUpdater.compareAndSet(obj, obj2, obj3)) {
            if (atomicReferenceFieldUpdater.get(obj) != obj2) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ Object ArtificialStackFrames$ar$MethodMerging$dc56d17a_14(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static /* synthetic */ boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(AtomicReference atomicReference, Object obj) {
        while (!atomicReference.compareAndSet(null, obj)) {
            if (atomicReference.get() != null) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ String ArtificialStackFrames$ar$MethodMerging$dc56d17a_2(int i) {
        switch (i) {
            case 2:
                return "LEFT";
            case 3:
                return "TOP";
            case 4:
                return "RIGHT";
            case 5:
                return "BOTTOM";
            case 6:
                return "BASELINE";
            case 7:
                return "CENTER";
            case 8:
                return "CENTER_X";
            default:
                return "CENTER_Y";
        }
    }

    public static /* synthetic */ boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() {
        if (Build.VERSION.SDK_INT >= 31) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ String ArtificialStackFrames$ar$MethodMerging$dc56d17a_22(String str) {
        if (!TextUtils.isEmpty(str) && !Character.isUpperCase(str.charAt(0))) {
            return String.valueOf(str.substring(0, 1).toUpperCase(Locale.getDefault())).concat(String.valueOf(str.substring(1)));
        }
        return str;
    }

    public static /* synthetic */ Object ArtificialStackFrames$ar$MethodMerging$dc56d17a_23(Object obj) {
        if (obj instanceof AccessibilityNodeInfoCompat) {
            return String.valueOf(AccessibilityNodeInfoUtils.toStringShort((AccessibilityNodeInfoCompat) obj)).concat("    ");
        }
        if (obj instanceof AccessibilityNodeInfo) {
            return String.valueOf(AccessibilityNodeInfoUtils.toStringShort(AccessibilityNodeInfoUtils.toCompat((AccessibilityNodeInfo) obj))).concat("    ");
        }
        if (obj instanceof AccessibilityEvent) {
            return String.valueOf(SpannableUtils$IdentifierSpan.toStringShort((AccessibilityEvent) obj)).concat("    ");
        }
        return obj;
    }

    public static /* synthetic */ CharSequence ArtificialStackFrames$ar$MethodMerging$dc56d17a_25(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Context context, GlobalVariables globalVariables, ClickableHint clickableHint, LongClickableHint longClickableHint) {
        ImmutableList suggestionSpans;
        ArrayList arrayList = new ArrayList();
        if (!accessibilityNodeInfoCompat.isFocused()) {
            arrayList.add(clickableHint.getEditTextClickableHint(accessibilityNodeInfoCompat));
        }
        arrayList.add(longClickableHint.getHint(accessibilityNodeInfoCompat));
        Class cls = AccessibilityNodeInfoUtils.CLASS_TOUCHWIZ_TWADAPTERVIEW;
        if (accessibilityNodeInfoCompat == null) {
            int i = ImmutableList.ImmutableList$ar$NoOp;
            suggestionSpans = RegularImmutableList.EMPTY;
        } else {
            suggestionSpans = AccessibilityNodeInfoUtils.getSuggestionSpans(context, accessibilityNodeInfoCompat.getText(), accessibilityNodeInfoCompat.getInputType());
        }
        int i2 = ((RegularImmutableList) suggestionSpans).size;
        if (i2 > 0 && SpannableUtils$IdentifierSpan.supportTextSuggestion()) {
            arrayList.add(context.getString(R.string.template_hint_edit_text_containing_typo, Integer.valueOf(i2), globalVariables.getGestureStringForReadingMenuNextSetting()));
        }
        return CompositorUtils.joinCharSequences$ar$ds(arrayList, CompositorUtils.separator);
    }

    public static /* synthetic */ boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null && obj.equals(obj2)) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(AtomicReference atomicReference, Object obj, Object obj2) {
        while (!atomicReference.compareAndSet(obj, obj2)) {
            if (atomicReference.get() != obj) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ void ArtificialStackFrames$ar$MethodMerging$dc56d17a_6() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public static /* synthetic */ void ArtificialStackFrames$ar$MethodMerging$dc56d17a_7() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining(int i, String str, SparseIntArray sparseIntArray) {
        return str + Integer.toHexString(i) + "   " + sparseIntArray.get(i);
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_0(String str, String str2) {
        return str2 + str;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_1(Object obj, String str, String str2) {
        return str + obj + str2;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_10(Object obj, String str, String str2) {
        return str + obj.toString() + str2;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_11(Object obj, String str, String str2) {
        return str + String.valueOf(obj) + str2;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_2(Object obj, Object obj2, String str, String str2) {
        return str + obj2 + str2 + obj;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_3(int i, String str) {
        return str + i;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_4(String str, String str2, String str3) {
        return str2 + str + str3;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_5(String str, AttributeSet attributeSet, String str2) {
        return attributeSet.getPositionDescription() + str2 + str;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_6(String str, String str2, String str3) {
        return str2 + str3 + str;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_7(int i, String str, String str2) {
        return str + i + str2;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_8(long j, String str) {
        return str + j;
    }

    public static /* synthetic */ String _BOUNDARY$ar$MethodOutlining$dc56d17a_9(int i, int i2, String str, String str2) {
        return str + i2 + str2 + i;
    }

    public static int averageColors(int i, int i2) {
        return ((Integer) new ArgbEvaluator().evaluate(0.5f, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
    }

    public static void d(String str, String str2) {
        AppCompatDelegate.Api33Impl.d("BrailleIme", str, str2);
    }

    public static double distance(PointF pointF, PointF pointF2) {
        return Math.hypot(pointF.x - pointF2.x, pointF.y - pointF2.y);
    }

    public static void e(String str, String str2) {
        AppCompatDelegate.Api33Impl.e("BrailleIme", str, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x007a, code lost:
    
        throw new java.util.zip.ZipException("Illegal name: ".concat(java.lang.String.valueOf(r6)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean extractTables(android.content.res.Resources r10, int r11, java.io.File r12) {
        /*
            java.lang.String r0 = "Exception during zipStream.close()"
            java.lang.String r1 = "TranslateUtils"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.io.InputStream r10 = r10.openRawResource(r11)
            java.util.zip.ZipInputStream r11 = new java.util.zip.ZipInputStream
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream
            r3.<init>(r10)
            r11.<init>(r3)
            r10 = 10240(0x2800, float:1.4349E-41)
            r3 = 1
            r4 = 0
            byte[] r10 = new byte[r10]     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
        L1d:
            java.util.zip.ZipEntry r5 = r11.getNextEntry()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            if (r5 == 0) goto L7b
            java.lang.String r6 = r5.getName()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r7.<init>(r12, r6)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r8 = r7.getCanonicalPath()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r9 = r12.getCanonicalPath()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            boolean r8 = r8.startsWith(r9)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            if (r8 == 0) goto L6b
            r2.add(r7)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            boolean r5 = r5.isDirectory()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            if (r5 == 0) goto L4a
            r7.mkdirs()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            makeReadable(r7)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            goto L1d
        L4a:
            java.io.File r5 = r7.getParentFile()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r5.mkdirs()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
        L56:
            int r6 = r11.read(r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r8 = -1
            if (r6 == r8) goto L61
            r5.write(r10, r4, r6)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            goto L56
        L61:
            r5.close()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r11.closeEntry()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            makeReadable(r7)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            goto L1d
        L6b:
            java.util.zip.ZipException r10 = new java.util.zip.ZipException     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r12 = "Illegal name: "
            java.lang.String r5 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r12 = r12.concat(r5)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r10.<init>(r12)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            throw r10     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
        L7b:
            r11.close()     // Catch: java.io.IOException -> L7f
            goto L87
        L7f:
            r10 = move-exception
            java.lang.Object[] r11 = new java.lang.Object[r3]
            r11[r4] = r10
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r1, r0, r11)
        L87:
            return r3
        L88:
            r10 = move-exception
            goto Lbe
        L8a:
            r10 = move-exception
            java.lang.String r12 = "Exception during extractEntries()"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L88
            r5[r4] = r10     // Catch: java.lang.Throwable -> L88
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r1, r12, r5)     // Catch: java.lang.Throwable -> L88
            java.util.Iterator r10 = r2.iterator()     // Catch: java.lang.Throwable -> L88
        L98:
            boolean r12 = r10.hasNext()     // Catch: java.lang.Throwable -> L88
            if (r12 == 0) goto Lae
            java.lang.Object r12 = r10.next()     // Catch: java.lang.Throwable -> L88
            java.io.File r12 = (java.io.File) r12     // Catch: java.lang.Throwable -> L88
            boolean r5 = r12.isDirectory()     // Catch: java.lang.Throwable -> L88
            if (r5 != 0) goto L98
            r12.delete()     // Catch: java.lang.Throwable -> L88
            goto L98
        Lae:
            r2.clear()     // Catch: java.lang.Throwable -> L88
            r11.close()     // Catch: java.io.IOException -> Lb5
            goto Lbd
        Lb5:
            r10 = move-exception
            java.lang.Object[] r11 = new java.lang.Object[r3]
            r11[r4] = r10
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r1, r0, r11)
        Lbd:
            return r4
        Lbe:
            r11.close()     // Catch: java.io.IOException -> Lc2
            goto Lca
        Lc2:
            r11 = move-exception
            java.lang.Object[] r12 = new java.lang.Object[r3]
            r12[r4] = r11
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r1, r0, r12)
        Lca:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.extractTables(android.content.res.Resources, int, java.io.File):boolean");
    }

    public static String getBrailleKeyboardDisplayName(Context context) {
        if (BrailleUserPreferences.readAvailablePreferredCodes(context).size() == 1) {
            return context.getString(R.string.braille_ime_displayed_name);
        }
        return context.getString(R.string.multiple_languages_braille_ime_displayed_name, BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(context).getUserFacingName(context));
    }

    public static int getDisplayRotationDegrees(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static Size getDisplaySizeInPixels(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return new Size(point.x, point.y);
    }

    public static int getPaintTextBaselineInPixels(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom);
    }

    public static float getResourcesFloat(Resources resources, int i) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        return typedValue.getFloat();
    }

    public static boolean isPhonePermissionGranted(Context context) {
        if (EditorInfoCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
            return true;
        }
        return false;
    }

    public static boolean isRobolectric() {
        return "robolectric".equals(Build.FINGERPRINT);
    }

    public static WorkName loadAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Context context, Fragment fragment, boolean z, boolean z2) {
        int exitAnim;
        int nextTransition = fragment.getNextTransition();
        if (z2) {
            if (z) {
                exitAnim = fragment.getPopEnterAnim();
            } else {
                exitAnim = fragment.getPopExitAnim();
            }
        } else if (z) {
            exitAnim = fragment.getEnterAnim();
        } else {
            exitAnim = fragment.getExitAnim();
        }
        fragment.setAnimations(0, 0, 0, 0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getTag(R.id.visible_removing_fragment_view_tag) != null) {
            fragment.mContainer.setTag(R.id.visible_removing_fragment_view_tag, null);
        }
        ViewGroup viewGroup2 = fragment.mContainer;
        if (viewGroup2 != null && viewGroup2.getLayoutTransition() != null) {
            return null;
        }
        Animation onCreateAnimation = fragment.onCreateAnimation(nextTransition, z, exitAnim);
        if (onCreateAnimation != null) {
            return new WorkName(onCreateAnimation);
        }
        Animator onCreateAnimator = fragment.onCreateAnimator(nextTransition, z, exitAnim);
        if (onCreateAnimator != null) {
            return new WorkName(onCreateAnimator);
        }
        if (exitAnim == 0) {
            if (nextTransition != 0) {
                if (nextTransition != 4097) {
                    if (nextTransition != 8194) {
                        if (nextTransition != 8197) {
                            if (nextTransition != 4099) {
                                if (nextTransition != 4100) {
                                    exitAnim = -1;
                                } else if (z) {
                                    exitAnim = toActivityTransitResId(context, android.R.attr.activityOpenEnterAnimation);
                                } else {
                                    exitAnim = toActivityTransitResId(context, android.R.attr.activityOpenExitAnimation);
                                }
                            } else if (true != z) {
                                exitAnim = R.animator.fragment_fade_exit;
                            } else {
                                exitAnim = R.animator.fragment_fade_enter;
                            }
                        } else if (z) {
                            exitAnim = toActivityTransitResId(context, android.R.attr.activityCloseEnterAnimation);
                        } else {
                            exitAnim = toActivityTransitResId(context, android.R.attr.activityCloseExitAnimation);
                        }
                    } else if (true != z) {
                        exitAnim = R.animator.fragment_close_exit;
                    } else {
                        exitAnim = R.animator.fragment_close_enter;
                    }
                } else if (true != z) {
                    exitAnim = R.animator.fragment_open_exit;
                } else {
                    exitAnim = R.animator.fragment_open_enter;
                }
            } else {
                exitAnim = 0;
            }
        }
        if (exitAnim != 0) {
            boolean equals = "anim".equals(context.getResources().getResourceTypeName(exitAnim));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(context, exitAnim);
                    if (loadAnimation != null) {
                        return new WorkName(loadAnimation);
                    }
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException unused) {
                }
            }
            try {
                Animator loadAnimator = AnimatorInflater.loadAnimator(context, exitAnim);
                if (loadAnimator != null) {
                    return new WorkName(loadAnimator);
                }
            } catch (RuntimeException e2) {
                if (!equals) {
                    Animation loadAnimation2 = AnimationUtils.loadAnimation(context, exitAnim);
                    if (loadAnimation2 != null) {
                        return new WorkName(loadAnimation2);
                    }
                } else {
                    throw e2;
                }
            }
        }
        return null;
    }

    private static void makeReadable(File file) {
        if (!file.canRead()) {
            file.setReadable(true);
        }
    }

    public static float mmToPixels(Resources resources, int i) {
        return i * TypedValue.applyDimension(5, 1.0f, resources.getDisplayMetrics());
    }

    public static int parseIntWithDefault(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static void setComponentEnabled$ar$ds(Context context, ComponentName componentName, boolean z) {
        int i;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (true != z) {
                i = 2;
            } else {
                i = 1;
            }
            packageManager.setComponentEnabledSetting(componentName, i, 1);
            Optional.of(Boolean.valueOf(z));
        } catch (RuntimeException unused) {
            Optional.empty();
        }
    }

    private static int toActivityTransitResId(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(android.R.style.Animation.Activity, new int[]{i});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int[] values$ar$edu$2b5d664c_0() {
        return new int[]{1, 2, 92, 182, 272};
    }

    public static void e(String str, String str2, Throwable th) {
        AppCompatDelegate.Api33Impl.e("BrailleIme", str, str2, th);
    }
}
