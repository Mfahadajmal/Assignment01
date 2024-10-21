package com.google.common.android.concurrent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager$$ExternalSyntheticLambda0;
import androidx.collection.ArraySet;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.common.base.Function;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FutureCallbackViewModel extends ViewModel {
    private static volatile Handler mainThreadHandler;
    public final String appVersionMarker;
    public ContextHolder contextHolder;
    public boolean resumedOnce;
    private final SavedStateHandle savedStateHandle;
    public final SparseArrayCompat callbacks = new SparseArrayCompat();
    public final Set pendingFutures = new ArraySet();
    public FragmentManager hostFragmentManager = null;
    public boolean retryDelivery = false;
    public boolean callbacksFrozen = false;
    public boolean isDeliveringResult = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedInstanceStateAppVersionMismatchException extends RuntimeException {
        public SavedInstanceStateAppVersionMismatchException(String str) {
            super(str);
        }
    }

    public FutureCallbackViewModel(SavedStateHandle savedStateHandle) {
        this.resumedOnce = false;
        this.savedStateHandle = savedStateHandle;
        this.appVersionMarker = Function.class.getName() + Fragment.class.getName() + getClass().getName();
        if (savedStateHandle.contains$ar$ds()) {
            Bundle bundle = (Bundle) savedStateHandle.get$ar$ds$d696d055_0();
            this.resumedOnce = true;
            assertStateIsFromThisVersion(bundle);
            Parcelable[] parcelableArray = bundle.getParcelableArray("futures");
            if (parcelableArray != null) {
                for (Parcelable parcelable : parcelableArray) {
                    this.pendingFutures.add((ParcelableFuture) parcelable);
                }
            }
        }
        savedStateHandle.savedStateProviders.put("FutureListenerState", new FragmentManager$$ExternalSyntheticLambda0(this, 6));
    }

    public static final void assertMainThread$ar$ds$72a67533_0() {
        boolean z;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Must be called from the main thread.");
    }

    private final void assertStateIsFromThisVersion(Bundle bundle) {
        String string = bundle.getString("appVersion");
        if (string != null) {
            if (this.appVersionMarker.equals(string)) {
                return;
            }
            throw new SavedInstanceStateAppVersionMismatchException("Got data from old app version: expected=" + this.appVersionMarker + " got=" + string);
        }
        throw new SavedInstanceStateAppVersionMismatchException("Got key but not value from saved state.");
    }

    public final void callOnPending(FutureCallbackRegistry$Callback futureCallbackRegistry$Callback, ParcelableFuture parcelableFuture) {
        deliverResult(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(futureCallbackRegistry$Callback, parcelableFuture, 18));
    }

    public final void deliverResult(Runnable runnable) {
        this.isDeliveringResult = true;
        try {
            runnable.run();
        } finally {
            this.isDeliveringResult = false;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        ArraySet.ElementIterator elementIterator = new ArraySet.ElementIterator();
        while (elementIterator.hasNext()) {
            ParcelableFuture parcelableFuture = (ParcelableFuture) elementIterator.next();
            FutureCallbackRegistry$Callback futureCallbackRegistry$Callback = (FutureCallbackRegistry$Callback) SparseArrayCompatKt.commonGet(this.callbacks, parcelableFuture.callbackId);
            if (futureCallbackRegistry$Callback != null) {
                deliverResult(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(futureCallbackRegistry$Callback, parcelableFuture, 17));
            }
        }
        this.pendingFutures.clear();
    }

    public final void onFailure(ParcelableFuture parcelableFuture, Throwable th) {
        sendResult(parcelableFuture, new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, parcelableFuture, th, 3));
    }

    public final void sendResult(ParcelableFuture parcelableFuture, Runnable runnable) {
        if (this.hostFragmentManager != null) {
            if (mainThreadHandler == null) {
                mainThreadHandler = new Handler(Looper.getMainLooper());
            }
            Handler handler = mainThreadHandler;
            ContextHolder contextHolder = this.contextHolder;
            FutureCallbackViewModel$$ExternalSyntheticLambda1 futureCallbackViewModel$$ExternalSyntheticLambda1 = new FutureCallbackViewModel$$ExternalSyntheticLambda1(this, parcelableFuture, runnable, 2);
            contextHolder.propagateToRunnable$ar$ds(futureCallbackViewModel$$ExternalSyntheticLambda1);
            handler.post(futureCallbackViewModel$$ExternalSyntheticLambda1);
        }
    }

    public final void startCallbacks(FragmentManager fragmentManager) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (fragmentManager != null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z);
        FragmentManager fragmentManager2 = this.hostFragmentManager;
        if (fragmentManager2 != null && fragmentManager != fragmentManager2) {
            z2 = false;
        } else {
            z2 = true;
        }
        ContextDataProvider.checkState(z2);
        if (!this.callbacksFrozen) {
            if (this.savedStateHandle.contains$ar$ds()) {
                Bundle bundle = (Bundle) this.savedStateHandle.get$ar$ds$d696d055_0();
                assertStateIsFromThisVersion(bundle);
                for (int i : bundle.getIntArray("callback_ids")) {
                    if (SparseArrayCompatKt.commonGet(this.callbacks, i) != null) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    ContextDataProvider.checkState(z3, "Didn't re-register callback.");
                }
            }
            this.callbacksFrozen = true;
        }
        if (!this.retryDelivery && this.hostFragmentManager != null) {
            z4 = false;
        }
        if (this.hostFragmentManager == null) {
            this.hostFragmentManager = fragmentManager;
        }
        if (z4) {
            this.retryDelivery = false;
            ArraySet.ElementIterator elementIterator = new ArraySet.ElementIterator();
            while (elementIterator.hasNext()) {
                ParcelableFuture parcelableFuture = (ParcelableFuture) elementIterator.next();
                if (!parcelableFuture.hasResult()) {
                    callOnPending((FutureCallbackRegistry$Callback) SparseArrayCompatKt.commonGet(this.callbacks, parcelableFuture.callbackId), parcelableFuture);
                }
                parcelableFuture.setListener$ar$class_merging$59ab07de_0(this);
            }
        }
    }
}
