package android.support.v7.widget;

import android.R;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.collection.ArraySet;
import androidx.collection.internal.ContainerHelpersKt;
import androidx.core.app.NotificationCompatBuilder$Api24Impl;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.common.flogger.context.ContextDataProvider;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ConcurrentModificationException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DrawableUtils {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] EMPTY_STATE_SET = new int[0];
    public static final Rect INSETS_NONE = new Rect();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api18Impl {
        public static final Field sBottom;
        public static final Method sGetOpticalInsets;
        public static final Field sLeft;
        public static final boolean sReflectionSuccessful;
        public static final Field sRight;
        public static final Field sTop;

        /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0056  */
        static {
            /*
                r0 = 1
                r1 = 0
                r2 = 0
                java.lang.String r3 = "android.graphics.Insets"
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.NoSuchFieldException -> L39 java.lang.ClassNotFoundException -> L3d java.lang.NoSuchMethodException -> L41
                java.lang.Class<android.graphics.drawable.Drawable> r4 = android.graphics.drawable.Drawable.class
                java.lang.String r5 = "getOpticalInsets"
                java.lang.reflect.Method r4 = r4.getMethod(r5, r2)     // Catch: java.lang.NoSuchFieldException -> L39 java.lang.ClassNotFoundException -> L3d java.lang.NoSuchMethodException -> L41
                java.lang.String r5 = "left"
                java.lang.reflect.Field r5 = r3.getField(r5)     // Catch: java.lang.NoSuchFieldException -> L33 java.lang.ClassNotFoundException -> L35 java.lang.NoSuchMethodException -> L37
                java.lang.String r6 = "top"
                java.lang.reflect.Field r6 = r3.getField(r6)     // Catch: java.lang.NoSuchFieldException -> L2d java.lang.ClassNotFoundException -> L2f java.lang.NoSuchMethodException -> L31
                java.lang.String r7 = "right"
                java.lang.reflect.Field r7 = r3.getField(r7)     // Catch: java.lang.Throwable -> L2b
                java.lang.String r8 = "bottom"
                java.lang.reflect.Field r3 = r3.getField(r8)     // Catch: java.lang.Throwable -> L45
                r8 = r0
                goto L47
            L2b:
                r7 = r2
                goto L45
            L2d:
                r6 = r2
                goto L44
            L2f:
                r6 = r2
                goto L44
            L31:
                r6 = r2
                goto L44
            L33:
                r5 = r2
                goto L3b
            L35:
                r5 = r2
                goto L3f
            L37:
                r5 = r2
                goto L43
            L39:
                r4 = r2
                r5 = r4
            L3b:
                r6 = r5
                goto L44
            L3d:
                r4 = r2
                r5 = r4
            L3f:
                r6 = r5
                goto L44
            L41:
                r4 = r2
                r5 = r4
            L43:
                r6 = r5
            L44:
                r7 = r6
            L45:
                r8 = r1
                r3 = r2
            L47:
                if (r8 == 0) goto L56
                android.support.v7.widget.DrawableUtils.Api18Impl.sGetOpticalInsets = r4
                android.support.v7.widget.DrawableUtils.Api18Impl.sLeft = r5
                android.support.v7.widget.DrawableUtils.Api18Impl.sTop = r6
                android.support.v7.widget.DrawableUtils.Api18Impl.sRight = r7
                android.support.v7.widget.DrawableUtils.Api18Impl.sBottom = r3
                android.support.v7.widget.DrawableUtils.Api18Impl.sReflectionSuccessful = r0
                return
            L56:
                android.support.v7.widget.DrawableUtils.Api18Impl.sGetOpticalInsets = r2
                android.support.v7.widget.DrawableUtils.Api18Impl.sLeft = r2
                android.support.v7.widget.DrawableUtils.Api18Impl.sTop = r2
                android.support.v7.widget.DrawableUtils.Api18Impl.sRight = r2
                android.support.v7.widget.DrawableUtils.Api18Impl.sBottom = r2
                android.support.v7.widget.DrawableUtils.Api18Impl.sReflectionSuccessful = r1
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.DrawableUtils.Api18Impl.<clinit>():void");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        public static void addWordWrapBreakPoints(SparseIntArray sparseIntArray, BrailleWord brailleWord, int i, int i2) {
            boolean z;
            boolean z2 = false;
            if (!brailleWord.isEmpty() && i >= 0 && i2 <= brailleWord.size()) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z);
            while (i < i2) {
                boolean isEmpty = brailleWord.get(i).isEmpty();
                if (isEmpty) {
                    sparseIntArray.append(i, 2);
                } else if (z2) {
                    sparseIntArray.append(i, 1);
                }
                i++;
                z2 = isEmpty;
            }
        }

        public static final void allocArrays(ArraySet arraySet, int i) {
            arraySet.hashes = new int[i];
            arraySet.array = new Object[i];
        }

        public static void appendWithSpaces$ar$ds(Editable editable, Object... objArr) {
            Object obj = objArr[0];
            if (obj == null) {
                return;
            }
            if (editable.length() > 0 && !endsWithSpace(editable) && !TextUtils.isEmpty(obj.toString())) {
                editable.append(' ');
            }
            if (obj instanceof CharSequence) {
                editable.append((CharSequence) obj);
            } else {
                editable.append((CharSequence) obj.toString());
            }
        }

        public static void appendWithSpaces$ar$ds$245c4f76_0(StringBuilder sb, Object... objArr) {
            Object obj = objArr[0];
            if (obj == null) {
                return;
            }
            if (sb.length() > 0 && !endsWithSpace(sb)) {
                sb.append(' ');
            }
            sb.append(obj);
        }

        public static final int binarySearchInternal(ArraySet arraySet, int i) {
            try {
                return ContainerHelpersKt.binarySearch(arraySet.hashes, arraySet._size, i);
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }

        public static boolean endsWithSpace(CharSequence charSequence) {
            if (charSequence.length() == 0) {
                return false;
            }
            return Character.isSpaceChar(Character.codePointBefore(charSequence, charSequence.length()));
        }

        public static int findWordWrapCutPoint(BrailleWord brailleWord, int i, int i2, int i3) {
            if (brailleWord.get(i).isEmpty()) {
                for (int i4 = i + 1; i4 < i3; i4++) {
                    if (!brailleWord.get(i4).isEmpty()) {
                        return i4;
                    }
                }
            } else {
                for (int i5 = i - 1; i5 >= i2; i5--) {
                    if (brailleWord.get(i5).isEmpty()) {
                        return i5 + 1;
                    }
                }
            }
            return i;
        }

        static Insets getOpticalInsets(Drawable drawable) {
            Insets opticalInsets;
            opticalInsets = drawable.getOpticalInsets();
            return opticalInsets;
        }

        public static final int indexOf(ArraySet arraySet, Object obj, int i) {
            int i2 = arraySet._size;
            if (i2 == 0) {
                return -1;
            }
            int binarySearchInternal = binarySearchInternal(arraySet, i);
            if (binarySearchInternal < 0) {
                return binarySearchInternal;
            }
            if (Intrinsics.areEqual(obj, arraySet.array[binarySearchInternal])) {
                return binarySearchInternal;
            }
            int i3 = binarySearchInternal + 1;
            while (i3 < i2 && arraySet.hashes[i3] == i) {
                if (Intrinsics.areEqual(obj, arraySet.array[i3])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = binarySearchInternal - 1; i4 >= 0 && arraySet.hashes[i4] == i; i4--) {
                if (Intrinsics.areEqual(obj, arraySet.array[i4])) {
                    return i4;
                }
            }
            return ~i3;
        }

        public static final int indexOfNull(ArraySet arraySet) {
            return indexOf(arraySet, null, 0);
        }
    }

    public static void fixDrawable(Drawable drawable) {
        String name = drawable.getClass().getName();
        if (Build.VERSION.SDK_INT >= 29 && Build.VERSION.SDK_INT < 31 && "android.graphics.drawable.ColorStateListDrawable".equals(name)) {
            int[] state = drawable.getState();
            if (state != null && state.length != 0) {
                drawable.setState(EMPTY_STATE_SET);
            } else {
                drawable.setState(CHECKED_STATE_SET);
            }
            drawable.setState(state);
        }
    }

    public static Rect getOpticalBounds(Drawable drawable) {
        int i;
        int i2;
        int i3;
        int i4;
        if (Build.VERSION.SDK_INT >= 29) {
            Insets opticalInsets = Api29Impl.getOpticalInsets(drawable);
            i = opticalInsets.left;
            i2 = opticalInsets.top;
            i3 = opticalInsets.right;
            i4 = opticalInsets.bottom;
            return new Rect(i, i2, i3, i4);
        }
        Drawable unwrap = NotificationCompatBuilder$Api24Impl.unwrap(drawable);
        if (Build.VERSION.SDK_INT < 29 && Api18Impl.sReflectionSuccessful) {
            try {
                Object invoke = Api18Impl.sGetOpticalInsets.invoke(unwrap, null);
                if (invoke != null) {
                    return new Rect(Api18Impl.sLeft.getInt(invoke), Api18Impl.sTop.getInt(invoke), Api18Impl.sRight.getInt(invoke), Api18Impl.sBottom.getInt(invoke));
                }
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
        return INSETS_NONE;
    }
}
