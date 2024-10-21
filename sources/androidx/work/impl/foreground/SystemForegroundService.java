package androidx.work.impl.foreground;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.support.v7.view.WindowCallbackWrapper;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.LifecycleService;
import androidx.transition.ViewUtilsApi19;
import androidx.work.Logger;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import androidx.work.impl.constraints.NetworkRequestConstraintController$track$1;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.utils.SerialExecutorImpl;
import java.util.Objects;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SystemForegroundService extends LifecycleService implements SystemForegroundDispatcher.Callback {
    public static final String TAG = Logger.tagWithPrefix("SystemFgService");
    SystemForegroundDispatcher mDispatcher;
    private boolean mIsShutdown;
    NotificationManager mNotificationManager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        static void startForeground(Service service, int i, Notification notification, int i2) {
            service.startForeground(i, notification, i2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api31Impl {
        public static /* synthetic */ int m(long j) {
            return (int) (j ^ (j >>> 32));
        }

        static void startForeground(Service service, int i, Notification notification, int i2) {
            try {
                service.startForeground(i, notification, i2);
            } catch (ForegroundServiceStartNotAllowedException e) {
                Logger.get$ar$ds$16341a92_0();
                Log.w(SystemForegroundService.TAG, "Unable to start foreground service", e);
            }
        }
    }

    private final void initializeDispatcher() {
        this.mNotificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        SystemForegroundDispatcher systemForegroundDispatcher = new SystemForegroundDispatcher(getApplicationContext());
        this.mDispatcher = systemForegroundDispatcher;
        if (systemForegroundDispatcher.mCallback != null) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(SystemForegroundDispatcher.TAG, "A callback already exists.");
        } else {
            systemForegroundDispatcher.mCallback = this;
        }
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public final void cancelNotification(int i) {
        this.mNotificationManager.cancel(i);
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public final void notify(int i, Notification notification) {
        this.mNotificationManager.notify(i, notification);
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        initializeDispatcher();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.mDispatcher.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.mIsShutdown) {
            Logger.get$ar$ds$16341a92_0();
            this.mDispatcher.onDestroy();
            initializeDispatcher();
            this.mIsShutdown = false;
        }
        if (intent != null) {
            SystemForegroundDispatcher systemForegroundDispatcher = this.mDispatcher;
            String action = intent.getAction();
            if ("ACTION_START_FOREGROUND".equals(action)) {
                Logger.get$ar$ds$16341a92_0();
                Objects.toString(intent);
                intent.toString();
                systemForegroundDispatcher.mTaskExecutor$ar$class_merging.executeOnTaskThread(new DelayedWorkTracker.AnonymousClass1(systemForegroundDispatcher, intent.getStringExtra("KEY_WORKSPEC_ID"), 4));
                systemForegroundDispatcher.handleNotify(intent);
            } else if ("ACTION_NOTIFY".equals(action)) {
                systemForegroundDispatcher.handleNotify(intent);
            } else if ("ACTION_CANCEL_WORK".equals(action)) {
                Logger.get$ar$ds$16341a92_0();
                Objects.toString(intent);
                intent.toString();
                String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
                if (stringExtra != null && !TextUtils.isEmpty(stringExtra)) {
                    WorkManagerImpl workManagerImpl = systemForegroundDispatcher.mWorkManagerImpl;
                    UUID fromString = UUID.fromString(stringExtra);
                    fromString.getClass();
                    ViewUtilsApi19.Api29Impl api29Impl = workManagerImpl.mConfiguration.tracer$ar$class_merging$ar$class_merging;
                    SerialExecutorImpl serialTaskExecutor$ar$class_merging = workManagerImpl.mWorkTaskExecutor$ar$class_merging.getSerialTaskExecutor$ar$class_merging();
                    serialTaskExecutor$ar$class_merging.getClass();
                    WindowCallbackWrapper.Api24Impl.launchOperation$ar$class_merging$ar$class_merging(api29Impl, "CancelWorkById", serialTaskExecutor$ar$class_merging, new NetworkRequestConstraintController$track$1.AnonymousClass1(workManagerImpl, fromString, 3, null));
                }
            } else if ("ACTION_STOP_FOREGROUND".equals(action)) {
                Logger.get$ar$ds$16341a92_0();
                SystemForegroundDispatcher.Callback callback = systemForegroundDispatcher.mCallback;
                if (callback != null) {
                    callback.stop();
                }
            }
        }
        return 3;
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public final void startForeground(int i, int i2, Notification notification) {
        if (Build.VERSION.SDK_INT >= 31) {
            Api31Impl.startForeground(this, i, notification, i2);
        } else if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.startForeground(this, i, notification, i2);
        } else {
            startForeground(i, notification);
        }
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public final void stop() {
        this.mIsShutdown = true;
        Logger.get$ar$ds$16341a92_0();
        stopForeground(true);
        stopSelf();
    }
}
