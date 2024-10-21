package com.google.android.libraries.phenotype.client.stable;

import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18 implements ProcessStablePhenotypeFlagFactory.Converter {
    public final /* synthetic */ Object ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18(Object obj, int i) {
        this.switching_field = i;
        this.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$Converter, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory$Converter, java.lang.Object] */
    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return (String) ((Class) this.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0).cast(obj);
                        }
                        return (Boolean) ((Class) this.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0).cast(obj);
                    }
                    return this.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0.convert((byte[]) obj);
                }
                return this.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0.convert(Base64.decode((String) obj, 3));
            }
            return (Long) ((Class) this.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0).cast(obj);
        }
        return (Double) ((Class) this.ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda18$ar$f$0).cast(obj);
    }
}
