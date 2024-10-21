package androidx.work.impl;

import androidx.work.Logger;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnfinishedWorkListenerKt {
    public static final String TAG = Logger.tagWithPrefix("UnfinishedWorkListener");
    public static final long MAX_DELAY_MS = TimeUnit.HOURS.toMillis(1);
}
