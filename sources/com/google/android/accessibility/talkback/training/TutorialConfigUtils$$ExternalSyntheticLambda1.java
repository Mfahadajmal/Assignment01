package com.google.android.accessibility.talkback.training;

import android.content.Context;
import com.google.android.accessibility.talkback.permission.PermissionRequestActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.Consumer;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.TreeDebug;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TutorialConfigUtils$$ExternalSyntheticLambda1 implements Consumer {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TutorialConfigUtils$$ExternalSyntheticLambda1(int i) {
        this.switching_field = i;
    }

    @Override // com.google.android.accessibility.utils.Consumer
    public final void accept(Object obj) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        if (this.switching_field != 0) {
            TreeDebug.logNodeTrees((List) obj, TreeDebug.defaultLogger);
            return;
        }
        Context context = (Context) obj;
        if (SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(context)) {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(context, 2);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.talkback_inactive_title);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$ar$ds(R.string.talkback_inactive_message);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setCancelable$ar$class_merging$ar$ds();
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setOnCancelListener$ar$class_merging$ar$ds();
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.talkback_inactive_go_to_settings_button, new PermissionRequestActivity$$ExternalSyntheticLambda0(context, 3));
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(R.string.talkback_inactive_not_now_button, null);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create().show();
        }
    }
}
