package androidx.navigation.fragment;

import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavigatorState;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentNavigator$attachClearViewModel$1 extends Lambda implements Function0 {
    final /* synthetic */ Object FragmentNavigator$attachClearViewModel$1$ar$$fragment;
    final /* synthetic */ Object FragmentNavigator$attachClearViewModel$1$ar$$state;
    final /* synthetic */ Object FragmentNavigator$attachClearViewModel$1$ar$this$0;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentNavigator$attachClearViewModel$1(DefaultSpecialEffectsController$TransitionEffect defaultSpecialEffectsController$TransitionEffect, ViewGroup viewGroup, Object obj, int i) {
        super(0);
        this.switching_field = i;
        this.FragmentNavigator$attachClearViewModel$1$ar$$fragment = defaultSpecialEffectsController$TransitionEffect;
        this.FragmentNavigator$attachClearViewModel$1$ar$$state = viewGroup;
        this.FragmentNavigator$attachClearViewModel$1$ar$this$0 = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        if (this.switching_field != 0) {
            ((DefaultSpecialEffectsController$TransitionEffect) this.FragmentNavigator$attachClearViewModel$1$ar$$fragment).transitionImpl.beginDelayedTransition((ViewGroup) this.FragmentNavigator$attachClearViewModel$1$ar$$state, this.FragmentNavigator$attachClearViewModel$1$ar$this$0);
            return Unit.INSTANCE;
        }
        NavigatorState navigatorState = (NavigatorState) this.FragmentNavigator$attachClearViewModel$1$ar$$state;
        Iterator it = ((Iterable) navigatorState.transitionsInProgress.getValue()).iterator();
        while (it.hasNext()) {
            navigatorState.markTransitionComplete((NavBackStackEntry) it.next());
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentNavigator$attachClearViewModel$1(NavigatorState navigatorState, FragmentNavigator fragmentNavigator, Fragment fragment, int i) {
        super(0);
        this.switching_field = i;
        this.FragmentNavigator$attachClearViewModel$1$ar$$state = navigatorState;
        this.FragmentNavigator$attachClearViewModel$1$ar$this$0 = fragmentNavigator;
        this.FragmentNavigator$attachClearViewModel$1$ar$$fragment = fragment;
    }
}
