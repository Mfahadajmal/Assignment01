package android.support.v4.app;

import android.os.Build;
import android.support.v4.app.SpecialEffectsController;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat$Api21Impl;
import androidx.navigation.fragment.FragmentNavigator$attachClearViewModel$1;
import androidx.work.impl.constraints.NetworkRequestConstraintController$track$1;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$1$$ExternalSyntheticLambda0;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import io.grpc.CallCredentials$RequestInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController$TransitionEffect extends SpecialEffectsController.Effect {
    public Object controller;
    private final ArrayList enteringNames;
    private final ArrayList exitingNames;
    public final SpecialEffectsController.Operation firstOut;
    private final ArrayMap firstOutViews;
    public final boolean isPop;
    public final SpecialEffectsController.Operation lastIn;
    public final ArrayMap lastInViews;
    private final ArrayList sharedElementFirstOutViews;
    private final ArrayList sharedElementLastInViews;
    private final ArrayMap sharedElementNameMapping;
    private final Object sharedElementTransition;
    public final FragmentTransitionImpl transitionImpl;
    public final List transitionInfos;
    private final CancellationSignal transitionSignal;

    public DefaultSpecialEffectsController$TransitionEffect(List list, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, FragmentTransitionImpl fragmentTransitionImpl, Object obj, ArrayList arrayList, ArrayList arrayList2, ArrayMap arrayMap, ArrayList arrayList3, ArrayList arrayList4, ArrayMap arrayMap2, ArrayMap arrayMap3, boolean z) {
        arrayList3.getClass();
        arrayList4.getClass();
        this.transitionInfos = list;
        this.firstOut = operation;
        this.lastIn = operation2;
        this.transitionImpl = fragmentTransitionImpl;
        this.sharedElementTransition = obj;
        this.sharedElementFirstOutViews = arrayList;
        this.sharedElementLastInViews = arrayList2;
        this.sharedElementNameMapping = arrayMap;
        this.enteringNames = arrayList3;
        this.exitingNames = arrayList4;
        this.firstOutViews = arrayMap2;
        this.lastInViews = arrayMap3;
        this.isPop = z;
        this.transitionSignal = new CancellationSignal();
    }

    private final void captureTransitioningViews(ArrayList arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat$Api21Impl.isTransitionGroup(viewGroup)) {
                if (!arrayList.contains(view)) {
                    arrayList.add(view);
                    return;
                }
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    childAt.getClass();
                    captureTransitioningViews(arrayList, childAt);
                }
            }
            return;
        }
        if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.Pair createMergedTransition(android.view.ViewGroup r26, android.support.v4.app.SpecialEffectsController.Operation r27, android.support.v4.app.SpecialEffectsController.Operation r28) {
        /*
            Method dump skipped, instructions count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect.createMergedTransition(android.view.ViewGroup, android.support.v4.app.SpecialEffectsController$Operation, android.support.v4.app.SpecialEffectsController$Operation):kotlin.Pair");
    }

    private final void runTransition(ArrayList arrayList, ViewGroup viewGroup, Function0 function0) {
        FragmentTransition.setViewVisibility(arrayList, 4);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = this.sharedElementLastInViews;
        int size = arrayList3.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayList3.get(i);
            arrayList2.add(ViewCompat.Api21Impl.getTransitionName(view));
            ViewCompat.Api21Impl.setTransitionName(view, null);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            ArrayList arrayList4 = this.sharedElementFirstOutViews;
            int size2 = arrayList4.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Object obj = arrayList4.get(i2);
                obj.getClass();
                View view2 = (View) obj;
                Objects.toString(view2);
                ViewCompat.Api21Impl.getTransitionName(view2);
            }
            ArrayList arrayList5 = this.sharedElementLastInViews;
            int size3 = arrayList5.size();
            for (int i3 = 0; i3 < size3; i3++) {
                Object obj2 = arrayList5.get(i3);
                obj2.getClass();
                View view3 = (View) obj2;
                Objects.toString(view3);
                ViewCompat.Api21Impl.getTransitionName(view3);
            }
        }
        function0.invoke();
        ArrayList arrayList6 = this.sharedElementFirstOutViews;
        ArrayList arrayList7 = this.sharedElementLastInViews;
        ArrayMap arrayMap = this.sharedElementNameMapping;
        int size4 = arrayList7.size();
        ArrayList arrayList8 = new ArrayList();
        for (int i4 = 0; i4 < size4; i4++) {
            View view4 = (View) arrayList6.get(i4);
            String transitionName = ViewCompat.Api21Impl.getTransitionName(view4);
            arrayList8.add(transitionName);
            if (transitionName != null) {
                ViewCompat.Api21Impl.setTransitionName(view4, null);
                String str = (String) arrayMap.get(transitionName);
                int i5 = 0;
                while (true) {
                    if (i5 >= size4) {
                        break;
                    }
                    if (str.equals(arrayList2.get(i5))) {
                        ViewCompat.Api21Impl.setTransitionName((View) arrayList7.get(i5), transitionName);
                        break;
                    }
                    i5++;
                }
            }
        }
        OneShotPreDrawListener.add$ar$ds$ef887652_0(viewGroup, new AiCoreClientImpl$1$$ExternalSyntheticLambda0(size4, arrayList7, arrayList2, arrayList6, arrayList8, 1));
        FragmentTransition.setViewVisibility(arrayList, 0);
        this.transitionImpl.swapSharedElementTargets(this.sharedElementTransition, this.sharedElementFirstOutViews, this.sharedElementLastInViews);
    }

    public final boolean getTransitioning() {
        List list = this.transitionInfos;
        if (list.isEmpty()) {
            return true;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!((DefaultSpecialEffectsController$TransitionInfo) it.next()).operation.fragment.mTransitioning) {
                return false;
            }
        }
        return true;
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final boolean isSeekingSupported() {
        Object obj;
        if (!this.transitionImpl.isSeekingSupported()) {
            return false;
        }
        List<DefaultSpecialEffectsController$TransitionInfo> list = this.transitionInfos;
        if (!list.isEmpty()) {
            for (DefaultSpecialEffectsController$TransitionInfo defaultSpecialEffectsController$TransitionInfo : list) {
                if (Build.VERSION.SDK_INT < 34 || (obj = defaultSpecialEffectsController$TransitionInfo.transition) == null || !this.transitionImpl.isSeekingSupported(obj)) {
                    return false;
                }
            }
        }
        Object obj2 = this.sharedElementTransition;
        if (obj2 != null && !this.transitionImpl.isSeekingSupported(obj2)) {
            return false;
        }
        return true;
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onCancel(ViewGroup viewGroup) {
        CancellationSignal cancellationSignal = this.transitionSignal;
        synchronized (cancellationSignal) {
            if (cancellationSignal.mIsCanceled) {
                return;
            }
            cancellationSignal.mIsCanceled = true;
            cancellationSignal.mCancelInProgress = true;
            CallCredentials$RequestInfo callCredentials$RequestInfo = cancellationSignal.mOnCancelListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (callCredentials$RequestInfo != null) {
                try {
                    callCredentials$RequestInfo.onCancel();
                } catch (Throwable th) {
                    synchronized (cancellationSignal) {
                        cancellationSignal.mCancelInProgress = false;
                        cancellationSignal.notifyAll();
                        throw th;
                    }
                }
            }
            synchronized (cancellationSignal) {
                cancellationSignal.mCancelInProgress = false;
                cancellationSignal.notifyAll();
            }
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onCommit(ViewGroup viewGroup) {
        if (!viewGroup.isLaidOut()) {
            for (DefaultSpecialEffectsController$TransitionInfo defaultSpecialEffectsController$TransitionInfo : this.transitionInfos) {
                SpecialEffectsController.Operation operation = defaultSpecialEffectsController$TransitionInfo.operation;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(viewGroup);
                    Objects.toString(operation);
                }
                defaultSpecialEffectsController$TransitionInfo.operation.completeEffect(this);
            }
            return;
        }
        Object obj = this.controller;
        if (obj != null) {
            this.transitionImpl.animateToEnd(obj);
            if (FragmentManager.isLoggingEnabled(2)) {
                Objects.toString(this.firstOut);
                Objects.toString(this.lastIn);
                return;
            }
            return;
        }
        Pair createMergedTransition = createMergedTransition(viewGroup, this.lastIn, this.firstOut);
        Object obj2 = createMergedTransition.first;
        Object obj3 = createMergedTransition.second;
        List list = this.transitionInfos;
        ArrayList arrayList = (ArrayList) obj2;
        ArrayList<SpecialEffectsController.Operation> arrayList2 = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList2.add(((DefaultSpecialEffectsController$TransitionInfo) it.next()).operation);
        }
        for (SpecialEffectsController.Operation operation2 : arrayList2) {
            this.transitionImpl.setListenerForTransitionEnd(operation2.fragment, obj3, this.transitionSignal, new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(operation2, this, 0));
        }
        runTransition(arrayList, viewGroup, new FragmentNavigator$attachClearViewModel$1(this, viewGroup, obj3, 1));
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(this.firstOut);
            Objects.toString(this.lastIn);
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onProgress$ar$ds(BackEventCompat backEventCompat) {
        Object obj = this.controller;
        if (obj != null) {
            this.transitionImpl.setCurrentPlayTime(obj, backEventCompat.progress);
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onStart(final ViewGroup viewGroup) {
        if (!viewGroup.isLaidOut()) {
            Iterator it = this.transitionInfos.iterator();
            while (it.hasNext()) {
                SpecialEffectsController.Operation operation = ((DefaultSpecialEffectsController$TransitionInfo) it.next()).operation;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(viewGroup);
                    Objects.toString(operation);
                }
            }
            return;
        }
        if (getTransitioning() && this.sharedElementTransition != null && !isSeekingSupported()) {
            Objects.toString(this.sharedElementTransition);
            Objects.toString(this.firstOut);
            Objects.toString(this.lastIn);
        }
        if (isSeekingSupported() && getTransitioning()) {
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Pair createMergedTransition = createMergedTransition(viewGroup, this.lastIn, this.firstOut);
            Object obj = createMergedTransition.first;
            final Object obj2 = createMergedTransition.second;
            List list = this.transitionInfos;
            ArrayList arrayList = (ArrayList) obj;
            ArrayList<SpecialEffectsController.Operation> arrayList2 = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(list));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((DefaultSpecialEffectsController$TransitionInfo) it2.next()).operation);
            }
            for (SpecialEffectsController.Operation operation2 : arrayList2) {
                DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4 = new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4(ref$ObjectRef, 0);
                FragmentTransitionImpl fragmentTransitionImpl = this.transitionImpl;
                Fragment fragment = operation2.fragment;
                fragmentTransitionImpl.setListenerForTransitionEnd$ar$ds(obj2, this.transitionSignal, defaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4, new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(operation2, this, 2));
            }
            runTransition(arrayList, viewGroup, new Function0() { // from class: android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    DefaultSpecialEffectsController$TransitionEffect defaultSpecialEffectsController$TransitionEffect = DefaultSpecialEffectsController$TransitionEffect.this;
                    defaultSpecialEffectsController$TransitionEffect.controller = defaultSpecialEffectsController$TransitionEffect.transitionImpl.controlDelayedTransition(viewGroup, obj2);
                    DefaultSpecialEffectsController$TransitionEffect defaultSpecialEffectsController$TransitionEffect2 = DefaultSpecialEffectsController$TransitionEffect.this;
                    ViewGroup viewGroup2 = viewGroup;
                    if (defaultSpecialEffectsController$TransitionEffect2.controller != null) {
                        ref$ObjectRef.element = new NetworkRequestConstraintController$track$1.AnonymousClass1(defaultSpecialEffectsController$TransitionEffect2, viewGroup2, 1, null);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(DefaultSpecialEffectsController$TransitionEffect.this.firstOut);
                            Objects.toString(DefaultSpecialEffectsController$TransitionEffect.this.lastIn);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("Unable to start transition " + obj2 + " for container " + viewGroup2 + '.');
                }
            });
        }
    }
}
