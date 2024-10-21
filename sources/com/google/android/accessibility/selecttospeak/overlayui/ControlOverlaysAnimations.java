package com.google.android.accessibility.selecttospeak.overlayui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import androidx.activity.OnBackPressedDispatcher;
import androidx.room.SharedSQLiteStatement$stmt$2;
import com.google.android.accessibility.selecttospeak.logging.S2SPipelineAnalytics;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.ui.AnimatorHelper;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import com.google.android.accessibility.selecttospeak.ui.OverlayCoordinatesAnimator;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ControlOverlaysAnimations {
    public final S2SPipelineAnalytics analytics;
    public final MetricsLogger chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging;
    public CollapsibleControlPanel.CollapseExpandAnimator collapseExpandAnimator;
    public AnimatorHelper controlPanelAnimatorHelper;
    public final Function1 foregroundUpdater;
    public final Function2 loadTriggerButtonFractionalCoordinates;
    public OverlayCoordinatesAnimator overlayCoordinatesAnimator;
    public final Function1 triggerButtonAppearanceUpdater;
    public AnimatorHelper triggerButtonsAnimatorHelper;

    public ControlOverlaysAnimations(Function1 function1, Function1 function12, Function2 function2, MetricsLogger metricsLogger, S2SPipelineAnalytics s2SPipelineAnalytics) {
        metricsLogger.getClass();
        s2SPipelineAnalytics.getClass();
        this.foregroundUpdater = function1;
        this.triggerButtonAppearanceUpdater = function12;
        this.loadTriggerButtonFractionalCoordinates = function2;
        this.chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging = metricsLogger;
        this.analytics = s2SPipelineAnalytics;
    }

    public final int getControlPanelAnimatorVisibility() {
        AnimatorHelper animatorHelper = this.controlPanelAnimatorHelper;
        if (animatorHelper != null) {
            return animatorHelper.visibility;
        }
        return 0;
    }

    public final Animator hideControlPanelAnimator(ControlOverlays controlOverlays, boolean z) {
        int i;
        int i2;
        Animator baseAnimator;
        if (getControlPanelAnimatorVisibility() != 0 && (controlOverlays.foregroundOverlayType == ControlOverlays.OverlayTypes.COLLAPSED || controlOverlays.foregroundOverlayType == ControlOverlays.OverlayTypes.EXPANDABLE)) {
            final AnimatorHelper animatorHelper = this.controlPanelAnimatorHelper;
            if (animatorHelper != null) {
                if (true != z) {
                    i = 2;
                } else {
                    i = 1;
                }
                if (animatorHelper.visibility == 0) {
                    LogUtils.d("AnimationHelper", "Cannot animate off screen: invisible.", new Object[0]);
                    baseAnimator = null;
                } else {
                    if (i != 1) {
                        i2 = 2;
                    } else {
                        i2 = i;
                    }
                    animatorHelper.getOffscreenTranslation(new int[2], i2);
                    baseAnimator = animatorHelper.baseAnimator(animatorHelper.pivotView.getTranslationX(), r15[0], animatorHelper.pivotView.getTranslationY(), r15[1], animatorHelper.pivotView.getAlpha(), animatorHelper.pivotView.getAlpha());
                    baseAnimator.setDuration(Math.max(SpannableUtils$IdentifierSpan.scaleValue(Math.abs(r15[0] - animatorHelper.pivotView.getTranslationX()), 0.0f, Math.abs(r15[0]), 0.0f, (float) animatorHelper.durationAnimateOff), SpannableUtils$IdentifierSpan.scaleValue(Math.abs(r15[1] - animatorHelper.pivotView.getTranslationY()), 0.0f, Math.abs(r15[1]), 0.0f, (float) animatorHelper.durationAnimateOff)));
                    baseAnimator.setInterpolator(animatorHelper.interpolatorAnimateOff);
                    baseAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.accessibility.selecttospeak.ui.AnimatorHelper.3
                        public AnonymousClass3() {
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            if (AnimatorHelper.this.isVisible()) {
                                AnimatorHelper.this.visibility = 1;
                                return;
                            }
                            AnimatorHelper.this.setViewVisibility(4);
                            AnimatorHelper.this.setAlpha(0.0f);
                            AnimatorHelper.this.visibility = 0;
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator) {
                            AnimatorHelper.this.visibility = 2;
                        }
                    });
                    LogUtils.d("AnimationHelper", "Animate off screen at %s bound.", AnimatorHelper.directionToString(i2));
                }
                if (baseAnimator != null) {
                    OverlayUiUtil.addCallback$default$ar$ds(OverlayUiUtil.INSTANCE, baseAnimator, null, new OnBackPressedDispatcher.AnonymousClass1(controlOverlays, 19), 1);
                    return baseAnimator;
                }
            }
        }
        return null;
    }

    public final void hideInitialViewsImmediately() {
        AnimatorHelper animatorHelper = this.controlPanelAnimatorHelper;
        if (animatorHelper != null) {
            animatorHelper.hideImmediately();
        }
        AnimatorHelper animatorHelper2 = this.triggerButtonsAnimatorHelper;
        if (animatorHelper2 != null) {
            animatorHelper2.hideImmediately();
        }
    }

    public final Animator hideTriggerButtonAnimator(boolean z) {
        if (z) {
            final AnimatorHelper animatorHelper = this.triggerButtonsAnimatorHelper;
            if ((animatorHelper == null || animatorHelper.visibility != 0) && animatorHelper != null) {
                Animator baseAnimator = animatorHelper.baseAnimator(0.0f, 0.0f, 0.0f, 0.0f, animatorHelper.pivotView.getAlpha(), 0.0f);
                baseAnimator.setDuration(((float) animatorHelper.durationFadeOut) * animatorHelper.pivotView.getAlpha());
                baseAnimator.setInterpolator(animatorHelper.interpolatorFadeOut);
                baseAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.accessibility.selecttospeak.ui.AnimatorHelper.5
                    public AnonymousClass5() {
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        if (AnimatorHelper.this.isVisible()) {
                            AnimatorHelper.this.visibility = 1;
                        } else {
                            AnimatorHelper.this.setViewVisibility(4);
                            AnimatorHelper.this.visibility = 0;
                        }
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator) {
                        AnimatorHelper.this.visibility = 5;
                    }
                });
                LogUtils.d("AnimationHelper", "Fade out.", new Object[0]);
                return baseAnimator;
            }
            return null;
        }
        AnimatorHelper animatorHelper2 = this.triggerButtonsAnimatorHelper;
        if (animatorHelper2 != null && animatorHelper2.visibility == 0) {
            return animatorHelper2.fadeIn();
        }
        if (animatorHelper2 != null) {
            return animatorHelper2.animateToDefaultStateIfOnScreen();
        }
        return null;
    }

    public final Animator showControlPanelAnimator(ControlOverlays controlOverlays, boolean z) {
        int i;
        Animator animator = null;
        if (getControlPanelAnimatorVisibility() == 0) {
            final AnimatorHelper animatorHelper = this.controlPanelAnimatorHelper;
            if (animatorHelper != null) {
                if (true != z) {
                    i = 2;
                } else {
                    i = 1;
                }
                if (i != 1) {
                    i = 2;
                }
                final int[] iArr = new int[2];
                animatorHelper.getOffscreenTranslation(iArr, i);
                Animator baseAnimator = animatorHelper.baseAnimator(iArr[0], 0.0f, iArr[1], 0.0f, 1.0f, 1.0f);
                baseAnimator.setDuration(animatorHelper.durationAnimateOn);
                baseAnimator.setInterpolator(animatorHelper.interpolatorAnimateOn);
                baseAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.accessibility.selecttospeak.ui.AnimatorHelper.2
                    final /* synthetic */ AnimatorHelper this$0;
                    final /* synthetic */ int[] val$offScreenTranslation;

                    public AnonymousClass2(final AnimatorHelper animatorHelper2, final int[] iArr2) {
                        r2 = iArr2;
                        r1 = animatorHelper2;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator2) {
                        r1.visibility = 1;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator2) {
                        AnimatorHelper animatorHelper2 = r1;
                        animatorHelper2.visibility = 3;
                        animatorHelper2.setTranslationX(r2[0]);
                        r1.setTranslationY(r2[1]);
                        r1.setAlpha(1.0f);
                        r1.setViewVisibility(0);
                    }
                });
                LogUtils.d("AnimationHelper", "Animate on screen from %s bound.", AnimatorHelper.directionToString(i));
                if (baseAnimator != null) {
                    OverlayUiUtil.addCallback$default$ar$ds(OverlayUiUtil.INSTANCE, baseAnimator, new SharedSQLiteStatement$stmt$2(controlOverlays, 16), null, 2);
                    return baseAnimator;
                }
            }
        } else {
            if (!controlOverlays.isCollapsed()) {
                CollapsibleControlPanel.CollapseExpandAnimator collapseExpandAnimator = this.collapseExpandAnimator;
                if (collapseExpandAnimator != null) {
                    collapseExpandAnimator.prepareForCollapseAnimation$ar$ds();
                }
                AnimatorHelper animatorHelper2 = this.controlPanelAnimatorHelper;
                if (animatorHelper2 != null) {
                    animator = animatorHelper2.animateToDefaultStateIfOnScreen();
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(this.collapseExpandAnimator, animator);
                return animatorSet;
            }
            AnimatorHelper animatorHelper3 = this.controlPanelAnimatorHelper;
            if (animatorHelper3 != null) {
                return animatorHelper3.animateToDefaultStateIfOnScreen();
            }
        }
        return null;
    }
}
