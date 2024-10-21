package androidx.constraintlayout.core;

import com.google.common.util.concurrent.ExecutionList;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ArrayRow {
    public ArrayLinkedVariables variables$ar$class_merging;
    SolverVariable mVariable = null;
    public float mConstantValue = 0.0f;
    boolean mUsed = false;
    final ArrayList mVariablesToUpdate = new ArrayList();
    boolean mIsSimpleDefinition = false;

    public ArrayRow() {
    }

    public static final boolean isNew$ar$ds(SolverVariable solverVariable) {
        if (solverVariable.usageInRowCount <= 1) {
            return true;
        }
        return false;
    }

    public final void addError$ar$ds$bebff777_0(LinearSystem linearSystem, int i) {
        this.variables$ar$class_merging.put(linearSystem.createErrorVariable(i, "ep"), 1.0f);
        this.variables$ar$class_merging.put(linearSystem.createErrorVariable(i, "em"), -1.0f);
    }

    public final void createRowDimensionRatio$ar$ds$797f96c3_0(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables$ar$class_merging.put(solverVariable, -1.0f);
        this.variables$ar$class_merging.put(solverVariable2, 1.0f);
        this.variables$ar$class_merging.put(solverVariable3, f);
        this.variables$ar$class_merging.put(solverVariable4, -f);
    }

    public final void createRowGreaterThan$ar$ds$cd721f52_0(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.mConstantValue = i;
            if (z) {
                this.variables$ar$class_merging.put(solverVariable, 1.0f);
                this.variables$ar$class_merging.put(solverVariable2, -1.0f);
                this.variables$ar$class_merging.put(solverVariable3, -1.0f);
                return;
            }
        }
        this.variables$ar$class_merging.put(solverVariable, -1.0f);
        this.variables$ar$class_merging.put(solverVariable2, 1.0f);
        this.variables$ar$class_merging.put(solverVariable3, 1.0f);
    }

    public final void createRowLowerThan$ar$ds$f5420b2b_0(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.mConstantValue = i;
            if (z) {
                this.variables$ar$class_merging.put(solverVariable, 1.0f);
                this.variables$ar$class_merging.put(solverVariable2, -1.0f);
                this.variables$ar$class_merging.put(solverVariable3, 1.0f);
                return;
            }
        }
        this.variables$ar$class_merging.put(solverVariable, -1.0f);
        this.variables$ar$class_merging.put(solverVariable2, 1.0f);
        this.variables$ar$class_merging.put(solverVariable3, -1.0f);
    }

    public final void createRowWithAngle$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables$ar$class_merging.put(solverVariable3, 0.5f);
        this.variables$ar$class_merging.put(solverVariable4, 0.5f);
        this.variables$ar$class_merging.put(solverVariable, -0.5f);
        this.variables$ar$class_merging.put(solverVariable2, -0.5f);
        this.mConstantValue = -f;
    }

    public final SolverVariable getKey() {
        return this.mVariable;
    }

    public SolverVariable getPivotCandidate$ar$ds(boolean[] zArr) {
        return pickPivotInVariables(zArr, null);
    }

    public final void initFromRow$ar$class_merging(ArrayRow arrayRow) {
        this.mVariable = null;
        this.variables$ar$class_merging.clear();
        for (int i = 0; i < arrayRow.variables$ar$class_merging.getCurrentSize(); i++) {
            this.variables$ar$class_merging.add(arrayRow.variables$ar$class_merging.getVariable(i), arrayRow.variables$ar$class_merging.getVariableValue(i), true);
        }
    }

    public boolean isEmpty() {
        if (this.mVariable == null && this.mConstantValue == 0.0f && this.variables$ar$class_merging.getCurrentSize() == 0) {
            return true;
        }
        return false;
    }

    public final SolverVariable pickPivotInVariables(boolean[] zArr, SolverVariable solverVariable) {
        int currentSize = this.variables$ar$class_merging.getCurrentSize();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i = 0; i < currentSize; i++) {
            float variableValue = this.variables$ar$class_merging.getVariableValue(i);
            if (variableValue < 0.0f) {
                SolverVariable variable = this.variables$ar$class_merging.getVariable(i);
                if ((zArr == null || !zArr[variable.id]) && variable != solverVariable && ((variable.mType$ar$edu$e789bd0d_0 == 3 || variable.mType$ar$edu$e789bd0d_0 == 4) && variableValue < f)) {
                    f = variableValue;
                    solverVariable2 = variable;
                }
            }
        }
        return solverVariable2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void pivot(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.mVariable;
        if (solverVariable2 != null) {
            this.variables$ar$class_merging.put(solverVariable2, -1.0f);
            this.mVariable.mDefinitionId = -1;
            this.mVariable = null;
        }
        float f = -this.variables$ar$class_merging.remove(solverVariable, true);
        this.mVariable = solverVariable;
        if (f == 1.0f) {
            return;
        }
        this.mConstantValue /= f;
        this.variables$ar$class_merging.divideByAmount(f);
    }

    public String toString() {
        String concat;
        boolean z;
        String valueOf;
        String str;
        SolverVariable solverVariable = this.mVariable;
        if (solverVariable == null) {
            concat = "0";
        } else {
            Objects.toString(solverVariable);
            concat = "".concat(String.valueOf(solverVariable));
        }
        float f = this.mConstantValue;
        String concat2 = concat.concat(" = ");
        if (f != 0.0f) {
            concat2 = concat2 + this.mConstantValue;
            z = true;
        } else {
            z = false;
        }
        int currentSize = this.variables$ar$class_merging.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable variable = this.variables$ar$class_merging.getVariable(i);
            if (variable != null) {
                float variableValue = this.variables$ar$class_merging.getVariableValue(i);
                if (variableValue != 0.0f) {
                    String solverVariable2 = variable.toString();
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
                    if (variableValue == 1.0f) {
                        concat2 = String.valueOf(concat2).concat(String.valueOf(solverVariable2));
                    } else {
                        concat2 = concat2 + variableValue + " " + solverVariable2;
                    }
                    z = true;
                }
            }
        }
        if (!z) {
            return String.valueOf(concat2).concat("0.0");
        }
        return concat2;
    }

    public final void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable != null && solverVariable.isFinalValue) {
            this.mConstantValue += solverVariable.computedValue * this.variables$ar$class_merging.get(solverVariable);
            this.variables$ar$class_merging.remove(solverVariable, z);
            if (z) {
                solverVariable.removeFromRow(this);
            }
            if (this.variables$ar$class_merging.getCurrentSize() == 0) {
                this.mIsSimpleDefinition = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        this.mConstantValue += arrayRow.mConstantValue * this.variables$ar$class_merging.use(arrayRow, z);
        if (z) {
            arrayRow.mVariable.removeFromRow(this);
        }
        if (this.mVariable != null && this.variables$ar$class_merging.getCurrentSize() == 0) {
            this.mIsSimpleDefinition = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    public ArrayRow(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.variables$ar$class_merging = new ArrayLinkedVariables(this, runnableExecutorPair);
    }
}
