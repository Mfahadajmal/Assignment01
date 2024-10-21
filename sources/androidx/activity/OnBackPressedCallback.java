package androidx.activity;

import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class OnBackPressedCallback {
    public final CopyOnWriteArrayList cancellables = new CopyOnWriteArrayList();
    public Function0 enabledChangedCallback;
    public boolean isEnabled;

    public OnBackPressedCallback(boolean z) {
        this.isEnabled = z;
    }

    public final void addCancellable(Cancellable cancellable) {
        this.cancellables.add(cancellable);
    }

    public abstract void handleOnBackPressed();

    public final void removeCancellable(Cancellable cancellable) {
        this.cancellables.remove(cancellable);
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
        Function0 function0 = this.enabledChangedCallback;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public void handleOnBackCancelled() {
    }

    public void handleOnBackStarted$ar$ds() {
    }

    public void handleOnBackProgressed(BackEventCompat backEventCompat) {
    }
}
