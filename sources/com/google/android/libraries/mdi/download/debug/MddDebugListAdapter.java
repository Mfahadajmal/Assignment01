package com.google.android.libraries.mdi.download.debug;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.libraries.mdi.Download$ClientFileGroup;
import com.google.android.marvin.talkback.R;
import com.google.android.material.datepicker.YearGridAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugListAdapter extends ListAdapter {
    public static final AppCompatDelegateImpl.Api24Impl DIFF_CALLBACK$ar$class_merging = new AppCompatDelegateImpl.Api24Impl(null);
    private final Context context;
    public FloatingActionButton.ShadowDelegateImpl selectListener$ar$class_merging$ar$class_merging;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MddDebugListAdapter(android.content.Context r5) {
        /*
            r4 = this;
            android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder r0 = new android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder
            android.support.v7.app.AppCompatDelegateImpl$Api24Impl r1 = com.google.android.libraries.mdi.download.debug.MddDebugListAdapter.DIFF_CALLBACK$ar$class_merging
            r0.<init>(r1)
            r1 = 0
            r0.mBackgroundThreadExecutor = r1
            java.util.concurrent.Executor r2 = r0.mBackgroundThreadExecutor
            if (r2 != 0) goto L25
            java.lang.Object r2 = android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder.sExecutorLock
            monitor-enter(r2)
            java.util.concurrent.Executor r3 = android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder.sDiffExecutor     // Catch: java.lang.Throwable -> L22
            if (r3 != 0) goto L1c
            r3 = 2
            java.util.concurrent.ExecutorService r3 = java.util.concurrent.Executors.newFixedThreadPool(r3)     // Catch: java.lang.Throwable -> L22
            android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder.sDiffExecutor = r3     // Catch: java.lang.Throwable -> L22
        L1c:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L22
            java.util.concurrent.Executor r2 = android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder.sDiffExecutor
            r0.mBackgroundThreadExecutor = r2
            goto L25
        L22:
            r5 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L22
            throw r5
        L25:
            androidx.work.impl.model.WorkName r2 = new androidx.work.impl.model.WorkName
            java.util.concurrent.Executor r3 = r0.mBackgroundThreadExecutor
            android.support.v7.app.AppCompatDelegateImpl$Api24Impl r0 = r0.mDiffCallback$ar$class_merging
            r2.<init>(r3, r0)
            r4.<init>(r2)
            r4.selectListener$ar$class_merging$ar$class_merging = r1
            r4.context = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.mdi.download.debug.MddDebugListAdapter.<init>(android.content.Context):void");
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        boolean z;
        int i2;
        int i3;
        int i4;
        String str;
        YearGridAdapter.ViewHolder viewHolder2 = (YearGridAdapter.ViewHolder) viewHolder;
        Download$ClientFileGroup download$ClientFileGroup = (Download$ClientFileGroup) getItem(i);
        TextView textView = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.group_name);
        TextView textView2 = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.locale);
        TextView textView3 = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.download_account);
        TextView textView4 = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.variant_id);
        TextView textView5 = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.build_id);
        LinearLayout linearLayout = (LinearLayout) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.variant_container);
        LinearLayout linearLayout2 = (LinearLayout) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.build_container);
        LinearLayout linearLayout3 = (LinearLayout) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.locale_container);
        TextView textView6 = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.download_status);
        TextView textView7 = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.version_number);
        TextView textView8 = (TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView.findViewById(R.id.file_count);
        if ((download$ClientFileGroup.bitField0_ & 1) != 0 && !download$ClientFileGroup.groupName_.isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, (Object) "Invalid Metadata");
        textView.setText(download$ClientFileGroup.groupName_);
        if (true != download$ClientFileGroup.locale_.isEmpty()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        linearLayout3.setVisibility(i2);
        textView2.setText(download$ClientFileGroup.locale_.toString());
        if ((download$ClientFileGroup.bitField0_ & 4) != 0) {
            textView3.setText(download$ClientFileGroup.account_);
        } else {
            textView3.setText(R.string.list_item_no_account);
        }
        if (download$ClientFileGroup.buildId_ == 0) {
            i3 = 8;
        } else {
            i3 = 0;
        }
        linearLayout2.setVisibility(i3);
        textView5.setText(String.format(Locale.ENGLISH, "%d", Long.valueOf(download$ClientFileGroup.buildId_)));
        if (true != download$ClientFileGroup.variantId_.isEmpty()) {
            i4 = 0;
        } else {
            i4 = 8;
        }
        linearLayout.setVisibility(i4);
        textView4.setText(download$ClientFileGroup.variantId_);
        if ((download$ClientFileGroup.bitField0_ & 16) != 0) {
            Download$ClientFileGroup.Status forNumber = Download$ClientFileGroup.Status.forNumber(download$ClientFileGroup.status_);
            if (forNumber == null) {
                forNumber = Download$ClientFileGroup.Status.UNSPECIFIED;
            }
            int i5 = forNumber.value;
            if (i5 != 1) {
                if (i5 != 2) {
                    str = "Unspecified";
                } else {
                    str = "Pending";
                }
            } else {
                str = "Downloaded";
            }
            textView6.setText(str);
        } else {
            textView6.setText(android.R.string.unknownName);
        }
        if ((download$ClientFileGroup.bitField0_ & 8) != 0) {
            textView7.setText(String.format(Locale.ENGLISH, "%d", Integer.valueOf(download$ClientFileGroup.versionNumber_)));
        } else {
            textView7.setText(android.R.string.unknownName);
        }
        Resources resources = this.context.getResources();
        int size = download$ClientFileGroup.file_.size();
        textView8.setText(resources.getQuantityString(R.plurals.list_item_file_count, size, Integer.valueOf(size)));
        viewHolder2.YearGridAdapter$ViewHolder$ar$textView.setOnClickListener(new MddDebugListAdapter$$ExternalSyntheticLambda0(this, i, 0));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new YearGridAdapter.ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.mdd_debug_list_item_view, viewGroup, false));
    }
}
