package org.chromium.base.task;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SingleThreadTaskRunner extends TaskRunner {
    boolean belongsToCurrentThread();
}
