package com.google.android.accessibility.utils.output;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import j$.util.DesugarCollections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackFragment {
    volatile int fragmentStartIndex;
    private Set mEarcons;
    public Set mHaptics;
    public Locale mLocale;
    public Bundle mNonSpeechParams;
    public Bundle mSpeechParams;
    public CharSequence mText;
    public int startIndexInFeedbackItem;

    public FeedbackFragment(CharSequence charSequence, Bundle bundle) {
        this(charSequence, null, null, bundle, null);
    }

    private final boolean bundleNotEqual(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return false;
        }
        if (bundle == null || bundle2 == null) {
            return true;
        }
        if (bundle2.size() != bundle.size()) {
            return true;
        }
        for (String str : bundle.keySet()) {
            if (objectsNotEqual$ar$ds(bundle.get(str), bundle2.get(str))) {
                return true;
            }
        }
        return false;
    }

    private static final int getBundleHashCode$ar$ds(Bundle bundle) {
        int hashCode;
        if (bundle == null) {
            return 0;
        }
        Iterator<String> it = bundle.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Object obj = bundle.get(it.next());
            if (obj == null) {
                hashCode = 0;
            } else {
                hashCode = obj.hashCode();
            }
            i += hashCode;
        }
        return i;
    }

    private static final boolean objectsNotEqual$ar$ds(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        if (obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    public final void addEarcon(int i) {
        this.mEarcons.add(Integer.valueOf(i));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeedbackFragment)) {
            return false;
        }
        FeedbackFragment feedbackFragment = (FeedbackFragment) obj;
        if (TextUtils.equals(this.mText, feedbackFragment.mText) && !objectsNotEqual$ar$ds(this.mEarcons, feedbackFragment.mEarcons) && !objectsNotEqual$ar$ds(this.mHaptics, feedbackFragment.mHaptics) && !bundleNotEqual(this.mSpeechParams, feedbackFragment.mSpeechParams) && !bundleNotEqual(this.mNonSpeechParams, feedbackFragment.mNonSpeechParams)) {
            return true;
        }
        return false;
    }

    public final Set getEarcons() {
        return DesugarCollections.unmodifiableSet(this.mEarcons);
    }

    public final Set getHaptics() {
        return DesugarCollections.unmodifiableSet(this.mHaptics);
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        CharSequence charSequence = this.mText;
        int i = 0;
        if (charSequence == null) {
            hashCode = 0;
        } else {
            hashCode = charSequence.hashCode();
        }
        Set set = this.mEarcons;
        if (set == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = set.hashCode();
        }
        int i2 = hashCode + 527;
        Set set2 = this.mHaptics;
        if (set2 != null) {
            i = set2.hashCode();
        }
        return (((((((i2 * 31) + hashCode2) * 31) + i) * 31) + getBundleHashCode$ar$ds(this.mSpeechParams)) * 31) + getBundleHashCode$ar$ds(this.mNonSpeechParams);
    }

    public final String toString() {
        return "{text:" + String.valueOf(this.mText) + ", earcons:" + String.valueOf(this.mEarcons) + ", haptics:" + String.valueOf(this.mHaptics) + ", speechParams:" + String.valueOf(this.mSpeechParams) + "nonSpeechParams:" + String.valueOf(this.mNonSpeechParams) + "fragmentStartIndex:" + this.fragmentStartIndex + "}";
    }

    public FeedbackFragment(CharSequence charSequence, Set set, Set set2, Bundle bundle, Bundle bundle2) {
        this.startIndexInFeedbackItem = 0;
        this.fragmentStartIndex = 0;
        this.mText = new SpannableString(charSequence);
        HashSet hashSet = new HashSet();
        this.mEarcons = hashSet;
        if (set != null) {
            hashSet.addAll(set);
        }
        HashSet hashSet2 = new HashSet();
        this.mHaptics = hashSet2;
        if (set2 != null) {
            hashSet2.addAll(set2);
        }
        Bundle bundle3 = new Bundle(Bundle.EMPTY);
        this.mSpeechParams = bundle3;
        if (bundle != null) {
            bundle3.putAll(bundle);
        }
        Bundle bundle4 = new Bundle(Bundle.EMPTY);
        this.mNonSpeechParams = bundle4;
        if (bundle2 != null) {
            bundle4.putAll(bundle2);
        }
    }
}
