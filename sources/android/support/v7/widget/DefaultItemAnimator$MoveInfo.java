package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultItemAnimator$MoveInfo {
    public final int fromX;
    public final int fromY;
    public final RecyclerView.ViewHolder holder;
    public final int toX;
    public final int toY;

    public DefaultItemAnimator$MoveInfo(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        this.holder = viewHolder;
        this.fromX = i;
        this.fromY = i2;
        this.toX = i3;
        this.toY = i4;
    }
}
