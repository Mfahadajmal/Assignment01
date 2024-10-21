package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IMultiInstanceInvalidationCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx$room$IMultiInstanceInvalidationCallback".replace('$', '.');

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends Binder implements IMultiInstanceInvalidationCallback {
        public static final /* synthetic */ int IMultiInstanceInvalidationCallback$Stub$ar$NoOp = 0;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class Proxy implements IMultiInstanceInvalidationCallback {
            private final IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.room.IMultiInstanceInvalidationCallback
            public final void onInvalidation(String[] strArr) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            throw null;
        }

        @Override // androidx.room.IMultiInstanceInvalidationCallback
        public final void onInvalidation(String[] strArr) {
            throw null;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            throw null;
        }
    }

    void onInvalidation(String[] strArr);
}
