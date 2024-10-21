package com.google.android.accessibility.braille.brltty;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleDisplayProperties implements Parcelable {
    public static final Parcelable.Creator<BrailleDisplayProperties> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(2);
    public final String driverCode;
    public final Map friendlyKeyNames;
    public final BrailleKeyBinding[] keyBindings;
    private final int numStatusCells;
    public final int numTextCells;

    public BrailleDisplayProperties(String str, int i, int i2, BrailleKeyBinding[] brailleKeyBindingArr, Map map) {
        this.numTextCells = i;
        this.numStatusCells = i2;
        this.keyBindings = brailleKeyBindingArr;
        this.friendlyKeyNames = map;
        this.driverCode = str;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return String.format("BrailleDisplayProperties [numTextCells: %d, numStatusCells: %d, keyBindings: %d], driverCode: %s", Integer.valueOf(this.numTextCells), Integer.valueOf(this.numStatusCells), Integer.valueOf(this.keyBindings.length), this.driverCode);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.driverCode);
        parcel.writeInt(this.numTextCells);
        parcel.writeInt(this.numStatusCells);
        parcel.writeTypedArray(this.keyBindings, i);
        parcel.writeInt(this.friendlyKeyNames.size());
        for (Map.Entry entry : this.friendlyKeyNames.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public BrailleDisplayProperties(Parcel parcel) {
        this.driverCode = parcel.readString();
        this.numTextCells = parcel.readInt();
        this.numStatusCells = parcel.readInt();
        this.keyBindings = (BrailleKeyBinding[]) parcel.createTypedArray(BrailleKeyBinding.CREATOR);
        int readInt = parcel.readInt();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        this.friendlyKeyNames = DesugarCollections.unmodifiableMap(hashMap);
    }
}
