package com.google.mlkit.common.model;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc$$ExternalSyntheticLambda0;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.InvalidRegistrarException;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.DelayedClientCall;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.OkHttpClientTransport;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.chromium.net.CronetEngine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RemoteModelManager {
    public final Object RemoteModelManager$ar$remoteModelManagerInstances;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RemoteModelManagerRegistration {
        public final Object RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider;
        public final Object RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;

        public RemoteModelManagerRegistration(NetworkCallerGrpc.ManagedChannelFactory managedChannelFactory) {
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider = managedChannelFactory;
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass = null;
        }

        public static ComponentRegistrar instantiate(String str) {
            try {
                Class<?> cls = Class.forName(str);
                if (ComponentRegistrar.class.isAssignableFrom(cls)) {
                    return (ComponentRegistrar) cls.getDeclaredConstructor(null).newInstance(null);
                }
                throw new InvalidRegistrarException(String.format("Class %s is not an instance of %s", str, "com.google.firebase.components.ComponentRegistrar"));
            } catch (ClassNotFoundException unused) {
                Log.w("ComponentDiscovery", String.format("Class %s is not an found.", str));
                return null;
            } catch (IllegalAccessException e) {
                throw new InvalidRegistrarException(String.format("Could not instantiate %s.", str), e);
            } catch (InstantiationException e2) {
                throw new InvalidRegistrarException(String.format("Could not instantiate %s.", str), e2);
            } catch (NoSuchMethodException e3) {
                throw new InvalidRegistrarException(String.format("Could not instantiate %s", str), e3);
            } catch (InvocationTargetException e4) {
                throw new InvalidRegistrarException(String.format("Could not instantiate %s", str), e4);
            }
        }

        /* JADX WARN: Type inference failed for: r5v0, types: [com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc$ManagedChannelFactory, java.lang.Object] */
        public final NetworkCaller createNetworkCaller(Context context, String str, String str2, String str3) {
            StatsStorage statsStorage = new StatsStorage(new JankObserverFactory((byte[]) null));
            ?? r5 = this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider;
            if (r5 == 0) {
                return new NetworkCallerGrpc(context, str, str2, str3, new NetworkCallerGrpc$$ExternalSyntheticLambda0(this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass, 0), statsStorage);
            }
            return new NetworkCallerGrpc(context, str, str2, str3, r5, statsStorage);
        }

        public final boolean isAnswerValid() {
            for (boolean z : (boolean[]) this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass) {
                if (z) {
                    return true;
                }
            }
            return false;
        }

        public RemoteModelManagerRegistration(Object obj, Object obj2) {
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass = obj;
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider = obj2;
        }

        public RemoteModelManagerRegistration(String str, boolean[] zArr) {
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider = str;
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass = zArr;
        }

        @Deprecated
        public RemoteModelManagerRegistration(CronetEngine cronetEngine) {
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass = cronetEngine;
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider = null;
        }

        public RemoteModelManagerRegistration(byte[] bArr) {
        }

        public RemoteModelManagerRegistration(Status status, Metadata metadata) {
            ContextDataProvider.checkState(!status.isOk(), "Error status must not be OK");
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass = status;
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider = metadata;
        }

        public RemoteModelManagerRegistration(byte[] bArr, byte[] bArr2) {
            this((byte[]) null);
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass = new Rect();
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider = new Rect();
        }

        public RemoteModelManagerRegistration() {
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass = new ArrayList();
            this.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider = new ArrayList();
        }
    }

    public RemoteModelManager(Supplier supplier) {
        this.RemoteModelManager$ar$remoteModelManagerInstances = supplier;
    }

    public final long addAndGet(long j) {
        return ((AtomicLong) this.RemoteModelManager$ar$remoteModelManagerInstances).addAndGet(j);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [io.grpc.internal.TimeProvider, java.lang.Object] */
    public final TransportTracer create() {
        return new TransportTracer(this.RemoteModelManager$ar$remoteModelManagerInstances);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [io.grpc.internal.ConnectionClientTransport, java.lang.Object] */
    public final void onPingTimeout() {
        this.RemoteModelManager$ar$remoteModelManagerInstances.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
    }

    public final void ping() {
        boolean z;
        long nextLong;
        Runnable anonymousClass1;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(this);
        Object obj = this.RemoteModelManager$ar$remoteModelManagerInstances;
        DirectExecutor directExecutor = DirectExecutor.INSTANCE;
        synchronized (((OkHttpClientTransport) obj).lock) {
            boolean z2 = true;
            if (((OkHttpClientTransport) obj).frameWriter != null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkState(z);
            if (((OkHttpClientTransport) obj).stopped) {
                Http2Ping.notifyFailed$ar$class_merging$ar$class_merging(resolutionResultListener, directExecutor, ((OkHttpClientTransport) obj).getPingFailure());
                return;
            }
            Http2Ping http2Ping = ((OkHttpClientTransport) obj).ping;
            if (http2Ping != null) {
                nextLong = 0;
                z2 = false;
            } else {
                nextLong = ((OkHttpClientTransport) obj).random.nextLong();
                Stopwatch stopwatch = (Stopwatch) ((OkHttpClientTransport) obj).stopwatchFactory.get();
                stopwatch.start$ar$ds$db96ddcc_0();
                Http2Ping http2Ping2 = new Http2Ping(nextLong, stopwatch);
                ((OkHttpClientTransport) obj).ping = http2Ping2;
                ((OkHttpClientTransport) obj).transportTracer.keepAlivesSent++;
                http2Ping = http2Ping2;
            }
            if (z2) {
                ((OkHttpClientTransport) obj).frameWriter.ping(false, (int) (nextLong >>> 32), (int) nextLong);
            }
            synchronized (http2Ping) {
                if (!http2Ping.completed) {
                    http2Ping.callbacks.put(resolutionResultListener, directExecutor);
                    return;
                }
                Throwable th = http2Ping.failureCause;
                if (th != null) {
                    anonymousClass1 = new DelayedClientCall.AnonymousClass4(resolutionResultListener, th, 12);
                } else {
                    anonymousClass1 = new Http2Ping.AnonymousClass1(resolutionResultListener, http2Ping.roundTripTimeNanos, 0);
                }
                Http2Ping.doExecute(directExecutor, anonymousClass1);
            }
        }
    }

    public RemoteModelManager(Object obj) {
        this.RemoteModelManager$ar$remoteModelManagerInstances = obj;
    }

    public RemoteModelManager(Status status, Metadata metadata) {
        status.getClass();
        this.RemoteModelManager$ar$remoteModelManagerInstances = status;
        metadata.getClass();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Map, java.lang.Object] */
    public RemoteModelManager(Set set) {
        this.RemoteModelManager$ar$remoteModelManagerInstances = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            RemoteModelManagerRegistration remoteModelManagerRegistration = (RemoteModelManagerRegistration) it.next();
            this.RemoteModelManager$ar$remoteModelManagerInstances.put(remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass, remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider);
        }
    }

    public RemoteModelManager(byte[] bArr) {
        this.RemoteModelManager$ar$remoteModelManagerInstances = new AtomicLong();
    }

    public RemoteModelManager() {
        this.RemoteModelManager$ar$remoteModelManagerInstances = new ArrayList(20);
    }
}
