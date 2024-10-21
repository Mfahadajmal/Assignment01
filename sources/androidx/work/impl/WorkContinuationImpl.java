package androidx.work.impl;

import android.support.v7.view.WindowCallbackWrapper;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkContinuationImpl extends WindowCallbackWrapper.Api26Impl {
    public static final String TAG = Logger.tagWithPrefix("WorkContinuationImpl");
    public boolean mEnqueued;
    public final List mIds;
    public Operation mOperation;
    public final List mWork;
    public final WorkManagerImpl mWorkManagerImpl;
    public final int mExistingWorkPolicy$ar$edu = 2;
    private final List mAllIds = new ArrayList();

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, int i, List list) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mWork = list;
        this.mIds = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            String stringId = ((WorkRequest) list.get(i2)).getStringId();
            this.mIds.add(stringId);
            this.mAllIds.add(stringId);
        }
    }

    public static Set prerequisitesFor$ar$ds() {
        return new HashSet();
    }
}
