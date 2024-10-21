package androidx.work;

import android.support.v7.app.AppCompatDelegateImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListenableWorker$Result$Success extends AppCompatDelegateImpl.Api33Impl {
    public final Data mOutputData;

    public ListenableWorker$Result$Success(Data data) {
        this.mOutputData = data;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.mOutputData.equals(((ListenableWorker$Result$Success) obj).mOutputData);
        }
        return false;
    }

    public final int hashCode() {
        return (-1876823561) + this.mOutputData.hashCode();
    }

    public final String toString() {
        return "Success {mOutputData=" + this.mOutputData + '}';
    }

    public ListenableWorker$Result$Success() {
        this(Data.EMPTY);
    }
}
