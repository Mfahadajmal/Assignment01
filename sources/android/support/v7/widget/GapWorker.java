package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Trace;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import androidx.constraintlayout.core.SolverVariable;
import androidx.core.os.TraceCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.accessibility.braille.brltty.BrailleKeyBinding;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.apps.tiktok.tracing.SuffixTree;
import io.grpc.ManagedChannelProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.chromium.net.CronetEngine;
import org.chromium.net.CronetProvider;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class GapWorker implements Runnable {
    static final ThreadLocal sGapWorker = new ThreadLocal();
    static final Comparator sTaskComparator = new AnonymousClass1(0);
    long mFrameIntervalNs;
    long mPostTimeNs;
    final ArrayList mRecyclerViews = new ArrayList();
    private final ArrayList mTasks = new ArrayList();

    /* compiled from: PG */
    /* renamed from: android.support.v7.widget.GapWorker$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Comparator {
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(Object obj, Object obj2) {
            boolean z;
            boolean z2;
            int i;
            int i2;
            switch (this.switching_field) {
                case 0:
                    Task task = (Task) obj;
                    Task task2 = (Task) obj2;
                    RecyclerView recyclerView = task.view;
                    if (recyclerView != null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (task2.view != null) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    if (z != z2) {
                        if (recyclerView != null) {
                            return -1;
                        }
                        return 1;
                    }
                    boolean z3 = task.neededNextFrame;
                    if (z3 != task2.neededNextFrame) {
                        if (z3) {
                            return -1;
                        }
                        return 1;
                    }
                    int i3 = task2.viewVelocity - task.viewVelocity;
                    if (i3 == 0) {
                        int i4 = task.distanceToItem - task2.distanceToItem;
                        if (i4 == 0) {
                            return 0;
                        }
                        return i4;
                    }
                    return i3;
                case 1:
                    return ((SuffixTree.TandemRepeatRegion) obj).end - ((SuffixTree.TandemRepeatRegion) obj2).end;
                case 2:
                    return ((SolverVariable) obj).id - ((SolverVariable) obj2).id;
                case 3:
                    float z4 = ViewCompat.Api21Impl.getZ((View) obj);
                    float z5 = ViewCompat.Api21Impl.getZ((View) obj2);
                    if (z4 > z5) {
                        return -1;
                    }
                    if (z4 >= z5) {
                        return 0;
                    }
                    return 1;
                case 4:
                    return ((ViewPager.ItemInfo) obj).position - ((ViewPager.ItemInfo) obj2).position;
                case 5:
                    BrailleKeyBinding brailleKeyBinding = (BrailleKeyBinding) obj;
                    BrailleKeyBinding brailleKeyBinding2 = (BrailleKeyBinding) obj2;
                    int i5 = brailleKeyBinding.command;
                    int i6 = brailleKeyBinding2.command;
                    if (i5 != i6) {
                        return i5 - i6;
                    }
                    boolean isLongPress = brailleKeyBinding.isLongPress();
                    if (isLongPress != brailleKeyBinding2.isLongPress()) {
                        if (!isLongPress) {
                            return -1;
                        }
                        return 1;
                    }
                    boolean z6 = brailleKeyBinding.unifiedKeyBinding;
                    if (z6 != brailleKeyBinding2.unifiedKeyBinding) {
                        if (z6) {
                            return -1;
                        }
                        return 1;
                    }
                    String[] strArr = brailleKeyBinding.keyNames;
                    String[] strArr2 = brailleKeyBinding2.keyNames;
                    int length = strArr.length;
                    int length2 = strArr2.length;
                    if (length != length2) {
                        return length - length2;
                    }
                    for (int i7 = 0; i7 < strArr.length; i7++) {
                        int compareTo = strArr[i7].compareTo(strArr2[i7]);
                        if (compareTo != 0) {
                            return compareTo;
                        }
                    }
                    return 0;
                case 6:
                    return ((BrailleKeyBinding) obj).command - ((BrailleKeyBinding) obj2).command;
                case 7:
                    return Integer.compare(((GestureShortcutMapping.TalkBackGesture) obj).gestureType, ((GestureShortcutMapping.TalkBackGesture) obj2).gestureType);
                case 8:
                    Label label = (Label) obj;
                    Label label2 = (Label) obj2;
                    if (label == null) {
                        if (label2 != null) {
                            return -1;
                        }
                        return 0;
                    }
                    if (label2 == null) {
                        return 1;
                    }
                    int compareTo2 = label.mPackageName.compareTo(label2.mPackageName);
                    if (compareTo2 != 0) {
                        return compareTo2;
                    }
                    return label.mViewName.compareTo(label2.mViewName);
                case 9:
                    Rect rect = (Rect) obj;
                    Rect rect2 = (Rect) obj2;
                    if (rect.top != rect2.top) {
                        i = rect.top;
                        i2 = rect2.top;
                    } else if (rect.bottom != rect2.bottom) {
                        i = rect.bottom;
                        i2 = rect2.bottom;
                    } else if (rect.left != rect2.left) {
                        i = rect.left;
                        i2 = rect2.left;
                    } else {
                        i = rect.right;
                        i2 = rect2.right;
                    }
                    return i - i2;
                case 10:
                    return ((View) obj).getTop() - ((View) obj2).getTop();
                case 11:
                    return ((Comparable) obj).compareTo((Comparable) obj2);
                case 12:
                    return ((ManagedChannelProvider) obj).priority() - ((ManagedChannelProvider) obj2).priority();
                default:
                    CronetProvider.ProviderInfo providerInfo = (CronetProvider.ProviderInfo) obj;
                    CronetProvider.ProviderInfo providerInfo2 = (CronetProvider.ProviderInfo) obj2;
                    if (CronetProvider.PROVIDER_NAME_FALLBACK.equals(providerInfo.provider.getName())) {
                        return 1;
                    }
                    if (CronetProvider.PROVIDER_NAME_FALLBACK.equals(providerInfo2.provider.getName())) {
                        return -1;
                    }
                    return -CronetEngine.Builder.compareVersions(providerInfo.provider.getVersion(), providerInfo2.provider.getVersion());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutPrefetchRegistryImpl {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        public final void addPosition(int i, int i2) {
            if (i >= 0) {
                if (i2 >= 0) {
                    int i3 = this.mCount;
                    int i4 = i3 + i3;
                    int[] iArr = this.mPrefetchArray;
                    if (iArr == null) {
                        int[] iArr2 = new int[4];
                        this.mPrefetchArray = iArr2;
                        Arrays.fill(iArr2, -1);
                    } else {
                        int length = iArr.length;
                        if (i4 >= length) {
                            int[] iArr3 = new int[i4 + i4];
                            this.mPrefetchArray = iArr3;
                            System.arraycopy(iArr, 0, iArr3, 0, length);
                        }
                    }
                    int[] iArr4 = this.mPrefetchArray;
                    iArr4[i4] = i;
                    iArr4[i4 + 1] = i2;
                    this.mCount++;
                    return;
                }
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            throw new IllegalArgumentException("Layout positions must be non-negative");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void clearPrefetchPositions() {
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mCount = 0;
        }

        final void collectPrefetchPositionsFromView(RecyclerView recyclerView, boolean z) {
            this.mCount = 0;
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.mLayout;
            if (recyclerView.mAdapter != null && layoutManager != null && layoutManager.mItemPrefetchEnabled) {
                if (z) {
                    if (!recyclerView.mAdapterHelper$ar$class_merging.hasPendingUpdates()) {
                        layoutManager.collectInitialPrefetchPositions$ar$class_merging(recyclerView.mAdapter.getItemCount(), this);
                    }
                } else if (!recyclerView.hasPendingAdapterUpdates()) {
                    layoutManager.collectAdjacentPrefetchPositions$ar$class_merging(this.mPrefetchDx, this.mPrefetchDy, recyclerView.mState, this);
                }
                int i = this.mCount;
                if (i > layoutManager.mPrefetchMaxCountObserved) {
                    layoutManager.mPrefetchMaxCountObserved = i;
                    layoutManager.mPrefetchMaxObservedInInitialPrefetch = z;
                    recyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean lastPrefetchIncludedPosition(int i) {
            if (this.mPrefetchArray != null) {
                int i2 = this.mCount;
                int i3 = i2 + i2;
                for (int i4 = 0; i4 < i3; i4 += 2) {
                    if (this.mPrefetchArray[i4] == i) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Task {
        public int distanceToItem;
        public boolean neededNextFrame;
        public int position;
        public RecyclerView view;
        public int viewVelocity;
    }

    private static final RecyclerView.ViewHolder prefetchPositionWithDeadline$ar$ds(RecyclerView recyclerView, int i, long j) {
        int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                return null;
            }
        }
        RecyclerView.Recycler recycler = recyclerView.mRecycler;
        if (j == Long.MAX_VALUE) {
            try {
                if (TraceCompat.isEnabled()) {
                    Trace.beginSection("RV Prefetch forced - needed next frame");
                }
            } finally {
                recyclerView.onExitLayoutOrScroll(false);
                Trace.endSection();
            }
        }
        recyclerView.onEnterLayoutOrScroll();
        RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline$ar$ds = recycler.tryGetViewHolderForPositionByDeadline$ar$ds(i, j);
        if (tryGetViewHolderForPositionByDeadline$ar$ds != null) {
            if (tryGetViewHolderForPositionByDeadline$ar$ds.isBound() && !tryGetViewHolderForPositionByDeadline$ar$ds.isInvalid()) {
                recycler.recycleView(tryGetViewHolderForPositionByDeadline$ar$ds.itemView);
            } else {
                recycler.addViewHolderToRecycledViewPool(tryGetViewHolderForPositionByDeadline$ar$ds, false);
            }
        }
        return tryGetViewHolderForPositionByDeadline$ar$ds;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void postFromTraversal(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.mIsAttached && this.mPostTimeNs == 0) {
            this.mPostTimeNs = RecyclerView.getNanoTime$ar$ds();
            recyclerView.post(this);
        }
        LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
        layoutPrefetchRegistryImpl.mPrefetchDx = i;
        layoutPrefetchRegistryImpl.mPrefetchDy = i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d2, code lost:
    
        continue;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GapWorker.run():void");
    }
}
