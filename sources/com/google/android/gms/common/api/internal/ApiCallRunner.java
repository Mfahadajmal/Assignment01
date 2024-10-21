package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ApiCallRunner {
    public final int type;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class FeatureRunner extends ApiCallRunner {
        public FeatureRunner(int i) {
            super(i);
        }

        public abstract Feature[] getRequiredFeatures(GoogleApiManager.ClientConnection clientConnection);

        public abstract boolean shouldAutoResolveMissingFeatures(GoogleApiManager.ClientConnection clientConnection);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class ListenerApiCallRunner extends FeatureRunner {
        protected final AppLifecycleMonitor completionSource$ar$class_merging$ar$class_merging;

        public ListenerApiCallRunner(int i, AppLifecycleMonitor appLifecycleMonitor) {
            super(i);
            this.completionSource$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        }

        protected abstract void doExecute(GoogleApiManager.ClientConnection clientConnection);

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void reportFailure(Status status) {
            this.completionSource$ar$class_merging$ar$class_merging.trySetException(new ApiException(status));
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void run(GoogleApiManager.ClientConnection clientConnection) {
            try {
                doExecute(clientConnection);
            } catch (DeadObjectException e) {
                reportFailure(ApiCallRunner.createFailureStatus(e));
                throw e;
            } catch (RemoteException e2) {
                reportFailure(ApiCallRunner.createFailureStatus(e2));
            } catch (RuntimeException e3) {
                reportFailure(e3);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void reportFailure(Exception exc) {
            this.completionSource$ar$class_merging$ar$class_merging.trySetException(exc);
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void trackAsInProgressCall$ar$class_merging$ar$class_merging$ar$class_merging(WindowTrackerFactory windowTrackerFactory, boolean z) {
        }
    }

    public ApiCallRunner(int i) {
        this.type = i;
    }

    public static Status createFailureStatus(RemoteException remoteException) {
        return new Status(19, remoteException.getClass().getSimpleName() + ": " + remoteException.getLocalizedMessage());
    }

    public abstract void reportFailure(Status status);

    public abstract void reportFailure(Exception exc);

    public abstract void run(GoogleApiManager.ClientConnection clientConnection);

    public abstract void trackAsInProgressCall$ar$class_merging$ar$class_merging$ar$class_merging(WindowTrackerFactory windowTrackerFactory, boolean z);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PendingResultApiCallRunner extends ApiCallRunner {
        protected final BaseImplementation$ApiMethodImpl apiMethod;

        public PendingResultApiCallRunner(int i, BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
            super(i);
            this.apiMethod = baseImplementation$ApiMethodImpl;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void reportFailure(Status status) {
            try {
                this.apiMethod.setFailedResult(status);
            } catch (IllegalStateException e) {
                Log.w("ApiCallRunner", "Exception reporting failure", e);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void run(GoogleApiManager.ClientConnection clientConnection) {
            try {
                this.apiMethod.run(clientConnection.client);
            } catch (RuntimeException e) {
                reportFailure(e);
            }
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void trackAsInProgressCall$ar$class_merging$ar$class_merging$ar$class_merging(final WindowTrackerFactory windowTrackerFactory, boolean z) {
            final BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl = this.apiMethod;
            windowTrackerFactory.WindowTrackerFactory$ar$executorProvider.put(baseImplementation$ApiMethodImpl, Boolean.valueOf(z));
            baseImplementation$ApiMethodImpl.addStatusListener(new PendingResult.StatusListener() { // from class: com.google.android.gms.common.api.internal.ConnectionlessInProgressCalls$1
                /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Map, java.lang.Object] */
                @Override // com.google.android.gms.common.api.PendingResult.StatusListener
                public final void onComplete(Status status) {
                    windowTrackerFactory.WindowTrackerFactory$ar$executorProvider.remove(baseImplementation$ApiMethodImpl);
                }
            });
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void reportFailure(Exception exc) {
            try {
                this.apiMethod.setFailedResult(new Status(10, exc.getClass().getSimpleName() + ": " + exc.getLocalizedMessage()));
            } catch (IllegalStateException e) {
                Log.w("ApiCallRunner", "Exception reporting failure", e);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TaskRunner extends FeatureRunner {
        private final AppLifecycleMonitor completionSource$ar$class_merging$ar$class_merging;
        private final TaskApiCall method;
        private final SpannableUtils$IdentifierSpan statusExceptionMapper$ar$class_merging$ar$class_merging$ar$class_merging;

        public TaskRunner(int i, TaskApiCall taskApiCall, AppLifecycleMonitor appLifecycleMonitor, SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan) {
            super(i);
            this.completionSource$ar$class_merging$ar$class_merging = appLifecycleMonitor;
            this.method = taskApiCall;
            this.statusExceptionMapper$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$IdentifierSpan;
            if (i == 2 && taskApiCall.autoResolveMissingFeatures) {
                throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public final Feature[] getRequiredFeatures(GoogleApiManager.ClientConnection clientConnection) {
            return this.method.features;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void reportFailure(Status status) {
            this.completionSource$ar$class_merging$ar$class_merging.trySetException(StrictModeUtils$VmPolicyBuilderCompatS.fromStatus(status));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.common.api.internal.RemoteCall, java.lang.Object] */
        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void run(GoogleApiManager.ClientConnection clientConnection) {
            try {
                TaskApiCall taskApiCall = this.method;
                taskApiCall.this$0.TaskApiCall$Builder$ar$execute.accept(clientConnection.client, this.completionSource$ar$class_merging$ar$class_merging);
            } catch (DeadObjectException e) {
                throw e;
            } catch (RemoteException e2) {
                reportFailure(ApiCallRunner.createFailureStatus(e2));
            } catch (RuntimeException e3) {
                reportFailure(e3);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public final boolean shouldAutoResolveMissingFeatures(GoogleApiManager.ClientConnection clientConnection) {
            return this.method.autoResolveMissingFeatures;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void trackAsInProgressCall$ar$class_merging$ar$class_merging$ar$class_merging(final WindowTrackerFactory windowTrackerFactory, boolean z) {
            ?? r0 = windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
            final AppLifecycleMonitor appLifecycleMonitor = this.completionSource$ar$class_merging$ar$class_merging;
            r0.put(appLifecycleMonitor, Boolean.valueOf(z));
            ((Task) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).addOnCompleteListener$ar$ds(new OnCompleteListener() { // from class: com.google.android.gms.common.api.internal.ConnectionlessInProgressCalls$2
                /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Map, java.lang.Object] */
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider.remove(appLifecycleMonitor);
                }
            });
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void reportFailure(Exception exc) {
            this.completionSource$ar$class_merging$ar$class_merging.trySetException(exc);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RegisterListenerRunner extends ListenerApiCallRunner {
        public final OptionalMethod listener$ar$class_merging$71ac95ff_0$ar$class_merging;

        public RegisterListenerRunner(OptionalMethod optionalMethod, AppLifecycleMonitor appLifecycleMonitor) {
            super(3, appLifecycleMonitor);
            this.listener$ar$class_merging$71ac95ff_0$ar$class_merging = optionalMethod;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner
        public final void doExecute(GoogleApiManager.ClientConnection clientConnection) {
            ((RegisterListenerMethod) this.listener$ar$class_merging$71ac95ff_0$ar$class_merging.OptionalMethod$ar$methodParams).registerListener$ar$class_merging$ar$class_merging(clientConnection.client, this.completionSource$ar$class_merging$ar$class_merging);
            ListenerHolder.ListenerKey listenerKey = ((RegisterListenerMethod) this.listener$ar$class_merging$71ac95ff_0$ar$class_merging.OptionalMethod$ar$methodParams).getListenerKey();
            if (listenerKey != null) {
                clientConnection.registeredListeners.put(listenerKey, this.listener$ar$class_merging$71ac95ff_0$ar$class_merging);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public final Feature[] getRequiredFeatures(GoogleApiManager.ClientConnection clientConnection) {
            return ((RegisterListenerMethod) this.listener$ar$class_merging$71ac95ff_0$ar$class_merging.OptionalMethod$ar$methodParams).requiredFeatures;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public final boolean shouldAutoResolveMissingFeatures(GoogleApiManager.ClientConnection clientConnection) {
            return ((RegisterListenerMethod) this.listener$ar$class_merging$71ac95ff_0$ar$class_merging.OptionalMethod$ar$methodParams).shouldAutoResolveMissingFeatures;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner, com.google.android.gms.common.api.internal.ApiCallRunner
        public final /* bridge */ /* synthetic */ void trackAsInProgressCall$ar$class_merging$ar$class_merging$ar$class_merging(WindowTrackerFactory windowTrackerFactory, boolean z) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UnregisterListenerRunner extends ListenerApiCallRunner {
        public final ListenerHolder.ListenerKey unregisterKey;

        public UnregisterListenerRunner(ListenerHolder.ListenerKey listenerKey, AppLifecycleMonitor appLifecycleMonitor) {
            super(4, appLifecycleMonitor);
            this.unregisterKey = listenerKey;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner
        public final void doExecute(GoogleApiManager.ClientConnection clientConnection) {
            OptionalMethod optionalMethod = (OptionalMethod) clientConnection.registeredListeners.remove(this.unregisterKey);
            if (optionalMethod != null) {
                ((UnregisterListenerMethod) optionalMethod.OptionalMethod$ar$returnType).this$0.unregister.accept(clientConnection.client, this.completionSource$ar$class_merging$ar$class_merging);
                ListenerHolder listenerHolder = ((RegisterListenerMethod) optionalMethod.OptionalMethod$ar$methodParams).listenerHolder;
                listenerHolder.listener = null;
                listenerHolder.listenerKey = null;
                return;
            }
            this.completionSource$ar$class_merging$ar$class_merging.trySetResult(false);
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public final Feature[] getRequiredFeatures(GoogleApiManager.ClientConnection clientConnection) {
            OptionalMethod optionalMethod = (OptionalMethod) clientConnection.registeredListeners.get(this.unregisterKey);
            if (optionalMethod == null) {
                return null;
            }
            return ((RegisterListenerMethod) optionalMethod.OptionalMethod$ar$methodParams).requiredFeatures;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public final boolean shouldAutoResolveMissingFeatures(GoogleApiManager.ClientConnection clientConnection) {
            OptionalMethod optionalMethod = (OptionalMethod) clientConnection.registeredListeners.get(this.unregisterKey);
            if (optionalMethod != null && ((RegisterListenerMethod) optionalMethod.OptionalMethod$ar$methodParams).shouldAutoResolveMissingFeatures) {
                return true;
            }
            return false;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner, com.google.android.gms.common.api.internal.ApiCallRunner
        public final /* bridge */ /* synthetic */ void trackAsInProgressCall$ar$class_merging$ar$class_merging$ar$class_merging(WindowTrackerFactory windowTrackerFactory, boolean z) {
        }
    }
}
