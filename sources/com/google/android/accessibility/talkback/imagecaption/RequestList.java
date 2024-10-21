package com.google.android.accessibility.talkback.imagecaption;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import j$.time.Duration;
import j$.time.Instant;
import java.util.ArrayDeque;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RequestList {
    private final int capacity;
    private final Handler handler;
    private Instant lastRequestExecutionTime;
    private final Duration minIntervalTime;
    public final WindowTrackerFactory requests$ar$class_merging$ar$class_merging;

    public RequestList() {
        this(10, Duration.ZERO);
    }

    public final void addRequest(Request request) {
        WindowTrackerFactory windowTrackerFactory = this.requests$ar$class_merging$ar$class_merging;
        synchronized (windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider) {
            ((ArrayDeque) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).add(request);
        }
        if (this.requests$ar$class_merging$ar$class_merging.size() > 1) {
            LogUtils.v("RequestsForCaption", "addRequest() waiting... %d %s", Integer.valueOf(this.requests$ar$class_merging$ar$class_merging.size() - 1), request.getClass().getSimpleName());
        } else {
            performNextRequest(false);
        }
    }

    public final void clear() {
        while (!this.requests$ar$class_merging$ar$class_merging.isEmpty()) {
        }
    }

    public final void performNextRequest() {
        performNextRequest(true);
    }

    public RequestList(int i, Duration duration) {
        this.requests$ar$class_merging$ar$class_merging = new WindowTrackerFactory((byte[]) null);
        this.handler = new Handler(Looper.myLooper()) { // from class: com.google.android.accessibility.talkback.imagecaption.RequestList.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1) {
                    LogUtils.v("RequestsForCaption", "Retry to perform request", new Object[0]);
                    RequestList.this.performNextRequest(false);
                }
            }
        };
        this.lastRequestExecutionTime = Instant.EPOCH;
        this.capacity = i;
        this.minIntervalTime = duration;
    }

    public final void performNextRequest(boolean z) {
        if (this.requests$ar$class_merging$ar$class_merging.isEmpty()) {
            return;
        }
        if (z) {
            Instant instant = ((Request) this.requests$ar$class_merging$ar$class_merging.removeFirst()).endTimestamp;
            if (instant != null) {
                this.lastRequestExecutionTime = instant;
            }
            if (this.requests$ar$class_merging$ar$class_merging.isEmpty()) {
                return;
            }
        }
        while (this.requests$ar$class_merging$ar$class_merging.size() > this.capacity) {
            LogUtils.v("RequestsForCaption", "cancel %s size=%d ", ((Request) this.requests$ar$class_merging$ar$class_merging.removeFirst()).getClass().getSimpleName(), Integer.valueOf(this.requests$ar$class_merging$ar$class_merging.size()));
        }
        Duration between = Duration.between(this.lastRequestExecutionTime, Instant.now());
        long millis = this.minIntervalTime.minus(between).toMillis();
        if (millis > 0) {
            LogUtils.v("RequestsForCaption", "waiting... %d ms", Long.valueOf(millis));
            Message message = new Message();
            message.what = 1;
            Request request = (Request) this.requests$ar$class_merging$ar$class_merging.getFirst();
            if (this.handler.sendMessageDelayed(message, millis)) {
                request.onPending(true, between);
                return;
            } else {
                LogUtils.e("RequestsForCaption", "Fail to send message to the handler.", new Object[0]);
                request.onPending(false, between);
                return;
            }
        }
        Request request2 = (Request) this.requests$ar$class_merging$ar$class_merging.getFirst();
        this.lastRequestExecutionTime = Instant.now();
        request2.perform();
        LogUtils.v("RequestsForCaption", "perform %s", request2.getClass().getSimpleName());
    }
}
