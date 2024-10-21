package com.google.android.accessibility.talkback;

import android.speech.tts.Voice;
import com.google.android.accessibility.talkback.actor.gemini.DataFieldUtils$GeminiResponse;
import com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.accessibility.talkback.compositor.EventFeedback;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription;
import com.google.android.accessibility.utils.ocr.OcrController;
import com.google.android.apps.aicore.llm.InterfaceData$DrafterGuess;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import com.google.android.marvin.talkback.R;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.collect.Cut;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators$PeekingImpl;
import com.google.common.collect.Range;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.vision.text.Text$TextBlock;
import com.google.search.mdi.aratea.proto.FilteredData;
import com.google.search.mdi.aratea.proto.FilteredReason;
import j$.util.function.Function$CC;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TalkBackAnalyticsImpl$$ExternalSyntheticLambda1 implements Function {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(int i) {
        this.switching_field = i;
    }

    public final /* synthetic */ Function andThen(Function function) {
        switch (this.switching_field) {
            case 0:
                return Function$CC.$default$andThen(this, function);
            case 1:
                return Function$CC.$default$andThen(this, function);
            case 2:
                return Function$CC.$default$andThen(this, function);
            case 3:
                return Function$CC.$default$andThen(this, function);
            case 4:
                return Function$CC.$default$andThen(this, function);
            case 5:
                return Function$CC.$default$andThen(this, function);
            case 6:
                return Function$CC.$default$andThen(this, function);
            case 7:
                return Function$CC.$default$andThen(this, function);
            case 8:
                return Function$CC.$default$andThen(this, function);
            case 9:
                return Function$CC.$default$andThen(this, function);
            case 10:
                return Function$CC.$default$andThen(this, function);
            case 11:
                return Function$CC.$default$andThen(this, function);
            case 12:
                return Function$CC.$default$andThen(this, function);
            case 13:
                return Function$CC.$default$andThen(this, function);
            default:
                return Function$CC.$default$andThen(this, function);
        }
    }

    /* JADX WARN: Type inference failed for: r10v40, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.List, java.lang.Object] */
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Iterators$PeekingImpl iterators$PeekingImpl;
        Cut cut;
        Cut cut2;
        switch (this.switching_field) {
            case 0:
                int i = TalkBackAnalyticsImpl.TalkBackAnalyticsImpl$ar$NoOp;
                return ((Integer[]) obj)[1];
            case 1:
                int i2 = TalkBackAnalyticsImpl.TalkBackAnalyticsImpl$ar$NoOp;
                return ((Integer[]) obj)[0];
            case 2:
                return ((Voice) obj).getLocale();
            case 3:
                FilteredReason forNumber = FilteredReason.forNumber(((FilteredData) obj).reason_);
                if (forNumber == null) {
                    forNumber = FilteredReason.FILTERED_REASON_UNSPECIFIED;
                }
                int ordinal = forNumber.ordinal();
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            if (ordinal != 4) {
                                if (ordinal != 5) {
                                    return "RESPONSE_CODE_UNSPECIFIED";
                                }
                                return "IRRELEVANT_QUERY";
                            }
                            return "BLOCK_LIST";
                        }
                        return "LOW_SCORE";
                    }
                    return "TEXT_SAFETY";
                }
                return "IMAGE_SAFETY";
            case 4:
                Compositor$HandleEventOptions builder$ar$class_merging$d9081d28_0 = DataFieldUtils$GeminiResponse.builder$ar$class_merging$d9081d28_0();
                builder$ar$class_merging$d9081d28_0.Compositor$HandleEventOptions$ar$sourceNode = (String) obj;
                return builder$ar$class_merging$d9081d28_0.build();
            case 5:
                return (List) ((AbstractMap.SimpleEntry) obj).getValue();
            case 6:
                return (GeminiFunctionUtils.DescribeImageDecision) ((AbstractMap.SimpleEntry) obj).getKey();
            case 7:
                DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0 = EventFeedback.builder$ar$class_merging$f18d5ad3_0();
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider = Integer.valueOf(R.raw.long_clicked);
                builder$ar$class_merging$f18d5ad3_0.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider = Integer.valueOf(R.array.view_long_clicked_pattern);
                return builder$ar$class_merging$f18d5ad3_0.build();
            case 8:
                return Double.valueOf(((NodePathDescription.Match) obj).distance());
            case 9:
                Comparator comparator = OcrController.TEXT_BLOCK_POSITION_COMPARATOR;
                return ((Text$TextBlock) obj).boundingBox;
            case 10:
                InterfaceData$DrafterGuess interfaceData$DrafterGuess = (InterfaceData$DrafterGuess) obj;
                return String.format(Locale.getDefault(), "(%d, %d)", Integer.valueOf(interfaceData$DrafterGuess.numGuesses_), Integer.valueOf(interfaceData$DrafterGuess.numCorrect_));
            case 11:
                SplitInstallSharedPreferences splitInstallSharedPreferences = (SplitInstallSharedPreferences) obj;
                ImmutableList.Builder builder = new ImmutableList.Builder(splitInstallSharedPreferences.SplitInstallSharedPreferences$ar$context.size());
                Collections.sort(splitInstallSharedPreferences.SplitInstallSharedPreferences$ar$context, Range.RangeLexOrdering.INSTANCE);
                Iterator it = splitInstallSharedPreferences.SplitInstallSharedPreferences$ar$context.iterator();
                if (it instanceof Iterators$PeekingImpl) {
                    iterators$PeekingImpl = (Iterators$PeekingImpl) it;
                } else {
                    iterators$PeekingImpl = new Iterators$PeekingImpl(it);
                }
                while (iterators$PeekingImpl.hasNext()) {
                    Range range = (Range) iterators$PeekingImpl.next();
                    while (iterators$PeekingImpl.hasNext()) {
                        if (!iterators$PeekingImpl.hasPeeked) {
                            iterators$PeekingImpl.peekedElement = iterators$PeekingImpl.iterator.next();
                            iterators$PeekingImpl.hasPeeked = true;
                        }
                        Range range2 = (Range) iterators$PeekingImpl.peekedElement;
                        if (range.isConnected(range2)) {
                            ContextDataProvider.checkArgument(range.intersection(range2).isEmpty(), "Overlapping ranges not permitted but found %s overlapping %s", range, range2);
                            Range range3 = (Range) iterators$PeekingImpl.next();
                            int compareTo = range.lowerBound.compareTo(range3.lowerBound);
                            int compareTo2 = range.upperBound.compareTo(range3.upperBound);
                            if (compareTo > 0 || compareTo2 < 0) {
                                if (compareTo < 0 || compareTo2 > 0) {
                                    if (compareTo <= 0) {
                                        cut = range.lowerBound;
                                    } else {
                                        cut = range3.lowerBound;
                                    }
                                    if (compareTo2 >= 0) {
                                        cut2 = range.upperBound;
                                    } else {
                                        cut2 = range3.upperBound;
                                    }
                                    range3 = new Range(cut, cut2);
                                }
                                range = range3;
                            }
                        }
                    }
                    builder.add$ar$ds$4f674a09_0(range);
                }
                ImmutableList build = builder.build();
                if (build.isEmpty()) {
                    return ImmutableRangeSet.EMPTY;
                }
                if (((RegularImmutableList) build).size == 1) {
                    UnmodifiableListIterator it2 = build.iterator();
                    Object next = it2.next();
                    if (!it2.hasNext()) {
                        if (((Range) next).equals(Range.ALL)) {
                            return ImmutableRangeSet.ALL;
                        }
                    } else {
                        StringBuilder sb = new StringBuilder("expected one element but was: <");
                        sb.append(next);
                        for (int i3 = 0; i3 < 4 && it2.hasNext(); i3++) {
                            sb.append(", ");
                            sb.append(it2.next());
                        }
                        if (it2.hasNext()) {
                            sb.append(", ...");
                        }
                        sb.append('>');
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                return new ImmutableRangeSet(build);
            case 12:
                return ((ImmutableList.Builder) obj).build();
            case 13:
                return ((ImmutableSet.Builder) obj).build();
            default:
                return ((ImmutableMap.Builder) obj).buildOrThrow();
        }
    }

    public final /* synthetic */ Function compose(Function function) {
        switch (this.switching_field) {
            case 0:
                return Function$CC.$default$compose(this, function);
            case 1:
                return Function$CC.$default$compose(this, function);
            case 2:
                return Function$CC.$default$compose(this, function);
            case 3:
                return Function$CC.$default$compose(this, function);
            case 4:
                return Function$CC.$default$compose(this, function);
            case 5:
                return Function$CC.$default$compose(this, function);
            case 6:
                return Function$CC.$default$compose(this, function);
            case 7:
                return Function$CC.$default$compose(this, function);
            case 8:
                return Function$CC.$default$compose(this, function);
            case 9:
                return Function$CC.$default$compose(this, function);
            case 10:
                return Function$CC.$default$compose(this, function);
            case 11:
                return Function$CC.$default$compose(this, function);
            case 12:
                return Function$CC.$default$compose(this, function);
            case 13:
                return Function$CC.$default$compose(this, function);
            default:
                return Function$CC.$default$compose(this, function);
        }
    }
}
