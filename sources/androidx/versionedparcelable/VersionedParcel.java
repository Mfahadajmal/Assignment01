package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.collection.SimpleArrayMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VersionedParcel {
    private int mCurrentField;
    private final int mEnd;
    private int mFieldId;
    private int mNextRead;
    private final int mOffset;
    private final Parcel mParcel;
    final SimpleArrayMap mParcelizerCache;
    private final SparseIntArray mPositionLookup;
    private final String mPrefix;
    final SimpleArrayMap mReadCache;
    final SimpleArrayMap mWriteCache;

    public VersionedParcel(SimpleArrayMap simpleArrayMap, SimpleArrayMap simpleArrayMap2, SimpleArrayMap simpleArrayMap3) {
        this.mReadCache = simpleArrayMap;
        this.mWriteCache = simpleArrayMap2;
        this.mParcelizerCache = simpleArrayMap3;
    }

    private final Class findParcelClass(Class cls) {
        Class cls2 = (Class) this.mParcelizerCache.get(cls.getName());
        if (cls2 == null) {
            Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
            this.mParcelizerCache.put(cls.getName(), cls3);
            return cls3;
        }
        return cls2;
    }

    public final void closeField() {
        int i = this.mCurrentField;
        if (i >= 0) {
            SparseIntArray sparseIntArray = this.mPositionLookup;
            Parcel parcel = this.mParcel;
            int i2 = sparseIntArray.get(i);
            int dataPosition = parcel.dataPosition();
            this.mParcel.setDataPosition(i2);
            this.mParcel.writeInt(dataPosition - i2);
            this.mParcel.setDataPosition(dataPosition);
        }
    }

    protected final VersionedParcel createSubParcel() {
        int dataPosition = this.mParcel.dataPosition();
        int i = this.mNextRead;
        if (i == this.mOffset) {
            i = this.mEnd;
        }
        int i2 = i;
        Parcel parcel = this.mParcel;
        String str = this.mPrefix;
        return new VersionedParcel(parcel, dataPosition, i2, String.valueOf(str).concat("  "), this.mReadCache, this.mWriteCache, this.mParcelizerCache);
    }

    public final boolean readBoolean(boolean z, int i) {
        return !readField(i) ? z : readBoolean();
    }

    public final byte[] readByteArray() {
        int readInt = this.mParcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.mParcel.readByteArray(bArr);
        return bArr;
    }

    public final CharSequence readCharSequence(CharSequence charSequence, int i) {
        return !readField(i) ? charSequence : readCharSequence();
    }

    public final boolean readField(int i) {
        while (this.mNextRead < this.mEnd) {
            int i2 = this.mFieldId;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.mParcel.setDataPosition(this.mNextRead);
            Parcel parcel = this.mParcel;
            int readInt = parcel.readInt();
            this.mFieldId = parcel.readInt();
            this.mNextRead += readInt;
        }
        if (this.mFieldId == i) {
            return true;
        }
        return false;
    }

    public final int readInt(int i, int i2) {
        return !readField(i2) ? i : readInt();
    }

    public final Parcelable readParcelable(Parcelable parcelable, int i) {
        return !readField(i) ? parcelable : readParcelable();
    }

    public final String readString(String str, int i) {
        return !readField(i) ? str : readString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final VersionedParcelable readVersionedParcelable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        VersionedParcel createSubParcel = createSubParcel();
        try {
            Method method = (Method) this.mReadCache.get(readString);
            if (method == null) {
                method = Class.forName(readString, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
                this.mReadCache.put(readString, method);
            }
            return (VersionedParcelable) method.invoke(null, createSubParcel);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw new RuntimeException(e4);
            }
            throw ((RuntimeException) cause);
        }
    }

    public final VersionedParcelable readVersionedParcelable$ar$ds(VersionedParcelable versionedParcelable) {
        if (!readField(1)) {
            return versionedParcelable;
        }
        return readVersionedParcelable();
    }

    public final void setOutputField(int i) {
        closeField();
        this.mCurrentField = i;
        this.mPositionLookup.put(i, this.mParcel.dataPosition());
        writeInt(0);
        writeInt(i);
    }

    public final void writeBoolean(boolean z, int i) {
        setOutputField(i);
        writeBoolean(z);
    }

    public final void writeByteArray(byte[] bArr) {
        this.mParcel.writeInt(bArr.length);
        this.mParcel.writeByteArray(bArr);
    }

    public final void writeCharSequence(CharSequence charSequence, int i) {
        setOutputField(i);
        writeCharSequence(charSequence);
    }

    public final void writeInt(int i, int i2) {
        setOutputField(i2);
        writeInt(i);
    }

    public final void writeParcelable(Parcelable parcelable, int i) {
        setOutputField(i);
        writeParcelable(parcelable);
    }

    public final void writeString(String str, int i) {
        setOutputField(i);
        writeString(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable != null) {
            try {
                writeString(findParcelClass(versionedParcelable.getClass()).getName());
                VersionedParcel createSubParcel = createSubParcel();
                try {
                    Class<?> cls = versionedParcelable.getClass();
                    Method method = (Method) this.mWriteCache.get(cls.getName());
                    if (method == null) {
                        method = findParcelClass(cls).getDeclaredMethod("write", cls, VersionedParcel.class);
                        this.mWriteCache.put(cls.getName(), method);
                    }
                    method.invoke(null, versionedParcelable, createSubParcel);
                    createSubParcel.closeField();
                    return;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException(e3);
                } catch (InvocationTargetException e4) {
                    Throwable cause = e4.getCause();
                    if (!(cause instanceof RuntimeException)) {
                        if (cause instanceof Error) {
                            throw ((Error) cause);
                        }
                        throw new RuntimeException(e4);
                    }
                    throw ((RuntimeException) cause);
                }
            } catch (ClassNotFoundException e5) {
                throw new RuntimeException(String.valueOf(versionedParcelable.getClass().getSimpleName()).concat(" does not have a Parcelizer"), e5);
            }
        }
        writeString(null);
    }

    public final void writeVersionedParcelable$ar$ds(VersionedParcelable versionedParcelable) {
        setOutputField(1);
        writeVersionedParcelable(versionedParcelable);
    }

    public VersionedParcel(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new SimpleArrayMap(), new SimpleArrayMap(), new SimpleArrayMap());
    }

    private VersionedParcel(Parcel parcel, int i, int i2, String str, SimpleArrayMap simpleArrayMap, SimpleArrayMap simpleArrayMap2, SimpleArrayMap simpleArrayMap3) {
        this(simpleArrayMap, simpleArrayMap2, simpleArrayMap3);
        this.mPositionLookup = new SparseIntArray();
        this.mCurrentField = -1;
        this.mFieldId = -1;
        this.mParcel = parcel;
        this.mOffset = i;
        this.mEnd = i2;
        this.mNextRead = i;
        this.mPrefix = str;
    }

    public final boolean readBoolean() {
        return this.mParcel.readInt() != 0;
    }

    protected final CharSequence readCharSequence() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.mParcel);
    }

    public final int readInt() {
        return this.mParcel.readInt();
    }

    public final Parcelable readParcelable() {
        return this.mParcel.readParcelable(getClass().getClassLoader());
    }

    public final String readString() {
        return this.mParcel.readString();
    }

    public final void writeBoolean(boolean z) {
        this.mParcel.writeInt(z ? 1 : 0);
    }

    protected final void writeCharSequence(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.mParcel, 0);
    }

    public final void writeInt(int i) {
        this.mParcel.writeInt(i);
    }

    public final void writeParcelable(Parcelable parcelable) {
        this.mParcel.writeParcelable(parcelable, 0);
    }

    public final void writeString(String str) {
        this.mParcel.writeString(str);
    }
}
