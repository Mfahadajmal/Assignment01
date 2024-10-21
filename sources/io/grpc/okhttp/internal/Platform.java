package io.grpc.okhttp.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Platform {
    public final Provider sslProvider;
    public static final Logger logger = Logger.getLogger(Platform.class.getName());
    private static final String[] ANDROID_SECURITY_PROVIDERS = {"com.google.android.gms.org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLProvider", "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider", "com.google.android.libraries.stitch.sslguard.SslGuardProvider"};
    public static final Platform PLATFORM = findPlatform();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Android extends Platform {
        private final OptionalMethod getAlpnSelectedProtocol;
        private final OptionalMethod setAlpnProtocols;
        private final OptionalMethod setHostname;
        private final OptionalMethod setUseSessionTickets;
        private final int tlsExtensionType$ar$edu;

        public Android(OptionalMethod optionalMethod, OptionalMethod optionalMethod2, OptionalMethod optionalMethod3, OptionalMethod optionalMethod4, Provider provider, int i) {
            super(provider);
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
            this.tlsExtensionType$ar$edu = i;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final void configureTlsExtensions(SSLSocket sSLSocket, String str, List list) {
            if (str != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException$ar$ds(sSLSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException$ar$ds(sSLSocket, str);
            }
            if (this.setAlpnProtocols.isSupported(sSLSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sSLSocket, concatLengthPrefixed(list));
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final String getSelectedProtocol(SSLSocket sSLSocket) {
            byte[] bArr;
            if (this.getAlpnSelectedProtocol.isSupported(sSLSocket) && (bArr = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sSLSocket, new Object[0])) != null) {
                return new String(bArr, Util.UTF_8);
            }
            return null;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final int getTlsExtensionType$ar$edu() {
            return this.tlsExtensionType$ar$edu;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class JdkAlpnPlatform extends Platform {
        private final Method getApplicationProtocol;
        private final Method setApplicationProtocols;

        public JdkAlpnPlatform(Provider provider, Method method, Method method2) {
            super(provider);
            this.setApplicationProtocols = method;
            this.getApplicationProtocol = method2;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final void configureTlsExtensions(SSLSocket sSLSocket, String str, List list) {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Protocol protocol = (Protocol) it.next();
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.protocol);
                }
            }
            try {
                this.setApplicationProtocols.invoke(sSLParameters, arrayList.toArray(new String[arrayList.size()]));
                sSLSocket.setSSLParameters(sSLParameters);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                return (String) this.getApplicationProtocol.invoke(sSLSocket, null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final int getTlsExtensionType$ar$edu() {
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class JdkWithJettyBootPlatform extends Platform {
        private final Class clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class serverProviderClass;

        public JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class cls, Class cls2, Provider provider) {
            super(provider);
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls;
            this.serverProviderClass = cls2;
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final void afterHandshake(SSLSocket sSLSocket) {
            try {
                this.removeMethod.invoke(null, sSLSocket);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e) {
                logger.logp(Level.FINE, "io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform", "afterHandshake", "Failed to remove SSLSocket from Jetty ALPN", (Throwable) e);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final void configureTlsExtensions(SSLSocket sSLSocket, String str, List list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = (Protocol) list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.protocol);
                }
            }
            try {
                this.putMethod.invoke(null, sSLSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(arrayList)));
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke(null, sSLSocket));
                int i = JettyNegoProvider.Platform$JettyNegoProvider$ar$NoOp;
                if (jettyNegoProvider.unsupported) {
                    return null;
                }
                if (jettyNegoProvider.selected == null) {
                    logger.logp(Level.INFO, "io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform", "getSelectedProtocol", "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                }
                return jettyNegoProvider.selected;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException unused2) {
                throw new AssertionError();
            }
        }

        @Override // io.grpc.okhttp.internal.Platform
        public final int getTlsExtensionType$ar$edu() {
            return 1;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class JettyNegoProvider implements InvocationHandler {
        public static final /* synthetic */ int Platform$JettyNegoProvider$ar$NoOp = 0;
        private final List protocols;
        public String selected;
        public boolean unsupported;

        public JettyNegoProvider(List list) {
            this.protocols = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.protocols;
            }
            if ((name.equals("selectProtocol") || name.equals("select")) && returnType == String.class && objArr.length == 1) {
                Object obj2 = objArr[0];
                if (obj2 instanceof List) {
                    List list = (List) obj2;
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.protocols.contains(list.get(i))) {
                            String str = (String) list.get(i);
                            this.selected = str;
                            return str;
                        }
                    }
                    String str2 = (String) this.protocols.get(0);
                    this.selected = str2;
                    return str2;
                }
            }
            if ((name.equals("protocolSelected") || name.equals("selected")) && objArr.length == 1) {
                this.selected = (String) objArr[0];
                return null;
            }
            return method.invoke(this, objArr);
        }
    }

    public Platform(Provider provider) {
        this.sslProvider = provider;
    }

    public static byte[] concatLengthPrefixed(List list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte$ar$ds(protocol.protocol.length());
                buffer.writeUtf8(protocol.protocol);
            }
        }
        return buffer.readByteArray(buffer.size);
    }

    private static Platform findPlatform() {
        Provider provider;
        int i;
        Provider[] providers = Security.getProviders();
        int length = providers.length;
        int i2 = 0;
        loop0: while (true) {
            if (i2 < length) {
                Provider provider2 = providers[i2];
                String[] strArr = ANDROID_SECURITY_PROVIDERS;
                int length2 = strArr.length;
                for (int i3 = 0; i3 < 5; i3++) {
                    String str = strArr[i3];
                    if (str.equals(provider2.getClass().getName())) {
                        logger.logp(Level.FINE, "io.grpc.okhttp.internal.Platform", "getAndroidSecurityProvider", "Found registered provider {0}", str);
                        provider = provider2;
                        break loop0;
                    }
                }
                i2++;
            } else {
                logger.logp(Level.WARNING, "io.grpc.okhttp.internal.Platform", "getAndroidSecurityProvider", "Unable to find Conscrypt");
                provider = null;
                break;
            }
        }
        if (provider != null) {
            OptionalMethod optionalMethod = new OptionalMethod((Object) null, "setUseSessionTickets", new Class[]{Boolean.TYPE});
            OptionalMethod optionalMethod2 = new OptionalMethod((Object) null, "setHostname", new Class[]{String.class});
            OptionalMethod optionalMethod3 = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            OptionalMethod optionalMethod4 = new OptionalMethod((Object) null, "setAlpnProtocols", new Class[]{byte[].class});
            try {
                Class<?> cls = Class.forName("android.net.TrafficStats");
                cls.getMethod("tagSocket", Socket.class);
                cls.getMethod("untagSocket", Socket.class);
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
            }
            if (!provider.getName().equals("GmsCore_OpenSSL") && !provider.getName().equals("Conscrypt") && !provider.getName().equals("Ssl_Guard")) {
                try {
                    Platform.class.getClassLoader().loadClass("android.net.Network");
                } catch (ClassNotFoundException e) {
                    logger.logp(Level.FINE, "io.grpc.okhttp.internal.Platform", "isAtLeastAndroid5", "Can't find class", (Throwable) e);
                    try {
                        Platform.class.getClassLoader().loadClass("android.app.ActivityOptions");
                        i = 2;
                    } catch (ClassNotFoundException e2) {
                        logger.logp(Level.FINE, "io.grpc.okhttp.internal.Platform", "isAtLeastAndroid41", "Can't find class", (Throwable) e2);
                        i = 3;
                    }
                }
            }
            i = 1;
            return new Android(optionalMethod, optionalMethod2, optionalMethod3, optionalMethod4, provider, i);
        }
        try {
            Provider provider3 = SSLContext.getDefault().getProvider();
            try {
                try {
                    SSLContext sSLContext = SSLContext.getInstance("TLS", provider3);
                    sSLContext.init(null, null, null);
                    SSLEngine.class.getMethod("getApplicationProtocol", null).invoke(sSLContext.createSSLEngine(), null);
                    return new JdkAlpnPlatform(provider3, SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", null));
                } catch (ClassNotFoundException | NoSuchMethodException unused2) {
                    return new Platform(provider3);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException unused3) {
                Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN");
                Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
                return new JdkWithJettyBootPlatform(cls2.getMethod("put", SSLSocket.class, cls3), cls2.getMethod("get", SSLSocket.class), cls2.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"), provider3);
            }
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public int getTlsExtensionType$ar$edu() {
        return 3;
    }

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List list) {
    }
}
