package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.NameResolver;
import io.grpc.ProxyDetector;
import io.grpc.SynchronizationContext;
import io.grpc.internal.SharedResourceHolder;
import j$.util.DesugarCollections;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DnsNameResolver extends NameResolver {
    private static final String JNDI_LOCALHOST_PROPERTY;
    private static final String JNDI_PROPERTY;
    private static final String JNDI_TXT_PROPERTY;
    public static final Set SERVICE_CONFIG_CHOICE_KEYS;
    static final boolean enableJndi;
    static final boolean enableJndiLocalhost;
    protected static final boolean enableTxt;
    private static String localHostname;
    public static final Logger logger;
    public static final ResourceResolverFactory resourceResolverFactory;
    private final String authority;
    public final long cacheTtlNanos;
    private Executor executor;
    private final SharedResourceHolder.Resource executorResource;
    public final String host;
    private NameResolver.Listener2 listener;
    public final int port;
    final ProxyDetector proxyDetector;
    public boolean resolved;
    public boolean resolving;
    public final NameResolver.ServiceConfigParser serviceConfigParser;
    private boolean shutdown;
    public final Stopwatch stopwatch;
    public final SynchronizationContext syncContext;
    private final boolean usingExecutorResource;
    public final Random random = new Random();
    protected volatile int addressResolver$ar$class_merging$ar$edu = JdkAddressResolver.INSTANCE$ar$edu;
    public final AtomicReference resourceResolver = new AtomicReference();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class JdkAddressResolver {
        public static final int INSTANCE$ar$edu = 1;
        private static final /* synthetic */ int[] $VALUES$ar$edu$9db33419_0 = {INSTANCE$ar$edu};

        public static int[] values$ar$edu$70e17f74_0() {
            return new int[]{1};
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Resolve implements Runnable {
        private final NameResolver.Listener2 savedListener;

        public Resolve(NameResolver.Listener2 listener2) {
            listener2.getClass();
            this.savedListener = listener2;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(15:1|(1:3)|4|5|6|(2:308|309)(1:8)|9|(4:11|(1:13)|14|15)(14:41|42|43|44|45|46|(4:49|50|51|47)|76|77|78|(1:80)(7:81|(6:254|(3:(1:257)|258|(1:262))(2:263|(1:265)(3:266|(5:269|270|(1:281)(4:272|(1:280)(1:276)|277|278)|279|267)|282))|(2:248|249)|85|(12:87|88|89|90|(5:93|94|(7:126|127|128|129|130|131|132)(9:96|97|98|99|100|101|102|103|(2:105|106)(3:108|109|110))|107|91)|139|140|(14:143|144|145|(2:148|146)|149|150|(5:154|(3:157|(2:159|160)(1:161)|155)|162|163|(2:166|167)(1:165))|179|(4:181|(1:188)(1:184)|185|(3:187|163|(0)(0)))|189|(5:193|(3:196|(1:198)(1:199)|194)|200|163|(0)(0))|201|(2:203|(0)(0))(7:204|205|206|207|208|209|210)|141)|221|(1:169)(1:178)|(2:171|(1:173)(1:176))(1:177)|174)(7:228|229|230|231|232|233|234)|175)|83|(0)|85|(0)(0)|175)|56|57|(6:59|60|(1:62)(1:64)|63|23|24)(5:65|(1:67)|68|(1:70)|71))|16|17|(1:26)(1:21)|22|23|24|(2:(0)|(1:294))) */
        /* JADX WARN: Code restructure failed: missing block: B:283:0x011b, code lost:
        
            if (r12 != false) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x03c3, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x03c4, code lost:
        
            r2 = r0;
            r5 = r5;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:165:0x02a2 A[LOOP:2: B:141:0x01f9->B:165:0x02a2, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:166:0x02a1 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:171:0x02f3 A[Catch: all -> 0x030c, IOException -> 0x0312, TryCatch #19 {IOException -> 0x0312, blocks: (B:88:0x014f, B:140:0x01f4, B:141:0x01f9, B:143:0x01ff, B:145:0x0205, B:146:0x020d, B:148:0x0213, B:150:0x022a, B:152:0x0232, B:154:0x0238, B:155:0x023c, B:157:0x0242, B:179:0x0253, B:181:0x025b, B:185:0x0268, B:189:0x0274, B:191:0x027c, B:193:0x0282, B:194:0x0286, B:196:0x028c, B:201:0x0298, B:205:0x02a5, B:209:0x02b2, B:210:0x02b9, B:220:0x02c0, B:171:0x02f3, B:173:0x02f7, B:176:0x02fd, B:178:0x02d6, B:224:0x02df), top: B:87:0x014f }] */
        /* JADX WARN: Removed duplicated region for block: B:177:0x0308  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x03f4  */
        /* JADX WARN: Removed duplicated region for block: B:228:0x0318 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:248:0x0132 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:298:0x0360 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:303:? A[Catch: all -> 0x036e, Exception -> 0x0371, SYNTHETIC, TRY_LEAVE, TryCatch #1 {all -> 0x036e, blocks: (B:175:0x0330, B:234:0x0328, B:55:0x0373, B:299:0x0360, B:300:0x036d), top: B:41:0x0085 }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0408  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x038d A[Catch: all -> 0x03c6, IOException -> 0x03ca, TRY_LEAVE, TryCatch #32 {IOException -> 0x03ca, all -> 0x03c6, blocks: (B:57:0x0389, B:59:0x038d, B:65:0x03a9, B:67:0x03ad, B:68:0x03af, B:70:0x03b3), top: B:56:0x0389 }] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x03a9 A[Catch: all -> 0x03c6, IOException -> 0x03ca, TRY_ENTER, TryCatch #32 {IOException -> 0x03ca, all -> 0x03c6, blocks: (B:57:0x0389, B:59:0x038d, B:65:0x03a9, B:67:0x03ad, B:68:0x03af, B:70:0x03b3), top: B:56:0x0389 }] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x014f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 1053
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DnsNameResolver.Resolve.run():void");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ResourceResolver {
        List resolveTxt$ar$ds();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface ResourceResolverFactory {
        ResourceResolver newResourceResolver();

        Throwable unavailabilityCause();
    }

    static {
        Logger logger2 = Logger.getLogger(DnsNameResolver.class.getName());
        logger = logger2;
        SERVICE_CONFIG_CHOICE_KEYS = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList("clientLanguage", "percentage", "clientHostname", "serviceConfig")));
        String property = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "true");
        JNDI_PROPERTY = property;
        String property2 = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", "false");
        JNDI_LOCALHOST_PROPERTY = property2;
        String property3 = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", "false");
        JNDI_TXT_PROPERTY = property3;
        enableJndi = Boolean.parseBoolean(property);
        enableJndiLocalhost = Boolean.parseBoolean(property2);
        enableTxt = Boolean.parseBoolean(property3);
        ResourceResolverFactory resourceResolverFactory2 = null;
        try {
            try {
                try {
                    ResourceResolverFactory resourceResolverFactory3 = (ResourceResolverFactory) Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, DnsNameResolver.class.getClassLoader()).asSubclass(ResourceResolverFactory.class).getConstructor(null).newInstance(null);
                    if (resourceResolverFactory3.unavailabilityCause() != null) {
                        logger2.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "JndiResourceResolverFactory not available, skipping.", resourceResolverFactory3.unavailabilityCause());
                    } else {
                        resourceResolverFactory2 = resourceResolverFactory3;
                    }
                } catch (Exception e) {
                    logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "Can't construct JndiResourceResolverFactory, skipping.", (Throwable) e);
                }
            } catch (Exception e2) {
                logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "Can't find JndiResourceResolverFactory ctor, skipping.", (Throwable) e2);
            }
        } catch (ClassCastException e3) {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "Unable to cast JndiResourceResolverFactory, skipping.", (Throwable) e3);
        } catch (ClassNotFoundException e4) {
            logger.logp(Level.FINE, "io.grpc.internal.DnsNameResolver", "getResourceResolverFactory", "Unable to find JndiResourceResolverFactory, skipping.", (Throwable) e4);
        }
        resourceResolverFactory = resourceResolverFactory2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DnsNameResolver(String str, NameResolver.Args args, SharedResourceHolder.Resource resource, Stopwatch stopwatch, boolean z) {
        boolean z2;
        args.getClass();
        this.executorResource = resource;
        str.getClass();
        URI create = URI.create("//".concat(str));
        if (create.getHost() != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        ContextDataProvider.checkArgument(z2, "Invalid DNS name: %s", str);
        String authority = create.getAuthority();
        authority.getClass();
        this.authority = authority;
        this.host = create.getHost();
        if (create.getPort() == -1) {
            this.port = args.defaultPort;
        } else {
            this.port = create.getPort();
        }
        this.proxyDetector = args.proxyDetector;
        long j = 0;
        if (!z) {
            String property = System.getProperty("networkaddress.cache.ttl");
            long j2 = 30;
            if (property != null) {
                try {
                    j2 = Long.parseLong(property);
                } catch (NumberFormatException unused) {
                    logger.logp(Level.WARNING, "io.grpc.internal.DnsNameResolver", "getNetworkAddressCacheTtlNanos", "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{"networkaddress.cache.ttl", property, 30L});
                }
            }
            if (j2 > 0) {
                j = TimeUnit.SECONDS.toNanos(j2);
            } else {
                j = j2;
            }
        }
        this.cacheTtlNanos = j;
        this.stopwatch = stopwatch;
        this.syncContext = args.syncContext;
        Executor executor = args.executor;
        this.executor = executor;
        this.usingExecutorResource = executor == null;
        this.serviceConfigParser = args.serviceConfigParser;
    }

    public static String getLocalHostname() {
        if (localHostname == null) {
            try {
                localHostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return localHostname;
    }

    private final void resolve() {
        if (!this.resolving && !this.shutdown) {
            if (this.resolved) {
                long j = this.cacheTtlNanos;
                if (j != 0 && (j <= 0 || this.stopwatch.elapsed(TimeUnit.NANOSECONDS) <= this.cacheTtlNanos)) {
                    return;
                }
            }
            this.resolving = true;
            this.executor.execute(new Resolve(this.listener));
        }
    }

    @Override // io.grpc.NameResolver
    public final String getServiceAuthority() {
        return this.authority;
    }

    @Override // io.grpc.NameResolver
    public final void refresh() {
        boolean z;
        if (this.listener != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "not started");
        resolve();
    }

    @Override // io.grpc.NameResolver
    public final void shutdown() {
        if (!this.shutdown) {
            this.shutdown = true;
            Executor executor = this.executor;
            if (executor != null && this.usingExecutorResource) {
                SharedResourceHolder.holder.releaseInternal$ar$ds(this.executorResource, executor);
                this.executor = null;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.concurrent.Executor, java.lang.Object] */
    @Override // io.grpc.NameResolver
    public final void start(NameResolver.Listener2 listener2) {
        boolean z;
        if (this.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "already started");
        if (this.usingExecutorResource) {
            this.executor = SharedResourceHolder.get(this.executorResource);
        }
        this.listener = listener2;
        resolve();
    }
}
