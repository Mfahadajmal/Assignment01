package com.google.android.material.shape;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MarkerEdgeTreatment extends EdgeTreatment {
    private final float radius;

    public MarkerEdgeTreatment(float f) {
        this.radius = f - 0.001f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public final boolean forceIntersection() {
        return true;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public final void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        double d = this.radius;
        float sqrt = (float) ((Math.sqrt(2.0d) * d) / 2.0d);
        float sqrt2 = (float) Math.sqrt(Math.pow(d, 2.0d) - Math.pow(sqrt, 2.0d));
        double d2 = this.radius;
        shapePath.reset(f2 - sqrt, ((float) (-((Math.sqrt(2.0d) * d2) - d2))) + sqrt2);
        double d3 = this.radius;
        shapePath.lineTo(f2, (float) (-((Math.sqrt(2.0d) * d3) - d3)));
        double d4 = this.radius;
        shapePath.lineTo(f2 + sqrt, ((float) (-((Math.sqrt(2.0d) * d4) - d4))) + sqrt2);
    }
}
