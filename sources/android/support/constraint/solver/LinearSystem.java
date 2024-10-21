package android.support.constraint.solver;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import com.google.android.gms.clearcut.internal.LogErrorQueue;
import com.google.common.util.concurrent.ExecutionList;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LinearSystem {
    private static int POOL_SIZE = 1000;
    public final ExecutionList.RunnableExecutorPair mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public ArrayRow[] mRows;
    int mVariablesID = 0;
    public final Goal mGoal = new Goal();
    private int TABLE_SIZE = 32;
    private int mMaxColumns = 32;
    public boolean[] mAlreadyTestedCandidates = new boolean[32];
    public int mNumColumns = 1;
    public int mNumRows = 0;
    private int mMaxRows = 32;
    private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
    private int mPoolVariablesCount = 0;
    private ArrayRow[] tempClientsCopy = new ArrayRow[32];

    public LinearSystem() {
        this.mRows = null;
        this.mRows = new ArrayRow[32];
        releaseRows();
        this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new ExecutionList.RunnableExecutorPair((byte[]) null);
    }

    private final SolverVariable acquireSolverVariable$ar$edu(int i) {
        SolverVariable solverVariable = (SolverVariable) ((LogErrorQueue) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable).acquire();
        if (solverVariable == null) {
            solverVariable = new SolverVariable(i);
        } else {
            solverVariable.reset();
            solverVariable.mType$ar$edu = i;
        }
        int i2 = this.mPoolVariablesCount;
        int i3 = POOL_SIZE;
        if (i2 >= i3) {
            int i4 = i3 + i3;
            POOL_SIZE = i4;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i4);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i5 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i5 + 1;
        solverVariableArr[i5] = solverVariable;
        return solverVariable;
    }

    public static ArrayRow createRowCentering(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowCentering$ar$ds(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (z) {
            SolverVariable createErrorVariable = linearSystem.createErrorVariable();
            SolverVariable createErrorVariable2 = linearSystem.createErrorVariable();
            createErrorVariable.strength = 4;
            createErrorVariable2.strength = 4;
            createRow.addError$ar$ds(createErrorVariable, createErrorVariable2);
        }
        return createRow;
    }

    public static ArrayRow createRowEquals(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowEquals$ar$ds$b19c6a08_0(solverVariable, solverVariable2, i);
        if (z) {
            linearSystem.addSingleError(createRow, 1);
        }
        return createRow;
    }

    public static final int getObjectVariableValue$ar$ds(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).mSolverVariable;
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    private final void increaseTableSize() {
        int i = this.TABLE_SIZE;
        int i2 = i + i;
        this.TABLE_SIZE = i2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i2);
        this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next = (SolverVariable[]) Arrays.copyOf((Object[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next, this.TABLE_SIZE);
        int i3 = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i3];
        this.mMaxColumns = i3;
        this.mMaxRows = i3;
        this.mGoal.variables.clear();
    }

    private final void releaseRows() {
        int i = 0;
        while (true) {
            ArrayRow[] arrayRowArr = this.mRows;
            if (i < arrayRowArr.length) {
                ArrayRow arrayRow = arrayRowArr[i];
                if (arrayRow != null) {
                    ((LogErrorQueue) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$executor).release$ar$ds(arrayRow);
                }
                this.mRows[i] = null;
                i++;
            } else {
                return;
            }
        }
    }

    public final void addCentering$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowCentering$ar$ds(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        SolverVariable createErrorVariable = createErrorVariable();
        SolverVariable createErrorVariable2 = createErrorVariable();
        createErrorVariable.strength = 4;
        createErrorVariable2.strength = 4;
        createRow.addError$ar$ds(createErrorVariable, createErrorVariable2);
        addConstraint(createRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0104 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addConstraint(android.support.constraint.solver.ArrayRow r13) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.LinearSystem.addConstraint(android.support.constraint.solver.ArrayRow):void");
    }

    public final void addEquality(SolverVariable solverVariable, int i) {
        int i2 = solverVariable.definitionId;
        if (i2 != -1) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = i;
                return;
            }
            ArrayRow createRow = createRow();
            createRow.createRowEquals$ar$ds(solverVariable, i);
            addConstraint(createRow);
            return;
        }
        float f = i;
        ArrayRow createRow2 = createRow();
        createRow2.variable = solverVariable;
        solverVariable.computedValue = f;
        createRow2.constantValue = f;
        createRow2.isSimpleDefinition = true;
        addConstraint(createRow2);
    }

    public final void addEquality$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowEquals$ar$ds$b19c6a08_0(solverVariable, solverVariable2, i);
        SolverVariable createErrorVariable = createErrorVariable();
        SolverVariable createErrorVariable2 = createErrorVariable();
        createErrorVariable.strength = i2;
        createErrorVariable2.strength = i2;
        createRow.addError$ar$ds(createErrorVariable, createErrorVariable2);
        addConstraint(createRow);
    }

    public final void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = i2;
        createRow.createRowGreaterThan$ar$ds(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public final void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = i2;
        createRow.createRowLowerThan$ar$ds(solverVariable, solverVariable2, createSlackVariable, i);
        addConstraint(createRow);
    }

    public final void addSingleError(ArrayRow arrayRow, int i) {
        arrayRow.variables.put(createErrorVariable(), i);
    }

    public final SolverVariable createErrorVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable$ar$edu = acquireSolverVariable$ar$edu(4);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable$ar$edu.id = i;
        ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i] = acquireSolverVariable$ar$edu;
        return acquireSolverVariable$ar$edu;
    }

    public final SolverVariable createObjectVariable(Object obj) {
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
        SolverVariable solverVariable = constraintAnchor.mSolverVariable;
        if (solverVariable == null) {
            constraintAnchor.resetSolverVariable$ar$ds();
            solverVariable = constraintAnchor.mSolverVariable;
        }
        int i = solverVariable.id;
        if (i != -1) {
            if (i > this.mVariablesID || ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i] == null) {
                if (i != -1) {
                    solverVariable.reset();
                }
            }
            return solverVariable;
        }
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        solverVariable.id = i2;
        solverVariable.mType$ar$edu = 1;
        ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[this.mVariablesID] = solverVariable;
        return solverVariable;
    }

    public final ArrayRow createRow() {
        ArrayRow arrayRow = (ArrayRow) ((LogErrorQueue) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$executor).acquire();
        if (arrayRow == null) {
            return new ArrayRow(this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        }
        arrayRow.variable = null;
        ArrayLinkedVariables arrayLinkedVariables = arrayRow.variables;
        arrayLinkedVariables.mHead = -1;
        arrayLinkedVariables.mLast = -1;
        arrayLinkedVariables.mDidFillOnce = false;
        arrayLinkedVariables.currentSize = 0;
        arrayRow.constantValue = 0.0f;
        arrayRow.isSimpleDefinition = false;
        return arrayRow;
    }

    public final SolverVariable createSlackVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable$ar$edu = acquireSolverVariable$ar$edu(3);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable$ar$edu.id = i;
        ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i] = acquireSolverVariable$ar$edu;
        return acquireSolverVariable$ar$edu;
    }

    public final void enforceBFS$ar$ds(Goal goal) {
        int i = 0;
        while (true) {
            if (i >= this.mNumRows) {
                break;
            }
            if (this.mRows[i].variable.mType$ar$edu != 1 && this.mRows[i].constantValue < 0.0f) {
                while (true) {
                    float f = Float.MAX_VALUE;
                    int i2 = -1;
                    int i3 = -1;
                    int i4 = 0;
                    for (int i5 = 0; i5 < this.mNumRows; i5++) {
                        ArrayRow arrayRow = this.mRows[i5];
                        if (arrayRow.variable.mType$ar$edu != 1 && arrayRow.constantValue < 0.0f) {
                            for (int i6 = 1; i6 < this.mNumColumns; i6++) {
                                SolverVariable solverVariable = ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i6];
                                float f2 = arrayRow.variables.get(solverVariable);
                                if (f2 > 0.0f) {
                                    for (int i7 = 0; i7 < 6; i7++) {
                                        float f3 = solverVariable.strengthVector[i7] / f2;
                                        if ((f3 < f && i7 == i4) || i7 > i4) {
                                            f = f3;
                                            i2 = i5;
                                            i3 = i6;
                                            i4 = i7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (i2 == -1) {
                        break;
                    }
                    ArrayRow arrayRow2 = this.mRows[i2];
                    arrayRow2.variable.definitionId = -1;
                    arrayRow2.pivot(((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i3]);
                    arrayRow2.variable.definitionId = i2;
                    for (int i8 = 0; i8 < this.mNumRows; i8++) {
                        this.mRows[i8].updateRowWithEquation$ar$ds(arrayRow2);
                    }
                    goal.updateFromSystem(this);
                }
            } else {
                i++;
            }
        }
        for (int i9 = 0; i9 < this.mNumRows; i9++) {
            if (this.mRows[i9].variable.mType$ar$edu != 1 && this.mRows[i9].constantValue < 0.0f) {
                return;
            }
        }
    }

    public final void reset() {
        ExecutionList.RunnableExecutorPair runnableExecutorPair;
        int i = 0;
        while (true) {
            runnableExecutorPair = this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            SolverVariable[] solverVariableArr = (SolverVariable[]) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next;
            if (i >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i++;
        }
        Object obj = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable;
        SolverVariable[] solverVariableArr2 = this.mPoolVariables;
        int i2 = this.mPoolVariablesCount;
        int length = solverVariableArr2.length;
        if (i2 > length) {
            i2 = length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable solverVariable2 = solverVariableArr2[i3];
            LogErrorQueue logErrorQueue = (LogErrorQueue) obj;
            int i4 = logErrorQueue.overflowErrorCount;
            if (i4 < 256) {
                ((Object[]) logErrorQueue.LogErrorQueue$ar$errorMap)[i4] = solverVariable2;
                logErrorQueue.overflowErrorCount = i4 + 1;
            }
        }
        this.mPoolVariablesCount = 0;
        Arrays.fill((Object[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next, (Object) null);
        this.mVariablesID = 0;
        this.mGoal.variables.clear();
        this.mNumColumns = 1;
        for (int i5 = 0; i5 < this.mNumRows; i5++) {
            this.mRows[i5].used = false;
        }
        releaseRows();
        this.mNumRows = 0;
    }
}
