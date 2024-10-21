package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import android.net.Uri;
import android.os.StrictMode;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$CredentialEncryptedStorageInfo;
import com.google.android.libraries.phenotype.client.shareddir.StorageInfoProto$DeviceEncryptedStorageInfo;
import com.google.android.libraries.storage.file.backends.AndroidUri;
import com.google.android.libraries.storage.file.openers.ReadProtoOpener;
import com.google.common.base.Supplier;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageInfoHandler {
    public static final Object CREDENTIAL_ENCRYPTED_STORAGE_INFO_LOCK = new Object();
    public static final Object DEVICE_ENCRYPTED_STORAGE_INFO_LOCK = new Object();
    public final Supplier clientProvider;
    public final Context context;
    public volatile StorageInfoProto$DeviceEncryptedStorageInfo deviceEncryptedStorageInfo;
    public final Uri deviceEncryptedStorageInfoUri;
    public final Supplier executorProvider;
    public final Supplier memoizedStorageInfosUpdateFromGms;
    public volatile StorageInfoProto$CredentialEncryptedStorageInfo storageInfo;
    public final Uri storageInfoUri;
    public final Supplier storageProvider;
    public final Supplier updateScheduledFuture;

    public StorageInfoHandler(Context context, Supplier supplier, Supplier supplier2, Supplier supplier3) {
        this.context = context;
        this.executorProvider = supplier;
        this.clientProvider = supplier3;
        this.storageProvider = supplier2;
        AndroidUri.Builder builder = new AndroidUri.Builder(context);
        builder.setModule$ar$ds("phenotype_storage_info");
        builder.setRelativePath$ar$ds("storage-info.pb");
        this.storageInfoUri = builder.build();
        AndroidUri.Builder builder2 = new AndroidUri.Builder(context);
        builder2.setModule$ar$ds("phenotype_storage_info");
        builder2.setRelativePath$ar$ds("device-encrypted-storage-info.pb");
        int i = DirectBootUtils.DirectBootUtils$ar$NoOp;
        builder2.setDirectBootFilesLocation$ar$ds();
        this.deviceEncryptedStorageInfoUri = builder2.build();
        this.memoizedStorageInfosUpdateFromGms = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(this, 19));
        this.updateScheduledFuture = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(supplier, 20));
    }

    public final StorageInfoProto$DeviceEncryptedStorageInfo getDeviceEncryptedStorageInfo() {
        StorageInfoProto$DeviceEncryptedStorageInfo storageInfoProto$DeviceEncryptedStorageInfo = this.deviceEncryptedStorageInfo;
        if (storageInfoProto$DeviceEncryptedStorageInfo == null) {
            synchronized (DEVICE_ENCRYPTED_STORAGE_INFO_LOCK) {
                storageInfoProto$DeviceEncryptedStorageInfo = this.deviceEncryptedStorageInfo;
                if (storageInfoProto$DeviceEncryptedStorageInfo == null) {
                    storageInfoProto$DeviceEncryptedStorageInfo = StorageInfoProto$DeviceEncryptedStorageInfo.DEFAULT_INSTANCE;
                    ReadProtoOpener create = ReadProtoOpener.create(storageInfoProto$DeviceEncryptedStorageInfo);
                    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().build());
                    try {
                        try {
                            StorageInfoProto$DeviceEncryptedStorageInfo storageInfoProto$DeviceEncryptedStorageInfo2 = (StorageInfoProto$DeviceEncryptedStorageInfo) ((OptionalMethod) this.storageProvider.get()).open(this.deviceEncryptedStorageInfoUri, create);
                            StrictMode.setThreadPolicy(threadPolicy);
                            storageInfoProto$DeviceEncryptedStorageInfo = storageInfoProto$DeviceEncryptedStorageInfo2;
                        } finally {
                            StrictMode.setThreadPolicy(threadPolicy);
                        }
                    } catch (IOException unused) {
                    }
                    this.deviceEncryptedStorageInfo = storageInfoProto$DeviceEncryptedStorageInfo;
                }
            }
        }
        return storageInfoProto$DeviceEncryptedStorageInfo;
    }

    public final StorageInfoProto$CredentialEncryptedStorageInfo getStorageInfo() {
        StorageInfoProto$CredentialEncryptedStorageInfo storageInfoProto$CredentialEncryptedStorageInfo = this.storageInfo;
        if (storageInfoProto$CredentialEncryptedStorageInfo == null) {
            synchronized (CREDENTIAL_ENCRYPTED_STORAGE_INFO_LOCK) {
                storageInfoProto$CredentialEncryptedStorageInfo = this.storageInfo;
                if (storageInfoProto$CredentialEncryptedStorageInfo == null) {
                    storageInfoProto$CredentialEncryptedStorageInfo = StorageInfoProto$CredentialEncryptedStorageInfo.DEFAULT_INSTANCE;
                    ReadProtoOpener create = ReadProtoOpener.create(storageInfoProto$CredentialEncryptedStorageInfo);
                    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().build());
                    try {
                        try {
                            StorageInfoProto$CredentialEncryptedStorageInfo storageInfoProto$CredentialEncryptedStorageInfo2 = (StorageInfoProto$CredentialEncryptedStorageInfo) ((OptionalMethod) this.storageProvider.get()).open(this.storageInfoUri, create);
                            StrictMode.setThreadPolicy(threadPolicy);
                            storageInfoProto$CredentialEncryptedStorageInfo = storageInfoProto$CredentialEncryptedStorageInfo2;
                        } finally {
                            StrictMode.setThreadPolicy(threadPolicy);
                        }
                    } catch (IOException unused) {
                    }
                    this.storageInfo = storageInfoProto$CredentialEncryptedStorageInfo;
                }
            }
        }
        return storageInfoProto$CredentialEncryptedStorageInfo;
    }
}
