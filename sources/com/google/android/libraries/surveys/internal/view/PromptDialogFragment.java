package com.google.android.libraries.surveys.internal.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.surveys.internal.view.PromptDialogDelegate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PromptDialogFragment extends DialogFragment implements PromptDialogDelegate.DialogFragmentInterface {
    private final PromptDialogDelegate delegate = new PromptDialogDelegate(this);

    @Override // com.google.android.libraries.surveys.internal.view.PromptDialogDelegate.DialogFragmentInterface
    public final /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.delegate.onCreate$ar$ds$aa6d3cfc_0();
    }

    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.delegate.onCreateView$ar$ds(viewGroup);
    }

    @Override // android.support.v4.app.Fragment
    public final void onDestroy() {
        this.delegate.onDestroy();
        super.onDestroy();
    }

    @Override // android.support.v4.app.Fragment
    public final void onResume() {
        super.onResume();
        this.delegate.onResume(getView());
    }
}
