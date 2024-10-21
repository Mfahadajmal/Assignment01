package io.grpc;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StatusException extends Exception {
    private static final long serialVersionUID = -660954903976144640L;
    public final Status status;

    public StatusException(Status status) {
        super(Status.formatThrowableMessage(status), status.cause, true, true);
        this.status = status;
    }
}
