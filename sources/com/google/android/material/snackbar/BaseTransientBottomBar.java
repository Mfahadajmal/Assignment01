package com.google.android.material.snackbar;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Objects;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BaseTransientBottomBar {
    private final AccessibilityManager accessibilityManager;
    public final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    public final int animationFadeOutDuration;
    public final TimeInterpolator animationScaleInterpolator;
    public final int animationSlideDuration;
    public final TimeInterpolator animationSlideInterpolator;
    public int appliedBottomMarginGestureInset;
    public final SnackbarContentLayout contentViewCallback$ar$class_merging;
    public final Context context;
    public int duration;
    public int extraBottomMarginGestureInset;
    public int extraBottomMarginWindowInset;
    public int extraLeftMarginWindowInset;
    public int extraRightMarginWindowInset;
    public boolean pendingShowingView;
    public final ViewGroup targetParent;
    public final SnackbarBaseLayout view;
    private static final TimeInterpolator DEFAULT_ANIMATION_SLIDE_INTERPOLATOR = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    private static final TimeInterpolator DEFAULT_ANIMATION_FADE_INTERPOLATOR = AnimationUtils.LINEAR_INTERPOLATOR;
    private static final TimeInterpolator DEFAULT_ANIMATION_SCALE_INTERPOLATOR = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
    private static final int[] SNACKBAR_STYLE_ATTR = {R.attr.snackbarStyle};
    public static final String TAG = "BaseTransientBottomBar";
    public static final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return false;
                }
                final BaseTransientBottomBar baseTransientBottomBar = (BaseTransientBottomBar) message.obj;
                final int i2 = message.arg1;
                if (baseTransientBottomBar.shouldAnimate() && baseTransientBottomBar.view.getVisibility() == 0) {
                    if (baseTransientBottomBar.view.animationMode == 1) {
                        ValueAnimator alphaAnimator = baseTransientBottomBar.getAlphaAnimator(1.0f, 0.0f);
                        alphaAnimator.setDuration(baseTransientBottomBar.animationFadeOutDuration);
                        alphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.10
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator) {
                                baseTransientBottomBar.onViewHidden$ar$ds();
                            }
                        });
                        alphaAnimator.start();
                    } else {
                        ValueAnimator valueAnimator = new ValueAnimator();
                        valueAnimator.setIntValues(0, baseTransientBottomBar.getTranslationYBottom());
                        valueAnimator.setInterpolator(baseTransientBottomBar.animationSlideInterpolator);
                        valueAnimator.setDuration(baseTransientBottomBar.animationSlideDuration);
                        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.15
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator) {
                                baseTransientBottomBar.onViewHidden$ar$ds();
                            }

                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationStart(Animator animator) {
                                BaseTransientBottomBar baseTransientBottomBar2 = baseTransientBottomBar;
                                baseTransientBottomBar2.contentViewCallback$ar$class_merging.animateContentOut$ar$ds(baseTransientBottomBar2.animationFadeOutDuration);
                            }
                        });
                        valueAnimator.addUpdateListener(new BottomSheetBehavior.AnonymousClass3(baseTransientBottomBar, 5));
                        valueAnimator.start();
                    }
                } else {
                    baseTransientBottomBar.onViewHidden$ar$ds();
                }
                return true;
            }
            BaseTransientBottomBar baseTransientBottomBar2 = (BaseTransientBottomBar) message.obj;
            if (baseTransientBottomBar2.view.getParent() == null) {
                ViewGroup.LayoutParams layoutParams = baseTransientBottomBar2.view.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    baseTransientBottomBar2.setUpBehavior((CoordinatorLayout.LayoutParams) layoutParams);
                }
                SnackbarBaseLayout snackbarBaseLayout = baseTransientBottomBar2.view;
                ViewGroup viewGroup = baseTransientBottomBar2.targetParent;
                snackbarBaseLayout.addingToTargetParent = true;
                viewGroup.addView(snackbarBaseLayout);
                snackbarBaseLayout.addingToTargetParent = false;
                baseTransientBottomBar2.updateMargins();
                baseTransientBottomBar2.view.setVisibility(4);
            }
            if (baseTransientBottomBar2.view.isLaidOut()) {
                baseTransientBottomBar2.showViewImpl();
            } else {
                baseTransientBottomBar2.pendingShowingView = true;
            }
            return true;
        }
    });
    private final Runnable bottomMarginGestureInsetRunnable = new FlagStore$$ExternalSyntheticLambda3(this, 12, null);
    public final RetryingNameResolver.ResolutionResultListener managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);

    /* compiled from: PG */
    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements OnApplyWindowInsetsListener {
        final /* synthetic */ Object BaseTransientBottomBar$3$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass3(Object obj, int i) {
            this.switching_field = i;
            this.BaseTransientBottomBar$3$ar$this$0 = obj;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            WindowInsetsCompat windowInsetsCompat2;
            if (this.switching_field != 0) {
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.BaseTransientBottomBar$3$ar$this$0;
                if (true != collapsingToolbarLayout.getFitsSystemWindows()) {
                    windowInsetsCompat2 = null;
                } else {
                    windowInsetsCompat2 = windowInsetsCompat;
                }
                if (!Objects.equals(collapsingToolbarLayout.lastInsets, windowInsetsCompat2)) {
                    collapsingToolbarLayout.lastInsets = windowInsetsCompat2;
                    collapsingToolbarLayout.requestLayout();
                }
                return windowInsetsCompat.consumeSystemWindowInsets();
            }
            ((BaseTransientBottomBar) this.BaseTransientBottomBar$3$ar$this$0).extraBottomMarginWindowInset = windowInsetsCompat.getSystemWindowInsetBottom();
            ((BaseTransientBottomBar) this.BaseTransientBottomBar$3$ar$this$0).extraLeftMarginWindowInset = windowInsetsCompat.getSystemWindowInsetLeft();
            ((BaseTransientBottomBar) this.BaseTransientBottomBar$3$ar$this$0).extraRightMarginWindowInset = windowInsetsCompat.getSystemWindowInsetRight();
            ((BaseTransientBottomBar) this.BaseTransientBottomBar$3$ar$this$0).updateMargins();
            return windowInsetsCompat;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Behavior extends SwipeDismissBehavior<View> {
        public final OnDeviceTextDetectionLoadLogEvent delegate$ar$class_merging$48a40cfc_0 = new OnDeviceTextDetectionLoadLogEvent(this);

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public final boolean canSwipeDismissView(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = this.delegate$ar$class_merging$48a40cfc_0;
            if (actionMasked != 0) {
                if (actionMasked == 1 || actionMasked == 3) {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging((RetryingNameResolver.ResolutionResultListener) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode);
                }
            } else if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                SnackbarManager.getInstance().pauseTimeout$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging((RetryingNameResolver.ResolutionResultListener) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode);
            }
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SnackbarBaseLayout extends FrameLayout {
        private static final View.OnTouchListener consumeAllTouchListener = new View.OnTouchListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout.1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        };
        public final float actionTextColorAlpha;
        public boolean addingToTargetParent;
        public int animationMode;
        private final float backgroundOverlayColorAlpha;
        private ColorStateList backgroundTint;
        private PorterDuff.Mode backgroundTintMode;
        public BaseTransientBottomBar baseTransientBottomBar;
        public final int maxInlineActionWidth;
        private final int maxWidth;
        public Rect originalMargins;
        ShapeAppearanceModel shapeAppearanceModel;

        /* JADX INFO: Access modifiers changed from: protected */
        public SnackbarBaseLayout(Context context) {
            this(context, null);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected final void onAttachedToWindow() {
            WindowInsets rootWindowInsets;
            Insets mandatorySystemGestureInsets;
            int i;
            super.onAttachedToWindow();
            BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null && Build.VERSION.SDK_INT >= 29 && (rootWindowInsets = baseTransientBottomBar.view.getRootWindowInsets()) != null) {
                mandatorySystemGestureInsets = rootWindowInsets.getMandatorySystemGestureInsets();
                i = mandatorySystemGestureInsets.bottom;
                baseTransientBottomBar.extraBottomMarginGestureInset = i;
                baseTransientBottomBar.updateMargins();
            }
            ViewCompat.Api20Impl.requestApplyInsets(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected final void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null && baseTransientBottomBar.isShownOrQueued()) {
                BaseTransientBottomBar.handler.post(new FlagStore$$ExternalSyntheticLambda3(baseTransientBottomBar, 13, null));
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null && baseTransientBottomBar.pendingShowingView) {
                baseTransientBottomBar.showViewImpl();
                baseTransientBottomBar.pendingShowingView = false;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.maxWidth > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = this.maxWidth;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
                }
            }
        }

        @Override // android.view.View
        public final void setBackground(Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public final void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null && this.backgroundTint != null) {
                drawable = drawable.mutate();
                DrawableCompat$Api21Impl.setTintList(drawable, this.backgroundTint);
                DrawableCompat$Api21Impl.setTintMode(drawable, this.backgroundTintMode);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public final void setBackgroundTintList(ColorStateList colorStateList) {
            this.backgroundTint = colorStateList;
            if (getBackground() != null) {
                Drawable mutate = getBackground().mutate();
                DrawableCompat$Api21Impl.setTintList(mutate, colorStateList);
                DrawableCompat$Api21Impl.setTintMode(mutate, this.backgroundTintMode);
                if (mutate != getBackground()) {
                    super.setBackgroundDrawable(mutate);
                }
            }
        }

        @Override // android.view.View
        public final void setBackgroundTintMode(PorterDuff.Mode mode) {
            this.backgroundTintMode = mode;
            if (getBackground() != null) {
                Drawable mutate = getBackground().mutate();
                DrawableCompat$Api21Impl.setTintMode(mutate, mode);
                if (mutate != getBackground()) {
                    super.setBackgroundDrawable(mutate);
                }
            }
        }

        @Override // android.view.View
        public final void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (!this.addingToTargetParent && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                this.originalMargins = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
                if (baseTransientBottomBar != null) {
                    baseTransientBottomBar.updateMargins();
                }
            }
        }

        @Override // android.view.View
        public final void setOnClickListener(View.OnClickListener onClickListener) {
            View.OnTouchListener onTouchListener;
            if (onClickListener != null) {
                onTouchListener = null;
            } else {
                onTouchListener = consumeAllTouchListener;
            }
            setOnTouchListener(onTouchListener);
            super.setOnClickListener(onClickListener);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        public SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(MaterialThemeOverlay.wrap(context, attributeSet, 0, 0), attributeSet);
            GradientDrawable gradientDrawable;
            Context context2 = getContext();
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(6)) {
                ViewCompat.Api21Impl.setElevation(this, obtainStyledAttributes.getDimensionPixelSize(6, 0));
            }
            this.animationMode = obtainStyledAttributes.getInt(2, 0);
            if (obtainStyledAttributes.hasValue(8) || obtainStyledAttributes.hasValue(9)) {
                this.shapeAppearanceModel = new ShapeAppearanceModel(ShapeAppearanceModel.builder(context2, attributeSet, 0, 0));
            }
            float f = obtainStyledAttributes.getFloat(3, 1.0f);
            this.backgroundOverlayColorAlpha = f;
            setBackgroundTintList(DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 4));
            setBackgroundTintMode(_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(obtainStyledAttributes.getInt(5, -1), PorterDuff.Mode.SRC_IN));
            this.actionTextColorAlpha = obtainStyledAttributes.getFloat(1, 1.0f);
            this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            this.maxInlineActionWidth = obtainStyledAttributes.getDimensionPixelSize(7, -1);
            obtainStyledAttributes.recycle();
            setOnTouchListener(consumeAllTouchListener);
            setFocusable(true);
            if (getBackground() == null) {
                int layer = FileUtils.layer(FileUtils.getColor(this, R.attr.colorSurface), FileUtils.getColor(this, R.attr.colorOnSurface), f);
                ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
                if (shapeAppearanceModel != null) {
                    Handler handler = BaseTransientBottomBar.handler;
                    MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
                    materialShapeDrawable.setFillColor(ColorStateList.valueOf(layer));
                    gradientDrawable = materialShapeDrawable;
                } else {
                    Resources resources = getResources();
                    Handler handler2 = BaseTransientBottomBar.handler;
                    float dimension = resources.getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
                    GradientDrawable gradientDrawable2 = new GradientDrawable();
                    gradientDrawable2.setShape(0);
                    gradientDrawable2.setCornerRadius(dimension);
                    gradientDrawable2.setColor(layer);
                    gradientDrawable = gradientDrawable2;
                }
                ColorStateList colorStateList = this.backgroundTint;
                if (colorStateList != null) {
                    DrawableCompat$Api21Impl.setTintList(gradientDrawable, colorStateList);
                }
                setBackground(gradientDrawable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseTransientBottomBar(Context context, ViewGroup viewGroup, View view, SnackbarContentLayout snackbarContentLayout) {
        int i;
        if (view != null) {
            if (snackbarContentLayout != null) {
                this.targetParent = viewGroup;
                this.contentViewCallback$ar$class_merging = snackbarContentLayout;
                this.context = context;
                ThemeEnforcement.checkAppCompatTheme(context);
                LayoutInflater from = LayoutInflater.from(context);
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
                int resourceId = obtainStyledAttributes.getResourceId(0, -1);
                obtainStyledAttributes.recycle();
                if (resourceId != -1) {
                    i = R.layout.mtrl_layout_snackbar;
                } else {
                    i = R.layout.design_layout_snackbar;
                }
                SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) from.inflate(i, viewGroup, false);
                this.view = snackbarBaseLayout;
                snackbarBaseLayout.baseTransientBottomBar = this;
                float f = snackbarBaseLayout.actionTextColorAlpha;
                if (f != 1.0f) {
                    SnackbarContentLayout snackbarContentLayout2 = (SnackbarContentLayout) view;
                    snackbarContentLayout2.actionView.setTextColor(FileUtils.layer(FileUtils.getColor(view, R.attr.colorSurface), snackbarContentLayout2.actionView.getCurrentTextColor(), f));
                }
                ((SnackbarContentLayout) view).maxInlineActionWidth = snackbarBaseLayout.maxInlineActionWidth;
                snackbarBaseLayout.addView(view);
                snackbarBaseLayout.setAccessibilityLiveRegion(1);
                snackbarBaseLayout.setImportantForAccessibility(1);
                snackbarBaseLayout.setFitsSystemWindows(true);
                ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(snackbarBaseLayout, new AnonymousClass3(this, 0));
                ViewCompat.setAccessibilityDelegate(snackbarBaseLayout, new AccessibilityDelegateCompat() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.4
                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                        super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                        accessibilityNodeInfoCompat.addAction(1048576);
                        accessibilityNodeInfoCompat.mInfo.setDismissable(true);
                    }

                    @Override // androidx.core.view.AccessibilityDelegateCompat
                    public final boolean performAccessibilityAction(View view2, int i2, Bundle bundle) {
                        if (i2 == 1048576) {
                            BaseTransientBottomBar.this.dismiss();
                            return true;
                        }
                        return super.performAccessibilityAction(view2, i2, bundle);
                    }
                });
                this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
                this.animationSlideDuration = DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationLong2, 250);
                this.animationFadeInDuration = DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationLong2, 150);
                this.animationFadeOutDuration = DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationMedium1, 75);
                this.animationFadeInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context, R.attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_FADE_INTERPOLATOR);
                this.animationScaleInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context, R.attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_SCALE_INTERPOLATOR);
                this.animationSlideInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context, R.attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_SLIDE_INTERPOLATOR);
                return;
            }
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        throw new IllegalArgumentException("Transient bottom bar must have non-null content");
    }

    public final void dismiss() {
        dispatchDismiss(3);
    }

    public final void dispatchDismiss(int i) {
        SnackbarManager snackbarManager = SnackbarManager.getInstance();
        Object obj = snackbarManager.lock;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        synchronized (obj) {
            if (snackbarManager.isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                snackbarManager.cancelSnackbarLocked((SnackbarManager.SnackbarRecord) snackbarManager.SnackbarManager$ar$currentSnackbar, i);
            } else if (snackbarManager.isNextSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                snackbarManager.cancelSnackbarLocked((SnackbarManager.SnackbarRecord) snackbarManager.SnackbarManager$ar$nextSnackbar, i);
            }
        }
    }

    public final ValueAnimator getAlphaAnimator(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.animationFadeInterpolator);
        ofFloat.addUpdateListener(new BottomSheetBehavior.AnonymousClass3(this, 3));
        return ofFloat;
    }

    public int getDuration() {
        throw null;
    }

    public final int getTranslationYBottom() {
        SnackbarBaseLayout snackbarBaseLayout = this.view;
        int height = snackbarBaseLayout.getHeight();
        ViewGroup.LayoutParams layoutParams = snackbarBaseLayout.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return height;
    }

    public final boolean isShownOrQueued() {
        boolean z;
        SnackbarManager snackbarManager = SnackbarManager.getInstance();
        Object obj = snackbarManager.lock;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        synchronized (obj) {
            z = true;
            if (!snackbarManager.isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener) && !snackbarManager.isNextSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                z = false;
            }
        }
        return z;
    }

    public final void onViewHidden$ar$ds() {
        SnackbarManager snackbarManager = SnackbarManager.getInstance();
        Object obj = snackbarManager.lock;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        synchronized (obj) {
            if (snackbarManager.isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                snackbarManager.SnackbarManager$ar$currentSnackbar = null;
                if (snackbarManager.SnackbarManager$ar$nextSnackbar != null) {
                    snackbarManager.showNextSnackbarLocked();
                }
            }
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    final void onViewShown() {
        SnackbarManager snackbarManager = SnackbarManager.getInstance();
        Object obj = snackbarManager.lock;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        synchronized (obj) {
            if (snackbarManager.isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                snackbarManager.scheduleTimeoutLocked((SnackbarManager.SnackbarRecord) snackbarManager.SnackbarManager$ar$currentSnackbar);
            }
        }
    }

    public final void setUpBehavior(CoordinatorLayout.LayoutParams layoutParams) {
        Behavior behavior = new Behavior();
        behavior.delegate$ar$class_merging$48a40cfc_0.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = this.managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        behavior.listener$ar$class_merging$887f9d95_0$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
        layoutParams.setBehavior(behavior);
        layoutParams.insetEdge = 80;
    }

    final boolean shouldAnimate() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        if (enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty()) {
            return true;
        }
        return false;
    }

    public final void show() {
        SnackbarManager snackbarManager = SnackbarManager.getInstance();
        Object obj = snackbarManager.lock;
        int duration = getDuration();
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.managerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        synchronized (obj) {
            if (snackbarManager.isCurrentSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                Object obj2 = snackbarManager.SnackbarManager$ar$currentSnackbar;
                ((SnackbarManager.SnackbarRecord) obj2).duration = duration;
                ((Handler) snackbarManager.SnackbarManager$ar$handler).removeCallbacksAndMessages(obj2);
                snackbarManager.scheduleTimeoutLocked((SnackbarManager.SnackbarRecord) snackbarManager.SnackbarManager$ar$currentSnackbar);
                return;
            }
            if (snackbarManager.isNextSnackbarLocked$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(resolutionResultListener)) {
                ((SnackbarManager.SnackbarRecord) snackbarManager.SnackbarManager$ar$nextSnackbar).duration = duration;
            } else {
                snackbarManager.SnackbarManager$ar$nextSnackbar = new SnackbarManager.SnackbarRecord(duration, resolutionResultListener);
            }
            Object obj3 = snackbarManager.SnackbarManager$ar$currentSnackbar;
            if (obj3 != null && snackbarManager.cancelSnackbarLocked((SnackbarManager.SnackbarRecord) obj3, 4)) {
                return;
            }
            snackbarManager.SnackbarManager$ar$currentSnackbar = null;
            snackbarManager.showNextSnackbarLocked();
        }
    }

    public final void showViewImpl() {
        if (shouldAnimate()) {
            this.view.post(new FlagStore$$ExternalSyntheticLambda3(this, 14, null));
            return;
        }
        if (this.view.getParent() != null) {
            this.view.setVisibility(0);
        }
        onViewShown();
    }

    public final void updateMargins() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            Log.w(TAG, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        SnackbarBaseLayout snackbarBaseLayout = this.view;
        if (snackbarBaseLayout.originalMargins == null) {
            Log.w(TAG, "Unable to update margins because original view margins are not set");
            return;
        }
        if (snackbarBaseLayout.getParent() != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int i = this.view.originalMargins.bottom + this.extraBottomMarginWindowInset;
            int i2 = this.view.originalMargins.left + this.extraLeftMarginWindowInset;
            int i3 = this.view.originalMargins.right + this.extraRightMarginWindowInset;
            int i4 = this.view.originalMargins.top;
            if (marginLayoutParams.bottomMargin == i && marginLayoutParams.leftMargin == i2 && marginLayoutParams.rightMargin == i3 && marginLayoutParams.topMargin == i4) {
                if (this.appliedBottomMarginGestureInset == this.extraBottomMarginGestureInset) {
                    return;
                }
            } else {
                marginLayoutParams.bottomMargin = i;
                marginLayoutParams.leftMargin = i2;
                marginLayoutParams.rightMargin = i3;
                marginLayoutParams.topMargin = i4;
                this.view.requestLayout();
            }
            if (Build.VERSION.SDK_INT >= 29 && this.extraBottomMarginGestureInset > 0) {
                ViewGroup.LayoutParams layoutParams2 = this.view.getLayoutParams();
                if ((layoutParams2 instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams2).mBehavior instanceof SwipeDismissBehavior)) {
                    this.view.removeCallbacks(this.bottomMarginGestureInsetRunnable);
                    this.view.post(this.bottomMarginGestureInsetRunnable);
                }
            }
        }
    }
}
