package androidx.core.view;

import android.view.ViewConfiguration;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavControllerViewModel;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewConfigurationCompat$Api34Impl {
    public static final NavControllerViewModel getInstance$ar$ds(ViewModelStore viewModelStore) {
        return (NavControllerViewModel) new ViewModelProvider(viewModelStore, NavControllerViewModel.FACTORY, (byte[]) null).get(NavControllerViewModel.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getScaledMaximumFlingVelocity(ViewConfiguration viewConfiguration, int i, int i2, int i3) {
        return viewConfiguration.getScaledMaximumFlingVelocity(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getScaledMinimumFlingVelocity(ViewConfiguration viewConfiguration, int i, int i2, int i3) {
        return viewConfiguration.getScaledMinimumFlingVelocity(i, i2, i3);
    }
}
