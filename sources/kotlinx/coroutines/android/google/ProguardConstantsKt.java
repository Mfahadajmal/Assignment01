package kotlinx.coroutines.android.google;

import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompatBuilder$Api31Impl;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.android.HandlerDispatcherKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProguardConstantsKt {
    public static final MainCoroutineDispatcher MAIN_DISPATCHER;

    static {
        Handler createAsync = NotificationCompatBuilder$Api31Impl.createAsync(Looper.getMainLooper());
        int i = HandlerDispatcherKt.HandlerDispatcherKt$ar$NoOp;
        MAIN_DISPATCHER = new HandlerContext(createAsync, "main");
    }
}
