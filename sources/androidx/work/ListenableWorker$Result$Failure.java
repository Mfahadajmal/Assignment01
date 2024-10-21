package androidx.work;

import android.support.v7.app.AppCompatDelegateImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListenableWorker$Result$Failure extends AppCompatDelegateImpl.Api33Impl {
    public final Data mOutputData = Data.EMPTY;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.mOutputData.equals(((ListenableWorker$Result$Failure) obj).mOutputData);
        }
        return false;
    }

    public final int hashCode() {
        return 846803280 + this.mOutputData.hashCode();
    }

    public final String toString() {
        return "Failure {mOutputData=" + this.mOutputData + '}';
    }
}
