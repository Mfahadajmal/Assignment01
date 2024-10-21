package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ForegroundUpdater {
    ListenableFuture setForegroundAsync(Context context, UUID uuid, ForegroundInfo foregroundInfo);
}
