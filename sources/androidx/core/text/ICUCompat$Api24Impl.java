package androidx.core.text;

import _COROUTINE._BOUNDARY;
import android.icu.util.ULocale;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleCoroutineScopeImpl$register$1;
import androidx.lifecycle.LifecycleOwner;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import java.util.Locale;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.internal.MainDispatcherLoader;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ICUCompat$Api24Impl {
    public static ULocale addLikelySubtags(Object obj) {
        return ULocale.addLikelySubtags((ULocale) obj);
    }

    public static ULocale forLocale(Locale locale) {
        return ULocale.forLocale(locale);
    }

    public static final LifecycleCoroutineScope getLifecycleScope(LifecycleOwner lifecycleOwner) {
        LifecycleCoroutineScope lifecycleCoroutineScope;
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        lifecycle.getClass();
        while (true) {
            lifecycleCoroutineScope = (LifecycleCoroutineScope) lifecycle.internalScopeRef.get();
            if (lifecycleCoroutineScope != null) {
                break;
            }
            JobImpl SupervisorJob$default$ar$class_merging$ar$ds = ScannerAutoZoomEvent.SupervisorJob$default$ar$class_merging$ar$ds();
            CoroutineDispatcher coroutineDispatcher = Dispatchers.Default;
            lifecycleCoroutineScope = new LifecycleCoroutineScope(lifecycle, SupervisorJob$default$ar$class_merging$ar$ds.plus(MainDispatcherLoader.dispatcher.getImmediate()));
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(lifecycle.internalScopeRef, lifecycleCoroutineScope)) {
                OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(lifecycleCoroutineScope, MainDispatcherLoader.dispatcher.getImmediate(), 0, new LifecycleCoroutineScopeImpl$register$1(lifecycleCoroutineScope, null), 2);
                break;
            }
        }
        return lifecycleCoroutineScope;
    }

    public static String getScript(Object obj) {
        return ((ULocale) obj).getScript();
    }
}
