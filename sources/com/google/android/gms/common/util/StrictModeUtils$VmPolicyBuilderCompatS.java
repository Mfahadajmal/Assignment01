package com.google.android.gms.common.util;

import _COROUTINE._BOUNDARY;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;
import com.google.android.gms.googlehelp.InProductHelp;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StrictModeUtils$VmPolicyBuilderCompatS {
    private static Context applicationContext;
    private static Boolean isInstantApp;

    public static int beginObjectHeader(Parcel parcel) {
        return beginVariableData(parcel, 20293);
    }

    public static int beginVariableData(Parcel parcel, int i) {
        parcel.writeInt(i | (-65536));
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkHandlerThread(Handler handler) {
        String str;
        Looper myLooper = Looper.myLooper();
        if (myLooper != handler.getLooper()) {
            if (myLooper != null) {
                str = myLooper.getThread().getName();
            } else {
                str = "null current looper";
            }
            throw new IllegalStateException("Must be called on " + handler.getLooper().getThread().getName() + " thread, but got " + str + ".");
        }
    }

    public static void checkNotEmpty$ar$ds(String str) {
        if (!TextUtils.isEmpty(str)) {
        } else {
            throw new IllegalArgumentException("Given String is empty or null");
        }
    }

    public static void checkNotEmpty$ar$ds$c11d1227_0(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
        } else {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static void checkNotGoogleApiHandlerThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && Objects.equals(myLooper.getThread().getName(), "GoogleApiHandler")) {
            throw new IllegalStateException("Must not be called on GoogleApiHandler thread.");
        }
    }

    public static void checkNotMainThread() {
        checkNotMainThread("Must not be called on the main application thread");
    }

    public static void checkNotNull$ar$ds$4e7b8cd1_1(Object obj, Object obj2) {
        if (obj != null) {
        } else {
            throw new NullPointerException((String) obj2);
        }
    }

    public static void checkNotNull$ar$ds$ca384cd1_1(Object obj) {
        if (obj != null) {
        } else {
            throw new NullPointerException("null reference");
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static boolean contains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static Bundle createBundle(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(dataPosition + readSize);
        return readBundle;
    }

    public static byte[] createByteArray(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createByteArray;
    }

    public static byte[][] createByteArrayArray(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        byte[][] bArr = new byte[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            bArr[i2] = parcel.createByteArray();
        }
        parcel.setDataPosition(dataPosition + readSize);
        return bArr;
    }

    public static int[] createIntArray(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createIntArray;
    }

    public static ListenerHolder createListenerHolder(Object obj, Looper looper, String str) {
        checkNotNull$ar$ds$4e7b8cd1_1(looper, "Looper must not be null");
        checkNotNull$ar$ds$4e7b8cd1_1(str, "Listener type must not be null");
        return new ListenerHolder(looper, obj, str);
    }

    public static ArrayList createLongList(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int readInt = parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList.add(Long.valueOf(parcel.readLong()));
        }
        parcel.setDataPosition(dataPosition + readSize);
        return arrayList;
    }

    public static Parcelable createParcelable(Parcel parcel, int i, Parcelable.Creator creator) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + readSize);
        return parcelable;
    }

    public static String createString(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + readSize);
        return readString;
    }

    public static String[] createStringArray(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createStringArray;
    }

    public static ArrayList createStringList(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(dataPosition + readSize);
        return createStringArrayList;
    }

    public static Object[] createTypedArray(Parcel parcel, int i, Parcelable.Creator creator) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        Object[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + readSize);
        return createTypedArray;
    }

    public static ArrayList createTypedList(Parcel parcel, int i, Parcelable.Creator creator) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        ArrayList createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(dataPosition + readSize);
        return createTypedArrayList;
    }

    public static void enforceSize$ar$ds(Parcel parcel, int i, int i2) {
        if (i == i2) {
            return;
        }
        throw new SafeParcelReader$ParseException("Expected size " + i2 + " got " + i + " (0x" + Integer.toHexString(i) + ")", parcel);
    }

    public static void ensureAtEnd(Parcel parcel, int i) {
        if (parcel.dataPosition() == i) {
        } else {
            throw new SafeParcelReader$ParseException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Overread allowed size end="), parcel);
        }
    }

    public static void finishVariableData(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static ApiException fromStatus(Status status) {
        if (status.pendingIntent != null) {
            return new ResolvableApiException(status);
        }
        return new ApiException(status);
    }

    public static String getActionBarTitle(Activity activity) {
        ViewGroup viewGroup;
        String charSequence = activity.getTitle().toString();
        int identifier = activity.getResources().getIdentifier("action_bar", "id", activity.getPackageName());
        if (identifier != 0 && (viewGroup = (ViewGroup) activity.findViewById(identifier)) != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof TextView) {
                    return ((TextView) childAt).getText().toString();
                }
            }
        }
        return charSequence;
    }

    public static int getFieldId(int i) {
        return (char) i;
    }

    public static synchronized boolean isInstantApp(Context context) {
        boolean isInstantApp2;
        Boolean bool;
        synchronized (StrictModeUtils$VmPolicyBuilderCompatS.class) {
            Context applicationContext2 = context.getApplicationContext();
            Context context2 = applicationContext;
            if (context2 != null && (bool = isInstantApp) != null && context2 == applicationContext2) {
                return bool.booleanValue();
            }
            isInstantApp = null;
            isInstantApp2 = applicationContext2.getPackageManager().isInstantApp();
            Boolean valueOf = Boolean.valueOf(isInstantApp2);
            isInstantApp = valueOf;
            applicationContext = applicationContext2;
            return valueOf.booleanValue();
        }
    }

    public static int parseBuildVersion(int i) {
        if (i == -1) {
            return -1;
        }
        return i / 1000;
    }

    public static StrictMode.VmPolicy.Builder permitUnsafeIntentLaunch(StrictMode.VmPolicy.Builder builder) {
        StrictMode.VmPolicy.Builder permitUnsafeIntentLaunch;
        permitUnsafeIntentLaunch = builder.permitUnsafeIntentLaunch();
        return permitUnsafeIntentLaunch;
    }

    private static void readAndEnforceSize(Parcel parcel, int i, int i2) {
        int readSize = readSize(parcel, i);
        if (readSize == i2) {
            return;
        }
        throw new SafeParcelReader$ParseException("Expected size " + i2 + " got " + readSize + " (0x" + Integer.toHexString(readSize) + ")", parcel);
    }

    public static boolean readBoolean(Parcel parcel, int i) {
        readAndEnforceSize(parcel, i, 4);
        if (parcel.readInt() != 0) {
            return true;
        }
        return false;
    }

    public static double readDouble(Parcel parcel, int i) {
        readAndEnforceSize(parcel, i, 8);
        return parcel.readDouble();
    }

    public static float readFloat(Parcel parcel, int i) {
        readAndEnforceSize(parcel, i, 4);
        return parcel.readFloat();
    }

    public static int readHeader(Parcel parcel) {
        return parcel.readInt();
    }

    public static IBinder readIBinder(Parcel parcel, int i) {
        int readSize = readSize(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (readSize == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(dataPosition + readSize);
        return readStrongBinder;
    }

    public static int readInt(Parcel parcel, int i) {
        readAndEnforceSize(parcel, i, 4);
        return parcel.readInt();
    }

    public static long readLong(Parcel parcel, int i) {
        readAndEnforceSize(parcel, i, 8);
        return parcel.readLong();
    }

    public static int readSize(Parcel parcel, int i) {
        if ((i & (-65536)) != -65536) {
            return (char) (i >> 16);
        }
        return parcel.readInt();
    }

    public static void serializeToIntentExtra$ar$ds(SafeParcelable safeParcelable, Intent intent) {
        Parcel obtain = Parcel.obtain();
        AdditionalConsentConfigCreator.writeToParcel((InProductHelp) safeParcelable, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        intent.putExtra("EXTRA_IN_PRODUCT_HELP", marshall);
    }

    public static void setResultOrApiException$ar$class_merging$3b9aef9e_0$ar$class_merging(Status status, Object obj, AppLifecycleMonitor appLifecycleMonitor) {
        if (status.isSuccess()) {
            appLifecycleMonitor.setResult(obj);
        } else {
            appLifecycleMonitor.setException(fromStatus(status));
        }
    }

    public static void skipUnknownField(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + readSize(parcel, i));
    }

    public static boolean trySetResultOrApiException$ar$class_merging$ar$class_merging(Status status, Object obj, AppLifecycleMonitor appLifecycleMonitor) {
        if (status.isSuccess()) {
            return appLifecycleMonitor.trySetResult(obj);
        }
        return appLifecycleMonitor.trySetException(fromStatus(status));
    }

    public static int validateObjectHeader(Parcel parcel) {
        int readInt = parcel.readInt();
        int readSize = readSize(parcel, readInt);
        int fieldId = getFieldId(readInt);
        int dataPosition = parcel.dataPosition();
        if (fieldId == 20293) {
            int i = readSize + dataPosition;
            if (i >= dataPosition && i <= parcel.dataSize()) {
                return i;
            }
            throw new SafeParcelReader$ParseException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i, dataPosition, "Size read is invalid start=", " end="), parcel);
        }
        throw new SafeParcelReader$ParseException("Expected object header. Got 0x".concat(String.valueOf(Integer.toHexString(readInt))), parcel);
    }

    public static void writeBoolean(Parcel parcel, int i, boolean z) {
        writeHeader(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void writeBundle$ar$ds(Parcel parcel, int i, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeBundle(bundle);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeByteArray$ar$ds(Parcel parcel, int i, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeByteArray(bArr);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeByteArrayArray$ar$ds(Parcel parcel, int i, byte[][] bArr) {
        if (bArr == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeInt(bArr.length);
        for (byte[] bArr2 : bArr) {
            parcel.writeByteArray(bArr2);
        }
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeDouble(Parcel parcel, int i, double d) {
        writeHeader(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void writeFloat(Parcel parcel, int i, float f) {
        writeHeader(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void writeHeader(Parcel parcel, int i, int i2) {
        parcel.writeInt(i | (i2 << 16));
    }

    public static void writeIBinder$ar$ds(Parcel parcel, int i, IBinder iBinder) {
        if (iBinder == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeStrongBinder(iBinder);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeInt(Parcel parcel, int i, int i2) {
        writeHeader(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void writeIntArray$ar$ds(Parcel parcel, int i, int[] iArr) {
        if (iArr == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeIntArray(iArr);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeLong(Parcel parcel, int i, long j) {
        writeHeader(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void writeLongList$ar$ds(Parcel parcel, int i, List list) {
        if (list == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeLong(((Long) list.get(i2)).longValue());
        }
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeParcelable$ar$ds(Parcel parcel, int i, Parcelable parcelable, int i2) {
        if (parcelable == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcelable.writeToParcel(parcel, i2);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeString$ar$ds(Parcel parcel, int i, String str) {
        if (str == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeString(str);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeStringArray$ar$ds(Parcel parcel, int i, String[] strArr) {
        if (strArr == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeStringArray(strArr);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeStringList$ar$ds(Parcel parcel, int i, List list) {
        if (list == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeStringList(list);
        finishVariableData(parcel, beginVariableData);
    }

    public static void writeTypedArray$ar$ds(Parcel parcel, int i, Parcelable[] parcelableArr, int i2) {
        if (parcelableArr == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        parcel.writeInt(parcelableArr.length);
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                writeTypedItemWithSize(parcel, parcelable, i2);
            }
        }
        finishVariableData(parcel, beginVariableData);
    }

    private static void writeTypedItemWithSize(Parcel parcel, Parcelable parcelable, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static void writeTypedList$ar$ds(Parcel parcel, int i, List list) {
        if (list == null) {
            return;
        }
        int beginVariableData = beginVariableData(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            Parcelable parcelable = (Parcelable) list.get(i2);
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                writeTypedItemWithSize(parcel, parcelable, 0);
            }
        }
        finishVariableData(parcel, beginVariableData);
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static void checkNotMainThread(String str) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }
}
