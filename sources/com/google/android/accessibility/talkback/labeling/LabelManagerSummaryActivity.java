package com.google.android.accessibility.talkback.labeling;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.google.android.accessibility.talkback.labeling.LabelsFetchRequest;
import com.google.android.accessibility.talkback.labeling.RevertImportedLabelsRequest;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.preference.BasePreferencesActivity;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;
import java.io.File;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LabelManagerSummaryActivity extends BasePreferencesActivity implements View.OnClickListener {
    private final SpannableUtils$IdentifierSpan exportLabelsCallBack$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan() { // from class: com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity.1
        @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
        public final void onFail() {
            LabelManagerSummaryActivity.this.notifyLabelExportFailure();
        }

        @Override // com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
        public final void onLabelsExported(File file) {
            try {
                Uri uriForFile = FileProvider.getPathStrategy$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(LabelManagerSummaryActivity.this.getApplicationContext(), "com.google.android.marvin.talkback.providers.FileProvider").getUriForFile(file);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.STREAM", uriForFile);
                intent.addFlags(1);
                intent.setType("application/json");
                if (LabelManagerSummaryActivity.this.getApplicationContext().getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                    Toast.makeText(LabelManagerSummaryActivity.this.getApplicationContext(), R.string.no_apps_to_export_labels, 0).show();
                } else {
                    LabelManagerSummaryActivity.this.startActivity(Intent.createChooser(intent, LabelManagerSummaryActivity.this.getResources().getString(R.string.label_choose_app_to_export)));
                }
            } catch (IllegalArgumentException unused) {
                LabelManagerSummaryActivity.this.notifyLabelExportFailure();
            }
        }
    };
    private CustomLabelManager labelManager;
    private PackageLabelInfoAdapter packageLabelInfoAdapter;
    private RecyclerView packageList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PackageLabelInfoAdapter extends RecyclerView.Adapter {
        public final Activity activity;
        public List items;
        private final CustomLabelManager labelManager;
        private final LayoutInflater layoutInflater;
        private final View.OnClickListener onClickListener;
        public Button revertButton;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class PackageLabelViewHolder extends RecyclerView.ViewHolder {
            public final Context context;
            public final View view;

            public PackageLabelViewHolder(Context context, View view) {
                super(view);
                this.context = context;
                this.view = view;
            }
        }

        public PackageLabelInfoAdapter(Activity activity, View.OnClickListener onClickListener, CustomLabelManager customLabelManager) {
            this.activity = activity;
            this.onClickListener = onClickListener;
            this.labelManager = customLabelManager;
            this.layoutInflater = (LayoutInflater) activity.getSystemService("layout_inflater");
        }

        public final void checkImportedLabels() {
            Button button = this.revertButton;
            if (button == null) {
                return;
            }
            button.setEnabled(false);
            CustomLabelManager customLabelManager = this.labelManager;
            new LabelTask(new HasImportedLabelsRequest(customLabelManager.client$ar$class_merging$ae701839_0, new HapticPatternParser$$ExternalSyntheticLambda1(this)), customLabelManager.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final int getItemCount() {
            List list = this.items;
            if (list != null && !list.isEmpty()) {
                return this.items.size() + 1;
            }
            return 2;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final int getItemViewType(int i) {
            if (i == 0) {
                return 0;
            }
            List list = this.items;
            if (list != null) {
                if (!list.isEmpty() || i != 1) {
                    return 1;
                }
                return 2;
            }
            return 2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x005f, code lost:
        
            if (r8 == null) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x007c, code lost:
        
            r0.setText(r5);
            r1.setText(java.lang.Integer.toString(r14.type$ar$edu$88c656f2_0));
            r2.setImageDrawable(r8);
            r14 = new android.content.Intent(r13.context, (java.lang.Class<?>) com.google.android.accessibility.talkback.labeling.LabelManagerPackageActivity.class);
            r14.addFlags(268435456);
            r14.addFlags(67108864);
            r14.putExtra("packageName", (java.lang.String) r4);
            r13.view.setOnClickListener(new com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(r13, r14, 0));
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x00af, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0078, code lost:
        
            r8 = r3.getDefaultActivityIcon();
         */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.support.v7.widget.RecyclerView.Adapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final /* bridge */ /* synthetic */ void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder r13, int r14) {
            /*
                r12 = this;
                com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder r13 = (com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity.PackageLabelInfoAdapter.PackageLabelViewHolder) r13
                int r0 = r12.getItemViewType(r14)
                if (r0 == 0) goto Lb7
                r1 = 2
                if (r0 != r1) goto Ld
                goto Lb7
            Ld:
                java.util.List r0 = r12.items
                int r14 = r14 + (-1)
                java.lang.Object r14 = r0.get(r14)
                com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall$State r14 = (com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall.State) r14
                android.view.View r0 = r13.view
                r1 = 2131427991(0x7f0b0297, float:1.8477614E38)
                android.view.View r0 = r0.findViewById(r1)
                android.widget.TextView r0 = (android.widget.TextView) r0
                android.view.View r1 = r13.view
                r2 = 2131427990(0x7f0b0296, float:1.8477612E38)
                android.view.View r1 = r1.findViewById(r2)
                android.widget.TextView r1 = (android.widget.TextView) r1
                android.view.View r2 = r13.view
                r3 = 2131427785(0x7f0b01c9, float:1.8477196E38)
                android.view.View r2 = r2.findViewById(r3)
                android.widget.ImageView r2 = (android.widget.ImageView) r2
                android.content.Context r3 = r13.context
                android.content.pm.PackageManager r3 = r3.getPackageManager()
                java.lang.Object r4 = r14.OrderVerifyingClientCall$State$ar$cancellationStatus
                r5 = 0
                r6 = 1
                r7 = 0
                r8 = r4
                java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L62 android.content.pm.PackageManager.NameNotFoundException -> L64
                android.content.pm.PackageInfo r8 = r3.getPackageInfo(r8, r7)     // Catch: java.lang.Throwable -> L62 android.content.pm.PackageManager.NameNotFoundException -> L64
                android.content.pm.ApplicationInfo r8 = r8.applicationInfo     // Catch: java.lang.Throwable -> L62 android.content.pm.PackageManager.NameNotFoundException -> L64
                java.lang.CharSequence r5 = r3.getApplicationLabel(r8)     // Catch: java.lang.Throwable -> L62 android.content.pm.PackageManager.NameNotFoundException -> L64
                r8 = r4
                java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L62 android.content.pm.PackageManager.NameNotFoundException -> L64
                android.graphics.drawable.Drawable r8 = r3.getApplicationIcon(r8)     // Catch: java.lang.Throwable -> L62 android.content.pm.PackageManager.NameNotFoundException -> L64
                boolean r9 = android.text.TextUtils.isEmpty(r5)
                if (r6 == r9) goto L5e
                goto L5f
            L5e:
                r5 = r4
            L5f:
                if (r8 != 0) goto L7c
                goto L78
            L62:
                r13 = move-exception
                goto Lb0
            L64:
                java.lang.String r8 = "LabelManagerSummaryAct"
                java.lang.String r9 = "Could not load package info for package %s."
                java.lang.Object r10 = r14.OrderVerifyingClientCall$State$ar$cancellationStatus     // Catch: java.lang.Throwable -> L62
                java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L62
                r11[r7] = r10     // Catch: java.lang.Throwable -> L62
                com.google.android.libraries.accessibility.utils.log.LogUtils.i(r8, r9, r11)     // Catch: java.lang.Throwable -> L62
                boolean r8 = android.text.TextUtils.isEmpty(r5)
                if (r6 != r8) goto L78
                r5 = r4
            L78:
                android.graphics.drawable.Drawable r8 = r3.getDefaultActivityIcon()
            L7c:
                r0.setText(r5)
                int r14 = r14.type$ar$edu$88c656f2_0
                java.lang.String r14 = java.lang.Integer.toString(r14)
                r1.setText(r14)
                r2.setImageDrawable(r8)
                android.content.Intent r14 = new android.content.Intent
                android.content.Context r0 = r13.context
                java.lang.Class<com.google.android.accessibility.talkback.labeling.LabelManagerPackageActivity> r1 = com.google.android.accessibility.talkback.labeling.LabelManagerPackageActivity.class
                r14.<init>(r0, r1)
                r0 = 268435456(0x10000000, float:2.5243549E-29)
                r14.addFlags(r0)
                r0 = 67108864(0x4000000, float:1.5046328E-36)
                r14.addFlags(r0)
                java.lang.String r0 = "packageName"
                java.lang.String r4 = (java.lang.String) r4
                r14.putExtra(r0, r4)
                android.view.View r0 = r13.view
                com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0 r1 = new com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0
                r1.<init>(r13, r14, r7)
                r0.setOnClickListener(r1)
                return
            Lb0:
                android.text.TextUtils.isEmpty(r5)
                r3.getDefaultActivityIcon()
                throw r13
            Lb7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity.PackageLabelInfoAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view;
            if (i == 0) {
                view = this.layoutInflater.inflate(R.layout.label_manager_buttons, viewGroup, false);
                Button button = (Button) view.findViewById(R.id.revert_import);
                this.revertButton = button;
                button.setOnClickListener(this.onClickListener);
                checkImportedLabels();
                ((Button) view.findViewById(R.id.import_labels)).setOnClickListener(this.onClickListener);
                ((Button) view.findViewById(R.id.export_labels)).setOnClickListener(this.onClickListener);
            } else if (i == 2) {
                view = this.layoutInflater.inflate(R.layout.label_manager_no_package_message, viewGroup, false);
            } else if (i == 1) {
                view = this.layoutInflater.inflate(R.layout.label_manager_package_row, viewGroup, false);
            } else {
                view = null;
            }
            return new PackageLabelViewHolder(this.activity.getApplicationContext(), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UpdatePackageSummaryTask extends AsyncTask {
        private final ExecutionList.RunnableExecutorPair labelProviderClient$ar$class_merging;
        private String locale;
        private final PackageLabelInfoAdapter packageLabelInfoAdapter;

        public UpdatePackageSummaryTask(Context context, PackageLabelInfoAdapter packageLabelInfoAdapter) {
            this.labelProviderClient$ar$class_merging = new ExecutionList.RunnableExecutorPair(context);
            this.packageLabelInfoAdapter = packageLabelInfoAdapter;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0097 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00bc  */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r13v12, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r13v14, types: [java.util.List] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected final /* bridge */ /* synthetic */ java.lang.Object doInBackground(java.lang.Object[] r13) {
            /*
                r12 = this;
                java.lang.Void[] r13 = (java.lang.Void[]) r13
                int r13 = r12.hashCode()
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                java.lang.String r0 = r12.locale
                r1 = 2
                java.lang.Object[] r1 = new java.lang.Object[r1]
                r2 = 0
                r1[r2] = r13
                r13 = 1
                r1[r13] = r0
                java.lang.String r0 = "LabelManagerSummaryAct"
                java.lang.String r3 = "Spawning new UpdatePackageSummaryTask(%d) for %s."
                com.google.android.libraries.accessibility.utils.log.LogUtils.v(r0, r3, r1)
                java.lang.String r0 = r12.locale
                java.lang.String r1 = "Querying package summary."
                java.lang.Object[] r3 = new java.lang.Object[r2]
                java.lang.String r4 = "LabelProviderClient"
                com.google.android.libraries.accessibility.utils.log.LogUtils.d(r4, r1, r3)
                com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r1 = r12.labelProviderClient$ar$class_merging
                boolean r3 = r1.checkClient()
                r5 = 0
                if (r3 != 0) goto L32
                goto Lb3
            L32:
                java.lang.String r0 = java.lang.String.valueOf(r0)
                java.lang.String r3 = "%"
                java.lang.String r0 = r0.concat(r3)
                java.lang.String r3 = "2"
                java.lang.String[] r10 = new java.lang.String[]{r0, r3}
                java.lang.Object r0 = r1.ExecutionList$RunnableExecutorPair$ar$next     // Catch: java.lang.Throwable -> L9d android.os.RemoteException -> L9f
                java.lang.Object r1 = r1.ExecutionList$RunnableExecutorPair$ar$executor     // Catch: java.lang.Throwable -> L9d android.os.RemoteException -> L9f
                java.lang.String r9 = "locale LIKE ? AND sourceType != ? "
                r7 = r1
                android.net.Uri r7 = (android.net.Uri) r7     // Catch: java.lang.Throwable -> L9d android.os.RemoteException -> L9f
                r6 = r0
                android.content.ContentProviderClient r6 = (android.content.ContentProviderClient) r6     // Catch: java.lang.Throwable -> L9d android.os.RemoteException -> L9f
                r8 = 0
                r11 = 0
                android.database.Cursor r0 = r6.query(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L9d android.os.RemoteException -> L9f
                if (r0 != 0) goto L5c
                java.util.List r13 = java.util.Collections.emptyList()     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
            L5a:
                r5 = r13
                goto L6c
            L5c:
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                r1.<init>()     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
            L61:
                boolean r3 = r0.moveToNext()     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                if (r3 != 0) goto L72
                java.util.List r13 = j$.util.DesugarCollections.unmodifiableList(r1)     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                goto L5a
            L6c:
                if (r0 == 0) goto Lb3
                r0.close()
                return r5
            L72:
                boolean r3 = r0.isClosed()     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                if (r3 != 0) goto L8d
                boolean r3 = r0.isAfterLast()     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                if (r3 == 0) goto L7f
                goto L8d
            L7f:
                java.lang.String r3 = r0.getString(r2)     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                int r6 = r0.getInt(r13)     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall$State r7 = new com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall$State     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                r7.<init>(r3, r6)     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                goto L95
            L8d:
                java.lang.String r3 = "Failed to get PackageLabelInfo from cursor."
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                com.google.android.libraries.accessibility.utils.log.LogUtils.w(r4, r3, r6)     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                r7 = r5
            L95:
                if (r7 == 0) goto L61
                r1.add(r7)     // Catch: android.os.RemoteException -> L9b java.lang.Throwable -> Lb8
                goto L61
            L9b:
                r13 = move-exception
                goto La1
            L9d:
                r13 = move-exception
                goto Lba
            L9f:
                r13 = move-exception
                r0 = r5
            La1:
                java.lang.String r1 = "RemoteException caught!"
                java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lb8
                com.google.android.libraries.accessibility.utils.log.LogUtils.e(r4, r1, r3)     // Catch: java.lang.Throwable -> Lb8
                java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> Lb8
                java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lb8
                com.google.android.libraries.accessibility.utils.log.LogUtils.d(r4, r13, r1)     // Catch: java.lang.Throwable -> Lb8
                if (r0 != 0) goto Lb4
            Lb3:
                return r5
            Lb4:
                r0.close()
                return r5
            Lb8:
                r13 = move-exception
                r5 = r0
            Lba:
                if (r5 == 0) goto Lbf
                r5.close()
            Lbf:
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity.UpdatePackageSummaryTask.doInBackground(java.lang.Object[]):java.lang.Object");
        }

        @Override // android.os.AsyncTask
        protected final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
            PackageLabelInfoAdapter packageLabelInfoAdapter = this.packageLabelInfoAdapter;
            packageLabelInfoAdapter.items = (List) obj;
            packageLabelInfoAdapter.checkImportedLabels();
            this.packageLabelInfoAdapter.notifyDataSetChanged();
            this.labelProviderClient$ar$class_merging.shutdown();
        }

        @Override // android.os.AsyncTask
        protected final void onPreExecute() {
            this.locale = SpannableUtils$IdentifierSpan.getDefaultLocale();
        }
    }

    public final void notifyLabelExportFailure() {
        Toast.makeText(getApplicationContext(), R.string.label_export_failed, 0).show();
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && intent != null) {
            Uri data = intent.getData();
            Intent intent2 = new Intent(this, (Class<?>) LabelImportActivity.class);
            intent2.setData(data);
            startActivity(intent2);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (id == R.id.import_labels) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.setType("application/json");
            intent.addCategory("android.intent.category.OPENABLE");
            if (getApplicationContext().getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                Toast.makeText(getApplicationContext(), R.string.no_apps_to_import_labels, 0).show();
                return;
            } else {
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.label_choose_app_to_import)), 0);
                return;
            }
        }
        if (id == R.id.export_labels) {
            final ProcessStatsCapture processStatsCapture = new ProcessStatsCapture(getApplicationContext());
            final SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = this.exportLabelsCallBack$ar$class_merging$ar$class_merging$ar$class_merging;
            ((CustomLabelManager) processStatsCapture.ProcessStatsCapture$ar$processImportanceCapture).getLabelsFromDatabase(new LabelsFetchRequest.OnLabelsFetchedListener() { // from class: com.google.android.accessibility.talkback.labeling.CustomLabelMigrationManager$1
                /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object, java.util.concurrent.ExecutorService] */
                @Override // com.google.android.accessibility.talkback.labeling.LabelsFetchRequest.OnLabelsFetchedListener
                public final void onLabelsFetched(List list) {
                    if (list != null && !list.isEmpty()) {
                        ProcessStatsCapture processStatsCapture2 = processStatsCapture;
                        processStatsCapture2.ProcessStatsCapture$ar$foregroundStateCapture.execute(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(processStatsCapture2, list, spannableUtils$IdentifierSpan, 11));
                        return;
                    }
                    Toast.makeText((Context) processStatsCapture.ProcessStatsCapture$ar$context, R.string.label_export_empty, 0).show();
                }
            });
            return;
        }
        if (id == R.id.revert_import) {
            CustomLabelManager customLabelManager = this.labelManager;
            new LabelTask(new RevertImportedLabelsRequest(customLabelManager.client$ar$class_merging$ae701839_0, new RevertImportedLabelsRequest.OnImportLabelsRevertedListener() { // from class: com.google.android.accessibility.talkback.labeling.CustomLabelManager.6
                final /* synthetic */ CustomLabelManager this$0;
                final /* synthetic */ RevertImportedLabelsRequest.OnImportLabelsRevertedListener val$listener;

                public AnonymousClass6(CustomLabelManager customLabelManager2, RevertImportedLabelsRequest.OnImportLabelsRevertedListener onImportLabelsRevertedListener) {
                    r2 = onImportLabelsRevertedListener;
                    r1 = customLabelManager2;
                }

                @Override // com.google.android.accessibility.talkback.labeling.RevertImportedLabelsRequest.OnImportLabelsRevertedListener
                public final void onImportLabelsReverted() {
                    r1.sendCacheRefreshIntent(new String[0]);
                    r2.onImportLabelsReverted();
                }
            }), customLabelManager2.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
        }
    }

    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.label_manager_packages);
        prepareActionBar(null);
        this.packageList = (RecyclerView) findViewById(R.id.package_list);
        this.labelManager = new CustomLabelManager(this);
        this.packageList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        PackageLabelInfoAdapter packageLabelInfoAdapter = new PackageLabelInfoAdapter(this, this, this.labelManager);
        this.packageLabelInfoAdapter = packageLabelInfoAdapter;
        this.packageList.setAdapter(packageLabelInfoAdapter);
        this.packageList.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.labelManager.shutdown();
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        updatePackageSummary();
    }

    public final void updatePackageSummary() {
        new UpdatePackageSummaryTask(getApplicationContext(), this.packageLabelInfoAdapter).execute(new Void[0]);
    }
}
