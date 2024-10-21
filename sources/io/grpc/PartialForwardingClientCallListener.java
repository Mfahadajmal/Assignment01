package io.grpc;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PartialForwardingClientCallListener extends OnDeviceFaceMeshCreateLogEvent {
    public final OnDeviceFaceMeshCreateLogEvent delegate$ar$class_merging$a40ae667_0$ar$class_merging;

    public PartialForwardingClientCallListener() {
    }

    public final OnDeviceFaceMeshCreateLogEvent delegate$ar$class_merging$1cf311ea_0$ar$class_merging() {
        return this.delegate$ar$class_merging$a40ae667_0$ar$class_merging;
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public void onHeaders(Metadata metadata) {
        delegate$ar$class_merging$1cf311ea_0$ar$class_merging().onHeaders(metadata);
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public void onMessage(Object obj) {
        delegate$ar$class_merging$1cf311ea_0$ar$class_merging().onMessage(obj);
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onReady() {
        delegate$ar$class_merging$1cf311ea_0$ar$class_merging().onReady();
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", delegate$ar$class_merging$1cf311ea_0$ar$class_merging());
        return stringHelper.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PartialForwardingClientCallListener(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent) {
        this((byte[]) null);
        this.delegate$ar$class_merging$a40ae667_0$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
    }

    public PartialForwardingClientCallListener(byte[] bArr) {
        this();
    }
}
