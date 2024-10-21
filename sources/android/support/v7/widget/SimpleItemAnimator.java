package android.support.v7.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import androidx.core.view.NestedScrollingParentHelper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleItemAnimator extends RecyclerView.ItemAnimator {
    private static TimeInterpolator sDefaultInterpolator;
    public ArrayList mAddAnimations;
    public ArrayList mAdditionsList;
    public ArrayList mChangeAnimations;
    public ArrayList mChangesList;
    public ArrayList mMoveAnimations;
    public ArrayList mMovesList;
    public ArrayList mPendingAdditions;
    public ArrayList mPendingChanges;
    public ArrayList mPendingMoves;
    public ArrayList mPendingRemovals;
    ArrayList mRemoveAnimations;
    boolean mSupportsChangeAnimations;

    public SimpleItemAnimator(byte[] bArr) {
        this();
        this.mPendingRemovals = new ArrayList();
        this.mPendingAdditions = new ArrayList();
        this.mPendingMoves = new ArrayList();
        this.mPendingChanges = new ArrayList();
        this.mAdditionsList = new ArrayList();
        this.mMovesList = new ArrayList();
        this.mChangesList = new ArrayList();
        this.mAddAnimations = new ArrayList();
        this.mMoveAnimations = new ArrayList();
        this.mRemoveAnimations = new ArrayList();
        this.mChangeAnimations = new ArrayList();
    }

    public static void cancelAll$ar$ds(List list) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                ((RecyclerView.ViewHolder) list.get(size)).itemView.animate().cancel();
            } else {
                return;
            }
        }
    }

    private final void endChangeAnimation(List list, RecyclerView.ViewHolder viewHolder) {
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo = (DefaultItemAnimator$ChangeInfo) list.get(size);
                if (endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo, viewHolder) && defaultItemAnimator$ChangeInfo.oldHolder == null && defaultItemAnimator$ChangeInfo.newHolder == null) {
                    list.remove(defaultItemAnimator$ChangeInfo);
                }
            } else {
                return;
            }
        }
    }

    private final void endChangeAnimationIfNecessary(DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo) {
        RecyclerView.ViewHolder viewHolder = defaultItemAnimator$ChangeInfo.oldHolder;
        if (viewHolder != null) {
            endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo, viewHolder);
        }
        RecyclerView.ViewHolder viewHolder2 = defaultItemAnimator$ChangeInfo.newHolder;
        if (viewHolder2 != null) {
            endChangeAnimationIfNecessary(defaultItemAnimator$ChangeInfo, viewHolder2);
        }
    }

    private final void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewHolder);
    }

    public final void animateAdd$ar$ds(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        this.mPendingAdditions.add(viewHolder);
    }

    public final boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2) {
            return animateMove(viewHolder, i, i2, i3, i4);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        resetAnimation(viewHolder);
        float f = (i3 - i) - translationX;
        float f2 = (i4 - i2) - translationY;
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        if (viewHolder2 != null) {
            resetAnimation(viewHolder2);
            viewHolder2.itemView.setTranslationX(-((int) f));
            viewHolder2.itemView.setTranslationY(-((int) f2));
            viewHolder2.itemView.setAlpha(0.0f);
        }
        this.mPendingChanges.add(new DefaultItemAnimator$ChangeInfo(viewHolder, viewHolder2, i, i2, i3, i4));
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public final boolean animateChange$ar$class_merging(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, NestedScrollingParentHelper nestedScrollingParentHelper, NestedScrollingParentHelper nestedScrollingParentHelper2) {
        int i;
        int i2;
        int i3 = nestedScrollingParentHelper.mNestedScrollAxesNonTouch;
        int i4 = nestedScrollingParentHelper.mNestedScrollAxesTouch;
        if (viewHolder2.shouldIgnore()) {
            int i5 = nestedScrollingParentHelper.mNestedScrollAxesNonTouch;
            i2 = nestedScrollingParentHelper.mNestedScrollAxesTouch;
            i = i5;
        } else {
            i = nestedScrollingParentHelper2.mNestedScrollAxesNonTouch;
            i2 = nestedScrollingParentHelper2.mNestedScrollAxesTouch;
        }
        return animateChange(viewHolder, viewHolder2, i3, i4, i, i2);
    }

    public final boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = (int) view.getTranslationX();
        int translationY = (int) viewHolder.itemView.getTranslationY();
        resetAnimation(viewHolder);
        int i5 = i + translationX;
        int i6 = i3 - i5;
        int i7 = i2 + translationY;
        int i8 = i4 - i7;
        if (i6 == 0) {
            i6 = 0;
            if (i8 == 0) {
                dispatchAnimationFinished(viewHolder);
                return false;
            }
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i8 != 0) {
            view.setTranslationY(-i8);
        }
        this.mPendingMoves.add(new DefaultItemAnimator$MoveInfo(viewHolder, i5, i7, i3, i4));
        return true;
    }

    public final void animateRemove$ar$ds(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        this.mPendingRemovals.add(viewHolder);
    }

    public final void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public final void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((DefaultItemAnimator$MoveInfo) this.mPendingMoves.get(size)).holder == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchAnimationFinished(viewHolder);
                this.mPendingMoves.remove(size);
            }
        }
        endChangeAnimation(this.mPendingChanges, viewHolder);
        if (this.mPendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        int size2 = this.mChangesList.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            ArrayList arrayList = (ArrayList) this.mChangesList.get(size2);
            endChangeAnimation(arrayList, viewHolder);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        int size3 = this.mMovesList.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            ArrayList arrayList2 = (ArrayList) this.mMovesList.get(size3);
            int size4 = arrayList2.size();
            while (true) {
                size4--;
                if (size4 < 0) {
                    break;
                }
                if (((DefaultItemAnimator$MoveInfo) arrayList2.get(size4)).holder == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchAnimationFinished(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(size3);
                    }
                }
            }
        }
        int size5 = this.mAdditionsList.size();
        while (true) {
            size5--;
            if (size5 >= 0) {
                ArrayList arrayList3 = (ArrayList) this.mAdditionsList.get(size5);
                if (arrayList3.remove(viewHolder)) {
                    view.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder);
                    if (arrayList3.isEmpty()) {
                        this.mAdditionsList.remove(size5);
                    }
                }
            } else {
                this.mRemoveAnimations.remove(viewHolder);
                this.mAddAnimations.remove(viewHolder);
                this.mChangeAnimations.remove(viewHolder);
                this.mMoveAnimations.remove(viewHolder);
                dispatchFinishedWhenDone();
                return;
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public final void endAnimations() {
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            DefaultItemAnimator$MoveInfo defaultItemAnimator$MoveInfo = (DefaultItemAnimator$MoveInfo) this.mPendingMoves.get(size);
            View view = defaultItemAnimator$MoveInfo.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchAnimationFinished(defaultItemAnimator$MoveInfo.holder);
            this.mPendingMoves.remove(size);
        }
        int size2 = this.mPendingRemovals.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            dispatchAnimationFinished((RecyclerView.ViewHolder) this.mPendingRemovals.get(size2));
            this.mPendingRemovals.remove(size2);
        }
        int size3 = this.mPendingAdditions.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.mPendingAdditions.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
            this.mPendingAdditions.remove(size3);
        }
        int size4 = this.mPendingChanges.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            } else {
                endChangeAnimationIfNecessary((DefaultItemAnimator$ChangeInfo) this.mPendingChanges.get(size4));
            }
        }
        this.mPendingChanges.clear();
        if (!isRunning()) {
            return;
        }
        int size5 = this.mMovesList.size();
        while (true) {
            size5--;
            if (size5 < 0) {
                break;
            }
            ArrayList arrayList = (ArrayList) this.mMovesList.get(size5);
            int size6 = arrayList.size();
            while (true) {
                size6--;
                if (size6 >= 0) {
                    DefaultItemAnimator$MoveInfo defaultItemAnimator$MoveInfo2 = (DefaultItemAnimator$MoveInfo) arrayList.get(size6);
                    View view2 = defaultItemAnimator$MoveInfo2.holder.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchAnimationFinished(defaultItemAnimator$MoveInfo2.holder);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.mMovesList.remove(arrayList);
                    }
                }
            }
        }
        int size7 = this.mAdditionsList.size();
        while (true) {
            size7--;
            if (size7 < 0) {
                break;
            }
            ArrayList arrayList2 = (ArrayList) this.mAdditionsList.get(size7);
            int size8 = arrayList2.size();
            while (true) {
                size8--;
                if (size8 >= 0) {
                    RecyclerView.ViewHolder viewHolder2 = (RecyclerView.ViewHolder) arrayList2.get(size8);
                    viewHolder2.itemView.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.mAdditionsList.remove(arrayList2);
                    }
                }
            }
        }
        int size9 = this.mChangesList.size();
        while (true) {
            size9--;
            if (size9 >= 0) {
                ArrayList arrayList3 = (ArrayList) this.mChangesList.get(size9);
                int size10 = arrayList3.size();
                while (true) {
                    size10--;
                    if (size10 >= 0) {
                        endChangeAnimationIfNecessary((DefaultItemAnimator$ChangeInfo) arrayList3.get(size10));
                        if (arrayList3.isEmpty()) {
                            this.mChangesList.remove(arrayList3);
                        }
                    }
                }
            } else {
                cancelAll$ar$ds(this.mRemoveAnimations);
                cancelAll$ar$ds(this.mMoveAnimations);
                cancelAll$ar$ds(this.mAddAnimations);
                cancelAll$ar$ds(this.mChangeAnimations);
                dispatchAnimationsFinished();
                return;
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public final boolean isRunning() {
        if (this.mPendingAdditions.isEmpty() && this.mPendingChanges.isEmpty() && this.mPendingMoves.isEmpty() && this.mPendingRemovals.isEmpty() && this.mMoveAnimations.isEmpty() && this.mRemoveAnimations.isEmpty() && this.mAddAnimations.isEmpty() && this.mChangeAnimations.isEmpty() && this.mMovesList.isEmpty() && this.mAdditionsList.isEmpty() && this.mChangesList.isEmpty()) {
            return false;
        }
        return true;
    }

    private final boolean endChangeAnimationIfNecessary(DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo, RecyclerView.ViewHolder viewHolder) {
        if (defaultItemAnimator$ChangeInfo.newHolder == viewHolder) {
            defaultItemAnimator$ChangeInfo.newHolder = null;
        } else {
            if (defaultItemAnimator$ChangeInfo.oldHolder != viewHolder) {
                return false;
            }
            defaultItemAnimator$ChangeInfo.oldHolder = null;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchAnimationFinished(viewHolder);
        return true;
    }

    public SimpleItemAnimator() {
        this.mSupportsChangeAnimations = true;
    }
}
