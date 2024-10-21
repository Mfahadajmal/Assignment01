package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ExecutionListener {
    void onExecuted(WorkGenerationalId workGenerationalId, boolean z);
}
