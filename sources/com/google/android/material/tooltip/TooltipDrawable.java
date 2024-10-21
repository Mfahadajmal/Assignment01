package com.google.android.material.tooltip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.navigation.NavigationBarItemView$$ExternalSyntheticLambda0;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MarkerEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.OffsetEdgeTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TooltipDrawable extends MaterialShapeDrawable implements TextDrawableHelper.TextDrawableDelegate {
    public int arrowSize;
    public final View.OnLayoutChangeListener attachedViewLayoutChangeListener;
    public final Context context;
    private final Rect displayFrame;
    private final Paint.FontMetrics fontMetrics;
    public float labelOpacity;
    public int layoutMargin;
    private int locationOnScreenX;
    public int minHeight;
    public int minWidth;
    public int padding;
    public boolean showMarker;
    private CharSequence text;
    public final TextDrawableHelper textDrawableHelper;
    public float tooltipPivotY;
    public float tooltipScaleX;
    public float tooltipScaleY;

    public TooltipDrawable(Context context, int i) {
        super(context, null, 0, i);
        this.fontMetrics = new Paint.FontMetrics();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper;
        this.attachedViewLayoutChangeListener = new NavigationBarItemView$$ExternalSyntheticLambda0(this, 2);
        this.displayFrame = new Rect();
        this.tooltipScaleX = 1.0f;
        this.tooltipScaleY = 1.0f;
        this.tooltipPivotY = 0.5f;
        this.labelOpacity = 1.0f;
        this.context = context;
        textDrawableHelper.textPaint.density = context.getResources().getDisplayMetrics().density;
        textDrawableHelper.textPaint.setTextAlign(Paint.Align.CENTER);
    }

    private final float calculatePointerOffset() {
        if (((this.displayFrame.right - getBounds().right) - this.locationOnScreenX) - this.layoutMargin < 0) {
            return ((this.displayFrame.right - getBounds().right) - this.locationOnScreenX) - this.layoutMargin;
        }
        if (((this.displayFrame.left - getBounds().left) - this.locationOnScreenX) + this.layoutMargin <= 0) {
            return 0.0f;
        }
        return ((this.displayFrame.left - getBounds().left) - this.locationOnScreenX) + this.layoutMargin;
    }

    public final EdgeTreatment createMarkerEdge() {
        float f = -calculatePointerOffset();
        float width = ((float) (getBounds().width() - (this.arrowSize * Math.sqrt(2.0d)))) / 2.0f;
        return new OffsetEdgeTreatment(new MarkerEdgeTreatment(this.arrowSize), Math.min(Math.max(f, -width), width));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.save();
        float calculatePointerOffset = calculatePointerOffset();
        double sqrt = this.arrowSize * Math.sqrt(2.0d);
        double d = this.arrowSize;
        canvas.scale(this.tooltipScaleX, this.tooltipScaleY, getBounds().left + (getBounds().width() * 0.5f), getBounds().top + (getBounds().height() * this.tooltipPivotY));
        canvas.translate(calculatePointerOffset, (float) (-(sqrt - d)));
        super.draw(canvas);
        if (this.text != null) {
            float centerY = getBounds().centerY();
            this.textDrawableHelper.textPaint.getFontMetrics(this.fontMetrics);
            Paint.FontMetrics fontMetrics = this.fontMetrics;
            float f = fontMetrics.descent + fontMetrics.ascent;
            TextDrawableHelper textDrawableHelper = this.textDrawableHelper;
            if (textDrawableHelper.textAppearance != null) {
                textDrawableHelper.textPaint.drawableState = getState();
                this.textDrawableHelper.updateTextPaintDrawState(this.context);
                this.textDrawableHelper.textPaint.setAlpha((int) (this.labelOpacity * 255.0f));
            }
            float f2 = centerY - (f / 2.0f);
            CharSequence charSequence = this.text;
            canvas.drawText(charSequence, 0, charSequence.length(), r0.centerX(), (int) f2, this.textDrawableHelper.textPaint);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) Math.max(this.textDrawableHelper.textPaint.getTextSize(), this.minHeight);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        float textWidth;
        int i = this.padding;
        int i2 = i + i;
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            textWidth = 0.0f;
        } else {
            textWidth = this.textDrawableHelper.getTextWidth(charSequence.toString());
        }
        return (int) Math.max(i2 + textWidth, this.minWidth);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.showMarker) {
            ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder(getShapeAppearanceModel());
            builder.bottomEdge = createMarkerEdge();
            setShapeAppearanceModel(new ShapeAppearanceModel(builder));
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public final void onTextSizeChange() {
        invalidateSelf();
    }

    public final void setText(CharSequence charSequence) {
        if (!TextUtils.equals(this.text, charSequence)) {
            this.text = charSequence;
            this.textDrawableHelper.setTextWidthDirty$ar$ds();
            invalidateSelf();
        }
    }

    public final void updateLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.locationOnScreenX = iArr[0];
        view.getWindowVisibleDisplayFrame(this.displayFrame);
    }
}
