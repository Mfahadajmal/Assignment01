package androidx.navigation;

import android.view.View;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.GeneratorSequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Navigation {
    public static final Navigation INSTANCE = new Navigation();

    private Navigation() {
    }

    public static final NavController findViewNavController$ar$ds(View view) {
        Object next;
        Iterator it = OnDeviceSmartReplyLogEvent.filterNotNull(new GeneratorSequence(OnDeviceSmartReplyLogEvent.generateSequence(view, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$c2132036_0), (Function1) ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$9d1256f6_0, 3)).iterator();
        if (!it.hasNext()) {
            next = null;
        } else {
            next = it.next();
        }
        return (NavController) next;
    }

    public static final void setViewNavController(View view, NavController navController) {
        view.setTag(R.id.nav_controller_view_tag, navController);
    }
}
