package io.grpc;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StatusRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1950934672280720624L;
    public final Status status;

    public StatusRuntimeException(Status status) {
        this(status, null);
    }

    public StatusRuntimeException(Status status, byte[] bArr) {
        super(Status.formatThrowableMessage(status), status.cause, true, true);
        this.status = status;
    }
}
