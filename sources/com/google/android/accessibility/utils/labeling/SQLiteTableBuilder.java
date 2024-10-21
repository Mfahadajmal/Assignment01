package com.google.android.accessibility.utils.labeling;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SQLiteTableBuilder {
    public SQLiteDatabase mDatabase;
    public StringBuilder mStringBuilder;
    private boolean mHasColumns = false;
    public boolean mCreated = false;

    public SQLiteTableBuilder(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            if (!TextUtils.isEmpty("labels")) {
                if ("labels".matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                    this.mDatabase = sQLiteDatabase;
                    StringBuilder sb = new StringBuilder();
                    this.mStringBuilder = sb;
                    sb.append("CREATE TABLE ");
                    this.mStringBuilder.append("labels");
                    this.mStringBuilder.append("(");
                    return;
                }
                throw new IllegalArgumentException("Invalid table name.");
            }
            throw new IllegalArgumentException("Table name cannot be empty.");
        }
        throw new IllegalArgumentException("Database cannot be null.");
    }

    public final SQLiteTableBuilder addColumn(String str, int i) {
        addColumn$ar$ds(str, i, false);
        return this;
    }

    public final void addColumn$ar$ds(String str, int i, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (str.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                if (this.mHasColumns) {
                    this.mStringBuilder.append(", ");
                }
                this.mStringBuilder.append(str);
                if (i != 1) {
                    if (i != 2) {
                        this.mStringBuilder.append(" TEXT");
                    } else {
                        this.mStringBuilder.append(" REAL");
                    }
                } else {
                    this.mStringBuilder.append(" INTEGER");
                }
                if (z) {
                    this.mStringBuilder.append(" PRIMARY KEY");
                }
                this.mHasColumns = true;
                return;
            }
            throw new IllegalArgumentException("Invalid column name.");
        }
        throw new IllegalArgumentException("Column name cannot be empty.");
    }
}
