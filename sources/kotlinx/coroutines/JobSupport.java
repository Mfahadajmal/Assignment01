package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceObjectLoadLogEvent;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import io.perfmark.Tag;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.InternalCompletionHandler;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Removed;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JobSupport implements Job {
    private final AtomicRef _parentHandle;
    private final AtomicRef _state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ChildCompletion extends JobNode {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        public ChildCompletion(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
            this.parent = jobSupport;
            this.state = finishing;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        @Override // kotlinx.coroutines.InternalCompletionHandler
        public final void invoke(Throwable th) {
            boolean z = DebugKt.ASSERTIONS_ENABLED;
            JobSupport jobSupport = this.parent;
            Finishing finishing = this.state;
            ChildHandleNode nextChild = jobSupport.nextChild(this.child);
            Object obj = this.proposedUpdate;
            if (nextChild != null && jobSupport.tryWaitForChild(finishing, nextChild, obj)) {
                return;
            }
            jobSupport.afterCompletion(jobSupport.finalizeFinishingState(finishing, obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Finishing implements Incomplete {
        private final AtomicRef _rootCause;
        public final NodeList list;
        public final AtomicBoolean _isCompleting = OnDeviceSubjectSegmentationCreateLogEvent.atomic(false);
        private final AtomicRef _exceptionsHolder = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);

        public Finishing(NodeList nodeList, Throwable th) {
            this.list = nodeList;
            this._rootCause = OnDeviceSubjectSegmentationCreateLogEvent.atomic(th);
        }

        public final void addExceptionLocked(Throwable th) {
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                this._rootCause.setValue(th);
                return;
            }
            if (th != rootCause) {
                Object exceptionsHolder = getExceptionsHolder();
                if (exceptionsHolder == null) {
                    setExceptionsHolder(th);
                    return;
                }
                if (exceptionsHolder instanceof Throwable) {
                    if (th != exceptionsHolder) {
                        ArrayList arrayList = new ArrayList(4);
                        arrayList.add(exceptionsHolder);
                        arrayList.add(th);
                        setExceptionsHolder(arrayList);
                        return;
                    }
                    return;
                }
                if (exceptionsHolder instanceof ArrayList) {
                    ((ArrayList) exceptionsHolder).add(th);
                } else {
                    Objects.toString(exceptionsHolder);
                    throw new IllegalStateException("State is ".concat(exceptionsHolder.toString()));
                }
            }
        }

        public final Object getExceptionsHolder() {
            return this._exceptionsHolder.value;
        }

        @Override // kotlinx.coroutines.Incomplete
        public final NodeList getList() {
            return this.list;
        }

        public final Throwable getRootCause() {
            return (Throwable) this._rootCause.value;
        }

        @Override // kotlinx.coroutines.Incomplete
        public final boolean isActive() {
            if (getRootCause() == null) {
                return true;
            }
            return false;
        }

        public final boolean isCancelling() {
            if (getRootCause() != null) {
                return true;
            }
            return false;
        }

        public final boolean isCompleting() {
            return this._isCompleting.getValue();
        }

        public final boolean isSealed() {
            if (getExceptionsHolder() == JobSupportKt.SEALED) {
                return true;
            }
            return false;
        }

        public final void setExceptionsHolder(Object obj) {
            this._exceptionsHolder.setValue(obj);
        }

        public final String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + isCompleting() + ", rootCause=" + getRootCause() + ", exceptions=" + getExceptionsHolder() + ", list=" + this.list + "]";
        }
    }

    public JobSupport(boolean z) {
        Empty empty;
        if (z) {
            empty = JobSupportKt.EMPTY_ACTIVE;
        } else {
            empty = JobSupportKt.EMPTY_NEW;
        }
        this._state = OnDeviceSubjectSegmentationCreateLogEvent.atomic(empty);
        this._parentHandle = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
    }

    private final boolean addLastAtomic(Object obj, NodeList nodeList, JobNode jobNode) {
        char c;
        OpDescriptor opDescriptor = new OpDescriptor(jobNode, this, obj);
        do {
            LockFreeLinkedListNode prevNode = nodeList.getPrevNode();
            jobNode._prev.lazySet(prevNode);
            jobNode._next.lazySet(nodeList);
            opDescriptor.oldNext = nodeList;
            if (!prevNode._next.compareAndSet(nodeList, opDescriptor)) {
                c = 0;
            } else if (opDescriptor.perform(prevNode) == null) {
                c = 1;
            } else {
                c = 2;
            }
            if (c == 1) {
                return true;
            }
        } while (c != 2);
        return false;
    }

    private final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null && parentHandle$kotlinx_coroutines_core != NonDisposableHandle.INSTANCE) {
            if (parentHandle$kotlinx_coroutines_core.childCancelled(th) || z) {
                return true;
            }
            return false;
        }
        return z;
    }

    private final void completeStateFinalization(Incomplete incomplete, Object obj) {
        CompletedExceptionally completedExceptionally;
        Throwable th;
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null) {
            parentHandle$kotlinx_coroutines_core.dispose();
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
        CompletionHandlerException completionHandlerException = null;
        if (obj instanceof CompletedExceptionally) {
            completedExceptionally = (CompletedExceptionally) obj;
        } else {
            completedExceptionally = null;
        }
        if (completedExceptionally != null) {
            th = completedExceptionally.cause;
        } else {
            th = null;
        }
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
                return;
            } catch (Throwable th2) {
                handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, incomplete, "Exception in completion handler ", " for "), th2));
                return;
            }
        }
        NodeList list = incomplete.getList();
        if (list != null) {
            Object next = list.getNext();
            next.getClass();
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, list); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof JobNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (completionHandlerException != null) {
                            Tag.addSuppressed(completionHandlerException, th3);
                        } else {
                            completionHandlerException = new CompletionHandlerException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, jobNode, "Exception in completion handler ", " for "), th3);
                        }
                    }
                }
            }
            if (completionHandlerException != null) {
                handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException);
            }
        }
    }

    private final Throwable createCauseException(Object obj) {
        if (obj != null && !(obj instanceof Throwable)) {
            return ((JobSupport) obj).getChildJobCancellationCause();
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        return new JobCancellationException(cancellationExceptionMessage(), null, this);
    }

    private final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list == null) {
            if (incomplete instanceof Empty) {
                return new NodeList();
            }
            if (incomplete instanceof JobNode) {
                promoteSingleToNodeList((JobNode) incomplete);
                return null;
            }
            Objects.toString(incomplete);
            throw new IllegalStateException("State should have list: ".concat(String.valueOf(incomplete)));
        }
        return list;
    }

    private final void notifyCancelling(NodeList nodeList, Throwable th) {
        Object next = nodeList.getNext();
        next.getClass();
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        Tag.addSuppressed(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, jobNode, "Exception in completion handler ", " for "), th2);
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException);
        }
        cancelParent(th);
    }

    private final void promoteSingleToNodeList(JobNode jobNode) {
        NodeList nodeList = new NodeList();
        nodeList._prev.lazySet(jobNode);
        nodeList._next.lazySet(jobNode);
        while (true) {
            if (jobNode.getNext() != jobNode) {
                break;
            } else if (jobNode._next.compareAndSet(jobNode, nodeList)) {
                nodeList.finishAdd(jobNode);
                break;
            }
        }
        this._state.compareAndSet(jobNode, jobNode.getNextNode());
    }

    private final int startInternal(Object obj) {
        if (obj instanceof Empty) {
            if (((Empty) obj).isActive) {
                return 0;
            }
            if (!this._state.compareAndSet(obj, JobSupportKt.EMPTY_ACTIVE)) {
                return -1;
            }
            onStart();
            return 1;
        }
        if (!(obj instanceof InactiveNodeList)) {
            return 0;
        }
        if (!this._state.compareAndSet(obj, ((InactiveNodeList) obj).list)) {
            return -1;
        }
        onStart();
        return 1;
    }

    private static final String stateString$ar$ds(Object obj) {
        if (obj instanceof Finishing) {
            Finishing finishing = (Finishing) obj;
            if (finishing.isCancelling()) {
                return "Cancelling";
            }
            if (!finishing.isCompleting()) {
                return "Active";
            }
            return "Completing";
        }
        if (obj instanceof Incomplete) {
            if (((Incomplete) obj).isActive()) {
                return "Active";
            }
            return "New";
        }
        if (obj instanceof CompletedExceptionally) {
            return "Cancelled";
        }
        return "Completed";
    }

    private final Object tryMakeCompleting(Object obj, Object obj2) {
        Finishing finishing;
        CompletedExceptionally completedExceptionally;
        ChildHandleNode childHandleNode;
        if (!(obj instanceof Incomplete)) {
            return JobSupportKt.COMPLETING_ALREADY;
        }
        if (((obj instanceof Empty) || (obj instanceof JobNode)) && !(obj instanceof ChildHandleNode) && !(obj2 instanceof CompletedExceptionally)) {
            Incomplete incomplete = (Incomplete) obj;
            boolean z = DebugKt.ASSERTIONS_ENABLED;
            if (!this._state.compareAndSet(incomplete, JobSupportKt.boxIncomplete(obj2))) {
                return JobSupportKt.COMPLETING_RETRY;
            }
            onCompletionInternal(obj2);
            completeStateFinalization(incomplete, obj2);
            return obj2;
        }
        Incomplete incomplete2 = (Incomplete) obj;
        NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete2);
        if (orPromoteCancellingList == null) {
            return JobSupportKt.COMPLETING_RETRY;
        }
        ChildHandleNode childHandleNode2 = null;
        if (incomplete2 instanceof Finishing) {
            finishing = (Finishing) incomplete2;
        } else {
            finishing = null;
        }
        if (finishing == null) {
            finishing = new Finishing(orPromoteCancellingList, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (finishing) {
            if (finishing.isCompleting()) {
                return JobSupportKt.COMPLETING_ALREADY;
            }
            finishing._isCompleting.setValue$ar$ds();
            if (finishing != incomplete2 && !this._state.compareAndSet(incomplete2, finishing)) {
                return JobSupportKt.COMPLETING_RETRY;
            }
            boolean z2 = DebugKt.ASSERTIONS_ENABLED;
            boolean isCancelling = finishing.isCancelling();
            if (obj2 instanceof CompletedExceptionally) {
                completedExceptionally = (CompletedExceptionally) obj2;
            } else {
                completedExceptionally = null;
            }
            if (completedExceptionally != null) {
                finishing.addExceptionLocked(completedExceptionally.cause);
            }
            Throwable rootCause = finishing.getRootCause();
            boolean z3 = !isCancelling;
            Boolean.valueOf(z3).getClass();
            if (true != z3) {
                rootCause = null;
            }
            ref$ObjectRef.element = rootCause;
            Throwable th = (Throwable) ref$ObjectRef.element;
            if (th != null) {
                notifyCancelling(orPromoteCancellingList, th);
            }
            if (incomplete2 instanceof ChildHandleNode) {
                childHandleNode = (ChildHandleNode) incomplete2;
            } else {
                childHandleNode = null;
            }
            if (childHandleNode == null) {
                NodeList list = incomplete2.getList();
                if (list != null) {
                    childHandleNode2 = nextChild(list);
                }
            } else {
                childHandleNode2 = childHandleNode;
            }
            if (childHandleNode2 != null && tryWaitForChild(finishing, childHandleNode2, obj2)) {
                return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }
            return finalizeFinishingState(finishing, obj2);
        }
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild$ar$class_merging(JobSupport jobSupport) {
        return (ChildHandle) ScannerAutoZoomEvent.PredictedArea.invokeOnCompletion$default$ar$ds(this, true, new ChildHandleNode(jobSupport), 2);
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0031, code lost:
    
        r0 = kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r1 == kotlinx.coroutines.JobSupportKt.COMPLETING_WAITING_CHILDREN) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001f, code lost:
    
        r0 = tryMakeCompleting(r0, new kotlinx.coroutines.CompletedExceptionally(createCauseException(r8)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
    
        if (r0 == kotlinx.coroutines.JobSupportKt.COMPLETING_RETRY) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003c, code lost:
    
        if (r1 != kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003e, code lost:
    
        r0 = null;
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        r3 = getState$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0046, code lost:
    
        if ((r3 instanceof kotlinx.coroutines.JobSupport.Finishing) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
    
        if ((r3 instanceof kotlinx.coroutines.Incomplete) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0087, code lost:
    
        if (r1 != null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0089, code lost:
    
        r1 = createCauseException(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x008d, code lost:
    
        r4 = (kotlinx.coroutines.Incomplete) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0007, code lost:
    
        if (r0 != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0094, code lost:
    
        if (r4.isActive() == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b1, code lost:
    
        r4 = tryMakeCompleting(r3, new kotlinx.coroutines.CompletedExceptionally(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00bc, code lost:
    
        if (r4 == kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c0, code lost:
    
        if (r4 == kotlinx.coroutines.JobSupportKt.COMPLETING_RETRY) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c2, code lost:
    
        r1 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0009, code lost:
    
        r0 = getState$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c4, code lost:
    
        java.util.Objects.toString(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d6, code lost:
    
        throw new java.lang.IllegalStateException("Cannot happen in ".concat(java.lang.String.valueOf(r3)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0096, code lost:
    
        r3 = kotlinx.coroutines.DebugKt.ASSERTIONS_ENABLED;
        r3 = getOrPromoteCancellingList(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
    
        if (r3 == null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a9, code lost:
    
        if (r7._state.compareAndSet(r4, new kotlinx.coroutines.JobSupport.Finishing(r3, r1)) == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
    
        if ((r0 instanceof kotlinx.coroutines.Incomplete) == false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ab, code lost:
    
        notifyCancelling(r3, r1);
        r1 = kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00d7, code lost:
    
        r1 = kotlinx.coroutines.JobSupportKt.TOO_LATE_TO_CANCEL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0048, code lost:
    
        monitor-enter(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0049, code lost:
    
        r4 = (kotlinx.coroutines.JobSupport.Finishing) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0050, code lost:
    
        if (r4.isSealed() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0052, code lost:
    
        r1 = kotlinx.coroutines.JobSupportKt.TOO_LATE_TO_CANCEL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0054, code lost:
    
        monitor-exit(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0057, code lost:
    
        r5 = r4.isCancelling();
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x005b, code lost:
    
        if (r8 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x005d, code lost:
    
        if (r5 != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x005f, code lost:
    
        if (r1 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0064, code lost:
    
        r1 = createCauseException(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0068, code lost:
    
        r4.addExceptionLocked(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x006b, code lost:
    
        r8 = r4.getRootCause();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
    
        if ((r0 instanceof kotlinx.coroutines.JobSupport.Finishing) == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x006f, code lost:
    
        if (true != r5) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0072, code lost:
    
        r0 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0073, code lost:
    
        monitor-exit(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0074, code lost:
    
        if (r0 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0076, code lost:
    
        notifyCancelling(((kotlinx.coroutines.JobSupport.Finishing) r3).list, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x007d, code lost:
    
        r1 = kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0062, code lost:
    
        if (r1 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00db, code lost:
    
        if (r1 != kotlinx.coroutines.JobSupportKt.COMPLETING_ALREADY) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00e0, code lost:
    
        if (r1 != kotlinx.coroutines.JobSupportKt.COMPLETING_WAITING_CHILDREN) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00e5, code lost:
    
        if (r1 != kotlinx.coroutines.JobSupportKt.TOO_LATE_TO_CANCEL) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00e7, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00e9, code lost:
    
        afterCompletion(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00ec, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:?, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:?, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
    
        if (((kotlinx.coroutines.JobSupport.Finishing) r0).isCompleting() == false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean cancelImpl$kotlinx_coroutines_core(java.lang.Object r8) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.cancelImpl$kotlinx_coroutines_core(java.lang.Object):boolean");
    }

    public void cancelInternal(Throwable th) {
        cancelImpl$kotlinx_coroutines_core(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        if (cancelImpl$kotlinx_coroutines_core(th) && getHandlesException$kotlinx_coroutines_core()) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d7 A[Catch: all -> 0x013b, TryCatch #0 {, blocks: (B:8:0x0013, B:10:0x001d, B:11:0x0037, B:13:0x003e, B:15:0x0043, B:17:0x0049, B:18:0x004c, B:20:0x0058, B:22:0x005e, B:25:0x00af, B:28:0x00b6, B:31:0x00cd, B:32:0x00d1, B:34:0x00d7, B:36:0x00e1, B:41:0x00e9, B:44:0x00ed, B:47:0x00f3, B:55:0x00c9, B:69:0x0069, B:70:0x006d, B:72:0x0073, B:76:0x0081, B:78:0x0085, B:80:0x008f, B:81:0x0093, B:83:0x0099, B:86:0x00a2, B:90:0x00a7, B:101:0x0023, B:103:0x0027, B:104:0x0031, B:106:0x0035, B:107:0x0128, B:108:0x013a), top: B:7:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00c9 A[Catch: all -> 0x013b, TryCatch #0 {, blocks: (B:8:0x0013, B:10:0x001d, B:11:0x0037, B:13:0x003e, B:15:0x0043, B:17:0x0049, B:18:0x004c, B:20:0x0058, B:22:0x005e, B:25:0x00af, B:28:0x00b6, B:31:0x00cd, B:32:0x00d1, B:34:0x00d7, B:36:0x00e1, B:41:0x00e9, B:44:0x00ed, B:47:0x00f3, B:55:0x00c9, B:69:0x0069, B:70:0x006d, B:72:0x0073, B:76:0x0081, B:78:0x0085, B:80:0x008f, B:81:0x0093, B:83:0x0099, B:86:0x00a2, B:90:0x00a7, B:101:0x0023, B:103:0x0027, B:104:0x0031, B:106:0x0035, B:107:0x0128, B:108:0x013a), top: B:7:0x0013 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object finalizeFinishingState(kotlinx.coroutines.JobSupport.Finishing r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.finalizeFinishingState(kotlinx.coroutines.JobSupport$Finishing, java.lang.Object):java.lang.Object");
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return OnDeviceObjectLoadLogEvent.fold(this, obj, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        return OnDeviceObjectLoadLogEvent.get(this, key);
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
            if (rootCause != null) {
                return toCancellationException(rootCause, String.valueOf(DebugStringsKt.getClassSimpleName(this)).concat(" is cancelling"));
            }
            toString();
            throw new IllegalStateException("Job is still new or active: ".concat(toString()));
        }
        if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                return toCancellationException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause, null);
            }
            return new JobCancellationException(String.valueOf(DebugStringsKt.getClassSimpleName(this)).concat(" has completed normally"), null, this);
        }
        toString();
        throw new IllegalStateException("Job is still new or active: ".concat(toString()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Throwable] */
    public final CancellationException getChildJobCancellationCause() {
        CancellationException cancellationException;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        CancellationException cancellationException2 = null;
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            cancellationException = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            cancellationException = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            cancellationException = null;
        } else {
            Objects.toString(state$kotlinx_coroutines_core);
            throw new IllegalStateException("Cannot be cancelling child in this state: ".concat(String.valueOf(state$kotlinx_coroutines_core)));
        }
        if (cancellationException instanceof CancellationException) {
            cancellationException2 = cancellationException;
        }
        if (cancellationException2 == null) {
            return new JobCancellationException("Parent job is ".concat(stateString$ar$ds(state$kotlinx_coroutines_core)), cancellationException, this);
        }
        return cancellationException2;
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return Job.Key$ar$class_merging$e5be0816_0;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public final Job getParent() {
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        if (parentHandle$kotlinx_coroutines_core != null) {
            return parentHandle$kotlinx_coroutines_core.getParent();
        }
        return null;
    }

    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle) this._parentHandle.value;
    }

    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = this._state.value;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    protected boolean handleJobException(Throwable th) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void initParentJob(Job job) {
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        if (job == null) {
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
            return;
        }
        job.start$ar$ds$f3a3431a_0();
        ChildHandle attachChild$ar$class_merging = job.attachChild$ar$class_merging(this);
        setParentHandle$kotlinx_coroutines_core(attachChild$ar$class_merging);
        if (isCompleted()) {
            attachChild$ar$class_merging.dispose();
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1 function1) {
        return invokeOnCompletionInternal$kotlinx_coroutines_core(z, z2, new InternalCompletionHandler.UserSupplied(function1));
    }

    @Override // kotlinx.coroutines.Job
    public final void invokeOnCompletion$ar$ds(Function1 function1) {
        invokeOnCompletionInternal$kotlinx_coroutines_core(false, true, new InternalCompletionHandler.UserSupplied(function1));
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ac, code lost:
    
        return r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [kotlinx.coroutines.InactiveNodeList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.DisposableHandle invokeOnCompletionInternal$kotlinx_coroutines_core(boolean r9, boolean r10, kotlinx.coroutines.InternalCompletionHandler r11) {
        /*
            Method dump skipped, instructions count: 193
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.invokeOnCompletionInternal$kotlinx_coroutines_core(boolean, boolean, kotlinx.coroutines.InternalCompletionHandler):kotlinx.coroutines.DisposableHandle");
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if ((state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).isActive()) {
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            return true;
        }
        if ((state$kotlinx_coroutines_core instanceof Finishing) && ((Finishing) state$kotlinx_coroutines_core).isCancelling()) {
            return true;
        }
        return false;
    }

    public final boolean isCompleted() {
        if (!(getState$kotlinx_coroutines_core() instanceof Incomplete)) {
            return true;
        }
        return false;
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public final Object join(Continuation continuation) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                ScannerAutoZoomEvent.PredictedArea.ensureActive(continuation.getContext());
                return Unit.INSTANCE;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        OnDeviceTranslationLogEvent.disposeOnCancellation$ar$class_merging(cancellableContinuationImpl, ScannerAutoZoomEvent.PredictedArea.invokeOnCompletion$default$ar$ds(this, false, new ResumeOnCompletion(cancellableContinuationImpl), 3));
        Object result = cancellableContinuationImpl.getResult();
        if (result != CoroutineSingletons.COROUTINE_SUSPENDED) {
            result = Unit.INSTANCE;
        }
        if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return result;
        }
        return Unit.INSTANCE;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object obj) {
        Object tryMakeCompleting;
        CompletedExceptionally completedExceptionally;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                String _BOUNDARY$ar$MethodOutlining$dc56d17a_2 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(obj, this, "Job ", " is already complete or completing, but is being completed with ");
                Throwable th = null;
                if (obj instanceof CompletedExceptionally) {
                    completedExceptionally = (CompletedExceptionally) obj;
                } else {
                    completedExceptionally = null;
                }
                if (completedExceptionally != null) {
                    th = completedExceptionally.cause;
                }
                throw new IllegalStateException(_BOUNDARY$ar$MethodOutlining$dc56d17a_2, th);
            }
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        return tryMakeCompleting;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        return OnDeviceObjectLoadLogEvent.minusKey(this, key);
    }

    public String nameString$kotlinx_coroutines_core() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    public final ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    public final void parentCancelled$ar$class_merging(JobSupport jobSupport) {
        cancelImpl$kotlinx_coroutines_core(jobSupport);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return OnDeviceObjectLoadLogEvent.plus((CoroutineContext.Element) this, coroutineContext);
    }

    public final void removeNode$kotlinx_coroutines_core(JobNode jobNode) {
        Object state$kotlinx_coroutines_core;
        Object next;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof JobNode) {
                if (state$kotlinx_coroutines_core != jobNode) {
                    return;
                }
            } else {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete) || ((Incomplete) state$kotlinx_coroutines_core).getList() == null) {
                    return;
                }
                do {
                    next = jobNode.getNext();
                    if (next instanceof Removed) {
                        LockFreeLinkedListNode lockFreeLinkedListNode2 = ((Removed) next).ref;
                        return;
                    }
                    if (next == jobNode) {
                        return;
                    }
                    next.getClass();
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                    removed = (Removed) lockFreeLinkedListNode._removedRef.value;
                    if (removed == null) {
                        removed = new Removed(lockFreeLinkedListNode);
                        lockFreeLinkedListNode._removedRef.lazySet(removed);
                    }
                } while (!jobNode._next.compareAndSet(next, removed));
                lockFreeLinkedListNode.correctPrev$ar$ds();
                return;
            }
        } while (!this._state.compareAndSet(state$kotlinx_coroutines_core, JobSupportKt.EMPTY_ACTIVE));
    }

    public final void setParentHandle$kotlinx_coroutines_core(ChildHandle childHandle) {
        this._parentHandle.setValue(childHandle);
    }

    @Override // kotlinx.coroutines.Job
    public final void start$ar$ds$f3a3431a_0() {
        int startInternal;
        do {
            startInternal = startInternal(getState$kotlinx_coroutines_core());
            if (startInternal == 0) {
                return;
            }
        } while (startInternal != 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CancellationException toCancellationException(Throwable th, String str) {
        CancellationException cancellationException;
        if (th instanceof CancellationException) {
            cancellationException = (CancellationException) th;
        } else {
            cancellationException = null;
        }
        if (cancellationException == null) {
            if (str == null) {
                str = cancellationExceptionMessage();
            }
            cancellationException = new JobCancellationException(str, th, this);
        }
        return cancellationException;
    }

    public final String toString() {
        return (nameString$kotlinx_coroutines_core() + "{" + stateString$ar$ds(getState$kotlinx_coroutines_core()) + "}") + "@" + DebugStringsKt.getHexAddress(this);
    }

    public final boolean tryWaitForChild(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (ScannerAutoZoomEvent.PredictedArea.invokeOnCompletion$default$ar$ds(childHandleNode.childJob$ar$class_merging, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1) == NonDisposableHandle.INSTANCE) {
            childHandleNode = nextChild(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    protected void onStart() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterCompletion(Object obj) {
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) {
        throw th;
    }

    protected void onCompletionInternal(Object obj) {
    }
}
