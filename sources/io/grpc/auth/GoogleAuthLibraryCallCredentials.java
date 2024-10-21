package io.grpc.auth;

import com.google.auth.Credentials;
import com.google.mlkit.logging.schema.OnDeviceFaceDetectionLogEvent;
import io.grpc.CallCredentials$MetadataApplier;
import io.grpc.InternalMayRequireSpecificExecutor;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleAuthLibraryCallCredentials extends OnDeviceFaceDetectionLogEvent implements InternalMayRequireSpecificExecutor {
    private static final Class APP_ENGINE_CREDENTIALS_CLASS;
    private static final Class GOOGLE_CREDENTIALS_CLASS;
    private static final WorkQueue jwtHelper$ar$class_merging;
    public static final Logger log = Logger.getLogger(GoogleAuthLibraryCallCredentials.class.getName());
    public final Credentials creds;
    public Metadata lastHeaders;
    public Map lastMetadata;
    public final boolean requirePrivacy;
    private final boolean requiresSpecificExecutor;

    /* compiled from: PG */
    /* renamed from: io.grpc.auth.GoogleAuthLibraryCallCredentials$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 {
        public final /* synthetic */ GoogleAuthLibraryCallCredentials this$0;
        public final /* synthetic */ CallCredentials$MetadataApplier val$applier;

        public AnonymousClass1(GoogleAuthLibraryCallCredentials googleAuthLibraryCallCredentials, CallCredentials$MetadataApplier callCredentials$MetadataApplier) {
            this.val$applier = callCredentials$MetadataApplier;
            this.this$0 = googleAuthLibraryCallCredentials;
        }
    }

    static {
        WorkQueue workQueue;
        Class cls;
        ClassLoader classLoader = GoogleAuthLibraryCallCredentials.class.getClassLoader();
        Class<?> cls2 = null;
        try {
            try {
                workQueue = new WorkQueue(Class.forName("com.google.auth.oauth2.ServiceAccountCredentials", false, classLoader), classLoader);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                log.logp(Level.WARNING, "io.grpc.auth.GoogleAuthLibraryCallCredentials", "createJwtHelperOrNull", "Failed to create JWT helper. This is unexpected", e);
                workQueue = null;
                jwtHelper$ar$class_merging = workQueue;
                cls = Class.forName("com.google.auth.oauth2.GoogleCredentials").asSubclass(Credentials.class);
                GOOGLE_CREDENTIALS_CLASS = cls;
                cls2 = Class.forName("com.google.auth.appengine.AppEngineCredentials");
                APP_ENGINE_CREDENTIALS_CLASS = cls2;
            }
        } catch (ClassNotFoundException unused) {
        }
        jwtHelper$ar$class_merging = workQueue;
        try {
            cls = Class.forName("com.google.auth.oauth2.GoogleCredentials").asSubclass(Credentials.class);
        } catch (ClassNotFoundException e2) {
            log.logp(Level.FINE, "io.grpc.auth.GoogleAuthLibraryCallCredentials", "loadGoogleCredentialsClass", "Failed to load GoogleCredentials", (Throwable) e2);
            cls = null;
        }
        GOOGLE_CREDENTIALS_CLASS = cls;
        try {
            cls2 = Class.forName("com.google.auth.appengine.AppEngineCredentials");
        } catch (ClassNotFoundException e3) {
            log.logp(Level.FINE, "io.grpc.auth.GoogleAuthLibraryCallCredentials", "loadAppEngineCredentials", "AppEngineCredentials not available in classloader", (Throwable) e3);
        }
        APP_ENGINE_CREDENTIALS_CLASS = cls2;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0099  */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public GoogleAuthLibraryCallCredentials(com.google.auth.Credentials r11) {
        /*
            r10 = this;
            kotlinx.coroutines.scheduling.WorkQueue r0 = io.grpc.auth.GoogleAuthLibraryCallCredentials.jwtHelper$ar$class_merging
            r10.<init>()
            java.lang.Class r1 = io.grpc.auth.GoogleAuthLibraryCallCredentials.GOOGLE_CREDENTIALS_CLASS
            r2 = 0
            if (r1 == 0) goto Lf
            boolean r1 = r1.isInstance(r11)
            goto L10
        Lf:
            r1 = r2
        L10:
            if (r0 == 0) goto L8e
            java.lang.Object r3 = r0.WorkQueue$ar$buffer
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r3 = r3.isInstance(r11)
            if (r3 != 0) goto L1e
            goto L8e
        L1e:
            java.lang.Object r3 = r0.WorkQueue$ar$buffer     // Catch: java.lang.reflect.InvocationTargetException -> L79 java.lang.IllegalAccessException -> L7c
            java.lang.Class r3 = (java.lang.Class) r3     // Catch: java.lang.reflect.InvocationTargetException -> L79 java.lang.IllegalAccessException -> L7c
            java.lang.Object r3 = r3.cast(r11)     // Catch: java.lang.reflect.InvocationTargetException -> L79 java.lang.IllegalAccessException -> L7c
            com.google.auth.Credentials r3 = (com.google.auth.Credentials) r3     // Catch: java.lang.reflect.InvocationTargetException -> L79 java.lang.IllegalAccessException -> L7c
            java.lang.Object r11 = r0.WorkQueue$ar$consumerIndex     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.reflect.Method r11 = (java.lang.reflect.Method) r11     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            r4 = 0
            java.lang.Object r11 = r11.invoke(r3, r4)     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.util.Collection r11 = (java.util.Collection) r11     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            int r11 = r11.size()     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            if (r11 != 0) goto L75
            java.lang.Object r11 = r0.WorkQueue$ar$producerIndex     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.reflect.Method r11 = (java.lang.reflect.Method) r11     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.Object r11 = r11.invoke(r4, r4)     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.Object r5 = r0.WorkQueue$ar$lastScheduledTask     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.util.Iterator r5 = r5.iterator()     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
        L47:
            boolean r6 = r5.hasNext()     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            if (r6 == 0) goto L6a
            java.lang.Object r6 = r5.next()     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            io.grpc.util.MultiChildLoadBalancer$AcceptResolvedAddrRetVal r6 = (io.grpc.util.MultiChildLoadBalancer.AcceptResolvedAddrRetVal) r6     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.Object r7 = r6.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.Object r6 = r6.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.reflect.Method r6 = (java.lang.reflect.Method) r6     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.Object r6 = r6.invoke(r3, r4)     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            r8[r2] = r6     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            r7.invoke(r11, r8)     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            goto L47
        L68:
            r11 = move-exception
            goto L7f
        L6a:
            java.lang.Object r0 = r0.WorkQueue$ar$blockingTasksInBuffer     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            java.lang.Object r11 = r0.invoke(r11, r4)     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            com.google.auth.Credentials r11 = (com.google.auth.Credentials) r11     // Catch: java.lang.IllegalAccessException -> L68 java.lang.reflect.InvocationTargetException -> L77
            goto L8e
        L75:
            r11 = r3
            goto L8e
        L77:
            r11 = move-exception
            goto L7f
        L79:
            r0 = move-exception
            r9 = r0
            goto L81
        L7c:
            r0 = move-exception
            r3 = r11
            r11 = r0
        L7f:
            r9 = r11
            r11 = r3
        L81:
            java.util.logging.Logger r4 = io.grpc.auth.GoogleAuthLibraryCallCredentials.log
            java.util.logging.Level r5 = java.util.logging.Level.WARNING
            java.lang.String r7 = "tryServiceAccountToJwt"
            java.lang.String r8 = "Failed converting service account credential to JWT. This is unexpected"
            java.lang.String r6 = "io.grpc.auth.GoogleAuthLibraryCallCredentials$JwtHelper"
            r4.logp(r5, r6, r7, r8, r9)
        L8e:
            r10.requirePrivacy = r1
            r10.creds = r11
            java.lang.Class r0 = io.grpc.auth.GoogleAuthLibraryCallCredentials.APP_ENGINE_CREDENTIALS_CLASS
            if (r0 != 0) goto L99
            r10.requiresSpecificExecutor = r2
            return
        L99:
            boolean r11 = r0.isInstance(r11)
            r10.requiresSpecificExecutor = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.auth.GoogleAuthLibraryCallCredentials.<init>(com.google.auth.Credentials):void");
    }

    private static URI removePort(URI uri) {
        try {
            return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
        } catch (URISyntaxException e) {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Unable to construct service URI after removing port").withCause(e));
        }
    }

    public static URI serviceUri(String str, MethodDescriptor methodDescriptor) {
        try {
            URI uri = new URI("https", str, "/".concat(String.valueOf(methodDescriptor.serviceName)), null, null);
            if (uri.getPort() == 443) {
                return removePort(uri);
            }
            return uri;
        } catch (URISyntaxException e) {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Unable to construct service URI for auth").withCause(e));
        }
    }

    @Override // io.grpc.InternalMayRequireSpecificExecutor
    public final boolean isSpecificExecutorRequired() {
        return this.requiresSpecificExecutor;
    }
}
