package com.google.android.play.core.splitinstall;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.play.core.serviceconnection.SafeRunnable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallManagerImpl implements SplitInstallManager {
    public final SplitInstallListenerRegistry listenerRegistry;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private final SplitInstallService service;
    private final SplitInstallInfoProvider splitInstallInfoProvider;
    private final SplitInstallSharedPreferences splitInstallSharedPreferences;

    public SplitInstallManagerImpl(SplitInstallService splitInstallService, SplitInstallListenerRegistry splitInstallListenerRegistry, SplitInstallInfoProvider splitInstallInfoProvider, SplitInstallSharedPreferences splitInstallSharedPreferences) {
        this.service = splitInstallService;
        this.listenerRegistry = splitInstallListenerRegistry;
        this.splitInstallInfoProvider = splitInstallInfoProvider;
        this.splitInstallSharedPreferences = splitInstallSharedPreferences;
    }

    public static List getLanguageNames(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Locale) it.next()).toLanguageTag());
        }
        return arrayList;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task deferredUninstall(final List list) {
        Object obj;
        SplitInstallSharedPreferences splitInstallSharedPreferences = this.splitInstallSharedPreferences;
        synchronized (SplitInstallSharedPreferences.class) {
            HashSet hashSet = new HashSet(splitInstallSharedPreferences.getModulesToUninstallIfEmulated());
            Iterator it = list.iterator();
            boolean z = false;
            while (it.hasNext()) {
                z |= hashSet.add((String) it.next());
            }
            if (z) {
                try {
                    splitInstallSharedPreferences.getSharedPreferences().edit().putStringSet("modules_to_uninstall_if_emulated", hashSet).apply();
                } catch (Exception unused) {
                }
            }
        }
        final SplitInstallService splitInstallService = this.service;
        if (splitInstallService.connectionManager == null) {
            obj = SplitInstallService.createPhoneskyNotFoundExceptionTask();
        } else {
            final AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((short[]) null);
            splitInstallService.connectionManager.bind$ar$class_merging$ar$class_merging(new SafeRunnable(appLifecycleMonitor) { // from class: com.google.android.play.core.splitinstall.SplitInstallService.2
                final /* synthetic */ SplitInstallService this$0;
                final /* synthetic */ List val$moduleNames;
                final /* synthetic */ AppLifecycleMonitor val$source$ar$class_merging$ar$class_merging;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(final SplitInstallService splitInstallService2, final AppLifecycleMonitor appLifecycleMonitor2, final List list2, final AppLifecycleMonitor appLifecycleMonitor22) {
                    super(appLifecycleMonitor22);
                    r3 = list2;
                    r4 = appLifecycleMonitor22;
                    r1 = splitInstallService2;
                }

                /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.play.core.splitinstall.protocol.ISplitInstallService, android.os.IInterface] */
                @Override // com.google.android.play.core.serviceconnection.SafeRunnable
                protected final void unsafeRun() {
                    try {
                        SplitInstallService splitInstallService2 = r1;
                        splitInstallService2.connectionManager.service.deferredUninstall(splitInstallService2.packageName, SplitInstallService.makeModuleNameBundles(r3), SplitInstallService.createOptionsBundle(), new OnDeferredUninstallCallback(r1, r4));
                    } catch (RemoteException e) {
                        SplitInstallService.logger$ar$class_merging$ceb098d3_0$ar$class_merging.e$ar$ds$fb17e3b8_0(e, "deferredUninstall(%s)", r3);
                        r4.trySetException(new RuntimeException(e));
                    }
                }
            }, appLifecycleMonitor22);
            obj = appLifecycleMonitor22.AppLifecycleMonitor$ar$tracker;
        }
        return (Task) obj;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set getInstalledModules() {
        return this.splitInstallInfoProvider.getInstalledAndEmulatedModules();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task getSessionStates() {
        Object obj;
        final SplitInstallService splitInstallService = this.service;
        if (splitInstallService.connectionManager == null) {
            obj = SplitInstallService.createPhoneskyNotFoundExceptionTask();
        } else {
            final AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((short[]) null);
            splitInstallService.connectionManager.bind$ar$class_merging$ar$class_merging(new SafeRunnable(appLifecycleMonitor) { // from class: com.google.android.play.core.splitinstall.SplitInstallService.7
                final /* synthetic */ SplitInstallService this$0;
                final /* synthetic */ AppLifecycleMonitor val$source$ar$class_merging$ar$class_merging;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass7(final SplitInstallService splitInstallService2, final AppLifecycleMonitor appLifecycleMonitor2, final AppLifecycleMonitor appLifecycleMonitor22) {
                    super(appLifecycleMonitor22);
                    r3 = appLifecycleMonitor22;
                    r1 = splitInstallService2;
                }

                /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.play.core.splitinstall.protocol.ISplitInstallService, android.os.IInterface] */
                @Override // com.google.android.play.core.serviceconnection.SafeRunnable
                protected final void unsafeRun() {
                    try {
                        SplitInstallService splitInstallService2 = r1;
                        splitInstallService2.connectionManager.service.getSessionStates_deprecated(splitInstallService2.packageName, new OnGetSessionStatesCallback(splitInstallService2, r3));
                    } catch (RemoteException e) {
                        SplitInstallService.logger$ar$class_merging$ceb098d3_0$ar$class_merging.e$ar$ds$fb17e3b8_0(e, "getSessionStates", new Object[0]);
                        r3.trySetException(new RuntimeException(e));
                    }
                }
            }, appLifecycleMonitor22);
            obj = appLifecycleMonitor22.AppLifecycleMonitor$ar$tracker;
        }
        return (Task) obj;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void registerListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.listenerRegistry.registerListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0096, code lost:
    
        if (r8.containsAll(r3) != false) goto L27;
     */
    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.tasks.Task startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest r12) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.SplitInstallManagerImpl.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.gms.tasks.Task");
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void unregisterListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.listenerRegistry.unregisterListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
    }
}
