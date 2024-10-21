package com.google.auth.oauth2;

import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.api.client.util.Clock;
import com.google.auth.Credentials;
import com.google.auth.Retryable;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.io.BaseEncoding;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;
import io.grpc.internal.RetriableStream;
import j$.time.Duration;
import j$.util.Objects;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class OAuth2Credentials extends Credentials {
    static final Duration DEFAULT_EXPIRATION_MARGIN = Duration.ofMinutes(3);
    static final Duration DEFAULT_REFRESH_MARGIN = Duration.ofMinutes(3).plusSeconds(45);
    private static final ImmutableMap EMPTY_EXTRA_HEADERS = RegularImmutableMap.EMPTY;
    private static final long serialVersionUID = 4556936364828217687L;
    transient Clock clock;
    private final Duration expirationMargin;
    final Object lock;
    private final Duration refreshMargin;
    transient RefreshTask refreshTask;
    public volatile OAuthValue value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        public AccessToken accessToken;
        public final Duration refreshMargin = OAuth2Credentials.DEFAULT_REFRESH_MARGIN;
        public final Duration expirationMargin = OAuth2Credentials.DEFAULT_EXPIRATION_MARGIN;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FutureCallbackToMetadataCallbackAdapter implements FutureCallback {
        private final GoogleAuthLibraryCallCredentials.AnonymousClass1 callback$ar$class_merging$e7bb7865_0;

        public FutureCallbackToMetadataCallbackAdapter(GoogleAuthLibraryCallCredentials.AnonymousClass1 anonymousClass1) {
            this.callback$ar$class_merging$e7bb7865_0 = anonymousClass1;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public final void onFailure(Throwable th) {
            if (th instanceof ExecutionException) {
                th = th.getCause();
            }
            GoogleAuthLibraryCallCredentials.AnonymousClass1 anonymousClass1 = this.callback$ar$class_merging$e7bb7865_0;
            if ((th instanceof Retryable) && ((Retryable) th).isRetryable()) {
                anonymousClass1.val$applier.fail(Status.UNAVAILABLE.withDescription("Credentials failed to obtain metadata").withCause(th));
            } else {
                anonymousClass1.val$applier.fail(Status.UNAUTHENTICATED.withDescription("Failed computing credential metadata").withCause(th));
            }
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
            Metadata metadata;
            Map map = ((OAuthValue) obj).requestMetadata;
            GoogleAuthLibraryCallCredentials.AnonymousClass1 anonymousClass1 = this.callback$ar$class_merging$e7bb7865_0;
            try {
                synchronized (anonymousClass1.this$0) {
                    GoogleAuthLibraryCallCredentials googleAuthLibraryCallCredentials = anonymousClass1.this$0;
                    Map map2 = googleAuthLibraryCallCredentials.lastMetadata;
                    if (map2 == null || map2 != map) {
                        Metadata metadata2 = new Metadata();
                        for (String str : map.keySet()) {
                            if (str.endsWith("-bin")) {
                                Metadata.BinaryKey binaryKey = new Metadata.BinaryKey(str, Metadata.BINARY_BYTE_MARSHALLER$ar$class_merging$ar$class_merging$ar$class_merging);
                                Iterator it = ((List) map.get(str)).iterator();
                                while (it.hasNext()) {
                                    metadata2.put(binaryKey, BaseEncoding.BASE64.decode((String) it.next()));
                                }
                            } else {
                                Metadata.Key of = Metadata.Key.of(str, Metadata.ASCII_STRING_MARSHALLER);
                                Iterator it2 = ((List) map.get(str)).iterator();
                                while (it2.hasNext()) {
                                    metadata2.put(of, (String) it2.next());
                                }
                            }
                        }
                        googleAuthLibraryCallCredentials.lastHeaders = metadata2;
                        anonymousClass1.this$0.lastMetadata = map;
                    }
                    metadata = anonymousClass1.this$0.lastHeaders;
                }
                anonymousClass1.val$applier.apply(metadata);
            } catch (Throwable th) {
                anonymousClass1.val$applier.fail(Status.UNAUTHENTICATED.withDescription("Failed to convert credential metadata").withCause(th));
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OAuthValue implements Serializable {
        public final Map requestMetadata;
        public final AccessToken temporaryAccess;

        private OAuthValue(AccessToken accessToken, Map map) {
            this.temporaryAccess = accessToken;
            this.requestMetadata = map;
        }

        static OAuthValue create(AccessToken accessToken, Map map) {
            ImmutableMap.Builder builder = new ImmutableMap.Builder();
            builder.put$ar$ds$de9b9d28_0("Authorization", ImmutableList.of((Object) "Bearer ".concat(String.valueOf(accessToken.tokenValue))));
            builder.putAll$ar$ds$2e89a4e2_0(map.entrySet());
            return new OAuthValue(accessToken, builder.buildOrThrow());
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof OAuthValue)) {
                return false;
            }
            OAuthValue oAuthValue = (OAuthValue) obj;
            if (!Objects.equals(this.requestMetadata, oAuthValue.requestMetadata) || !Objects.equals(this.temporaryAccess, oAuthValue.temporaryAccess)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Objects.hash(this.temporaryAccess, this.requestMetadata);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RefreshTask extends AbstractFuture implements Runnable {
        public final ListenableFutureTask task;

        public RefreshTask(OAuth2Credentials oAuth2Credentials, ListenableFutureTask listenableFutureTask, RefreshTaskListener refreshTaskListener) {
            this.task = listenableFutureTask;
            listenableFutureTask.addListener(refreshTaskListener, DirectExecutor.INSTANCE);
            ContextDataProvider.addCallback(listenableFutureTask, new AiCoreEndpoint.DownloadCallBackInternal.AnonymousClass1(this, 3), DirectExecutor.INSTANCE);
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.task.run();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RefreshTaskListener implements Runnable {
        private final ListenableFutureTask task;

        public RefreshTaskListener(ListenableFutureTask listenableFutureTask) {
            this.task = listenableFutureTask;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ListenableFutureTask listenableFutureTask = this.task;
            OAuth2Credentials oAuth2Credentials = OAuth2Credentials.this;
            synchronized (oAuth2Credentials.lock) {
                try {
                    oAuth2Credentials.value = (OAuthValue) ContextDataProvider.getDone(listenableFutureTask);
                    throw null;
                } catch (Exception unused) {
                    RefreshTask refreshTask = oAuth2Credentials.refreshTask;
                    if (refreshTask != null && refreshTask.task == listenableFutureTask) {
                        oAuth2Credentials.refreshTask = null;
                    }
                } catch (Throwable th) {
                    RefreshTask refreshTask2 = oAuth2Credentials.refreshTask;
                    if (refreshTask2 != null && refreshTask2.task == listenableFutureTask) {
                        oAuth2Credentials.refreshTask = null;
                    }
                    throw th;
                }
            }
        }
    }

    protected OAuth2Credentials() {
        this(null, DEFAULT_REFRESH_MARGIN, DEFAULT_EXPIRATION_MARGIN);
    }

    private final int getState$ar$edu() {
        if (this.value == null) {
            return 3;
        }
        return 1;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.clock = Clock.SYSTEM;
        this.refreshTask = null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OAuth2Credentials)) {
            return false;
        }
        return Objects.equals(this.value, ((OAuth2Credentials) obj).value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, java.lang.Runnable] */
    @Override // com.google.auth.Credentials
    public void getRequestMetadata$ar$class_merging$ar$ds(Executor executor, GoogleAuthLibraryCallCredentials.AnonymousClass1 anonymousClass1) {
        RetriableStream.HedgingPlan hedgingPlan;
        ListenableFuture listenableFuture;
        ListenableFuture listenableFuture2;
        if (getState$ar$edu() == 1) {
            listenableFuture2 = ContextDataProvider.immediateFuture(this.value);
        } else {
            synchronized (this.lock) {
                if (getState$ar$edu() != 1) {
                    synchronized (this.lock) {
                        RefreshTask refreshTask = this.refreshTask;
                        if (refreshTask != null) {
                            hedgingPlan = new RetriableStream.HedgingPlan((Object) refreshTask, false);
                        } else {
                            ListenableFutureTask listenableFutureTask = new ListenableFutureTask(new Callable() { // from class: com.google.auth.oauth2.OAuth2Credentials.1
                                @Override // java.util.concurrent.Callable
                                public final /* bridge */ /* synthetic */ Object call() {
                                    return OAuthValue.create(OAuth2Credentials.this.refreshAccessToken(), OAuth2Credentials.EMPTY_EXTRA_HEADERS);
                                }
                            });
                            this.refreshTask = new RefreshTask(this, listenableFutureTask, new RefreshTaskListener(listenableFutureTask));
                            hedgingPlan = new RetriableStream.HedgingPlan((Object) this.refreshTask, true);
                        }
                    }
                } else {
                    hedgingPlan = null;
                }
            }
            if (hedgingPlan != null && hedgingPlan.isHedgeable) {
                executor.execute(hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis);
            }
            synchronized (this.lock) {
                if (getState$ar$edu() != 3) {
                    listenableFuture = ContextDataProvider.immediateFuture(this.value);
                } else if (hedgingPlan != null) {
                    listenableFuture = hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis;
                } else {
                    listenableFuture = ContextDataProvider.immediateFailedFuture(new IllegalStateException("Credentials expired, but there is no task to refresh"));
                }
            }
            listenableFuture2 = listenableFuture;
        }
        ContextDataProvider.addCallback(listenableFuture2, new FutureCallbackToMetadataCallbackAdapter(anonymousClass1), DirectExecutor.INSTANCE);
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public AccessToken refreshAccessToken() {
        throw new IllegalStateException("OAuth2Credentials instance does not support refreshing the access token. An instance with a new access token should be used, or a derived type that supports refreshing.");
    }

    public String toString() {
        Map map;
        AccessToken accessToken;
        OAuthValue oAuthValue = this.value;
        if (oAuthValue != null) {
            map = oAuthValue.requestMetadata;
            accessToken = oAuthValue.temporaryAccess;
        } else {
            map = null;
            accessToken = null;
        }
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("requestMetadata", map);
        stringHelper.addHolder$ar$ds("temporaryAccess", accessToken);
        return stringHelper.toString();
    }

    public OAuth2Credentials(AccessToken accessToken, Duration duration, Duration duration2) {
        this.lock = new byte[0];
        this.value = null;
        this.clock = Clock.SYSTEM;
        if (accessToken != null) {
            this.value = OAuthValue.create(accessToken, EMPTY_EXTRA_HEADERS);
        }
        duration.getClass();
        this.refreshMargin = duration;
        ContextDataProvider.checkArgument(!duration.isNegative(), (Object) "refreshMargin can't be negative");
        duration2.getClass();
        this.expirationMargin = duration2;
        ContextDataProvider.checkArgument(!duration2.isNegative(), (Object) "expirationMargin can't be negative");
    }
}
