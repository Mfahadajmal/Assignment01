package com.google.android.accessibility.utils.output;

import android.text.SpannableStringBuilder;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.output.SpeechController;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackItem {
    public boolean flushGlobalTtsQueue;
    public SpeechController.UtteranceCompleteRunnable mCompletedAction;
    public final long mCreationTime;
    public final Performance.EventId mEventId;
    private int mFlags;
    public List mFragments;
    public boolean mIsUninterruptible;
    public HapticPatternParser$$ExternalSyntheticLambda1 mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public int mUtteranceGroup;
    public String mUtteranceId;

    public FeedbackItem(Performance.EventId eventId) {
        this.mUtteranceId = "";
        this.mFragments = new ArrayList();
        this.flushGlobalTtsQueue = true;
        this.mUtteranceGroup = 0;
        this.mCreationTime = System.currentTimeMillis();
        this.mEventId = eventId;
    }

    public final void addFlag(int i) {
        this.mFlags = i | this.mFlags;
    }

    public final void addFragmentAtPosition(FeedbackFragment feedbackFragment, int i) {
        this.mFragments.add(i, feedbackFragment);
    }

    public final CharSequence getAggregateText() {
        if (this.mFragments.isEmpty()) {
            return null;
        }
        if (this.mFragments.size() == 1) {
            return ((FeedbackFragment) this.mFragments.get(0)).mText;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Iterator it = this.mFragments.iterator();
        while (it.hasNext()) {
            StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, true, ((FeedbackFragment) it.next()).mText);
        }
        return spannableStringBuilder;
    }

    public final List getFragments() {
        return DesugarCollections.unmodifiableList(this.mFragments);
    }

    public final boolean hasFlag(int i) {
        if ((this.mFlags & i) == i) {
            return true;
        }
        return false;
    }

    public final void removeFragment$ar$ds(FeedbackFragment feedbackFragment) {
        this.mFragments.remove(feedbackFragment);
    }

    public final String toString() {
        return "{utteranceId:\"" + this.mUtteranceId + "\", fragments:" + String.valueOf(this.mFragments) + ", uninterruptible:" + this.mIsUninterruptible + ", flushGlobalTtsQueue:" + this.flushGlobalTtsQueue + ", flags:" + this.mFlags + ", creationTime:" + this.mCreationTime + "}";
    }

    public FeedbackItem(FeedbackItem feedbackItem) {
        this.mUtteranceId = "";
        this.mFragments = new ArrayList();
        this.flushGlobalTtsQueue = true;
        this.mUtteranceGroup = 0;
        this.mCreationTime = System.currentTimeMillis();
        this.mEventId = feedbackItem.mEventId;
        this.mFlags = feedbackItem.mFlags;
        this.mUtteranceGroup = feedbackItem.mUtteranceGroup;
        this.mCompletedAction = feedbackItem.mCompletedAction;
        this.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = feedbackItem.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        for (FeedbackFragment feedbackFragment : feedbackItem.getFragments()) {
            this.mFragments.add(new FeedbackFragment(feedbackFragment.mText, feedbackFragment.getEarcons(), feedbackFragment.getHaptics(), feedbackFragment.mSpeechParams, feedbackFragment.mNonSpeechParams));
        }
    }
}
