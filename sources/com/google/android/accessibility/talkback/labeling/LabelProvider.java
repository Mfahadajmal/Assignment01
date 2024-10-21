package com.google.android.accessibility.talkback.labeling;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.os.UserManagerCompat$Api24Impl;
import com.google.android.accessibility.utils.labeling.LabelsTable;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LabelProvider extends ContentProvider {
    static final Uri LABELS_CONTENT_URI;
    private static final Uri LABELS_ID_CONTENT_URI;
    private static final Uri PACKAGE_SUMMARY_URI;
    static final UriMatcher uriMatcher;
    private SQLiteDatabase database;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LabelsDatabaseOpenHelper extends SQLiteOpenHelper {
        public LabelsDatabaseOpenHelper(Context context) {
            super(context, "labelsDatabase.db", (SQLiteDatabase.CursorFactory) null, 3);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            LabelsTable.onCreate(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            String[] strArr = LabelsTable.ALL_COLUMNS;
            if (i < 2) {
                LogUtils.i("LabelsTable", "Dropping table %s to upgrade from version %d to version %d.", "labels", Integer.valueOf(i), Integer.valueOf(i2));
                sQLiteDatabase.execSQL(String.format(Locale.ROOT, "DROP TABLE IF EXISTS %s", "labels"));
                LabelsTable.onCreate(sQLiteDatabase);
            } else if (i < 3) {
                sQLiteDatabase.execSQL(String.format(Locale.ROOT, "ALTER TABLE %s ADD COLUMN %s INTEGER DEFAULT %d", "labels", "sourceType", 0));
            }
        }
    }

    static {
        Uri build = new Uri.Builder().scheme("content").authority("com.google.android.marvin.talkback.providers.LabelProvider").path("labels").build();
        LABELS_CONTENT_URI = build;
        Uri withAppendedPath = Uri.withAppendedPath(build, "#");
        LABELS_ID_CONTENT_URI = withAppendedPath;
        Uri build2 = new Uri.Builder().scheme("content").authority("com.google.android.marvin.talkback.providers.LabelProvider").path("packageSummary").build();
        PACKAGE_SUMMARY_URI = build2;
        UriMatcher uriMatcher2 = new UriMatcher(-1);
        uriMatcher = uriMatcher2;
        uriMatcher2.addURI("com.google.android.marvin.talkback.providers.LabelProvider", build.getPath(), 1);
        uriMatcher2.addURI("com.google.android.marvin.talkback.providers.LabelProvider", withAppendedPath.getPath(), 2);
        uriMatcher2.addURI("com.google.android.marvin.talkback.providers.LabelProvider", build2.getPath(), 3);
    }

    private static final String combineSelectionAndWhere$ar$ds(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return String.format(Locale.ROOT, "(%s) AND (%s)", str2, str);
    }

    private final void initializeDatabaseIfNull() {
        if (this.database == null) {
            this.database = new LabelsDatabaseOpenHelper(getContext()).getWritableDatabase();
        }
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        if (uri == null) {
            LogUtils.w("LabelProvider", "URI is null", new Object[0]);
            return 0;
        }
        if (!UserManagerCompat$Api24Impl.isUserUnlocked(getContext())) {
            return 0;
        }
        int match = uriMatcher.match(uri);
        if (match != 1) {
            if (match != 2) {
                LogUtils.w("LabelProvider", "Unknown URI: %s", uri);
                return 0;
            }
            initializeDatabaseIfNull();
            try {
                int delete = this.database.delete("labels", combineSelectionAndWhere$ar$ds(str, String.format(Locale.ROOT, "%s = %d", "_id", Integer.valueOf(Integer.parseInt(uri.getLastPathSegment())))), strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return delete;
            } catch (NumberFormatException unused) {
                LogUtils.w("LabelProvider", "Unknown URI: %s", uri);
                return 0;
            }
        }
        initializeDatabaseIfNull();
        int delete2 = this.database.delete("labels", str, strArr);
        getContext().getContentResolver().notifyChange(uri, null);
        return delete2;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        if (uri == null) {
            LogUtils.w("LabelProvider", "URI is null", new Object[0]);
            return null;
        }
        if (UserManagerCompat$Api24Impl.isUserUnlocked(getContext())) {
            if (uriMatcher.match(uri) != 1) {
                LogUtils.w("LabelProvider", "Unknown URI: %s", uri);
                return null;
            }
            initializeDatabaseIfNull();
            if (contentValues != null) {
                if (contentValues.containsKey("_id")) {
                    LogUtils.w("LabelProvider", "Label ID must be assigned by the database.", new Object[0]);
                    return null;
                }
                long insert = this.database.insert("labels", null, contentValues);
                if (insert < 0) {
                    LogUtils.w("LabelProvider", "Failed to insert label.", new Object[0]);
                    return null;
                }
                return ContentUris.withAppendedId(LABELS_CONTENT_URI, insert);
            }
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String[] strArr3;
        String str3;
        String str4;
        if (uri == null) {
            LogUtils.w("LabelProvider", "URI is null", new Object[0]);
            return null;
        }
        if (!UserManagerCompat$Api24Impl.isUserUnlocked(getContext())) {
            return null;
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("labels");
        int match = uriMatcher.match(uri);
        if (match != 1) {
            if (match != 2) {
                if (match != 3) {
                    LogUtils.w("LabelProvider", "Unknown URI: %s", uri);
                    return null;
                }
                strArr3 = new String[]{"packageName", "COUNT(*)"};
                str3 = "packageName";
                str4 = str3;
            } else {
                try {
                    sQLiteQueryBuilder.appendWhere(String.format(Locale.ROOT, "%s = %d", "_id", Integer.valueOf(Integer.parseInt(uri.getLastPathSegment()))));
                    strArr3 = strArr;
                    str4 = str2;
                    str3 = null;
                } catch (NumberFormatException unused) {
                    LogUtils.w("LabelProvider", "Unknown URI: %s", uri);
                    return null;
                }
            }
        } else {
            strArr3 = strArr;
            if (TextUtils.isEmpty(str2)) {
                str3 = null;
                str4 = "_id";
            }
            str4 = str2;
            str3 = null;
        }
        initializeDatabaseIfNull();
        return sQLiteQueryBuilder.query(this.database, strArr3, str, strArr2, str3, null, str4);
    }

    @Override // android.content.ContentProvider
    public final void shutdown() {
        SQLiteDatabase sQLiteDatabase = this.database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (uri == null) {
            LogUtils.w("LabelProvider", "URI is null", new Object[0]);
            return 0;
        }
        if (!UserManagerCompat$Api24Impl.isUserUnlocked(getContext())) {
            return 0;
        }
        int match = uriMatcher.match(uri);
        if (match != 1) {
            if (match != 2) {
                LogUtils.w("LabelProvider", "Unknown URI: %s", uri);
                return 0;
            }
            initializeDatabaseIfNull();
            try {
                int update = this.database.update("labels", contentValues, combineSelectionAndWhere$ar$ds(str, String.format(Locale.ROOT, "%s = %d", "_id", Integer.valueOf(Integer.parseInt(uri.getLastPathSegment())))), strArr);
                getContext().getContentResolver().notifyChange(uri, null);
                return update;
            } catch (NumberFormatException unused) {
                LogUtils.w("LabelProvider", "Unknown URI: %s", uri);
                return 0;
            }
        }
        initializeDatabaseIfNull();
        int update2 = this.database.update("labels", contentValues, str, strArr);
        getContext().getContentResolver().notifyChange(uri, null);
        return update2;
    }
}
