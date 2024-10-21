package io.grpc;

import _COROUTINE._BOUNDARY;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.util.MultiChildLoadBalancer;
import j$.util.DesugarCollections;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class NameResolver {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Args {
        private final ChannelLogger channelLogger;
        public final int defaultPort;
        public final Executor executor;
        public final ProxyDetector proxyDetector;
        public final ScheduledExecutorService scheduledExecutorService;
        public final ServiceConfigParser serviceConfigParser;
        public final SynchronizationContext syncContext;

        public Args(Integer num, ProxyDetector proxyDetector, SynchronizationContext synchronizationContext, ServiceConfigParser serviceConfigParser, ScheduledExecutorService scheduledExecutorService, ChannelLogger channelLogger, Executor executor) {
            num.getClass();
            this.defaultPort = num.intValue();
            proxyDetector.getClass();
            this.proxyDetector = proxyDetector;
            synchronizationContext.getClass();
            this.syncContext = synchronizationContext;
            serviceConfigParser.getClass();
            this.serviceConfigParser = serviceConfigParser;
            this.scheduledExecutorService = scheduledExecutorService;
            this.channelLogger = channelLogger;
            this.executor = executor;
        }

        public final String toString() {
            MoreObjects$ToStringHelper add = ContextDataProvider.toStringHelper(this).add("defaultPort", this.defaultPort);
            add.addHolder$ar$ds("proxyDetector", this.proxyDetector);
            add.addHolder$ar$ds("syncContext", this.syncContext);
            add.addHolder$ar$ds("serviceConfigParser", this.serviceConfigParser);
            add.addHolder$ar$ds("scheduledExecutorService", this.scheduledExecutorService);
            add.addHolder$ar$ds("channelLogger", this.channelLogger);
            add.addHolder$ar$ds("executor", this.executor);
            add.addHolder$ar$ds("overrideAuthority", null);
            return add.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ConfigOrError {
        public final Object config;
        public final Status status;

        public ConfigOrError(Object obj) {
            this.config = obj;
            this.status = null;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                ConfigOrError configOrError = (ConfigOrError) obj;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.status, configOrError.status) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.config, configOrError.config)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.status, this.config});
        }

        public final String toString() {
            if (this.config != null) {
                MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
                stringHelper.addHolder$ar$ds("config", this.config);
                return stringHelper.toString();
            }
            MoreObjects$ToStringHelper stringHelper2 = ContextDataProvider.toStringHelper(this);
            stringHelper2.addHolder$ar$ds("error", this.status);
            return stringHelper2.toString();
        }

        public ConfigOrError(Status status) {
            this.config = null;
            this.status = status;
            ContextDataProvider.checkArgument(!status.isOk(), "cannot use OK status: %s", status);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Factory {
        public abstract String getDefaultScheme();

        public abstract NameResolver newNameResolver(URI uri, Args args);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Listener2 {
        public abstract void onError(Status status);

        public abstract void onResult(ResolutionResult resolutionResult);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ResolutionResult {
        public final List addresses;
        public final Attributes attributes;
        public final ConfigOrError serviceConfig;

        public ResolutionResult(List list, Attributes attributes, ConfigOrError configOrError) {
            this.addresses = DesugarCollections.unmodifiableList(new ArrayList(list));
            attributes.getClass();
            this.attributes = attributes;
            this.serviceConfig = configOrError;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof ResolutionResult)) {
                return false;
            }
            ResolutionResult resolutionResult = (ResolutionResult) obj;
            if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.addresses, resolutionResult.addresses) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.attributes, resolutionResult.attributes) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.serviceConfig, resolutionResult.serviceConfig)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.addresses, this.attributes, this.serviceConfig});
        }

        public final String toString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("addresses", this.addresses);
            stringHelper.addHolder$ar$ds("attributes", this.attributes);
            stringHelper.addHolder$ar$ds("serviceConfig", this.serviceConfig);
            return stringHelper.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ServiceConfigParser {
        private final MultiChildLoadBalancer.AcceptResolvedAddrRetVal autoLoadBalancerFactory$ar$class_merging;
        private final int maxHedgedAttemptsLimit;
        private final int maxRetryAttemptsLimit;
        private final boolean retryEnabled;

        public ServiceConfigParser() {
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x013b  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0177  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0183  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x019f  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x01af A[Catch: RuntimeException -> 0x0259, TryCatch #0 {RuntimeException -> 0x0259, blocks: (B:3:0x0004, B:25:0x014a, B:29:0x0155, B:32:0x015e, B:35:0x017a, B:38:0x0186, B:39:0x0193, B:42:0x01a7, B:44:0x01af, B:45:0x0253, B:48:0x01bb, B:49:0x01bf, B:51:0x01c5, B:53:0x01d8, B:60:0x01de, B:61:0x01e2, B:63:0x01e8, B:65:0x0202, B:68:0x0210, B:72:0x0217, B:74:0x021d, B:76:0x022c, B:81:0x0249, B:82:0x01a1, B:86:0x013d, B:88:0x0141, B:90:0x0147, B:120:0x0125, B:96:0x000a, B:98:0x0017, B:99:0x001e, B:101:0x0024, B:103:0x002c, B:104:0x003d, B:107:0x0044, B:108:0x004d, B:110:0x0053, B:112:0x005f, B:114:0x0080, B:115:0x00a6, B:117:0x00a7, B:8:0x00b2, B:10:0x00b8, B:11:0x00c3, B:13:0x00c9, B:15:0x00db, B:17:0x00df, B:19:0x00e5, B:20:0x00fa, B:22:0x0104, B:92:0x0111), top: B:2:0x0004, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x01bb A[Catch: RuntimeException -> 0x0259, TryCatch #0 {RuntimeException -> 0x0259, blocks: (B:3:0x0004, B:25:0x014a, B:29:0x0155, B:32:0x015e, B:35:0x017a, B:38:0x0186, B:39:0x0193, B:42:0x01a7, B:44:0x01af, B:45:0x0253, B:48:0x01bb, B:49:0x01bf, B:51:0x01c5, B:53:0x01d8, B:60:0x01de, B:61:0x01e2, B:63:0x01e8, B:65:0x0202, B:68:0x0210, B:72:0x0217, B:74:0x021d, B:76:0x022c, B:81:0x0249, B:82:0x01a1, B:86:0x013d, B:88:0x0141, B:90:0x0147, B:120:0x0125, B:96:0x000a, B:98:0x0017, B:99:0x001e, B:101:0x0024, B:103:0x002c, B:104:0x003d, B:107:0x0044, B:108:0x004d, B:110:0x0053, B:112:0x005f, B:114:0x0080, B:115:0x00a6, B:117:0x00a7, B:8:0x00b2, B:10:0x00b8, B:11:0x00c3, B:13:0x00c9, B:15:0x00db, B:17:0x00df, B:19:0x00e5, B:20:0x00fa, B:22:0x0104, B:92:0x0111), top: B:2:0x0004, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x01a1 A[Catch: RuntimeException -> 0x0259, TryCatch #0 {RuntimeException -> 0x0259, blocks: (B:3:0x0004, B:25:0x014a, B:29:0x0155, B:32:0x015e, B:35:0x017a, B:38:0x0186, B:39:0x0193, B:42:0x01a7, B:44:0x01af, B:45:0x0253, B:48:0x01bb, B:49:0x01bf, B:51:0x01c5, B:53:0x01d8, B:60:0x01de, B:61:0x01e2, B:63:0x01e8, B:65:0x0202, B:68:0x0210, B:72:0x0217, B:74:0x021d, B:76:0x022c, B:81:0x0249, B:82:0x01a1, B:86:0x013d, B:88:0x0141, B:90:0x0147, B:120:0x0125, B:96:0x000a, B:98:0x0017, B:99:0x001e, B:101:0x0024, B:103:0x002c, B:104:0x003d, B:107:0x0044, B:108:0x004d, B:110:0x0053, B:112:0x005f, B:114:0x0080, B:115:0x00a6, B:117:0x00a7, B:8:0x00b2, B:10:0x00b8, B:11:0x00c3, B:13:0x00c9, B:15:0x00db, B:17:0x00df, B:19:0x00e5, B:20:0x00fa, B:22:0x0104, B:92:0x0111), top: B:2:0x0004, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0185  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0179  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x013d A[Catch: RuntimeException -> 0x0259, TryCatch #0 {RuntimeException -> 0x0259, blocks: (B:3:0x0004, B:25:0x014a, B:29:0x0155, B:32:0x015e, B:35:0x017a, B:38:0x0186, B:39:0x0193, B:42:0x01a7, B:44:0x01af, B:45:0x0253, B:48:0x01bb, B:49:0x01bf, B:51:0x01c5, B:53:0x01d8, B:60:0x01de, B:61:0x01e2, B:63:0x01e8, B:65:0x0202, B:68:0x0210, B:72:0x0217, B:74:0x021d, B:76:0x022c, B:81:0x0249, B:82:0x01a1, B:86:0x013d, B:88:0x0141, B:90:0x0147, B:120:0x0125, B:96:0x000a, B:98:0x0017, B:99:0x001e, B:101:0x0024, B:103:0x002c, B:104:0x003d, B:107:0x0044, B:108:0x004d, B:110:0x0053, B:112:0x005f, B:114:0x0080, B:115:0x00a6, B:117:0x00a7, B:8:0x00b2, B:10:0x00b8, B:11:0x00c3, B:13:0x00c9, B:15:0x00db, B:17:0x00df, B:19:0x00e5, B:20:0x00fa, B:22:0x0104, B:92:0x0111), top: B:2:0x0004, inners: #1 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final io.grpc.NameResolver.ConfigOrError parseServiceConfig(java.util.Map r18) {
            /*
                Method dump skipped, instructions count: 620
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.NameResolver.ServiceConfigParser.parseServiceConfig(java.util.Map):io.grpc.NameResolver$ConfigOrError");
        }

        public ServiceConfigParser(boolean z, int i, int i2, MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal) {
            this();
            this.retryEnabled = z;
            this.maxRetryAttemptsLimit = i;
            this.maxHedgedAttemptsLimit = i2;
            acceptResolvedAddrRetVal.getClass();
            this.autoLoadBalancerFactory$ar$class_merging = acceptResolvedAddrRetVal;
        }
    }

    public abstract String getServiceAuthority();

    public abstract void shutdown();

    public void start(Listener2 listener2) {
        throw null;
    }

    public void refresh() {
    }
}
