package androidx.startup;

import android.content.Context;
import android.os.Trace;
import android.support.v7.app.AppCompatDelegateImpl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppInitializer {
    public static volatile AppInitializer sInstance;
    public static final Object sLock = new Object();
    final Context mContext;
    final Set mDiscovered = new HashSet();
    final Map mInitialized = new HashMap();

    public AppInitializer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public final void doInitialize$ar$ds(Class cls, Set set) {
        if (AppCompatDelegateImpl.Api21Impl.isEnabled()) {
            try {
                AppCompatDelegateImpl.Api21Impl.beginSection(cls.getSimpleName());
            } finally {
                Trace.endSection();
            }
        }
        if (!set.contains(cls)) {
            if (!this.mInitialized.containsKey(cls)) {
                set.add(cls);
                try {
                    Initializer initializer = (Initializer) cls.getDeclaredConstructor(null).newInstance(null);
                    List<Class> dependencies = initializer.dependencies();
                    if (!dependencies.isEmpty()) {
                        for (Class cls2 : dependencies) {
                            if (!this.mInitialized.containsKey(cls2)) {
                                doInitialize$ar$ds(cls2, set);
                            }
                        }
                    }
                    Object create = initializer.create(this.mContext);
                    set.remove(cls);
                    this.mInitialized.put(cls, create);
                } catch (Throwable th) {
                    throw new StartupException(th);
                }
            } else {
                this.mInitialized.get(cls);
            }
            return;
        }
        throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
    }
}
