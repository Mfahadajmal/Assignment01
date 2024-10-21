package com.google.android.gms.clearcut.internal;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.clearcut.AbstractLogEventBuilder;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.ClearcutLoggerApi;
import com.google.android.gms.clearcut.Features;
import com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutLoggerApiImpl extends GoogleApi implements ClearcutLoggerApi {
    public final Supplier logErrorQueueEnabledSupplier;

    static {
        new AtomicBoolean(false);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ClearcutLoggerApiImpl(android.content.Context r5, com.google.common.base.Supplier r6) {
        /*
            r4 = this;
            io.grpc.okhttp.internal.OptionalMethod r0 = com.google.android.gms.clearcut.ClearcutLogger.API$ar$class_merging$ar$class_merging$ar$class_merging
            com.google.android.gms.common.api.Api$ApiOptions$NoOptions r1 = com.google.android.gms.common.api.Api$ApiOptions.NO_OPTIONS
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r2 = new com.google.android.gms.common.api.GoogleApi$Settings$Builder
            r2.<init>()
            com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan r3 = new com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan
            r3.<init>()
            r2.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging = r3
            com.google.android.gms.common.api.GoogleApi$Settings r2 = r2.build()
            r4.<init>(r5, r0, r1, r2)
            r4.logErrorQueueEnabledSupplier = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.<init>(android.content.Context, com.google.common.base.Supplier):void");
    }

    public final void logError$ar$ds$78ed6e06_0(BatchedLogErrorParcelable batchedLogErrorParcelable) {
        if (batchedLogErrorParcelable.logErrors.isEmpty()) {
            SpannableUtils$NonCopyableTextSpan.forResult(Status.RESULT_SUCCESS);
            return;
        }
        TaskApiCall.Builder builder = new TaskApiCall.Builder();
        builder.TaskApiCall$Builder$ar$execute = new ClearcutLoggerApiImpl$$ExternalSyntheticLambda2(this, batchedLogErrorParcelable, 0);
        builder.TaskApiCall$Builder$ar$features = new Feature[]{Features.LOG_ERROR};
        builder.autoResolveMissingFeatures = false;
        doBestEffortWrite(builder.build());
    }

    public final PendingResult logEventInternal$ar$ds(AbstractLogEventBuilder abstractLogEventBuilder) {
        LogEventMethodImpl logEventMethodImpl = new LogEventMethodImpl(abstractLogEventBuilder, this.wrapper);
        super.doNonListenerCall$ar$ds(2, logEventMethodImpl);
        return logEventMethodImpl;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LogEventMethodImpl extends BaseImplementation$ApiMethodImpl {
        private final AbstractLogEventBuilder logEventBuilder;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class LogEventCallback extends IClearcutLoggerCallbacks.Stub {
            public LogEventCallback() {
                super(null);
            }

            @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks.Stub
            public final void onLogEvent(Status status) {
                LogEventMethodImpl.this.setResult(status);
            }
        }

        public LogEventMethodImpl(AbstractLogEventBuilder abstractLogEventBuilder, GoogleApiClient googleApiClient) {
            super(ClearcutLogger.API$ar$class_merging$ar$class_merging$ar$class_merging, googleApiClient);
            this.logEventBuilder = abstractLogEventBuilder;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:116:0x029e  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x021f A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0225 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x020a  */
        @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void doExecute(com.google.android.gms.clearcut.internal.ClearcutLoggerClientImpl r21) {
            /*
                Method dump skipped, instructions count: 744
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.LogEventMethodImpl.doExecute(com.google.android.gms.clearcut.internal.ClearcutLoggerClientImpl):void");
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        protected final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }
}
