package androidx.constraintlayout.core;

import android.support.v7.widget.GapWorker;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.util.concurrent.ExecutionList;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PriorityGoalRow extends ArrayRow {
    final GoalVariableAccessor mAccessor;
    private SolverVariable[] mArrayGoals;
    public int mNumGoals;
    private SolverVariable[] mSortArray;
    private final int mTableSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GoalVariableAccessor {
        SolverVariable mVariable;

        public GoalVariableAccessor() {
        }

        public final boolean addToGoal(SolverVariable solverVariable, float f) {
            boolean z = true;
            if (this.mVariable.inGoal) {
                for (int i = 0; i < 9; i++) {
                    float[] fArr = this.mVariable.mGoalStrengthVector;
                    float f2 = fArr[i] + (solverVariable.mGoalStrengthVector[i] * f);
                    fArr[i] = f2;
                    if (Math.abs(f2) < 1.0E-4f) {
                        this.mVariable.mGoalStrengthVector[i] = 0.0f;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    PriorityGoalRow.this.removeGoal(this.mVariable);
                }
                return false;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                float f3 = solverVariable.mGoalStrengthVector[i2];
                if (f3 != 0.0f) {
                    float f4 = f3 * f;
                    if (Math.abs(f4) < 1.0E-4f) {
                        f4 = 0.0f;
                    }
                    this.mVariable.mGoalStrengthVector[i2] = f4;
                } else {
                    this.mVariable.mGoalStrengthVector[i2] = 0.0f;
                }
            }
            return true;
        }

        public final String toString() {
            String str = "[ ";
            if (this.mVariable != null) {
                for (int i = 0; i < 9; i++) {
                    str = str + this.mVariable.mGoalStrengthVector[i] + " ";
                }
            }
            return str + "] " + this.mVariable;
        }
    }

    public PriorityGoalRow(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        super(runnableExecutorPair);
        this.mTableSize = BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        this.mArrayGoals = new SolverVariable[BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE];
        this.mSortArray = new SolverVariable[BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE];
        this.mNumGoals = 0;
        this.mAccessor = new GoalVariableAccessor();
    }

    public final void addToGoal(SolverVariable solverVariable) {
        int i;
        int i2 = this.mNumGoals + 1;
        SolverVariable[] solverVariableArr = this.mArrayGoals;
        int length = solverVariableArr.length;
        if (i2 > length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, length + length);
            this.mArrayGoals = solverVariableArr2;
            int length2 = solverVariableArr2.length;
            this.mSortArray = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, length2 + length2);
        }
        SolverVariable[] solverVariableArr3 = this.mArrayGoals;
        int i3 = this.mNumGoals;
        solverVariableArr3[i3] = solverVariable;
        int i4 = i3 + 1;
        this.mNumGoals = i4;
        if (i4 > 1 && solverVariableArr3[i3].id > solverVariable.id) {
            int i5 = 0;
            while (true) {
                i = this.mNumGoals;
                if (i5 >= i) {
                    break;
                }
                this.mSortArray[i5] = this.mArrayGoals[i5];
                i5++;
            }
            Arrays.sort(this.mSortArray, 0, i, new GapWorker.AnonymousClass1(2));
            for (int i6 = 0; i6 < this.mNumGoals; i6++) {
                this.mArrayGoals[i6] = this.mSortArray[i6];
            }
        }
        solverVariable.inGoal = true;
        solverVariable.addToRow(this);
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public final SolverVariable getPivotCandidate$ar$ds(boolean[] zArr) {
        int i = -1;
        for (int i2 = 0; i2 < this.mNumGoals; i2++) {
            SolverVariable[] solverVariableArr = this.mArrayGoals;
            SolverVariable solverVariable = solverVariableArr[i2];
            if (!zArr[solverVariable.id]) {
                GoalVariableAccessor goalVariableAccessor = this.mAccessor;
                goalVariableAccessor.mVariable = solverVariable;
                int i3 = 8;
                if (i == -1) {
                    while (i3 >= 0) {
                        float f = goalVariableAccessor.mVariable.mGoalStrengthVector[i3];
                        if (f > 0.0f) {
                            break;
                        }
                        if (f < 0.0f) {
                            i = i2;
                            break;
                        }
                        i3--;
                    }
                    i = -1;
                } else {
                    SolverVariable solverVariable2 = solverVariableArr[i];
                    while (true) {
                        if (i3 >= 0) {
                            float f2 = solverVariable2.mGoalStrengthVector[i3];
                            float f3 = goalVariableAccessor.mVariable.mGoalStrengthVector[i3];
                            if (f3 == f2) {
                                i3--;
                            } else if (f3 >= f2) {
                            }
                        }
                    }
                }
            }
        }
        if (i == -1) {
            return null;
        }
        return this.mArrayGoals[i];
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public final boolean isEmpty() {
        if (this.mNumGoals == 0) {
            return true;
        }
        return false;
    }

    public final void removeGoal(SolverVariable solverVariable) {
        int i = 0;
        while (i < this.mNumGoals) {
            if (this.mArrayGoals[i] == solverVariable) {
                while (true) {
                    int i2 = this.mNumGoals - 1;
                    if (i < i2) {
                        SolverVariable[] solverVariableArr = this.mArrayGoals;
                        int i3 = i + 1;
                        solverVariableArr[i] = solverVariableArr[i3];
                        i = i3;
                    } else {
                        this.mNumGoals = i2;
                        solverVariable.inGoal = false;
                        return;
                    }
                }
            } else {
                i++;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public final String toString() {
        String str = " goal -> (" + this.mConstantValue + ") : ";
        for (int i = 0; i < this.mNumGoals; i++) {
            this.mAccessor.mVariable = this.mArrayGoals[i];
            str = str + this.mAccessor + " ";
        }
        return str;
    }

    @Override // androidx.constraintlayout.core.ArrayRow
    public final void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        SolverVariable solverVariable = arrayRow.mVariable;
        if (solverVariable == null) {
            return;
        }
        ArrayLinkedVariables arrayLinkedVariables = arrayRow.variables$ar$class_merging;
        int currentSize = arrayLinkedVariables.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable variable = arrayLinkedVariables.getVariable(i);
            float variableValue = arrayLinkedVariables.getVariableValue(i);
            GoalVariableAccessor goalVariableAccessor = this.mAccessor;
            goalVariableAccessor.mVariable = variable;
            if (goalVariableAccessor.addToGoal(solverVariable, variableValue)) {
                addToGoal(variable);
            }
            this.mConstantValue += arrayRow.mConstantValue * variableValue;
        }
        removeGoal(solverVariable);
    }
}
