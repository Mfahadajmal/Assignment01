package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* compiled from: PG */
/* loaded from: classes.dex */
class CutoutDrawable extends MaterialShapeDrawable {
    CutoutDrawableState drawableState;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CutoutDrawableState extends MaterialShapeDrawable.MaterialShapeDrawableState {
        public final RectF cutoutBounds;

        public CutoutDrawableState(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
            super(shapeAppearanceModel);
            this.cutoutBounds = rectF;
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable.MaterialShapeDrawableState, android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            ImplApi18 implApi18 = new ImplApi18(this);
            implApi18.invalidateSelf();
            return implApi18;
        }

        public CutoutDrawableState(CutoutDrawableState cutoutDrawableState) {
            super(cutoutDrawableState);
            this.cutoutBounds = cutoutDrawableState.cutoutBounds;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ImplApi18 extends CutoutDrawable {
        public ImplApi18(CutoutDrawableState cutoutDrawableState) {
            super(cutoutDrawableState);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.material.shape.MaterialShapeDrawable
        public final void drawStrokeShape(Canvas canvas) {
            if (this.drawableState.cutoutBounds.isEmpty()) {
                super.drawStrokeShape(canvas);
                return;
            }
            canvas.save();
            canvas.clipOutRect(this.drawableState.cutoutBounds);
            super.drawStrokeShape(canvas);
            canvas.restore();
        }
    }

    public CutoutDrawable(CutoutDrawableState cutoutDrawableState) {
        super(cutoutDrawableState);
        this.drawableState = cutoutDrawableState;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public final Drawable mutate() {
        this.drawableState = new CutoutDrawableState(this.drawableState);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setCutout(float f, float f2, float f3, float f4) {
        if (f == this.drawableState.cutoutBounds.left && f2 == this.drawableState.cutoutBounds.top && f3 == this.drawableState.cutoutBounds.right && f4 == this.drawableState.cutoutBounds.bottom) {
            return;
        }
        this.drawableState.cutoutBounds.set(f, f2, f3, f4);
        invalidateSelf();
    }
}
