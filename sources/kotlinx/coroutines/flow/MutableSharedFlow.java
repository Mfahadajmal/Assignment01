package kotlinx.coroutines.flow;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MutableSharedFlow extends SharedFlow, FlowCollector {
    boolean tryEmit(Object obj);
}
