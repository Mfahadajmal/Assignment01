package logs.proto.wireless.performance.mobile;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$UidHealthProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final BatteryMetric$UidHealthProto DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public BatteryMetric$Timer audio_;
    public int bitField0_;
    public int bitField1_;
    public long bluetoothIdleMs_;
    public long bluetoothPowerMams_;
    public long bluetoothRxBytes_;
    public long bluetoothRxMs_;
    public long bluetoothRxPackets_;
    public BatteryMetric$Timer bluetoothScan_;
    public long bluetoothTxBytes_;
    public long bluetoothTxMs_;
    public long bluetoothTxPackets_;
    public long buttonUserActivityCount_;
    public BatteryMetric$Timer camera_;
    public long cpuPowerMams_;
    public BatteryMetric$Timer flashlight_;
    public BatteryMetric$Timer foregroundActivity_;
    public BatteryMetric$Timer gpsSensor_;
    public long mobileIdleMs_;
    public long mobilePowerMams_;
    public BatteryMetric$Timer mobileRadioActive_;
    public long mobileRxBytes_;
    public long mobileRxMs_;
    public long mobileRxPackets_;
    public long mobileTxBytes_;
    public long mobileTxMs_;
    public long mobileTxPackets_;
    public long otherUserActivityCount_;
    public BatteryMetric$Timer processStateBackgroundMs_;
    public BatteryMetric$Timer processStateCachedMs_;
    public BatteryMetric$Timer processStateForegroundMs_;
    public BatteryMetric$Timer processStateForegroundServiceMs_;
    public BatteryMetric$Timer processStateTopMs_;
    public BatteryMetric$Timer processStateTopSleepingMs_;
    public long realtimeBatteryMs_;
    public long realtimeScreenOffBatteryMs_;
    public long systemCpuTimeMs_;
    public long touchUserActivityCount_;
    public long uptimeBatteryMs_;
    public long uptimeScreenOffBatteryMs_;
    public long userCpuTimeMs_;
    public BatteryMetric$Timer vibrator_;
    public BatteryMetric$Timer video_;
    public long wifiFullLockMs_;
    public long wifiIdleMs_;
    public long wifiMulticastMs_;
    public long wifiPowerMams_;
    public long wifiRunningMs_;
    public long wifiRxBytes_;
    public long wifiRxMs_;
    public long wifiRxPackets_;
    public BatteryMetric$Timer wifiScan_;
    public long wifiTxBytes_;
    public long wifiTxMs_;
    public long wifiTxPackets_;
    public Internal.ProtobufList wakelocksFull_ = emptyProtobufList();
    public Internal.ProtobufList wakelocksPartial_ = emptyProtobufList();
    public Internal.ProtobufList wakelocksWindow_ = emptyProtobufList();
    public Internal.ProtobufList wakelocksDraw_ = emptyProtobufList();
    public Internal.ProtobufList syncs_ = emptyProtobufList();
    public Internal.ProtobufList jobs_ = emptyProtobufList();
    public Internal.ProtobufList sensors_ = emptyProtobufList();
    public Internal.ProtobufList statsPids_ = emptyProtobufList();
    public Internal.ProtobufList statsProcesses_ = emptyProtobufList();
    public Internal.ProtobufList statsPackages_ = emptyProtobufList();

    static {
        BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = new BatteryMetric$UidHealthProto();
        DEFAULT_INSTANCE = batteryMetric$UidHealthProto;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$UidHealthProto.class, batteryMetric$UidHealthProto);
    }

    private BatteryMetric$UidHealthProto() {
    }

    public static /* synthetic */ void access$11500(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 32;
        batteryMetric$UidHealthProto.wifiIdleMs_ = j;
    }

    public static /* synthetic */ void access$11700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 64;
        batteryMetric$UidHealthProto.wifiRxMs_ = j;
    }

    public static /* synthetic */ void access$11900(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        batteryMetric$UidHealthProto.wifiTxMs_ = j;
    }

    public static /* synthetic */ void access$12100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 256;
        batteryMetric$UidHealthProto.wifiPowerMams_ = j;
    }

    public static /* synthetic */ void access$12300(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 512;
        batteryMetric$UidHealthProto.bluetoothIdleMs_ = j;
    }

    public static /* synthetic */ void access$12500(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 1024;
        batteryMetric$UidHealthProto.bluetoothRxMs_ = j;
    }

    public static /* synthetic */ void access$12700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 2048;
        batteryMetric$UidHealthProto.bluetoothTxMs_ = j;
    }

    public static /* synthetic */ void access$12900(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 4096;
        batteryMetric$UidHealthProto.bluetoothPowerMams_ = j;
    }

    public static /* synthetic */ void access$13100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 8192;
        batteryMetric$UidHealthProto.mobileIdleMs_ = j;
    }

    public static /* synthetic */ void access$13300(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 16384;
        batteryMetric$UidHealthProto.mobileRxMs_ = j;
    }

    public static /* synthetic */ void access$13500(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 32768;
        batteryMetric$UidHealthProto.mobileTxMs_ = j;
    }

    public static /* synthetic */ void access$13700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 65536;
        batteryMetric$UidHealthProto.mobilePowerMams_ = j;
    }

    public static /* synthetic */ void access$13900(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 131072;
        batteryMetric$UidHealthProto.wifiRunningMs_ = j;
    }

    public static /* synthetic */ void access$14100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 262144;
        batteryMetric$UidHealthProto.wifiFullLockMs_ = j;
    }

    public static /* synthetic */ void access$14300(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.wifiScan_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 524288;
    }

    public static /* synthetic */ void access$14600(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 1048576;
        batteryMetric$UidHealthProto.wifiMulticastMs_ = j;
    }

    public static /* synthetic */ void access$14800(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.audio_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 2097152;
    }

    public static /* synthetic */ void access$15100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.video_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 4194304;
    }

    public static /* synthetic */ void access$15400(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.flashlight_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 8388608;
    }

    public static /* synthetic */ void access$15700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.camera_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 16777216;
    }

    public static /* synthetic */ void access$16000(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.foregroundActivity_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 33554432;
    }

    public static /* synthetic */ void access$16300(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.bluetoothScan_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 67108864;
    }

    public static /* synthetic */ void access$16600(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.processStateTopMs_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 134217728;
    }

    public static /* synthetic */ void access$16900(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.processStateForegroundServiceMs_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 268435456;
    }

    public static /* synthetic */ void access$17200(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.processStateTopSleepingMs_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 536870912;
    }

    public static /* synthetic */ void access$17500(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.processStateForegroundMs_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 1073741824;
    }

    public static /* synthetic */ void access$17800(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.processStateBackgroundMs_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= Integer.MIN_VALUE;
    }

    public static /* synthetic */ void access$18100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.processStateCachedMs_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField1_ |= 1;
    }

    public static /* synthetic */ void access$18400(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.vibrator_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField1_ |= 2;
    }

    public static /* synthetic */ void access$18700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 4;
        batteryMetric$UidHealthProto.otherUserActivityCount_ = j;
    }

    public static /* synthetic */ void access$18900(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 8;
        batteryMetric$UidHealthProto.buttonUserActivityCount_ = j;
    }

    public static /* synthetic */ void access$19100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 16;
        batteryMetric$UidHealthProto.touchUserActivityCount_ = j;
    }

    public static /* synthetic */ void access$19300(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 32;
        batteryMetric$UidHealthProto.mobileRxBytes_ = j;
    }

    public static /* synthetic */ void access$19500(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 64;
        batteryMetric$UidHealthProto.mobileTxBytes_ = j;
    }

    public static /* synthetic */ void access$19700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        batteryMetric$UidHealthProto.wifiRxBytes_ = j;
    }

    public static /* synthetic */ void access$19900(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 256;
        batteryMetric$UidHealthProto.wifiTxBytes_ = j;
    }

    public static /* synthetic */ void access$20100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 512;
        batteryMetric$UidHealthProto.bluetoothRxBytes_ = j;
    }

    public static /* synthetic */ void access$20300(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 1024;
        batteryMetric$UidHealthProto.bluetoothTxBytes_ = j;
    }

    public static /* synthetic */ void access$20500(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 2048;
        batteryMetric$UidHealthProto.mobileRxPackets_ = j;
    }

    public static /* synthetic */ void access$20700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 4096;
        batteryMetric$UidHealthProto.mobileTxPackets_ = j;
    }

    public static /* synthetic */ void access$20900(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 8192;
        batteryMetric$UidHealthProto.wifiRxPackets_ = j;
    }

    public static /* synthetic */ void access$21100(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 16384;
        batteryMetric$UidHealthProto.wifiTxPackets_ = j;
    }

    public static /* synthetic */ void access$21300(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 32768;
        batteryMetric$UidHealthProto.bluetoothRxPackets_ = j;
    }

    public static /* synthetic */ void access$21500(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 65536;
        batteryMetric$UidHealthProto.bluetoothTxPackets_ = j;
    }

    public static /* synthetic */ void access$21700(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.mobileRadioActive_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField1_ |= 131072;
    }

    public static /* synthetic */ void access$22000(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 262144;
        batteryMetric$UidHealthProto.userCpuTimeMs_ = j;
    }

    public static /* synthetic */ void access$22200(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 524288;
        batteryMetric$UidHealthProto.systemCpuTimeMs_ = j;
    }

    public static /* synthetic */ void access$22400(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField1_ |= 1048576;
        batteryMetric$UidHealthProto.cpuPowerMams_ = j;
    }

    public static /* synthetic */ void access$4400(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 1;
        batteryMetric$UidHealthProto.realtimeBatteryMs_ = j;
    }

    public static /* synthetic */ void access$4600(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 2;
        batteryMetric$UidHealthProto.uptimeBatteryMs_ = j;
    }

    public static /* synthetic */ void access$4800(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 4;
        batteryMetric$UidHealthProto.realtimeScreenOffBatteryMs_ = j;
    }

    public static /* synthetic */ void access$5000(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, long j) {
        batteryMetric$UidHealthProto.bitField0_ |= 8;
        batteryMetric$UidHealthProto.uptimeScreenOffBatteryMs_ = j;
    }

    public static /* synthetic */ void access$8800(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$UidHealthProto.gpsSensor_ = batteryMetric$Timer;
        batteryMetric$UidHealthProto.bitField0_ |= 16;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001?\u0000\u0002\u0001@?\u0000\n\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005\u001b\u0006\u001b\u0007\u001b\b\u001b\t\u001b\n\u001b\u000bဉ\u0004\f\u001b\r\u001b\u000e\u001b\u000f\u001b\u0010ဂ\u0005\u0011ဂ\u0006\u0012ဂ\u0007\u0013ဂ\b\u0014ဂ\t\u0015ဂ\n\u0016ဂ\u000b\u0017ဂ\f\u0018ဂ\r\u0019ဂ\u000e\u001aဂ\u000f\u001bဂ\u0010\u001cဂ\u0011\u001dဂ\u0012\u001eဉ\u0013\u001fဂ\u0014 ဉ\u0015!ဉ\u0016\"ဉ\u0017#ဉ\u0018$ဉ\u0019%ဉ\u001a&ဉ\u001b'ဉ\u001c(ဉ\u001d)ဉ\u001e*ဉ\u001f+ဉ ,ဉ!-ဂ\".ဂ#/ဂ$0ဂ%1ဂ&2ဂ'3ဂ(4ဂ)5ဂ*6ဂ+7ဂ,8ဂ-9ဂ.:ဂ/;ဂ0=ဉ1>ဂ2?ဂ3@ဂ4", new Object[]{"bitField0_", "bitField1_", "realtimeBatteryMs_", "uptimeBatteryMs_", "realtimeScreenOffBatteryMs_", "uptimeScreenOffBatteryMs_", "wakelocksFull_", BatteryMetric$Timer.class, "wakelocksPartial_", BatteryMetric$Timer.class, "wakelocksWindow_", BatteryMetric$Timer.class, "wakelocksDraw_", BatteryMetric$Timer.class, "syncs_", BatteryMetric$Timer.class, "jobs_", BatteryMetric$Timer.class, "gpsSensor_", "sensors_", BatteryMetric$Timer.class, "statsPids_", BatteryMetric$PidHealthProto.class, "statsProcesses_", BatteryMetric$ProcessHealthProto.class, "statsPackages_", BatteryMetric$PackageHealthProto.class, "wifiIdleMs_", "wifiRxMs_", "wifiTxMs_", "wifiPowerMams_", "bluetoothIdleMs_", "bluetoothRxMs_", "bluetoothTxMs_", "bluetoothPowerMams_", "mobileIdleMs_", "mobileRxMs_", "mobileTxMs_", "mobilePowerMams_", "wifiRunningMs_", "wifiFullLockMs_", "wifiScan_", "wifiMulticastMs_", "audio_", "video_", "flashlight_", "camera_", "foregroundActivity_", "bluetoothScan_", "processStateTopMs_", "processStateForegroundServiceMs_", "processStateTopSleepingMs_", "processStateForegroundMs_", "processStateBackgroundMs_", "processStateCachedMs_", "vibrator_", "otherUserActivityCount_", "buttonUserActivityCount_", "touchUserActivityCount_", "mobileRxBytes_", "mobileTxBytes_", "wifiRxBytes_", "wifiTxBytes_", "bluetoothRxBytes_", "bluetoothTxBytes_", "mobileRxPackets_", "mobileTxPackets_", "wifiRxPackets_", "wifiTxPackets_", "bluetoothRxPackets_", "bluetoothTxPackets_", "mobileRadioActive_", "userCpuTimeMs_", "systemCpuTimeMs_", "cpuPowerMams_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatteryMetric$UidHealthProto();
            case NEW_BUILDER:
                return new SystemHealthProto$PackedHistogram.Builder((short[]) null, (byte[]) null, (byte[]) null);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$UidHealthProto.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final void ensureJobsIsMutable() {
        Internal.ProtobufList protobufList = this.jobs_;
        if (!protobufList.isModifiable()) {
            this.jobs_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public final void ensureSensorsIsMutable() {
        Internal.ProtobufList protobufList = this.sensors_;
        if (!protobufList.isModifiable()) {
            this.sensors_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public final void ensureSyncsIsMutable() {
        Internal.ProtobufList protobufList = this.syncs_;
        if (!protobufList.isModifiable()) {
            this.syncs_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public final void ensureWakelocksDrawIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksDraw_;
        if (!protobufList.isModifiable()) {
            this.wakelocksDraw_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public final void ensureWakelocksFullIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksFull_;
        if (!protobufList.isModifiable()) {
            this.wakelocksFull_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public final void ensureWakelocksPartialIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksPartial_;
        if (!protobufList.isModifiable()) {
            this.wakelocksPartial_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public final void ensureWakelocksWindowIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksWindow_;
        if (!protobufList.isModifiable()) {
            this.wakelocksWindow_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }
}
