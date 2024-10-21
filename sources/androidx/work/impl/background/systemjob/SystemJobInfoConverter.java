package androidx.work.impl.background.systemjob;

import android.content.ComponentName;
import android.content.Context;
import android.support.v7.view.WindowCallbackWrapper;
import androidx.work.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SystemJobInfoConverter {
    private static final String TAG = Logger.tagWithPrefix("SystemJobInfoConverter");
    public final WindowCallbackWrapper.Api26Impl mClock$ar$class_merging$ar$class_merging$ar$class_merging;
    public final boolean mMarkImportantWhileForeground = true;
    public final ComponentName mWorkServiceComponent;

    public SystemJobInfoConverter(Context context, WindowCallbackWrapper.Api26Impl api26Impl, boolean z) {
        this.mClock$ar$class_merging$ar$class_merging$ar$class_merging = api26Impl;
        this.mWorkServiceComponent = new ComponentName(context.getApplicationContext(), (Class<?>) SystemJobService.class);
    }
}
