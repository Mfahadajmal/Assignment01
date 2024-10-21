package com.google.android.libraries.phenotype.client.stable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.EditText;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.android.libraries.surveys.internal.view.RatingView;
import com.google.android.libraries.surveys.internal.view.SurveyActivityImpl;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.textfield.ClearTextEndIconDelegate;
import com.google.android.material.textfield.DropdownMenuEndIconDelegate;
import com.google.android.material.textfield.EndCompoundLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.SplitInstallListenerRegistry;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FlagStore$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ Object FlagStore$$ExternalSyntheticLambda3$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FlagStore$$ExternalSyntheticLambda3(Object obj, int i) {
        this.switching_field = i;
        this.FlagStore$$ExternalSyntheticLambda3$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [java.util.concurrent.Future, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v17, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.util.concurrent.Future, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        Rect rect;
        WindowMetrics currentWindowMetrics;
        int i = 0;
        int i2 = 2;
        switch (this.switching_field) {
            case 0:
                Object obj = this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                FlagStore flagStore = (FlagStore) obj;
                if (flagStore.account.equals("")) {
                    return;
                }
                PhenotypeContext phenotypeContext = flagStore.context;
                ListenableFuture updateData = PhenotypeAccountStore.getAccountsStore$ar$class_merging(phenotypeContext).updateData(new PhenotypeAccountStore$$ExternalSyntheticLambda4(flagStore.configPackage, flagStore.account, i), phenotypeContext.getExecutor());
                updateData.addListener(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(obj, updateData, 8), flagStore.context.getExecutor());
                return;
            case 1:
                ((FlagStore) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).commitToSnapshot();
                return;
            case 2:
                throw new RuntimeException(((ExecutionException) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).getCause());
            case 3:
                try {
                    ContextDataProvider.getDone(this.FlagStore$$ExternalSyntheticLambda3$ar$f$0);
                    return;
                } catch (ExecutionException e) {
                    ThreadUtil.postOnMainThread(new FlagStore$$ExternalSyntheticLambda3(e, i2));
                    return;
                }
            case 4:
                if (((Boolean) ((PhenotypeProcessReaper) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).PhenotypeProcessReaper$ar$isKillable.get()).booleanValue()) {
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                    return;
                }
                return;
            case 5:
                try {
                    ContextDataProvider.getDone(this.FlagStore$$ExternalSyntheticLambda3$ar$f$0);
                    return;
                } catch (Exception unused) {
                    return;
                }
            case 6:
                EditText editText = (EditText) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                return;
            case 7:
                RatingView ratingView = (RatingView) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                RatingView.OnRatingClickListener onRatingClickListener = ratingView.onRatingClickListener;
                if (onRatingClickListener != null) {
                    onRatingClickListener.onClickRating(1);
                    ratingView.onRatingClickListener = null;
                    return;
                }
                return;
            case 8:
                RatingView ratingView2 = (RatingView) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                RatingView.OnRatingClickListener onRatingClickListener2 = ratingView2.onRatingClickListener;
                if (onRatingClickListener2 != null) {
                    onRatingClickListener2.onClickRating(2);
                    ratingView2.onRatingClickListener = null;
                    return;
                }
                return;
            case 9:
                SurveyActivityImpl surveyActivityImpl = (SurveyActivityImpl) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                surveyActivityImpl.isSubmitting = true;
                surveyActivityImpl.activity.finish();
                return;
            case 10:
                BottomSheetBehavior.StateSettlingTracker stateSettlingTracker = (BottomSheetBehavior.StateSettlingTracker) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                stateSettlingTracker.isContinueSettlingRunnablePosted = false;
                ViewDragHelper viewDragHelper = BottomSheetBehavior.this.viewDragHelper;
                if (viewDragHelper != null && viewDragHelper.continueSettling$ar$ds()) {
                    BottomSheetBehavior.StateSettlingTracker stateSettlingTracker2 = (BottomSheetBehavior.StateSettlingTracker) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                    stateSettlingTracker2.continueSettlingToState(stateSettlingTracker2.targetState);
                    return;
                }
                BottomSheetBehavior.StateSettlingTracker stateSettlingTracker3 = (BottomSheetBehavior.StateSettlingTracker) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.state == 2) {
                    bottomSheetBehavior.setStateInternal(stateSettlingTracker3.targetState);
                    return;
                }
                return;
            case 11:
                SideSheetBehavior.StateSettlingTracker stateSettlingTracker4 = (SideSheetBehavior.StateSettlingTracker) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                stateSettlingTracker4.isContinueSettlingRunnablePosted = false;
                ViewDragHelper viewDragHelper2 = SideSheetBehavior.this.viewDragHelper;
                if (viewDragHelper2 != null && viewDragHelper2.continueSettling$ar$ds()) {
                    stateSettlingTracker4.continueSettlingToState(stateSettlingTracker4.targetState);
                    return;
                }
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                if (sideSheetBehavior.state == 2) {
                    sideSheetBehavior.setStateInternal(stateSettlingTracker4.targetState);
                    return;
                }
                return;
            case 12:
                BaseTransientBottomBar baseTransientBottomBar = (BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                if (baseTransientBottomBar.view != null && (context = baseTransientBottomBar.context) != null) {
                    WindowManager windowManager = (WindowManager) context.getSystemService("window");
                    if (Build.VERSION.SDK_INT >= 30) {
                        currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                        rect = currentWindowMetrics.getBounds();
                    } else {
                        Display defaultDisplay = windowManager.getDefaultDisplay();
                        Point point = new Point();
                        defaultDisplay.getRealSize(point);
                        rect = new Rect();
                        rect.right = point.x;
                        rect.bottom = point.y;
                    }
                    Object obj2 = this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                    int height = rect.height();
                    int[] iArr = new int[2];
                    BaseTransientBottomBar baseTransientBottomBar2 = (BaseTransientBottomBar) obj2;
                    baseTransientBottomBar2.view.getLocationInWindow(iArr);
                    int height2 = height - (iArr[1] + baseTransientBottomBar2.view.getHeight());
                    int translationY = (int) ((BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).view.getTranslationY();
                    BaseTransientBottomBar baseTransientBottomBar3 = (BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                    int i3 = height2 + translationY;
                    int i4 = baseTransientBottomBar3.extraBottomMarginGestureInset;
                    if (i3 >= i4) {
                        baseTransientBottomBar3.appliedBottomMarginGestureInset = i4;
                        return;
                    }
                    ViewGroup.LayoutParams layoutParams = baseTransientBottomBar3.view.getLayoutParams();
                    if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                        Log.w(BaseTransientBottomBar.TAG, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                        return;
                    }
                    BaseTransientBottomBar baseTransientBottomBar4 = (BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                    baseTransientBottomBar4.appliedBottomMarginGestureInset = baseTransientBottomBar4.extraBottomMarginGestureInset;
                    ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += ((BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).extraBottomMarginGestureInset - i3;
                    ((BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).view.requestLayout();
                    return;
                }
                return;
            case 13:
                ((BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).onViewHidden$ar$ds();
                return;
            case 14:
                BaseTransientBottomBar.SnackbarBaseLayout snackbarBaseLayout = ((BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).view;
                if (snackbarBaseLayout == null) {
                    return;
                }
                if (snackbarBaseLayout.getParent() != null) {
                    ((BaseTransientBottomBar) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).view.setVisibility(0);
                }
                Object obj3 = this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                final BaseTransientBottomBar baseTransientBottomBar5 = (BaseTransientBottomBar) obj3;
                if (baseTransientBottomBar5.view.animationMode == 1) {
                    ValueAnimator alphaAnimator = baseTransientBottomBar5.getAlphaAnimator(0.0f, 1.0f);
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
                    ofFloat.setInterpolator(baseTransientBottomBar5.animationScaleInterpolator);
                    ofFloat.addUpdateListener(new BottomSheetBehavior.AnonymousClass3(obj3, 4));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(alphaAnimator, ofFloat);
                    animatorSet.setDuration(baseTransientBottomBar5.animationFadeInDuration);
                    animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.9
                        public AnonymousClass9() {
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            BaseTransientBottomBar.this.onViewShown();
                        }
                    });
                    animatorSet.start();
                    return;
                }
                final int translationYBottom = baseTransientBottomBar5.getTranslationYBottom();
                baseTransientBottomBar5.view.setTranslationY(translationYBottom);
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setIntValues(translationYBottom, 0);
                valueAnimator.setInterpolator(baseTransientBottomBar5.animationSlideInterpolator);
                valueAnimator.setDuration(baseTransientBottomBar5.animationSlideDuration);
                valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.13
                    public AnonymousClass13() {
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        BaseTransientBottomBar.this.onViewShown();
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator) {
                        BaseTransientBottomBar baseTransientBottomBar6 = BaseTransientBottomBar.this;
                        int i5 = baseTransientBottomBar6.animationSlideDuration;
                        SnackbarContentLayout snackbarContentLayout = baseTransientBottomBar6.contentViewCallback$ar$class_merging;
                        int i6 = baseTransientBottomBar6.animationFadeInDuration;
                        snackbarContentLayout.animateContentIn(i5 - i6, i6);
                    }
                });
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.14
                    final /* synthetic */ BaseTransientBottomBar this$0;
                    final /* synthetic */ int val$translationYBottom;

                    public AnonymousClass14(final BaseTransientBottomBar baseTransientBottomBar52, final int translationYBottom2) {
                        r2 = translationYBottom2;
                        r1 = baseTransientBottomBar52;
                    }

                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                        Handler handler = BaseTransientBottomBar.handler;
                        r1.view.setTranslationY(intValue);
                    }
                });
                valueAnimator.start();
                return;
            case 15:
                ((ClearTextEndIconDelegate) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).animateIcon(true);
                return;
            case 16:
                DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = (DropdownMenuEndIconDelegate) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                boolean isPopupShowing = dropdownMenuEndIconDelegate.autoCompleteTextView.isPopupShowing();
                dropdownMenuEndIconDelegate.setEndIconChecked(isPopupShowing);
                dropdownMenuEndIconDelegate.dropdownPopupDirty = isPopupShowing;
                return;
            case 17:
                ((TextInputLayout) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).editText.requestLayout();
                return;
            case 18:
                EndCompoundLayout endCompoundLayout = ((TextInputLayout) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).endLayout;
                endCompoundLayout.endIconView.performClick();
                endCompoundLayout.endIconView.jumpDrawablesToCurrentState();
                return;
            case 19:
                Object obj4 = this.FlagStore$$ExternalSyntheticLambda3$ar$f$0;
                AtomicReference atomicReference = SplitCompat.installed;
                try {
                    SplitInstallListenerRegistry.getInstance((Context) obj4).setReceiverAlwaysOn$ar$ds();
                    return;
                } catch (SecurityException unused2) {
                    Log.e("SplitCompat", "Failed to set broadcast receiver to always on.");
                    return;
                }
            default:
                try {
                    ((SplitCompat) this.FlagStore$$ExternalSyntheticLambda3$ar$f$0).storage.cleanup();
                    return;
                } catch (Exception e2) {
                    Log.e("SplitCompat", "Failed to cleanup splitcompat storage", e2);
                    return;
                }
        }
    }

    public FlagStore$$ExternalSyntheticLambda3(Object obj, int i, byte[] bArr) {
        this.switching_field = i;
        this.FlagStore$$ExternalSyntheticLambda3$ar$f$0 = obj;
    }
}
