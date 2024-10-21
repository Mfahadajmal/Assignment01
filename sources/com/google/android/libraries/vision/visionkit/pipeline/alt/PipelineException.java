package com.google.android.libraries.vision.visionkit.pipeline.alt;

import com.google.android.libraries.vision.visionkit.pipeline.ComponentStatus;
import com.google.android.libraries.vision.visionkit.pipeline.Status;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Protobuf;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PipelineException extends Exception {
    private static final String ROOT_CAUSE_DELIMITER = "#vk ";
    private final StatusCode statusCode;
    private final String statusMessage;
    private final Status visionkitStatus;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum StatusCode {
        OK("ok"),
        CANCELLED("canceled"),
        UNKNOWN("unknown"),
        INVALID_ARGUMENT("invalid argument"),
        DEADLINE_EXCEEDED("deadline exceeded"),
        NOT_FOUND("not found"),
        ALREADY_EXISTS("already exists"),
        PERMISSION_DENIED("permission denied"),
        RESOURCE_EXHAUSTED("resource exhausted"),
        FAILED_PRECONDITION("failed precondition"),
        ABORTED("aborted"),
        OUT_OF_RANGE("out of range"),
        UNIMPLEMENTED("unimplemented"),
        INTERNAL("internal"),
        UNAVAILABLE("unavailable"),
        DATA_LOSS("data loss"),
        UNAUTHENTICATED("unauthenticated");

        public final String description;

        StatusCode(String str) {
            this.description = str;
        }
    }

    public PipelineException(int i, String str) {
        super(StatusCode.values()[i].description + ": " + str);
        this.statusCode = StatusCode.values()[i];
        this.statusMessage = str;
        this.visionkitStatus = null;
    }

    public List<ComponentStatus> getComponentStatuses() {
        Status status = this.visionkitStatus;
        if (status != null) {
            return status.componentStatus_;
        }
        int i = ImmutableList.ImmutableList$ar$NoOp;
        return RegularImmutableList.EMPTY;
    }

    public Optional<String> getRootCauseMessage() {
        if (this.statusMessage.contains(ROOT_CAUSE_DELIMITER)) {
            return Optional.of((String) ContextDataProvider.getLast(Splitter.on(ROOT_CAUSE_DELIMITER).splitToList(this.statusMessage)));
        }
        return Absent.INSTANCE;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    private PipelineException(Status status) {
        super(StatusCode.values()[status.code_].description + ": " + status.message_);
        this.statusCode = StatusCode.values()[status.code_];
        this.statusMessage = status.message_;
        this.visionkitStatus = status;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    PipelineException(byte[] bArr) {
        this((Status) GeneratedMessageLite.parseFrom(Status.DEFAULT_INSTANCE, bArr, ExtensionRegistryLite.EMPTY_REGISTRY_LITE));
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
    }
}
