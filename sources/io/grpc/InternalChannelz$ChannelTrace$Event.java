package io.grpc;

import _COROUTINE._BOUNDARY;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalChannelz$ChannelTrace$Event {
    public final InternalWithLogId channelRef;
    public final String description;
    public final Severity severity;
    public final InternalWithLogId subchannelRef;
    public final long timestampNanos;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object InternalChannelz$ChannelTrace$Event$Builder$ar$description;
        public Object InternalChannelz$ChannelTrace$Event$Builder$ar$severity;
        public Object InternalChannelz$ChannelTrace$Event$Builder$ar$subchannelRef;
        private Object InternalChannelz$ChannelTrace$Event$Builder$ar$timestampNanos;

        public final HttpConnectProxiedSocketAddress build() {
            Object obj = this.InternalChannelz$ChannelTrace$Event$Builder$ar$description;
            Object obj2 = this.InternalChannelz$ChannelTrace$Event$Builder$ar$timestampNanos;
            return new HttpConnectProxiedSocketAddress((SocketAddress) obj, (InetSocketAddress) obj2, (String) this.InternalChannelz$ChannelTrace$Event$Builder$ar$subchannelRef, (String) this.InternalChannelz$ChannelTrace$Event$Builder$ar$severity);
        }

        public final void setProxyAddress$ar$ds(SocketAddress socketAddress) {
            socketAddress.getClass();
            this.InternalChannelz$ChannelTrace$Event$Builder$ar$description = socketAddress;
        }

        public final void setTargetAddress$ar$ds(InetSocketAddress inetSocketAddress) {
            inetSocketAddress.getClass();
            this.InternalChannelz$ChannelTrace$Event$Builder$ar$timestampNanos = inetSocketAddress;
        }

        public final void setTimestampNanos$ar$ds(long j) {
            this.InternalChannelz$ChannelTrace$Event$Builder$ar$timestampNanos = Long.valueOf(j);
        }

        /* JADX WARN: Type inference failed for: r7v0, types: [io.grpc.InternalWithLogId, java.lang.Object] */
        /* renamed from: build, reason: collision with other method in class */
        public final InternalChannelz$ChannelTrace$Event m241build() {
            this.InternalChannelz$ChannelTrace$Event$Builder$ar$description.getClass();
            this.InternalChannelz$ChannelTrace$Event$Builder$ar$severity.getClass();
            this.InternalChannelz$ChannelTrace$Event$Builder$ar$timestampNanos.getClass();
            ContextDataProvider.checkState(true, "at least one of channelRef and subchannelRef must be null");
            Object obj = this.InternalChannelz$ChannelTrace$Event$Builder$ar$description;
            return new InternalChannelz$ChannelTrace$Event((String) obj, (Severity) this.InternalChannelz$ChannelTrace$Event$Builder$ar$severity, ((Long) this.InternalChannelz$ChannelTrace$Event$Builder$ar$timestampNanos).longValue(), this.InternalChannelz$ChannelTrace$Event$Builder$ar$subchannelRef);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Severity {
        CT_UNKNOWN,
        CT_INFO,
        CT_WARNING,
        CT_ERROR
    }

    public InternalChannelz$ChannelTrace$Event(String str, Severity severity, long j, InternalWithLogId internalWithLogId) {
        this.description = str;
        severity.getClass();
        this.severity = severity;
        this.timestampNanos = j;
        this.channelRef = null;
        this.subchannelRef = internalWithLogId;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof InternalChannelz$ChannelTrace$Event) {
            InternalChannelz$ChannelTrace$Event internalChannelz$ChannelTrace$Event = (InternalChannelz$ChannelTrace$Event) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.description, internalChannelz$ChannelTrace$Event.description) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.severity, internalChannelz$ChannelTrace$Event.severity) && this.timestampNanos == internalChannelz$ChannelTrace$Event.timestampNanos) {
                InternalWithLogId internalWithLogId = internalChannelz$ChannelTrace$Event.channelRef;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.subchannelRef, internalChannelz$ChannelTrace$Event.subchannelRef)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.description, this.severity, Long.valueOf(this.timestampNanos), null, this.subchannelRef});
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("description", this.description);
        stringHelper.addHolder$ar$ds("severity", this.severity);
        MoreObjects$ToStringHelper add = stringHelper.add("timestampNanos", this.timestampNanos);
        add.addHolder$ar$ds("channelRef", null);
        add.addHolder$ar$ds("subchannelRef", this.subchannelRef);
        return add.toString();
    }
}
