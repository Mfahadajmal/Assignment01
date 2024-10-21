package android.support.v7.util;

import android.support.v7.widget.GapWorker;
import com.google.apps.tiktok.tracing.SuffixTree;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DiffUtil {
    public static final Comparator DIAGONAL_COMPARATOR = new GapWorker.AnonymousClass1(1);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public void getChangePayload$ar$ds(int i, int i2) {
            throw null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DiffResult {
        public final Callback mCallback;
        public final List mDiagonals;
        public final int[] mNewItemStatuses;
        public final int mNewListSize;
        public final int[] mOldItemStatuses;
        public final int mOldListSize;

        public DiffResult(Callback callback, List list, int[] iArr, int[] iArr2) {
            SuffixTree.TandemRepeatRegion tandemRepeatRegion;
            int i;
            int i2;
            this.mDiagonals = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.mCallback = callback;
            int oldListSize = callback.getOldListSize();
            this.mOldListSize = oldListSize;
            int newListSize = callback.getNewListSize();
            this.mNewListSize = newListSize;
            if (list.isEmpty()) {
                tandemRepeatRegion = null;
            } else {
                tandemRepeatRegion = (SuffixTree.TandemRepeatRegion) list.get(0);
            }
            if (tandemRepeatRegion == null || tandemRepeatRegion.end != 0 || tandemRepeatRegion.numSeen != 0) {
                list.add(0, new SuffixTree.TandemRepeatRegion(0, 0, 0, null));
            }
            list.add(new SuffixTree.TandemRepeatRegion(oldListSize, newListSize, 0, null));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SuffixTree.TandemRepeatRegion tandemRepeatRegion2 = (SuffixTree.TandemRepeatRegion) it.next();
                for (int i3 = 0; i3 < tandemRepeatRegion2.begin; i3++) {
                    int i4 = tandemRepeatRegion2.end + i3;
                    int i5 = tandemRepeatRegion2.numSeen + i3;
                    if (true != this.mCallback.areContentsTheSame(i4, i5)) {
                        i2 = 2;
                    } else {
                        i2 = 1;
                    }
                    this.mOldItemStatuses[i4] = (i5 << 4) | i2;
                    this.mNewItemStatuses[i5] = (i4 << 4) | i2;
                }
            }
            int i6 = 0;
            for (SuffixTree.TandemRepeatRegion tandemRepeatRegion3 : this.mDiagonals) {
                while (i6 < tandemRepeatRegion3.end) {
                    if (this.mOldItemStatuses[i6] == 0) {
                        int size = this.mDiagonals.size();
                        int i7 = 0;
                        int i8 = 0;
                        while (true) {
                            if (i7 < size) {
                                SuffixTree.TandemRepeatRegion tandemRepeatRegion4 = (SuffixTree.TandemRepeatRegion) this.mDiagonals.get(i7);
                                while (i8 < tandemRepeatRegion4.numSeen) {
                                    if (this.mNewItemStatuses[i8] == 0 && this.mCallback.areItemsTheSame(i6, i8)) {
                                        if (true != this.mCallback.areContentsTheSame(i6, i8)) {
                                            i = 4;
                                        } else {
                                            i = 8;
                                        }
                                        this.mOldItemStatuses[i6] = (i8 << 4) | i;
                                        this.mNewItemStatuses[i8] = i | (i6 << 4);
                                    } else {
                                        i8++;
                                    }
                                }
                                i8 = tandemRepeatRegion4.endY();
                                i7++;
                            }
                        }
                    }
                    i6++;
                }
                i6 = tandemRepeatRegion3.endX();
            }
        }

        public static PostponedUpdate getPostponedUpdate(Collection collection, int i, boolean z) {
            PostponedUpdate postponedUpdate;
            Iterator it = collection.iterator();
            while (true) {
                if (it.hasNext()) {
                    postponedUpdate = (PostponedUpdate) it.next();
                    if (postponedUpdate.posInOwnerList == i && postponedUpdate.removal == z) {
                        it.remove();
                        break;
                    }
                } else {
                    postponedUpdate = null;
                    break;
                }
            }
            while (it.hasNext()) {
                PostponedUpdate postponedUpdate2 = (PostponedUpdate) it.next();
                if (z) {
                    postponedUpdate2.currentPos--;
                } else {
                    postponedUpdate2.currentPos++;
                }
            }
            return postponedUpdate;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PostponedUpdate {
        public int currentPos;
        final int posInOwnerList;
        final boolean removal;

        public PostponedUpdate(int i, int i2, boolean z) {
            this.posInOwnerList = i;
            this.currentPos = i2;
            this.removal = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Range {
        public int newListEnd;
        public int newListStart;
        public int oldListEnd;
        public int oldListStart;

        public Range() {
        }

        public final int newSize() {
            return this.newListEnd - this.newListStart;
        }

        public final int oldSize() {
            return this.oldListEnd - this.oldListStart;
        }

        public Range(int i, int i2) {
            this.oldListStart = 0;
            this.oldListEnd = i;
            this.newListStart = 0;
            this.newListEnd = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Snake {
        public int endX;
        public int endY;
        public boolean reverse;
        public int startX;
        public int startY;

        public final int diagonalSize() {
            return Math.min(this.endX - this.startX, this.endY - this.startY);
        }
    }
}
