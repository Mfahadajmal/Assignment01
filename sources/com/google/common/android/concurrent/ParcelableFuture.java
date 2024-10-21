package com.google.common.android.concurrent;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ParcelableFuture implements Parcelable {
    public static final Parcelable.Creator<ParcelableFuture> CREATOR = new ServiceDumpRequestCreator(4);
    public final int callbackId;
    public ListenableFuture future;
    private FutureCallbackViewModel futureListener$ar$class_merging;
    public final Object input;

    public ParcelableFuture(int i, Object obj, ListenableFuture listenableFuture) {
        this.callbackId = i;
        this.input = obj;
        this.future = listenableFuture;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean hasResult() {
        return this.future.isDone();
    }

    public final void notifyListener() {
        FutureCallbackViewModel futureCallbackViewModel = this.futureListener$ar$class_merging;
        if (futureCallbackViewModel != null && hasResult()) {
            try {
                futureCallbackViewModel.sendResult(this, new FutureCallbackViewModel$$ExternalSyntheticLambda1(futureCallbackViewModel, this, ContextDataProvider.getDone(this.future), 4));
            } catch (Error e) {
                e = e;
                futureCallbackViewModel.onFailure(this, e);
            } catch (RuntimeException e2) {
                e = e2;
                futureCallbackViewModel.onFailure(this, e);
            } catch (ExecutionException e3) {
                futureCallbackViewModel.onFailure(this, e3.getCause());
            }
        }
    }

    public final void setListener$ar$class_merging$59ab07de_0(FutureCallbackViewModel futureCallbackViewModel) {
        this.futureListener$ar$class_merging = futureCallbackViewModel;
        notifyListener();
    }

    public final String toString() {
        String str;
        Object obj = this.input;
        if (obj != null) {
            str = "input=" + obj.getClass().getName() + ";";
        } else {
            str = "";
        }
        return "ParcelableFuture(" + str + "future=" + String.valueOf(this.future) + ")";
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @Override // android.os.Parcelable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void writeToParcel(android.os.Parcel r8, int r9) {
        /*
            r7 = this;
            int r9 = r7.callbackId
            r8.writeInt(r9)
            boolean r9 = r7.hasResult()
            r0 = 1
            r1 = 2
            r2 = 0
            if (r9 == 0) goto L25
            com.google.common.util.concurrent.ListenableFuture r9 = r7.future     // Catch: java.lang.Error -> L18 java.lang.RuntimeException -> L1a java.util.concurrent.ExecutionException -> L1c
            java.lang.Object r9 = com.google.common.flogger.context.ContextDataProvider.getDone(r9)     // Catch: java.lang.Error -> L18 java.lang.RuntimeException -> L1a java.util.concurrent.ExecutionException -> L1c
            r3 = r9
            r9 = r0
            r4 = r2
            goto L28
        L18:
            r9 = move-exception
            goto L21
        L1a:
            r9 = move-exception
            goto L21
        L1c:
            r9 = move-exception
            java.lang.Throwable r9 = r9.getCause()
        L21:
            r4 = r9
            r9 = r1
            r3 = r2
            goto L28
        L25:
            r9 = 0
            r3 = r2
            r4 = r3
        L28:
            int r5 = r8.dataPosition()
            java.lang.Object r6 = r7.input     // Catch: java.lang.RuntimeException -> L33
            r8.writeValue(r6)     // Catch: java.lang.RuntimeException -> L33
            r2 = r3
            goto L64
        L33:
            r9 = move-exception
            r8.setDataPosition(r5)
            r8.writeValue(r2)
            java.lang.Object r3 = r7.input
            com.google.common.android.concurrent.UnknownResultException r4 = new com.google.common.android.concurrent.UnknownResultException
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r9 = r9.getMessage()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Parceling failed for type and will be dropped: "
            r5.<init>(r6)
            r5.append(r3)
            java.lang.String r3 = "; "
            r5.append(r3)
            r5.append(r9)
            java.lang.String r9 = r5.toString()
            r4.<init>(r9)
            r9 = r1
        L64:
            int r3 = r8.dataPosition()
            r8.writeInt(r9)     // Catch: java.lang.RuntimeException -> L77
            if (r9 == 0) goto L76
            if (r9 == r0) goto L73
            r8.writeValue(r4)     // Catch: java.lang.RuntimeException -> L77
            return
        L73:
            r8.writeValue(r2)     // Catch: java.lang.RuntimeException -> L77
        L76:
            return
        L77:
            r9 = move-exception
            r8.setDataPosition(r3)
            com.google.common.android.concurrent.UnknownResultException r0 = new com.google.common.android.concurrent.UnknownResultException
            java.lang.String r9 = r9.getMessage()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.lang.String r2 = "Parceling failed for result and will be dropped: "
            java.lang.String r9 = r2.concat(r9)
            r0.<init>(r9)
            r8.writeInt(r1)
            r8.writeValue(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.android.concurrent.ParcelableFuture.writeToParcel(android.os.Parcel, int):void");
    }

    public ParcelableFuture(Parcel parcel) {
        ClassLoader classLoader = getClass().getClassLoader();
        this.callbackId = parcel.readInt();
        this.input = parcel.readValue(classLoader);
        int readInt = parcel.readInt();
        if (readInt == 0) {
            this.future = ContextDataProvider.immediateFailedFuture(new UnknownResultException("ParcelableFuture was Parceled before the Future completed."));
        } else if (readInt == 1) {
            this.future = ContextDataProvider.immediateFuture(parcel.readValue(classLoader));
        } else if (readInt == 2) {
            this.future = ContextDataProvider.immediateFailedFuture((Throwable) parcel.readValue(classLoader));
        } else {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readInt, "Unknown result type: "));
        }
        notifyListener();
    }
}
