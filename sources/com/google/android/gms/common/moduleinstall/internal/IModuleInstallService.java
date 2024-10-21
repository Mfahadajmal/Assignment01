package com.google.android.gms.common.moduleinstall.internal;

import android.os.IInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IModuleInstallService extends IInterface {
    void installModules(IModuleInstallCallbacks iModuleInstallCallbacks, ApiFeatureRequest apiFeatureRequest, IModuleInstallStatusListener iModuleInstallStatusListener);
}
