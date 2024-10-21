package androidx.core.view;

import android.view.View;
import android.view.Window;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowCompat$Api30Impl {
    public static final NavDestination findStartDestination$ar$ds(NavGraph navGraph) {
        navGraph.getClass();
        Iterator it = OnDeviceSmartReplyLogEvent.generateSequence(navGraph.findNode(navGraph.startDestId), ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$16b5d130_0).iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = it.next();
            }
            return (NavDestination) next;
        }
        throw new NoSuchElementException("Sequence is empty.");
    }

    public static void setDecorFitsSystemWindows(Window window, boolean z) {
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 256);
        window.setDecorFitsSystemWindows(z);
    }
}
