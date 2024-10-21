package com.google.android.play.core.splitinstall;

import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.gms.tasks.Task;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SplitInstallManager {
    Task deferredUninstall(List list);

    Set getInstalledModules();

    Task getSessionStates();

    void registerListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1);

    Task startInstall(SplitInstallRequest splitInstallRequest);

    void unregisterListener$ar$class_merging$c44e3674_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1);
}
