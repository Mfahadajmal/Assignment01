package androidx.navigation;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.sqlite.SQLiteStatement;
import androidx.work.impl.constraints.controllers.ConstraintController;
import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActivityNavigator$hostActivity$1 extends Lambda implements Function1 {
    private final /* synthetic */ int switching_field;
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$3cb683b7_0 = new ActivityNavigator$hostActivity$1(20);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$86d7b783_0 = new ActivityNavigator$hostActivity$1(19);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$226cffef_0 = new ActivityNavigator$hostActivity$1(18);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$1faf1460_0 = new ActivityNavigator$hostActivity$1(17);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$2c410286_0 = new ActivityNavigator$hostActivity$1(16);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$561cc4db_0 = new ActivityNavigator$hostActivity$1(15);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$e724a7f4_0 = new ActivityNavigator$hostActivity$1(14);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$a8ba3a7a_0 = new ActivityNavigator$hostActivity$1(13);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$4aa1c371_0 = new ActivityNavigator$hostActivity$1(12);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$9d1256f6_0 = new ActivityNavigator$hostActivity$1(11);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$c2132036_0 = new ActivityNavigator$hostActivity$1(10);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$16b5d130_0 = new ActivityNavigator$hostActivity$1(9);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$cf812260_0 = new ActivityNavigator$hostActivity$1(8);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$9089ccdf_0 = new ActivityNavigator$hostActivity$1(7);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$95bd8f5f_0 = new ActivityNavigator$hostActivity$1(6);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$3fc7c434_0 = new ActivityNavigator$hostActivity$1(5);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$18898783_0 = new ActivityNavigator$hostActivity$1(4);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$91ece90d_0 = new ActivityNavigator$hostActivity$1(3);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$ba4de846_0 = new ActivityNavigator$hostActivity$1(2);
    public static final ActivityNavigator$hostActivity$1 INSTANCE$ar$class_merging$8a2ce234_0 = new ActivityNavigator$hostActivity$1(1);
    public static final ActivityNavigator$hostActivity$1 INSTANCE = new ActivityNavigator$hostActivity$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityNavigator$hostActivity$1(int i) {
        super(1);
        this.switching_field = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        ViewGroup viewGroup;
        switch (this.switching_field) {
            case 0:
                Context context = (Context) obj;
                context.getClass();
                if (!(context instanceof ContextWrapper)) {
                    return null;
                }
                return ((ContextWrapper) context).getBaseContext();
            case 1:
                View view = (View) obj;
                if (view instanceof ViewGroup) {
                    viewGroup = (ViewGroup) view;
                } else {
                    viewGroup = null;
                }
                if (viewGroup == null) {
                    return null;
                }
                return new ViewGroupKt$special$$inlined$Sequence$1(viewGroup, 1).iterator();
            case 2:
                Context context2 = (Context) obj;
                context2.getClass();
                if (!(context2 instanceof ContextWrapper)) {
                    return null;
                }
                return ((ContextWrapper) context2).getBaseContext();
            case 3:
                NavOptionsBuilder navOptionsBuilder = (NavOptionsBuilder) obj;
                navOptionsBuilder.getClass();
                navOptionsBuilder.restoreState = true;
                return Unit.INSTANCE;
            case 4:
                NavDestination navDestination = (NavDestination) obj;
                navDestination.getClass();
                NavGraph navGraph = navDestination.parent;
                if (navGraph == null || navGraph.startDestId != navDestination.id) {
                    return null;
                }
                return navGraph;
            case 5:
                NavDestination navDestination2 = (NavDestination) obj;
                navDestination2.getClass();
                NavGraph navGraph2 = navDestination2.parent;
                if (navGraph2 == null || navGraph2.startDestId != navDestination2.id) {
                    return null;
                }
                return navGraph2;
            case 6:
                AnimBuilder animBuilder = (AnimBuilder) obj;
                animBuilder.getClass();
                animBuilder.enter = 0;
                animBuilder.exit = 0;
                return Unit.INSTANCE;
            case 7:
                PopUpToBuilder popUpToBuilder = (PopUpToBuilder) obj;
                popUpToBuilder.getClass();
                popUpToBuilder.saveState = true;
                return Unit.INSTANCE;
            case 8:
                NavDestination navDestination3 = (NavDestination) obj;
                navDestination3.getClass();
                return navDestination3.parent;
            case 9:
                NavDestination navDestination4 = (NavDestination) obj;
                navDestination4.getClass();
                if (!(navDestination4 instanceof NavGraph)) {
                    return null;
                }
                NavGraph navGraph3 = (NavGraph) navDestination4;
                return navGraph3.findNode(navGraph3.startDestId);
            case 10:
                View view2 = (View) obj;
                view2.getClass();
                Object parent = view2.getParent();
                if (!(parent instanceof View)) {
                    return null;
                }
                return (View) parent;
            case 11:
                View view3 = (View) obj;
                view3.getClass();
                Object tag = view3.getTag(R.id.nav_controller_view_tag);
                if (tag instanceof WeakReference) {
                    return (NavController) ((WeakReference) tag).get();
                }
                if (!(tag instanceof NavController)) {
                    return null;
                }
                return (NavController) tag;
            case 12:
                NavOptionsBuilder navOptionsBuilder2 = (NavOptionsBuilder) obj;
                navOptionsBuilder2.getClass();
                navOptionsBuilder2.launchSingleTop = true;
                return Unit.INSTANCE;
            case 13:
                ((CreationExtras) obj).getClass();
                return new FragmentNavigator.ClearEntryStateViewModel();
            case 14:
                Pair pair = (Pair) obj;
                pair.getClass();
                return (String) pair.first;
            case 15:
                SQLiteStatement sQLiteStatement = (SQLiteStatement) obj;
                sQLiteStatement.getClass();
                return Boolean.valueOf(sQLiteStatement.step());
            case 16:
                SQLiteStatement sQLiteStatement2 = (SQLiteStatement) obj;
                sQLiteStatement2.getClass();
                SetBuilder setBuilder = new SetBuilder();
                while (sQLiteStatement2.step()) {
                    setBuilder.add(Integer.valueOf((int) sQLiteStatement2.getLong(0)));
                }
                return OnDeviceObjectCreateLogEvent.build(setBuilder);
            case 17:
                Map.Entry entry = (Map.Entry) obj;
                entry.getClass();
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" : ");
                if (value instanceof Object[]) {
                    value = Arrays.toString((Object[]) value);
                    value.getClass();
                }
                sb.append(value);
                return sb.toString();
            case 18:
                ConstraintController constraintController = (ConstraintController) obj;
                constraintController.getClass();
                String simpleName = constraintController.getClass().getSimpleName();
                simpleName.getClass();
                return simpleName;
            case 19:
                ((Throwable) obj).getClass();
                return Unit.INSTANCE;
            default:
                S2sHatsSurveyRequester s2sHatsSurveyRequester = S2sHatsSurveyRequester.INSTANCE;
                S2sHatsSurveyRequester.surveysClient = new WeakReference(null);
                return Unit.INSTANCE;
        }
    }
}
