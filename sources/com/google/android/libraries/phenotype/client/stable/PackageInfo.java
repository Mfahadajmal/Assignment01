package com.google.android.libraries.phenotype.client.stable;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.util.Log;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.common.collect.ImmutableMap;
import com.google.experiments.mobile.base.AndroidBacking;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Protobuf;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageInfo {
    private static final Object PACKAGES_LOCK = new Object();
    private static volatile Map packagesFromAssets;
    public final boolean accountScoped;
    public final AndroidBacking backing;
    public final String configPackage;
    public final boolean directBootAware;
    public final boolean stickyAccountSupport;

    public PackageInfo(Context context, PackageMetadataProto$PackageMetadata packageMetadataProto$PackageMetadata) {
        String str;
        if (packageMetadataProto$PackageMetadata.autoSubpackage_) {
            str = PhenotypeConstants.getSubpackagedName(context, packageMetadataProto$PackageMetadata.staticConfigPackage_);
        } else {
            str = packageMetadataProto$PackageMetadata.staticConfigPackage_;
        }
        this.configPackage = str;
        AndroidBacking forNumber = AndroidBacking.forNumber(packageMetadataProto$PackageMetadata.backing_);
        this.backing = forNumber == null ? AndroidBacking.UNKNOWN : forNumber;
        this.directBootAware = packageMetadataProto$PackageMetadata.directBootAware_;
        this.stickyAccountSupport = packageMetadataProto$PackageMetadata.stickyAccountSupport_;
        this.accountScoped = packageMetadataProto$PackageMetadata.accountScoped_;
    }

    public static Map getRegisteredPackages(Context context) {
        Map map = packagesFromAssets;
        if (map == null) {
            synchronized (PACKAGES_LOCK) {
                map = packagesFromAssets;
                if (map == null) {
                    ImmutableMap.Builder builder = new ImmutableMap.Builder();
                    try {
                        String[] list = context.getAssets().list("phenotype");
                        if (list != null) {
                            for (String str : list) {
                                if (str.endsWith("_package_metadata.binarypb")) {
                                    try {
                                        InputStream open = context.getAssets().open(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(str, "phenotype/"));
                                        try {
                                            ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
                                            Protobuf protobuf = Protobuf.INSTANCE;
                                            PackageInfo packageInfo = new PackageInfo(context, (PackageMetadataProto$PackageMetadata) GeneratedMessageLite.parseFrom(PackageMetadataProto$PackageMetadata.DEFAULT_INSTANCE, open, ExtensionRegistryLite.EMPTY_REGISTRY_LITE));
                                            builder.put$ar$ds$de9b9d28_0(packageInfo.configPackage, packageInfo);
                                            if (open != null) {
                                                open.close();
                                            }
                                        } catch (Throwable th) {
                                            if (open != null) {
                                                try {
                                                    open.close();
                                                } catch (Throwable th2) {
                                                    th.addSuppressed(th2);
                                                }
                                            }
                                            throw th;
                                            break;
                                        }
                                    } catch (InvalidProtocolBufferException e) {
                                        Log.e("PackageInfo", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(str, "Unable to read Phenotype PackageMetadata for "), e);
                                    }
                                }
                            }
                        }
                    } catch (IOException e2) {
                        Log.e("PackageInfo", "Unable to read Phenotype PackageMetadata from assets.", e2);
                    }
                    ImmutableMap buildOrThrow = builder.buildOrThrow();
                    packagesFromAssets = buildOrThrow;
                    map = buildOrThrow;
                }
            }
        }
        return map;
    }
}
