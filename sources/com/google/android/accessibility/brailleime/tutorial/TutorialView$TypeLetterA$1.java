package com.google.android.accessibility.brailleime.tutorial;

import _COROUTINE._BOUNDARY;
import android.animation.Animator;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TutorialView$TypeLetterA$1 implements Animator.AnimatorListener {
    public final /* synthetic */ Object TutorialView$TypeLetterA$1$ar$this$1;
    private final /* synthetic */ int switching_field;

    public TutorialView$TypeLetterA$1(Object obj, int i) {
        this.switching_field = i;
        this.TutorialView$TypeLetterA$1$ar$this$1 = obj;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                CollapsibleControlPanel.CollapseExpandAnimator collapseExpandAnimator = (CollapsibleControlPanel.CollapseExpandAnimator) this.TutorialView$TypeLetterA$1$ar$this$1;
                if (collapseExpandAnimator.isExpanding) {
                    CollapsibleControlPanel collapsibleControlPanel = CollapsibleControlPanel.this;
                    collapsibleControlPanel.collapseExpandButton.setContentDescription(collapsibleControlPanel.collapseButtonContentDescription);
                    collapsibleControlPanel.collapseExpandButton.setImageDrawable(collapsibleControlPanel.collapseButtonDrawable);
                    collapsibleControlPanel.isCollapsed = false;
                    return;
                }
                CollapsibleControlPanel collapsibleControlPanel2 = CollapsibleControlPanel.this;
                float floatValue = ((Float) collapsibleControlPanel2.collapseExpandAnimator.getAnimatedValue()).floatValue();
                ContextDataProvider.checkArgument(true);
                if (Math.copySign((-1.0f) + floatValue, 1.0f) <= 1.0E-5f || floatValue == 1.0f || (Float.isNaN(floatValue) && Float.isNaN(1.0f))) {
                    collapsibleControlPanel2.leftCollapsiblePanel.setVisibility(8);
                    collapsibleControlPanel2.rightCollapsiblePanel.setVisibility(8);
                }
                collapsibleControlPanel2.collapseExpandButton.setContentDescription(collapsibleControlPanel2.expandButtonContentDescription);
                collapsibleControlPanel2.collapseExpandButton.setImageDrawable(collapsibleControlPanel2.expandButtonDrawable);
                collapsibleControlPanel2.isCollapsed = true;
                return;
            }
            ((TutorialView.RotateOrientationContinue) this.TutorialView$TypeLetterA$1$ar$this$1).animator.setStartDelay(3000L);
            if (!_BOUNDARY.isRobolectric()) {
                ((TutorialView.RotateOrientationContinue) this.TutorialView$TypeLetterA$1$ar$this$1).animator.start();
                return;
            }
            return;
        }
        ((TutorialView.SwipeDown3Fingers) this.TutorialView$TypeLetterA$1$ar$this$1).this$0.postDelayed(new ContextMenuDialog$$ExternalSyntheticLambda5(this, 13), 1000L);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i = this.switching_field;
        if (i != 0 && i != 1) {
            CollapsibleControlPanel.CollapseExpandAnimator collapseExpandAnimator = (CollapsibleControlPanel.CollapseExpandAnimator) this.TutorialView$TypeLetterA$1$ar$this$1;
            if (collapseExpandAnimator.isExpanding) {
                CollapsibleControlPanel.this.transformInvisibleChildren();
            }
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }
}
