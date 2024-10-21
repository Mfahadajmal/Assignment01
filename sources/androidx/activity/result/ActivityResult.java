package androidx.activity.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.support.v7.widget.AppCompatTextHelper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActivityResult implements Parcelable {
    public final Intent data;
    public final int resultCode;
    public static final AppCompatTextHelper.Api26Impl Companion$ar$class_merging$dcd62732_0 = new AppCompatTextHelper.Api26Impl();
    public static final Parcelable.Creator<ActivityResult> CREATOR = new BackStackState.AnonymousClass1(10);

    public ActivityResult(int i, Intent intent) {
        this.resultCode = i;
        this.data = intent;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ActivityResult{resultCode=");
        int i = this.resultCode;
        if (i != -1) {
            if (i != 0) {
                str = String.valueOf(i);
            } else {
                str = "RESULT_CANCELED";
            }
        } else {
            str = "RESULT_OK";
        }
        sb.append(str);
        sb.append(", data=");
        sb.append(this.data);
        sb.append('}');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        parcel.getClass();
        parcel.writeInt(this.resultCode);
        if (this.data == null) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        parcel.writeInt(i2);
        Intent intent = this.data;
        if (intent != null) {
            intent.writeToParcel(parcel, i);
        }
    }
}
