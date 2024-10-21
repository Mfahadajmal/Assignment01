package com.google.android.libraries.phenotype.client.stable;

import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStablePhenotypeFlagFactory {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Converter {
        Object convert(Object obj);
    }

    public static final ProcessStablePhenotypeFlag createFlagRestricted$ar$objectUnboxing(String str, double d, String str2, Set set, boolean z, boolean z2) {
        return new ProcessStablePhenotypeFlag("com.google.android.marvin.talkback", str, Double.valueOf(d), new CombinedFlagSource(true, false, set, (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(0), (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18(Double.class, 0)));
    }

    public static final ProcessStablePhenotypeFlag createFlagRestricted$ar$objectUnboxing$1bcefca3_0(String str, String str2, String str3, Set set, boolean z, boolean z2) {
        return new ProcessStablePhenotypeFlag(str3, str, str2, new CombinedFlagSource(true, false, set, (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(3), (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18(String.class, 5)));
    }

    public static final ProcessStablePhenotypeFlag createFlagRestricted$ar$objectUnboxing$64c97a1b_0(String str, long j, String str2, Set set, boolean z, boolean z2) {
        return new ProcessStablePhenotypeFlag(str2, str, Long.valueOf(j), new CombinedFlagSource(true, z2, set, (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(1), (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18(Long.class, 1)));
    }

    public static final ProcessStablePhenotypeFlag createFlagRestricted$ar$objectUnboxing$c42f40f1_0(String str, boolean z, String str2, Set set, boolean z2, boolean z3) {
        return new ProcessStablePhenotypeFlag(str2, str, Boolean.valueOf(z), new CombinedFlagSource(true, z3, set, (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda17(2), (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18(Boolean.class, 4)));
    }

    public static final ProcessStablePhenotypeFlag createFlagRestricted$ar$objectUnboxing$f7eda825_0(String str, Converter converter, String str2, String str3, Set set, boolean z, boolean z2) {
        return new ProcessStablePhenotypeFlag(str3, str, new CombinedFlagSource(true, z2, set, (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18(converter, 2), (Converter) new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18(converter, 3)), str2);
    }
}
