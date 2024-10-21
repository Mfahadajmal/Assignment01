package com.google.android.play.core.splitinstall.testing;

import com.google.mlkit.logging.schema.ImageInfo;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LocalTestingConfig {
    public static final LocalTestingConfig DEFAULT = builder$ar$class_merging$7885372b_0$ar$class_merging().m222build();
    private final Integer defaultSplitInstallErrorCode;
    private final Map splitInstallErrorCodeByModule;

    public LocalTestingConfig() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImageInfo.Builder builder$ar$class_merging$7885372b_0$ar$class_merging() {
        ImageInfo.Builder builder = new ImageInfo.Builder(null, null, null);
        builder.setSplitInstallErrorCodeByModule$ar$ds(new HashMap());
        return builder;
    }

    public final Integer defaultSplitInstallErrorCode() {
        return this.defaultSplitInstallErrorCode;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LocalTestingConfig) {
            LocalTestingConfig localTestingConfig = (LocalTestingConfig) obj;
            Integer num = this.defaultSplitInstallErrorCode;
            if (num != null ? num.equals(localTestingConfig.defaultSplitInstallErrorCode()) : localTestingConfig.defaultSplitInstallErrorCode() == null) {
                if (this.splitInstallErrorCodeByModule.equals(localTestingConfig.splitInstallErrorCodeByModule())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        Integer num = this.defaultSplitInstallErrorCode;
        if (num == null) {
            hashCode = 0;
        } else {
            hashCode = num.hashCode();
        }
        return ((hashCode ^ 1000003) * 1000003) ^ this.splitInstallErrorCodeByModule.hashCode();
    }

    public final Map splitInstallErrorCodeByModule() {
        return this.splitInstallErrorCodeByModule;
    }

    public final String toString() {
        return "LocalTestingConfig{defaultSplitInstallErrorCode=" + this.defaultSplitInstallErrorCode + ", splitInstallErrorCodeByModule=" + String.valueOf(this.splitInstallErrorCodeByModule) + "}";
    }

    public LocalTestingConfig(Integer num, Map map) {
        this();
        this.defaultSplitInstallErrorCode = num;
        this.splitInstallErrorCodeByModule = map;
    }
}
