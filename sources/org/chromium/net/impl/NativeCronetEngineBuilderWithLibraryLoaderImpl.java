package org.chromium.net.impl;

import android.content.Context;
import org.chromium.net.CronetEngine;
import org.chromium.net.ICronetEngineBuilder;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NativeCronetEngineBuilderWithLibraryLoaderImpl extends NativeCronetEngineBuilderImpl {
    private VersionSafeCallbacks$LibraryLoader mLibraryLoader;

    public NativeCronetEngineBuilderWithLibraryLoaderImpl(Context context) {
        super(context);
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl
    public final VersionSafeCallbacks$LibraryLoader libraryLoader() {
        return this.mLibraryLoader;
    }

    @Override // org.chromium.net.impl.NativeCronetEngineBuilderImpl, org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public final /* bridge */ /* synthetic */ ICronetEngineBuilder setLibraryLoader(CronetEngine.Builder.LibraryLoader libraryLoader) {
        setLibraryLoader$ar$ds(libraryLoader);
        return this;
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl
    public final void setLibraryLoader$ar$ds(CronetEngine.Builder.LibraryLoader libraryLoader) {
        this.mLibraryLoader = new VersionSafeCallbacks$LibraryLoader(libraryLoader);
    }
}
