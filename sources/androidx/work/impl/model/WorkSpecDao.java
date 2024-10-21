package androidx.work.impl.model;

import androidx.work.Data;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface WorkSpecDao {
    List getAllEligibleWorkSpecsForScheduling$ar$ds();

    List getRunningWork();

    List getScheduledWork();

    int getState$ar$edu$fd856834_0(String str);

    WorkSpec getWorkSpec(String str);

    List getWorkSpecIdAndStatesForName$ar$ds();

    void markWorkSpecScheduled$ar$ds(String str, long j);

    void resetWorkSpecNextScheduleTimeOverride(String str, int i);

    void setLastEnqueueTime(String str, long j);

    void setOutput(String str, Data data);

    void setState$ar$edu$ar$ds(int i, String str);

    void setStopReason(String str, int i);
}
