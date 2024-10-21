package io.grpc.okhttp;

import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.Util;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* compiled from: PG */
/* loaded from: classes.dex */
class OkHttpProtocolNegotiator {
    public static final OkHttpProtocolNegotiator NEGOTIATOR;
    protected final Platform platform;
    public static final Logger logger = Logger.getLogger(OkHttpProtocolNegotiator.class.getName());
    private static final Platform DEFAULT_PLATFORM = Platform.PLATFORM;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        private static final Method GET_APPLICATION_PROTOCOL;
        private static final Method GET_APPLICATION_PROTOCOLS;
        private static final Method SET_APPLICATION_PROTOCOLS;
        private static final Method SET_SERVER_NAMES;
        private static final Constructor SNI_HOST_NAME;
        private static final Method SSL_SOCKETS_IS_SUPPORTED_SOCKET;
        private static final Method SSL_SOCKETS_SET_USE_SESSION_TICKET;
        private static final OptionalMethod SET_USE_SESSION_TICKETS = new OptionalMethod((Object) null, "setUseSessionTickets", new Class[]{Boolean.TYPE});
        private static final OptionalMethod SET_HOSTNAME = new OptionalMethod((Object) null, "setHostname", new Class[]{String.class});
        private static final OptionalMethod GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod SET_ALPN_PROTOCOLS = new OptionalMethod((Object) null, "setAlpnProtocols", new Class[]{byte[].class});
        private static final OptionalMethod GET_NPN_SELECTED_PROTOCOL = new OptionalMethod(byte[].class, "getNpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod SET_NPN_PROTOCOLS = new OptionalMethod((Object) null, "setNpnProtocols", new Class[]{byte[].class});

        static {
            NoSuchMethodException e;
            Method method;
            Method method2;
            Method method3;
            ClassNotFoundException e2;
            Method method4;
            NoSuchMethodException noSuchMethodException;
            ClassNotFoundException classNotFoundException;
            Method method5;
            Method method6;
            Method method7;
            NoSuchMethodException noSuchMethodException2;
            Method method8;
            ClassNotFoundException classNotFoundException2;
            Constructor<?> constructor = null;
            try {
                method2 = SSLParameters.class.getMethod("setApplicationProtocols", String[].class);
                try {
                    method = SSLParameters.class.getMethod("getApplicationProtocols", null);
                } catch (ClassNotFoundException e3) {
                    e2 = e3;
                    method = null;
                    method3 = null;
                } catch (NoSuchMethodException e4) {
                    e = e4;
                    method = null;
                    method3 = null;
                }
                try {
                    method6 = SSLSocket.class.getMethod("getApplicationProtocol", null);
                    try {
                        Class<?> cls = Class.forName("android.net.ssl.SSLSockets");
                        method7 = cls.getMethod("isSupportedSocket", SSLSocket.class);
                        try {
                            method5 = cls.getMethod("setUseSessionTickets", SSLSocket.class, Boolean.TYPE);
                        } catch (ClassNotFoundException e5) {
                            classNotFoundException = e5;
                            method3 = method6;
                            method4 = method7;
                            OkHttpProtocolNegotiator.logger.logp(Level.FINER, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "<clinit>", "Failed to find Android 10.0+ APIs", (Throwable) classNotFoundException);
                            method5 = null;
                            method6 = method3;
                            method7 = method4;
                            SET_APPLICATION_PROTOCOLS = method2;
                            GET_APPLICATION_PROTOCOLS = method;
                            GET_APPLICATION_PROTOCOL = method6;
                            SSL_SOCKETS_IS_SUPPORTED_SOCKET = method7;
                            SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                            method8 = SSLParameters.class.getMethod("setServerNames", List.class);
                            try {
                                constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
                            } catch (ClassNotFoundException e6) {
                                classNotFoundException2 = e6;
                                OkHttpProtocolNegotiator.logger.logp(Level.FINER, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "<clinit>", "Failed to find Android 7.0+ APIs", (Throwable) classNotFoundException2);
                                SET_SERVER_NAMES = method8;
                                SNI_HOST_NAME = constructor;
                            } catch (NoSuchMethodException e7) {
                                noSuchMethodException2 = e7;
                                OkHttpProtocolNegotiator.logger.logp(Level.FINER, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "<clinit>", "Failed to find Android 7.0+ APIs", (Throwable) noSuchMethodException2);
                                SET_SERVER_NAMES = method8;
                                SNI_HOST_NAME = constructor;
                            }
                            SET_SERVER_NAMES = method8;
                            SNI_HOST_NAME = constructor;
                        } catch (NoSuchMethodException e8) {
                            noSuchMethodException = e8;
                            method3 = method6;
                            method4 = method7;
                            OkHttpProtocolNegotiator.logger.logp(Level.FINER, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "<clinit>", "Failed to find Android 10.0+ APIs", (Throwable) noSuchMethodException);
                            method5 = null;
                            method6 = method3;
                            method7 = method4;
                            SET_APPLICATION_PROTOCOLS = method2;
                            GET_APPLICATION_PROTOCOLS = method;
                            GET_APPLICATION_PROTOCOL = method6;
                            SSL_SOCKETS_IS_SUPPORTED_SOCKET = method7;
                            SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                            method8 = SSLParameters.class.getMethod("setServerNames", List.class);
                            constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
                            SET_SERVER_NAMES = method8;
                            SNI_HOST_NAME = constructor;
                        }
                    } catch (ClassNotFoundException e9) {
                        method4 = null;
                        classNotFoundException = e9;
                        method3 = method6;
                    } catch (NoSuchMethodException e10) {
                        method4 = null;
                        noSuchMethodException = e10;
                        method3 = method6;
                    }
                } catch (ClassNotFoundException e11) {
                    e2 = e11;
                    method3 = null;
                    method4 = method3;
                    classNotFoundException = e2;
                    OkHttpProtocolNegotiator.logger.logp(Level.FINER, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "<clinit>", "Failed to find Android 10.0+ APIs", (Throwable) classNotFoundException);
                    method5 = null;
                    method6 = method3;
                    method7 = method4;
                    SET_APPLICATION_PROTOCOLS = method2;
                    GET_APPLICATION_PROTOCOLS = method;
                    GET_APPLICATION_PROTOCOL = method6;
                    SSL_SOCKETS_IS_SUPPORTED_SOCKET = method7;
                    SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                    method8 = SSLParameters.class.getMethod("setServerNames", List.class);
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
                    SET_SERVER_NAMES = method8;
                    SNI_HOST_NAME = constructor;
                } catch (NoSuchMethodException e12) {
                    e = e12;
                    method3 = null;
                    method4 = method3;
                    noSuchMethodException = e;
                    OkHttpProtocolNegotiator.logger.logp(Level.FINER, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "<clinit>", "Failed to find Android 10.0+ APIs", (Throwable) noSuchMethodException);
                    method5 = null;
                    method6 = method3;
                    method7 = method4;
                    SET_APPLICATION_PROTOCOLS = method2;
                    GET_APPLICATION_PROTOCOLS = method;
                    GET_APPLICATION_PROTOCOL = method6;
                    SSL_SOCKETS_IS_SUPPORTED_SOCKET = method7;
                    SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
                    method8 = SSLParameters.class.getMethod("setServerNames", List.class);
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
                    SET_SERVER_NAMES = method8;
                    SNI_HOST_NAME = constructor;
                }
            } catch (ClassNotFoundException e13) {
                e2 = e13;
                method = null;
                method2 = null;
                method3 = null;
            } catch (NoSuchMethodException e14) {
                e = e14;
                method = null;
                method2 = null;
                method3 = null;
            }
            SET_APPLICATION_PROTOCOLS = method2;
            GET_APPLICATION_PROTOCOLS = method;
            GET_APPLICATION_PROTOCOL = method6;
            SSL_SOCKETS_IS_SUPPORTED_SOCKET = method7;
            SSL_SOCKETS_SET_USE_SESSION_TICKET = method5;
            try {
                method8 = SSLParameters.class.getMethod("setServerNames", List.class);
                constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
            } catch (ClassNotFoundException e15) {
                classNotFoundException2 = e15;
                method8 = null;
            } catch (NoSuchMethodException e16) {
                noSuchMethodException2 = e16;
                method8 = null;
            }
            SET_SERVER_NAMES = method8;
            SNI_HOST_NAME = constructor;
        }

        public AndroidNegotiator(Platform platform) {
            super(platform);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x00fc  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x010a  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0110  */
        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected final void configureTlsExtensions(javax.net.ssl.SSLSocket r10, java.lang.String r11, java.util.List r12) {
            /*
                Method dump skipped, instructions count: 301
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpProtocolNegotiator.AndroidNegotiator.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List):void");
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        public final String getSelectedProtocol(SSLSocket sSLSocket) {
            Method method = GET_APPLICATION_PROTOCOL;
            if (method != null) {
                try {
                    return (String) method.invoke(sSLSocket, null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    if (e2.getTargetException() instanceof UnsupportedOperationException) {
                        OkHttpProtocolNegotiator.logger.logp(Level.FINER, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "getSelectedProtocol", "Socket unsupported for getApplicationProtocol, will try old methods");
                    } else {
                        throw new RuntimeException(e2);
                    }
                }
            }
            if (this.platform.getTlsExtensionType$ar$edu() == 1) {
                try {
                    byte[] bArr = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                    if (bArr != null) {
                        return new String(bArr, Util.UTF_8);
                    }
                } catch (Exception e3) {
                    OkHttpProtocolNegotiator.logger.logp(Level.FINE, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "getSelectedProtocol", "Failed calling getAlpnSelectedProtocol()", (Throwable) e3);
                }
            }
            if (this.platform.getTlsExtensionType$ar$edu() != 3) {
                try {
                    byte[] bArr2 = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                    if (bArr2 != null) {
                        return new String(bArr2, Util.UTF_8);
                    }
                } catch (Exception e4) {
                    OkHttpProtocolNegotiator.logger.logp(Level.FINE, "io.grpc.okhttp.OkHttpProtocolNegotiator$AndroidNegotiator", "getSelectedProtocol", "Failed calling getNpnSelectedProtocol()", (Throwable) e4);
                }
            }
            return null;
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        public final String negotiate(SSLSocket sSLSocket, String str, List list) {
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            if (selectedProtocol == null) {
                return super.negotiate(sSLSocket, str, list);
            }
            return selectedProtocol;
        }
    }

    static {
        OkHttpProtocolNegotiator okHttpProtocolNegotiator;
        ClassLoader classLoader = OkHttpProtocolNegotiator.class.getClassLoader();
        try {
            classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e) {
            logger.logp(Level.FINE, "io.grpc.okhttp.OkHttpProtocolNegotiator", "createNegotiator", "Unable to find Conscrypt. Skipping", (Throwable) e);
            try {
                classLoader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e2) {
                logger.logp(Level.FINE, "io.grpc.okhttp.OkHttpProtocolNegotiator", "createNegotiator", "Unable to find any OpenSSLSocketImpl. Skipping", (Throwable) e2);
                okHttpProtocolNegotiator = new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
            }
        }
        okHttpProtocolNegotiator = new AndroidNegotiator(DEFAULT_PLATFORM);
        NEGOTIATOR = okHttpProtocolNegotiator;
    }

    public OkHttpProtocolNegotiator(Platform platform) {
        platform.getClass();
        this.platform = platform;
    }

    protected void configureTlsExtensions(SSLSocket sSLSocket, String str, List list) {
        this.platform.configureTlsExtensions(sSLSocket, str, list);
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return this.platform.getSelectedProtocol(sSLSocket);
    }

    public String negotiate(SSLSocket sSLSocket, String str, List list) {
        if (list != null) {
            configureTlsExtensions(sSLSocket, str, list);
        }
        try {
            sSLSocket.startHandshake();
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            if (selectedProtocol != null) {
                return selectedProtocol;
            }
            throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + String.valueOf(list));
        } finally {
            this.platform.afterHandshake(sSLSocket);
        }
    }
}
