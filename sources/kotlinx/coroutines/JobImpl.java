package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JobImpl extends JobSupport implements Job {
    private final boolean handlesException;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
    
        r1 = r1.getParentHandle$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
    
        if ((r1 instanceof kotlinx.coroutines.ChildHandleNode) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
    
        r1 = (kotlinx.coroutines.ChildHandleNode) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:
    
        if (r1 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0033, code lost:
    
        r1 = r1.getJob();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0030, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
    
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        if (r1.getHandlesException$kotlinx_coroutines_core() == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public JobImpl(kotlinx.coroutines.Job r5) {
        /*
            r4 = this;
            r5 = 1
            r4.<init>(r5)
            r0 = 0
            r4.initParentJob(r0)
            kotlinx.coroutines.ChildHandle r1 = r4.getParentHandle$kotlinx_coroutines_core()
            boolean r2 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r2 == 0) goto L13
            kotlinx.coroutines.ChildHandleNode r1 = (kotlinx.coroutines.ChildHandleNode) r1
            goto L14
        L13:
            r1 = r0
        L14:
            r2 = 0
            if (r1 == 0) goto L39
            kotlinx.coroutines.JobSupport r1 = r1.getJob()
            if (r1 != 0) goto L1e
            goto L39
        L1e:
            boolean r3 = r1.getHandlesException$kotlinx_coroutines_core()
            if (r3 == 0) goto L25
            goto L3a
        L25:
            kotlinx.coroutines.ChildHandle r1 = r1.getParentHandle$kotlinx_coroutines_core()
            boolean r3 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r3 == 0) goto L30
            kotlinx.coroutines.ChildHandleNode r1 = (kotlinx.coroutines.ChildHandleNode) r1
            goto L31
        L30:
            r1 = r0
        L31:
            if (r1 == 0) goto L39
            kotlinx.coroutines.JobSupport r1 = r1.getJob()
            if (r1 != 0) goto L1e
        L39:
            r5 = r2
        L3a:
            r4.handlesException = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobImpl.<init>(kotlinx.coroutines.Job):void");
    }

    @Override // kotlinx.coroutines.JobSupport
    public final boolean getHandlesException$kotlinx_coroutines_core() {
        return this.handlesException;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}
