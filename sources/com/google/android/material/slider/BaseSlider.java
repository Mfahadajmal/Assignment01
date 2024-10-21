package com.google.android.material.slider;

import _COROUTINE._BOUNDARY;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.core.app.NotificationCompatBuilder$Api26Impl;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import androidx.preference.Preference;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver.Callback;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.tooltip.TooltipDrawable;
import io.grpc.internal.RetryingNameResolver;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BaseSlider<S extends BaseSlider<S, L, T>, L extends GooglePlayServicesUpdatedReceiver.Callback, T extends BaseOnSliderTouchListener<S>> extends View {
    static final int DEF_STYLE_RES = 2132150317;
    private static final int LABEL_ANIMATION_ENTER_DURATION_ATTR = 2130969554;
    private static final int LABEL_ANIMATION_ENTER_EASING_ATTR = 2130969564;
    private static final int LABEL_ANIMATION_EXIT_DURATION_ATTR = 2130969557;
    private static final int LABEL_ANIMATION_EXIT_EASING_ATTR = 2130969562;
    private static final String TAG = "BaseSlider";
    private AccessibilityEventSender accessibilityEventSender;
    public final AccessibilityHelper accessibilityHelper;
    private final AccessibilityManager accessibilityManager;
    public int activeThumbIdx;
    private final Paint activeTicksPaint;
    private final Paint activeTrackPaint;
    private final List changeListeners;
    private final RectF cornerRect;
    private List customThumbDrawablesForValues;
    private final MaterialShapeDrawable defaultThumbDrawable;
    private int defaultThumbRadius;
    private int defaultThumbTrackGapSize;
    private int defaultThumbWidth;
    private int defaultTickActiveRadius;
    private int defaultTickInactiveRadius;
    private int defaultTrackHeight;
    public boolean dirtyConfig;
    private int focusedThumbIdx;
    private RetryingNameResolver.ResolutionResultListener formatter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private ColorStateList haloColor;
    private final Paint haloPaint;
    private int haloRadius;
    private final Paint inactiveTicksPaint;
    private final Paint inactiveTrackPaint;
    private boolean isLongPress;
    private int labelBehavior;
    private int labelPadding;
    private int labelStyle;
    public final List labels;
    private boolean labelsAreAnimatedIn;
    private ValueAnimator labelsInAnimator;
    private ValueAnimator labelsOutAnimator;
    private MotionEvent lastEvent;
    private int minTickSpacing;
    private int minTouchTargetSize;
    private int minTrackSidePadding;
    private int minWidgetHeight;
    private final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    private final int scaledTouchSlop;
    public float stepSize;
    private final Paint stopIndicatorPaint;
    private int thumbHeight;
    private boolean thumbIsPressed;
    private final Paint thumbPaint;
    private int thumbTrackGapSize;
    private int thumbWidth;
    private int tickActiveRadius;
    private ColorStateList tickColorActive;
    private ColorStateList tickColorInactive;
    private int tickInactiveRadius;
    private boolean tickVisible;
    private float[] ticksCoordinates;
    private float touchDownX;
    private final List touchListeners;
    private float touchPosition;
    private ColorStateList trackColorActive;
    private ColorStateList trackColorInactive;
    private int trackHeight;
    private int trackInsideCornerSize;
    private final Path trackPath;
    private final RectF trackRect;
    private int trackSidePadding;
    private int trackStopIndicatorSize;
    private int trackWidth;
    public float valueFrom;
    public float valueTo;
    private ArrayList values;
    private int widgetHeight;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AccessibilityEventSender implements Runnable {
        int virtualViewId = -1;

        public AccessibilityEventSender() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            BaseSlider.this.accessibilityHelper.sendEventForVirtualView$ar$ds(this.virtualViewId, 4);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AccessibilityHelper extends ExploreByTouchHelper {
        private final BaseSlider slider;
        final Rect virtualViewBounds;

        public AccessibilityHelper(BaseSlider baseSlider) {
            super(baseSlider);
            this.virtualViewBounds = new Rect();
            this.slider = baseSlider;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected final int getVirtualViewAt(float f, float f2) {
            for (int i = 0; i < this.slider.getValues().size(); i++) {
                this.slider.updateBoundsForVirtualViewId(i, this.virtualViewBounds);
                if (this.virtualViewBounds.contains((int) f, (int) f2)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected final void getVisibleVirtualViews(List list) {
            for (int i = 0; i < this.slider.getValues().size(); i++) {
                list.add(Integer.valueOf(i));
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public final boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            if (this.slider.isEnabled()) {
                if (i2 != 4096 && i2 != 8192) {
                    if (i2 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                        if (this.slider.snapThumbToValue(i, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"))) {
                            this.slider.updateHaloHotspot();
                            this.slider.postInvalidate();
                            invalidateVirtualView(i);
                            return true;
                        }
                        return false;
                    }
                    return false;
                }
                float calculateStepIncrement$ar$ds = this.slider.calculateStepIncrement$ar$ds();
                if (i2 == 8192) {
                    calculateStepIncrement$ar$ds = -calculateStepIncrement$ar$ds;
                }
                if (this.slider.isRtl()) {
                    calculateStepIncrement$ar$ds = -calculateStepIncrement$ar$ds;
                }
                float floatValue = ((Float) this.slider.getValues().get(i)).floatValue() + calculateStepIncrement$ar$ds;
                BaseSlider baseSlider = this.slider;
                if (this.slider.snapThumbToValue(i, NotificationCompatBuilder$Api26Impl.clamp(floatValue, baseSlider.getValueFrom(), baseSlider.getValueTo()))) {
                    this.slider.updateHaloHotspot();
                    this.slider.postInvalidate();
                    invalidateVirtualView(i);
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
            List values = this.slider.getValues();
            float floatValue = ((Float) values.get(i)).floatValue();
            float valueFrom = this.slider.getValueFrom();
            float valueTo = this.slider.getValueTo();
            if (this.slider.isEnabled()) {
                if (floatValue > valueFrom) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (floatValue < valueTo) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
            accessibilityNodeInfoCompat.mInfo.setRangeInfo((AccessibilityNodeInfo.RangeInfo) new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(1, valueFrom, valueTo, floatValue)).mInfo);
            accessibilityNodeInfoCompat.setClassName(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.slider.getContentDescription() != null) {
                sb.append(this.slider.getContentDescription());
                sb.append(",");
            }
            String formatValue = this.slider.formatValue(floatValue);
            String string = this.slider.getContext().getString(R.string.material_slider_value);
            if (values.size() > 1) {
                if (i == this.slider.getValues().size() - 1) {
                    string = this.slider.getContext().getString(R.string.material_slider_range_end);
                } else if (i == 0) {
                    string = this.slider.getContext().getString(R.string.material_slider_range_start);
                } else {
                    string = "";
                }
            }
            sb.append(String.format(Locale.US, "%s, %s", string, formatValue));
            accessibilityNodeInfoCompat.setContentDescription(sb.toString());
            this.slider.updateBoundsForVirtualViewId(i, this.virtualViewBounds);
            accessibilityNodeInfoCompat.setBoundsInParent(this.virtualViewBounds);
        }
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    private final void adjustCustomThumbDrawableBounds(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1) {
            if (intrinsicHeight != -1) {
                intrinsicWidth = -1;
            } else {
                drawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
                return;
            }
        }
        float max = Math.max(this.thumbWidth, this.thumbHeight) / Math.max(intrinsicWidth, intrinsicHeight);
        drawable.setBounds(0, 0, (int) (intrinsicWidth * max), (int) (intrinsicHeight * max));
    }

    private final void attachLabelToContentView(TooltipDrawable tooltipDrawable) {
        ViewGroup contentView = DrawableUtils$OutlineCompatL.getContentView(this);
        if (contentView == null) {
            return;
        }
        tooltipDrawable.updateLocationOnScreen(contentView);
        contentView.addOnLayoutChangeListener(tooltipDrawable.attachedViewLayoutChangeListener);
    }

    private final float calculateStepIncrement() {
        float f = this.stepSize;
        if (f == 0.0f) {
            return 1.0f;
        }
        return f;
    }

    private final int calculateTrackCenter() {
        int i = this.widgetHeight / 2;
        int i2 = 0;
        if (this.labelBehavior == 1 || shouldAlwaysShowLabel()) {
            i2 = ((TooltipDrawable) this.labels.get(0)).getIntrinsicHeight();
        }
        return i + i2;
    }

    private final ValueAnimator createLabelAnimator(boolean z) {
        ValueAnimator valueAnimator;
        float f;
        int resolveInteger;
        TimeInterpolator resolveThemeInterpolator;
        if (z) {
            valueAnimator = this.labelsOutAnimator;
        } else {
            valueAnimator = this.labelsInAnimator;
        }
        float f2 = 1.0f;
        if (true != z) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        if (valueAnimator != null && valueAnimator.isRunning()) {
            f = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            valueAnimator.cancel();
        }
        if (true != z) {
            f2 = 0.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        if (z) {
            resolveInteger = DrawableUtils$OutlineCompatR.resolveInteger(getContext(), LABEL_ANIMATION_ENTER_DURATION_ATTR, 83);
            resolveThemeInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_ENTER_EASING_ATTR, AnimationUtils.DECELERATE_INTERPOLATOR);
        } else {
            resolveInteger = DrawableUtils$OutlineCompatR.resolveInteger(getContext(), LABEL_ANIMATION_EXIT_DURATION_ATTR, BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL);
            resolveThemeInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_EXIT_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
        ofFloat.setDuration(resolveInteger);
        ofFloat.setInterpolator(resolveThemeInterpolator);
        ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 7, null));
        return ofFloat;
    }

    private final void detachLabelFromContentView(TooltipDrawable tooltipDrawable) {
        StatsStorage contentViewOverlay$ar$class_merging$ar$class_merging = DrawableUtils$OutlineCompatL.getContentViewOverlay$ar$class_merging$ar$class_merging(this);
        if (contentViewOverlay$ar$class_merging$ar$class_merging != null) {
            contentViewOverlay$ar$class_merging$ar$class_merging.remove(tooltipDrawable);
            ViewGroup contentView = DrawableUtils$OutlineCompatL.getContentView(this);
            if (contentView != null) {
                contentView.removeOnLayoutChangeListener(tooltipDrawable.attachedViewLayoutChangeListener);
            }
        }
    }

    private final void drawThumbDrawable(Canvas canvas, int i, int i2, float f, Drawable drawable) {
        canvas.save();
        canvas.translate((this.trackSidePadding + ((int) (normalizeValue(f) * i))) - (drawable.getBounds().width() / 2.0f), i2 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    private final void ensureLabelsAdded() {
        if (!this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = true;
            ValueAnimator createLabelAnimator = createLabelAnimator(true);
            this.labelsInAnimator = createLabelAnimator;
            this.labelsOutAnimator = null;
            createLabelAnimator.start();
        }
        Iterator it = this.labels.iterator();
        for (int i = 0; i < this.values.size() && it.hasNext(); i++) {
            if (i != this.focusedThumbIdx) {
                setValueForLabel((TooltipDrawable) it.next(), ((Float) this.values.get(i)).floatValue());
            }
        }
        if (it.hasNext()) {
            setValueForLabel((TooltipDrawable) it.next(), ((Float) this.values.get(this.focusedThumbIdx)).floatValue());
            return;
        }
        throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.labels.size()), Integer.valueOf(this.values.size())));
    }

    private final void ensureLabelsRemoved() {
        if (this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = false;
            ValueAnimator createLabelAnimator = createLabelAnimator(false);
            this.labelsOutAnimator = createLabelAnimator;
            this.labelsInAnimator = null;
            createLabelAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.slider.BaseSlider.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    StatsStorage contentViewOverlay$ar$class_merging$ar$class_merging = DrawableUtils$OutlineCompatL.getContentViewOverlay$ar$class_merging$ar$class_merging(BaseSlider.this);
                    Iterator it = BaseSlider.this.labels.iterator();
                    while (it.hasNext()) {
                        contentViewOverlay$ar$class_merging$ar$class_merging.remove((TooltipDrawable) it.next());
                    }
                }
            });
            this.labelsOutAnimator.start();
        }
    }

    private final float[] getActiveRange() {
        float f;
        float floatValue = ((Float) this.values.get(0)).floatValue();
        float floatValue2 = ((Float) this.values.get(r2.size() - 1)).floatValue();
        if (this.values.size() == 1) {
            floatValue = this.valueFrom;
        }
        float normalizeValue = normalizeValue(floatValue);
        float normalizeValue2 = normalizeValue(floatValue2);
        boolean isRtl = isRtl();
        if (true != isRtl) {
            f = normalizeValue;
        } else {
            f = normalizeValue2;
        }
        if (true != isRtl) {
            normalizeValue = normalizeValue2;
        }
        return new float[]{f, normalizeValue};
    }

    private final int getColorForState(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    private final boolean hasGapBetweenThumbAndTrack() {
        if (this.thumbTrackGapSize > 0) {
            return true;
        }
        return false;
    }

    private final boolean isPotentialVerticalScroll(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) != 3) {
            for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                ViewGroup viewGroup = (ViewGroup) parent;
                if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void maybeCalculateTicksCoordinates() {
        if (this.stepSize > 0.0f) {
            validateConfigurationIfDirty();
            int min = Math.min((int) (((this.valueTo - this.valueFrom) / this.stepSize) + 1.0f), (this.trackWidth / this.minTickSpacing) + 1);
            int i = min + min;
            float[] fArr = this.ticksCoordinates;
            if (fArr == null || fArr.length != i) {
                this.ticksCoordinates = new float[i];
            }
            float f = this.trackWidth;
            int i2 = min - 1;
            for (int i3 = 0; i3 < i; i3 += 2) {
                float[] fArr2 = this.ticksCoordinates;
                fArr2[i3] = this.trackSidePadding + ((i3 / 2.0f) * (f / i2));
                fArr2[i3 + 1] = calculateTrackCenter();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0018, code lost:
    
        if (r1 > r5) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean moveFocus(int r8) {
        /*
            r7 = this;
            int r0 = r7.focusedThumbIdx
            long r1 = (long) r0
            java.util.ArrayList r3 = r7.values
            int r3 = r3.size()
            r4 = -1
            int r3 = r3 + r4
            long r5 = (long) r8
            long r1 = r1 + r5
            r5 = 0
            int r8 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r8 >= 0) goto L15
        L13:
            r1 = r5
            goto L1b
        L15:
            long r5 = (long) r3
            int r8 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r8 <= 0) goto L1b
            goto L13
        L1b:
            int r8 = (int) r1
            r7.focusedThumbIdx = r8
            if (r8 != r0) goto L22
            r8 = 0
            return r8
        L22:
            int r0 = r7.activeThumbIdx
            if (r0 == r4) goto L28
            r7.activeThumbIdx = r8
        L28:
            r7.updateHaloHotspot()
            r7.postInvalidate()
            r8 = 1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.moveFocus(int):boolean");
    }

    private final void moveFocusInAbsoluteDirection$ar$ds(int i) {
        if (isRtl()) {
            if (i == Integer.MIN_VALUE) {
                i = Preference.DEFAULT_ORDER;
            } else {
                i = -i;
            }
        }
        moveFocus(i);
    }

    private final float normalizeValue(float f) {
        float f2 = this.valueFrom;
        float f3 = (f - f2) / (this.valueTo - f2);
        if (isRtl()) {
            return 1.0f - f3;
        }
        return f3;
    }

    private final void onStartTrackingTouch() {
        Iterator it = this.touchListeners.iterator();
        while (it.hasNext()) {
            ((BaseOnSliderTouchListener) it.next()).onStartTrackingTouch$ar$ds();
        }
    }

    private final void setValueForLabel(TooltipDrawable tooltipDrawable, float f) {
        tooltipDrawable.setText(formatValue(f));
        int normalizeValue = this.trackSidePadding + ((int) (normalizeValue(f) * this.trackWidth));
        int intrinsicWidth = tooltipDrawable.getIntrinsicWidth() / 2;
        int calculateTrackCenter = calculateTrackCenter() - (this.labelPadding + (this.thumbHeight / 2));
        int i = normalizeValue - intrinsicWidth;
        tooltipDrawable.setBounds(i, calculateTrackCenter - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + i, calculateTrackCenter);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(DrawableUtils$OutlineCompatL.getContentView(this), this, rect);
        tooltipDrawable.setBounds(rect);
        ((ViewOverlay) DrawableUtils$OutlineCompatL.getContentViewOverlay$ar$class_merging$ar$class_merging(this).StatsStorage$ar$storage).add(tooltipDrawable);
    }

    private final void setValuesInternal(ArrayList arrayList) {
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
            if (this.values.size() == arrayList.size() && this.values.equals(arrayList)) {
                return;
            }
            this.values = arrayList;
            int i = 1;
            this.dirtyConfig = true;
            this.focusedThumbIdx = 0;
            updateHaloHotspot();
            if (this.labels.size() > this.values.size()) {
                List<TooltipDrawable> subList = this.labels.subList(this.values.size(), this.labels.size());
                for (TooltipDrawable tooltipDrawable : subList) {
                    if (isAttachedToWindow()) {
                        detachLabelFromContentView(tooltipDrawable);
                    }
                }
                subList.clear();
            }
            while (this.labels.size() < this.values.size()) {
                Context context = getContext();
                int i2 = this.labelStyle;
                TooltipDrawable tooltipDrawable2 = new TooltipDrawable(context, i2);
                TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(tooltipDrawable2.context, null, com.google.android.material.tooltip.R$styleable.Tooltip, 0, i2, new int[0]);
                tooltipDrawable2.arrowSize = tooltipDrawable2.context.getResources().getDimensionPixelSize(R.dimen.mtrl_tooltip_arrowSize);
                boolean z = obtainStyledAttributes.getBoolean(8, true);
                tooltipDrawable2.showMarker = z;
                if (z) {
                    ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder(tooltipDrawable2.getShapeAppearanceModel());
                    builder.bottomEdge = tooltipDrawable2.createMarkerEdge();
                    tooltipDrawable2.setShapeAppearanceModel(new ShapeAppearanceModel(builder));
                } else {
                    tooltipDrawable2.arrowSize = 0;
                }
                tooltipDrawable2.setText(obtainStyledAttributes.getText(6));
                TextAppearance textAppearance$ar$ds = DrawableUtils$OutlineCompatR.getTextAppearance$ar$ds(tooltipDrawable2.context, obtainStyledAttributes);
                if (textAppearance$ar$ds != null && obtainStyledAttributes.hasValue(1)) {
                    textAppearance$ar$ds.textColor = DrawableUtils$OutlineCompatR.getColorStateList(tooltipDrawable2.context, obtainStyledAttributes, 1);
                }
                tooltipDrawable2.textDrawableHelper.setTextAppearance(textAppearance$ar$ds, tooltipDrawable2.context);
                tooltipDrawable2.setFillColor(ColorStateList.valueOf(obtainStyledAttributes.getColor(7, ColorUtils.compositeColors(ColorUtils.setAlphaComponent(FileUtils.getColor(tooltipDrawable2.context, R.attr.colorOnBackground, TooltipDrawable.class.getCanonicalName()), 153), ColorUtils.setAlphaComponent(FileUtils.getColor(tooltipDrawable2.context, android.R.attr.colorBackground, TooltipDrawable.class.getCanonicalName()), 229)))));
                tooltipDrawable2.setStrokeColor(ColorStateList.valueOf(FileUtils.getColor(tooltipDrawable2.context, R.attr.colorSurface, TooltipDrawable.class.getCanonicalName())));
                tooltipDrawable2.padding = obtainStyledAttributes.getDimensionPixelSize(2, 0);
                tooltipDrawable2.minWidth = obtainStyledAttributes.getDimensionPixelSize(4, 0);
                tooltipDrawable2.minHeight = obtainStyledAttributes.getDimensionPixelSize(5, 0);
                tooltipDrawable2.layoutMargin = obtainStyledAttributes.getDimensionPixelSize(3, 0);
                obtainStyledAttributes.recycle();
                this.labels.add(tooltipDrawable2);
                if (isAttachedToWindow()) {
                    attachLabelToContentView(tooltipDrawable2);
                }
            }
            if (this.labels.size() == 1) {
                i = 0;
            }
            Iterator it = this.labels.iterator();
            while (it.hasNext()) {
                ((TooltipDrawable) it.next()).setStrokeWidth(i);
            }
            for (GooglePlayServicesUpdatedReceiver.Callback callback : this.changeListeners) {
                ArrayList arrayList2 = this.values;
                int size = arrayList2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    callback.onValueChange$ar$ds(this, ((Float) arrayList2.get(i3)).floatValue());
                }
            }
            postInvalidate();
            return;
        }
        throw new IllegalArgumentException("At least one value must be set");
    }

    private final boolean shouldAlwaysShowLabel() {
        if (this.labelBehavior == 3) {
            return true;
        }
        return false;
    }

    private final boolean shouldDrawCompatHalo() {
        if (!(getBackground() instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    private final boolean snapActiveThumbToValue(float f) {
        return snapThumbToValue(this.activeThumbIdx, f);
    }

    private final void snapTouchPosition$ar$ds() {
        double d;
        float f = this.touchPosition;
        float f2 = this.stepSize;
        if (f2 > 0.0f) {
            d = Math.round(f * r1) / ((int) ((this.valueTo - this.valueFrom) / f2));
        } else {
            d = f;
        }
        if (isRtl()) {
            d = 1.0d - d;
        }
        float f3 = this.valueTo;
        snapActiveThumbToValue((float) ((d * (f3 - r1)) + this.valueFrom));
    }

    private final void updateThumbWidthWhenPressed() {
        if (hasGapBetweenThumbAndTrack()) {
            int i = this.thumbWidth;
            this.defaultThumbWidth = i;
            this.defaultThumbTrackGapSize = this.thumbTrackGapSize;
            int round = Math.round(i * 0.5f);
            int i2 = this.thumbWidth - round;
            setThumbWidth(round);
            setThumbTrackGapSize(this.thumbTrackGapSize - (i2 / 2));
        }
    }

    private final void updateTrack$ar$edu(Canvas canvas, Paint paint, RectF rectF, int i) {
        float f;
        int i2 = i - 1;
        float f2 = this.trackHeight / 2.0f;
        if (i2 != 1) {
            if (i2 != 2) {
                f2 = this.trackInsideCornerSize;
                f = f2;
            } else {
                f = f2;
                f2 = this.trackInsideCornerSize;
            }
        } else {
            f = this.trackInsideCornerSize;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        this.trackPath.reset();
        if (rectF.width() >= f2 + f) {
            this.trackPath.addRoundRect(rectF, new float[]{f2, f2, f, f, f, f, f2, f2}, Path.Direction.CW);
            canvas.drawPath(this.trackPath, paint);
            return;
        }
        float min = Math.min(f2, f);
        float max = Math.max(f2, f);
        canvas.save();
        this.trackPath.addRoundRect(rectF, min, min, Path.Direction.CW);
        canvas.clipPath(this.trackPath);
        if (i2 != 1) {
            if (i2 != 2) {
                this.cornerRect.set(rectF.centerX() - max, rectF.top, rectF.centerX() + max, rectF.bottom);
            } else {
                this.cornerRect.set(rectF.right - (max + max), rectF.top, rectF.right, rectF.bottom);
            }
        } else {
            this.cornerRect.set(rectF.left, rectF.top, rectF.left + max + max, rectF.bottom);
        }
        canvas.drawRoundRect(this.cornerRect, max, max, paint);
        canvas.restore();
    }

    private final void updateTrackWidth(int i) {
        int i2 = this.trackSidePadding;
        this.trackWidth = Math.max(i - (i2 + i2), 0);
        maybeCalculateTicksCoordinates();
    }

    private final void updateWidgetLayout() {
        boolean z;
        int max = Math.max(this.minWidgetHeight, Math.max(this.trackHeight + getPaddingTop() + getPaddingBottom(), this.thumbHeight + getPaddingTop() + getPaddingBottom()));
        boolean z2 = true;
        if (max == this.widgetHeight) {
            z = false;
        } else {
            this.widgetHeight = max;
            z = true;
        }
        int max2 = this.minTrackSidePadding + Math.max(Math.max(Math.max((this.thumbWidth / 2) - this.defaultThumbRadius, 0), Math.max((this.trackHeight - this.defaultTrackHeight) / 2, 0)), Math.max(Math.max(this.tickActiveRadius - this.defaultTickActiveRadius, 0), Math.max(this.tickInactiveRadius - this.defaultTickInactiveRadius, 0)));
        if (this.trackSidePadding == max2) {
            z2 = false;
        } else {
            this.trackSidePadding = max2;
            if (isLaidOut()) {
                updateTrackWidth(getWidth());
            }
        }
        if (z) {
            requestLayout();
        } else if (z2) {
            postInvalidate();
        }
    }

    private final void validateConfigurationIfDirty() {
        if (this.dirtyConfig) {
            float f = this.valueFrom;
            float f2 = this.valueTo;
            if (f < f2) {
                if (f < f2) {
                    if (this.stepSize > 0.0f && !valueLandsOnTick(f2)) {
                        throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.valueOf(this.stepSize), Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)));
                    }
                    ArrayList arrayList = this.values;
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        Float f3 = (Float) arrayList.get(i);
                        if (f3.floatValue() >= this.valueFrom && f3.floatValue() <= this.valueTo) {
                            if (this.stepSize > 0.0f && !valueLandsOnTick(f3.floatValue())) {
                                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", f3, Float.valueOf(this.valueFrom), Float.valueOf(this.stepSize), Float.valueOf(this.stepSize)));
                            }
                        } else {
                            throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", f3, Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)));
                        }
                    }
                    float f4 = this.stepSize;
                    if (f4 != 0.0f) {
                        if (((int) f4) != f4) {
                            Log.w(TAG, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "stepSize", Float.valueOf(f4)));
                        }
                        float f5 = this.valueFrom;
                        if (((int) f5) != f5) {
                            Log.w(TAG, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueFrom", Float.valueOf(f5)));
                        }
                        float f6 = this.valueTo;
                        if (((int) f6) != f6) {
                            Log.w(TAG, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueTo", Float.valueOf(f6)));
                        }
                    }
                    this.dirtyConfig = false;
                    return;
                }
                throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.valueOf(f2), Float.valueOf(this.valueFrom)));
            }
            throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.valueOf(f), Float.valueOf(this.valueTo)));
        }
    }

    private final boolean valueLandsOnTick(float f) {
        if (Math.abs(Math.round(r0) - new BigDecimal(Double.toString(new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(this.valueFrom)), MathContext.DECIMAL64).doubleValue())).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue()) < 1.0E-4d) {
            return true;
        }
        return false;
    }

    private final float valueToX(float f) {
        return (normalizeValue(f) * this.trackWidth) + this.trackSidePadding;
    }

    public final void addOnChangeListener$ar$class_merging$ar$class_merging$ar$class_merging(GooglePlayServicesUpdatedReceiver.Callback callback) {
        this.changeListeners.add(callback);
    }

    public final float calculateStepIncrement$ar$ds() {
        float calculateStepIncrement = calculateStepIncrement();
        if ((this.valueTo - this.valueFrom) / calculateStepIncrement <= 20.0f) {
            return calculateStepIncrement;
        }
        return Math.round(r1 / 20.0f) * calculateStepIncrement;
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.accessibilityHelper.dispatchHoverEvent(motionEvent) && !super.dispatchHoverEvent(motionEvent)) {
            return false;
        }
        return true;
    }

    @Override // android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        this.inactiveTrackPaint.setColor(getColorForState(this.trackColorInactive));
        this.activeTrackPaint.setColor(getColorForState(this.trackColorActive));
        this.inactiveTicksPaint.setColor(getColorForState(this.tickColorInactive));
        this.activeTicksPaint.setColor(getColorForState(this.tickColorActive));
        this.stopIndicatorPaint.setColor(getColorForState(this.trackColorActive));
        for (TooltipDrawable tooltipDrawable : this.labels) {
            if (tooltipDrawable.isStateful()) {
                tooltipDrawable.setState(getDrawableState());
            }
        }
        if (this.defaultThumbDrawable.isStateful()) {
            this.defaultThumbDrawable.setState(getDrawableState());
        }
        this.haloPaint.setColor(getColorForState(this.haloColor));
        this.haloPaint.setAlpha(63);
    }

    public final String formatValue(float f) {
        String str;
        if (hasLabelFormatter()) {
            return ((Preference) this.formatter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.RetryingNameResolver$ResolutionResultListener$ar$this$0).getContext().getString(R.string.bd_auto_scroll_duration_unit);
        }
        Object[] objArr = {Float.valueOf(f)};
        if (((int) f) == f) {
            str = "%.0f";
        } else {
            str = "%.2f";
        }
        return String.format(str, objArr);
    }

    @Override // android.view.View
    public final CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getActiveThumbIndex() {
        return this.activeThumbIdx;
    }

    public final int getThumbRadius() {
        return this.thumbWidth / 2;
    }

    public final float getValueFrom() {
        return this.valueFrom;
    }

    public final float getValueTo() {
        return this.valueTo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List getValues() {
        return new ArrayList(this.values);
    }

    public final boolean hasLabelFormatter() {
        if (this.formatter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
            return true;
        }
        return false;
    }

    final boolean isRtl() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
        Iterator it = this.labels.iterator();
        while (it.hasNext()) {
            attachLabelToContentView((TooltipDrawable) it.next());
        }
    }

    @Override // android.view.View
    protected final void onDetachedFromWindow() {
        AccessibilityEventSender accessibilityEventSender = this.accessibilityEventSender;
        if (accessibilityEventSender != null) {
            removeCallbacks(accessibilityEventSender);
        }
        this.labelsAreAnimatedIn = false;
        Iterator it = this.labels.iterator();
        while (it.hasNext()) {
            detachLabelFromContentView((TooltipDrawable) it.next());
        }
        getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x018e A[SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onDraw(android.graphics.Canvas r18) {
        /*
            Method dump skipped, instructions count: 781
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    protected final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 17) {
                        if (i == 66) {
                            moveFocusInAbsoluteDirection$ar$ds(Integer.MIN_VALUE);
                        }
                    } else {
                        moveFocusInAbsoluteDirection$ar$ds(Preference.DEFAULT_ORDER);
                    }
                } else {
                    moveFocus(Integer.MIN_VALUE);
                }
            } else {
                moveFocus(Preference.DEFAULT_ORDER);
            }
            this.accessibilityHelper.requestKeyboardFocusForVirtualView(this.focusedThumbIdx);
            return;
        }
        this.activeThumbIdx = -1;
        this.accessibilityHelper.clearKeyboardFocusForVirtualView(this.focusedThumbIdx);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        float calculateStepIncrement;
        if (!isEnabled()) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.values.size() == 1) {
            this.activeThumbIdx = 0;
        }
        Float f = null;
        Boolean bool = null;
        if (this.activeThumbIdx == -1) {
            if (i != 61) {
                if (i != 66) {
                    if (i != 81) {
                        if (i != 69) {
                            if (i != 70) {
                                switch (i) {
                                    case 21:
                                        moveFocusInAbsoluteDirection$ar$ds(-1);
                                        bool = true;
                                        break;
                                    case 22:
                                        moveFocusInAbsoluteDirection$ar$ds(1);
                                        bool = true;
                                        break;
                                }
                            }
                        } else {
                            moveFocus(-1);
                            bool = true;
                        }
                    }
                    moveFocus(1);
                    bool = true;
                }
                this.activeThumbIdx = this.focusedThumbIdx;
                postInvalidate();
                bool = true;
            } else if (keyEvent.hasNoModifiers()) {
                bool = Boolean.valueOf(moveFocus(1));
            } else if (keyEvent.isShiftPressed()) {
                bool = Boolean.valueOf(moveFocus(-1));
            } else {
                bool = false;
            }
            if (bool != null) {
                return bool.booleanValue();
            }
            return super.onKeyDown(i, keyEvent);
        }
        boolean isLongPress = this.isLongPress | keyEvent.isLongPress();
        this.isLongPress = isLongPress;
        if (isLongPress) {
            calculateStepIncrement = calculateStepIncrement$ar$ds();
        } else {
            calculateStepIncrement = calculateStepIncrement();
        }
        if (i != 21) {
            if (i != 22) {
                if (i != 69) {
                    if (i == 70 || i == 81) {
                        f = Float.valueOf(calculateStepIncrement);
                    }
                } else {
                    f = Float.valueOf(-calculateStepIncrement);
                }
            } else {
                if (isRtl()) {
                    calculateStepIncrement = -calculateStepIncrement;
                }
                f = Float.valueOf(calculateStepIncrement);
            }
        } else {
            if (!isRtl()) {
                calculateStepIncrement = -calculateStepIncrement;
            }
            f = Float.valueOf(calculateStepIncrement);
        }
        if (f != null) {
            if (snapActiveThumbToValue(((Float) this.values.get(this.activeThumbIdx)).floatValue() + f.floatValue())) {
                updateHaloHotspot();
                postInvalidate();
            }
            return true;
        }
        if (i != 23) {
            if (i != 61) {
                if (i != 66) {
                    return super.onKeyDown(i, keyEvent);
                }
            } else {
                if (keyEvent.hasNoModifiers()) {
                    return moveFocus(1);
                }
                if (!keyEvent.isShiftPressed()) {
                    return false;
                }
                return moveFocus(-1);
            }
        }
        this.activeThumbIdx = -1;
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.isLongPress = false;
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3 = this.widgetHeight;
        int i4 = 0;
        if (this.labelBehavior == 1 || shouldAlwaysShowLabel()) {
            i4 = ((TooltipDrawable) this.labels.get(0)).getIntrinsicHeight();
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3 + i4, 1073741824));
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.valueFrom = sliderState.valueFrom;
        this.valueTo = sliderState.valueTo;
        setValuesInternal(sliderState.values);
        this.stepSize = sliderState.stepSize;
        if (sliderState.hasFocus) {
            requestFocus();
        }
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.valueFrom = this.valueFrom;
        sliderState.valueTo = this.valueTo;
        sliderState.values = new ArrayList(this.values);
        sliderState.stepSize = this.stepSize;
        sliderState.hasFocus = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        updateTrackWidth(i);
        updateHaloHotspot();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
    
        if (java.lang.Math.abs(r0 - r5.touchDownX) >= r5.scaledTouchSlop) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0033, code lost:
    
        if (r2 != 3) goto L49;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    protected final void onVisibilityChanged(View view, int i) {
        StatsStorage contentViewOverlay$ar$class_merging$ar$class_merging;
        super.onVisibilityChanged(view, i);
        if (i != 0 && (contentViewOverlay$ar$class_merging$ar$class_merging = DrawableUtils$OutlineCompatL.getContentViewOverlay$ar$class_merging$ar$class_merging(this)) != null) {
            Iterator it = this.labels.iterator();
            while (it.hasNext()) {
                contentViewOverlay$ar$class_merging$ar$class_merging.remove((TooltipDrawable) it.next());
            }
        }
    }

    protected void pickActiveThumb$ar$ds() {
        throw null;
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        int i;
        super.setEnabled(z);
        if (true != z) {
            i = 2;
        } else {
            i = 0;
        }
        setLayerType(i, null);
    }

    public final void setHaloRadius(int i) {
        if (i == this.haloRadius) {
            return;
        }
        this.haloRadius = i;
        Drawable background = getBackground();
        if (!shouldDrawCompatHalo() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setRadius(this.haloRadius);
        } else {
            postInvalidate();
        }
    }

    public final void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.haloColor)) {
            return;
        }
        this.haloColor = colorStateList;
        Drawable background = getBackground();
        if (!shouldDrawCompatHalo() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        this.haloPaint.setColor(getColorForState(colorStateList));
        this.haloPaint.setAlpha(63);
        invalidate();
    }

    public final void setLabelBehavior(int i) {
        if (this.labelBehavior != i) {
            this.labelBehavior = i;
            requestLayout();
        }
    }

    public final void setLabelFormatter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.formatter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    public final void setThumbElevation(float f) {
        this.defaultThumbDrawable.setElevation(f);
    }

    public final void setThumbHeight(int i) {
        if (i == this.thumbHeight) {
            return;
        }
        this.thumbHeight = i;
        this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, i);
        Iterator it = this.customThumbDrawablesForValues.iterator();
        while (it.hasNext()) {
            adjustCustomThumbDrawableBounds((Drawable) it.next());
        }
        updateWidgetLayout();
    }

    public final void setThumbStrokeColor(ColorStateList colorStateList) {
        this.defaultThumbDrawable.setStrokeColor(colorStateList);
        postInvalidate();
    }

    public final void setThumbStrokeWidth(float f) {
        this.defaultThumbDrawable.setStrokeWidth(f);
        postInvalidate();
    }

    public final void setThumbTrackGapSize(int i) {
        if (this.thumbTrackGapSize == i) {
            return;
        }
        this.thumbTrackGapSize = i;
        invalidate();
    }

    public final void setThumbWidth(int i) {
        if (i == this.thumbWidth) {
            return;
        }
        this.thumbWidth = i;
        this.defaultThumbDrawable.setShapeAppearanceModel(new ShapeAppearanceModel(new ShapeAppearanceModel.Builder().setAllCorners$ar$ds(i / 2.0f)));
        this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
        Iterator it = this.customThumbDrawablesForValues.iterator();
        while (it.hasNext()) {
            adjustCustomThumbDrawableBounds((Drawable) it.next());
        }
        updateWidgetLayout();
    }

    public final void setTickActiveRadius(int i) {
        if (this.tickActiveRadius != i) {
            this.tickActiveRadius = i;
            this.activeTicksPaint.setStrokeWidth(i + i);
            updateWidgetLayout();
        }
    }

    public final void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.tickColorActive)) {
            return;
        }
        this.tickColorActive = colorStateList;
        this.activeTicksPaint.setColor(getColorForState(colorStateList));
        invalidate();
    }

    public final void setTickInactiveRadius(int i) {
        if (this.tickInactiveRadius != i) {
            this.tickInactiveRadius = i;
            this.inactiveTicksPaint.setStrokeWidth(i + i);
            updateWidgetLayout();
        }
    }

    public final void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.tickColorInactive)) {
            return;
        }
        this.tickColorInactive = colorStateList;
        this.inactiveTicksPaint.setColor(getColorForState(colorStateList));
        invalidate();
    }

    public final void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.trackColorActive)) {
            return;
        }
        this.trackColorActive = colorStateList;
        this.activeTrackPaint.setColor(getColorForState(colorStateList));
        this.stopIndicatorPaint.setColor(getColorForState(this.trackColorActive));
        invalidate();
    }

    public final void setTrackHeight(int i) {
        if (this.trackHeight != i) {
            this.trackHeight = i;
            this.inactiveTrackPaint.setStrokeWidth(i);
            this.activeTrackPaint.setStrokeWidth(this.trackHeight);
            updateWidgetLayout();
        }
    }

    public final void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.trackColorInactive)) {
            return;
        }
        this.trackColorInactive = colorStateList;
        this.inactiveTrackPaint.setColor(getColorForState(colorStateList));
        invalidate();
    }

    public final void setTrackInsideCornerSize(int i) {
        if (this.trackInsideCornerSize == i) {
            return;
        }
        this.trackInsideCornerSize = i;
        invalidate();
    }

    public final void setTrackStopIndicatorSize(int i) {
        if (this.trackStopIndicatorSize == i) {
            return;
        }
        this.trackStopIndicatorSize = i;
        this.stopIndicatorPaint.setStrokeWidth(i);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setValues(Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final boolean snapThumbToValue(int i, float f) {
        float floatValue;
        float floatValue2;
        this.focusedThumbIdx = i;
        if (Math.abs(f - ((Float) this.values.get(i)).floatValue()) >= 1.0E-4d) {
            isRtl();
            int i2 = i + 1;
            if (i2 >= this.values.size()) {
                floatValue = this.valueTo;
            } else {
                floatValue = ((Float) this.values.get(i2)).floatValue() + 0.0f;
            }
            int i3 = i - 1;
            if (i3 < 0) {
                floatValue2 = this.valueFrom;
            } else {
                floatValue2 = ((Float) this.values.get(i3)).floatValue() + 0.0f;
            }
            this.values.set(i, Float.valueOf(NotificationCompatBuilder$Api26Impl.clamp(f, floatValue2, floatValue)));
            Iterator it = this.changeListeners.iterator();
            while (it.hasNext()) {
                ((GooglePlayServicesUpdatedReceiver.Callback) it.next()).onValueChange$ar$ds(this, ((Float) this.values.get(i)).floatValue());
            }
            AccessibilityManager accessibilityManager = this.accessibilityManager;
            if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                AccessibilityEventSender accessibilityEventSender = this.accessibilityEventSender;
                if (accessibilityEventSender == null) {
                    this.accessibilityEventSender = new AccessibilityEventSender();
                } else {
                    removeCallbacks(accessibilityEventSender);
                }
                AccessibilityEventSender accessibilityEventSender2 = this.accessibilityEventSender;
                accessibilityEventSender2.virtualViewId = i;
                postDelayed(accessibilityEventSender2, 200L);
                return true;
            }
            return true;
        }
        return false;
    }

    final void updateBoundsForVirtualViewId(int i, Rect rect) {
        int normalizeValue = this.trackSidePadding + ((int) (normalizeValue(((Float) getValues().get(i)).floatValue()) * this.trackWidth));
        int calculateTrackCenter = calculateTrackCenter();
        int max = Math.max(this.thumbWidth / 2, this.minTouchTargetSize / 2);
        int max2 = Math.max(this.thumbHeight / 2, this.minTouchTargetSize / 2);
        rect.set(normalizeValue - max, calculateTrackCenter - max2, normalizeValue + max, calculateTrackCenter + max2);
    }

    public final void updateHaloHotspot() {
        if (!shouldDrawCompatHalo() && getMeasuredWidth() > 0) {
            Drawable background = getBackground();
            if (background instanceof RippleDrawable) {
                float normalizeValue = normalizeValue(((Float) this.values.get(this.focusedThumbIdx)).floatValue()) * this.trackWidth;
                float f = this.trackSidePadding;
                int calculateTrackCenter = calculateTrackCenter();
                int i = this.haloRadius;
                int i2 = (int) (normalizeValue + f);
                DrawableCompat$Api21Impl.setHotspotBounds(background, i2 - i, calculateTrackCenter - i, i2 + i, calculateTrackCenter + i);
            }
        }
    }

    public final void updateLabels() {
        int i = this.labelBehavior;
        if (i != 0 && i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (isEnabled()) {
                        Rect rect = new Rect();
                        DrawableUtils$OutlineCompatL.getContentView(this).getHitRect(rect);
                        if (getLocalVisibleRect(rect)) {
                            ensureLabelsAdded();
                            return;
                        }
                    }
                    ensureLabelsRemoved();
                    return;
                }
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unexpected labelBehavior: "));
            }
            ensureLabelsRemoved();
            return;
        }
        if (this.activeThumbIdx != -1 && isEnabled()) {
            ensureLabelsAdded();
        } else {
            ensureLabelsRemoved();
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BaseSlider(android.content.Context r11, android.util.AttributeSet r12, int r13) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new BadgeState.State.AnonymousClass1(6);
        boolean hasFocus;
        float stepSize;
        float valueFrom;
        float valueTo;
        ArrayList values;

        public SliderState(Parcel parcel) {
            super(parcel);
            this.valueFrom = parcel.readFloat();
            this.valueTo = parcel.readFloat();
            ArrayList arrayList = new ArrayList();
            this.values = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.stepSize = parcel.readFloat();
            this.hasFocus = parcel.createBooleanArray()[0];
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.valueFrom);
            parcel.writeFloat(this.valueTo);
            parcel.writeList(this.values);
            parcel.writeFloat(this.stepSize);
            parcel.writeBooleanArray(new boolean[]{this.hasFocus});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
