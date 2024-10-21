package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LifecycleCallback {
    protected final LifecycleFragment mLifecycleFragment;

    /* JADX INFO: Access modifiers changed from: protected */
    public LifecycleCallback(LifecycleFragment lifecycleFragment) {
        this.mLifecycleFragment = lifecycleFragment;
    }

    public final Activity getActivity() {
        Activity lifecycleActivity = this.mLifecycleFragment.getLifecycleActivity();
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(lifecycleActivity);
        return lifecycleActivity;
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onCreate(Bundle bundle) {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }
}
