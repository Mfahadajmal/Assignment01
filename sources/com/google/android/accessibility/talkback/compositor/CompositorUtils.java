package com.google.android.accessibility.talkback.compositor;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.SpeechCleanupUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CompositorUtils {
    public static String separator = ", ";

    public static CharSequence conditionalAppend(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        if (TextUtils.isEmpty(charSequence)) {
            return "";
        }
        if (TextUtils.isEmpty(charSequence2)) {
            return charSequence;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(charSequence).append(SpannableUtils$IdentifierSpan.wrapWithIdentifierSpan(charSequence3)).append(charSequence2);
        return spannableStringBuilder;
    }

    public static CharSequence conditionalPrepend(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        if (TextUtils.isEmpty(charSequence2)) {
            return "";
        }
        if (TextUtils.isEmpty(charSequence)) {
            return charSequence2;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(charSequence).append(SpannableUtils$IdentifierSpan.wrapWithIdentifierSpan(charSequence3)).append(charSequence2);
        return spannableStringBuilder;
    }

    public static CharSequence dedupJoin(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        boolean z = true;
        CharSequence[] charSequenceArr = {charSequence, charSequence2, charSequence3};
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < 3; i++) {
            CharSequence charSequence4 = charSequenceArr[i];
            if (!TextUtils.isEmpty(charSequence4)) {
                String lowerCase = ContextDataProvider.toLowerCase(charSequence4.toString());
                if (!hashSet.contains(lowerCase)) {
                    hashSet.add(lowerCase);
                    if (!z) {
                        spannableStringBuilder.append(SpannableUtils$IdentifierSpan.wrapWithIdentifierSpan(separator));
                    }
                    spannableStringBuilder.append(charSequence4);
                    z = false;
                }
            }
        }
        return spannableStringBuilder;
    }

    public static CharSequence getCleanupString(CharSequence charSequence, Context context) {
        if (TextUtils.isEmpty(charSequence)) {
            return "";
        }
        return SpeechCleanupUtils.collapseRepeatedCharactersAndCleanUp(context, charSequence);
    }

    public static CharSequence joinCharSequences(CharSequence... charSequenceArr) {
        ArrayList arrayList = new ArrayList(charSequenceArr.length);
        for (CharSequence charSequence : charSequenceArr) {
            if (charSequence != null) {
                arrayList.add(charSequence);
            }
        }
        return joinCharSequences$ar$ds(arrayList, separator);
    }

    public static CharSequence joinCharSequences$ar$ds(List list, CharSequence charSequence) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Iterator it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            CharSequence charSequence2 = (CharSequence) it.next();
            if (!TextUtils.isEmpty(charSequence2)) {
                if (charSequence != null) {
                    if (!z) {
                        spannableStringBuilder.append(SpannableUtils$IdentifierSpan.wrapWithIdentifierSpan(charSequence));
                    }
                    z = false;
                }
                spannableStringBuilder.append(charSequence2);
            }
        }
        return spannableStringBuilder;
    }

    public static CharSequence prependCapital(CharSequence charSequence, Context context) {
        if (TextUtils.isEmpty(charSequence)) {
            return "";
        }
        if (charSequence.length() == 1 && Character.isUpperCase(charSequence.charAt(0))) {
            return context.getString(R.string.template_capital_letter, Character.valueOf(charSequence.charAt(0)));
        }
        return charSequence;
    }
}
