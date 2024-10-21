package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.google.android.gsf.Gservices;
import com.google.android.gsf.GservicesConstants;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricService;
import com.google.android.libraries.phenotype.client.FlagLoader;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class GservicesLoader implements FlagLoader {
    private static GservicesLoader loader;
    public final Context context;
    private final ContentObserver observer;

    private GservicesLoader() {
        this.context = null;
        this.observer = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void clearLoader() {
        Context context;
        synchronized (GservicesLoader.class) {
            GservicesLoader gservicesLoader = loader;
            if (gservicesLoader != null && (context = gservicesLoader.context) != null && gservicesLoader.observer != null) {
                context.getContentResolver().unregisterContentObserver(loader.observer);
            }
            loader = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GservicesLoader getLoader(Context context) {
        GservicesLoader gservicesLoader;
        GservicesLoader gservicesLoader2;
        synchronized (GservicesLoader.class) {
            if (loader == null) {
                if (ActivityCompat.Api32Impl.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                    gservicesLoader2 = new GservicesLoader(context);
                } else {
                    gservicesLoader2 = new GservicesLoader();
                }
                loader = gservicesLoader2;
            }
            gservicesLoader = loader;
        }
        return gservicesLoader;
    }

    @Override // com.google.android.libraries.phenotype.client.FlagLoader
    public final /* bridge */ /* synthetic */ Object getFlag(String str) {
        throw null;
    }

    private GservicesLoader(Context context) {
        this.context = context;
        ContentObserver contentObserver = new ContentObserver() { // from class: com.google.android.libraries.phenotype.client.GservicesLoader.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                PhenotypeFlag.invalidateProcessCache();
            }
        };
        this.observer = contentObserver;
        context.getContentResolver().registerContentObserver(GservicesConstants.CONTENT_URI, true, contentObserver);
    }

    @Override // com.google.android.libraries.phenotype.client.FlagLoader
    public final String getFlag(final String str) {
        Context context = this.context;
        if (context != null && !DirectBootUtils.isDirectBoot(context)) {
            try {
                return (String) ApplicationExitMetricService.executeBinderAware(new FlagLoader.BinderAwareFunction() { // from class: com.google.android.libraries.phenotype.client.GservicesLoader$$ExternalSyntheticLambda0
                    @Override // com.google.android.libraries.phenotype.client.FlagLoader.BinderAwareFunction
                    public final Object execute() {
                        return Gservices.sDelegate$ar$class_merging.getString$ar$ds(GservicesLoader.this.context.getContentResolver(), str);
                    }
                });
            } catch (IllegalStateException | NullPointerException | SecurityException e) {
                Log.e("GservicesLoader", "Unable to read GServices for: ".concat(String.valueOf(str)), e);
            }
        }
        return null;
    }
}
