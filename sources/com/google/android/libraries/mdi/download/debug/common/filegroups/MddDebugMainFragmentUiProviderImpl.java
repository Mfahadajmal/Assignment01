package com.google.android.libraries.mdi.download.debug.common.filegroups;

import android.support.v4.app.Fragment;
import com.google.common.base.Supplier;
import com.google.common.flogger.GoogleLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugMainFragmentUiProviderImpl implements MddDebugMainFragmentUiProvider {
    public static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/mdi/download/debug/common/filegroups/MddDebugMainFragmentUiProviderImpl");
    public final MddDebugMainFragmentActionProvider actionProvider;
    public final Fragment fragment;
    private final Supplier listFragmentSupplier;

    public MddDebugMainFragmentUiProviderImpl(Fragment fragment, MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider, Supplier supplier) {
        this.fragment = fragment;
        this.actionProvider = mddDebugMainFragmentActionProvider;
        this.listFragmentSupplier = supplier;
    }
}
