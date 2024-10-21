package com.google.android.gms.dynamite;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IDynamiteLoaderV2 extends IInterface {
    IObjectWrapper loadModule2(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2);

    IObjectWrapper loadModule2NoCrashUtils(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2);
}
