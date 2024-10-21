package android.support.v7.recyclerview.extensions;

import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.util.ListUpdateCallback;
import androidx.work.impl.model.WorkName;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AsyncListDiffer {
    private static final Executor sMainThreadExecutor = new HandlerExecutor(1);
    public final WorkName mConfig$ar$class_merging$ar$class_merging$ar$class_merging;
    public List mList;
    public int mMaxScheduledGeneration;
    public final ListUpdateCallback mUpdateCallback;
    public final List mListeners = new CopyOnWriteArrayList();
    public List mReadOnlyList = Collections.emptyList();
    final Executor mMainThreadExecutor = sMainThreadExecutor;

    /* compiled from: PG */
    /* renamed from: android.support.v7.recyclerview.extensions.AsyncListDiffer$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ List val$newList;
        final /* synthetic */ List val$oldList;
        public final /* synthetic */ int val$runGeneration;

        public AnonymousClass1(List list, List list2, int i) {
            this.val$oldList = list;
            this.val$newList = list2;
            this.val$runGeneration = i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x00a6, code lost:
        
            if (r6.get(r7 + 1) > r6.get(r11)) goto L23;
         */
        /* JADX WARN: Removed duplicated region for block: B:10:0x01e6  */
        /* JADX WARN: Removed duplicated region for block: B:111:0x0181  */
        /* JADX WARN: Removed duplicated region for block: B:121:0x0119  */
        /* JADX WARN: Removed duplicated region for block: B:126:0x00d5  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x026c  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00c5  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00f6  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0179  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x0197 A[LOOP:5: B:81:0x0183->B:87:0x0197, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:88:0x019e A[EDGE_INSN: B:88:0x019e->B:89:0x019e BREAK  A[LOOP:5: B:81:0x0183->B:87:0x0197], SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 669
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.recyclerview.extensions.AsyncListDiffer.AnonymousClass1.run():void");
        }
    }

    public AsyncListDiffer(ListUpdateCallback listUpdateCallback, WorkName workName) {
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig$ar$class_merging$ar$class_merging$ar$class_merging = workName;
    }

    public final void onCurrentListChanged$ar$ds(List list) {
        for (AppCompatDelegateImpl.Api21Impl api21Impl : this.mListeners) {
        }
    }
}
