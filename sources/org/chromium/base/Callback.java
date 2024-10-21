package org.chromium.base;

import j$.util.Optional;

/* compiled from: PG */
@FunctionalInterface
/* loaded from: classes.dex */
public interface Callback<T> {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Helper {
        static void onBooleanResultFromNative(Callback callback, boolean z) {
            callback.onResult$ar$ds();
        }

        static void onIntResultFromNative(Callback callback, int i) {
            callback.onResult$ar$ds();
        }

        static void onLongResultFromNative(Callback callback, long j) {
            callback.onResult$ar$ds();
        }

        static void onObjectResultFromNative(Callback callback, Object obj) {
            callback.onResult$ar$ds();
        }

        static void onOptionalStringResultFromNative(Callback<Optional<String>> callback, boolean z, String str) {
            if (z) {
                Optional.of(str);
            } else {
                Optional.empty();
            }
            callback.onResult$ar$ds();
        }

        static void onTimeResultFromNative(Callback callback, long j) {
            callback.onResult$ar$ds();
        }

        static void runRunnable(Runnable runnable) {
            runnable.run();
        }
    }

    void onResult$ar$ds();
}
