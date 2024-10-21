package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.collection.LongSparseArrayKt;
import androidx.collection.SimpleArrayMap;
import androidx.collection.internal.ContainerHelpersKt;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.tracing.TraceApi29Impl;
import androidx.transition.Transition;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public ArrayList mEndValuesList;
    public Impl26 mEpicenterCallback$ar$class_merging;
    private TransitionListener[] mListenersCache;
    SeekController mSeekController;
    long mSeekOffsetInParent;
    public ArrayList mStartValuesList;
    long mTotalDuration;
    private static final Animator[] EMPTY_ANIMATOR_ARRAY = new Animator[0];
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final TraceApi29Impl STRAIGHT_PATH_MOTION$ar$class_merging = new TraceApi29Impl(null);
    private static final ThreadLocal sRunningAnimators = new ThreadLocal();
    private final String mName = getClass().getName();
    public long mStartDelay = -1;
    public long mDuration = -1;
    public TimeInterpolator mInterpolator = null;
    final ArrayList mTargetIds = new ArrayList();
    final ArrayList mTargets = new ArrayList();
    public WorkManagerTaskExecutor mStartValues$ar$class_merging = new WorkManagerTaskExecutor();
    public WorkManagerTaskExecutor mEndValues$ar$class_merging = new WorkManagerTaskExecutor();
    TransitionSet mParent = null;
    public final int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    final ArrayList mCurrentAnimators = new ArrayList();
    private Animator[] mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
    int mNumInstances = 0;
    private boolean mPaused = false;
    boolean mEnded = false;
    public Transition mCloneParent = null;
    private ArrayList mListeners = null;
    ArrayList mAnimators = new ArrayList();
    public TraceApi29Impl mPathMotion$ar$class_merging = STRAIGHT_PATH_MOTION$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Impl26 {
        static long getTotalDuration(Animator animator) {
            long totalDuration;
            totalDuration = animator.getTotalDuration();
            return totalDuration;
        }

        static void setCurrentPlayTime(Animator animator, long j) {
            ((AnimatorSet) animator).setCurrentPlayTime(j);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SeekController extends TransitionListenerAdapter {
        public boolean mIsCanceled;
        public boolean mIsReady;
        private Runnable mResetToStartState;
        public SpringAnimation mSpringAnimation;
        public long mCurrentPlayTime = -1;
        public final AsyncInterceptorsClientCallListener.PendingMessage mVelocityTracker$ar$class_merging$ar$class_merging = new AsyncInterceptorsClientCallListener.PendingMessage();

        public SeekController() {
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void ensureAnimation() {
            /*
                Method dump skipped, instructions count: 355
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.SeekController.ensureAnimation():void");
        }

        public final void animateToEnd() {
            ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition((float) (getDurationMillis() + 1));
        }

        public final void animateToStart(Runnable runnable) {
            this.mResetToStartState = runnable;
            ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition(0.0f);
        }

        public final long getDurationMillis() {
            return Transition.this.mTotalDuration;
        }

        /* renamed from: lambda$ensureAnimation$0$androidx-transition-Transition$SeekController$ar$ds, reason: not valid java name */
        public final /* synthetic */ void m36x8255e67e(float f) {
            if (f < 1.0f) {
                long durationMillis = getDurationMillis();
                Transition transitionAt = ((TransitionSet) Transition.this).getTransitionAt(0);
                Transition transition = transitionAt.mCloneParent;
                transitionAt.mCloneParent = null;
                Transition.this.setCurrentPlayTimeMillis(-1L, this.mCurrentPlayTime);
                Transition.this.setCurrentPlayTimeMillis(durationMillis, -1L);
                this.mCurrentPlayTime = durationMillis;
                Runnable runnable = this.mResetToStartState;
                if (runnable != null) {
                    runnable.run();
                }
                Transition.this.mAnimators.clear();
                if (transition != null) {
                    transition.notifyFromTransition(transition, TransitionNotification.ON_END, true);
                    return;
                }
                return;
            }
            Transition transition2 = Transition.this;
            transition2.notifyFromTransition(transition2, TransitionNotification.ON_END, false);
        }

        public final void onAnimationUpdate$ar$ds(float f) {
            long max = Math.max(-1L, Math.min(getDurationMillis() + 1, Math.round(f)));
            Transition.this.setCurrentPlayTimeMillis(max, this.mCurrentPlayTime);
            this.mCurrentPlayTime = max;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public final void onTransitionCancel(Transition transition) {
            this.mIsCanceled = true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionCancel(Transition transition);

        void onTransitionEnd(Transition transition);

        void onTransitionEnd(Transition transition, boolean z);

        void onTransitionPause$ar$ds();

        void onTransitionResume$ar$ds();

        void onTransitionStart(Transition transition);

        void onTransitionStart(Transition transition, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TransitionNotification {
        public static final TransitionNotification ON_CANCEL;
        public static final TransitionNotification ON_END;
        public static final TransitionNotification ON_PAUSE;
        public static final TransitionNotification ON_RESUME;
        public static final TransitionNotification ON_START;

        static {
            final int i = 1;
            ON_START = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda1
                @Override // androidx.transition.Transition.TransitionNotification
                public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                    int i2 = i;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 != 2) {
                                if (i2 != 3) {
                                    transitionListener.onTransitionResume$ar$ds();
                                    return;
                                } else {
                                    transitionListener.onTransitionPause$ar$ds();
                                    return;
                                }
                            }
                            transitionListener.onTransitionCancel(transition);
                            return;
                        }
                        transitionListener.onTransitionStart(transition, z);
                        return;
                    }
                    transitionListener.onTransitionEnd(transition, z);
                }
            };
            final int i2 = 0;
            ON_END = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda1
                @Override // androidx.transition.Transition.TransitionNotification
                public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                    int i22 = i2;
                    if (i22 != 0) {
                        if (i22 != 1) {
                            if (i22 != 2) {
                                if (i22 != 3) {
                                    transitionListener.onTransitionResume$ar$ds();
                                    return;
                                } else {
                                    transitionListener.onTransitionPause$ar$ds();
                                    return;
                                }
                            }
                            transitionListener.onTransitionCancel(transition);
                            return;
                        }
                        transitionListener.onTransitionStart(transition, z);
                        return;
                    }
                    transitionListener.onTransitionEnd(transition, z);
                }
            };
            final int i3 = 2;
            ON_CANCEL = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda1
                @Override // androidx.transition.Transition.TransitionNotification
                public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                    int i22 = i3;
                    if (i22 != 0) {
                        if (i22 != 1) {
                            if (i22 != 2) {
                                if (i22 != 3) {
                                    transitionListener.onTransitionResume$ar$ds();
                                    return;
                                } else {
                                    transitionListener.onTransitionPause$ar$ds();
                                    return;
                                }
                            }
                            transitionListener.onTransitionCancel(transition);
                            return;
                        }
                        transitionListener.onTransitionStart(transition, z);
                        return;
                    }
                    transitionListener.onTransitionEnd(transition, z);
                }
            };
            final int i4 = 3;
            ON_PAUSE = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda1
                @Override // androidx.transition.Transition.TransitionNotification
                public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                    int i22 = i4;
                    if (i22 != 0) {
                        if (i22 != 1) {
                            if (i22 != 2) {
                                if (i22 != 3) {
                                    transitionListener.onTransitionResume$ar$ds();
                                    return;
                                } else {
                                    transitionListener.onTransitionPause$ar$ds();
                                    return;
                                }
                            }
                            transitionListener.onTransitionCancel(transition);
                            return;
                        }
                        transitionListener.onTransitionStart(transition, z);
                        return;
                    }
                    transitionListener.onTransitionEnd(transition, z);
                }
            };
            final int i5 = 4;
            ON_RESUME = new TransitionNotification() { // from class: androidx.transition.Transition$TransitionNotification$$ExternalSyntheticLambda1
                @Override // androidx.transition.Transition.TransitionNotification
                public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
                    int i22 = i5;
                    if (i22 != 0) {
                        if (i22 != 1) {
                            if (i22 != 2) {
                                if (i22 != 3) {
                                    transitionListener.onTransitionResume$ar$ds();
                                    return;
                                } else {
                                    transitionListener.onTransitionPause$ar$ds();
                                    return;
                                }
                            }
                            transitionListener.onTransitionCancel(transition);
                            return;
                        }
                        transitionListener.onTransitionStart(transition, z);
                        return;
                    }
                    transitionListener.onTransitionEnd(transition, z);
                }
            };
        }

        void notifyListener(TransitionListener transitionListener, Transition transition, boolean z);
    }

    private static void addViewValues$ar$class_merging(WorkManagerTaskExecutor workManagerTaskExecutor, View view, TransitionValues transitionValues) {
        ((SimpleArrayMap) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mMainThreadExecutor).put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (((SparseArray) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mMainThreadHandler).indexOfKey(id) >= 0) {
                ((SparseArray) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mMainThreadHandler).put(id, null);
            } else {
                ((SparseArray) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mMainThreadHandler).put(id, view);
            }
        }
        String transitionName = ViewCompat.Api21Impl.getTransitionName(view);
        if (transitionName != null) {
            if (((SimpleArrayMap) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mBackgroundExecutor).containsKey(transitionName)) {
                ((SimpleArrayMap) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mBackgroundExecutor).put(transitionName, null);
            } else {
                ((SimpleArrayMap) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mBackgroundExecutor).put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                LongSparseArray longSparseArray = (LongSparseArray) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mTaskDispatcher;
                if (longSparseArray.garbage) {
                    int i = longSparseArray.size;
                    long[] jArr = longSparseArray.keys;
                    Object[] objArr = longSparseArray.values;
                    int i2 = 0;
                    for (int i3 = 0; i3 < i; i3++) {
                        Object obj = objArr[i3];
                        if (obj != LongSparseArrayKt.DELETED) {
                            if (i3 != i2) {
                                jArr[i2] = jArr[i3];
                                objArr[i2] = obj;
                                objArr[i3] = null;
                            }
                            i2++;
                        }
                    }
                    longSparseArray.garbage = false;
                    longSparseArray.size = i2;
                }
                if (ContainerHelpersKt.binarySearch(longSparseArray.keys, longSparseArray.size, itemIdAtPosition) >= 0) {
                    View view2 = (View) ((LongSparseArray) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mTaskDispatcher).get(itemIdAtPosition);
                    if (view2 != null) {
                        view2.setHasTransientState(false);
                        ((LongSparseArray) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mTaskDispatcher).put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                view.setHasTransientState(true);
                ((LongSparseArray) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mTaskDispatcher).put(itemIdAtPosition, view);
            }
        }
    }

    private final void captureHierarchy(View view, boolean z) {
        if (view != null) {
            view.getId();
            if (view.getParent() instanceof ViewGroup) {
                TransitionValues transitionValues = new TransitionValues(view);
                if (z) {
                    captureStartValues(transitionValues);
                } else {
                    captureEndValues(transitionValues);
                }
                transitionValues.mTargetedTransitions.add(this);
                capturePropagationValues(transitionValues);
                if (z) {
                    addViewValues$ar$class_merging(this.mStartValues$ar$class_merging, view, transitionValues);
                } else {
                    addViewValues$ar$class_merging(this.mEndValues$ar$class_merging, view, transitionValues);
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    captureHierarchy(viewGroup.getChildAt(i), z);
                }
            }
        }
    }

    public static ArrayMap getRunningAnimators() {
        ThreadLocal threadLocal = sRunningAnimators;
        ArrayMap arrayMap = (ArrayMap) threadLocal.get();
        if (arrayMap == null) {
            ArrayMap arrayMap2 = new ArrayMap();
            threadLocal.set(arrayMap2);
            return arrayMap2;
        }
        return arrayMap;
    }

    private static boolean isValueChanged(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Map map = transitionValues2.values;
        Object obj = transitionValues.values.get(str);
        Object obj2 = map.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    public void addListener$ar$ds(TransitionListener transitionListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(transitionListener);
    }

    public void addTarget$ar$ds$f83a504d_0(View view) {
        this.mTargets.add(view);
    }

    public void cancel() {
        ArrayList arrayList = this.mCurrentAnimators;
        int size = arrayList.size();
        Animator[] animatorArr = (Animator[]) arrayList.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        while (true) {
            size--;
            if (size >= 0) {
                Animator animator = animatorArr[size];
                animatorArr[size] = null;
                animator.cancel();
            } else {
                this.mAnimatorCache = animatorArr;
                notifyFromTransition(this, TransitionNotification.ON_CANCEL, false);
                return;
            }
        }
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    public abstract void captureStartValues(TransitionValues transitionValues);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void captureValues(ViewGroup viewGroup, boolean z) {
        boolean z2;
        clearValues(z);
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            captureHierarchy(viewGroup, z);
            return;
        }
        int i = 0;
        while (true) {
            boolean z3 = true;
            if (i >= this.mTargetIds.size()) {
                break;
            }
            View findViewById = viewGroup.findViewById(((Integer) this.mTargetIds.get(i)).intValue());
            if (findViewById != null) {
                TransitionValues transitionValues = new TransitionValues(findViewById);
                if (z) {
                    captureStartValues(transitionValues);
                } else {
                    captureEndValues(transitionValues);
                    z3 = false;
                }
                transitionValues.mTargetedTransitions.add(this);
                capturePropagationValues(transitionValues);
                if (z3) {
                    addViewValues$ar$class_merging(this.mStartValues$ar$class_merging, findViewById, transitionValues);
                } else {
                    addViewValues$ar$class_merging(this.mEndValues$ar$class_merging, findViewById, transitionValues);
                }
            }
            i++;
        }
        for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
            View view = (View) this.mTargets.get(i2);
            TransitionValues transitionValues2 = new TransitionValues(view);
            if (z) {
                captureStartValues(transitionValues2);
                z2 = true;
            } else {
                captureEndValues(transitionValues2);
                z2 = false;
            }
            transitionValues2.mTargetedTransitions.add(this);
            capturePropagationValues(transitionValues2);
            if (z2) {
                addViewValues$ar$class_merging(this.mStartValues$ar$class_merging, view, transitionValues2);
            } else {
                addViewValues$ar$class_merging(this.mEndValues$ar$class_merging, view, transitionValues2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearValues(boolean z) {
        if (z) {
            ((SimpleArrayMap) this.mStartValues$ar$class_merging.WorkManagerTaskExecutor$ar$mMainThreadExecutor).clear();
            ((SparseArray) this.mStartValues$ar$class_merging.WorkManagerTaskExecutor$ar$mMainThreadHandler).clear();
            ((LongSparseArray) this.mStartValues$ar$class_merging.WorkManagerTaskExecutor$ar$mTaskDispatcher).clear();
        } else {
            ((SimpleArrayMap) this.mEndValues$ar$class_merging.WorkManagerTaskExecutor$ar$mMainThreadExecutor).clear();
            ((SparseArray) this.mEndValues$ar$class_merging.WorkManagerTaskExecutor$ar$mMainThreadHandler).clear();
            ((LongSparseArray) this.mEndValues$ar$class_merging.WorkManagerTaskExecutor$ar$mTaskDispatcher).clear();
        }
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Transition mo35clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.mAnimators = new ArrayList();
            transition.mStartValues$ar$class_merging = new WorkManagerTaskExecutor();
            transition.mEndValues$ar$class_merging = new WorkManagerTaskExecutor();
            transition.mStartValuesList = null;
            transition.mEndValuesList = null;
            transition.mSeekController = null;
            transition.mCloneParent = this;
            transition.mListeners = null;
            return transition;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public void createAnimators$ar$class_merging(ViewGroup viewGroup, WorkManagerTaskExecutor workManagerTaskExecutor, WorkManagerTaskExecutor workManagerTaskExecutor2, ArrayList arrayList, ArrayList arrayList2) {
        Animator createAnimator;
        View view;
        Animator animator;
        TransitionValues transitionValues;
        int i;
        Animator animator2;
        TransitionValues transitionValues2;
        ArrayMap runningAnimators = getRunningAnimators();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        SeekController seekController = getRootTransition().mSeekController;
        int i2 = 0;
        while (i2 < size) {
            TransitionValues transitionValues3 = (TransitionValues) arrayList.get(i2);
            TransitionValues transitionValues4 = (TransitionValues) arrayList2.get(i2);
            if (transitionValues3 != null && !transitionValues3.mTargetedTransitions.contains(this)) {
                transitionValues3 = null;
            }
            if (transitionValues4 != null && !transitionValues4.mTargetedTransitions.contains(this)) {
                transitionValues4 = null;
            }
            if ((transitionValues3 != null || transitionValues4 != null) && ((transitionValues3 == null || transitionValues4 == null || isTransitionRequired(transitionValues3, transitionValues4)) && (createAnimator = createAnimator(viewGroup, transitionValues3, transitionValues4)) != null)) {
                if (transitionValues4 != null) {
                    View view2 = transitionValues4.view;
                    String[] transitionProperties = getTransitionProperties();
                    if (transitionProperties != null) {
                        TransitionValues transitionValues5 = new TransitionValues(view2);
                        TransitionValues transitionValues6 = (TransitionValues) ((SimpleArrayMap) workManagerTaskExecutor2.WorkManagerTaskExecutor$ar$mMainThreadExecutor).get(view2);
                        animator2 = createAnimator;
                        if (transitionValues6 != null) {
                            int i3 = 0;
                            while (i3 < transitionProperties.length) {
                                Map map = transitionValues5.values;
                                String str = transitionProperties[i3];
                                map.put(str, transitionValues6.values.get(str));
                                i3++;
                                transitionProperties = transitionProperties;
                            }
                        }
                        int i4 = runningAnimators.size;
                        int i5 = 0;
                        while (true) {
                            if (i5 < i4) {
                                NodeMenuRuleCreator nodeMenuRuleCreator = (NodeMenuRuleCreator) runningAnimators.get((Animator) runningAnimators.keyAt(i5));
                                if (nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleSpannables != null && nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleAction == view2) {
                                    if (((String) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleUnlabeledNode).equals(this.mName) && ((TransitionValues) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleSpannables).equals(transitionValues5)) {
                                        transitionValues2 = transitionValues5;
                                        animator2 = null;
                                        break;
                                    }
                                }
                                i5++;
                            } else {
                                transitionValues2 = transitionValues5;
                                break;
                            }
                        }
                    } else {
                        animator2 = createAnimator;
                        transitionValues2 = null;
                    }
                    view = view2;
                    transitionValues = transitionValues2;
                    animator = animator2;
                } else {
                    view = transitionValues3.view;
                    animator = createAnimator;
                    transitionValues = null;
                }
                if (animator != null) {
                    i = size;
                    NodeMenuRuleCreator nodeMenuRuleCreator2 = new NodeMenuRuleCreator(view, this.mName, this, viewGroup.getWindowId(), transitionValues, animator);
                    if (seekController != null) {
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.play(animator);
                        animator = animatorSet;
                    }
                    runningAnimators.put(animator, nodeMenuRuleCreator2);
                    this.mAnimators.add(animator);
                    i2++;
                    size = i;
                }
            }
            i = size;
            i2++;
            size = i;
        }
        if (sparseIntArray.size() != 0) {
            for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
                NodeMenuRuleCreator nodeMenuRuleCreator3 = (NodeMenuRuleCreator) runningAnimators.get((Animator) this.mAnimators.get(sparseIntArray.keyAt(i6)));
                ((Animator) nodeMenuRuleCreator3.NodeMenuRuleCreator$ar$ruleGranularity).setStartDelay((sparseIntArray.valueAt(i6) - Long.MAX_VALUE) + ((Animator) nodeMenuRuleCreator3.NodeMenuRuleCreator$ar$ruleGranularity).getStartDelay());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void end() {
        int i = this.mNumInstances - 1;
        this.mNumInstances = i;
        if (i == 0) {
            notifyFromTransition(this, TransitionNotification.ON_END, false);
            for (int i2 = 0; i2 < ((LongSparseArray) this.mStartValues$ar$class_merging.WorkManagerTaskExecutor$ar$mTaskDispatcher).size(); i2++) {
                View view = (View) ((LongSparseArray) this.mStartValues$ar$class_merging.WorkManagerTaskExecutor$ar$mTaskDispatcher).valueAt(i2);
                if (view != null) {
                    view.setHasTransientState(false);
                }
            }
            for (int i3 = 0; i3 < ((LongSparseArray) this.mEndValues$ar$class_merging.WorkManagerTaskExecutor$ar$mTaskDispatcher).size(); i3++) {
                View view2 = (View) ((LongSparseArray) this.mEndValues$ar$class_merging.WorkManagerTaskExecutor$ar$mTaskDispatcher).valueAt(i3);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                }
            }
            this.mEnded = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final TransitionValues getMatchedTransitionValues(View view, boolean z) {
        ArrayList arrayList;
        ArrayList arrayList2;
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getMatchedTransitionValues(view, z);
        }
        if (z) {
            arrayList = this.mStartValuesList;
        } else {
            arrayList = this.mEndValuesList;
        }
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i < size) {
                TransitionValues transitionValues = (TransitionValues) arrayList.get(i);
                if (transitionValues == null) {
                    return null;
                }
                if (transitionValues.view == view) {
                    break;
                }
                i++;
            } else {
                i = -1;
                break;
            }
        }
        if (i < 0) {
            return null;
        }
        if (z) {
            arrayList2 = this.mEndValuesList;
        } else {
            arrayList2 = this.mStartValuesList;
        }
        return (TransitionValues) arrayList2.get(i);
    }

    public final Transition getRootTransition() {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getRootTransition();
        }
        return this;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public final TransitionValues getTransitionValues(View view, boolean z) {
        WorkManagerTaskExecutor workManagerTaskExecutor;
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getTransitionValues(view, z);
        }
        if (z) {
            workManagerTaskExecutor = this.mStartValues$ar$class_merging;
        } else {
            workManagerTaskExecutor = this.mEndValues$ar$class_merging;
        }
        return (TransitionValues) ((SimpleArrayMap) workManagerTaskExecutor.WorkManagerTaskExecutor$ar$mMainThreadExecutor).get(view);
    }

    public boolean hasAnimators() {
        if (!this.mCurrentAnimators.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isSeekingSupported() {
        return false;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties != null) {
            for (String str : transitionProperties) {
                if (!isValueChanged(transitionValues, transitionValues2, str)) {
                }
            }
            return false;
        }
        Iterator it = transitionValues.values.keySet().iterator();
        while (it.hasNext()) {
            if (isValueChanged(transitionValues, transitionValues2, (String) it.next())) {
            }
        }
        return false;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isValidTarget(View view) {
        int id = view.getId();
        if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
            return true;
        }
        return false;
    }

    public final void notifyFromTransition(Transition transition, TransitionNotification transitionNotification, boolean z) {
        Transition transition2 = this.mCloneParent;
        if (transition2 != null) {
            transition2.notifyFromTransition(transition, transitionNotification, z);
        }
        ArrayList arrayList = this.mListeners;
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = this.mListeners.size();
            TransitionListener[] transitionListenerArr = this.mListenersCache;
            if (transitionListenerArr == null) {
                transitionListenerArr = new TransitionListener[size];
            }
            this.mListenersCache = null;
            TransitionListener[] transitionListenerArr2 = (TransitionListener[]) this.mListeners.toArray(transitionListenerArr);
            for (int i = 0; i < size; i++) {
                transitionNotification.notifyListener(transitionListenerArr2[i], transition, z);
                transitionListenerArr2[i] = null;
            }
            this.mListenersCache = transitionListenerArr2;
        }
    }

    public void pause(View view) {
        if (!this.mEnded) {
            ArrayList arrayList = this.mCurrentAnimators;
            int size = arrayList.size();
            Animator[] animatorArr = (Animator[]) arrayList.toArray(this.mAnimatorCache);
            this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
            while (true) {
                size--;
                if (size >= 0) {
                    Animator animator = animatorArr[size];
                    animatorArr[size] = null;
                    animator.pause();
                } else {
                    this.mAnimatorCache = animatorArr;
                    notifyFromTransition(this, TransitionNotification.ON_PAUSE, false);
                    this.mPaused = true;
                    return;
                }
            }
        }
    }

    public void prepareAnimatorsForSeeking() {
        ArrayMap runningAnimators = getRunningAnimators();
        this.mTotalDuration = 0L;
        for (int i = 0; i < this.mAnimators.size(); i++) {
            Animator animator = (Animator) this.mAnimators.get(i);
            NodeMenuRuleCreator nodeMenuRuleCreator = (NodeMenuRuleCreator) runningAnimators.get(animator);
            if (animator != null && nodeMenuRuleCreator != null) {
                long j = this.mDuration;
                if (j >= 0) {
                    ((Animator) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleGranularity).setDuration(j);
                }
                long j2 = this.mStartDelay;
                if (j2 >= 0) {
                    Animator animator2 = (Animator) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleGranularity;
                    animator2.setStartDelay(j2 + animator2.getStartDelay());
                }
                TimeInterpolator timeInterpolator = this.mInterpolator;
                if (timeInterpolator != null) {
                    ((Animator) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleGranularity).setInterpolator(timeInterpolator);
                }
                this.mCurrentAnimators.add(animator);
                this.mTotalDuration = Math.max(this.mTotalDuration, Impl26.getTotalDuration(animator));
            }
        }
        this.mAnimators.clear();
    }

    public void removeListener$ar$ds(TransitionListener transitionListener) {
        Transition transition;
        ArrayList arrayList = this.mListeners;
        if (arrayList != null) {
            if (!arrayList.remove(transitionListener) && (transition = this.mCloneParent) != null) {
                transition.removeListener$ar$ds(transitionListener);
            }
            if (this.mListeners.size() == 0) {
                this.mListeners = null;
            }
        }
    }

    public void removeTarget$ar$ds(View view) {
        this.mTargets.remove(view);
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                ArrayList arrayList = this.mCurrentAnimators;
                int size = arrayList.size();
                Animator[] animatorArr = (Animator[]) arrayList.toArray(this.mAnimatorCache);
                this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    Animator animator = animatorArr[size];
                    animatorArr[size] = null;
                    animator.resume();
                }
                this.mAnimatorCache = animatorArr;
                notifyFromTransition(this, TransitionNotification.ON_RESUME, false);
            }
            this.mPaused = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runAnimators() {
        start();
        final ArrayMap runningAnimators = getRunningAnimators();
        ArrayList arrayList = this.mAnimators;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animator animator = (Animator) arrayList.get(i);
            if (runningAnimators.containsKey(animator)) {
                start();
                if (animator != null) {
                    animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.2
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator2) {
                            runningAnimators.remove(animator2);
                            Transition.this.mCurrentAnimators.remove(animator2);
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator2) {
                            Transition.this.mCurrentAnimators.add(animator2);
                        }
                    });
                    long j = this.mDuration;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.mStartDelay;
                    if (j2 >= 0) {
                        animator.setStartDelay(j2 + animator.getStartDelay());
                    }
                    TimeInterpolator timeInterpolator = this.mInterpolator;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.3
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator2) {
                            Transition.this.end();
                            animator2.removeListener(this);
                        }
                    });
                    animator.start();
                }
            }
        }
        this.mAnimators.clear();
        end();
    }

    public void setCurrentPlayTimeMillis(long j, long j2) {
        boolean z;
        long j3 = this.mTotalDuration;
        if (j < j2) {
            z = true;
        } else {
            z = false;
        }
        if ((j2 < 0 && j >= 0) || (j2 > j3 && j <= j3)) {
            this.mEnded = false;
            notifyFromTransition(this, TransitionNotification.ON_START, z);
        }
        ArrayList arrayList = this.mCurrentAnimators;
        int size = arrayList.size();
        Animator[] animatorArr = (Animator[]) arrayList.toArray(this.mAnimatorCache);
        this.mAnimatorCache = EMPTY_ANIMATOR_ARRAY;
        for (int i = 0; i < size; i++) {
            Animator animator = animatorArr[i];
            animatorArr[i] = null;
            Impl26.setCurrentPlayTime(animator, Math.min(Math.max(0L, j), Impl26.getTotalDuration(animator)));
        }
        this.mAnimatorCache = animatorArr;
        if ((j > j3 && j2 <= j3) || (j < 0 && j2 >= 0)) {
            if (j > j3) {
                this.mEnded = true;
            }
            notifyFromTransition(this, TransitionNotification.ON_END, z);
        }
    }

    public void setDuration$ar$ds$b4560d67_0(long j) {
        this.mDuration = j;
    }

    public void setEpicenterCallback$ar$class_merging(Impl26 impl26) {
        this.mEpicenterCallback$ar$class_merging = impl26;
    }

    public void setInterpolator$ar$ds$b0a8efd3_0(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
    }

    public void setPathMotion$ar$class_merging(TraceApi29Impl traceApi29Impl) {
        if (traceApi29Impl == null) {
            this.mPathMotion$ar$class_merging = STRAIGHT_PATH_MOTION$ar$class_merging;
        } else {
            this.mPathMotion$ar$class_merging = traceApi29Impl;
        }
    }

    public void setStartDelay$ar$ds(long j) {
        this.mStartDelay = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void start() {
        if (this.mNumInstances == 0) {
            notifyFromTransition(this, TransitionNotification.ON_START, false);
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    public final String toString() {
        return toString("");
    }

    public String toString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(": ");
        if (this.mDuration != -1) {
            sb.append("dur(");
            sb.append(this.mDuration);
            sb.append(") ");
        }
        if (this.mStartDelay != -1) {
            sb.append("dly(");
            sb.append(this.mStartDelay);
            sb.append(") ");
        }
        if (this.mInterpolator != null) {
            sb.append("interp(");
            sb.append(this.mInterpolator);
            sb.append(") ");
        }
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            sb.append("tgts(");
            if (this.mTargetIds.size() > 0) {
                for (int i = 0; i < this.mTargetIds.size(); i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.mTargetIds.get(i));
                }
            }
            if (this.mTargets.size() > 0) {
                for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                    if (i2 > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.mTargets.get(i2));
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public void setPropagation$ar$ds() {
    }

    public void capturePropagationValues(TransitionValues transitionValues) {
    }
}
