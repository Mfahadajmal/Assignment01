package kotlinx.coroutines.flow.internal;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CombineKt$combineInternal$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function0 $arrayFactory;
    final /* synthetic */ Flow[] $flows;
    final /* synthetic */ FlowCollector $this_combineInternal;
    final /* synthetic */ Function3 $transform;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Flow[] $flows;
        final /* synthetic */ int $i;
        final /* synthetic */ AtomicInteger $nonClosed;
        final /* synthetic */ Channel $resultChannel;
        int label;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C00071 implements FlowCollector {
            final /* synthetic */ int $i;
            final /* synthetic */ Channel $resultChannel;

            public C00071(Channel channel, int i) {
                this.$resultChannel = channel;
                this.$i = i;
            }

            /* JADX WARN: Code restructure failed: missing block: B:25:0x00cc, code lost:
            
                if (r7 != r1) goto L53;
             */
            /* JADX WARN: Code restructure failed: missing block: B:26:0x00d2, code lost:
            
                return r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:57:0x0049, code lost:
            
                if (r8.send(r5, r0) != r1) goto L18;
             */
            /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x00ca  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
            /* JADX WARN: Removed duplicated region for block: B:55:0x0060  */
            /* JADX WARN: Removed duplicated region for block: B:56:0x0037  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r8
                    kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1$emit$1 r0 = (kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1$emit$1 r0 = new kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1$emit$1
                    r0.<init>(r6, r8)
                L18:
                    java.lang.Object r8 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L37
                    if (r2 == r4) goto L33
                    if (r2 != r3) goto L2b
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
                    goto Lcf
                L2b:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r8)
                    throw r7
                L33:
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
                    goto L4b
                L37:
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
                    kotlinx.coroutines.channels.Channel r8 = r6.$resultChannel
                    int r2 = r6.$i
                    kotlin.collections.IndexedValue r5 = new kotlin.collections.IndexedValue
                    r5.<init>(r2, r7)
                    r0.label = r4
                    java.lang.Object r7 = r8.send(r5, r0)
                    if (r7 == r1) goto Ld2
                L4b:
                    r0.label = r3
                    kotlin.coroutines.CoroutineContext r7 = r0.getContext()
                    com.google.mlkit.logging.schema.ScannerAutoZoomEvent.PredictedArea.ensureActive(r7)
                    kotlin.coroutines.Continuation r8 = com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent.intercepted(r0)
                    boolean r0 = r8 instanceof kotlinx.coroutines.internal.DispatchedContinuation
                    r2 = 0
                    if (r0 == 0) goto L60
                    kotlinx.coroutines.internal.DispatchedContinuation r8 = (kotlinx.coroutines.internal.DispatchedContinuation) r8
                    goto L61
                L60:
                    r8 = r2
                L61:
                    if (r8 != 0) goto L66
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    goto Lc6
                L66:
                    kotlinx.coroutines.CoroutineDispatcher r0 = r8.dispatcher
                    boolean r0 = r0.isDispatchNeeded(r7)
                    if (r0 == 0) goto L74
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    r8.dispatchYield$kotlinx_coroutines_core(r7, r0)
                    goto Lc4
                L74:
                    kotlinx.coroutines.YieldContext r0 = new kotlinx.coroutines.YieldContext
                    r0.<init>()
                    kotlin.coroutines.CoroutineContext r7 = r7.plus(r0)
                    kotlin.Unit r3 = kotlin.Unit.INSTANCE
                    r8.dispatchYield$kotlinx_coroutines_core(r7, r3)
                    boolean r7 = r0.dispatcherWasUnconfined
                    if (r7 == 0) goto Lc4
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    boolean r0 = kotlinx.coroutines.DebugKt.ASSERTIONS_ENABLED
                    kotlinx.coroutines.ThreadLocalEventLoop r0 = kotlinx.coroutines.ThreadLocalEventLoop.INSTANCE
                    kotlinx.coroutines.EventLoop r0 = kotlinx.coroutines.ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core$ar$ds()
                    boolean r3 = r0.isUnconfinedQueueEmpty()
                    if (r3 == 0) goto L97
                    goto Lbc
                L97:
                    boolean r3 = r0.isUnconfinedLoopActive()
                    if (r3 == 0) goto La7
                    r8._state = r7
                    r8.resumeMode = r4
                    r0.dispatchUnconfined(r8)
                    kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    goto Lc6
                La7:
                    r0.incrementUseCount(r4)
                    r8.run()     // Catch: java.lang.Throwable -> Lb7
                Lad:
                    boolean r7 = r0.processUnconfinedEvent()     // Catch: java.lang.Throwable -> Lb7
                    if (r7 != 0) goto Lad
                Lb3:
                    r0.decrementUseCount(r4)
                    goto Lbc
                Lb7:
                    r7 = move-exception
                    r8.handleFatalException$kotlinx_coroutines_core(r7, r2)     // Catch: java.lang.Throwable -> Lbf
                    goto Lb3
                Lbc:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    goto Lc6
                Lbf:
                    r7 = move-exception
                    r0.decrementUseCount(r4)
                    throw r7
                Lc4:
                    kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                Lc6:
                    kotlin.coroutines.intrinsics.CoroutineSingletons r8 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    if (r7 == r8) goto Lcc
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                Lcc:
                    if (r7 != r1) goto Lcf
                    goto Ld2
                Lcf:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    return r7
                Ld2:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2.AnonymousClass1.C00071.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow[] flowArr, int i, AtomicInteger atomicInteger, Channel channel, Continuation continuation) {
            super(2, continuation);
            this.$flows = flowArr;
            this.$i = i;
            this.$nonClosed = atomicInteger;
            this.$resultChannel = channel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$flows, this.$i, this.$nonClosed, this.$resultChannel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AtomicInteger atomicInteger;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            try {
                if (this.label != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                } else {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    Flow[] flowArr = this.$flows;
                    int i = this.$i;
                    Flow flow = flowArr[i];
                    C00071 c00071 = new C00071(this.$resultChannel, i);
                    this.label = 1;
                    if (flow.collect(c00071, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
                if (atomicInteger.decrementAndGet() == 0) {
                    this.$resultChannel.close(null);
                }
                return Unit.INSTANCE;
            } finally {
                if (this.$nonClosed.decrementAndGet() == 0) {
                    this.$resultChannel.close(null);
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineInternal$2(Flow[] flowArr, Function0 function0, Function3 function3, FlowCollector flowCollector, Continuation continuation) {
        super(2, continuation);
        this.$flows = flowArr;
        this.$arrayFactory = function0;
        this.$transform = function3;
        this.$this_combineInternal = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(this.$flows, this.$arrayFactory, this.$transform, this.$this_combineInternal, continuation);
        combineKt$combineInternal$2.L$0 = obj;
        return combineKt$combineInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((CombineKt$combineInternal$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x009c, code lost:
    
        if (r4 == null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x009e, code lost:
    
        r11 = r4.index;
        r12 = r10[r11];
        r10[r11] = r4.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x00a8, code lost:
    
        if (r12 != kotlinx.coroutines.flow.internal.NullSurrogateKt.UNINITIALIZED) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00aa, code lost:
    
        r7 = r7 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00ac, code lost:
    
        r4 = (byte[]) r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00b1, code lost:
    
        if (r4[r11] == r2) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00b3, code lost:
    
        r4[r11] = (byte) r2;
        r4 = (kotlin.collections.IndexedValue) kotlinx.coroutines.channels.ChannelResult.m258getOrNullimpl(r9.mo256tryReceivePtdJZtk());
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00c0, code lost:
    
        if (r4 != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00c2, code lost:
    
        if (r7 != 0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00c4, code lost:
    
        r4 = (java.lang.Object[]) r19.$arrayFactory.invoke();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00cc, code lost:
    
        if (r4 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00ce, code lost:
    
        r4 = r19.$transform;
        r11 = r19.$this_combineInternal;
        r19.L$0 = r10;
        r19.L$1 = r9;
        r19.L$2 = r8;
        r19.I$0 = 0;
        r19.I$1 = r2;
        r19.label = 2;
        r8 = r8;
        r9 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00e3, code lost:
    
        if (r4.invoke(r11, r10, r19) != r1) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00e5, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00e6, code lost:
    
        com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$default$ar$ds$41d531b2_0(r10, r4, 0, 0, 14);
        r11 = r19.$transform;
        r13 = r19.$this_combineInternal;
        r19.L$0 = r10;
        r19.L$1 = r9;
        r19.L$2 = r8;
        r19.I$0 = 0;
        r19.I$1 = r2;
        r19.label = 3;
        r8 = r8;
        r9 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0100, code lost:
    
        if (r11.invoke(r13, r4, r19) != r1) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0102, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0103, code lost:
    
        r8 = r8;
        r9 = r9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00e3 -> B:7:0x0082). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0100 -> B:7:0x0082). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0103 -> B:7:0x0082). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
