package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
final class EmojiInputFilter implements InputFilter {
    private final TextView mTextView;

    public EmojiInputFilter(TextView textView) {
        this.mTextView = textView;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.mTextView.isInEditMode()) {
            return charSequence;
        }
        EmojiCompat.get$ar$ds$55e866f7_0();
        throw null;
    }
}
