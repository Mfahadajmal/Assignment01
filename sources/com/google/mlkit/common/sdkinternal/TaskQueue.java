package com.google.mlkit.common.sdkinternal;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import java.io.Closeable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskQueue {
    public final Object TaskQueue$ar$pendingTasks;
    public final Object TaskQueue$ar$runningThread;
    public boolean isActive;
    public final Object lock;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TaskContext implements Closeable {
        public TaskContext() {
            boolean z;
            if (((Thread) ((AtomicReference) TaskQueue.this.TaskQueue$ar$runningThread).getAndSet(Thread.currentThread())) == null) {
                z = true;
            } else {
                z = false;
            }
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(z);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            ((AtomicReference) TaskQueue.this.TaskQueue$ar$runningThread).set(null);
            TaskQueue.this.onTaskFinished();
        }
    }

    public TaskQueue(FocusProcessorForLogicalNavigation focusProcessorForLogicalNavigation, NavigationAction navigationAction, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        this.TaskQueue$ar$pendingTasks = focusProcessorForLogicalNavigation;
        this.TaskQueue$ar$runningThread = navigationAction;
        this.lock = accessibilityNodeInfoCompat;
        this.isActive = z;
    }

    private final void scheduleTask(Executor executor, Runnable runnable) {
        try {
            executor.execute(new EventBus$$ExternalSyntheticLambda0(this, runnable, 19, null));
        } catch (RejectedExecutionException unused) {
            onTaskFinished();
        }
    }

    public final void clear() {
        this.isActive = false;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List, java.lang.Object] */
    public final void fullyDrawnReported() {
        synchronized (this.TaskQueue$ar$pendingTasks) {
            this.isActive = true;
            Iterator it = this.lock.iterator();
            while (it.hasNext()) {
                ((Function0) it.next()).invoke();
            }
            this.lock.clear();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x012b, code lost:
    
        if (r8 == null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x021c, code lost:
    
        if (r12.performScrollActionInternal$ar$edu(1, r17, r14, com.google.android.accessibility.utils.traversal.TraversalStrategyUtils.convertSearchDirectionToScrollAction(r11.searchDirection), r11, 1000, r18) != false) goto L113;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAutoScrolled(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r17, com.google.android.accessibility.utils.Performance.EventId r18, int r19, int r20) {
        /*
            Method dump skipped, instructions count: 608
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.TaskQueue.onAutoScrolled(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, com.google.android.accessibility.utils.Performance$EventId, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.Queue] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, java.util.Queue] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object, java.lang.Runnable] */
    public final void onTaskFinished() {
        synchronized (this.lock) {
            if (this.TaskQueue$ar$pendingTasks.isEmpty()) {
                this.isActive = false;
            } else {
                Cleaner cleaner = (Cleaner) this.TaskQueue$ar$pendingTasks.remove();
                scheduleTask(cleaner.Cleaner$ar$cleanables, cleaner.Cleaner$ar$referenceQueue);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.util.Queue] */
    public final void submit(Executor executor, Runnable runnable) {
        synchronized (this.lock) {
            if (this.isActive) {
                this.TaskQueue$ar$pendingTasks.add(new Cleaner(executor, runnable));
            } else {
                this.isActive = true;
                scheduleTask(executor, runnable);
            }
        }
    }

    public TaskQueue() {
        this.lock = new Object();
        this.TaskQueue$ar$pendingTasks = new ArrayDeque();
        this.TaskQueue$ar$runningThread = new AtomicReference();
    }

    public TaskQueue(Executor executor, Function0 function0) {
        executor.getClass();
        this.TaskQueue$ar$runningThread = function0;
        this.TaskQueue$ar$pendingTasks = new Object();
        this.lock = new ArrayList();
    }

    public TaskQueue(int i) {
        this.TaskQueue$ar$pendingTasks = new ReentrantLock();
        this.lock = new long[i];
        this.TaskQueue$ar$runningThread = new boolean[i];
    }
}
