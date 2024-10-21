package com.google.android.gsf;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GservicesQueryCachingDelegate {
    public final AtomicBoolean mInvalidateCache = new AtomicBoolean();
    private HashMap mStringCache = null;
    public final HashMap mBooleanCache = new HashMap(16, 1.0f);
    private final HashMap mIntCache = new HashMap(16, 1.0f);
    public final HashMap mLongCache = new HashMap(16, 1.0f);
    private final HashMap mFloatCache = new HashMap(16, 1.0f);
    public Object mVersionToken = null;
    private boolean mPreloaded = false;
    private final String[] mPreloadedPrefixes = new String[0];
    private final SpannableUtils$NonCopyableTextSpan mQueryDelegate$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class QueryDelegateException extends Exception {
        public QueryDelegateException() {
        }

        public QueryDelegateException(byte[] bArr) {
            super("Failed to connect to GservicesProvider");
        }
    }

    public static final void getContentResolver$ar$ds(ContentResolver contentResolver) {
        if (contentResolver != null) {
        } else {
            throw new IllegalStateException("ContentResolver needed with GservicesDelegateSupplier.init()");
        }
    }

    public static final Object getValueLocked$ar$ds(Map map, String str, Object obj) {
        if (map.containsKey(str)) {
            Object obj2 = map.get(str);
            if (obj2 != null) {
                return obj2;
            }
            return obj;
        }
        return null;
    }

    public final void ensureCacheInitializedLocked(ContentResolver contentResolver) {
        if (this.mStringCache == null) {
            this.mInvalidateCache.set(false);
            this.mStringCache = new HashMap(16, 1.0f);
            this.mVersionToken = new Object();
            contentResolver.registerContentObserver(GservicesConstants.CONTENT_URI, true, new ContentObserver() { // from class: com.google.android.gsf.GservicesQueryCachingDelegate.1
                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    GservicesQueryCachingDelegate.this.mInvalidateCache.set(true);
                }
            });
            return;
        }
        if (this.mInvalidateCache.getAndSet(false)) {
            this.mStringCache.clear();
            this.mBooleanCache.clear();
            this.mIntCache.clear();
            this.mLongCache.clear();
            this.mFloatCache.clear();
            this.mVersionToken = new Object();
            this.mPreloaded = false;
        }
    }

    public final String getString$ar$ds(ContentResolver contentResolver, String str) {
        String str2;
        getContentResolver$ar$ds(contentResolver);
        synchronized (this) {
            ensureCacheInitializedLocked(contentResolver);
            Object obj = this.mVersionToken;
            String str3 = null;
            if (this.mStringCache.containsKey(str)) {
                String str4 = (String) this.mStringCache.get(str);
                if (str4 != null) {
                    str3 = str4;
                }
                return str3;
            }
            try {
                Cursor query = contentResolver.query(GservicesConstants.CONTENT_URI, null, null, new String[]{str}, null);
                try {
                    if (query != null) {
                        if (query.moveToFirst()) {
                            str2 = query.getString(1);
                            query.close();
                        } else {
                            query.close();
                            str2 = null;
                        }
                        if (str2 != null && str2.equals(null)) {
                            str2 = null;
                        }
                        synchronized (this) {
                            if (obj == this.mVersionToken) {
                                this.mStringCache.put(str, str2);
                            }
                        }
                        if (str2 == null) {
                            return null;
                        }
                        return str2;
                    }
                    throw new QueryDelegateException(null);
                } finally {
                }
            } catch (QueryDelegateException unused) {
                return null;
            }
        }
    }

    public final void putValueAndRemoveFromStringCacheLocked(Object obj, Map map, String str, Object obj2) {
        if (obj == this.mVersionToken) {
            map.put(str, obj2);
            this.mStringCache.remove(str);
        }
    }
}
