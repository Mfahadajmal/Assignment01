package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {
    private final boolean active = true;
    private final ObjectEncoder fallbackEncoder;
    private final boolean ignoreNullValues;
    public final JsonWriter jsonWriter;
    private final Map objectEncoders;
    private final Map valueEncoders;

    public JsonValueObjectEncoderContext(Writer writer, Map map, Map map2, ObjectEncoder objectEncoder, boolean z) {
        this.jsonWriter = new JsonWriter(writer);
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
        this.ignoreNullValues = z;
    }

    public final JsonValueObjectEncoderContext add(String str, Object obj) {
        if (this.ignoreNullValues) {
            if (obj == null) {
                return this;
            }
            this.jsonWriter.name(str);
            return add$ar$ds$fc39d10f_0(obj);
        }
        this.jsonWriter.name(str);
        if (obj == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        return add$ar$ds$fc39d10f_0(obj);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final /* bridge */ /* synthetic */ void add$ar$ds$18f2e6ce_0(boolean z) {
        this.jsonWriter.value(z);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    /* renamed from: add$ar$ds$466c4abe_0, reason: merged with bridge method [inline-methods] */
    public final void add$ar$ds$cff682e7_0(String str) {
        this.jsonWriter.value(str);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public final void add$ar$ds$6bd70da1_0(FieldDescriptor fieldDescriptor, Object obj) {
        add(fieldDescriptor.name, obj);
    }

    public final JsonValueObjectEncoderContext add$ar$ds$fc39d10f_0(Object obj) {
        if (obj == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        if (obj instanceof Number) {
            this.jsonWriter.value((Number) obj);
            return this;
        }
        int i = 0;
        if (obj.getClass().isArray()) {
            if (obj instanceof byte[]) {
                this.jsonWriter.value(Base64.encodeToString((byte[]) obj, 2));
                return this;
            }
            this.jsonWriter.beginArray();
            if (obj instanceof int[]) {
                int length = ((int[]) obj).length;
                while (i < length) {
                    this.jsonWriter.value(r7[i]);
                    i++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                int length2 = jArr.length;
                while (i < length2) {
                    this.jsonWriter.value(jArr[i]);
                    i++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                int length3 = dArr.length;
                while (i < length3) {
                    this.jsonWriter.value(dArr[i]);
                    i++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                int length4 = zArr.length;
                while (i < length4) {
                    this.jsonWriter.value(zArr[i]);
                    i++;
                }
            } else if (obj instanceof Number[]) {
                Number[] numberArr = (Number[]) obj;
                int length5 = numberArr.length;
                while (i < length5) {
                    add$ar$ds$fc39d10f_0(numberArr[i]);
                    i++;
                }
            } else {
                Object[] objArr = (Object[]) obj;
                int length6 = objArr.length;
                while (i < length6) {
                    add$ar$ds$fc39d10f_0(objArr[i]);
                    i++;
                }
            }
            this.jsonWriter.endArray();
            return this;
        }
        if (obj instanceof Collection) {
            this.jsonWriter.beginArray();
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                add$ar$ds$fc39d10f_0(it.next());
            }
            this.jsonWriter.endArray();
            return this;
        }
        if (obj instanceof Map) {
            this.jsonWriter.beginObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                try {
                    add((String) key, entry.getValue());
                } catch (ClassCastException e) {
                    throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e);
                }
            }
            this.jsonWriter.endObject();
            return this;
        }
        ObjectEncoder objectEncoder = (ObjectEncoder) this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            doEncode$ar$ds(objectEncoder, obj);
            return this;
        }
        ValueEncoder valueEncoder = (ValueEncoder) this.valueEncoders.get(obj.getClass());
        if (valueEncoder != null) {
            valueEncoder.encode(obj, this);
            return this;
        }
        if (obj instanceof Enum) {
            if (obj instanceof NumberedEnum) {
                this.jsonWriter.value(((NumberedEnum) obj).getNumber());
            } else {
                add$ar$ds$cff682e7_0(((Enum) obj).name());
            }
            return this;
        }
        doEncode$ar$ds(this.fallbackEncoder, obj);
        return this;
    }

    final void doEncode$ar$ds(ObjectEncoder objectEncoder, Object obj) {
        this.jsonWriter.beginObject();
        objectEncoder.encode(obj, this);
        this.jsonWriter.endObject();
    }
}
