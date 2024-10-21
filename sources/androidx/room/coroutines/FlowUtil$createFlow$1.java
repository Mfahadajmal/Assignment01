package androidx.room.coroutines;

import androidx.room.RoomDatabase;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlowUtil$createFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ RoomDatabase $db;
    final /* synthetic */ String[] $tableNames;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: androidx.room.coroutines.FlowUtil$createFlow$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FlowCollector $$this$flow;
        final /* synthetic */ Function1 $block;
        final /* synthetic */ RoomDatabase $db;
        final /* synthetic */ String[] $tableNames;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* renamed from: androidx.room.coroutines.FlowUtil$createFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C00011 extends SuspendLambda implements Function2 {
            final /* synthetic */ Function1 $block;
            final /* synthetic */ RoomDatabase $db;
            final /* synthetic */ FlowUtil$createFlow$1$1$observer$1 $observer;
            final /* synthetic */ Channel $observerChannel;
            final /* synthetic */ Channel $resultChannel;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00011(RoomDatabase roomDatabase, FlowUtil$createFlow$1$1$observer$1 flowUtil$createFlow$1$1$observer$1, Channel channel, Channel channel2, Function1 function1, Continuation continuation) {
                super(2, continuation);
                this.$db = roomDatabase;
                this.$observer = flowUtil$createFlow$1$1$observer$1;
                this.$observerChannel = channel;
                this.$resultChannel = channel2;
                this.$block = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00011(this.$db, this.$observer, this.$observerChannel, this.$resultChannel, this.$block, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return ((C00011) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:21:0x0075 A[Catch: all -> 0x0099, TryCatch #0 {all -> 0x0099, blocks: (B:16:0x0027, B:17:0x0062, B:19:0x006c, B:21:0x0075, B:23:0x008c, B:33:0x002f, B:35:0x0037, B:37:0x005c), top: B:2:0x0009 }] */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0098 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:27:0x009c  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00b2 A[RETURN] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0096 -> B:17:0x0062). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r12) {
                /*
                    r11 = this;
                    kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r1 = r11.label
                    r2 = 5
                    r3 = 4
                    r4 = 3
                    r5 = 2
                    r6 = 1
                    if (r1 == 0) goto L3f
                    if (r1 == r6) goto L3b
                    if (r1 == r5) goto L33
                    if (r1 == r4) goto L2b
                    if (r1 == r3) goto L23
                    if (r1 != r2) goto L1a
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
                    goto Laf
                L1a:
                    java.lang.Object r0 = r11.L$0
                    java.lang.Throwable r0 = (java.lang.Throwable) r0
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
                    goto Lc8
                L23:
                    java.lang.Object r1 = r11.L$0
                    kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r1
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L99
                    goto L62
                L2b:
                    java.lang.Object r1 = r11.L$0
                    kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r1
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L99
                    goto L8c
                L33:
                    java.lang.Object r1 = r11.L$0
                    kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r1
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L99
                    goto L6c
                L3b:
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
                    goto L5c
                L3f:
                    com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
                    androidx.room.RoomDatabase r12 = r11.$db
                    androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1 r1 = r11.$observer
                    com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons r12 = r12.getInvalidationTracker$ar$class_merging()
                    r11.label = r6
                    java.lang.Object r12 = r12.PlusMinusButtons$ar$canPlus
                    androidx.room.TriggerBasedInvalidationTracker r12 = (androidx.room.TriggerBasedInvalidationTracker) r12
                    java.lang.Object r12 = r12.addObserver$room_runtime_release(r1, r11)
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    if (r12 == r1) goto L5a
                    kotlin.Unit r12 = kotlin.Unit.INSTANCE
                L5a:
                    if (r12 == r0) goto Lc9
                L5c:
                    kotlinx.coroutines.channels.Channel r12 = r11.$observerChannel     // Catch: java.lang.Throwable -> L99
                    kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = r12.iterator$ar$class_merging()     // Catch: java.lang.Throwable -> L99
                L62:
                    r11.L$0 = r1     // Catch: java.lang.Throwable -> L99
                    r11.label = r5     // Catch: java.lang.Throwable -> L99
                    java.lang.Object r12 = r1.hasNext(r11)     // Catch: java.lang.Throwable -> L99
                    if (r12 == r0) goto Lb2
                L6c:
                    java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch: java.lang.Throwable -> L99
                    boolean r12 = r12.booleanValue()     // Catch: java.lang.Throwable -> L99
                    r7 = 0
                    if (r12 == 0) goto L9c
                    r1.next()     // Catch: java.lang.Throwable -> L99
                    androidx.room.RoomDatabase r12 = r11.$db     // Catch: java.lang.Throwable -> L99
                    kotlin.jvm.functions.Function1 r8 = r11.$block     // Catch: java.lang.Throwable -> L99
                    androidx.room.coroutines.FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1 r9 = new androidx.room.coroutines.FlowUtil$createFlow$1$1$1$invokeSuspend$$inlined$internalPerform$1     // Catch: java.lang.Throwable -> L99
                    r10 = 0
                    r9.<init>(r10, r12, r7, r8)     // Catch: java.lang.Throwable -> L99
                    r11.L$0 = r1     // Catch: java.lang.Throwable -> L99
                    r11.label = r4     // Catch: java.lang.Throwable -> L99
                    java.lang.Object r12 = r12.useConnection$room_runtime_release(r6, r9, r11)     // Catch: java.lang.Throwable -> L99
                    if (r12 == r0) goto L9b
                L8c:
                    kotlinx.coroutines.channels.Channel r7 = r11.$resultChannel     // Catch: java.lang.Throwable -> L99
                    r11.L$0 = r1     // Catch: java.lang.Throwable -> L99
                    r11.label = r3     // Catch: java.lang.Throwable -> L99
                    java.lang.Object r12 = r7.send(r12, r11)     // Catch: java.lang.Throwable -> L99
                    if (r12 != r0) goto L62
                    return r0
                L99:
                    r12 = move-exception
                    goto Lb3
                L9b:
                    return r0
                L9c:
                    androidx.room.RoomDatabase r12 = r11.$db
                    androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1 r1 = r11.$observer
                    com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons r12 = r12.getInvalidationTracker$ar$class_merging()
                    r11.L$0 = r7
                    r11.label = r2
                    java.lang.Object r12 = r12.unsubscribe(r1, r11)
                    if (r12 != r0) goto Laf
                    return r0
                Laf:
                    kotlin.Unit r12 = kotlin.Unit.INSTANCE
                    return r12
                Lb2:
                    return r0
                Lb3:
                    androidx.room.RoomDatabase r1 = r11.$db
                    com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons r1 = r1.getInvalidationTracker$ar$class_merging()
                    r11.L$0 = r12
                    r2 = 6
                    r11.label = r2
                    androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1 r2 = r11.$observer
                    java.lang.Object r1 = r1.unsubscribe(r2, r11)
                    if (r1 != r0) goto Lc7
                    return r0
                Lc7:
                    r0 = r12
                Lc8:
                    throw r0
                Lc9:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.FlowUtil$createFlow$1.AnonymousClass1.C00011.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(RoomDatabase roomDatabase, FlowCollector flowCollector, String[] strArr, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$db = roomDatabase;
            this.$$this$flow = flowCollector;
            this.$tableNames = strArr;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$db, this.$$this$flow, this.$tableNames, this.$block, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x009d, code lost:
        
            if (com.google.mlkit.logging.schema.SmartReplyOptionalModuleLogEvent.emitAll(r15, r2, r14) == r0) goto L21;
         */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                r14 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r14.label
                r2 = 1
                r3 = 0
                r4 = 0
                if (r1 == 0) goto L1f
                if (r1 == r2) goto L10
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r15)
                goto La0
            L10:
                java.lang.Object r1 = r14.L$3
                java.lang.Object r2 = r14.L$2
                java.lang.Object r5 = r14.L$1
                java.lang.Object r6 = r14.L$0
                kotlinx.coroutines.channels.Channel r6 = (kotlinx.coroutines.channels.Channel) r6
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r15)
                r8 = r6
                goto L72
            L1f:
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r15)
                java.lang.Object r15 = r14.L$0
                r1 = r15
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                r15 = -1
                r5 = 6
                kotlinx.coroutines.channels.Channel r6 = com.google.mlkit.logging.schema.ShadowRemovalOptionalModuleLogEvent.Channel$default$ar$ds$ar$edu(r15, r3, r4, r5)
                java.lang.String[] r15 = r14.$tableNames
                androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1 r5 = new androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1
                r5.<init>(r15)
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                r6.mo257trySendJP2dKIU(r15)
                r15 = 7
                kotlinx.coroutines.channels.Channel r15 = com.google.mlkit.logging.schema.ShadowRemovalOptionalModuleLogEvent.Channel$default$ar$ds$ar$edu(r3, r3, r4, r15)
                androidx.room.RoomDatabase r7 = r14.$db
                r14.L$0 = r6
                r14.L$1 = r5
                r14.L$2 = r15
                r14.L$3 = r1
                r14.label = r2
                boolean r2 = r7.inCompatibilityMode$room_runtime_release()
                if (r2 == 0) goto L64
                kotlin.coroutines.CoroutineContext r2 = r14.getContext()
                kotlin.coroutines.ContinuationInterceptor$Key r8 = androidx.room.TransactionElement.Key$ar$class_merging
                kotlin.coroutines.CoroutineContext$Element r2 = r2.get(r8)
                androidx.room.TransactionElement r2 = (androidx.room.TransactionElement) r2
                if (r2 != 0) goto L63
                kotlin.coroutines.CoroutineContext r2 = r7.getQueryContext()
                goto L6c
            L63:
                throw r4
            L64:
                kotlinx.coroutines.CoroutineScope r2 = r7.getCoroutineScope$room_runtime_release()
                kotlin.coroutines.CoroutineContext r2 = r2.getCoroutineContext()
            L6c:
                if (r2 == r0) goto La3
                r8 = r6
                r13 = r2
                r2 = r15
                r15 = r13
            L72:
                kotlin.coroutines.CoroutineContext r15 = (kotlin.coroutines.CoroutineContext) r15
                kotlin.coroutines.ContinuationInterceptor$Key r6 = kotlinx.coroutines.Job.Key$ar$class_merging$e5be0816_0
                kotlin.coroutines.CoroutineContext r15 = r15.minusKey(r6)
                androidx.room.RoomDatabase r6 = r14.$db
                kotlin.jvm.functions.Function1 r10 = r14.$block
                androidx.room.coroutines.FlowUtil$createFlow$1$1$1 r12 = new androidx.room.coroutines.FlowUtil$createFlow$1$1$1
                r7 = r5
                androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1 r7 = (androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1) r7
                r11 = 0
                r5 = r12
                r9 = r2
                r5.<init>(r6, r7, r8, r9, r10, r11)
                r5 = 2
                com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(r1, r15, r3, r12, r5)
                kotlinx.coroutines.flow.FlowCollector r15 = r14.$$this$flow
                r14.L$0 = r4
                r14.L$1 = r4
                r14.L$2 = r4
                r14.L$3 = r4
                r14.label = r5
                java.lang.Object r15 = com.google.mlkit.logging.schema.SmartReplyOptionalModuleLogEvent.emitAll(r15, r2, r14)
                if (r15 != r0) goto La0
                goto La3
            La0:
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            La3:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.FlowUtil$createFlow$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowUtil$createFlow$1(RoomDatabase roomDatabase, String[] strArr, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$db = roomDatabase;
        this.$tableNames = strArr;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowUtil$createFlow$1 flowUtil$createFlow$1 = new FlowUtil$createFlow$1(this.$db, this.$tableNames, this.$block, continuation);
        flowUtil$createFlow$1.L$0 = obj;
        return flowUtil$createFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((FlowUtil$createFlow$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$db, (FlowCollector) this.L$0, this.$tableNames, this.$block, null);
            this.label = 1;
            if (OtherError.coroutineScope(anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }
}
