package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MatrixEvaluator implements TypeEvaluator {
    private final float[] tempStartValues = new float[9];
    private final float[] tempEndValues = new float[9];
    private final Matrix tempMatrix = new Matrix();

    @Override // android.animation.TypeEvaluator
    public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.tempStartValues);
        matrix2.getValues(this.tempEndValues);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.tempEndValues;
            float[] fArr2 = this.tempStartValues;
            float f2 = fArr[i];
            float f3 = fArr2[i];
            fArr[i] = f3 + ((f2 - f3) * f);
        }
        this.tempMatrix.setValues(this.tempEndValues);
        return this.tempMatrix;
    }
}
