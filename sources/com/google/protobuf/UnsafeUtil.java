package com.google.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import org.chromium.net.PrivateKeyType;
import sun.misc.Unsafe;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnsafeUtil {
    private static final long BUFFER_ADDRESS_OFFSET;
    static final long BYTE_ARRAY_BASE_OFFSET;
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS;
    public static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    private static final boolean IS_ANDROID_32;
    private static final boolean IS_ANDROID_64;
    static final boolean IS_BIG_ENDIAN;
    private static final MemoryAccessor MEMORY_ACCESSOR;
    private static final Class MEMORY_CLASS;
    private static final Unsafe UNSAFE;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Android32MemoryAccessor extends MemoryAccessor {
        public Android32MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void copyMemory(long j, byte[] bArr, long j2, long j3) {
            Memory.peekByteArray((int) j, bArr, (int) j2, (int) j3);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final boolean getBoolean(Object obj, long j) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                return UnsafeUtil.getBooleanBigEndian(obj, j);
            }
            return UnsafeUtil.getBooleanLittleEndian(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final byte getByte(long j) {
            return Memory.peekByte((int) j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final double getDouble(Object obj, long j) {
            return Double.longBitsToDouble(getLong(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final float getFloat(Object obj, long j) {
            return Float.intBitsToFloat(getInt(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putBoolean(Object obj, long j, boolean z) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, z ? (byte) 1 : (byte) 0);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, z ? (byte) 1 : (byte) 0);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putByte(Object obj, long j, byte b) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, b);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, b);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putDouble(Object obj, long j, double d) {
            putLong(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putFloat(Object obj, long j, float f) {
            putInt(obj, j, Float.floatToIntBits(f));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Android64MemoryAccessor extends MemoryAccessor {
        public Android64MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void copyMemory(long j, byte[] bArr, long j2, long j3) {
            Memory.peekByteArray(j, bArr, (int) j2, (int) j3);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final boolean getBoolean(Object obj, long j) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                return UnsafeUtil.getBooleanBigEndian(obj, j);
            }
            return UnsafeUtil.getBooleanLittleEndian(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final byte getByte(long j) {
            return Memory.peekByte(j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final double getDouble(Object obj, long j) {
            return Double.longBitsToDouble(getLong(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final float getFloat(Object obj, long j) {
            return Float.intBitsToFloat(getInt(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putBoolean(Object obj, long j, boolean z) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, z ? (byte) 1 : (byte) 0);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, z ? (byte) 1 : (byte) 0);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putByte(Object obj, long j, byte b) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, b);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, b);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putDouble(Object obj, long j, double d) {
            putLong(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public final void putFloat(Object obj, long j, float f) {
            putInt(obj, j, Float.floatToIntBits(f));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class MemoryAccessor {
        final Unsafe unsafe;

        public MemoryAccessor(Unsafe unsafe) {
            this.unsafe = unsafe;
        }

        public abstract void copyMemory(long j, byte[] bArr, long j2, long j3);

        public abstract boolean getBoolean(Object obj, long j);

        public abstract byte getByte(long j);

        public abstract double getDouble(Object obj, long j);

        public abstract float getFloat(Object obj, long j);

        public final int getInt(Object obj, long j) {
            return this.unsafe.getInt(obj, j);
        }

        public final long getLong(Object obj, long j) {
            return this.unsafe.getLong(obj, j);
        }

        public final long objectFieldOffset(Field field) {
            return this.unsafe.objectFieldOffset(field);
        }

        public abstract void putBoolean(Object obj, long j, boolean z);

        public abstract void putByte(Object obj, long j, byte b);

        public abstract void putDouble(Object obj, long j, double d);

        public abstract void putFloat(Object obj, long j, float f);

        public final void putInt(Object obj, long j, int i) {
            this.unsafe.putInt(obj, j, i);
        }

        public final void putLong(Object obj, long j, long j2) {
            this.unsafe.putLong(obj, j, j2);
        }

        public final boolean supportsUnsafeArrayOperations() {
            try {
                Class<?> cls = this.unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                cls.getMethod("getInt", Object.class, Long.TYPE);
                cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                Class<?> cls2 = Long.TYPE;
                cls.getMethod("putLong", Object.class, cls2, cls2);
                cls.getMethod("getObject", Object.class, Long.TYPE);
                cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }

        public final boolean supportsUnsafeByteBufferOperations() {
            try {
                Class<?> cls = this.unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                if (UnsafeUtil.bufferAddressField() == null) {
                    return false;
                }
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }
    }

    static {
        boolean supportsUnsafeByteBufferOperations;
        boolean supportsUnsafeArrayOperations;
        Unsafe unsafe = getUnsafe();
        UNSAFE = unsafe;
        MEMORY_CLASS = Memory.class;
        boolean determineAndroidSupportByAddressSize = determineAndroidSupportByAddressSize(Long.TYPE);
        IS_ANDROID_64 = determineAndroidSupportByAddressSize;
        boolean determineAndroidSupportByAddressSize2 = determineAndroidSupportByAddressSize(Integer.TYPE);
        IS_ANDROID_32 = determineAndroidSupportByAddressSize2;
        MemoryAccessor memoryAccessor = null;
        if (unsafe != null) {
            if (determineAndroidSupportByAddressSize) {
                memoryAccessor = new Android64MemoryAccessor(unsafe);
            } else if (determineAndroidSupportByAddressSize2) {
                memoryAccessor = new Android32MemoryAccessor(unsafe);
            }
        }
        MEMORY_ACCESSOR = memoryAccessor;
        boolean z = false;
        if (memoryAccessor == null) {
            supportsUnsafeByteBufferOperations = false;
        } else {
            supportsUnsafeByteBufferOperations = memoryAccessor.supportsUnsafeByteBufferOperations();
        }
        HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations;
        if (memoryAccessor == null) {
            supportsUnsafeArrayOperations = false;
        } else {
            supportsUnsafeArrayOperations = memoryAccessor.supportsUnsafeArrayOperations();
        }
        HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations;
        BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset(byte[].class);
        arrayBaseOffset(boolean[].class);
        arrayIndexScale$ar$ds(boolean[].class);
        arrayBaseOffset(int[].class);
        arrayIndexScale$ar$ds(int[].class);
        arrayBaseOffset(long[].class);
        arrayIndexScale$ar$ds(long[].class);
        arrayBaseOffset(float[].class);
        arrayIndexScale$ar$ds(float[].class);
        arrayBaseOffset(double[].class);
        arrayIndexScale$ar$ds(double[].class);
        arrayBaseOffset(Object[].class);
        arrayIndexScale$ar$ds(Object[].class);
        Field bufferAddressField = bufferAddressField();
        long j = -1;
        if (bufferAddressField != null && memoryAccessor != null) {
            j = memoryAccessor.objectFieldOffset(bufferAddressField);
        }
        BUFFER_ADDRESS_OFFSET = j;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            z = true;
        }
        IS_BIG_ENDIAN = z;
    }

    private UnsafeUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long addressOffset(ByteBuffer byteBuffer) {
        return MEMORY_ACCESSOR.getLong(byteBuffer, BUFFER_ADDRESS_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object allocateInstance(Class cls) {
        try {
            return UNSAFE.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int arrayBaseOffset(Class cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.unsafe.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static void arrayIndexScale$ar$ds(Class cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale(cls);
        }
    }

    public static Field bufferAddressField() {
        Field field = field(Buffer.class, "effectiveDirectAddress");
        if (field == null) {
            Field field2 = field(Buffer.class, "address");
            if (field2 != null && field2.getType() == Long.TYPE) {
                return field2;
            }
            return null;
        }
        return field;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyMemory(long j, byte[] bArr, long j2, long j3) {
        MEMORY_ACCESSOR.copyMemory(j, bArr, j2, j3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean determineAndroidSupportByAddressSize(Class cls) {
        try {
            Class cls2 = MEMORY_CLASS;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            Class cls3 = Integer.TYPE;
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls3, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field field(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getBoolean(Object obj, long j) {
        return MEMORY_ACCESSOR.getBoolean(obj, j);
    }

    public static boolean getBooleanBigEndian(Object obj, long j) {
        if (getByteBigEndian(obj, j) != 0) {
            return true;
        }
        return false;
    }

    public static boolean getBooleanLittleEndian(Object obj, long j) {
        if (getByteLittleEndian(obj, j) != 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte getByte(long j) {
        return MEMORY_ACCESSOR.getByte(j);
    }

    public static byte getByteBigEndian(Object obj, long j) {
        return (byte) ((getInt(obj, j & (-4)) >>> ((int) (((~j) & 3) << 3))) & PrivateKeyType.INVALID);
    }

    public static byte getByteLittleEndian(Object obj, long j) {
        return (byte) ((getInt(obj, j & (-4)) >>> ((int) ((3 & j) << 3))) & PrivateKeyType.INVALID);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double getDouble(Object obj, long j) {
        return MEMORY_ACCESSOR.getDouble(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getFloat(Object obj, long j) {
        return MEMORY_ACCESSOR.getFloat(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getInt(Object obj, long j) {
        return MEMORY_ACCESSOR.getInt(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLong(Object obj, long j) {
        return MEMORY_ACCESSOR.getLong(obj, j);
    }

    public static Object getObject(Object obj, long j) {
        return MEMORY_ACCESSOR.unsafe.getObject(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe getUnsafe() {
        try {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: com.google.protobuf.UnsafeUtil.1
                @Override // java.security.PrivilegedExceptionAction
                public final /* bridge */ /* synthetic */ Object run() {
                    for (Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void logMissingMethod(Throwable th) {
        Logger.getLogger(UnsafeUtil.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putBoolean(Object obj, long j, boolean z) {
        MEMORY_ACCESSOR.putBoolean(obj, j, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putByte(byte[] bArr, long j, byte b) {
        MEMORY_ACCESSOR.putByte(bArr, BYTE_ARRAY_BASE_OFFSET + j, b);
    }

    public static void putByteBigEndian(Object obj, long j, byte b) {
        int i = ((~((int) j)) & 3) << 3;
        int i2 = PrivateKeyType.INVALID << i;
        long j2 = j & (-4);
        int i3 = (b & 255) << i;
        putInt(obj, j2, i3 | ((~i2) & getInt(obj, j2)));
    }

    public static void putByteLittleEndian(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        int i2 = PrivateKeyType.INVALID << i;
        long j2 = j & (-4);
        int i3 = (b & 255) << i;
        putInt(obj, j2, i3 | ((~i2) & getInt(obj, j2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putDouble(Object obj, long j, double d) {
        MEMORY_ACCESSOR.putDouble(obj, j, d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putFloat(Object obj, long j, float f) {
        MEMORY_ACCESSOR.putFloat(obj, j, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putInt(Object obj, long j, int i) {
        MEMORY_ACCESSOR.putInt(obj, j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putLong(Object obj, long j, long j2) {
        MEMORY_ACCESSOR.putLong(obj, j, j2);
    }

    public static void putObject(Object obj, long j, Object obj2) {
        MEMORY_ACCESSOR.unsafe.putObject(obj, j, obj2);
    }
}
