package androidx.transition;

import android.util.Log;
import android.view.View;
import androidx.work.Data;
import androidx.work.Data_Kt;
import androidx.work.Logger;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class ViewUtilsApi22 extends ViewUtilsApi21 {
    private static boolean sTryHiddenSetLeftTopRightBottom = true;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        static void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
            view.setLeftTopRightBottom(i, i2, i3, i4);
        }

        public static final byte[] toByteArrayInternalV1$ar$ds(Data data) {
            int i;
            String str;
            Double d;
            double d2;
            Float f;
            float f2;
            Long l;
            long j;
            Integer num;
            int i2;
            Byte b;
            byte b2;
            Boolean bool;
            boolean z;
            data.getClass();
            int i3 = 0;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeShort(-21521);
                    int i4 = 1;
                    dataOutputStream.writeShort(1);
                    dataOutputStream.writeInt(data.values.size());
                    for (Map.Entry entry : data.values.entrySet()) {
                        String str2 = (String) entry.getKey();
                        Object value = entry.getValue();
                        if (value == null) {
                            dataOutputStream.writeByte(i3);
                        } else if (value instanceof Boolean) {
                            dataOutputStream.writeByte(i4);
                            dataOutputStream.writeBoolean(((Boolean) value).booleanValue());
                        } else if (value instanceof Byte) {
                            dataOutputStream.writeByte(2);
                            dataOutputStream.writeByte(((Number) value).byteValue());
                        } else if (value instanceof Integer) {
                            dataOutputStream.writeByte(3);
                            dataOutputStream.writeInt(((Number) value).intValue());
                        } else if (value instanceof Long) {
                            dataOutputStream.writeByte(4);
                            dataOutputStream.writeLong(((Number) value).longValue());
                        } else if (value instanceof Float) {
                            dataOutputStream.writeByte(5);
                            dataOutputStream.writeFloat(((Number) value).floatValue());
                        } else if (value instanceof Double) {
                            dataOutputStream.writeByte(6);
                            dataOutputStream.writeDouble(((Number) value).doubleValue());
                        } else if (value instanceof String) {
                            dataOutputStream.writeByte(7);
                            dataOutputStream.writeUTF((String) value);
                        } else if (value instanceof Object[]) {
                            Object[] objArr = (Object[]) value;
                            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(objArr.getClass());
                            int i5 = 11;
                            int i6 = 10;
                            int i7 = 8;
                            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean[].class))) {
                                i = 8;
                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Byte[].class))) {
                                i = 9;
                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer[].class))) {
                                i = 10;
                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long[].class))) {
                                i = 11;
                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float[].class))) {
                                i = 12;
                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double[].class))) {
                                i = 13;
                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String[].class))) {
                                i = 14;
                            } else {
                                throw new IllegalArgumentException("Unsupported value type ".concat(String.valueOf(Reflection.getOrCreateKotlinClass(objArr.getClass()).getQualifiedName())));
                            }
                            dataOutputStream.writeByte(i);
                            int length = objArr.length;
                            dataOutputStream.writeInt(length);
                            int i8 = i3;
                            while (i8 < length) {
                                Object obj = objArr[i8];
                                if (i == i7) {
                                    if (obj instanceof Boolean) {
                                        bool = (Boolean) obj;
                                    } else {
                                        bool = null;
                                    }
                                    if (bool != null) {
                                        z = bool.booleanValue();
                                    } else {
                                        z = false;
                                    }
                                    dataOutputStream.writeBoolean(z);
                                } else if (i == 9) {
                                    if (obj instanceof Byte) {
                                        b = (Byte) obj;
                                    } else {
                                        b = null;
                                    }
                                    if (b != null) {
                                        b2 = b.byteValue();
                                    } else {
                                        b2 = 0;
                                    }
                                    dataOutputStream.writeByte(b2);
                                } else if (i == i6) {
                                    if (obj instanceof Integer) {
                                        num = (Integer) obj;
                                    } else {
                                        num = null;
                                    }
                                    if (num != null) {
                                        i2 = num.intValue();
                                    } else {
                                        i2 = 0;
                                    }
                                    dataOutputStream.writeInt(i2);
                                } else if (i == i5) {
                                    if (obj instanceof Long) {
                                        l = (Long) obj;
                                    } else {
                                        l = null;
                                    }
                                    if (l != null) {
                                        j = l.longValue();
                                    } else {
                                        j = 0;
                                    }
                                    dataOutputStream.writeLong(j);
                                } else if (i == 12) {
                                    if (obj instanceof Float) {
                                        f = (Float) obj;
                                    } else {
                                        f = null;
                                    }
                                    if (f != null) {
                                        f2 = f.floatValue();
                                    } else {
                                        f2 = 0.0f;
                                    }
                                    dataOutputStream.writeFloat(f2);
                                } else if (i == 13) {
                                    if (obj instanceof Double) {
                                        d = (Double) obj;
                                    } else {
                                        d = null;
                                    }
                                    if (d != null) {
                                        d2 = d.doubleValue();
                                    } else {
                                        d2 = 0.0d;
                                    }
                                    dataOutputStream.writeDouble(d2);
                                } else {
                                    if (obj instanceof String) {
                                        str = (String) obj;
                                    } else {
                                        str = null;
                                    }
                                    if (str == null) {
                                        str = "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d";
                                    }
                                    dataOutputStream.writeUTF(str);
                                }
                                i8++;
                                i7 = 8;
                                i5 = 11;
                                i6 = 10;
                            }
                        } else {
                            throw new IllegalArgumentException("Unsupported value type ".concat(String.valueOf(Reflection.getOrCreateKotlinClass(value.getClass()).getSimpleName())));
                        }
                        dataOutputStream.writeUTF(str2);
                        i3 = 0;
                        i4 = 1;
                    }
                    dataOutputStream.flush();
                    if (dataOutputStream.size() <= 10240) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        OnDeviceShadowRemovalLogEvent.closeFinally(dataOutputStream, null);
                        byteArray.getClass();
                        return byteArray;
                    }
                    throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
                } finally {
                }
            } catch (IOException e) {
                String str3 = Data_Kt.TAG;
                Logger.get$ar$ds$16341a92_0();
                Log.e(str3, "Error in Data#toByteArray: ", e);
                return new byte[0];
            }
        }
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
        if (sTryHiddenSetLeftTopRightBottom) {
            try {
                Api29Impl.setLeftTopRightBottom(view, i, i2, i3, i4);
            } catch (NoSuchMethodError unused) {
                sTryHiddenSetLeftTopRightBottom = false;
            }
        }
    }
}
