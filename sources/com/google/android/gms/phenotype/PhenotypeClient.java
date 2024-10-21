package com.google.android.gms.phenotype;

import android.content.Context;
import android.util.Pair;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.GoogleApi;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeClient extends GoogleApi {
    public static final /* synthetic */ int PhenotypeClient$ar$NoOp = 0;

    static {
        Pair.create(new SpannableUtils$NonCopyableTextSpan(), SpannableUtils$NonCopyableTextSpan.forResult(null));
    }

    public PhenotypeClient(Context context) {
        super(context, Phenotype.API$ar$class_merging$ar$class_merging$ar$class_merging, Api$ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
