package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseImplementation$ApiMethodImpl extends BasePendingResult implements BaseImplementation$ResultHolder {
    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseImplementation$ApiMethodImpl(OptionalMethod optionalMethod, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(googleApiClient, "GoogleApiClient must not be null");
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(optionalMethod, "Api must not be null");
    }

    private final void setFailedResult(RemoteException remoteException) {
        setFailedResult(new Status(8, remoteException.getLocalizedMessage(), null));
    }

    protected abstract void doExecute(Api$AnyClient api$AnyClient);

    public final void run(Api$AnyClient api$AnyClient) {
        try {
            doExecute(api$AnyClient);
        } catch (DeadObjectException e) {
            setFailedResult(e);
            throw e;
        } catch (RemoteException e2) {
            setFailedResult(e2);
        }
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
    public final /* bridge */ /* synthetic */ void setResult$ar$ds() {
        throw null;
    }

    public final void setFailedResult(Status status) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(!status.isSuccess(), "Failed result must not be success");
        setResult(createFailedResult(status));
    }
}
