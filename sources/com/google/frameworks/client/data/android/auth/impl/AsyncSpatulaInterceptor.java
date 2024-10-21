package com.google.frameworks.client.data.android.auth.impl;

import com.google.apps.tiktok.tracing.SpanEndSignal;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import com.google.frameworks.client.data.android.interceptor.Outcome;
import com.google.frameworks.client.data.android.interceptor.ResponseOutcome;
import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.RetryingNameResolver;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AsyncSpatulaInterceptor implements AsyncClientInterceptor {
    private static final Metadata.Key X_GOOG_SPATULA_HEADER_NAME = Metadata.Key.of("X-Goog-Spatula", Metadata.ASCII_STRING_MARSHALLER);
    private ListenableFuture headerFuture;
    private final RetryingNameResolver.ResolutionResultListener headerProvider$ar$class_merging$ar$class_merging$ar$class_merging;

    public AsyncSpatulaInterceptor(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.headerProvider$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueOnCompleteProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final Outcome continueRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        try {
            requestHeaderContext.requestMetadata.put(X_GOOG_SPATULA_HEADER_NAME, (String) ContextDataProvider.getDone(this.headerFuture));
            return Outcome.PROCEED;
        } catch (ExecutionException e) {
            Status.Code code = Status.Code.UNAUTHENTICATED;
            List list = Status.STATUS_LIST;
            Status withDescription = code.toStatus().withCause(e.getCause()).withDescription("Could not retrieve spatula header");
            Metadata metadata = new Metadata();
            ContextDataProvider.checkState(!withDescription.isOk(), "Error status must not be ok");
            return new Outcome(2, null, new RemoteModelManager.RemoteModelManagerRegistration(withDescription, metadata), null, null);
        }
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome continueRequestMessageProcessing$ar$ds() {
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueResponseHeaderProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueResponseMessageProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startOnCompleteProcessing$ar$class_merging$ar$class_merging(RemoteModelManager remoteModelManager) {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final Outcome startRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        SpanEndSignal beginSpan = Tracer.beginSpan("AsyncSpatulaInterceptor#headerFuture");
        try {
            ListenableFuture header = this.headerProvider$ar$class_merging$ar$class_merging$ar$class_merging.getHeader();
            beginSpan.attachToFuture$ar$ds(header);
            this.headerFuture = header;
            header.getClass();
            Outcome outcome = new Outcome(4, null, null, header, null);
            beginSpan.close();
            return outcome;
        } catch (Throwable th) {
            try {
                beginSpan.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome startRequestMessageProcessing$ar$ds() {
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startResponseHeaderProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startResponseMessageProcessing$ar$ds() {
        return ResponseOutcome.PROCEED;
    }
}
