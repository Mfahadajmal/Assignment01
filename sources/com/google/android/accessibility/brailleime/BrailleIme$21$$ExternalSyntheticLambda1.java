package com.google.android.accessibility.brailleime;

import J.N;
import android.os.Looper;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.apps.tiktok.tracing.Trace;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.collect.ImmutableList;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleIme$21$$ExternalSyntheticLambda1 implements Runnable {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BrailleIme$21$$ExternalSyntheticLambda1(int i) {
        this.switching_field = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.switching_field) {
            case 0:
                BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(44, new Object[0]);
                return;
            case 1:
                BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(46, new Object[0]);
                return;
            case 2:
                ImmutableList immutableList = ContextMenuDialog.ITEM_STRING_IDS;
                return;
            case 3:
            case 4:
                return;
            case 5:
                PhenotypeFlag.invalidateProcessCache();
                return;
            case 6:
                throw new IllegalStateException("Span was closed by an invalid call to SpanEndSignal.run()");
            case 7:
                Object remove = Tracer.traceQueue.remove();
                if (remove == Tracer.UNSET_ASYNC_TRACE) {
                    Tracer.asyncCurrent.pop();
                    return;
                } else {
                    Tracer.asyncCurrent.push((Trace) remove);
                    return;
                }
            case 8:
            case 9:
                return;
            case 10:
                if (ApplicationStatus.sNativeApplicationStateListener != null) {
                    return;
                }
                ApplicationStatus.sNativeApplicationStateListener = new ApplicationStatus.ApplicationStateListener() { // from class: org.chromium.base.ApplicationStatus$3$1
                };
                ApplicationStatus.registerApplicationStateListener(ApplicationStatus.sNativeApplicationStateListener);
                return;
            default:
                if (N.MnfJQqTB()) {
                    if (TraceEvent.ViewHierarchyDumper.sInstance == null) {
                        TraceEvent.ViewHierarchyDumper.sInstance = new TraceEvent.ViewHierarchyDumper();
                    }
                    if (!TraceEvent.ViewHierarchyDumper.sEnabled) {
                        Looper.myQueue().addIdleHandler(TraceEvent.ViewHierarchyDumper.sInstance);
                        TraceEvent.ViewHierarchyDumper.sEnabled = true;
                        return;
                    }
                    return;
                }
                if (TraceEvent.ViewHierarchyDumper.sInstance != null && TraceEvent.ViewHierarchyDumper.sEnabled) {
                    Looper.myQueue().removeIdleHandler(TraceEvent.ViewHierarchyDumper.sInstance);
                    TraceEvent.ViewHierarchyDumper.sEnabled = false;
                    return;
                }
                return;
        }
    }
}
