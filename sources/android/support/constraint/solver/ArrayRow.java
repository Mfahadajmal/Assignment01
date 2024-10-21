package android.support.constraint.solver;

import com.google.common.util.concurrent.ExecutionList;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayRow {
    public final ArrayLinkedVariables variables;
    public SolverVariable variable = null;
    public float constantValue = 0.0f;
    boolean used = false;
    boolean isSimpleDefinition = false;

    public ArrayRow(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.variables = new ArrayLinkedVariables(this, runnableExecutorPair);
    }

    public final void addError$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2) {
        this.variables.put(solverVariable, 1.0f);
        this.variables.put(solverVariable2, -1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void createRowCentering$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable2, -2.0f);
            return;
        }
        if (f == 0.5f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.constantValue = (-i) + i2;
                return;
            }
            return;
        }
        if (f <= 0.0f) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.constantValue = i;
            return;
        }
        if (f >= 1.0f) {
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.constantValue = i2;
            return;
        }
        float f2 = 1.0f - f;
        this.variables.put(solverVariable, f2);
        this.variables.put(solverVariable2, -f2);
        this.variables.put(solverVariable3, -f);
        this.variables.put(solverVariable4, f);
        if (i <= 0 && i2 <= 0) {
            return;
        }
        this.constantValue = ((-i) * f2) + (i2 * f);
    }

    public final void createRowDimensionRatio$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, f);
        this.variables.put(solverVariable4, -f);
    }

    public final void createRowEqualDimension$ar$ds(float f, float f2, float f3, SolverVariable solverVariable, int i, SolverVariable solverVariable2, int i2, SolverVariable solverVariable3, int i3, SolverVariable solverVariable4, int i4) {
        int i5 = -i;
        if (f2 != 0.0f && f != f3) {
            float f4 = (f / f2) / (f3 / f2);
            this.constantValue = (i5 - i2) + (i3 * f4) + (i4 * f4);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f4);
            this.variables.put(solverVariable3, -f4);
            return;
        }
        this.constantValue = (i5 - i2) + i3 + i4;
        this.variables.put(solverVariable, 1.0f);
        this.variables.put(solverVariable2, -1.0f);
        this.variables.put(solverVariable4, 1.0f);
        this.variables.put(solverVariable3, -1.0f);
    }

    public final void createRowEquals$ar$ds(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.constantValue = -i;
            this.variables.put(solverVariable, 1.0f);
        } else {
            this.constantValue = i;
            this.variables.put(solverVariable, -1.0f);
        }
    }

    public final void createRowEquals$ar$ds$b19c6a08_0(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.constantValue = i;
            if (z) {
                this.variables.put(solverVariable, 1.0f);
                this.variables.put(solverVariable2, -1.0f);
                return;
            }
        }
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
    }

    public final void createRowGreaterThan$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.constantValue = i;
            if (z) {
                this.variables.put(solverVariable, 1.0f);
                this.variables.put(solverVariable2, -1.0f);
                this.variables.put(solverVariable3, -1.0f);
                return;
            }
        }
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, 1.0f);
    }

    public final void createRowLowerThan$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.constantValue = i;
            if (z) {
                this.variables.put(solverVariable, 1.0f);
                this.variables.put(solverVariable2, -1.0f);
                this.variables.put(solverVariable3, 1.0f);
                return;
            }
        }
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, -1.0f);
    }

    public final void pivot(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.variable;
        if (solverVariable2 != null) {
            this.variables.put(solverVariable2, -1.0f);
            this.variable = null;
        }
        float f = -this.variables.remove(solverVariable);
        this.variable = solverVariable;
        if (f != 1.0f) {
            this.constantValue /= f;
            ArrayLinkedVariables arrayLinkedVariables = this.variables;
            int i = arrayLinkedVariables.mHead;
            for (int i2 = 0; i != -1 && i2 < arrayLinkedVariables.currentSize; i2++) {
                float[] fArr = arrayLinkedVariables.mArrayValues;
                fArr[i] = fArr[i] / f;
                i = arrayLinkedVariables.mArrayNextIndices[i];
            }
        }
    }

    public final String toString() {
        String concat;
        boolean z;
        String valueOf;
        String str;
        SolverVariable solverVariable = this.variable;
        if (solverVariable == null) {
            concat = "0";
        } else {
            Objects.toString(solverVariable);
            concat = "".concat(String.valueOf(solverVariable));
        }
        float f = this.constantValue;
        String concat2 = concat.concat(" = ");
        if (f != 0.0f) {
            concat2 = concat2 + this.constantValue;
            z = true;
        } else {
            z = false;
        }
        int i = this.variables.currentSize;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = this.variables.getVariable(i2);
            if (variable != null) {
                float variableValue = this.variables.getVariableValue(i2);
                if (!z) {
                    if (variableValue < 0.0f) {
                        variableValue = -variableValue;
                        concat2 = String.valueOf(concat2).concat("- ");
                    }
                } else {
                    if (variableValue > 0.0f) {
                        valueOf = String.valueOf(concat2);
                        str = " + ";
                    } else {
                        variableValue = -variableValue;
                        valueOf = String.valueOf(concat2);
                        str = " - ";
                    }
                    concat2 = valueOf.concat(str);
                }
                String solverVariable2 = variable.toString();
                if (variableValue == 1.0f) {
                    concat2 = String.valueOf(concat2).concat(String.valueOf(solverVariable2));
                } else {
                    concat2 = concat2 + variableValue + " " + solverVariable2;
                }
                z = true;
            }
        }
        if (!z) {
            return String.valueOf(concat2).concat("0.0");
        }
        return concat2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateClientEquations() {
        ArrayLinkedVariables arrayLinkedVariables = this.variables;
        int i = arrayLinkedVariables.mHead;
        for (int i2 = 0; i != -1 && i2 < arrayLinkedVariables.currentSize; i2++) {
            SolverVariable solverVariable = ((SolverVariable[]) arrayLinkedVariables.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[arrayLinkedVariables.mArrayIndices[i]];
            int i3 = 0;
            while (true) {
                int i4 = solverVariable.mClientEquationsCount;
                if (i3 < i4) {
                    if (solverVariable.mClientEquations[i3] == this) {
                        break;
                    } else {
                        i3++;
                    }
                } else {
                    ArrayRow[] arrayRowArr = solverVariable.mClientEquations;
                    int length = arrayRowArr.length;
                    if (i4 >= length) {
                        solverVariable.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, length + length);
                    }
                    ArrayRow[] arrayRowArr2 = solverVariable.mClientEquations;
                    int i5 = solverVariable.mClientEquationsCount;
                    arrayRowArr2[i5] = this;
                    solverVariable.mClientEquationsCount = i5 + 1;
                }
            }
            i = arrayLinkedVariables.mArrayNextIndices[i];
        }
    }

    public final void updateRowWithEquation$ar$ds(ArrayRow arrayRow) {
        this.variables.updateFromRow(this, arrayRow);
    }
}
