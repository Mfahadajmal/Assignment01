package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import io.grpc.internal.SharedResourceHolder;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SupportLifecycleFragmentImpl extends Fragment implements LifecycleFragment {
    public static final WeakHashMap fragmentByActivity = new WeakHashMap();
    private final SharedResourceHolder.Instance stateRegistry$ar$class_merging = new SharedResourceHolder.Instance();

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback$ar$ds$e7521d11_0(LifecycleCallback lifecycleCallback) {
        this.stateRegistry$ar$class_merging.addCallback$ar$ds$e7521d11_1(lifecycleCallback);
    }

    @Override // android.support.v4.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        this.stateRegistry$ar$class_merging.dump$ar$ds$b2012eff_0();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final LifecycleCallback getCallbackOrNull$ar$ds(Class cls) {
        return this.stateRegistry$ar$class_merging.getCallbackOrNull$ar$ds$eb92a4e_0(cls);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final Activity getLifecycleActivity() {
        return getActivity();
    }

    @Override // android.support.v4.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.stateRegistry$ar$class_merging.onActivityResult(i, i2, intent);
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.stateRegistry$ar$class_merging.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.stateRegistry$ar$class_merging.onDestroy();
    }

    @Override // android.support.v4.app.Fragment
    public final void onResume() {
        super.onResume();
        this.stateRegistry$ar$class_merging.onResume();
    }

    @Override // android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        this.stateRegistry$ar$class_merging.onSaveInstanceState(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public final void onStart() {
        super.onStart();
        this.stateRegistry$ar$class_merging.onStart();
    }

    @Override // android.support.v4.app.Fragment
    public final void onStop() {
        super.onStop();
        this.stateRegistry$ar$class_merging.onStop();
    }
}
