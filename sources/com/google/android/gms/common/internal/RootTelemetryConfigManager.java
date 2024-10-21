package com.google.android.gms.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RootTelemetryConfigManager {
    private static final RootTelemetryConfiguration NO_TELEMETRY_CONFIG = new RootTelemetryConfiguration(0, false, false, 0, 0);
    private static RootTelemetryConfigManager instance;
    public RootTelemetryConfiguration config;

    private RootTelemetryConfigManager() {
    }

    public static synchronized RootTelemetryConfigManager getInstance() {
        RootTelemetryConfigManager rootTelemetryConfigManager;
        synchronized (RootTelemetryConfigManager.class) {
            if (instance == null) {
                instance = new RootTelemetryConfigManager();
            }
            rootTelemetryConfigManager = instance;
        }
        return rootTelemetryConfigManager;
    }

    public final synchronized void updateConfig(RootTelemetryConfiguration rootTelemetryConfiguration) {
        if (rootTelemetryConfiguration == null) {
            this.config = NO_TELEMETRY_CONFIG;
            return;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration2 = this.config;
        if (rootTelemetryConfiguration2 != null) {
            if (rootTelemetryConfiguration2.version >= rootTelemetryConfiguration.version) {
                return;
            }
        }
        this.config = rootTelemetryConfiguration;
    }
}
