package androidx.sqlite.util;

import android.support.v7.app.AppCompatDelegateImpl;
import android.util.Log;
import j$.nio.channels.DesugarChannels;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessLock {
    public static final AppCompatDelegateImpl.Api21Impl Companion$ar$class_merging$22191bb6_0$ar$class_merging = new AppCompatDelegateImpl.Api21Impl();
    public static final Map threadLocksMap = new HashMap();
    private FileChannel lockChannel;
    private final File lockFile;
    public final boolean processLock;
    private final Lock threadLock;

    public ProcessLock(String str, File file, boolean z) {
        File file2;
        Lock lock;
        str.getClass();
        this.processLock = false;
        if (file != null) {
            file2 = new File(file, str.concat(".lck"));
        } else {
            file2 = null;
        }
        this.lockFile = file2;
        Map map = threadLocksMap;
        synchronized (map) {
            Object obj = map.get(str);
            if (obj == null) {
                obj = new ReentrantLock();
                map.put(str, obj);
            }
            lock = (Lock) obj;
        }
        this.threadLock = lock;
    }

    public final void lock(boolean z) {
        this.threadLock.lock();
        if (z) {
            try {
                File file = this.lockFile;
                if (file != null) {
                    File parentFile = file.getParentFile();
                    if (parentFile != null) {
                        parentFile.mkdirs();
                    }
                    FileChannel convertMaybeLegacyFileChannelFromLibrary = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(new FileOutputStream(this.lockFile).getChannel());
                    convertMaybeLegacyFileChannelFromLibrary.lock();
                    this.lockChannel = convertMaybeLegacyFileChannelFromLibrary;
                    return;
                }
                throw new IOException("No lock directory was provided.");
            } catch (IOException e) {
                this.lockChannel = null;
                Log.w("SupportSQLiteLock", "Unable to grab file lock.", e);
            }
        }
    }

    public final void unlock() {
        try {
            FileChannel fileChannel = this.lockChannel;
            if (fileChannel != null) {
                fileChannel.close();
            }
        } catch (IOException unused) {
        }
        this.threadLock.unlock();
    }
}
