package io.grpc.internal;

import J.N;
import com.google.android.accessibility.talkback.actor.gemini.progress.ProgressTonePlayer;
import com.google.common.base.Stopwatch;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.chromium.base.JavaHandlerThread;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Http2Ping {
    private static final Logger log = Logger.getLogger(Http2Ping.class.getName());
    public Map callbacks = new LinkedHashMap();
    public boolean completed;
    public final long data;
    public Throwable failureCause;
    public long roundTripTimeNanos;
    public final Stopwatch stopwatch;

    /* compiled from: PG */
    /* renamed from: io.grpc.internal.Http2Ping$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Runnable {
        final /* synthetic */ Object Http2Ping$1$ar$val$callback$ar$class_merging$7a06e9c8_0;
        private final /* synthetic */ int switching_field;
        final /* synthetic */ long val$roundTripTimeNanos;

        public /* synthetic */ AnonymousClass1(long j, ArrayList arrayList, int i) {
            this.switching_field = i;
            this.val$roundTripTimeNanos = j;
            this.Http2Ping$1$ar$val$callback$ar$class_merging$7a06e9c8_0 = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkSoonToDisconnect(this.val$roundTripTimeNanos);
                            return;
                        } else {
                            N.Ml5G_GLY(this.val$roundTripTimeNanos, this.Http2Ping$1$ar$val$callback$ar$class_merging$7a06e9c8_0);
                            return;
                        }
                    }
                    ((JavaHandlerThread) this.Http2Ping$1$ar$val$callback$ar$class_merging$7a06e9c8_0).mThread.quit();
                    N.MYwg$x8E(this.val$roundTripTimeNanos);
                    return;
                }
                ((ProgressTonePlayer) this.Http2Ping$1$ar$val$callback$ar$class_merging$7a06e9c8_0).playTonesInternal(this.val$roundTripTimeNanos);
            }
        }

        public /* synthetic */ AnonymousClass1(ProgressTonePlayer progressTonePlayer, long j, int i) {
            this.switching_field = i;
            this.Http2Ping$1$ar$val$callback$ar$class_merging$7a06e9c8_0 = progressTonePlayer;
            this.val$roundTripTimeNanos = j;
        }

        public AnonymousClass1(Object obj, long j, int i) {
            this.switching_field = i;
            this.Http2Ping$1$ar$val$callback$ar$class_merging$7a06e9c8_0 = obj;
            this.val$roundTripTimeNanos = j;
        }
    }

    public Http2Ping(long j, Stopwatch stopwatch) {
        this.data = j;
        this.stopwatch = stopwatch;
    }

    public static void doExecute(Executor executor, Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Throwable th) {
            log.logp(Level.SEVERE, "io.grpc.internal.Http2Ping", "doExecute", "Failed to execute PingCallback", th);
        }
    }
}
