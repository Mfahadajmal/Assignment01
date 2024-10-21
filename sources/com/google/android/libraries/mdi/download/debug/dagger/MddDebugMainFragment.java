package com.google.android.libraries.mdi.download.debug.dagger;

import android.os.Bundle;
import android.support.v4.app.BackStackRecord;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentActionProvider;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentActionProviderImpl;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentHelper;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentUiProvider;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugMainFragmentUiProviderImpl;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.GoogleLogger;
import com.google.mlkit.logging.schema.ImageInfo;
import dagger.android.support.DaggerFragment;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugMainFragment extends DaggerFragment {
    private MddDebugMainFragmentHelper helper;

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        MddDebugMainFragmentActionProviderImpl mddDebugMainFragmentActionProviderImpl = new MddDebugMainFragmentActionProviderImpl(this);
        MddDebugMainFragmentHelper mddDebugMainFragmentHelper = new MddDebugMainFragmentHelper(new MddDebugMainFragmentUiProviderImpl(this, mddDebugMainFragmentActionProviderImpl, new PrimesController$$ExternalSyntheticLambda9(10)), mddDebugMainFragmentActionProviderImpl);
        this.helper = mddDebugMainFragmentHelper;
        MddDebugMainFragmentActionProviderImpl mddDebugMainFragmentActionProviderImpl2 = (MddDebugMainFragmentActionProviderImpl) mddDebugMainFragmentHelper.actionProvider;
        mddDebugMainFragmentActionProviderImpl2.futureRegistry$ar$class_merging$ar$class_merging = OptionalMethod.forFragment$ar$class_merging$ar$class_merging(mddDebugMainFragmentActionProviderImpl2.fragment);
        mddDebugMainFragmentActionProviderImpl2.futureRegistry$ar$class_merging$ar$class_merging.registerCallback$ar$ds(R.id.main_fragment_action_callback, mddDebugMainFragmentActionProviderImpl2.mainFragmentActionCallback);
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MddDebugMainFragmentUiProvider mddDebugMainFragmentUiProvider = this.helper.uiProvider;
        SubMenu addSubMenu = menu.addSubMenu("Run MDD Task...");
        MenuItem add = addSubMenu.add("Maintenance Task");
        MddDebugMainFragmentUiProviderImpl mddDebugMainFragmentUiProviderImpl = (MddDebugMainFragmentUiProviderImpl) mddDebugMainFragmentUiProvider;
        MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider = mddDebugMainFragmentUiProviderImpl.actionProvider;
        ImageInfo.Builder newBuilder$ar$class_merging$ar$class_merging$ar$class_merging = MddDebugMainFragmentHelper.ActionInfo.newBuilder$ar$class_merging$ar$class_merging$ar$class_merging();
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging.ImageInfo$Builder$ar$originalImageSize = "MDD.DEBUG.TRIGGER_MDD_ACTION";
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging.ImageInfo$Builder$ar$imageFormat = "MDD.MAINTENANCE.PERIODIC.GCM.TASK";
        add.setOnMenuItemClickListener(new MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0((MddDebugMainFragmentActionProviderImpl) mddDebugMainFragmentActionProvider, newBuilder$ar$class_merging$ar$class_merging$ar$class_merging.build(), 0));
        MenuItem add2 = addSubMenu.add("Charging Task");
        MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider2 = mddDebugMainFragmentUiProviderImpl.actionProvider;
        ImageInfo.Builder newBuilder$ar$class_merging$ar$class_merging$ar$class_merging2 = MddDebugMainFragmentHelper.ActionInfo.newBuilder$ar$class_merging$ar$class_merging$ar$class_merging();
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging2.ImageInfo$Builder$ar$originalImageSize = "MDD.DEBUG.TRIGGER_MDD_ACTION";
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging2.ImageInfo$Builder$ar$imageFormat = "MDD.CHARGING.PERIODIC.TASK";
        add2.setOnMenuItemClickListener(new MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0((MddDebugMainFragmentActionProviderImpl) mddDebugMainFragmentActionProvider2, newBuilder$ar$class_merging$ar$class_merging$ar$class_merging2.build(), 0));
        MenuItem add3 = addSubMenu.add("Cellular Charging Task");
        MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider3 = mddDebugMainFragmentUiProviderImpl.actionProvider;
        ImageInfo.Builder newBuilder$ar$class_merging$ar$class_merging$ar$class_merging3 = MddDebugMainFragmentHelper.ActionInfo.newBuilder$ar$class_merging$ar$class_merging$ar$class_merging();
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging3.ImageInfo$Builder$ar$originalImageSize = "MDD.DEBUG.TRIGGER_MDD_ACTION";
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging3.ImageInfo$Builder$ar$imageFormat = "MDD.CELLULAR.CHARGING.PERIODIC.TASK";
        add3.setOnMenuItemClickListener(new MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0((MddDebugMainFragmentActionProviderImpl) mddDebugMainFragmentActionProvider3, newBuilder$ar$class_merging$ar$class_merging$ar$class_merging3.build(), 0));
        MenuItem add4 = addSubMenu.add("Wifi Charging Task");
        MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider4 = mddDebugMainFragmentUiProviderImpl.actionProvider;
        ImageInfo.Builder newBuilder$ar$class_merging$ar$class_merging$ar$class_merging4 = MddDebugMainFragmentHelper.ActionInfo.newBuilder$ar$class_merging$ar$class_merging$ar$class_merging();
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging4.ImageInfo$Builder$ar$originalImageSize = "MDD.DEBUG.TRIGGER_MDD_ACTION";
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging4.ImageInfo$Builder$ar$imageFormat = "MDD.WIFI.CHARGING.PERIODIC.TASK";
        add4.setOnMenuItemClickListener(new MddDebugMainFragmentActionProviderImpl$$ExternalSyntheticLambda0((MddDebugMainFragmentActionProviderImpl) mddDebugMainFragmentActionProvider4, newBuilder$ar$class_merging$ar$class_merging$ar$class_merging4.build(), 0));
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MddDebugMainFragmentUiProvider mddDebugMainFragmentUiProvider = this.helper.uiProvider;
        View inflate = layoutInflater.inflate(R.layout.mdd_debug_container_fragment, viewGroup, false);
        if (bundle == null) {
            try {
                MddDebugListFragment mddDebugListFragment = new MddDebugListFragment();
                BackStackRecord backStackRecord = new BackStackRecord(((MddDebugMainFragmentUiProviderImpl) mddDebugMainFragmentUiProvider).fragment.getChildFragmentManager());
                backStackRecord.add$ar$ds(R.id.file_group_list_container, mddDebugListFragment);
                backStackRecord.commitNow();
            } catch (RuntimeException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) MddDebugMainFragmentUiProviderImpl.logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugMainFragmentUiProviderImpl", "setupView", 53, "MddDebugMainFragmentUiProviderImpl.java")).log("Unable to add list fragment");
            }
        }
        Button button = (Button) inflate.findViewById(R.id.clear_storage);
        MddDebugMainFragmentUiProviderImpl mddDebugMainFragmentUiProviderImpl = (MddDebugMainFragmentUiProviderImpl) mddDebugMainFragmentUiProvider;
        MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider = mddDebugMainFragmentUiProviderImpl.actionProvider;
        ImageInfo.Builder newBuilder$ar$class_merging$ar$class_merging$ar$class_merging = MddDebugMainFragmentHelper.ActionInfo.newBuilder$ar$class_merging$ar$class_merging$ar$class_merging();
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging.ImageInfo$Builder$ar$originalImageSize = "MDD.DEBUG.CLEAR_ACTION";
        button.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(mddDebugMainFragmentActionProvider, newBuilder$ar$class_merging$ar$class_merging$ar$class_merging.build(), 5, null));
        Button button2 = (Button) inflate.findViewById(R.id.refresh_file_groups);
        MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider2 = mddDebugMainFragmentUiProviderImpl.actionProvider;
        ImageInfo.Builder newBuilder$ar$class_merging$ar$class_merging$ar$class_merging2 = MddDebugMainFragmentHelper.ActionInfo.newBuilder$ar$class_merging$ar$class_merging$ar$class_merging();
        newBuilder$ar$class_merging$ar$class_merging$ar$class_merging2.ImageInfo$Builder$ar$originalImageSize = "MDD.DEBUG.REFRESH_ACTION";
        button2.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(mddDebugMainFragmentActionProvider2, newBuilder$ar$class_merging$ar$class_merging$ar$class_merging2.build(), 5, null));
        return inflate;
    }
}
