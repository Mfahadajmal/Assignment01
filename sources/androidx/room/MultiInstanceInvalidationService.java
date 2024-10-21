package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import androidx.room.IMultiInstanceInvalidationService;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiInstanceInvalidationService extends Service {
    public int maxClientId;
    public final Map clientNames = new LinkedHashMap();
    public final RemoteCallbackList callbackList = new RemoteCallbackList() { // from class: androidx.room.MultiInstanceInvalidationService$callbackList$1
        @Override // android.os.RemoteCallbackList
        public final /* bridge */ /* synthetic */ void onCallbackDied(IInterface iInterface, Object obj) {
            ((IMultiInstanceInvalidationCallback) iInterface).getClass();
            obj.getClass();
            MultiInstanceInvalidationService.this.clientNames.remove((Integer) obj);
        }
    };
    private final IMultiInstanceInvalidationService.Stub binder = new IMultiInstanceInvalidationService.Stub(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        intent.getClass();
        return this.binder;
    }
}
