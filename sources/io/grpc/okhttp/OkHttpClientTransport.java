package io.grpc.okhttp;

import androidx.preference.Preference;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.AsyncSink;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.okhttp.OkHttpClientStream;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Http2;
import io.grpc.okhttp.internal.framed.Settings;
import io.grpc.okhttp.internal.framed.Variant;
import io.grpc.util.MultiChildLoadBalancer;
import j$.util.DesugarCollections;
import java.net.InetSocketAddress;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.RealBufferedSink;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OkHttpClientTransport implements ConnectionClientTransport, ExceptionHandlingFrameWriter.TransportExceptionHandler, OutboundFlowController.Transport {
    private static final Map ERROR_CODE_TO_STATUS;
    public static final Logger log;
    public final InetSocketAddress address;
    public Attributes attributes;
    public ClientFrameHandler clientFrameHandler;
    public final ConnectionSpec connectionSpec;
    public int connectionUnacknowledgedBytesRead;
    public final String defaultAuthority;
    public boolean enableKeepAlive;
    public final Executor executor;
    public ExceptionHandlingFrameWriter frameWriter;
    private boolean goAwaySent;
    public Status goAwayStatus;
    private boolean hasStream;
    private final InUseStateAggregator inUseState;
    public final int initialWindowSize;
    public KeepAliveManager keepAliveManager;
    public long keepAliveTimeNanos;
    public long keepAliveTimeoutNanos;
    public ManagedClientTransport.Listener listener;
    public final Object lock;
    private final InternalLogId logId;
    public int maxConcurrentStreams;
    public final int maxInboundMetadataSize;
    private final int maxMessageSize;
    private int nextStreamId;
    public OutboundFlowController outboundFlow;
    public final Deque pendingStreams;
    public Http2Ping ping;
    final HttpConnectProxiedSocketAddress proxiedAddr;
    int proxySocketTimeout;
    public final Random random = new Random();
    private final ScheduledExecutorService scheduler;
    private final SerializingExecutor serializingExecutor;
    public final SocketFactory socketFactory;
    public SSLSocketFactory sslSocketFactory;
    public boolean stopped;
    public final Supplier stopwatchFactory;
    public final Map streams;
    public final Runnable tooManyPingsRunnable;
    public final TransportTracer transportTracer;
    public final String userAgent;
    public final Variant variant;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ClientFrameHandler implements Runnable {
        final Http2.Reader frameReader$ar$class_merging;
        private final MultiChildLoadBalancer.AcceptResolvedAddrRetVal logger$ar$class_merging$a8a646dc_0 = new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(Level.FINE, OkHttpClientTransport.class);
        boolean firstSettings = true;

        public ClientFrameHandler(Http2.Reader reader) {
            this.frameReader$ar$class_merging = reader;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:195:0x037b, code lost:
        
            throw io.grpc.okhttp.internal.framed.Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", java.lang.Integer.valueOf(r12));
         */
        /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0066. Please report as an issue. */
        /* JADX WARN: Failed to find 'out' block for switch in B:185:0x0361. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:18:0x073b A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0013 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:231:0x03f3 A[Catch: all -> 0x0433, TryCatch #13 {, blocks: (B:216:0x03a9, B:218:0x03af, B:219:0x03b7, B:221:0x03bd, B:223:0x03c7, B:225:0x03d7, B:229:0x03ef, B:231:0x03f3, B:232:0x0408, B:234:0x0411, B:235:0x0418, B:236:0x041d, B:241:0x03e2, B:242:0x03ed), top: B:215:0x03a9, outer: #12 }] */
        /* JADX WARN: Removed duplicated region for block: B:234:0x0411 A[Catch: all -> 0x0433, TryCatch #13 {, blocks: (B:216:0x03a9, B:218:0x03af, B:219:0x03b7, B:221:0x03bd, B:223:0x03c7, B:225:0x03d7, B:229:0x03ef, B:231:0x03f3, B:232:0x0408, B:234:0x0411, B:235:0x0418, B:236:0x041d, B:241:0x03e2, B:242:0x03ed), top: B:215:0x03a9, outer: #12 }] */
        /* JADX WARN: Removed duplicated region for block: B:267:0x0483 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:336:0x05d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00be A[Catch: all -> 0x0771, TRY_ENTER, TRY_LEAVE, TryCatch #12 {all -> 0x0771, blocks: (B:4:0x0014, B:6:0x0016, B:7:0x001d, B:9:0x0028, B:11:0x004e, B:13:0x0066, B:14:0x0069, B:15:0x0731, B:16:0x0735, B:19:0x073b, B:24:0x006f, B:26:0x007b, B:27:0x0084, B:35:0x00be, B:47:0x00d0, B:50:0x00d1, B:51:0x00d9, B:53:0x00da, B:54:0x00e8, B:58:0x00ef, B:60:0x0103, B:62:0x0107, B:63:0x010e, B:65:0x0117, B:67:0x013b, B:68:0x0142, B:73:0x0154, B:75:0x015b, B:76:0x017f, B:78:0x018b, B:79:0x0193, B:80:0x017d, B:83:0x019b, B:84:0x01a9, B:86:0x01aa, B:87:0x01b2, B:89:0x01b3, B:90:0x01c1, B:94:0x01c8, B:96:0x01e8, B:97:0x01ec, B:106:0x01fa, B:107:0x01fb, B:108:0x01ff, B:117:0x024a, B:125:0x0264, B:126:0x026c, B:128:0x0272, B:134:0x0290, B:142:0x0294, B:144:0x0295, B:145:0x029d, B:147:0x029e, B:148:0x02ac, B:150:0x02af, B:152:0x02b3, B:153:0x02bd, B:155:0x02da, B:156:0x0315, B:157:0x0319, B:166:0x0329, B:169:0x032a, B:170:0x0332, B:172:0x0335, B:177:0x033d, B:178:0x0345, B:179:0x0346, B:181:0x034a, B:184:0x0353, B:185:0x0361, B:188:0x039c, B:194:0x036d, B:195:0x037b, B:199:0x0399, B:202:0x037f, B:203:0x0387, B:209:0x038f, B:210:0x0397, B:213:0x039f, B:214:0x03a8, B:237:0x041e, B:239:0x0424, B:248:0x0436, B:250:0x0437, B:251:0x0445, B:253:0x0446, B:254:0x044e, B:257:0x0453, B:259:0x045f, B:261:0x0474, B:265:0x047e, B:266:0x0482, B:281:0x04b3, B:284:0x04b4, B:285:0x04c2, B:287:0x04c3, B:288:0x04cb, B:290:0x04cc, B:291:0x04da, B:295:0x04e0, B:297:0x04e5, B:298:0x04ed, B:300:0x04ee, B:301:0x04fc, B:304:0x0500, B:306:0x0506, B:307:0x0510, B:309:0x0514, B:310:0x0519, B:312:0x052a, B:315:0x055c, B:317:0x056a, B:320:0x0576, B:322:0x057c, B:324:0x0596, B:326:0x05a1, B:329:0x05ae, B:333:0x05c5, B:334:0x05d4, B:335:0x05d8, B:345:0x063f, B:358:0x0651, B:392:0x0652, B:393:0x065b, B:394:0x065c, B:396:0x0662, B:398:0x0666, B:399:0x0670, B:402:0x0680, B:403:0x068d, B:408:0x069d, B:410:0x06a5, B:411:0x06a9, B:416:0x06b5, B:417:0x06ee, B:419:0x0700, B:420:0x0702, B:424:0x070f, B:429:0x0717, B:430:0x0718, B:435:0x06bc, B:436:0x06bd, B:437:0x06cb, B:438:0x06e5, B:446:0x0722, B:451:0x0726, B:454:0x0727, B:455:0x0730, B:457:0x0740, B:458:0x074f, B:460:0x0750, B:461:0x0754, B:466:0x075c, B:467:0x0764, B:472:0x0770, B:405:0x068e, B:406:0x069a, B:159:0x031a, B:160:0x0323, B:463:0x0755, B:464:0x0759, B:119:0x024b, B:121:0x024f, B:123:0x0252, B:124:0x0263, B:40:0x0087, B:41:0x008f, B:29:0x0092, B:31:0x00a2, B:33:0x00bb, B:36:0x00b1, B:337:0x05d9, B:339:0x05e9, B:341:0x05f1, B:343:0x063c, B:347:0x05fd, B:349:0x0607, B:350:0x0616, B:352:0x0627, B:353:0x0630, B:268:0x0483, B:270:0x0493, B:272:0x049f, B:273:0x04a4, B:274:0x04a2, B:275:0x04ad, B:110:0x0200, B:112:0x0206, B:114:0x020c, B:115:0x0247, B:135:0x0210, B:137:0x0239, B:216:0x03a9, B:218:0x03af, B:219:0x03b7, B:221:0x03bd, B:223:0x03c7, B:225:0x03d7, B:229:0x03ef, B:231:0x03f3, B:232:0x0408, B:234:0x0411, B:235:0x0418, B:236:0x041d, B:241:0x03e2, B:242:0x03ed, B:422:0x0703, B:423:0x070e, B:99:0x01ed, B:100:0x01f4, B:413:0x06aa, B:414:0x06b3, B:440:0x06e6, B:441:0x06ed), top: B:3:0x0014, inners: #0, #1, #4, #5, #6, #7, #8, #10, #11, #13, #14, #16, #17, #20 }] */
        /* JADX WARN: Removed duplicated region for block: B:75:0x015b A[Catch: all -> 0x0771, TryCatch #12 {all -> 0x0771, blocks: (B:4:0x0014, B:6:0x0016, B:7:0x001d, B:9:0x0028, B:11:0x004e, B:13:0x0066, B:14:0x0069, B:15:0x0731, B:16:0x0735, B:19:0x073b, B:24:0x006f, B:26:0x007b, B:27:0x0084, B:35:0x00be, B:47:0x00d0, B:50:0x00d1, B:51:0x00d9, B:53:0x00da, B:54:0x00e8, B:58:0x00ef, B:60:0x0103, B:62:0x0107, B:63:0x010e, B:65:0x0117, B:67:0x013b, B:68:0x0142, B:73:0x0154, B:75:0x015b, B:76:0x017f, B:78:0x018b, B:79:0x0193, B:80:0x017d, B:83:0x019b, B:84:0x01a9, B:86:0x01aa, B:87:0x01b2, B:89:0x01b3, B:90:0x01c1, B:94:0x01c8, B:96:0x01e8, B:97:0x01ec, B:106:0x01fa, B:107:0x01fb, B:108:0x01ff, B:117:0x024a, B:125:0x0264, B:126:0x026c, B:128:0x0272, B:134:0x0290, B:142:0x0294, B:144:0x0295, B:145:0x029d, B:147:0x029e, B:148:0x02ac, B:150:0x02af, B:152:0x02b3, B:153:0x02bd, B:155:0x02da, B:156:0x0315, B:157:0x0319, B:166:0x0329, B:169:0x032a, B:170:0x0332, B:172:0x0335, B:177:0x033d, B:178:0x0345, B:179:0x0346, B:181:0x034a, B:184:0x0353, B:185:0x0361, B:188:0x039c, B:194:0x036d, B:195:0x037b, B:199:0x0399, B:202:0x037f, B:203:0x0387, B:209:0x038f, B:210:0x0397, B:213:0x039f, B:214:0x03a8, B:237:0x041e, B:239:0x0424, B:248:0x0436, B:250:0x0437, B:251:0x0445, B:253:0x0446, B:254:0x044e, B:257:0x0453, B:259:0x045f, B:261:0x0474, B:265:0x047e, B:266:0x0482, B:281:0x04b3, B:284:0x04b4, B:285:0x04c2, B:287:0x04c3, B:288:0x04cb, B:290:0x04cc, B:291:0x04da, B:295:0x04e0, B:297:0x04e5, B:298:0x04ed, B:300:0x04ee, B:301:0x04fc, B:304:0x0500, B:306:0x0506, B:307:0x0510, B:309:0x0514, B:310:0x0519, B:312:0x052a, B:315:0x055c, B:317:0x056a, B:320:0x0576, B:322:0x057c, B:324:0x0596, B:326:0x05a1, B:329:0x05ae, B:333:0x05c5, B:334:0x05d4, B:335:0x05d8, B:345:0x063f, B:358:0x0651, B:392:0x0652, B:393:0x065b, B:394:0x065c, B:396:0x0662, B:398:0x0666, B:399:0x0670, B:402:0x0680, B:403:0x068d, B:408:0x069d, B:410:0x06a5, B:411:0x06a9, B:416:0x06b5, B:417:0x06ee, B:419:0x0700, B:420:0x0702, B:424:0x070f, B:429:0x0717, B:430:0x0718, B:435:0x06bc, B:436:0x06bd, B:437:0x06cb, B:438:0x06e5, B:446:0x0722, B:451:0x0726, B:454:0x0727, B:455:0x0730, B:457:0x0740, B:458:0x074f, B:460:0x0750, B:461:0x0754, B:466:0x075c, B:467:0x0764, B:472:0x0770, B:405:0x068e, B:406:0x069a, B:159:0x031a, B:160:0x0323, B:463:0x0755, B:464:0x0759, B:119:0x024b, B:121:0x024f, B:123:0x0252, B:124:0x0263, B:40:0x0087, B:41:0x008f, B:29:0x0092, B:31:0x00a2, B:33:0x00bb, B:36:0x00b1, B:337:0x05d9, B:339:0x05e9, B:341:0x05f1, B:343:0x063c, B:347:0x05fd, B:349:0x0607, B:350:0x0616, B:352:0x0627, B:353:0x0630, B:268:0x0483, B:270:0x0493, B:272:0x049f, B:273:0x04a4, B:274:0x04a2, B:275:0x04ad, B:110:0x0200, B:112:0x0206, B:114:0x020c, B:115:0x0247, B:135:0x0210, B:137:0x0239, B:216:0x03a9, B:218:0x03af, B:219:0x03b7, B:221:0x03bd, B:223:0x03c7, B:225:0x03d7, B:229:0x03ef, B:231:0x03f3, B:232:0x0408, B:234:0x0411, B:235:0x0418, B:236:0x041d, B:241:0x03e2, B:242:0x03ed, B:422:0x0703, B:423:0x070e, B:99:0x01ed, B:100:0x01f4, B:413:0x06aa, B:414:0x06b3, B:440:0x06e6, B:441:0x06ed), top: B:3:0x0014, inners: #0, #1, #4, #5, #6, #7, #8, #10, #11, #13, #14, #16, #17, #20 }] */
        /* JADX WARN: Removed duplicated region for block: B:78:0x018b A[Catch: all -> 0x0771, TryCatch #12 {all -> 0x0771, blocks: (B:4:0x0014, B:6:0x0016, B:7:0x001d, B:9:0x0028, B:11:0x004e, B:13:0x0066, B:14:0x0069, B:15:0x0731, B:16:0x0735, B:19:0x073b, B:24:0x006f, B:26:0x007b, B:27:0x0084, B:35:0x00be, B:47:0x00d0, B:50:0x00d1, B:51:0x00d9, B:53:0x00da, B:54:0x00e8, B:58:0x00ef, B:60:0x0103, B:62:0x0107, B:63:0x010e, B:65:0x0117, B:67:0x013b, B:68:0x0142, B:73:0x0154, B:75:0x015b, B:76:0x017f, B:78:0x018b, B:79:0x0193, B:80:0x017d, B:83:0x019b, B:84:0x01a9, B:86:0x01aa, B:87:0x01b2, B:89:0x01b3, B:90:0x01c1, B:94:0x01c8, B:96:0x01e8, B:97:0x01ec, B:106:0x01fa, B:107:0x01fb, B:108:0x01ff, B:117:0x024a, B:125:0x0264, B:126:0x026c, B:128:0x0272, B:134:0x0290, B:142:0x0294, B:144:0x0295, B:145:0x029d, B:147:0x029e, B:148:0x02ac, B:150:0x02af, B:152:0x02b3, B:153:0x02bd, B:155:0x02da, B:156:0x0315, B:157:0x0319, B:166:0x0329, B:169:0x032a, B:170:0x0332, B:172:0x0335, B:177:0x033d, B:178:0x0345, B:179:0x0346, B:181:0x034a, B:184:0x0353, B:185:0x0361, B:188:0x039c, B:194:0x036d, B:195:0x037b, B:199:0x0399, B:202:0x037f, B:203:0x0387, B:209:0x038f, B:210:0x0397, B:213:0x039f, B:214:0x03a8, B:237:0x041e, B:239:0x0424, B:248:0x0436, B:250:0x0437, B:251:0x0445, B:253:0x0446, B:254:0x044e, B:257:0x0453, B:259:0x045f, B:261:0x0474, B:265:0x047e, B:266:0x0482, B:281:0x04b3, B:284:0x04b4, B:285:0x04c2, B:287:0x04c3, B:288:0x04cb, B:290:0x04cc, B:291:0x04da, B:295:0x04e0, B:297:0x04e5, B:298:0x04ed, B:300:0x04ee, B:301:0x04fc, B:304:0x0500, B:306:0x0506, B:307:0x0510, B:309:0x0514, B:310:0x0519, B:312:0x052a, B:315:0x055c, B:317:0x056a, B:320:0x0576, B:322:0x057c, B:324:0x0596, B:326:0x05a1, B:329:0x05ae, B:333:0x05c5, B:334:0x05d4, B:335:0x05d8, B:345:0x063f, B:358:0x0651, B:392:0x0652, B:393:0x065b, B:394:0x065c, B:396:0x0662, B:398:0x0666, B:399:0x0670, B:402:0x0680, B:403:0x068d, B:408:0x069d, B:410:0x06a5, B:411:0x06a9, B:416:0x06b5, B:417:0x06ee, B:419:0x0700, B:420:0x0702, B:424:0x070f, B:429:0x0717, B:430:0x0718, B:435:0x06bc, B:436:0x06bd, B:437:0x06cb, B:438:0x06e5, B:446:0x0722, B:451:0x0726, B:454:0x0727, B:455:0x0730, B:457:0x0740, B:458:0x074f, B:460:0x0750, B:461:0x0754, B:466:0x075c, B:467:0x0764, B:472:0x0770, B:405:0x068e, B:406:0x069a, B:159:0x031a, B:160:0x0323, B:463:0x0755, B:464:0x0759, B:119:0x024b, B:121:0x024f, B:123:0x0252, B:124:0x0263, B:40:0x0087, B:41:0x008f, B:29:0x0092, B:31:0x00a2, B:33:0x00bb, B:36:0x00b1, B:337:0x05d9, B:339:0x05e9, B:341:0x05f1, B:343:0x063c, B:347:0x05fd, B:349:0x0607, B:350:0x0616, B:352:0x0627, B:353:0x0630, B:268:0x0483, B:270:0x0493, B:272:0x049f, B:273:0x04a4, B:274:0x04a2, B:275:0x04ad, B:110:0x0200, B:112:0x0206, B:114:0x020c, B:115:0x0247, B:135:0x0210, B:137:0x0239, B:216:0x03a9, B:218:0x03af, B:219:0x03b7, B:221:0x03bd, B:223:0x03c7, B:225:0x03d7, B:229:0x03ef, B:231:0x03f3, B:232:0x0408, B:234:0x0411, B:235:0x0418, B:236:0x041d, B:241:0x03e2, B:242:0x03ed, B:422:0x0703, B:423:0x070e, B:99:0x01ed, B:100:0x01f4, B:413:0x06aa, B:414:0x06b3, B:440:0x06e6, B:441:0x06ed), top: B:3:0x0014, inners: #0, #1, #4, #5, #6, #7, #8, #10, #11, #13, #14, #16, #17, #20 }] */
        /* JADX WARN: Removed duplicated region for block: B:80:0x017d A[Catch: all -> 0x0771, TryCatch #12 {all -> 0x0771, blocks: (B:4:0x0014, B:6:0x0016, B:7:0x001d, B:9:0x0028, B:11:0x004e, B:13:0x0066, B:14:0x0069, B:15:0x0731, B:16:0x0735, B:19:0x073b, B:24:0x006f, B:26:0x007b, B:27:0x0084, B:35:0x00be, B:47:0x00d0, B:50:0x00d1, B:51:0x00d9, B:53:0x00da, B:54:0x00e8, B:58:0x00ef, B:60:0x0103, B:62:0x0107, B:63:0x010e, B:65:0x0117, B:67:0x013b, B:68:0x0142, B:73:0x0154, B:75:0x015b, B:76:0x017f, B:78:0x018b, B:79:0x0193, B:80:0x017d, B:83:0x019b, B:84:0x01a9, B:86:0x01aa, B:87:0x01b2, B:89:0x01b3, B:90:0x01c1, B:94:0x01c8, B:96:0x01e8, B:97:0x01ec, B:106:0x01fa, B:107:0x01fb, B:108:0x01ff, B:117:0x024a, B:125:0x0264, B:126:0x026c, B:128:0x0272, B:134:0x0290, B:142:0x0294, B:144:0x0295, B:145:0x029d, B:147:0x029e, B:148:0x02ac, B:150:0x02af, B:152:0x02b3, B:153:0x02bd, B:155:0x02da, B:156:0x0315, B:157:0x0319, B:166:0x0329, B:169:0x032a, B:170:0x0332, B:172:0x0335, B:177:0x033d, B:178:0x0345, B:179:0x0346, B:181:0x034a, B:184:0x0353, B:185:0x0361, B:188:0x039c, B:194:0x036d, B:195:0x037b, B:199:0x0399, B:202:0x037f, B:203:0x0387, B:209:0x038f, B:210:0x0397, B:213:0x039f, B:214:0x03a8, B:237:0x041e, B:239:0x0424, B:248:0x0436, B:250:0x0437, B:251:0x0445, B:253:0x0446, B:254:0x044e, B:257:0x0453, B:259:0x045f, B:261:0x0474, B:265:0x047e, B:266:0x0482, B:281:0x04b3, B:284:0x04b4, B:285:0x04c2, B:287:0x04c3, B:288:0x04cb, B:290:0x04cc, B:291:0x04da, B:295:0x04e0, B:297:0x04e5, B:298:0x04ed, B:300:0x04ee, B:301:0x04fc, B:304:0x0500, B:306:0x0506, B:307:0x0510, B:309:0x0514, B:310:0x0519, B:312:0x052a, B:315:0x055c, B:317:0x056a, B:320:0x0576, B:322:0x057c, B:324:0x0596, B:326:0x05a1, B:329:0x05ae, B:333:0x05c5, B:334:0x05d4, B:335:0x05d8, B:345:0x063f, B:358:0x0651, B:392:0x0652, B:393:0x065b, B:394:0x065c, B:396:0x0662, B:398:0x0666, B:399:0x0670, B:402:0x0680, B:403:0x068d, B:408:0x069d, B:410:0x06a5, B:411:0x06a9, B:416:0x06b5, B:417:0x06ee, B:419:0x0700, B:420:0x0702, B:424:0x070f, B:429:0x0717, B:430:0x0718, B:435:0x06bc, B:436:0x06bd, B:437:0x06cb, B:438:0x06e5, B:446:0x0722, B:451:0x0726, B:454:0x0727, B:455:0x0730, B:457:0x0740, B:458:0x074f, B:460:0x0750, B:461:0x0754, B:466:0x075c, B:467:0x0764, B:472:0x0770, B:405:0x068e, B:406:0x069a, B:159:0x031a, B:160:0x0323, B:463:0x0755, B:464:0x0759, B:119:0x024b, B:121:0x024f, B:123:0x0252, B:124:0x0263, B:40:0x0087, B:41:0x008f, B:29:0x0092, B:31:0x00a2, B:33:0x00bb, B:36:0x00b1, B:337:0x05d9, B:339:0x05e9, B:341:0x05f1, B:343:0x063c, B:347:0x05fd, B:349:0x0607, B:350:0x0616, B:352:0x0627, B:353:0x0630, B:268:0x0483, B:270:0x0493, B:272:0x049f, B:273:0x04a4, B:274:0x04a2, B:275:0x04ad, B:110:0x0200, B:112:0x0206, B:114:0x020c, B:115:0x0247, B:135:0x0210, B:137:0x0239, B:216:0x03a9, B:218:0x03af, B:219:0x03b7, B:221:0x03bd, B:223:0x03c7, B:225:0x03d7, B:229:0x03ef, B:231:0x03f3, B:232:0x0408, B:234:0x0411, B:235:0x0418, B:236:0x041d, B:241:0x03e2, B:242:0x03ed, B:422:0x0703, B:423:0x070e, B:99:0x01ed, B:100:0x01f4, B:413:0x06aa, B:414:0x06b3, B:440:0x06e6, B:441:0x06ed), top: B:3:0x0014, inners: #0, #1, #4, #5, #6, #7, #8, #10, #11, #13, #14, #16, #17, #20 }] */
        /* JADX WARN: Type inference failed for: r6v53, types: [io.grpc.okhttp.OutboundFlowController$Transport, java.lang.Object] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 2070
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.ClientFrameHandler.run():void");
        }
    }

    static {
        EnumMap enumMap = new EnumMap(ErrorCode.class);
        enumMap.put((EnumMap) ErrorCode.NO_ERROR, (ErrorCode) Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        enumMap.put((EnumMap) ErrorCode.PROTOCOL_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Protocol error"));
        enumMap.put((EnumMap) ErrorCode.INTERNAL_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Internal error"));
        enumMap.put((EnumMap) ErrorCode.FLOW_CONTROL_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Flow control error"));
        enumMap.put((EnumMap) ErrorCode.STREAM_CLOSED, (ErrorCode) Status.INTERNAL.withDescription("Stream closed"));
        enumMap.put((EnumMap) ErrorCode.FRAME_TOO_LARGE, (ErrorCode) Status.INTERNAL.withDescription("Frame too large"));
        enumMap.put((EnumMap) ErrorCode.REFUSED_STREAM, (ErrorCode) Status.UNAVAILABLE.withDescription("Refused stream"));
        enumMap.put((EnumMap) ErrorCode.CANCEL, (ErrorCode) Status.CANCELLED.withDescription("Cancelled"));
        enumMap.put((EnumMap) ErrorCode.COMPRESSION_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Compression error"));
        enumMap.put((EnumMap) ErrorCode.CONNECT_ERROR, (ErrorCode) Status.INTERNAL.withDescription("Connect error"));
        enumMap.put((EnumMap) ErrorCode.ENHANCE_YOUR_CALM, (ErrorCode) Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        enumMap.put((EnumMap) ErrorCode.INADEQUATE_SECURITY, (ErrorCode) Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        ERROR_CODE_TO_STATUS = DesugarCollections.unmodifiableMap(enumMap);
        log = Logger.getLogger(OkHttpClientTransport.class.getName());
    }

    public OkHttpClientTransport(OkHttpChannelBuilder.OkHttpTransportFactory okHttpTransportFactory, InetSocketAddress inetSocketAddress, String str, String str2, Attributes attributes, Supplier supplier, Variant variant, HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress, Runnable runnable) {
        Object obj = new Object();
        this.lock = obj;
        this.streams = new HashMap();
        this.maxConcurrentStreams = 0;
        this.pendingStreams = new LinkedList();
        this.inUseState = new InUseStateAggregator() { // from class: io.grpc.okhttp.OkHttpClientTransport.1
            @Override // io.grpc.internal.InUseStateAggregator
            protected final void handleInUse() {
                OkHttpClientTransport.this.listener.transportInUse(true);
            }

            @Override // io.grpc.internal.InUseStateAggregator
            protected final void handleNotInUse() {
                OkHttpClientTransport.this.listener.transportInUse(false);
            }
        };
        this.proxySocketTimeout = 30000;
        inetSocketAddress.getClass();
        this.address = inetSocketAddress;
        this.defaultAuthority = str;
        this.maxMessageSize = okHttpTransportFactory.maxMessageSize;
        this.initialWindowSize = okHttpTransportFactory.flowControlWindow;
        Executor executor = okHttpTransportFactory.executor;
        executor.getClass();
        this.executor = executor;
        this.serializingExecutor = new SerializingExecutor(okHttpTransportFactory.executor);
        ScheduledExecutorService scheduledExecutorService = okHttpTransportFactory.scheduledExecutorService;
        scheduledExecutorService.getClass();
        this.scheduler = scheduledExecutorService;
        this.nextStreamId = 3;
        this.socketFactory = SocketFactory.getDefault();
        this.sslSocketFactory = okHttpTransportFactory.sslSocketFactory;
        ConnectionSpec connectionSpec = okHttpTransportFactory.connectionSpec;
        connectionSpec.getClass();
        this.connectionSpec = connectionSpec;
        supplier.getClass();
        this.stopwatchFactory = supplier;
        this.variant = variant;
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", str2);
        this.proxiedAddr = httpConnectProxiedSocketAddress;
        this.tooManyPingsRunnable = runnable;
        this.maxInboundMetadataSize = okHttpTransportFactory.maxInboundMetadataSize;
        this.transportTracer = okHttpTransportFactory.transportTracerFactory$ar$class_merging$ar$class_merging.create();
        this.logId = InternalLogId.allocate(getClass(), inetSocketAddress.toString());
        Attributes attributes2 = Attributes.EMPTY;
        Attributes.Builder builder = new Attributes.Builder(Attributes.EMPTY);
        builder.set$ar$ds$d0d6fadb_0(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, attributes);
        this.attributes = builder.build();
        synchronized (obj) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String readUtf8LineStrictUnbuffered(okio.Source r16) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.readUtf8LineStrictUnbuffered(okio.Source):java.lang.String");
    }

    private final void stopIfNecessary() {
        if (this.goAwayStatus != null && this.streams.isEmpty() && this.pendingStreams.isEmpty() && !this.stopped) {
            this.stopped = true;
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onTransportTermination();
            }
            Http2Ping http2Ping = this.ping;
            if (http2Ping != null) {
                Throwable pingFailure = getPingFailure();
                synchronized (http2Ping) {
                    if (!http2Ping.completed) {
                        http2Ping.completed = true;
                        http2Ping.failureCause = pingFailure;
                        Map map = http2Ping.callbacks;
                        http2Ping.callbacks = null;
                        for (Map.Entry entry : map.entrySet()) {
                            Http2Ping.doExecute((Executor) entry.getValue(), new DelayedClientCall.AnonymousClass4((RetryingNameResolver.ResolutionResultListener) entry.getKey(), pingFailure, 12));
                        }
                    }
                }
                this.ping = null;
            }
            if (!this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway$ar$ds(ErrorCode.NO_ERROR, new byte[0]);
            }
            this.frameWriter.close();
        }
    }

    static Status toGrpcStatus(ErrorCode errorCode) {
        Status status = (Status) ERROR_CODE_TO_STATUS.get(errorCode);
        if (status != null) {
            return status;
        }
        return Status.UNKNOWN.withDescription("Unknown http2 error code: " + errorCode.httpCode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void finishStream(int i, Status status, ClientStreamListener.RpcProgress rpcProgress, boolean z, ErrorCode errorCode, Metadata metadata) {
        synchronized (this.lock) {
            OkHttpClientStream okHttpClientStream = (OkHttpClientStream) this.streams.remove(Integer.valueOf(i));
            if (okHttpClientStream != null) {
                if (errorCode != null) {
                    this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                }
                if (status != null) {
                    OkHttpClientStream.TransportState transportState = okHttpClientStream.state;
                    if (metadata == null) {
                        metadata = new Metadata();
                    }
                    transportState.transportReportStatus(status, rpcProgress, z, metadata);
                }
                if (!startPendingStreams()) {
                    stopIfNecessary();
                }
                maybeClearInUse(okHttpClientStream);
            }
        }
    }

    @Override // io.grpc.okhttp.OutboundFlowController.Transport
    public final OutboundFlowController.StreamState[] getActiveStreams() {
        OutboundFlowController.StreamState[] streamStateArr;
        synchronized (this.lock) {
            streamStateArr = new OutboundFlowController.StreamState[this.streams.size()];
            Iterator it = this.streams.values().iterator();
            int i = 0;
            while (it.hasNext()) {
                streamStateArr[i] = ((OkHttpClientStream) it.next()).state.getOutboundFlowState();
                i++;
            }
        }
        return streamStateArr;
    }

    @Override // io.grpc.internal.ConnectionClientTransport
    public final Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.grpc.InternalWithLogId
    public final InternalLogId getLogId() {
        return this.logId;
    }

    public final Throwable getPingFailure() {
        synchronized (this.lock) {
            Status status = this.goAwayStatus;
            if (status != null) {
                return new StatusException(status);
            }
            return new StatusException(Status.UNAVAILABLE.withDescription("Connection closed"));
        }
    }

    final boolean mayHaveCreatedStream(int i) {
        boolean z;
        synchronized (this.lock) {
            z = false;
            if (i < this.nextStreamId && (i & 1) == 1) {
                z = true;
            }
        }
        return z;
    }

    public final void maybeClearInUse(OkHttpClientStream okHttpClientStream) {
        if (this.hasStream && this.pendingStreams.isEmpty() && this.streams.isEmpty()) {
            this.hasStream = false;
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onTransportIdle();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse) {
            this.inUseState.updateObjectInUse(okHttpClientStream, false);
        }
    }

    @Override // io.grpc.internal.ClientTransport
    public final /* bridge */ /* synthetic */ ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        StatsTraceContext newClientContext = StatsTraceContext.newClientContext(clientStreamTracerArr, this.attributes, metadata);
        synchronized (this.lock) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                return new OkHttpClientStream(methodDescriptor, metadata, this.frameWriter, this, this.outboundFlow, this.lock, this.maxMessageSize, this.initialWindowSize, this.defaultAuthority, this.userAgent, newClientContext, this.transportTracer, callOptions);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public final void onError(ErrorCode errorCode, String str) {
        startGoAway(0, errorCode, toGrpcStatus(errorCode).augmentDescription(str));
    }

    @Override // io.grpc.okhttp.ExceptionHandlingFrameWriter.TransportExceptionHandler
    public final void onException(Throwable th) {
        startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withCause(th));
    }

    public final void setInUse(OkHttpClientStream okHttpClientStream) {
        if (!this.hasStream) {
            this.hasStream = true;
            KeepAliveManager keepAliveManager = this.keepAliveManager;
            if (keepAliveManager != null) {
                keepAliveManager.onTransportActive();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse) {
            this.inUseState.updateObjectInUse(okHttpClientStream, true);
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdown(Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus != null) {
                return;
            }
            this.goAwayStatus = status;
            this.listener.transportShutdown(status);
            stopIfNecessary();
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdownNow(Status status) {
        shutdown(status);
        synchronized (this.lock) {
            Iterator it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                it.remove();
                ((OkHttpClientStream) entry.getValue()).state.transportReportStatus(status, false, new Metadata());
                maybeClearInUse((OkHttpClientStream) entry.getValue());
            }
            for (OkHttpClientStream okHttpClientStream : this.pendingStreams) {
                okHttpClientStream.state.transportReportStatus(status, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
                maybeClearInUse(okHttpClientStream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final Runnable start(ManagedClientTransport.Listener listener) {
        this.listener = listener;
        if (this.enableKeepAlive) {
            KeepAliveManager keepAliveManager = new KeepAliveManager(new RemoteModelManager(this), this.scheduler, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos);
            this.keepAliveManager = keepAliveManager;
            keepAliveManager.onTransportStarted();
        }
        final AsyncSink asyncSink = new AsyncSink(this.serializingExecutor, this);
        AsyncSink.LimitControlFramesWriter limitControlFramesWriter = new AsyncSink.LimitControlFramesWriter(this.variant.newWriter$ar$ds(new RealBufferedSink(asyncSink)));
        synchronized (this.lock) {
            ExceptionHandlingFrameWriter exceptionHandlingFrameWriter = new ExceptionHandlingFrameWriter(this, limitControlFramesWriter);
            this.frameWriter = exceptionHandlingFrameWriter;
            this.outboundFlow = new OutboundFlowController(this, exceptionHandlingFrameWriter);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.serializingExecutor.execute(new Runnable(this) { // from class: io.grpc.okhttp.OkHttpClientTransport.3
            final /* synthetic */ OkHttpClientTransport this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Code restructure failed: missing block: B:122:0x00b1, code lost:
            
                r3 = new okio.Buffer();
                r3.writeUtf8$ar$ds(r15, r9);
             */
            /* JADX WARN: Code restructure failed: missing block: B:123:0x00b9, code lost:
            
                if (r9 >= r8) goto L471;
             */
            /* JADX WARN: Code restructure failed: missing block: B:124:0x00bb, code lost:
            
                r23 = r4;
             */
            /* JADX WARN: Code restructure failed: missing block: B:126:0x00bd, code lost:
            
                r4 = r15.codePointAt(r9);
             */
            /* JADX WARN: Code restructure failed: missing block: B:127:0x00c1, code lost:
            
                if (r4 != r7) goto L48;
             */
            /* JADX WARN: Code restructure failed: missing block: B:128:0x00c3, code lost:
            
                r4 = r9 + 2;
             */
            /* JADX WARN: Code restructure failed: missing block: B:129:0x00c5, code lost:
            
                if (r4 >= r8) goto L47;
             */
            /* JADX WARN: Code restructure failed: missing block: B:132:0x00c9, code lost:
            
                r7 = io.grpc.okhttp.internal.proxy.HttpUrl.decodeHexDigit(r15.charAt(r9 + 1));
                r4 = io.grpc.okhttp.internal.proxy.HttpUrl.decodeHexDigit(r15.charAt(r4));
                r24 = r9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:133:0x00de, code lost:
            
                if (r7 == (-1)) goto L38;
             */
            /* JADX WARN: Code restructure failed: missing block: B:134:0x00e0, code lost:
            
                if (r4 == (-1)) goto L38;
             */
            /* JADX WARN: Code restructure failed: missing block: B:135:0x00e2, code lost:
            
                r3.writeByte$ar$ds((r7 << 4) + r4);
                r26 = r10;
                r27 = r12;
                r28 = r14;
                r9 = r4;
                r4 = 37;
             */
            /* JADX WARN: Code restructure failed: missing block: B:137:0x01d7, code lost:
            
                r9 = r9 + java.lang.Character.charCount(r4);
                r1 = r37;
                r4 = r23;
                r10 = r26;
                r12 = r27;
                r14 = r28;
                r7 = 37;
             */
            /* JADX WARN: Code restructure failed: missing block: B:138:0x0462, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:139:0x0463, code lost:
            
                r1 = r0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:140:0x0466, code lost:
            
                r10 = r26;
             */
            /* JADX WARN: Code restructure failed: missing block: B:143:0x099b, code lost:
            
                io.grpc.internal.GrpcUtil.closeQuietly(r8);
             */
            /* JADX WARN: Code restructure failed: missing block: B:146:0x00f4, code lost:
            
                r4 = 37;
             */
            /* JADX WARN: Code restructure failed: missing block: B:148:0x010e, code lost:
            
                if (r4 >= 128) goto L53;
             */
            /* JADX WARN: Code restructure failed: missing block: B:149:0x0110, code lost:
            
                r3.writeByte$ar$ds(r4);
             */
            /* JADX WARN: Code restructure failed: missing block: B:150:0x0113, code lost:
            
                r26 = r10;
                r27 = r12;
                r28 = r14;
             */
            /* JADX WARN: Code restructure failed: missing block: B:151:0x01d5, code lost:
            
                r9 = r24;
             */
            /* JADX WARN: Code restructure failed: missing block: B:153:0x011d, code lost:
            
                if (r4 >= 2048) goto L59;
             */
            /* JADX WARN: Code restructure failed: missing block: B:155:0x0120, code lost:
            
                r7 = r3.writableSegment$third_party_java_src_okio_okio_jvm(2);
                r9 = r7.data;
                r1 = r7.limit;
             */
            /* JADX WARN: Code restructure failed: missing block: B:156:0x0128, code lost:
            
                r26 = r10;
             */
            /* JADX WARN: Code restructure failed: missing block: B:157:0x012f, code lost:
            
                r9[r1] = (byte) ((r4 >> 6) | 192);
                r27 = r12;
                r28 = r14;
                r9[r1 + 1] = (byte) ((r4 & 63) | com.google.android.accessibility.braille.brltty.BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                r7.limit = r1 + 2;
                r3.size += 2;
             */
            /* JADX WARN: Code restructure failed: missing block: B:158:0x014d, code lost:
            
                r26 = r10;
                r27 = r12;
                r28 = r14;
             */
            /* JADX WARN: Code restructure failed: missing block: B:159:0x0158, code lost:
            
                if (r4 < 55296) goto L64;
             */
            /* JADX WARN: Code restructure failed: missing block: B:161:0x015d, code lost:
            
                if (r4 >= 57344) goto L64;
             */
            /* JADX WARN: Code restructure failed: missing block: B:162:0x015f, code lost:
            
                r3.writeByte$ar$ds(63);
             */
            /* JADX WARN: Code restructure failed: missing block: B:164:0x0165, code lost:
            
                if (r4 >= 65536) goto L67;
             */
            /* JADX WARN: Code restructure failed: missing block: B:165:0x0167, code lost:
            
                r9 = r3.writableSegment$third_party_java_src_okio_okio_jvm(3);
                r1 = r9.data;
                r10 = r9.limit;
                r1[r10] = (byte) ((r4 >> 12) | 224);
                r1[r10 + 1] = (byte) ((63 & (r4 >> 6)) | com.google.android.accessibility.braille.brltty.BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                r1[r10 + 2] = (byte) ((r4 & 63) | com.google.android.accessibility.braille.brltty.BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                r9.limit = r10 + 3;
                r3.size += 3;
             */
            /* JADX WARN: Code restructure failed: missing block: B:167:0x0198, code lost:
            
                if (r4 > 1114111) goto L470;
             */
            /* JADX WARN: Code restructure failed: missing block: B:168:0x019a, code lost:
            
                r9 = r3.writableSegment$third_party_java_src_okio_okio_jvm(4);
                r1 = r9.data;
                r10 = r9.limit;
                r1[r10] = (byte) ((r4 >> 18) | 240);
                r1[r10 + 1] = (byte) (((r4 >> 12) & 63) | com.google.android.accessibility.braille.brltty.BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                r1[r10 + 2] = (byte) (((r4 >> 6) & 63) | com.google.android.accessibility.braille.brltty.BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                r1[r10 + 3] = (byte) (128 | (r4 & 63));
                r9.limit = r10 + 4;
                r3.size += 4;
             */
            /* JADX WARN: Code restructure failed: missing block: B:170:0x01ea, code lost:
            
                r2 = okio.internal.ByteString.HEX_DIGIT_CHARS[r4 >> 28];
                r3 = okio.internal.ByteString.HEX_DIGIT_CHARS;
             */
            /* JADX WARN: Code restructure failed: missing block: B:173:0x021e, code lost:
            
                r11 = new char[]{r2, r3[(r4 >> 24) & 15], r3[(r4 >> 20) & 15], r3[(r4 >> 16) & 15], r3[(r4 >> 12) & 15], r3[(r4 >> 8) & 15], r3[(r4 >> 4) & 15], r3[r4 & 15]};
             */
            /* JADX WARN: Code restructure failed: missing block: B:174:0x0237, code lost:
            
                r2 = 0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:176:0x023a, code lost:
            
                if (r2 >= 8) goto L473;
             */
            /* JADX WARN: Code restructure failed: missing block: B:178:0x0240, code lost:
            
                if (r11[r2] != '0') goto L472;
             */
            /* JADX WARN: Code restructure failed: missing block: B:179:0x0242, code lost:
            
                r2 = r2 + 1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:181:0x0245, code lost:
            
                if (r2 < 0) goto L88;
             */
            /* JADX WARN: Code restructure failed: missing block: B:183:0x0249, code lost:
            
                if (r2 > 8) goto L86;
             */
            /* JADX WARN: Code restructure failed: missing block: B:185:0x025b, code lost:
            
                throw new java.lang.IllegalArgumentException("Unexpected code point: 0x".concat(new java.lang.String(r11, r2, 8 - r2)));
             */
            /* JADX WARN: Code restructure failed: missing block: B:187:0x026b, code lost:
            
                throw new java.lang.IllegalArgumentException(_COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(8, r2, "startIndex: ", " > endIndex: "));
             */
            /* JADX WARN: Code restructure failed: missing block: B:189:0x0294, code lost:
            
                throw new java.lang.IndexOutOfBoundsException("startIndex: " + r2 + ", endIndex: 8, size: 8");
             */
            /* JADX WARN: Code restructure failed: missing block: B:192:0x02a1, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:193:0x02a2, code lost:
            
                r1 = r0;
                r3 = r37;
             */
            /* JADX WARN: Code restructure failed: missing block: B:195:0x02a7, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:196:0x02a8, code lost:
            
                r1 = r0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:197:0x029b, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:198:0x029c, code lost:
            
                r1 = r0;
                r3 = r37;
             */
            /* JADX WARN: Code restructure failed: missing block: B:200:0x0295, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:201:0x0296, code lost:
            
                r1 = r0;
                r3 = r37;
             */
            /* JADX WARN: Code restructure failed: missing block: B:206:0x00ff, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:207:0x0100, code lost:
            
                r3 = r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:209:0x0103, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:212:0x00fb, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:213:0x00fc, code lost:
            
                r3 = r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:215:0x00f7, code lost:
            
                r0 = th;
             */
            /* JADX WARN: Code restructure failed: missing block: B:216:0x00f8, code lost:
            
                r3 = r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:218:0x0107, code lost:
            
                r24 = r9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:219:0x010a, code lost:
            
                r24 = r9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:221:0x02ad, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:226:0x02af, code lost:
            
                r23 = r4;
                r26 = r10;
                r27 = r12;
                r28 = r14;
                r1 = r3.readUtf8();
             */
            /* JADX WARN: Code restructure failed: missing block: B:332:0x0978, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:333:0x0979, code lost:
            
                r10 = r26;
             */
            /* JADX WARN: Code restructure failed: missing block: B:457:0x02d4, code lost:
            
                r23 = r4;
                r26 = r10;
                r27 = r12;
                r28 = r14;
             */
            /* JADX WARN: Code restructure failed: missing block: B:459:0x02dd, code lost:
            
                r1 = r15.substring(0, r8);
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:143:0x099b A[Catch: all -> 0x09d9, Exception -> 0x09db, StatusException -> 0x09dd, TRY_ENTER, TryCatch #29 {StatusException -> 0x09dd, Exception -> 0x09db, all -> 0x09d9, blocks: (B:10:0x06a0, B:12:0x06a6, B:14:0x06b2, B:15:0x06b9, B:17:0x06c8, B:18:0x06d3, B:20:0x06ea, B:21:0x06fa, B:24:0x0713, B:25:0x071f, B:28:0x0725, B:29:0x0732, B:31:0x0740, B:32:0x0743, B:34:0x0749, B:35:0x074d, B:37:0x075d, B:38:0x0786, B:40:0x07b3, B:42:0x07bb, B:43:0x07c8, B:45:0x07d2, B:46:0x0805, B:76:0x07d7, B:77:0x07e6, B:79:0x0760, B:81:0x076a, B:82:0x076d, B:84:0x0777, B:85:0x077a, B:87:0x0784, B:88:0x07e7, B:89:0x07f2, B:91:0x0729, B:92:0x07f3, B:93:0x07fa, B:94:0x0717, B:95:0x07fb, B:96:0x0802, B:98:0x06cd, B:99:0x06b7, B:143:0x099b, B:144:0x099e, B:145:0x09af, B:318:0x0884, B:320:0x0889, B:322:0x08ae, B:323:0x08b1, B:324:0x08dc, B:330:0x0896, B:290:0x08f5, B:291:0x0908, B:335:0x08e1, B:336:0x08f4, B:347:0x0909, B:348:0x091c, B:359:0x0923, B:360:0x092e, B:361:0x092f, B:362:0x093a, B:363:0x093b, B:364:0x0946, B:365:0x0947, B:366:0x0956, B:367:0x0957, B:368:0x0968, B:464:0x097e, B:465:0x0988, B:472:0x09b0, B:473:0x09d8), top: B:7:0x0020 }] */
            /* JADX WARN: Type inference failed for: r3v5 */
            /* JADX WARN: Type inference failed for: r3v6 */
            /* JADX WARN: Type inference failed for: r3v7 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void run() {
                /*
                    Method dump skipped, instructions count: 2597
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.AnonymousClass3.run():void");
            }
        });
        try {
            synchronized (this.lock) {
                this.frameWriter.connectionPreface();
                Settings settings = new Settings();
                settings.set$ar$ds$b5988668_0(7, this.initialWindowSize);
                this.frameWriter.settings(settings);
            }
            countDownLatch.countDown();
            this.serializingExecutor.execute(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 8));
            return null;
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    public final void startGoAway(int i, ErrorCode errorCode, Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
            }
            if (errorCode != null && !this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway$ar$ds(errorCode, new byte[0]);
            }
            Iterator it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (((Integer) entry.getKey()).intValue() > i) {
                    it.remove();
                    ((OkHttpClientStream) entry.getValue()).state.transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, false, new Metadata());
                    maybeClearInUse((OkHttpClientStream) entry.getValue());
                }
            }
            for (OkHttpClientStream okHttpClientStream : this.pendingStreams) {
                okHttpClientStream.state.transportReportStatus(status, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
                maybeClearInUse(okHttpClientStream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    public final boolean startPendingStreams() {
        boolean z = false;
        while (!this.pendingStreams.isEmpty() && this.streams.size() < this.maxConcurrentStreams) {
            startStream((OkHttpClientStream) this.pendingStreams.poll());
            z = true;
        }
        return z;
    }

    public final void startStream(OkHttpClientStream okHttpClientStream) {
        boolean z;
        boolean z2 = true;
        if (okHttpClientStream.state.id == -1) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "StreamId already assigned");
        this.streams.put(Integer.valueOf(this.nextStreamId), okHttpClientStream);
        setInUse(okHttpClientStream);
        OkHttpClientStream.TransportState transportState = okHttpClientStream.state;
        int i = this.nextStreamId;
        if (transportState.id != -1) {
            z2 = false;
        }
        ContextDataProvider.checkState(z2, "the stream has been started with id %s", i);
        transportState.id = i;
        OutboundFlowController outboundFlowController = transportState.outboundFlow;
        transportState.outboundFlowState = new OutboundFlowController.StreamState(i, outboundFlowController.initialWindowSize, transportState);
        OkHttpClientStream.this.state.onStreamAllocated();
        if (transportState.canStart) {
            transportState.frameWriter.synStream$ar$ds(OkHttpClientStream.this.useGet, transportState.id, transportState.requestHeaders);
            OkHttpClientStream.this.statsTraceCtx.clientOutboundHeaders();
            transportState.requestHeaders = null;
            Buffer buffer = transportState.pendingData;
            if (buffer.size > 0) {
                transportState.outboundFlow.data(transportState.pendingDataHasEndOfStream, transportState.outboundFlowState, buffer, transportState.flushPendingData);
            }
            transportState.canStart = false;
        }
        if ((okHttpClientStream.getType() != MethodDescriptor.MethodType.UNARY && okHttpClientStream.getType() != MethodDescriptor.MethodType.SERVER_STREAMING) || okHttpClientStream.useGet) {
            this.frameWriter.flush();
        }
        int i2 = this.nextStreamId;
        if (i2 >= 2147483645) {
            this.nextStreamId = Preference.DEFAULT_ORDER;
            startGoAway(Preference.DEFAULT_ORDER, ErrorCode.NO_ERROR, Status.UNAVAILABLE.withDescription("Stream ids exhausted"));
        } else {
            this.nextStreamId = i2 + 2;
        }
    }

    public final String toString() {
        MoreObjects$ToStringHelper add = ContextDataProvider.toStringHelper(this).add("logId", this.logId.id);
        add.addHolder$ar$ds("address", this.address);
        return add.toString();
    }
}
