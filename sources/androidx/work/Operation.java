package androidx.work;

import android.support.v7.view.WindowCallbackWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Operation {
    public static final Operation$State$SUCCESS SUCCESS = new WindowCallbackWrapper.Api23Impl() { // from class: androidx.work.Operation$State$SUCCESS
        public final String toString() {
            return "SUCCESS";
        }
    };
    public static final Operation$State$IN_PROGRESS IN_PROGRESS = new WindowCallbackWrapper.Api23Impl() { // from class: androidx.work.Operation$State$IN_PROGRESS
        public final String toString() {
            return "IN_PROGRESS";
        }
    };
}
