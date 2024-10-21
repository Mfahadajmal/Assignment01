package com.google.android.material.shape;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Objects;
import java.util.BitSet;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MaterialShapeDrawable extends Drawable implements Shapeable {
    public static final ShapeAppearanceModel DEFAULT_INTERPOLATION_START_SHAPE_APPEARANCE_MODEL = new ShapeAppearanceModel(new ShapeAppearanceModel.Builder().setAllCorners$ar$ds(0.0f));
    private static final String TAG = "MaterialShapeDrawable";
    private static final Paint clearPaint;
    public final BitSet containsIncompatibleShadowOp;
    public final ShapePath.ShadowCompatOperation[] cornerShadowOperation;
    public MaterialShapeDrawableState drawableState;
    public final ShapePath.ShadowCompatOperation[] edgeShadowOperation;
    private final Paint fillPaint;
    private final RectF insetRectF;
    private final Matrix matrix;
    private final Path path;
    private final RectF pathBounds;
    public boolean pathDirty;
    private final Path pathInsetByStroke;
    private final ShapeAppearancePathProvider pathProvider;
    private final RetryingNameResolver.ResolutionResultListener pathShadowListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final RectF rectF;
    public int resolvedTintColor;
    private final Region scratchRegion;
    public boolean shadowBitmapDrawingEnable;
    private final ShadowRenderer shadowRenderer;
    private ShapeAppearanceModel strokeInterpolationStartShapeAppearance;
    private final Paint strokePaint;
    private ShapeAppearanceModel strokeShapeAppearance;
    private PorterDuffColorFilter strokeTintFilter;
    private PorterDuffColorFilter tintFilter;
    private final Region transparentRegion;

    static {
        Paint paint = new Paint(1);
        clearPaint = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    private final void calculatePath(RectF rectF, Path path) {
        calculatePathForSize(rectF, path);
        if (this.drawableState.scale != 1.0f) {
            this.matrix.reset();
            Matrix matrix = this.matrix;
            float f = this.drawableState.scale;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.matrix);
        }
        path.computeBounds(this.pathBounds, true);
    }

    private final PorterDuffColorFilter calculateTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        if (colorStateList != null && mode != null) {
            int colorForState = colorStateList.getColorForState(getState(), 0);
            if (z) {
                colorForState = compositeElevationOverlayIfNeeded(colorForState);
            }
            this.resolvedTintColor = colorForState;
            return new PorterDuffColorFilter(colorForState, mode);
        }
        PorterDuffColorFilter porterDuffColorFilter = null;
        if (z) {
            int color = paint.getColor();
            int compositeElevationOverlayIfNeeded = compositeElevationOverlayIfNeeded(color);
            this.resolvedTintColor = compositeElevationOverlayIfNeeded;
            if (compositeElevationOverlayIfNeeded != color) {
                porterDuffColorFilter = new PorterDuffColorFilter(compositeElevationOverlayIfNeeded, PorterDuff.Mode.SRC_IN);
            }
        }
        return porterDuffColorFilter;
    }

    private final void drawCompatShadow(Canvas canvas) {
        if (this.containsIncompatibleShadowOp.cardinality() > 0) {
            Log.w(TAG, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.drawableState.shadowCompatOffset != 0) {
            canvas.drawPath(this.path, this.shadowRenderer.shadowPaint);
        }
        for (int i = 0; i < 4; i++) {
            this.cornerShadowOperation[i].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
            this.edgeShadowOperation[i].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
        }
        if (this.shadowBitmapDrawingEnable) {
            int shadowOffsetX = getShadowOffsetX();
            int shadowOffsetY = getShadowOffsetY();
            canvas.translate(-shadowOffsetX, -shadowOffsetY);
            canvas.drawPath(this.path, clearPaint);
            canvas.translate(shadowOffsetX, shadowOffsetY);
        }
    }

    private final RectF getBoundsInsetByStroke() {
        this.insetRectF.set(getBoundsAsRectF());
        RectF rectF = this.insetRectF;
        float strokeInsetLength = getStrokeInsetLength();
        rectF.inset(strokeInsetLength, strokeInsetLength);
        return this.insetRectF;
    }

    private final float getStrokeInsetLength() {
        if (hasStroke()) {
            return this.strokePaint.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private final boolean hasStroke() {
        if ((this.drawableState.paintStyle == Paint.Style.FILL_AND_STROKE || this.drawableState.paintStyle == Paint.Style.STROKE) && this.strokePaint.getStrokeWidth() > 0.0f) {
            return true;
        }
        return false;
    }

    private static int modulateAlpha(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    private final boolean updateColorsForState(int[] iArr) {
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        boolean z = false;
        if (this.drawableState.fillColor != null && color2 != (colorForState2 = this.drawableState.fillColor.getColorForState(iArr, (color2 = this.fillPaint.getColor())))) {
            this.fillPaint.setColor(colorForState2);
            z = true;
        }
        if (this.drawableState.strokeColor != null && color != (colorForState = this.drawableState.strokeColor.getColorForState(iArr, (color = this.strokePaint.getColor())))) {
            this.strokePaint.setColor(colorForState);
            return true;
        }
        return z;
    }

    private final boolean updateTintFilter() {
        PorterDuffColorFilter porterDuffColorFilter = this.tintFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.strokeTintFilter;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        this.tintFilter = calculateTintFilter(materialShapeDrawableState.tintList, materialShapeDrawableState.tintMode, this.fillPaint, true);
        MaterialShapeDrawableState materialShapeDrawableState2 = this.drawableState;
        ColorStateList colorStateList = materialShapeDrawableState2.strokeTintList;
        this.strokeTintFilter = calculateTintFilter(null, materialShapeDrawableState2.tintMode, this.strokePaint, false);
        boolean z = this.drawableState.useTintColorForShadow;
        if (!Objects.equals(porterDuffColorFilter, this.tintFilter) || !Objects.equals(porterDuffColorFilter2, this.strokeTintFilter)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void calculatePathForSize(RectF rectF, Path path) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        this.pathProvider.calculatePath$ar$class_merging$402b682e_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(materialShapeDrawableState.shapeAppearanceModel, materialShapeDrawableState.interpolationStartShapeAppearanceModel, materialShapeDrawableState.interpolation, rectF, this.pathShadowListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, path);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int compositeElevationOverlayIfNeeded(int i) {
        float z = getZ();
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        float f = z + materialShapeDrawableState.parentAbsoluteElevation;
        ElevationOverlayProvider elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
        if (elevationOverlayProvider != null) {
            return elevationOverlayProvider.compositeOverlayIfNeeded(i, f);
        }
        return i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        this.fillPaint.setColorFilter(this.tintFilter);
        int alpha = this.fillPaint.getAlpha();
        this.fillPaint.setAlpha(modulateAlpha(alpha, this.drawableState.alpha));
        this.strokePaint.setColorFilter(this.strokeTintFilter);
        this.strokePaint.setStrokeWidth(this.drawableState.strokeWidth);
        int alpha2 = this.strokePaint.getAlpha();
        this.strokePaint.setAlpha(modulateAlpha(alpha2, this.drawableState.alpha));
        final int i2 = 1;
        if (this.pathDirty) {
            final float f = -getStrokeInsetLength();
            this.strokeShapeAppearance = getShapeAppearanceModel().withTransformedCornerSizes(new ShapeAppearanceModel.CornerSizeUnaryOperator() { // from class: com.google.android.material.shape.MaterialShapeDrawable.3
                @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
                public final CornerSize apply(CornerSize cornerSize) {
                    if (i2 != 0) {
                        if (cornerSize instanceof RelativeCornerSize) {
                            return cornerSize;
                        }
                        return new AdjustedCornerSize(f, cornerSize);
                    }
                    if (cornerSize instanceof RelativeCornerSize) {
                        return cornerSize;
                    }
                    return new AdjustedCornerSize(f, cornerSize);
                }
            });
            final int i3 = 0;
            ShapeAppearanceModel withTransformedCornerSizes = this.drawableState.interpolationStartShapeAppearanceModel.withTransformedCornerSizes(new ShapeAppearanceModel.CornerSizeUnaryOperator() { // from class: com.google.android.material.shape.MaterialShapeDrawable.3
                @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
                public final CornerSize apply(CornerSize cornerSize) {
                    if (i3 != 0) {
                        if (cornerSize instanceof RelativeCornerSize) {
                            return cornerSize;
                        }
                        return new AdjustedCornerSize(f, cornerSize);
                    }
                    if (cornerSize instanceof RelativeCornerSize) {
                        return cornerSize;
                    }
                    return new AdjustedCornerSize(f, cornerSize);
                }
            });
            this.strokeInterpolationStartShapeAppearance = withTransformedCornerSizes;
            this.pathProvider.calculatePath$ar$class_merging$402b682e_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.strokeShapeAppearance, withTransformedCornerSizes, this.drawableState.interpolation, getBoundsInsetByStroke(), null, this.pathInsetByStroke);
            calculatePath(getBoundsAsRectF(), this.path);
            this.pathDirty = false;
        }
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        int i4 = materialShapeDrawableState.shadowCompatMode;
        if (i4 != 1 && materialShapeDrawableState.shadowCompatRadius > 0 && (i4 == 2 || (!isRoundRect() && !this.path.isConvex() && Build.VERSION.SDK_INT < 29))) {
            canvas.save();
            canvas.translate(getShadowOffsetX(), getShadowOffsetY());
            if (!this.shadowBitmapDrawingEnable) {
                drawCompatShadow(canvas);
                canvas.restore();
            } else {
                float width = this.pathBounds.width() - getBounds().width();
                float height = this.pathBounds.height() - getBounds().height();
                int i5 = (int) width;
                if (i5 >= 0 && (i = (int) height) >= 0) {
                    int width2 = (int) this.pathBounds.width();
                    int i6 = this.drawableState.shadowCompatRadius;
                    int height2 = (int) this.pathBounds.height();
                    int i7 = this.drawableState.shadowCompatRadius;
                    Bitmap createBitmap = Bitmap.createBitmap(width2 + i6 + i6 + i5, height2 + i7 + i7 + i, Bitmap.Config.ARGB_8888);
                    Canvas canvas2 = new Canvas(createBitmap);
                    float f2 = (getBounds().left - this.drawableState.shadowCompatRadius) - i5;
                    float f3 = (getBounds().top - this.drawableState.shadowCompatRadius) - i;
                    canvas2.translate(-f2, -f3);
                    drawCompatShadow(canvas2);
                    canvas.drawBitmap(createBitmap, f2, f3, (Paint) null);
                    createBitmap.recycle();
                    canvas.restore();
                } else {
                    throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
                }
            }
        }
        if (this.drawableState.paintStyle == Paint.Style.FILL_AND_STROKE || this.drawableState.paintStyle == Paint.Style.FILL) {
            Paint paint = this.fillPaint;
            Path path = this.path;
            MaterialShapeDrawableState materialShapeDrawableState2 = this.drawableState;
            drawShape(canvas, paint, path, materialShapeDrawableState2.shapeAppearanceModel, materialShapeDrawableState2.interpolationStartShapeAppearanceModel, getBoundsAsRectF());
        }
        if (hasStroke()) {
            drawStrokeShape(canvas);
        }
        this.fillPaint.setAlpha(alpha);
        this.strokePaint.setAlpha(alpha2);
    }

    public final void drawShape(Canvas canvas, Paint paint, Path path, ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF) {
        if (shapeAppearanceModel.isRoundRect(rectF)) {
            float lerp = DrawableUtils$OutlineCompatL.lerp(shapeAppearanceModel2.topLeftCornerSize.getCornerSize(rectF), shapeAppearanceModel.topLeftCornerSize.getCornerSize(rectF), this.drawableState.interpolation);
            canvas.drawRoundRect(rectF, lerp, lerp, paint);
            return;
        }
        canvas.drawPath(path, paint);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawStrokeShape(Canvas canvas) {
        drawShape(canvas, this.strokePaint, this.pathInsetByStroke, this.strokeShapeAppearance, this.strokeInterpolationStartShapeAppearance, getBoundsInsetByStroke());
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.drawableState.alpha;
    }

    public final RectF getBoundsAsRectF() {
        this.rectF.set(getBounds());
        return this.rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    public final float getElevation() {
        return this.drawableState.elevation;
    }

    public final ColorStateList getFillColor() {
        return this.drawableState.fillColor;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.drawableState.shadowCompatMode == 2) {
            return;
        }
        if (isRoundRect()) {
            outline.setRoundRect(getBounds(), DrawableUtils$OutlineCompatL.lerp(this.drawableState.interpolationStartShapeAppearanceModel.topLeftCornerSize.getCornerSize(getBoundsAsRectF()), getTopLeftCornerResolvedSize(), this.drawableState.interpolation));
        } else {
            calculatePath(getBoundsAsRectF(), this.path);
            DrawableUtils$OutlineCompatR.setOutlineToPath(outline, this.path);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.drawableState.padding;
        if (rect2 != null) {
            rect.set(rect2);
            return true;
        }
        return super.getPadding(rect);
    }

    public final int getShadowOffsetX() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        return (int) (materialShapeDrawableState.shadowCompatOffset * Math.sin(Math.toRadians(materialShapeDrawableState.shadowCompatRotation)));
    }

    public final int getShadowOffsetY() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        return (int) (materialShapeDrawableState.shadowCompatOffset * Math.cos(Math.toRadians(materialShapeDrawableState.shadowCompatRotation)));
    }

    public final ShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.shapeAppearanceModel;
    }

    public final float getTopLeftCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.topLeftCornerSize.getCornerSize(getBoundsAsRectF());
    }

    public final float getTopRightCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.topRightCornerSize.getCornerSize(getBoundsAsRectF());
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        this.transparentRegion.set(getBounds());
        calculatePath(getBoundsAsRectF(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public final float getZ() {
        float elevation = getElevation();
        float f = this.drawableState.translationZ;
        return elevation + 0.0f;
    }

    public final void initializeElevationOverlay(Context context) {
        this.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
        updateZ();
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.pathDirty = true;
        super.invalidateSelf();
    }

    public final boolean isRoundRect() {
        if (this.drawableState.shapeAppearanceModel.isRoundRect(getBoundsAsRectF()) && this.drawableState.interpolationStartShapeAppearanceModel.isRoundRect(getBoundsAsRectF())) {
            return true;
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (super.isStateful()) {
            return true;
        }
        ColorStateList colorStateList = this.drawableState.tintList;
        if (colorStateList != null && colorStateList.isStateful()) {
            return true;
        }
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        ColorStateList colorStateList2 = materialShapeDrawableState.strokeTintList;
        ColorStateList colorStateList3 = materialShapeDrawableState.strokeColor;
        if (colorStateList3 != null && colorStateList3.isStateful()) {
            return true;
        }
        ColorStateList colorStateList4 = this.drawableState.fillColor;
        if (colorStateList4 != null && colorStateList4.isStateful()) {
            return true;
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.drawableState = new MaterialShapeDrawableState(this.drawableState);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.pathDirty = true;
        super.onBoundsChange(rect);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        boolean updateColorsForState = updateColorsForState(iArr);
        boolean updateTintFilter = updateTintFilter();
        boolean z = true;
        if (!updateColorsForState && !updateTintFilter) {
            z = false;
        }
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.alpha != i) {
            materialShapeDrawableState.alpha = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.drawableState.colorFilter = colorFilter;
        super.invalidateSelf();
    }

    public final void setCornerSize(float f) {
        setShapeAppearanceModel(this.drawableState.shapeAppearanceModel.withCornerSize(f));
    }

    public final void setElevation(float f) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.elevation != f) {
            materialShapeDrawableState.elevation = f;
            updateZ();
        }
    }

    public final void setFillColor(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.fillColor != colorStateList) {
            materialShapeDrawableState.fillColor = colorStateList;
            onStateChange(getState());
        }
    }

    public final void setInterpolation(float f) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.interpolation != f) {
            materialShapeDrawableState.interpolation = f;
            this.pathDirty = true;
            invalidateSelf();
        }
    }

    public final void setShadowColor$ar$ds() {
        this.shadowRenderer.setShadowColor(-12303292);
        this.drawableState.useTintColorForShadow = false;
        super.invalidateSelf();
    }

    public final void setShadowCompatibilityMode$ar$ds() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.shadowCompatMode != 2) {
            materialShapeDrawableState.shadowCompatMode = 2;
            super.invalidateSelf();
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        invalidateSelf();
    }

    public final void setStroke(float f, int i) {
        setStrokeWidth(f);
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public final void setStrokeColor(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.strokeColor != colorStateList) {
            materialShapeDrawableState.strokeColor = colorStateList;
            onStateChange(getState());
        }
    }

    public final void setStrokeWidth(float f) {
        this.drawableState.strokeWidth = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.drawableState.tintList = colorStateList;
        updateTintFilter();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.tintMode != mode) {
            materialShapeDrawableState.tintMode = mode;
            updateTintFilter();
            super.invalidateSelf();
        }
    }

    public final void updateZ() {
        float z = getZ();
        this.drawableState.shadowCompatRadius = (int) Math.ceil(0.75f * z);
        this.drawableState.shadowCompatOffset = (int) Math.ceil(z * 0.25f);
        updateTintFilter();
        super.invalidateSelf();
    }

    public MaterialShapeDrawable(Context context, AttributeSet attributeSet, int i, int i2) {
        this(new ShapeAppearanceModel(ShapeAppearanceModel.builder(context, attributeSet, i, i2)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MaterialShapeDrawable(MaterialShapeDrawableState materialShapeDrawableState) {
        ShapeAppearancePathProvider shapeAppearancePathProvider;
        this.cornerShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.containsIncompatibleShadowOp = new BitSet(8);
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        Paint paint = new Paint(1);
        this.fillPaint = paint;
        Paint paint2 = new Paint(1);
        this.strokePaint = paint2;
        this.shadowRenderer = new ShadowRenderer();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            shapeAppearancePathProvider = ShapeAppearancePathProvider.Lazy.INSTANCE;
        } else {
            shapeAppearancePathProvider = new ShapeAppearancePathProvider();
        }
        this.pathProvider = shapeAppearancePathProvider;
        this.pathBounds = new RectF();
        this.shadowBitmapDrawingEnable = true;
        this.drawableState = materialShapeDrawableState;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        updateTintFilter();
        updateColorsForState(getState());
        this.pathShadowListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    }

    public final void setStroke(float f, ColorStateList colorStateList) {
        setStrokeWidth(f);
        setStrokeColor(colorStateList);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class MaterialShapeDrawableState extends Drawable.ConstantState {
        int alpha;
        ColorFilter colorFilter;
        float elevation;
        public ElevationOverlayProvider elevationOverlayProvider;
        ColorStateList fillColor;
        public float interpolation;
        public final ShapeAppearanceModel interpolationStartShapeAppearanceModel;
        public Rect padding;
        Paint.Style paintStyle;
        public float parentAbsoluteElevation;
        float scale;
        int shadowCompatMode;
        int shadowCompatOffset;
        int shadowCompatRadius;
        int shadowCompatRotation;
        public ShapeAppearanceModel shapeAppearanceModel;
        ColorStateList strokeColor;
        ColorStateList strokeTintList;
        float strokeWidth;
        ColorStateList tintList;
        PorterDuff.Mode tintMode;
        float translationZ;
        boolean useTintColorForShadow;

        public MaterialShapeDrawableState(MaterialShapeDrawableState materialShapeDrawableState) {
            this.fillColor = null;
            this.strokeColor = null;
            this.strokeTintList = null;
            this.tintList = null;
            this.tintMode = PorterDuff.Mode.SRC_IN;
            this.padding = null;
            this.scale = 1.0f;
            this.interpolation = 1.0f;
            this.alpha = PrivateKeyType.INVALID;
            this.parentAbsoluteElevation = 0.0f;
            this.elevation = 0.0f;
            this.translationZ = 0.0f;
            this.shadowCompatMode = 0;
            this.shadowCompatRadius = 0;
            this.shadowCompatOffset = 0;
            this.shadowCompatRotation = 0;
            this.useTintColorForShadow = false;
            this.paintStyle = Paint.Style.FILL_AND_STROKE;
            this.shapeAppearanceModel = materialShapeDrawableState.shapeAppearanceModel;
            this.interpolationStartShapeAppearanceModel = materialShapeDrawableState.interpolationStartShapeAppearanceModel;
            this.elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
            this.strokeWidth = materialShapeDrawableState.strokeWidth;
            this.colorFilter = materialShapeDrawableState.colorFilter;
            this.fillColor = materialShapeDrawableState.fillColor;
            this.strokeColor = materialShapeDrawableState.strokeColor;
            this.tintMode = materialShapeDrawableState.tintMode;
            this.tintList = materialShapeDrawableState.tintList;
            this.alpha = materialShapeDrawableState.alpha;
            this.scale = materialShapeDrawableState.scale;
            this.shadowCompatOffset = materialShapeDrawableState.shadowCompatOffset;
            this.shadowCompatMode = materialShapeDrawableState.shadowCompatMode;
            boolean z = materialShapeDrawableState.useTintColorForShadow;
            this.useTintColorForShadow = false;
            this.interpolation = materialShapeDrawableState.interpolation;
            this.parentAbsoluteElevation = materialShapeDrawableState.parentAbsoluteElevation;
            this.elevation = materialShapeDrawableState.elevation;
            float f = materialShapeDrawableState.translationZ;
            this.translationZ = 0.0f;
            this.shadowCompatRadius = materialShapeDrawableState.shadowCompatRadius;
            this.shadowCompatRotation = materialShapeDrawableState.shadowCompatRotation;
            ColorStateList colorStateList = materialShapeDrawableState.strokeTintList;
            this.strokeTintList = null;
            this.paintStyle = materialShapeDrawableState.paintStyle;
            Rect rect = materialShapeDrawableState.padding;
            if (rect != null) {
                this.padding = new Rect(rect);
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this);
            materialShapeDrawable.pathDirty = true;
            return materialShapeDrawable;
        }

        public MaterialShapeDrawableState(ShapeAppearanceModel shapeAppearanceModel) {
            this.fillColor = null;
            this.strokeColor = null;
            this.strokeTintList = null;
            this.tintList = null;
            this.tintMode = PorterDuff.Mode.SRC_IN;
            this.padding = null;
            this.scale = 1.0f;
            this.interpolation = 1.0f;
            this.alpha = PrivateKeyType.INVALID;
            this.parentAbsoluteElevation = 0.0f;
            this.elevation = 0.0f;
            this.translationZ = 0.0f;
            this.shadowCompatMode = 0;
            this.shadowCompatRadius = 0;
            this.shadowCompatOffset = 0;
            this.shadowCompatRotation = 0;
            this.useTintColorForShadow = false;
            this.paintStyle = Paint.Style.FILL_AND_STROKE;
            this.shapeAppearanceModel = shapeAppearanceModel;
            this.elevationOverlayProvider = null;
            this.interpolationStartShapeAppearanceModel = MaterialShapeDrawable.DEFAULT_INTERPOLATION_START_SHAPE_APPEARANCE_MODEL;
        }
    }

    public MaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        this(new MaterialShapeDrawableState(shapeAppearanceModel));
    }
}
