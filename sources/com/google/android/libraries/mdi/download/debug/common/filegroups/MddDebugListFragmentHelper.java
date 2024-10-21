package com.google.android.libraries.mdi.download.debug.common.filegroups;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugListFragmentHelper implements MddDebugListFragmentActionProvider, SwipeRefreshLayout.OnRefreshListener {
    public final MddDebugListFragmentActionProvider actionProvider;
    public final MddDebugListFragmentUiProviderImpl uiProvider;

    public MddDebugListFragmentHelper(MddDebugListFragmentUiProviderImpl mddDebugListFragmentUiProviderImpl, MddDebugListFragmentActionProvider mddDebugListFragmentActionProvider) {
        this.actionProvider = mddDebugListFragmentActionProvider;
        this.uiProvider = mddDebugListFragmentUiProviderImpl;
    }

    @Override // com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentActionProvider
    public final void updateFileGroupList() {
        throw null;
    }
}
