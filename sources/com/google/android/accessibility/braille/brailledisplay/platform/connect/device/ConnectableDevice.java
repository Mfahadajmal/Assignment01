package com.google.android.accessibility.braille.brailledisplay.platform.connect.device;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ConnectableDevice {
    public final boolean useHid = false;

    public abstract String address();

    public abstract String name();

    public final String toString() {
        return name() + "(" + address() + ")";
    }
}
