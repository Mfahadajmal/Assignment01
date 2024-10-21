package androidx.preference;

import android.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditTextPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    private EditText mEditText;
    private CharSequence mText;
    private final Runnable mShowSoftInputRunnable = new SearchView$SearchAutoComplete.AnonymousClass1(this, 14);
    private long mShowRequestTime = -1;

    private final EditTextPreference getEditTextPreference() {
        return (EditTextPreference) getPreference();
    }

    private final void setPendingShowSoftInputRequest(boolean z) {
        long j;
        if (z) {
            j = SystemClock.currentThreadTimeMillis();
        } else {
            j = -1;
        }
        this.mShowRequestTime = j;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected final boolean needInputMethod() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void onBindDialogView(View view) {
        super.onBindDialogView(view);
        EditText editText = (EditText) view.findViewById(R.id.edit);
        this.mEditText = editText;
        if (editText != null) {
            editText.requestFocus();
            this.mEditText.setText(this.mText);
            EditText editText2 = this.mEditText;
            editText2.setSelection(editText2.getText().length());
            getEditTextPreference();
            return;
        }
        throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.mText = getEditTextPreference().mText;
        } else {
            this.mText = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void onDialogClosed(boolean z) {
        if (z) {
            String obj = this.mEditText.getText().toString();
            EditTextPreference editTextPreference = getEditTextPreference();
            if (editTextPreference.callChangeListener(obj)) {
                editTextPreference.setText(obj);
            }
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.mText);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected final void scheduleShowSoftInput() {
        setPendingShowSoftInputRequest(true);
        scheduleShowSoftInputInner();
    }

    public final void scheduleShowSoftInputInner() {
        long j = this.mShowRequestTime;
        if (j != -1 && j + 1000 > SystemClock.currentThreadTimeMillis()) {
            EditText editText = this.mEditText;
            if (editText != null && editText.isFocused()) {
                if (((InputMethodManager) this.mEditText.getContext().getSystemService("input_method")).showSoftInput(this.mEditText, 0)) {
                    setPendingShowSoftInputRequest(false);
                    return;
                } else {
                    this.mEditText.removeCallbacks(this.mShowSoftInputRunnable);
                    this.mEditText.postDelayed(this.mShowSoftInputRunnable, 50L);
                    return;
                }
            }
            setPendingShowSoftInputRequest(false);
        }
    }
}
