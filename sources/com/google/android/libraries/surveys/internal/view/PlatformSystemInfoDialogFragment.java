package com.google.android.libraries.surveys.internal.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PlatformSystemInfoDialogFragment extends DialogFragment {
    public static final /* synthetic */ int PlatformSystemInfoDialogFragment$ar$NoOp = 0;

    @Override // android.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.SurveyTheme);
        final View inflate = ((LayoutInflater) contextThemeWrapper.getSystemService("layout_inflater")).inflate(R.layout.survey_system_info_dialog, (ViewGroup) null);
        inflate.getContext().setTheme(R.style.SurveyAlertDialogCustomViewTheme);
        AlertDialog create = DefaultExperimentTokenDecorator.alertDialogBuilder(contextThemeWrapper).setView(inflate).create();
        inflate.findViewById(R.id.survey_system_info_dialog_ok_button).setOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(create, 5));
        Bundle arguments = getArguments();
        String string = arguments.getString("EXTRA_ACCOUNT_NAME");
        Bundle bundle2 = arguments.getBundle("EXTRA_PSD_BUNDLE");
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.survey_system_info_dialog_list_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(contextThemeWrapper));
        SystemInfoItemsAdapter systemInfoItemsAdapter = new SystemInfoItemsAdapter();
        recyclerView.setAdapter(systemInfoItemsAdapter);
        recyclerView.addOnScrollListener$ar$class_merging(new AppCompatSpinner.Api23Impl(this) { // from class: com.google.android.libraries.surveys.internal.view.PlatformSystemInfoDialogFragment.1
            final /* synthetic */ PlatformSystemInfoDialogFragment this$0;

            {
                this.this$0 = this;
            }

            @Override // android.support.v7.widget.AppCompatSpinner.Api23Impl
            public final void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                int i3;
                if (!recyclerView2.canScrollVertically(-1)) {
                    inflate.findViewById(R.id.survey_system_info_dialog_title).setElevation(0.0f);
                } else {
                    inflate.findViewById(R.id.survey_system_info_dialog_title).setElevation(this.this$0.getResources().getDimensionPixelSize(R.dimen.survey_system_info_dialog_title_elevation));
                }
                boolean canScrollVertically = recyclerView2.canScrollVertically(1);
                View findViewById = inflate.findViewById(R.id.survey_system_info_dialog_section_divider);
                if (true != canScrollVertically) {
                    i3 = 8;
                } else {
                    i3 = 0;
                }
                findViewById.setVisibility(i3);
            }
        });
        systemInfoItemsAdapter.setItems(DefaultExperimentTokenDecorator.getSystemInfoDialogItems(contextThemeWrapper, string, bundle2));
        return create;
    }
}
