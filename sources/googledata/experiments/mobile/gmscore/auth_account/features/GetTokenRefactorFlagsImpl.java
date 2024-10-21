package googledata.experiments.mobile.gmscore.auth_account.features;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.CombinedFlagSource;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GetTokenRefactorFlagsImpl implements GetTokenRefactorFlags {
    public static final PhenotypeFlag blockedPackages;
    public static final PhenotypeFlag gaulTokenApiEvolved;

    static {
        CombinedFlagSource combinedFlagSource = new CombinedFlagSource(PhenotypeConstants.getContentProviderUri("com.google.android.gms.auth_account"));
        if (((String) combinedFlagSource.CombinedFlagSource$ar$stringConverter).isEmpty()) {
            Object obj = combinedFlagSource.CombinedFlagSource$ar$objectConverter;
            Object obj2 = combinedFlagSource.CombinedFlagSource$ar$stringConverter;
            String str = (String) obj2;
            Uri uri = (Uri) obj;
            CombinedFlagSource combinedFlagSource2 = new CombinedFlagSource(uri, str, (String) combinedFlagSource.CombinedFlagSource$ar$logSourceNames, true, combinedFlagSource.autoSubpackage);
            Object obj3 = combinedFlagSource2.CombinedFlagSource$ar$objectConverter;
            Object obj4 = combinedFlagSource2.CombinedFlagSource$ar$stringConverter;
            String str2 = (String) obj4;
            Uri uri2 = (Uri) obj3;
            CombinedFlagSource combinedFlagSource3 = new CombinedFlagSource(uri2, str2, (String) combinedFlagSource2.CombinedFlagSource$ar$logSourceNames, combinedFlagSource2.directBootAware, true);
            combinedFlagSource3.createFlagRestricted$ar$ds("getTokenRefactor__account_data_service_sample_percentage");
            combinedFlagSource3.createFlagRestricted("getTokenRefactor__account_data_service_tokenAPI_usable", true);
            combinedFlagSource3.createFlagRestricted$ar$ds$7c4d897c_0("getTokenRefactor__account_manager_timeout_seconds", 20L);
            combinedFlagSource3.createFlagRestricted$ar$ds$7c4d897c_0("getTokenRefactor__android_id_shift", 0L);
            final GetTokenRefactorFlagsImpl$$ExternalSyntheticLambda0 getTokenRefactorFlagsImpl$$ExternalSyntheticLambda0 = new GetTokenRefactorFlagsImpl$$ExternalSyntheticLambda0();
            blockedPackages = new PhenotypeFlag(combinedFlagSource3) { // from class: com.google.android.libraries.phenotype.client.PhenotypeFlag.9
                @Override // com.google.android.libraries.phenotype.client.PhenotypeFlag
                public final Object convertValue(Object obj5) {
                    if (obj5 instanceof String) {
                        try {
                            return (TypedFeatures$StringListParam) GeneratedMessageLite.parseFrom(TypedFeatures$StringListParam.DEFAULT_INSTANCE, Base64.decode((String) obj5, 3));
                        } catch (IOException | IllegalArgumentException unused) {
                        }
                    }
                    Log.e("PhenotypeFlag", "Invalid byte[] value for " + super.getMendelFlagName() + ": " + String.valueOf(obj5));
                    return null;
                }
            };
            combinedFlagSource3.createFlagRestricted("getTokenRefactor__chimera_get_token_evolved", true);
            combinedFlagSource3.createFlagRestricted$ar$ds$7c4d897c_0("getTokenRefactor__clear_token_timeout_seconds", 20L);
            combinedFlagSource3.createFlagRestricted$ar$ds$7c4d897c_0("getTokenRefactor__default_task_timeout_seconds", 20L);
            combinedFlagSource3.createFlagRestricted("getTokenRefactor__gaul_accounts_api_evolved", false);
            gaulTokenApiEvolved = combinedFlagSource3.createFlagRestricted("getTokenRefactor__gaul_token_api_evolved", false);
            combinedFlagSource3.createFlagRestricted$ar$ds$7c4d897c_0("getTokenRefactor__get_token_timeout_seconds", 120L);
            combinedFlagSource3.createFlagRestricted("getTokenRefactor__gms_account_authenticator_evolved", true);
            combinedFlagSource3.createFlagRestricted$ar$ds("getTokenRefactor__gms_account_authenticator_sample_percentage");
            return;
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    @Override // googledata.experiments.mobile.gmscore.auth_account.features.GetTokenRefactorFlags
    public final TypedFeatures$StringListParam blockedPackages() {
        return (TypedFeatures$StringListParam) blockedPackages.get();
    }

    @Override // googledata.experiments.mobile.gmscore.auth_account.features.GetTokenRefactorFlags
    public final boolean gaulTokenApiEvolved() {
        return ((Boolean) gaulTokenApiEvolved.get()).booleanValue();
    }
}
