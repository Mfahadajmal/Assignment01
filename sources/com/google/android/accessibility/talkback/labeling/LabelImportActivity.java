package com.google.android.accessibility.talkback.labeling;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;
import com.google.android.accessibility.talkback.NotificationActivity;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda4;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LabelImportActivity extends Activity {

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.labeling.LabelImportActivity$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements DialogInterface.OnClickListener {
        public final /* synthetic */ Object LabelImportActivity$1$ar$this$0;
        final /* synthetic */ Object LabelImportActivity$1$ar$val$uri;
        private final /* synthetic */ int switching_field;

        /* compiled from: PG */
        /* renamed from: com.google.android.accessibility.talkback.labeling.LabelImportActivity$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C00021 extends SpannableUtils$IdentifierSpan {
            public C00021() {
                super((byte[]) null, (byte[]) null);
            }

            @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
            public final void onFail() {
                ((LabelImportActivity) AnonymousClass1.this.LabelImportActivity$1$ar$this$0).notifyFailure();
            }
        }

        public /* synthetic */ AnonymousClass1(NotificationActivity notificationActivity, String str, int i) {
            this.switching_field = i;
            this.LabelImportActivity$1$ar$val$uri = notificationActivity;
            this.LabelImportActivity$1$ar$this$0 = str;
        }

        /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.CharSequence, java.lang.Object] */
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            boolean z;
            ImmutableList copyOf;
            if (this.switching_field != 0) {
                NotificationActivity notificationActivity = (NotificationActivity) this.LabelImportActivity$1$ar$val$uri;
                if (notificationActivity.notificationId != Integer.MIN_VALUE) {
                    ((NotificationManager) notificationActivity.getSystemService("notification")).cancel(notificationActivity.notificationId);
                }
                ?? r2 = this.LabelImportActivity$1$ar$this$0;
                Window window = notificationActivity.a11yAlertDialogWrapper.getWindow();
                if (!TextUtils.isEmpty(r2) && window != null) {
                    Uri.parse((String) r2);
                    notificationActivity.getApplicationContext();
                    window.getDecorView();
                    return;
                }
                dialogInterface.dismiss();
                return;
            }
            if (i == -1) {
                dialogInterface.dismiss();
                z = false;
            } else {
                z = true;
                if (i == -2) {
                    dialogInterface.dismiss();
                }
            }
            ProcessStatsCapture processStatsCapture = new ProcessStatsCapture(((LabelImportActivity) this.LabelImportActivity$1$ar$this$0).getApplicationContext());
            Object obj = this.LabelImportActivity$1$ar$val$uri;
            C00021 c00021 = new C00021();
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((Context) processStatsCapture.ProcessStatsCapture$ar$context).getContentResolver().openInputStream((Uri) obj)));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append('\n');
                }
                bufferedReader.close();
                JSONArray jSONArray = new JSONObject(sb.toString()).getJSONArray("labels_array");
                if (jSONArray == null) {
                    int i2 = ImmutableList.ImmutableList$ar$NoOp;
                    copyOf = RegularImmutableList.EMPTY;
                } else {
                    int length = jSONArray.length();
                    ArrayList arrayList = new ArrayList(length);
                    for (int i3 = 0; i3 < length; i3++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i3);
                        String string = jSONObject.getString("package_name");
                        if (!TextUtils.isEmpty(string)) {
                            String string2 = jSONObject.getString("package_signature");
                            String string3 = jSONObject.getString("view_name");
                            if (!TextUtils.isEmpty(string3)) {
                                String string4 = jSONObject.getString("label_text");
                                if (!TextUtils.isEmpty(string4)) {
                                    arrayList.add(new Label(string, string2, string3, string4, jSONObject.getString("locale"), jSONObject.getInt("package_version"), "", jSONObject.getLong("timestamp")));
                                }
                            }
                        }
                    }
                    copyOf = ImmutableList.copyOf((Collection) arrayList);
                }
                if (copyOf.isEmpty()) {
                    return;
                }
                Object obj2 = processStatsCapture.ProcessStatsCapture$ar$processImportanceCapture;
                new LabelTask(new ImportLabelRequest(((CustomLabelManager) obj2).client$ar$class_merging$ae701839_0, copyOf, z, new GooglePlayServicesUpdatedReceiver.Callback((CustomLabelManager) obj2, c00021)), ((CustomLabelManager) obj2).taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
            } catch (Exception unused) {
                processStatsCapture.notifyFailure$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(c00021);
                LogUtils.e("CustomLabelMigrManager", "failed to import labels", new Object[0]);
            }
        }

        public AnonymousClass1(LabelImportActivity labelImportActivity, Uri uri, int i) {
            this.switching_field = i;
            this.LabelImportActivity$1$ar$val$uri = uri;
            this.LabelImportActivity$1$ar$this$0 = labelImportActivity;
        }
    }

    public final void notifyFailure() {
        Toast.makeText(getApplicationContext(), R.string.label_import_failed, 0).show();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            notifyFailure();
            finish();
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            notifyFailure();
            finish();
            return;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this, data, 0);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this, 1);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$ar$ds(R.string.label_import_dialog_message);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.label_import_dialog_title);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.label_import_dialog_skip, anonymousClass1);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(R.string.label_import_dialog_override, anonymousClass1);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setCancelable$ar$class_merging$ar$ds();
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setOnDismissListener$ar$class_merging$ar$ds(new ListMenuManager$$ExternalSyntheticLambda4(this, 3));
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create().show();
    }
}
