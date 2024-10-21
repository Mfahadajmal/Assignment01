package com.google.android.accessibility.switchaccesslegacy.migration;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import androidx.core.content.ContextCompat$Api24Impl;
import androidx.preference.PreferenceManager;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.ImmutableSet;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MigrationProvider extends ContentProvider {
    private static final ImmutableSet ALLOWED_CALLING_PACKAGES = ImmutableSet.of((Object) "com.google.android.marvin.talkback", (Object) "com.google.android.accessibility.switchaccess");
    private UriMatcher uriMatcher;

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("MigrationProvider does not support deleting");
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("MigrationProvider does not support inserting");
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.uriMatcher = uriMatcher;
        uriMatcher.addURI("com.google.android.marvin.talkback.providers.MigrationProvider", DatabaseUtils$Preference.CONTENT_ALL_URI.getPath(), 1);
        return true;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SharedPreferences defaultSharedPreferences;
        int i;
        if (ALLOWED_CALLING_PACKAGES.contains(getContext().getPackageManager().getNameForUid(Binder.getCallingUid()))) {
            int match = this.uriMatcher.match(uri);
            LogUtils.d("MigrationProvider", "query uri: " + String.valueOf(uri) + ", query match: " + match, new Object[0]);
            if (match == 1) {
                Context context = getContext();
                MatrixCursor matrixCursor = new MatrixCursor((String[]) DatabaseUtils$Preference.COLUMNS.toArray(new String[0]));
                Context createDeviceProtectedStorageContext = ContextCompat$Api24Impl.createDeviceProtectedStorageContext(context);
                if (createDeviceProtectedStorageContext != null) {
                    defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(createDeviceProtectedStorageContext);
                } else {
                    defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                }
                Map<String, ?> all = defaultSharedPreferences.getAll();
                for (String str3 : all.keySet()) {
                    Object obj = all.get(str3);
                    if (obj instanceof Long) {
                        i = 2;
                    } else if (obj instanceof Float) {
                        i = 3;
                    } else if (obj instanceof Integer) {
                        i = 4;
                    } else {
                        i = 1;
                    }
                    matrixCursor.addRow(new Object[]{str3, obj, Integer.valueOf(i)});
                }
                return matrixCursor;
            }
            throw new UnsupportedOperationException("MigrationProvider does not support querying: ".concat(String.valueOf(String.valueOf(uri))));
        }
        throw new UnsupportedOperationException("MigrationProvider does not support querying from this calling package");
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("MigrationProvider does not support updating");
    }
}
