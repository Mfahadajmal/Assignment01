package com.google.android.material.navigation;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.TooltipCompat$Api26Impl;
import android.text.TextUtils;
import android.util.Log;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat$Api21Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.PointerIconCompat$Api24Impl;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.BaselineLayout;
import com.google.android.material.ripple.RippleUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NavigationBarItemView extends FrameLayout implements MenuView.ItemView {
    private ValueAnimator activeIndicatorAnimator;
    private int activeIndicatorDesiredHeight;
    public int activeIndicatorDesiredWidth;
    private boolean activeIndicatorEnabled;
    private int activeIndicatorLabelPadding;
    public int activeIndicatorMarginHorizontal;
    public float activeIndicatorProgress;
    private DrawableUtils$OutlineCompatR activeIndicatorTransform$ar$class_merging$ar$class_merging$ar$class_merging;
    public final View activeIndicatorView;
    private int activeTextAppearance;
    public BadgeDrawable badgeDrawable;
    private int badgeFixedEdge;
    private final LinearLayout contentContainer;
    public final ImageView icon;
    private final FrameLayout iconContainer;
    private ColorStateList iconTint;
    public boolean initialized;
    public final LinearLayout innerContentContainer;
    private boolean isShifting;
    Drawable itemBackground;
    public MenuItemImpl itemData;
    public int itemIconGravity;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private ColorStateList itemRippleColor;
    private final BaselineLayout labelGroup;
    private int labelVisibilityMode;
    private final TextView largeLabel;
    private Drawable originalIconDrawable;
    private float scaleDownFactor;
    private float scaleUpFactor;
    private float shiftAmount;
    private final TextView smallLabel;
    private Drawable wrappedIconDrawable;
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final DrawableUtils$OutlineCompatR ACTIVE_INDICATOR_LABELED_TRANSFORM$ar$class_merging$ar$class_merging$ar$class_merging = new DrawableUtils$OutlineCompatR();
    private static final DrawableUtils$OutlineCompatR ACTIVE_INDICATOR_UNLABELED_TRANSFORM$ar$class_merging$ar$class_merging$ar$class_merging = new ActiveIndicatorUnlabeledTransform();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ActiveIndicatorUnlabeledTransform extends DrawableUtils$OutlineCompatR {
    }

    public NavigationBarItemView(Context context) {
        super(context);
        this.initialized = false;
        this.activeTextAppearance = 0;
        this.activeIndicatorTransform$ar$class_merging$ar$class_merging$ar$class_merging = ACTIVE_INDICATOR_LABELED_TRANSFORM$ar$class_merging$ar$class_merging$ar$class_merging;
        this.activeIndicatorProgress = 0.0f;
        this.activeIndicatorEnabled = false;
        this.activeIndicatorDesiredWidth = 0;
        this.activeIndicatorDesiredHeight = 0;
        this.activeIndicatorMarginHorizontal = 0;
        this.badgeFixedEdge = 0;
        LayoutInflater.from(context).inflate(com.google.android.marvin.talkback.R.layout.design_bottom_navigation_item, (ViewGroup) this, true);
        this.contentContainer = (LinearLayout) findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_content_container);
        LinearLayout linearLayout = (LinearLayout) findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_inner_content_container);
        this.innerContentContainer = linearLayout;
        this.activeIndicatorView = findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_active_indicator_view);
        this.iconContainer = (FrameLayout) findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_icon_container);
        this.icon = (ImageView) findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_icon_view);
        BaselineLayout baselineLayout = (BaselineLayout) findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_labels_group);
        this.labelGroup = baselineLayout;
        TextView textView = (TextView) findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_small_label_view);
        this.smallLabel = textView;
        TextView textView2 = (TextView) findViewById(com.google.android.marvin.talkback.R.id.navigation_bar_item_large_label_view);
        this.largeLabel = textView2;
        setBackgroundResource(com.google.android.marvin.talkback.R.drawable.mtrl_navigation_bar_item_background);
        this.itemPaddingTop = getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.design_bottom_navigation_margin);
        this.itemPaddingBottom = baselineLayout.getPaddingBottom();
        this.activeIndicatorLabelPadding = 0;
        textView.setImportantForAccessibility(2);
        textView2.setImportantForAccessibility(2);
        setFocusable(true);
        calculateTextScaleFactors(textView.getTextSize(), textView2.getTextSize());
        linearLayout.addOnLayoutChangeListener(new NavigationBarItemView$$ExternalSyntheticLambda0(this, 0));
    }

    private final void calculateTextScaleFactors(float f, float f2) {
        this.shiftAmount = f - f2;
        this.scaleUpFactor = f2 / f;
        this.scaleDownFactor = f / f2;
    }

    private final void refreshChecked() {
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null) {
            setChecked(menuItemImpl.isChecked());
        }
    }

    private final void refreshItemBackground() {
        Drawable drawable = this.itemBackground;
        boolean z = true;
        RippleDrawable rippleDrawable = null;
        if (this.itemRippleColor != null) {
            Drawable activeIndicatorDrawable = getActiveIndicatorDrawable();
            if (this.activeIndicatorEnabled && getActiveIndicatorDrawable() != null && activeIndicatorDrawable != null) {
                rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.itemRippleColor), null, activeIndicatorDrawable);
                z = false;
            } else if (drawable == null) {
                ColorStateList colorStateList = this.itemRippleColor;
                int[] iArr = RippleUtils.PRESSED_STATE_SET;
                int colorForState = RippleUtils.getColorForState(colorStateList, RippleUtils.SELECTED_PRESSED_STATE_SET);
                int[] iArr2 = RippleUtils.FOCUSED_STATE_SET;
                drawable = new RippleDrawable(new ColorStateList(new int[][]{RippleUtils.SELECTED_STATE_SET, iArr2, StateSet.NOTHING}, new int[]{colorForState, RippleUtils.getColorForState(colorStateList, iArr2), RippleUtils.getColorForState(colorStateList, RippleUtils.PRESSED_STATE_SET)}), null, null);
            }
        }
        this.iconContainer.setPadding(0, 0, 0, 0);
        this.iconContainer.setForeground(rippleDrawable);
        setBackground(drawable);
        setDefaultFocusHighlightEnabled(z);
    }

    private final void setLayoutConfigurationIconAndLabel(View view, View view2, float f, float f2) {
        int i;
        int i2;
        int i3;
        int i4 = this.itemIconGravity;
        if (i4 == 0) {
            i = (int) (this.itemPaddingTop + f2);
        } else {
            i = 0;
        }
        LinearLayout linearLayout = this.contentContainer;
        if (i4 == 0) {
            i2 = 49;
        } else {
            i2 = 17;
        }
        setViewMarginAndGravity(linearLayout, i, 0, i2);
        BaselineLayout baselineLayout = this.labelGroup;
        if (this.itemIconGravity == 0) {
            i3 = this.itemPaddingBottom;
        } else {
            i3 = 0;
        }
        updateViewPaddingBottom(baselineLayout, i3);
        this.labelGroup.setVisibility(0);
        setViewScaleValues(view, 1.0f, 1.0f, 0);
        setViewScaleValues(view2, f, f, 4);
    }

    private final void setLayoutConfigurationIconOnly() {
        LinearLayout linearLayout = this.contentContainer;
        int i = this.itemPaddingTop;
        setViewMarginAndGravity(linearLayout, i, i, 17);
        updateViewPaddingBottom(this.labelGroup, 0);
        this.labelGroup.setVisibility(8);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void setTextAppearanceWithoutFontScaling(android.widget.TextView r4, int r5) {
        /*
            r4.setTextAppearance(r5)
            android.content.Context r0 = r4.getContext()
            r1 = 0
            if (r5 != 0) goto Lc
        La:
            r5 = r1
            goto L4c
        Lc:
            int[] r2 = com.google.android.material.resources.R$styleable.TextAppearance
            android.content.res.TypedArray r5 = r0.obtainStyledAttributes(r5, r2)
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            boolean r3 = r5.getValue(r1, r2)
            r5.recycle()
            if (r3 != 0) goto L21
            goto La
        L21:
            int r5 = r2.getComplexUnit()
            r3 = 2
            if (r5 != r3) goto L3e
            int r5 = r2.data
            float r5 = android.util.TypedValue.complexToFloat(r5)
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.density
            float r5 = r5 * r0
            int r5 = java.lang.Math.round(r5)
            goto L4c
        L3e:
            int r5 = r2.data
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r5 = android.util.TypedValue.complexToDimensionPixelSize(r5, r0)
        L4c:
            if (r5 == 0) goto L52
            float r5 = (float) r5
            r4.setTextSize(r1, r5)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setTextAppearanceWithoutFontScaling(android.widget.TextView, int):void");
    }

    private static void setViewMarginAndGravity(View view, int i, int i2, int i3) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = i2;
        layoutParams.gravity = i3;
        view.setLayoutParams(layoutParams);
    }

    private static void setViewScaleValues(View view, float f, float f2, int i) {
        view.setScaleX(f);
        view.setScaleY(f2);
        view.setVisibility(i);
    }

    private static void updateViewPaddingBottom(View view, int i) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.activeIndicatorEnabled) {
            this.iconContainer.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final Drawable getActiveIndicatorDrawable() {
        return this.activeIndicatorView.getBackground();
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final MenuItemImpl getItemData() {
        return this.itemData;
    }

    @Override // android.view.View
    protected final int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.contentContainer.getLayoutParams();
        return this.contentContainer.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    protected final int getSuggestedMinimumWidth() {
        int minimumWidth;
        if (this.itemIconGravity == 1) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.innerContentContainer.getLayoutParams();
            return this.innerContentContainer.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.labelGroup.getLayoutParams();
        int measuredWidth = layoutParams2.leftMargin + this.labelGroup.getMeasuredWidth() + layoutParams2.rightMargin;
        BadgeDrawable badgeDrawable = this.badgeDrawable;
        if (badgeDrawable == null) {
            minimumWidth = 0;
        } else {
            minimumWidth = badgeDrawable.getMinimumWidth() - this.badgeDrawable.state.getHorizontalOffsetWithoutText();
        }
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.iconContainer.getLayoutParams();
        return Math.max(Math.max(minimumWidth, layoutParams3.leftMargin) + this.icon.getMeasuredWidth() + Math.max(minimumWidth, layoutParams3.rightMargin), measuredWidth);
    }

    public final boolean hasBadge() {
        if (this.badgeDrawable != null) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final void initialize$ar$ds(MenuItemImpl menuItemImpl) {
        CharSequence charSequence;
        int i;
        this.itemData = menuItemImpl;
        menuItemImpl.isCheckable();
        refreshDrawableState();
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        Drawable icon = menuItemImpl.getIcon();
        if (icon != this.originalIconDrawable) {
            this.originalIconDrawable = icon;
            if (icon != null) {
                Drawable.ConstantState constantState = icon.getConstantState();
                if (constantState != null) {
                    icon = constantState.newDrawable();
                }
                icon = icon.mutate();
                this.wrappedIconDrawable = icon;
                ColorStateList colorStateList = this.iconTint;
                if (colorStateList != null) {
                    DrawableCompat$Api21Impl.setTintList(icon, colorStateList);
                }
            }
            this.icon.setImageDrawable(icon);
        }
        CharSequence charSequence2 = menuItemImpl.mTitle;
        this.smallLabel.setText(charSequence2);
        this.largeLabel.setText(charSequence2);
        MenuItemImpl menuItemImpl2 = this.itemData;
        if (menuItemImpl2 == null || TextUtils.isEmpty(menuItemImpl2.mContentDescription)) {
            setContentDescription(charSequence2);
        }
        MenuItemImpl menuItemImpl3 = this.itemData;
        if (menuItemImpl3 != null && !TextUtils.isEmpty(menuItemImpl3.mTooltipText)) {
            charSequence2 = this.itemData.mTooltipText;
        }
        TooltipCompat$Api26Impl.setTooltipText(this, charSequence2);
        setId(menuItemImpl.mId);
        if (!TextUtils.isEmpty(menuItemImpl.mContentDescription)) {
            setContentDescription(menuItemImpl.mContentDescription);
        }
        if (!TextUtils.isEmpty(menuItemImpl.mTooltipText)) {
            charSequence = menuItemImpl.mTooltipText;
        } else {
            charSequence = menuItemImpl.mTitle;
        }
        TooltipCompat$Api26Impl.setTooltipText(this, charSequence);
        if (true != menuItemImpl.isVisible()) {
            i = 8;
        } else {
            i = 0;
        }
        setVisibility(i);
        this.initialized = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.itemData.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        Context context;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable = this.badgeDrawable;
        if (badgeDrawable != null && badgeDrawable.isVisible()) {
            MenuItemImpl menuItemImpl = this.itemData;
            CharSequence charSequence = menuItemImpl.mTitle;
            if (!TextUtils.isEmpty(menuItemImpl.mContentDescription)) {
                charSequence = this.itemData.mContentDescription;
            }
            String valueOf = String.valueOf(charSequence);
            BadgeDrawable badgeDrawable2 = this.badgeDrawable;
            Object obj = null;
            if (badgeDrawable2.isVisible()) {
                if (badgeDrawable2.hasText()) {
                    obj = badgeDrawable2.state.currentState.contentDescriptionForText;
                    if (obj == null) {
                        obj = badgeDrawable2.getText();
                    }
                } else if (badgeDrawable2.hasNumber()) {
                    if (badgeDrawable2.state.getContentDescriptionQuantityStrings() != 0 && (context = (Context) badgeDrawable2.contextRef.get()) != null) {
                        if (badgeDrawable2.maxBadgeNumber != -2) {
                            int number = badgeDrawable2.getNumber();
                            int i = badgeDrawable2.maxBadgeNumber;
                            if (number > i) {
                                obj = context.getString(badgeDrawable2.state.currentState.contentDescriptionExceedsMaxBadgeNumberRes, Integer.valueOf(i));
                            }
                        }
                        obj = context.getResources().getQuantityString(badgeDrawable2.state.getContentDescriptionQuantityStrings(), badgeDrawable2.getNumber(), Integer.valueOf(badgeDrawable2.getNumber()));
                    }
                } else {
                    obj = badgeDrawable2.state.currentState.contentDescriptionNumberless;
                }
            }
            accessibilityNodeInfo.setContentDescription(valueOf + ", " + String.valueOf(obj));
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
        ViewGroup viewGroup = (ViewGroup) getParent();
        int indexOfChild = viewGroup.indexOfChild(this);
        int i2 = 0;
        for (int i3 = 0; i3 < indexOfChild; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i2++;
            }
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$c443ecb5_0(0, 1, i2, 1, isSelected()));
        if (isSelected()) {
            accessibilityNodeInfoCompat.setClickable(false);
            accessibilityNodeInfoCompat.removeAction$ar$ds(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }
        accessibilityNodeInfoCompat.mInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(com.google.android.marvin.talkback.R.string.item_view_role_description));
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new RatingView$$ExternalSyntheticLambda5(this, i, 3, (byte[]) null));
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final boolean prefersCondensedTitle() {
        return false;
    }

    public final void setActiveIndicatorDrawable(Drawable drawable) {
        this.activeIndicatorView.setBackground(drawable);
        refreshItemBackground();
    }

    public final void setActiveIndicatorEnabled(boolean z) {
        int i;
        this.activeIndicatorEnabled = z;
        refreshItemBackground();
        if (true != z) {
            i = 8;
        } else {
            i = 0;
        }
        this.activeIndicatorView.setVisibility(i);
        requestLayout();
    }

    public final void setActiveIndicatorHeight(int i) {
        this.activeIndicatorDesiredHeight = i;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public final void setActiveIndicatorLabelPadding(int i) {
        if (this.activeIndicatorLabelPadding != i) {
            this.activeIndicatorLabelPadding = i;
            ((LinearLayout.LayoutParams) this.labelGroup.getLayoutParams()).topMargin = i;
            requestLayout();
        }
    }

    public final void setActiveIndicatorMarginHorizontal(int i) {
        this.activeIndicatorMarginHorizontal = i;
        if (this.itemIconGravity == 1) {
            setPadding(i, 0, i, 0);
        }
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public final void setActiveIndicatorProgress(float f, float f2) {
        float f3;
        float f4;
        DrawableUtils$OutlineCompatR drawableUtils$OutlineCompatR = this.activeIndicatorTransform$ar$class_merging$ar$class_merging$ar$class_merging;
        View view = this.activeIndicatorView;
        view.setScaleX(DrawableUtils$OutlineCompatR.calculateScaleX$ar$ds(f));
        view.setScaleY(drawableUtils$OutlineCompatR.calculateScaleY(f, f2));
        if (f2 == 0.0f) {
            f3 = 0.8f;
        } else {
            f3 = 0.0f;
        }
        if (f2 == 0.0f) {
            f4 = 1.0f;
        } else {
            f4 = 0.2f;
        }
        view.setAlpha(AnimationUtils.lerp(0.0f, 1.0f, f3, f4, f));
        this.activeIndicatorProgress = f;
    }

    public final void setActiveIndicatorWidth(int i) {
        this.activeIndicatorDesiredWidth = i;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setBadge(BadgeDrawable badgeDrawable) {
        if (this.badgeDrawable != badgeDrawable) {
            if (hasBadge() && this.icon != null) {
                Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
                tryRemoveBadgeFromAnchor(this.icon);
            }
            this.badgeDrawable = badgeDrawable;
            int i = this.badgeFixedEdge;
            BadgeState badgeState = badgeDrawable.state;
            if (badgeState.badgeFixedEdge != i) {
                badgeState.badgeFixedEdge = i;
                badgeDrawable.updateCenterAndBounds();
            }
            ImageView imageView = this.icon;
            if (imageView != null && hasBadge()) {
                setClipChildren(false);
                setClipToPadding(false);
                BadgeDrawable badgeDrawable2 = this.badgeDrawable;
                FileUtils.setBadgeDrawableBounds$ar$ds(badgeDrawable2, imageView);
                if (badgeDrawable2.getCustomBadgeParent() != null) {
                    badgeDrawable2.getCustomBadgeParent().setForeground(badgeDrawable2);
                } else {
                    imageView.getOverlay().add(badgeDrawable2);
                }
            }
        }
    }

    public final void setChecked(boolean z) {
        final float f;
        this.largeLabel.setPivotX(this.largeLabel.getWidth() / 2);
        this.largeLabel.setPivotY(this.largeLabel.getBaseline());
        this.smallLabel.setPivotX(this.smallLabel.getWidth() / 2);
        this.smallLabel.setPivotY(this.smallLabel.getBaseline());
        boolean z2 = this.activeIndicatorEnabled;
        if (true != z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (z2 && this.initialized && isAttachedToWindow()) {
            ValueAnimator valueAnimator = this.activeIndicatorAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.activeIndicatorAnimator = null;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.activeIndicatorProgress, f);
            this.activeIndicatorAnimator = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.navigation.NavigationBarItemView.2
                final /* synthetic */ NavigationBarItemView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    this.this$0.setActiveIndicatorProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue(), f);
                }
            });
            this.activeIndicatorAnimator.setInterpolator(DrawableUtils$OutlineCompatL.resolveThemeInterpolator(getContext(), com.google.android.marvin.talkback.R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            this.activeIndicatorAnimator.setDuration(DrawableUtils$OutlineCompatR.resolveInteger(getContext(), com.google.android.marvin.talkback.R.attr.motionDurationLong2, getResources().getInteger(com.google.android.marvin.talkback.R.integer.material_motion_duration_long_1)));
            this.activeIndicatorAnimator.start();
        } else {
            setActiveIndicatorProgress(f, f);
        }
        int i = this.labelVisibilityMode;
        if (i != -1) {
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        setLayoutConfigurationIconOnly();
                    }
                } else if (z) {
                    setLayoutConfigurationIconAndLabel(this.largeLabel, this.smallLabel, this.scaleUpFactor, this.shiftAmount);
                    z = true;
                } else {
                    setLayoutConfigurationIconAndLabel(this.smallLabel, this.largeLabel, this.scaleDownFactor, 0.0f);
                    z = false;
                }
            } else if (z) {
                setLayoutConfigurationIconAndLabel(this.largeLabel, this.smallLabel, this.scaleUpFactor, 0.0f);
                z = true;
            } else {
                setLayoutConfigurationIconOnly();
                z = false;
            }
        } else if (this.isShifting) {
            if (z) {
                setLayoutConfigurationIconAndLabel(this.largeLabel, this.smallLabel, this.scaleUpFactor, 0.0f);
                z = true;
            } else {
                setLayoutConfigurationIconOnly();
                z = false;
            }
        } else if (z) {
            setLayoutConfigurationIconAndLabel(this.largeLabel, this.smallLabel, this.scaleUpFactor, this.shiftAmount);
            z = true;
        } else {
            setLayoutConfigurationIconAndLabel(this.smallLabel, this.largeLabel, this.scaleDownFactor, 0.0f);
            z = false;
        }
        refreshDrawableState();
        setSelected(z);
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.smallLabel.setEnabled(z);
        this.largeLabel.setEnabled(z);
        this.icon.setEnabled(z);
        if (z) {
            ViewCompat.setPointerIcon$ar$class_merging$ar$class_merging(this, new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(PointerIconCompat$Api24Impl.getSystemIcon(getContext(), TrainingProto$TrainingPageId.PAGE_ID_UPDATE_WELCOME_WITHOUT_TYPO_CORRECTION$ar$edu)));
        } else {
            ViewCompat.setPointerIcon$ar$class_merging$ar$class_merging(this, null);
        }
    }

    public final void setIconSize(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.icon.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        this.icon.setLayoutParams(layoutParams);
    }

    public final void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.iconTint = colorStateList;
        if (this.itemData != null && (drawable = this.wrappedIconDrawable) != null) {
            DrawableCompat$Api21Impl.setTintList(drawable, colorStateList);
            this.wrappedIconDrawable.invalidateSelf();
        }
    }

    public final void setItemBackground(int i) {
        Drawable drawable;
        if (i == 0) {
            drawable = null;
        } else {
            drawable = ContextCompat$Api21Impl.getDrawable(getContext(), i);
        }
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        this.itemBackground = drawable;
        refreshItemBackground();
    }

    public final void setItemIconGravity(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        if (this.itemIconGravity != i) {
            this.itemIconGravity = i;
            int i8 = this.activeIndicatorLabelPadding;
            this.badgeFixedEdge = 0;
            if (i == 1) {
                i4 = getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.m3_expressive_navigation_item_leading_trailing_space);
                i5 = this.activeIndicatorLabelPadding;
                this.badgeFixedEdge = 1;
                int i9 = this.activeIndicatorMarginHorizontal;
                i2 = 17;
                if (this.labelGroup.getParent() != this.innerContentContainer) {
                    this.contentContainer.removeView(this.labelGroup);
                    this.innerContentContainer.addView(this.labelGroup);
                }
                i6 = i9;
                i3 = 0;
            } else {
                i2 = 49;
                if (this.labelGroup.getParent() != this.contentContainer) {
                    this.innerContentContainer.removeView(this.labelGroup);
                    this.contentContainer.addView(this.labelGroup);
                }
                i3 = i8;
                i4 = 0;
                i5 = 0;
                i6 = 0;
            }
            ((FrameLayout.LayoutParams) this.contentContainer.getLayoutParams()).gravity = i2;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.innerContentContainer.getLayoutParams();
            layoutParams.leftMargin = i4;
            layoutParams.rightMargin = i4;
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.labelGroup.getLayoutParams();
            if (getLayoutDirection() == 1) {
                i7 = i5;
            } else {
                i7 = 0;
            }
            layoutParams2.rightMargin = i7;
            if (getLayoutDirection() == 1) {
                i5 = 0;
            }
            layoutParams2.leftMargin = i5;
            layoutParams2.topMargin = i3;
            setPadding(i6, 0, i6, 0);
            updateActiveIndicatorLayoutParams(getWidth());
            refreshItemBackground();
        }
    }

    public final void setItemPaddingBottom(int i) {
        if (this.itemPaddingBottom != i) {
            this.itemPaddingBottom = i;
            refreshChecked();
        }
    }

    public final void setItemPaddingTop(int i) {
        if (this.itemPaddingTop != i) {
            this.itemPaddingTop = i;
            refreshChecked();
        }
    }

    public final void setItemRippleColor(ColorStateList colorStateList) {
        this.itemRippleColor = colorStateList;
        refreshItemBackground();
    }

    public final void setLabelVisibilityMode(int i) {
        if (this.labelVisibilityMode != i) {
            this.labelVisibilityMode = i;
            this.activeIndicatorTransform$ar$class_merging$ar$class_merging$ar$class_merging = ACTIVE_INDICATOR_LABELED_TRANSFORM$ar$class_merging$ar$class_merging$ar$class_merging;
            updateActiveIndicatorLayoutParams(getWidth());
            refreshChecked();
        }
    }

    public final void setMeasureBottomPaddingFromLabelBaseline(boolean z) {
        this.labelGroup.measurePaddingFromBaseline = z;
        this.smallLabel.setIncludeFontPadding(z);
        this.largeLabel.setIncludeFontPadding(z);
        requestLayout();
    }

    public final void setShifting(boolean z) {
        if (this.isShifting != z) {
            this.isShifting = z;
            refreshChecked();
        }
    }

    public final void setTextAppearanceActive(int i) {
        this.activeTextAppearance = i;
        setTextAppearanceWithoutFontScaling(this.largeLabel, i);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
        this.largeLabel.setMinimumHeight(DrawableUtils$OutlineCompatR.getUnscaledLineHeight$ar$ds(this.largeLabel.getContext(), i));
    }

    public final void setTextAppearanceActiveBoldEnabled(boolean z) {
        setTextAppearanceActive(this.activeTextAppearance);
        TextView textView = this.largeLabel;
        textView.setTypeface(textView.getTypeface(), z ? 1 : 0);
    }

    public final void setTextAppearanceInactive(int i) {
        setTextAppearanceWithoutFontScaling(this.smallLabel, i);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
        this.smallLabel.setMinimumHeight(DrawableUtils$OutlineCompatR.getUnscaledLineHeight$ar$ds(this.smallLabel.getContext(), i));
    }

    public final void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.smallLabel.setTextColor(colorStateList);
            this.largeLabel.setTextColor(colorStateList);
        }
    }

    public final void tryRemoveBadgeFromAnchor(View view) {
        if (!hasBadge()) {
            return;
        }
        if (view != null) {
            setClipChildren(true);
            setClipToPadding(true);
            BadgeDrawable badgeDrawable = this.badgeDrawable;
            if (badgeDrawable != null) {
                if (badgeDrawable.getCustomBadgeParent() != null) {
                    badgeDrawable.getCustomBadgeParent().setForeground(null);
                } else {
                    view.getOverlay().remove(badgeDrawable);
                }
            }
        }
        this.badgeDrawable = null;
    }

    public final void updateActiveIndicatorLayoutParams(int i) {
        if (i <= 0) {
            return;
        }
        int i2 = this.activeIndicatorDesiredWidth;
        int i3 = this.activeIndicatorMarginHorizontal;
        int min = Math.min(i2, i - (i3 + i3));
        int i4 = this.activeIndicatorDesiredHeight;
        if (this.itemIconGravity == 1) {
            min = Math.max(this.contentContainer.getMeasuredWidth(), min);
            i4 = getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.m3_expressive_horizontal_item_active_indicator_height);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.activeIndicatorView.getLayoutParams();
        layoutParams.height = i4;
        layoutParams.width = min;
        this.activeIndicatorView.setLayoutParams(layoutParams);
    }
}
