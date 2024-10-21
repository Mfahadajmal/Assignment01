package kotlinx.coroutines.flow.internal;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ChannelFlow implements Flow {
    public final int capacity;
    public final CoroutineContext context;
    public final int onBufferOverflow$ar$edu;

    public ChannelFlow(CoroutineContext coroutineContext, int i, int i2) {
        this.context = coroutineContext;
        this.capacity = i;
        this.onBufferOverflow$ar$edu = i2;
        boolean z = DebugKt.ASSERTIONS_ENABLED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object collect$suspendImpl(ChannelFlow channelFlow, FlowCollector flowCollector, Continuation continuation) {
        Object coroutineScope = OtherError.coroutineScope(new ChannelFlow$collect$2(flowCollector, channelFlow, null), continuation);
        if (coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return coroutineScope;
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object collectTo$ar$class_merging(ProducerCoroutine producerCoroutine, Continuation continuation);

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        CoroutineContext coroutineContext = this.context;
        if (coroutineContext != EmptyCoroutineContext.INSTANCE) {
            Objects.toString(coroutineContext);
            arrayList.add("context=".concat(String.valueOf(coroutineContext)));
        }
        arrayList.add("capacity=" + this.capacity);
        if (this.onBufferOverflow$ar$edu != BufferOverflow.SUSPEND$ar$edu) {
            int i = this.onBufferOverflow$ar$edu;
            Objects.toString(BufferOverflow.toStringGeneratedd0f0220c226c70ff(i));
            arrayList.add("onBufferOverflow=".concat(BufferOverflow.toStringGeneratedd0f0220c226c70ff(i)));
        }
        return DebugStringsKt.getClassSimpleName(this) + "[" + OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(arrayList, ", ", null, null, null, 62) + "]";
    }
}
