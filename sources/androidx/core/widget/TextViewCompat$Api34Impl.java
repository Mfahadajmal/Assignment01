package androidx.core.widget;

import android.widget.TextView;
import androidx.sqlite.SQLiteStatement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextViewCompat$Api34Impl {
    public static final int getColumnIndex(SQLiteStatement sQLiteStatement, String str) {
        int columnIndexOf = TextViewCompat$Api28Impl.columnIndexOf(sQLiteStatement, str);
        if (columnIndexOf >= 0) {
            return columnIndexOf;
        }
        int columnIndexOf2 = TextViewCompat$Api28Impl.columnIndexOf(sQLiteStatement, "`" + str + '`');
        if (columnIndexOf2 >= 0) {
            return columnIndexOf2;
        }
        return -1;
    }

    public static void setLineHeight(TextView textView, int i, float f) {
        textView.setLineHeight(i, f);
    }
}
