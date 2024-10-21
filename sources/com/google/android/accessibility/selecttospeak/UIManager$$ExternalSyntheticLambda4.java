package com.google.android.accessibility.selecttospeak;

import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction1;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class UIManager$$ExternalSyntheticLambda4 implements VoidAsUnit$VoidFunction1 {
    public final /* synthetic */ UIManager f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ UIManager$$ExternalSyntheticLambda4(UIManager uIManager, int i) {
        this.switching_field = i;
        this.f$0 = uIManager;
    }

    @Override // com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction1
    public final void invoke(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                this.f$0.updateTriggerButtonAppearance(((Boolean) obj).booleanValue());
                return;
            }
            RetriableStream.HedgingPlan hedgingPlan = this.f$0.uiInflationController$ar$class_merging$ar$class_merging$ar$class_merging;
            LogUtils.v("ControlOverlayInflater", "afterHide: isInflated: %s onDemand: %s", Boolean.valueOf(((RetryingNameResolver.ResolutionResultListener) hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis).isViewInflated()), Boolean.valueOf(hedgingPlan.isHedgeable));
            if (hedgingPlan.isHedgeable) {
                ((RetryingNameResolver.ResolutionResultListener) hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis).releaseView();
                return;
            }
            return;
        }
        this.f$0.setForegroundOverlay((ControlOverlays.OverlayTypes) obj);
    }
}
