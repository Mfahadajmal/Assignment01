package com.google.mlkit.common.sdkinternal;

import _COROUTINE._BOUNDARY;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRuntime;
import com.google.mlkit.common.internal.MlKitComponentDiscoveryService;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.shared.logger.firelog.FirelogLoggingTransport$$ExternalSyntheticLambda1;
import com.google.protobuf.MapEntryLite$Metadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MlKitContext {
    public static MlKitContext instance;
    public static final Object lock = new Object();
    private ComponentRuntime componentRuntime;

    private MlKitContext() {
    }

    public static MlKitContext getInstance() {
        boolean z;
        MlKitContext mlKitContext;
        synchronized (lock) {
            if (instance != null) {
                z = true;
            } else {
                z = false;
            }
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(z, "MlKitContext has not been initialized");
            mlKitContext = instance;
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(mlKitContext);
        }
        return mlKitContext;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object, java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.util.Collection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7, types: [com.google.firebase.components.ComponentRegistrarProcessor, java.lang.Object] */
    public static void initialize$ar$ds$b6efebf2_0(Context context) {
        boolean z;
        List arrayList;
        HashMap hashMap;
        Object obj = lock;
        synchronized (obj) {
            Executor executor = TaskExecutors.MAIN_THREAD;
            synchronized (obj) {
                int i = 1;
                if (instance == null) {
                    z = true;
                } else {
                    z = false;
                }
                StrictModeUtils$VmPolicyBuilderCompatS.checkState(z, "MlKitContext is already initialized");
                MlKitContext mlKitContext = new MlKitContext();
                instance = mlKitContext;
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                Bundle bundle = null;
                RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = new RemoteModelManager.RemoteModelManagerRegistration(context, new SplitInstallSharedPreferences(MlKitComponentDiscoveryService.class, null));
                ArrayList arrayList2 = new ArrayList();
                Object obj2 = remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider;
                Object obj3 = remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;
                try {
                    PackageManager packageManager = ((Context) obj3).getPackageManager();
                    if (packageManager == null) {
                        Log.w("ComponentDiscovery", "Context has no PackageManager.");
                    } else {
                        ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName((Context) obj3, (Class<?>) ((SplitInstallSharedPreferences) obj2).SplitInstallSharedPreferences$ar$context), BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                        if (serviceInfo == null) {
                            Log.w("ComponentDiscovery", ((SplitInstallSharedPreferences) obj2).SplitInstallSharedPreferences$ar$context.toString() + " has no service info.");
                        } else {
                            bundle = serviceInfo.metaData;
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.w("ComponentDiscovery", "Application info not found.");
                }
                if (bundle == null) {
                    Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                    arrayList = Collections.emptyList();
                } else {
                    arrayList = new ArrayList();
                    for (String str : bundle.keySet()) {
                        if ("com.google.firebase.components.ComponentRegistrar".equals(bundle.get(str)) && str.startsWith("com.google.firebase.components:")) {
                            arrayList.add(str.substring(31));
                        }
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new FirelogLoggingTransport$$ExternalSyntheticLambda1((String) it.next(), i));
                }
                MapEntryLite$Metadata mapEntryLite$Metadata = new MapEntryLite$Metadata(executor);
                mapEntryLite$Metadata.defaultValue.addAll(arrayList2);
                mapEntryLite$Metadata.addComponent$ar$ds(Component.of(context, Context.class, new Class[0]));
                mapEntryLite$Metadata.addComponent$ar$ds(Component.of(mlKitContext, MlKitContext.class, new Class[0]));
                ComponentRuntime componentRuntime = new ComponentRuntime(mapEntryLite$Metadata.MapEntryLite$Metadata$ar$valueType, mapEntryLite$Metadata.defaultValue, mapEntryLite$Metadata.defaultKey, mapEntryLite$Metadata.MapEntryLite$Metadata$ar$keyType);
                mlKitContext.componentRuntime = componentRuntime;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(componentRuntime.eagerComponentsInitializedWith, true)) {
                    synchronized (componentRuntime) {
                        hashMap = new HashMap(componentRuntime.components);
                    }
                    componentRuntime.doInitializeEagerComponents$ar$ds(hashMap);
                }
            }
        }
    }

    public final Object get(Class cls) {
        boolean z;
        if (instance == this) {
            z = true;
        } else {
            z = false;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkState(z, "MlKitContext has been deleted");
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(this.componentRuntime);
        return ContextDataProvider.$default$get(this.componentRuntime, cls);
    }

    public final Context getApplicationContext() {
        return (Context) get(Context.class);
    }
}
