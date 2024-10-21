package com.google.android.accessibility.talkback.menurules;

import android.accessibilityservice.AccessibilityService;
import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.view.WindowId;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.marvin.talkback.R;
import j$.util.DesugarArrays;
import j$.util.function.Predicate$CC;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeMenuRuleCreator {
    public final Object NodeMenuRuleCreator$ar$ruleAction;
    public final Object NodeMenuRuleCreator$ar$ruleGranularity;
    public final Object NodeMenuRuleCreator$ar$ruleImageCaption;
    public final Object NodeMenuRuleCreator$ar$ruleSpannables;
    public final Object NodeMenuRuleCreator$ar$ruleUnlabeledNode;
    public final Object NodeMenuRuleCreator$ar$ruleViewPager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum MenuRules {
        RULE_UNLABELLED(R.id.labeling_breakout_add_label),
        RULE_CUSTOM_ACTION(R.id.custom_action_menu),
        RULE_VIEWPAGER(R.id.viewpager_menu),
        RULE_GRANULARITY(R.id.granularity_menu),
        RULE_SPANNABLES(R.id.links_menu),
        RULE_IMAGE_CAPTION(R.id.image_caption_menu);

        final int ruleId;

        MenuRules(int i) {
            this.ruleId = i;
        }
    }

    public NodeMenuRuleCreator(AccessibilityService accessibilityService, Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, ListMenuManager listMenuManager, SelectorController selectorController, AppLifecycleMonitor appLifecycleMonitor) {
        this.NodeMenuRuleCreator$ar$ruleAction = accessibilityService;
        this.NodeMenuRuleCreator$ar$ruleImageCaption = feedbackReturner;
        this.NodeMenuRuleCreator$ar$ruleGranularity = actorState;
        this.NodeMenuRuleCreator$ar$ruleSpannables = listMenuManager;
        this.NodeMenuRuleCreator$ar$ruleUnlabeledNode = selectorController;
        this.NodeMenuRuleCreator$ar$ruleViewPager = appLifecycleMonitor;
    }

    private final AccessibilityNodeInfoCompat getAccessibilityNodeInfoCompat() {
        AccessibilityNodeInfoCompat findFocusCompat = ((AppLifecycleMonitor) this.NodeMenuRuleCreator$ar$ruleViewPager).findFocusCompat(2);
        if (findFocusCompat == null) {
            return ((AppLifecycleMonitor) this.NodeMenuRuleCreator$ar$ruleViewPager).findFocusCompat(1);
        }
        return findFocusCompat;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final boolean performFocusAction(int i) {
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.FocusDirection.Builder focusDirection = Feedback.focusDirection(i);
        focusDirection.granularity = CursorGranularity.DEFAULT;
        focusDirection.setInputMode$ar$ds(4);
        focusDirection.setWrap$ar$ds(true);
        focusDirection.setScroll$ar$ds(true);
        focusDirection.setDefaultToInputFocus$ar$ds(true);
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, focusDirection);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final boolean performGlobalAction(int i) {
        if (i != 1) {
            ((ListMenuManager) this.NodeMenuRuleCreator$ar$ruleSpannables).dismissAll();
        }
        ?? r0 = this.NodeMenuRuleCreator$ar$ruleImageCaption;
        Logger logger = Performance.DEFAULT_LOGGER;
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r0, (Performance.EventId) null, Feedback.systemAction(i));
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final boolean performSelectedAction(Predicate predicate) {
        boolean test;
        boolean test2;
        if (!((ActorState) this.NodeMenuRuleCreator$ar$ruleGranularity).getDirectionNavigation$ar$class_merging$ar$class_merging$ar$class_merging().isSelectionModeActive()) {
            setSelectionModeOn();
            if (!((ActorState) this.NodeMenuRuleCreator$ar$ruleGranularity).getDirectionNavigation$ar$class_merging$ar$class_merging$ar$class_merging().isSelectionModeActive()) {
                setSelectionModeOn();
            }
            test = predicate.test(null);
            ?? r0 = this.NodeMenuRuleCreator$ar$ruleImageCaption;
            Logger logger = Performance.DEFAULT_LOGGER;
            Feedback.Part.Builder builder = Feedback.Part.builder();
            Feedback.FocusDirection.Builder builder2 = Feedback.FocusDirection.builder();
            builder2.setAction$ar$ds$940a2012_0(Feedback.FocusDirection.Action.SELECTION_MODE_OFF);
            builder.focusDirection = builder2.build();
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r0, (Performance.EventId) null, builder);
            return test;
        }
        test2 = predicate.test(null);
        return test2;
    }

    private final boolean performSelectedEditAction$ar$edu(final int i) {
        return performSelectedAction(new Predicate() { // from class: com.google.android.accessibility.talkback.braille.BrailleHelper$$ExternalSyntheticLambda0
            public final /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public final /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public final /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NodeMenuRuleCreator.this.performEditAction$ar$edu(i);
            }
        });
    }

    private final boolean performSelectedFocusAction(final int i, final CursorGranularity cursorGranularity, final int i2) {
        return performSelectedAction(new Predicate() { // from class: com.google.android.accessibility.talkback.braille.BrailleHelper$$ExternalSyntheticLambda1
            public final /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public final /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public final /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NodeMenuRuleCreator.this.performGranularityFocusAction(i, cursorGranularity, i2);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final void setSelectionModeOn() {
        Logger logger = Performance.DEFAULT_LOGGER;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.selectionModeOn(getAccessibilityNodeInfoCompat()));
    }

    private static final boolean shouldReverseAdjustReadingControl$ar$ds(Context context) {
        SelectorController.Setting currentSetting = SelectorController.getCurrentSetting(context);
        if (currentSetting != SelectorController.Setting.SPEECH_RATE && currentSetting != SelectorController.Setting.SCROLLING_SEQUENTIAL && currentSetting != SelectorController.Setting.CHANGE_ACCESSIBILITY_VOLUME) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    public final int getMaxWindowX() {
        return this.NodeMenuRuleCreator$ar$ruleViewPager.getMaxWindowX();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    public final int getMaxWindowY() {
        return this.NodeMenuRuleCreator$ar$ruleViewPager.getMaxWindowY();
    }

    public final NodeMenuRule getMenuRule(MenuRules menuRules) {
        Object obj;
        if (menuRules == null) {
            return null;
        }
        int ordinal = menuRules.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal != 4) {
                            if (ordinal == 5) {
                                obj = this.NodeMenuRuleCreator$ar$ruleImageCaption;
                            } else {
                                throw new AssertionError("Unsupported Menu Rule.");
                            }
                        } else {
                            obj = this.NodeMenuRuleCreator$ar$ruleSpannables;
                        }
                    } else {
                        obj = this.NodeMenuRuleCreator$ar$ruleGranularity;
                    }
                } else {
                    obj = this.NodeMenuRuleCreator$ar$ruleViewPager;
                }
            } else {
                obj = this.NodeMenuRuleCreator$ar$ruleAction;
            }
        } else {
            obj = this.NodeMenuRuleCreator$ar$ruleUnlabeledNode;
        }
        return (NodeMenuRule) obj;
    }

    public final NodeMenuRule getMenuRuleById(int i) {
        return getMenuRule((MenuRules) DesugarArrays.stream(MenuRules.values()).filter(new NodeMenuRuleCreator$MenuRules$$ExternalSyntheticLambda0(i, 0)).findFirst().orElse(null));
    }

    public final List getNodeMenuByRule$ar$ds(MenuRules menuRules, Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return getMenuRule(menuRules).getMenuItemsForNode(context, accessibilityNodeInfoCompat, true);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    public final void getPixelCoordinates(int[] iArr) {
        this.NodeMenuRuleCreator$ar$ruleViewPager.getPixelCoordinates(iArr);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v10, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v104, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v12, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v125, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v127, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v14, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v16, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v26, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v38, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v50, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v55, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v19, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v27, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final boolean performAction$ar$edu(int i, int i2, Object... objArr) {
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.setInterruptGentle$ar$ds(true);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, builder);
        switch (i - 1) {
            case 0:
                return performFocusAction(1);
            case 1:
                return performFocusAction(2);
            case 2:
                return performFocusAction(6);
            case 3:
                return performFocusAction(5);
            case 4:
                return performGranularityFocusAction(1, CursorGranularity.WINDOWS, i2);
            case 5:
                return performGranularityFocusAction(2, CursorGranularity.WINDOWS, i2);
            case 6:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.focusDirection(Feedback.FocusDirection.Action.NEXT_PAGE));
            case 7:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.focusDirection(Feedback.FocusDirection.Action.PREVIOUS_PAGE));
            case 8:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.focusTop(4));
            case 9:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.focusBottom(4));
            case 10:
                ((SelectorController) this.NodeMenuRuleCreator$ar$ruleUnlabeledNode).adjustSelectedSetting(null, shouldReverseAdjustReadingControl$ar$ds((Context) this.NodeMenuRuleCreator$ar$ruleAction));
                return true;
            case 11:
                ((SelectorController) this.NodeMenuRuleCreator$ar$ruleUnlabeledNode).adjustSelectedSetting(null, !shouldReverseAdjustReadingControl$ar$ds((Context) this.NodeMenuRuleCreator$ar$ruleAction));
                return true;
            case 12:
                if (SelectorController.getCurrentSetting((Context) this.NodeMenuRuleCreator$ar$ruleAction).equals(SelectorController.Setting.ACTIONS)) {
                    ((SelectorController) this.NodeMenuRuleCreator$ar$ruleUnlabeledNode).activateCurrentAction(null);
                    return true;
                }
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.focus(Feedback.Focus.Action.CLICK_CURRENT));
            case 13:
                Object obj = objArr[0];
                if (obj != null && (obj instanceof AccessibilityNodeInfoCompat)) {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                    if (accessibilityNodeInfoCompat.isAccessibilityFocused() && SelectorController.getCurrentSetting((Context) this.NodeMenuRuleCreator$ar$ruleAction).equals(SelectorController.Setting.ACTIONS)) {
                        ((SelectorController) this.NodeMenuRuleCreator$ar$ruleUnlabeledNode).activateCurrentAction(null);
                        return true;
                    }
                    ?? r9 = this.NodeMenuRuleCreator$ar$ruleImageCaption;
                    Feedback.Part.Builder builder2 = Feedback.Part.builder();
                    Feedback.Focus.Builder focus = Feedback.focus(Feedback.Focus.Action.CLICK_NODE);
                    focus.target = accessibilityNodeInfoCompat;
                    builder2.focus = focus.build();
                    return r9.returnFeedback(Feedback.create((Performance.EventId) null, builder2.build()));
                }
                return false;
            case 14:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.focus(Feedback.Focus.Action.LONG_CLICK_CURRENT));
            case 15:
                Object obj2 = objArr[0];
                if (obj2 != null && (obj2 instanceof AccessibilityNodeInfoCompat)) {
                    ?? r92 = this.NodeMenuRuleCreator$ar$ruleImageCaption;
                    Feedback.Part.Builder builder3 = Feedback.Part.builder();
                    Feedback.Focus.Builder focus2 = Feedback.focus(Feedback.Focus.Action.LONG_CLICK_NODE);
                    focus2.target = (AccessibilityNodeInfoCompat) obj2;
                    builder3.focus = focus2.build();
                    return r92.returnFeedback(Feedback.create((Performance.EventId) null, builder3.build()));
                }
                return false;
            case 16:
                ((SelectorController) this.NodeMenuRuleCreator$ar$ruleUnlabeledNode).selectPreviousOrNextSetting$ar$edu(null, 2, false);
                return true;
            case 17:
                ((SelectorController) this.NodeMenuRuleCreator$ar$ruleUnlabeledNode).selectPreviousOrNextSetting$ar$edu(null, 2, true);
                return true;
            case 18:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.universalSearch$ar$edu(1));
            case 19:
                return ((ListMenuManager) this.NodeMenuRuleCreator$ar$ruleSpannables).showMenu$ar$edu(1, null);
            case 20:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.speech(Feedback.Speech.Action.TOGGLE_VOICE_FEEDBACK));
            case 21:
                return performGlobalAction(2);
            case 22:
                return performGlobalAction(1);
            case 23:
                return performGlobalAction(3);
            case 24:
                return performGlobalAction(4);
            case 25:
                return performGlobalAction(5);
            case 26:
                return performGlobalAction(14);
            case 27:
                return performGranularityFocusAction(1, CursorGranularity.WEB_HEADING, i2);
            case 28:
                return performGranularityFocusAction(2, CursorGranularity.WEB_HEADING, i2);
            case 29:
                return performGranularityFocusAction(1, CursorGranularity.WEB_CONTROL, i2);
            case 30:
                return performGranularityFocusAction(2, CursorGranularity.WEB_CONTROL, i2);
            case 31:
                return performGranularityFocusAction(1, CursorGranularity.WEB_LINK, i2);
            case 32:
                return performGranularityFocusAction(2, CursorGranularity.WEB_LINK, i2);
            case 33:
                return performGranularityFocusAction(1, CursorGranularity.HEADING, i2);
            case 34:
                return performGranularityFocusAction(2, CursorGranularity.HEADING, i2);
            case 35:
                return performGranularityFocusAction(1, CursorGranularity.CONTROL, i2);
            case 36:
                return performGranularityFocusAction(2, CursorGranularity.CONTROL, i2);
            case 37:
                return performGranularityFocusAction(1, CursorGranularity.LINK, i2);
            case 38:
                return performGranularityFocusAction(2, CursorGranularity.LINK, i2);
            case 39:
                Object obj3 = objArr[0];
                if (obj3 != null && (obj3 instanceof AccessibilityNodeInfoCompat)) {
                    return ((AccessibilityNodeInfoCompat) obj3).performAction$ar$ds();
                }
                return false;
            case 40:
                return performGranularityFocusAction(1, CursorGranularity.CHARACTER, i2);
            case 41:
                return performGranularityFocusAction(2, CursorGranularity.CHARACTER, i2);
            case 42:
                return performGranularityFocusAction(1, CursorGranularity.WORD, i2);
            case 43:
                return performGranularityFocusAction(2, CursorGranularity.WORD, i2);
            case 44:
                return performGranularityFocusAction(1, CursorGranularity.LINE, i2);
            case 45:
                return performGranularityFocusAction(2, CursorGranularity.LINE, i2);
            case 46:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.continuousRead$ar$edu(4));
            case 47:
                return performEditAction$ar$edu(5);
            case 48:
                return performEditAction$ar$edu(4);
            case 49:
                return performEditAction$ar$edu(6);
            case 50:
                return performSelectedFocusAction(2, CursorGranularity.CHARACTER, i2);
            case 51:
                return performSelectedFocusAction(1, CursorGranularity.CHARACTER, i2);
            case 52:
                return performSelectedFocusAction(2, CursorGranularity.WORD, i2);
            case 53:
                return performSelectedFocusAction(1, CursorGranularity.WORD, i2);
            case 54:
                return performSelectedFocusAction(2, CursorGranularity.LINE, i2);
            case 55:
                return performSelectedFocusAction(1, CursorGranularity.LINE, i2);
            case 56:
                return performSelectedEditAction$ar$edu(8);
            case 57:
                return performSelectedEditAction$ar$edu(9);
            case 58:
            default:
                ?? r8 = this.NodeMenuRuleCreator$ar$ruleImageCaption;
                Feedback.Part.Builder builder4 = Feedback.Part.builder();
                Feedback.EditText.Builder edit$ar$edu = Feedback.edit$ar$edu((AccessibilityNodeInfoCompat) objArr[0], 11);
                edit$ar$edu.text = (CharSequence) objArr[1];
                edit$ar$edu.spellingSuggestion = (AccessibilityNodeInfoUtils.SpellingSuggestion) objArr[2];
                builder4.edit = edit$ar$edu.build();
                return r8.returnFeedback(Feedback.create((Performance.EventId) null, builder4.build()));
            case 59:
                return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.systemAction(10));
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final boolean performEditAction$ar$edu(int i) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = getAccessibilityNodeInfoCompat();
        if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.isEditable()) {
            ?? r0 = this.NodeMenuRuleCreator$ar$ruleImageCaption;
            Logger logger = Performance.DEFAULT_LOGGER;
            return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r0, (Performance.EventId) null, Feedback.edit$ar$edu(getAccessibilityNodeInfoCompat(), i));
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v3, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final boolean performGranularityFocusAction(int i, CursorGranularity cursorGranularity, int i2) {
        Logger logger = Performance.DEFAULT_LOGGER;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, Feedback.granularity(cursorGranularity));
        Feedback.FocusDirection.Builder focusDirection = Feedback.focusDirection(i);
        focusDirection.granularity = cursorGranularity;
        focusDirection.setInputMode$ar$ds(i2);
        focusDirection.setToWindow$ar$ds(cursorGranularity.equals(CursorGranularity.WINDOWS));
        focusDirection.setDefaultToInputFocus$ar$ds(true);
        focusDirection.setScroll$ar$ds(true);
        focusDirection.setWrap$ar$ds(true);
        return SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.NodeMenuRuleCreator$ar$ruleImageCaption, (Performance.EventId) null, focusDirection);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    public final void setPixelCoordinates(int i, int i2) {
        this.NodeMenuRuleCreator$ar$ruleViewPager.setPixelCoordinates(i, i2);
        this.NodeMenuRuleCreator$ar$ruleAction.setPixelCoordinates(i - SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext((Context) this.NodeMenuRuleCreator$ar$ruleGranularity).getResources().getDimensionPixelSize(R.dimen.offset_between_collapsed_panel_overlay_and_trigger_button_overlay), i2);
        this.NodeMenuRuleCreator$ar$ruleImageCaption.setPixelCoordinates(i - SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext((Context) this.NodeMenuRuleCreator$ar$ruleGranularity).getResources().getDimensionPixelSize(R.dimen.offset_between_expanded_control_panel_overlay_and_trigger_button_overlay), i2);
    }

    public NodeMenuRuleCreator(Context context, SelectToSpeakOverlay selectToSpeakOverlay, SelectToSpeakOverlay selectToSpeakOverlay2, SelectToSpeakOverlay selectToSpeakOverlay3) {
        this.NodeMenuRuleCreator$ar$ruleUnlabeledNode = new int[2];
        this.NodeMenuRuleCreator$ar$ruleSpannables = new float[2];
        this.NodeMenuRuleCreator$ar$ruleGranularity = context;
        this.NodeMenuRuleCreator$ar$ruleViewPager = selectToSpeakOverlay;
        this.NodeMenuRuleCreator$ar$ruleAction = selectToSpeakOverlay2;
        this.NodeMenuRuleCreator$ar$ruleImageCaption = selectToSpeakOverlay3;
    }

    public NodeMenuRuleCreator(View view, String str, Transition transition, WindowId windowId, TransitionValues transitionValues, Animator animator) {
        this.NodeMenuRuleCreator$ar$ruleAction = view;
        this.NodeMenuRuleCreator$ar$ruleUnlabeledNode = str;
        this.NodeMenuRuleCreator$ar$ruleSpannables = transitionValues;
        this.NodeMenuRuleCreator$ar$ruleImageCaption = windowId;
        this.NodeMenuRuleCreator$ar$ruleViewPager = transition;
        this.NodeMenuRuleCreator$ar$ruleGranularity = animator;
    }

    public NodeMenuRuleCreator(Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, AccessibilityFocusMonitor accessibilityFocusMonitor, TalkBackAnalytics talkBackAnalytics) {
        this.NodeMenuRuleCreator$ar$ruleUnlabeledNode = new RuleUnlabeledNode(feedbackReturner, actorState, talkBackAnalytics);
        this.NodeMenuRuleCreator$ar$ruleAction = new RuleAction(feedbackReturner, actorState, accessibilityFocusMonitor, talkBackAnalytics);
        this.NodeMenuRuleCreator$ar$ruleViewPager = new RuleViewPager(feedbackReturner, talkBackAnalytics);
        this.NodeMenuRuleCreator$ar$ruleGranularity = new RuleGranularity(feedbackReturner, actorState, talkBackAnalytics);
        this.NodeMenuRuleCreator$ar$ruleSpannables = new RuleSpannables(talkBackAnalytics);
        this.NodeMenuRuleCreator$ar$ruleImageCaption = new RuleImageCaption(feedbackReturner, actorState, talkBackAnalytics);
    }
}
