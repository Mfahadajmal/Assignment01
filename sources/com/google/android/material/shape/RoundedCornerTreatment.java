package com.google.android.material.shape;

import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.shape.ShapePath;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RoundedCornerTreatment extends EdgeTreatment {
    final float radius = -1.0f;

    @Override // com.google.android.material.shape.EdgeTreatment
    public final void getCornerPath$ar$ds(ShapePath shapePath, float f, float f2) {
        getCornerPath$ar$ds$f7fef056_0(shapePath, f, 0.0f, f2);
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public final void getCornerPath$ar$ds$f7fef056_0(ShapePath shapePath, float f, float f2, float f3) {
        float lerp = DrawableUtils$OutlineCompatL.lerp(f2, f3, f);
        shapePath.reset(0.0f, lerp, 180.0f, 90.0f);
        float f4 = lerp + lerp;
        ShapePath.PathArcOperation pathArcOperation = new ShapePath.PathArcOperation(0.0f, 0.0f, f4, f4);
        pathArcOperation.startAngle = 180.0f;
        pathArcOperation.sweepAngle = 90.0f;
        shapePath.operations.add(pathArcOperation);
        shapePath.addShadowCompatOperation(new ShapePath.ArcShadowOperation(pathArcOperation), 180.0f, 270.0f);
        float f5 = f4 + 0.0f;
        float f6 = f5 / 2.0f;
        float f7 = f5 * 0.5f;
        shapePath.endX = (((float) Math.cos(Math.toRadians(270.0d))) * f6) + f7;
        shapePath.endY = f7 + (f6 * ((float) Math.sin(Math.toRadians(270.0d))));
    }
}
