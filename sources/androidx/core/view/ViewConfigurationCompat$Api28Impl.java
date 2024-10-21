package androidx.core.view;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewConfiguration;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavViewModelStoreProvider;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewConfigurationCompat$Api28Impl {
    public static final NavBackStackEntry create$ar$ds(Context context, NavDestination navDestination, Bundle bundle, Lifecycle.State state, NavViewModelStoreProvider navViewModelStoreProvider, String str, Bundle bundle2) {
        state.getClass();
        str.getClass();
        return new NavBackStackEntry(context, navDestination, bundle, state, navViewModelStoreProvider, str, bundle2);
    }

    public static /* synthetic */ NavBackStackEntry create$default$ar$ds$ar$class_merging(ViewConfigurationCompat$Api28Impl viewConfigurationCompat$Api28Impl, Context context, NavDestination navDestination, Bundle bundle, Lifecycle.State state, NavViewModelStoreProvider navViewModelStoreProvider) {
        String uuid = UUID.randomUUID().toString();
        uuid.getClass();
        return create$ar$ds(context, navDestination, bundle, state, navViewModelStoreProvider, uuid, null);
    }

    static int getScaledHoverSlop(ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledHoverSlop();
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewConfiguration) {
        return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
    }
}
