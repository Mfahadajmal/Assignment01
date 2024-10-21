package com.google.android.accessibility.utils.labeling;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LabelsTable {
    public static final String[] ALL_COLUMNS = {"_id", "packageName", "packageSignature", "viewName", "text", "locale", "packageVersion", "screenshotPath", "timestamp", "sourceType"};

    public static void onCreate(SQLiteDatabase sQLiteDatabase) {
        LogUtils.i("LabelsTable", "Creating table: %s.", "labels");
        SQLiteTableBuilder sQLiteTableBuilder = new SQLiteTableBuilder(sQLiteDatabase);
        sQLiteTableBuilder.addColumn$ar$ds("_id", 1, true);
        SQLiteTableBuilder addColumn = sQLiteTableBuilder.addColumn("packageName", 3).addColumn("packageSignature", 3).addColumn("viewName", 3).addColumn("text", 3).addColumn("locale", 3).addColumn("packageVersion", 1).addColumn("screenshotPath", 3).addColumn("timestamp", 1).addColumn("sourceType", 1);
        if (!addColumn.mCreated) {
            addColumn.mDatabase.execSQL(String.format("%s%s", addColumn.mStringBuilder.toString(), ")"));
            addColumn.mCreated = true;
            return;
        }
        throw new IllegalStateException("createTable was already called on this instance.");
    }
}
