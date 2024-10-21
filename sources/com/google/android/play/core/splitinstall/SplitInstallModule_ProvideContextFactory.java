package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.apps.common.inject.ApplicationModule;
import dagger.internal.Factory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallModule_ProvideContextFactory implements Factory {
    private final Object SplitInstallModule_ProvideContextFactory$ar$module;
    private final /* synthetic */ int switching_field;

    public SplitInstallModule_ProvideContextFactory(Object obj, int i) {
        this.switching_field = i;
        this.SplitInstallModule_ProvideContextFactory$ar$module = obj;
    }

    @Override // javax.inject.Provider
    public final Context get() {
        if (this.switching_field != 0) {
            Object obj = ((ApplicationModule) this.SplitInstallModule_ProvideContextFactory$ar$module).ApplicationModule$ar$application;
            obj.getClass();
            return (Context) obj;
        }
        Object obj2 = ((SplitInstallModule) this.SplitInstallModule_ProvideContextFactory$ar$module).SplitInstallModule$ar$context;
        obj2.getClass();
        return (Context) obj2;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        return this.switching_field != 0 ? get() : get();
    }
}
