package com.google.common.util.concurrent;

import com.google.common.android.concurrent.ParcelableFuture;
import com.google.frameworks.client.data.android.impl.TransportChannel;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCall;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.ConnectivityState;
import io.grpc.cronet.CronetClientTransport;
import io.grpc.internal.BackoffPolicyRetryScheduler;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.DelayedStream;
import io.grpc.internal.InternalSubchannel;
import io.grpc.util.MultiChildLoadBalancer;
import java.util.ArrayDeque;
import java.util.Deque;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Object Futures$$ExternalSyntheticLambda1$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ Futures$$ExternalSyntheticLambda1(Object obj, int i) {
        this.switching_field = i;
        this.Futures$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.concurrent.Future, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r0v30, types: [io.grpc.internal.ManagedClientTransport$Listener, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v31, types: [io.grpc.internal.ManagedClientTransport$Listener, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v32, types: [io.grpc.internal.ManagedClientTransport$Listener, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.switching_field) {
            case 0:
                this.Futures$$ExternalSyntheticLambda1$ar$f$0.cancel(false);
                return;
            case 1:
                ((ParcelableFuture) this.Futures$$ExternalSyntheticLambda1$ar$f$0).notifyListener();
                return;
            case 2:
                ((TransportChannel.EnqueueingClientCall) this.Futures$$ExternalSyntheticLambda1$ar$f$0).delegate.halfClose();
                return;
            case 3:
                AsyncInterceptorsClientCall asyncInterceptorsClientCall = (AsyncInterceptorsClientCall) this.Futures$$ExternalSyntheticLambda1$ar$f$0;
                if (!asyncInterceptorsClientCall.aborted) {
                    asyncInterceptorsClientCall.halfClosed = true;
                    asyncInterceptorsClientCall.maybeHalfCloseOrTransitionState();
                    return;
                }
                return;
            case 4:
                AsyncInterceptorsClientCall.ClosedOnceListener closedOnceListener = (AsyncInterceptorsClientCall.ClosedOnceListener) this.Futures$$ExternalSyntheticLambda1$ar$f$0;
                if (!closedOnceListener.closed) {
                    closedOnceListener.delegate$ar$class_merging$a40ae667_0$ar$class_merging.onReady();
                    return;
                }
                return;
            case 5:
                ((OnDeviceFaceMeshCreateLogEvent) this.Futures$$ExternalSyntheticLambda1$ar$f$0).onReady();
                return;
            case 6:
                MlKitThreadPool.runOnThisThread((Deque) MlKitThreadPool.queuedRunnables.get(), this.Futures$$ExternalSyntheticLambda1$ar$f$0);
                return;
            case 7:
                MlKitThreadPool.queuedRunnables.set(new ArrayDeque());
                this.Futures$$ExternalSyntheticLambda1$ar$f$0.run();
                return;
            case 8:
                CronetClientTransport cronetClientTransport = (CronetClientTransport) this.Futures$$ExternalSyntheticLambda1$ar$f$0;
                cronetClientTransport.attrs = cronetClientTransport.listener.filterTransport(cronetClientTransport.attrs);
                ((CronetClientTransport) this.Futures$$ExternalSyntheticLambda1$ar$f$0).listener.transportReady();
                return;
            case 9:
                BackoffPolicyRetryScheduler backoffPolicyRetryScheduler = (BackoffPolicyRetryScheduler) this.Futures$$ExternalSyntheticLambda1$ar$f$0;
                MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = backoffPolicyRetryScheduler.scheduledHandle$ar$class_merging$ar$class_merging;
                if (acceptResolvedAddrRetVal != null && acceptResolvedAddrRetVal.isPending()) {
                    backoffPolicyRetryScheduler.scheduledHandle$ar$class_merging$ar$class_merging.cancel();
                }
                backoffPolicyRetryScheduler.policy$ar$class_merging = null;
                return;
            case 10:
                ((DelayedClientCall) this.Futures$$ExternalSyntheticLambda1$ar$f$0).realCall.halfClose();
                return;
            case 11:
                ((DelayedClientCall.DelayedListener) this.Futures$$ExternalSyntheticLambda1$ar$f$0).realListener$ar$class_merging$ar$class_merging.onReady();
                return;
            case 12:
                this.Futures$$ExternalSyntheticLambda1$ar$f$0.transportInUse(true);
                return;
            case 13:
                this.Futures$$ExternalSyntheticLambda1$ar$f$0.transportInUse(false);
                return;
            case 14:
                this.Futures$$ExternalSyntheticLambda1$ar$f$0.transportTerminated();
                return;
            case 15:
                ((DelayedStream) this.Futures$$ExternalSyntheticLambda1$ar$f$0).realStream.optimizeForDirectExecutor();
                return;
            case 16:
                ((DelayedStream) this.Futures$$ExternalSyntheticLambda1$ar$f$0).drainPendingCalls();
                return;
            case 17:
                ((DelayedStream) this.Futures$$ExternalSyntheticLambda1$ar$f$0).realStream.flush();
                return;
            case 18:
                ((DelayedStream) this.Futures$$ExternalSyntheticLambda1$ar$f$0).realStream.halfClose();
                return;
            case 19:
                ((DelayedStream.DelayedStreamListener) this.Futures$$ExternalSyntheticLambda1$ar$f$0).realListener.onReady();
                return;
            default:
                InternalSubchannel internalSubchannel = (InternalSubchannel) this.Futures$$ExternalSyntheticLambda1$ar$f$0;
                internalSubchannel.reconnectTask$ar$class_merging$ar$class_merging = null;
                internalSubchannel.channelLogger.log$ar$edu(2, "CONNECTING after backoff");
                ((InternalSubchannel) this.Futures$$ExternalSyntheticLambda1$ar$f$0).gotoNonErrorState(ConnectivityState.CONNECTING);
                ((InternalSubchannel) this.Futures$$ExternalSyntheticLambda1$ar$f$0).startNewTransport();
                return;
        }
    }

    public Futures$$ExternalSyntheticLambda1(Object obj, int i, byte[] bArr) {
        this.switching_field = i;
        this.Futures$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }
}
