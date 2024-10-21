package androidx.work;

import android.support.v7.view.WindowCallbackWrapper;
import androidx.work.WorkRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OneTimeWorkRequest extends WorkRequest {
    public static final WindowCallbackWrapper.Api23Impl Companion$ar$class_merging$14775d_0$ar$class_merging = new WindowCallbackWrapper.Api23Impl(null);

    public OneTimeWorkRequest(WorkRequest.Builder builder) {
        super(builder.id, builder.workSpec, builder.tags);
    }
}
