package androidx.transition;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import androidx.tracing.TraceApi29Impl;
import androidx.transition.Transition;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransitionSet extends Transition {
    private int mChangeFlags;
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    ArrayList mTransitions;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TransitionSetListener extends TransitionListenerAdapter {
        final TransitionSet mTransitionSet;

        public TransitionSetListener(TransitionSet transitionSet) {
            this.mTransitionSet = transitionSet;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public final void onTransitionEnd(Transition transition) {
            TransitionSet transitionSet = this.mTransitionSet;
            int i = transitionSet.mCurrentListeners - 1;
            transitionSet.mCurrentListeners = i;
            if (i == 0) {
                transitionSet.mStarted = false;
                transitionSet.end();
            }
            transition.removeListener$ar$ds(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public final void onTransitionStart(Transition transition) {
            TransitionSet transitionSet = this.mTransitionSet;
            if (!transitionSet.mStarted) {
                transitionSet.start();
                this.mTransitionSet.mStarted = true;
            }
        }
    }

    public TransitionSet(byte[] bArr) {
        this();
        setOrdering$ar$ds(1);
        addTransition$ar$ds(new Fade(2));
        addTransition$ar$ds(new ChangeBounds());
        addTransition$ar$ds(new Fade(1));
    }

    private final void addTransitionInternal(Transition transition) {
        this.mTransitions.add(transition);
        transition.mParent = this;
    }

    @Override // androidx.transition.Transition
    public final /* synthetic */ void addListener$ar$ds(Transition.TransitionListener transitionListener) {
        super.addListener$ar$ds(transitionListener);
    }

    @Override // androidx.transition.Transition
    public final /* bridge */ /* synthetic */ void addTarget$ar$ds$f83a504d_0(View view) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            ((Transition) this.mTransitions.get(i)).addTarget$ar$ds$f83a504d_0(view);
        }
        super.addTarget$ar$ds$f83a504d_0(view);
    }

    public final void addTransition$ar$ds(Transition transition) {
        addTransitionInternal(transition);
        long j = this.mDuration;
        if (j >= 0) {
            transition.setDuration$ar$ds$b4560d67_0(j);
        }
        if ((this.mChangeFlags & 1) != 0) {
            transition.setInterpolator$ar$ds$b0a8efd3_0(this.mInterpolator);
        }
        if ((this.mChangeFlags & 2) != 0) {
            transition.setPropagation$ar$ds();
        }
        if ((this.mChangeFlags & 4) != 0) {
            transition.setPathMotion$ar$class_merging(this.mPathMotion$ar$class_merging);
        }
        if ((this.mChangeFlags & 8) != 0) {
            transition.setEpicenterCallback$ar$class_merging(this.mEpicenterCallback$ar$class_merging);
        }
    }

    @Override // androidx.transition.Transition
    public final void cancel() {
        super.cancel();
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.mTransitions.get(i)).cancel();
        }
    }

    @Override // androidx.transition.Transition
    public final void captureEndValues(TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            ArrayList arrayList = this.mTransitions;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Transition transition = (Transition) arrayList.get(i);
                if (transition.isValidTarget(transitionValues.view)) {
                    transition.captureEndValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void capturePropagationValues(TransitionValues transitionValues) {
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.mTransitions.get(i)).capturePropagationValues(transitionValues);
        }
    }

    @Override // androidx.transition.Transition
    public final void captureStartValues(TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            ArrayList arrayList = this.mTransitions;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Transition transition = (Transition) arrayList.get(i);
                if (transition.isValidTarget(transitionValues.view)) {
                    transition.captureStartValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    /* renamed from: clone */
    public final Transition mo35clone() {
        TransitionSet transitionSet = (TransitionSet) super.mo35clone();
        transitionSet.mTransitions = new ArrayList();
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            transitionSet.addTransitionInternal(((Transition) this.mTransitions.get(i)).mo35clone());
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    public final void createAnimators$ar$class_merging(ViewGroup viewGroup, WorkManagerTaskExecutor workManagerTaskExecutor, WorkManagerTaskExecutor workManagerTaskExecutor2, ArrayList arrayList, ArrayList arrayList2) {
        long j = this.mStartDelay;
        int size = this.mTransitions.size();
        int i = 0;
        while (i < size) {
            Transition transition = (Transition) this.mTransitions.get(i);
            if (j > 0) {
                if (!this.mPlayTogether) {
                    if (i == 0) {
                        i = 0;
                    }
                }
                long j2 = transition.mStartDelay;
                if (j2 > 0) {
                    transition.setStartDelay$ar$ds(j2 + j);
                } else {
                    transition.setStartDelay$ar$ds(j);
                }
            }
            transition.createAnimators$ar$class_merging(viewGroup, workManagerTaskExecutor, workManagerTaskExecutor2, arrayList, arrayList2);
            i++;
        }
    }

    public final Transition getTransitionAt(int i) {
        if (i >= 0 && i < this.mTransitions.size()) {
            return (Transition) this.mTransitions.get(i);
        }
        return null;
    }

    public final int getTransitionCount() {
        return this.mTransitions.size();
    }

    @Override // androidx.transition.Transition
    public final boolean hasAnimators() {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            if (((Transition) this.mTransitions.get(i)).hasAnimators()) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.transition.Transition
    public final boolean isSeekingSupported() {
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            if (!((Transition) this.mTransitions.get(i)).isSeekingSupported()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.transition.Transition
    public final void pause(View view) {
        super.pause(view);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.mTransitions.get(i)).pause(view);
        }
    }

    @Override // androidx.transition.Transition
    public final void prepareAnimatorsForSeeking() {
        this.mTotalDuration = 0L;
        TransitionListenerAdapter transitionListenerAdapter = new TransitionListenerAdapter() { // from class: androidx.transition.TransitionSet.2
            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public final void onTransitionCancel(Transition transition) {
                TransitionSet.this.mTransitions.remove(transition);
                if (!TransitionSet.this.hasAnimators()) {
                    Transition transition2 = TransitionSet.this;
                    transition2.notifyFromTransition(transition2, Transition.TransitionNotification.ON_CANCEL, false);
                    TransitionSet transitionSet = TransitionSet.this;
                    transitionSet.mEnded = true;
                    transitionSet.notifyFromTransition(transitionSet, Transition.TransitionNotification.ON_END, false);
                }
            }
        };
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition transition = (Transition) this.mTransitions.get(i);
            transition.addListener$ar$ds(transitionListenerAdapter);
            transition.prepareAnimatorsForSeeking();
            long j = transition.mTotalDuration;
            if (this.mPlayTogether) {
                this.mTotalDuration = Math.max(this.mTotalDuration, j);
            } else {
                long j2 = this.mTotalDuration;
                transition.mSeekOffsetInParent = j2;
                this.mTotalDuration = j2 + j;
            }
        }
    }

    @Override // androidx.transition.Transition
    public final /* synthetic */ void removeListener$ar$ds(Transition.TransitionListener transitionListener) {
        super.removeListener$ar$ds(transitionListener);
    }

    @Override // androidx.transition.Transition
    public final /* bridge */ /* synthetic */ void removeTarget$ar$ds(View view) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            ((Transition) this.mTransitions.get(i)).removeTarget$ar$ds(view);
        }
        super.removeTarget$ar$ds(view);
    }

    @Override // androidx.transition.Transition
    public final void resume(View view) {
        super.resume(view);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.mTransitions.get(i)).resume(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.transition.Transition
    public final void runAnimators() {
        if (!this.mTransitions.isEmpty()) {
            TransitionSetListener transitionSetListener = new TransitionSetListener(this);
            ArrayList arrayList = this.mTransitions;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Transition) arrayList.get(i)).addListener$ar$ds(transitionSetListener);
            }
            this.mCurrentListeners = this.mTransitions.size();
            if (!this.mPlayTogether) {
                for (int i2 = 1; i2 < this.mTransitions.size(); i2++) {
                    Transition transition = (Transition) this.mTransitions.get(i2 - 1);
                    final Transition transition2 = (Transition) this.mTransitions.get(i2);
                    transition.addListener$ar$ds(new TransitionListenerAdapter() { // from class: androidx.transition.TransitionSet.1
                        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                        public final void onTransitionEnd(Transition transition3) {
                            Transition.this.runAnimators();
                            transition3.removeListener$ar$ds(this);
                        }
                    });
                }
                Transition transition3 = (Transition) this.mTransitions.get(0);
                if (transition3 != null) {
                    transition3.runAnimators();
                    return;
                }
                return;
            }
            ArrayList arrayList2 = this.mTransitions;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((Transition) arrayList2.get(i3)).runAnimators();
            }
            return;
        }
        start();
        end();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setCurrentPlayTimeMillis(long r19, long r21) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.TransitionSet.setCurrentPlayTimeMillis(long, long):void");
    }

    @Override // androidx.transition.Transition
    /* renamed from: setDuration$ar$ds$584dc375_0, reason: merged with bridge method [inline-methods] */
    public final void setDuration$ar$ds$b4560d67_0(long j) {
        ArrayList arrayList;
        this.mDuration = j;
        if (this.mDuration >= 0 && (arrayList = this.mTransitions) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Transition) this.mTransitions.get(i)).setDuration$ar$ds$b4560d67_0(j);
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void setEpicenterCallback$ar$class_merging(Transition.Impl26 impl26) {
        this.mEpicenterCallback$ar$class_merging = impl26;
        this.mChangeFlags |= 8;
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.mTransitions.get(i)).setEpicenterCallback$ar$class_merging(impl26);
        }
    }

    @Override // androidx.transition.Transition
    /* renamed from: setInterpolator, reason: merged with bridge method [inline-methods] */
    public final TransitionSet setInterpolator$ar$ds$b0a8efd3_0(TimeInterpolator timeInterpolator) {
        this.mChangeFlags |= 1;
        ArrayList arrayList = this.mTransitions;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Transition) this.mTransitions.get(i)).setInterpolator$ar$ds$b0a8efd3_0(timeInterpolator);
            }
        }
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public final void setOrdering$ar$ds(int i) {
        boolean z;
        if (i != 0) {
            z = false;
        } else {
            z = true;
        }
        this.mPlayTogether = z;
    }

    @Override // androidx.transition.Transition
    public final void setPathMotion$ar$class_merging(TraceApi29Impl traceApi29Impl) {
        super.setPathMotion$ar$class_merging(traceApi29Impl);
        this.mChangeFlags |= 4;
        if (this.mTransitions != null) {
            for (int i = 0; i < this.mTransitions.size(); i++) {
                ((Transition) this.mTransitions.get(i)).setPathMotion$ar$class_merging(traceApi29Impl);
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void setPropagation$ar$ds() {
        this.mChangeFlags |= 2;
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.mTransitions.get(i)).setPropagation$ar$ds();
        }
    }

    @Override // androidx.transition.Transition
    public final /* synthetic */ void setStartDelay$ar$ds(long j) {
        this.mStartDelay = j;
    }

    @Override // androidx.transition.Transition
    public final String toString(String str) {
        String transition = super.toString(str);
        for (int i = 0; i < this.mTransitions.size(); i++) {
            transition = transition + "\n" + ((Transition) this.mTransitions.get(i)).toString(String.valueOf(str).concat("  "));
        }
        return transition;
    }

    @Override // androidx.transition.Transition
    /* renamed from: clone */
    public final /* bridge */ /* synthetic */ Object mo35clone() {
        return mo35clone();
    }

    public TransitionSet() {
        this.mTransitions = new ArrayList();
        this.mPlayTogether = true;
        this.mStarted = false;
        this.mChangeFlags = 0;
    }
}
