package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DebugKt {
    public static final boolean ASSERTIONS_ENABLED = false;
    public static final AtomicLong COROUTINE_ID;
    public static final boolean DEBUG;
    public static final boolean RECOVER_STACK_TRACES;

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0036, code lost:
    
        if (r0.equals("on") != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0041, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x003f, code lost:
    
        if (r0.equals("") != false) goto L23;
     */
    static {
        /*
            java.lang.String r0 = "kotlinx.coroutines.debug"
            java.lang.String r0 = kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt.systemProp(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L51
            int r3 = r0.hashCode()
            if (r3 == 0) goto L39
            r4 = 3551(0xddf, float:4.976E-42)
            if (r3 == r4) goto L30
            r4 = 109935(0x1ad6f, float:1.54052E-40)
            if (r3 == r4) goto L27
            r4 = 3005871(0x2dddaf, float:4.212122E-39)
            if (r3 != r4) goto L43
            java.lang.String r3 = "auto"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L43
            goto L51
        L27:
            java.lang.String r3 = "off"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L43
            goto L51
        L30:
            java.lang.String r3 = "on"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L43
            goto L41
        L39:
            java.lang.String r3 = ""
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L43
        L41:
            r0 = r1
            goto L52
        L43:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "System property 'kotlinx.coroutines.debug' has unrecognized value '"
            java.lang.String r3 = "'"
            java.lang.String r0 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(r0, r2, r3)
            r1.<init>(r0)
            throw r1
        L51:
            r0 = r2
        L52:
            kotlinx.coroutines.DebugKt.DEBUG = r0
            if (r0 == 0) goto L5f
            java.lang.String r0 = "kotlinx.coroutines.stacktrace.recovery"
            boolean r0 = com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions.systemProp(r0, r1)
            if (r0 == 0) goto L5f
            goto L60
        L5f:
            r1 = r2
        L60:
            kotlinx.coroutines.DebugKt.RECOVER_STACK_TRACES = r1
            java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
            r1 = 0
            r0.<init>(r1)
            kotlinx.coroutines.DebugKt.COROUTINE_ID = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DebugKt.<clinit>():void");
    }
}
