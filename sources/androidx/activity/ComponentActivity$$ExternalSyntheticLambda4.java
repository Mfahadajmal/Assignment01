package androidx.activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentHostCallback;
import androidx.activity.contextaware.OnContextAvailableListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda4 implements OnContextAvailableListener {
    public final /* synthetic */ ComponentActivity f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda4(ComponentActivity componentActivity, int i) {
        this.switching_field = i;
        this.f$0 = componentActivity;
    }

    @Override // androidx.activity.contextaware.OnContextAvailableListener
    public final void onContextAvailable(Context context) {
        if (this.switching_field != 0) {
            Object obj = ((FragmentActivity) this.f$0).mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.mInfo;
            FragmentHostCallback fragmentHostCallback = (FragmentHostCallback) obj;
            fragmentHostCallback.fragmentManager.attachController(fragmentHostCallback, (FragmentContainer) obj, null);
            return;
        }
        ComponentActivity._init_$lambda$5$ar$ds(this.f$0);
    }
}
