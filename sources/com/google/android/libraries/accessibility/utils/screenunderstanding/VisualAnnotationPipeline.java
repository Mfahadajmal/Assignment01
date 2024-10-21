package com.google.android.libraries.accessibility.utils.screenunderstanding;

import android.os.Handler;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.libraries.vision.visionkit.pipeline.Pipeline;
import com.google.android.libraries.vision.visionkit.pipeline.Results;
import com.google.android.libraries.vision.visionkit.pipeline.VisualAnnotationResults;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ExecutionList;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protos.research.socrates.Visual$UIComponent;
import com.google.protos.research.socrates.Visual$VisualAnnotation;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VisualAnnotationPipeline extends Pipeline {
    public FloatingActionButton.ShadowDelegateImpl listener$ar$class_merging$643a5d1f_0$ar$class_merging$ar$class_merging;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public VisualAnnotationPipeline() {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.accessibility.utils.screenunderstanding.VisualAnnotationPipeline.<init>():void");
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.Pipeline, com.google.android.libraries.vision.visionkit.pipeline.PipelineResultsCallback
    public final void onResult(Results results) {
        Object fromFieldSetType;
        if (this.listener$ar$class_merging$643a5d1f_0$ar$class_merging$ar$class_merging != null) {
            GeneratedMessageLite.GeneratedExtension checkIsLite = GeneratedMessageLite.checkIsLite(VisualAnnotationResults.ext);
            results.verifyExtensionContainingType(checkIsLite);
            if (results.extensions.hasField$ar$class_merging(checkIsLite.descriptor)) {
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.listener$ar$class_merging$643a5d1f_0$ar$class_merging$ar$class_merging;
                GeneratedMessageLite.GeneratedExtension checkIsLite2 = GeneratedMessageLite.checkIsLite(VisualAnnotationResults.ext);
                results.verifyExtensionContainingType(checkIsLite2);
                Object field$ar$class_merging = results.extensions.getField$ar$class_merging(checkIsLite2.descriptor);
                if (field$ar$class_merging == null) {
                    fromFieldSetType = checkIsLite2.defaultValue;
                } else {
                    fromFieldSetType = checkIsLite2.fromFieldSetType(field$ar$class_merging);
                }
                Visual$VisualAnnotation visual$VisualAnnotation = ((VisualAnnotationResults) fromFieldSetType).visualAnnotation_;
                if (visual$VisualAnnotation == null) {
                    visual$VisualAnnotation = Visual$VisualAnnotation.DEFAULT_INSTANCE;
                }
                Object obj = shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                ImmutableList.Builder builder = new ImmutableList.Builder();
                Iterator<E> it = visual$VisualAnnotation.uiComponent_.iterator();
                while (it.hasNext()) {
                    builder.add$ar$ds$4f674a09_0(new Annotation((Visual$UIComponent) it.next()));
                }
                ExecutionList.RunnableExecutorPair runnableExecutorPair = (ExecutionList.RunnableExecutorPair) obj;
                if (runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next != null) {
                    ((Handler) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor).post(new ListMenuManager$$ExternalSyntheticLambda3(obj, builder, 20));
                }
            }
        }
    }
}
