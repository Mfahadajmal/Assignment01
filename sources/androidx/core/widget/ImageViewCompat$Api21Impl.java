package androidx.core.widget;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageViewCompat$Api21Impl {
    public static final int getColumnIndexOrThrow(Cursor cursor, String str) {
        String str2;
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0) {
            columnIndex = cursor.getColumnIndex("`" + str + '`');
            if (columnIndex < 0) {
                columnIndex = -1;
            }
        }
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            String[] columnNames = cursor.getColumnNames();
            columnNames.getClass();
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) "");
            int i = 0;
            for (String str3 : columnNames) {
                i++;
                if (i > 1) {
                    sb.append((CharSequence) ", ");
                }
                OnDeviceStainRemovalLogEvent.appendElement(sb, str3, null);
            }
            sb.append((CharSequence) "");
            str2 = sb.toString();
        } catch (Exception unused) {
            str2 = "unknown";
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + str2);
    }

    public static ColorStateList getImageTintList(ImageView imageView) {
        return imageView.getImageTintList();
    }

    public static PorterDuff.Mode getImageTintMode(ImageView imageView) {
        return imageView.getImageTintMode();
    }

    public static void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
        imageView.setImageTintList(colorStateList);
    }

    public static void setImageTintMode(ImageView imageView, PorterDuff.Mode mode) {
        imageView.setImageTintMode(mode);
    }
}
