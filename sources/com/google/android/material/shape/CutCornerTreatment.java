package com.google.android.material.shape;

import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CutCornerTreatment extends EdgeTreatment {
    final float size = -1.0f;

    @Override // com.google.android.material.shape.EdgeTreatment
    public final void getCornerPath$ar$ds(ShapePath shapePath, float f, float f2) {
        getCornerPath$ar$ds$f7fef056_0(shapePath, f, 0.0f, f2);
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public final void getCornerPath$ar$ds$f7fef056_0(ShapePath shapePath, float f, float f2, float f3) {
        float lerp = DrawableUtils$OutlineCompatL.lerp(f2, f3, f);
        shapePath.reset(0.0f, lerp, 180.0f, 90.0f);
        double d = lerp;
        shapePath.lineTo((float) (Math.sin(Math.toRadians(90.0d)) * d), (float) (Math.sin(Math.toRadians(0.0d)) * d));
    }
}
