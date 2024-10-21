package org.chromium.base;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CommandLine {
    public static final AtomicReference sCommandLine = new AtomicReference();

    private CommandLine() {
    }

    public static CommandLine getInstance() {
        return (CommandLine) sCommandLine.get();
    }

    public abstract String getSwitchValue$ar$ds();

    public abstract boolean hasSwitch$ar$ds();
}
