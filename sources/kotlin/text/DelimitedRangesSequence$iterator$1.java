package kotlin.text;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.sequences.GeneratorSequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DelimitedRangesSequence$iterator$1 implements Iterator, KMappedMarker {
    private int currentStartIndex;
    private IntRange nextItem;
    private int nextSearchIndex;
    private int nextState = -1;
    final /* synthetic */ GeneratorSequence this$0$ar$class_merging$9dfe2b5a_0;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.CharSequence, java.lang.Object] */
    public DelimitedRangesSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.this$0$ar$class_merging$9dfe2b5a_0 = generatorSequence;
        int coerceIn$ar$ds = OnDeviceSmartReplyLogEvent.SmartReply.coerceIn$ar$ds(0, generatorSequence.GeneratorSequence$ar$getInitialValue.length());
        this.currentStartIndex = coerceIn$ar$ds;
        this.nextSearchIndex = coerceIn$ar$ds;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, kotlin.jvm.functions.Function2] */
    private final void calcNext() {
        IntRange intRange;
        int i = this.nextSearchIndex;
        int i2 = 0;
        if (i < 0) {
            this.nextState = 0;
            this.nextItem = null;
            return;
        }
        if (i > this.this$0$ar$class_merging$9dfe2b5a_0.GeneratorSequence$ar$getInitialValue.length()) {
            this.nextItem = new IntRange(this.currentStartIndex, OnDeviceStainRemovalLogEvent.getLastIndex(this.this$0$ar$class_merging$9dfe2b5a_0.GeneratorSequence$ar$getInitialValue));
            this.nextSearchIndex = -1;
        } else {
            GeneratorSequence generatorSequence = this.this$0$ar$class_merging$9dfe2b5a_0;
            Pair pair = (Pair) generatorSequence.GeneratorSequence$ar$getNextValue.invoke(generatorSequence.GeneratorSequence$ar$getInitialValue, Integer.valueOf(this.nextSearchIndex));
            if (pair == null) {
                this.nextItem = new IntRange(this.currentStartIndex, OnDeviceStainRemovalLogEvent.getLastIndex(this.this$0$ar$class_merging$9dfe2b5a_0.GeneratorSequence$ar$getInitialValue));
                this.nextSearchIndex = -1;
            } else {
                int intValue = ((Number) pair.first).intValue();
                int intValue2 = ((Number) pair.second).intValue();
                int i3 = this.currentStartIndex;
                if (intValue <= Integer.MIN_VALUE) {
                    intRange = IntRange.EMPTY;
                } else {
                    intRange = new IntRange(i3, intValue - 1);
                }
                this.nextItem = intRange;
                int i4 = intValue + intValue2;
                this.currentStartIndex = i4;
                if (intValue2 == 0) {
                    i2 = 1;
                }
                this.nextSearchIndex = i4 + i2;
            }
        }
        this.nextState = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState == 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            IntRange intRange = this.nextItem;
            intRange.getClass();
            this.nextItem = null;
            this.nextState = -1;
            return intRange;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }
}
