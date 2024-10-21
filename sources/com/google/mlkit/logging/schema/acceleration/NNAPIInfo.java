package com.google.mlkit.logging.schema.acceleration;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.StatsEvent;
import android.util.StatsLog;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NNAPIInfo {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DeviceInfo {
        public static long estimateHeadersSizeInBytes(Map map) {
            long j = 0;
            if (map == null) {
                return 0L;
            }
            for (Map.Entry entry : map.entrySet()) {
                if (((String) entry.getKey()) != null) {
                    j += r3.length();
                }
                if (entry.getValue() != null) {
                    while (((List) entry.getValue()).iterator().hasNext()) {
                        j += ((String) r2.next()).length();
                    }
                }
            }
            return j;
        }

        public static int finishedReasonToCronetTrafficInfoRequestTerminalState$ar$edu(int i) {
            if (i == 0) {
                return 1;
            }
            if (i != 1) {
                return 3;
            }
            return 2;
        }

        public static Bundle getMetaData(Context context) {
            ServiceInfo serviceInfo;
            try {
                serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, "android.net.http.MetaDataHolder"), 787072);
            } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
                serviceInfo = null;
            }
            if (serviceInfo != null) {
                return serviceInfo.metaData;
            }
            return new Bundle();
        }
    }

    public static void checkDirect(ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
        } else {
            throw new IllegalArgumentException("byteBuffer must be a direct ByteBuffer.");
        }
    }

    public static void checkHasRemaining(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
        } else {
            throw new IllegalArgumentException("ByteBuffer is already full.");
        }
    }

    public static void write$ar$ds$3303faad_0(long j, int i, int i2, int i3, int i4, long[] jArr, long[] jArr2) {
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(764);
        newBuilder.writeLong(j);
        newBuilder.writeInt(i);
        newBuilder.writeInt(i2);
        newBuilder.writeInt(i3);
        newBuilder.writeInt(i4);
        newBuilder.writeLongArray(jArr);
        newBuilder.writeLongArray(jArr2);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
    }

    public static void write$ar$ds$c46fd982_0(long j, int i, int i2, int i3, int i4, int i5, long j2, int i6, int i7, boolean z, boolean z2, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(704);
        newBuilder.writeLong(j);
        newBuilder.writeInt(i);
        newBuilder.writeInt(i2);
        newBuilder.writeInt(i3);
        newBuilder.writeInt(i4);
        newBuilder.writeInt(i5);
        newBuilder.writeLong(j2);
        newBuilder.writeInt(i6);
        newBuilder.writeInt(i7);
        newBuilder.writeBoolean(z);
        newBuilder.writeBoolean(z2);
        newBuilder.writeInt(i8);
        newBuilder.writeInt(i9);
        newBuilder.writeInt(i10);
        newBuilder.writeLong(-1L);
        newBuilder.writeLong(-1L);
        newBuilder.writeInt(i11);
        newBuilder.writeInt(i12);
        newBuilder.writeInt(i13);
        newBuilder.writeInt(i14);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
    }

    public static void write$ar$ds$cda622eb_0(long j, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, int i6, boolean z3, boolean z4, boolean z5, int i7, String str, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, long j2) {
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(703);
        newBuilder.writeLong(j);
        newBuilder.writeInt(i);
        newBuilder.writeInt(i2);
        newBuilder.writeInt(i3);
        newBuilder.writeInt(i4);
        newBuilder.writeInt(i5);
        newBuilder.writeBoolean(z);
        newBuilder.writeBoolean(z2);
        newBuilder.writeInt(i6);
        newBuilder.writeBoolean(z3);
        newBuilder.writeBoolean(z4);
        newBuilder.writeBoolean(z5);
        newBuilder.writeInt(i7);
        newBuilder.writeString(str);
        newBuilder.writeInt(i8);
        newBuilder.writeInt(i9);
        newBuilder.writeInt(i10);
        newBuilder.writeInt(i11);
        newBuilder.writeInt(i12);
        newBuilder.writeInt(i13);
        newBuilder.writeInt(i14);
        newBuilder.writeInt(i15);
        newBuilder.writeInt(i16);
        newBuilder.writeInt(i17);
        newBuilder.writeInt(i18);
        newBuilder.writeInt(i19);
        newBuilder.writeInt(i20);
        newBuilder.writeInt(i21);
        newBuilder.writeInt(i22);
        newBuilder.writeInt(i23);
        newBuilder.writeInt(i24);
        newBuilder.writeInt(i25);
        newBuilder.writeInt(i26);
        newBuilder.writeInt(i27);
        newBuilder.writeInt(i28);
        newBuilder.writeLong(j2);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
    }

    public static void write$ar$ds$d3c1bcd5_0(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
        StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
        newBuilder.setAtomId(762);
        newBuilder.writeLong(j);
        newBuilder.writeInt(i);
        newBuilder.writeInt(i2);
        newBuilder.writeInt(i3);
        newBuilder.writeInt(i4);
        newBuilder.writeInt(i5);
        newBuilder.writeInt(i6);
        newBuilder.writeInt(i7);
        newBuilder.writeInt(i8);
        newBuilder.writeInt(i9);
        newBuilder.writeInt(i10);
        newBuilder.writeInt(i11);
        newBuilder.writeInt(i12);
        newBuilder.writeInt(i13);
        newBuilder.addBooleanAnnotation((byte) 1, true);
        newBuilder.usePooledBuffer();
        StatsLog.write(newBuilder.build());
    }
}
