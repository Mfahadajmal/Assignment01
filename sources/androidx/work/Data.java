package androidx.work;

import _COROUTINE._BOUNDARY;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import androidx.transition.ViewUtilsApi22;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Data {
    public static final ViewUtilsApi22.Api29Impl Companion$ar$class_merging$841b59fa_0 = new ViewUtilsApi22.Api29Impl();
    public static final Data EMPTY = new ViewModelStore((byte[]) null, (byte[]) null).build();
    public final Map values;

    public Data(Data data) {
        data.getClass();
        this.values = new HashMap(data.values);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Data fromByteArray(byte[] bArr) {
        boolean z;
        Object obj;
        bArr.getClass();
        int length = bArr.length;
        if (length <= 10240) {
            if (length == 0) {
                return EMPTY;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                byte[] bArr2 = new byte[2];
                byteArrayInputStream.read(bArr2);
                if (bArr2[0] == -84 && bArr2[1] == -19) {
                    z = true;
                } else {
                    z = false;
                }
                byteArrayInputStream.reset();
                if (z) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        int readInt = objectInputStream.readInt();
                        for (int i = 0; i < readInt; i++) {
                            String readUTF = objectInputStream.readUTF();
                            readUTF.getClass();
                            linkedHashMap.put(readUTF, objectInputStream.readObject());
                        }
                        OnDeviceShadowRemovalLogEvent.closeFinally(objectInputStream, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            OnDeviceShadowRemovalLogEvent.closeFinally(objectInputStream, th);
                            throw th2;
                        }
                    }
                } else {
                    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                    try {
                        short readShort = dataInputStream.readShort();
                        if (readShort == -21521) {
                            short readShort2 = dataInputStream.readShort();
                            if (readShort2 == 1) {
                                int readInt2 = dataInputStream.readInt();
                                for (int i2 = 0; i2 < readInt2; i2++) {
                                    byte readByte = dataInputStream.readByte();
                                    if (readByte == 0) {
                                        obj = null;
                                    } else if (readByte == 1) {
                                        obj = Boolean.valueOf(dataInputStream.readBoolean());
                                    } else if (readByte == 2) {
                                        obj = Byte.valueOf(dataInputStream.readByte());
                                    } else if (readByte == 3) {
                                        obj = Integer.valueOf(dataInputStream.readInt());
                                    } else if (readByte == 4) {
                                        obj = Long.valueOf(dataInputStream.readLong());
                                    } else if (readByte == 5) {
                                        obj = Float.valueOf(dataInputStream.readFloat());
                                    } else if (readByte == 6) {
                                        obj = Double.valueOf(dataInputStream.readDouble());
                                    } else if (readByte == 7) {
                                        obj = dataInputStream.readUTF();
                                    } else if (readByte == 8) {
                                        int readInt3 = dataInputStream.readInt();
                                        Boolean[] boolArr = new Boolean[readInt3];
                                        for (int i3 = 0; i3 < readInt3; i3++) {
                                            boolArr[i3] = Boolean.valueOf(dataInputStream.readBoolean());
                                        }
                                        obj = (Serializable) boolArr;
                                    } else if (readByte == 9) {
                                        int readInt4 = dataInputStream.readInt();
                                        Byte[] bArr3 = new Byte[readInt4];
                                        for (int i4 = 0; i4 < readInt4; i4++) {
                                            bArr3[i4] = Byte.valueOf(dataInputStream.readByte());
                                        }
                                        obj = (Serializable) bArr3;
                                    } else if (readByte == 10) {
                                        int readInt5 = dataInputStream.readInt();
                                        Integer[] numArr = new Integer[readInt5];
                                        for (int i5 = 0; i5 < readInt5; i5++) {
                                            numArr[i5] = Integer.valueOf(dataInputStream.readInt());
                                        }
                                        obj = (Serializable) numArr;
                                    } else if (readByte == 11) {
                                        int readInt6 = dataInputStream.readInt();
                                        Long[] lArr = new Long[readInt6];
                                        for (int i6 = 0; i6 < readInt6; i6++) {
                                            lArr[i6] = Long.valueOf(dataInputStream.readLong());
                                        }
                                        obj = (Serializable) lArr;
                                    } else if (readByte == 12) {
                                        int readInt7 = dataInputStream.readInt();
                                        Float[] fArr = new Float[readInt7];
                                        for (int i7 = 0; i7 < readInt7; i7++) {
                                            fArr[i7] = Float.valueOf(dataInputStream.readFloat());
                                        }
                                        obj = (Serializable) fArr;
                                    } else if (readByte == 13) {
                                        int readInt8 = dataInputStream.readInt();
                                        Double[] dArr = new Double[readInt8];
                                        for (int i8 = 0; i8 < readInt8; i8++) {
                                            dArr[i8] = Double.valueOf(dataInputStream.readDouble());
                                        }
                                        obj = (Serializable) dArr;
                                    } else if (readByte == 14) {
                                        int readInt9 = dataInputStream.readInt();
                                        String[] strArr = new String[readInt9];
                                        for (int i9 = 0; i9 < readInt9; i9++) {
                                            String readUTF2 = dataInputStream.readUTF();
                                            if (true == Intrinsics.areEqual(readUTF2, "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d")) {
                                                readUTF2 = null;
                                            }
                                            strArr[i9] = readUTF2;
                                        }
                                        obj = (Serializable) strArr;
                                    } else {
                                        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readByte, "Unsupported type "));
                                    }
                                    String readUTF3 = dataInputStream.readUTF();
                                    readUTF3.getClass();
                                    linkedHashMap.put(readUTF3, obj);
                                }
                                OnDeviceShadowRemovalLogEvent.closeFinally(dataInputStream, null);
                            } else {
                                throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readShort2, "Unsupported version number: "));
                            }
                        } else {
                            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readShort, "Magic number doesn't match: "));
                        }
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            OnDeviceShadowRemovalLogEvent.closeFinally(dataInputStream, th3);
                            throw th4;
                        }
                    }
                }
            } catch (IOException e) {
                String str = Data_Kt.TAG;
                Logger.get$ar$ds$16341a92_0();
                Log.e(str, "Error in Data#fromByteArray: ", e);
            } catch (ClassNotFoundException e2) {
                String str2 = Data_Kt.TAG;
                Logger.get$ar$ds$16341a92_0();
                Log.e(str2, "Error in Data#fromByteArray: ", e2);
            }
            return new Data(linkedHashMap);
        }
        throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
    }

    public static final byte[] toByteArrayInternalV1(Data data) {
        return ViewUtilsApi22.Api29Impl.toByteArrayInternalV1$ar$ds(data);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r7 != r8) goto L4
            return r0
        L4:
            r1 = 0
            if (r8 == 0) goto L69
            java.lang.Class r2 = r7.getClass()
            java.lang.Class r3 = r8.getClass()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 != 0) goto L16
            goto L69
        L16:
            androidx.work.Data r8 = (androidx.work.Data) r8
            java.util.Map r2 = r7.values
            java.util.Map r3 = r8.values
            java.util.Set r2 = r2.keySet()
            java.util.Set r3 = r3.keySet()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r3 != 0) goto L2b
            return r1
        L2b:
            java.util.Iterator r2 = r2.iterator()
        L2f:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L68
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.util.Map r4 = r7.values
            java.lang.Object r4 = r4.get(r3)
            java.util.Map r5 = r8.values
            java.lang.Object r3 = r5.get(r3)
            if (r4 == 0) goto L65
            if (r3 != 0) goto L4c
            goto L65
        L4c:
            boolean r5 = r4 instanceof java.lang.Object[]
            if (r5 == 0) goto L5e
            r5 = r4
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            boolean r6 = r3 instanceof java.lang.Object[]
            if (r6 == 0) goto L5e
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            boolean r3 = com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.contentDeepEquals(r5, r3)
            goto L62
        L5e:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
        L62:
            if (r3 != 0) goto L2f
            goto L67
        L65:
            if (r4 == r3) goto L2f
        L67:
            return r1
        L68:
            return r0
        L69:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.equals(java.lang.Object):boolean");
    }

    public final Map getKeyValueMap() {
        Map unmodifiableMap = DesugarCollections.unmodifiableMap(this.values);
        unmodifiableMap.getClass();
        return unmodifiableMap;
    }

    public final String getString(String str) {
        Object obj = this.values.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public final boolean hasKeyWithValueOfType(String str, Class cls) {
        Object obj = this.values.get(str);
        if (obj != null && cls.isAssignableFrom(obj.getClass())) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int i = 0;
        for (Map.Entry entry : this.values.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Object[]) {
                hashCode = Objects.hashCode(entry.getKey()) ^ Arrays.deepHashCode((Object[]) value);
            } else {
                hashCode = entry.hashCode();
            }
            i += hashCode;
        }
        return i * 31;
    }

    public final String toString() {
        return "Data {" + OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(this.values.entrySet(), null, null, null, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$1faf1460_0, 31) + "}";
    }

    public Data(Map map) {
        this.values = new HashMap(map);
    }
}
