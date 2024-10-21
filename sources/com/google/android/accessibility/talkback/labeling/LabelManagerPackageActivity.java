package com.google.android.accessibility.talkback.labeling;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.preference.Preference;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.preference.BasePreferencesActivity;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LabelManagerPackageActivity extends BasePreferencesActivity {
    public ListView labelList;
    public ExecutionList.RunnableExecutorPair labelProviderClient$ar$class_merging;
    public String packageName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LabelAdapter extends ArrayAdapter {
        private final LayoutInflater layoutInflater;

        public LabelAdapter(Context context, List list) {
            super(context, R.layout.label_manager_label_row, list);
            this.layoutInflater = (LayoutInflater) LabelManagerPackageActivity.this.getSystemService("layout_inflater");
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.layoutInflater.inflate(R.layout.label_manager_label_row, viewGroup, false);
            }
            Label label = (Label) getItem(i);
            if (label == null) {
                return view;
            }
            ((TextView) view.findViewById(R.id.label_text)).setText(label.mText);
            ((TextView) view.findViewById(R.id.label_timestamp)).setText(LabelManagerPackageActivity.this.getString(R.string.label_manager_timestamp_text, new Object[]{new SimpleDateFormat().format(new Date(label.mTimestampMillis))}));
            view.setOnClickListener(new LabelManagerSummaryActivity$PackageLabelInfoAdapter$PackageLabelViewHolder$$ExternalSyntheticLambda0(this, label, 1, null));
            return view;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UpdateLabelsTask extends AsyncTask {
        private String locale;

        public UpdateLabelsTask() {
        }

        @Override // android.os.AsyncTask
        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            LogUtils.v("LabelManagerPackageAct", "Spawning new UpdateLabelsTask(%d) for (%s, %s).", Integer.valueOf(hashCode()), LabelManagerPackageActivity.this.packageName, this.locale);
            LabelManagerPackageActivity labelManagerPackageActivity = LabelManagerPackageActivity.this;
            return new ArrayList(labelManagerPackageActivity.labelProviderClient$ar$class_merging.getLabelsForPackage(labelManagerPackageActivity.packageName, this.locale, Preference.DEFAULT_ORDER).values());
        }

        @Override // android.os.AsyncTask
        protected final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
            LabelAdapter labelAdapter;
            List list = (List) obj;
            if (LabelManagerPackageActivity.this.labelList.getAdapter() instanceof LabelAdapter) {
                labelAdapter = (LabelAdapter) LabelManagerPackageActivity.this.labelList.getAdapter();
            } else {
                labelAdapter = null;
            }
            if (labelAdapter == null) {
                LabelManagerPackageActivity labelManagerPackageActivity = LabelManagerPackageActivity.this;
                labelManagerPackageActivity.labelList.setAdapter((ListAdapter) new LabelAdapter(labelManagerPackageActivity, list));
            } else {
                labelAdapter.clear();
                labelAdapter.addAll(list);
                labelAdapter.notifyDataSetChanged();
            }
        }

        @Override // android.os.AsyncTask
        protected final void onPreExecute() {
            this.locale = SpannableUtils$IdentifierSpan.getDefaultLocale();
        }
    }

    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        Drawable defaultActivityIcon;
        CharSequence charSequence;
        super.onCreate(bundle);
        super.setContentView(R.layout.label_manager_labels);
        Intent intent = getIntent();
        if (intent.hasExtra("packageName")) {
            this.packageName = intent.getStringExtra("packageName");
            PackageManager packageManager = getPackageManager();
            try {
                defaultActivityIcon = packageManager.getApplicationIcon(this.packageName);
                charSequence = packageManager.getApplicationLabel(packageManager.getPackageInfo(this.packageName, 0).applicationInfo);
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtils.i("LabelManagerPackageAct", "Could not load package info for package %s.", this.packageName);
                defaultActivityIcon = packageManager.getDefaultActivityIcon();
                charSequence = this.packageName;
            }
            setTitle(getString(R.string.label_manager_package_title, new Object[]{charSequence}));
            prepareActionBar(defaultActivityIcon);
            this.labelList = (ListView) findViewById(R.id.label_list);
            this.labelProviderClient$ar$class_merging = new ExecutionList.RunnableExecutorPair((Context) this);
            return;
        }
        throw new IllegalArgumentException("Intent missing package name extra.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.labelProviderClient$ar$class_merging.shutdown();
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
        new UpdateLabelsTask().execute(new Void[0]);
    }
}
