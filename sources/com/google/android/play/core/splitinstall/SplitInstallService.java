package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.play.core.serviceconnection.ServiceConnectionManager;
import com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback;
import com.google.android.play.core.util.PhoneskyVerificationUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallService {
    public ServiceConnectionManager connectionManager;
    public final String packageName;
    public static final SplitInstallModule logger$ar$class_merging$ceb098d3_0$ar$class_merging = new SplitInstallModule("SplitInstallService");
    private static final Intent SERVICE_INTENT = new Intent("com.google.android.play.core.splitinstall.BIND_SPLIT_INSTALL_SERVICE").setPackage("com.android.vending");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnDeferredUninstallCallback extends ISplitInstallServiceCallback.Stub {
        public OnDeferredUninstallCallback(SplitInstallService splitInstallService, AppLifecycleMonitor appLifecycleMonitor) {
            super(splitInstallService, appLifecycleMonitor);
        }

        @Override // com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback.Stub
        public final void onDeferredUninstall(Bundle bundle) {
            super.onDeferredUninstall(bundle);
            this.source$ar$class_merging$6cd5309_0$ar$class_merging.trySetResult(null);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnGetSessionStatesCallback extends ISplitInstallServiceCallback.Stub {
        public OnGetSessionStatesCallback(SplitInstallService splitInstallService, AppLifecycleMonitor appLifecycleMonitor) {
            super(splitInstallService, appLifecycleMonitor);
        }

        @Override // com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback.Stub
        public final void onGetSessionStates(List list) {
            super.onGetSessionStates(list);
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(SplitInstallSessionState.fromBundle((Bundle) it.next()));
            }
            this.source$ar$class_merging$6cd5309_0$ar$class_merging.trySetResult(arrayList);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnStartInstallCallback extends ISplitInstallServiceCallback.Stub {
        public OnStartInstallCallback(SplitInstallService splitInstallService, AppLifecycleMonitor appLifecycleMonitor) {
            super(splitInstallService, appLifecycleMonitor);
        }

        @Override // com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback.Stub
        public final void onStartInstall(int i, Bundle bundle) {
            super.onStartInstall(i, bundle);
            this.source$ar$class_merging$6cd5309_0$ar$class_merging.trySetResult(Integer.valueOf(i));
        }
    }

    public SplitInstallService(Context context) {
        this.packageName = context.getPackageName();
        if (PhoneskyVerificationUtils.isPhoneskyInstalled(context)) {
            this.connectionManager = new ServiceConnectionManager(NativeLibraryPathListMutex.getApplicationContext(context), logger$ar$class_merging$ceb098d3_0$ar$class_merging, SERVICE_INTENT, new NativeLibraryPathListMutex());
        }
    }

    public static Bundle createOptionsBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 20100);
        return bundle;
    }

    public static Task createPhoneskyNotFoundExceptionTask() {
        logger$ar$class_merging$ceb098d3_0$ar$class_merging.e$ar$ds$cdf76eb7_0("onError(%d)", -14);
        return SpannableUtils$NonCopyableTextSpan.forException(new SplitInstallException(-14));
    }

    public static ArrayList makeModuleNameBundles(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Bundle bundle = new Bundle();
            bundle.putString("module_name", str);
            arrayList.add(bundle);
        }
        return arrayList;
    }
}
