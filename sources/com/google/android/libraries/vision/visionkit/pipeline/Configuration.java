package com.google.android.libraries.vision.visionkit.pipeline;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Configuration {
    private final String nativeLibraryName;
    public final PipelineConfig pipelineConfig;

    public Configuration() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            if (this.pipelineConfig.equals(configuration.pipelineConfig()) && this.nativeLibraryName.equals(configuration.nativeLibraryName())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.pipelineConfig.hashCode() ^ 1000003) * 1000003) ^ this.nativeLibraryName.hashCode();
    }

    public final String nativeLibraryName() {
        return this.nativeLibraryName;
    }

    public final PipelineConfig pipelineConfig() {
        return this.pipelineConfig;
    }

    public final String toString() {
        return "Configuration{pipelineConfig=" + this.pipelineConfig.toString() + ", nativeLibraryName=" + this.nativeLibraryName + "}";
    }

    public Configuration(PipelineConfig pipelineConfig) {
        this();
        if (pipelineConfig == null) {
            throw new NullPointerException("Null pipelineConfig");
        }
        this.pipelineConfig = pipelineConfig;
        this.nativeLibraryName = "demopipeline";
    }
}
