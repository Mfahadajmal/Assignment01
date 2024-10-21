package com.google.android.accessibility.talkback.actor.gemini;

import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import com.google.common.collect.ImmutableMap;
import com.google.search.mdi.aratea.proto.FilteredData;
import com.google.search.mdi.aratea.proto.FilteredReason;
import java.util.function.ToIntFunction;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ArateaEndpoint$$ExternalSyntheticLambda0 implements ToIntFunction {
    public final /* synthetic */ Object ArateaEndpoint$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ArateaEndpoint$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.ArateaEndpoint$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, java.util.function.Function] */
    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        Object apply;
        if (this.switching_field != 0) {
            apply = this.ArateaEndpoint$$ExternalSyntheticLambda0$ar$f$0.apply((MultitouchHandler.PointerWithHistory) obj);
            return Integer.signum(((Float) apply).intValue());
        }
        FilteredReason forNumber = FilteredReason.forNumber(((FilteredData) obj).reason_);
        if (forNumber == null) {
            forNumber = FilteredReason.FILTERED_REASON_UNSPECIFIED;
        }
        return ((Integer) ((ImmutableMap) this.ArateaEndpoint$$ExternalSyntheticLambda0$ar$f$0).get(forNumber)).intValue();
    }
}
