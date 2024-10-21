package com.google.mlkit.logging.schema;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
import com.google.android.accessibility.selecttospeak.popup.S2SPopupParsedIntentKt$parseIntent$text$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;
import kotlin.sequences.GeneratorSequence;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1;
import kotlin.time.DurationJvmKt;
import kotlinx.coroutines.flow.internal.SafeCollector_commonKt$checkContext$result$1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceStainRemovalLogEvent {
    public static final void appendElement(Appendable appendable, Object obj, Function1 function1) {
        if (function1 != null) {
            appendable.append((CharSequence) function1.invoke(obj));
            return;
        }
        if (obj != null && !(obj instanceof CharSequence)) {
            if (obj instanceof Character) {
                appendable.append(((Character) obj).charValue());
                return;
            } else {
                appendable.append(obj.toString());
                return;
            }
        }
        appendable.append((CharSequence) obj);
    }

    public static /* synthetic */ boolean contains$default$ar$ds(CharSequence charSequence, CharSequence charSequence2) {
        if (indexOf$default$ar$ds$4df40546_0(charSequence, (String) charSequence2, 0, 2) < 0) {
            return false;
        }
        return true;
    }

    public static final long durationOfMillis(long j) {
        long j2 = j + j + 1;
        DurationJvmKt.durationAssertionsEnabled;
        return j2;
    }

    public static boolean equals$ar$ds$566ac877_0(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    private static Function1 getIndentFunction$StringsKt__IndentKt$ar$ds() {
        if ("".length() == 0) {
            return S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$2a28f0fe_0;
        }
        return new S2SPopupParsedIntentKt$parseIntent$text$1(10);
    }

    public static int getLastIndex(CharSequence charSequence) {
        charSequence.getClass();
        return charSequence.length() - 1;
    }

    public static int indexOf$StringsKt__StringsKt$ar$ds(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z) {
        IntRange intRange = new IntRange(OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(i, 0), OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(i2, charSequence.length()));
        if (charSequence instanceof String) {
            int i3 = intRange.first;
            int i4 = intRange.last;
            int i5 = intRange.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (!regionMatches((String) charSequence2, 0, (String) charSequence, i3, charSequence2.length(), false)) {
                    if (i3 != i4) {
                        i3 += i5;
                    } else {
                        return -1;
                    }
                }
                return i3;
            }
            return -1;
        }
        int i6 = intRange.first;
        int i7 = intRange.last;
        int i8 = intRange.step;
        if ((i8 > 0 && i6 <= i7) || (i8 < 0 && i7 <= i6)) {
            while (!regionMatchesImpl$ar$ds(charSequence2, charSequence, i6, charSequence2.length())) {
                if (i6 != i7) {
                    i6 += i8;
                } else {
                    return -1;
                }
            }
            return i6;
        }
        return -1;
    }

    public static int indexOf$ar$ds(CharSequence charSequence, String str, int i) {
        str.getClass();
        if (!(charSequence instanceof String)) {
            return indexOf$StringsKt__StringsKt$ar$ds(charSequence, str, i, charSequence.length(), false);
        }
        return ((String) charSequence).indexOf(str, i);
    }

    public static /* synthetic */ int indexOf$default$ar$ds$4df40546_0(CharSequence charSequence, String str, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOf$ar$ds(charSequence, str, i);
    }

    public static boolean isBlank(CharSequence charSequence) {
        charSequence.getClass();
        if (charSequence.length() == 0) {
            return true;
        }
        IntIterator it = new IntRange(0, charSequence.length() - 1).iterator();
        while (it.hasNext()) {
            if (!OnDeviceSmartReplyLogEvent.isWhitespace(charSequence.charAt(it.nextInt()))) {
                return false;
            }
        }
        return true;
    }

    public static Sequence lineSequence(CharSequence charSequence) {
        return new GeneratorSequence(rangesDelimitedBy$StringsKt__StringsKt$default$ar$ds(charSequence, new String[]{"\r\n", "\n", "\r"}), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(charSequence, 12), 3);
    }

    public static List lines(CharSequence charSequence) {
        return OnDeviceSmartReplyLogEvent.toList(lineSequence(charSequence));
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default$ar$ds(CharSequence charSequence, String[] strArr) {
        return new GeneratorSequence(charSequence, new SafeCollector_commonKt$checkContext$result$1(OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.asList(strArr), 1), 4);
    }

    public static boolean regionMatches(String str, int i, String str2, int i2, int i3, boolean z) {
        str.getClass();
        return str.regionMatches(0, str2, i2, i3);
    }

    public static boolean regionMatchesImpl$ar$ds(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        charSequence.getClass();
        if (i < 0 || charSequence.length() - i2 < 0 || i > charSequence2.length() - i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!OnDeviceSmartReplyLogEvent.equals$ar$ds$837f257a_0(charSequence.charAt(i3), charSequence2.charAt(i + i3))) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ String replace$default$ar$ds(String str, String str2, String str3) {
        int i = 0;
        int indexOf$ar$ds = indexOf$ar$ds(str, str2, 0);
        if (indexOf$ar$ds >= 0) {
            int length = str2.length();
            int length2 = (str.length() - length) + str3.length();
            if (length2 >= 0) {
                StringBuilder sb = new StringBuilder(length2);
                do {
                    sb.append((CharSequence) str, i, indexOf$ar$ds);
                    sb.append(str3);
                    i = indexOf$ar$ds + length;
                    if (indexOf$ar$ds >= str.length()) {
                        break;
                    }
                    indexOf$ar$ds = indexOf$ar$ds(str, str2, indexOf$ar$ds + OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(length, 1));
                } while (indexOf$ar$ds > 0);
                sb.append((CharSequence) str, i, str.length());
                return sb.toString();
            }
            throw new OutOfMemoryError();
        }
        return str;
    }

    public static /* synthetic */ List split$default$ar$ds(CharSequence charSequence, String[] strArr) {
        charSequence.getClass();
        int i = 0;
        String str = strArr[0];
        if (str.length() == 0) {
            SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 = new SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(rangesDelimitedBy$StringsKt__StringsKt$default$ar$ds(charSequence, strArr));
            ArrayList arrayList = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1));
            Iterator it = sequencesKt___SequencesKt$asIterable$$inlined$Iterable$1.iterator();
            while (it.hasNext()) {
                arrayList.add(substring(charSequence, (IntRange) it.next()));
            }
            return arrayList;
        }
        int indexOf$ar$ds = indexOf$ar$ds(charSequence, str, 0);
        if (indexOf$ar$ds != -1) {
            ArrayList arrayList2 = new ArrayList(10);
            do {
                arrayList2.add(charSequence.subSequence(i, indexOf$ar$ds).toString());
                i = str.length() + indexOf$ar$ds;
                indexOf$ar$ds = indexOf$ar$ds(charSequence, str, i);
            } while (indexOf$ar$ds != -1);
            arrayList2.add(charSequence.subSequence(i, charSequence.length()).toString());
            return arrayList2;
        }
        return OnDeviceLanguageIdentificationLogEvent.listOf(charSequence.toString());
    }

    public static /* synthetic */ boolean startsWith$default$ar$ds(String str, String str2) {
        str.getClass();
        return str.startsWith(str2);
    }

    public static String substring(CharSequence charSequence, IntRange intRange) {
        intRange.getClass();
        int i = intRange.first;
        Integer.valueOf(i).getClass();
        int i2 = intRange.last;
        Integer.valueOf(i2).getClass();
        return charSequence.subSequence(i, i2 + 1).toString();
    }

    public static String substringAfter(String str, String str2, String str3) {
        int indexOf$default$ar$ds$4df40546_0 = indexOf$default$ar$ds$4df40546_0(str, str2, 0, 6);
        if (indexOf$default$ar$ds$4df40546_0 == -1) {
            return str3;
        }
        String substring = str.substring(indexOf$default$ar$ds$4df40546_0 + str2.length(), str.length());
        substring.getClass();
        return substring;
    }

    public static /* synthetic */ String substringAfterLast$default$ar$ds(String str) {
        str.getClass();
        str.getClass();
        int lastIndexOf = str.lastIndexOf(46, getLastIndex(str));
        if (lastIndexOf == -1) {
            return str;
        }
        String substring = str.substring(lastIndexOf + 1, str.length());
        substring.getClass();
        return substring;
    }

    public static CharSequence trim(CharSequence charSequence) {
        int i;
        int length = charSequence.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            if (true != z) {
                i = i2;
            } else {
                i = length;
            }
            boolean isWhitespace = OnDeviceSmartReplyLogEvent.isWhitespace(charSequence.charAt(i));
            if (!z) {
                if (!isWhitespace) {
                    z = true;
                } else {
                    i2++;
                }
            } else {
                if (!isWhitespace) {
                    break;
                }
                length--;
            }
        }
        return charSequence.subSequence(i2, length + 1);
    }

    public static String trimIndent(String str) {
        int i;
        Comparable comparable;
        int i2;
        List lines = lines(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : lines) {
            if (!isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(arrayList));
        Iterator it = arrayList.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            int length = str2.length();
            while (true) {
                if (i < length) {
                    if (!OnDeviceSmartReplyLogEvent.isWhitespace(str2.charAt(i))) {
                        break;
                    }
                    i++;
                } else {
                    i = -1;
                    break;
                }
            }
            if (i == -1) {
                i = str2.length();
            }
            arrayList2.add(Integer.valueOf(i));
        }
        Iterator it2 = arrayList2.iterator();
        if (!it2.hasNext()) {
            comparable = null;
        } else {
            comparable = (Comparable) it2.next();
            while (it2.hasNext()) {
                Comparable comparable2 = (Comparable) it2.next();
                if (comparable.compareTo(comparable2) > 0) {
                    comparable = comparable2;
                }
            }
        }
        Integer num = (Integer) comparable;
        if (num != null) {
            i2 = num.intValue();
        } else {
            i2 = 0;
        }
        int length2 = str.length();
        lines.size();
        Function1 indentFunction$StringsKt__IndentKt$ar$ds = getIndentFunction$StringsKt__IndentKt$ar$ds();
        int lastIndex = OnDeviceLanguageIdentificationLogEvent.getLastIndex(lines);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : lines) {
            int i3 = i + 1;
            if (i < 0) {
                OnDeviceLanguageIdentificationLogEvent.throwIndexOverflow();
            }
            String str3 = (String) obj2;
            if ((i == 0 || i == lastIndex) && isBlank(str3)) {
                str3 = null;
            } else {
                str3.getClass();
                if (i2 >= 0) {
                    String substring = str3.substring(OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(i2, str3.length()));
                    substring.getClass();
                    String str4 = (String) indentFunction$StringsKt__IndentKt$ar$ds.invoke(substring);
                    if (str4 != null) {
                        str3 = str4;
                    }
                } else {
                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i2, "Requested character count ", " is less than zero."));
                }
            }
            if (str3 != null) {
                arrayList3.add(str3);
            }
            i = i3;
        }
        return ((StringBuilder) OnDeviceLanguageIdentificationLogEvent.joinTo$default$ar$ds(arrayList3, new StringBuilder(length2), "\n")).toString();
    }

    public static /* synthetic */ String trimMargin$default$ar$ds(String str) {
        String str2;
        if (!isBlank("|")) {
            List lines = lines(str);
            lines.size();
            Function1 indentFunction$StringsKt__IndentKt$ar$ds = getIndentFunction$StringsKt__IndentKt$ar$ds();
            int lastIndex = OnDeviceLanguageIdentificationLogEvent.getLastIndex(lines);
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (Object obj : lines) {
                int i2 = i + 1;
                if (i < 0) {
                    OnDeviceLanguageIdentificationLogEvent.throwIndexOverflow();
                }
                String str3 = (String) obj;
                String str4 = null;
                if ((i == 0 || i == lastIndex) && isBlank(str3)) {
                    str3 = null;
                } else {
                    int length = str3.length();
                    int i3 = 0;
                    while (true) {
                        if (i3 < length) {
                            if (!OnDeviceSmartReplyLogEvent.isWhitespace(str3.charAt(i3))) {
                                break;
                            }
                            i3++;
                        } else {
                            i3 = -1;
                            break;
                        }
                    }
                    if (i3 != -1) {
                        str3.getClass();
                        if (str3.startsWith("|", i3)) {
                            str3.getClass();
                            str4 = str3.substring(i3 + 1);
                            str4.getClass();
                        }
                    }
                    if (str4 != null && (str2 = (String) indentFunction$StringsKt__IndentKt$ar$ds.invoke(str4)) != null) {
                        str3 = str2;
                    }
                }
                if (str3 != null) {
                    arrayList.add(str3);
                }
                i = i2;
            }
            return ((StringBuilder) OnDeviceLanguageIdentificationLogEvent.joinTo$default$ar$ds(arrayList, new StringBuilder(str.length()), "\n")).toString();
        }
        throw new IllegalArgumentException("marginPrefix must be non-blank string.");
    }
}
