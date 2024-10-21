package com.google.android.apps.aicore.client.api.internal;

import android.net.Uri;
import android.os.RemoteException;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.actor.gemini.ArateaEndpoint$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.actor.gemini.DataFieldUtils$GeminiResponse;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.apps.aicore.aidl.IAICoreService;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.gms.clearcut.AbstractLogEventBuilder;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.usagereporting.internal.OptInOptionsResultImpl;
import com.google.android.libraries.performance.primes.metrics.core.perfetto.PerfettoTrigger;
import com.google.android.libraries.performance.primes.transmitter.clearcut.CheckboxChecker;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$CredentialEncryptedStorageInfo;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$DeviceEncryptedStorageInfo;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$StorageInfos;
import com.google.android.libraries.phenotype.client.stable.AccountList;
import com.google.android.libraries.phenotype.client.stable.Accounts;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.phenotype.client.stable.ExperimentTokenData;
import com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl;
import com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore;
import com.google.android.libraries.phenotype.client.stable.StorageInfoHandler;
import com.google.android.libraries.storage.file.openers.WriteProtoOpener;
import com.google.common.base.Function;
import com.google.common.base.Pair;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.protobuf.Internal;
import com.google.protobuf.MapFieldLite;
import com.google.search.mdi.aratea.proto.ArateaOutputData;
import com.google.search.mdi.aratea.proto.FilteredReason;
import com.google.search.mdi.aratea.proto.GenerateResponse;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.Collection;
import j$.util.Comparator$CC;
import j$.util.Optional;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AiCoreClientImpl$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ Object AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ AiCoreClientImpl$$ExternalSyntheticLambda2(Object obj, int i) {
        this.switching_field = i;
        this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0 = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        String str;
        DataFieldUtils$GeminiResponse build;
        boolean z = false;
        int i = 0;
        switch (this.switching_field) {
            case 0:
                IAICoreService iAICoreService = (IAICoreService) obj;
                String str2 = AiCoreClientImpl.TAG;
                Object obj2 = this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0;
                try {
                    return Integer.valueOf(iAICoreService.getFeatureStatus(((AiFeature) obj2).toParcelableAiFeature()));
                } catch (RemoteException e) {
                    Log.e(AiCoreClientImpl.TAG, "AiCore service failed to get feature status for ".concat(((AiFeature) obj2).getName()), e);
                    return i;
                }
            case 1:
                GenerateResponse generateResponse = (GenerateResponse) obj;
                if (generateResponse.filteredData_.size() == 0 && generateResponse.outputData_.size() == 0) {
                    Compositor$HandleEventOptions builder$ar$class_merging$d9081d28_0 = DataFieldUtils$GeminiResponse.builder$ar$class_merging$d9081d28_0();
                    builder$ar$class_merging$d9081d28_0.Compositor$HandleEventOptions$ar$eventInterpretation = "Aratea response has no filter or output";
                    return builder$ar$class_merging$d9081d28_0.build();
                }
                Internal.ProtobufList protobufList = generateResponse.filteredData_;
                FilteredReason filteredReason = FilteredReason.IMAGE_SAFETY;
                FilteredReason filteredReason2 = FilteredReason.TEXT_SAFETY;
                FilteredReason filteredReason3 = FilteredReason.TEXT_BLOCKLIST;
                FilteredReason filteredReason4 = FilteredReason.TEXT_LOW_QUALITY;
                FilteredReason filteredReason5 = FilteredReason.TEXT_IRRELEVANT;
                FilteredReason filteredReason6 = FilteredReason.FILTERED_REASON_UNSPECIFIED;
                ContextDataProvider.checkEntryNotNull(filteredReason, i);
                ContextDataProvider.checkEntryNotNull(filteredReason2, 1);
                ContextDataProvider.checkEntryNotNull(filteredReason3, 2);
                ContextDataProvider.checkEntryNotNull(filteredReason4, 3);
                ContextDataProvider.checkEntryNotNull(filteredReason5, 4);
                ContextDataProvider.checkEntryNotNull(filteredReason6, 5);
                Optional map = Collection.EL.stream(protobufList).min(Comparator$CC.comparingInt(new ArateaEndpoint$$ExternalSyntheticLambda0(RegularImmutableMap.create(6, new Object[]{filteredReason, i, filteredReason2, 1, filteredReason3, 2, filteredReason4, 3, filteredReason5, 4, filteredReason6, 5}), 0))).map(new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(3)).map(new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(4));
                Optional findFirst = Collection.EL.stream(generateResponse.outputData_).findFirst();
                if (findFirst.isEmpty()) {
                    Compositor$HandleEventOptions builder$ar$class_merging$d9081d28_02 = DataFieldUtils$GeminiResponse.builder$ar$class_merging$d9081d28_0();
                    builder$ar$class_merging$d9081d28_02.Compositor$HandleEventOptions$ar$eventInterpretation = "Aratea response missing output.";
                    build = builder$ar$class_merging$d9081d28_02.build();
                } else {
                    ArateaOutputData arateaOutputData = (ArateaOutputData) findFirst.get();
                    if (arateaOutputData.outputDataCase_ == 1) {
                        str = (String) arateaOutputData.outputData_;
                    } else {
                        str = "";
                    }
                    if (TextUtils.isEmpty(str)) {
                        Compositor$HandleEventOptions builder$ar$class_merging$d9081d28_03 = DataFieldUtils$GeminiResponse.builder$ar$class_merging$d9081d28_0();
                        builder$ar$class_merging$d9081d28_03.Compositor$HandleEventOptions$ar$eventInterpretation = "Answer empty";
                        build = builder$ar$class_merging$d9081d28_03.build();
                    } else {
                        Compositor$HandleEventOptions builder$ar$class_merging$d9081d28_04 = DataFieldUtils$GeminiResponse.builder$ar$class_merging$d9081d28_0();
                        builder$ar$class_merging$d9081d28_04.Compositor$HandleEventOptions$ar$onCompleteRunnable = str;
                        build = builder$ar$class_merging$d9081d28_04.build();
                    }
                }
                return (DataFieldUtils$GeminiResponse) map.orElse(build);
            case 2:
                try {
                    return new ProcessBuilder("/system/bin/trigger_perfetto", (String) obj).start();
                } catch (IOException unused) {
                    ((PerfettoTrigger) this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0).hasFailed = true;
                    return null;
                }
            case 3:
                OptInOptionsResultImpl optInOptionsResultImpl = (OptInOptionsResultImpl) ((OnDeviceTextDetectionLoadLogEvent) obj).OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(optInOptionsResultImpl.optInOptions);
                int i2 = optInOptionsResultImpl.optInOptions.optInUsageReporting;
                if (i2 == 1 || i2 == 3) {
                    z = true;
                }
                Object obj3 = this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0;
                Boolean valueOf = Boolean.valueOf(z);
                ((CheckboxChecker) obj3).checkboxEnabled.set(valueOf);
                return valueOf;
            case 4:
                DefaultExperimentTokenDecorator.get();
                return (ExperimentTokenData) ExperimentTokenDecoratorImpl.accountScopedConfigPackageKeyMap.get(new Pair((String) obj, ((AbstractLogEventBuilder) this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0).uploadAccountName));
            case 5:
                ConcurrentMap concurrentMap = PhenotypeAccountStore.accountCommitterByPackage;
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) Accounts.DEFAULT_INSTANCE.createBuilder();
                for (Map.Entry entry : Collections.unmodifiableMap(((Accounts) obj).accountLists_).entrySet()) {
                    Object obj4 = this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0;
                    AccountList accountList = (AccountList) entry.getValue();
                    SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) AccountList.DEFAULT_INSTANCE.createBuilder();
                    if (!accountList.latestAccount_.equals(obj4)) {
                        String str3 = accountList.latestAccount_;
                        builder2.copyOnWrite();
                        AccountList accountList2 = (AccountList) builder2.instance;
                        str3.getClass();
                        accountList2.bitField0_ |= 1;
                        accountList2.latestAccount_ = str3;
                    }
                    for (String str4 : accountList.values_) {
                        if (!str4.equals(obj4)) {
                            builder2.addValues$ar$ds(str4);
                        }
                    }
                    builder.putAccountLists$ar$ds((String) entry.getKey(), (AccountList) builder2.build());
                }
                return (Accounts) builder.build();
            case 6:
                ConcurrentMap concurrentMap2 = PhenotypeAccountStore.accountCommitterByPackage;
                AccountList accountList3 = AccountList.DEFAULT_INSTANCE;
                MapFieldLite mapFieldLite = ((Accounts) obj).accountLists_;
                Object obj5 = this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0;
                if (mapFieldLite.containsKey(obj5)) {
                    accountList3 = (AccountList) mapFieldLite.get(obj5);
                }
                return accountList3.values_;
            case 7:
                ConcurrentMap concurrentMap3 = PhenotypeAccountStore.accountCommitterByPackage;
                Object obj6 = this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0;
                AccountList accountList4 = AccountList.DEFAULT_INSTANCE;
                obj6.getClass();
                MapFieldLite mapFieldLite2 = ((Accounts) obj).accountLists_;
                if (mapFieldLite2.containsKey(obj6)) {
                    accountList4 = (AccountList) mapFieldLite2.get(obj6);
                }
                return accountList4.latestAccount_;
            default:
                StorageInfoProto$StorageInfos storageInfoProto$StorageInfos = (StorageInfoProto$StorageInfos) obj;
                ImageInfo.Builder builder3 = new ImageInfo.Builder();
                StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskWrites().build());
                Object obj7 = this.AiCoreClientImpl$$ExternalSyntheticLambda2$ar$f$0;
                try {
                    try {
                        synchronized (StorageInfoHandler.CREDENTIAL_ENCRYPTED_STORAGE_INFO_LOCK) {
                            OptionalMethod optionalMethod = (OptionalMethod) ((StorageInfoHandler) obj7).storageProvider.get();
                            Uri uri = ((StorageInfoHandler) obj7).storageInfoUri;
                            StorageInfoProto$CredentialEncryptedStorageInfo storageInfoProto$CredentialEncryptedStorageInfo = storageInfoProto$StorageInfos.credentialEncryptedStorageInfo_;
                            if (storageInfoProto$CredentialEncryptedStorageInfo == null) {
                                storageInfoProto$CredentialEncryptedStorageInfo = StorageInfoProto$CredentialEncryptedStorageInfo.DEFAULT_INSTANCE;
                            }
                            WriteProtoOpener writeProtoOpener = new WriteProtoOpener(storageInfoProto$CredentialEncryptedStorageInfo);
                            writeProtoOpener.behaviors$ar$class_merging$ar$class_merging$ar$class_merging = new ImageInfo.Builder[]{builder3};
                            optionalMethod.open(uri, writeProtoOpener);
                            StorageInfoProto$CredentialEncryptedStorageInfo storageInfoProto$CredentialEncryptedStorageInfo2 = storageInfoProto$StorageInfos.credentialEncryptedStorageInfo_;
                            if (storageInfoProto$CredentialEncryptedStorageInfo2 == null) {
                                storageInfoProto$CredentialEncryptedStorageInfo2 = StorageInfoProto$CredentialEncryptedStorageInfo.DEFAULT_INSTANCE;
                            }
                            ((StorageInfoHandler) obj7).storageInfo = storageInfoProto$CredentialEncryptedStorageInfo2;
                        }
                        synchronized (StorageInfoHandler.DEVICE_ENCRYPTED_STORAGE_INFO_LOCK) {
                            OptionalMethod optionalMethod2 = (OptionalMethod) ((StorageInfoHandler) obj7).storageProvider.get();
                            Uri uri2 = ((StorageInfoHandler) obj7).deviceEncryptedStorageInfoUri;
                            StorageInfoProto$DeviceEncryptedStorageInfo storageInfoProto$DeviceEncryptedStorageInfo = storageInfoProto$StorageInfos.deviceEncryptedStorageInfo_;
                            if (storageInfoProto$DeviceEncryptedStorageInfo == null) {
                                storageInfoProto$DeviceEncryptedStorageInfo = StorageInfoProto$DeviceEncryptedStorageInfo.DEFAULT_INSTANCE;
                            }
                            WriteProtoOpener writeProtoOpener2 = new WriteProtoOpener(storageInfoProto$DeviceEncryptedStorageInfo);
                            writeProtoOpener2.behaviors$ar$class_merging$ar$class_merging$ar$class_merging = new ImageInfo.Builder[]{builder3};
                            optionalMethod2.open(uri2, writeProtoOpener2);
                            StorageInfoProto$DeviceEncryptedStorageInfo storageInfoProto$DeviceEncryptedStorageInfo2 = storageInfoProto$StorageInfos.deviceEncryptedStorageInfo_;
                            if (storageInfoProto$DeviceEncryptedStorageInfo2 == null) {
                                storageInfoProto$DeviceEncryptedStorageInfo2 = StorageInfoProto$DeviceEncryptedStorageInfo.DEFAULT_INSTANCE;
                            }
                            ((StorageInfoHandler) obj7).deviceEncryptedStorageInfo = storageInfoProto$DeviceEncryptedStorageInfo2;
                        }
                        return null;
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                } finally {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
        }
    }
}
