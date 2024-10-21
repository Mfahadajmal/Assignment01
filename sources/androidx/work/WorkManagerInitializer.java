package androidx.work;

import android.content.Context;
import androidx.startup.Initializer;
import androidx.work.Configuration;
import androidx.work.impl.WorkManagerImpl;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkManagerInitializer implements Initializer<WorkManager> {
    private static final String TAG = Logger.tagWithPrefix("WrkMgrInitializer");

    @Override // androidx.startup.Initializer
    public final /* bridge */ /* synthetic */ Object create(Context context) {
        Logger.get$ar$ds$16341a92_0();
        Configuration configuration = new Configuration(new Configuration.Builder());
        context.getClass();
        WorkManagerImpl.initialize(context, configuration);
        return WorkManager.getInstance(context);
    }

    @Override // androidx.startup.Initializer
    public final List dependencies() {
        return Collections.emptyList();
    }
}
