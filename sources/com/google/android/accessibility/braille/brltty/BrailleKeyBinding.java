package com.google.android.accessibility.braille.brltty;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleKeyBinding implements Parcelable {
    public static final Parcelable.Creator<BrailleKeyBinding> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(4);
    public int command;
    private int flags;
    public String[] keyNames;
    public boolean unifiedKeyBinding;

    public BrailleKeyBinding() {
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean isLongPress() {
        if ((this.flags & 1) != 0) {
            return true;
        }
        return false;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.command);
        parcel.writeStringArray(this.keyNames);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.unifiedKeyBinding ? 1 : 0);
    }

    public BrailleKeyBinding(int i, String[] strArr, boolean z, boolean z2) {
        this.command = i;
        this.keyNames = strArr;
        this.flags = z ? 1 : 0;
        this.unifiedKeyBinding = z2;
    }

    public BrailleKeyBinding(Parcel parcel) {
        this.command = parcel.readInt();
        this.keyNames = parcel.createStringArray();
        this.flags = parcel.readInt();
        this.unifiedKeyBinding = parcel.readInt() == 1;
    }
}
