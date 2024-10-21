package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Application;
import android.graphics.Typeface;
import android.support.v4.app.SpecialEffectsController;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.BatchingListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator$ChangeInfo;
import android.support.v7.widget.DefaultItemAnimator$MoveInfo;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.app.ActivityRecreator;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.loader.content.ModernAsyncTask;
import androidx.work.impl.utils.SerialExecutorImpl;
import com.google.apps.tiktok.tracing.SuffixTree;
import j$.util.DesugarCollections;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ Object DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
    public final /* synthetic */ Object DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
    private final /* synthetic */ int switching_field;

    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(NotificationManagerCompat.Api24Impl api24Impl, Typeface typeface, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0 = api24Impl;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1 = typeface;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r0v30, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v36, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v42, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v59, types: [androidx.core.util.Consumer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v65, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r2v36, types: [android.app.Application$ActivityLifecycleCallbacks, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        final View view;
        boolean z = false;
        switch (this.switching_field) {
            case 0:
                AppLocalesStorageHelper.AppLocalesStorageHelper$ar$MethodMerging((SpecialEffectsController.Operation) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0, (DefaultSpecialEffectsController$TransitionEffect) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1);
                return;
            case 1:
                Object obj = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                obj.getClass();
                ((SpecialEffectsController) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0).applyContainerChangesToOperation$fragment_release((SpecialEffectsController.Operation) obj);
                return;
            case 2:
                AppLocalesStorageHelper.AppLocalesStorageHelper$ar$MethodMerging((SpecialEffectsController.Operation) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0, (DefaultSpecialEffectsController$TransitionEffect) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1);
                return;
            case 3:
                Iterator it = ((DefaultSpecialEffectsController$TransitionEffect) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1).transitionInfos.iterator();
                while (it.hasNext()) {
                    SpecialEffectsController.Operation operation = ((DefaultSpecialEffectsController$TransitionInfo) it.next()).operation;
                    View view2 = operation.fragment.getView();
                    if (view2 != null) {
                        SpecialEffectsController.Operation.State.applyState$ar$edu(operation.finalState$ar$edu, view2, (ViewGroup) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0);
                    }
                }
                return;
            case 4:
                SpecialEffectsController specialEffectsController = (SpecialEffectsController) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                List list = specialEffectsController.pendingOperations;
                Object obj2 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                if (list.contains(obj2)) {
                    SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) obj2;
                    int i3 = operation2.finalState$ar$edu;
                    View view3 = operation2.fragment.mView;
                    view3.getClass();
                    SpecialEffectsController.Operation.State.applyState$ar$edu(i3, view3, specialEffectsController.container);
                    return;
                }
                return;
            case 5:
                SpecialEffectsController specialEffectsController2 = (SpecialEffectsController) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                List list2 = specialEffectsController2.pendingOperations;
                Object obj3 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                list2.remove(obj3);
                specialEffectsController2.runningOperations.remove(obj3);
                return;
            case 6:
                ?? r0 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                Object obj4 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                try {
                    r0.run();
                    return;
                } finally {
                    ((AppCompatDelegate.SerialExecutor) obj4).scheduleNext();
                }
            case 7:
                AsyncListDiffer.AnonymousClass1 anonymousClass1 = (AsyncListDiffer.AnonymousClass1) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                AsyncListDiffer asyncListDiffer = AsyncListDiffer.this;
                if (asyncListDiffer.mMaxScheduledGeneration == anonymousClass1.val$runGeneration) {
                    Object obj5 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                    List list3 = asyncListDiffer.mReadOnlyList;
                    List list4 = anonymousClass1.val$newList;
                    asyncListDiffer.mList = list4;
                    asyncListDiffer.mReadOnlyList = DesugarCollections.unmodifiableList(list4);
                    BatchingListUpdateCallback batchingListUpdateCallback = new BatchingListUpdateCallback(asyncListDiffer.mUpdateCallback);
                    ArrayDeque arrayDeque = new ArrayDeque();
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) obj5;
                    int size = diffResult.mDiagonals.size() - 1;
                    int i4 = diffResult.mOldListSize;
                    int i5 = diffResult.mNewListSize;
                    int i6 = i4;
                    while (size >= 0) {
                        SuffixTree.TandemRepeatRegion tandemRepeatRegion = (SuffixTree.TandemRepeatRegion) diffResult.mDiagonals.get(size);
                        int endX = tandemRepeatRegion.endX();
                        int endY = tandemRepeatRegion.endY();
                        while (i6 > endX) {
                            int i7 = i6 - 1;
                            int i8 = diffResult.mOldItemStatuses[i7];
                            if ((i8 & 12) != 0) {
                                int i9 = i8 >> 4;
                                int i10 = i5;
                                DiffUtil.PostponedUpdate postponedUpdate = DiffUtil.DiffResult.getPostponedUpdate(arrayDeque, i9, z);
                                if (postponedUpdate != null) {
                                    int i11 = (i4 - postponedUpdate.currentPos) - 1;
                                    batchingListUpdateCallback.onMoved(i7, i11);
                                    if ((i8 & 4) != 0) {
                                        diffResult.mCallback.getChangePayload$ar$ds(i7, i9);
                                        batchingListUpdateCallback.onChanged$ar$ds(i11, 1);
                                    }
                                } else {
                                    arrayDeque.add(new DiffUtil.PostponedUpdate(i7, (i4 - i7) - 1, true));
                                }
                                i6 = i7;
                                i5 = i10;
                            } else {
                                int i12 = i5;
                                if (batchingListUpdateCallback.mLastEventType == 2 && (i2 = batchingListUpdateCallback.mLastEventPosition) >= i7 && i2 <= i6) {
                                    batchingListUpdateCallback.mLastEventCount++;
                                    batchingListUpdateCallback.mLastEventPosition = i7;
                                } else {
                                    batchingListUpdateCallback.dispatchLastEvent();
                                    batchingListUpdateCallback.mLastEventPosition = i7;
                                    batchingListUpdateCallback.mLastEventCount = 1;
                                    batchingListUpdateCallback.mLastEventType = 2;
                                }
                                i4--;
                                i6 = i7;
                                i5 = i12;
                                z = false;
                            }
                        }
                        while (i5 > endY) {
                            i5--;
                            int i13 = diffResult.mNewItemStatuses[i5];
                            if ((i13 & 12) != 0) {
                                int i14 = i13 >> 4;
                                if (DiffUtil.DiffResult.getPostponedUpdate(arrayDeque, i14, true) == null) {
                                    arrayDeque.add(new DiffUtil.PostponedUpdate(i5, i4 - i6, false));
                                } else {
                                    batchingListUpdateCallback.onMoved((i4 - r14.currentPos) - 1, i6);
                                    if ((i13 & 4) != 0) {
                                        diffResult.mCallback.getChangePayload$ar$ds(i14, i5);
                                        batchingListUpdateCallback.onChanged$ar$ds(i6, 1);
                                    }
                                }
                            } else {
                                if (batchingListUpdateCallback.mLastEventType == 1 && i6 >= (i = batchingListUpdateCallback.mLastEventPosition)) {
                                    int i15 = batchingListUpdateCallback.mLastEventCount;
                                    if (i6 <= i + i15) {
                                        batchingListUpdateCallback.mLastEventCount = i15 + 1;
                                        batchingListUpdateCallback.mLastEventPosition = Math.min(i6, i);
                                        i4++;
                                    }
                                }
                                batchingListUpdateCallback.dispatchLastEvent();
                                batchingListUpdateCallback.mLastEventPosition = i6;
                                batchingListUpdateCallback.mLastEventCount = 1;
                                batchingListUpdateCallback.mLastEventType = 1;
                                i4++;
                            }
                        }
                        int i16 = tandemRepeatRegion.end;
                        int i17 = tandemRepeatRegion.numSeen;
                        for (int i18 = 0; i18 < tandemRepeatRegion.begin; i18++) {
                            if ((diffResult.mOldItemStatuses[i16] & 15) == 2) {
                                diffResult.mCallback.getChangePayload$ar$ds(i16, i17);
                                batchingListUpdateCallback.onChanged$ar$ds(i16, 1);
                            }
                            i16++;
                            i17++;
                        }
                        i6 = tandemRepeatRegion.end;
                        size--;
                        z = false;
                        i5 = tandemRepeatRegion.numSeen;
                    }
                    batchingListUpdateCallback.dispatchLastEvent();
                    asyncListDiffer.onCurrentListChanged$ar$ds(list3);
                    return;
                }
                return;
            case 8:
                ?? r02 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                int size2 = r02.size();
                for (int i19 = 0; i19 < size2; i19++) {
                    DefaultItemAnimator$MoveInfo defaultItemAnimator$MoveInfo = (DefaultItemAnimator$MoveInfo) r02.get(i19);
                    Object obj6 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                    final RecyclerView.ViewHolder viewHolder = defaultItemAnimator$MoveInfo.holder;
                    int i20 = defaultItemAnimator$MoveInfo.fromX;
                    int i21 = defaultItemAnimator$MoveInfo.fromY;
                    int i22 = defaultItemAnimator$MoveInfo.toX;
                    int i23 = defaultItemAnimator$MoveInfo.toY;
                    final View view4 = viewHolder.itemView;
                    final int i24 = i22 - i20;
                    final int i25 = i23 - i21;
                    if (i24 != 0) {
                        view4.animate().translationX(0.0f);
                    }
                    if (i25 != 0) {
                        view4.animate().translationY(0.0f);
                    }
                    final ViewPropertyAnimator animate = view4.animate();
                    final SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) obj6;
                    simpleItemAnimator.mMoveAnimations.add(viewHolder);
                    animate.setDuration(250L).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.DefaultItemAnimator$6
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            if (i24 != 0) {
                                view4.setTranslationX(0.0f);
                            }
                            if (i25 != 0) {
                                view4.setTranslationY(0.0f);
                            }
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            animate.setListener(null);
                            SimpleItemAnimator.this.dispatchAnimationFinished(viewHolder);
                            SimpleItemAnimator.this.mMoveAnimations.remove(viewHolder);
                            SimpleItemAnimator.this.dispatchFinishedWhenDone();
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator) {
                        }
                    }).start();
                }
                ((ArrayList) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0).clear();
                ((SimpleItemAnimator) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1).mMovesList.remove(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 9:
                ?? r03 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                int size3 = r03.size();
                for (int i26 = 0; i26 < size3; i26++) {
                    final DefaultItemAnimator$ChangeInfo defaultItemAnimator$ChangeInfo = (DefaultItemAnimator$ChangeInfo) r03.get(i26);
                    Object obj7 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                    RecyclerView.ViewHolder viewHolder2 = defaultItemAnimator$ChangeInfo.oldHolder;
                    final View view5 = null;
                    if (viewHolder2 == null) {
                        view = null;
                    } else {
                        view = viewHolder2.itemView;
                    }
                    RecyclerView.ViewHolder viewHolder3 = defaultItemAnimator$ChangeInfo.newHolder;
                    if (viewHolder3 != null) {
                        view5 = viewHolder3.itemView;
                    }
                    if (view != null) {
                        final ViewPropertyAnimator duration = view.animate().setDuration(250L);
                        final SimpleItemAnimator simpleItemAnimator2 = (SimpleItemAnimator) obj7;
                        simpleItemAnimator2.mChangeAnimations.add(defaultItemAnimator$ChangeInfo.oldHolder);
                        duration.translationX(defaultItemAnimator$ChangeInfo.toX - defaultItemAnimator$ChangeInfo.fromX);
                        duration.translationY(defaultItemAnimator$ChangeInfo.toY - defaultItemAnimator$ChangeInfo.fromY);
                        duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.DefaultItemAnimator$7
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator) {
                                duration.setListener(null);
                                view.setAlpha(1.0f);
                                view.setTranslationX(0.0f);
                                view.setTranslationY(0.0f);
                                SimpleItemAnimator.this.dispatchAnimationFinished(defaultItemAnimator$ChangeInfo.oldHolder);
                                SimpleItemAnimator.this.mChangeAnimations.remove(defaultItemAnimator$ChangeInfo.oldHolder);
                                SimpleItemAnimator.this.dispatchFinishedWhenDone();
                            }

                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationStart(Animator animator) {
                                RecyclerView.ViewHolder viewHolder4 = defaultItemAnimator$ChangeInfo.oldHolder;
                            }
                        }).start();
                    }
                    if (view5 != null) {
                        final ViewPropertyAnimator animate2 = view5.animate();
                        final SimpleItemAnimator simpleItemAnimator3 = (SimpleItemAnimator) obj7;
                        simpleItemAnimator3.mChangeAnimations.add(defaultItemAnimator$ChangeInfo.newHolder);
                        animate2.translationX(0.0f).translationY(0.0f).setDuration(250L).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.DefaultItemAnimator$8
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator) {
                                animate2.setListener(null);
                                view5.setAlpha(1.0f);
                                view5.setTranslationX(0.0f);
                                view5.setTranslationY(0.0f);
                                SimpleItemAnimator.this.dispatchAnimationFinished(defaultItemAnimator$ChangeInfo.newHolder);
                                SimpleItemAnimator.this.mChangeAnimations.remove(defaultItemAnimator$ChangeInfo.newHolder);
                                SimpleItemAnimator.this.dispatchFinishedWhenDone();
                            }

                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationStart(Animator animator) {
                                RecyclerView.ViewHolder viewHolder4 = defaultItemAnimator$ChangeInfo.newHolder;
                            }
                        }).start();
                    }
                }
                ((ArrayList) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0).clear();
                ((SimpleItemAnimator) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1).mChangesList.remove(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 10:
                ?? r04 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                int size4 = r04.size();
                for (int i27 = 0; i27 < size4; i27++) {
                    final RecyclerView.ViewHolder viewHolder4 = (RecyclerView.ViewHolder) r04.get(i27);
                    Object obj8 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                    final View view6 = viewHolder4.itemView;
                    final ViewPropertyAnimator animate3 = view6.animate();
                    final SimpleItemAnimator simpleItemAnimator4 = (SimpleItemAnimator) obj8;
                    simpleItemAnimator4.mAddAnimations.add(viewHolder4);
                    animate3.alpha(1.0f).setDuration(120L).setListener(new AnimatorListenerAdapter() { // from class: android.support.v7.widget.DefaultItemAnimator$5
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            view6.setAlpha(1.0f);
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            animate3.setListener(null);
                            SimpleItemAnimator.this.dispatchAnimationFinished(viewHolder4);
                            SimpleItemAnimator.this.mAddAnimations.remove(viewHolder4);
                            SimpleItemAnimator.this.dispatchFinishedWhenDone();
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator) {
                        }
                    }).start();
                }
                ((ArrayList) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0).clear();
                ((SimpleItemAnimator) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1).mAdditionsList.remove(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 11:
                ((ComponentActivity) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1).addObserverForBackInvoker((OnBackPressedDispatcher) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 12:
                ((ActivityRecreator.LifecycleCheckCallbacks) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1).currentlyRecreatingToken = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                return;
            case 13:
                ((Application) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1).unregisterActivityLifecycleCallbacks(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 14:
                try {
                    if (ActivityRecreator.performStopActivity3ParamsMethod != null) {
                        ActivityRecreator.performStopActivity3ParamsMethod.invoke(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1, this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0, false, "AppCompat recreation");
                        return;
                    } else {
                        ActivityRecreator.performStopActivity2ParamsMethod.invoke(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1, this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0, false);
                        return;
                    }
                } catch (RuntimeException e) {
                    if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                        throw e;
                    }
                    return;
                } catch (Throwable th) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                    return;
                }
            case 15:
                ((ResourcesCompat.FontCallback) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0).onFontRetrieved((Typeface) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1);
                return;
            case 16:
                ResourcesCompat.FontCallback fontCallback = ((TypefaceCompat.ResourcesCallbackAdapter) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0).mFontCallback;
                if (fontCallback != null) {
                    fontCallback.onFontRetrieved((Typeface) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1);
                    return;
                }
                return;
            case 17:
                this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1.accept(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0);
                return;
            case 18:
                String concat = "Policy violation with PENALTY_DEATH in ".concat(String.valueOf(this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0));
                Throwable th2 = (Throwable) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                Log.e("FragmentStrictMode", concat, th2);
                throw th2;
            case 19:
                ModernAsyncTask modernAsyncTask = (ModernAsyncTask) this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                boolean isCancelled = modernAsyncTask.isCancelled();
                Object obj9 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0;
                if (isCancelled) {
                    modernAsyncTask.onCancelled(obj9);
                } else {
                    modernAsyncTask.onPostExecute(obj9);
                }
                modernAsyncTask.mStatus$ar$edu = 3;
                return;
            default:
                Object obj10 = this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1;
                try {
                    this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0.run();
                    return;
                } finally {
                    ((SerialExecutorImpl) obj10).scheduleNext();
                }
        }
    }

    public /* synthetic */ DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0 = obj;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1 = obj2;
    }

    public /* synthetic */ DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(Object obj, Object obj2, int i, byte[] bArr) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1 = obj;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0 = obj2;
    }

    public DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(Object obj, Object obj2, int i, char[] cArr) {
        this.switching_field = i;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$1 = obj;
        this.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3$ar$f$0 = obj2;
    }
}
