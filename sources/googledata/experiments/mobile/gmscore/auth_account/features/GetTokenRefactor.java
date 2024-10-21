package googledata.experiments.mobile.gmscore.auth_account.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GetTokenRefactor implements Supplier {
    public static final GetTokenRefactor INSTANCE = new GetTokenRefactor();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new GetTokenRefactorFlagsImpl());

    @Override // com.google.common.base.Supplier
    public final GetTokenRefactorFlags get() {
        return (GetTokenRefactorFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}
