package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    private WeakReference anchorViewRef;
    private final Rect badgeBounds;
    private float badgeCenterX;
    private float badgeCenterY;
    public final WeakReference contextRef;
    private float cornerRadius;
    private WeakReference customBadgeParentRef;
    private float halfBadgeHeight;
    private float halfBadgeWidth;
    public int maxBadgeNumber;
    private final MaterialShapeDrawable shapeDrawable;
    public final BadgeState state;
    private final TextDrawableHelper textDrawableHelper;

    public BadgeDrawable(Context context, BadgeState.State state) {
        int badgeShapeAppearanceResId;
        int badgeShapeAppearanceOverlayResId;
        FrameLayout frameLayout;
        TextAppearance textAppearance;
        WeakReference weakReference = new WeakReference(context);
        this.contextRef = weakReference;
        ThemeEnforcement.checkMaterialTheme(context);
        this.badgeBounds = new Rect();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper;
        textDrawableHelper.textPaint.setTextAlign(Paint.Align.CENTER);
        BadgeState badgeState = new BadgeState(context, state);
        this.state = badgeState;
        if (hasBadgeContent()) {
            badgeShapeAppearanceResId = badgeState.getBadgeWithTextShapeAppearanceResId();
        } else {
            badgeShapeAppearanceResId = badgeState.getBadgeShapeAppearanceResId();
        }
        if (hasBadgeContent()) {
            badgeShapeAppearanceOverlayResId = badgeState.getBadgeWithTextShapeAppearanceOverlayResId();
        } else {
            badgeShapeAppearanceOverlayResId = badgeState.getBadgeShapeAppearanceOverlayResId();
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(new ShapeAppearanceModel(ShapeAppearanceModel.builder(context, badgeShapeAppearanceResId, badgeShapeAppearanceOverlayResId)));
        this.shapeDrawable = materialShapeDrawable;
        onBadgeShapeAppearanceUpdated();
        Context context2 = (Context) weakReference.get();
        if (context2 != null && textDrawableHelper.textAppearance != (textAppearance = new TextAppearance(context2, badgeState.currentState.badgeTextAppearanceResId.intValue()))) {
            textDrawableHelper.setTextAppearance(textAppearance, context2);
            onBadgeTextColorUpdated();
            updateCenterAndBounds();
            invalidateSelf();
        }
        if (getMaxCharacterCount() != -2) {
            this.maxBadgeNumber = ((int) Math.pow(10.0d, getMaxCharacterCount() - 1.0d)) - 1;
        } else {
            this.maxBadgeNumber = badgeState.currentState.maxNumber;
        }
        textDrawableHelper.setTextSizeDirty$ar$ds();
        updateCenterAndBounds();
        invalidateSelf();
        textDrawableHelper.setTextSizeDirty$ar$ds();
        onBadgeShapeAppearanceUpdated();
        updateCenterAndBounds();
        invalidateSelf();
        onAlphaUpdated();
        ColorStateList valueOf = ColorStateList.valueOf(badgeState.currentState.backgroundColor.intValue());
        if (materialShapeDrawable.getFillColor() != valueOf) {
            materialShapeDrawable.setFillColor(valueOf);
            invalidateSelf();
        }
        onBadgeTextColorUpdated();
        WeakReference weakReference2 = this.anchorViewRef;
        if (weakReference2 != null && weakReference2.get() != null) {
            View view = (View) this.anchorViewRef.get();
            WeakReference weakReference3 = this.customBadgeParentRef;
            if (weakReference3 != null) {
                frameLayout = (FrameLayout) weakReference3.get();
            } else {
                frameLayout = null;
            }
            updateBadgeCoordinates(view, frameLayout);
        }
        updateCenterAndBounds();
        setVisible(badgeState.currentState.isVisible.booleanValue(), false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.view.ViewParent] */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.view.ViewParent] */
    private final void autoAdjustWithinViewBounds(View view, View view2) {
        float f;
        float f2;
        View view3;
        FrameLayout customBadgeParent = getCustomBadgeParent();
        if (customBadgeParent == null) {
            float y = view.getY();
            f2 = view.getX();
            view3 = view.getParent();
            f = y;
        } else {
            f = 0.0f;
            f2 = 0.0f;
            view3 = customBadgeParent;
        }
        while (view3 instanceof View) {
            if (view3 != view2) {
                ViewParent parent = view3.getParent();
                if ((parent instanceof ViewGroup) && !((ViewGroup) parent).getClipChildren()) {
                    View view4 = view3;
                    f += view4.getY();
                    f2 += view4.getX();
                    view3 = view3.getParent();
                }
            }
            float f3 = (this.badgeCenterY - this.halfBadgeHeight) + f;
            float f4 = (this.badgeCenterX - this.halfBadgeWidth) + f2;
            View view5 = view3;
            float height = ((this.badgeCenterY + this.halfBadgeHeight) - view5.getHeight()) + f;
            float width = ((this.badgeCenterX + this.halfBadgeWidth) - view5.getWidth()) + f2;
            if (f3 < 0.0f) {
                this.badgeCenterY += Math.abs(f3);
            }
            if (f4 < 0.0f) {
                this.badgeCenterX += Math.abs(f4);
            }
            if (height > 0.0f) {
                this.badgeCenterY -= Math.abs(height);
            }
            if (width > 0.0f) {
                this.badgeCenterX -= Math.abs(width);
                return;
            }
            return;
        }
    }

    private final String getBadgeContent() {
        if (hasText()) {
            String text = getText();
            int maxCharacterCount = getMaxCharacterCount();
            if (maxCharacterCount != -2 && text != null && text.length() > maxCharacterCount) {
                Context context = (Context) this.contextRef.get();
                if (context == null) {
                    return "";
                }
                return String.format(context.getString(R.string.m3_exceed_max_badge_text_suffix), text.substring(0, maxCharacterCount - 1), "…");
            }
            return text;
        }
        if (hasNumber()) {
            if (this.maxBadgeNumber != -2 && getNumber() > this.maxBadgeNumber) {
                Context context2 = (Context) this.contextRef.get();
                if (context2 == null) {
                    return "";
                }
                return String.format(this.state.getNumberLocale(), context2.getString(R.string.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.maxBadgeNumber), "+");
            }
            return NumberFormat.getInstance(this.state.getNumberLocale()).format(getNumber());
        }
        return null;
    }

    private final boolean hasBadgeContent() {
        if (!hasText() && !hasNumber()) {
            return false;
        }
        return true;
    }

    private final void onAlphaUpdated() {
        this.textDrawableHelper.textPaint.setAlpha(getAlpha());
        invalidateSelf();
    }

    private final void onBadgeShapeAppearanceUpdated() {
        int badgeShapeAppearanceResId;
        int badgeShapeAppearanceOverlayResId;
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        if (hasBadgeContent()) {
            badgeShapeAppearanceResId = this.state.getBadgeWithTextShapeAppearanceResId();
        } else {
            badgeShapeAppearanceResId = this.state.getBadgeShapeAppearanceResId();
        }
        if (hasBadgeContent()) {
            badgeShapeAppearanceOverlayResId = this.state.getBadgeWithTextShapeAppearanceOverlayResId();
        } else {
            badgeShapeAppearanceOverlayResId = this.state.getBadgeShapeAppearanceOverlayResId();
        }
        materialShapeDrawable.setShapeAppearanceModel(new ShapeAppearanceModel(ShapeAppearanceModel.builder(context, badgeShapeAppearanceResId, badgeShapeAppearanceOverlayResId)));
        invalidateSelf();
    }

    private final void onBadgeTextColorUpdated() {
        this.textDrawableHelper.textPaint.setColor(this.state.currentState.badgeTextColor.intValue());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        String badgeContent;
        int round;
        if (!getBounds().isEmpty() && getAlpha() != 0 && isVisible()) {
            this.shapeDrawable.draw(canvas);
            if (hasBadgeContent() && (badgeContent = getBadgeContent()) != null) {
                Rect rect = new Rect();
                this.textDrawableHelper.textPaint.getTextBounds(badgeContent, 0, badgeContent.length(), rect);
                float exactCenterY = this.badgeCenterY - rect.exactCenterY();
                float f = this.badgeCenterX;
                if (rect.bottom <= 0) {
                    round = (int) exactCenterY;
                } else {
                    round = Math.round(exactCenterY);
                }
                canvas.drawText(badgeContent, f, round, this.textDrawableHelper.textPaint);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.state.currentState.alpha;
    }

    public final FrameLayout getCustomBadgeParent() {
        WeakReference weakReference = this.customBadgeParentRef;
        if (weakReference != null) {
            return (FrameLayout) weakReference.get();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public final int getMaxCharacterCount() {
        return this.state.currentState.maxCharacterCount;
    }

    public final int getNumber() {
        if (this.state.hasNumber()) {
            return this.state.currentState.number;
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final String getText() {
        return this.state.currentState.text;
    }

    public final boolean hasNumber() {
        if (!this.state.hasText() && this.state.hasNumber()) {
            return true;
        }
        return false;
    }

    public final boolean hasText() {
        return this.state.hasText();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public final void onTextSizeChange() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        BadgeState badgeState = this.state;
        badgeState.overridingState.alpha = i;
        badgeState.currentState.alpha = i;
        onAlphaUpdated();
    }

    public final void updateBadgeCoordinates(View view, FrameLayout frameLayout) {
        this.anchorViewRef = new WeakReference(view);
        this.customBadgeParentRef = new WeakReference(frameLayout);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        updateCenterAndBounds();
        invalidateSelf();
    }

    public final void updateCenterAndBounds() {
        View view;
        ViewGroup viewGroup;
        float f;
        float f2;
        float f3;
        int horizontalOffsetWithoutText;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        int i;
        float f13;
        Context context = (Context) this.contextRef.get();
        WeakReference weakReference = this.anchorViewRef;
        if (weakReference != null) {
            view = (View) weakReference.get();
        } else {
            view = null;
        }
        if (context != null && view != null) {
            Rect rect = new Rect();
            rect.set(this.badgeBounds);
            Rect rect2 = new Rect();
            view.getDrawingRect(rect2);
            WeakReference weakReference2 = this.customBadgeParentRef;
            if (weakReference2 != null) {
                viewGroup = (ViewGroup) weakReference2.get();
            } else {
                viewGroup = null;
            }
            if (viewGroup != null) {
                viewGroup.offsetDescendantRectToMyCoords(view, rect2);
            }
            if (hasBadgeContent()) {
                f = this.state.badgeWithTextRadius;
            } else {
                f = this.state.badgeRadius;
            }
            this.cornerRadius = f;
            if (f != -1.0f) {
                this.halfBadgeWidth = f;
                this.halfBadgeHeight = f;
            } else {
                if (hasBadgeContent()) {
                    f2 = this.state.badgeWithTextWidth;
                } else {
                    f2 = this.state.badgeWidth;
                }
                this.halfBadgeWidth = Math.round(f2 / 2.0f);
                if (hasBadgeContent()) {
                    f3 = this.state.badgeWithTextHeight;
                } else {
                    f3 = this.state.badgeHeight;
                }
                this.halfBadgeHeight = Math.round(f3 / 2.0f);
            }
            if (hasBadgeContent()) {
                String badgeContent = getBadgeContent();
                this.halfBadgeWidth = Math.max(this.halfBadgeWidth, (this.textDrawableHelper.getTextWidth(badgeContent) / 2.0f) + this.state.currentState.badgeHorizontalPadding.intValue());
                float f14 = this.halfBadgeHeight;
                TextDrawableHelper textDrawableHelper = this.textDrawableHelper;
                if (!textDrawableHelper.textSizeDirty) {
                    f13 = textDrawableHelper.textHeight;
                } else {
                    textDrawableHelper.refreshTextDimens(badgeContent);
                    f13 = textDrawableHelper.textHeight;
                }
                float max = Math.max(f14, (f13 / 2.0f) + this.state.currentState.badgeVerticalPadding.intValue());
                this.halfBadgeHeight = max;
                this.halfBadgeWidth = Math.max(this.halfBadgeWidth, max);
            }
            int intValue = this.state.currentState.verticalOffsetWithoutText.intValue();
            if (hasBadgeContent()) {
                intValue = this.state.currentState.verticalOffsetWithText.intValue();
                Context context2 = (Context) this.contextRef.get();
                if (context2 != null) {
                    float f15 = context2.getResources().getConfiguration().fontScale - 1.0f;
                    intValue = AnimationUtils.lerp(intValue, intValue - this.state.currentState.largeFontVerticalOffsetAdjustment.intValue(), AnimationUtils.lerp(0.0f, 1.0f, 0.3f, 1.0f, f15));
                }
            }
            if (this.state.offsetAlignmentMode == 0) {
                intValue -= Math.round(this.halfBadgeHeight);
            }
            int intValue2 = intValue + this.state.currentState.additionalVerticalOffset.intValue();
            int badgeGravity = this.state.getBadgeGravity();
            if (badgeGravity != 8388691 && badgeGravity != 8388693) {
                this.badgeCenterY = rect2.top + intValue2;
            } else {
                this.badgeCenterY = rect2.bottom - intValue2;
            }
            if (hasBadgeContent()) {
                horizontalOffsetWithoutText = this.state.currentState.horizontalOffsetWithText.intValue();
            } else {
                horizontalOffsetWithoutText = this.state.getHorizontalOffsetWithoutText();
            }
            if (this.state.offsetAlignmentMode == 1) {
                if (hasBadgeContent()) {
                    i = this.state.horizontalInsetWithText;
                } else {
                    i = this.state.horizontalInset;
                }
                horizontalOffsetWithoutText += i;
            }
            float intValue3 = horizontalOffsetWithoutText + this.state.currentState.additionalHorizontalOffset.intValue();
            int badgeGravity2 = this.state.getBadgeGravity();
            if (badgeGravity2 != 8388659 && badgeGravity2 != 8388691) {
                if (this.state.badgeFixedEdge == 0) {
                    if (view.getLayoutDirection() == 0) {
                        f4 = rect2.right;
                        f5 = this.halfBadgeWidth;
                        f12 = (f4 + f5) - intValue3;
                    } else {
                        f6 = rect2.left;
                        f7 = this.halfBadgeWidth;
                        f12 = (f6 - f7) + intValue3;
                    }
                } else if (view.getLayoutDirection() == 0) {
                    f8 = rect2.right - this.halfBadgeWidth;
                    f9 = this.halfBadgeHeight;
                    f12 = f8 + ((f9 + f9) - intValue3);
                } else {
                    f10 = rect2.left + this.halfBadgeWidth;
                    f11 = this.halfBadgeHeight;
                    f12 = f10 - ((f11 + f11) - intValue3);
                }
            } else if (this.state.badgeFixedEdge == 0) {
                if (view.getLayoutDirection() == 0) {
                    f10 = rect2.left + this.halfBadgeWidth;
                    f11 = this.halfBadgeHeight;
                    f12 = f10 - ((f11 + f11) - intValue3);
                } else {
                    f8 = rect2.right - this.halfBadgeWidth;
                    f9 = this.halfBadgeHeight;
                    f12 = f8 + ((f9 + f9) - intValue3);
                }
            } else if (view.getLayoutDirection() == 0) {
                f6 = rect2.left;
                f7 = this.halfBadgeWidth;
                f12 = (f6 - f7) + intValue3;
            } else {
                f4 = rect2.right;
                f5 = this.halfBadgeWidth;
                f12 = (f4 + f5) - intValue3;
            }
            this.badgeCenterX = f12;
            if (this.state.currentState.autoAdjustToWithinGrandparentBounds.booleanValue()) {
                ViewParent customBadgeParent = getCustomBadgeParent();
                if (customBadgeParent == null) {
                    customBadgeParent = view.getParent();
                }
                if ((customBadgeParent instanceof View) && (customBadgeParent.getParent() instanceof View)) {
                    autoAdjustWithinViewBounds(view, (View) customBadgeParent.getParent());
                }
            } else {
                autoAdjustWithinViewBounds(view, null);
            }
            Rect rect3 = this.badgeBounds;
            float f16 = this.badgeCenterX;
            float f17 = this.badgeCenterY;
            float f18 = this.halfBadgeWidth;
            float f19 = this.halfBadgeHeight;
            rect3.set((int) (f16 - f18), (int) (f17 - f19), (int) (f16 + f18), (int) (f17 + f19));
            float f20 = this.cornerRadius;
            if (f20 != -1.0f) {
                this.shapeDrawable.setCornerSize(f20);
            }
            if (!rect.equals(this.badgeBounds)) {
                this.shapeDrawable.setBounds(this.badgeBounds);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
