package androidx.work.impl.constraints;

import _COROUTINE._BOUNDARY;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkState {
    public final boolean isConnected;
    public final boolean isMetered;
    public final boolean isNotRoaming;
    public final boolean isValidated;

    public NetworkState(boolean z, boolean z2, boolean z3, boolean z4) {
        this.isConnected = z;
        this.isValidated = z2;
        this.isMetered = z3;
        this.isNotRoaming = z4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkState)) {
            return false;
        }
        NetworkState networkState = (NetworkState) obj;
        if (this.isConnected == networkState.isConnected && this.isValidated == networkState.isValidated && this.isMetered == networkState.isMetered && this.isNotRoaming == networkState.isNotRoaming) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int ArtificialStackFrames$ar$MethodMerging$dc56d17a_11 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.isConnected);
        boolean z = this.isNotRoaming;
        return (((((ArtificialStackFrames$ar$MethodMerging$dc56d17a_11 * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.isValidated)) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.isMetered)) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(z);
    }

    public final String toString() {
        return "NetworkState(isConnected=" + this.isConnected + ", isValidated=" + this.isValidated + ", isMetered=" + this.isMetered + ", isNotRoaming=" + this.isNotRoaming + ')';
    }
}
