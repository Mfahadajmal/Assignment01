package com.google.android.accessibility.selecttospeak.logging;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimeBucketHelper {
    public static final TimeBucketHelper INSTANCE = new TimeBucketHelper();
    public static final List timeBucketMs = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.asList(new Integer[]{1, 500, 1000, 2000, 5000, 10000, 20000, 40000});

    private TimeBucketHelper() {
    }
}
