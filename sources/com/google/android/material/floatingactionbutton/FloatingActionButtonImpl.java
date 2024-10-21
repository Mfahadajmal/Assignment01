package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat$Api23Impl;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.snackbar.SnackbarManager;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class FloatingActionButtonImpl {
    BorderDrawable borderDrawable;
    Drawable contentBackground;
    public Animator currentAnimator;
    float elevation;
    boolean ensureMinTouchTargetSize;
    public MotionSpec hideMotionSpec;
    float hoveredFocusedTranslationZ;
    public int maxImageSize;
    int minTouchTargetSize;
    public ViewTreeObserver.OnPreDrawListener preDrawListener;
    float pressedTranslationZ;
    Drawable rippleDrawable;
    public float rotation;
    final FloatingActionButton.ShadowDelegateImpl shadowViewDelegate$ar$class_merging;
    ShapeAppearanceModel shapeAppearance;
    MaterialShapeDrawable shapeDrawable;
    public MotionSpec showMotionSpec;
    private final SnackbarManager stateListAnimator$ar$class_merging;
    final FloatingActionButton view;
    static final TimeInterpolator ELEVATION_ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    public static final int SHOW_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
    public static final int SHOW_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    public static final int HIDE_ANIM_DURATION_ATTR = R.attr.motionDurationMedium1;
    public static final int HIDE_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;
    static final int[] PRESSED_ENABLED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled};
    static final int[] HOVERED_FOCUSED_ENABLED_STATE_SET = {android.R.attr.state_hovered, android.R.attr.state_focused, android.R.attr.state_enabled};
    static final int[] FOCUSED_ENABLED_STATE_SET = {android.R.attr.state_focused, android.R.attr.state_enabled};
    static final int[] HOVERED_ENABLED_STATE_SET = {android.R.attr.state_hovered, android.R.attr.state_enabled};
    static final int[] ENABLED_STATE_SET = {android.R.attr.state_enabled};
    static final int[] EMPTY_STATE_SET = new int[0];
    final boolean shadowPaddingEnabled = true;
    public float imageMatrixScale = 1.0f;
    public int animState = 0;
    private final Rect tmpRect = new Rect();
    private final RectF tmpRectF1 = new RectF();
    private final RectF tmpRectF2 = new RectF();
    private final Matrix tmpMatrix = new Matrix();

    /* compiled from: PG */
    /* renamed from: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl$6, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass6 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ Object FloatingActionButtonImpl$6$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass6(Object obj, int i) {
            this.switching_field = i;
            this.FloatingActionButtonImpl$6$ar$this$0 = obj;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            if (this.switching_field != 0) {
                ((CoordinatorLayout) this.FloatingActionButtonImpl$6$ar$this$0).onChildViewsChanged(0);
                return true;
            }
            FloatingActionButtonImpl floatingActionButtonImpl = (FloatingActionButtonImpl) this.FloatingActionButtonImpl$6$ar$this$0;
            float rotation = floatingActionButtonImpl.view.getRotation();
            if (floatingActionButtonImpl.rotation != rotation) {
                floatingActionButtonImpl.rotation = rotation;
            }
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DisabledElevationAnimation extends ShadowAnimatorImpl {
        public DisabledElevationAnimation(FloatingActionButtonImpl floatingActionButtonImpl) {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected final float getTargetShadowSize() {
            return 0.0f;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ElevateToHoveredFocusedTranslationZAnimation extends ShadowAnimatorImpl {
        public ElevateToHoveredFocusedTranslationZAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected final float getTargetShadowSize() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.elevation + floatingActionButtonImpl.hoveredFocusedTranslationZ;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ElevateToPressedTranslationZAnimation extends ShadowAnimatorImpl {
        public ElevateToPressedTranslationZAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected final float getTargetShadowSize() {
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            return floatingActionButtonImpl.elevation + floatingActionButtonImpl.pressedTranslationZ;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface InternalVisibilityChangedListener {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ResetElevationAnimation extends ShadowAnimatorImpl {
        public ResetElevationAnimation() {
            super();
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.ShadowAnimatorImpl
        protected final float getTargetShadowSize() {
            return FloatingActionButtonImpl.this.elevation;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private float shadowSizeEnd;
        private float shadowSizeStart;
        private boolean validValues;

        public ShadowAnimatorImpl() {
        }

        protected abstract float getTargetShadowSize();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            FloatingActionButtonImpl.this.updateShapeElevation((int) this.shadowSizeEnd);
            this.validValues = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float elevation;
            if (!this.validValues) {
                MaterialShapeDrawable materialShapeDrawable = FloatingActionButtonImpl.this.shapeDrawable;
                if (materialShapeDrawable == null) {
                    elevation = 0.0f;
                } else {
                    elevation = materialShapeDrawable.getElevation();
                }
                this.shadowSizeStart = elevation;
                this.shadowSizeEnd = getTargetShadowSize();
                this.validValues = true;
            }
            FloatingActionButtonImpl floatingActionButtonImpl = FloatingActionButtonImpl.this;
            float f = this.shadowSizeStart;
            floatingActionButtonImpl.updateShapeElevation((int) (f + ((this.shadowSizeEnd - f) * valueAnimator.getAnimatedFraction())));
        }
    }

    public FloatingActionButtonImpl(FloatingActionButton floatingActionButton, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.view = floatingActionButton;
        this.shadowViewDelegate$ar$class_merging = shadowDelegateImpl;
        SnackbarManager snackbarManager = new SnackbarManager((byte[]) null);
        this.stateListAnimator$ar$class_merging = snackbarManager;
        snackbarManager.addState(PRESSED_ENABLED_STATE_SET, createElevationAnimator$ar$ds(new ElevateToPressedTranslationZAnimation()));
        snackbarManager.addState(HOVERED_FOCUSED_ENABLED_STATE_SET, createElevationAnimator$ar$ds(new ElevateToHoveredFocusedTranslationZAnimation()));
        snackbarManager.addState(FOCUSED_ENABLED_STATE_SET, createElevationAnimator$ar$ds(new ElevateToHoveredFocusedTranslationZAnimation()));
        snackbarManager.addState(HOVERED_ENABLED_STATE_SET, createElevationAnimator$ar$ds(new ElevateToHoveredFocusedTranslationZAnimation()));
        snackbarManager.addState(ENABLED_STATE_SET, createElevationAnimator$ar$ds(new ResetElevationAnimation()));
        snackbarManager.addState(EMPTY_STATE_SET, createElevationAnimator$ar$ds(new DisabledElevationAnimation(this)));
        this.rotation = floatingActionButton.getRotation();
    }

    private static final ValueAnimator createElevationAnimator$ar$ds(ShadowAnimatorImpl shadowAnimatorImpl) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(ELEVATION_ANIM_INTERPOLATOR);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(shadowAnimatorImpl);
        valueAnimator.addUpdateListener(shadowAnimatorImpl);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    private final void workAroundOreoBug(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new TypeEvaluator() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.5
            final FloatEvaluator floatEvaluator = new FloatEvaluator();

            @Override // android.animation.TypeEvaluator
            public final /* bridge */ /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
                float floatValue = this.floatEvaluator.evaluate(f, (Number) obj, (Number) obj2).floatValue();
                if (floatValue < 0.1f) {
                    floatValue = 0.0f;
                }
                return Float.valueOf(floatValue);
            }
        });
    }

    public final void calculateImageMatrixFromScale(float f, Matrix matrix) {
        matrix.reset();
        if (this.view.getDrawable() != null && this.maxImageSize != 0) {
            RectF rectF = this.tmpRectF1;
            RectF rectF2 = this.tmpRectF2;
            rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
            float f2 = this.maxImageSize;
            rectF2.set(0.0f, 0.0f, f2, f2);
            matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            float f3 = this.maxImageSize / 2.0f;
            matrix.postScale(f, f, f3, f3);
        }
    }

    public final AnimatorSet createAnimator(MotionSpec motionSpec, float f, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.view, (Property<FloatingActionButton, Float>) View.ALPHA, f);
        motionSpec.getTiming("opacity").apply(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.view, (Property<FloatingActionButton, Float>) View.SCALE_X, f2);
        motionSpec.getTiming("scale").apply(ofFloat2);
        workAroundOreoBug(ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.view, (Property<FloatingActionButton, Float>) View.SCALE_Y, f2);
        motionSpec.getTiming("scale").apply(ofFloat3);
        workAroundOreoBug(ofFloat3);
        arrayList.add(ofFloat3);
        calculateImageMatrixFromScale(f3, this.tmpMatrix);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.view, new ImageMatrixProperty(), new MatrixEvaluator() { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.3
            @Override // com.google.android.material.animation.MatrixEvaluator
            public final Matrix evaluate(float f4, Matrix matrix, Matrix matrix2) {
                FloatingActionButtonImpl.this.imageMatrixScale = f4;
                return super.evaluate(f4, matrix, matrix2);
            }

            @Override // com.google.android.material.animation.MatrixEvaluator, android.animation.TypeEvaluator
            public final /* bridge */ /* synthetic */ Object evaluate(float f4, Object obj, Object obj2) {
                return evaluate(f4, (Matrix) obj, (Matrix) obj2);
            }
        }, new Matrix(this.tmpMatrix));
        motionSpec.getTiming("iconScale").apply(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        FileUtils.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    public final AnimatorSet createDefaultAnimator(final float f, final float f2, final float f3, int i, int i2) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        final float alpha = this.view.getAlpha();
        final float scaleX = this.view.getScaleX();
        final float scaleY = this.view.getScaleY();
        final float f4 = this.imageMatrixScale;
        final Matrix matrix = new Matrix(this.tmpMatrix);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.floatingactionbutton.FloatingActionButtonImpl.4
            final /* synthetic */ FloatingActionButtonImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.this$0.view.setAlpha(AnimationUtils.lerp(alpha, f, 0.0f, 0.2f, floatValue));
                float f5 = f2;
                float f6 = scaleX;
                this.this$0.view.setScaleX(f6 + ((f5 - f6) * floatValue));
                float f7 = f2;
                float f8 = scaleY;
                this.this$0.view.setScaleY(f8 + ((f7 - f8) * floatValue));
                float f9 = f3;
                float f10 = f4;
                float f11 = floatValue * (f9 - f10);
                FloatingActionButtonImpl floatingActionButtonImpl = this.this$0;
                float f12 = f10 + f11;
                floatingActionButtonImpl.imageMatrixScale = f12;
                floatingActionButtonImpl.calculateImageMatrixFromScale(f12, matrix);
                this.this$0.view.setImageMatrix(matrix);
            }
        });
        arrayList.add(ofFloat);
        FileUtils.playTogether(animatorSet, arrayList);
        animatorSet.setDuration(DrawableUtils$OutlineCompatR.resolveInteger(this.view.getContext(), i, this.view.getContext().getResources().getInteger(R.integer.material_motion_duration_long_1)));
        animatorSet.setInterpolator(DrawableUtils$OutlineCompatL.resolveThemeInterpolator(this.view.getContext(), i2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    public MaterialShapeDrawable createShapeDrawable() {
        throw null;
    }

    public float getElevation() {
        return this.elevation;
    }

    public void getPadding(Rect rect) {
        int touchTargetPadding = getTouchTargetPadding();
        int max = Math.max(touchTargetPadding, (int) Math.ceil(getElevation() + this.pressedTranslationZ));
        int max2 = Math.max(touchTargetPadding, (int) Math.ceil(r1 * 1.5f));
        rect.set(max, max2, max, max2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getTouchTargetPadding() {
        if (!this.ensureMinTouchTargetSize) {
            return 0;
        }
        return Math.max((this.minTouchTargetSize - this.view.getSizeDimension()) / 2, 0);
    }

    public void initializeBackgroundDrawable(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        throw null;
    }

    public void jumpDrawableToCurrentState() {
        throw null;
    }

    public void onDrawableStateChanged(int[] iArr) {
        throw null;
    }

    public void onElevationsChanged(float f, float f2, float f3) {
        throw null;
    }

    public boolean requirePreDrawListener() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setImageMatrixScale(float f) {
        this.imageMatrixScale = f;
        Matrix matrix = this.tmpMatrix;
        calculateImageMatrixFromScale(f, matrix);
        this.view.setImageMatrix(matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setShapeAppearance(ShapeAppearanceModel shapeAppearanceModel) {
        this.shapeAppearance = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        Object obj = this.rippleDrawable;
        if (obj instanceof Shapeable) {
            ((Shapeable) obj).setShapeAppearanceModel(shapeAppearanceModel);
        }
        BorderDrawable borderDrawable = this.borderDrawable;
        if (borderDrawable != null) {
            borderDrawable.shapeAppearanceModel = shapeAppearanceModel;
            borderDrawable.invalidateSelf();
        }
    }

    public boolean shouldAddPadding() {
        return true;
    }

    public final boolean shouldAnimateVisibilityChange() {
        if (this.view.isLaidOut() && !this.view.isInEditMode()) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean shouldExpandBoundsForA11y() {
        if (this.ensureMinTouchTargetSize && this.view.getSizeDimension() < this.minTouchTargetSize) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateImageMatrixScale() {
        setImageMatrixScale(this.imageMatrixScale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updatePadding() {
        Rect rect = this.tmpRect;
        getPadding(rect);
        ContextCompat$Api23Impl.checkNotNull$ar$ds$4e7b8cd1_0(this.contentBackground, "Didn't initialize content background");
        if (shouldAddPadding()) {
            this.shadowViewDelegate$ar$class_merging.setBackgroundDrawable(new InsetDrawable(this.contentBackground, rect.left, rect.top, rect.right, rect.bottom));
        } else {
            this.shadowViewDelegate$ar$class_merging.setBackgroundDrawable(this.contentBackground);
        }
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.shadowViewDelegate$ar$class_merging;
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        ((FloatingActionButton) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).shadowPadding.set(i, i2, i3, i4);
        FloatingActionButton floatingActionButton = (FloatingActionButton) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
        int i5 = floatingActionButton.imagePadding;
        floatingActionButton.setPadding(i + i5, i2 + i5, i3 + i5, i4 + i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateShapeElevation(float f) {
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setElevation(f);
        }
    }
}
