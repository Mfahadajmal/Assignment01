package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BufferedChannelKt$createSegmentFunction$1 extends FunctionReference implements Function2 {
    public static final BufferedChannelKt$createSegmentFunction$1 INSTANCE = new BufferedChannelKt$createSegmentFunction$1();

    public BufferedChannelKt$createSegmentFunction$1() {
        super(2, BufferedChannelKt.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        ConcurrentLinkedListNode concurrentLinkedListNode = (ConcurrentLinkedListNode) obj2;
        return new ConcurrentLinkedListNode(((Number) obj).longValue(), concurrentLinkedListNode, concurrentLinkedListNode.getChannel(), 0);
    }
}
