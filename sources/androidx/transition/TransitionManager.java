package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.collection.ArrayMap;
import com.google.android.marvin.talkback.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransitionManager {
    private static final Transition sDefaultTransition = new TransitionSet(null);
    private static final ThreadLocal sRunningTransitions = new ThreadLocal();
    static final ArrayList sPendingTransitions = new ArrayList();

    public static void beginDelayedTransition(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = sPendingTransitions;
        if (!arrayList.contains(viewGroup) && viewGroup.isLaidOut()) {
            arrayList.add(viewGroup);
            if (transition == null) {
                transition = sDefaultTransition;
            }
            Transition mo35clone = transition.mo35clone();
            sceneChangeSetup(viewGroup, mo35clone);
            viewGroup.setTag(R.id.transition_current_scene, null);
            sceneChangeRunTransition(viewGroup, mo35clone);
        }
    }

    static ArrayMap getRunningTransitions() {
        ArrayMap arrayMap;
        ThreadLocal threadLocal = sRunningTransitions;
        WeakReference weakReference = (WeakReference) threadLocal.get();
        if (weakReference != null && (arrayMap = (ArrayMap) weakReference.get()) != null) {
            return arrayMap;
        }
        ArrayMap arrayMap2 = new ArrayMap();
        threadLocal.set(new WeakReference(arrayMap2));
        return arrayMap2;
    }

    public static void sceneChangeRunTransition(ViewGroup viewGroup, Transition transition) {
        if (transition != null && viewGroup != null) {
            MultiListener multiListener = new MultiListener(transition, viewGroup);
            viewGroup.addOnAttachStateChangeListener(multiListener);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(multiListener);
        }
    }

    public static void sceneChangeSetup(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = (ArrayList) getRunningTransitions().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Transition) arrayList.get(i)).pause(viewGroup);
            }
        }
        if (transition != null) {
            transition.captureValues(viewGroup, true);
        }
        if (((ObjectAnimatorUtils$Api21Impl) viewGroup.getTag(R.id.transition_current_scene)) != null) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        final ViewGroup mSceneRoot;
        final Transition mTransition;

        public MultiListener(Transition transition, ViewGroup viewGroup) {
            this.mTransition = transition;
            this.mSceneRoot = viewGroup;
        }

        private final void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        /* JADX WARN: Removed duplicated region for block: B:121:0x01e9 A[EDGE_INSN: B:121:0x01e9->B:122:0x01e9 BREAK  A[LOOP:1: B:17:0x008f->B:28:0x01e0], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:125:0x01ee  */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x020f  */
        /* JADX WARN: Removed duplicated region for block: B:146:0x023b  */
        /* JADX WARN: Removed duplicated region for block: B:196:0x02d3 A[EDGE_INSN: B:196:0x02d3->B:197:0x02d3 BREAK  A[LOOP:8: B:144:0x0237->B:163:0x0237], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:199:0x02e3  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:201:0x02e7  */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean onPreDraw() {
            /*
                Method dump skipped, instructions count: 783
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.transition.TransitionManager.MultiListener.onPreDraw():boolean");
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            removeListeners();
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList arrayList = (ArrayList) TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (arrayList != null && arrayList.size() > 0) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((Transition) arrayList.get(i)).resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }
    }
}
