package androidx.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigator;
import androidx.savedstate.SavedStateRegistry;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda2 implements LifecycleEventObserver {
    public final /* synthetic */ Object ComponentActivity$$ExternalSyntheticLambda2$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda2(Object obj, int i) {
        this.switching_field = i;
        this.ComponentActivity$$ExternalSyntheticLambda2$ar$f$0 = obj;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Window window;
        View peekDecorView;
        int i = this.switching_field;
        Object obj = null;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        Object obj2 = this.ComponentActivity$$ExternalSyntheticLambda2$ar$f$0;
                        if (event == Lifecycle.Event.ON_START) {
                            ((SavedStateRegistry) obj2).isAllowingSavingState = true;
                            return;
                        } else {
                            if (event == Lifecycle.Event.ON_STOP) {
                                ((SavedStateRegistry) obj2).isAllowingSavingState = false;
                                return;
                            }
                            return;
                        }
                    }
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        Fragment fragment = (Fragment) lifecycleOwner;
                        Navigator navigator = (Navigator) this.ComponentActivity$$ExternalSyntheticLambda2$ar$f$0;
                        for (Object obj3 : (Iterable) navigator.getState().transitionsInProgress.getValue()) {
                            if (Intrinsics.areEqual(((NavBackStackEntry) obj3).id, fragment.getTag())) {
                                obj = obj3;
                            }
                        }
                        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
                        if (navBackStackEntry != null) {
                            navigator.getState().markTransitionComplete(navBackStackEntry);
                            return;
                        }
                        return;
                    }
                    return;
                }
                Lifecycle.State targetState = event.getTargetState();
                NavController navController = (NavController) this.ComponentActivity$$ExternalSyntheticLambda2$ar$f$0;
                navController.hostLifecycleState = targetState;
                if (navController._graph != null) {
                    Iterator it = navController.backQueue.iterator();
                    while (it.hasNext()) {
                        NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) it.next();
                        navBackStackEntry2.hostLifecycleState = event.getTargetState();
                        navBackStackEntry2.updateState();
                    }
                    return;
                }
                return;
            }
            if (event == Lifecycle.Event.ON_STOP && (window = ((ComponentActivity) this.ComponentActivity$$ExternalSyntheticLambda2$ar$f$0).getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                peekDecorView.cancelPendingInputEvents();
                return;
            }
            return;
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            ComponentActivity componentActivity = (ComponentActivity) this.ComponentActivity$$ExternalSyntheticLambda2$ar$f$0;
            componentActivity.contextAwareHelper.context = null;
            if (!componentActivity.isChangingConfigurations()) {
                componentActivity.getViewModelStore().clear();
            }
            componentActivity.reportFullyDrawnExecutor$ar$class_merging.activityDestroyed();
        }
    }
}
