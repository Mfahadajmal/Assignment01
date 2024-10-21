package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ShapeAppearanceModel {
    public static final CornerSize PILL = new RelativeCornerSize(0.5f);
    EdgeTreatment bottomEdge;
    public EdgeTreatment bottomLeftCorner$ar$class_merging;
    public CornerSize bottomLeftCornerSize;
    public EdgeTreatment bottomRightCorner$ar$class_merging;
    public CornerSize bottomRightCornerSize;
    EdgeTreatment leftEdge;
    EdgeTreatment rightEdge;
    EdgeTreatment topEdge;
    public EdgeTreatment topLeftCorner$ar$class_merging;
    public CornerSize topLeftCornerSize;
    public EdgeTreatment topRightCorner$ar$class_merging;
    public CornerSize topRightCornerSize;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public EdgeTreatment bottomEdge;
        public EdgeTreatment bottomLeftCorner$ar$class_merging;
        public CornerSize bottomLeftCornerSize;
        public EdgeTreatment bottomRightCorner$ar$class_merging;
        public CornerSize bottomRightCornerSize;
        public EdgeTreatment leftEdge;
        public EdgeTreatment rightEdge;
        public EdgeTreatment topEdge;
        public EdgeTreatment topLeftCorner$ar$class_merging;
        public CornerSize topLeftCornerSize;
        public EdgeTreatment topRightCorner$ar$class_merging;
        public CornerSize topRightCornerSize;

        public Builder() {
            this.topLeftCorner$ar$class_merging = new RoundedCornerTreatment();
            this.topRightCorner$ar$class_merging = new RoundedCornerTreatment();
            this.bottomRightCorner$ar$class_merging = new RoundedCornerTreatment();
            this.bottomLeftCorner$ar$class_merging = new RoundedCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = new EdgeTreatment();
            this.rightEdge = new EdgeTreatment();
            this.bottomEdge = new EdgeTreatment();
            this.leftEdge = new EdgeTreatment();
        }

        private static float compatCornerTreatmentSize$ar$class_merging(EdgeTreatment edgeTreatment) {
            if (edgeTreatment instanceof RoundedCornerTreatment) {
                float f = ((RoundedCornerTreatment) edgeTreatment).radius;
                return -1.0f;
            }
            if (edgeTreatment instanceof CutCornerTreatment) {
                float f2 = ((CutCornerTreatment) edgeTreatment).size;
            }
            return -1.0f;
        }

        public final Builder setAllCornerSizes(float f) {
            setTopLeftCornerSize$ar$ds(f);
            setTopRightCornerSize$ar$ds(f);
            setBottomRightCornerSize$ar$ds(f);
            setBottomLeftCornerSize$ar$ds(f);
            return this;
        }

        public final Builder setAllCorners$ar$ds(float f) {
            EdgeTreatment createCornerTreatment$ar$class_merging = EdgeTreatment.createCornerTreatment$ar$class_merging(0);
            setTopLeftCorner$ar$ds$ar$class_merging(createCornerTreatment$ar$class_merging);
            setTopRightCorner$ar$ds$ar$class_merging(createCornerTreatment$ar$class_merging);
            setBottomRightCorner$ar$ds$ar$class_merging(createCornerTreatment$ar$class_merging);
            setBottomLeftCorner$ar$ds$ar$class_merging(createCornerTreatment$ar$class_merging);
            return setAllCornerSizes(f);
        }

        public final void setBottomLeftCorner$ar$ds$ar$class_merging(EdgeTreatment edgeTreatment) {
            this.bottomLeftCorner$ar$class_merging = edgeTreatment;
            compatCornerTreatmentSize$ar$class_merging(edgeTreatment);
        }

        public final void setBottomLeftCornerSize$ar$ds(float f) {
            this.bottomLeftCornerSize = new AbsoluteCornerSize(f);
        }

        public final void setBottomRightCorner$ar$ds$ar$class_merging(EdgeTreatment edgeTreatment) {
            this.bottomRightCorner$ar$class_merging = edgeTreatment;
            compatCornerTreatmentSize$ar$class_merging(edgeTreatment);
        }

        public final void setBottomRightCornerSize$ar$ds(float f) {
            this.bottomRightCornerSize = new AbsoluteCornerSize(f);
        }

        public final void setTopLeftCorner$ar$ds$ar$class_merging(EdgeTreatment edgeTreatment) {
            this.topLeftCorner$ar$class_merging = edgeTreatment;
            compatCornerTreatmentSize$ar$class_merging(edgeTreatment);
        }

        public final void setTopLeftCornerSize$ar$ds(float f) {
            this.topLeftCornerSize = new AbsoluteCornerSize(f);
        }

        public final void setTopRightCorner$ar$ds$ar$class_merging(EdgeTreatment edgeTreatment) {
            this.topRightCorner$ar$class_merging = edgeTreatment;
            compatCornerTreatmentSize$ar$class_merging(edgeTreatment);
        }

        public final void setTopRightCornerSize$ar$ds(float f) {
            this.topRightCornerSize = new AbsoluteCornerSize(f);
        }

        public Builder(ShapeAppearanceModel shapeAppearanceModel) {
            this.topLeftCorner$ar$class_merging = new RoundedCornerTreatment();
            this.topRightCorner$ar$class_merging = new RoundedCornerTreatment();
            this.bottomRightCorner$ar$class_merging = new RoundedCornerTreatment();
            this.bottomLeftCorner$ar$class_merging = new RoundedCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = new EdgeTreatment();
            this.rightEdge = new EdgeTreatment();
            this.bottomEdge = new EdgeTreatment();
            this.leftEdge = new EdgeTreatment();
            this.topLeftCorner$ar$class_merging = shapeAppearanceModel.topLeftCorner$ar$class_merging;
            this.topRightCorner$ar$class_merging = shapeAppearanceModel.topRightCorner$ar$class_merging;
            this.bottomRightCorner$ar$class_merging = shapeAppearanceModel.bottomRightCorner$ar$class_merging;
            this.bottomLeftCorner$ar$class_merging = shapeAppearanceModel.bottomLeftCorner$ar$class_merging;
            this.topLeftCornerSize = shapeAppearanceModel.topLeftCornerSize;
            this.topRightCornerSize = shapeAppearanceModel.topRightCornerSize;
            this.bottomRightCornerSize = shapeAppearanceModel.bottomRightCornerSize;
            this.bottomLeftCornerSize = shapeAppearanceModel.bottomLeftCornerSize;
            this.topEdge = shapeAppearanceModel.topEdge;
            this.rightEdge = shapeAppearanceModel.rightEdge;
            this.bottomEdge = shapeAppearanceModel.bottomEdge;
            this.leftEdge = shapeAppearanceModel.leftEdge;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface CornerSizeUnaryOperator {
        CornerSize apply(CornerSize cornerSize);
    }

    public ShapeAppearanceModel() {
        this.topLeftCorner$ar$class_merging = new RoundedCornerTreatment();
        this.topRightCorner$ar$class_merging = new RoundedCornerTreatment();
        this.bottomRightCorner$ar$class_merging = new RoundedCornerTreatment();
        this.bottomLeftCorner$ar$class_merging = new RoundedCornerTreatment();
        this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topEdge = new EdgeTreatment();
        this.rightEdge = new EdgeTreatment();
        this.bottomEdge = new EdgeTreatment();
        this.leftEdge = new EdgeTreatment();
    }

    public static Builder builder(Context context, int i, int i2) {
        return builder(context, i, i2, new AbsoluteCornerSize(0.0f));
    }

    public static CornerSize getCornerSize(TypedArray typedArray, int i, CornerSize cornerSize) {
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue != null) {
            if (peekValue.type == 5) {
                return new AbsoluteCornerSize(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics()));
            }
            if (peekValue.type == 6) {
                return new RelativeCornerSize(peekValue.getFraction(1.0f, 1.0f));
            }
        }
        return cornerSize;
    }

    public final boolean isRoundRect(RectF rectF) {
        boolean z;
        boolean z2;
        boolean z3;
        if (this.leftEdge.getClass().equals(EdgeTreatment.class) && this.rightEdge.getClass().equals(EdgeTreatment.class) && this.topEdge.getClass().equals(EdgeTreatment.class) && this.bottomEdge.getClass().equals(EdgeTreatment.class)) {
            z = true;
        } else {
            z = false;
        }
        float cornerSize = this.topLeftCornerSize.getCornerSize(rectF);
        if (this.topRightCornerSize.getCornerSize(rectF) == cornerSize && this.bottomLeftCornerSize.getCornerSize(rectF) == cornerSize && this.bottomRightCornerSize.getCornerSize(rectF) == cornerSize) {
            z2 = true;
        } else {
            z2 = false;
        }
        if ((this.topRightCorner$ar$class_merging instanceof RoundedCornerTreatment) && (this.topLeftCorner$ar$class_merging instanceof RoundedCornerTreatment) && (this.bottomRightCorner$ar$class_merging instanceof RoundedCornerTreatment) && (this.bottomLeftCorner$ar$class_merging instanceof RoundedCornerTreatment)) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z && z2 && z3) {
            return true;
        }
        return false;
    }

    public final ShapeAppearanceModel withCornerSize(float f) {
        return new ShapeAppearanceModel(new Builder(this).setAllCornerSizes(f));
    }

    public final ShapeAppearanceModel withTransformedCornerSizes(CornerSizeUnaryOperator cornerSizeUnaryOperator) {
        Builder builder = new Builder(this);
        builder.topLeftCornerSize = cornerSizeUnaryOperator.apply(this.topLeftCornerSize);
        builder.topRightCornerSize = cornerSizeUnaryOperator.apply(this.topRightCornerSize);
        builder.bottomLeftCornerSize = cornerSizeUnaryOperator.apply(this.bottomLeftCornerSize);
        builder.bottomRightCornerSize = cornerSizeUnaryOperator.apply(this.bottomRightCornerSize);
        return new ShapeAppearanceModel(builder);
    }

    public ShapeAppearanceModel(Builder builder) {
        this.topLeftCorner$ar$class_merging = builder.topLeftCorner$ar$class_merging;
        this.topRightCorner$ar$class_merging = builder.topRightCorner$ar$class_merging;
        this.bottomRightCorner$ar$class_merging = builder.bottomRightCorner$ar$class_merging;
        this.bottomLeftCorner$ar$class_merging = builder.bottomLeftCorner$ar$class_merging;
        this.topLeftCornerSize = builder.topLeftCornerSize;
        this.topRightCornerSize = builder.topRightCornerSize;
        this.bottomRightCornerSize = builder.bottomRightCornerSize;
        this.bottomLeftCornerSize = builder.bottomLeftCornerSize;
        this.topEdge = builder.topEdge;
        this.rightEdge = builder.rightEdge;
        this.bottomEdge = builder.bottomEdge;
        this.leftEdge = builder.leftEdge;
    }

    private static Builder builder(Context context, int i, int i2, CornerSize cornerSize) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        if (i2 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i2);
        }
        TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R$styleable.ShapeAppearance);
        try {
            int i3 = obtainStyledAttributes.getInt(0, 0);
            int i4 = obtainStyledAttributes.getInt(3, i3);
            int i5 = obtainStyledAttributes.getInt(4, i3);
            int i6 = obtainStyledAttributes.getInt(2, i3);
            int i7 = obtainStyledAttributes.getInt(1, i3);
            CornerSize cornerSize2 = getCornerSize(obtainStyledAttributes, 5, cornerSize);
            CornerSize cornerSize3 = getCornerSize(obtainStyledAttributes, 8, cornerSize2);
            CornerSize cornerSize4 = getCornerSize(obtainStyledAttributes, 9, cornerSize2);
            CornerSize cornerSize5 = getCornerSize(obtainStyledAttributes, 7, cornerSize2);
            CornerSize cornerSize6 = getCornerSize(obtainStyledAttributes, 6, cornerSize2);
            Builder builder = new Builder();
            builder.setTopLeftCorner$ar$ds$ar$class_merging(EdgeTreatment.createCornerTreatment$ar$class_merging(i4));
            builder.topLeftCornerSize = cornerSize3;
            builder.setTopRightCorner$ar$ds$ar$class_merging(EdgeTreatment.createCornerTreatment$ar$class_merging(i5));
            builder.topRightCornerSize = cornerSize4;
            builder.setBottomRightCorner$ar$ds$ar$class_merging(EdgeTreatment.createCornerTreatment$ar$class_merging(i6));
            builder.bottomRightCornerSize = cornerSize5;
            builder.setBottomLeftCorner$ar$ds$ar$class_merging(EdgeTreatment.createCornerTreatment$ar$class_merging(i7));
            builder.bottomLeftCornerSize = cornerSize6;
            return builder;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2) {
        return builder(context, attributeSet, i, i2, new AbsoluteCornerSize(0.0f));
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2, CornerSize cornerSize) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return builder(context, resourceId, resourceId2, cornerSize);
    }
}
