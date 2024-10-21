package com.google.android.accessibility.talkback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda4;
import com.google.android.accessibility.talkback.labeling.LabelImportActivity;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NotificationActivity extends FragmentActivity {
    public A11yAlertDialogWrapper a11yAlertDialogWrapper;
    public int notificationId = Integer.MIN_VALUE;

    public static Intent createStartIntent(Context context, int i, int i2, int i3, int i4, String str) {
        Intent intent = new Intent(context, (Class<?>) NotificationActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        intent.putExtra("title", i);
        intent.putExtra("message", i2);
        intent.putExtra("button", i4);
        intent.putExtra("notificationId", i3);
        intent.putExtra("url", str);
        return intent;
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            LogUtils.w("NotificationActivity", "NotificationActivity received an empty extras bundle.", new Object[0]);
            finish();
            return;
        }
        this.notificationId = extras.getInt("notificationId", Integer.MIN_VALUE);
        int i = extras.getInt("title", -1);
        int i2 = extras.getInt("message", -1);
        int i3 = extras.getInt("button", -1);
        String str = null;
        String string = extras.getString("url", null);
        if (i == -1) {
            i = R.string.talkback_title;
        }
        String string2 = getString(i);
        if (i2 != -1) {
            str = getString(i2);
        }
        if (i3 == -1) {
            i3 = android.R.string.ok;
        }
        String string3 = getString(i3);
        if (TextUtils.isEmpty(str)) {
            finish();
            return;
        }
        LabelImportActivity.AnonymousClass1 anonymousClass1 = new LabelImportActivity.AnonymousClass1(this, string, 1);
        TextUtils.isEmpty(string);
        getSupportFragmentManager();
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this, 2);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$51f49cd0_0$ar$ds(string2);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$51f49cd0_0$ar$ds(str);
        ((AlertDialog.Builder) createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).setPositiveButton(string3, anonymousClass1);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setOnDismissListener$ar$class_merging$ar$ds(new ListMenuManager$$ExternalSyntheticLambda4(this, 1));
        A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
        this.a11yAlertDialogWrapper = create;
        create.show();
    }
}
