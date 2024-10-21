package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultItemAnimator$ChangeInfo {
    public int fromX;
    public int fromY;
    public RecyclerView.ViewHolder newHolder;
    public RecyclerView.ViewHolder oldHolder;
    public int toX;
    public int toY;

    public DefaultItemAnimator$ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        this.oldHolder = viewHolder;
        this.newHolder = viewHolder2;
        this.fromX = i;
        this.fromY = i2;
        this.toX = i3;
        this.toY = i4;
    }

    public final String toString() {
        return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
    }
}
