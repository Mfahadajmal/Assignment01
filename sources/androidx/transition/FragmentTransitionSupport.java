package androidx.transition;

import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransitionImpl;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.transition.Transition;
import com.google.android.marvin.talkback.R;
import io.grpc.CallCredentials$RequestInfo;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FragmentTransitionSupport extends FragmentTransitionImpl {

    /* compiled from: PG */
    /* renamed from: androidx.transition.FragmentTransitionSupport$5, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass5 extends Transition.Impl26 {
    }

    private static boolean hasSimpleTarget(Transition transition) {
        if (isNullOrEmpty(transition.mTargetIds) && isNullOrEmpty(null) && isNullOrEmpty(null)) {
            return false;
        }
        return true;
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void addTarget(Object obj, View view) {
        ((Transition) obj).addTarget$ar$ds$f83a504d_0(view);
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void addTargets(Object obj, ArrayList arrayList) {
        Transition transition = (Transition) obj;
        if (transition != null) {
            int i = 0;
            if (transition instanceof TransitionSet) {
                TransitionSet transitionSet = (TransitionSet) transition;
                int transitionCount = transitionSet.getTransitionCount();
                while (i < transitionCount) {
                    addTargets(transitionSet.getTransitionAt(i), arrayList);
                    i++;
                }
                return;
            }
            if (!hasSimpleTarget(transition) && isNullOrEmpty(transition.mTargets)) {
                int size = arrayList.size();
                while (i < size) {
                    transition.addTarget$ar$ds$f83a504d_0((View) arrayList.get(i));
                    i++;
                }
            }
        }
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void animateToEnd(Object obj) {
        ((Transition.SeekController) obj).animateToEnd();
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void animateToStart(Object obj, Runnable runnable) {
        ((Transition.SeekController) obj).animateToStart(runnable);
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final boolean canHandle(Object obj) {
        return obj instanceof Transition;
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final Object cloneTransition(Object obj) {
        if (obj != null) {
            return ((Transition) obj).mo35clone();
        }
        return null;
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final Object controlDelayedTransition(ViewGroup viewGroup, Object obj) {
        Transition transition = (Transition) obj;
        if (TransitionManager.sPendingTransitions.contains(viewGroup) || !viewGroup.isLaidOut() || Build.VERSION.SDK_INT < 34) {
            return null;
        }
        if (transition.isSeekingSupported()) {
            TransitionManager.sPendingTransitions.add(viewGroup);
            Transition mo35clone = transition.mo35clone();
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.addTransition$ar$ds(mo35clone);
            TransitionManager.sceneChangeSetup(viewGroup, transitionSet);
            viewGroup.setTag(R.id.transition_current_scene, null);
            TransitionManager.sceneChangeRunTransition(viewGroup, transitionSet);
            viewGroup.invalidate();
            transitionSet.mSeekController = new Transition.SeekController();
            transitionSet.addListener$ar$ds(transitionSet.mSeekController);
            return transitionSet.mSeekController;
        }
        throw new IllegalArgumentException("The Transition must support seeking.");
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final boolean isSeekingSupported() {
        return true;
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition != null && transition2 != null) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.addTransition$ar$ds(transition);
            transitionSet.addTransition$ar$ds(transition2);
            transitionSet.setOrdering$ar$ds(1);
            transition = transitionSet;
        } else if (transition == null) {
            if (transition2 != null) {
                transition = transition2;
            } else {
                transition = null;
            }
        }
        if (transition3 != null) {
            TransitionSet transitionSet2 = new TransitionSet();
            if (transition != null) {
                transitionSet2.addTransition$ar$ds(transition);
            }
            transitionSet2.addTransition$ar$ds(transition3);
            return transitionSet2;
        }
        return transition;
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final Object mergeTransitionsTogether$ar$ds(Object obj, Object obj2) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.addTransition$ar$ds((Transition) obj);
        }
        transitionSet.addTransition$ar$ds((Transition) obj2);
        return transitionSet;
    }

    public final void replaceTargets(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        int size;
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int transitionCount = transitionSet.getTransitionCount();
            while (i < transitionCount) {
                replaceTargets(transitionSet.getTransitionAt(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (!hasSimpleTarget(transition)) {
            ArrayList arrayList3 = transition.mTargets;
            if (arrayList3.size() == arrayList.size() && arrayList3.containsAll(arrayList)) {
                if (arrayList2 == null) {
                    size = 0;
                } else {
                    size = arrayList2.size();
                }
                while (i < size) {
                    transition.addTarget$ar$ds$f83a504d_0((View) arrayList2.get(i));
                    i++;
                }
                int size2 = arrayList.size();
                while (true) {
                    size2--;
                    if (size2 >= 0) {
                        transition.removeTarget$ar$ds((View) arrayList.get(size2));
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void scheduleHideFragmentView(Object obj, final View view, final ArrayList arrayList) {
        ((Transition) obj).addListener$ar$ds(new Transition.TransitionListener() { // from class: androidx.transition.FragmentTransitionSupport.2
            @Override // androidx.transition.Transition.TransitionListener
            public final /* synthetic */ void onTransitionEnd(Transition transition, boolean z) {
                onTransitionEnd(transition);
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final /* synthetic */ void onTransitionStart(Transition transition, boolean z) {
                onTransitionStart(transition);
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionEnd(Transition transition) {
                transition.removeListener$ar$ds(this);
                view.setVisibility(8);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((View) arrayList.get(i)).setVisibility(0);
                }
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionStart(Transition transition) {
                transition.removeListener$ar$ds(this);
                transition.addListener$ar$ds(this);
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionPause$ar$ds() {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionResume$ar$ds() {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionCancel(Transition transition) {
            }
        });
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void scheduleRemoveTargets$ar$ds(Object obj, final Object obj2, final ArrayList arrayList, final Object obj3, final ArrayList arrayList2) {
        ((Transition) obj).addListener$ar$ds(new TransitionListenerAdapter() { // from class: androidx.transition.FragmentTransitionSupport.3
            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public final void onTransitionEnd(Transition transition) {
                transition.removeListener$ar$ds(this);
            }

            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public final void onTransitionStart(Transition transition) {
                Object obj4 = obj2;
                if (obj4 != null) {
                    FragmentTransitionSupport.this.replaceTargets(obj4, arrayList, null);
                }
                Object obj5 = obj3;
                if (obj5 != null) {
                    FragmentTransitionSupport.this.replaceTargets(obj5, arrayList2, null);
                }
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        if (r8 > 0) goto L28;
     */
    @Override // android.support.v4.app.FragmentTransitionImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setCurrentPlayTime(java.lang.Object r11, float r12) {
        /*
            r10 = this;
            androidx.transition.Transition$SeekController r11 = (androidx.transition.Transition.SeekController) r11
            boolean r0 = r11.mIsReady
            if (r0 == 0) goto L6c
            long r0 = r11.getDurationMillis()
            float r0 = (float) r0
            float r12 = r12 * r0
            long r0 = (long) r12
            r2 = 0
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r4 = 1
            if (r12 != 0) goto L16
            r0 = r4
        L16:
            long r6 = r11.getDurationMillis()
            int r12 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            r6 = -1
            if (r12 != 0) goto L25
            long r0 = r11.getDurationMillis()
            long r0 = r0 + r6
        L25:
            androidx.dynamicanimation.animation.SpringAnimation r12 = r11.mSpringAnimation
            if (r12 != 0) goto L64
            long r8 = r11.mCurrentPlayTime
            int r12 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r12 == 0) goto L6c
            boolean r12 = r11.mIsCanceled
            if (r12 != 0) goto L59
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r12 != 0) goto L3c
            int r12 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r12 <= 0) goto L3d
            goto L4d
        L3c:
            r2 = r0
        L3d:
            long r0 = r11.getDurationMillis()
            int r12 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r12 != 0) goto L4c
            int r12 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r12 >= 0) goto L4c
            long r6 = r0 + r4
            goto L4d
        L4c:
            r6 = r2
        L4d:
            int r12 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r12 == 0) goto L58
            androidx.transition.Transition r12 = androidx.transition.Transition.this
            r12.setCurrentPlayTimeMillis(r6, r8)
            r11.mCurrentPlayTime = r6
        L58:
            r0 = r6
        L59:
            com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener$PendingMessage r11 = r11.mVelocityTracker$ar$class_merging$ar$class_merging
            long r2 = android.view.animation.AnimationUtils.currentAnimationTimeMillis()
            float r12 = (float) r0
            r11.addDataPoint(r2, r12)
            return
        L64:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "setCurrentPlayTimeMillis() called after animation has been started"
            r11.<init>(r12)
            throw r11
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.FragmentTransitionSupport.setCurrentPlayTime(java.lang.Object, float):void");
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void setEpicenter(Object obj, Rect rect) {
        ((Transition) obj).setEpicenterCallback$ar$class_merging(new AnonymousClass5());
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void setListenerForTransitionEnd(Fragment fragment, Object obj, CancellationSignal cancellationSignal, Runnable runnable) {
        setListenerForTransitionEnd$ar$ds(obj, cancellationSignal, null, runnable);
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void setListenerForTransitionEnd$ar$ds(Object obj, CancellationSignal cancellationSignal, Runnable runnable, final Runnable runnable2) {
        Transition transition = (Transition) obj;
        CallCredentials$RequestInfo callCredentials$RequestInfo = new CallCredentials$RequestInfo(runnable, transition, runnable2);
        synchronized (cancellationSignal) {
            while (cancellationSignal.mCancelInProgress) {
                try {
                    cancellationSignal.wait();
                } catch (InterruptedException unused) {
                }
            }
            if (cancellationSignal.mOnCancelListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != callCredentials$RequestInfo) {
                cancellationSignal.mOnCancelListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = callCredentials$RequestInfo;
                if (cancellationSignal.mIsCanceled) {
                    callCredentials$RequestInfo.onCancel();
                }
            }
        }
        transition.addListener$ar$ds(new Transition.TransitionListener() { // from class: androidx.transition.FragmentTransitionSupport.4
            @Override // androidx.transition.Transition.TransitionListener
            public final /* synthetic */ void onTransitionEnd(Transition transition2, boolean z) {
                onTransitionEnd(transition2);
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionStart(Transition transition2) {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionEnd(Transition transition2) {
                runnable2.run();
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final /* synthetic */ void onTransitionStart(Transition transition2, boolean z) {
                onTransitionStart(transition2);
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionPause$ar$ds() {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionResume$ar$ds() {
            }

            @Override // androidx.transition.Transition.TransitionListener
            public final void onTransitionCancel(Transition transition2) {
            }
        });
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void setSharedElementTargets(Object obj, View view, ArrayList arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        ArrayList arrayList2 = transitionSet.mTargets;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            bfsAddViewChildren(arrayList2, (View) arrayList.get(i));
        }
        arrayList2.add(view);
        arrayList.add(view);
        addTargets(transitionSet, arrayList);
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void swapSharedElementTargets(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.mTargets.clear();
            transitionSet.mTargets.addAll(arrayList2);
            replaceTargets(transitionSet, arrayList, arrayList2);
        }
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final Object wrapTransitionInSet(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition$ar$ds((Transition) obj);
        return transitionSet;
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final boolean isSeekingSupported(Object obj) {
        boolean isSeekingSupported = ((Transition) obj).isSeekingSupported();
        if (!isSeekingSupported) {
            Objects.toString(obj);
        }
        return isSeekingSupported;
    }

    @Override // android.support.v4.app.FragmentTransitionImpl
    public final void setEpicenter(Object obj, View view) {
        if (view != null) {
            getBoundsOnScreen$ar$ds(view, new Rect());
            ((Transition) obj).setEpicenterCallback$ar$class_merging(new AnonymousClass5());
        }
    }
}
