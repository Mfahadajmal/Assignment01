package com.google.mlkit.shared.logger;

import android.content.Context;
import com.google.mlkit.shared.logger.firelog.FirelogLoggingTransport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MLKitLoggingTransport implements LoggingTransportInterface {
    final List loggingTransports;

    public MLKitLoggingTransport(Context context, MLKitLoggingOptions mLKitLoggingOptions) {
        ArrayList arrayList = new ArrayList();
        this.loggingTransports = arrayList;
        if (mLKitLoggingOptions.isEnableFirelog()) {
            arrayList.add(new FirelogLoggingTransport(context, mLKitLoggingOptions));
        }
    }

    @Override // com.google.mlkit.shared.logger.LoggingTransportInterface
    public final void logSdkEvent$ar$class_merging(SchemaLogEvent schemaLogEvent) {
        Iterator it = this.loggingTransports.iterator();
        while (it.hasNext()) {
            ((LoggingTransportInterface) it.next()).logSdkEvent$ar$class_merging(schemaLogEvent);
        }
    }
}
