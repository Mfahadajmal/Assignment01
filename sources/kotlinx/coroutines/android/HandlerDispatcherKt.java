package kotlinx.coroutines.android;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Result;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HandlerDispatcherKt {
    public static final /* synthetic */ int HandlerDispatcherKt$ar$NoOp = 0;
    private static volatile Choreographer choreographer;

    static {
        Object createFailure;
        try {
            createFailure = new HandlerContext(asHandler$ar$ds(Looper.getMainLooper()));
        } catch (Throwable th) {
            createFailure = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th);
        }
        if (true == (createFailure instanceof Result.Failure)) {
            createFailure = null;
        }
    }

    public static final Handler asHandler$ar$ds(Looper looper) {
        if (Build.VERSION.SDK_INT < 28) {
            try {
                return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, true);
            } catch (NoSuchMethodException unused) {
                return new Handler(looper);
            }
        }
        Object invoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        invoke.getClass();
        return (Handler) invoke;
    }
}
