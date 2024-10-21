package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface LifecycleFragment {
    void addCallback$ar$ds$e7521d11_0(LifecycleCallback lifecycleCallback);

    LifecycleCallback getCallbackOrNull$ar$ds(Class cls);

    Activity getLifecycleActivity();

    void startActivityForResult(Intent intent, int i);
}
