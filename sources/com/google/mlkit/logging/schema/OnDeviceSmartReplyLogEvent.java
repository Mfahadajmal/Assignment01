package com.google.mlkit.logging.schema;

import androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1;
import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.GeneratorSequence;
import kotlin.sequences.Sequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceSmartReplyLogEvent {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SmartReply {
        public static int coerceAtLeast(int i, int i2) {
            return i < i2 ? i2 : i;
        }

        public static int coerceAtMost(int i, int i2) {
            return i > i2 ? i2 : i;
        }

        public static int coerceIn$ar$ds(int i, int i2) {
            if (i2 >= 0) {
                if (i < 0) {
                    return 0;
                }
                if (i > i2) {
                    return i2;
                }
                return i;
            }
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i2 + " is less than minimum 0.");
        }

        public static long coerceAtLeast(long j, long j2) {
            return j < j2 ? j2 : j;
        }

        public static long coerceAtMost(long j, long j2) {
            return j > j2 ? j2 : j;
        }
    }

    public static Sequence asSequence(Iterator it) {
        it.getClass();
        return constrainOnce(new ViewGroupKt$special$$inlined$Sequence$1(it, 5));
    }

    public static final void checkRadix$ar$ds(int i) {
        if (new IntRange(2, 36).contains(i)) {
            return;
        }
        throw new IllegalArgumentException("radix " + i + " was not in valid range " + new IntRange(2, 36));
    }

    public static Sequence constrainOnce(Sequence sequence) {
        if (sequence instanceof ConstrainedOnceSequence) {
            return sequence;
        }
        return new ConstrainedOnceSequence(sequence);
    }

    public static final int digitOf$ar$ds(char c) {
        return Character.digit((int) c, 10);
    }

    public static boolean equals$ar$ds$837f257a_0(char c, char c2) {
        if (c == c2) {
            return true;
        }
        return false;
    }

    public static Sequence filterNotNull(Sequence sequence) {
        return new GeneratorSequence(sequence, (Function1) S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$c629e1d6_0, 1);
    }

    public static Sequence generateSequence(final Object obj, Function1 function1) {
        if (obj == null) {
            return EmptySequence.INSTANCE;
        }
        return new GeneratorSequence(new Function0() { // from class: kotlin.sequences.SequencesKt__SequencesKt$generateSequence$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return obj;
            }
        }, function1, 0);
    }

    public static final boolean isWhitespace(char c) {
        if (!Character.isWhitespace(c) && !Character.isSpaceChar(c)) {
            return false;
        }
        return true;
    }

    public static /* synthetic */ String joinToString$default$ar$ds$9e9f5bd_0(Sequence sequence, CharSequence charSequence, Function1 function1, int i) {
        CharSequence charSequence2;
        String str;
        char c;
        Function1 function12;
        String str2;
        String str3 = "";
        if ((i & 2) != 0) {
            charSequence2 = "";
        } else {
            charSequence2 = null;
        }
        charSequence2.getClass();
        if ((i & 4) == 0) {
            str3 = null;
        }
        str3.getClass();
        if ((i & 16) != 0) {
            str = "...";
        } else {
            str = null;
        }
        str.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence2);
        Iterator it = sequence.iterator();
        char c2 = 0;
        int i2 = 0;
        while (true) {
            if ((i & 8) != 0) {
                c = 65535;
            } else {
                c = 0;
            }
            if (it.hasNext()) {
                Object next = it.next();
                i2++;
                if (i2 > 1) {
                    if (1 != (i & 1)) {
                        str2 = "\n";
                    } else {
                        str2 = ", ";
                    }
                    sb.append((CharSequence) str2);
                }
                if (c >= 0 && i2 > 0) {
                    break;
                }
                if ((i & 32) != 0) {
                    function12 = null;
                } else {
                    function12 = function1;
                }
                OnDeviceStainRemovalLogEvent.appendElement(sb, next, function12);
            } else {
                c2 = c;
                break;
            }
        }
        if (c2 >= 0 && i2 > 0) {
            sb.append((CharSequence) str);
        }
        sb.append((CharSequence) str3);
        return sb.toString();
    }

    public static List toList(Sequence sequence) {
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return OnDeviceLanguageIdentificationLogEvent.listOf(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
