package io.perfmark;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PerfMark {
    public static final /* synthetic */ int PerfMark$ar$NoOp = 0;
    private static final Impl impl;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    static {
        /*
            r0 = 0
            java.lang.String r1 = "io.perfmark.impl.SecretPerfMarkImpl$PerfMarkImpl"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L9
            r2 = r0
            goto Lc
        L9:
            r1 = move-exception
            r2 = r1
            r1 = r0
        Lc:
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L2f
            java.lang.Class<io.perfmark.Impl> r5 = io.perfmark.Impl.class
            java.lang.Class r1 = r1.asSubclass(r5)     // Catch: java.lang.Throwable -> L2d
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L2d
            java.lang.Class<io.perfmark.Tag> r6 = io.perfmark.Tag.class
            r5[r3] = r6     // Catch: java.lang.Throwable -> L2d
            java.lang.reflect.Constructor r1 = r1.getConstructor(r5)     // Catch: java.lang.Throwable -> L2d
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L2d
            io.perfmark.Tag r6 = io.perfmark.Impl.NO_TAG     // Catch: java.lang.Throwable -> L2d
            r5[r3] = r6     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r1 = r1.newInstance(r5)     // Catch: java.lang.Throwable -> L2d
            io.perfmark.Impl r1 = (io.perfmark.Impl) r1     // Catch: java.lang.Throwable -> L2d
            goto L30
        L2d:
            r1 = move-exception
            r2 = r1
        L2f:
            r1 = r0
        L30:
            if (r1 == 0) goto L35
            io.perfmark.PerfMark.impl = r1
            goto L3e
        L35:
            io.perfmark.Impl r1 = new io.perfmark.Impl
            io.perfmark.Tag r5 = io.perfmark.Impl.NO_TAG
            r1.<init>(r5)
            io.perfmark.PerfMark.impl = r1
        L3e:
            if (r2 == 0) goto L99
            java.lang.String r1 = "io.perfmark.PerfMark.debug"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch: java.lang.Throwable -> L99
            if (r1 == 0) goto L99
            java.lang.String r1 = "java.util.logging.Logger"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L99
            java.lang.String r5 = "getLogger"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L99
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r3] = r7     // Catch: java.lang.Throwable -> L99
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch: java.lang.Throwable -> L99
            java.lang.Class<io.perfmark.PerfMark> r6 = io.perfmark.PerfMark.class
            java.lang.String r6 = r6.getName()     // Catch: java.lang.Throwable -> L99
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L99
            r7[r3] = r6     // Catch: java.lang.Throwable -> L99
            java.lang.Object r5 = r5.invoke(r0, r7)     // Catch: java.lang.Throwable -> L99
            java.lang.String r6 = "java.util.logging.Level"
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch: java.lang.Throwable -> L99
            java.lang.String r7 = "FINE"
            java.lang.reflect.Field r7 = r6.getField(r7)     // Catch: java.lang.Throwable -> L99
            java.lang.Object r0 = r7.get(r0)     // Catch: java.lang.Throwable -> L99
            java.lang.String r7 = "log"
            r8 = 3
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch: java.lang.Throwable -> L99
            r9[r3] = r6     // Catch: java.lang.Throwable -> L99
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r9[r4] = r6     // Catch: java.lang.Throwable -> L99
            java.lang.Class<java.lang.Throwable> r6 = java.lang.Throwable.class
            r10 = 2
            r9[r10] = r6     // Catch: java.lang.Throwable -> L99
            java.lang.reflect.Method r1 = r1.getMethod(r7, r9)     // Catch: java.lang.Throwable -> L99
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L99
            r6[r3] = r0     // Catch: java.lang.Throwable -> L99
            java.lang.String r0 = "Error during PerfMark.<clinit>"
            r6[r4] = r0     // Catch: java.lang.Throwable -> L99
            r6[r10] = r2     // Catch: java.lang.Throwable -> L99
            r1.invoke(r5, r6)     // Catch: java.lang.Throwable -> L99
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.perfmark.PerfMark.<clinit>():void");
    }

    private PerfMark() {
    }
}
