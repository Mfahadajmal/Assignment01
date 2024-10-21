package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.app.NotificationCompatBuilder$Api24Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api23Impl;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChipDrawable extends MaterialShapeDrawable implements Drawable.Callback, TextDrawableHelper.TextDrawableDelegate {
    private static final int[] DEFAULT_STATE = {R.attr.state_enabled};
    private static final ShapeDrawable closeIconRippleMask = new ShapeDrawable(new OvalShape());
    private int alpha;
    public boolean checkable;
    private Drawable checkedIcon;
    private ColorStateList checkedIconTint;
    private boolean checkedIconVisible;
    private ColorStateList chipBackgroundColor;
    private float chipCornerRadius;
    public float chipEndPadding;
    private Drawable chipIcon;
    private float chipIconSize;
    private ColorStateList chipIconTint;
    private boolean chipIconVisible;
    public float chipMinHeight;
    private final Paint chipPaint;
    public float chipStartPadding;
    private ColorStateList chipStrokeColor;
    private float chipStrokeWidth;
    private ColorStateList chipSurfaceColor;
    public Drawable closeIcon;
    private float closeIconEndPadding;
    private Drawable closeIconRipple;
    private float closeIconSize;
    private float closeIconStartPadding;
    private int[] closeIconStateSet;
    private ColorStateList closeIconTint;
    public boolean closeIconVisible;
    private ColorFilter colorFilter;
    private ColorStateList compatRippleColor;
    private final Context context;
    private boolean currentChecked;
    private int currentChipBackgroundColor;
    private int currentChipStrokeColor;
    private int currentChipSurfaceColor;
    private int currentCompatRippleColor;
    private int currentCompositeSurfaceBackgroundColor;
    private int currentTextColor;
    private int currentTint;
    private WeakReference delegate;
    private final Paint.FontMetrics fontMetrics;
    private boolean hasChipIconTint;
    private float iconEndPadding;
    private float iconStartPadding;
    private boolean isShapeThemingEnabled;
    public int maxWidth;
    private final PointF pointF;
    private final RectF rectF;
    public ColorStateList rippleColor;
    private final Path shapePath;
    public boolean shouldDrawText;
    public CharSequence text;
    public final TextDrawableHelper textDrawableHelper;
    public float textEndPadding;
    public float textStartPadding;
    private ColorStateList tint;
    private PorterDuffColorFilter tintFilter;
    private PorterDuff.Mode tintMode;
    public TextUtils.TruncateAt truncateAt;
    public boolean useCompatRipple;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    private ChipDrawable(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.chipCornerRadius = -1.0f;
        this.chipPaint = new Paint(1);
        this.fontMetrics = new Paint.FontMetrics();
        this.rectF = new RectF();
        this.pointF = new PointF();
        this.shapePath = new Path();
        this.alpha = PrivateKeyType.INVALID;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.delegate = new WeakReference(null);
        initializeElevationOverlay(context);
        this.context = context;
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper;
        this.text = "";
        textDrawableHelper.textPaint.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = DEFAULT_STATE;
        setState(iArr);
        setCloseIconState(iArr);
        this.shouldDrawText = true;
        int[] iArr2 = RippleUtils.PRESSED_STATE_SET;
        closeIconRippleMask.setTint(-1);
    }

    private final void applyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            DrawableCompat$Api23Impl.setLayoutDirection(drawable, DrawableCompat$Api23Impl.getLayoutDirection(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.closeIcon) {
                if (drawable.isStateful()) {
                    drawable.setState(this.closeIconStateSet);
                }
                DrawableCompat$Api21Impl.setTintList(drawable, this.closeIconTint);
                return;
            }
            Drawable drawable2 = this.chipIcon;
            if (drawable == drawable2 && this.hasChipIconTint) {
                DrawableCompat$Api21Impl.setTintList(drawable2, this.chipIconTint);
            }
            if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    private final void calculateChipIconBounds(Rect rect, RectF rectF) {
        Drawable drawable;
        rectF.setEmpty();
        if (!showsChipIcon() && !showsCheckedIcon()) {
            return;
        }
        float f = this.chipStartPadding + this.iconStartPadding;
        float currentChipIconWidth = getCurrentChipIconWidth();
        if (DrawableCompat$Api23Impl.getLayoutDirection(this) == 0) {
            rectF.left = rect.left + f;
            rectF.right = rectF.left + currentChipIconWidth;
        } else {
            rectF.right = rect.right - f;
            rectF.left = rectF.right - currentChipIconWidth;
        }
        if (this.currentChecked) {
            drawable = this.checkedIcon;
        } else {
            drawable = this.chipIcon;
        }
        float f2 = this.chipIconSize;
        if (f2 <= 0.0f && drawable != null) {
            f2 = (float) Math.ceil(DrawableUtils$OutlineCompatL.dpToPx(this.context, 24));
            if (drawable.getIntrinsicHeight() <= f2) {
                f2 = drawable.getIntrinsicHeight();
            }
        }
        rectF.top = rect.exactCenterY() - (f2 / 2.0f);
        rectF.bottom = rectF.top + f2;
    }

    private final boolean canShowCheckedIcon() {
        if (this.checkedIconVisible && this.checkedIcon != null && this.checkable) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0243  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.material.chip.ChipDrawable createFromAttributes(android.content.Context r8, android.util.AttributeSet r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 969
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipDrawable.createFromAttributes(android.content.Context, android.util.AttributeSet, int, int):com.google.android.material.chip.ChipDrawable");
    }

    private final float getCurrentChipIconWidth() {
        Drawable drawable;
        if (this.currentChecked) {
            drawable = this.checkedIcon;
        } else {
            drawable = this.chipIcon;
        }
        float f = this.chipIconSize;
        if (f <= 0.0f && drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return f;
    }

    private final ColorFilter getTintColorFilter() {
        ColorFilter colorFilter = this.colorFilter;
        if (colorFilter != null) {
            return colorFilter;
        }
        return this.tintFilter;
    }

    private final boolean showsCheckedIcon() {
        if (this.checkedIconVisible && this.checkedIcon != null && this.currentChecked) {
            return true;
        }
        return false;
    }

    private final boolean showsChipIcon() {
        if (this.chipIconVisible && this.chipIcon != null) {
            return true;
        }
        return false;
    }

    private final boolean showsCloseIcon() {
        if (this.closeIconVisible && this.closeIcon != null) {
            return true;
        }
        return false;
    }

    private static final void unapplyChildDrawable$ar$ds(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final float calculateChipIconWidth() {
        if (!showsChipIcon() && !showsCheckedIcon()) {
            return 0.0f;
        }
        return this.iconStartPadding + getCurrentChipIconWidth() + this.iconEndPadding;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final float calculateCloseIconWidth() {
        if (showsCloseIcon()) {
            return this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding;
        }
        return 0.0f;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i;
        int i2;
        boolean z;
        int i3;
        Rect bounds = getBounds();
        if (!bounds.isEmpty() && (i = this.alpha) != 0) {
            if (i < 255) {
                i2 = canvas.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, this.alpha);
            } else {
                i2 = 0;
            }
            if (!this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipSurfaceColor);
                this.chipPaint.setStyle(Paint.Style.FILL);
                this.rectF.set(bounds);
                canvas.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            }
            if (!this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipBackgroundColor);
                this.chipPaint.setStyle(Paint.Style.FILL);
                this.chipPaint.setColorFilter(getTintColorFilter());
                this.rectF.set(bounds);
                canvas.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            }
            if (this.isShapeThemingEnabled) {
                super.draw(canvas);
            }
            if (this.chipStrokeWidth > 0.0f && !this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipStrokeColor);
                this.chipPaint.setStyle(Paint.Style.STROKE);
                if (!this.isShapeThemingEnabled) {
                    this.chipPaint.setColorFilter(getTintColorFilter());
                }
                this.rectF.set(bounds.left + (this.chipStrokeWidth / 2.0f), bounds.top + (this.chipStrokeWidth / 2.0f), bounds.right - (this.chipStrokeWidth / 2.0f), bounds.bottom - (this.chipStrokeWidth / 2.0f));
                float f = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
                canvas.drawRoundRect(this.rectF, f, f, this.chipPaint);
            }
            this.chipPaint.setColor(this.currentCompatRippleColor);
            this.chipPaint.setStyle(Paint.Style.FILL);
            this.rectF.set(bounds);
            if (!this.isShapeThemingEnabled) {
                canvas.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            } else {
                calculatePathForSize(new RectF(bounds), this.shapePath);
                Paint paint = this.chipPaint;
                Path path = this.shapePath;
                RectF boundsAsRectF = getBoundsAsRectF();
                MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
                super.drawShape(canvas, paint, path, materialShapeDrawableState.shapeAppearanceModel, materialShapeDrawableState.interpolationStartShapeAppearanceModel, boundsAsRectF);
            }
            if (showsChipIcon()) {
                calculateChipIconBounds(bounds, this.rectF);
                RectF rectF = this.rectF;
                float f2 = rectF.left;
                float f3 = rectF.top;
                canvas.translate(f2, f3);
                this.chipIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
                this.chipIcon.draw(canvas);
                canvas.translate(-f2, -f3);
            }
            if (showsCheckedIcon()) {
                calculateChipIconBounds(bounds, this.rectF);
                RectF rectF2 = this.rectF;
                float f4 = rectF2.left;
                float f5 = rectF2.top;
                canvas.translate(f4, f5);
                this.checkedIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
                this.checkedIcon.draw(canvas);
                canvas.translate(-f4, -f5);
            }
            if (this.shouldDrawText && this.text != null) {
                PointF pointF = this.pointF;
                pointF.set(0.0f, 0.0f);
                Paint.Align align = Paint.Align.LEFT;
                if (this.text != null) {
                    float calculateChipIconWidth = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
                    if (DrawableCompat$Api23Impl.getLayoutDirection(this) == 0) {
                        pointF.x = bounds.left + calculateChipIconWidth;
                        align = Paint.Align.LEFT;
                    } else {
                        pointF.x = bounds.right - calculateChipIconWidth;
                        align = Paint.Align.RIGHT;
                    }
                    float centerY = bounds.centerY();
                    this.textDrawableHelper.textPaint.getFontMetrics(this.fontMetrics);
                    Paint.FontMetrics fontMetrics = this.fontMetrics;
                    pointF.y = centerY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
                }
                RectF rectF3 = this.rectF;
                rectF3.setEmpty();
                if (this.text != null) {
                    float calculateChipIconWidth2 = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
                    float calculateCloseIconWidth = this.chipEndPadding + calculateCloseIconWidth() + this.textEndPadding;
                    if (DrawableCompat$Api23Impl.getLayoutDirection(this) == 0) {
                        rectF3.left = bounds.left + calculateChipIconWidth2;
                        rectF3.right = bounds.right - calculateCloseIconWidth;
                    } else {
                        rectF3.left = bounds.left + calculateCloseIconWidth;
                        rectF3.right = bounds.right - calculateChipIconWidth2;
                    }
                    rectF3.top = bounds.top;
                    rectF3.bottom = bounds.bottom;
                }
                TextDrawableHelper textDrawableHelper = this.textDrawableHelper;
                if (textDrawableHelper.textAppearance != null) {
                    textDrawableHelper.textPaint.drawableState = getState();
                    this.textDrawableHelper.updateTextPaintDrawState(this.context);
                }
                this.textDrawableHelper.textPaint.setTextAlign(align);
                if (Math.round(this.textDrawableHelper.getTextWidth(this.text.toString())) > Math.round(this.rectF.width())) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    int save = canvas.save();
                    canvas.clipRect(this.rectF);
                    i3 = save;
                } else {
                    i3 = 0;
                }
                CharSequence charSequence = this.text;
                if (z && this.truncateAt != null) {
                    charSequence = TextUtils.ellipsize(charSequence, this.textDrawableHelper.textPaint, this.rectF.width(), this.truncateAt);
                }
                CharSequence charSequence2 = charSequence;
                PointF pointF2 = this.pointF;
                canvas.drawText(charSequence2, 0, charSequence2.length(), pointF2.x, pointF2.y, this.textDrawableHelper.textPaint);
                if (z) {
                    canvas.restoreToCount(i3);
                }
            }
            if (showsCloseIcon()) {
                RectF rectF4 = this.rectF;
                rectF4.setEmpty();
                if (showsCloseIcon()) {
                    float f6 = this.chipEndPadding + this.closeIconEndPadding;
                    if (DrawableCompat$Api23Impl.getLayoutDirection(this) == 0) {
                        rectF4.right = bounds.right - f6;
                        rectF4.left = rectF4.right - this.closeIconSize;
                    } else {
                        rectF4.left = bounds.left + f6;
                        rectF4.right = rectF4.left + this.closeIconSize;
                    }
                    rectF4.top = bounds.exactCenterY() - (this.closeIconSize / 2.0f);
                    rectF4.bottom = rectF4.top + this.closeIconSize;
                }
                RectF rectF5 = this.rectF;
                float f7 = rectF5.left;
                float f8 = rectF5.top;
                canvas.translate(f7, f8);
                this.closeIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
                int[] iArr = RippleUtils.PRESSED_STATE_SET;
                this.closeIconRipple.setBounds(this.closeIcon.getBounds());
                this.closeIconRipple.jumpToCurrentState();
                this.closeIconRipple.draw(canvas);
                canvas.translate(-f7, -f8);
            }
            if (this.alpha < 255) {
                canvas.restoreToCount(i2);
            }
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.alpha;
    }

    public final float getChipCornerRadius() {
        if (this.isShapeThemingEnabled) {
            return getTopLeftCornerResolvedSize();
        }
        return this.chipCornerRadius;
    }

    public final Drawable getCloseIcon() {
        Drawable drawable = this.closeIcon;
        if (drawable != null) {
            return NotificationCompatBuilder$Api24Impl.unwrap(drawable);
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.min(Math.round(this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding + this.textDrawableHelper.getTextWidth(this.text.toString()) + this.textEndPadding + calculateCloseIconWidth() + this.chipEndPadding), this.maxWidth);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        if (this.isShapeThemingEnabled) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.chipCornerRadius);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.chipCornerRadius);
        }
        outline.setAlpha(this.alpha / 255.0f);
    }

    public final TextAppearance getTextAppearance() {
        return this.textDrawableHelper.textAppearance;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        if (isStateful(this.chipSurfaceColor) || isStateful(this.chipBackgroundColor) || isStateful(this.chipStrokeColor)) {
            return true;
        }
        TextAppearance textAppearance = this.textDrawableHelper.textAppearance;
        return !(textAppearance == null || (colorStateList = textAppearance.textColor) == null || !colorStateList.isStateful()) || canShowCheckedIcon() || isStateful(this.chipIcon) || isStateful(this.checkedIcon) || isStateful(this.tint);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (showsChipIcon()) {
            onLayoutDirectionChanged |= DrawableCompat$Api23Impl.setLayoutDirection(this.chipIcon, i);
        }
        if (showsCheckedIcon()) {
            onLayoutDirectionChanged |= DrawableCompat$Api23Impl.setLayoutDirection(this.checkedIcon, i);
        }
        if (showsCloseIcon()) {
            onLayoutDirectionChanged |= DrawableCompat$Api23Impl.setLayoutDirection(this.closeIcon, i);
        }
        if (onLayoutDirectionChanged) {
            invalidateSelf();
            return true;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected final boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (showsChipIcon()) {
            onLevelChange |= this.chipIcon.setLevel(i);
        }
        if (showsCheckedIcon()) {
            onLevelChange |= this.checkedIcon.setLevel(i);
        }
        if (showsCloseIcon()) {
            onLevelChange |= this.closeIcon.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    protected final void onSizeChange() {
        Delegate delegate = (Delegate) this.delegate.get();
        if (delegate != null) {
            delegate.onChipDrawableSizeChange();
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public final boolean onStateChange(int[] iArr) {
        if (this.isShapeThemingEnabled) {
            super.onStateChange(iArr);
        }
        return onStateChange(iArr, this.closeIconStateSet);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public final void onTextSizeChange() {
        onSizeChange();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.alpha != i) {
            this.alpha = i;
            invalidateSelf();
        }
    }

    public final void setCheckedIconVisible(boolean z) {
        if (this.checkedIconVisible != z) {
            boolean showsCheckedIcon = showsCheckedIcon();
            this.checkedIconVisible = z;
            boolean showsCheckedIcon2 = showsCheckedIcon();
            if (showsCheckedIcon != showsCheckedIcon2) {
                if (showsCheckedIcon2) {
                    applyChildDrawable(this.checkedIcon);
                } else {
                    unapplyChildDrawable$ar$ds(this.checkedIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public final void setChipIconVisible(boolean z) {
        if (this.chipIconVisible != z) {
            boolean showsChipIcon = showsChipIcon();
            this.chipIconVisible = z;
            boolean showsChipIcon2 = showsChipIcon();
            if (showsChipIcon != showsChipIcon2) {
                if (showsChipIcon2) {
                    applyChildDrawable(this.chipIcon);
                } else {
                    unapplyChildDrawable$ar$ds(this.chipIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public final boolean setCloseIconState(int[] iArr) {
        if (!Arrays.equals(this.closeIconStateSet, iArr)) {
            this.closeIconStateSet = iArr;
            if (showsCloseIcon()) {
                return onStateChange(getState(), iArr);
            }
            return false;
        }
        return false;
    }

    public final void setCloseIconVisible(boolean z) {
        if (this.closeIconVisible != z) {
            boolean showsCloseIcon = showsCloseIcon();
            this.closeIconVisible = z;
            boolean showsCloseIcon2 = showsCloseIcon();
            if (showsCloseIcon != showsCloseIcon2) {
                if (showsCloseIcon2) {
                    applyChildDrawable(this.closeIcon);
                } else {
                    unapplyChildDrawable$ar$ds(this.closeIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.colorFilter != colorFilter) {
            this.colorFilter = colorFilter;
            invalidateSelf();
        }
    }

    public final void setDelegate(Delegate delegate) {
        this.delegate = new WeakReference(delegate);
    }

    public final void setText(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (!TextUtils.equals(this.text, charSequence)) {
            this.text = charSequence;
            this.textDrawableHelper.setTextWidthDirty$ar$ds();
            invalidateSelf();
            onSizeChange();
        }
    }

    public final void setTextAppearance(TextAppearance textAppearance) {
        this.textDrawableHelper.setTextAppearance(textAppearance, this.context);
    }

    public final void setTextAppearanceResource(int i) {
        setTextAppearance(new TextAppearance(this.context, i));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.tint != colorStateList) {
            this.tint = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.tintMode != mode) {
            this.tintMode = mode;
            this.tintFilter = DrawableUtils$OutlineCompatR.updateTintFilter(this, this.tint, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (showsChipIcon()) {
            visible |= this.chipIcon.setVisible(z, z2);
        }
        if (showsCheckedIcon()) {
            visible |= this.checkedIcon.setVisible(z, z2);
        }
        if (showsCloseIcon()) {
            visible |= this.closeIcon.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void updateCompatRippleColor() {
        this.compatRippleColor = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean onStateChange(int[] r8, int[] r9) {
        /*
            Method dump skipped, instructions count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipDrawable.onStateChange(int[], int[]):boolean");
    }

    private static boolean isStateful(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean isStateful(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }
}
