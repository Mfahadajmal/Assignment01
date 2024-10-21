package androidx.navigation;

import _COROUTINE._BOUNDARY;
import android.os.Bundle;
import androidx.navigation.Navigator;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
@Navigator.Name("navigation")
/* loaded from: classes.dex */
public class NavGraphNavigator extends Navigator<NavGraph> {
    private final NavigatorProvider navigatorProvider;

    public NavGraphNavigator(NavigatorProvider navigatorProvider) {
        navigatorProvider.getClass();
        this.navigatorProvider = navigatorProvider;
    }

    @Override // androidx.navigation.Navigator
    public final /* synthetic */ NavDestination createDestination() {
        return new NavGraph(this);
    }

    @Override // androidx.navigation.Navigator
    public final void navigate$ar$ds$c4edcefd_0(List list, NavOptions navOptions) {
        list.getClass();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) it.next();
            NavDestination navDestination = navBackStackEntry.destination;
            navDestination.getClass();
            NavGraph navGraph = (NavGraph) navDestination;
            Bundle arguments = navBackStackEntry.getArguments();
            int i = navGraph.startDestId;
            if (i != 0) {
                NavDestination findNode = navGraph.findNode(i, false);
                if (findNode == null) {
                    if (navGraph.startDestIdName == null) {
                        navGraph.startDestIdName = String.valueOf(navGraph.startDestId);
                    }
                    String str = navGraph.startDestIdName;
                    str.getClass();
                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "navigation destination ", " is not a direct child of this NavGraph"));
                }
                this.navigatorProvider.getNavigator(findNode.navigatorName).navigate$ar$ds$c4edcefd_0(OnDeviceLanguageIdentificationLogEvent.listOf(getState().createBackStackEntry(findNode, findNode.addInDefaultArgs(arguments))), navOptions);
            } else {
                throw new IllegalStateException("no start destination defined via app:startDestination for ".concat(String.valueOf(navGraph.getDisplayName())));
            }
        }
    }
}
