package com.google.android.libraries.performance.primes.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.foreground.DebouncedForegroundSignalAdapter;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessLifecycleOwner {
    public Handler handler;
    public NoPiiString lastPausedActivity;
    public int startedCounter = 0;
    public int resumedCounter = 0;
    public boolean pauseSent = true;
    public boolean stopSent = true;
    public final Set listeners = new HashSet();
    public Runnable delayedPauseRunnable = new TrainingActivity$$ExternalSyntheticLambda1(this, 16);

    public ProcessLifecycleOwner(Context context) {
        this.handler = null;
        this.handler = new Handler(context.getMainLooper());
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new EmptyCallbacks() { // from class: com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner.1
            @Override // com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner.EmptyCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityPaused(Activity activity) {
                ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.this;
                int i = processLifecycleOwner.resumedCounter - 1;
                processLifecycleOwner.resumedCounter = i;
                if (i == 0) {
                    processLifecycleOwner.lastPausedActivity = NoPiiString.fromClass(activity.getClass());
                    ((Handler) ContextDataProvider.verifyNotNull(ProcessLifecycleOwner.this.handler)).postDelayed((Runnable) ContextDataProvider.verifyNotNull(ProcessLifecycleOwner.this.delayedPauseRunnable), 700L);
                }
            }

            @Override // com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner.EmptyCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityResumed(Activity activity) {
                ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.this;
                int i = processLifecycleOwner.resumedCounter + 1;
                processLifecycleOwner.resumedCounter = i;
                if (i == 1) {
                    if (processLifecycleOwner.pauseSent) {
                        Iterator it = processLifecycleOwner.listeners.iterator();
                        while (it.hasNext()) {
                            ((DebouncedForegroundSignalAdapter) it.next()).onActivityResumed(NoPiiString.fromClass(activity.getClass()));
                        }
                        ProcessLifecycleOwner.this.pauseSent = false;
                        return;
                    }
                    ((Handler) ContextDataProvider.verifyNotNull(processLifecycleOwner.handler)).removeCallbacks((Runnable) ContextDataProvider.verifyNotNull(ProcessLifecycleOwner.this.delayedPauseRunnable));
                }
            }

            @Override // com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner.EmptyCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityStarted(Activity activity) {
                ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.this;
                int i = processLifecycleOwner.startedCounter + 1;
                processLifecycleOwner.startedCounter = i;
                if (i == 1 && processLifecycleOwner.stopSent) {
                    for (DebouncedForegroundSignalAdapter debouncedForegroundSignalAdapter : processLifecycleOwner.listeners) {
                        NoPiiString.fromClass(activity.getClass());
                    }
                    ProcessLifecycleOwner.this.stopSent = false;
                }
            }

            @Override // com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner.EmptyCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityStopped(Activity activity) {
                r0.startedCounter--;
                ProcessLifecycleOwner.this.dispatchStopIfNeeded(NoPiiString.fromClass(activity.getClass()));
            }
        });
    }

    public final void dispatchStopIfNeeded(NoPiiString noPiiString) {
        if (this.startedCounter == 0 && this.pauseSent) {
            for (DebouncedForegroundSignalAdapter debouncedForegroundSignalAdapter : this.listeners) {
            }
            this.stopSent = true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class EmptyCallbacks implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }
    }
}
