package com.google.mlkit.logging.schema;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDevicePoseDetectionLogEvent {
    /* JADX WARN: Multi-variable type inference failed */
    public static final Continuation createCoroutineUnintercepted(final Function2 function2, final Object obj, final Continuation continuation) {
        if (function2 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) function2).create(obj, continuation);
        }
        final CoroutineContext context = continuation.getContext();
        if (context == EmptyCoroutineContext.INSTANCE) {
            return new RestrictedContinuationImpl(continuation) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3
                private int label;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                protected final Object invokeSuspend(Object obj2) {
                    int i = this.label;
                    if (i != 0) {
                        if (i == 1) {
                            this.label = 2;
                            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj2);
                            return obj2;
                        }
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                    this.label = 1;
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj2);
                    Function2 function22 = function2;
                    TypeIntrinsics.beforeCheckcastToFunctionOfArity$ar$ds(function22, 2);
                    return function22.invoke(obj, this);
                }
            };
        }
        return new ContinuationImpl(continuation, context) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4
            private int label;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected final Object invokeSuspend(Object obj2) {
                int i = this.label;
                if (i != 0) {
                    if (i == 1) {
                        this.label = 2;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj2);
                        return obj2;
                    }
                    throw new IllegalStateException("This coroutine had already completed");
                }
                this.label = 1;
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj2);
                Function2 function22 = function2;
                TypeIntrinsics.beforeCheckcastToFunctionOfArity$ar$ds(function22, 2);
                return function22.invoke(obj, this);
            }
        };
    }

    public static final EnumEntriesList enumEntries$ar$class_merging(Enum[] enumArr) {
        enumArr.getClass();
        return new EnumEntriesList(enumArr);
    }

    public static final Continuation intercepted(Continuation continuation) {
        ContinuationImpl continuationImpl;
        continuation.getClass();
        if (continuation instanceof ContinuationImpl) {
            continuationImpl = (ContinuationImpl) continuation;
        } else {
            continuationImpl = null;
        }
        if (continuationImpl != null && (continuation = continuationImpl.intercepted) == null) {
            ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) continuationImpl.getContext().get(ContinuationInterceptor.Key);
            if (continuationInterceptor != null) {
                continuation = continuationInterceptor.interceptContinuation(continuationImpl);
            } else {
                continuation = continuationImpl;
            }
            continuationImpl.intercepted = continuation;
        }
        return continuation;
    }

    public static final Object wrapWithContinuationImpl(Function2 function2, Object obj, final Continuation continuation) {
        Object obj2;
        function2.getClass();
        final CoroutineContext context = continuation.getContext();
        if (context == EmptyCoroutineContext.INSTANCE) {
            obj2 = new RestrictedContinuationImpl(continuation) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$1
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                protected final Object invokeSuspend(Object obj3) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj3);
                    return obj3;
                }
            };
        } else {
            obj2 = new ContinuationImpl(continuation, context) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$2
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                protected final Object invokeSuspend(Object obj3) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj3);
                    return obj3;
                }
            };
        }
        TypeIntrinsics.beforeCheckcastToFunctionOfArity$ar$ds(function2, 2);
        return function2.invoke(obj, obj2);
    }
}
