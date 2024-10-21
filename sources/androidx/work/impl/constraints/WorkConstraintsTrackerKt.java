package androidx.work.impl.constraints;

import androidx.lifecycle.ViewModelStore;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import com.google.mlkit.logging.schema.OnDeviceObjectLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkConstraintsTrackerKt {
    private static final String TAG = Logger.tagWithPrefix("WorkConstraintsTracker");
    public static final long DefaultNetworkRequestTimeoutMs = 1000;

    public static final Job listen$ar$class_merging$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore, WorkSpec workSpec, CoroutineDispatcher coroutineDispatcher, OnConstraintsStateChangedListener onConstraintsStateChangedListener) {
        viewModelStore.getClass();
        workSpec.getClass();
        coroutineDispatcher.getClass();
        JobImpl Job$default$ar$class_merging$ar$ds = ScannerAutoZoomEvent.PredictedArea.Job$default$ar$class_merging$ar$ds();
        OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(OtherError.CoroutineScope(OnDeviceObjectLoadLogEvent.plus((CoroutineContext.Element) coroutineDispatcher, (CoroutineContext) Job$default$ar$class_merging$ar$ds)), null, 0, new WorkConstraintsTrackerKt$listen$1(viewModelStore, workSpec, onConstraintsStateChangedListener, (Continuation) null, 0), 3);
        return Job$default$ar$class_merging$ar$ds;
    }
}
