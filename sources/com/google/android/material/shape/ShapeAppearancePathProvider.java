package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ShapeAppearancePathProvider {
    private final ShapePath[] cornerPaths = new ShapePath[4];
    private final Matrix[] cornerTransforms = new Matrix[4];
    private final Matrix[] edgeTransforms = new Matrix[4];
    private final PointF pointF = new PointF();
    private final Path overlappedEdgePath = new Path();
    private final Path boundsPath = new Path();
    private final ShapePath shapePath = new ShapePath();
    private final float[] scratch = new float[2];
    private final float[] scratch2 = new float[2];
    private final Path edgePath = new Path();
    private final Path cornerPath = new Path();
    private boolean edgeIntersectionCheckEnabled = true;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Lazy {
        public static final ShapeAppearancePathProvider INSTANCE = new ShapeAppearancePathProvider();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ShapeAppearancePathSpec {
        public final RectF bounds;
        public final float interpolation;
        public final ShapeAppearanceModel interpolationStartShapeAppearanceModel;
        public final Path path;
        public final RetryingNameResolver.ResolutionResultListener pathListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        public final ShapeAppearanceModel shapeAppearanceModel;

        public ShapeAppearancePathSpec(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, float f, RectF rectF, RetryingNameResolver.ResolutionResultListener resolutionResultListener, Path path) {
            this.pathListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
            this.shapeAppearanceModel = shapeAppearanceModel;
            this.interpolationStartShapeAppearanceModel = shapeAppearanceModel2;
            this.interpolation = f;
            this.bounds = rectF;
            this.path = path;
        }
    }

    public ShapeAppearancePathProvider() {
        for (int i = 0; i < 4; i++) {
            this.cornerPaths[i] = new ShapePath();
            this.cornerTransforms[i] = new Matrix();
            this.edgeTransforms[i] = new Matrix();
        }
    }

    private static final float angleOfEdge$ar$ds(int i) {
        return ((i + 1) % 4) * 90;
    }

    private static final CornerSize getCornerSizeForIndex$ar$ds(int i, ShapeAppearanceModel shapeAppearanceModel) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return shapeAppearanceModel.topRightCornerSize;
                }
                return shapeAppearanceModel.topLeftCornerSize;
            }
            return shapeAppearanceModel.bottomLeftCornerSize;
        }
        return shapeAppearanceModel.bottomRightCornerSize;
    }

    private final boolean pathOverlapsCorner(Path path, int i) {
        this.cornerPath.reset();
        this.cornerPaths[i].applyToPath(this.cornerTransforms[i], this.cornerPath);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.cornerPath.computeBounds(rectF, true);
        path.op(this.cornerPath, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (!rectF.isEmpty()) {
            return true;
        }
        if (rectF.width() > 1.0f && rectF.height() > 1.0f) {
            return true;
        }
        return false;
    }

    public final void calculatePath$ar$class_merging$402b682e_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, float f, RectF rectF, RetryingNameResolver.ResolutionResultListener resolutionResultListener, Path path) {
        float abs;
        EdgeTreatment edgeTreatment;
        EdgeTreatment edgeTreatment2;
        path.rewind();
        this.overlappedEdgePath.rewind();
        this.boundsPath.rewind();
        this.boundsPath.addRect(rectF, Path.Direction.CW);
        ShapeAppearancePathSpec shapeAppearancePathSpec = new ShapeAppearancePathSpec(shapeAppearanceModel, shapeAppearanceModel2, f, rectF, resolutionResultListener, path);
        for (int i = 0; i < 4; i++) {
            ShapeAppearanceModel shapeAppearanceModel3 = shapeAppearancePathSpec.shapeAppearanceModel;
            ShapeAppearanceModel shapeAppearanceModel4 = shapeAppearancePathSpec.interpolationStartShapeAppearanceModel;
            ShapeAppearanceModel shapeAppearanceModel5 = shapeAppearancePathSpec.shapeAppearanceModel;
            CornerSize cornerSizeForIndex$ar$ds = getCornerSizeForIndex$ar$ds(i, shapeAppearanceModel3);
            CornerSize cornerSizeForIndex$ar$ds2 = getCornerSizeForIndex$ar$ds(i, shapeAppearanceModel4);
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        edgeTreatment2 = shapeAppearanceModel5.topRightCorner$ar$class_merging;
                    } else {
                        edgeTreatment2 = shapeAppearanceModel5.topLeftCorner$ar$class_merging;
                    }
                } else {
                    edgeTreatment2 = shapeAppearanceModel5.bottomLeftCorner$ar$class_merging;
                }
            } else {
                edgeTreatment2 = shapeAppearanceModel5.bottomRightCorner$ar$class_merging;
            }
            ShapePath shapePath = this.cornerPaths[i];
            float f2 = shapeAppearancePathSpec.interpolation;
            RectF rectF2 = shapeAppearancePathSpec.bounds;
            edgeTreatment2.getCornerPath$ar$ds$f7fef056_0(shapePath, f2, cornerSizeForIndex$ar$ds2.getCornerSize(rectF2), cornerSizeForIndex$ar$ds.getCornerSize(rectF2));
            float angleOfEdge$ar$ds = angleOfEdge$ar$ds(i);
            this.cornerTransforms[i].reset();
            RectF rectF3 = shapeAppearancePathSpec.bounds;
            PointF pointF = this.pointF;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        pointF.set(rectF3.right, rectF3.top);
                    } else {
                        pointF.set(rectF3.left, rectF3.top);
                    }
                } else {
                    pointF.set(rectF3.left, rectF3.bottom);
                }
            } else {
                pointF.set(rectF3.right, rectF3.bottom);
            }
            Matrix matrix = this.cornerTransforms[i];
            PointF pointF2 = this.pointF;
            matrix.setTranslate(pointF2.x, pointF2.y);
            this.cornerTransforms[i].preRotate(angleOfEdge$ar$ds);
            float[] fArr = this.scratch;
            ShapePath shapePath2 = this.cornerPaths[i];
            fArr[0] = shapePath2.endX;
            fArr[1] = shapePath2.endY;
            this.cornerTransforms[i].mapPoints(fArr);
            float angleOfEdge$ar$ds2 = angleOfEdge$ar$ds(i);
            this.edgeTransforms[i].reset();
            Matrix matrix2 = this.edgeTransforms[i];
            float[] fArr2 = this.scratch;
            matrix2.setTranslate(fArr2[0], fArr2[1]);
            this.edgeTransforms[i].preRotate(angleOfEdge$ar$ds2);
        }
        int i2 = 0;
        while (i2 < 4) {
            float[] fArr3 = this.scratch;
            ShapePath shapePath3 = this.cornerPaths[i2];
            fArr3[0] = shapePath3.startX;
            fArr3[1] = shapePath3.startY;
            this.cornerTransforms[i2].mapPoints(fArr3);
            if (i2 == 0) {
                Path path2 = shapeAppearancePathSpec.path;
                float[] fArr4 = this.scratch;
                path2.moveTo(fArr4[0], fArr4[1]);
            } else {
                Path path3 = shapeAppearancePathSpec.path;
                float[] fArr5 = this.scratch;
                path3.lineTo(fArr5[0], fArr5[1]);
            }
            this.cornerPaths[i2].applyToPath(this.cornerTransforms[i2], shapeAppearancePathSpec.path);
            RetryingNameResolver.ResolutionResultListener resolutionResultListener2 = shapeAppearancePathSpec.pathListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (resolutionResultListener2 != null) {
                ShapePath shapePath4 = this.cornerPaths[i2];
                Matrix matrix3 = this.cornerTransforms[i2];
                ((MaterialShapeDrawable) resolutionResultListener2.RetryingNameResolver$ResolutionResultListener$ar$this$0).containsIncompatibleShadowOp.set(i2, false);
                ((MaterialShapeDrawable) resolutionResultListener2.RetryingNameResolver$ResolutionResultListener$ar$this$0).cornerShadowOperation[i2] = shapePath4.createShadowCompatOperation(matrix3);
            }
            int i3 = i2 + 1;
            float[] fArr6 = this.scratch;
            ShapePath shapePath5 = this.cornerPaths[i2];
            fArr6[0] = shapePath5.endX;
            fArr6[1] = shapePath5.endY;
            this.cornerTransforms[i2].mapPoints(fArr6);
            float[] fArr7 = this.scratch2;
            int i4 = i3 % 4;
            ShapePath shapePath6 = this.cornerPaths[i4];
            fArr7[0] = shapePath6.startX;
            fArr7[1] = shapePath6.startY;
            this.cornerTransforms[i4].mapPoints(fArr7);
            float f3 = this.scratch[0];
            float[] fArr8 = this.scratch2;
            float hypot = (float) Math.hypot(f3 - fArr8[0], r9[1] - fArr8[1]);
            RectF rectF4 = shapeAppearancePathSpec.bounds;
            float[] fArr9 = this.scratch;
            ShapePath[] shapePathArr = this.cornerPaths;
            float max = Math.max(hypot - 0.001f, 0.0f);
            ShapePath shapePath7 = shapePathArr[i2];
            fArr9[0] = shapePath7.endX;
            fArr9[1] = shapePath7.endY;
            this.cornerTransforms[i2].mapPoints(fArr9);
            if (i2 != 1 && i2 != 3) {
                abs = Math.abs(rectF4.centerY() - this.scratch[1]);
            } else {
                abs = Math.abs(rectF4.centerX() - this.scratch[0]);
            }
            this.shapePath.reset(0.0f, 0.0f);
            ShapeAppearanceModel shapeAppearanceModel6 = shapeAppearancePathSpec.shapeAppearanceModel;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        edgeTreatment = shapeAppearanceModel6.rightEdge;
                    } else {
                        edgeTreatment = shapeAppearanceModel6.topEdge;
                    }
                } else {
                    edgeTreatment = shapeAppearanceModel6.leftEdge;
                }
            } else {
                edgeTreatment = shapeAppearanceModel6.bottomEdge;
            }
            edgeTreatment.getEdgePath(max, abs, shapeAppearancePathSpec.interpolation, this.shapePath);
            this.edgePath.reset();
            this.shapePath.applyToPath(this.edgeTransforms[i2], this.edgePath);
            if (this.edgeIntersectionCheckEnabled && (edgeTreatment.forceIntersection() || pathOverlapsCorner(this.edgePath, i2) || pathOverlapsCorner(this.edgePath, i4))) {
                Path path4 = this.edgePath;
                path4.op(path4, this.boundsPath, Path.Op.DIFFERENCE);
                float[] fArr10 = this.scratch;
                ShapePath shapePath8 = this.shapePath;
                fArr10[0] = shapePath8.startX;
                fArr10[1] = shapePath8.startY;
                this.edgeTransforms[i2].mapPoints(fArr10);
                Path path5 = this.overlappedEdgePath;
                float[] fArr11 = this.scratch;
                path5.moveTo(fArr11[0], fArr11[1]);
                this.shapePath.applyToPath(this.edgeTransforms[i2], this.overlappedEdgePath);
            } else {
                this.shapePath.applyToPath(this.edgeTransforms[i2], shapeAppearancePathSpec.path);
            }
            RetryingNameResolver.ResolutionResultListener resolutionResultListener3 = shapeAppearancePathSpec.pathListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (resolutionResultListener3 != null) {
                ShapePath shapePath9 = this.shapePath;
                Matrix matrix4 = this.edgeTransforms[i2];
                ((MaterialShapeDrawable) resolutionResultListener3.RetryingNameResolver$ResolutionResultListener$ar$this$0).containsIncompatibleShadowOp.set(i2 + 4, false);
                ((MaterialShapeDrawable) resolutionResultListener3.RetryingNameResolver$ResolutionResultListener$ar$this$0).edgeShadowOperation[i2] = shapePath9.createShadowCompatOperation(matrix4);
            }
            i2 = i3;
        }
        path.close();
        this.overlappedEdgePath.close();
        if (!this.overlappedEdgePath.isEmpty()) {
            path.op(this.overlappedEdgePath, Path.Op.UNION);
        }
    }
}
