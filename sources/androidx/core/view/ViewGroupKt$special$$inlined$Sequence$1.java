package androidx.core.view;

import androidx.navigation.ActivityNavigator$hostActivity$1;
import com.google.android.accessibility.selecttospeak.popup.S2SPopupParsedIntentKt$items$1$iterator$1;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import java.util.Iterator;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewGroupKt$special$$inlined$Sequence$1 implements Sequence {
    final /* synthetic */ Object ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined;
    private final /* synthetic */ int switching_field;

    public ViewGroupKt$special$$inlined$Sequence$1(Object obj, int i) {
        this.switching_field = i;
        this.ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined = obj;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Iterator, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object, kotlin.jvm.functions.Function2] */
    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return this.ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined;
                        }
                        SequenceBuilderIterator sequenceBuilderIterator = new SequenceBuilderIterator();
                        sequenceBuilderIterator.nextStep = OnDevicePoseDetectionLogEvent.createCoroutineUnintercepted(this.ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined, sequenceBuilderIterator, sequenceBuilderIterator);
                        return sequenceBuilderIterator;
                    }
                    return this.ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined.iterator();
                }
                return new S2SPopupParsedIntentKt$items$1$iterator$1(this.ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined, 0);
            }
            return new S2SPopupParsedIntentKt$items$1$iterator$1(this.ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined, 1);
        }
        return new TreeIterator(new ViewGroupKt$special$$inlined$Sequence$1(this.ViewGroupKt$special$$inlined$Sequence$1$ar$$this_descendants$inlined, 1).iterator(), ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$8a2ce234_0);
    }
}
