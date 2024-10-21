package com.google.android.play.core.splitcompat.loader;

import java.io.File;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ClassLoaderInjector {
    boolean installDexes(ClassLoader classLoader, File file, File file2, boolean z);

    void installNativeLibraries(ClassLoader classLoader, Set set);
}
