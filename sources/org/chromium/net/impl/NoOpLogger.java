package org.chromium.net.impl;

import org.chromium.net.impl.CronetLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoOpLogger extends CronetLogger {
    @Override // org.chromium.net.impl.CronetLogger
    public final long generateId() {
        return 0L;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final void logCronetEngineBuilderInitializedInfo(CronetLogger.CronetEngineBuilderInitializedInfo cronetEngineBuilderInitializedInfo) {
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final void logCronetInitializedInfo(CronetLogger.CronetInitializedInfo cronetInitializedInfo) {
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final void logCronetTrafficInfo(long j, CronetLogger.CronetTrafficInfo cronetTrafficInfo) {
    }

    @Override // org.chromium.net.impl.CronetLogger
    public final void logCronetEngineCreation(long j, CronetLogger.CronetEngineBuilderInfo cronetEngineBuilderInfo, CronetLogger.CronetVersion cronetVersion, CronetLogger.CronetSource cronetSource) {
    }
}
