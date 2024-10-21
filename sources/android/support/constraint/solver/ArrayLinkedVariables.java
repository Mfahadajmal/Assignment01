package android.support.constraint.solver;

import com.google.common.util.concurrent.ExecutionList;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayLinkedVariables {
    public final ExecutionList.RunnableExecutorPair mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final ArrayRow mRow;
    public int currentSize = 0;
    private int ROW_SIZE = 8;
    public int[] mArrayIndices = new int[8];
    public int[] mArrayNextIndices = new int[8];
    public float[] mArrayValues = new float[8];
    public int mHead = -1;
    public int mLast = -1;
    public boolean mDidFillOnce = false;

    public ArrayLinkedVariables(ArrayRow arrayRow, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.mRow = arrayRow;
        this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = runnableExecutorPair;
    }

    public final void add(SolverVariable solverVariable, float f) {
        if (f != 0.0f) {
            int i = this.mHead;
            if (i == -1) {
                this.mHead = 0;
                this.mArrayValues[0] = f;
                this.mArrayIndices[0] = solverVariable.id;
                this.mArrayNextIndices[0] = -1;
                this.currentSize++;
                if (!this.mDidFillOnce) {
                    this.mLast++;
                    return;
                }
                return;
            }
            int i2 = -1;
            for (int i3 = 0; i != -1 && i3 < this.currentSize; i3++) {
                int i4 = this.mArrayIndices[i];
                int i5 = solverVariable.id;
                if (i4 == i5) {
                    float[] fArr = this.mArrayValues;
                    float f2 = fArr[i] + f;
                    fArr[i] = f2;
                    if (f2 == 0.0f) {
                        if (i == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i];
                        } else {
                            int[] iArr = this.mArrayNextIndices;
                            iArr[i2] = iArr[i];
                        }
                        ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i4].removeClientEquation(this.mRow);
                        if (this.mDidFillOnce) {
                            this.mLast = i;
                        }
                        this.currentSize--;
                        return;
                    }
                    return;
                }
                if (i4 < i5) {
                    i2 = i;
                }
                i = this.mArrayNextIndices[i];
            }
            int i6 = this.mLast;
            int i7 = i6 + 1;
            if (this.mDidFillOnce) {
                int[] iArr2 = this.mArrayIndices;
                if (iArr2[i6] != -1) {
                    i6 = iArr2.length;
                }
            } else {
                i6 = i7;
            }
            int length = this.mArrayIndices.length;
            if (i6 >= length && this.currentSize < length) {
                int i8 = 0;
                while (true) {
                    int[] iArr3 = this.mArrayIndices;
                    if (i8 >= iArr3.length) {
                        break;
                    }
                    if (iArr3[i8] == -1) {
                        i6 = i8;
                        break;
                    }
                    i8++;
                }
            }
            int length2 = this.mArrayIndices.length;
            if (i6 >= length2) {
                int i9 = this.ROW_SIZE;
                int i10 = i9 + i9;
                this.ROW_SIZE = i10;
                this.mDidFillOnce = false;
                this.mLast = length2 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, i10);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
                i6 = length2;
            }
            int[] iArr4 = this.mArrayIndices;
            iArr4[i6] = solverVariable.id;
            this.mArrayValues[i6] = f;
            if (i2 != -1) {
                int[] iArr5 = this.mArrayNextIndices;
                iArr5[i6] = iArr5[i2];
                iArr5[i2] = i6;
            } else {
                this.mArrayNextIndices[i6] = this.mHead;
                this.mHead = i6;
            }
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            int i11 = this.mLast;
            int length3 = iArr4.length;
            if (i11 >= length3) {
                this.mDidFillOnce = true;
                this.mLast = length3 - 1;
            }
        }
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 == i) {
                return ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final float getVariableValue(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return 0.0f;
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable);
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f;
            this.mArrayIndices[0] = solverVariable.id;
            this.mArrayNextIndices[0] = -1;
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
                return;
            }
            return;
        }
        int i2 = -1;
        for (int i3 = 0; i != -1 && i3 < this.currentSize; i3++) {
            int i4 = this.mArrayIndices[i];
            int i5 = solverVariable.id;
            if (i4 == i5) {
                this.mArrayValues[i] = f;
                return;
            }
            if (i4 < i5) {
                i2 = i;
            }
            i = this.mArrayNextIndices[i];
        }
        int i6 = this.mLast;
        int i7 = i6 + 1;
        if (this.mDidFillOnce) {
            int[] iArr = this.mArrayIndices;
            if (iArr[i6] != -1) {
                i6 = iArr.length;
            }
        } else {
            i6 = i7;
        }
        int length = this.mArrayIndices.length;
        if (i6 >= length && this.currentSize < length) {
            int i8 = 0;
            while (true) {
                int[] iArr2 = this.mArrayIndices;
                if (i8 >= iArr2.length) {
                    break;
                }
                if (iArr2[i8] == -1) {
                    i6 = i8;
                    break;
                }
                i8++;
            }
        }
        int length2 = this.mArrayIndices.length;
        if (i6 >= length2) {
            int i9 = this.ROW_SIZE;
            int i10 = i9 + i9;
            this.ROW_SIZE = i10;
            this.mDidFillOnce = false;
            this.mLast = length2 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, i10);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            i6 = length2;
        }
        int[] iArr3 = this.mArrayIndices;
        iArr3[i6] = solverVariable.id;
        this.mArrayValues[i6] = f;
        if (i2 != -1) {
            int[] iArr4 = this.mArrayNextIndices;
            iArr4[i6] = iArr4[i2];
            iArr4[i2] = i6;
        } else {
            this.mArrayNextIndices[i6] = this.mHead;
            this.mHead = i6;
        }
        int i11 = this.currentSize + 1;
        this.currentSize = i11;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        if (i11 >= iArr3.length) {
            this.mDidFillOnce = true;
        }
    }

    public final float remove(SolverVariable solverVariable) {
        int i = this.mHead;
        if (i != -1) {
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.currentSize) {
                int i4 = this.mArrayIndices[i];
                if (i4 == solverVariable.id) {
                    if (i == this.mHead) {
                        this.mHead = this.mArrayNextIndices[i];
                    } else {
                        int[] iArr = this.mArrayNextIndices;
                        iArr[i3] = iArr[i];
                    }
                    ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[i4].removeClientEquation(this.mRow);
                    this.currentSize--;
                    this.mArrayIndices[i] = -1;
                    if (this.mDidFillOnce) {
                        this.mLast = i;
                    }
                    return this.mArrayValues[i];
                }
                i2++;
                i3 = i;
                i = this.mArrayNextIndices[i];
            }
            return 0.0f;
        }
        return 0.0f;
    }

    public final String toString() {
        int i = this.mHead;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            String str2 = str.concat(" -> ") + this.mArrayValues[i] + " : ";
            SolverVariable solverVariable = ((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[this.mArrayIndices[i]];
            Objects.toString(solverVariable);
            String valueOf = String.valueOf(solverVariable);
            i = this.mArrayNextIndices[i];
            str = str2.concat(valueOf);
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateFromRow(ArrayRow arrayRow, ArrayRow arrayRow2) {
        int i = this.mHead;
        while (true) {
            for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
                int i3 = this.mArrayIndices[i];
                SolverVariable solverVariable = arrayRow2.variable;
                if (i3 == solverVariable.id) {
                    float f = this.mArrayValues[i];
                    remove(solverVariable);
                    ArrayLinkedVariables arrayLinkedVariables = arrayRow2.variables;
                    int i4 = arrayLinkedVariables.mHead;
                    for (int i5 = 0; i4 != -1 && i5 < arrayLinkedVariables.currentSize; i5++) {
                        add(((SolverVariable[]) this.mCache$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[arrayLinkedVariables.mArrayIndices[i4]], arrayLinkedVariables.mArrayValues[i4] * f);
                        i4 = arrayLinkedVariables.mArrayNextIndices[i4];
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    arrayRow2.variable.removeClientEquation(arrayRow);
                    i = this.mHead;
                } else {
                    i = this.mArrayNextIndices[i];
                }
            }
            return;
        }
    }
}
