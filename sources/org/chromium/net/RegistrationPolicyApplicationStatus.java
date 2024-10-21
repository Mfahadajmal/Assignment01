package org.chromium.net;

import org.chromium.base.ApplicationStatus;
import org.chromium.base.ObserverList;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RegistrationPolicyApplicationStatus extends NetworkChangeNotifierAutoDetect.RegistrationPolicy implements ApplicationStatus.ApplicationStateListener {
    private boolean mDestroyed;

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    protected void destroy() {
        if (this.mDestroyed) {
            return;
        }
        ObserverList observerList = ApplicationStatus.sApplicationStateListeners;
        if (observerList != null) {
            observerList.removeObserver(this);
        }
        this.mDestroyed = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    public void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        super.init(networkChangeNotifierAutoDetect);
        ApplicationStatus.registerApplicationStateListener(this);
        onApplicationStateChange(0);
    }

    public void onApplicationStateChange(int i) {
        ApplicationStatus.hasVisibleActivities();
        unregister();
    }
}
