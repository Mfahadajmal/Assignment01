package com.google.android.accessibility.talkback.actor.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.LocaleSpan;
import android.text.style.StyleSpan;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.AccessibilityWindow;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SearchScreenNodeStrategy {
    public final Object SearchScreenNodeStrategy$ar$labelManager;
    public Object SearchScreenNodeStrategy$ar$lastKeyword;
    private final Object SearchScreenNodeStrategy$ar$nodesCache$ar$class_merging;
    private final Object SearchScreenNodeStrategy$ar$observer$ar$class_merging;

    public SearchScreenNodeStrategy(Context context, Object obj, Object obj2) {
        this.SearchScreenNodeStrategy$ar$observer$ar$class_merging = context;
        this.SearchScreenNodeStrategy$ar$labelManager = obj;
        this.SearchScreenNodeStrategy$ar$nodesCache$ar$class_merging = obj2;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final void feedbackNoTypo(Performance.EventId eventId) {
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.SearchScreenNodeStrategy$ar$lastKeyword, eventId, getSpeechFeedbackBuilder$ar$ds(((Context) this.SearchScreenNodeStrategy$ar$observer$ar$class_merging).getString(R.string.hint_no_typo_found), R.raw.complete));
    }

    private static final Feedback.Part.Builder getSpeechFeedbackBuilder$ar$ds(CharSequence charSequence, int i) {
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mQueueMode = 3;
        speakOptions.mFlags = 318;
        return Feedback.speech(charSequence, speakOptions).sound(i).vibration(R.array.typo_pattern);
    }

    public final void cacheNodeTree(AccessibilityWindow accessibilityWindow) {
        clearCachedNodes();
        ((ApplicationModule) this.SearchScreenNodeStrategy$ar$nodesCache$ar$class_merging).cacheCurrentWindow(accessibilityWindow, new Filter() { // from class: com.google.android.accessibility.talkback.actor.search.SearchScreenNodeStrategy.1
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                if (AccessibilityNodeInfoUtils.isVisible(accessibilityNodeInfoCompat) && !TextUtils.isEmpty(TalkBackLabelManager.getNodeText(accessibilityNodeInfoCompat, (TalkBackLabelManager) SearchScreenNodeStrategy.this.SearchScreenNodeStrategy$ar$labelManager))) {
                    return true;
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearCachedNodes() {
        ((ApplicationModule) this.SearchScreenNodeStrategy$ar$nodesCache$ar$class_merging).clearCachedNodes();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x0855, code lost:
    
        if (r5 <= r4) goto L385;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01a3, code lost:
    
        if (r4 != 1048576) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:265:0x061a  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0622  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x020f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0210  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.accessibility.talkback.Feedback mapToFeedback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(com.google.android.accessibility.utils.Performance.EventId r23, android.view.accessibility.AccessibilityEvent r24, com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan r25, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r26) {
        /*
            Method dump skipped, instructions count: 2354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.search.SearchScreenNodeStrategy.mapToFeedback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(com.google.android.accessibility.utils.Performance$EventId, android.view.accessibility.AccessibilityEvent, com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan, androidx.core.view.accessibility.AccessibilityNodeInfoCompat):com.google.android.accessibility.talkback.Feedback");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0114  */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v14, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v2, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean navigate$ar$ds$5d851ca3_0(com.google.android.accessibility.utils.Performance.EventId r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.search.SearchScreenNodeStrategy.navigate$ar$ds$5d851ca3_0(com.google.android.accessibility.utils.Performance$EventId, boolean):boolean");
    }

    public final boolean searchAndFocus(boolean z, CharSequence charSequence, DirectionNavigationActor directionNavigationActor) {
        if (!TextUtils.isEmpty(charSequence)) {
            final String trim = charSequence.toString().trim();
            if (!trim.isEmpty()) {
                this.SearchScreenNodeStrategy$ar$lastKeyword = trim;
                return directionNavigationActor.searchAndFocus(z, new Filter(this) { // from class: com.google.android.accessibility.talkback.actor.search.SearchScreenNodeStrategy.2
                    final /* synthetic */ SearchScreenNodeStrategy this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.google.android.accessibility.utils.Filter
                    public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                        List findMatches;
                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                        if (accessibilityNodeInfoCompat != null && AccessibilityNodeInfoUtils.isVisible(accessibilityNodeInfoCompat)) {
                            CharSequence nodeText = TalkBackLabelManager.getNodeText(accessibilityNodeInfoCompat, (TalkBackLabelManager) this.this$0.SearchScreenNodeStrategy$ar$labelManager);
                            if (!TextUtils.isEmpty(nodeText) && (findMatches = SpannableUtils$NonCopyableTextSpan.findMatches(nodeText.toString(), trim)) != null && !findMatches.isEmpty()) {
                                return true;
                            }
                            return false;
                        }
                        return false;
                    }
                });
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.List, java.lang.Object] */
    public final void searchKeyword(CharSequence charSequence) {
        ApplicationModule applicationModule;
        boolean z;
        String string;
        int i;
        int i2;
        if (TextUtils.isEmpty(charSequence)) {
            applicationModule = new ApplicationModule();
        } else {
            String trim = charSequence.toString().trim();
            if (trim.isEmpty()) {
                applicationModule = new ApplicationModule();
            } else {
                this.SearchScreenNodeStrategy$ar$lastKeyword = trim;
                ApplicationModule applicationModule2 = new ApplicationModule();
                for (AccessibilityNode accessibilityNode : ((ApplicationModule) this.SearchScreenNodeStrategy$ar$nodesCache$ar$class_merging).getCachedNodes()) {
                    List findMatches = SpannableUtils$NonCopyableTextSpan.findMatches(accessibilityNode.getNodeText().toString(), trim);
                    if (!findMatches.isEmpty()) {
                        applicationModule2.ApplicationModule$ar$application.add(new WindowTrackerFactory(accessibilityNode, findMatches));
                    }
                }
                applicationModule = applicationModule2;
            }
        }
        Object obj = this.SearchScreenNodeStrategy$ar$observer$ar$class_merging;
        if (obj != null) {
            SearchScreenOverlay searchScreenOverlay = (SearchScreenOverlay) obj;
            searchScreenOverlay.clearSearchResult();
            ArrayList arrayList = new ArrayList();
            Iterator it = applicationModule.ApplicationModule$ar$application.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                WindowTrackerFactory windowTrackerFactory = (WindowTrackerFactory) it.next();
                if (!windowTrackerFactory.hasMatchedResult()) {
                    it.remove();
                } else {
                    CharSequence charSequence2 = null;
                    charSequence2 = null;
                    if (windowTrackerFactory != null && !TextUtils.isEmpty(windowTrackerFactory.getNodeText())) {
                        charSequence2 = new SpannableStringBuilder(windowTrackerFactory.getNodeText());
                        if (windowTrackerFactory.hasMatchedResult()) {
                            for (Object obj2 : charSequence2.getSpans(0, charSequence2.length(), Object.class)) {
                                if (!(obj2 instanceof LocaleSpan)) {
                                    charSequence2.removeSpan(obj2);
                                }
                            }
                            for (StringMatcher$MatchResult stringMatcher$MatchResult : windowTrackerFactory.matchResults()) {
                                charSequence2.setSpan(new StyleSpan(1), stringMatcher$MatchResult.start, stringMatcher$MatchResult.end, 33);
                            }
                        }
                    }
                    int length = charSequence2.length();
                    CharSequence charSequence3 = charSequence2;
                    charSequence3 = charSequence2;
                    if (length != 0 && windowTrackerFactory != null) {
                        charSequence3 = charSequence2;
                        if (windowTrackerFactory.hasMatchedResult()) {
                            int length2 = charSequence2.length();
                            charSequence3 = charSequence2;
                            if (length2 >= 20) {
                                StringMatcher$MatchResult stringMatcher$MatchResult2 = (StringMatcher$MatchResult) windowTrackerFactory.matchResults().get(0);
                                int i3 = stringMatcher$MatchResult2.start;
                                int i4 = stringMatcher$MatchResult2.end;
                                int i5 = i4 - 1;
                                if (i3 > 20) {
                                    int i6 = i3 - 20;
                                    int lastIndexOf = charSequence2.toString().lastIndexOf(32, i6);
                                    if (lastIndexOf == -1) {
                                        i = SearchScreenOverlay.scanForNonAlphabetic(charSequence2, i6, true);
                                    } else {
                                        i = lastIndexOf + 1;
                                    }
                                } else {
                                    i = 0;
                                }
                                if (charSequence2.length() - i5 < 20) {
                                    i2 = charSequence2.length();
                                } else {
                                    int i7 = i4 + 18;
                                    int indexOf = charSequence2.toString().indexOf(32, i7);
                                    if (indexOf == -1) {
                                        i2 = SearchScreenOverlay.scanForNonAlphabetic(charSequence2.toString(), i7, false);
                                    } else {
                                        i2 = indexOf;
                                    }
                                }
                                charSequence3 = charSequence2.subSequence(i, i2);
                            }
                        }
                    }
                    arrayList.add(charSequence3);
                }
            }
            SearchAdapter searchAdapter = searchScreenOverlay.searchStateAdapter;
            searchAdapter.searchResult = new ArrayList(arrayList);
            searchAdapter.notifyDataSetChanged();
            int size = applicationModule.ApplicationModule$ar$application.size();
            if (size > 0) {
                string = searchScreenOverlay.service.getResources().getQuantityString(R.plurals.msg_matches_found, size, Integer.valueOf(size));
            } else {
                string = searchScreenOverlay.service.getResources().getString(R.string.msg_no_matches);
            }
            searchScreenOverlay.speakHint(string);
            RecyclerView recyclerView = searchScreenOverlay.searchResultList;
            if (size <= 0) {
                z = false;
            }
            recyclerView.setClickable(z);
            ApplicationModule applicationModule3 = searchScreenOverlay.searchState$ar$class_merging$7099764c_0;
            if (applicationModule3 != null) {
                applicationModule3.clear();
            }
            searchScreenOverlay.searchState$ar$class_merging$7099764c_0 = applicationModule;
        }
    }

    public SearchScreenNodeStrategy(SearchScreenOverlay searchScreenOverlay, TalkBackLabelManager talkBackLabelManager) {
        this.SearchScreenNodeStrategy$ar$observer$ar$class_merging = searchScreenOverlay;
        this.SearchScreenNodeStrategy$ar$labelManager = talkBackLabelManager;
        this.SearchScreenNodeStrategy$ar$nodesCache$ar$class_merging = new ApplicationModule((byte[]) null);
    }
}
