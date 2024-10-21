package com.google.android.accessibility.braille.brailledisplay.controller;

import com.google.android.accessibility.braille.brailledisplay.controller.ContentHelper;
import com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WrapStrategy;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ContentHelper$$ExternalSyntheticLambda0 implements ContentHelper.WrapStrategyRetriever {
    public final /* synthetic */ Object ContentHelper$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public ContentHelper$$ExternalSyntheticLambda0(CellsContentManager cellsContentManager, int i) {
        this.switching_field = i;
        this.ContentHelper$$ExternalSyntheticLambda0$ar$f$0 = cellsContentManager;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.ContentHelper.WrapStrategyRetriever
    public final WrapStrategy getWrapStrategy() {
        if (this.switching_field != 0) {
            if (((CellsContentManager) this.ContentHelper$$ExternalSyntheticLambda0$ar$f$0).imeStatusProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.isImeOpen()) {
                return ((CellsContentManager) this.ContentHelper$$ExternalSyntheticLambda0$ar$f$0).editingWrapStrategy;
            }
            return ((CellsContentManager) this.ContentHelper$$ExternalSyntheticLambda0$ar$f$0).preferredWrapStrategy;
        }
        int i = ContentHelper.ContentHelper$ar$NoOp;
        return (WrapStrategy) this.ContentHelper$$ExternalSyntheticLambda0$ar$f$0;
    }

    public /* synthetic */ ContentHelper$$ExternalSyntheticLambda0(WrapStrategy wrapStrategy, int i) {
        this.switching_field = i;
        this.ContentHelper$$ExternalSyntheticLambda0$ar$f$0 = wrapStrategy;
    }
}
