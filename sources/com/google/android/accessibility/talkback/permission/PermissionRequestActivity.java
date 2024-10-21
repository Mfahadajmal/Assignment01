package com.google.android.accessibility.talkback.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PermissionRequestActivity extends FragmentActivity {
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        Intent intent = new Intent();
        finish();
        intent.setAction("done");
        intent.putExtra("permissions", strArr);
        intent.putExtra("grant_results", iArr);
        sendBroadcast(intent);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        int length;
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        super.onResume();
        String[] stringArrayExtra = getIntent().getStringArrayExtra("permissions");
        if (stringArrayExtra != null && (length = stringArrayExtra.length) != 0) {
            int i = 0;
            if (TextUtils.equals(stringArrayExtra[0], "android.permission.READ_PHONE_STATE") && length == 1 && shouldShowRequestPermissionRationale("android.permission.READ_PHONE_STATE")) {
                getSupportFragmentManager();
                createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this, 2);
                createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.title_request_phone_permission);
                createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$ar$ds(R.string.message_request_phone_permission);
                createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.continue_button, new PermissionRequestActivity$$ExternalSyntheticLambda0(this, i));
                createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, new PermissionRequestActivity$$ExternalSyntheticLambda0(this, 2));
                A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
                ((AlertDialog) create.dialogWrapper$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application).setCanceledOnTouchOutside(true);
                create.show();
                return;
            }
            ActivityCompat.requestPermissions(this, stringArrayExtra, 1);
        }
    }
}
