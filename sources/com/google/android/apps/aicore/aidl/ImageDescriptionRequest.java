package com.google.android.apps.aicore.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionRequest extends AbstractSafeParcelable implements SafeParcelable {
    public static final Parcelable.Creator<ImageDescriptionRequest> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(6);
    public final List images;
    private final IImageDescriptionStreamingCallback streamingCallback;

    public ImageDescriptionRequest(List list, IImageDescriptionStreamingCallback iImageDescriptionStreamingCallback) {
        this.images = list;
        this.streamingCallback = null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        IBinder asBinder;
        List list = this.images;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedList$ar$ds(parcel, 1, list);
        IImageDescriptionStreamingCallback iImageDescriptionStreamingCallback = this.streamingCallback;
        if (iImageDescriptionStreamingCallback == null) {
            asBinder = null;
        } else {
            asBinder = iImageDescriptionStreamingCallback.asBinder();
        }
        StrictModeUtils$VmPolicyBuilderCompatS.writeIBinder$ar$ds(parcel, 2, asBinder);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public ImageDescriptionRequest(List list, IBinder iBinder) {
        IImageDescriptionStreamingCallback iImageDescriptionStreamingCallback$Stub$Proxy;
        this.images = list;
        if (iBinder == null) {
            iImageDescriptionStreamingCallback$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.apps.aicore.aidl.IImageDescriptionStreamingCallback");
            iImageDescriptionStreamingCallback$Stub$Proxy = queryLocalInterface instanceof IImageDescriptionStreamingCallback ? (IImageDescriptionStreamingCallback) queryLocalInterface : new IImageDescriptionStreamingCallback$Stub$Proxy(iBinder);
        }
        this.streamingCallback = iImageDescriptionStreamingCallback$Stub$Proxy;
    }
}
