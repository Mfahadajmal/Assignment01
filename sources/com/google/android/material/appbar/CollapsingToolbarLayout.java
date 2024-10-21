package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.graphics.drawable.DrawableCompat$Api23Impl;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.ArrayList;
import java.util.List;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    public final CollapsingTextHelper collapsingTextHelper;
    private boolean collapsingTitleEnabled;
    private Drawable contentScrim;
    public int currentOffset;
    private boolean drawCollapsingTitle;
    private View dummyView;
    final ElevationOverlayProvider elevationOverlayProvider;
    private int expandedMarginBottom;
    private int expandedMarginEnd;
    private int expandedMarginStart;
    private int expandedMarginTop;
    private int extraMultilineHeight;
    private boolean extraMultilineHeightEnabled;
    private boolean forceApplySystemWindowInsetTop;
    public WindowInsetsCompat lastInsets;
    private FloatingActionButton.ShadowDelegateImpl onOffsetChangedListener$ar$class_merging$ar$class_merging;
    private boolean refreshToolbar;
    private int scrimAlpha;
    private long scrimAnimationDuration;
    private final TimeInterpolator scrimAnimationFadeInInterpolator;
    private final TimeInterpolator scrimAnimationFadeOutInterpolator;
    private ValueAnimator scrimAnimator;
    private int scrimVisibleHeightTrigger;
    private boolean scrimsAreShown;
    public Drawable statusBarScrim;
    private int titleCollapseMode;
    private final Rect tmpRect;
    private ViewGroup toolbar;
    private View toolbarDirectChild;
    private int toolbarId;
    private int topInsetApplied;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends FrameLayout.LayoutParams {
        public int collapseMode;
        public float parallaxMult;

        public LayoutParams() {
            super(-1, -1);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CollapsingToolbarLayout_Layout);
            this.collapseMode = obtainStyledAttributes.getInt(0, 0);
            this.parallaxMult = obtainStyledAttributes.getFloat(1, 0.5f);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    private final void disableLiftOnScrollIfNeeded(AppBarLayout appBarLayout) {
        if (isTitleCollapseFadeMode()) {
            appBarLayout.liftOnScroll = false;
        }
    }

    private final void ensureToolbar() {
        View view;
        if (!this.refreshToolbar) {
            return;
        }
        ViewGroup viewGroup = null;
        this.toolbar = null;
        this.toolbarDirectChild = null;
        int i = this.toolbarId;
        if (i != -1) {
            ViewGroup viewGroup2 = (ViewGroup) findViewById(i);
            this.toolbar = viewGroup2;
            if (viewGroup2 != null) {
                ViewParent parent = viewGroup2.getParent();
                View view2 = viewGroup2;
                while (parent != this && parent != null) {
                    if (parent instanceof View) {
                        view2 = (View) parent;
                    }
                    parent = parent.getParent();
                    view2 = view2;
                }
                this.toolbarDirectChild = view2;
            }
        }
        if (this.toolbar == null) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if ((childAt instanceof Toolbar) || (childAt instanceof android.widget.Toolbar)) {
                    viewGroup = (ViewGroup) childAt;
                    break;
                }
            }
            this.toolbar = viewGroup;
        }
        if (!this.collapsingTitleEnabled && (view = this.dummyView) != null) {
            ViewParent parent2 = view.getParent();
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(this.dummyView);
            }
        }
        if (this.collapsingTitleEnabled && this.toolbar != null) {
            if (this.dummyView == null) {
                this.dummyView = new View(getContext());
            }
            if (this.dummyView.getParent() == null) {
                this.toolbar.addView(this.dummyView, -1, -1);
            }
        }
        this.refreshToolbar = false;
    }

    private static int getHeightWithMargins(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            return view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        }
        return view.getMeasuredHeight();
    }

    public static ViewOffsetHelper getViewOffsetHelper(View view) {
        ViewOffsetHelper viewOffsetHelper = (ViewOffsetHelper) view.getTag(R.id.view_offset_helper);
        if (viewOffsetHelper == null) {
            ViewOffsetHelper viewOffsetHelper2 = new ViewOffsetHelper(view);
            view.setTag(R.id.view_offset_helper, viewOffsetHelper2);
            return viewOffsetHelper2;
        }
        return viewOffsetHelper;
    }

    private final boolean isTitleCollapseFadeMode() {
        if (this.titleCollapseMode == 1) {
            return true;
        }
        return false;
    }

    private final void updateContentScrimBounds(Drawable drawable, int i, int i2) {
        updateContentScrimBounds(drawable, this.toolbar, i, i2);
    }

    private final void updateTextBounds(int i, int i2, int i3, int i4, boolean z) {
        View view;
        boolean z2;
        boolean z3;
        boolean z4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        if (this.collapsingTitleEnabled && (view = this.dummyView) != null) {
            int i11 = 0;
            if (view.isAttachedToWindow() && this.dummyView.getVisibility() == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.drawCollapsingTitle = z2;
            if (!z2) {
                if (z) {
                    z3 = true;
                } else {
                    return;
                }
            } else {
                z3 = z;
            }
            int layoutDirection = getLayoutDirection();
            if (layoutDirection == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            View view2 = this.toolbarDirectChild;
            if (view2 == null) {
                view2 = this.toolbar;
            }
            int maxOffsetForPinChild = getMaxOffsetForPinChild(view2);
            DescendantOffsetUtils.getDescendantRect(this, this.dummyView, this.tmpRect);
            ViewGroup viewGroup = this.toolbar;
            if (viewGroup instanceof Toolbar) {
                Toolbar toolbar = (Toolbar) viewGroup;
                i11 = toolbar.mTitleMarginStart;
                i6 = toolbar.mTitleMarginEnd;
                i7 = toolbar.mTitleMarginTop;
                i5 = toolbar.mTitleMarginBottom;
            } else if (viewGroup instanceof android.widget.Toolbar) {
                android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
                i11 = toolbar2.getTitleMarginStart();
                i6 = toolbar2.getTitleMarginEnd();
                i7 = toolbar2.getTitleMarginTop();
                i5 = toolbar2.getTitleMarginBottom();
            } else {
                i5 = 0;
                i6 = 0;
                i7 = 0;
            }
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            Rect rect = this.tmpRect;
            if (layoutDirection == 1) {
                i8 = i6;
            } else {
                i8 = i11;
            }
            int i12 = rect.left + i8;
            int i13 = this.tmpRect.top + maxOffsetForPinChild;
            Rect rect2 = this.tmpRect;
            if (layoutDirection != 1) {
                i11 = i6;
            }
            collapsingTextHelper.setCollapsedBounds(i12, i13 + i7, rect2.right - i11, (this.tmpRect.bottom + maxOffsetForPinChild) - i5);
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            if (z4) {
                i9 = this.expandedMarginEnd;
            } else {
                i9 = this.expandedMarginStart;
            }
            int i14 = this.tmpRect.top + this.expandedMarginTop;
            int i15 = i3 - i;
            if (z4) {
                i10 = this.expandedMarginStart;
            } else {
                i10 = this.expandedMarginEnd;
            }
            collapsingTextHelper2.setExpandedBounds(i9, i14, i15 - i10, (i4 - i2) - this.expandedMarginBottom);
            this.collapsingTextHelper.recalculate(z3);
        }
    }

    private final void updateTitleFromToolbarIfNeeded() {
        CharSequence charSequence;
        if (this.toolbar != null && this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.text)) {
            ViewGroup viewGroup = this.toolbar;
            if (viewGroup instanceof Toolbar) {
                charSequence = ((Toolbar) viewGroup).mTitleText;
            } else if (viewGroup instanceof android.widget.Toolbar) {
                charSequence = ((android.widget.Toolbar) viewGroup).getTitle();
            } else {
                charSequence = null;
            }
            setTitle(charSequence);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i;
        Drawable drawable;
        super.draw(canvas);
        ensureToolbar();
        if (this.toolbar == null && (drawable = this.contentScrim) != null && this.scrimAlpha > 0) {
            drawable.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
        }
        if (this.collapsingTitleEnabled && this.drawCollapsingTitle) {
            if (this.toolbar != null && this.contentScrim != null && this.scrimAlpha > 0 && isTitleCollapseFadeMode()) {
                CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
                if (collapsingTextHelper.expandedFraction < collapsingTextHelper.fadeModeThresholdFraction) {
                    int save = canvas.save();
                    canvas.clipRect(this.contentScrim.getBounds(), Region.Op.DIFFERENCE);
                    this.collapsingTextHelper.draw(canvas);
                    canvas.restoreToCount(save);
                }
            }
            this.collapsingTextHelper.draw(canvas);
        }
        if (this.statusBarScrim != null && this.scrimAlpha > 0) {
            WindowInsetsCompat windowInsetsCompat = this.lastInsets;
            if (windowInsetsCompat != null) {
                i = windowInsetsCompat.getSystemWindowInsetTop();
            } else {
                i = 0;
            }
            if (i > 0) {
                this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), i - this.currentOffset);
                this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
                this.statusBarScrim.draw(canvas);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected final boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        View view2;
        Drawable drawable = this.contentScrim;
        if (drawable != null && this.scrimAlpha > 0 && ((view2 = this.toolbarDirectChild) == null || view2 == this ? view == this.toolbar : view == view2)) {
            updateContentScrimBounds(drawable, view, getWidth(), getHeight());
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
            z = true;
        } else {
            z = false;
        }
        if (super.drawChild(canvas, view, j) || z) {
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarScrim;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = drawable.setState(drawableState);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        if (collapsingTextHelper != null) {
            z |= collapsingTextHelper.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public final int getMaxOffsetForPinChild(View view) {
        return ((getHeight() - getViewOffsetHelper(view).layoutTop) - view.getHeight()) - ((LayoutParams) view.getLayoutParams()).bottomMargin;
    }

    public final int getScrimVisibleHeightTrigger() {
        int i;
        int i2 = this.scrimVisibleHeightTrigger;
        if (i2 >= 0) {
            return i2 + this.topInsetApplied + this.extraMultilineHeight;
        }
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            i = windowInsetsCompat.getSystemWindowInsetTop();
        } else {
            i = 0;
        }
        int minimumHeight = getMinimumHeight();
        if (minimumHeight > 0) {
            return Math.min(minimumHeight + minimumHeight + i, getHeight());
        }
        return getHeight() / 3;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            disableLiftOnScrollIfNeeded(appBarLayout);
            setFitsSystemWindows(appBarLayout.getFitsSystemWindows());
            if (this.onOffsetChangedListener$ar$class_merging$ar$class_merging == null) {
                this.onOffsetChangedListener$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
            }
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.onOffsetChangedListener$ar$class_merging$ar$class_merging;
            if (appBarLayout.listeners == null) {
                appBarLayout.listeners = new ArrayList();
            }
            if (shadowDelegateImpl != null && !appBarLayout.listeners.contains(shadowDelegateImpl)) {
                appBarLayout.listeners.add(shadowDelegateImpl);
            }
            ViewCompat.Api20Impl.requestApplyInsets(this);
        }
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        List list;
        ViewParent parent = getParent();
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.onOffsetChangedListener$ar$class_merging$ar$class_merging;
        if (shadowDelegateImpl != null && (parent instanceof AppBarLayout) && (list = ((AppBarLayout) parent).listeners) != null) {
            list.remove(shadowDelegateImpl);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (!childAt.getFitsSystemWindows() && childAt.getTop() < systemWindowInsetTop) {
                    int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                    childAt.offsetTopAndBottom(systemWindowInsetTop);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            getViewOffsetHelper(getChildAt(i6)).onViewLayout();
        }
        updateTextBounds(i, i2, i3, i4, false);
        updateTitleFromToolbarIfNeeded();
        updateScrimVisibility();
        int childCount3 = getChildCount();
        for (int i7 = 0; i7 < childCount3; i7++) {
            getViewOffsetHelper(getChildAt(i7)).applyOffsets();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        ensureToolbar();
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            i3 = windowInsetsCompat.getSystemWindowInsetTop();
        } else {
            i3 = 0;
        }
        if ((mode == 0 || this.forceApplySystemWindowInsetTop) && i3 > 0) {
            this.topInsetApplied = i3;
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + i3, 1073741824));
        }
        if (this.extraMultilineHeightEnabled && this.collapsingTextHelper.maxLines > 1) {
            updateTitleFromToolbarIfNeeded();
            updateTextBounds(0, 0, getMeasuredWidth(), getMeasuredHeight(), true);
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            int i4 = collapsingTextHelper.expandedLineCount;
            if (i4 > 1 && collapsingTextHelper.expandedFraction == 0.0f) {
                collapsingTextHelper.getTextPaintExpanded(collapsingTextHelper.tmpPaint);
                this.extraMultilineHeight = Math.round((-collapsingTextHelper.tmpPaint.ascent()) + collapsingTextHelper.tmpPaint.descent()) * (i4 - 1);
                super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + this.extraMultilineHeight, 1073741824));
            } else {
                this.extraMultilineHeight = 0;
            }
        }
        ViewGroup viewGroup = this.toolbar;
        if (viewGroup != null) {
            View view = this.toolbarDirectChild;
            if (view != null && view != this) {
                setMinimumHeight(getHeightWithMargins(view));
            } else {
                setMinimumHeight(getHeightWithMargins(viewGroup));
            }
        }
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.contentScrim;
        if (drawable != null) {
            updateContentScrimBounds(drawable, i, i2);
        }
    }

    public final void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.contentScrim = drawable3;
            if (drawable3 != null) {
                updateContentScrimBounds(drawable3, getWidth(), getHeight());
                this.contentScrim.setCallback(this);
                this.contentScrim.setAlpha(this.scrimAlpha);
            }
            postInvalidateOnAnimation();
        }
    }

    public final void setScrimAlpha(int i) {
        ViewGroup viewGroup;
        if (i != this.scrimAlpha) {
            if (this.contentScrim != null && (viewGroup = this.toolbar) != null) {
                viewGroup.postInvalidateOnAnimation();
            }
            this.scrimAlpha = i;
            postInvalidateOnAnimation();
        }
    }

    public final void setTitle(CharSequence charSequence) {
        CharSequence charSequence2;
        this.collapsingTextHelper.setText(charSequence);
        if (this.collapsingTitleEnabled) {
            charSequence2 = this.collapsingTextHelper.text;
        } else {
            charSequence2 = null;
        }
        setContentDescription(charSequence2);
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        boolean z;
        super.setVisibility(i);
        Drawable drawable = this.statusBarScrim;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (drawable != null && drawable.isVisible() != z) {
            this.statusBarScrim.setVisible(z, false);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != null && drawable2.isVisible() != z) {
            this.contentScrim.setVisible(z, false);
        }
    }

    public final void updateScrimVisibility() {
        boolean z;
        boolean z2;
        TimeInterpolator timeInterpolator;
        if (this.contentScrim != null || this.statusBarScrim != null) {
            int height = getHeight() + this.currentOffset;
            int scrimVisibleHeightTrigger = getScrimVisibleHeightTrigger();
            int i = 0;
            if (height < scrimVisibleHeightTrigger) {
                z = true;
            } else {
                z = false;
            }
            if (isLaidOut() && !isInEditMode()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.scrimsAreShown != z) {
                if (height < scrimVisibleHeightTrigger) {
                    i = PrivateKeyType.INVALID;
                }
                if (z2) {
                    ensureToolbar();
                    ValueAnimator valueAnimator = this.scrimAnimator;
                    if (valueAnimator == null) {
                        ValueAnimator valueAnimator2 = new ValueAnimator();
                        this.scrimAnimator = valueAnimator2;
                        if (i > this.scrimAlpha) {
                            timeInterpolator = this.scrimAnimationFadeInInterpolator;
                        } else {
                            timeInterpolator = this.scrimAnimationFadeOutInterpolator;
                        }
                        valueAnimator2.setInterpolator(timeInterpolator);
                        this.scrimAnimator.addUpdateListener(new BottomSheetBehavior.AnonymousClass3(this, 1));
                    } else if (valueAnimator.isRunning()) {
                        this.scrimAnimator.cancel();
                    }
                    this.scrimAnimator.setDuration(this.scrimAnimationDuration);
                    this.scrimAnimator.setIntValues(this.scrimAlpha, i);
                    this.scrimAnimator.start();
                } else {
                    setScrimAlpha(i);
                }
                this.scrimsAreShown = z;
            }
        }
    }

    @Override // android.view.View
    protected final boolean verifyDrawable(Drawable drawable) {
        if (!super.verifyDrawable(drawable) && drawable != this.contentScrim && drawable != this.statusBarScrim) {
            return false;
        }
        return true;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.collapsingToolbarLayoutStyle);
    }

    private final void updateContentScrimBounds(Drawable drawable, View view, int i, int i2) {
        if (isTitleCollapseFadeMode() && view != null && this.collapsingTitleEnabled) {
            i2 = view.getBottom();
        }
        drawable.setBounds(0, 0, i, i2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected final /* synthetic */ FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, R.style.Widget_Design_CollapsingToolbar), attributeSet, i);
        int compositeOverlayIfNeeded;
        int i2;
        ColorStateList colorStateList;
        TextUtils.TruncateAt truncateAt;
        this.refreshToolbar = true;
        this.tmpRect = new Rect();
        this.scrimVisibleHeightTrigger = -1;
        this.topInsetApplied = 0;
        this.extraMultilineHeight = 0;
        Context context2 = getContext();
        CollapsingTextHelper collapsingTextHelper = new CollapsingTextHelper(this);
        this.collapsingTextHelper = collapsingTextHelper;
        collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        collapsingTextHelper.isRtlTextDirectionHeuristicsEnabled = false;
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(context2);
        this.elevationOverlayProvider = elevationOverlayProvider;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.CollapsingToolbarLayout, i, R.style.Widget_Design_CollapsingToolbar, new int[0]);
        collapsingTextHelper.setExpandedTextGravity(obtainStyledAttributes.getInt(4, 8388691));
        collapsingTextHelper.setCollapsedTextGravity(obtainStyledAttributes.getInt(0, 8388627));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        this.expandedMarginBottom = dimensionPixelSize;
        this.expandedMarginEnd = dimensionPixelSize;
        this.expandedMarginTop = dimensionPixelSize;
        this.expandedMarginStart = dimensionPixelSize;
        if (obtainStyledAttributes.hasValue(8)) {
            this.expandedMarginStart = obtainStyledAttributes.getDimensionPixelSize(8, 0);
        }
        if (obtainStyledAttributes.hasValue(7)) {
            this.expandedMarginEnd = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        }
        if (obtainStyledAttributes.hasValue(9)) {
            this.expandedMarginTop = obtainStyledAttributes.getDimensionPixelSize(9, 0);
        }
        if (obtainStyledAttributes.hasValue(6)) {
            this.expandedMarginBottom = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        }
        this.collapsingTitleEnabled = obtainStyledAttributes.getBoolean(20, true);
        setTitle(obtainStyledAttributes.getText(18));
        collapsingTextHelper.setExpandedTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        collapsingTextHelper.setCollapsedTextAppearance(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (obtainStyledAttributes.hasValue(10)) {
            collapsingTextHelper.setExpandedTextAppearance(obtainStyledAttributes.getResourceId(10, 0));
        }
        if (obtainStyledAttributes.hasValue(1)) {
            collapsingTextHelper.setCollapsedTextAppearance(obtainStyledAttributes.getResourceId(1, 0));
        }
        if (obtainStyledAttributes.hasValue(22)) {
            int i3 = obtainStyledAttributes.getInt(22, -1);
            if (i3 == 0) {
                truncateAt = TextUtils.TruncateAt.START;
            } else if (i3 != 1) {
                truncateAt = i3 != 3 ? TextUtils.TruncateAt.END : TextUtils.TruncateAt.MARQUEE;
            } else {
                truncateAt = TextUtils.TruncateAt.MIDDLE;
            }
            collapsingTextHelper.titleTextEllipsize = truncateAt;
            collapsingTextHelper.recalculate();
        }
        if (obtainStyledAttributes.hasValue(11) && collapsingTextHelper.expandedTextColor != (colorStateList = DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 11))) {
            collapsingTextHelper.expandedTextColor = colorStateList;
            collapsingTextHelper.recalculate();
        }
        if (obtainStyledAttributes.hasValue(2)) {
            collapsingTextHelper.setCollapsedTextColor(DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 2));
        }
        this.scrimVisibleHeightTrigger = obtainStyledAttributes.getDimensionPixelSize(16, -1);
        if (obtainStyledAttributes.hasValue(14) && (i2 = obtainStyledAttributes.getInt(14, 1)) != collapsingTextHelper.maxLines) {
            collapsingTextHelper.maxLines = i2;
            collapsingTextHelper.recalculate();
        }
        if (obtainStyledAttributes.hasValue(21)) {
            collapsingTextHelper.setPositionInterpolator(android.view.animation.AnimationUtils.loadInterpolator(context2, obtainStyledAttributes.getResourceId(21, 0)));
        }
        this.scrimAnimationDuration = obtainStyledAttributes.getInt(15, 600);
        this.scrimAnimationFadeInInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context2, R.attr.motionEasingStandardInterpolator, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        this.scrimAnimationFadeOutInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context2, R.attr.motionEasingStandardInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        setContentScrim(obtainStyledAttributes.getDrawable(3));
        Drawable drawable = obtainStyledAttributes.getDrawable(17);
        Drawable drawable2 = this.statusBarScrim;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.statusBarScrim = mutate;
            if (mutate != null) {
                if (mutate.isStateful()) {
                    this.statusBarScrim.setState(getDrawableState());
                }
                DrawableCompat$Api23Impl.setLayoutDirection(this.statusBarScrim, getLayoutDirection());
                this.statusBarScrim.setVisible(getVisibility() == 0, false);
                this.statusBarScrim.setCallback(this);
                this.statusBarScrim.setAlpha(this.scrimAlpha);
            }
            postInvalidateOnAnimation();
        }
        this.titleCollapseMode = obtainStyledAttributes.getInt(19, 0);
        boolean isTitleCollapseFadeMode = isTitleCollapseFadeMode();
        collapsingTextHelper.fadeModeEnabled = isTitleCollapseFadeMode;
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            disableLiftOnScrollIfNeeded((AppBarLayout) parent);
        }
        if (isTitleCollapseFadeMode && this.contentScrim == null) {
            ColorStateList colorStateListOrNull = FileUtils.getColorStateListOrNull(getContext(), R.attr.colorSurfaceContainer);
            if (colorStateListOrNull != null) {
                compositeOverlayIfNeeded = colorStateListOrNull.getDefaultColor();
            } else {
                compositeOverlayIfNeeded = elevationOverlayProvider.compositeOverlayIfNeeded(elevationOverlayProvider.colorSurface, getResources().getDimension(R.dimen.design_appbar_elevation));
            }
            setContentScrim(new ColorDrawable(compositeOverlayIfNeeded));
        }
        this.toolbarId = obtainStyledAttributes.getResourceId(23, -1);
        this.forceApplySystemWindowInsetTop = obtainStyledAttributes.getBoolean(13, false);
        this.extraMultilineHeightEnabled = obtainStyledAttributes.getBoolean(12, false);
        obtainStyledAttributes.recycle();
        setWillNotDraw(false);
        ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(this, new BaseTransientBottomBar.AnonymousClass3(this, 1));
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected final /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }
}
