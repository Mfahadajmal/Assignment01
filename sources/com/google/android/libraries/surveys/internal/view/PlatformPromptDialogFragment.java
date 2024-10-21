package com.google.android.libraries.surveys.internal.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.surveys.internal.view.PromptDialogDelegate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PlatformPromptDialogFragment extends DialogFragment implements PromptDialogDelegate.DialogFragmentInterface {
    private final PromptDialogDelegate delegate = new PromptDialogDelegate(this);

    @Override // android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.delegate.onCreate$ar$ds$aa6d3cfc_0();
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.delegate.onCreateView$ar$ds(viewGroup);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        this.delegate.onDestroy();
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.delegate.onResume(getView());
    }
}
