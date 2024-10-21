package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.core.os.UserManagerCompat$Api24Impl;

/* compiled from: PG */
/* loaded from: classes.dex */
final class EmojiTextViewHelper$HelperInternal19 extends UserManagerCompat$Api24Impl {
    private final EmojiInputFilter mEmojiInputFilter;
    public boolean mEnabled = true;
    private final TextView mTextView;

    public EmojiTextViewHelper$HelperInternal19(TextView textView) {
        this.mTextView = textView;
        this.mEmojiInputFilter = new EmojiInputFilter(textView);
    }

    @Override // androidx.core.os.UserManagerCompat$Api24Impl
    public final InputFilter[] getFilters(InputFilter[] inputFilterArr) {
        InputFilter[] inputFilterArr2;
        int length;
        if (!this.mEnabled) {
            SparseArray sparseArray = new SparseArray(1);
            int i = 0;
            while (true) {
                length = inputFilterArr.length;
                if (i >= length) {
                    break;
                }
                InputFilter inputFilter = inputFilterArr[i];
                if (inputFilter instanceof EmojiInputFilter) {
                    sparseArray.put(i, inputFilter);
                }
                i++;
            }
            if (sparseArray.size() != 0) {
                inputFilterArr2 = new InputFilter[length - sparseArray.size()];
                int i2 = 0;
                for (int i3 = 0; i3 < length; i3++) {
                    if (sparseArray.indexOfKey(i3) < 0) {
                        inputFilterArr2[i2] = inputFilterArr[i3];
                        i2++;
                    }
                }
            } else {
                return inputFilterArr;
            }
        } else {
            int length2 = inputFilterArr.length;
            for (InputFilter inputFilter2 : inputFilterArr) {
                if (inputFilter2 == this.mEmojiInputFilter) {
                    return inputFilterArr;
                }
            }
            inputFilterArr2 = new InputFilter[inputFilterArr.length + 1];
            System.arraycopy(inputFilterArr, 0, inputFilterArr2, 0, length2);
            inputFilterArr2[length2] = this.mEmojiInputFilter;
        }
        return inputFilterArr2;
    }

    @Override // androidx.core.os.UserManagerCompat$Api24Impl
    public final void setAllCaps(boolean z) {
        if (z) {
            updateTransformationMethod();
        }
    }

    @Override // androidx.core.os.UserManagerCompat$Api24Impl
    public final void setEnabled(boolean z) {
        this.mEnabled = z;
        updateTransformationMethod();
        this.mTextView.setFilters(getFilters(this.mTextView.getFilters()));
    }

    final void updateTransformationMethod() {
        this.mTextView.setTransformationMethod(wrapTransformationMethod(this.mTextView.getTransformationMethod()));
    }

    @Override // androidx.core.os.UserManagerCompat$Api24Impl
    public final TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod) {
        if (this.mEnabled) {
            if (!(transformationMethod instanceof EmojiTransformationMethod) && !(transformationMethod instanceof PasswordTransformationMethod)) {
                return new EmojiTransformationMethod(transformationMethod);
            }
            return transformationMethod;
        }
        if (transformationMethod instanceof EmojiTransformationMethod) {
            return ((EmojiTransformationMethod) transformationMethod).mTransformationMethod;
        }
        return transformationMethod;
    }
}
