package io.grpc;

import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectivityStateInfo {
    public final ConnectivityState state;
    public final Status status;

    public ConnectivityStateInfo(ConnectivityState connectivityState, Status status) {
        connectivityState.getClass();
        this.state = connectivityState;
        status.getClass();
        this.status = status;
    }

    public static ConnectivityStateInfo forNonError(ConnectivityState connectivityState) {
        boolean z;
        if (connectivityState != ConnectivityState.TRANSIENT_FAILURE) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, (Object) "state is TRANSIENT_ERROR. Use forError() instead");
        return new ConnectivityStateInfo(connectivityState, Status.OK);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ConnectivityStateInfo)) {
            return false;
        }
        ConnectivityStateInfo connectivityStateInfo = (ConnectivityStateInfo) obj;
        if (!this.state.equals(connectivityStateInfo.state) || !this.status.equals(connectivityStateInfo.status)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Status status = this.status;
        return status.hashCode() ^ this.state.hashCode();
    }

    public final String toString() {
        if (this.status.isOk()) {
            return this.state.toString();
        }
        ConnectivityState connectivityState = this.state;
        Status status = this.status;
        return connectivityState.toString() + "(" + status.toString() + ")";
    }
}
