package com.google.android.libraries.mdi.download.debug.common.filegroups;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.libraries.mdi.download.debug.MddDebugListAdapter;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProvider;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugListFragmentUiProviderImpl implements SwipeRefreshLayout.OnRefreshListener, MddDebugListFragmentActionProvider.UiCallbacks {
    public final MddDebugListFragmentActionProvider actionProvider;
    public EditText filterField;
    public final Fragment fragment;
    public MddDebugListAdapter listAdapter;
    public TextView listStatusTextView;
    public RecyclerView recyclerView;
    public SwipeRefreshLayout swipeRefreshLayout;

    public MddDebugListFragmentUiProviderImpl(Fragment fragment, MddDebugListFragmentActionProvider mddDebugListFragmentActionProvider) {
        this.actionProvider = mddDebugListFragmentActionProvider;
        this.fragment = fragment;
    }

    @Override // com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProvider.UiCallbacks
    public final void onShowFileGroupStatus(String str) {
        this.fragment.requireActivity().runOnUiThread(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, str, 1));
    }
}
