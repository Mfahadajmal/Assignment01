package com.google.android.libraries.phenotype.client.lockdown;

import com.google.android.libraries.phenotype.client.lockdown.buildinfo.BuildInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagExemptionsReader {
    public final boolean doFlagLockdownRuntimeValidations;

    public FlagExemptionsReader(boolean z) {
        this.doFlagLockdownRuntimeValidations = z;
    }

    public FlagExemptionsReader(BuildInfo buildInfo) {
        buildInfo.isProductionBuild$ar$ds();
        this.doFlagLockdownRuntimeValidations = false;
    }
}
