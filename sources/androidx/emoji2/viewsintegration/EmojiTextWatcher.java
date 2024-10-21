package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EmojiTextWatcher implements TextWatcher {
    private final EditText mEditText;
    public boolean mEnabled = true;
    private int mLastEditLength;
    private int mLastEditPosition;

    public EmojiTextWatcher(EditText editText) {
        this.mEditText = editText;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (!this.mEditText.isInEditMode() && this.mEnabled) {
            EmojiCompat.isConfigured();
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mLastEditPosition = i;
        this.mLastEditLength = i3;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
