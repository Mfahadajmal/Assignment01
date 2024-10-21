package androidx.core.widget;

import android.icu.text.DecimalFormatSymbols;
import android.text.PrecomputedText;
import android.widget.TextView;
import androidx.sqlite.SQLiteStatement;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextViewCompat$Api28Impl {
    public static final int columnIndexOf(SQLiteStatement sQLiteStatement, String str) {
        int columnCount = sQLiteStatement.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            if (Intrinsics.areEqual(str, sQLiteStatement.getColumnName(i))) {
                return i;
            }
        }
        return -1;
    }

    static String[] getDigitStrings(DecimalFormatSymbols decimalFormatSymbols) {
        return decimalFormatSymbols.getDigitStrings();
    }

    static PrecomputedText.Params getTextMetricsParams(TextView textView) {
        return textView.getTextMetricsParams();
    }

    public static void setFirstBaselineToTopHeight(TextView textView, int i) {
        textView.setFirstBaselineToTopHeight(i);
    }

    static CharSequence castToCharSequence(PrecomputedText precomputedText) {
        return precomputedText;
    }
}
