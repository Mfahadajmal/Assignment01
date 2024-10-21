package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ClearcutFlags {
    String clearcutLogSourceName(Context context);

    boolean disableLoggingForLoggedInUsers(Context context);

    boolean enableLoggingViaClearcut(Context context);
}
