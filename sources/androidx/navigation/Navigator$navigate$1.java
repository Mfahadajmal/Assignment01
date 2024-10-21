package androidx.navigation;

import android.content.Context;
import androidx.core.view.ViewParentCompat$Api21Impl;
import androidx.core.view.WindowCompat$Api30Impl;
import androidx.navigation.NavOptions;
import androidx.work.ListenableWorker;
import androidx.work.impl.WorkerStoppedException;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlaysAnimations;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.surveys.SurveyData;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.android.HandlerContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Navigator$navigate$1 extends Lambda implements Function1 {
    final /* synthetic */ Object Navigator$navigate$1$ar$$navOptions;
    final /* synthetic */ Object Navigator$navigate$1$ar$this$0;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Navigator$navigate$1(Object obj, Object obj2, int i) {
        super(1);
        this.switching_field = i;
        this.Navigator$navigate$1$ar$this$0 = obj;
        this.Navigator$navigate$1$ar$$navOptions = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r6v27, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r6v7, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        NavGraph navGraph;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                ((HandlerContext) this.Navigator$navigate$1$ar$this$0).handler.removeCallbacks(this.Navigator$navigate$1$ar$$navOptions);
                                return Unit.INSTANCE;
                            }
                            SurveyData surveyData = (SurveyData) obj;
                            if (surveyData != null) {
                                SpannableUtils$NonCopyableTextSpan.launchActivity$ar$ds((Context) this.Navigator$navigate$1$ar$$navOptions, surveyData);
                            }
                            ((SelectToSpeakPopupActivity) this.Navigator$navigate$1$ar$this$0).closeActivity();
                            return Unit.INSTANCE;
                        }
                        if (!((Boolean) obj).booleanValue()) {
                            ((ControlOverlaysAnimations) this.Navigator$navigate$1$ar$this$0).triggerButtonAppearanceUpdater.invoke(false);
                            ((ControlOverlaysAnimations) this.Navigator$navigate$1$ar$this$0).foregroundUpdater.invoke(this.Navigator$navigate$1$ar$$navOptions);
                        }
                        return Unit.INSTANCE;
                    }
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    ((CancellableContinuationImpl) this.Navigator$navigate$1$ar$this$0).resume(Unit.INSTANCE, null);
                    ?? r0 = this.Navigator$navigate$1$ar$$navOptions;
                    if (r0 != 0) {
                        r0.invoke(bool);
                    }
                    return Unit.INSTANCE;
                }
                Throwable th = (Throwable) obj;
                if (th instanceof WorkerStoppedException) {
                    ((ListenableWorker) this.Navigator$navigate$1$ar$$navOptions).stop(((WorkerStoppedException) th).reason);
                }
                this.Navigator$navigate$1$ar$this$0.cancel(false);
                return Unit.INSTANCE;
            }
            NavOptionsBuilder navOptionsBuilder = (NavOptionsBuilder) obj;
            navOptionsBuilder.getClass();
            ActivityNavigator$hostActivity$1 activityNavigator$hostActivity$1 = ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$95bd8f5f_0;
            AnimBuilder animBuilder = new AnimBuilder();
            activityNavigator$hostActivity$1.invoke(animBuilder);
            int i2 = animBuilder.enter;
            NavOptions.Builder builder = navOptionsBuilder.builder;
            builder.enterAnim = i2;
            builder.exitAnim = animBuilder.exit;
            builder.popEnterAnim = -1;
            builder.popExitAnim = -1;
            Object obj2 = this.Navigator$navigate$1$ar$this$0;
            if (obj2 instanceof NavGraph) {
                Sequence hierarchy$ar$ds = ViewParentCompat$Api21Impl.getHierarchy$ar$ds((NavDestination) obj2);
                Object obj3 = this.Navigator$navigate$1$ar$$navOptions;
                Iterator it = hierarchy$ar$ds.iterator();
                while (true) {
                    if (it.hasNext()) {
                        NavDestination navDestination = (NavDestination) it.next();
                        NavDestination currentDestination = ((NavController) obj3).getCurrentDestination();
                        if (currentDestination != null) {
                            navGraph = currentDestination.parent;
                        } else {
                            navGraph = null;
                        }
                        if (Intrinsics.areEqual(navDestination, navGraph)) {
                            break;
                        }
                    } else {
                        WindowCompat$Api30Impl windowCompat$Api30Impl = NavGraph.Companion$ar$class_merging$ad045ee8_0;
                        int i3 = WindowCompat$Api30Impl.findStartDestination$ar$ds(((NavController) this.Navigator$navigate$1$ar$$navOptions).getGraph()).id;
                        ActivityNavigator$hostActivity$1 activityNavigator$hostActivity$12 = ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$9089ccdf_0;
                        navOptionsBuilder.popUpToId = i3;
                        PopUpToBuilder popUpToBuilder = new PopUpToBuilder();
                        activityNavigator$hostActivity$12.invoke(popUpToBuilder);
                        navOptionsBuilder.saveState = popUpToBuilder.saveState;
                        break;
                    }
                }
            }
            return Unit.INSTANCE;
        }
        NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
        navBackStackEntry.getClass();
        NavDestination navDestination2 = navBackStackEntry.destination;
        if (true != (navDestination2 instanceof NavDestination)) {
            navDestination2 = null;
        }
        if (navDestination2 == null) {
            return null;
        }
        NavDestination navigate$ar$ds = ((Navigator) this.Navigator$navigate$1$ar$this$0).navigate$ar$ds(navDestination2, navBackStackEntry.getArguments(), (NavOptions) this.Navigator$navigate$1$ar$$navOptions);
        if (navigate$ar$ds == null) {
            return null;
        }
        if (Intrinsics.areEqual(navigate$ar$ds, navDestination2)) {
            return navBackStackEntry;
        }
        return ((Navigator) this.Navigator$navigate$1$ar$this$0).getState().createBackStackEntry(navigate$ar$ds, navigate$ar$ds.addInDefaultArgs(navBackStackEntry.getArguments()));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Navigator$navigate$1(Object obj, Object obj2, int i, byte[] bArr) {
        super(1);
        this.switching_field = i;
        this.Navigator$navigate$1$ar$$navOptions = obj;
        this.Navigator$navigate$1$ar$this$0 = obj2;
    }
}
