package com.google.android.accessibility.selecttospeak.overlayui;

import androidx.lifecycle.LifecycleCoroutineScope;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UIDelayedJobScheduler {
    public Job autoExpandJob;
    private final Function0 collapseControlPanel;
    private final ControlOverlays controlOverlays;
    public Job hideAllExceptJob;
    public final LifecycleCoroutineScope scope;

    public UIDelayedJobScheduler(LifecycleCoroutineScope lifecycleCoroutineScope, ControlOverlays controlOverlays, Function0 function0) {
        this.scope = lifecycleCoroutineScope;
        this.controlOverlays = controlOverlays;
        this.collapseControlPanel = function0;
    }

    public final void cancelAutoCollapseJob() {
        Job job = this.autoExpandJob;
        if (job != null) {
            job.cancel(null);
        }
        this.autoExpandJob = null;
    }

    public final void cancelHideOverlayJob() {
        Job job = this.hideAllExceptJob;
        if (job != null) {
            job.cancel(null);
        }
        this.hideAllExceptJob = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object collapse(long r5, kotlin.coroutines.Continuation r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$collapse$1
            if (r0 == 0) goto L13
            r0 = r7
            com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$collapse$1 r0 = (com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$collapse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$collapse$1 r0 = new com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$collapse$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler r5 = r0.L$0$ar$dn$730981eb_0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            r0.L$0$ar$dn$730981eb_0 = r4
            r0.label = r3
            java.lang.Object r5 = kotlinx.coroutines.DebugStringsKt.delay(r5, r0)
            if (r5 == r1) goto L55
            r5 = r4
        L3f:
            com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays r6 = r5.controlOverlays
            boolean r7 = r6.isCollapsed()
            if (r7 != 0) goto L52
            com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays$OverlayTypes r6 = r6.foregroundOverlayType
            com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays$OverlayTypes r7 = com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays.OverlayTypes.EXPANDABLE
            if (r6 != r7) goto L52
            kotlin.jvm.functions.Function0 r5 = r5.collapseControlPanel
            r5.invoke()
        L52:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L55:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler.collapse(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object hideAllExcept(com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay r5, long r6, kotlin.coroutines.Continuation r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$hideAllExcept$1
            if (r0 == 0) goto L13
            r0 = r8
            com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$hideAllExcept$1 r0 = (com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$hideAllExcept$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$hideAllExcept$1 r0 = new com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$hideAllExcept$1
            r0.<init>(r4, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r5 = r0.L$1
            com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler r6 = r0.L$0$ar$dn$129fa825_0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
            goto L43
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r8)
            r0.L$0$ar$dn$129fa825_0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.DebugStringsKt.delay(r6, r0)
            if (r6 == r1) goto L81
            r6 = r4
        L43:
            com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays r6 = r6.controlOverlays
            java.lang.Object[] r7 = new java.lang.Object[r3]
            r8 = 0
            r7[r8] = r5
            java.lang.String r8 = "ControlOverlays"
            java.lang.String r0 = "hideAllExcept %s"
            com.google.android.libraries.accessibility.utils.log.LogUtils.v(r8, r0, r7)
            com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay r7 = r6.triggerButtonOverlay
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r5)
            if (r7 != 0) goto L60
            com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay r7 = r6.triggerButtonOverlay
            if (r7 == 0) goto L60
            r7.hide()
        L60:
            com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay r7 = r6.collapsedOverlay
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r5)
            if (r7 != 0) goto L6f
            com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay r7 = r6.collapsedOverlay
            if (r7 == 0) goto L6f
            r7.hide()
        L6f:
            com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay r7 = r6.expandableOverlay
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r5)
            if (r5 != 0) goto L7e
            com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay r5 = r6.expandableOverlay
            if (r5 == 0) goto L7e
            r5.hide()
        L7e:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L81:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler.hideAllExcept(com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
