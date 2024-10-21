package android.support.constraint.solver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SolverVariable {
    public float computedValue;
    public int mType$ar$edu;
    public int id = -1;
    public int definitionId = -1;
    public int strength = 0;
    public final float[] strengthVector = new float[6];
    ArrayRow[] mClientEquations = new ArrayRow[8];
    int mClientEquationsCount = 0;

    public SolverVariable(int i) {
        this.mType$ar$edu = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeClientEquation(ArrayRow arrayRow) {
        int i = 0;
        for (int i2 = 0; i2 < this.mClientEquationsCount; i2++) {
            if (this.mClientEquations[i2] == arrayRow) {
                while (true) {
                    int i3 = this.mClientEquationsCount;
                    if (i < (i3 - i2) - 1) {
                        ArrayRow[] arrayRowArr = this.mClientEquations;
                        int i4 = i2 + i;
                        arrayRowArr[i4] = arrayRowArr[i4 + 1];
                        i++;
                    } else {
                        this.mClientEquationsCount = i3 - 1;
                        return;
                    }
                }
            }
        }
    }

    public final void reset() {
        this.mType$ar$edu = 5;
        this.strength = 0;
        this.id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.mClientEquationsCount = 0;
    }

    public final String toString() {
        return "".concat("null");
    }
}
