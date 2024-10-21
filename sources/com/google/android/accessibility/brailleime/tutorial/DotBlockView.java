package com.google.android.accessibility.brailleime.tutorial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatDelegateImpl;
import android.view.View;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DotBlockView extends View {
    private final Paint blocksPaint;
    private final DashPathEffect dashPathEffectInColor1;
    private final Paint dashPathPaint;
    private final boolean isTabletop;
    private final BrailleInputOptions options;
    public int orientation;
    private final Path path;

    public DotBlockView(Context context, int i, boolean z, BrailleInputOptions brailleInputOptions) {
        super(context);
        this.orientation = i;
        this.isTabletop = z;
        this.options = brailleInputOptions;
        this.path = new Path();
        float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dot_block_dash_path_interval);
        Paint paint = new Paint();
        this.blocksPaint = paint;
        paint.setColor(context.getColor(R.color.braille_block_background));
        Paint paint2 = new Paint();
        this.dashPathPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dot_block_dash_path_stroke_width));
        paint2.setAlpha(100);
        this.dashPathEffectInColor1 = new DashPathEffect(new float[]{dimensionPixelSize, dimensionPixelSize}, 0.0f);
    }

    private final float getPairedPosition1(int i, float f, int i2, int i3) {
        int i4 = i2 / (this.options.brailleType$ar$edu >> 1);
        return (f * i2) + ((f / 2.0f) * ((i2 + 1) / ((i4 + i4) + 2))) + (i * i2) + i3;
    }

    private static float getPairedPosition2(int i, float f, int i2, int i3, int i4) {
        float f2 = (i2 + 1) * f;
        float f3 = f / 2.0f;
        return (((f2 + f3) + (f3 * (r4 / i3))) + (i * r4)) - i4;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i;
        int width = getWidth();
        int height = getHeight();
        canvas.save();
        int i2 = 1;
        if (AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(getResources()) && this.orientation == 1) {
            width = getHeight();
            height = getWidth();
            canvas.rotate(270.0f);
            canvas.translate(-getHeight(), 0.0f);
        }
        boolean z = this.isTabletop;
        if (z) {
            i = this.options.brailleType$ar$edu;
        } else {
            i = 2;
        }
        int i3 = i;
        if (!z) {
            i2 = this.options.brailleType$ar$edu >> 1;
        }
        int i4 = i2;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.input_plane_dot_radius);
        int i5 = dimensionPixelSize + dimensionPixelSize;
        float f = width;
        int i6 = i3 * i5;
        int i7 = i3 + 1;
        float f2 = height;
        int i8 = i4 + 1;
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.dot_block_padding);
        int i9 = 0;
        while (i9 < i3) {
            float f3 = (f - i6) / i7;
            float pairedPosition1 = getPairedPosition1(i5, f3, i9, dimensionPixelSize2);
            float pairedPosition2 = getPairedPosition2(i5, f3, i9, i3, dimensionPixelSize2);
            int i10 = 0;
            while (i10 < i4) {
                float f4 = pairedPosition2;
                float f5 = (f2 - (i4 * i5)) / i8;
                float pairedPosition12 = getPairedPosition1(i5, f5, i10, dimensionPixelSize2);
                float pairedPosition22 = getPairedPosition2(i5, f5, i10, i4, dimensionPixelSize2);
                int i11 = i3;
                float f6 = pairedPosition1;
                canvas.drawRect(pairedPosition1, pairedPosition12, f4, pairedPosition22, this.blocksPaint);
                this.path.reset();
                this.path.moveTo(f6, pairedPosition12);
                pairedPosition2 = f4;
                this.path.lineTo(pairedPosition2, pairedPosition12);
                this.path.lineTo(pairedPosition2, pairedPosition22);
                this.path.lineTo(f6, pairedPosition22);
                this.path.lineTo(f6, pairedPosition12);
                this.dashPathPaint.setPathEffect(this.dashPathEffectInColor1);
                this.dashPathPaint.setColor(getContext().getColor(R.color.braille_block_dash_path));
                canvas.drawPath(this.path, this.dashPathPaint);
                i10++;
                pairedPosition1 = f6;
                dimensionPixelSize2 = dimensionPixelSize2;
                i3 = i11;
                i9 = i9;
            }
            i9++;
        }
        canvas.restore();
    }
}
