package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.collect.Range;
import com.google.common.flogger.context.ContextDataProvider;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallSharedPreferences {
    public final Object SplitInstallSharedPreferences$ar$context;

    /* renamed from: -$$Nest$fgetkey$ar$ds$ar$class_merging$ar$class_merging, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m213$$Nest$fgetkey$ar$ds$ar$class_merging$ar$class_merging(SplitInstallSharedPreferences splitInstallSharedPreferences) {
        Object obj = splitInstallSharedPreferences.SplitInstallSharedPreferences$ar$context;
    }

    public SplitInstallSharedPreferences(Object obj) {
        this.SplitInstallSharedPreferences$ar$context = obj;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List, java.lang.Object] */
    public final void add$ar$ds$52de16dd_0(Range range) {
        ContextDataProvider.checkArgument(!range.isEmpty(), "range must not be empty, but was %s", range);
        this.SplitInstallSharedPreferences$ar$context.add(range);
    }

    public final Set getModulesToUninstallIfEmulated() {
        Set<String> hashSet;
        synchronized (SplitInstallSharedPreferences.class) {
            try {
                hashSet = getSharedPreferences().getStringSet("modules_to_uninstall_if_emulated", new HashSet());
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                }
            } catch (Exception unused) {
                hashSet = new HashSet<>();
            }
        }
        return hashSet;
    }

    public final SharedPreferences getSharedPreferences() {
        return ((Context) this.SplitInstallSharedPreferences$ar$context).getSharedPreferences("playcore_split_install_internal", 0);
    }

    public final void set(Object obj, Object obj2) {
        try {
            ((Field) this.SplitInstallSharedPreferences$ar$context).set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public SplitInstallSharedPreferences(Object obj, byte[] bArr) {
        this.SplitInstallSharedPreferences$ar$context = obj;
    }

    public SplitInstallSharedPreferences() {
        this.SplitInstallSharedPreferences$ar$context = new ArrayList();
    }

    public SplitInstallSharedPreferences(Field field) {
        this.SplitInstallSharedPreferences$ar$context = field;
        field.setAccessible(true);
    }
}
