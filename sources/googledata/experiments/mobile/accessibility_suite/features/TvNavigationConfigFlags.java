package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TvNavigationConfigFlags {
    long handlerThreadPriority(Context context);

    long keyEventTimeoutMillis(Context context);

    boolean letSystemHandleDpadCenterWhenFocusNotInSyncNew(Context context);

    TypedFeatures$StringListParam packagesDpadAllowlist(Context context);

    TypedFeatures$StringListParam packagesDpadBlocklist(Context context);

    boolean useHandlerThread(Context context);
}
