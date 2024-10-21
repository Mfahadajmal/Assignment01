package com.google.android.libraries.mdi.download.debug.common.filegroups;

import android.view.MenuItem;
import com.google.android.libraries.mdi.download.debug.common.debuginfo.MddDebugInfoFragmentActionProviderImpl;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentHelper;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0 implements MenuItem.OnMenuItemClickListener {
    public final /* synthetic */ Object MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0(MddDebugInfoFragmentActionProviderImpl mddDebugInfoFragmentActionProviderImpl, int i) {
        this.switching_field = i;
        this.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$0 = mddDebugInfoFragmentActionProviderImpl;
        this.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$1 = "MDD.DEBUG.LOG_INFO_ACTION";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Parcelable, java.lang.Object] */
    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        if (this.switching_field == 0) {
            MddDebugMainFragmentActionProviderImpl mddDebugMainFragmentActionProviderImpl = (MddDebugMainFragmentActionProviderImpl) this.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$0;
            OptionalMethod optionalMethod = mddDebugMainFragmentActionProviderImpl.futureRegistry$ar$class_merging$ar$class_merging;
            ?? r1 = this.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$1;
            optionalMethod.listen$ar$class_merging$cca55420_0$ar$class_merging(OptionalMethod.ignoringValueFuture$ar$class_merging$ar$class_merging(mddDebugMainFragmentActionProviderImpl.performAction((MddDebugMainFragmentHelper.ActionInfo) r1)), mddDebugMainFragmentActionProviderImpl.mainFragmentActionCallback, r1);
            return true;
        }
        throw null;
    }

    public /* synthetic */ MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0(MddDebugMainFragmentActionProviderImpl mddDebugMainFragmentActionProviderImpl, MddDebugMainFragmentHelper.ActionInfo actionInfo, int i) {
        this.switching_field = i;
        this.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$0 = mddDebugMainFragmentActionProviderImpl;
        this.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0$ar$f$1 = actionInfo;
    }
}
