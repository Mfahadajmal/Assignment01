package com.google.android.gms.common;

import _COROUTINE._BOUNDARY;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionResult extends AbstractSafeParcelable {
    final int mVersionCode;
    public final PendingIntent pendingIntent;
    public final int statusCode;
    public final String statusMessage;
    public static final ConnectionResult RESULT_SUCCESS = new ConnectionResult(0);
    public static final Parcelable.Creator<ConnectionResult> CREATOR = new FeatureCreator(1);

    public ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.mVersionCode = i;
        this.statusCode = i2;
        this.pendingIntent = pendingIntent;
        this.statusMessage = str;
    }

    public static String getStatusString(int i) {
        if (i != 99) {
            if (i != 1500) {
                switch (i) {
                    case -1:
                        return "UNKNOWN";
                    case 0:
                        return "SUCCESS";
                    case 1:
                        return "SERVICE_MISSING";
                    case 2:
                        return "SERVICE_VERSION_UPDATE_REQUIRED";
                    case 3:
                        return "SERVICE_DISABLED";
                    case 4:
                        return "SIGN_IN_REQUIRED";
                    case 5:
                        return "INVALID_ACCOUNT";
                    case 6:
                        return "RESOLUTION_REQUIRED";
                    case 7:
                        return "NETWORK_ERROR";
                    case 8:
                        return "INTERNAL_ERROR";
                    case 9:
                        return "SERVICE_INVALID";
                    case 10:
                        return "DEVELOPER_ERROR";
                    case 11:
                        return "LICENSE_CHECK_FAILED";
                    default:
                        switch (i) {
                            case 13:
                                return "CANCELED";
                            case 14:
                                return "TIMEOUT";
                            case 15:
                                return "INTERRUPTED";
                            case 16:
                                return "API_UNAVAILABLE";
                            case 17:
                                return "SIGN_IN_FAILED";
                            case 18:
                                return "SERVICE_UPDATING";
                            case 19:
                                return "SERVICE_MISSING_PERMISSION";
                            case 20:
                                return "RESTRICTED_PROFILE";
                            case 21:
                                return "API_VERSION_UPDATE_REQUIRED";
                            case 22:
                                return "RESOLUTION_ACTIVITY_NOT_FOUND";
                            case 23:
                                return "API_DISABLED";
                            case 24:
                                return "API_DISABLED_FOR_CONNECTION";
                            default:
                                return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "UNKNOWN_ERROR_CODE(", ")");
                        }
                }
            }
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        return "UNFINISHED";
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        if (this.statusCode == connectionResult.statusCode && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.pendingIntent, connectionResult.pendingIntent) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.statusMessage, connectionResult.statusMessage)) {
            return true;
        }
        return false;
    }

    public final boolean hasResolution() {
        if (this.statusCode != 0 && this.pendingIntent != null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.statusCode), this.pendingIntent, this.statusMessage});
    }

    public final boolean isSuccess() {
        if (this.statusCode == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        Objects$ToStringHelper objects$ToStringHelper = new Objects$ToStringHelper(this);
        objects$ToStringHelper.add$ar$ds$bdea682c_0("statusCode", getStatusString(this.statusCode));
        objects$ToStringHelper.add$ar$ds$bdea682c_0("resolution", this.pendingIntent);
        objects$ToStringHelper.add$ar$ds$bdea682c_0("message", this.statusMessage);
        return objects$ToStringHelper.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.mVersionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.statusCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 3, this.pendingIntent, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, this.statusMessage);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public ConnectionResult(int i) {
        this(i, null, null);
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this(i, pendingIntent, null);
    }

    public ConnectionResult(int i, PendingIntent pendingIntent, String str) {
        this(1, i, pendingIntent, str);
    }
}
