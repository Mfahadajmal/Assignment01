package com.google.android.libraries.phenotype.client.stable;

import android.graphics.Rect;
import android.util.Log;
import com.google.android.gms.vision.text.internal.client.LineBoxParcel;
import com.google.android.gms.vision.text.internal.client.WordBoxParcel;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.phenotype.client.api.PhenotypeRuntimeException;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$CredentialEncryptedStorageInfo;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$StorageInfos;
import com.google.common.base.Function;
import com.google.common.base.Pair;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.AggregatedAutoMLImageLabelingInferenceLogEvent;
import com.google.mlkit.vision.text.Text$TextBase;
import com.google.mlkit.vision.text.Text$TextBlock;
import com.google.mlkit.vision.text.aidls.TextBlockParcel;
import com.google.mlkit.vision.text.aidls.TextElementParcel;
import com.google.mlkit.vision.text.aidls.TextLineParcel;
import com.google.mlkit.vision.text.aidls.TextSymbolParcel;
import com.google.mlkit.vision.text.internal.LegacyTextRecognitionDelegateUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3 implements Function {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(int i) {
        this.switching_field = i;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.util.concurrent.ConcurrentMap] */
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        String str = "und";
        String str2 = "";
        switch (this.switching_field) {
            case 0:
                DefaultExperimentTokenDecorator.get();
                return (ExperimentTokenData) ExperimentTokenDecoratorImpl.deviceScopedConfigPackageKeyMap.get((String) obj);
            case 1:
                return Integer.valueOf(Log.w("AccountRemovedRecv", "Failed to remove account snapshot: ", (IOException) obj));
            case 2:
                Set set = (Set) obj;
                StatsStorage statsStorage = FlagStore.SHARED_REGISTRY$ar$class_merging;
                boolean z = false;
                if (set != null && !set.isEmpty()) {
                    for (Map.Entry entry : statsStorage.StatsStorage$ar$storage.entrySet()) {
                        if (set.contains(((Pair) entry.getKey()).first)) {
                            boolean z2 = ((FlagStore) entry.getValue()).canInvalidate;
                            z = true;
                        }
                    }
                }
                return Boolean.valueOf(z);
            case 3:
                PhenotypeRuntimeException phenotypeRuntimeException = (PhenotypeRuntimeException) obj;
                if (phenotypeRuntimeException.errorCode == 29514) {
                    SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) StorageInfoProto$StorageInfos.DEFAULT_INSTANCE.createBuilder();
                    SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) StorageInfoProto$CredentialEncryptedStorageInfo.DEFAULT_INSTANCE.createBuilder();
                    long currentTimeMillis = System.currentTimeMillis();
                    builder2.copyOnWrite();
                    StorageInfoProto$CredentialEncryptedStorageInfo storageInfoProto$CredentialEncryptedStorageInfo = (StorageInfoProto$CredentialEncryptedStorageInfo) builder2.instance;
                    storageInfoProto$CredentialEncryptedStorageInfo.bitField0_ |= 8;
                    storageInfoProto$CredentialEncryptedStorageInfo.lastFetchTimestampMillis_ = currentTimeMillis;
                    builder.copyOnWrite();
                    StorageInfoProto$StorageInfos storageInfoProto$StorageInfos = (StorageInfoProto$StorageInfos) builder.instance;
                    StorageInfoProto$CredentialEncryptedStorageInfo storageInfoProto$CredentialEncryptedStorageInfo2 = (StorageInfoProto$CredentialEncryptedStorageInfo) builder2.build();
                    storageInfoProto$CredentialEncryptedStorageInfo2.getClass();
                    storageInfoProto$StorageInfos.credentialEncryptedStorageInfo_ = storageInfoProto$CredentialEncryptedStorageInfo2;
                    storageInfoProto$StorageInfos.bitField0_ |= 1;
                    return (StorageInfoProto$StorageInfos) builder.build();
                }
                throw phenotypeRuntimeException;
            case 4:
                return new Text$TextBlock((TextBlockParcel) obj);
            case 5:
                return new Text$TextBase((TextSymbolParcel) obj);
            case 6:
                return new Text$TextBase((TextElementParcel) obj);
            case 7:
                TextLineParcel textLineParcel = (TextLineParcel) obj;
                float f = textLineParcel.confidence;
                float f2 = textLineParcel.angle;
                return new Text$TextBlock(textLineParcel);
            case 8:
                return ((Text$TextBlock) obj).getTextInternal();
            case 9:
                LineBoxParcel lineBoxParcel = (LineBoxParcel) obj;
                Comparator comparator = LegacyTextRecognitionDelegateUtils.languageComparator;
                List computeCornerPointsFromBoundingBox = AggregatedAutoMLImageLabelingInferenceLogEvent.computeCornerPointsFromBoundingBox(lineBoxParcel.box);
                if (!ContextDataProvider.stringIsNullOrEmpty(lineBoxParcel.utf8String)) {
                    str2 = lineBoxParcel.utf8String;
                }
                String str3 = str2;
                Rect computeBoundingBoxFromCornerPoints = AggregatedAutoMLImageLabelingInferenceLogEvent.computeBoundingBoxFromCornerPoints(computeCornerPointsFromBoundingBox);
                if (!ContextDataProvider.stringIsNullOrEmpty(lineBoxParcel.language)) {
                    str = lineBoxParcel.language;
                }
                String str4 = str;
                List transform = ContextDataProvider.transform(Arrays.asList(lineBoxParcel.words), new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(11));
                float f3 = lineBoxParcel.confidence;
                float f4 = lineBoxParcel.box.angleDegrees;
                return new Text$TextBlock(str3, computeBoundingBoxFromCornerPoints, computeCornerPointsFromBoundingBox, str4, transform, null);
            case 10:
                return ((Text$TextBlock) obj).getTextInternal();
            default:
                WordBoxParcel wordBoxParcel = (WordBoxParcel) obj;
                Comparator comparator2 = LegacyTextRecognitionDelegateUtils.languageComparator;
                List computeCornerPointsFromBoundingBox2 = AggregatedAutoMLImageLabelingInferenceLogEvent.computeCornerPointsFromBoundingBox(wordBoxParcel.box);
                if (!ContextDataProvider.stringIsNullOrEmpty(wordBoxParcel.utf8String)) {
                    str2 = wordBoxParcel.utf8String;
                }
                String str5 = str2;
                Rect computeBoundingBoxFromCornerPoints2 = AggregatedAutoMLImageLabelingInferenceLogEvent.computeBoundingBoxFromCornerPoints(computeCornerPointsFromBoundingBox2);
                if (!ContextDataProvider.stringIsNullOrEmpty(wordBoxParcel.language)) {
                    str = wordBoxParcel.language;
                }
                String str6 = str;
                float f5 = wordBoxParcel.confidence;
                float f6 = wordBoxParcel.box.angleDegrees;
                int i = ImmutableList.ImmutableList$ar$NoOp;
                ImmutableList immutableList = RegularImmutableList.EMPTY;
                return new Text$TextBase(str5, computeBoundingBoxFromCornerPoints2, computeCornerPointsFromBoundingBox2, str6, null);
        }
    }
}
