package android.support.constraint.solver;

import java.util.ArrayList;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Goal {
    public final ArrayList variables = new ArrayList();

    public final String toString() {
        String str;
        int size = this.variables.size();
        String str2 = "Goal: ";
        for (int i = 0; i < size; i++) {
            SolverVariable solverVariable = (SolverVariable) this.variables.get(i);
            Objects.toString(solverVariable);
            String concat = solverVariable.toString().concat("[");
            int i2 = 0;
            while (true) {
                float[] fArr = solverVariable.strengthVector;
                if (i2 < 6) {
                    String str3 = concat + solverVariable.strengthVector[i2];
                    float[] fArr2 = solverVariable.strengthVector;
                    if (i2 < 5) {
                        str = ", ";
                    } else {
                        str = "] ";
                    }
                    concat = str3.concat(str);
                    i2++;
                }
            }
            str2 = str2.concat(String.valueOf(concat));
        }
        return str2;
    }

    public final void updateFromSystem(LinearSystem linearSystem) {
        this.variables.clear();
        for (int i = 1; i < linearSystem.mNumColumns; i++) {
            SolverVariable solverVariable = ((SolverVariable[]) linearSystem.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i];
            for (int i2 = 0; i2 < 6; i2++) {
                solverVariable.strengthVector[i2] = 0.0f;
            }
            solverVariable.strengthVector[solverVariable.strength] = 1.0f;
            if (solverVariable.mType$ar$edu == 4) {
                this.variables.add(solverVariable);
            }
        }
        int size = this.variables.size();
        for (int i3 = 0; i3 < size; i3++) {
            SolverVariable solverVariable2 = (SolverVariable) this.variables.get(i3);
            int i4 = solverVariable2.definitionId;
            if (i4 != -1) {
                ArrayLinkedVariables arrayLinkedVariables = linearSystem.mRows[i4].variables;
                int i5 = arrayLinkedVariables.currentSize;
                for (int i6 = 0; i6 < i5; i6++) {
                    SolverVariable variable = arrayLinkedVariables.getVariable(i6);
                    if (variable != null) {
                        float variableValue = arrayLinkedVariables.getVariableValue(i6);
                        for (int i7 = 0; i7 < 6; i7++) {
                            float[] fArr = variable.strengthVector;
                            fArr[i7] = fArr[i7] + (solverVariable2.strengthVector[i7] * variableValue);
                        }
                        if (!this.variables.contains(variable)) {
                            this.variables.add(variable);
                        }
                    }
                }
                for (int i8 = 0; i8 < 6; i8++) {
                    solverVariable2.strengthVector[i8] = 0.0f;
                }
            }
        }
    }
}
