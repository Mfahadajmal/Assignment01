package com.google.android.gms.common.internal.service;

import android.os.IInterface;
import com.google.android.gms.common.internal.TelemetryData;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IClientTelemetryService extends IInterface {
    void recordData(TelemetryData telemetryData);
}
