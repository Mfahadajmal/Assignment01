package androidx.navigation;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import androidx.core.view.ViewConfigurationCompat$Api28Impl;
import androidx.lifecycle.Lifecycle;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavBackStackEntryState implements Parcelable {
    public static final Parcelable.Creator<NavBackStackEntryState> CREATOR = new BackStackState.AnonymousClass1(13);
    private final Bundle args;
    public final int destinationId;
    public final String id;
    private final Bundle savedState;

    public NavBackStackEntryState(Parcel parcel) {
        String readString = parcel.readString();
        readString.getClass();
        this.id = readString;
        this.destinationId = parcel.readInt();
        this.args = parcel.readBundle(getClass().getClassLoader());
        Bundle readBundle = parcel.readBundle(getClass().getClassLoader());
        readBundle.getClass();
        this.savedState = readBundle;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final NavBackStackEntry instantiate(Context context, NavDestination navDestination, Lifecycle.State state, NavControllerViewModel navControllerViewModel) {
        state.getClass();
        Bundle bundle = this.args;
        if (bundle != null) {
            bundle.setClassLoader(context.getClassLoader());
        } else {
            bundle = null;
        }
        return ViewConfigurationCompat$Api28Impl.create$ar$ds(context, navDestination, bundle, state, navControllerViewModel, this.id, this.savedState);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.getClass();
        parcel.writeString(this.id);
        parcel.writeInt(this.destinationId);
        parcel.writeBundle(this.args);
        parcel.writeBundle(this.savedState);
    }

    public NavBackStackEntryState(NavBackStackEntry navBackStackEntry) {
        navBackStackEntry.getClass();
        this.id = navBackStackEntry.id;
        this.destinationId = navBackStackEntry.destination.id;
        this.args = navBackStackEntry.getArguments();
        Bundle bundle = new Bundle();
        this.savedState = bundle;
        navBackStackEntry.savedStateRegistryController.performSave(bundle);
    }
}
