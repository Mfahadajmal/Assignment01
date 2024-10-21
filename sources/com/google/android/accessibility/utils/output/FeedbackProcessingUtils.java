package com.google.android.accessibility.utils.output;

import android.speech.tts.TextToSpeech;
import android.text.SpannableString;
import android.text.Spanned;
import com.google.apps.tiktok.tracing.SuffixTree;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackProcessingUtils {
    public static final int MAX_UTTERANCE_LENGTH = TextToSpeech.getMaxSpeechInputLength();
    public static final Pattern CHUNK_DELIMITER = Pattern.compile("\n");
    public static boolean aggressiveChunking = false;

    public static void copyFragmentMetadata(FeedbackFragment feedbackFragment, FeedbackFragment feedbackFragment2) {
        feedbackFragment2.mSpeechParams = feedbackFragment.mSpeechParams;
        feedbackFragment2.mNonSpeechParams = feedbackFragment.mNonSpeechParams;
        Iterator it = feedbackFragment.getEarcons().iterator();
        while (it.hasNext()) {
            feedbackFragment2.addEarcon(((Integer) it.next()).intValue());
        }
        for (Integer num : feedbackFragment.getHaptics()) {
            num.intValue();
            feedbackFragment2.mHaptics.add(num);
        }
    }

    public static List formInRangeSpans(CharSequence charSequence, int i) {
        Spanned spanned = (Spanned) charSequence;
        Object[] spans = spanned.getSpans(0, i, Object.class);
        ArrayList arrayList = new ArrayList();
        for (Object obj : spans) {
            arrayList.add(new SuffixTree.Candidate(obj, spanned.getSpanStart(obj), spanned.getSpanEnd(obj), spanned.getSpanFlags(obj)));
        }
        return arrayList;
    }

    public static void splitChunk(FeedbackItem feedbackItem, FeedbackFragment feedbackFragment, List list, int i, int i2, int i3) {
        SpannableString spannableString = new SpannableString(feedbackFragment.mText.subSequence(i, i2));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SuffixTree.Candidate candidate = (SuffixTree.Candidate) it.next();
            int i4 = candidate.numSeen;
            int i5 = candidate.begin;
            if (i5 > i && i4 < i2) {
                spannableString.setSpan(candidate.SuffixTree$Candidate$ar$node, Math.max(i4, i) - i, Math.min(i5, i2) - i, candidate.end);
            }
        }
        FeedbackFragment feedbackFragment2 = new FeedbackFragment(spannableString, feedbackFragment.mSpeechParams);
        feedbackFragment2.mLocale = feedbackFragment.mLocale;
        feedbackItem.addFragmentAtPosition(feedbackFragment2, i3);
    }

    public static boolean splitFeasible(List list, int i) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SuffixTree.Candidate candidate = (SuffixTree.Candidate) it.next();
            if (candidate.numSeen < i && candidate.begin > i) {
                return false;
            }
        }
        return true;
    }
}
