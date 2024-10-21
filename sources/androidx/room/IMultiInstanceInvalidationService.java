package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationCallback;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IMultiInstanceInvalidationService extends IInterface {
    public static final String DESCRIPTOR = "androidx$room$IMultiInstanceInvalidationService".replace('$', '.');

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends Binder implements IMultiInstanceInvalidationService {
        final /* synthetic */ MultiInstanceInvalidationService this$0;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public final void broadcastInvalidation(int i, String[] strArr) {
            strArr.getClass();
            MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
            synchronized (multiInstanceInvalidationService.callbackList) {
                String str = (String) multiInstanceInvalidationService.clientNames.get(Integer.valueOf(i));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                    return;
                }
                int beginBroadcast = multiInstanceInvalidationService.callbackList.beginBroadcast();
                for (int i2 = 0; i2 < beginBroadcast; i2++) {
                    try {
                        Object broadcastCookie = multiInstanceInvalidationService.callbackList.getBroadcastCookie(i2);
                        broadcastCookie.getClass();
                        Integer num = (Integer) broadcastCookie;
                        int intValue = num.intValue();
                        String str2 = (String) multiInstanceInvalidationService.clientNames.get(num);
                        if (i != intValue && Intrinsics.areEqual(str, str2)) {
                            try {
                                ((IMultiInstanceInvalidationCallback) multiInstanceInvalidationService.callbackList.getBroadcastItem(i2)).onInvalidation(strArr);
                            } catch (RemoteException e) {
                                Log.w("ROOM", "Error invoking a remote callback", e);
                            }
                        }
                    } finally {
                        multiInstanceInvalidationService.callbackList.finishBroadcast();
                    }
                }
            }
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = DESCRIPTOR;
            if (i > 0 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback = null;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    broadcastInvalidation(parcel.readInt(), parcel.createStringArray());
                } else {
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    int i3 = IMultiInstanceInvalidationCallback.Stub.IMultiInstanceInvalidationCallback$Stub$ar$NoOp;
                    if (readStrongBinder != null) {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface(IMultiInstanceInvalidationCallback.Stub.DESCRIPTOR);
                        if (queryLocalInterface != null && (queryLocalInterface instanceof IMultiInstanceInvalidationCallback)) {
                            iMultiInstanceInvalidationCallback = (IMultiInstanceInvalidationCallback) queryLocalInterface;
                        } else {
                            iMultiInstanceInvalidationCallback = new IMultiInstanceInvalidationCallback.Stub.Proxy(readStrongBinder);
                        }
                    }
                    unregisterCallback(iMultiInstanceInvalidationCallback, parcel.readInt());
                    parcel2.writeNoException();
                }
            } else {
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                int i4 = IMultiInstanceInvalidationCallback.Stub.IMultiInstanceInvalidationCallback$Stub$ar$NoOp;
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface(IMultiInstanceInvalidationCallback.Stub.DESCRIPTOR);
                    if (queryLocalInterface2 != null && (queryLocalInterface2 instanceof IMultiInstanceInvalidationCallback)) {
                        iMultiInstanceInvalidationCallback = (IMultiInstanceInvalidationCallback) queryLocalInterface2;
                    } else {
                        iMultiInstanceInvalidationCallback = new IMultiInstanceInvalidationCallback.Stub.Proxy(readStrongBinder2);
                    }
                }
                int registerCallback = registerCallback(iMultiInstanceInvalidationCallback, parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(registerCallback);
            }
            return true;
        }

        public final int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
            iMultiInstanceInvalidationCallback.getClass();
            int i = 0;
            if (str == null) {
                return 0;
            }
            MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
            synchronized (multiInstanceInvalidationService.callbackList) {
                int i2 = multiInstanceInvalidationService.maxClientId + 1;
                multiInstanceInvalidationService.maxClientId = i2;
                RemoteCallbackList remoteCallbackList = multiInstanceInvalidationService.callbackList;
                Integer valueOf = Integer.valueOf(i2);
                if (remoteCallbackList.register(iMultiInstanceInvalidationCallback, valueOf)) {
                    multiInstanceInvalidationService.clientNames.put(valueOf, str);
                    i = i2;
                } else {
                    multiInstanceInvalidationService.maxClientId--;
                }
            }
            return i;
        }

        public final void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) {
            iMultiInstanceInvalidationCallback.getClass();
            MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
            synchronized (multiInstanceInvalidationService.callbackList) {
                multiInstanceInvalidationService.callbackList.unregister(iMultiInstanceInvalidationCallback);
            }
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Stub(MultiInstanceInvalidationService multiInstanceInvalidationService) {
            this();
            this.this$0 = multiInstanceInvalidationService;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
