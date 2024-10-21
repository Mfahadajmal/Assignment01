package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeConstants {
    public static final /* synthetic */ int PhenotypeConstants$ar$NoOp = 0;
    private static final ArrayMap uriByConfigPackageName = new ArrayMap();

    public static synchronized Uri getContentProviderUri(String str) {
        synchronized (PhenotypeConstants.class) {
            ArrayMap arrayMap = uriByConfigPackageName;
            Uri uri = (Uri) arrayMap.get(str);
            if (uri == null) {
                Uri parse = Uri.parse("content://com.google.android.gms.phenotype/".concat(String.valueOf(Uri.encode(str))));
                arrayMap.put(str, parse);
                return parse;
            }
            return uri;
        }
    }

    public static String getSubpackagedName(Context context, String str) {
        if (!str.contains("#")) {
            return str + "#" + context.getPackageName();
        }
        throw new IllegalArgumentException("The passed in package cannot already have a subpackage: ".concat(String.valueOf(str)));
    }
}
