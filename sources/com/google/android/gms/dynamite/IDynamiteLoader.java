package com.google.android.gms.dynamite;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IDynamiteLoader extends IInterface {
    IObjectWrapper createModuleContext(IObjectWrapper iObjectWrapper, String str, int i);

    IObjectWrapper createModuleContext3NoCrashUtils(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2);

    IObjectWrapper createModuleContextNoCrashUtils(IObjectWrapper iObjectWrapper, String str, int i);

    int getIDynamiteLoaderVersion();

    int getModuleVersion2(IObjectWrapper iObjectWrapper, String str, boolean z);

    int getModuleVersion2NoCrashUtils(IObjectWrapper iObjectWrapper, String str, boolean z);

    IObjectWrapper queryForDynamiteModuleNoCrashUtils(IObjectWrapper iObjectWrapper, String str, boolean z, long j);
}
