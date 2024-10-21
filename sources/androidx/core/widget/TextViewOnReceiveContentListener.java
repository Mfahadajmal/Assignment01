package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextViewOnReceiveContentListener implements OnReceiveContentListener {
    @Override // androidx.core.view.OnReceiveContentListener
    public final ContentInfoCompat onReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
        CharSequence coerceToStyledText;
        if (contentInfoCompat.mCompat.getSource() != 2) {
            ContentInfoCompat.Compat compat = contentInfoCompat.mCompat;
            ClipData clip = compat.getClip();
            int flags = compat.getFlags();
            TextView textView = (TextView) view;
            Editable editable = (Editable) textView.getText();
            Context context = textView.getContext();
            boolean z = false;
            for (int i = 0; i < clip.getItemCount(); i++) {
                ClipData.Item itemAt = clip.getItemAt(i);
                if ((flags & 1) != 0) {
                    coerceToStyledText = itemAt.coerceToText(context);
                    if (coerceToStyledText instanceof Spanned) {
                        coerceToStyledText = coerceToStyledText.toString();
                    }
                } else {
                    coerceToStyledText = itemAt.coerceToStyledText(context);
                }
                if (coerceToStyledText != null) {
                    if (!z) {
                        int selectionStart = Selection.getSelectionStart(editable);
                        int selectionEnd = Selection.getSelectionEnd(editable);
                        int max = Math.max(0, Math.min(selectionStart, selectionEnd));
                        int max2 = Math.max(0, Math.max(selectionStart, selectionEnd));
                        Selection.setSelection(editable, max2);
                        editable.replace(max, max2, coerceToStyledText);
                    } else {
                        editable.insert(Selection.getSelectionEnd(editable), "\n");
                        editable.insert(Selection.getSelectionEnd(editable), coerceToStyledText);
                    }
                    z = true;
                }
            }
            return null;
        }
        return contentInfoCompat;
    }
}
