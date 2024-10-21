package com.google.android.accessibility.braille.brailledisplay.platform;

import _COROUTINE._BOUNDARY;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.util.Log;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.brltty.BrailleKeyBinding;
import com.google.android.accessibility.braille.brltty.BrlttyEncoder;
import com.google.android.accessibility.braille.brltty.DeviceInfo;
import com.google.android.accessibility.braille.brltty.Encoder;
import com.google.android.accessibility.braille.brltty.SupportedDevicesHelper;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.GmsClientSupervisorImpl$GmsClientConnectionStatus;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableMap;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Optional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Displayer {
    public Handler bgHandler;
    public HandlerThread bgThread;
    public final RetryingNameResolver.ResolutionResultListener callback$ar$class_merging$dbd9c3ba_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public ConnectableDevice device;
    public BrailleDisplayProperties displayProperties;
    public final Encoder encoder;
    public final AtomicBoolean isDisplayReady = new AtomicBoolean();
    public final AppCompatDelegate.Api33Impl parameterProviderFactory$ar$class_merging$ar$class_merging = new AppCompatDelegate.Api33Impl();
    public final Handler mainHandler = new Handler(Looper.getMainLooper(), new MainHandlerCallback(this, 0));

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EncoderCallback {
        private final Runnable runnable = new WorkerKt$$ExternalSyntheticLambda0(this, 11);

        public EncoderCallback() {
        }

        public final void readAfterDelay(int i) {
            if (Displayer.this.isReady()) {
                Displayer.this.bgHandler.removeCallbacks(this.runnable);
                Displayer.this.bgHandler.postDelayed(this.runnable, i);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MainHandlerCallback implements Handler.Callback {
        final /* synthetic */ Object Displayer$MainHandlerCallback$ar$this$0;
        private final /* synthetic */ int switching_field;

        public MainHandlerCallback(Object obj, int i) {
            this.switching_field = i;
            this.Displayer$MainHandlerCallback$ar$this$0 = obj;
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    int i2 = message.what;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            return false;
                        }
                        synchronized (((GmsClientSupervisor) this.Displayer$MainHandlerCallback$ar$this$0).connectionStatus) {
                            GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig = (GmsClientSupervisor.ConnectionStatusConfig) message.obj;
                            GmsClientSupervisorImpl$GmsClientConnectionStatus gmsClientSupervisorImpl$GmsClientConnectionStatus = (GmsClientSupervisorImpl$GmsClientConnectionStatus) ((GmsClientSupervisor) this.Displayer$MainHandlerCallback$ar$this$0).connectionStatus.get(connectionStatusConfig);
                            if (gmsClientSupervisorImpl$GmsClientConnectionStatus != null && gmsClientSupervisorImpl$GmsClientConnectionStatus.state == 3) {
                                Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + String.valueOf(connectionStatusConfig), new Exception());
                                ComponentName componentName = gmsClientSupervisorImpl$GmsClientConnectionStatus.componentName;
                                if (componentName == null) {
                                    componentName = connectionStatusConfig.componentName;
                                }
                                if (componentName == null) {
                                    String str = connectionStatusConfig.packageName;
                                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str);
                                    componentName = new ComponentName(str, "unknown");
                                }
                                gmsClientSupervisorImpl$GmsClientConnectionStatus.onServiceDisconnected(componentName);
                            }
                        }
                        return true;
                    }
                    synchronized (((GmsClientSupervisor) this.Displayer$MainHandlerCallback$ar$this$0).connectionStatus) {
                        GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig2 = (GmsClientSupervisor.ConnectionStatusConfig) message.obj;
                        GmsClientSupervisorImpl$GmsClientConnectionStatus gmsClientSupervisorImpl$GmsClientConnectionStatus2 = (GmsClientSupervisorImpl$GmsClientConnectionStatus) ((GmsClientSupervisor) this.Displayer$MainHandlerCallback$ar$this$0).connectionStatus.get(connectionStatusConfig2);
                        if (gmsClientSupervisorImpl$GmsClientConnectionStatus2 != null && gmsClientSupervisorImpl$GmsClientConnectionStatus2.hasNoGmsServiceConnections()) {
                            if (gmsClientSupervisorImpl$GmsClientConnectionStatus2.isBound) {
                                gmsClientSupervisorImpl$GmsClientConnectionStatus2.this$0$ar$class_merging$33154e58_0.handler.removeMessages(1, gmsClientSupervisorImpl$GmsClientConnectionStatus2.config);
                                GmsClientSupervisor gmsClientSupervisor = gmsClientSupervisorImpl$GmsClientConnectionStatus2.this$0$ar$class_merging$33154e58_0;
                                gmsClientSupervisor.connectionTracker.unbindService(gmsClientSupervisor.applicationContext, gmsClientSupervisorImpl$GmsClientConnectionStatus2);
                                gmsClientSupervisorImpl$GmsClientConnectionStatus2.isBound = false;
                                gmsClientSupervisorImpl$GmsClientConnectionStatus2.state = 2;
                            }
                            ((GmsClientSupervisor) this.Displayer$MainHandlerCallback$ar$this$0).connectionStatus.remove(connectionStatusConfig2);
                        }
                    }
                    return true;
                }
                int i3 = MessageBg.values$ar$edu$7e01051b_0()[message.what];
                AppCompatTextViewAutoSizeHelper.Api23Impl.v("Displayer", "handleMessage bg ".concat(String.valueOf(MessageBg.toStringGenerated66c19cf6a7add94c(i3))));
                int i4 = i3 - 1;
                if (i3 != 0) {
                    Object obj = this.Displayer$MainHandlerCallback$ar$this$0;
                    if (i4 != 0) {
                        if (i4 != 1) {
                            if (i4 != 2) {
                                if (i4 == 3) {
                                    MessageBg.Displayer$MessageBg$4$handle$ar$poly_enum_reducer$ar$ds$ar$edu(i3, (Displayer) obj);
                                } else {
                                    throw null;
                                }
                            } else {
                                MessageBg.Displayer$MessageBg$3$handle$ar$poly_enum_reducer$ar$edu(i3, (Displayer) obj, message);
                            }
                        } else {
                            MessageBg.Displayer$MessageBg$2$handle$ar$poly_enum_reducer$ar$ds$ar$edu(i3, (Displayer) obj);
                        }
                    } else {
                        MessageBg.Displayer$MessageBg$1$handle$ar$poly_enum_reducer$ar$edu(i3, (Displayer) obj, message);
                    }
                    return true;
                }
                throw null;
            }
            int i5 = MessageMain.values$ar$edu$a67b14e3_0()[message.what];
            AppCompatTextViewAutoSizeHelper.Api23Impl.v("Displayer", "handleMessage main ".concat(String.valueOf(MessageMain.toStringGenerated1abd052008d7efb5(i5))));
            int i6 = i5 - 1;
            if (i5 != 0) {
                Object obj2 = this.Displayer$MainHandlerCallback$ar$this$0;
                if (i6 != 0) {
                    if (i6 != 1) {
                        if (i6 == 2) {
                            ((Displayer) obj2).callback$ar$class_merging$dbd9c3ba_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onBrailleInputEvent((BrailleInputEvent) message.obj);
                        } else {
                            throw null;
                        }
                    } else {
                        Displayer displayer = (Displayer) obj2;
                        displayer.displayProperties = (BrailleDisplayProperties) message.obj;
                        displayer.callback$ar$class_merging$dbd9c3ba_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDisplayReady(displayer.displayProperties);
                    }
                } else {
                    ((Displayer) obj2).callback$ar$class_merging$dbd9c3ba_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onStartFailed();
                }
                return true;
            }
            throw null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MessageBg {
        public static final int START$ar$edu = 1;
        public static final int STOP$ar$edu = 2;
        public static final int WRITE_BRAILLE_DOTS$ar$edu = 3;
        public static final int READ_COMMAND$ar$edu = 4;
        private static final /* synthetic */ int[] $VALUES$ar$edu$ec5c61fc_0 = {START$ar$edu, STOP$ar$edu, WRITE_BRAILLE_DOTS$ar$edu, READ_COMMAND$ar$edu};

        public static void Displayer$MessageBg$1$handle$ar$poly_enum_reducer$ar$edu(int i, Displayer displayer, Message message) {
            Optional empty;
            BrlttyEncoder brlttyEncoder;
            if (i != 0) {
                if (!displayer.isDisplayReady.get()) {
                    Encoder encoder = displayer.encoder;
                    String name = displayer.device.name();
                    boolean z = displayer.device.useHid;
                    String str = (String) message.obj;
                    BrlttyEncoder brlttyEncoder2 = (BrlttyEncoder) encoder;
                    if (brlttyEncoder2.dataFileState$ar$edu == 2) {
                        if (_BOUNDARY.extractTables(brlttyEncoder2.context.getResources(), R.raw.keytables, brlttyEncoder2.tablesDir)) {
                            brlttyEncoder2.dataFileState$ar$edu = 3;
                        } else {
                            brlttyEncoder2.dataFileState$ar$edu = 1;
                        }
                    }
                    SystemClock.elapsedRealtime();
                    int i2 = 0;
                    DeviceInfo deviceInfo = SupportedDevicesHelper.getDeviceInfo(name, false);
                    if (!brlttyEncoder2.initNative(brlttyEncoder2.context)) {
                        empty = Optional.empty();
                    } else {
                        String str2 = deviceInfo.driverCode;
                        boolean startNative = brlttyEncoder2.startNative(str2, str, 2.0f);
                        SystemClock.elapsedRealtime();
                        if (startNative) {
                            ImmutableMap immutableMap = deviceInfo.friendlyKeyNames;
                            BrailleKeyBinding[] keyMapNative = brlttyEncoder2.getKeyMapNative();
                            ArrayList arrayList = new ArrayList();
                            for (BrailleKeyBinding brailleKeyBinding : keyMapNative) {
                                String[] strArr = brailleKeyBinding.keyNames;
                                int length = strArr.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 < length) {
                                        if (!immutableMap.containsKey(strArr[i3])) {
                                            break;
                                        } else {
                                            i3++;
                                        }
                                    } else {
                                        arrayList.add(brailleKeyBinding);
                                        break;
                                    }
                                }
                            }
                            BrailleKeyBinding[] brailleKeyBindingArr = (BrailleKeyBinding[]) arrayList.toArray(new BrailleKeyBinding[0]);
                            int textCellsNative = brlttyEncoder2.getTextCellsNative();
                            int statusCellsNative = brlttyEncoder2.getStatusCellsNative();
                            ImmutableMap immutableMap2 = deviceInfo.friendlyKeyNames;
                            HashMap hashMap = new HashMap();
                            int length2 = brailleKeyBindingArr.length;
                            int i4 = 0;
                            while (i4 < length2) {
                                String[] strArr2 = brailleKeyBindingArr[i4].keyNames;
                                int length3 = strArr2.length;
                                int i5 = i2;
                                while (i5 < length3) {
                                    String str3 = strArr2[i5];
                                    Integer num = (Integer) immutableMap2.get(str3);
                                    if (num != null) {
                                        brlttyEncoder = brlttyEncoder2;
                                        hashMap.put(str3, brlttyEncoder2.context.getString(num.intValue()));
                                    } else {
                                        brlttyEncoder = brlttyEncoder2;
                                        hashMap.put(str3, str3);
                                    }
                                    i5++;
                                    brlttyEncoder2 = brlttyEncoder;
                                }
                                i4++;
                                i2 = 0;
                            }
                            empty = Optional.of(new BrailleDisplayProperties(str2, textCellsNative, statusCellsNative, brailleKeyBindingArr, hashMap));
                        } else {
                            empty = Optional.empty();
                        }
                    }
                    if (empty.isPresent()) {
                        displayer.isDisplayReady.getAndSet(true);
                        Handler handler = displayer.mainHandler;
                        int i6 = MessageMain.DISPLAY_READY$ar$edu;
                        int i7 = i6 - 1;
                        if (i6 != 0) {
                            handler.obtainMessage(i7, empty.get()).sendToTarget();
                            return;
                        }
                        throw null;
                    }
                    Handler handler2 = displayer.mainHandler;
                    int i8 = MessageMain.START_FAILED$ar$edu;
                    int i9 = i8 - 1;
                    if (i8 != 0) {
                        handler2.obtainMessage(i9).sendToTarget();
                        return;
                    }
                    throw null;
                }
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("Displayer", "Braille display has started.");
                return;
            }
            throw null;
        }

        public static void Displayer$MessageBg$2$handle$ar$poly_enum_reducer$ar$ds$ar$edu(int i, Displayer displayer) {
            if (i != 0) {
                if (displayer.isDisplayReady.getAndSet(false)) {
                    displayer.displayProperties = null;
                    ((BrlttyEncoder) displayer.encoder).stopNative();
                    Handler handler = displayer.bgHandler;
                    int i2 = START$ar$edu;
                    int i3 = i2 - 1;
                    if (i2 != 0) {
                        if (!handler.hasMessages(i3)) {
                            AppCompatTextViewAutoSizeHelper.Api23Impl.v("Displayer", "stop a thread");
                            displayer.bgThread.quitSafely();
                            return;
                        }
                        return;
                    }
                    throw null;
                }
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("Displayer", "Braille display has stopped");
                return;
            }
            throw null;
        }

        public static void Displayer$MessageBg$3$handle$ar$poly_enum_reducer$ar$edu(int i, Displayer displayer, Message message) {
            if (i != 0) {
                ((BrlttyEncoder) displayer.encoder).writeWindowNative((byte[]) message.obj);
                return;
            }
            throw null;
        }

        public static void Displayer$MessageBg$4$handle$ar$poly_enum_reducer$ar$ds$ar$edu(int i, Displayer displayer) {
            if (i != 0) {
                int readCommandNative = ((BrlttyEncoder) displayer.encoder).readCommandNative();
                if (readCommandNative < 0) {
                    return;
                }
                BrailleInputEvent brailleInputEvent = new BrailleInputEvent((char) readCommandNative, readCommandNative >> 16, SystemClock.uptimeMillis());
                Handler handler = displayer.mainHandler;
                int i2 = MessageMain.READ_COMMAND_ARRIVED$ar$edu;
                int i3 = i2 - 1;
                if (i2 != 0) {
                    handler.obtainMessage(i3, brailleInputEvent).sendToTarget();
                    return;
                }
                throw null;
            }
            throw null;
        }

        public static /* synthetic */ String toStringGenerated66c19cf6a7add94c(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return "null";
                        }
                        return "READ_COMMAND";
                    }
                    return "WRITE_BRAILLE_DOTS";
                }
                return "STOP";
            }
            return "START";
        }

        public static int[] values$ar$edu$7e01051b_0() {
            return new int[]{START$ar$edu, STOP$ar$edu, WRITE_BRAILLE_DOTS$ar$edu, READ_COMMAND$ar$edu};
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MessageMain {
        public static final int START_FAILED$ar$edu = 1;
        public static final int DISPLAY_READY$ar$edu = 2;
        public static final int READ_COMMAND_ARRIVED$ar$edu = 3;
        private static final /* synthetic */ int[] $VALUES$ar$edu$5e483ad2_0 = {START_FAILED$ar$edu, DISPLAY_READY$ar$edu, READ_COMMAND_ARRIVED$ar$edu};

        public static /* synthetic */ String toStringGenerated1abd052008d7efb5(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return "null";
                    }
                    return "READ_COMMAND_ARRIVED";
                }
                return "DISPLAY_READY";
            }
            return "START_FAILED";
        }

        public static int[] values$ar$edu$a67b14e3_0() {
            return new int[]{START_FAILED$ar$edu, DISPLAY_READY$ar$edu, READ_COMMAND_ARRIVED$ar$edu};
        }
    }

    public Displayer(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.callback$ar$class_merging$dbd9c3ba_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.encoder = new BrlttyEncoder(context, new EncoderCallback());
    }

    public final void consumePacketFromDevice(byte[] bArr) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            try {
                ((BrlttyEncoder) this.encoder).addBytesFromDeviceNative(bArr, bArr.length);
                return;
            } catch (IOException unused) {
                return;
            }
        }
        throw new IllegalStateException("Must be off main thread");
    }

    public final boolean isDisplayReady() {
        return this.isDisplayReady.get();
    }

    public final boolean isReady() {
        if (this.bgHandler != null && this.bgThread.isAlive()) {
            return true;
        }
        AppCompatTextViewAutoSizeHelper.Api23Impl.v("Displayer", "thread has not started or has died.");
        return false;
    }

    public final void readCommand() {
        if (isReady()) {
            Handler handler = this.bgHandler;
            int i = MessageBg.READ_COMMAND$ar$edu;
            int i2 = i - 1;
            if (i != 0) {
                if (!handler.hasMessages(i2)) {
                    Handler handler2 = this.bgHandler;
                    int i3 = MessageBg.READ_COMMAND$ar$edu;
                    int i4 = i3 - 1;
                    if (i3 != 0) {
                        handler2.obtainMessage(i4).sendToTarget();
                        return;
                    }
                    throw null;
                }
                return;
            }
            throw null;
        }
    }

    public final void stop() {
        this.mainHandler.removeCallbacksAndMessages(null);
        Handler handler = this.bgHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Handler handler2 = this.bgHandler;
            int i = MessageBg.STOP$ar$edu;
            int i2 = i - 1;
            if (i != 0) {
                handler2.obtainMessage(i2).sendToTarget();
                return;
            }
            throw null;
        }
    }
}
