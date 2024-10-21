package org.chromium.net.impl;

import org.chromium.net.CronetEngine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VersionSafeCallbacks$LibraryLoader extends CronetEngine.Builder.LibraryLoader {
    private final CronetEngine.Builder.LibraryLoader mWrappedLoader;

    public VersionSafeCallbacks$LibraryLoader(CronetEngine.Builder.LibraryLoader libraryLoader) {
        this.mWrappedLoader = libraryLoader;
    }

    @Override // org.chromium.net.CronetEngine.Builder.LibraryLoader
    public final void loadLibrary(String str) {
        this.mWrappedLoader.loadLibrary(str);
    }
}
