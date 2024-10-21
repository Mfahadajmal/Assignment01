package androidx.work;

import android.content.Context;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import androidx.work.impl.WorkManagerImpl;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class WorkManager {
    public static final AppCompatReceiveContentHelper$OnDropApi24Impl Companion$ar$class_merging$1cbcfbce_0 = new AppCompatReceiveContentHelper$OnDropApi24Impl(null);

    public static WorkManager getInstance(Context context) {
        context.getClass();
        return WorkManagerImpl.getInstance(context);
    }

    public abstract void enqueue$ar$ds(List list);
}
