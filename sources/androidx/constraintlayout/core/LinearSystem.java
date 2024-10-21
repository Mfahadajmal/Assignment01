package androidx.constraintlayout.core;

import androidx.constraintlayout.core.PriorityGoalRow;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import com.google.common.util.concurrent.ExecutionList;
import com.google.firebase.encoders.proto.AtProtobuf;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static final boolean SIMPLIFY_SYNONYMS = true;
    public static final boolean SKIP_COLUMNS = true;
    public static final boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public final ExecutionList.RunnableExecutorPair mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging;
    private final ArrayRow mGoal$ar$class_merging;
    ArrayRow[] mRows;
    private ArrayRow mTempGoal$ar$class_merging;
    private int mPoolSize = 1000;
    public boolean hasSimpleDefinition = false;
    int mVariablesID = 0;
    private int mTableSize = 32;
    private int mMaxColumns = 32;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    private boolean[] mAlreadyTestedCandidates = new boolean[32];
    int mNumColumns = 1;
    int mNumRows = 0;
    private int mMaxRows = 32;
    private SolverVariable[] mPoolVariables = new SolverVariable[1000];
    private int mPoolVariablesCount = 0;

    public LinearSystem() {
        this.mRows = null;
        this.mRows = new ArrayRow[32];
        releaseRows();
        ExecutionList.RunnableExecutorPair runnableExecutorPair = new ExecutionList.RunnableExecutorPair();
        this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging = runnableExecutorPair;
        this.mGoal$ar$class_merging = new PriorityGoalRow(runnableExecutorPair);
        this.mTempGoal$ar$class_merging = new ArrayRow(runnableExecutorPair);
    }

    private final SolverVariable acquireSolverVariable$ar$edu$61e0c7a3_0(int i, String str) {
        SolverVariable solverVariable = (SolverVariable) ((AtProtobuf) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable).acquire();
        if (solverVariable == null) {
            solverVariable = new SolverVariable(i);
            solverVariable.mType$ar$edu$e789bd0d_0 = i;
        } else {
            solverVariable.reset();
            solverVariable.mType$ar$edu$e789bd0d_0 = i;
        }
        int i2 = this.mPoolVariablesCount;
        int i3 = this.mPoolSize;
        if (i2 >= i3) {
            int i4 = i3 + i3;
            this.mPoolSize = i4;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i4);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i5 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i5 + 1;
        solverVariableArr[i5] = solverVariable;
        return solverVariable;
    }

    private final void addRow(ArrayRow arrayRow) {
        int i;
        if (arrayRow.mIsSimpleDefinition) {
            arrayRow.mVariable.setFinalValue(this, arrayRow.mConstantValue);
        } else {
            ArrayRow[] arrayRowArr = this.mRows;
            int i2 = this.mNumRows;
            arrayRowArr[i2] = arrayRow;
            SolverVariable solverVariable = arrayRow.mVariable;
            solverVariable.mDefinitionId = i2;
            this.mNumRows = i2 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        }
        if (this.hasSimpleDefinition) {
            int i3 = 0;
            while (i3 < this.mNumRows) {
                if (this.mRows[i3] == null) {
                    System.out.println("WTF");
                }
                ArrayRow arrayRow2 = this.mRows[i3];
                if (arrayRow2 != null && arrayRow2.mIsSimpleDefinition) {
                    arrayRow2.mVariable.setFinalValue(this, arrayRow2.mConstantValue);
                    ((AtProtobuf) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$executor).release$ar$ds$843266ac_0(arrayRow2);
                    this.mRows[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.mNumRows;
                        if (i4 >= i) {
                            break;
                        }
                        ArrayRow[] arrayRowArr2 = this.mRows;
                        int i6 = i4 - 1;
                        ArrayRow arrayRow3 = arrayRowArr2[i4];
                        arrayRowArr2[i6] = arrayRow3;
                        SolverVariable solverVariable2 = arrayRow3.mVariable;
                        if (solverVariable2.mDefinitionId == i4) {
                            solverVariable2.mDefinitionId = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        this.mRows[i5] = null;
                    }
                    this.mNumRows = i - 1;
                    i3--;
                }
                i3++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    private final void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            arrayRow.mVariable.computedValue = arrayRow.mConstantValue;
        }
    }

    public static final int getObjectVariableValue$ar$ds$8c6d81d4_0(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).mSolverVariable;
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    private final void increaseTableSize() {
        int i = this.mTableSize;
        int i2 = i + i;
        this.mTableSize = i2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i2);
        this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next = (SolverVariable[]) Arrays.copyOf((Object[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next, this.mTableSize);
        int i3 = this.mTableSize;
        this.mAlreadyTestedCandidates = new boolean[i3];
        this.mMaxColumns = i3;
        this.mMaxRows = i3;
    }

    private final void optimize$ar$class_merging$ar$ds(ArrayRow arrayRow) {
        for (int i = 0; i < this.mNumColumns; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2++;
            int i3 = this.mNumColumns;
            if (i2 >= i3 + i3) {
                return;
            }
            if (arrayRow.getKey() != null) {
                this.mAlreadyTestedCandidates[arrayRow.getKey().id] = true;
            }
            SolverVariable pivotCandidate$ar$ds = arrayRow.getPivotCandidate$ar$ds(this.mAlreadyTestedCandidates);
            if (pivotCandidate$ar$ds != null) {
                boolean[] zArr = this.mAlreadyTestedCandidates;
                int i4 = pivotCandidate$ar$ds.id;
                if (zArr[i4]) {
                    return;
                } else {
                    zArr[i4] = true;
                }
            }
            if (pivotCandidate$ar$ds != null) {
                float f = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.mNumRows; i6++) {
                    ArrayRow arrayRow2 = this.mRows[i6];
                    if (arrayRow2.mVariable.mType$ar$edu$e789bd0d_0 != 1 && !arrayRow2.mIsSimpleDefinition && arrayRow2.variables$ar$class_merging.contains(pivotCandidate$ar$ds)) {
                        float f2 = arrayRow2.variables$ar$class_merging.get(pivotCandidate$ar$ds);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow2.mConstantValue) / f2;
                            if (f3 < f) {
                                i5 = i6;
                                f = f3;
                            }
                        }
                    }
                }
                if (i5 >= 0) {
                    ArrayRow arrayRow3 = this.mRows[i5];
                    arrayRow3.mVariable.mDefinitionId = -1;
                    arrayRow3.pivot(pivotCandidate$ar$ds);
                    SolverVariable solverVariable = arrayRow3.mVariable;
                    solverVariable.mDefinitionId = i5;
                    solverVariable.updateReferencesWithNewDefinition(this, arrayRow3);
                }
            } else {
                z = true;
            }
        }
    }

    private final void releaseRows() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            if (arrayRow != null) {
                ((AtProtobuf) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$executor).release$ar$ds$843266ac_0(arrayRow);
            }
            this.mRows[i] = null;
        }
    }

    public final void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        ArrayRow createRow = createRow();
        if (solverVariable2 == solverVariable3) {
            createRow.variables$ar$class_merging.put(solverVariable, 1.0f);
            createRow.variables$ar$class_merging.put(solverVariable4, 1.0f);
            createRow.variables$ar$class_merging.put(solverVariable2, -2.0f);
        } else if (f == 0.5f) {
            createRow.variables$ar$class_merging.put(solverVariable, 1.0f);
            createRow.variables$ar$class_merging.put(solverVariable2, -1.0f);
            createRow.variables$ar$class_merging.put(solverVariable3, -1.0f);
            createRow.variables$ar$class_merging.put(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                createRow.mConstantValue = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            createRow.variables$ar$class_merging.put(solverVariable, -1.0f);
            createRow.variables$ar$class_merging.put(solverVariable2, 1.0f);
            createRow.mConstantValue = i;
        } else if (f >= 1.0f) {
            createRow.variables$ar$class_merging.put(solverVariable4, -1.0f);
            createRow.variables$ar$class_merging.put(solverVariable3, 1.0f);
            createRow.mConstantValue = -i2;
        } else {
            float f2 = 1.0f - f;
            createRow.variables$ar$class_merging.put(solverVariable, f2);
            createRow.variables$ar$class_merging.put(solverVariable2, -f2);
            createRow.variables$ar$class_merging.put(solverVariable3, -f);
            createRow.variables$ar$class_merging.put(solverVariable4, f);
            if (i > 0 || i2 > 0) {
                createRow.mConstantValue = ((-i) * f2) + (i2 * f);
            }
        }
        if (i3 != 8) {
            createRow.addError$ar$ds$bebff777_0(this, i3);
        }
        addConstraint(createRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0191 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addConstraint(androidx.constraintlayout.core.ArrayRow r17) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.LinearSystem.addConstraint(androidx.constraintlayout.core.ArrayRow):void");
    }

    public final void addEquality(SolverVariable solverVariable, int i) {
        if (solverVariable.mDefinitionId == -1) {
            solverVariable.setFinalValue(this, i);
            for (int i2 = 0; i2 < this.mVariablesID + 1; i2++) {
                SolverVariable solverVariable2 = ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i2];
            }
            return;
        }
        int i3 = solverVariable.mDefinitionId;
        if (i3 != -1) {
            ArrayRow arrayRow = this.mRows[i3];
            if (arrayRow.mIsSimpleDefinition) {
                arrayRow.mConstantValue = i;
                return;
            }
            if (arrayRow.variables$ar$class_merging.getCurrentSize() == 0) {
                arrayRow.mIsSimpleDefinition = true;
                arrayRow.mConstantValue = i;
                return;
            }
            ArrayRow createRow = createRow();
            if (i < 0) {
                createRow.mConstantValue = -i;
                createRow.variables$ar$class_merging.put(solverVariable, 1.0f);
            } else {
                createRow.mConstantValue = i;
                createRow.variables$ar$class_merging.put(solverVariable, -1.0f);
            }
            addConstraint(createRow);
            return;
        }
        float f = i;
        ArrayRow createRow2 = createRow();
        createRow2.mVariable = solverVariable;
        solverVariable.computedValue = f;
        createRow2.mConstantValue = f;
        createRow2.mIsSimpleDefinition = true;
        addConstraint(createRow2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addEquality$ar$ds$e82a2b65_0(androidx.constraintlayout.core.SolverVariable r6, androidx.constraintlayout.core.SolverVariable r7, int r8, int r9) {
        /*
            r5 = this;
            r0 = 8
            if (r9 != r0) goto L17
            boolean r9 = r7.isFinalValue
            if (r9 == 0) goto L16
            int r9 = r6.mDefinitionId
            r1 = -1
            if (r9 == r1) goto Le
            goto L16
        Le:
            float r7 = r7.computedValue
            float r8 = (float) r8
            float r7 = r7 + r8
            r6.setFinalValue(r5, r7)
            return
        L16:
            r9 = r0
        L17:
            androidx.constraintlayout.core.ArrayRow r1 = r5.createRow()
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r8 == 0) goto L38
            if (r8 >= 0) goto L26
            int r8 = -r8
            r4 = 1
            goto L27
        L26:
            r4 = 0
        L27:
            float r8 = (float) r8
            r1.mConstantValue = r8
            if (r4 != 0) goto L2d
            goto L38
        L2d:
            androidx.constraintlayout.core.ArrayLinkedVariables r8 = r1.variables$ar$class_merging
            r8.put(r6, r2)
            androidx.constraintlayout.core.ArrayLinkedVariables r6 = r1.variables$ar$class_merging
            r6.put(r7, r3)
            goto L42
        L38:
            androidx.constraintlayout.core.ArrayLinkedVariables r8 = r1.variables$ar$class_merging
            r8.put(r6, r3)
            androidx.constraintlayout.core.ArrayLinkedVariables r6 = r1.variables$ar$class_merging
            r6.put(r7, r2)
        L42:
            if (r9 == r0) goto L47
            r1.addError$ar$ds$bebff777_0(r5, r9)
        L47:
            r5.addConstraint(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.LinearSystem.addEquality$ar$ds$e82a2b65_0(androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.SolverVariable, int, int):void");
    }

    public final void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan$ar$ds$cd721f52_0(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            addSingleError(createRow, (int) (-createRow.variables$ar$class_merging.get(createSlackVariable)), i2);
        }
        addConstraint(createRow);
    }

    public final void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan$ar$ds$f5420b2b_0(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            addSingleError(createRow, (int) (-createRow.variables$ar$class_merging.get(createSlackVariable)), i2);
        }
        addConstraint(createRow);
    }

    public final void addRatio$ar$ds(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio$ar$ds$797f96c3_0(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        addConstraint(createRow);
    }

    final void addSingleError(ArrayRow arrayRow, int i, int i2) {
        arrayRow.variables$ar$class_merging.put(createErrorVariable(i2, null), i);
    }

    public final SolverVariable createErrorVariable(int i, String str) {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable$ar$edu$61e0c7a3_0 = acquireSolverVariable$ar$edu$61e0c7a3_0(4, str);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        acquireSolverVariable$ar$edu$61e0c7a3_0.id = i2;
        acquireSolverVariable$ar$edu$61e0c7a3_0.strength = i;
        ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i2] = acquireSolverVariable$ar$edu$61e0c7a3_0;
        PriorityGoalRow priorityGoalRow = (PriorityGoalRow) this.mGoal$ar$class_merging;
        PriorityGoalRow.GoalVariableAccessor goalVariableAccessor = priorityGoalRow.mAccessor;
        goalVariableAccessor.mVariable = acquireSolverVariable$ar$edu$61e0c7a3_0;
        Arrays.fill(goalVariableAccessor.mVariable.mGoalStrengthVector, 0.0f);
        acquireSolverVariable$ar$edu$61e0c7a3_0.mGoalStrengthVector[acquireSolverVariable$ar$edu$61e0c7a3_0.strength] = 1.0f;
        priorityGoalRow.addToGoal(acquireSolverVariable$ar$edu$61e0c7a3_0);
        return acquireSolverVariable$ar$edu$61e0c7a3_0;
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
            constraintAnchor.resetSolverVariable$ar$ds$cb2131bd_0();
            solverVariable = constraintAnchor.mSolverVariable;
        }
        int i = solverVariable.id;
        if (i != -1) {
            if (i > this.mVariablesID || ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i] == null) {
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
        solverVariable.mType$ar$edu$e789bd0d_0 = 1;
        ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[this.mVariablesID] = solverVariable;
        return solverVariable;
    }

    public final ArrayRow createRow() {
        ArrayRow arrayRow = (ArrayRow) ((AtProtobuf) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$executor).acquire();
        if (arrayRow == null) {
            ArrayRow arrayRow2 = new ArrayRow(this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging);
            ARRAY_ROW_CREATION++;
            arrayRow = arrayRow2;
        } else {
            arrayRow.mVariable = null;
            arrayRow.variables$ar$class_merging.clear();
            arrayRow.mConstantValue = 0.0f;
            arrayRow.mIsSimpleDefinition = false;
        }
        SolverVariable.sUniqueErrorId++;
        return arrayRow;
    }

    public final SolverVariable createSlackVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable$ar$edu$61e0c7a3_0 = acquireSolverVariable$ar$edu$61e0c7a3_0(3, null);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable$ar$edu$61e0c7a3_0.id = i;
        ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i] = acquireSolverVariable$ar$edu$61e0c7a3_0;
        return acquireSolverVariable$ar$edu$61e0c7a3_0;
    }

    public final void minimize() {
        if (this.mGoal$ar$class_merging.isEmpty()) {
            computeValues();
            return;
        }
        if (this.newgraphOptimizer) {
            for (int i = 0; i < this.mNumRows; i++) {
                if (!this.mRows[i].mIsSimpleDefinition) {
                    minimizeGoal$ar$class_merging(this.mGoal$ar$class_merging);
                    return;
                }
            }
            computeValues();
            return;
        }
        minimizeGoal$ar$class_merging(this.mGoal$ar$class_merging);
    }

    final void minimizeGoal$ar$class_merging(ArrayRow arrayRow) {
        boolean z;
        boolean z2;
        int i = 0;
        while (true) {
            if (i >= this.mNumRows) {
                break;
            }
            if (this.mRows[i].mVariable.mType$ar$edu$e789bd0d_0 != 1) {
                float f = 0.0f;
                if (this.mRows[i].mConstantValue < 0.0f) {
                    boolean z3 = false;
                    int i2 = 0;
                    while (!z3) {
                        i2++;
                        float f2 = Float.MAX_VALUE;
                        int i3 = -1;
                        int i4 = -1;
                        int i5 = 0;
                        int i6 = 0;
                        while (i5 < this.mNumRows) {
                            ArrayRow arrayRow2 = this.mRows[i5];
                            if (arrayRow2.mVariable.mType$ar$edu$e789bd0d_0 != 1 && !arrayRow2.mIsSimpleDefinition && arrayRow2.mConstantValue < f) {
                                int currentSize = arrayRow2.variables$ar$class_merging.getCurrentSize();
                                int i7 = 0;
                                while (i7 < currentSize) {
                                    SolverVariable variable = arrayRow2.variables$ar$class_merging.getVariable(i7);
                                    float f3 = arrayRow2.variables$ar$class_merging.get(variable);
                                    if (f3 > f) {
                                        for (int i8 = 0; i8 < 9; i8++) {
                                            float f4 = variable.mStrengthVector[i8] / f3;
                                            if ((f4 < f2 && i8 == i6) || i8 > i6) {
                                                i4 = variable.id;
                                                i6 = i8;
                                                f2 = f4;
                                                i3 = i5;
                                            }
                                        }
                                    }
                                    i7++;
                                    f = 0.0f;
                                }
                            }
                            i5++;
                            f = 0.0f;
                        }
                        if (i3 != -1) {
                            ArrayRow arrayRow3 = this.mRows[i3];
                            arrayRow3.mVariable.mDefinitionId = -1;
                            arrayRow3.pivot(((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i4]);
                            SolverVariable solverVariable = arrayRow3.mVariable;
                            solverVariable.mDefinitionId = i3;
                            solverVariable.updateReferencesWithNewDefinition(this, arrayRow3);
                            z = false;
                        } else {
                            z = true;
                        }
                        if (i2 > this.mNumColumns / 2) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        z3 = (!z2) | z;
                        f = 0.0f;
                    }
                }
            }
            i++;
        }
        optimize$ar$class_merging$ar$ds(arrayRow);
        computeValues();
    }

    public final void reset() {
        ExecutionList.RunnableExecutorPair runnableExecutorPair;
        int i = 0;
        while (true) {
            runnableExecutorPair = this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging;
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
            AtProtobuf atProtobuf = (AtProtobuf) obj;
            int i4 = atProtobuf.tag;
            if (i4 < 256) {
                ((Object[]) atProtobuf.AtProtobuf$ar$intEncoding)[i4] = solverVariable2;
                atProtobuf.tag = i4 + 1;
            }
        }
        this.mPoolVariablesCount = 0;
        Arrays.fill((Object[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next, (Object) null);
        this.mVariablesID = 0;
        PriorityGoalRow priorityGoalRow = (PriorityGoalRow) this.mGoal$ar$class_merging;
        priorityGoalRow.mNumGoals = 0;
        priorityGoalRow.mConstantValue = 0.0f;
        this.mNumColumns = 1;
        for (int i5 = 0; i5 < this.mNumRows; i5++) {
            ArrayRow arrayRow = this.mRows[i5];
            if (arrayRow != null) {
                arrayRow.mUsed = false;
            }
        }
        releaseRows();
        this.mNumRows = 0;
        this.mTempGoal$ar$class_merging = new ArrayRow(this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging);
    }
}
