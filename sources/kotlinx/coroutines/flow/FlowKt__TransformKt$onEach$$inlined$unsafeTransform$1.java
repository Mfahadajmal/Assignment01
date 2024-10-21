package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 implements Flow {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ Object FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$ar$$action$inlined;
    private final /* synthetic */ int switching_field;

    /* compiled from: PG */
    /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements FlowCollector {
        final /* synthetic */ FlowCollector $this_unsafeFlow;
        final /* synthetic */ Object FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$ar$$action$inlined;
        private final /* synthetic */ int switching_field;

        /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
            Object L$1;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector flowCollector, Object obj, int i) {
            this.switching_field = i;
            this.$this_unsafeFlow = flowCollector;
            this.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$ar$$action$inlined = obj;
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x00a9, code lost:
        
            if (r7.emit(r1, r0) != r3) goto L50;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0089  */
        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, kotlin.jvm.functions.Function2] */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
            /*
                r6 = this;
                int r0 = r6.switching_field
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r2 = 1
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                if (r0 == 0) goto L54
                boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1
                if (r0 == 0) goto L1a
                r0 = r8
                kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1) r0
                int r4 = r0.label
                r5 = r4 & r3
                if (r5 == 0) goto L1a
                int r4 = r4 - r3
                r0.label = r4
                goto L1f
            L1a:
                kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1
                r0.<init>(r6, r8)
            L1f:
                java.lang.Object r8 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r4 = r0.label
                if (r4 == 0) goto L37
                if (r4 != r2) goto L31
                kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2 r7 = r0.L$0$ar$dn$8e6f6c46_0$ar$class_merging
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L2f
                goto L47
            L2f:
                r8 = move-exception
                goto L4d
            L31:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                r7.<init>(r1)
                throw r7
            L37:
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
                kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow     // Catch: java.lang.Throwable -> L4a
                r0.L$0$ar$dn$8e6f6c46_0$ar$class_merging = r6     // Catch: java.lang.Throwable -> L4a
                r0.label = r2     // Catch: java.lang.Throwable -> L4a
                java.lang.Object r7 = r8.emit(r7, r0)     // Catch: java.lang.Throwable -> L4a
                if (r7 != r3) goto L47
                return r3
            L47:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            L4a:
                r7 = move-exception
                r8 = r7
                r7 = r6
            L4d:
                java.lang.Object r7 = r7.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$ar$$action$inlined
                kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref$ObjectRef) r7
                r7.element = r8
                throw r8
            L54:
                boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L65
                r0 = r8
                kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1) r0
                int r4 = r0.label
                r5 = r4 & r3
                if (r5 == 0) goto L65
                int r4 = r4 - r3
                r0.label = r4
                goto L6a
            L65:
                kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1
                r0.<init>(r8)
            L6a:
                java.lang.Object r8 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r4 = r0.label
                r5 = 2
                if (r4 == 0) goto L89
                if (r4 == r2) goto L81
                if (r4 != r5) goto L7b
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
                goto Lac
            L7b:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                r7.<init>(r1)
                throw r7
            L81:
                java.lang.Object r7 = r0.L$1
                java.lang.Object r1 = r0.L$0
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
                goto L9e
            L89:
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
                kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                java.lang.Object r1 = r6.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$ar$$action$inlined
                r0.L$0 = r7
                r0.L$1 = r8
                r0.label = r2
                java.lang.Object r1 = r1.invoke(r7, r0)
                if (r1 == r3) goto Laf
                r1 = r7
                r7 = r8
            L9e:
                r8 = 0
                r0.L$0 = r8
                r0.L$1 = r8
                r0.label = r5
                java.lang.Object r7 = r7.emit(r1, r0)
                if (r7 != r3) goto Lac
                goto Laf
            Lac:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            Laf:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(Flow flow, Object obj, int i) {
        this.switching_field = i;
        this.$this_unsafeTransform$inlined = flow;
        this.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$ar$$action$inlined = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x004b  */
    /* JADX WARN: Type inference failed for: r11v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v6, types: [kotlin.jvm.functions.Function4, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0082 -> B:13:0x0085). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0097 -> B:16:0x009a). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r11, kotlin.coroutines.Continuation r12) {
        /*
            r10 = this;
            int r0 = r10.switching_field
            r1 = 0
            if (r0 == 0) goto L9f
            boolean r0 = r12 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1
            if (r0 == 0) goto L18
            r0 = r12
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1) r0
            int r2 = r0.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L18
            int r2 = r2 - r3
            r0.label = r2
            goto L1d
        L18:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1
            r0.<init>(r10, r12)
        L1d:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r3 = r0.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L4b
            if (r3 == r5) goto L3f
            if (r3 != r4) goto L37
            long r6 = r0.J$0
            java.lang.Object r11 = r0.L$2
            java.lang.Object r3 = r0.L$1
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 r8 = r0.L$0$ar$dn$3b60d34e_0$ar$class_merging
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
            goto L85
        L37:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3f:
            long r6 = r0.J$0
            java.lang.Object r11 = r0.L$1
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 r3 = r0.L$0$ar$dn$3b60d34e_0$ar$class_merging
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
            r8 = r3
        L49:
            r3 = r11
            goto L68
        L4b:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
            r6 = 0
            r12 = r10
        L51:
            kotlinx.coroutines.flow.Flow r3 = r12.$this_unsafeTransform$inlined
            r0.L$0$ar$dn$3b60d34e_0$ar$class_merging = r12
            r0.L$1 = r11
            r8 = 0
            r0.L$2 = r8
            r0.J$0 = r6
            r0.label = r5
            java.lang.Object r3 = com.google.mlkit.logging.schema.StainRemovalOptionalModuleLogEvent.catchImpl(r3, r11, r0)
            if (r3 != r2) goto L65
            goto L9e
        L65:
            r8 = r12
            r12 = r3
            goto L49
        L68:
            r11 = r12
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            if (r11 == 0) goto L97
            java.lang.Object r12 = r8.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$ar$$action$inlined
            java.lang.Long r9 = new java.lang.Long
            r9.<init>(r6)
            r0.L$0$ar$dn$3b60d34e_0$ar$class_merging = r8
            r0.L$1 = r3
            r0.L$2 = r11
            r0.J$0 = r6
            r0.label = r4
            java.lang.Object r12 = r12.invoke(r3, r11, r9, r0)
            if (r12 != r2) goto L85
            goto L9e
        L85:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L94
            r11 = 1
            long r6 = r6 + r11
            r11 = r3
            r3 = r5
            r12 = r8
            goto L9a
        L94:
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            throw r11
        L97:
            r11 = r3
            r12 = r8
            r3 = r1
        L9a:
            if (r3 != 0) goto L51
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
        L9e:
            return r2
        L9f:
            java.lang.Object r0 = r10.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$ar$$action$inlined
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2 r2 = new kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2
            r2.<init>(r11, r0, r1)
            kotlinx.coroutines.flow.Flow r11 = r10.$this_unsafeTransform$inlined
            java.lang.Object r11 = r11.collect(r2, r12)
            kotlin.coroutines.intrinsics.CoroutineSingletons r12 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r11 != r12) goto Lb1
            return r11
        Lb1:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
