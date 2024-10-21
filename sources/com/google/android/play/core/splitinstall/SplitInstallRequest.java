package com.google.android.play.core.splitinstall;

import com.google.mlkit.common.model.RemoteModelManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitInstallRequest {
    public final List languages;
    public final List moduleNames;

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Collection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.Collection, java.lang.Object] */
    public SplitInstallRequest(RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration) {
        this.moduleNames = new ArrayList((Collection) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass);
        this.languages = new ArrayList((Collection) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider);
    }

    public final String toString() {
        return String.format("SplitInstallRequest{modulesNames=%s,languages=%s}", this.moduleNames, this.languages);
    }
}
