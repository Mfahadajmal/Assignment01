package com.google.android.gms.clearcut.internal;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pair;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;
import com.google.common.math.IntMath;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogErrorQueue {
    private static LogErrorQueue instance;
    public final Object LogErrorQueue$ar$errorMap;
    public int overflowErrorCount;

    public LogErrorQueue(byte[] bArr) {
        this.LogErrorQueue$ar$errorMap = new Object[256];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized LogErrorQueue getInstance() {
        LogErrorQueue logErrorQueue;
        synchronized (LogErrorQueue.class) {
            if (instance == null) {
                instance = new LogErrorQueue();
            }
            logErrorQueue = instance;
        }
        return logErrorQueue;
    }

    public final Object acquire() {
        int i = this.overflowErrorCount;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = (Object[]) this.LogErrorQueue$ar$errorMap;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.overflowErrorCount = i2;
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.Map, java.lang.Object] */
    public final synchronized void add(LogErrorParcelable logErrorParcelable) {
        Pair pair = new Pair(logErrorParcelable.logSourceName, Integer.valueOf(logErrorParcelable.clearcutStatusCode));
        LogErrorParcelable logErrorParcelable2 = (LogErrorParcelable) this.LogErrorQueue$ar$errorMap.get(pair);
        if (logErrorParcelable2 != null) {
            logErrorParcelable2.errorCount = IntMath.saturatedAdd(logErrorParcelable2.errorCount, logErrorParcelable.errorCount);
            return;
        }
        ?? r1 = this.LogErrorQueue$ar$errorMap;
        if (((SimpleArrayMap) r1).size >= 100) {
            this.overflowErrorCount = IntMath.saturatedAdd(this.overflowErrorCount, logErrorParcelable.errorCount);
        } else {
            r1.put(pair, logErrorParcelable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Map, java.lang.Object] */
    public final synchronized BatchedLogErrorParcelable getAndClearErrors() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.LogErrorQueue$ar$errorMap.values());
        int i = this.overflowErrorCount;
        if (i > 0) {
            arrayList.add(new LogErrorParcelable("UNKNOWN", TrainingProto$TrainingPageId.PAGE_ID_UPDATE_WELCOME_WITHOUT_TYPO_CORRECTION$ar$edu, i));
            this.overflowErrorCount = 0;
        }
        this.LogErrorQueue$ar$errorMap.clear();
        return new BatchedLogErrorParcelable(arrayList);
    }

    public final void release$ar$ds(Object obj) {
        int i = this.overflowErrorCount;
        if (i < 256) {
            ((Object[]) this.LogErrorQueue$ar$errorMap)[i] = obj;
            this.overflowErrorCount = i + 1;
        }
    }

    private LogErrorQueue() {
        this.overflowErrorCount = 0;
        this.LogErrorQueue$ar$errorMap = new ArrayMap();
    }
}
