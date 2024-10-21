package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK = new Object();

    public static Context wrap(Context context) {
        if (!(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources)) {
            context.getResources();
        }
        return context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        throw null;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        throw null;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        throw null;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        throw null;
    }
}
