package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListenerHolder {
    private final Executor executor;
    public volatile Object listener;
    public volatile ListenerKey listenerKey;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ListenerKey {
        private final Object listener;
        private final String listenerType;

        public ListenerKey(Object obj, String str) {
            this.listener = obj;
            this.listenerType = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            if (this.listener == listenerKey.listener && this.listenerType.equals(listenerKey.listenerType)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.listener) * 31) + this.listenerType.hashCode();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Notifier {
        void notifyListener(Object obj);
    }

    public ListenerHolder(Looper looper, Object obj, String str) {
        this.executor = new HandlerExecutor(looper, 0);
        this.listener = obj;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds(str);
        this.listenerKey = new ListenerKey(obj, str);
    }

    public final void notifyListener(Notifier notifier) {
        this.executor.execute(new ListMenuManager$$ExternalSyntheticLambda3(this, notifier, 12, (char[]) null));
    }
}
