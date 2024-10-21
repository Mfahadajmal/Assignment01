package com.google.android.libraries.mdi.download.debug.dagger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.mdi.download.debug.common.debuginfo.MddDebugInfoFragmentActionProviderImpl;
import com.google.android.libraries.mdi.download.debug.common.debuginfo.MddDebugInfoFragmentHelper;
import com.google.android.libraries.mdi.download.debug.common.debuginfo.MddDebugInfoFragmentUiProvider;
import com.google.android.libraries.mdi.download.debug.common.debuginfo.MddDebugInfoFragmentUiProviderImpl;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0;
import com.google.android.marvin.talkback.R;
import dagger.android.support.DaggerFragment;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugInfoFragment extends DaggerFragment {
    private MddDebugInfoFragmentHelper helper;

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        MddDebugInfoFragmentActionProviderImpl mddDebugInfoFragmentActionProviderImpl = new MddDebugInfoFragmentActionProviderImpl(this);
        MddDebugInfoFragmentHelper mddDebugInfoFragmentHelper = new MddDebugInfoFragmentHelper(new MddDebugInfoFragmentUiProviderImpl(mddDebugInfoFragmentActionProviderImpl), mddDebugInfoFragmentActionProviderImpl);
        this.helper = mddDebugInfoFragmentHelper;
        MddDebugInfoFragmentActionProviderImpl mddDebugInfoFragmentActionProviderImpl2 = (MddDebugInfoFragmentActionProviderImpl) mddDebugInfoFragmentHelper.actionProvider;
        mddDebugInfoFragmentActionProviderImpl2.futureRegistry$ar$class_merging$ar$class_merging = OptionalMethod.forFragment$ar$class_merging$ar$class_merging(mddDebugInfoFragmentActionProviderImpl2.fragment);
        mddDebugInfoFragmentActionProviderImpl2.futureRegistry$ar$class_merging$ar$class_merging.registerCallback$ar$ds(R.id.debug_info_fragment_action_callback, mddDebugInfoFragmentActionProviderImpl2.debugInfoFragmentActionCallback);
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MddDebugInfoFragmentUiProvider mddDebugInfoFragmentUiProvider = this.helper.uiProvider;
        menuInflater.inflate(R.menu.debug_info_fragment_options_menu, menu);
        menu.findItem(R.id.dump_info_option).setOnMenuItemClickListener(new MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0((MddDebugInfoFragmentActionProviderImpl) ((MddDebugInfoFragmentUiProviderImpl) mddDebugInfoFragmentUiProvider).actionProvider, 1));
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MddDebugInfoFragmentUiProvider mddDebugInfoFragmentUiProvider = this.helper.uiProvider;
        throw null;
    }
}
