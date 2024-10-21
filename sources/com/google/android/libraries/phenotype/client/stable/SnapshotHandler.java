package com.google.android.libraries.phenotype.client.stable;

import android.net.Uri;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor$$ExternalSyntheticLambda0;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.api.Configurations;
import com.google.android.libraries.phenotype.client.api.Flag;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$CredentialEncryptedStorageInfo;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$DeviceEncryptedStorageInfo;
import com.google.android.libraries.storage.file.backends.AndroidUri;
import com.google.android.libraries.storage.file.openers.WriteProtoOpener;
import com.google.common.base.Function;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.experiments.mobile.base.AndroidBacking;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.IOException;
import java.util.logging.Level;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnapshotHandler {
    private static final DefaultExperimentTokenDecorator phlogger$ar$class_merging = new DefaultExperimentTokenDecorator(null);
    private final String account;
    private final String configPackage;
    private final boolean directBootAware;
    private final PhenotypeContext phenotypeContext;
    private final Uri uri;

    public SnapshotHandler(PhenotypeContext phenotypeContext, String str, String str2, boolean z) {
        this.phenotypeContext = phenotypeContext;
        this.configPackage = str;
        this.account = str2;
        this.directBootAware = z;
        AndroidUri.Builder builder = new AndroidUri.Builder(phenotypeContext.context);
        builder.setModule$ar$ds("phenotype");
        builder.setRelativePath$ar$ds(str2 + "/" + str + ".pb");
        if (z) {
            int i = DirectBootUtils.DirectBootUtils$ar$NoOp;
            builder.setDirectBootFilesLocation$ar$ds();
        }
        this.uri = builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ListenableFuture getLatestSnapshot(String str) {
        return AbstractTransformFuture.create(this.phenotypeContext.getPhenotypeClient$ar$class_merging$ar$class_merging$ar$class_merging().getConfigurationSnapshot(this.configPackage, str), new Function() { // from class: com.google.android.libraries.phenotype.client.stable.SnapshotHandler$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                int i;
                long j;
                double d;
                String str2;
                ByteString byteString;
                Configurations configurations = (Configurations) obj;
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SnapshotProto$Snapshot.DEFAULT_INSTANCE.createBuilder();
                if (configurations == null) {
                    return (SnapshotProto$Snapshot) builder.build();
                }
                for (Flag flag : configurations.flag_) {
                    SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SnapshotProto$SnapshotFlag.DEFAULT_INSTANCE.createBuilder();
                    String str3 = flag.name_;
                    builder2.copyOnWrite();
                    SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag = (SnapshotProto$SnapshotFlag) builder2.instance;
                    str3.getClass();
                    snapshotProto$SnapshotFlag.bitField0_ |= 1;
                    snapshotProto$SnapshotFlag.name_ = str3;
                    int i2 = flag.valueCase_;
                    boolean z = false;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 != 2) {
                                if (i2 != 3) {
                                    if (i2 != 4) {
                                        if (i2 != 5) {
                                            i = 0;
                                        } else {
                                            i = Flag.ValueCase.BYTES_VALUE$ar$edu;
                                        }
                                    } else {
                                        i = Flag.ValueCase.STRING_VALUE$ar$edu;
                                    }
                                } else {
                                    i = Flag.ValueCase.DOUBLE_VALUE$ar$edu;
                                }
                            } else {
                                i = Flag.ValueCase.BOOL_VALUE$ar$edu;
                            }
                        } else {
                            i = Flag.ValueCase.LONG_VALUE$ar$edu;
                        }
                    } else {
                        i = Flag.ValueCase.VALUE_NOT_SET$ar$edu;
                    }
                    if (i != 0) {
                        int i3 = i - 1;
                        if (i3 != 0) {
                            if (i3 != 1) {
                                if (i3 != 2) {
                                    if (i3 != 3) {
                                        if (i3 == 4) {
                                            if (flag.valueCase_ == 5) {
                                                byteString = (ByteString) flag.value_;
                                            } else {
                                                byteString = ByteString.EMPTY;
                                            }
                                            builder2.copyOnWrite();
                                            SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag2 = (SnapshotProto$SnapshotFlag) builder2.instance;
                                            byteString.getClass();
                                            snapshotProto$SnapshotFlag2.valueCase_ = 6;
                                            snapshotProto$SnapshotFlag2.value_ = byteString;
                                        } else {
                                            throw new IllegalStateException("No known flag type");
                                        }
                                    } else {
                                        if (flag.valueCase_ == 4) {
                                            str2 = (String) flag.value_;
                                        } else {
                                            str2 = "";
                                        }
                                        builder2.copyOnWrite();
                                        SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag3 = (SnapshotProto$SnapshotFlag) builder2.instance;
                                        str2.getClass();
                                        snapshotProto$SnapshotFlag3.valueCase_ = 5;
                                        snapshotProto$SnapshotFlag3.value_ = str2;
                                    }
                                } else {
                                    if (flag.valueCase_ == 3) {
                                        d = ((Double) flag.value_).doubleValue();
                                    } else {
                                        d = 0.0d;
                                    }
                                    builder2.copyOnWrite();
                                    SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag4 = (SnapshotProto$SnapshotFlag) builder2.instance;
                                    snapshotProto$SnapshotFlag4.valueCase_ = 4;
                                    snapshotProto$SnapshotFlag4.value_ = Double.valueOf(d);
                                }
                            } else {
                                if (flag.valueCase_ == 2) {
                                    z = ((Boolean) flag.value_).booleanValue();
                                }
                                builder2.copyOnWrite();
                                SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag5 = (SnapshotProto$SnapshotFlag) builder2.instance;
                                snapshotProto$SnapshotFlag5.valueCase_ = 3;
                                snapshotProto$SnapshotFlag5.value_ = Boolean.valueOf(z);
                            }
                        } else {
                            if (flag.valueCase_ == 1) {
                                j = ((Long) flag.value_).longValue();
                            } else {
                                j = 0;
                            }
                            builder2.copyOnWrite();
                            SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag6 = (SnapshotProto$SnapshotFlag) builder2.instance;
                            snapshotProto$SnapshotFlag6.valueCase_ = 2;
                            snapshotProto$SnapshotFlag6.value_ = Long.valueOf(j);
                        }
                        SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag7 = (SnapshotProto$SnapshotFlag) builder2.build();
                        builder.copyOnWrite();
                        SnapshotProto$Snapshot snapshotProto$Snapshot = (SnapshotProto$Snapshot) builder.instance;
                        snapshotProto$SnapshotFlag7.getClass();
                        Internal.ProtobufList protobufList = snapshotProto$Snapshot.flag_;
                        if (!protobufList.isModifiable()) {
                            snapshotProto$Snapshot.flag_ = GeneratedMessageLite.mutableCopy(protobufList);
                        }
                        snapshotProto$Snapshot.flag_.add(snapshotProto$SnapshotFlag7);
                    } else {
                        throw null;
                    }
                }
                String str4 = configurations.serverToken_;
                builder.copyOnWrite();
                SnapshotProto$Snapshot snapshotProto$Snapshot2 = (SnapshotProto$Snapshot) builder.instance;
                str4.getClass();
                snapshotProto$Snapshot2.bitField0_ = 4 | snapshotProto$Snapshot2.bitField0_;
                snapshotProto$Snapshot2.serverToken_ = str4;
                String str5 = configurations.snapshotToken_;
                builder.copyOnWrite();
                SnapshotProto$Snapshot snapshotProto$Snapshot3 = (SnapshotProto$Snapshot) builder.instance;
                str5.getClass();
                snapshotProto$Snapshot3.bitField0_ = 1 | snapshotProto$Snapshot3.bitField0_;
                snapshotProto$Snapshot3.snapshotToken_ = str5;
                long j2 = configurations.configurationVersion_;
                builder.copyOnWrite();
                SnapshotProto$Snapshot snapshotProto$Snapshot4 = (SnapshotProto$Snapshot) builder.instance;
                snapshotProto$Snapshot4.bitField0_ |= 8;
                snapshotProto$Snapshot4.configurationVersion_ = j2;
                if ((configurations.bitField0_ & 2) != 0) {
                    ByteString byteString2 = configurations.experimentToken_;
                    builder.copyOnWrite();
                    SnapshotProto$Snapshot snapshotProto$Snapshot5 = (SnapshotProto$Snapshot) builder.instance;
                    byteString2.getClass();
                    snapshotProto$Snapshot5.bitField0_ |= 2;
                    snapshotProto$Snapshot5.experimentToken_ = byteString2;
                }
                return (SnapshotProto$Snapshot) builder.build();
            }
        }, this.phenotypeContext.getExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01e5  */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.google.common.base.Supplier, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final io.grpc.okhttp.internal.OptionalMethod getStoredSnapshot$ar$class_merging() {
        /*
            Method dump skipped, instructions count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.phenotype.client.stable.SnapshotHandler.getStoredSnapshot$ar$class_merging():io.grpc.okhttp.internal.OptionalMethod");
    }

    /* renamed from: lambda$updateStoredSnapshot$0$com-google-android-libraries-phenotype-client-stable-SnapshotHandler$ar$ds, reason: not valid java name */
    public final /* synthetic */ void m205x39098f80(SnapshotProto$Snapshot snapshotProto$Snapshot) {
        ImageInfo.Builder builder = new ImageInfo.Builder();
        try {
            OptionalMethod storageBackend$ar$class_merging$ar$class_merging$ar$class_merging = this.phenotypeContext.getStorageBackend$ar$class_merging$ar$class_merging$ar$class_merging();
            Uri uri = this.uri;
            WriteProtoOpener writeProtoOpener = new WriteProtoOpener(snapshotProto$Snapshot);
            writeProtoOpener.behaviors$ar$class_merging$ar$class_merging$ar$class_merging = new ImageInfo.Builder[]{builder};
        } catch (IOException | RuntimeException e) {
            DefaultExperimentTokenDecorator.logInternal$ar$ds(Level.WARNING, this.phenotypeContext.getExecutor(), e, "Failed to update snapshot for %s flags may be stale.", this.configPackage);
        }
    }

    public final boolean shouldUseSharedStorage() {
        StorageInfoHandler storageInfoHandler = this.phenotypeContext.storageInfoHandler;
        if (this.directBootAware) {
            StorageInfoProto$DeviceEncryptedStorageInfo deviceEncryptedStorageInfo = storageInfoHandler.getDeviceEncryptedStorageInfo();
            if (deviceEncryptedStorageInfo.shouldUseSharedStorage_ && new Internal.IntListAdapter(deviceEncryptedStorageInfo.enabledBackings_, StorageInfoProto$DeviceEncryptedStorageInfo.enabledBackings_converter_).contains(AndroidBacking.PROCESS_STABLE)) {
                return true;
            }
            return false;
        }
        StorageInfoProto$CredentialEncryptedStorageInfo storageInfo = storageInfoHandler.getStorageInfo();
        if (storageInfo.shouldUseSharedStorage_ && new Internal.IntListAdapter(storageInfo.enabledBackings_, StorageInfoProto$CredentialEncryptedStorageInfo.enabledBackings_converter_).contains(AndroidBacking.PROCESS_STABLE)) {
            return true;
        }
        return false;
    }

    public final ListenableFuture updateStoredSnapshot(SnapshotProto$Snapshot snapshotProto$Snapshot) {
        return ContextDataProvider.submit(new ImageDescriptionProcessor$$ExternalSyntheticLambda0(this, snapshotProto$Snapshot, 2, null), this.phenotypeContext.getExecutor());
    }
}
