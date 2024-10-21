package android.support.v7.widget;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.cardview.widget.RoundRectDrawable;
import androidx.cardview.widget.RoundRectDrawableWithShadow;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.preference.Preference;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatTextViewAutoSizeHelper {
    private static final RectF TEMP_RECTF = new RectF();
    private static final ConcurrentHashMap sTextViewMethodByNameCache = new ConcurrentHashMap();
    public final Context mContext;
    private final Impl mImpl;
    private TextPaint mTempTextPaint;
    public final TextView mTextView;
    public int mAutoSizeTextType = 0;
    public boolean mNeedsAutoSizeText = false;
    public float mAutoSizeStepGranularityInPx = -1.0f;
    public float mAutoSizeMinTextSizeInPx = -1.0f;
    public float mAutoSizeMaxTextSizeInPx = -1.0f;
    public int[] mAutoSizeTextSizesInPx = new int[0];
    public boolean mHasPresetAutoSizeValues = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api23Impl {
        static StaticLayout createStaticLayoutForMeasuring(CharSequence charSequence, Layout.Alignment alignment, int i, int i2, TextView textView, TextPaint textPaint, Impl impl) {
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), textPaint, i);
            StaticLayout.Builder hyphenationFrequency = obtain.setAlignment(alignment).setLineSpacing(textView.getLineSpacingExtra(), textView.getLineSpacingMultiplier()).setIncludePad(textView.getIncludeFontPadding()).setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency());
            if (i2 == -1) {
                i2 = Preference.DEFAULT_ORDER;
            }
            hyphenationFrequency.setMaxLines(i2);
            try {
                impl.computeAndSetTextDirection(obtain, textView);
            } catch (ClassCastException unused) {
                Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
            }
            return obtain.build();
        }

        public static void d(String str, String str2) {
            AppCompatDelegate.Api33Impl.d("BrailleDisplay", str, str2);
        }

        public static void e(String str, String str2) {
            AppCompatDelegate.Api33Impl.e("BrailleDisplay", str, str2);
        }

        public static ThemeSettings getThemeSettings() {
            ThemeSettings themeSettings = new ThemeSettings();
            themeSettings.themeId = 1;
            return themeSettings;
        }

        public static void i(String str, String str2) {
            LogUtils.i("BrailleDisplay", "%s: %s", str, str2);
        }

        public static void v(String str, String str2) {
            LogUtils.v("BrailleDisplay", "%s: %s", str, str2);
        }

        public static void w(String str, String str2) {
            LogUtils.w("BrailleDisplay", "%s: %s", "BdController", "Displayer is not ready yet.");
        }

        public static void e(String str, String str2, Throwable th) {
            AppCompatDelegate.Api33Impl.e("BrailleDisplay", str, str2, th);
        }

        public static void w(String str, String str2, Throwable th) {
            LogUtils.log("BrailleDisplay", 5, th, "%s: %s", str, str2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Impl {
        public Impl() {
        }

        private static RoundRectDrawable getCardBackground$ar$class_merging$ar$ds$ar$class_merging(AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33) {
            return (RoundRectDrawable) durationScaleChangeListener33.AnimationHandler$DurationScaleChangeListener33$ar$mListener;
        }

        public final ColorStateList getBackgroundColor$ar$class_merging$ar$class_merging(AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33) {
            return getCardBackground$ar$class_merging$ar$ds$ar$class_merging(durationScaleChangeListener33).mBackground;
        }

        public final float getMaxElevation$ar$class_merging$ar$class_merging(AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33) {
            return getCardBackground$ar$class_merging$ar$ds$ar$class_merging(durationScaleChangeListener33).mPadding;
        }

        public final float getRadius$ar$class_merging$ar$class_merging(AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33) {
            return getCardBackground$ar$class_merging$ar$ds$ar$class_merging(durationScaleChangeListener33).mRadius;
        }

        public final void initialize$ar$class_merging$4b34ab49_0$ar$ds$ar$class_merging(AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33, ColorStateList colorStateList, float f, float f2, float f3) {
            RoundRectDrawable roundRectDrawable = new RoundRectDrawable(colorStateList, f);
            durationScaleChangeListener33.AnimationHandler$DurationScaleChangeListener33$ar$mListener = roundRectDrawable;
            ((CardView) durationScaleChangeListener33.AnimationHandler$DurationScaleChangeListener33$ar$this$0).setBackgroundDrawable(roundRectDrawable);
            View view = (View) durationScaleChangeListener33.AnimationHandler$DurationScaleChangeListener33$ar$this$0;
            view.setClipToOutline(true);
            view.setElevation(f2);
            RoundRectDrawable cardBackground$ar$class_merging$ar$ds$ar$class_merging = getCardBackground$ar$class_merging$ar$ds$ar$class_merging(durationScaleChangeListener33);
            boolean useCompatPadding = durationScaleChangeListener33.getUseCompatPadding();
            boolean preventCornerOverlap = durationScaleChangeListener33.getPreventCornerOverlap();
            if (f3 != cardBackground$ar$class_merging$ar$ds$ar$class_merging.mPadding || cardBackground$ar$class_merging$ar$ds$ar$class_merging.mInsetForPadding != useCompatPadding || cardBackground$ar$class_merging$ar$ds$ar$class_merging.mInsetForRadius != preventCornerOverlap) {
                cardBackground$ar$class_merging$ar$ds$ar$class_merging.mPadding = f3;
                cardBackground$ar$class_merging$ar$ds$ar$class_merging.mInsetForPadding = useCompatPadding;
                cardBackground$ar$class_merging$ar$ds$ar$class_merging.mInsetForRadius = preventCornerOverlap;
                cardBackground$ar$class_merging$ar$ds$ar$class_merging.updateBounds(null);
                cardBackground$ar$class_merging$ar$ds$ar$class_merging.invalidateSelf();
            }
            updatePadding$ar$class_merging$ar$class_merging(durationScaleChangeListener33);
        }

        public boolean isHorizontallyScrollable(TextView textView) {
            return ((Boolean) AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(textView, "getHorizontallyScrolling", false)).booleanValue();
        }

        public final void updatePadding$ar$class_merging$ar$class_merging(AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33) {
            if (!durationScaleChangeListener33.getUseCompatPadding()) {
                durationScaleChangeListener33.setShadowPadding(0, 0, 0, 0);
                return;
            }
            float maxElevation$ar$class_merging$ar$class_merging = getMaxElevation$ar$class_merging$ar$class_merging(durationScaleChangeListener33);
            float radius$ar$class_merging$ar$class_merging = getRadius$ar$class_merging$ar$class_merging(durationScaleChangeListener33);
            int ceil = (int) Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(maxElevation$ar$class_merging$ar$class_merging, radius$ar$class_merging$ar$class_merging, durationScaleChangeListener33.getPreventCornerOverlap()));
            int ceil2 = (int) Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(maxElevation$ar$class_merging$ar$class_merging, radius$ar$class_merging$ar$class_merging, durationScaleChangeListener33.getPreventCornerOverlap()));
            durationScaleChangeListener33.setShadowPadding(ceil, ceil2, ceil, ceil2);
        }

        public Impl(byte[] bArr) {
        }

        public void computeAndSetTextDirection(StaticLayout.Builder builder, TextView textView) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Impl23 extends Impl {
        @Override // android.support.v7.widget.AppCompatTextViewAutoSizeHelper.Impl
        public void computeAndSetTextDirection(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection((TextDirectionHeuristic) AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Impl29 extends Impl23 {
        @Override // android.support.v7.widget.AppCompatTextViewAutoSizeHelper.Impl23, android.support.v7.widget.AppCompatTextViewAutoSizeHelper.Impl
        public final void computeAndSetTextDirection(StaticLayout.Builder builder, TextView textView) {
            TextDirectionHeuristic textDirectionHeuristic;
            textDirectionHeuristic = textView.getTextDirectionHeuristic();
            builder.setTextDirection(textDirectionHeuristic);
        }

        @Override // android.support.v7.widget.AppCompatTextViewAutoSizeHelper.Impl
        public final boolean isHorizontallyScrollable(TextView textView) {
            boolean isHorizontallyScrollable;
            isHorizontallyScrollable = textView.isHorizontallyScrollable();
            return isHorizontallyScrollable;
        }
    }

    public AppCompatTextViewAutoSizeHelper(TextView textView) {
        Impl impl23;
        this.mTextView = textView;
        this.mContext = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            impl23 = new Impl29();
        } else {
            impl23 = new Impl23();
        }
        this.mImpl = impl23;
    }

    public static final int[] cleanupAutoSizePresetSizes$ar$ds(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0) {
                Integer valueOf = Integer.valueOf(i);
                if (Collections.binarySearch(arrayList, valueOf) < 0) {
                    arrayList.add(valueOf);
                }
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    private static Method getTextViewMethod(String str) {
        try {
            ConcurrentHashMap concurrentHashMap = sTextViewMethodByNameCache;
            Method method = (Method) concurrentHashMap.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, null)) != null) {
                method.setAccessible(true);
                concurrentHashMap.put(str, method);
            }
            return method;
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Failed to retrieve TextView#", "() method"), e);
            return null;
        }
    }

    static Object invokeAndReturnWithDefault(Object obj, String str, Object obj2) {
        try {
            obj2 = getTextViewMethod(str).invoke(obj, null);
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Failed to invoke TextView#", "() method"), e);
        }
        if (obj2 == null) {
            return null;
        }
        return obj2;
    }

    public final void autoSizeText() {
        int measuredWidth;
        CharSequence transformation;
        if (isAutoSizeEnabled()) {
            if (this.mNeedsAutoSizeText) {
                if (this.mTextView.getMeasuredHeight() > 0 && this.mTextView.getMeasuredWidth() > 0) {
                    if (this.mImpl.isHorizontallyScrollable(this.mTextView)) {
                        measuredWidth = 1048576;
                    } else {
                        TextView textView = this.mTextView;
                        measuredWidth = (textView.getMeasuredWidth() - textView.getTotalPaddingLeft()) - this.mTextView.getTotalPaddingRight();
                    }
                    TextView textView2 = this.mTextView;
                    int height = (textView2.getHeight() - textView2.getCompoundPaddingBottom()) - this.mTextView.getCompoundPaddingTop();
                    if (measuredWidth > 0 && height > 0) {
                        RectF rectF = TEMP_RECTF;
                        synchronized (rectF) {
                            rectF.setEmpty();
                            rectF.right = measuredWidth;
                            rectF.bottom = height;
                            int length = this.mAutoSizeTextSizesInPx.length;
                            if (length != 0) {
                                int i = length - 1;
                                int i2 = 1;
                                int i3 = 0;
                                while (i2 <= i) {
                                    int i4 = (i2 + i) / 2;
                                    int i5 = this.mAutoSizeTextSizesInPx[i4];
                                    CharSequence text = this.mTextView.getText();
                                    TransformationMethod transformationMethod = this.mTextView.getTransformationMethod();
                                    if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.mTextView)) != null) {
                                        text = transformation;
                                    }
                                    int maxLines = this.mTextView.getMaxLines();
                                    TextPaint textPaint = this.mTempTextPaint;
                                    if (textPaint == null) {
                                        this.mTempTextPaint = new TextPaint();
                                    } else {
                                        textPaint.reset();
                                    }
                                    this.mTempTextPaint.set(this.mTextView.getPaint());
                                    this.mTempTextPaint.setTextSize(i5);
                                    StaticLayout createStaticLayoutForMeasuring = Api23Impl.createStaticLayoutForMeasuring(text, (Layout.Alignment) invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines, this.mTextView, this.mTempTextPaint, this.mImpl);
                                    if ((maxLines == -1 || (createStaticLayoutForMeasuring.getLineCount() <= maxLines && createStaticLayoutForMeasuring.getLineEnd(createStaticLayoutForMeasuring.getLineCount() - 1) == text.length())) && createStaticLayoutForMeasuring.getHeight() <= rectF.bottom) {
                                        int i6 = i4 + 1;
                                        i3 = i2;
                                        i2 = i6;
                                    }
                                    i3 = i4 - 1;
                                    i = i3;
                                }
                                float f = this.mAutoSizeTextSizesInPx[i3];
                                if (f != this.mTextView.getTextSize()) {
                                    setTextSizeInternal(0, f);
                                }
                            } else {
                                throw new IllegalStateException("No available text sizes to choose from.");
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.mNeedsAutoSizeText = true;
        }
    }

    public final int getAutoSizeMaxTextSize() {
        return Math.round(this.mAutoSizeMaxTextSizeInPx);
    }

    public final int getAutoSizeMinTextSize() {
        return Math.round(this.mAutoSizeMinTextSizeInPx);
    }

    public final int getAutoSizeStepGranularity() {
        return Math.round(this.mAutoSizeStepGranularityInPx);
    }

    public final boolean isAutoSizeEnabled() {
        if (supportsAutoSizeText() && this.mAutoSizeTextType != 0) {
            return true;
        }
        return false;
    }

    public final void setTextSizeInternal(int i, float f) {
        Resources resources;
        Context context = this.mContext;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        float applyDimension = TypedValue.applyDimension(i, f, resources.getDisplayMetrics());
        if (applyDimension != this.mTextView.getPaint().getTextSize()) {
            this.mTextView.getPaint().setTextSize(applyDimension);
            TextView textView = this.mTextView;
            boolean isInLayout = textView.isInLayout();
            if (textView.getLayout() != null) {
                this.mNeedsAutoSizeText = false;
                try {
                    Method textViewMethod = getTextViewMethod("nullLayouts");
                    if (textViewMethod != null) {
                        textViewMethod.invoke(this.mTextView, null);
                    }
                } catch (Exception e) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e);
                }
                if (!isInLayout) {
                    this.mTextView.requestLayout();
                } else {
                    this.mTextView.forceLayout();
                }
                this.mTextView.invalidate();
            }
        }
    }

    public final boolean setupAutoSizeText() {
        boolean z = false;
        z = false;
        if (supportsAutoSizeText() && this.mAutoSizeTextType == 1) {
            if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
                int floor = ((int) Math.floor((this.mAutoSizeMaxTextSizeInPx - this.mAutoSizeMinTextSizeInPx) / this.mAutoSizeStepGranularityInPx)) + 1;
                int[] iArr = new int[floor];
                for (int i = 0; i < floor; i++) {
                    iArr[i] = Math.round(this.mAutoSizeMinTextSizeInPx + (i * this.mAutoSizeStepGranularityInPx));
                }
                this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes$ar$ds(iArr);
            }
            z = true;
        }
        this.mNeedsAutoSizeText = z;
        return z;
    }

    public final boolean setupAutoSizeUniformPresetSizesConfiguration() {
        boolean z;
        if (this.mAutoSizeTextSizesInPx.length > 0) {
            z = true;
        } else {
            z = false;
        }
        this.mHasPresetAutoSizeValues = z;
        if (z) {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = r0[0];
            this.mAutoSizeMaxTextSizeInPx = r0[r1 - 1];
            this.mAutoSizeStepGranularityInPx = -1.0f;
        }
        return z;
    }

    public final boolean supportsAutoSizeText() {
        if (!(this.mTextView instanceof AppCompatEditText)) {
            return true;
        }
        return false;
    }

    public final void validateAndSetAutoSizeTextTypeUniformConfiguration(float f, float f2, float f3) {
        if (f > 0.0f) {
            if (f2 > f) {
                if (f3 > 0.0f) {
                    this.mAutoSizeTextType = 1;
                    this.mAutoSizeMinTextSizeInPx = f;
                    this.mAutoSizeMaxTextSizeInPx = f2;
                    this.mAutoSizeStepGranularityInPx = f3;
                    this.mHasPresetAutoSizeValues = false;
                    return;
                }
                throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
            }
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size text size (" + f + "px)");
        }
        throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
    }
}
