package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Storage {
    public static Storage sInstance;
    public static final Lock sLk = new ReentrantLock();
    private final Lock mLk = new ReentrantLock();
    private final SharedPreferences mPrefs;

    public Storage(Context context) {
        this.mPrefs = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public final String getFromStore(String str) {
        this.mLk.lock();
        try {
            return this.mPrefs.getString(str, null);
        } finally {
            this.mLk.unlock();
        }
    }
}
