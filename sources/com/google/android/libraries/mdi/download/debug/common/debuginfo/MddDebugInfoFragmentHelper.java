package com.google.android.libraries.mdi.download.debug.common.debuginfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugInfoFragmentHelper implements MddDebugInfoFragmentActionProvider, MddDebugInfoFragmentUiProvider {
    public final MddDebugInfoFragmentActionProvider actionProvider;
    public final MddDebugInfoFragmentUiProvider uiProvider;

    public MddDebugInfoFragmentHelper(MddDebugInfoFragmentUiProvider mddDebugInfoFragmentUiProvider, MddDebugInfoFragmentActionProvider mddDebugInfoFragmentActionProvider) {
        this.uiProvider = mddDebugInfoFragmentUiProvider;
        this.actionProvider = mddDebugInfoFragmentActionProvider;
    }
}
