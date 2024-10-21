package com.google.android.accessibility.utils.monitor;

import android.content.Context;
import android.hardware.display.DisplayManager;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DisplayMonitor implements DisplayManager.DisplayListener {
    public final DisplayManager displayManager;
    public boolean monitoring = false;
    public boolean defaultDisplayOn = true;
    public final List displayStateChangedListeners = new CopyOnWriteArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DisplayStateChangedListener {
        void onDisplayStateChanged(boolean z);
    }

    public DisplayMonitor(Context context) {
        this.displayManager = (DisplayManager) context.getSystemService("display");
    }

    public final void addDisplayStateChangedListener(DisplayStateChangedListener displayStateChangedListener) {
        this.displayStateChangedListeners.add(displayStateChangedListener);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayAdded(int i) {
        if (i == 0) {
            boolean z = false;
            if (this.displayManager.getDisplay(0).getState() == 2) {
                z = true;
            }
            Iterator it = this.displayStateChangedListeners.iterator();
            while (it.hasNext()) {
                ((DisplayStateChangedListener) it.next()).onDisplayStateChanged(z);
            }
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayChanged(int i) {
        if (i == 0) {
            boolean z = false;
            if (this.displayManager.getDisplay(0).getState() == 2) {
                z = true;
            }
            if (this.defaultDisplayOn != z) {
                this.defaultDisplayOn = z;
                Iterator it = this.displayStateChangedListeners.iterator();
                while (it.hasNext()) {
                    ((DisplayStateChangedListener) it.next()).onDisplayStateChanged(this.defaultDisplayOn);
                }
            }
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayRemoved(int i) {
        if (i == 0) {
            Iterator it = this.displayStateChangedListeners.iterator();
            while (it.hasNext()) {
                ((DisplayStateChangedListener) it.next()).onDisplayStateChanged(false);
            }
        }
    }

    public final void removeDisplayStateChangedListener(DisplayStateChangedListener displayStateChangedListener) {
        this.displayStateChangedListeners.remove(displayStateChangedListener);
    }
}
