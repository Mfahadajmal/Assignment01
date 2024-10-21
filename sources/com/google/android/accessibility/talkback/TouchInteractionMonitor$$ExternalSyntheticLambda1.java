package com.google.android.accessibility.talkback;

import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.scone.proto.Service$SurveyTriggerResponse;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TouchInteractionMonitor$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Object TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0;
    public final /* synthetic */ Object TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1;
    public final /* synthetic */ long f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TouchInteractionMonitor$$ExternalSyntheticLambda1(TouchInteractionMonitor touchInteractionMonitor, String str, long j, int i) {
        this.switching_field = i;
        this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0 = touchInteractionMonitor;
        this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1 = str;
        this.f$2 = j;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.android.libraries.surveys.internal.network.provider.NetworkCaller$1] */
    @Override // java.lang.Runnable
    public final void run() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                Object obj = this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1;
                                final long j = this.f$2;
                                final NetworkCaller networkCaller = (NetworkCaller) this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0;
                                final Service$SurveyTriggerResponse service$SurveyTriggerResponse = (Service$SurveyTriggerResponse) obj;
                                
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0022: INVOKE 
                                      (wrap:??:0x001f: CONSTRUCTOR 
                                      (r3v5 'networkCaller' com.google.android.libraries.surveys.internal.network.provider.NetworkCaller A[DONT_INLINE])
                                      (r1v5 'j' long A[DONT_INLINE])
                                      (r0v8 'service$SurveyTriggerResponse' com.google.scone.proto.Service$SurveyTriggerResponse A[DONT_INLINE])
                                     A[MD:(com.google.android.libraries.surveys.internal.network.provider.NetworkCaller, long, com.google.scone.proto.Service$SurveyTriggerResponse):void (m), WRAPPED] (LINE:32) call: com.google.android.libraries.surveys.internal.network.provider.NetworkCaller.1.<init>(com.google.android.libraries.surveys.internal.network.provider.NetworkCaller, long, com.google.scone.proto.Service$SurveyTriggerResponse):void type: CONSTRUCTOR)
                                     VIRTUAL call: com.google.android.libraries.surveys.internal.network.provider.NetworkCaller.1.start():android.os.CountDownTimer A[MD:():android.os.CountDownTimer (c)] (LINE:35) in method: com.google.android.accessibility.talkback.TouchInteractionMonitor$$ExternalSyntheticLambda1.run():void, file: classes.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.android.libraries.surveys.internal.network.provider.NetworkCaller, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:304)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:781)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                    	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:97)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:852)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                    	... 63 more
                                    */
                                /*
                                    this = this;
                                    int r0 = r5.switching_field
                                    if (r0 == 0) goto L50
                                    r1 = 1
                                    if (r0 == r1) goto L42
                                    r1 = 2
                                    if (r0 == r1) goto L3c
                                    r1 = 3
                                    if (r0 == r1) goto L34
                                    r1 = 4
                                    if (r0 == r1) goto L2e
                                    r1 = 5
                                    if (r0 == r1) goto L26
                                    java.lang.Object r0 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1
                                    long r1 = r5.f$2
                                    java.lang.Object r3 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0
                                    com.google.android.libraries.surveys.internal.network.provider.NetworkCaller$1 r4 = new com.google.android.libraries.surveys.internal.network.provider.NetworkCaller$1
                                    com.google.android.libraries.surveys.internal.network.provider.NetworkCaller r3 = (com.google.android.libraries.surveys.internal.network.provider.NetworkCaller) r3
                                    com.google.scone.proto.Service$SurveyTriggerResponse r0 = (com.google.scone.proto.Service$SurveyTriggerResponse) r0
                                    r4.<init>(r3, r1, r0)
                                    r4.start()
                                    return
                                L26:
                                    long r0 = r5.f$2
                                    java.lang.Object r2 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1
                                    r2.onDownloadProgress$ar$ds(r0)
                                    return
                                L2e:
                                    java.lang.Object r0 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1
                                    r0.onDownloadStarted$ar$ds()
                                    return
                                L34:
                                    long r0 = r5.f$2
                                    java.lang.Object r2 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1
                                    r2.onDownloadProgress$ar$ds(r0)
                                    return
                                L3c:
                                    java.lang.Object r0 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1
                                    r0.onDownloadStarted$ar$ds()
                                    return
                                L42:
                                    long r0 = r5.f$2
                                    java.lang.Object r2 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1
                                    java.lang.Object r3 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0
                                    com.google.android.accessibility.talkback.TouchInteractionMonitor r3 = (com.google.android.accessibility.talkback.TouchInteractionMonitor) r3
                                    java.lang.String r2 = (java.lang.String) r2
                                    r3.requestDelegatingFromMainThread(r2, r0)
                                    return
                                L50:
                                    long r0 = r5.f$2
                                    java.lang.Object r2 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1
                                    java.lang.Object r3 = r5.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0
                                    com.google.android.accessibility.talkback.TouchInteractionMonitor r3 = (com.google.android.accessibility.talkback.TouchInteractionMonitor) r3
                                    java.lang.String r2 = (java.lang.String) r2
                                    r3.requestTouchExplorationFromMainThread(r2, r0)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TouchInteractionMonitor$$ExternalSyntheticLambda1.run():void");
                            }

                            public /* synthetic */ TouchInteractionMonitor$$ExternalSyntheticLambda1(DownloadCallback downloadCallback, AiFeature aiFeature, long j, int i) {
                                this.switching_field = i;
                                this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1 = downloadCallback;
                                this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0 = aiFeature;
                                this.f$2 = j;
                            }

                            public /* synthetic */ TouchInteractionMonitor$$ExternalSyntheticLambda1(NetworkCaller networkCaller, long j, Service$SurveyTriggerResponse service$SurveyTriggerResponse, int i) {
                                this.switching_field = i;
                                this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$0 = networkCaller;
                                this.f$2 = j;
                                this.TouchInteractionMonitor$$ExternalSyntheticLambda1$ar$f$1 = service$SurveyTriggerResponse;
                            }
                        }
