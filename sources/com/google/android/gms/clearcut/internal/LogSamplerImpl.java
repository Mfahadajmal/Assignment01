package com.google.android.gms.clearcut.internal;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.clearcut.LegacyLogSampler;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.CombinedFlagSource;
import j$.util.concurrent.ConcurrentHashMap;
import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogSamplerImpl implements LegacyLogSampler {
    public static final CombinedFlagSource FLAG_FACTORY$ar$class_merging;
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    static Long androidId;
    public static final ConcurrentHashMap flagMap;
    static Boolean hasGserviceReadPermission;
    public final Context context;

    static {
        CombinedFlagSource combinedFlagSource = new CombinedFlagSource(PhenotypeConstants.getContentProviderUri("com.google.android.gms.clearcut.public"));
        if (!combinedFlagSource.directBootAware) {
            CombinedFlagSource combinedFlagSource2 = new CombinedFlagSource((Uri) combinedFlagSource.CombinedFlagSource$ar$objectConverter, "gms:playlog:service:samplingrules_", (String) combinedFlagSource.CombinedFlagSource$ar$logSourceNames, false, combinedFlagSource.autoSubpackage);
            FLAG_FACTORY$ar$class_merging = new CombinedFlagSource((Uri) combinedFlagSource2.CombinedFlagSource$ar$objectConverter, (String) combinedFlagSource2.CombinedFlagSource$ar$stringConverter, "LogSamplingRulesV2__", combinedFlagSource2.directBootAware, combinedFlagSource2.autoSubpackage);
            flagMap = new ConcurrentHashMap();
            hasGserviceReadPermission = null;
            androidId = null;
            return;
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public LogSamplerImpl(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        if (applicationContext != null) {
            PhenotypeFlag.maybeInit(applicationContext);
        }
    }
}
