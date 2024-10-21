package com.google.android.accessibility.talkback.actor.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.TalkBackUIActor$Type;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.AccessibilityWindow;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.marvin.talkback.R;
import java.util.Collections;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SearchScreenOverlay {
    private static final Filter FILTER_NO_SEEK_BAR = new Filter() { // from class: com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay.1
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat != null && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 10) {
                return true;
            }
            return false;
        }
    };
    private ImageButton cancelButton;
    private ImageButton clearInputButton;
    public final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public AccessibilityNode initialFocusedNode;
    public EditText keywordEditText;
    private ImageButton nextScreenButton;
    public SearchScreenOverlayLayout overlayPanel;
    public Pipeline.FeedbackReturner pipeline;
    private ImageButton prevScreenButton;
    public AnonymousClass5 scrollCallback$ar$class_merging;
    public RecyclerView searchResultList;
    ApplicationModule searchState$ar$class_merging$7099764c_0;
    public SearchAdapter searchStateAdapter;
    public final SearchScreenNodeStrategy searchStrategy;
    public final TalkBackService service;
    public AccessibilityWindow initialFocusedWindow = null;
    private boolean ttsOverlayWasOn = false;
    private final Handler hintHandler = new Handler();

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay$5, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass5 {
        public final /* synthetic */ SearchScreenOverlay this$0;
        public final /* synthetic */ int val$action;
        public final /* synthetic */ boolean val$needToUpdateFocus;

        public AnonymousClass5(SearchScreenOverlay searchScreenOverlay, boolean z, int i) {
            this.val$needToUpdateFocus = z;
            this.val$action = i;
            this.this$0 = searchScreenOverlay;
        }

        public final void onAutoScrollFailed$ar$ds() {
            SearchScreenOverlay searchScreenOverlay = this.this$0;
            searchScreenOverlay.searchStrategy.cacheNodeTree(searchScreenOverlay.initialFocusedWindow);
            SearchScreenOverlay searchScreenOverlay2 = this.this$0;
            searchScreenOverlay2.searchStrategy.searchKeyword(searchScreenOverlay2.keywordEditText.getText().toString());
            this.this$0.refreshUiState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Action {
        void act();
    }

    public SearchScreenOverlay(TalkBackService talkBackService, AppLifecycleMonitor appLifecycleMonitor, TalkBackLabelManager talkBackLabelManager) {
        this.service = talkBackService;
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.searchStrategy = new SearchScreenNodeStrategy(this, talkBackLabelManager);
    }

    static Filter getScrollFilter(int i) {
        if (i == 4096) {
            return AccessibilityNodeInfoUtils.FILTER_COULD_SCROLL_FORWARD.and(FILTER_NO_SEEK_BAR);
        }
        if (i == 8192) {
            return AccessibilityNodeInfoUtils.FILTER_COULD_SCROLL_BACKWARD.and(FILTER_NO_SEEK_BAR);
        }
        return null;
    }

    private final AccessibilityNode getScrollableNode(int i) {
        AccessibilityNode selfOrMatchingAncestor;
        AccessibilityNode root;
        AccessibilityNode accessibilityNode = this.initialFocusedNode;
        if (accessibilityNode == null) {
            selfOrMatchingAncestor = null;
        } else {
            selfOrMatchingAncestor = accessibilityNode.getSelfOrMatchingAncestor(getScrollFilter(i));
        }
        if (selfOrMatchingAncestor == null) {
            AccessibilityWindow accessibilityWindow = this.initialFocusedWindow;
            if (accessibilityWindow == null || (root = accessibilityWindow.getRoot()) == null) {
                return null;
            }
            return AccessibilityNode.takeOwnership(AccessibilityNodeInfoUtils.searchFromBfs(root.getCompat(), getScrollFilter(i)));
        }
        return selfOrMatchingAncestor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
    
        return r3 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int scanForNonAlphabetic(java.lang.CharSequence r2, int r3, boolean r4) {
        /*
        L0:
            if (r4 == 0) goto L5
            if (r3 <= 0) goto L38
            goto Lb
        L5:
            int r0 = r2.length()
            if (r3 >= r0) goto L38
        Lb:
            char r0 = r2.charAt(r3)
            boolean r1 = java.lang.Character.isIdeographic(r0)
            if (r1 != 0) goto L34
            java.lang.Character$UnicodeBlock r0 = java.lang.Character.UnicodeBlock.of(r0)
            if (r0 == 0) goto L2c
            java.lang.Character$UnicodeBlock r1 = java.lang.Character.UnicodeBlock.HIRAGANA
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L34
            java.lang.Character$UnicodeBlock r1 = java.lang.Character.UnicodeBlock.KATAKANA
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L2c
            goto L34
        L2c:
            if (r4 == 0) goto L31
            int r3 = r3 + (-1)
            goto L0
        L31:
            int r3 = r3 + 1
            goto L0
        L34:
            if (r4 == 0) goto L38
            int r3 = r3 + 1
        L38:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay.scanForNonAlphabetic(java.lang.CharSequence, int, boolean):int");
    }

    private final void updateButtonState(ImageButton imageButton, int i) {
        if (imageButton != null) {
            if (getScrollableNode(i) == null) {
                if (imageButton.isEnabled()) {
                    imageButton.setEnabled(false);
                    imageButton.setFocusable(false);
                    imageButton.setImageAlpha(80);
                    return;
                }
                return;
            }
            if (!imageButton.isEnabled()) {
                imageButton.setEnabled(true);
                imageButton.setFocusable(true);
                imageButton.setImageAlpha(PrivateKeyType.INVALID);
            }
        }
    }

    public final void clearSearchResult() {
        this.searchResultList.setClickable(false);
        SearchAdapter searchAdapter = this.searchStateAdapter;
        searchAdapter.searchResult = Collections.emptyList();
        searchAdapter.notifyDataSetChanged();
    }

    public final void hide() {
        if (this.ttsOverlayWasOn) {
            TalkBackService talkBackService = this.service;
            SpannableUtils$IdentifierSpan.putBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences(talkBackService), talkBackService.getResources(), R.string.pref_tts_overlay_key, true);
        }
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        if (feedbackReturner != null) {
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.talkBackUI$ar$edu(4, TalkBackUIActor$Type.SELECTOR_MENU_ITEM_OVERLAY_SINGLE_FINGER));
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.talkBackUI$ar$edu(4, TalkBackUIActor$Type.SELECTOR_MENU_ITEM_OVERLAY_MULTI_FINGER));
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.talkBackUI$ar$edu(4, TalkBackUIActor$Type.SELECTOR_ITEM_ACTION_OVERLAY));
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.talkBackUI$ar$edu(4, TalkBackUIActor$Type.GESTURE_ACTION_OVERLAY));
        }
        SearchScreenOverlayLayout searchScreenOverlayLayout = this.overlayPanel;
        if (searchScreenOverlayLayout == null) {
            return;
        }
        searchScreenOverlayLayout.setVisibility(8);
        this.searchStrategy.clearCachedNodes();
        ApplicationModule applicationModule = this.searchState$ar$class_merging$7099764c_0;
        if (applicationModule != null) {
            applicationModule.clear();
        }
        this.initialFocusedNode = null;
    }

    public final void hideImeAndPerformAction(final Action action) {
        if (!((InputMethodManager) this.service.getSystemService("input_method")).hideSoftInputFromWindow(this.keywordEditText.getWindowToken(), 2, new ResultReceiver(new Handler()) { // from class: com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay.3
            @Override // android.os.ResultReceiver
            protected final void onReceiveResult(int i, Bundle bundle) {
                action.act();
            }
        })) {
            action.act();
        }
    }

    public final void performSearch() {
        hideImeAndPerformAction(new SearchScreenOverlay$$ExternalSyntheticLambda1(this, 1));
    }

    public final void refreshUiState() {
        updateButtonState(this.prevScreenButton, 8192);
        updateButtonState(this.nextScreenButton, 4096);
    }

    public final void scrollScreen$ar$ds(int i) {
        if (i != 8192 && i != 4096) {
            return;
        }
        final AccessibilityNode scrollableNode = getScrollableNode(i);
        if (scrollableNode == null) {
            refreshUiState();
            return;
        }
        AccessibilityNode accessibilityNode = this.initialFocusedNode;
        boolean z = false;
        if (accessibilityNode != null) {
            if (AccessibilityNodeInfoUtils.isOrHasMatchingAncestor(accessibilityNode.getCompat(), new Filter() { // from class: com.google.android.accessibility.utils.AccessibilityNode.2
                public AnonymousClass2() {
                }

                @Override // com.google.android.accessibility.utils.Filter
                public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                    return AccessibilityNode.this.equalTo((AccessibilityNodeInfoCompat) obj);
                }
            })) {
                z = true;
            }
        }
        this.scrollCallback$ar$class_merging = new AnonymousClass5(this, z, i);
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        if (feedbackReturner != null) {
            Logger logger = Performance.DEFAULT_LOGGER;
            Feedback.Part.Builder builder = Feedback.Part.builder();
            Feedback.Scroll.Builder builder2 = Feedback.Scroll.builder();
            builder2.setAction$ar$edu$e02d76b2_0$ar$ds(1);
            builder2.node = scrollableNode;
            builder2.setUserAction$ar$ds(1);
            builder2.setNodeAction$ar$ds(i);
            builder2.source = "SEARCH";
            builder.scroll = builder2.build();
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder);
        }
        refreshUiState();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void show() {
        /*
            Method dump skipped, instructions count: 479
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay.show():void");
    }

    public final void speakHint(String str) {
        byte[] bArr = null;
        this.hintHandler.removeCallbacksAndMessages(null);
        if (this.pipeline != null) {
            Feedback.Part.Builder builder = Feedback.Part.builder();
            Feedback.Speech.Builder builder2 = Feedback.Speech.builder();
            builder2.setAction$ar$ds$c7b78277_0(Feedback.Speech.Action.SPEAK);
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            speakOptions.mFlags = 2;
            builder2.hintSpeakOptions = speakOptions;
            builder2.hint = str;
            builder.speech = builder2.build();
            if (this.service.isSsbActiveAndHeadphoneOff()) {
                this.hintHandler.postDelayed(new DelayedWorkTracker.AnonymousClass1(this, str, 19, bArr), 1000L);
                return;
            }
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            feedbackReturner.returnFeedback(Feedback.create((Performance.EventId) null, builder.build()));
        }
    }
}
