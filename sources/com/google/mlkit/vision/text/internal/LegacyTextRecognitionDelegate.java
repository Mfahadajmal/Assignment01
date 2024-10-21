package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;
import com.google.android.gms.vision.text.internal.client.BoundingBoxParcel;
import com.google.android.gms.vision.text.internal.client.INativeTextRecognizer;
import com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator;
import com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator$Stub$Proxy;
import com.google.android.gms.vision.text.internal.client.LineBoxParcel;
import com.google.android.gms.vision.text.internal.client.TextRecognizerOptions;
import com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.logging.schema.AggregatedAutoMLImageLabelingInferenceLogEvent;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.text.Text$TextBlock;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LegacyTextRecognitionDelegate implements TextRecognitionDelegate {
    private final Context context;
    private INativeTextRecognizer nativeTextRecognizer;
    private boolean optionalModuleDownloadRequested;
    private final TextRecognizerOptions options = new TextRecognizerOptions(null);

    public LegacyTextRecognitionDelegate(Context context) {
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.mlkit.vision.text.internal.TextRecognitionDelegate
    public final void load() {
        INativeTextRecognizerCreator iNativeTextRecognizerCreator$Stub$Proxy;
        if (this.nativeTextRecognizer == null) {
            try {
                IBinder instantiate = DynamiteModule.load(this.context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.vision.dynamite").instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
                if (instantiate == null) {
                    iNativeTextRecognizerCreator$Stub$Proxy = null;
                } else {
                    IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
                    if (queryLocalInterface instanceof INativeTextRecognizerCreator) {
                        iNativeTextRecognizerCreator$Stub$Proxy = (INativeTextRecognizerCreator) queryLocalInterface;
                    } else {
                        iNativeTextRecognizerCreator$Stub$Proxy = new INativeTextRecognizerCreator$Stub$Proxy(instantiate);
                    }
                }
                INativeTextRecognizer newTextRecognizer = iNativeTextRecognizerCreator$Stub$Proxy.newTextRecognizer(new IObjectWrapper.Stub(this.context), this.options);
                this.nativeTextRecognizer = newTextRecognizer;
                if (newTextRecognizer == null && !this.optionalModuleDownloadRequested) {
                    Context context = this.context;
                    Feature[] featureArr = OptionalModuleUtils.EMPTY_FEATURES;
                    ImmutableList of = ImmutableList.of((Object) "ocr");
                    int i = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
                    if (GooglePlayServicesUtilLight.getApkVersion(context) >= 221500000) {
                        ImmutableMap immutableMap = OptionalModuleUtils.MODULE_NAME_FEATURE_MAP;
                        Feature[] featureArr2 = new Feature[((RegularImmutableList) of).size];
                        for (int i2 = 0; i2 < ((RegularImmutableList) of).size; i2++) {
                            Feature feature = (Feature) immutableMap.get(of.get(i2));
                            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(feature);
                            featureArr2[i2] = feature;
                        }
                        OptionalModuleUtils.requestDownload(context, featureArr2);
                    } else {
                        Intent intent = new Intent();
                        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
                        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
                        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", of));
                        intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
                        context.sendBroadcast(intent);
                    }
                    this.optionalModuleDownloadRequested = true;
                }
            } catch (RemoteException e) {
                throw new MlKitException("Failed to create legacy text recognizer.", e);
            } catch (DynamiteModule.LoadingException e2) {
                throw new MlKitException("Failed to load deprecated vision dynamite module.", e2);
            }
        }
    }

    @Override // com.google.mlkit.vision.text.internal.TextRecognitionDelegate
    public final void release() {
        INativeTextRecognizer iNativeTextRecognizer = this.nativeTextRecognizer;
        if (iNativeTextRecognizer != null) {
            try {
                iNativeTextRecognizer.finalizeRecognizer();
            } catch (RemoteException e) {
                Log.e("LegacyTextDelegate", "Failed to release legacy text recognizer.", e);
            }
            this.nativeTextRecognizer = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.mlkit.vision.text.internal.TextRecognitionDelegate
    public final MultiFlavorDetectorCreator run$ar$class_merging(InputImage inputImage) {
        String str;
        int i;
        if (this.nativeTextRecognizer == null) {
            load();
        }
        if (this.nativeTextRecognizer != null) {
            IObjectWrapper.Stub stub = new IObjectWrapper.Stub(inputImage.bitmap);
            FrameMetadataParcel frameMetadataParcel = new FrameMetadataParcel(inputImage.width, inputImage.height, 0, 0L, 0);
            try {
                INativeTextRecognizer iNativeTextRecognizer = this.nativeTextRecognizer;
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(iNativeTextRecognizer);
                LineBoxParcel[] recognizeBitmap = iNativeTextRecognizer.recognizeBitmap(stub, frameMetadataParcel);
                SparseArray sparseArray = new SparseArray();
                int i2 = 0;
                for (LineBoxParcel lineBoxParcel : recognizeBitmap) {
                    SparseArray sparseArray2 = (SparseArray) sparseArray.get(lineBoxParcel.blockId);
                    if (sparseArray2 == null) {
                        sparseArray2 = new SparseArray();
                        sparseArray.append(lineBoxParcel.blockId, sparseArray2);
                    }
                    sparseArray2.append(lineBoxParcel.orderWithinBlock, lineBoxParcel);
                }
                ImmutableList.Builder builder = new ImmutableList.Builder();
                int i3 = 0;
                while (i3 < sparseArray.size()) {
                    SparseArray sparseArray3 = (SparseArray) sparseArray.valueAt(i3);
                    ImmutableList.Builder builder2 = new ImmutableList.Builder();
                    for (int i4 = i2; i4 < sparseArray3.size(); i4++) {
                        builder2.add$ar$ds$4f674a09_0((LineBoxParcel) sparseArray3.valueAt(i4));
                    }
                    ImmutableList build = builder2.build();
                    List transform = ContextDataProvider.transform(build, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(9));
                    BoundingBoxParcel boundingBoxParcel = ((LineBoxParcel) build.get(i2)).box;
                    UnmodifiableListIterator it = build.iterator();
                    int i5 = Integer.MIN_VALUE;
                    int i6 = Integer.MAX_VALUE;
                    int i7 = Integer.MAX_VALUE;
                    int i8 = Integer.MIN_VALUE;
                    while (it.hasNext()) {
                        BoundingBoxParcel boundingBoxParcel2 = ((LineBoxParcel) it.next()).box;
                        int i9 = -boundingBoxParcel.left;
                        int i10 = -boundingBoxParcel.top;
                        SparseArray sparseArray4 = sparseArray;
                        double sin = Math.sin(Math.toRadians(boundingBoxParcel.angleDegrees));
                        UnmodifiableListIterator unmodifiableListIterator = it;
                        double cos = Math.cos(Math.toRadians(boundingBoxParcel.angleDegrees));
                        int i11 = i3;
                        ImmutableList.Builder builder3 = builder;
                        List list = transform;
                        Point point = new Point(boundingBoxParcel2.left, boundingBoxParcel2.top);
                        point.offset(i9, i10);
                        int i12 = (int) ((r3[0].x * cos) + (r3[0].y * sin));
                        r3[0].x = i12;
                        int i13 = (int) (((-r3[0].x) * sin) + (r3[0].y * cos));
                        r3[0].y = i13;
                        Point[] pointArr = {point, new Point(boundingBoxParcel2.width + i12, i13), new Point(boundingBoxParcel2.width + i12, boundingBoxParcel2.height + i13), new Point(i12, i13 + boundingBoxParcel2.height)};
                        i8 = i8;
                        i5 = i5;
                        for (int i14 = 0; i14 < 4; i14++) {
                            Point point2 = pointArr[i14];
                            i6 = Math.min(i6, point2.x);
                            i5 = Math.max(i5, point2.x);
                            i7 = Math.min(i7, point2.y);
                            i8 = Math.max(i8, point2.y);
                        }
                        sparseArray = sparseArray4;
                        it = unmodifiableListIterator;
                        i3 = i11;
                        builder = builder3;
                        transform = list;
                    }
                    ImmutableList.Builder builder4 = builder;
                    SparseArray sparseArray5 = sparseArray;
                    int i15 = i3;
                    int i16 = i5;
                    int i17 = i8;
                    List list2 = transform;
                    int i18 = boundingBoxParcel.left;
                    int i19 = boundingBoxParcel.top;
                    double sin2 = Math.sin(Math.toRadians(boundingBoxParcel.angleDegrees));
                    double cos2 = Math.cos(Math.toRadians(boundingBoxParcel.angleDegrees));
                    Point point3 = new Point(i6, i7);
                    Point point4 = new Point(i16, i7);
                    Point point5 = new Point(i16, i17);
                    Point point6 = new Point(i6, i17);
                    Point[] pointArr2 = {point3, point4, point5, point6};
                    int i20 = 0;
                    for (int i21 = 4; i20 < i21; i21 = 4) {
                        pointArr2[i20].x = (int) ((pointArr2[i20].x * cos2) - (pointArr2[i20].y * sin2));
                        pointArr2[i20].y = (int) ((pointArr2[i20].x * sin2) + (pointArr2[i20].y * cos2));
                        pointArr2[i20].offset(i18, i19);
                        i20++;
                        sin2 = sin2;
                    }
                    List asList = Arrays.asList(pointArr2);
                    String join = LegacyTextRecognitionDelegateUtils.TEXT_JOINER.join(ContextDataProvider.transform(list2, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(10)));
                    Rect computeBoundingBoxFromCornerPoints = AggregatedAutoMLImageLabelingInferenceLogEvent.computeBoundingBoxFromCornerPoints(asList);
                    HashMap hashMap = new HashMap();
                    Iterator it2 = list2.iterator();
                    while (it2.hasNext()) {
                        String str2 = ((Text$TextBlock) it2.next()).recognizedLanguage;
                        if (hashMap.containsKey(str2)) {
                            i = ((Integer) hashMap.get(str2)).intValue();
                        } else {
                            i = 0;
                        }
                        hashMap.put(str2, Integer.valueOf(i + 1));
                    }
                    Set entrySet = hashMap.entrySet();
                    if (!entrySet.isEmpty()) {
                        String str3 = (String) ((Map.Entry) Collections.max(entrySet, LegacyTextRecognitionDelegateUtils.languageComparator)).getKey();
                        if (!ContextDataProvider.stringIsNullOrEmpty(str3)) {
                            str = str3;
                            builder4.add$ar$ds$4f674a09_0(new Text$TextBlock(join, computeBoundingBoxFromCornerPoints, asList, str, list2));
                            i3 = i15 + 1;
                            builder = builder4;
                            sparseArray = sparseArray5;
                            i2 = 0;
                        }
                    }
                    str = "und";
                    builder4.add$ar$ds$4f674a09_0(new Text$TextBlock(join, computeBoundingBoxFromCornerPoints, asList, str, list2));
                    i3 = i15 + 1;
                    builder = builder4;
                    sparseArray = sparseArray5;
                    i2 = 0;
                }
                ImmutableList build2 = builder.build();
                LegacyTextRecognitionDelegateUtils.TEXT_JOINER.join(ContextDataProvider.transform(build2, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(8)));
                return new MultiFlavorDetectorCreator(build2);
            } catch (RemoteException e) {
                throw new MlKitException("Failed to run legacy text recognizer.", e);
            }
        }
        throw new MlKitException("Waiting for the text recognition module to be downloaded. Please wait.", 14);
    }
}
