package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.common.moduleinstall.internal.ApiFeatureRequest;
import com.google.android.gms.common.moduleinstall.internal.InternalModuleInstallClient;
import com.google.android.gms.moduleinstall.Features;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ImmutableMap;
import io.grpc.internal.RetriableStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OptionalModuleUtils {
    public static final Feature[] EMPTY_FEATURES = new Feature[0];
    public static final Feature FEATURE_BARCODE;
    public static final Feature FEATURE_CUSTOM_ICA;
    public static final Feature FEATURE_FACE;
    public static final Feature FEATURE_ICA;
    public static final Feature FEATURE_LANGID;
    public static final Feature FEATURE_MLKIT_BARCODE_UI;
    public static final Feature FEATURE_NLCLASSIFIER;
    public static final Feature FEATURE_OCR;
    public static final Feature FEATURE_SMART_REPLY;
    public static final Feature FEATURE_TFLITE_DYNAMITE;
    public static final ImmutableMap MODULE_NAME_FEATURE_MAP;

    static {
        Feature feature = new Feature("vision.barcode", 1L);
        FEATURE_BARCODE = feature;
        Feature feature2 = new Feature("vision.custom.ica", 1L);
        FEATURE_CUSTOM_ICA = feature2;
        Feature feature3 = new Feature("vision.face", 1L);
        FEATURE_FACE = feature3;
        Feature feature4 = new Feature("vision.ica", 1L);
        FEATURE_ICA = feature4;
        Feature feature5 = new Feature("vision.ocr", 1L);
        FEATURE_OCR = feature5;
        Feature feature6 = new Feature("mlkit.langid", 1L);
        FEATURE_LANGID = feature6;
        Feature feature7 = new Feature("mlkit.nlclassifier", 1L);
        FEATURE_NLCLASSIFIER = feature7;
        Feature feature8 = new Feature("tflite_dynamite", 1L);
        FEATURE_TFLITE_DYNAMITE = feature8;
        Feature feature9 = new Feature("mlkit.barcode.ui", 1L);
        FEATURE_MLKIT_BARCODE_UI = feature9;
        Feature feature10 = new Feature("mlkit.smartreply", 1L);
        FEATURE_SMART_REPLY = feature10;
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0("barcode", feature);
        builder.put$ar$ds$de9b9d28_0("custom_ica", feature2);
        builder.put$ar$ds$de9b9d28_0("face", feature3);
        builder.put$ar$ds$de9b9d28_0("ica", feature4);
        builder.put$ar$ds$de9b9d28_0("ocr", feature5);
        builder.put$ar$ds$de9b9d28_0("langid", feature6);
        builder.put$ar$ds$de9b9d28_0("nlclassifier", feature7);
        builder.put$ar$ds$de9b9d28_0("tflite_dynamite", feature8);
        builder.put$ar$ds$de9b9d28_0("barcode_ui", feature9);
        builder.put$ar$ds$de9b9d28_0("smart_reply", feature10);
        MODULE_NAME_FEATURE_MAP = builder.buildOrThrow();
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.vision.barcode", feature);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.vision.custom.ica", feature2);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.vision.face", feature3);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.vision.ica", feature4);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.vision.ocr", feature5);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.mlkit.langid", feature6);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.mlkit.nlclassifier", feature7);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.tflite_dynamite", feature8);
        builder2.put$ar$ds$de9b9d28_0("com.google.android.gms.mlkit_smartreply", feature10);
        builder2.buildOrThrow();
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.util.List, java.lang.Object] */
    public static void requestDownload(Context context, final Feature[] featureArr) {
        Task doRead;
        RetriableStream.HedgingPlan hedgingPlan = new RetriableStream.HedgingPlan();
        hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis.add(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.OptionalModuleUtils$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.common.api.OptionalModuleApi
            public final Feature[] getOptionalFeatures() {
                Feature[] featureArr2 = OptionalModuleUtils.EMPTY_FEATURES;
                return featureArr;
            }
        });
        RetriableStream.HedgingPlan hedgingPlan2 = new RetriableStream.HedgingPlan((List) hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis, true);
        InternalModuleInstallClient internalModuleInstallClient = new InternalModuleInstallClient(context);
        ?? r5 = hedgingPlan2.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis;
        TreeSet treeSet = new TreeSet(ApiFeatureRequest.FEATURE_COMPARATOR);
        Iterator it = r5.iterator();
        while (it.hasNext()) {
            Collections.addAll(treeSet, ((OptionalModuleApi) it.next()).getOptionalFeatures());
        }
        ApiFeatureRequest apiFeatureRequest = new ApiFeatureRequest(new ArrayList(treeSet), true, null, null);
        if (apiFeatureRequest.apiFeatures.isEmpty()) {
            doRead = SpannableUtils$NonCopyableTextSpan.forResult(new ModuleInstallResponse(0, false));
        } else {
            TaskApiCall.Builder builder = new TaskApiCall.Builder();
            builder.TaskApiCall$Builder$ar$features = new Feature[]{Features.MODULEINSTALL};
            builder.autoResolveMissingFeatures = true;
            builder.methodKey = 27304;
            builder.TaskApiCall$Builder$ar$execute = new ClearcutLoggerApiImpl$$ExternalSyntheticLambda2(internalModuleInstallClient, apiFeatureRequest, 2);
            doRead = internalModuleInstallClient.doRead(builder.build());
        }
        doRead.addOnFailureListener$ar$ds(new OptionalModuleUtils$$ExternalSyntheticLambda1(0));
    }
}
