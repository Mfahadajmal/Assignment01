package androidx.constraintlayout.core;

import com.google.common.util.concurrent.ExecutionList;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayLinkedVariables {
    private static final float sEpsilon = 0.001f;
    protected final ExecutionList.RunnableExecutorPair mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging;
    private final ArrayRow mRow;
    int mCurrentSize = 0;
    private int mRowSize = 8;
    private int[] mArrayIndices = new int[8];
    private int[] mArrayNextIndices = new int[8];
    private float[] mArrayValues = new float[8];
    private int mHead = -1;
    private int mLast = -1;
    private boolean mDidFillOnce = false;

    public ArrayLinkedVariables(ArrayRow arrayRow, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.mRow = arrayRow;
        this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging = runnableExecutorPair;
    }

    public final void add(SolverVariable solverVariable, float f, boolean z) {
        if (f <= -0.001f || f >= sEpsilon) {
            int i = this.mHead;
            if (i == -1) {
                this.mHead = 0;
                this.mArrayValues[0] = f;
                this.mArrayIndices[0] = solverVariable.id;
                this.mArrayNextIndices[0] = -1;
                solverVariable.usageInRowCount++;
                solverVariable.addToRow(this.mRow);
                this.mCurrentSize++;
                if (!this.mDidFillOnce) {
                    int i2 = this.mLast + 1;
                    this.mLast = i2;
                    int length = this.mArrayIndices.length;
                    if (i2 >= length) {
                        this.mDidFillOnce = true;
                        this.mLast = length - 1;
                        return;
                    }
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.mCurrentSize; i4++) {
                int i5 = this.mArrayIndices[i];
                int i6 = solverVariable.id;
                if (i5 == i6) {
                    float[] fArr = this.mArrayValues;
                    float f2 = fArr[i] + f;
                    if (f2 > -0.001f && f2 < sEpsilon) {
                        f2 = 0.0f;
                    }
                    fArr[i] = f2;
                    if (f2 == 0.0f) {
                        if (i == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i];
                        } else {
                            int[] iArr = this.mArrayNextIndices;
                            iArr[i3] = iArr[i];
                        }
                        if (z) {
                            solverVariable.removeFromRow(this.mRow);
                        }
                        if (this.mDidFillOnce) {
                            this.mLast = i;
                        }
                        solverVariable.usageInRowCount--;
                        this.mCurrentSize--;
                        return;
                    }
                    return;
                }
                if (i5 < i6) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
            }
            int i7 = this.mLast;
            int i8 = i7 + 1;
            if (this.mDidFillOnce) {
                int[] iArr2 = this.mArrayIndices;
                if (iArr2[i7] != -1) {
                    i7 = iArr2.length;
                }
            } else {
                i7 = i8;
            }
            int length2 = this.mArrayIndices.length;
            if (i7 >= length2 && this.mCurrentSize < length2) {
                int i9 = 0;
                while (true) {
                    int[] iArr3 = this.mArrayIndices;
                    if (i9 >= iArr3.length) {
                        break;
                    }
                    if (iArr3[i9] == -1) {
                        i7 = i9;
                        break;
                    }
                    i9++;
                }
            }
            int length3 = this.mArrayIndices.length;
            if (i7 >= length3) {
                int i10 = this.mRowSize;
                int i11 = i10 + i10;
                this.mRowSize = i11;
                this.mDidFillOnce = false;
                this.mLast = length3 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, i11);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.mRowSize);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.mRowSize);
                i7 = length3;
            }
            this.mArrayIndices[i7] = solverVariable.id;
            this.mArrayValues[i7] = f;
            if (i3 != -1) {
                int[] iArr4 = this.mArrayNextIndices;
                iArr4[i7] = iArr4[i3];
                iArr4[i3] = i7;
            } else {
                this.mArrayNextIndices[i7] = this.mHead;
                this.mHead = i7;
            }
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.mCurrentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            int i12 = this.mLast;
            int length4 = this.mArrayIndices.length;
            if (i12 >= length4) {
                this.mDidFillOnce = true;
                this.mLast = length4 - 1;
            }
        }
    }

    public final void clear() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.mCurrentSize; i2++) {
            SolverVariable solverVariable = ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[this.mArrayIndices[i]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
            i = this.mArrayNextIndices[i];
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.mCurrentSize = 0;
    }

    public final boolean contains(SolverVariable solverVariable) {
        int i = this.mHead;
        if (i != -1) {
            for (int i2 = 0; i != -1 && i2 < this.mCurrentSize; i2++) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    return true;
                }
                i = this.mArrayNextIndices[i];
            }
        }
        return false;
    }

    public final void divideByAmount(float f) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.mCurrentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
        }
    }

    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.mCurrentSize; i2++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    public final int getCurrentSize() {
        return this.mCurrentSize;
    }

    public final SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.mCurrentSize; i3++) {
            if (i3 == i) {
                return ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return null;
    }

    public final float getVariableValue(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.mCurrentSize; i3++) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return 0.0f;
    }

    public final void invert() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.mCurrentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = -fArr[i];
            i = this.mArrayNextIndices[i];
        }
    }

    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f;
            this.mArrayIndices[0] = solverVariable.id;
            this.mArrayNextIndices[0] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.mCurrentSize++;
            if (!this.mDidFillOnce) {
                int i2 = this.mLast + 1;
                this.mLast = i2;
                int length = this.mArrayIndices.length;
                if (i2 >= length) {
                    this.mDidFillOnce = true;
                    this.mLast = length - 1;
                    return;
                }
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.mCurrentSize; i4++) {
            int i5 = this.mArrayIndices[i];
            int i6 = solverVariable.id;
            if (i5 == i6) {
                this.mArrayValues[i] = f;
                return;
            }
            if (i5 < i6) {
                i3 = i;
            }
            i = this.mArrayNextIndices[i];
        }
        int i7 = this.mLast;
        int i8 = i7 + 1;
        if (this.mDidFillOnce) {
            int[] iArr = this.mArrayIndices;
            if (iArr[i7] != -1) {
                i7 = iArr.length;
            }
        } else {
            i7 = i8;
        }
        int length2 = this.mArrayIndices.length;
        if (i7 >= length2 && this.mCurrentSize < length2) {
            int i9 = 0;
            while (true) {
                int[] iArr2 = this.mArrayIndices;
                if (i9 >= iArr2.length) {
                    break;
                }
                if (iArr2[i9] == -1) {
                    i7 = i9;
                    break;
                }
                i9++;
            }
        }
        int length3 = this.mArrayIndices.length;
        if (i7 >= length3) {
            int i10 = this.mRowSize;
            int i11 = i10 + i10;
            this.mRowSize = i11;
            this.mDidFillOnce = false;
            this.mLast = length3 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, i11);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.mRowSize);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.mRowSize);
            i7 = length3;
        }
        this.mArrayIndices[i7] = solverVariable.id;
        this.mArrayValues[i7] = f;
        if (i3 != -1) {
            int[] iArr3 = this.mArrayNextIndices;
            iArr3[i7] = iArr3[i3];
            iArr3[i3] = i7;
        } else {
            this.mArrayNextIndices[i7] = this.mHead;
            this.mHead = i7;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        int i12 = this.mCurrentSize + 1;
        this.mCurrentSize = i12;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        int length4 = this.mArrayIndices.length;
        if (i12 >= length4) {
            this.mDidFillOnce = true;
        }
        if (this.mLast >= length4) {
            this.mDidFillOnce = true;
            this.mLast = length4 - 1;
        }
    }

    public final float remove(SolverVariable solverVariable, boolean z) {
        int i = this.mHead;
        if (i != -1) {
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.mCurrentSize) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    if (i == this.mHead) {
                        this.mHead = this.mArrayNextIndices[i];
                    } else {
                        int[] iArr = this.mArrayNextIndices;
                        iArr[i3] = iArr[i];
                    }
                    if (z) {
                        solverVariable.removeFromRow(this.mRow);
                    }
                    solverVariable.usageInRowCount--;
                    this.mCurrentSize--;
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
        for (int i2 = 0; i != -1 && i2 < this.mCurrentSize; i2++) {
            String str2 = str.concat(" -> ") + this.mArrayValues[i] + " : ";
            SolverVariable solverVariable = ((SolverVariable[]) this.mCache$ar$class_merging$b351702c_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next)[this.mArrayIndices[i]];
            Objects.toString(solverVariable);
            String valueOf = String.valueOf(solverVariable);
            i = this.mArrayNextIndices[i];
            str = str2.concat(valueOf);
        }
        return str;
    }

    public final float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.mVariable);
        remove(arrayRow.mVariable, z);
        ArrayLinkedVariables arrayLinkedVariables = arrayRow.variables$ar$class_merging;
        int currentSize = arrayLinkedVariables.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable variable = arrayLinkedVariables.getVariable(i);
            add(variable, arrayLinkedVariables.get(variable) * f, z);
        }
        return f;
    }
}
