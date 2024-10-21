package com.google.android.libraries.phenotype.client;

import android.content.SharedPreferences;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedPreferencesLoader implements FlagLoader {
    public static final /* synthetic */ int SharedPreferencesLoader$ar$NoOp = 0;
    private static final Map loadersByName = new ArrayMap();
    private final SharedPreferences.OnSharedPreferenceChangeListener changeListener;
    private final SharedPreferences sharedPreferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void clearLoaderMap() {
        synchronized (SharedPreferencesLoader.class) {
            Map map = loadersByName;
            Iterator it = map.values().iterator();
            if (!it.hasNext()) {
                map.clear();
            } else {
                SharedPreferencesLoader sharedPreferencesLoader = (SharedPreferencesLoader) it.next();
                SharedPreferences sharedPreferences = sharedPreferencesLoader.sharedPreferences;
                SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = sharedPreferencesLoader.changeListener;
                throw null;
            }
        }
    }

    @Override // com.google.android.libraries.phenotype.client.FlagLoader
    public final Object getFlag(String str) {
        throw null;
    }
}
