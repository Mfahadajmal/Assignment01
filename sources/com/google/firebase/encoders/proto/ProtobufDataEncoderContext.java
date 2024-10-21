package com.google.firebase.encoders.proto;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private static final ObjectEncoder DEFAULT_MAP_ENCODER;
    public static final FieldDescriptor MAP_KEY_DESC;
    public static final FieldDescriptor MAP_VALUE_DESC;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final ObjectEncoder fallbackEncoder;
    public final Map objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext = new ProtobufValueEncoderContext(this);
    private final Map valueEncoders;

    static {
        FieldDescriptor.Builder builder = new FieldDescriptor.Builder("key");
        AtProtobuf atProtobuf = new AtProtobuf();
        atProtobuf.tag = 1;
        builder.withProperty$ar$ds(atProtobuf.build());
        MAP_KEY_DESC = builder.build();
        FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("value");
        AtProtobuf atProtobuf2 = new AtProtobuf();
        atProtobuf2.tag = 2;
        builder2.withProperty$ar$ds(atProtobuf2.build());
        MAP_VALUE_DESC = builder2.build();
        DEFAULT_MAP_ENCODER = new ProtobufDataEncoderContext$$ExternalSyntheticLambda0(0);
    }

    public ProtobufDataEncoderContext(OutputStream outputStream, Map map, Map map2, ObjectEncoder objectEncoder) {
        this.output = outputStream;
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
    }

    private static ByteBuffer allocateBuffer(int i) {
        return ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void doEncode$ar$ds$2c872640_0(ObjectEncoder objectEncoder, FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        LengthCountingOutputStream lengthCountingOutputStream = new LengthCountingOutputStream();
        try {
            OutputStream outputStream = this.output;
            this.output = lengthCountingOutputStream;
            try {
                objectEncoder.encode(obj, this);
                this.output = outputStream;
                long j = lengthCountingOutputStream.length;
                lengthCountingOutputStream.close();
                if (z && j == 0) {
                    return;
                }
                writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
                writeVarInt64(j);
                objectEncoder.encode(obj, this);
            } catch (Throwable th) {
                this.output = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                lengthCountingOutputStream.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    private static Protobuf getProtobuf(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private static int getTag(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf.tag();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private final void writeVarInt32(int i) {
        while (true) {
            long j = i & (-128);
            int i2 = i & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE;
            if (j != 0) {
                this.output.write(i2 | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                i >>>= 7;
            } else {
                this.output.write(i2);
                return;
            }
        }
    }

    private final void writeVarInt64(long j) {
        while (true) {
            int i = (int) j;
            if (((-128) & j) != 0) {
                this.output.write((i & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE) | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                j >>>= 7;
            } else {
                this.output.write(i & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE);
                return;
            }
        }
    }

    public final ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, int i) {
        add$ar$ds$b85ccfce_0(fieldDescriptor, i, true);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final void add$ar$ds$6bd70da1_0(FieldDescriptor fieldDescriptor, Object obj) {
        add(fieldDescriptor, obj, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void add$ar$ds$b85ccfce_0(FieldDescriptor fieldDescriptor, int i, boolean z) {
        if (!z || i != 0) {
            Protobuf protobuf = getProtobuf(fieldDescriptor);
            int ordinal = protobuf.intEncoding().ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        return;
                    }
                    writeVarInt32((protobuf.tag() << 3) | 5);
                    this.output.write(allocateBuffer(4).putInt(i).array());
                    return;
                }
                writeVarInt32(protobuf.tag() << 3);
                writeVarInt32((i + i) ^ (i >> 31));
                return;
            }
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj, boolean z) {
        if (obj != null) {
            if (obj instanceof CharSequence) {
                CharSequence charSequence = (CharSequence) obj;
                if (!z || charSequence.length() != 0) {
                    writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
                    byte[] bytes = charSequence.toString().getBytes(UTF_8);
                    writeVarInt32(bytes.length);
                    this.output.write(bytes);
                    return this;
                }
            } else if (obj instanceof Collection) {
                Iterator it = ((Collection) obj).iterator();
                while (it.hasNext()) {
                    add(fieldDescriptor, it.next(), false);
                }
            } else if (obj instanceof Map) {
                Iterator it2 = ((Map) obj).entrySet().iterator();
                while (it2.hasNext()) {
                    doEncode$ar$ds$2c872640_0(DEFAULT_MAP_ENCODER, fieldDescriptor, (Map.Entry) it2.next(), false);
                }
            } else if (obj instanceof Double) {
                double doubleValue = ((Double) obj).doubleValue();
                if (!z || doubleValue != 0.0d) {
                    writeVarInt32((getTag(fieldDescriptor) << 3) | 1);
                    this.output.write(allocateBuffer(8).putDouble(doubleValue).array());
                }
            } else if (obj instanceof Float) {
                float floatValue = ((Float) obj).floatValue();
                if (!z || floatValue != 0.0f) {
                    writeVarInt32((getTag(fieldDescriptor) << 3) | 5);
                    this.output.write(allocateBuffer(4).putFloat(floatValue).array());
                }
            } else if (obj instanceof Number) {
                long longValue = ((Number) obj).longValue();
                if (!z || longValue != 0) {
                    Protobuf protobuf = getProtobuf(fieldDescriptor);
                    int ordinal = protobuf.intEncoding().ordinal();
                    if (ordinal == 0) {
                        writeVarInt32(protobuf.tag() << 3);
                        writeVarInt64(longValue);
                    } else if (ordinal == 1) {
                        writeVarInt32(protobuf.tag() << 3);
                        writeVarInt64((longValue + longValue) ^ (longValue >> 63));
                    } else if (ordinal == 2) {
                        writeVarInt32((protobuf.tag() << 3) | 1);
                        this.output.write(allocateBuffer(8).putLong(longValue).array());
                    }
                }
            } else {
                if (obj instanceof Boolean) {
                    add$ar$ds$b85ccfce_0(fieldDescriptor, ((Boolean) obj).booleanValue() ? 1 : 0, z);
                    return this;
                }
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    if (!z || bArr.length != 0) {
                        writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
                        writeVarInt32(bArr.length);
                        this.output.write(bArr);
                        return this;
                    }
                } else {
                    ObjectEncoder objectEncoder = (ObjectEncoder) this.objectEncoders.get(obj.getClass());
                    if (objectEncoder != null) {
                        doEncode$ar$ds$2c872640_0(objectEncoder, fieldDescriptor, obj, z);
                        return this;
                    }
                    ValueEncoder valueEncoder = (ValueEncoder) this.valueEncoders.get(obj.getClass());
                    if (valueEncoder != null) {
                        ProtobufValueEncoderContext protobufValueEncoderContext = this.valueEncoderContext;
                        protobufValueEncoderContext.encoded = false;
                        protobufValueEncoderContext.field = fieldDescriptor;
                        protobufValueEncoderContext.skipDefault = z;
                        valueEncoder.encode(obj, protobufValueEncoderContext);
                        return this;
                    }
                    if (obj instanceof ProtoEnum) {
                        return add(fieldDescriptor, ((ProtoEnum) obj).getNumber());
                    }
                    if (obj instanceof Enum) {
                        return add(fieldDescriptor, ((Enum) obj).ordinal());
                    }
                    doEncode$ar$ds$2c872640_0(this.fallbackEncoder, fieldDescriptor, obj, z);
                    return this;
                }
            }
        }
        return this;
    }
}
