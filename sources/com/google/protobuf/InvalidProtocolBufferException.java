package com.google.protobuf;

import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class InvalidProtocolBufferException extends IOException {
    private static final long serialVersionUID = -1616151763072450476L;
    public boolean wasThrownFromInputStream;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InvalidWireTypeException extends InvalidProtocolBufferException {
        private static final long serialVersionUID = 3283890091615336259L;

        public InvalidWireTypeException() {
            super("Protocol message tag had invalid wire type.");
        }
    }

    public InvalidProtocolBufferException(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setThrownFromInputStream() {
        this.wasThrownFromInputStream = true;
    }

    public InvalidProtocolBufferException(String str) {
        super(str);
    }
}
