package com.google.android.accessibility.braille.brailledisplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleView extends View {
    private static final float[] DOT_POSITIONS = {0.0f, 0.0f, 0.0f, 0.33f, 0.0f, 0.67f, 1.0f, 0.0f, 1.0f, 0.33f, 1.0f, 0.67f, 0.0f, 1.0f, 1.0f, 1.0f};
    public byte[] braille;
    public volatile OverlayDisplay brailleCellClickListener$ar$class_merging;
    public int[] brailleToTextPositions;
    private final float cellHeight;
    private final float cellPadding;
    private final float cellWidth;
    public final Runnable clearHighlightedCell;
    private final float dotRadius;
    private final Drawable highlightDrawable;
    public int highlightedCell;
    private int maxNumTextCells;
    public int numTextCells;
    private final float outerHeight;
    private final float outerWidth;
    private int pressedCell;
    private final Paint primaryPaint;
    private volatile OnResizeListener resizeListener;
    private final Paint secondaryPaint;
    public CharSequence text;
    private final int touchSlop;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnResizeListener {
    }

    public BrailleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.clearHighlightedCell = new WorkerKt$$ExternalSyntheticLambda0(this, 7, null);
        this.numTextCells = 0;
        this.braille = new byte[0];
        this.text = "";
        this.brailleToTextPositions = new int[0];
        this.maxNumTextCells = 0;
        this.highlightedCell = -1;
        this.pressedCell = -1;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.BrailleView, 0, 0);
        try {
            Paint paint = new Paint();
            this.primaryPaint = paint;
            paint.setAntiAlias(true);
            paint.setColor(obtainStyledAttributes.getColor(1, -1));
            paint.setTextSize(obtainStyledAttributes.getDimension(0, 20.0f));
            paint.setTextAlign(Paint.Align.CENTER);
            Paint paint2 = new Paint(paint);
            this.secondaryPaint = paint2;
            paint2.setAlpha(64);
            this.highlightDrawable = obtainStyledAttributes.getDrawable(6);
            this.dotRadius = obtainStyledAttributes.getDimension(5, 4.0f);
            float dimension = obtainStyledAttributes.getDimension(4, 10.0f);
            this.cellWidth = dimension;
            float dimension2 = obtainStyledAttributes.getDimension(2, 30.0f);
            this.cellHeight = dimension2;
            float dimension3 = obtainStyledAttributes.getDimension(3, 13.0f);
            this.cellPadding = dimension3;
            float f = dimension3 + dimension3;
            this.outerWidth = dimension + f;
            this.outerHeight = dimension2 + f;
            this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private final boolean withinTouchSlopOfCell(MotionEvent motionEvent, int i) {
        if (i >= 0 && i < this.numTextCells) {
            float x = motionEvent.getX();
            float f = this.outerWidth;
            float f2 = this.touchSlop;
            if ((i * f) - f2 <= x && x <= ((i + 1) * f) + f2) {
                return true;
            }
        }
        return false;
    }

    public final void cancelPendingTouches() {
        if (this.pressedCell != -1) {
            this.pressedCell = -1;
            invalidate();
        }
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        float f;
        float f2;
        int i;
        int[] iArr;
        int length;
        int length2;
        int i2;
        Paint paint;
        Drawable drawable;
        Drawable drawable2;
        float f3 = this.cellWidth;
        float f4 = f3 * 3.0f;
        float f5 = this.cellHeight;
        float f6 = 2.0f;
        if (f4 >= f5) {
            float f7 = f5 / 3.0f;
            float f8 = this.cellPadding;
            f = ((f3 - f7) / 2.0f) + f8;
            f3 = f7;
            f2 = f8;
            f4 = f5;
        } else {
            f = this.cellPadding;
            f2 = ((f5 - f4) / 2.0f) + f;
        }
        int i3 = this.highlightedCell;
        int i4 = 0;
        if (i3 >= 0 && i3 < this.numTextCells && (drawable2 = this.highlightDrawable) != null) {
            drawable2.setBounds(Math.round(i3 * this.outerWidth), 0, Math.round((this.highlightedCell + 1) * this.outerWidth), Math.round(this.outerHeight));
            this.highlightDrawable.draw(canvas);
        }
        int i5 = this.pressedCell;
        if (i5 >= 0 && i5 < this.numTextCells && i5 != this.highlightedCell && (drawable = this.highlightDrawable) != null) {
            drawable.setBounds(Math.round(i5 * this.outerWidth), 0, Math.round((this.pressedCell + 1) * this.outerWidth), Math.round(this.outerHeight));
            this.highlightDrawable.draw(canvas);
        }
        for (int i6 = 0; i6 < this.numTextCells; i6++) {
            canvas.save();
            canvas.translate(i6 * this.outerWidth, 0.0f);
            canvas.clipRect(0.0f, 0.0f, this.outerWidth, this.outerHeight);
            byte[] bArr = this.braille;
            if (i6 < bArr.length) {
                i2 = bArr[i6] & 255;
            } else {
                i2 = 0;
            }
            for (int i7 = 0; i7 < 16; i7 += 2) {
                float[] fArr = DOT_POSITIONS;
                float f9 = (fArr[i7] * f3) + f;
                float f10 = (fArr[i7 + 1] * f4) + f2;
                if ((i2 & 1) != 0) {
                    paint = this.primaryPaint;
                } else {
                    paint = this.secondaryPaint;
                }
                canvas.drawCircle(f9, f10, this.dotRadius, paint);
                i2 >>= 1;
            }
            canvas.restore();
        }
        Paint.FontMetrics fontMetrics = this.primaryPaint.getFontMetrics();
        while (i4 < this.numTextCells) {
            int[] iArr2 = this.brailleToTextPositions;
            if (i4 < iArr2.length) {
                int i8 = iArr2[i4];
                int i9 = i4;
                while (true) {
                    i = i9 + 1;
                    iArr = this.brailleToTextPositions;
                    length = iArr.length;
                    if (i >= length || iArr[i] > i8) {
                        break;
                    } else {
                        i9 = i;
                    }
                }
                if (i < length) {
                    length2 = iArr[i];
                } else {
                    length2 = this.text.length();
                }
                int i10 = length2;
                float f11 = this.outerWidth;
                float f12 = i4;
                float f13 = this.outerHeight;
                float f14 = ((this.cellPadding + f13) - fontMetrics.ascent) + fontMetrics.descent;
                float f15 = this.cellPadding;
                float f16 = f12 * f11;
                float f17 = f11 * i;
                float f18 = (f16 + f17) / f6;
                float f19 = f13 - fontMetrics.ascent;
                float measureText = this.primaryPaint.measureText(this.text, i8, i10);
                float f20 = f17 - f16;
                if (measureText > f20) {
                    this.primaryPaint.setTextScaleX(f20 / measureText);
                }
                canvas.save();
                canvas.clipRect(f16, f13, f17, f14 + f15);
                canvas.drawText(this.text, i8, i10, f18, f19, this.primaryPaint);
                canvas.restore();
                this.primaryPaint.setTextScaleX(1.0f);
                i4 = i;
                f6 = 2.0f;
            } else {
                return;
            }
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int size;
        Paint.FontMetrics fontMetrics = this.primaryPaint.getFontMetrics();
        if (View.MeasureSpec.getMode(i) == 0) {
            size = Math.round(this.numTextCells * this.outerWidth);
        } else {
            size = View.MeasureSpec.getSize(i);
        }
        setMeasuredDimension(size, Math.round(((this.outerHeight + this.cellPadding) - fontMetrics.ascent) + fontMetrics.descent));
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5 = (int) (i / this.outerWidth);
        if (i5 == this.maxNumTextCells) {
            return;
        }
        this.maxNumTextCells = i5;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked == 3) {
                        cancelPendingTouches();
                        return false;
                    }
                    return false;
                }
                if (!withinTouchSlopOfCell(motionEvent, this.pressedCell)) {
                    cancelPendingTouches();
                    return false;
                }
                return false;
            }
            if (withinTouchSlopOfCell(motionEvent, this.pressedCell)) {
                int i = this.pressedCell;
                OverlayDisplay overlayDisplay = this.brailleCellClickListener$ar$class_merging;
                if (overlayDisplay != null) {
                    overlayDisplay.onBrailleCellClick$ar$ds(i);
                }
            }
            cancelPendingTouches();
            return false;
        }
        int x = (int) (motionEvent.getX() / this.outerWidth);
        if (x >= 0 && x < this.numTextCells) {
            this.pressedCell = x;
            invalidate();
            return false;
        }
        cancelPendingTouches();
        return false;
    }
}
