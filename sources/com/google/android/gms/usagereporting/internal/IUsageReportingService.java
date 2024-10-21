package com.google.android.gms.usagereporting.internal;

import android.os.IInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IUsageReportingService extends IInterface {
    void getOptInOptions(IUsageReportingCallbacks iUsageReportingCallbacks);

    void registerOptInOptionsChangedListener(IUsageReportingOptInOptionsChangedListener iUsageReportingOptInOptionsChangedListener, IUsageReportingCallbacks iUsageReportingCallbacks);

    void unregisterOptInOptionsChangedListener(IUsageReportingOptInOptionsChangedListener iUsageReportingOptInOptionsChangedListener, IUsageReportingCallbacks iUsageReportingCallbacks);

    void unregisterOptInOptionsChangedListenerConnectionless(IUsageReportingOptInOptionsChangedListener iUsageReportingOptInOptionsChangedListener, IUsageReportingCallbacks iUsageReportingCallbacks);
}
