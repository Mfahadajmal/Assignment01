package com.google.mlkit.logging.schema;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SmartReplyOptionalModuleLogEvent {
    public static final Object emitAll(FlowCollector flowCollector, ReceiveChannel receiveChannel, Continuation continuation) {
        Object emitAllImpl$FlowKt__ChannelsKt = emitAllImpl$FlowKt__ChannelsKt(flowCollector, receiveChannel, true, continuation);
        if (emitAllImpl$FlowKt__ChannelsKt == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return emitAllImpl$FlowKt__ChannelsKt;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007b A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #0 {all -> 0x0035, blocks: (B:12:0x002f, B:14:0x005b, B:20:0x0073, B:22:0x007b, B:34:0x0048, B:38:0x0057), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0092 -> B:14:0x005b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector r7, kotlinx.coroutines.channels.ReceiveChannel r8, boolean r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L50
            if (r2 == r5) goto L40
            if (r2 != r4) goto L38
            boolean r9 = r0.Z$0
            java.lang.Object r7 = r0.L$2
            java.lang.Object r8 = r0.L$1
            java.lang.Object r2 = r0.L$0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L35
            r10 = r7
            r7 = r2
            goto L5b
        L35:
            r7 = move-exception
            goto L9e
        L38:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L40:
            boolean r9 = r0.Z$0
            java.lang.Object r7 = r0.L$2
            java.lang.Object r8 = r0.L$1
            java.lang.Object r2 = r0.L$0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L35
            r6 = r0
            r0 = r7
            r7 = r2
        L4e:
            r2 = r6
            goto L73
        L50:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r10)
            boolean r10 = r7 instanceof kotlinx.coroutines.flow.ThrowingCollector
            if (r10 != 0) goto La7
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r10 = r8.iterator$ar$class_merging()     // Catch: java.lang.Throwable -> L35
        L5b:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L35
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L35
            r0.L$2 = r10     // Catch: java.lang.Throwable -> L35
            r0.Z$0 = r9     // Catch: java.lang.Throwable -> L35
            r0.label = r5     // Catch: java.lang.Throwable -> L35
            r2 = r10
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r2 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r2     // Catch: java.lang.Throwable -> L35
            java.lang.Object r2 = r2.hasNext(r0)     // Catch: java.lang.Throwable -> L35
            if (r2 != r1) goto L6f
            return r1
        L6f:
            r6 = r0
            r0 = r10
            r10 = r2
            goto L4e
        L73:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L35
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r10 == 0) goto L96
            r10 = r0
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r10 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r10     // Catch: java.lang.Throwable -> L35
            java.lang.Object r10 = r10.next()     // Catch: java.lang.Throwable -> L35
            r2.L$0 = r7     // Catch: java.lang.Throwable -> L35
            r2.L$1 = r8     // Catch: java.lang.Throwable -> L35
            r2.L$2 = r0     // Catch: java.lang.Throwable -> L35
            r2.Z$0 = r9     // Catch: java.lang.Throwable -> L35
            r2.label = r4     // Catch: java.lang.Throwable -> L35
            java.lang.Object r10 = r7.emit(r10, r2)     // Catch: java.lang.Throwable -> L35
            if (r10 == r1) goto L95
            r10 = r0
            r0 = r2
            goto L5b
        L95:
            return r1
        L96:
            if (r9 == 0) goto L9b
            com.google.mlkit.logging.schema.SmartReplyGeneratorOptions.cancelConsumed(r8, r3)
        L9b:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L9e:
            throw r7     // Catch: java.lang.Throwable -> L9f
        L9f:
            r10 = move-exception
            if (r9 != 0) goto La3
            goto La6
        La3:
            com.google.mlkit.logging.schema.SmartReplyGeneratorOptions.cancelConsumed(r8, r7)
        La6:
            throw r10
        La7:
            kotlinx.coroutines.flow.ThrowingCollector r7 = (kotlinx.coroutines.flow.ThrowingCollector) r7
            java.lang.Throwable r7 = r7.e
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.logging.schema.SmartReplyOptionalModuleLogEvent.emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.channels.ReceiveChannel, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
