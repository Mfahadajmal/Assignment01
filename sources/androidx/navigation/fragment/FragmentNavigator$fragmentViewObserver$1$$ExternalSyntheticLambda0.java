package androidx.navigation.fragment;

import android.app.Activity;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final /* synthetic */ Object FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$0 = obj;
        this.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$1 = obj2;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (this.switching_field != 0) {
            if (event == Lifecycle.Event.ON_CREATE) {
                Object obj = this.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$1;
                ((OnBackPressedDispatcher) this.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$0).setOnBackInvokedDispatcher(ComponentActivity.Api33Impl.INSTANCE.getOnBackInvokedDispatcher((Activity) obj));
                return;
            }
            return;
        }
        Object obj2 = this.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$1;
        Object obj3 = this.FragmentNavigator$fragmentViewObserver$1$$ExternalSyntheticLambda0$ar$f$0;
        if (event == Lifecycle.Event.ON_RESUME) {
            Navigator navigator = (Navigator) obj3;
            if (((List) navigator.getState().backStack.getValue()).contains(obj2)) {
                navigator.getState().markTransitionComplete((NavBackStackEntry) obj2);
            }
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            ((Navigator) obj3).getState().markTransitionComplete((NavBackStackEntry) obj2);
        }
    }
}
