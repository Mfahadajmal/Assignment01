package androidx.startup;

import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.support.v7.app.AppCompatDelegateImpl;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.marvin.talkback.R;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class InitializationProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        Context context = getContext();
        if (context != null) {
            if (context.getApplicationContext() != null) {
                if (AppInitializer.sInstance == null) {
                    synchronized (AppInitializer.sLock) {
                        if (AppInitializer.sInstance == null) {
                            AppInitializer.sInstance = new AppInitializer(context);
                        }
                    }
                }
                Class<?> cls = getClass();
                AppInitializer appInitializer = AppInitializer.sInstance;
                try {
                    try {
                        AppCompatDelegateImpl.Api21Impl.beginSection("Startup");
                        Bundle bundle = appInitializer.mContext.getPackageManager().getProviderInfo(new ComponentName(appInitializer.mContext, cls), BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE).metaData;
                        String string = appInitializer.mContext.getString(R.string.androidx_startup);
                        if (bundle != null) {
                            try {
                                HashSet hashSet = new HashSet();
                                for (String str : bundle.keySet()) {
                                    if (string.equals(bundle.getString(str, null))) {
                                        Class<?> cls2 = Class.forName(str);
                                        if (Initializer.class.isAssignableFrom(cls2)) {
                                            appInitializer.mDiscovered.add(cls2);
                                        }
                                    }
                                }
                                Iterator it = appInitializer.mDiscovered.iterator();
                                while (it.hasNext()) {
                                    appInitializer.doInitialize$ar$ds((Class) it.next(), hashSet);
                                }
                            } catch (ClassNotFoundException e) {
                                throw new StartupException(e);
                            }
                        }
                        return true;
                    } catch (PackageManager.NameNotFoundException e2) {
                        throw new StartupException(e2);
                    }
                } finally {
                    Trace.endSection();
                }
            }
            return true;
        }
        throw new StartupException();
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }
}
