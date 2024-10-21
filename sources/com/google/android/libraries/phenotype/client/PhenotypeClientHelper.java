package com.google.android.libraries.phenotype.client;

import com.google.common.base.Absent;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeClientHelper {
    static volatile Optional isValidContentProvider = Absent.INSTANCE;
    public static final Object isValidContentProviderLock = new Object();
}
