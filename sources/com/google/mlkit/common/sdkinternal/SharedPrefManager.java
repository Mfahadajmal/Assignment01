package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.firebase.components.Component;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.internal.CommonComponentRegistrar$$ExternalSyntheticLambda1;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedPrefManager {
    public static final Component COMPONENT;
    protected final Context applicationContext;

    static {
        Component.Builder builder = Component.builder(SharedPrefManager.class);
        builder.add$ar$ds$327096f_0(new Dependency(MlKitContext.class, 1, 0));
        builder.add$ar$ds$327096f_0(new Dependency(Context.class, 1, 0));
        builder.factory$ar$ds(new CommonComponentRegistrar$$ExternalSyntheticLambda1(8));
        COMPONENT = builder.build();
    }

    public SharedPrefManager(Context context) {
        this.applicationContext = context;
    }

    public final synchronized String getMlSdkInstanceId() {
        String string = getSharedPreferences().getString("ml_sdk_instance_id", null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        getSharedPreferences().edit().putString("ml_sdk_instance_id", uuid).apply();
        return uuid;
    }

    protected final SharedPreferences getSharedPreferences() {
        return this.applicationContext.getSharedPreferences("com.google.mlkit.internal", 0);
    }
}
