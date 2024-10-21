package com.google.android.gms.common.data;

import _COROUTINE._BOUNDARY;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new FeatureCreator(8);
    public final String[] columns;
    public Bundle mColumnBundle;
    final int mVersionCode;
    public int[] mWindowRowOffsets;
    public final Bundle metadata;
    public final int statusCode;
    public final CursorWindow[] windows;
    boolean mClosed = false;
    private final boolean isLeakDetectionEnabled = true;

    static {
        new ArrayList();
        new HashMap();
    }

    public DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mVersionCode = i;
        this.columns = strArr;
        this.windows = cursorWindowArr;
        this.statusCode = i2;
        this.metadata = bundle;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                int i = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.windows;
                    if (i >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i].close();
                    i++;
                }
            }
        }
    }

    protected final void finalize() {
        boolean z;
        try {
            if (this.isLeakDetectionEnabled && this.windows.length > 0) {
                synchronized (this) {
                    z = this.mClosed;
                }
                if (!z) {
                    close();
                    Log.e("DataBuffer", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(this, "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ", ")"));
                }
            }
        } finally {
            super.finalize();
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String[] strArr = this.columns;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 1, strArr);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 2, this.windows, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.statusCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 4, this.metadata);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1000, this.mVersionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }
}
