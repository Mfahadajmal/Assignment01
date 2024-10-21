package android.support.v4.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.os.BundleCompat$Api33Impl;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedDispatcher;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import androidx.work.impl.model.WorkName;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpecialEffectsController {
    public static final BundleCompat$Api33Impl Companion$ar$class_merging$ar$class_merging = new BundleCompat$Api33Impl();
    public final ViewGroup container;
    public boolean isContainerPostponed;
    public boolean operationDirectionIsPop;
    public final List pendingOperations;
    public final List runningOperations;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FragmentStateManagerOperation extends Operation {
        private final FragmentStateManager fragmentStateManager;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public FragmentStateManagerOperation(int r2, int r3, android.support.v4.app.FragmentStateManager r4) {
            /*
                r1 = this;
                android.support.v4.app.Fragment r0 = r4.mFragment
                r0.getClass()
                r1.<init>(r2, r3, r0)
                r1.fragmentStateManager = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.SpecialEffectsController.FragmentStateManagerOperation.<init>(int, int, android.support.v4.app.FragmentStateManager):void");
        }

        @Override // android.support.v4.app.SpecialEffectsController.Operation
        public final void complete$fragment_release() {
            super.complete$fragment_release();
            this.fragment.mTransitioning = false;
            this.fragmentStateManager.moveToExpectedState();
        }

        @Override // android.support.v4.app.SpecialEffectsController.Operation
        public final void onStart() {
            if (!this.isStarted) {
                super.onStart();
                if (this.lifecycleImpact$ar$edu == 2) {
                    Fragment fragment = this.fragmentStateManager.mFragment;
                    fragment.getClass();
                    View findFocus = fragment.mView.findFocus();
                    if (findFocus != null) {
                        fragment.setFocusedView(findFocus);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(findFocus);
                            Objects.toString(fragment);
                        }
                    }
                    View requireView = this.fragment.requireView();
                    if (requireView.getParent() == null) {
                        this.fragmentStateManager.addViewToContainer();
                        requireView.setAlpha(0.0f);
                    }
                    if (requireView.getAlpha() == 0.0f && requireView.getVisibility() == 0) {
                        requireView.setVisibility(4);
                    }
                    requireView.setAlpha(fragment.getPostOnViewCreatedAlpha());
                    return;
                }
                if (this.lifecycleImpact$ar$edu == 3) {
                    Fragment fragment2 = this.fragmentStateManager.mFragment;
                    fragment2.getClass();
                    View requireView2 = fragment2.requireView();
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Objects.toString(requireView2.findFocus());
                        Objects.toString(requireView2);
                        Objects.toString(fragment2);
                    }
                    requireView2.clearFocus();
                }
            }
        }
    }

    public SpecialEffectsController(ViewGroup viewGroup, byte[] bArr) {
        this(viewGroup);
    }

    private final void findNamedViews(Map map, View view) {
        String transitionName = ViewCompat.Api21Impl.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    childAt.getClass();
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    public static final SpecialEffectsController getOrCreateController(ViewGroup viewGroup, FragmentManager fragmentManager) {
        viewGroup.getClass();
        BundleCompat$Api33Impl specialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging = fragmentManager.getSpecialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging();
        specialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging.getClass();
        return BundleCompat$Api33Impl.getOrCreateController$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(viewGroup, specialEffectsControllerFactory$ar$class_merging$ar$class_merging$ar$class_merging);
    }

    private static void retainMatchingViews$ar$ds(ArrayMap arrayMap, Collection collection) {
        OnDeviceLanguageIdentificationLogEvent.filterInPlace$CollectionsKt__MutableCollectionsKt$ar$ds(arrayMap.entrySet(), new OnBackPressedDispatcher.AnonymousClass1(collection, 1), false);
    }

    private final void updateFinalState() {
        for (Operation operation : this.pendingOperations) {
            if (operation.lifecycleImpact$ar$edu == 2) {
                operation.mergeWith$ar$edu(BundleCompat$Api33Impl.from$ar$edu$ar$ds(operation.fragment.requireView().getVisibility()), 1);
            }
        }
    }

    public final void applyContainerChangesToOperation$fragment_release(Operation operation) {
        operation.getClass();
        if (operation.isAwaitingContainerChanges) {
            int i = operation.finalState$ar$edu;
            Fragment fragment = operation.fragment;
            Operation.State.applyState$ar$edu(i, fragment.requireView(), this.container);
            operation.setAwaitingContainerChanges$ar$ds();
        }
    }

    public final void collectEffects(List list, boolean z) {
        Object obj;
        Object obj2;
        boolean z2;
        boolean z3;
        ArrayList<DefaultSpecialEffectsController$AnimationInfo> arrayList;
        FragmentTransitionImpl fragmentTransitionImpl;
        ArrayList arrayList2;
        Pair pair;
        boolean z4;
        boolean z5 = z;
        Iterator it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Operation operation = (Operation) obj;
                Fragment fragment = operation.fragment;
                BundleCompat$Api33Impl bundleCompat$Api33Impl = Operation.State.Companion$ar$class_merging$243e65ba_0;
                View view = fragment.mView;
                view.getClass();
                if (bundleCompat$Api33Impl.asOperationState$ar$edu(view) == 2 && operation.finalState$ar$edu != 2) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Operation operation2 = (Operation) obj;
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                obj2 = listIterator.previous();
                Operation operation3 = (Operation) obj2;
                Fragment fragment2 = operation3.fragment;
                BundleCompat$Api33Impl bundleCompat$Api33Impl2 = Operation.State.Companion$ar$class_merging$243e65ba_0;
                View view2 = fragment2.mView;
                view2.getClass();
                if (bundleCompat$Api33Impl2.asOperationState$ar$edu(view2) != 2 && operation3.finalState$ar$edu == 2) {
                    break;
                }
            } else {
                obj2 = null;
                break;
            }
        }
        Operation operation4 = (Operation) obj2;
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(operation2);
            Objects.toString(operation4);
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Fragment fragment3 = ((Operation) OnDeviceLanguageIdentificationLogEvent.last(list)).fragment;
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Fragment.AnimationInfo animationInfo = ((Operation) it2.next()).fragment.mAnimationInfo;
            Fragment.AnimationInfo animationInfo2 = fragment3.mAnimationInfo;
            animationInfo.mEnterAnim = animationInfo2.mEnterAnim;
            animationInfo.mExitAnim = animationInfo2.mExitAnim;
            animationInfo.mPopEnterAnim = animationInfo2.mPopEnterAnim;
            animationInfo.mPopExitAnim = animationInfo2.mPopExitAnim;
        }
        Iterator it3 = list.iterator();
        while (true) {
            z2 = false;
            if (!it3.hasNext()) {
                break;
            }
            Operation operation5 = (Operation) it3.next();
            arrayList3.add(new DefaultSpecialEffectsController$AnimationInfo(operation5, z5));
            if (!z5 ? operation5 == operation4 : operation5 == operation2) {
                z4 = true;
            } else {
                z4 = false;
            }
            arrayList4.add(new DefaultSpecialEffectsController$TransitionInfo(operation5, z5, z4));
            operation5.addCompletionListener(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(this, operation5, 1));
        }
        ArrayList arrayList5 = new ArrayList();
        for (Object obj3 : arrayList4) {
            if (!((DefaultSpecialEffectsController$TransitionInfo) obj3).isVisibilityUnchanged()) {
                arrayList5.add(obj3);
            }
        }
        ArrayList<DefaultSpecialEffectsController$TransitionInfo> arrayList6 = new ArrayList();
        for (Object obj4 : arrayList5) {
            if (((DefaultSpecialEffectsController$TransitionInfo) obj4).getHandlingImpl() != null) {
                arrayList6.add(obj4);
            }
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (DefaultSpecialEffectsController$TransitionInfo defaultSpecialEffectsController$TransitionInfo : arrayList6) {
            FragmentTransitionImpl handlingImpl = defaultSpecialEffectsController$TransitionInfo.getHandlingImpl();
            if (fragmentTransitionImpl2 != null && handlingImpl != fragmentTransitionImpl2) {
                throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + defaultSpecialEffectsController$TransitionInfo.operation.fragment + " returned Transition " + defaultSpecialEffectsController$TransitionInfo.transition + " which uses a different Transition type than other Fragments.");
            }
            fragmentTransitionImpl2 = handlingImpl;
        }
        if (fragmentTransitionImpl2 == null) {
            z3 = true;
            arrayList = arrayList3;
        } else {
            ArrayList arrayList7 = new ArrayList();
            ArrayList arrayList8 = new ArrayList();
            ArrayMap arrayMap = new ArrayMap();
            ArrayList<String> arrayList9 = new ArrayList<>();
            ArrayList arrayList10 = new ArrayList();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayMap arrayMap3 = new ArrayMap();
            ArrayList<String> arrayList11 = arrayList9;
            ArrayList arrayList12 = arrayList10;
            Object obj5 = null;
            for (DefaultSpecialEffectsController$TransitionInfo defaultSpecialEffectsController$TransitionInfo2 : arrayList6) {
                if (defaultSpecialEffectsController$TransitionInfo2.hasSharedElementTransition() && operation2 != null && operation4 != null) {
                    obj5 = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(defaultSpecialEffectsController$TransitionInfo2.sharedElementTransition));
                    ArrayList sharedElementSourceNames = operation4.fragment.getSharedElementSourceNames();
                    sharedElementSourceNames.getClass();
                    ArrayList<String> sharedElementSourceNames2 = operation2.fragment.getSharedElementSourceNames();
                    sharedElementSourceNames2.getClass();
                    ArrayList<String> sharedElementTargetNames = operation2.fragment.getSharedElementTargetNames();
                    sharedElementTargetNames.getClass();
                    int size = sharedElementTargetNames.size();
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    arrayList2 = arrayList3;
                    int i = 0;
                    while (i < size) {
                        int i2 = size;
                        int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                        if (indexOf != -1) {
                            sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i));
                        }
                        i++;
                        size = i2;
                    }
                    ArrayList<String> sharedElementTargetNames2 = operation4.fragment.getSharedElementTargetNames();
                    sharedElementTargetNames2.getClass();
                    if (!z5) {
                        pair = new Pair(operation2.fragment.getExitTransitionCallback(), operation4.fragment.getEnterTransitionCallback());
                    } else {
                        pair = new Pair(operation2.fragment.getEnterTransitionCallback(), operation4.fragment.getExitTransitionCallback());
                    }
                    Object obj6 = pair.second;
                    SharedElementCallback sharedElementCallback = (SharedElementCallback) pair.first;
                    SharedElementCallback sharedElementCallback2 = (SharedElementCallback) obj6;
                    int i3 = 0;
                    for (int size2 = sharedElementSourceNames.size(); i3 < size2; size2 = size2) {
                        Object obj7 = sharedElementSourceNames.get(i3);
                        obj7.getClass();
                        String str = (String) obj7;
                        String str2 = sharedElementTargetNames2.get(i3);
                        str2.getClass();
                        arrayMap.put(str, str2);
                        i3++;
                    }
                    if (FragmentManager.isLoggingEnabled(2)) {
                        int size3 = sharedElementTargetNames2.size();
                        for (int i4 = 0; i4 < size3; i4++) {
                            sharedElementTargetNames2.get(i4);
                        }
                        int size4 = sharedElementSourceNames.size();
                        for (int i5 = 0; i5 < size4; i5++) {
                        }
                    }
                    View view3 = operation2.fragment.mView;
                    view3.getClass();
                    findNamedViews(arrayMap2, view3);
                    arrayMap2.retainAll(sharedElementSourceNames);
                    if (sharedElementCallback != null) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(operation2);
                        }
                        throw null;
                    }
                    arrayMap.retainAll(arrayMap2.keySet());
                    View view4 = operation4.fragment.mView;
                    view4.getClass();
                    findNamedViews(arrayMap3, view4);
                    arrayMap3.retainAll(sharedElementTargetNames2);
                    arrayMap3.retainAll(arrayMap.values());
                    if (sharedElementCallback2 != null) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(operation4);
                        }
                        throw null;
                    }
                    FragmentTransition fragmentTransition = FragmentTransition.INSTANCE;
                    for (int i6 = arrayMap.size - 1; i6 >= 0; i6--) {
                        if (!arrayMap3.containsKey((String) arrayMap.valueAt(i6))) {
                            arrayMap.removeAt(i6);
                        }
                    }
                    retainMatchingViews$ar$ds(arrayMap2, arrayMap.keySet());
                    retainMatchingViews$ar$ds(arrayMap3, arrayMap.values());
                    if (arrayMap.isEmpty()) {
                        Objects.toString(obj5);
                        Objects.toString(operation2);
                        Objects.toString(operation4);
                        arrayList7.clear();
                        arrayList8.clear();
                        z5 = z;
                        arrayList12 = sharedElementSourceNames;
                        arrayList11 = sharedElementTargetNames2;
                        obj5 = null;
                    } else {
                        z5 = z;
                        arrayList12 = sharedElementSourceNames;
                        arrayList11 = sharedElementTargetNames2;
                    }
                } else {
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    arrayList2 = arrayList3;
                    z5 = z;
                }
                arrayList3 = arrayList2;
                fragmentTransitionImpl2 = fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
            ArrayList arrayList13 = arrayList3;
            if (obj5 == null) {
                if (!arrayList6.isEmpty()) {
                    Iterator it4 = arrayList6.iterator();
                    while (it4.hasNext()) {
                        if (((DefaultSpecialEffectsController$TransitionInfo) it4.next()).transition != null) {
                        }
                    }
                }
                arrayList = arrayList13;
                z3 = true;
            }
            z3 = true;
            arrayList = arrayList13;
            DefaultSpecialEffectsController$TransitionEffect defaultSpecialEffectsController$TransitionEffect = new DefaultSpecialEffectsController$TransitionEffect(arrayList6, operation2, operation4, fragmentTransitionImpl3, obj5, arrayList7, arrayList8, arrayMap, arrayList11, arrayList12, arrayMap2, arrayMap3, z);
            Iterator it5 = arrayList6.iterator();
            while (it5.hasNext()) {
                ((DefaultSpecialEffectsController$TransitionInfo) it5.next()).operation.addEffect(defaultSpecialEffectsController$TransitionEffect);
            }
        }
        ArrayList<DefaultSpecialEffectsController$AnimationInfo> arrayList14 = new ArrayList();
        ArrayList arrayList15 = new ArrayList();
        Iterator it6 = arrayList.iterator();
        while (it6.hasNext()) {
            OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList15, ((DefaultSpecialEffectsController$AnimationInfo) it6.next()).operation.effects);
        }
        boolean z6 = !arrayList15.isEmpty();
        for (DefaultSpecialEffectsController$AnimationInfo defaultSpecialEffectsController$AnimationInfo : arrayList) {
            ViewGroup viewGroup = this.container;
            Operation operation6 = defaultSpecialEffectsController$AnimationInfo.operation;
            Context context = viewGroup.getContext();
            context.getClass();
            WorkName animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = defaultSpecialEffectsController$AnimationInfo.getAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(context);
            if (animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                if (animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkName$ar$name == null) {
                    arrayList14.add(defaultSpecialEffectsController$AnimationInfo);
                } else {
                    Fragment fragment4 = operation6.fragment;
                    if (!operation6.effects.isEmpty()) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(fragment4);
                        }
                    } else {
                        if (operation6.finalState$ar$edu == 3) {
                            operation6.setAwaitingContainerChanges$ar$ds();
                        }
                        operation6.addEffect(new DefaultSpecialEffectsController$AnimatorEffect(defaultSpecialEffectsController$AnimationInfo));
                        z2 = z3;
                    }
                }
            }
        }
        for (DefaultSpecialEffectsController$AnimationInfo defaultSpecialEffectsController$AnimationInfo2 : arrayList14) {
            Operation operation7 = defaultSpecialEffectsController$AnimationInfo2.operation;
            Fragment fragment5 = operation7.fragment;
            if (z6) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(fragment5);
                }
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(fragment5);
                }
            } else {
                operation7.addEffect(new DefaultSpecialEffectsController$AnimationEffect(defaultSpecialEffectsController$AnimationInfo2));
            }
        }
    }

    public final void commitEffects$fragment_release(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList, ((Operation) it.next()).effects);
        }
        List list2 = OnDeviceLanguageIdentificationLogEvent.toList(OnDeviceLanguageIdentificationLogEvent.toSet(arrayList));
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            ((Effect) list2.get(i)).onCommit(this.container);
        }
        int size2 = list.size();
        for (int i2 = 0; i2 < size2; i2++) {
            applyContainerChangesToOperation$fragment_release((Operation) list.get(i2));
        }
        List list3 = OnDeviceLanguageIdentificationLogEvent.toList(list);
        int size3 = list3.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Operation operation = (Operation) list3.get(i3);
            if (operation.effects.isEmpty()) {
                operation.complete$fragment_release();
            }
        }
    }

    public final void enqueue$ar$edu(int i, int i2, FragmentStateManager fragmentStateManager) {
        synchronized (this.pendingOperations) {
            Fragment fragment = fragmentStateManager.mFragment;
            fragment.getClass();
            Operation findPendingOperation = findPendingOperation(fragment);
            if (findPendingOperation == null) {
                Fragment fragment2 = fragmentStateManager.mFragment;
                if (fragment2.mTransitioning) {
                    fragment2.getClass();
                    findPendingOperation = findRunningOperation(fragment2);
                } else {
                    findPendingOperation = null;
                }
            }
            if (findPendingOperation != null) {
                findPendingOperation.mergeWith$ar$edu(i, i2);
                return;
            }
            FragmentStateManagerOperation fragmentStateManagerOperation = new FragmentStateManagerOperation(i, i2, fragmentStateManager);
            this.pendingOperations.add(fragmentStateManagerOperation);
            fragmentStateManagerOperation.addCompletionListener(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(this, fragmentStateManagerOperation, 4));
            fragmentStateManagerOperation.addCompletionListener(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(this, fragmentStateManagerOperation, 5));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x011f A[Catch: all -> 0x0142, TryCatch #0 {, blocks: (B:12:0x0017, B:14:0x0020, B:15:0x002f, B:17:0x0035, B:19:0x0041, B:20:0x0044, B:23:0x004d, B:30:0x0053, B:31:0x0062, B:33:0x0068, B:35:0x0074, B:36:0x0077, B:39:0x0084, B:44:0x008a, B:48:0x009b, B:49:0x00b7, B:51:0x00bd, B:53:0x00cb, B:57:0x00ec, B:58:0x00d5, B:59:0x00d9, B:61:0x00df, B:69:0x00f4, B:71:0x00f8, B:72:0x0101, B:74:0x0107, B:76:0x0113, B:79:0x011b, B:81:0x011f, B:82:0x013e, B:84:0x0128, B:86:0x0132), top: B:11:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void executePendingOperations() {
        /*
            Method dump skipped, instructions count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.SpecialEffectsController.executePendingOperations():void");
    }

    public final Operation findPendingOperation(Fragment fragment) {
        Object obj;
        Iterator it = this.pendingOperations.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Operation operation = (Operation) obj;
                if (Intrinsics.areEqual(operation.fragment, fragment) && !operation.isCanceled) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        return (Operation) obj;
    }

    public final Operation findRunningOperation(Fragment fragment) {
        Object obj;
        Iterator it = this.runningOperations.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Operation operation = (Operation) obj;
                if (Intrinsics.areEqual(operation.fragment, fragment) && !operation.isCanceled) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        return (Operation) obj;
    }

    public final void forceCompleteAllOperations() {
        List list = this.pendingOperations;
        boolean isAttachedToWindow = this.container.isAttachedToWindow();
        synchronized (list) {
            updateFinalState();
            processStart(this.pendingOperations);
            for (Operation operation : OnDeviceLanguageIdentificationLogEvent.toMutableList((Collection) this.runningOperations)) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    if (!isAttachedToWindow) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Container ");
                        sb.append(this.container);
                        sb.append(" is not attached to window. ");
                    }
                    Objects.toString(operation);
                }
                operation.cancel(this.container);
            }
            for (Operation operation2 : OnDeviceLanguageIdentificationLogEvent.toMutableList((Collection) this.pendingOperations)) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    if (!isAttachedToWindow) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Container ");
                        sb2.append(this.container);
                        sb2.append(" is not attached to window. ");
                    }
                    Objects.toString(operation2);
                }
                operation2.cancel(this.container);
            }
        }
    }

    public final void markPostponedState() {
        Fragment fragment;
        Object obj;
        boolean z;
        synchronized (this.pendingOperations) {
            updateFinalState();
            List list = this.pendingOperations;
            ListIterator listIterator = list.listIterator(list.size());
            while (true) {
                fragment = null;
                if (listIterator.hasPrevious()) {
                    obj = listIterator.previous();
                    Operation operation = (Operation) obj;
                    BundleCompat$Api33Impl bundleCompat$Api33Impl = Operation.State.Companion$ar$class_merging$243e65ba_0;
                    View view = operation.fragment.mView;
                    view.getClass();
                    int asOperationState$ar$edu = bundleCompat$Api33Impl.asOperationState$ar$edu(view);
                    if (operation.finalState$ar$edu == 2 && asOperationState$ar$edu != 2) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            Operation operation2 = (Operation) obj;
            if (operation2 != null) {
                fragment = operation2.fragment;
            }
            if (fragment != null) {
                z = fragment.isPostponed();
            } else {
                z = false;
            }
            this.isContainerPostponed = z;
        }
    }

    public final void processStart(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((Operation) list.get(i)).onStart();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OnDeviceLanguageIdentificationLogEvent.addAll$ar$ds$2b82a983_0(arrayList, ((Operation) it.next()).effects);
        }
        List list2 = OnDeviceLanguageIdentificationLogEvent.toList(OnDeviceLanguageIdentificationLogEvent.toSet(arrayList));
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Effect effect = (Effect) list2.get(i2);
            ViewGroup viewGroup = this.container;
            if (!effect.isStarted) {
                effect.onStart(viewGroup);
            }
            effect.isStarted = true;
        }
    }

    public SpecialEffectsController(ViewGroup viewGroup) {
        this.container = viewGroup;
        this.pendingOperations = new ArrayList();
        this.runningOperations = new ArrayList();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Operation {
        private final List _effects;
        public final List effects;
        public int finalState$ar$edu;
        public final Fragment fragment;
        public boolean isCanceled;
        public boolean isComplete;
        public boolean isSeeking;
        public boolean isStarted;
        public int lifecycleImpact$ar$edu;
        private final List completionListeners = new ArrayList();
        public boolean isAwaitingContainerChanges = true;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class State {
            public static final BundleCompat$Api33Impl Companion$ar$class_merging$243e65ba_0 = new BundleCompat$Api33Impl();

            public static void applyState$ar$edu(int i, View view, ViewGroup viewGroup) {
                int i2 = i - 1;
                ViewGroup viewGroup2 = null;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Objects.toString(view);
                            }
                            view.setVisibility(4);
                            return;
                        } else {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Objects.toString(view);
                            }
                            view.setVisibility(8);
                            return;
                        }
                    }
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Objects.toString(view);
                    }
                    ViewParent parent = view.getParent();
                    if (parent instanceof ViewGroup) {
                        viewGroup2 = (ViewGroup) parent;
                    }
                    if (viewGroup2 == null) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(view);
                            Objects.toString(viewGroup);
                        }
                        viewGroup.addView(view);
                    }
                    view.setVisibility(0);
                    return;
                }
                ViewParent parent2 = view.getParent();
                if (parent2 instanceof ViewGroup) {
                    viewGroup2 = (ViewGroup) parent2;
                }
                if (viewGroup2 != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Objects.toString(view);
                        Objects.toString(viewGroup2);
                    }
                    viewGroup2.removeView(view);
                }
            }

            public static /* synthetic */ String toStringGenerated247055cdec681852(int i) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return "null";
                            }
                            return "INVISIBLE";
                        }
                        return "GONE";
                    }
                    return "VISIBLE";
                }
                return "REMOVED";
            }
        }

        public Operation(int i, int i2, Fragment fragment) {
            this.finalState$ar$edu = i;
            this.lifecycleImpact$ar$edu = i2;
            this.fragment = fragment;
            ArrayList arrayList = new ArrayList();
            this._effects = arrayList;
            this.effects = arrayList;
        }

        public final void addCompletionListener(Runnable runnable) {
            this.completionListeners.add(runnable);
        }

        public final void addEffect(Effect effect) {
            this._effects.add(effect);
        }

        public final void cancel(ViewGroup viewGroup) {
            this.isStarted = false;
            if (this.isCanceled) {
                return;
            }
            this.isCanceled = true;
            if (this._effects.isEmpty()) {
                complete$fragment_release();
                return;
            }
            for (Effect effect : OnDeviceLanguageIdentificationLogEvent.toList(this.effects)) {
                if (!effect.isCancelled) {
                    effect.onCancel(viewGroup);
                }
                effect.isCancelled = true;
            }
        }

        public void complete$fragment_release() {
            this.isStarted = false;
            if (!this.isComplete) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    toString();
                }
                this.isComplete = true;
                Iterator it = this.completionListeners.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
            }
        }

        public final void completeEffect(Effect effect) {
            if (this._effects.remove(effect) && this._effects.isEmpty()) {
                complete$fragment_release();
            }
        }

        public final void mergeWith$ar$edu(int i, int i2) {
            int i3 = i2 - 1;
            if (i3 != 1) {
                if (i3 != 2) {
                    if (this.finalState$ar$edu != 1) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(this.fragment);
                            Objects.toString(State.toStringGenerated247055cdec681852(this.finalState$ar$edu));
                            Objects.toString(State.toStringGenerated247055cdec681852(i));
                        }
                        this.finalState$ar$edu = i;
                        return;
                    }
                    return;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(this.fragment);
                    Objects.toString(State.toStringGenerated247055cdec681852(this.finalState$ar$edu));
                    Objects.toString(BundleCompat$Api33Impl.toStringGeneratedc4bc3d545aab1bc8(this.lifecycleImpact$ar$edu));
                }
                this.finalState$ar$edu = 1;
                this.lifecycleImpact$ar$edu = 3;
                this.isAwaitingContainerChanges = true;
                return;
            }
            if (this.finalState$ar$edu == 1) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(this.fragment);
                    Objects.toString(BundleCompat$Api33Impl.toStringGeneratedc4bc3d545aab1bc8(this.lifecycleImpact$ar$edu));
                }
                this.finalState$ar$edu = 2;
                this.lifecycleImpact$ar$edu = 2;
                this.isAwaitingContainerChanges = true;
            }
        }

        public void onStart() {
            this.isStarted = true;
        }

        public final void setAwaitingContainerChanges$ar$ds() {
            this.isAwaitingContainerChanges = false;
        }

        public final String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {finalState = " + ((Object) State.toStringGenerated247055cdec681852(this.finalState$ar$edu)) + " lifecycleImpact = " + ((Object) BundleCompat$Api33Impl.toStringGeneratedc4bc3d545aab1bc8(this.lifecycleImpact$ar$edu)) + " fragment = " + this.fragment + '}';
        }

        public final void cancel(ViewGroup viewGroup, boolean z) {
            if (this.isCanceled) {
                return;
            }
            if (z) {
                this.isSeeking = true;
            }
            cancel(viewGroup);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Effect {
        public boolean isCancelled;
        public boolean isStarted;

        public boolean isSeekingSupported() {
            return false;
        }

        public void onCancel(ViewGroup viewGroup) {
        }

        public void onCommit(ViewGroup viewGroup) {
        }

        public void onProgress$ar$ds(BackEventCompat backEventCompat) {
        }

        public void onStart(ViewGroup viewGroup) {
        }
    }
}
