package android.support.v7.widget;

import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RecyclerView$RecycledViewPool$ScrapData {
    final ArrayList mScrapHeap = new ArrayList();
    final int mMaxScrap = 5;
    long mCreateRunningAverageNs = 0;
    long mBindRunningAverageNs = 0;
}
