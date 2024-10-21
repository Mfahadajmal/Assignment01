package com.google.android.accessibility.braille.brailledisplay.controller;

import _COROUTINE._BOUNDARY;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.Range;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.controller.DisplayInfo;
import com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.interfaces.SelectionRange;
import com.google.android.accessibility.braille.translate.TranslationResult;
import com.google.common.collect.ImmutableList;
import j$.util.DesugarArrays;
import j$.util.stream.Collectors;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContentHelper {
    public static final /* synthetic */ int ContentHelper$ar$NoOp = 0;
    private static final BrailleCharacter SELECTION_DOTS = new BrailleCharacter("78");
    private BrailleWord brailleContent;
    public TranslationResult currentTranslationResult;
    public boolean isSplitParagraphs;
    public CharSequence originalText;
    private BrailleWord overlaidBrailleContent;
    private final TranslatorManager translatorManager;
    public final WrapStrategyRetriever wrapStrategyRetriever;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ByDistanceComparator implements Comparator {
        private final Spanned spanned;
        private final int start;

        public ByDistanceComparator(Spanned spanned, int i) {
            this.spanned = spanned;
            this.start = i;
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj2;
            int spanStart = this.spanned.getSpanStart(accessibilityNodeInfoCompat);
            int spanStart2 = this.spanned.getSpanStart(accessibilityNodeInfoCompat2);
            int i = this.start;
            int abs = Math.abs(i - spanStart);
            int abs2 = Math.abs(i - spanStart2);
            if (abs != abs2) {
                return abs - abs2;
            }
            return (spanStart + this.spanned.getSpanEnd(accessibilityNodeInfoCompat)) - (spanStart2 + this.spanned.getSpanEnd(accessibilityNodeInfoCompat2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface WrapStrategyRetriever {
        WrapStrategy getWrapStrategy();
    }

    public ContentHelper(TranslatorManager translatorManager, WrapStrategyRetriever wrapStrategyRetriever) {
        BrailleWord brailleWord = new BrailleWord();
        this.brailleContent = brailleWord;
        this.overlaidBrailleContent = brailleWord;
        this.translatorManager = translatorManager;
        this.wrapStrategyRetriever = wrapStrategyRetriever;
    }

    public static int displayToTextPosition(TranslationResult translationResult, int i) {
        ImmutableList<Integer> brailleToTextPositions = translationResult.brailleToTextPositions();
        if (i < 0) {
            return -1;
        }
        if (i >= brailleToTextPositions.size()) {
            return translationResult.textToBraillePositions().size();
        }
        return brailleToTextPositions.get(i).intValue();
    }

    private static int textToDisplayPosition(TranslationResult translationResult, int i) {
        ImmutableList<Integer> textToBraillePositions = translationResult.textToBraillePositions();
        if (i < 0) {
            return -1;
        }
        if (i >= textToBraillePositions.size()) {
            return translationResult.brailleToTextPositions().size();
        }
        return textToBraillePositions.get(i).intValue();
    }

    public final DisplayInfo generateDisplayInfo(CharSequence charSequence, int i, boolean z) {
        Range range;
        this.originalText = charSequence;
        TranslationResult translate = this.translatorManager.outputTranslator.translate(charSequence, -1);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        DisplaySpans$SelectionSpan[] displaySpans$SelectionSpanArr = (DisplaySpans$SelectionSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), DisplaySpans$SelectionSpan.class);
        if (displaySpans$SelectionSpanArr.length > 0) {
            range = new Range(Integer.valueOf(spannableStringBuilder.getSpanStart(displaySpans$SelectionSpanArr[0])), Integer.valueOf(spannableStringBuilder.getSpanEnd(displaySpans$SelectionSpanArr[0])));
        } else {
            range = new Range(-1, -1);
        }
        return generateDisplayInfo$ar$edu(i, new SelectionRange(textToDisplayPosition(translate, ((Integer) range.getLower()).intValue()), textToDisplayPosition(translate, ((Integer) range.getUpper()).intValue())), 0, 0, z, translate, 1);
    }

    public final DisplayInfo generateDisplayInfo$ar$edu(int i, SelectionRange selectionRange, int i2, int i3, boolean z, TranslationResult translationResult, int i4) {
        TranslationResult translationResult2;
        int i5;
        TranslationResult translationResult3;
        boolean z2;
        int i6;
        Object obj;
        WrapStrategy wrapStrategy = this.wrapStrategyRetriever.getWrapStrategy();
        CharSequence text = translationResult.text();
        TranslationResult translationResult4 = this.currentTranslationResult;
        int displayStart = wrapStrategy.getDisplayStart();
        int i7 = 0;
        if (i != 0) {
            if (i != 1 && translationResult4 != null && translationResult4.text() != null) {
                SpannedString spannedString = new SpannedString(translationResult4.text());
                SpannedString spannedString2 = new SpannedString(text);
                int displayToTextPosition = displayToTextPosition(translationResult4, displayStart);
                AccessibilityNodeInfoCompat[] accessibilityNodeInfoCompatArr = (AccessibilityNodeInfoCompat[]) spannedString.getSpans(displayToTextPosition, displayToTextPosition(translationResult4, displayStart), AccessibilityNodeInfoCompat.class);
                Arrays.sort(accessibilityNodeInfoCompatArr, new ByDistanceComparator(spannedString, displayToTextPosition));
                int length = accessibilityNodeInfoCompatArr.length;
                int i8 = 0;
                while (i8 < length) {
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = accessibilityNodeInfoCompatArr[i8];
                    Object[] spans = spannedString2.getSpans(i7, spannedString2.length(), accessibilityNodeInfoCompat.getClass());
                    int length2 = spans.length;
                    while (true) {
                        if (i7 < length2) {
                            obj = spans[i7];
                            if (accessibilityNodeInfoCompat.equals(obj)) {
                                break;
                            }
                            i7++;
                        } else {
                            obj = null;
                            break;
                        }
                    }
                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) obj;
                    if (accessibilityNodeInfoCompat2 == null) {
                        i8++;
                        i7 = 0;
                    } else {
                        int textToDisplayPosition = textToDisplayPosition(translationResult4, spannedString.getSpanStart(accessibilityNodeInfoCompat));
                        translationResult2 = translationResult;
                        i5 = displayStart + (textToDisplayPosition(translationResult2, spannedString2.getSpanStart(accessibilityNodeInfoCompat2)) - textToDisplayPosition);
                        break;
                    }
                }
            }
            translationResult2 = translationResult;
            i5 = -1;
        } else {
            translationResult2 = translationResult;
            i5 = 0;
        }
        this.isSplitParagraphs = z;
        if (Math.max(selectionRange.start, selectionRange.end) >= translationResult.cells().size()) {
            translationResult3 = TranslationResult.appendOneEmptyCell(translationResult);
        } else {
            translationResult3 = translationResult2;
        }
        this.currentTranslationResult = translationResult3;
        this.brailleContent = translationResult3.cells();
        this.overlaidBrailleContent = new BrailleWord(this.brailleContent);
        int i9 = selectionRange.start;
        int i10 = selectionRange.end;
        if (i9 != -1 && i10 != -1) {
            if (i9 == i10) {
                BrailleWord brailleWord = this.overlaidBrailleContent;
                brailleWord.set(i9, brailleWord.get(i9).union(SELECTION_DOTS));
            } else {
                for (int min = Math.min(i9, i10); min < Math.max(i9, i10) && min < this.overlaidBrailleContent.size(); min++) {
                    BrailleWord brailleWord2 = this.brailleContent;
                    BrailleCharacter brailleCharacter = brailleWord2.get(min);
                    BrailleCharacter brailleCharacter2 = SELECTION_DOTS;
                    brailleWord2.set(min, brailleCharacter.union(brailleCharacter2));
                    BrailleWord brailleWord3 = this.overlaidBrailleContent;
                    brailleWord3.set(min, brailleWord3.get(min).union(brailleCharacter2));
                }
            }
        }
        if (translationResult3 != null && !TextUtils.isEmpty(translationResult3.text()) && wrapStrategy.displayWidth > 0) {
            wrapStrategy.translation = translationResult3;
            wrapStrategy.endIndexOfInput = i3;
            wrapStrategy.startIndexOfInput = i2;
            wrapStrategy.displayStart = 0;
            wrapStrategy.displayEnd = 0;
            wrapStrategy.splitPoints.clear();
            if (z) {
                ImmutableList<Integer> textToBraillePositions = wrapStrategy.translation.textToBraillePositions();
                int size = wrapStrategy.translation.cells().size();
                int i11 = 0;
                while (i11 < wrapStrategy.translation.text().length()) {
                    int i12 = i11 + 1;
                    if (wrapStrategy.translation.text().charAt(i11) == '\n') {
                        if (i12 < textToBraillePositions.size()) {
                            i6 = textToBraillePositions.get(i12).intValue();
                        } else {
                            i6 = size;
                        }
                        wrapStrategy.splitPoints.append(i6, 1);
                    }
                    i11 = i12;
                }
            }
            wrapStrategy.breakPoints.clear();
            wrapStrategy.calculateBreakPoints();
            wrapStrategy.lineBreaks.clear();
            wrapStrategy.lineBreaks.append(0, 1);
            int i13 = 0;
            while (i13 < wrapStrategy.translation.cells().size()) {
                int i14 = wrapStrategy.displayWidth + i13;
                int findRightLimit = wrapStrategy.findRightLimit(wrapStrategy.splitPoints, i13);
                if (findRightLimit > i14) {
                    findRightLimit = wrapStrategy.findLeftLimit(wrapStrategy.breakPoints, i14 + 1);
                    if (findRightLimit > i13) {
                        while (findRightLimit < wrapStrategy.translation.cells().size() && wrapStrategy.breakPoints.get(findRightLimit) == 2) {
                            findRightLimit++;
                        }
                    } else {
                        i13 = i14;
                        wrapStrategy.lineBreaks.append(i13, 1);
                    }
                }
                i13 = findRightLimit;
                wrapStrategy.lineBreaks.append(i13, 1);
            }
            wrapStrategy.isValid = true;
            z2 = false;
        } else {
            wrapStrategy.translation = null;
            z2 = false;
            wrapStrategy.endIndexOfInput = 0;
            wrapStrategy.startIndexOfInput = 0;
            wrapStrategy.displayStart = 0;
            wrapStrategy.displayEnd = 0;
            wrapStrategy.splitPoints.clear();
            wrapStrategy.breakPoints.clear();
            wrapStrategy.lineBreaks.clear();
            wrapStrategy.isValid = false;
        }
        if (i5 >= 0) {
            wrapStrategy.panTo(i5, z2);
        } else {
            wrapStrategy.panTo(selectionRange.end, true);
        }
        return getDisplayInfo$ar$edu(translationResult3.text(), wrapStrategy.getDisplayStart(), wrapStrategy.getDisplayEnd(), this.currentTranslationResult.brailleToTextPositions(), i4);
    }

    public final DisplayInfo getDisplayInfo$ar$edu(CharSequence charSequence, int i, int i2, List list, int i3) {
        int intValue;
        int intValue2;
        BrailleWord brailleWord;
        ByteBuffer byteBuffer;
        CharSequence charSequence2;
        ImmutableList immutableList;
        if (charSequence == null || i2 < i) {
            return null;
        }
        if (i >= list.size()) {
            intValue = 0;
        } else {
            intValue = ((Integer) list.get(i)).intValue();
        }
        if (i2 >= list.size()) {
            intValue2 = charSequence.length();
        } else {
            intValue2 = ((Integer) list.get(i2)).intValue();
        }
        if (intValue2 < intValue) {
            intValue2 = intValue;
        }
        StringBuilder sb = new StringBuilder(charSequence.subSequence(intValue, intValue2));
        int i4 = i2 - i;
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            if (i6 < list.size()) {
                iArr[i5] = ((Integer) list.get(i6)).intValue() - intValue;
            } else {
                iArr[i5] = sb.length();
                sb.append(' ');
            }
        }
        BrailleWord subword = this.brailleContent.subword(i, i2);
        BrailleWord brailleWord2 = this.brailleContent;
        BrailleWord brailleWord3 = this.overlaidBrailleContent;
        if (brailleWord2 != brailleWord3) {
            brailleWord = brailleWord3.subword(i, i2);
        } else {
            brailleWord = subword;
        }
        DisplayInfo.Builder builder = new DisplayInfo.Builder(null);
        ByteBuffer wrap = ByteBuffer.wrap(subword.toByteArray());
        if (wrap != null) {
            builder.displayedBraille = wrap;
            ByteBuffer wrap2 = ByteBuffer.wrap(brailleWord.toByteArray());
            if (wrap2 != null) {
                builder.displayedOverlaidBraille = wrap2;
                builder.displayedText = sb.toString();
                builder.displayedBrailleToTextPositions = ImmutableList.copyOf((Collection) DesugarArrays.stream(iArr).boxed().collect(Collectors.toList()));
                boolean z = !subword.equals(brailleWord);
                builder.blink = z;
                builder.set$0 = (byte) 1;
                if (i3 != 0) {
                    builder.source$ar$edu = i3;
                    ByteBuffer byteBuffer2 = builder.displayedBraille;
                    if (byteBuffer2 != null && (byteBuffer = builder.displayedOverlaidBraille) != null && (charSequence2 = builder.displayedText) != null && (immutableList = builder.displayedBrailleToTextPositions) != null) {
                        return new DisplayInfo(byteBuffer2, byteBuffer, charSequence2, immutableList, z, i3);
                    }
                    StringBuilder sb2 = new StringBuilder();
                    if (builder.displayedBraille == null) {
                        sb2.append(" displayedBraille");
                    }
                    if (builder.displayedOverlaidBraille == null) {
                        sb2.append(" displayedOverlaidBraille");
                    }
                    if (builder.displayedText == null) {
                        sb2.append(" displayedText");
                    }
                    if (builder.displayedBrailleToTextPositions == null) {
                        sb2.append(" displayedBrailleToTextPositions");
                    }
                    if (builder.set$0 == 0) {
                        sb2.append(" blink");
                    }
                    if (builder.source$ar$edu == 0) {
                        sb2.append(" source");
                    }
                    throw new IllegalStateException("Missing required properties:".concat(sb2.toString()));
                }
                throw new NullPointerException("Null source");
            }
            throw new NullPointerException("Null displayedOverlaidBraille");
        }
        throw new NullPointerException("Null displayedBraille");
    }

    public final int getTextCursorPosition(int i) {
        return transferByteIndexToTextIndex(toWholeContentIndex(i));
    }

    public final int toWholeContentIndex(int i) {
        WrapStrategy wrapStrategy = this.wrapStrategyRetriever.getWrapStrategy();
        int displayStart = i + wrapStrategy.getDisplayStart();
        if (displayStart >= wrapStrategy.getDisplayEnd()) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.i(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(displayStart, "User clicked on outside of the currently displayed content: "), null);
            return -1;
        }
        return displayStart;
    }

    public final int transferByteIndexToTextIndex(int i) {
        return displayToTextPosition(this.currentTranslationResult, i);
    }
}
