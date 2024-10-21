package com.google.android.settings.search;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SearchIndexablesProvider extends ContentProvider {
    private static final int MATCH_DYNAMIC_RAW_CODE = 6;
    private static final int MATCH_NON_INDEXABLE_KEYS_CODE = 3;
    private static final int MATCH_RAW_CODE = 2;
    private static final int MATCH_RES_CODE = 1;
    private static final int MATCH_SITE_MAP_PAIRS_CODE = 4;
    private static final int MATCH_SLICE_URI_PAIRS_CODE = 5;
    private static final String READ_SEARCH_INDEXABLES = "android.permission.READ_SEARCH_INDEXABLES";
    private static final String TAG = "IndexablesProvider";
    private String authority;
    private UriMatcher uriMatcher;

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        this.authority = providerInfo.authority;
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.uriMatcher = uriMatcher;
        uriMatcher.addURI(this.authority, "settings/indexables_xml_res", 1);
        this.uriMatcher.addURI(this.authority, "settings/indexables_raw", 2);
        this.uriMatcher.addURI(this.authority, "settings/non_indexables_key", 3);
        this.uriMatcher.addURI(this.authority, "settings/site_map_pairs", 4);
        this.uriMatcher.addURI(this.authority, "settings/slice_uri_pairs", 5);
        this.uriMatcher.addURI(this.authority, "settings/dynamic_indexables_raw", 6);
        if (providerInfo.exported) {
            if (providerInfo.grantUriPermissions) {
                if (READ_SEARCH_INDEXABLES.equals(providerInfo.readPermission)) {
                    super.attachInfo(context, providerInfo);
                    return;
                }
                throw new SecurityException("Provider must be protected by READ_SEARCH_INDEXABLES");
            }
            throw new SecurityException("Provider must grantUriPermissions");
        }
        throw new SecurityException("Provider must be exported");
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Delete not supported");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int match = this.uriMatcher.match(uri);
        if (match != 1) {
            if (match != 2) {
                if (match != 3) {
                    if (match != 6) {
                        throw new IllegalArgumentException("Unknown URI ".concat(String.valueOf(String.valueOf(uri))));
                    }
                    return "vnd.android.cursor.dir/indexables_raw";
                }
                return "vnd.android.cursor.dir/non_indexables_key";
            }
            return "vnd.android.cursor.dir/indexables_raw";
        }
        return "vnd.android.cursor.dir/indexables_xml_res";
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Insert not supported");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            switch (this.uriMatcher.match(uri)) {
                case 1:
                    return queryXmlResources(null);
                case 2:
                    return queryRawData(null);
                case 3:
                    return queryNonIndexableKeys(null);
                case 4:
                    return querySiteMapPairs();
                case 5:
                    return querySliceUriPairs();
                case 6:
                    return queryDynamicRawData(null);
                default:
                    throw new UnsupportedOperationException("Unknown Uri " + String.valueOf(uri));
            }
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e2) {
            Log.e(TAG, "Provider querying exception:", e2);
            return null;
        }
    }

    public Cursor queryDynamicRawData(String[] strArr) {
        return null;
    }

    public abstract Cursor queryNonIndexableKeys(String[] strArr);

    public abstract Cursor queryRawData(String[] strArr);

    public Cursor querySiteMapPairs() {
        return null;
    }

    public Cursor querySliceUriPairs() {
        return null;
    }

    public abstract Cursor queryXmlResources(String[] strArr);

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Update not supported");
    }
}
