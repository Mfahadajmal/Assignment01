package com.google.android.libraries.phenotype.client.stable;

import com.google.android.gms.phenotype.ExperimentTokens;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExperimentTokenData {
    public final String configPackageName;
    public final ExperimentTokens experimentToken;

    public ExperimentTokenData(ExperimentTokens experimentTokens, String str) {
        this.experimentToken = experimentTokens;
        this.configPackageName = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExperimentTokenData)) {
            return false;
        }
        ExperimentTokenData experimentTokenData = (ExperimentTokenData) obj;
        if (Intrinsics.areEqual(this.experimentToken, experimentTokenData.experimentToken) && Intrinsics.areEqual(this.configPackageName, experimentTokenData.configPackageName)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.experimentToken.hashCode() * 31) + this.configPackageName.hashCode();
    }

    public final String toString() {
        return "ExperimentTokenData(experimentToken=" + this.experimentToken + ", configPackageName=" + this.configPackageName + ")";
    }
}
