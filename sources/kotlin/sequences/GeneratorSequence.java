package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.text.DelimitedRangesSequence$iterator$1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeneratorSequence implements Sequence {
    public final Object GeneratorSequence$ar$getInitialValue;
    public final Object GeneratorSequence$ar$getNextValue;
    private final /* synthetic */ int switching_field;

    public GeneratorSequence(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.GeneratorSequence$ar$getInitialValue = obj;
        this.GeneratorSequence$ar$getNextValue = obj2;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return new DelimitedRangesSequence$iterator$1(this);
                    }
                    return new TransformingSequence$iterator$1(this);
                }
                return new TakeWhileSequence$iterator$1(this, 0);
            }
            return new TakeWhileSequence$iterator$1(this, 1, null);
        }
        return new GeneratorSequence$iterator$1(this);
    }

    public GeneratorSequence(Sequence sequence, Function1 function1, int i) {
        this.switching_field = i;
        this.GeneratorSequence$ar$getNextValue = sequence;
        this.GeneratorSequence$ar$getInitialValue = function1;
    }
}
