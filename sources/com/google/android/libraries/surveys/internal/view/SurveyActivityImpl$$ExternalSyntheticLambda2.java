package com.google.android.libraries.surveys.internal.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.surveys.internal.common.view.BaseView;
import com.google.android.libraries.surveys.internal.resourceutils.ResourceUtils$SystemInfoLinkClickListener;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SurveyActivityImpl$$ExternalSyntheticLambda2 implements ResourceUtils$SystemInfoLinkClickListener {
    public final /* synthetic */ Object SurveyActivityImpl$$ExternalSyntheticLambda2$ar$f$0;
    public final /* synthetic */ String f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SurveyActivityImpl$$ExternalSyntheticLambda2(Object obj, String str, int i) {
        this.switching_field = i;
        this.SurveyActivityImpl$$ExternalSyntheticLambda2$ar$f$0 = obj;
        this.f$1 = str;
    }

    @Override // com.google.android.libraries.surveys.internal.resourceutils.ResourceUtils$SystemInfoLinkClickListener
    public final void onClick() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                Stopwatch stopwatch = new Stopwatch();
                SystemInfoDialogFragment systemInfoDialogFragment = new SystemInfoDialogFragment();
                Bundle bundle = new Bundle(2);
                String str = this.f$1;
                bundle.putString("EXTRA_ACCOUNT_NAME", str);
                BaseView baseView = (BaseView) this.SurveyActivityImpl$$ExternalSyntheticLambda2$ar$f$0;
                bundle.putBundle("EXTRA_PSD_BUNDLE", SurveyUtils.getPsdBundle(baseView.answer.productContext));
                systemInfoDialogFragment.setArguments(bundle);
                FragmentManager fragmentManager = baseView.supportFragmentManager;
                systemInfoDialogFragment.show(fragmentManager, "com.google.android.libraries.surveys.internal.view.SystemInfoDialogFragment");
                fragmentManager.executePendingTransactions$ar$ds();
                DefaultExperimentTokenDecorator.reportUserEventForAccountAndSystemInfoLinkClicked(stopwatch, baseView.getContext(), str);
                return;
            }
            Stopwatch stopwatch2 = new Stopwatch();
            PromptDialogDelegate promptDialogDelegate = (PromptDialogDelegate) this.SurveyActivityImpl$$ExternalSyntheticLambda2$ar$f$0;
            Activity activity = promptDialogDelegate.activity;
            boolean z = activity instanceof FragmentActivity;
            String str2 = this.f$1;
            if (z) {
                FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                SystemInfoDialogFragment systemInfoDialogFragment2 = new SystemInfoDialogFragment();
                Bundle bundle2 = new Bundle(2);
                bundle2.putString("EXTRA_ACCOUNT_NAME", str2);
                bundle2.putBundle("EXTRA_PSD_BUNDLE", SurveyUtils.getPsdBundle(promptDialogDelegate.answer.productContext));
                systemInfoDialogFragment2.setArguments(bundle2);
                systemInfoDialogFragment2.show(supportFragmentManager, "com.google.android.libraries.surveys.internal.view.SystemInfoDialogFragment");
                supportFragmentManager.executePendingTransactions$ar$ds();
            } else {
                FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
                PlatformSystemInfoDialogFragment platformSystemInfoDialogFragment = new PlatformSystemInfoDialogFragment();
                Bundle bundle3 = new Bundle(2);
                bundle3.putString("EXTRA_ACCOUNT_NAME", str2);
                bundle3.putBundle("EXTRA_PSD_BUNDLE", SurveyUtils.getPsdBundle(promptDialogDelegate.answer.productContext));
                platformSystemInfoDialogFragment.setArguments(bundle3);
                beginTransaction.add(platformSystemInfoDialogFragment, "com.google.android.libraries.surveys.internal.view.PlatformSystemInfoDialogFragment");
                beginTransaction.commitAllowingStateLoss();
            }
            DefaultExperimentTokenDecorator.reportUserEventForAccountAndSystemInfoLinkClicked(stopwatch2, promptDialogDelegate.themeContext, str2);
            return;
        }
        Stopwatch stopwatch3 = new Stopwatch();
        SystemInfoDialogFragment systemInfoDialogFragment3 = new SystemInfoDialogFragment();
        Bundle bundle4 = new Bundle(2);
        String str3 = this.f$1;
        bundle4.putString("EXTRA_ACCOUNT_NAME", str3);
        SurveyActivityImpl surveyActivityImpl = (SurveyActivityImpl) this.SurveyActivityImpl$$ExternalSyntheticLambda2$ar$f$0;
        bundle4.putBundle("EXTRA_PSD_BUNDLE", SurveyUtils.getPsdBundle(surveyActivityImpl.answer.productContext));
        systemInfoDialogFragment3.setArguments(bundle4);
        FragmentManager fragmentManager2 = surveyActivityImpl.supportFragmentManager;
        systemInfoDialogFragment3.show(fragmentManager2, "com.google.android.libraries.surveys.internal.view.SystemInfoDialogFragment");
        fragmentManager2.executePendingTransactions$ar$ds();
        DefaultExperimentTokenDecorator.reportUserEventForAccountAndSystemInfoLinkClicked(stopwatch3, surveyActivityImpl.activity, str3);
    }
}
