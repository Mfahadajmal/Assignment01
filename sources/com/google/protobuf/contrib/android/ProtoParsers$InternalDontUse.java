package com.google.protobuf.contrib.android;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoParsers$InternalDontUse<T extends MessageLite> implements Parcelable {
    public static final Parcelable.Creator<ProtoParsers$InternalDontUse<?>> CREATOR = new ServiceDumpRequestCreator(12);
    public volatile byte[] bytes;
    public volatile MessageLite message;

    public ProtoParsers$InternalDontUse(byte[] bArr, MessageLite messageLite) {
        boolean z = true;
        if (bArr == null && messageLite == null) {
            z = false;
        }
        ContextDataProvider.checkArgument(z, (Object) "Must have a message or bytes");
        this.bytes = bArr;
        this.message = messageLite;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        if (this.bytes == null) {
            byte[] bArr = new byte[this.message.getSerializedSize()];
            try {
                this.message.writeTo(CodedOutputStream.newInstance(bArr));
                this.bytes = bArr;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
        parcel.writeInt(this.bytes.length);
        parcel.writeByteArray(this.bytes);
    }
}
