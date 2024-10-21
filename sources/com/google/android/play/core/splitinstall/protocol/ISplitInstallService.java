package com.google.android.play.core.splitinstall.protocol;

import android.os.Bundle;
import android.os.IInterface;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ISplitInstallService extends IInterface {
    void deferredUninstall(String str, List list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback);

    void getSessionStates_deprecated(String str, ISplitInstallServiceCallback iSplitInstallServiceCallback);

    void startInstall(String str, List list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback);
}
