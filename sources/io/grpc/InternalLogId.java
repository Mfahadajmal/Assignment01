package io.grpc;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalLogId {
    private static final AtomicLong idAlloc = new AtomicLong();
    private final String details;
    public final long id;
    private final String typeName;

    public InternalLogId(String str, String str2, long j) {
        str.getClass();
        ContextDataProvider.checkArgument(!str.isEmpty(), (Object) "empty type");
        this.typeName = str;
        this.details = str2;
        this.id = j;
    }

    public static InternalLogId allocate(Class cls, String str) {
        cls.getClass();
        String simpleName = cls.getSimpleName();
        if (simpleName.isEmpty()) {
            simpleName = cls.getName().substring(cls.getPackage().getName().length() + 1);
        }
        return allocate(simpleName, str);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.typeName + "<" + this.id + ">");
        if (this.details != null) {
            sb.append(": (");
            sb.append(this.details);
            sb.append(')');
        }
        return sb.toString();
    }

    public static InternalLogId allocate(String str, String str2) {
        return new InternalLogId(str, str2, idAlloc.incrementAndGet());
    }
}
