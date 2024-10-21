package com.google.android.libraries.performance.primes.sampling;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentRateLimiting {
    public final Context application;
    public final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    public final Provider sharedPrefs;

    public PersistentRateLimiting(Context context, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, Provider provider) {
        this.application = context;
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.sharedPrefs = provider;
    }
}
