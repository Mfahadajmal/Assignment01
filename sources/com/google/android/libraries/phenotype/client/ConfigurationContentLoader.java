package com.google.android.libraries.phenotype.client;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricService;
import com.google.android.libraries.phenotype.client.FlagLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationContentLoader implements FlagLoader {
    public final Object cacheLock;
    public volatile Map cachedFlags;
    public final List listeners;
    private final ContentObserver observer;
    private final Runnable processCacheInvalidater;
    public final ContentResolver resolver;
    public final Uri uri;
    public static final Map loadersByUri = new ArrayMap();
    public static final String[] COLUMNS = {"key", "value"};

    public ConfigurationContentLoader(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        ContentObserver contentObserver = new ContentObserver() { // from class: com.google.android.libraries.phenotype.client.ConfigurationContentLoader.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                ConfigurationContentLoader configurationContentLoader = ConfigurationContentLoader.this;
                synchronized (configurationContentLoader.cacheLock) {
                    configurationContentLoader.cachedFlags = null;
                    PhenotypeFlag.invalidateProcessCache();
                }
                synchronized (configurationContentLoader) {
                    Iterator it = configurationContentLoader.listeners.iterator();
                    while (it.hasNext()) {
                        ((ConfigurationUpdatedListener) it.next()).onConfigurationUpdated();
                    }
                }
            }
        };
        this.observer = contentObserver;
        this.cacheLock = new Object();
        this.listeners = new ArrayList();
        contentResolver.getClass();
        uri.getClass();
        this.resolver = contentResolver;
        this.uri = uri;
        this.processCacheInvalidater = runnable;
        contentResolver.registerContentObserver(uri, false, contentObserver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void clearLoaderMap() {
        synchronized (ConfigurationContentLoader.class) {
            for (ConfigurationContentLoader configurationContentLoader : loadersByUri.values()) {
                configurationContentLoader.resolver.unregisterContentObserver(configurationContentLoader.observer);
            }
            loadersByUri.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.libraries.phenotype.client.FlagLoader
    public final /* bridge */ /* synthetic */ Object getFlag(String str) {
        Map map;
        Map map2;
        Map map3 = this.cachedFlags;
        Map map4 = map3;
        if (map3 == null) {
            synchronized (this.cacheLock) {
                Map map5 = this.cachedFlags;
                map = map5;
                if (map5 == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        try {
                            map2 = (Map) ApplicationExitMetricService.executeBinderAware(new FlagLoader.BinderAwareFunction() { // from class: com.google.android.libraries.phenotype.client.ConfigurationContentLoader$$ExternalSyntheticLambda0
                                @Override // com.google.android.libraries.phenotype.client.FlagLoader.BinderAwareFunction
                                public final Object execute() {
                                    Map hashMap;
                                    ConfigurationContentLoader configurationContentLoader = ConfigurationContentLoader.this;
                                    Cursor query = configurationContentLoader.resolver.query(configurationContentLoader.uri, ConfigurationContentLoader.COLUMNS, null, null, null);
                                    if (query == null) {
                                        return Collections.emptyMap();
                                    }
                                    try {
                                        int count = query.getCount();
                                        if (count == 0) {
                                            return Collections.emptyMap();
                                        }
                                        if (count <= 256) {
                                            hashMap = new ArrayMap(count);
                                        } else {
                                            hashMap = new HashMap(count, 1.0f);
                                        }
                                        while (query.moveToNext()) {
                                            hashMap.put(query.getString(0), query.getString(1));
                                        }
                                        query.close();
                                        return hashMap;
                                    } finally {
                                        query.close();
                                    }
                                }
                            });
                        } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                            Log.e("ConfigurationContentLdr", "PhenotypeFlag unable to load ContentProvider, using default values");
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            map2 = null;
                        }
                        this.cachedFlags = map2;
                        allowThreadDiskReads = map2;
                        map = allowThreadDiskReads;
                    } finally {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    }
                }
            }
            map4 = map;
        }
        if (map4 == null) {
            map4 = Collections.emptyMap();
        }
        return (String) map4.get(str);
    }
}
