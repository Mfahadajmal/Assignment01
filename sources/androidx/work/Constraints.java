package androidx.work;

import _COROUTINE._BOUNDARY;
import android.net.NetworkRequest;
import android.net.Uri;
import android.support.v7.app.AppCompatDelegateImpl;
import androidx.work.impl.utils.NetworkRequestCompat;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Constraints {
    public static final Constraints NONE = new Constraints(1);
    public final long contentTriggerMaxDelayMillis;
    public final long contentTriggerUpdateDelayMillis;
    public final Set contentUriTriggers;
    public final NetworkRequestCompat requiredNetworkRequestCompat;
    public final int requiredNetworkType$ar$edu;
    public final boolean requiresBatteryNotLow;
    public final boolean requiresCharging;
    public final boolean requiresDeviceIdle;
    public final boolean requiresStorageNotLow;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContentUriTrigger {
        public final boolean isTriggeredForDescendants;
        public final Uri uri;

        public ContentUriTrigger(Uri uri, boolean z) {
            this.uri = uri;
            this.isTriggeredForDescendants = z;
        }

        public final boolean equals(Object obj) {
            Class<?> cls;
            if (this == obj) {
                return true;
            }
            Class<?> cls2 = getClass();
            if (obj != null) {
                cls = obj.getClass();
            } else {
                cls = null;
            }
            if (!Intrinsics.areEqual(cls2, cls)) {
                return false;
            }
            obj.getClass();
            ContentUriTrigger contentUriTrigger = (ContentUriTrigger) obj;
            if (Intrinsics.areEqual(this.uri, contentUriTrigger.uri) && this.isTriggeredForDescendants == contentUriTrigger.isTriggeredForDescendants) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (this.uri.hashCode() * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.isTriggeredForDescendants);
        }
    }

    public Constraints(int i) {
        EmptySet emptySet = EmptySet.INSTANCE;
        this.requiredNetworkRequestCompat = new NetworkRequestCompat((byte[]) null);
        this.requiredNetworkType$ar$edu = 1;
        this.requiresCharging = false;
        this.requiresDeviceIdle = false;
        this.requiresBatteryNotLow = false;
        this.requiresStorageNotLow = false;
        this.contentTriggerUpdateDelayMillis = -1L;
        this.contentTriggerMaxDelayMillis = -1L;
        this.contentUriTriggers = emptySet;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Intrinsics.areEqual(getClass(), obj.getClass())) {
            return false;
        }
        Constraints constraints = (Constraints) obj;
        if (this.requiresCharging != constraints.requiresCharging || this.requiresDeviceIdle != constraints.requiresDeviceIdle || this.requiresBatteryNotLow != constraints.requiresBatteryNotLow || this.requiresStorageNotLow != constraints.requiresStorageNotLow || this.contentTriggerUpdateDelayMillis != constraints.contentTriggerUpdateDelayMillis || this.contentTriggerMaxDelayMillis != constraints.contentTriggerMaxDelayMillis || !Intrinsics.areEqual(getRequiredNetworkRequest(), constraints.getRequiredNetworkRequest()) || this.requiredNetworkType$ar$edu != constraints.requiredNetworkType$ar$edu) {
            return false;
        }
        return Intrinsics.areEqual(this.contentUriTriggers, constraints.contentUriTriggers);
    }

    public final NetworkRequest getRequiredNetworkRequest() {
        return (NetworkRequest) this.requiredNetworkRequestCompat.wrapped;
    }

    public final boolean hasContentUriTriggers() {
        if (!this.contentUriTriggers.isEmpty()) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.requiredNetworkType$ar$edu) * 31;
        long j = this.contentTriggerMaxDelayMillis;
        Set set = this.contentUriTriggers;
        long j2 = this.contentTriggerUpdateDelayMillis;
        int hashCode = ((((((((((((ArtificialStackFrames$ar$MethodMerging$dc56d17a_0 + (this.requiresCharging ? 1 : 0)) * 31) + (this.requiresDeviceIdle ? 1 : 0)) * 31) + (this.requiresBatteryNotLow ? 1 : 0)) * 31) + (this.requiresStorageNotLow ? 1 : 0)) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + set.hashCode();
        NetworkRequest requiredNetworkRequest = getRequiredNetworkRequest();
        if (requiredNetworkRequest != null) {
            i = requiredNetworkRequest.hashCode();
        } else {
            i = 0;
        }
        return (hashCode * 31) + i;
    }

    public final String toString() {
        return "Constraints{requiredNetworkType=" + ((Object) AppCompatDelegateImpl.Api33Impl.toStringGenerated69893d619e9da448(this.requiredNetworkType$ar$edu)) + ", requiresCharging=" + this.requiresCharging + ", requiresDeviceIdle=" + this.requiresDeviceIdle + ", requiresBatteryNotLow=" + this.requiresBatteryNotLow + ", requiresStorageNotLow=" + this.requiresStorageNotLow + ", contentTriggerUpdateDelayMillis=" + this.contentTriggerUpdateDelayMillis + ", contentTriggerMaxDelayMillis=" + this.contentTriggerMaxDelayMillis + ", contentUriTriggers=" + this.contentUriTriggers + ", }";
    }

    public Constraints(NetworkRequestCompat networkRequestCompat, int i, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2, Set set) {
        this.requiredNetworkRequestCompat = networkRequestCompat;
        this.requiredNetworkType$ar$edu = i;
        this.requiresCharging = z;
        this.requiresDeviceIdle = z2;
        this.requiresBatteryNotLow = z3;
        this.requiresStorageNotLow = z4;
        this.contentTriggerUpdateDelayMillis = j;
        this.contentTriggerMaxDelayMillis = j2;
        this.contentUriTriggers = set;
    }

    public Constraints(Constraints constraints) {
        constraints.getClass();
        this.requiresCharging = constraints.requiresCharging;
        this.requiresDeviceIdle = constraints.requiresDeviceIdle;
        this.requiredNetworkRequestCompat = constraints.requiredNetworkRequestCompat;
        this.requiredNetworkType$ar$edu = constraints.requiredNetworkType$ar$edu;
        this.requiresBatteryNotLow = constraints.requiresBatteryNotLow;
        this.requiresStorageNotLow = constraints.requiresStorageNotLow;
        this.contentUriTriggers = constraints.contentUriTriggers;
        this.contentTriggerUpdateDelayMillis = constraints.contentTriggerUpdateDelayMillis;
        this.contentTriggerMaxDelayMillis = constraints.contentTriggerMaxDelayMillis;
    }
}
