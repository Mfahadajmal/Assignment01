package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import androidx.core.os.BundleApi21ImplKt;
import androidx.core.os.LocaleListCompat;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EmojiInputConnection extends InputConnectionWrapper {
    private final LocaleListCompat.Api24Impl mEmojiCompatDeleteHelper$ar$class_merging;
    private final TextView mTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmojiInputConnection(TextView textView, InputConnection inputConnection) {
        super(inputConnection, false);
        LocaleListCompat.Api24Impl api24Impl = new LocaleListCompat.Api24Impl();
        this.mTextView = textView;
        this.mEmojiCompatDeleteHelper$ar$class_merging = api24Impl;
        EmojiCompat.isConfigured();
    }

    private final Editable getEditable() {
        return this.mTextView.getEditableText();
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i, int i2) {
        if (!BundleApi21ImplKt.handleDeleteSurroundingText(this, getEditable(), i, i2, false) && !super.deleteSurroundingText(i, i2)) {
            return false;
        }
        return true;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        if (BundleApi21ImplKt.handleDeleteSurroundingText(this, getEditable(), i, i2, true) || super.deleteSurroundingTextInCodePoints(i, i2)) {
            return true;
        }
        return false;
    }
}
