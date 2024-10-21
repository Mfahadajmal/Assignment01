package com.google.android.accessibility.selecttospeak;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.PopupWindow;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.selecttospeak.logging.S2sHaTsActivity;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakJobModel;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.keyboard.TalkBackKeymapChangesActivity;
import com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment;
import com.google.android.accessibility.talkback.trainingcommon.NavigationButtonBar;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.okhttp.internal.OptionalMethod;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class UIManager$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ Object UIManager$$ExternalSyntheticLambda2$ar$f$0;
    private final /* synthetic */ int switching_field;

    public UIManager$$ExternalSyntheticLambda2(CollapsibleControlPanel collapsibleControlPanel, int i, byte[] bArr) {
        this.switching_field = i;
        this.UIManager$$ExternalSyntheticLambda2$ar$f$0 = collapsibleControlPanel;
    }

    /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    @Override // android.view.View.OnClickListener
    public final void onClick(final View view) {
        int i = 3;
        switch (this.switching_field) {
            case 0:
                UIManager uIManager = (UIManager) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                if (uIManager.isUIStable()) {
                    if (uIManager.controlOverlays.isCollapsed()) {
                        uIManager.expandControlPanel();
                        OptionalMethod optionalMethod = uIManager.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
                        if (optionalMethod != null) {
                            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_CLICK_TO_EXPAND$ar$edu);
                            return;
                        }
                        return;
                    }
                    uIManager.collapseControlPanel();
                    OptionalMethod optionalMethod2 = uIManager.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (optionalMethod2 != null) {
                        optionalMethod2.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_CLICK_TO_COLLAPSE$ar$edu);
                        return;
                    }
                    return;
                }
                return;
            case 1:
                TutorialView.Intro intro = (TutorialView.Intro) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                intro.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSwitchToNextInputMethod();
                intro.this$0.switchState$ar$edu$ar$ds(1);
                return;
            case 2:
                ((S2sHaTsActivity) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).cleanUpAndFinish();
                return;
            case 3:
                PopupWindow popupWindow = ((SelectToSpeakPopupActivity) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).popupWindow;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    return;
                }
                return;
            case 4:
                SelectToSpeakJobModel.finish$default$ar$ds(((SelectToSpeakPopupActivity) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).getJobModel());
                return;
            case 5:
                SelectToSpeakJobModel jobModel = ((SelectToSpeakPopupActivity) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).getJobModel();
                if (Intrinsics.areEqual(jobModel.isPaused().getValue(), true)) {
                    jobModel.resume();
                    return;
                } else {
                    jobModel.pause();
                    return;
                }
            case 6:
                SelectToSpeakJobModel.finish$default$ar$ds(((SelectToSpeakPopupActivity) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).getJobModel());
                return;
            case 7:
                Object obj = this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                SelectToSpeakPopupActivity selectToSpeakPopupActivity = (SelectToSpeakPopupActivity) obj;
                if (selectToSpeakPopupActivity.popupWindow == null) {
                    PlusMinusButtons plusMinusButtons = null;
                    View inflate = selectToSpeakPopupActivity.getLayoutInflater().inflate(R.layout.layout_contextual_popup_settings_dialog, (ViewGroup) null);
                    inflate.getClass();
                    ViewGroup viewGroup = (ViewGroup) inflate;
                    PlusMinusButtons plusMinusButtons2 = selectToSpeakPopupActivity.textSizeButtons;
                    if (plusMinusButtons2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("textSizeButtons");
                        plusMinusButtons2 = null;
                    }
                    plusMinusButtons2.setup(viewGroup);
                    PlusMinusButtons plusMinusButtons3 = selectToSpeakPopupActivity.speedButtons;
                    if (plusMinusButtons3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("speedButtons");
                    } else {
                        plusMinusButtons = plusMinusButtons3;
                    }
                    plusMinusButtons.setup(viewGroup);
                    ((Button) viewGroup.findViewById(R.id.settings_done)).setOnClickListener(new UIManager$$ExternalSyntheticLambda2(obj, i));
                    selectToSpeakPopupActivity.popupWindow = new PopupWindow(viewGroup, -2, -2);
                }
                PopupWindow popupWindow2 = selectToSpeakPopupActivity.popupWindow;
                if (popupWindow2 != null) {
                    popupWindow2.showAtLocation(view, 8388691, 0, 0);
                    return;
                }
                return;
            case 8:
                int id = view.getId();
                Object obj2 = this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                if (id == R.id.reduce_speed_button) {
                    i = 1;
                } else if (id == R.id.increase_speed_button) {
                    i = 2;
                } else if (id != R.id.previous_item_button) {
                    if (id == R.id.next_item_button) {
                        i = 4;
                    } else if (id == R.id.play_pause_button) {
                        if (((CollapsibleControlPanel) obj2).isShowingPlayButton()) {
                            i = 6;
                        } else {
                            i = 7;
                        }
                    } else if (id == R.id.setting_button) {
                        i = 8;
                    } else {
                        i = 9;
                    }
                }
                if (i != 9) {
                    for (RetryingNameResolver.ResolutionResultListener resolutionResultListener : ((CollapsibleControlPanel) obj2).listeners) {
                        if (resolutionResultListener != null) {
                            resolutionResultListener.onAction(i);
                        }
                    }
                    return;
                }
                return;
            case 9:
                CollapsibleControlPanel collapsibleControlPanel = (CollapsibleControlPanel) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                if (collapsibleControlPanel.isCollapsed) {
                    collapsibleControlPanel.collapseExpandAnimator.cancel();
                    collapsibleControlPanel.collapseExpandAnimator.prepareForExpandAnimation$ar$ds();
                    collapsibleControlPanel.collapseExpandAnimator.start();
                    return;
                }
                collapsibleControlPanel.collapse$ar$ds$57f5e804_0(true);
                return;
            case 10:
                PlusMinusButtons plusMinusButtons4 = (PlusMinusButtons) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                plusMinusButtons4.PlusMinusButtons$ar$onPlus.invoke();
                plusMinusButtons4.updateUI();
                return;
            case 11:
                PlusMinusButtons plusMinusButtons5 = (PlusMinusButtons) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                plusMinusButtons5.PlusMinusButtons$ar$onMinus.invoke();
                plusMinusButtons5.updateUI();
                return;
            case 12:
                ((SearchScreenOverlay) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).keywordEditText.getText().clear();
                return;
            case 13:
                ((SearchScreenOverlay) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).hide();
                return;
            case 14:
                SearchScreenOverlay searchScreenOverlay = (SearchScreenOverlay) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                searchScreenOverlay.hideImeAndPerformAction(new SearchScreenOverlay$$ExternalSyntheticLambda1(searchScreenOverlay, 0));
                return;
            case 15:
                SearchScreenOverlay searchScreenOverlay2 = (SearchScreenOverlay) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                searchScreenOverlay2.hideImeAndPerformAction(new SearchScreenOverlay$$ExternalSyntheticLambda1(searchScreenOverlay2, 2));
                return;
            case 16:
                final SearchScreenOverlay searchScreenOverlay3 = (SearchScreenOverlay) this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                searchScreenOverlay3.searchResultList.setClickable(false);
                searchScreenOverlay3.hideImeAndPerformAction(new SearchScreenOverlay.Action() { // from class: com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay$$ExternalSyntheticLambda5
                    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.List, java.lang.Object] */
                    @Override // com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay.Action
                    public final void act() {
                        final SearchScreenOverlay searchScreenOverlay4 = SearchScreenOverlay.this;
                        final AccessibilityNode accessibilityNode = (AccessibilityNode) ((WindowTrackerFactory) searchScreenOverlay4.searchState$ar$class_merging$7099764c_0.ApplicationModule$ar$application.get(searchScreenOverlay4.searchResultList.getChildLayoutPosition(view))).WindowTrackerFactory$ar$handlerProvider;
                        if (WebInterfaceUtils.isWebContainer(accessibilityNode.getCompat())) {
                            Logger logger = Performance.DEFAULT_LOGGER;
                            SpannableUtils$IdentifierSpan.performAction(accessibilityNode.getCompat(), AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN.getId(), null);
                        }
                        Pipeline.FeedbackReturner feedbackReturner = searchScreenOverlay4.pipeline;
                        Logger logger2 = Performance.DEFAULT_LOGGER;
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.focus(Feedback.Focus.Action.RESTORE_ON_NEXT_WINDOW));
                        searchScreenOverlay4.hide();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() { // from class: com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay.4
                            int counter = 0;
                            final int counterLimit = 20;

                            @Override // java.lang.Runnable
                            public final void run() {
                                AccessibilityNode selfOrMatchingAncestor = accessibilityNode.getSelfOrMatchingAncestor(AccessibilityNodeInfoUtils.FILTER_SHOULD_FOCUS);
                                if (selfOrMatchingAncestor != null) {
                                    Pipeline.FeedbackReturner feedbackReturner2 = searchScreenOverlay4.pipeline;
                                    Logger logger3 = Performance.DEFAULT_LOGGER;
                                    Feedback.Focus.Builder focus = Feedback.focus(Feedback.Focus.Action.CACHE);
                                    focus.target = selfOrMatchingAncestor.getCompat();
                                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, focus);
                                    return;
                                }
                                int i2 = this.counter;
                                if (i2 < 20) {
                                    this.counter = i2 + 1;
                                    handler.postDelayed(this, 50L);
                                    return;
                                }
                                Pipeline.FeedbackReturner feedbackReturner3 = searchScreenOverlay4.pipeline;
                                Logger logger4 = Performance.DEFAULT_LOGGER;
                                Feedback.Focus.Builder focus2 = Feedback.focus(Feedback.Focus.Action.CACHE);
                                focus2.target = accessibilityNode.getCompat();
                                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner3, (Performance.EventId) null, focus2);
                            }
                        }, 50L);
                    }
                });
                return;
            case 17:
                Object obj3 = this.UIManager$$ExternalSyntheticLambda2$ar$f$0;
                Intent intent = new Intent((Context) obj3, (Class<?>) TalkBackPreferencesActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("FragmentName", TalkBackKeyboardShortcutPreferenceFragment.class.getName());
                ((TalkBackKeymapChangesActivity) obj3).startActivity(intent);
                return;
            case 18:
                TrainingActivity trainingActivity = (TrainingActivity) ((WeakReference) ((NavigationButtonBar) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).navigationListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WindowTrackerFactory$ar$handlerProvider).get();
                if (trainingActivity != null && !trainingActivity.pageController$ar$class_merging.previousPage()) {
                    trainingActivity.finishOnAbort(true);
                    return;
                }
                return;
            case 19:
                ((NavigationButtonBar) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).navigationListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onNext();
                return;
            default:
                ((NavigationButtonBar) this.UIManager$$ExternalSyntheticLambda2$ar$f$0).navigationListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onExit();
                return;
        }
    }

    public /* synthetic */ UIManager$$ExternalSyntheticLambda2(Object obj, int i) {
        this.switching_field = i;
        this.UIManager$$ExternalSyntheticLambda2$ar$f$0 = obj;
    }
}
