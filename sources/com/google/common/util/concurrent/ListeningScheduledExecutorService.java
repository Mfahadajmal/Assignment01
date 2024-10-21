package com.google.common.util.concurrent;

import com.google.common.util.concurrent.MoreExecutors$ScheduledListeningDecorator;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ListeningScheduledExecutorService extends ScheduledExecutorService, ListeningExecutorService {
    /* renamed from: schedule$ar$class_merging$49e426f9_0 */
    MoreExecutors$ScheduledListeningDecorator.ListenableScheduledTask schedule(Callable callable, long j, TimeUnit timeUnit);

    /* renamed from: schedule$ar$class_merging$7c20ace8_0 */
    MoreExecutors$ScheduledListeningDecorator.ListenableScheduledTask schedule(Runnable runnable, long j, TimeUnit timeUnit);
}
