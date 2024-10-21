package com.google.android.play.core.splitinstall;

import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.gms.tasks.Task;
import dagger.Lazy;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LazySplitInstallManager implements SplitInstallManager {
    private final Lazy fakeSplitInstallManagerLazy;
    private final Lazy localTestingDirectory;
    private final Lazy splitInstallManagerLazy;

    public LazySplitInstallManager(Lazy lazy, Lazy lazy2, Lazy lazy3) {
        this.splitInstallManagerLazy = lazy;
        this.fakeSplitInstallManagerLazy = lazy2;
        this.localTestingDirectory = lazy3;
    }

    private final SplitInstallManager getManager() {
        if (this.localTestingDirectory.get() != null) {
            return (SplitInstallManager) this.fakeSplitInstallManagerLazy.get();
        }
        return (SplitInstallManager) this.splitInstallManagerLazy.get();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task deferredUninstall(List list) {
        return getManager().deferredUninstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set getInstalledModules() {
        return getManager().getInstalledModules();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task getSessionStates() {
        return getManager().getSessionStates();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        getManager().registerListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task startInstall(SplitInstallRequest splitInstallRequest) {
        return getManager().startInstall(splitInstallRequest);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        getManager().unregisterListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(hapticPatternParser$$ExternalSyntheticLambda1);
    }
}
